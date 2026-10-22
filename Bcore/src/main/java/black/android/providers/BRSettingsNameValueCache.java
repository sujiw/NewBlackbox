package black.android.providers;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/providers/BRSettingsNameValueCache.class */
public class BRSettingsNameValueCache {
    public static SettingsNameValueCacheStatic getWithException() {
        return (SettingsNameValueCacheStatic) BlackReflection.create(SettingsNameValueCacheStatic.class, (Object) null, true);
    }

    public static SettingsNameValueCacheStatic get() {
        return (SettingsNameValueCacheStatic) BlackReflection.create(SettingsNameValueCacheStatic.class, (Object) null, false);
    }

    public static SettingsNameValueCacheContext getWithException(Object caller) {
        return (SettingsNameValueCacheContext) BlackReflection.create(SettingsNameValueCacheContext.class, caller, true);
    }

    public static SettingsNameValueCacheContext get(Object caller) {
        return (SettingsNameValueCacheContext) BlackReflection.create(SettingsNameValueCacheContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SettingsNameValueCacheContext.class);
    }
}
