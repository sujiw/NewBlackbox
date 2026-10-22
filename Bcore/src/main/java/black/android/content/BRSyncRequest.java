package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRSyncRequest.class */
public class BRSyncRequest {
    public static SyncRequestStatic getWithException() {
        return (SyncRequestStatic) BlackReflection.create(SyncRequestStatic.class, (Object) null, true);
    }

    public static SyncRequestStatic get() {
        return (SyncRequestStatic) BlackReflection.create(SyncRequestStatic.class, (Object) null, false);
    }

    public static SyncRequestContext getWithException(Object caller) {
        return (SyncRequestContext) BlackReflection.create(SyncRequestContext.class, caller, true);
    }

    public static SyncRequestContext get(Object caller) {
        return (SyncRequestContext) BlackReflection.create(SyncRequestContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SyncRequestContext.class);
    }
}
