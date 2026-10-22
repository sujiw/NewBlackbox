package black.com.android.internal.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/BRPhoneConstantsMtk.class */
public class BRPhoneConstantsMtk {
    public static PhoneConstantsMtkStatic getWithException() {
        return (PhoneConstantsMtkStatic) BlackReflection.create(PhoneConstantsMtkStatic.class, (Object) null, true);
    }

    public static PhoneConstantsMtkStatic get() {
        return (PhoneConstantsMtkStatic) BlackReflection.create(PhoneConstantsMtkStatic.class, (Object) null, false);
    }

    public static PhoneConstantsMtkContext getWithException(Object caller) {
        return (PhoneConstantsMtkContext) BlackReflection.create(PhoneConstantsMtkContext.class, caller, true);
    }

    public static PhoneConstantsMtkContext get(Object caller) {
        return (PhoneConstantsMtkContext) BlackReflection.create(PhoneConstantsMtkContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PhoneConstantsMtkContext.class);
    }
}
