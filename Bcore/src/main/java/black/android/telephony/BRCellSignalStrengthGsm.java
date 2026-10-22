package black.android.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/BRCellSignalStrengthGsm.class */
public class BRCellSignalStrengthGsm {
    public static CellSignalStrengthGsmStatic getWithException() {
        return (CellSignalStrengthGsmStatic) BlackReflection.create(CellSignalStrengthGsmStatic.class, (Object) null, true);
    }

    public static CellSignalStrengthGsmStatic get() {
        return (CellSignalStrengthGsmStatic) BlackReflection.create(CellSignalStrengthGsmStatic.class, (Object) null, false);
    }

    public static CellSignalStrengthGsmContext getWithException(Object caller) {
        return (CellSignalStrengthGsmContext) BlackReflection.create(CellSignalStrengthGsmContext.class, caller, true);
    }

    public static CellSignalStrengthGsmContext get(Object caller) {
        return (CellSignalStrengthGsmContext) BlackReflection.create(CellSignalStrengthGsmContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(CellSignalStrengthGsmContext.class);
    }
}
