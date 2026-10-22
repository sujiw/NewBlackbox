package black.android.telephony;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/BRCellIdentityGsm.class */
public class BRCellIdentityGsm {
    public static CellIdentityGsmStatic getWithException() {
        return (CellIdentityGsmStatic) BlackReflection.create(CellIdentityGsmStatic.class, (Object) null, true);
    }

    public static CellIdentityGsmStatic get() {
        return (CellIdentityGsmStatic) BlackReflection.create(CellIdentityGsmStatic.class, (Object) null, false);
    }

    public static CellIdentityGsmContext getWithException(Object caller) {
        return (CellIdentityGsmContext) BlackReflection.create(CellIdentityGsmContext.class, caller, true);
    }

    public static CellIdentityGsmContext get(Object caller) {
        return (CellIdentityGsmContext) BlackReflection.create(CellIdentityGsmContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(CellIdentityGsmContext.class);
    }
}
