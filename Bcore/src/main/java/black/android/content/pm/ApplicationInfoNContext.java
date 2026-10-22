package black.android.content.pm;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/ApplicationInfoNContext.class */
@BClassNameNotProcess("android.content.pm.ApplicationInfo")
public interface ApplicationInfoNContext {
    @BFieldSetNotProcess
    void _set_credentialEncryptedDataDir(Object obj);

    @BFieldCheckNotProcess
    Field _check_credentialEncryptedDataDir();

    @BFieldNotProcess
    String credentialEncryptedDataDir();

    @BFieldSetNotProcess
    void _set_credentialProtectedDataDir(Object obj);

    @BFieldCheckNotProcess
    Field _check_credentialProtectedDataDir();

    @BFieldNotProcess
    String credentialProtectedDataDir();

    @BFieldSetNotProcess
    void _set_deviceEncryptedDataDir(Object obj);

    @BFieldCheckNotProcess
    Field _check_deviceEncryptedDataDir();

    @BFieldNotProcess
    String deviceEncryptedDataDir();

    @BFieldSetNotProcess
    void _set_deviceProtectedDataDir(Object obj);

    @BFieldCheckNotProcess
    Field _check_deviceProtectedDataDir();

    @BFieldNotProcess
    String deviceProtectedDataDir();
}
