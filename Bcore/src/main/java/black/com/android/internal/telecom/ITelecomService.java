package black.com.android.internal.telecom;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telecom/ITelecomService.class */
@BClassName("com.android.internal.telecom.ITelecomService")
public interface ITelecomService {

    /* JADX INFO: loaded from: classes.jar:black/com/android/internal/telecom/ITelecomService$Stub.class */
    @BClassName("com.android.internal.telecom.ITelecomService$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
