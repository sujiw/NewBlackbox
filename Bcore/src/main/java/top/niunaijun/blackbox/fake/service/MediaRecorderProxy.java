package top.niunaijun.blackbox.fake.service;

import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.ClassInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy.class */
public class MediaRecorderProxy extends ClassInvocationStub {
    public static final String TAG = "MediaRecorderProxy";

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

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$Constructor.class */
    @ProxyMethod("<init>")
    public static class Constructor extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: Constructor called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$SetAudioSource.class */
    @ProxyMethod("setAudioSource")
    public static class SetAudioSource extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: setAudioSource called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$SetOutputFormat.class */
    @ProxyMethod("setOutputFormat")
    public static class SetOutputFormat extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: setOutputFormat called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$SetAudioEncoder.class */
    @ProxyMethod("setAudioEncoder")
    public static class SetAudioEncoder extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: setAudioEncoder called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$SetOutputFile.class */
    @ProxyMethod("setOutputFile")
    public static class SetOutputFile extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: setOutputFile called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$Prepare.class */
    @ProxyMethod("prepare")
    public static class Prepare extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: prepare called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$Start.class */
    @ProxyMethod("start")
    public static class Start extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: start called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$Stop.class */
    @ProxyMethod("stop")
    public static class Stop extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: stop called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$Release.class */
    @ProxyMethod("release")
    public static class Release extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: release called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$Reset.class */
    @ProxyMethod("reset")
    public static class Reset extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: reset called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$SetAudioSamplingRate.class */
    @ProxyMethod("setAudioSamplingRate")
    public static class SetAudioSamplingRate extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: setAudioSamplingRate called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$SetAudioChannels.class */
    @ProxyMethod("setAudioChannels")
    public static class SetAudioChannels extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: setAudioChannels called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$SetAudioEncodingBitRate.class */
    @ProxyMethod("setAudioEncodingBitRate")
    public static class SetAudioEncodingBitRate extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: setAudioEncodingBitRate called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$SetMaxDuration.class */
    @ProxyMethod("setMaxDuration")
    public static class SetMaxDuration extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: setMaxDuration called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$SetMaxFileSize.class */
    @ProxyMethod("setMaxFileSize")
    public static class SetMaxFileSize extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: setMaxFileSize called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$SetOnErrorListener.class */
    @ProxyMethod("setOnErrorListener")
    public static class SetOnErrorListener extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: setOnErrorListener called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/MediaRecorderProxy$SetOnInfoListener.class */
    @ProxyMethod("setOnInfoListener")
    public static class SetOnInfoListener extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(MediaRecorderProxy.TAG, "MediaRecorder: setOnInfoListener called, allowing");
            return method.invoke(who, args);
        }
    }
}
