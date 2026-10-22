package top.niunaijun.blackbox.fake.service;

import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderClassProxy.class */
public class MediaRecorderClassProxy extends ClassInvocationStub {
    public static final String TAG = "MediaRecorderClassProxy";

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        return null;
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderClassProxy$Constructor.class */
    @ProxyMethod("<init>")
    public static class Constructor extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderClassProxy.TAG, "MediaRecorder: Constructor called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderClassProxy$SetAudioSource.class */
    @ProxyMethod("setAudioSource")
    public static class SetAudioSource extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderClassProxy.TAG, "MediaRecorder: setAudioSource called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderClassProxy$SetOutputFormat.class */
    @ProxyMethod("setOutputFormat")
    public static class SetOutputFormat extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderClassProxy.TAG, "MediaRecorder: setOutputFormat called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderClassProxy$SetAudioEncoder.class */
    @ProxyMethod("setAudioEncoder")
    public static class SetAudioEncoder extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderClassProxy.TAG, "MediaRecorder: setAudioEncoder called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderClassProxy$SetOutputFile.class */
    @ProxyMethod("setOutputFile")
    public static class SetOutputFile extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderClassProxy.TAG, "MediaRecorder: setOutputFile called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderClassProxy$Prepare.class */
    @ProxyMethod("prepare")
    public static class Prepare extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderClassProxy.TAG, "MediaRecorder: prepare called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderClassProxy$Start.class */
    @ProxyMethod("start")
    public static class Start extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderClassProxy.TAG, "MediaRecorder: start called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderClassProxy$Stop.class */
    @ProxyMethod("stop")
    public static class Stop extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderClassProxy.TAG, "MediaRecorder: stop called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderClassProxy$Release.class */
    @ProxyMethod("release")
    public static class Release extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderClassProxy.TAG, "MediaRecorder: release called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderClassProxy$Reset.class */
    @ProxyMethod("reset")
    public static class Reset extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderClassProxy.TAG, "MediaRecorder: reset called, allowing");
            return method.invoke(who, args);
        }
    }
}
