package black.com.android.internal.telephony;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/ISms.class */
@BClassName("com.android.internal.telephony.ISms")
public interface ISms {

    /* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/ISms$Stub.class */
    @BClassName("com.android.internal.telephony.ISms$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
