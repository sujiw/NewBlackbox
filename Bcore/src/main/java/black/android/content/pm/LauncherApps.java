package black.android.content.pm;

import android.os.IInterface;
import android.os.UserManager;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BField;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/LauncherApps.class */
@BClassName("android.content.pm.LauncherApps")
public interface LauncherApps {
    @BField
    android.content.pm.PackageManager mPm();

    @BField
    IInterface mService();

    @BField
    UserManager mUserManager();
}
