package black.android.os;

import android.os.IBinder;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BMethod;

/* JADX INFO: loaded from: classes.jar:black/android/os/Bundle.class */
@BClassName("android.os.Bundle")
public interface Bundle {
    @BMethod
    IBinder getIBinder(String str);

    @BMethod
    void putIBinder(String str, IBinder iBinder);
}
