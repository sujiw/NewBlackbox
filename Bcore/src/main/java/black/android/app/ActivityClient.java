package black.android.app;

import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BField;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/ActivityClient.class */
@BClassName("android.app.ActivityClient")
public interface ActivityClient {

    /* JADX INFO: loaded from: classes.jar:black/android/app/ActivityClient$ActivityClientControllerSingleton.class */
    @BClassName("android.app.ActivityClient$ActivityClientControllerSingleton")
    public interface ActivityClientControllerSingleton {
        @BField
        IInterface mKnownInstance();
    }

    @BField
    Object INTERFACE_SINGLETON();

    @BStaticMethod
    Object getInstance();

    @BStaticMethod
    Object getActivityClientController();

    @BStaticMethod
    Object setActivityClientController(Object obj);
}
