package black.android.providers;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/providers/BRSettingsSecure.class */
public class BRSettingsSecure {
    public static SettingsSecureStatic getWithException() {
        return (SettingsSecureStatic) BlackReflection.create(SettingsSecureStatic.class, (Object) null, true);
    }

    public static SettingsSecureStatic get() {
        return (SettingsSecureStatic) BlackReflection.create(SettingsSecureStatic.class, (Object) null, false);
    }

    public static SettingsSecureContext getWithException(Object caller) {
        return (SettingsSecureContext) BlackReflection.create(SettingsSecureContext.class, caller, true);
    }

    public static SettingsSecureContext get(Object caller) {
        return (SettingsSecureContext) BlackReflection.create(SettingsSecureContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SettingsSecureContext.class);
    }
}
