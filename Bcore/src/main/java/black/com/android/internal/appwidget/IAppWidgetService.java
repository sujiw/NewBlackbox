package black.com.android.internal.appwidget;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/appwidget/IAppWidgetService.class */
@BClassName("com.android.internal.appwidget.IAppWidgetService")
public interface IAppWidgetService {

    /* JADX INFO: loaded from: classes.jar:black/com/android/internal/appwidget/IAppWidgetService$Stub.class */
    @BClassName("com.android.internal.appwidget.IAppWidgetService$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
