package black.com.android.internal.telephony;

import android.os.IBinder;
import android.os.IInterface;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/telephony/ITelephonyStubStatic.class */
@BClassNameNotProcess("com.android.internal.telephony.ITelephony$Stub")
public interface ITelephonyStubStatic {
    @BMethodCheckNotProcess
    Method _check_asInterface(IBinder iBinder);

    IInterface asInterface(IBinder iBinder);
}
