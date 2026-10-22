package black.android.os.storage;

import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/os/storage/StorageManager.class */
@BClassName("android.os.storage.StorageManager")
public interface StorageManager {
    @BStaticMethod
    android.os.storage.StorageVolume[] getVolumeList(int i, int i2);
}
