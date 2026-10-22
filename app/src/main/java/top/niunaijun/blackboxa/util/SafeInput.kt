package top.niunaijun.blackboxa.util

import android.text.InputType
import android.text.method.NumberKeyListener
import android.text.method.PasswordTransformationMethod

/**
 * KeyListener that never touches Settings.System.
 *
 * The default TextKeyListener reads the auto-caps / auto-text preferences on the
 * first key press; inside the virtual space that call is rejected with
 * "Calling uid ... doesn't match source uid ..." and kills the host process.
 */
class SafeKeyListener(
    private val chars: CharArray,
    private val type: Int
) : NumberKeyListener() {

    override fun getInputType(): Int = type

    override fun getAcceptedChars(): CharArray = chars

    companion object {
        private val ASCII = CharArray(95) { (32 + it).toChar() }

        private val HOST = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.-_:[]".toCharArray()

        private val BYPASS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.,;:/*-_ ".toCharArray()

        private val URL =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.-_:/?=&%#@+".toCharArray()

        private val PACKAGE =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ._".toCharArray()

        private val PATH =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ._-/".toCharArray()

        private val HEX = "0123456789abcdefABCDEF-".toCharArray()

        /** Host names, ip addresses, ipv6 literals. */
        fun host(): SafeKeyListener = SafeKeyListener(
            HOST, InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI
        )

        /** Any printable ascii character. */
        fun ascii(): SafeKeyListener = SafeKeyListener(
            ASCII, InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        )

        /** Proxy bypass list. */
        fun bypass(): SafeKeyListener = SafeKeyListener(
            BYPASS, InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        )

        /** Download urls. */
        fun url(): SafeKeyListener = SafeKeyListener(
            URL, InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI
        )

        /** File paths and package names. */
        fun packageName(): SafeKeyListener = SafeKeyListener(
            PACKAGE, InputType.TYPE_CLASS_TEXT
        )

        /** Filesystem paths. */
        fun path(): SafeKeyListener = SafeKeyListener(
            PATH, InputType.TYPE_CLASS_TEXT
        )

        /** Digits only. */
        fun number(): SafeKeyListener = SafeKeyListener(
            "0123456789".toCharArray(), InputType.TYPE_CLASS_NUMBER
        )

        /** GAID style hex uuid. */
        fun hex(): SafeKeyListener = SafeKeyListener(
            HEX, InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        )
    }
}

/**
 * Masks the text like PasswordTransformationMethod but skips its
 * onTextChanged() -> TextKeyListener.getPrefs() call, which reads
 * Settings.System and crashes the host process in the virtual space.
 */
class SafePasswordTransformation : PasswordTransformationMethod() {
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        // Intentionally empty: the base implementation touches Settings.System.
    }
}
