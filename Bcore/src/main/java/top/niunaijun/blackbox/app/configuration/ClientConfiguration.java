package top.niunaijun.blackbox.app.configuration;

import java.io.File;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/app/configuration/ClientConfiguration.class */
public abstract class ClientConfiguration {
    public abstract String getHostPackageName();

    public boolean isHideRoot() {
        return false;
    }

    public boolean isHideXposed() {
        return false;
    }

    public boolean isEnableDaemonService() {
        return true;
    }

    public boolean isEnableLauncherActivity() {
        return true;
    }

    public boolean requestInstallPackage(File file, int userId) {
        return false;
    }
}
