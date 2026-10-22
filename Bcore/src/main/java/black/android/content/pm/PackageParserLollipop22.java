package black.android.content.pm;

import java.io.File;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BConstructor;
import top.niunaijun.blackreflection.annotation.BMethod;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/PackageParserLollipop22.class */
@BClassName("android.content.pm.PackageParser")
public interface PackageParserLollipop22 {
    @BConstructor
    android.content.pm.PackageParser _new();

    @BMethod
    void collectCertificates(android.content.pm.PackageParser.Package r1, int i);

    @BMethod
    android.content.pm.PackageParser.Package parsePackage(File file, int i);
}
