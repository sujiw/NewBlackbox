package black.android.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/BRNeighboringCellInfo.class */
public class BRNeighboringCellInfo {
    public static NeighboringCellInfoStatic getWithException() {
        return (NeighboringCellInfoStatic) BlackReflection.create(NeighboringCellInfoStatic.class, (Object) null, true);
    }

    public static NeighboringCellInfoStatic get() {
        return (NeighboringCellInfoStatic) BlackReflection.create(NeighboringCellInfoStatic.class, (Object) null, false);
    }

    public static NeighboringCellInfoContext getWithException(Object caller) {
        return (NeighboringCellInfoContext) BlackReflection.create(NeighboringCellInfoContext.class, caller, true);
    }

    public static NeighboringCellInfoContext get(Object caller) {
        return (NeighboringCellInfoContext) BlackReflection.create(NeighboringCellInfoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(NeighboringCellInfoContext.class);
    }
}
