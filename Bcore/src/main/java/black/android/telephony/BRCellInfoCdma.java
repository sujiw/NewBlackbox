package black.android.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/BRCellInfoCdma.class */
public class BRCellInfoCdma {
    public static CellInfoCdmaStatic getWithException() {
        return (CellInfoCdmaStatic) BlackReflection.create(CellInfoCdmaStatic.class, (Object) null, true);
    }

    public static CellInfoCdmaStatic get() {
        return (CellInfoCdmaStatic) BlackReflection.create(CellInfoCdmaStatic.class, (Object) null, false);
    }

    public static CellInfoCdmaContext getWithException(Object caller) {
        return (CellInfoCdmaContext) BlackReflection.create(CellInfoCdmaContext.class, caller, true);
    }

    public static CellInfoCdmaContext get(Object caller) {
        return (CellInfoCdmaContext) BlackReflection.create(CellInfoCdmaContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(CellInfoCdmaContext.class);
    }
}
