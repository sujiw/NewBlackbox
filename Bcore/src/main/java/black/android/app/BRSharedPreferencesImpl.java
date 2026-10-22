package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRSharedPreferencesImpl.class */
public class BRSharedPreferencesImpl {
    public static SharedPreferencesImplStatic getWithException() {
        return (SharedPreferencesImplStatic) BlackReflection.create(SharedPreferencesImplStatic.class, (Object) null, true);
    }

    public static SharedPreferencesImplStatic get() {
        return (SharedPreferencesImplStatic) BlackReflection.create(SharedPreferencesImplStatic.class, (Object) null, false);
    }

    public static SharedPreferencesImplContext getWithException(Object caller) {
        return (SharedPreferencesImplContext) BlackReflection.create(SharedPreferencesImplContext.class, caller, true);
    }

    public static SharedPreferencesImplContext get(Object caller) {
        return (SharedPreferencesImplContext) BlackReflection.create(SharedPreferencesImplContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SharedPreferencesImplContext.class);
    }
}
