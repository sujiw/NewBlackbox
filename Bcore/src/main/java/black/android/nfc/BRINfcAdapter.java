package black.android.nfc;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/nfc/BRINfcAdapter.class */
public class BRINfcAdapter {
    public static INfcAdapterStatic getWithException() {
        return (INfcAdapterStatic) BlackReflection.create(INfcAdapterStatic.class, (Object) null, true);
    }

    public static INfcAdapterStatic get() {
        return (INfcAdapterStatic) BlackReflection.create(INfcAdapterStatic.class, (Object) null, false);
    }

    public static INfcAdapterContext getWithException(Object caller) {
        return (INfcAdapterContext) BlackReflection.create(INfcAdapterContext.class, caller, true);
    }

    public static INfcAdapterContext get(Object caller) {
        return (INfcAdapterContext) BlackReflection.create(INfcAdapterContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(INfcAdapterContext.class);
    }
}
