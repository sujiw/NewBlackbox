package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRSyncAdapterType.class */
public class BRSyncAdapterType {
    public static SyncAdapterTypeStatic getWithException() {
        return (SyncAdapterTypeStatic) BlackReflection.create(SyncAdapterTypeStatic.class, (Object) null, true);
    }

    public static SyncAdapterTypeStatic get() {
        return (SyncAdapterTypeStatic) BlackReflection.create(SyncAdapterTypeStatic.class, (Object) null, false);
    }

    public static SyncAdapterTypeContext getWithException(Object caller) {
        return (SyncAdapterTypeContext) BlackReflection.create(SyncAdapterTypeContext.class, caller, true);
    }

    public static SyncAdapterTypeContext get(Object caller) {
        return (SyncAdapterTypeContext) BlackReflection.create(SyncAdapterTypeContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SyncAdapterTypeContext.class);
    }
}
