package black.android.app;

import android.app.Application;
import android.content.Context;
import android.os.IBinder;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BParamClass;

/* JADX INFO: loaded from: classes.jar:black/android/app/ServiceContext.class */
@BClassNameNotProcess("android.app.Service")
public interface ServiceContext {
    @BMethodCheckNotProcess
    Method _check_attach(Context context, @BParamClass(android.app.ActivityThread.class) Object obj, String str, IBinder iBinder, Application application, Object obj2);

    Void attach(Context context, @BParamClass(android.app.ActivityThread.class) Object obj, String str, IBinder iBinder, Application application, Object obj2);
}
