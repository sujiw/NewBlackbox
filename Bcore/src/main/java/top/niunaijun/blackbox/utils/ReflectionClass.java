package top.niunaijun.blackbox.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/ReflectionClass.class */
public class ReflectionClass {
    public static void print(String name) {
        try {
            Class<?> cls = Class.forName(name);
            Class<?> superclass = cls.getSuperclass();
            Class<?>[] interfaces = cls.getInterfaces();
            String modifiers = Modifier.toString(cls.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers);
            }
            if (!cls.isInterface()) {
                System.out.print(" class ");
            }
            printType(cls);
            if (superclass != null && superclass != Object.class) {
                System.out.print(" extends ");
                printType(superclass);
            }
            if (interfaces.length > 0) {
                System.out.print(" implements ");
                for (int j = 0; j < interfaces.length; j++) {
                    if (j > 0) {
                        System.out.print(", ");
                    }
                    printType(interfaces[j]);
                }
            }
            System.out.print(" {\n");
            printConstructors(cls);
            System.out.println();
            printMethods(cls);
            System.out.println();
            printFields(cls);
            System.out.println("}");
            printExtendsChain(cls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printConstructors(Class cl) {
        for (Constructor<?> constructor : cl.getDeclaredConstructors()) {
            String name = constructor.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(constructor.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(name.substring(name.lastIndexOf(".") + 1) + " (");
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                if (j > 0) {
                    System.out.print(", ");
                }
                printType(parameterTypes[j]);
            }
            System.out.print(")");
            Class<?>[] exceptionTypes = constructor.getExceptionTypes();
            if (exceptionTypes.length > 0) {
                System.out.print(" throws ");
                for (int j2 = 0; j2 < exceptionTypes.length; j2++) {
                    if (j2 > 0) {
                        System.out.print(", ");
                    }
                    printType(exceptionTypes[j2]);
                }
            }
            System.out.println(";");
        }
    }

    private static void printMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods) {
            Class<?> returnType = m.getReturnType();
            String name = m.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            printType(returnType);
            System.out.print(" " + name + "(");
            Class<?>[] parameterTypes = m.getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                if (j > 0) {
                    System.out.print(", ");
                }
                printType(parameterTypes[j]);
            }
            System.out.print(")");
            Class<?>[] exceptionTypes = m.getExceptionTypes();
            if (exceptionTypes.length > 0) {
                System.out.print(" throws ");
                for (int j2 = 0; j2 < exceptionTypes.length; j2++) {
                    if (j2 > 0) {
                        System.out.print(", ");
                    }
                    printType(exceptionTypes[j2]);
                }
            }
            System.out.println(";");
        }
    }

    private static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields) {
            Class<?> type = f.getType();
            String name = f.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            printType(type);
            System.out.println(" " + name + ";");
        }
    }

    private static void printType(Class type) {
        String name = type.getName();
        if (name.startsWith("[")) {
            if (name.startsWith("[C")) {
                System.out.print("char[]");
                return;
            }
            if (name.startsWith("[B")) {
                System.out.print("byte[]");
                return;
            }
            if (name.startsWith("[S")) {
                System.out.print("short[]");
                return;
            }
            if (name.startsWith("[I")) {
                System.out.print("int[]");
                return;
            }
            if (name.startsWith("[J")) {
                System.out.print("long[]");
                return;
            }
            if (name.startsWith("[F")) {
                System.out.print("float[]");
                return;
            }
            if (name.startsWith("[D")) {
                System.out.print("double[]");
                return;
            } else if (name.startsWith("[Z")) {
                System.out.print("boolean[]");
                return;
            } else {
                if (name.startsWith("[L")) {
                    System.out.print(name.substring(name.lastIndexOf(".") + 1, name.length() - 1) + "[]");
                    return;
                }
                return;
            }
        }
        System.out.print(name.substring(name.lastIndexOf(".") + 1));
    }

    public static void printExtendsChain(Class cl) {
        System.out.print("\n\nExtends chain:\n    ");
        printType(cl);
        Class superclass = cl.getSuperclass();
        while (true) {
            Class superclass2 = superclass;
            if (superclass2 != Object.class) {
                System.out.print(" --> ");
                printType(superclass2);
                superclass = superclass2.getSuperclass();
            } else {
                System.out.print(" --> ");
                printType(superclass2);
                return;
            }
        }
    }
}
