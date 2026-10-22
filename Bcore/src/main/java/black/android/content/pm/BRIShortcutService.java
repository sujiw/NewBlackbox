package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRIShortcutService.class */
public class BRIShortcutService {
    public static IShortcutServiceStatic getWithException() {
        return (IShortcutServiceStatic) BlackReflection.create(IShortcutServiceStatic.class, (Object) null, true);
    }

    public static IShortcutServiceStatic get() {
        return (IShortcutServiceStatic) BlackReflection.create(IShortcutServiceStatic.class, (Object) null, false);
    }

    public static IShortcutServiceContext getWithException(Object caller) {
        return (IShortcutServiceContext) BlackReflection.create(IShortcutServiceContext.class, caller, true);
    }

    public static IShortcutServiceContext get(Object caller) {
        return (IShortcutServiceContext) BlackReflection.create(IShortcutServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IShortcutServiceContext.class);
    }
}
