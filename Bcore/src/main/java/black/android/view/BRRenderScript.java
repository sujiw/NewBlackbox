package black.android.view;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/view/BRRenderScript.class */
public class BRRenderScript {
    public static RenderScriptStatic getWithException() {
        return (RenderScriptStatic) BlackReflection.create(RenderScriptStatic.class, (Object) null, true);
    }

    public static RenderScriptStatic get() {
        return (RenderScriptStatic) BlackReflection.create(RenderScriptStatic.class, (Object) null, false);
    }

    public static RenderScriptContext getWithException(Object caller) {
        return (RenderScriptContext) BlackReflection.create(RenderScriptContext.class, caller, true);
    }

    public static RenderScriptContext get(Object caller) {
        return (RenderScriptContext) BlackReflection.create(RenderScriptContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(RenderScriptContext.class);
    }
}
