package black.android.nfc;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/nfc/INfcAdapter.class */
@BClassName("android.nfc.INfcAdapter")
public interface INfcAdapter {

    /* JADX INFO: loaded from: classes.jar:black/android/nfc/INfcAdapter$Stub.class */
    @BClassName("android.nfc.INfcAdapter$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
