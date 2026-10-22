package black.android.content.res;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/res/BRAssetManager.class */
public class BRAssetManager {
    public static AssetManagerStatic getWithException() {
        return (AssetManagerStatic) BlackReflection.create(AssetManagerStatic.class, (Object) null, true);
    }

    public static AssetManagerStatic get() {
        return (AssetManagerStatic) BlackReflection.create(AssetManagerStatic.class, (Object) null, false);
    }

    public static AssetManagerContext getWithException(Object caller) {
        return (AssetManagerContext) BlackReflection.create(AssetManagerContext.class, caller, true);
    }

    public static AssetManagerContext get(Object caller) {
        return (AssetManagerContext) BlackReflection.create(AssetManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(AssetManagerContext.class);
    }
}
