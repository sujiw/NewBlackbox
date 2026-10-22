package black.android.content.pm;

import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BConstructor;
import top.niunaijun.blackreflection.annotation.BField;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/SigningInfo.class */
@BClassName("android.content.pm.SigningInfo")
public interface SigningInfo {
    @BConstructor
    android.content.pm.SigningInfo _new(android.content.pm.PackageParser.SigningDetails signingDetails);

    @BField
    android.content.pm.PackageParser.SigningDetails mSigningDetails();
}
