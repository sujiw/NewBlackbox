package top.niunaijun.blackbox.core.env;

import android.content.pm.ApplicationInfo;
import black.android.ddm.BRDdmHandleAppName;
import black.android.os.BRProcess;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/env/VirtualRuntime.class */
public class VirtualRuntime {
    private static String sInitialPackageName;
    private static String sProcessName;

    public static String getProcessName() {
        return sProcessName;
    }

    public static String getInitialPackageName() {
        return sInitialPackageName;
    }

    public static void setupRuntime(String processName, ApplicationInfo appInfo) {
        if (sProcessName != null) {
            return;
        }
        sInitialPackageName = appInfo.packageName;
        sProcessName = processName;
        BRProcess.get().setArgV0(processName);
        BRDdmHandleAppName.get().setAppName(processName, 0);
    }
}
