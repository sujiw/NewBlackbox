package black.android.content.pm;

import android.content.pm.Signature;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParserSigningDetailsContext.class */
@BClassNameNotProcess("android.content.pm.PackageParser$SigningDetails")
public interface PackageParserSigningDetailsContext {
    @BMethodCheckNotProcess
    Method _check_hasPastSigningCertificates();

    Boolean hasPastSigningCertificates();

    @BMethodCheckNotProcess
    Method _check_hasSignatures();

    Boolean hasSignatures();

    @BFieldSetNotProcess
    void _set_pastSigningCertificates(Object obj);

    @BFieldCheckNotProcess
    Field _check_pastSigningCertificates();

    @BFieldNotProcess
    Signature[] pastSigningCertificates();

    @BFieldSetNotProcess
    void _set_signatures(Object obj);

    @BFieldCheckNotProcess
    Field _check_signatures();

    @BFieldNotProcess
    Signature[] signatures();
}
