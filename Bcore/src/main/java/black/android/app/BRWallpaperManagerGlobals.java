package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRWallpaperManagerGlobals.class */
public class BRWallpaperManagerGlobals {
    public static WallpaperManagerGlobalsStatic getWithException() {
        return (WallpaperManagerGlobalsStatic) BlackReflection.create(WallpaperManagerGlobalsStatic.class, (Object) null, true);
    }

    public static WallpaperManagerGlobalsStatic get() {
        return (WallpaperManagerGlobalsStatic) BlackReflection.create(WallpaperManagerGlobalsStatic.class, (Object) null, false);
    }

    public static WallpaperManagerGlobalsContext getWithException(Object caller) {
        return (WallpaperManagerGlobalsContext) BlackReflection.create(WallpaperManagerGlobalsContext.class, caller, true);
    }

    public static WallpaperManagerGlobalsContext get(Object caller) {
        return (WallpaperManagerGlobalsContext) BlackReflection.create(WallpaperManagerGlobalsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(WallpaperManagerGlobalsContext.class);
    }
}
