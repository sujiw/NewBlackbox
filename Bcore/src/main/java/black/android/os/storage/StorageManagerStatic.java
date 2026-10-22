package black.android.os.storage;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/os/storage/StorageManagerStatic.class */
@BClassNameNotProcess("android.os.storage.StorageManager")
public interface StorageManagerStatic {
    @BMethodCheckNotProcess
    Method _check_getVolumeList(int i, int i2);

    android.os.storage.StorageVolume[] getVolumeList(int i, int i2);
}
