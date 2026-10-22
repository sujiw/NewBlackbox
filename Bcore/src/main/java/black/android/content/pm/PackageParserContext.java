package black.android.content.pm;

import android.util.DisplayMetrics;
import java.io.File;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParserContext.class */
@BClassNameNotProcess("android.content.pm.PackageParser")
public interface PackageParserContext {
    @BMethodCheckNotProcess
    Method _check_collectCertificates(android.content.pm.PackageParser.Package r1, int i);

    Void collectCertificates(android.content.pm.PackageParser.Package r1, int i);

    @BMethodCheckNotProcess
    Method _check_parsePackage(File file, String str, DisplayMetrics displayMetrics, int i);

    android.content.pm.PackageParser.Package parsePackage(File file, String str, DisplayMetrics displayMetrics, int i);
}
