package black.android.os;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/BRBuild.class */
public class BRBuild {
    public static BuildStatic getWithException() {
        return (BuildStatic) BlackReflection.create(BuildStatic.class, (Object) null, true);
    }

    public static BuildStatic get() {
        return (BuildStatic) BlackReflection.create(BuildStatic.class, (Object) null, false);
    }

    public static BuildContext getWithException(Object caller) {
        return (BuildContext) BlackReflection.create(BuildContext.class, caller, true);
    }

    public static BuildContext get(Object caller) {
        return (BuildContext) BlackReflection.create(BuildContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(BuildContext.class);
    }
}
