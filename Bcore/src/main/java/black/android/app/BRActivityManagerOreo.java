package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRActivityManagerOreo.class */
public class BRActivityManagerOreo {
    public static ActivityManagerOreoStatic getWithException() {
        return (ActivityManagerOreoStatic) BlackReflection.create(ActivityManagerOreoStatic.class, (Object) null, true);
    }

    public static ActivityManagerOreoStatic get() {
        return (ActivityManagerOreoStatic) BlackReflection.create(ActivityManagerOreoStatic.class, (Object) null, false);
    }

    public static ActivityManagerOreoContext getWithException(Object caller) {
        return (ActivityManagerOreoContext) BlackReflection.create(ActivityManagerOreoContext.class, caller, true);
    }

    public static ActivityManagerOreoContext get(Object caller) {
        return (ActivityManagerOreoContext) BlackReflection.create(ActivityManagerOreoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ActivityManagerOreoContext.class);
    }
}
