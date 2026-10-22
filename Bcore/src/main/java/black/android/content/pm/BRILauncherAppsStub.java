package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRILauncherAppsStub.class */
public class BRILauncherAppsStub {
    public static ILauncherAppsStubStatic getWithException() {
        return (ILauncherAppsStubStatic) BlackReflection.create(ILauncherAppsStubStatic.class, (Object) null, true);
    }

    public static ILauncherAppsStubStatic get() {
        return (ILauncherAppsStubStatic) BlackReflection.create(ILauncherAppsStubStatic.class, (Object) null, false);
    }

    public static ILauncherAppsStubContext getWithException(Object caller) {
        return (ILauncherAppsStubContext) BlackReflection.create(ILauncherAppsStubContext.class, caller, true);
    }

    public static ILauncherAppsStubContext get(Object caller) {
        return (ILauncherAppsStubContext) BlackReflection.create(ILauncherAppsStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ILauncherAppsStubContext.class);
    }
}
