package black.android.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/BRCellSignalStrengthCdma.class */
public class BRCellSignalStrengthCdma {
    public static CellSignalStrengthCdmaStatic getWithException() {
        return (CellSignalStrengthCdmaStatic) BlackReflection.create(CellSignalStrengthCdmaStatic.class, (Object) null, true);
    }

    public static CellSignalStrengthCdmaStatic get() {
        return (CellSignalStrengthCdmaStatic) BlackReflection.create(CellSignalStrengthCdmaStatic.class, (Object) null, false);
    }

    public static CellSignalStrengthCdmaContext getWithException(Object caller) {
        return (CellSignalStrengthCdmaContext) BlackReflection.create(CellSignalStrengthCdmaContext.class, caller, true);
    }

    public static CellSignalStrengthCdmaContext get(Object caller) {
        return (CellSignalStrengthCdmaContext) BlackReflection.create(CellSignalStrengthCdmaContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(CellSignalStrengthCdmaContext.class);
    }
}
