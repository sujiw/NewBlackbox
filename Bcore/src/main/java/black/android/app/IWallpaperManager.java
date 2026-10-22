package black.android.app;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/IWallpaperManager.class */
@BClassName("android.app.IWallpaperManager")
public interface IWallpaperManager {

    /* JADX INFO: loaded from: classes.jar:black/android/app/IWallpaperManager$Stub.class */
    @BClassName("android.app.IWallpaperManager$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
