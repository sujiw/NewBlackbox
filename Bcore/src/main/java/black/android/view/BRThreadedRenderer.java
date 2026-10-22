package black.android.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/BRThreadedRenderer.class */
public class BRThreadedRenderer {
    public static ThreadedRendererStatic getWithException() {
        return (ThreadedRendererStatic) BlackReflection.create(ThreadedRendererStatic.class, (Object) null, true);
    }

    public static ThreadedRendererStatic get() {
        return (ThreadedRendererStatic) BlackReflection.create(ThreadedRendererStatic.class, (Object) null, false);
    }

    public static ThreadedRendererContext getWithException(Object caller) {
        return (ThreadedRendererContext) BlackReflection.create(ThreadedRendererContext.class, caller, true);
    }

    public static ThreadedRendererContext get(Object caller) {
        return (ThreadedRendererContext) BlackReflection.create(ThreadedRendererContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ThreadedRendererContext.class);
    }
}
