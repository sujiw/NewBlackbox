package black.android.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/BRIGraphicsStatsStub.class */
public class BRIGraphicsStatsStub {
    public static IGraphicsStatsStubStatic getWithException() {
        return (IGraphicsStatsStubStatic) BlackReflection.create(IGraphicsStatsStubStatic.class, (Object) null, true);
    }

    public static IGraphicsStatsStubStatic get() {
        return (IGraphicsStatsStubStatic) BlackReflection.create(IGraphicsStatsStubStatic.class, (Object) null, false);
    }

    public static IGraphicsStatsStubContext getWithException(Object caller) {
        return (IGraphicsStatsStubContext) BlackReflection.create(IGraphicsStatsStubContext.class, caller, true);
    }

    public static IGraphicsStatsStubContext get(Object caller) {
        return (IGraphicsStatsStubContext) BlackReflection.create(IGraphicsStatsStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IGraphicsStatsStubContext.class);
    }
}
