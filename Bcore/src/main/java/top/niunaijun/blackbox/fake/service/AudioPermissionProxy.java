package top.niunaijun.blackbox.fake.service;

import android.os.IBinder;
import black.android.os.BRServiceManager;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy.class */
public class AudioPermissionProxy extends BinderInvocationStub {
    public static final String TAG = "AudioPermissionProxy";

    public AudioPermissionProxy() {
        super(BRServiceManager.get().getService("audio"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        IBinder binder = BRServiceManager.get().getService("audio");
        if (binder == null) {
            Slog.e(TAG, "Failed to get audio service binder");
            return null;
        }
        try {
            Class<?> stubClass = Class.forName("android.media.IAudioService$Stub");
            Method asInterfaceMethod = stubClass.getMethod("asInterface", IBinder.class);
            Object iface = asInterfaceMethod.invoke(null, binder);
            if (iface != null) {
                Slog.d(TAG, "Successfully obtained IAudioService interface");
                return iface;
            }
            Slog.e(TAG, "Reflection succeeded but returned null interface");
            return null;
        } catch (Exception e) {
            Slog.e(TAG, "Failed to get IAudioService interface", e);
            return null;
        }
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("audio");
    }

    @Override // top.niunaijun.blackbox.fake.hook.IInjectHook
    public boolean isBadEnv() {
        return false;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$IsMicrophoneMuted.class */
    @ProxyMethod("isMicrophoneMuted")
    public static class IsMicrophoneMuted extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: isMicrophoneMuted returning false");
            return false;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$SetMicrophoneMute.class */
    @ProxyMethod("setMicrophoneMute")
    public static class SetMicrophoneMute extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: setMicrophoneMute called, forcing unmute");
            if (args != null && args.length > 0) {
                args[0] = false;
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$IsMicrophoneMutedForUser.class */
    @ProxyMethod("isMicrophoneMutedForUser")
    public static class IsMicrophoneMutedForUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: isMicrophoneMutedForUser returning false");
            return false;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$SetMicrophoneMuteForUser.class */
    @ProxyMethod("setMicrophoneMuteForUser")
    public static class SetMicrophoneMuteForUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: setMicrophoneMuteForUser called, forcing unmute");
            if (args != null && args.length > 1) {
                args[1] = false;
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$StartRecording.class */
    @ProxyMethod("startRecording")
    public static class StartRecording extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: startRecording called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$StopRecording.class */
    @ProxyMethod("stopRecording")
    public static class StopRecording extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: stopRecording called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$IsRecordingActive.class */
    @ProxyMethod("isRecordingActive")
    public static class IsRecordingActive extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: isRecordingActive called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$GetRecordingState.class */
    @ProxyMethod("getRecordingState")
    public static class GetRecordingState extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: getRecordingState called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$GetRecordingStateForUser.class */
    @ProxyMethod("getRecordingStateForUser")
    public static class GetRecordingStateForUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: getRecordingStateForUser called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$RequestAudioFocus.class */
    @ProxyMethod("requestAudioFocus")
    public static class RequestAudioFocus extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: requestAudioFocus called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$RegisterAudioFocusClient.class */
    @ProxyMethod("registerAudioFocusClient")
    public static class RegisterAudioFocusClient extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: registerAudioFocusClient called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$UnregisterAudioFocusClient.class */
    @ProxyMethod("unregisterAudioFocusClient")
    public static class UnregisterAudioFocusClient extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: unregisterAudioFocusClient called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$AbandonAudioFocus.class */
    @ProxyMethod("abandonAudioFocus")
    public static class AbandonAudioFocus extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: abandonAudioFocus called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$SetMode.class */
    @ProxyMethod("setMode")
    public static class SetMode extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: setMode called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$GetMode.class */
    @ProxyMethod("getMode")
    public static class GetMode extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: getMode called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$SetStreamVolume.class */
    @ProxyMethod("setStreamVolume")
    public static class SetStreamVolume extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: setStreamVolume called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$GetStreamVolume.class */
    @ProxyMethod("getStreamVolume")
    public static class GetStreamVolume extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: getStreamVolume called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$GetStreamMaxVolume.class */
    @ProxyMethod("getStreamMaxVolume")
    public static class GetStreamMaxVolume extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: getStreamMaxVolume called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$SetStreamMute.class */
    @ProxyMethod("setStreamMute")
    public static class SetStreamMute extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: setStreamMute called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$IsStreamMute.class */
    @ProxyMethod("isStreamMute")
    public static class IsStreamMute extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: isStreamMute called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$SetSpeakerphoneOn.class */
    @ProxyMethod("setSpeakerphoneOn")
    public static class SetSpeakerphoneOn extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: setSpeakerphoneOn called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$IsSpeakerphoneOn.class */
    @ProxyMethod("isSpeakerphoneOn")
    public static class IsSpeakerphoneOn extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: isSpeakerphoneOn called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$SetBluetoothScoOn.class */
    @ProxyMethod("setBluetoothScoOn")
    public static class SetBluetoothScoOn extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: setBluetoothScoOn called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/AudioPermissionProxy$IsBluetoothScoOn.class */
    @ProxyMethod("isBluetoothScoOn")
    public static class IsBluetoothScoOn extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(AudioPermissionProxy.TAG, "AudioPermission: isBluetoothScoOn called, allowing");
            return method.invoke(who, args);
        }
    }
}
