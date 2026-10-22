package black.android.app.backup;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/backup/BRIBackupManager.class */
public class BRIBackupManager {
    public static IBackupManagerStatic getWithException() {
        return (IBackupManagerStatic) BlackReflection.create(IBackupManagerStatic.class, (Object) null, true);
    }

    public static IBackupManagerStatic get() {
        return (IBackupManagerStatic) BlackReflection.create(IBackupManagerStatic.class, (Object) null, false);
    }

    public static IBackupManagerContext getWithException(Object caller) {
        return (IBackupManagerContext) BlackReflection.create(IBackupManagerContext.class, caller, true);
    }

    public static IBackupManagerContext get(Object caller) {
        return (IBackupManagerContext) BlackReflection.create(IBackupManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IBackupManagerContext.class);
    }
}
