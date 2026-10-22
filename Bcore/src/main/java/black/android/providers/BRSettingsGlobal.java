package black.android.providers;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/providers/BRSettingsGlobal.class */
public class BRSettingsGlobal {
    public static SettingsGlobalStatic getWithException() {
        return (SettingsGlobalStatic) BlackReflection.create(SettingsGlobalStatic.class, (Object) null, true);
    }

    public static SettingsGlobalStatic get() {
        return (SettingsGlobalStatic) BlackReflection.create(SettingsGlobalStatic.class, (Object) null, false);
    }

    public static SettingsGlobalContext getWithException(Object caller) {
        return (SettingsGlobalContext) BlackReflection.create(SettingsGlobalContext.class, caller, true);
    }

    public static SettingsGlobalContext get(Object caller) {
        return (SettingsGlobalContext) BlackReflection.create(SettingsGlobalContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SettingsGlobalContext.class);
    }
}
