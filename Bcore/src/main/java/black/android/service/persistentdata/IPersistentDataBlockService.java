package black.android.service.persistentdata;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/service/persistentdata/IPersistentDataBlockService.class */
@BClassName("android.service.persistentdata.IPersistentDataBlockService")
public interface IPersistentDataBlockService {

    /* JADX INFO: loaded from: classes.jar:black/android/service/persistentdata/IPersistentDataBlockService$Stub.class */
    @BClassName("android.service.persistentdata.IPersistentDataBlockService$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
