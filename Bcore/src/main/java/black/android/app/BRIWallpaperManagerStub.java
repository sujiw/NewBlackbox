package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIWallpaperManagerStub.class */
public class BRIWallpaperManagerStub {
    public static IWallpaperManagerStubStatic getWithException() {
        return (IWallpaperManagerStubStatic) BlackReflection.create(IWallpaperManagerStubStatic.class, (Object) null, true);
    }

    public static IWallpaperManagerStubStatic get() {
        return (IWallpaperManagerStubStatic) BlackReflection.create(IWallpaperManagerStubStatic.class, (Object) null, false);
    }

    public static IWallpaperManagerStubContext getWithException(Object caller) {
        return (IWallpaperManagerStubContext) BlackReflection.create(IWallpaperManagerStubContext.class, caller, true);
    }

    public static IWallpaperManagerStubContext get(Object caller) {
        return (IWallpaperManagerStubContext) BlackReflection.create(IWallpaperManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IWallpaperManagerStubContext.class);
    }
}
