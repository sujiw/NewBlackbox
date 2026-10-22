package black.android.widget;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/widget/BRRemoteViews.class */
public class BRRemoteViews {
    public static RemoteViewsStatic getWithException() {
        return (RemoteViewsStatic) BlackReflection.create(RemoteViewsStatic.class, (Object) null, true);
    }

    public static RemoteViewsStatic get() {
        return (RemoteViewsStatic) BlackReflection.create(RemoteViewsStatic.class, (Object) null, false);
    }

    public static RemoteViewsContext getWithException(Object caller) {
        return (RemoteViewsContext) BlackReflection.create(RemoteViewsContext.class, caller, true);
    }

    public static RemoteViewsContext get(Object caller) {
        return (RemoteViewsContext) BlackReflection.create(RemoteViewsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(RemoteViewsContext.class);
    }
}
