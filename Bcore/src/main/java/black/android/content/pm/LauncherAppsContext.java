package black.android.content.pm;

import android.os.IInterface;
import android.os.UserManager;
import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/LauncherAppsContext.class */
@BClassNameNotProcess("android.content.pm.LauncherApps")
public interface LauncherAppsContext {
    @BFieldSetNotProcess
    void _set_mPm(Object obj);

    @BFieldCheckNotProcess
    Field _check_mPm();

    @BFieldNotProcess
    android.content.pm.PackageManager mPm();

    @BFieldSetNotProcess
    void _set_mService(Object obj);

    @BFieldCheckNotProcess
    Field _check_mService();

    @BFieldNotProcess
    IInterface mService();

    @BFieldSetNotProcess
    void _set_mUserManager(Object obj);

    @BFieldCheckNotProcess
    Field _check_mUserManager();

    @BFieldNotProcess
    UserManager mUserManager();
}
