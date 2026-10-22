package black.android.app;

import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BField;
import top.niunaijun.blackreflection.annotation.BStaticField;

/* JADX INFO: loaded from: classes.jar:black/android/app/WallpaperManager.class */
@BClassName("android.app.WallpaperManager")
public interface WallpaperManager {

    /* JADX INFO: loaded from: classes.jar:black/android/app/WallpaperManager$Globals.class */
    @BClassName("android.app.WallpaperManager$Globals")
    public interface Globals {
        @BField
        Object mService();
    }

    @BStaticField
    Object sGlobals();
}
