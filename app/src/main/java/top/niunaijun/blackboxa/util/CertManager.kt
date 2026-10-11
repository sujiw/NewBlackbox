package top.niunaijun.blackboxa.util

import android.content.Intent
import android.security.KeyChain
import top.niunaijun.blackbox.utils.ShellUtils
import top.niunaijun.blackboxa.app.App
import java.io.ByteArrayInputStream
import java.io.File
import java.security.KeyStore
import java.security.MessageDigest
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.security.auth.x500.X500Principal

open class CertException(message: String) : Exception(message)

class CertPasswordException(message: String) : CertException(message)

/**
 * Root CA (抓包根证书) manager.
 *
 * Two install paths, honestly reported to the user:
 *
 *  1. User CA (no root needed): installed through the system KeyChain UI
 *     (Settings -> Encryption & credentials). Only trusted by apps that opt
 *     in (targetSdk <= 23) or explicitly trust user certificates. Modern
 *     apps (targetSdk >= 24) ignore user CAs unless they opt in.
 *
 *  2. System root CA (root needed): copied into /system/etc/security/cacerts,
 *     /apex/com.android.conscrypt/cacerts (Android 14+) and the user CA
 *     store. Trusted by every app, including the virtual ones.
 */
object CertManager {

    private val SYSTEM_CA_DIRS = listOf(
        "/system/etc/security/cacerts",
        "/apex/com.android.conscrypt/cacerts"
    )
    private const val USER_CA_DIR = "/data/misc/user/0/cacerts-added"

    const val ACTION_INSTALL_CREDENTIAL = "android.credentials.INSTALL"

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    // ---------------- parse ----------------

    fun parseCertificate(uri: android.net.Uri, password: String? = null): List<X509Certificate> {
        val context = App.getContext()
        val bytes = try {
            context.contentResolver.openInputStream(uri)?.use { it.readBytes() }
        } catch (e: Exception) {
            null
        } ?: throw CertException("cannot read file")
        return parseCertificate(bytes, password)
    }

    fun parseCertificate(bytes: ByteArray, password: String? = null): List<X509Certificate> {
        val text = runCatching { String(bytes, Charsets.UTF_8) }.getOrDefault("")
        if (text.contains("-----BEGIN CERTIFICATE-----") ||
            text.contains("-----BEGIN TRUSTED CERTIFICATE-----")
        ) {
            return parsePem(text)
        }
        if (bytes.isNotEmpty() && bytes[0] == 0x30.toByte()) {
            try {
                val cf = CertificateFactory.getInstance("X.509")
                return listOf(
                    cf.generateCertificate(ByteArrayInputStream(bytes)) as X509Certificate
                )
            } catch (e: Exception) {
                // not a plain DER certificate, maybe a keystore
            }
        }
        for (type in arrayOf("PKCS12", "BKS")) {
            try {
                val ks = KeyStore.getInstance(type)
                ks.load(ByteArrayInputStream(bytes), password?.toCharArray())
                val aliases = ks.aliases()
                val certs = mutableListOf<X509Certificate>()
                while (aliases.hasMoreElements()) {
                    val c = ks.getCertificate(aliases.nextElement())
                    if (c is X509Certificate) certs.add(c)
                }
                if (certs.isNotEmpty()) return certs
            } catch (e: Exception) {
                if (password.isNullOrEmpty() && looksLikeKeystore(bytes)) {
                    throw CertPasswordException("keystore password required")
                }
            }
        }
        throw CertException("unsupported format, PEM/DER/PKCS12/BKS only")
    }

    private fun looksLikeKeystore(bytes: ByteArray): Boolean {
        if (bytes.size < 4) return false
        if (bytes[0] != 0x30.toByte()) return false
        val len = bytes[1].toInt() and 0xFF
        return len > 0x80
    }

    private fun parsePem(text: String): List<X509Certificate> {
        val regex = Regex(
            "-----BEGIN (?:TRUSTED )?CERTIFICATE-----[\\s\\S]*?-----END (?:TRUSTED )?CERTIFICATE-----"
        )
        val blocks = regex.findAll(text).toList()
        if (blocks.isEmpty()) throw CertException("no certificate found in PEM")
        val cf = CertificateFactory.getInstance("X.509")
        return blocks.map { block ->
            val body = block.value.lines()
                .filterNot { it.startsWith("-----") }
                .joinToString("")
            val der = android.util.Base64.decode(body, android.util.Base64.DEFAULT)
            cf.generateCertificate(ByteArrayInputStream(der)) as X509Certificate
        }
    }

    // ---------------- naming ----------------

    /**
     * Android CA file name: openssl x509 -subject_hash_old equivalent, e.g. "9a5ba575.0"
     */
    fun caFileName(cert: X509Certificate): String =
        String.format(Locale.US, "%08x.0", nameHashOld(cert.subjectX500Principal))

    private fun nameHashOld(principal: X500Principal): Long {
        val md = MessageDigest.getInstance("SHA-1").digest(principal.encoded)
        return ((md[0].toLong() and 0xFF)) or
            ((md[1].toLong() and 0xFF) shl 8) or
            ((md[2].toLong() and 0xFF) shl 16) or
            ((md[3].toLong() and 0xFF) shl 24)
    }

    // ---------------- user CA (no root) ----------------

    /**
     * System KeyChain install intent. Puts the CA into the user trust store
     * through the standard system UI, no root required.
     * Only the root/self-signed CA is installed; the system UI takes a single
     * certificate via EXTRA_CERTIFICATE.
     */
    fun userCertInstallIntent(certs: List<X509Certificate>, alias: String): Intent {
        val intent = try {
            KeyChain.createInstallIntent()
        } catch (e: Throwable) {
            Intent(ACTION_INSTALL_CREDENTIAL)
        }
        intent.action = ACTION_INSTALL_CREDENTIAL
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(KeyChain.EXTRA_NAME, alias)
        intent.putExtra(KeyChain.EXTRA_CERTIFICATE, pickRootCa(certs).encoded)
        return intent
    }

    /** Self-signed cert of the chain, or the first one when unknown. */
    private fun pickRootCa(certs: List<X509Certificate>): X509Certificate =
        certs.firstOrNull { it.subjectX500Principal == it.issuerX500Principal } ?: certs[0]

    // ---------------- system store (root) ----------------

    fun hasRoot(): Boolean = ShellUtils.checkRootPermission()

    /**
     * Install into the real system/user CA stores via su.
     * Returns per-target results: "path/filename" to Boolean.
     */
    fun installSystem(certs: List<X509Certificate>): Map<String, Boolean> {
        if (certs.isEmpty()) return emptyMap()
        val tmpDir = File(App.getContext().cacheDir, "ca_tmp").apply { mkdirs() }
        val sb = StringBuilder()
        sb.append("mount -o rw,remount / >/dev/null 2>&1; ")
        sb.append("mount -o rw,remount /system >/dev/null 2>&1; ")
        val pairs = mutableListOf<Pair<String, String>>()
        for (cert in certs) {
            val name = caFileName(cert)
            val tmp = File(tmpDir, name)
            tmp.writeBytes(cert.encoded)
            for (dir in SYSTEM_CA_DIRS + USER_CA_DIR) {
                pairs.add(dir to name)
                sb.append("cp ${tmp.absolutePath} $dir/$name >/dev/null 2>&1 && ")
                sb.append("chmod 644 $dir/$name >/dev/null 2>&1 && ")
                sb.append("chown root:root $dir/$name >/dev/null 2>&1; ")
            }
        }
        ShellUtils.execCommand(sb.toString(), true)
        return verifySystem(pairs)
    }

    fun removeSystem(certs: List<X509Certificate>): Map<String, Boolean> {
        if (certs.isEmpty()) return emptyMap()
        val sb = StringBuilder()
        sb.append("mount -o rw,remount / >/dev/null 2>&1; ")
        val pairs = mutableListOf<Pair<String, String>>()
        for (cert in certs) {
            val name = caFileName(cert)
            for (dir in SYSTEM_CA_DIRS + USER_CA_DIR) {
                pairs.add(dir to name)
                sb.append("rm -f $dir/$name >/dev/null 2>&1; ")
            }
        }
        ShellUtils.execCommand(sb.toString(), true)
        return verifySystem(pairs)
    }

    /** Check whether the given CA files already exist in the system stores. */
    fun checkSystem(certs: List<X509Certificate>): Map<String, Boolean> {
        val pairs = mutableListOf<Pair<String, String>>()
        for (cert in certs) {
            val name = caFileName(cert)
            for (dir in SYSTEM_CA_DIRS + USER_CA_DIR) {
                pairs.add(dir to name)
            }
        }
        return verifySystem(pairs)
    }

    private fun verifySystem(pairs: List<Pair<String, String>>): Map<String, Boolean> {
        val out = LinkedHashMap<String, Boolean>()
        for ((dir, name) in pairs) {
            val result = ShellUtils.execCommand("ls $dir/$name", true)
            out["$dir/$name"] = result.result == 0 &&
                (result.successMsg?.contains(name) == true)
        }
        return out
    }

    // ---------------- info ----------------

    fun certInfo(cert: X509Certificate): String = buildString {
        appendLine("Subject: ${cert.subjectDN.name}")
        appendLine("Issuer: ${cert.issuerDN.name}")
        appendLine("Serial: ${cert.serialNumber.toString(16).uppercase(Locale.US)}")
        appendLine(
            "Valid: ${dateFormat.format(Date(cert.notBefore.time))} ~ ${
                dateFormat.format(Date(cert.notAfter.time))
            }"
        )
        appendLine("SigAlg: ${cert.sigAlgName}")
        appendLine("SHA-256: ${sha256(cert.encoded)}")
        append("CA file: ${caFileName(cert)}")
    }

    fun toPem(cert: X509Certificate): String {
        val b64 = android.util.Base64.encodeToString(cert.encoded, android.util.Base64.NO_WRAP)
        return "-----BEGIN CERTIFICATE-----\n" +
            b64.chunked(64).joinToString("\n") +
            "\n-----END CERTIFICATE-----"
    }

    fun sha256(bytes: ByteArray): String =
        MessageDigest.getInstance("SHA-256").digest(bytes)
            .joinToString(":") { String.format("%02X", it) }
}
