package top.niunaijun.blackbox.utils.compat;

import black.android.os.BRStrictMode;
import top.niunaijun.blackbox.core.system.JarConfig;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/compat/StrictModeCompat.class */
public class StrictModeCompat {
    public static int DETECT_VM_FILE_URI_EXPOSURE;
    public static int PENALTY_DEATH_ON_FILE_URI_EXPOSURE;

    static {
        DETECT_VM_FILE_URI_EXPOSURE = BRStrictMode.get().DETECT_VM_FILE_URI_EXPOSURE() == null ? JarConfig.DEFAULT_BUFFER_SIZE : BRStrictMode.get().DETECT_VM_FILE_URI_EXPOSURE().intValue();
        PENALTY_DEATH_ON_FILE_URI_EXPOSURE = BRStrictMode.get().PENALTY_DEATH_ON_FILE_URI_EXPOSURE() == null ? 67108864 : BRStrictMode.get().PENALTY_DEATH_ON_FILE_URI_EXPOSURE().intValue();
    }

    public static boolean disableDeathOnFileUriExposure() {
        try {
            BRStrictMode.get().disableDeathOnFileUriExposure();
            return true;
        } catch (Throwable th) {
            try {
                int sVmPolicyMask = BRStrictMode.get().sVmPolicyMask().intValue();
                BRStrictMode.get()._set_sVmPolicyMask(Integer.valueOf(sVmPolicyMask & ((DETECT_VM_FILE_URI_EXPOSURE | PENALTY_DEATH_ON_FILE_URI_EXPOSURE) ^ (-1))));
                return true;
            } catch (Throwable e2) {
                e2.printStackTrace();
                return false;
            }
        }
    }
}
