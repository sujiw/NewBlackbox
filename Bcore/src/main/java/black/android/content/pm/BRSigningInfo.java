package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRSigningInfo.class */
public class BRSigningInfo {
    public static SigningInfoStatic getWithException() {
        return (SigningInfoStatic) BlackReflection.create(SigningInfoStatic.class, (Object) null, true);
    }

    public static SigningInfoStatic get() {
        return (SigningInfoStatic) BlackReflection.create(SigningInfoStatic.class, (Object) null, false);
    }

    public static SigningInfoContext getWithException(Object caller) {
        return (SigningInfoContext) BlackReflection.create(SigningInfoContext.class, caller, true);
    }

    public static SigningInfoContext get(Object caller) {
        return (SigningInfoContext) BlackReflection.create(SigningInfoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SigningInfoContext.class);
    }
}
