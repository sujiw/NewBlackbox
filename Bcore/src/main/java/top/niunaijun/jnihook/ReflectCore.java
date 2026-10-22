package top.niunaijun.jnihook;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.jnihook.jni.JniHook;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/jnihook/ReflectCore.class */
public class ReflectCore {
    public static void set(Class<?> clazz) {
        try {
            Field accessFlags = Class.class.getDeclaredField("accessFlags");
            accessFlags.setAccessible(true);
            int o = ((Integer) accessFlags.get(clazz)).intValue();
            accessFlags.set(clazz, Integer.valueOf(o | 1));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            JniHook.setAccessible(clazz, declaredMethod);
        }
        for (Field declaredField : clazz.getDeclaredFields()) {
            JniHook.setAccessible(clazz, declaredField);
        }
        for (Class<?> declaredClass : clazz.getDeclaredClasses()) {
            set(declaredClass);
        }
    }
}
