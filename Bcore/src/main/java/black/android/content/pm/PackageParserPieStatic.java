package black.android.content.pm;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParserPieStatic.class */
@BClassNameNotProcess("android.content.pm.PackageParser")
public interface PackageParserPieStatic {
    @BMethodCheckNotProcess
    Method _check_collectCertificates(android.content.pm.PackageParser.Package r1, boolean z);

    Void collectCertificates(android.content.pm.PackageParser.Package r1, boolean z);
}
