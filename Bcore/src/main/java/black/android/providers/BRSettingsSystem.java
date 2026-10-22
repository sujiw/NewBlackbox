package black.android.providers;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/providers/BRSettingsSystem.class */
public class BRSettingsSystem {
    public static SettingsSystemStatic getWithException() {
        return (SettingsSystemStatic) BlackReflection.create(SettingsSystemStatic.class, (Object) null, true);
    }

    public static SettingsSystemStatic get() {
        return (SettingsSystemStatic) BlackReflection.create(SettingsSystemStatic.class, (Object) null, false);
    }

    public static SettingsSystemContext getWithException(Object caller) {
        return (SettingsSystemContext) BlackReflection.create(SettingsSystemContext.class, caller, true);
    }

    public static SettingsSystemContext get(Object caller) {
        return (SettingsSystemContext) BlackReflection.create(SettingsSystemContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SettingsSystemContext.class);
    }
}
