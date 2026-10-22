package top.niunaijun.blackbox.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/Reflector.class */
public class Reflector {
    public static final String LOG_TAG = "Reflector";
    protected Class<?> mType;
    protected Object mCaller;
    protected Constructor mConstructor;
    protected Field mField;
    protected Method mMethod;

    public static Reflector on(String name) throws Exception {
        return on(name, true, Reflector.class.getClassLoader());
    }

    public static Reflector on(String name, boolean initialize) throws Exception {
        return on(name, initialize, Reflector.class.getClassLoader());
    }

    public static Reflector on(String name, boolean initialize, ClassLoader loader) throws Exception {
        try {
            return on(Class.forName(name, initialize, loader));
        } catch (Throwable e) {
            throw new Exception("Oops!", e);
        }
    }

    public static Reflector on(Class<?> type) {
        Reflector reflector = new Reflector();
        reflector.mType = type;
        return reflector;
    }

    public static Reflector with(Object caller) throws Exception {
        return on(caller.getClass()).bind(caller);
    }

    protected Reflector() {
    }

    public Reflector constructor(Class<?>... parameterTypes) throws Exception {
        try {
            this.mConstructor = this.mType.getDeclaredConstructor(parameterTypes);
            this.mConstructor.setAccessible(true);
            this.mField = null;
            this.mMethod = null;
            return this;
        } catch (Throwable e) {
            throw new Exception("Oops!", e);
        }
    }

    public <R> R newInstance(Object... objArr) throws Exception {
        if (this.mConstructor == null) {
            throw new Exception("Constructor was null!");
        }
        try {
            return (R) this.mConstructor.newInstance(objArr);
        } catch (InvocationTargetException e) {
            throw new Exception("Oops!", e.getTargetException());
        } catch (Throwable th) {
            throw new Exception("Oops!", th);
        }
    }

    protected Object checked(Object caller) throws Exception {
        if (caller == null || this.mType.isInstance(caller)) {
            return caller;
        }
        throw new Exception("Caller [" + caller + "] is not a instance of type [" + this.mType + "]!");
    }

    protected void check(Object caller, Member member, String name) throws Exception {
        if (member == null) {
            throw new Exception(name + " was null!");
        }
        if (caller == null && !Modifier.isStatic(member.getModifiers())) {
            throw new Exception("Need a caller!");
        }
        checked(caller);
    }

    public Reflector bind(Object caller) throws Exception {
        this.mCaller = checked(caller);
        return this;
    }

    public Reflector unbind() {
        this.mCaller = null;
        return this;
    }

    public Reflector field(String name) throws Exception {
        try {
            this.mField = findField(name);
            this.mField.setAccessible(true);
            this.mConstructor = null;
            this.mMethod = null;
            return this;
        } catch (Throwable e) {
            throw new Exception("Oops!", e);
        }
    }

    protected Field findField(String name) throws NoSuchFieldException {
        try {
            return this.mType.getField(name);
        } catch (NoSuchFieldException e) {
            Class<?> superclass = this.mType;
            while (true) {
                Class<?> cls = superclass;
                if (cls != null) {
                    try {
                        return cls.getDeclaredField(name);
                    } catch (NoSuchFieldException e2) {
                        superclass = cls.getSuperclass();
                    }
                } else {
                    throw e;
                }
            }
        }
    }

    public <R> R get() throws Exception {
        return (R) get(this.mCaller);
    }

    public <R> R get(Object obj) throws Exception {
        check(obj, this.mField, "Field");
        try {
            return (R) this.mField.get(obj);
        } catch (Throwable th) {
            throw new Exception("Oops!", th);
        }
    }

    public Reflector set(Object value) throws Exception {
        return set(this.mCaller, value);
    }

    public Reflector set(Object caller, Object value) throws Exception {
        check(caller, this.mField, "Field");
        try {
            this.mField.set(caller, value);
            return this;
        } catch (Throwable e) {
            throw new Exception("Oops!", e);
        }
    }

    public Reflector method(String name, Class<?>... parameterTypes) throws Exception {
        try {
            this.mMethod = findMethod(name, parameterTypes);
            this.mMethod.setAccessible(true);
            this.mConstructor = null;
            this.mField = null;
            return this;
        } catch (NoSuchMethodException e) {
            throw new Exception("Oops!", e);
        }
    }

    protected Method findMethod(String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        try {
            return this.mType.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            Class<?> superclass = this.mType;
            while (true) {
                Class<?> cls = superclass;
                if (cls != null) {
                    try {
                        return cls.getDeclaredMethod(name, parameterTypes);
                    } catch (NoSuchMethodException e2) {
                        superclass = cls.getSuperclass();
                    }
                } else {
                    throw e;
                }
            }
        }
    }

    public <R> R call(Object... objArr) throws Exception {
        return (R) callByCaller(this.mCaller, objArr);
    }

    public <R> R callByCaller(Object obj, Object... objArr) throws Exception {
        check(obj, this.mMethod, "Method");
        try {
            return (R) this.mMethod.invoke(obj, objArr);
        } catch (InvocationTargetException e) {
            throw new Exception("Oops!", e.getTargetException());
        } catch (Throwable th) {
            throw new Exception("Oops!", th);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/Reflector$QuietReflector.class */
    public static class QuietReflector extends Reflector {
        protected Throwable mIgnored;

        public static QuietReflector on(String name) {
            return on(name, true, QuietReflector.class.getClassLoader());
        }

        public static QuietReflector on(String name, boolean initialize) {
            return on(name, initialize, QuietReflector.class.getClassLoader());
        }

        public static QuietReflector on(String name, boolean initialize, ClassLoader loader) {
            Class<?> cls = null;
            try {
                cls = Class.forName(name, initialize, loader);
                return on(cls, (Throwable) null);
            } catch (Throwable e) {
                return on(cls, e);
            }
        }

        public static QuietReflector on(Class<?> type) {
            return on(type, type == null ? new Exception("Type was null!") : null);
        }

        private static QuietReflector on(Class<?> type, Throwable ignored) {
            QuietReflector reflector = new QuietReflector();
            reflector.mType = type;
            reflector.mIgnored = ignored;
            return reflector;
        }

        public static QuietReflector with(Object caller) {
            if (caller == null) {
                return on((Class<?>) null);
            }
            return on(caller.getClass()).bind(caller);
        }

        protected QuietReflector() {
        }

        public Throwable getIgnored() {
            return this.mIgnored;
        }

        protected boolean skip() {
            return skipAlways() || this.mIgnored != null;
        }

        protected boolean skipAlways() {
            return this.mType == null;
        }

        @Override // top.niunaijun.blackbox.utils.Reflector
        public QuietReflector constructor(Class<?>... parameterTypes) {
            if (skipAlways()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.constructor(parameterTypes);
            } catch (Throwable e) {
                this.mIgnored = e;
            }
            return this;
        }

        @Override // top.niunaijun.blackbox.utils.Reflector
        public <R> R newInstance(Object... objArr) {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.newInstance(objArr);
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }

        @Override // top.niunaijun.blackbox.utils.Reflector
        public QuietReflector bind(Object obj) {
            if (skipAlways()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.bind(obj);
            } catch (Throwable e) {
                this.mIgnored = e;
            }
            return this;
        }

        @Override // top.niunaijun.blackbox.utils.Reflector
        public QuietReflector unbind() {
            super.unbind();
            return this;
        }

        @Override // top.niunaijun.blackbox.utils.Reflector
        public QuietReflector field(String name) {
            if (skipAlways()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.field(name);
            } catch (Throwable e) {
                this.mIgnored = e;
            }
            return this;
        }

        @Override // top.niunaijun.blackbox.utils.Reflector
        public <R> R get() {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.get();
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }

        @Override // top.niunaijun.blackbox.utils.Reflector
        public <R> R get(Object obj) {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.get(obj);
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }

        @Override // top.niunaijun.blackbox.utils.Reflector
        public QuietReflector set(Object value) {
            if (skip()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.set(value);
            } catch (Throwable e) {
                this.mIgnored = e;
            }
            return this;
        }

        @Override // top.niunaijun.blackbox.utils.Reflector
        public QuietReflector set(Object caller, Object value) {
            if (skip()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.set(caller, value);
            } catch (Throwable e) {
                this.mIgnored = e;
            }
            return this;
        }

        @Override // top.niunaijun.blackbox.utils.Reflector
        public QuietReflector method(String name, Class<?>... parameterTypes) {
            if (skipAlways()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.method(name, parameterTypes);
            } catch (Throwable e) {
                this.mIgnored = e;
            }
            return this;
        }

        @Override // top.niunaijun.blackbox.utils.Reflector
        public <R> R call(Object... objArr) {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.call(objArr);
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }

        @Override // top.niunaijun.blackbox.utils.Reflector
        public <R> R callByCaller(Object obj, Object... objArr) {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.callByCaller(obj, objArr);
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }
    }

    public static Method findMethodByFirstName(Class<?> clazz, String methodName) {
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            if (methodName.equals(declaredMethod.getName())) {
                return declaredMethod;
            }
        }
        return null;
    }
}
