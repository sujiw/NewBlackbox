package black.android.os.storage;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/os/storage/BRStorageVolume.class */
public class BRStorageVolume {
    public static StorageVolumeStatic getWithException() {
        return (StorageVolumeStatic) BlackReflection.create(StorageVolumeStatic.class, (Object) null, true);
    }

    public static StorageVolumeStatic get() {
        return (StorageVolumeStatic) BlackReflection.create(StorageVolumeStatic.class, (Object) null, false);
    }

    public static StorageVolumeContext getWithException(Object caller) {
        return (StorageVolumeContext) BlackReflection.create(StorageVolumeContext.class, caller, true);
    }

    public static StorageVolumeContext get(Object caller) {
        return (StorageVolumeContext) BlackReflection.create(StorageVolumeContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(StorageVolumeContext.class);
    }
}
