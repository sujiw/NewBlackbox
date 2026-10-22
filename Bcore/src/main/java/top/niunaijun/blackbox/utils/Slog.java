package top.niunaijun.blackbox.utils;

import android.util.Log;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/Slog.class */
public final class Slog {
    public static final int LOG_ID_SYSTEM = 3;

    private Slog() {
    }

    public static int v(String tag, String msg) {
        return Log.println(2, tag, msg);
    }

    public static int v(String tag, String msg, Throwable tr) {
        return Log.println(2, tag, msg + '\n' + Log.getStackTraceString(tr));
    }

    public static int d(String tag, String msg) {
        return Log.println(3, tag, msg);
    }

    public static int d(String tag, String msg, Throwable tr) {
        return Log.println(3, tag, msg + '\n' + Log.getStackTraceString(tr));
    }

    public static int i(String tag, String msg) {
        return Log.println(4, tag, msg);
    }

    public static int i(String tag, String msg, Throwable tr) {
        return Log.println(4, tag, msg + '\n' + Log.getStackTraceString(tr));
    }

    public static int w(String tag, String msg) {
        return Log.println(5, tag, msg);
    }

    public static int w(String tag, String msg, Throwable tr) {
        return Log.println(5, tag, msg + '\n' + Log.getStackTraceString(tr));
    }

    public static int w(String tag, Throwable tr) {
        return Log.println(5, tag, Log.getStackTraceString(tr));
    }

    public static int e(String tag, String msg) {
        return Log.println(6, tag, msg);
    }

    public static int e(String tag, String msg, Throwable tr) {
        return Log.println(6, tag, msg + '\n' + Log.getStackTraceString(tr));
    }

    public static int println(int priority, String tag, String msg) {
        return Log.println(priority, tag, msg);
    }
}
