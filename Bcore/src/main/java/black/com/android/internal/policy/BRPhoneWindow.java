package black.com.android.internal.policy;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/policy/BRPhoneWindow.class */
public class BRPhoneWindow {
    public static PhoneWindowStatic getWithException() {
        return (PhoneWindowStatic) BlackReflection.create(PhoneWindowStatic.class, (Object) null, true);
    }

    public static PhoneWindowStatic get() {
        return (PhoneWindowStatic) BlackReflection.create(PhoneWindowStatic.class, (Object) null, false);
    }

    public static PhoneWindowContext getWithException(Object caller) {
        return (PhoneWindowContext) BlackReflection.create(PhoneWindowContext.class, caller, true);
    }

    public static PhoneWindowContext get(Object caller) {
        return (PhoneWindowContext) BlackReflection.create(PhoneWindowContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PhoneWindowContext.class);
    }
}
