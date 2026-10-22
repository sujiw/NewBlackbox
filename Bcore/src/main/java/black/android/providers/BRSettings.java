package black.android.providers;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/providers/BRSettings.class */
public class BRSettings {
    public static SettingsStatic getWithException() {
        return (SettingsStatic) BlackReflection.create(SettingsStatic.class, (Object) null, true);
    }

    public static SettingsStatic get() {
        return (SettingsStatic) BlackReflection.create(SettingsStatic.class, (Object) null, false);
    }

    public static SettingsContext getWithException(Object caller) {
        return (SettingsContext) BlackReflection.create(SettingsContext.class, caller, true);
    }

    public static SettingsContext get(Object caller) {
        return (SettingsContext) BlackReflection.create(SettingsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SettingsContext.class);
    }
}
