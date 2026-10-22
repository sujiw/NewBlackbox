package black.android.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/BRCellIdentityCdma.class */
public class BRCellIdentityCdma {
    public static CellIdentityCdmaStatic getWithException() {
        return (CellIdentityCdmaStatic) BlackReflection.create(CellIdentityCdmaStatic.class, (Object) null, true);
    }

    public static CellIdentityCdmaStatic get() {
        return (CellIdentityCdmaStatic) BlackReflection.create(CellIdentityCdmaStatic.class, (Object) null, false);
    }

    public static CellIdentityCdmaContext getWithException(Object caller) {
        return (CellIdentityCdmaContext) BlackReflection.create(CellIdentityCdmaContext.class, caller, true);
    }

    public static CellIdentityCdmaContext get(Object caller) {
        return (CellIdentityCdmaContext) BlackReflection.create(CellIdentityCdmaContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(CellIdentityCdmaContext.class);
    }
}
