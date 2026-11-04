package top.niunaijun.probe

import android.app.Activity
import android.app.ActivityManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.content.Intent
import android.media.AudioManager
import android.net.wifi.WifiManager
import android.opengl.EGL14
import android.opengl.EGLConfig
import android.opengl.GLES20
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Parcel
import android.os.StatFs
import android.provider.Settings
import android.telephony.TelephonyManager
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import java.io.File
import java.security.MessageDigest
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * 设备指纹探针：把 guest 进程实际读到的系统值全部列出来，用于核对容器注入的档案。
 *
 * 覆盖 Build / VERSION / ABI / 显示 / 语言时区 / android_id / 电话 / WiFi /
 * 电池 / 音量 / 内存存储 / proc 文件 / GL 字符串 / 启动来源。
 */
class MainActivity : Activity() {

    private lateinit var content: TextView

    /** GAID 要通过绑定 GMS 服务异步取，取到后回填到下一次渲染。 */
    private var gaidId: String? = null
    private var gaidLimit: String? = null
    private var gaidState: String? = null

    /** Play 安装来源同样是异步 bind 取。 */
    private var referrerValue: String? = null
    private var referrerExtra: String? = null
    private var referrerState: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bar = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }
        val refresh = Button(this).apply { text = "刷新" }
        val copy = Button(this).apply { text = "复制" }
        bar.addView(refresh)
        bar.addView(copy)

        content = TextView(this).apply {
            typeface = android.graphics.Typeface.MONOSPACE
            textSize = 10.5f
            setTextIsSelectable(true)
            setPadding(16, 16, 16, 16)
        }

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            addView(bar)
            addView(
                ScrollView(this@MainActivity).apply { addView(content) },
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f)
            )
        }
        setContentView(root)

        refresh.setOnClickListener { content.text = collect() }
        copy.setOnClickListener {
            val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.setPrimaryClip(ClipData.newPlainText("device-probe", content.text))
            Toast.makeText(this, "copied", Toast.LENGTH_SHORT).show()
        }
        content.text = collect()
        fetchGaidAsync()
        fetchReferrerAsync()
    }

    private fun collect(): String {
        val sb = StringBuilder()
        val started = System.currentTimeMillis()

        fun section(name: String, block: () -> Unit) {
            sb.append("== ").append(name).append(" ==\n")
            try {
                block()
            } catch (t: Throwable) {
                sb.append("  ! ").append(t.javaClass.simpleName).append(": ")
                    .append(t.message).append('\n')
            }
            sb.append('\n')
        }

        fun line(key: String, value: Any?) {
            val text = value?.toString() ?: "null"
            sb.append("  ").append(key.padEnd(24)).append(": ").append(text).append('\n')
        }

        fun attempt(key: String, block: () -> Any?) {
            try {
                line(key, block())
            } catch (t: Throwable) {
                line(key, "! " + t.javaClass.simpleName + ": " + t.message)
            }
        }

        section("App") {
            line("package", packageName)
            line("pid", android.os.Process.myPid())
            line("process", currentProcessName())
            line("uptimeMs", android.os.SystemClock.elapsedRealtime())
            line("referrer", referrer())
        }

        section("Build") {
            line("BRAND", Build.BRAND)
            line("MODEL", Build.MODEL)
            line("MANUFACTURER", Build.MANUFACTURER)
            line("DEVICE", Build.DEVICE)
            line("PRODUCT", Build.PRODUCT)
            line("BOARD", Build.BOARD)
            line("HARDWARE", Build.HARDWARE)
            line("ID", Build.ID)
            line("DISPLAY", Build.DISPLAY)
            line("TYPE", Build.TYPE)
            line("TAGS", Build.TAGS)
            line("BOOTLOADER", Build.BOOTLOADER)
            if (Build.VERSION.SDK_INT >= 26) {
                line("SERIAL", try { Build.getSerial() } catch (t: Throwable) { "<${t.javaClass.simpleName}>" })
            }
            if (Build.VERSION.SDK_INT >= 31) {
                line("SOC_MANUFACTURER", Build.SOC_MANUFACTURER)
                line("SOC_MODEL", Build.SOC_MODEL)
            }
            line("FINGERPRINT", Build.FINGERPRINT)
        }

        section("VERSION") {
            line("RELEASE", Build.VERSION.RELEASE)
            line("SDK_INT", Build.VERSION.SDK_INT)
            line("CODENAME", Build.VERSION.CODENAME)
            line("INCREMENTAL", Build.VERSION.INCREMENTAL)
            line("SECURITY_PATCH", Build.VERSION.SECURITY_PATCH)
        }

        section("ABI") {
            line("SUPPORTED_ABIS", Build.SUPPORTED_ABIS.joinToString())
            line("SUPPORTED_32_BIT", Build.SUPPORTED_32_BIT_ABIS.joinToString())
            line("SUPPORTED_64_BIT", Build.SUPPORTED_64_BIT_ABIS.joinToString())
            line("os.arch", System.getProperty("os.arch"))
            line("os.name", System.getProperty("os.name"))
            line("java.vm.version", System.getProperty("java.vm.version"))
        }

        section("Display") {
            val metrics = resources.displayMetrics
            val config = resources.configuration
            line("widthPixels", metrics.widthPixels)
            line("heightPixels", metrics.heightPixels)
            line("densityDpi", metrics.densityDpi)
            line("density", metrics.density)
            line("xdpi / ydpi", "${metrics.xdpi} / ${metrics.ydpi}")
            line("screenWidthDp", config.screenWidthDp)
            line("screenHeightDp", config.screenHeightDp)
            line("smallestWidthDp", config.smallestScreenWidthDp)
            line("orientation", config.orientation)
            line("screenLayout", Integer.toHexString(config.screenLayout))
            line("densityDpi(config)", config.densityDpi)
        }

        section("Locale / TimeZone") {
            val locale = Locale.getDefault()
            line("Locale", locale.toString())
            line("language", locale.language)
            line("country", locale.country)
            line("TimeZone", TimeZone.getDefault().id)
            line("now", java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.US)
                .format(java.util.Date()))
        }

        section("Settings") {
            line("android_id", Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID))
            line("device_name", Settings.Global.getString(contentResolver, "device_name"))
            line("bluetooth_name", Settings.Secure.getString(contentResolver, "bluetooth_name"))
        }

        section("Telephony") {
            val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            line("phoneType", tm.phoneType)
            line("simState", tm.simState)
            line("networkType", tm.networkType)
            line("networkOperatorName", tm.networkOperatorName)
            line("networkOperator", tm.networkOperator)
            line("simOperatorName", tm.simOperatorName)
            line("simOperator", tm.simOperator)
            line("simCountryIso", tm.simCountryIso)
            line("networkCountryIso", tm.networkCountryIso)
            line("isNetworkRoaming", tm.isNetworkRoaming)
            attempt("deviceId") { tm.deviceId }
            attempt("imei(slot0)") { if (Build.VERSION.SDK_INT >= 26) tm.getImei(0) else null }
            attempt("imei(slot1)") { if (Build.VERSION.SDK_INT >= 26) tm.getImei(1) else null }
            attempt("meid") { if (Build.VERSION.SDK_INT >= 26) tm.getMeid() else null }
            attempt("subscriberId(IMSI)") { tm.subscriberId }
            attempt("simSerial(ICCID)") { tm.simSerialNumber }
            attempt("line1Number") { tm.line1Number }
        }

        section("WiFi") {
            val wm = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            @Suppress("DEPRECATION")
            val info = wm.connectionInfo
            line("SSID", info?.ssid)
            line("BSSID", info?.bssid)
            @Suppress("DEPRECATION")
            line("MAC", info?.macAddress)
            @Suppress("DEPRECATION")
            line("ipAddress", intToIp(info?.ipAddress ?: 0))
        }

        section("Battery / Audio") {
            val intent = registerReceiver(null, android.content.IntentFilter(Intent.ACTION_BATTERY_CHANGED))
            line("level", intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1))
            line("scale", intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1))
            line("status", intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1))
            line("plugged", intent?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1))
            line("temperature", intent?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1))
            line("voltage", intent?.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1))
            val am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            line("volume", am.getStreamVolume(AudioManager.STREAM_MUSIC))
            line("volumeMax", am.getStreamMaxVolume(AudioManager.STREAM_MUSIC))
        }

        section("Memory / Storage") {
            val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val mem = ActivityManager.MemoryInfo()
            am.getMemoryInfo(mem)
            line("totalMem", mem.totalMem)
            line("availMem", mem.availMem)
            line("lowMemory", mem.lowMemory)
            val stat = StatFs(filesDir.absolutePath)
            line("data.totalBytes", stat.totalBytes)
            line("data.availableBytes", stat.availableBytes)
            line("data.blockSize", stat.blockSizeLong)
        }

        section("proc") {
            val cpu = File("/proc/cpuinfo")
            line("cpuinfo.exists", cpu.exists())
            line("cpuinfo.length", cpu.length())
            val cpuText = try {
                cpu.readText()
            } catch (t: Throwable) {
                line("cpuinfo.read", "! " + t.javaClass.simpleName + ": " + t.message)
                ""
            }
            cpuText.lineSequence().take(2).forEachIndexed { index, text ->
                line("cpuinfo[$index]", text)
            }
            val memText = try {
                File("/proc/meminfo").readText()
            } catch (t: Throwable) {
                line("meminfo.read", "! " + t.javaClass.simpleName + ": " + t.message)
                ""
            }
            memText.lineSequence().take(3).forEach { text ->
                line("meminfo", text.trim())
            }
        }

        section("Install Referrer") {
            line("state", referrerState ?: "(not requested)")
            line("install_referrer", referrerValue ?: "(null)")
            line("timestamps", referrerExtra ?: "(null)")
        }

        section("GAID") {
            line("state", gaidState ?: "(not requested)")
            line("advertising_id", gaidId ?: "(null)")
            line("limitAdTracking", gaidLimit ?: "(null)")
        }

        section("Signature / Installer") {
            line("cert_sha256", certSha256(packageName))
            line("signature_count", signatureCount(packageName))
            if (Build.VERSION.SDK_INT >= 30) {
                val info = packageManager.getInstallSourceInfo(packageName)
                line("installer", info.installingPackageName)
                line("initiating", info.initiatingPackageName)
                line("originating", info.originatingPackageName)
            } else {
                @Suppress("DEPRECATION")
                line("installer", packageManager.getInstallerPackageName(packageName))
            }
            val pkgInfo = packageManager.getPackageInfo(packageName, 0)
            line("firstInstallTime", pkgInfo.firstInstallTime)
            line("lastUpdateTime", pkgInfo.lastUpdateTime)
        }

        section("Sensors") {
            val sm = getSystemService(Context.SENSOR_SERVICE) as android.hardware.SensorManager
            val sensors = sm.getSensorList(android.hardware.Sensor.TYPE_ALL)
            line("count", sensors.size)
            sensors.take(8).forEach { sensor ->
                line(sensor.type.toString(), sensor.name + " / " + sensor.vendor)
            }
        }

        section("proc self") {
            val status = try {
                File("/proc/self/status").readLines()
            } catch (t: Throwable) {
                line("status.read", "! " + t.javaClass.simpleName + ": " + t.message)
                emptyList<String>()
            }
            status.filter {
                it.startsWith("Name:") || it.startsWith("Pid:") || it.startsWith("PPid:")
                    || it.startsWith("Threads:") || it.startsWith("VmRSS:")
                    || it.startsWith("VmSize:") || it.startsWith("Seccomp:")
            }.forEach { line("status", it.trim()) }
            val cmdline = try {
                File("/proc/self/cmdline").readBytes()
                    .toString(Charsets.UTF_8).trim('\u0000')
            } catch (t: Throwable) {
                "! " + t.javaClass.simpleName
            }
            line("cmdline", cmdline)
        }

        section("GPU") {
            gpuStrings { k, v -> line(k, v) }
        }

        sb.append("---- collected in ").append(System.currentTimeMillis() - started).append("ms\n")
        return sb.toString()
    }

    /** EGL 离屏上下文里读 GL 字符串；容器会替换 GLES20.glGetString 的 RENDERER / VENDOR。 */
    private fun gpuStrings(line: (String, Any?) -> Unit) {
        val display = EGL14.eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY)
        if (display == EGL14.EGL_NO_DISPLAY) {
            line("egl", "no display")
            return
        }
        val version = IntArray(2)
        if (!EGL14.eglInitialize(display, version, 0, version, 1)) {
            line("eglInitialize", "failed")
            return
        }
        try {
            val configs = arrayOfNulls<EGLConfig>(1)
            val count = IntArray(1)
            val renderable = intArrayOf(
                EGL14.EGL_RENDERABLE_TYPE, EGL14.EGL_OPENGL_ES2_BIT,
                EGL14.EGL_SURFACE_TYPE, EGL14.EGL_PBUFFER_BIT,
                EGL14.EGL_NONE
            )
            EGL14.eglChooseConfig(display, renderable, 0, configs, 0, 1, count, 0)
            val config = configs[0] ?: run {
                line("eglConfig", "none")
                return
            }
            val context = EGL14.eglCreateContext(
                display, config, EGL14.EGL_NO_CONTEXT,
                intArrayOf(EGL14.EGL_CONTEXT_CLIENT_VERSION, 2, EGL14.EGL_NONE), 0
            )
            val surface = EGL14.eglCreatePbufferSurface(
                display, config,
                intArrayOf(EGL14.EGL_WIDTH, 1, EGL14.EGL_HEIGHT, 1, EGL14.EGL_NONE), 0
            )
            if (!EGL14.eglMakeCurrent(display, surface, surface, context)) {
                line("eglMakeCurrent", "failed")
                return
            }
            line("GL_VENDOR", GLES20.glGetString(GLES20.GL_VENDOR))
            line("GL_RENDERER", GLES20.glGetString(GLES20.GL_RENDERER))
            line("GL_VERSION", GLES20.glGetString(GLES20.GL_VERSION))
            line("EGL_VENDOR", EGL14.eglQueryString(display, EGL14.EGL_VENDOR))
            line("EGL_VERSION", EGL14.eglQueryString(display, EGL14.EGL_VERSION))
            EGL14.eglMakeCurrent(
                display, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT
            )
            EGL14.eglDestroySurface(display, surface)
            EGL14.eglDestroyContext(display, context)
        } finally {
            EGL14.eglTerminate(display)
        }
    }

    /**
     * 和 GMS 广告 SDK 一样，绑定 AdvertisingIdService 后 transact：
     * 1 = getId，2 = isLimitAdTrackingEnabled（容器在这里返回注入的 GAID）。
     */
    private fun fetchGaidAsync() {
        gaidState = "binding..."
        val intent = Intent("com.google.android.gms.ads.identifier.service.START")
            .setPackage("com.google.android.gms")
        val latch = CountDownLatch(1)
        var binder: IBinder? = null
        val connection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                binder = service
                latch.countDown()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                gaidState = "disconnected"
            }
        }
        val bound = try {
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        } catch (t: Throwable) {
            gaidState = "bind failed: " + t.javaClass.simpleName
            false
        }
        if (!bound) {
            if (gaidState == "binding...") gaidState = "bindService returned false"
            render()
            return
        }
        Thread {
            try {
                if (!latch.await(5, TimeUnit.SECONDS)) {
                    gaidState = "timeout waiting for service"
                    return@Thread
                }
                val service = binder
                if (service == null) {
                    gaidState = "null binder"
                    return@Thread
                }
                gaidId = transactString(service, 1)
                gaidLimit = transactInt(service, 2)
                gaidState = "ok"
            } catch (t: Throwable) {
                gaidState = "error: " + t.javaClass.simpleName + ": " + t.message
            } finally {
                try {
                    unbindService(connection)
                } catch (ignored: Throwable) {
                }
                render()
            }
        }.start()
    }

    /**
     * 绑定 com.android.vending 的 IGetInstallReferrerService，
     * transact(1) 传 {package_name}，返回 Bundle{install_referrer, *_timestamp_seconds}。
     */
    private fun fetchReferrerAsync() {
        referrerState = "binding..."
        val intent = Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE")
            .setPackage("com.android.vending")
        val latch = CountDownLatch(1)
        var binder: IBinder? = null
        val connection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                binder = service
                latch.countDown()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                referrerState = "disconnected"
            }
        }
        val bound = try {
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        } catch (t: Throwable) {
            referrerState = "bind failed: " + t.javaClass.simpleName
            false
        }
        if (!bound) {
            if (referrerState == "binding...") referrerState = "bindService returned false"
            render()
            return
        }
        Thread {
            try {
                if (!latch.await(5, TimeUnit.SECONDS)) {
                    referrerState = "timeout waiting for service"
                    return@Thread
                }
                val service = binder
                if (service == null) {
                    referrerState = "null binder"
                    return@Thread
                }
                val data = Parcel.obtain()
                val reply = Parcel.obtain()
                try {
                    data.writeInterfaceToken(
                        "com.google.android.finsky.externalreferrer.IGetInstallReferrerService"
                    )
                    val params = Bundle().apply { putString("package_name", packageName) }
                    if (Build.VERSION.SDK_INT >= 33) {
                        data.writeTypedObject(params, 0)
                    } else {
                        data.writeParcelable(params, 0)
                    }
                    service.transact(1, data, reply, 0)
                    reply.readException()
                    @Suppress("DEPRECATION")
                    val result = if (Build.VERSION.SDK_INT >= 33) {
                        reply.readTypedObject(Bundle.CREATOR)
                    } else {
                        reply.readParcelable(Bundle::class.java.classLoader)
                    }
                    referrerValue = result?.getString("install_referrer") ?: "(empty)"
                    referrerExtra = "click=" +
                        result?.getLong("referrer_click_timestamp_seconds", 0L) +
                        ", install=" +
                        result?.getLong("install_begin_timestamp_seconds", 0L)
                    referrerState = "ok"
                } finally {
                    reply.recycle()
                    data.recycle()
                }
            } catch (t: Throwable) {
                referrerState = "error: " + t.javaClass.simpleName + ": " + t.message
            } finally {
                try {
                    unbindService(connection)
                } catch (ignored: Throwable) {
                }
                render()
            }
        }.start()
    }

    private fun transactString(service: IBinder, code: Int): String? {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        return try {
            data.writeInterfaceToken(
                "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService"
            )
            service.transact(code, data, reply, 0)
            reply.readException()
            reply.readString()
        } finally {
            reply.recycle()
            data.recycle()
        }
    }

    private fun transactInt(service: IBinder, code: Int): String? {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        return try {
            data.writeInterfaceToken(
                "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService"
            )
            data.writeInt(1)
            service.transact(code, data, reply, 0)
            reply.readException()
            (reply.readInt() != 0).toString()
        } finally {
            reply.recycle()
            data.recycle()
        }
    }

    private fun render() {
        runOnUiThread { content.text = collect() }
    }

    private fun certSha256(packageName: String): String? {
        return try {
            @Suppress("DEPRECATION")
            val info = packageManager.getPackageInfo(
                packageName, android.content.pm.PackageManager.GET_SIGNATURES
            )
            val signature = info.signatures?.firstOrNull()
            if (signature == null) {
                null
            } else {
                val digest = MessageDigest.getInstance("SHA-256").digest(signature.toByteArray())
                digest.joinToString(":") { String.format("%02X", it) }
            }
        } catch (t: Throwable) {
            "! " + t.javaClass.simpleName
        }
    }

    private fun signatureCount(packageName: String): Int = try {
        @Suppress("DEPRECATION")
        val info = packageManager.getPackageInfo(
            packageName, android.content.pm.PackageManager.GET_SIGNATURES
        )
        info.signatures?.size ?: 0
    } catch (t: Throwable) {
        -1
    }

    private fun referrer(): String? = try {
        val fromApi = getReferrer()?.toString()
        val fromIntent = intent?.getParcelableExtra<android.os.Parcelable>(Intent.EXTRA_REFERRER)?.toString()
        "getReferrer()=$fromApi, extra=$fromIntent"
    } catch (t: Throwable) {
        "error: ${t.javaClass.simpleName}"
    }

    private fun currentProcessName(): String? =
        if (Build.VERSION.SDK_INT >= 28) android.app.Application.getProcessName() else null

    private fun intToIp(ip: Int): String =
        "${ip and 0xff}.${ip shr 8 and 0xff}.${ip shr 16 and 0xff}.${ip shr 24 and 0xff}"
}
