package top.niunaijun.jnihook.jni;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/jnihook/jni/JniHook.class */
public final class JniHook {
    public static final int NATIVE_OFFSET = 0;
    public static final int NATIVE_OFFSET_2 = 0;

    public static final native void nativeOffset();

    public static final native void nativeOffset2();

    public static native void setAccessible(Class<?> cls, Method method);

    public static native void setAccessible(Class<?> cls, Field field);
}
