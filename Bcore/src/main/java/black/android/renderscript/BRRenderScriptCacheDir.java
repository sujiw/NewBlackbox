package black.android.renderscript;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/renderscript/BRRenderScriptCacheDir.class */
public class BRRenderScriptCacheDir {
    public static RenderScriptCacheDirStatic getWithException() {
        return (RenderScriptCacheDirStatic) BlackReflection.create(RenderScriptCacheDirStatic.class, (Object) null, true);
    }

    public static RenderScriptCacheDirStatic get() {
        return (RenderScriptCacheDirStatic) BlackReflection.create(RenderScriptCacheDirStatic.class, (Object) null, false);
    }

    public static RenderScriptCacheDirContext getWithException(Object caller) {
        return (RenderScriptCacheDirContext) BlackReflection.create(RenderScriptCacheDirContext.class, caller, true);
    }

    public static RenderScriptCacheDirContext get(Object caller) {
        return (RenderScriptCacheDirContext) BlackReflection.create(RenderScriptCacheDirContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(RenderScriptCacheDirContext.class);
    }
}
