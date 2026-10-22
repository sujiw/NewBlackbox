package black.com.android.internal.telephony;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/IMms.class */
@BClassName("com.android.internal.telephony.IMms")
public interface IMms {

    /* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/IMms$Stub.class */
    @BClassName("com.android.internal.telephony.IMms$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
