package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRWallpaperManager.class */
public class BRWallpaperManager {
    public static WallpaperManagerStatic getWithException() {
        return (WallpaperManagerStatic) BlackReflection.create(WallpaperManagerStatic.class, (Object) null, true);
    }

    public static WallpaperManagerStatic get() {
        return (WallpaperManagerStatic) BlackReflection.create(WallpaperManagerStatic.class, (Object) null, false);
    }

    public static WallpaperManagerContext getWithException(Object caller) {
        return (WallpaperManagerContext) BlackReflection.create(WallpaperManagerContext.class, caller, true);
    }

    public static WallpaperManagerContext get(Object caller) {
        return (WallpaperManagerContext) BlackReflection.create(WallpaperManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(WallpaperManagerContext.class);
    }
}
