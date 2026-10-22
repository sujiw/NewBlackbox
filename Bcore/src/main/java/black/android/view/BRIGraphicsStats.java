package black.android.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/BRIGraphicsStats.class */
public class BRIGraphicsStats {
    public static IGraphicsStatsStatic getWithException() {
        return (IGraphicsStatsStatic) BlackReflection.create(IGraphicsStatsStatic.class, (Object) null, true);
    }

    public static IGraphicsStatsStatic get() {
        return (IGraphicsStatsStatic) BlackReflection.create(IGraphicsStatsStatic.class, (Object) null, false);
    }

    public static IGraphicsStatsContext getWithException(Object caller) {
        return (IGraphicsStatsContext) BlackReflection.create(IGraphicsStatsContext.class, caller, true);
    }

    public static IGraphicsStatsContext get(Object caller) {
        return (IGraphicsStatsContext) BlackReflection.create(IGraphicsStatsContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IGraphicsStatsContext.class);
    }
}
