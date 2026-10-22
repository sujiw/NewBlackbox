package top.niunaijun.blackbox.utils;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/StackTraceFilter.class */
public class StackTraceFilter {
    static {
        install();
    }

    public static void install() {
        try {
            Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
                StackTraceElement[] original = e.getStackTrace();
                e.setStackTrace(filterStackTrace(original));
            });
        } catch (Throwable th) {
        }
    }

    private static StackTraceElement[] filterStackTrace(StackTraceElement[] stack) {
        return (StackTraceElement[]) Arrays.stream(stack).filter(element -> {
            return !isSuspicious(element.getClassName());
        }).toArray(x$0 -> {
            return new StackTraceElement[x$0];
        });
    }

    private static boolean isSuspicious(String className) {
        return className.toLowerCase().contains("xposed") || className.toLowerCase().contains("epic") || className.toLowerCase().contains("virtual") || className.toLowerCase().contains("blackbox") || className.toLowerCase().contains("hook");
    }
}
