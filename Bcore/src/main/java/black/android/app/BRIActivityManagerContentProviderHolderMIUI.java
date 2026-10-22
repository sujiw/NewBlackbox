package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIActivityManagerContentProviderHolderMIUI.class */
public class BRIActivityManagerContentProviderHolderMIUI {
    public static IActivityManagerContentProviderHolderMIUIStatic getWithException() {
        return (IActivityManagerContentProviderHolderMIUIStatic) BlackReflection.create(IActivityManagerContentProviderHolderMIUIStatic.class, (Object) null, true);
    }

    public static IActivityManagerContentProviderHolderMIUIStatic get() {
        return (IActivityManagerContentProviderHolderMIUIStatic) BlackReflection.create(IActivityManagerContentProviderHolderMIUIStatic.class, (Object) null, false);
    }

    public static IActivityManagerContentProviderHolderMIUIContext getWithException(Object caller) {
        return (IActivityManagerContentProviderHolderMIUIContext) BlackReflection.create(IActivityManagerContentProviderHolderMIUIContext.class, caller, true);
    }

    public static IActivityManagerContentProviderHolderMIUIContext get(Object caller) {
        return (IActivityManagerContentProviderHolderMIUIContext) BlackReflection.create(IActivityManagerContentProviderHolderMIUIContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IActivityManagerContentProviderHolderMIUIContext.class);
    }
}
