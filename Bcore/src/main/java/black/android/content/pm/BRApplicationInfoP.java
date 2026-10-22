package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRApplicationInfoP.class */
public class BRApplicationInfoP {
    public static ApplicationInfoPStatic getWithException() {
        return (ApplicationInfoPStatic) BlackReflection.create(ApplicationInfoPStatic.class, (Object) null, true);
    }

    public static ApplicationInfoPStatic get() {
        return (ApplicationInfoPStatic) BlackReflection.create(ApplicationInfoPStatic.class, (Object) null, false);
    }

    public static ApplicationInfoPContext getWithException(Object caller) {
        return (ApplicationInfoPContext) BlackReflection.create(ApplicationInfoPContext.class, caller, true);
    }

    public static ApplicationInfoPContext get(Object caller) {
        return (ApplicationInfoPContext) BlackReflection.create(ApplicationInfoPContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ApplicationInfoPContext.class);
    }
}
