package black.android.content.res;

import android.content.pm.ApplicationInfo;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BConstructor;
import top.niunaijun.blackreflection.annotation.BStaticField;

/* JADX INFO: loaded from: classes.jar:black/android/content/res/CompatibilityInfo.class */
@BClassName("android.content.res.CompatibilityInfo")
public interface CompatibilityInfo {
    @BConstructor
    CompatibilityInfo _new(ApplicationInfo applicationInfo, int i, int i2, boolean z);

    @BConstructor
    CompatibilityInfo _new(ApplicationInfo applicationInfo, int i, int i2, boolean z, int i3);

    @BStaticField
    Object DEFAULT_COMPATIBILITY_INFO();
}
