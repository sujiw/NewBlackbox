package top.niunaijun.jnihook;

import androidx.annotation.Keep;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/jnihook/MethodUtils.class */
@Keep
public class MethodUtils {
    public static String getDeclaringClass(Method method) {
        return method.getDeclaringClass().getName().replace(".", "/");
    }

    public static String getMethodName(Method method) {
        return method.getName();
    }

    public static String getDesc(Method method) {
        StringBuffer buf = new StringBuffer();
        buf.append("(");
        Class<?>[] types = method.getParameterTypes();
        for (Class<?> cls : types) {
            buf.append(getDesc(cls));
        }
        buf.append(")");
        buf.append(getDesc(method.getReturnType()));
        return buf.toString();
    }

    private static String getDesc(Class<?> returnType) {
        if (returnType.isPrimitive()) {
            return getPrimitiveLetter(returnType);
        }
        if (returnType.isArray()) {
            return "[" + getDesc(returnType.getComponentType());
        }
        return "L" + getType(returnType) + ";";
    }

    private static String getType(Class<?> parameterType) {
        if (parameterType.isArray()) {
            return "[" + getDesc(parameterType.getComponentType());
        }
        if (!parameterType.isPrimitive()) {
            String clsName = parameterType.getName();
            return clsName.replaceAll("\\.", "/");
        }
        return getPrimitiveLetter(parameterType);
    }

    private static String getPrimitiveLetter(Class<?> type) {
        if (Integer.TYPE.equals(type)) {
            return "I";
        }
        if (Void.TYPE.equals(type)) {
            return "V";
        }
        if (Boolean.TYPE.equals(type)) {
            return "Z";
        }
        if (Character.TYPE.equals(type)) {
            return "C";
        }
        if (Byte.TYPE.equals(type)) {
            return "B";
        }
        if (Short.TYPE.equals(type)) {
            return "S";
        }
        if (Float.TYPE.equals(type)) {
            return "F";
        }
        if (Long.TYPE.equals(type)) {
            return "J";
        }
        if (Double.TYPE.equals(type)) {
            return "D";
        }
        throw new IllegalStateException("Type: " + type.getCanonicalName() + " is not a primitive type");
    }
}
