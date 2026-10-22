package top.niunaijun.blackbox.utils.compat;

import android.content.pm.PackageParser;
import android.os.Build;
import android.util.DisplayMetrics;
import black.android.content.pm.BRPackageParser;
import black.android.content.pm.BRPackageParserLollipop;
import black.android.content.pm.BRPackageParserLollipop22;
import black.android.content.pm.BRPackageParserMarshmallow;
import black.android.content.pm.BRPackageParserNougat;
import black.android.content.pm.BRPackageParserPie;
import java.io.File;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/compat/PackageParserCompat.class */
public class PackageParserCompat {
    public static final int[] GIDS = new int[0];
    private static final int API_LEVEL = Build.VERSION.SDK_INT;
    private static final int myUserId = 0;

    public static PackageParser createParser(File packageFile) {
        if (API_LEVEL >= 23) {
            return BRPackageParserMarshmallow.get()._new();
        }
        if (API_LEVEL >= 22) {
            return BRPackageParserLollipop22.get()._new();
        }
        if (API_LEVEL >= 21) {
            return BRPackageParserLollipop.get()._new();
        }
        return null;
    }

    public static PackageParser.Package parsePackage(PackageParser parser, File packageFile, int flags) throws Throwable {
        if (API_LEVEL >= 23) {
            return BRPackageParserMarshmallow.getWithException(parser).parsePackage(packageFile, flags);
        }
        if (API_LEVEL >= 22) {
            return BRPackageParserLollipop22.getWithException(parser).parsePackage(packageFile, flags);
        }
        if (API_LEVEL >= 21) {
            return BRPackageParserLollipop.getWithException(parser).parsePackage(packageFile, flags);
        }
        return BRPackageParser.getWithException(parser).parsePackage(packageFile, null, new DisplayMetrics(), flags);
    }

    public static void collectCertificates(PackageParser parser, PackageParser.Package p, int flags) throws Throwable {
        if (BuildCompat.isPie()) {
            BRPackageParserPie.getWithException().collectCertificates(p, true);
            return;
        }
        if (API_LEVEL >= 24) {
            BRPackageParserNougat.getWithException().collectCertificates(p, flags);
            return;
        }
        if (API_LEVEL >= 23) {
            BRPackageParserMarshmallow.getWithException(parser).collectCertificates(p, flags);
            return;
        }
        if (API_LEVEL >= 22) {
            BRPackageParserLollipop22.getWithException(parser).collectCertificates(p, flags);
        } else if (API_LEVEL >= 21) {
            BRPackageParserLollipop.getWithException(parser).collectCertificates(p, flags);
        } else {
            BRPackageParser.get(parser).collectCertificates(p, flags);
        }
    }
}
