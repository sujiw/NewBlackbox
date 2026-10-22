package black.android.content.pm;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/IShortcutService.class */
@BClassName("android.content.pm.IShortcutService")
public interface IShortcutService {

    /* JADX INFO: loaded from: classes.jar:black/android/content/pm/IShortcutService$Stub.class */
    @BClassName("android.content.pm.IShortcutService$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
