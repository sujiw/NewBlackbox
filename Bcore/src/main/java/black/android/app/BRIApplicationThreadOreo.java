package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIApplicationThreadOreo.class */
public class BRIApplicationThreadOreo {
    public static IApplicationThreadOreoStatic getWithException() {
        return (IApplicationThreadOreoStatic) BlackReflection.create(IApplicationThreadOreoStatic.class, (Object) null, true);
    }

    public static IApplicationThreadOreoStatic get() {
        return (IApplicationThreadOreoStatic) BlackReflection.create(IApplicationThreadOreoStatic.class, (Object) null, false);
    }

    public static IApplicationThreadOreoContext getWithException(Object caller) {
        return (IApplicationThreadOreoContext) BlackReflection.create(IApplicationThreadOreoContext.class, caller, true);
    }

    public static IApplicationThreadOreoContext get(Object caller) {
        return (IApplicationThreadOreoContext) BlackReflection.create(IApplicationThreadOreoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IApplicationThreadOreoContext.class);
    }
}
