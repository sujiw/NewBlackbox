package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRIShortcutServiceStub.class */
public class BRIShortcutServiceStub {
    public static IShortcutServiceStubStatic getWithException() {
        return (IShortcutServiceStubStatic) BlackReflection.create(IShortcutServiceStubStatic.class, (Object) null, true);
    }

    public static IShortcutServiceStubStatic get() {
        return (IShortcutServiceStubStatic) BlackReflection.create(IShortcutServiceStubStatic.class, (Object) null, false);
    }

    public static IShortcutServiceStubContext getWithException(Object caller) {
        return (IShortcutServiceStubContext) BlackReflection.create(IShortcutServiceStubContext.class, caller, true);
    }

    public static IShortcutServiceStubContext get(Object caller) {
        return (IShortcutServiceStubContext) BlackReflection.create(IShortcutServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IShortcutServiceStubContext.class);
    }
}
