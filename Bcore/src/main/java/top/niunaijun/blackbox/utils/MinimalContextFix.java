package top.niunaijun.blackbox.utils;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/MinimalContextFix.class */
public class MinimalContextFix {
    private static final String TAG = "MinimalContextFix";

    public static void installMinimalFix() {
        try {
            Slog.d(TAG, "Installing minimal context fix...");
            installGlobalExceptionHandler();
            Slog.d(TAG, "Minimal context fix installed successfully");
        } catch (Exception e) {
            Slog.e(TAG, "Failed to install minimal context fix: " + e.getMessage(), e);
        }
    }

    private static void installGlobalExceptionHandler() {
        try {
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: top.niunaijun.blackbox.utils.MinimalContextFix.1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable throwable) {
                    String message;
                    if ((throwable instanceof NullPointerException) && (message = throwable.getMessage()) != null && message.contains("getResources") && message.contains("null object reference")) {
                        Slog.w(MinimalContextFix.TAG, "Caught null context crash, preventing crash: " + message);
                    } else if (Thread.getDefaultUncaughtExceptionHandler() != null) {
                        Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, throwable);
                    }
                }
            });
            Slog.d(TAG, "Global exception handler installed successfully");
        } catch (Exception e) {
            Slog.w(TAG, "Could not install global exception handler: " + e.getMessage());
        }
    }
}
