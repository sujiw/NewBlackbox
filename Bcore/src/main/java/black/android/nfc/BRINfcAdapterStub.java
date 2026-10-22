package black.android.nfc;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/nfc/BRINfcAdapterStub.class */
public class BRINfcAdapterStub {
    public static INfcAdapterStubStatic getWithException() {
        return (INfcAdapterStubStatic) BlackReflection.create(INfcAdapterStubStatic.class, (Object) null, true);
    }

    public static INfcAdapterStubStatic get() {
        return (INfcAdapterStubStatic) BlackReflection.create(INfcAdapterStubStatic.class, (Object) null, false);
    }

    public static INfcAdapterStubContext getWithException(Object caller) {
        return (INfcAdapterStubContext) BlackReflection.create(INfcAdapterStubContext.class, caller, true);
    }

    public static INfcAdapterStubContext get(Object caller) {
        return (INfcAdapterStubContext) BlackReflection.create(INfcAdapterStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(INfcAdapterStubContext.class);
    }
}
