package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRUserInfo.class */
public class BRUserInfo {
    public static UserInfoStatic getWithException() {
        return (UserInfoStatic) BlackReflection.create(UserInfoStatic.class, (Object) null, true);
    }

    public static UserInfoStatic get() {
        return (UserInfoStatic) BlackReflection.create(UserInfoStatic.class, (Object) null, false);
    }

    public static UserInfoContext getWithException(Object caller) {
        return (UserInfoContext) BlackReflection.create(UserInfoContext.class, caller, true);
    }

    public static UserInfoContext get(Object caller) {
        return (UserInfoContext) BlackReflection.create(UserInfoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(UserInfoContext.class);
    }
}
