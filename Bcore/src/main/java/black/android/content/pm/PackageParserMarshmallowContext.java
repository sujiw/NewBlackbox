package black.android.content.pm;

import java.io.File;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParserMarshmallowContext.class */
@BClassNameNotProcess("android.content.pm.PackageParser")
public interface PackageParserMarshmallowContext {
    @BMethodCheckNotProcess
    Method _check_collectCertificates(android.content.pm.PackageParser.Package r1, int i);

    Void collectCertificates(android.content.pm.PackageParser.Package r1, int i);

    @BMethodCheckNotProcess
    Method _check_parsePackage(File file, int i);

    android.content.pm.PackageParser.Package parsePackage(File file, int i);
}
