package black.android.providers;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/providers/BRSettingsNameValueCacheOreo.class */
public class BRSettingsNameValueCacheOreo {
    public static SettingsNameValueCacheOreoStatic getWithException() {
        return (SettingsNameValueCacheOreoStatic) BlackReflection.create(SettingsNameValueCacheOreoStatic.class, (Object) null, true);
    }

    public static SettingsNameValueCacheOreoStatic get() {
        return (SettingsNameValueCacheOreoStatic) BlackReflection.create(SettingsNameValueCacheOreoStatic.class, (Object) null, false);
    }

    public static SettingsNameValueCacheOreoContext getWithException(Object caller) {
        return (SettingsNameValueCacheOreoContext) BlackReflection.create(SettingsNameValueCacheOreoContext.class, caller, true);
    }

    public static SettingsNameValueCacheOreoContext get(Object caller) {
        return (SettingsNameValueCacheOreoContext) BlackReflection.create(SettingsNameValueCacheOreoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SettingsNameValueCacheOreoContext.class);
    }
}
