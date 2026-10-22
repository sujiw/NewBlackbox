package black.android.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/BRSurfaceControl.class */
public class BRSurfaceControl {
    public static SurfaceControlStatic getWithException() {
        return (SurfaceControlStatic) BlackReflection.create(SurfaceControlStatic.class, (Object) null, true);
    }

    public static SurfaceControlStatic get() {
        return (SurfaceControlStatic) BlackReflection.create(SurfaceControlStatic.class, (Object) null, false);
    }

    public static SurfaceControlContext getWithException(Object caller) {
        return (SurfaceControlContext) BlackReflection.create(SurfaceControlContext.class, caller, true);
    }

    public static SurfaceControlContext get(Object caller) {
        return (SurfaceControlContext) BlackReflection.create(SurfaceControlContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(SurfaceControlContext.class);
    }
}
