package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRILauncherApps.class */
public class BRILauncherApps {
    public static ILauncherAppsStatic getWithException() {
        return (ILauncherAppsStatic) BlackReflection.create(ILauncherAppsStatic.class, (Object) null, true);
    }

    public static ILauncherAppsStatic get() {
        return (ILauncherAppsStatic) BlackReflection.create(ILauncherAppsStatic.class, (Object) null, false);
    }

    public static ILauncherAppsContext getWithException(Object caller) {
        return (ILauncherAppsContext) BlackReflection.create(ILauncherAppsContext.class, caller, true);
    }

    public static ILauncherAppsContext get(Object caller) {
        return (ILauncherAppsContext) BlackReflection.create(ILauncherAppsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ILauncherAppsContext.class);
    }
}
