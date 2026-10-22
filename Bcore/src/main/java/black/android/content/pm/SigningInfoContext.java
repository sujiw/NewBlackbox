package black.android.content.pm;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/SigningInfoContext.class */
@BClassNameNotProcess("android.content.pm.SigningInfo")
public interface SigningInfoContext {
    @BFieldSetNotProcess
    void _set_mSigningDetails(Object obj);

    @BFieldCheckNotProcess
    Field _check_mSigningDetails();

    @BFieldNotProcess
    android.content.pm.PackageParser.SigningDetails mSigningDetails();
}
