package black.android.content;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/BRSyncInfo.class */
public class BRSyncInfo {
    public static SyncInfoStatic getWithException() {
        return (SyncInfoStatic) BlackReflection.create(SyncInfoStatic.class, (Object) null, true);
    }

    public static SyncInfoStatic get() {
        return (SyncInfoStatic) BlackReflection.create(SyncInfoStatic.class, (Object) null, false);
    }

    public static SyncInfoContext getWithException(Object caller) {
        return (SyncInfoContext) BlackReflection.create(SyncInfoContext.class, caller, true);
    }

    public static SyncInfoContext get(Object caller) {
        return (SyncInfoContext) BlackReflection.create(SyncInfoContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SyncInfoContext.class);
    }
}
