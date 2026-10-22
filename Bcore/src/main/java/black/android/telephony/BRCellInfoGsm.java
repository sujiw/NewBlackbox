package black.android.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/BRCellInfoGsm.class */
public class BRCellInfoGsm {
    public static CellInfoGsmStatic getWithException() {
        return (CellInfoGsmStatic) BlackReflection.create(CellInfoGsmStatic.class, (Object) null, true);
    }

    public static CellInfoGsmStatic get() {
        return (CellInfoGsmStatic) BlackReflection.create(CellInfoGsmStatic.class, (Object) null, false);
    }

    public static CellInfoGsmContext getWithException(Object caller) {
        return (CellInfoGsmContext) BlackReflection.create(CellInfoGsmContext.class, caller, true);
    }

    public static CellInfoGsmContext get(Object caller) {
        return (CellInfoGsmContext) BlackReflection.create(CellInfoGsmContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(CellInfoGsmContext.class);
    }
}
