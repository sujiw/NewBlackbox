package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRIActivityManagerContentProviderHolder.class */
public class BRIActivityManagerContentProviderHolder {
    public static IActivityManagerContentProviderHolderStatic getWithException() {
        return (IActivityManagerContentProviderHolderStatic) BlackReflection.create(IActivityManagerContentProviderHolderStatic.class, (Object) null, true);
    }

    public static IActivityManagerContentProviderHolderStatic get() {
        return (IActivityManagerContentProviderHolderStatic) BlackReflection.create(IActivityManagerContentProviderHolderStatic.class, (Object) null, false);
    }

    public static IActivityManagerContentProviderHolderContext getWithException(Object caller) {
        return (IActivityManagerContentProviderHolderContext) BlackReflection.create(IActivityManagerContentProviderHolderContext.class, caller, true);
    }

    public static IActivityManagerContentProviderHolderContext get(Object caller) {
        return (IActivityManagerContentProviderHolderContext) BlackReflection.create(IActivityManagerContentProviderHolderContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IActivityManagerContentProviderHolderContext.class);
    }
}
