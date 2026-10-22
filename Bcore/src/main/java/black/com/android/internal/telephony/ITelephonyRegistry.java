package black.com.android.internal.telephony;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/ITelephonyRegistry.class */
@BClassName("com.android.internal.telephony.ITelephonyRegistry")
public interface ITelephonyRegistry {

    /* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/ITelephonyRegistry$Stub.class */
    @BClassName("com.android.internal.telephony.ITelephonyRegistry$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
