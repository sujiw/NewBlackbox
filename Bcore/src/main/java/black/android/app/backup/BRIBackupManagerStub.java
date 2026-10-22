package black.android.app.backup;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/backup/BRIBackupManagerStub.class */
public class BRIBackupManagerStub {
    public static IBackupManagerStubStatic getWithException() {
        return (IBackupManagerStubStatic) BlackReflection.create(IBackupManagerStubStatic.class, (Object) null, true);
    }

    public static IBackupManagerStubStatic get() {
        return (IBackupManagerStubStatic) BlackReflection.create(IBackupManagerStubStatic.class, (Object) null, false);
    }

    public static IBackupManagerStubContext getWithException(Object caller) {
        return (IBackupManagerStubContext) BlackReflection.create(IBackupManagerStubContext.class, caller, true);
    }

    public static IBackupManagerStubContext get(Object caller) {
        return (IBackupManagerStubContext) BlackReflection.create(IBackupManagerStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IBackupManagerStubContext.class);
    }
}
