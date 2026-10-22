package black.com.android.internal.appwidget;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/appwidget/BRIAppWidgetServiceStub.class */
public class BRIAppWidgetServiceStub {
    public static IAppWidgetServiceStubStatic getWithException() {
        return (IAppWidgetServiceStubStatic) BlackReflection.create(IAppWidgetServiceStubStatic.class, (Object) null, true);
    }

    public static IAppWidgetServiceStubStatic get() {
        return (IAppWidgetServiceStubStatic) BlackReflection.create(IAppWidgetServiceStubStatic.class, (Object) null, false);
    }

    public static IAppWidgetServiceStubContext getWithException(Object caller) {
        return (IAppWidgetServiceStubContext) BlackReflection.create(IAppWidgetServiceStubContext.class, caller, true);
    }

    public static IAppWidgetServiceStubContext get(Object caller) {
        return (IAppWidgetServiceStubContext) BlackReflection.create(IAppWidgetServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IAppWidgetServiceStubContext.class);
    }
}
