package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRSyncAdapterTypeN.class */
public class BRSyncAdapterTypeN {
    public static SyncAdapterTypeNStatic getWithException() {
        return (SyncAdapterTypeNStatic) BlackReflection.create(SyncAdapterTypeNStatic.class, (Object) null, true);
    }

    public static SyncAdapterTypeNStatic get() {
        return (SyncAdapterTypeNStatic) BlackReflection.create(SyncAdapterTypeNStatic.class, (Object) null, false);
    }

    public static SyncAdapterTypeNContext getWithException(Object caller) {
        return (SyncAdapterTypeNContext) BlackReflection.create(SyncAdapterTypeNContext.class, caller, true);
    }

    public static SyncAdapterTypeNContext get(Object caller) {
        return (SyncAdapterTypeNContext) BlackReflection.create(SyncAdapterTypeNContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SyncAdapterTypeNContext.class);
    }
}
