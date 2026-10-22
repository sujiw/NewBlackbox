package black.android.providers;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/providers/BRSettingsContentProviderHolder.class */
public class BRSettingsContentProviderHolder {
    public static SettingsContentProviderHolderStatic getWithException() {
        return (SettingsContentProviderHolderStatic) BlackReflection.create(SettingsContentProviderHolderStatic.class, (Object) null, true);
    }

    public static SettingsContentProviderHolderStatic get() {
        return (SettingsContentProviderHolderStatic) BlackReflection.create(SettingsContentProviderHolderStatic.class, (Object) null, false);
    }

    public static SettingsContentProviderHolderContext getWithException(Object caller) {
        return (SettingsContentProviderHolderContext) BlackReflection.create(SettingsContentProviderHolderContext.class, caller, true);
    }

    public static SettingsContentProviderHolderContext get(Object caller) {
        return (SettingsContentProviderHolderContext) BlackReflection.create(SettingsContentProviderHolderContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SettingsContentProviderHolderContext.class);
    }
}
