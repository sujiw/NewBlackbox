package black.com.android.internal.appwidget;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/appwidget/BRIAppWidgetService.class */
public class BRIAppWidgetService {
    public static IAppWidgetServiceStatic getWithException() {
        return (IAppWidgetServiceStatic) BlackReflection.create(IAppWidgetServiceStatic.class, (Object) null, true);
    }

    public static IAppWidgetServiceStatic get() {
        return (IAppWidgetServiceStatic) BlackReflection.create(IAppWidgetServiceStatic.class, (Object) null, false);
    }

    public static IAppWidgetServiceContext getWithException(Object caller) {
        return (IAppWidgetServiceContext) BlackReflection.create(IAppWidgetServiceContext.class, caller, true);
    }

    public static IAppWidgetServiceContext get(Object caller) {
        return (IAppWidgetServiceContext) BlackReflection.create(IAppWidgetServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IAppWidgetServiceContext.class);
    }
}
