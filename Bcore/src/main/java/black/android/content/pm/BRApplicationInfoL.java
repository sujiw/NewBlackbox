package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRApplicationInfoL.class */
public class BRApplicationInfoL {
    public static ApplicationInfoLStatic getWithException() {
        return (ApplicationInfoLStatic) BlackReflection.create(ApplicationInfoLStatic.class, (Object) null, true);
    }

    public static ApplicationInfoLStatic get() {
        return (ApplicationInfoLStatic) BlackReflection.create(ApplicationInfoLStatic.class, (Object) null, false);
    }

    public static ApplicationInfoLContext getWithException(Object caller) {
        return (ApplicationInfoLContext) BlackReflection.create(ApplicationInfoLContext.class, caller, true);
    }

    public static ApplicationInfoLContext get(Object caller) {
        return (ApplicationInfoLContext) BlackReflection.create(ApplicationInfoLContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ApplicationInfoLContext.class);
    }
}
