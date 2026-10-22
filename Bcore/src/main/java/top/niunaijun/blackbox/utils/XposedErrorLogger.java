package top.niunaijun.blackbox.utils;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/XposedErrorLogger.class */
public class XposedErrorLogger {
    private static final Map<String, String> moduleErrors = new HashMap();

    public static void logModuleError(String packageName, String error) {
        moduleErrors.put(packageName, error);
    }

    public static String getModuleError(String packageName) {
        return moduleErrors.get(packageName);
    }

    public static Map<String, String> getAllModuleErrors() {
        return new HashMap(moduleErrors);
    }
}
