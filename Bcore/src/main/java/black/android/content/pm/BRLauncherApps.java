package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRLauncherApps.class */
public class BRLauncherApps {
    public static LauncherAppsStatic getWithException() {
        return (LauncherAppsStatic) BlackReflection.create(LauncherAppsStatic.class, (Object) null, true);
    }

    public static LauncherAppsStatic get() {
        return (LauncherAppsStatic) BlackReflection.create(LauncherAppsStatic.class, (Object) null, false);
    }

    public static LauncherAppsContext getWithException(Object caller) {
        return (LauncherAppsContext) BlackReflection.create(LauncherAppsContext.class, caller, true);
    }

    public static LauncherAppsContext get(Object caller) {
        return (LauncherAppsContext) BlackReflection.create(LauncherAppsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(LauncherAppsContext.class);
    }
}
