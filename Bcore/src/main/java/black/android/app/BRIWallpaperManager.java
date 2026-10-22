package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIWallpaperManager.class */
public class BRIWallpaperManager {
    public static IWallpaperManagerStatic getWithException() {
        return (IWallpaperManagerStatic) BlackReflection.create(IWallpaperManagerStatic.class, (Object) null, true);
    }

    public static IWallpaperManagerStatic get() {
        return (IWallpaperManagerStatic) BlackReflection.create(IWallpaperManagerStatic.class, (Object) null, false);
    }

    public static IWallpaperManagerContext getWithException(Object caller) {
        return (IWallpaperManagerContext) BlackReflection.create(IWallpaperManagerContext.class, caller, true);
    }

    public static IWallpaperManagerContext get(Object caller) {
        return (IWallpaperManagerContext) BlackReflection.create(IWallpaperManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IWallpaperManagerContext.class);
    }
}
