package top.niunaijun.blackbox.fake.service;

import android.os.IBinder;
import android.os.IInterface;
import black.android.os.BRServiceManager;
import java.lang.reflect.Method;
import top.niunaijun.blackbox.fake.hook.BinderInvocationStub;
import top.niunaijun.blackbox.fake.hook.MethodHook;
import top.niunaijun.blackbox.fake.hook.ProxyMethod;
import top.niunaijun.blackbox.utils.Reflector;
import top.niunaijun.blackbox.utils.DeviceInfoManager;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAudioServiceProxy.class */
public class IAudioServiceProxy extends BinderInvocationStub {
    public static final String TAG = "AudioServiceProxy";

    public IAudioServiceProxy() {
        super(BRServiceManager.get().getService("audio"));
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected Object getWho() {
        Object iface;
        IBinder binder = BRServiceManager.get().getService("audio");
        if (binder == null) {
            Slog.e(TAG, "Failed to get AUDIO_SERVICE binder");
            return null;
        }
        try {
            try {
                iface = Reflector.on("android.media.IAudioService$Stub").call("asInterface", binder);
            } catch (Exception e1) {
                Slog.d(TAG, "Failed Android 16+ path, trying alternative: " + e1.getMessage());
                try {
                    iface = Reflector.on("android.media.IAudioService").call("asInterface", binder);
                } catch (Exception e2) {
                    Slog.d(TAG, "Failed alternative path: " + e2.getMessage());
                    try {
                        Class<?> stubClass = Class.forName("android.media.IAudioService$Stub");
                        Method asInterfaceMethod = stubClass.getMethod("asInterface", IBinder.class);
                        iface = asInterfaceMethod.invoke(null, binder);
                    } catch (Exception e3) {
                        Slog.e(TAG, "All reflection paths failed for IAudioService", e3);
                        return null;
                    }
                }
            }
            if (iface != null) {
                Slog.d(TAG, "Successfully obtained IAudioService interface");
                return (IInterface) iface;
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

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAudioServiceProxy$IsMicrophoneMuted.class */
    @ProxyMethod("isMicrophoneMuted")
    public static class IsMicrophoneMuted extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(IAudioServiceProxy.TAG, "AudioService: isMicrophoneMuted returning false");
            return false;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAudioServiceProxy$SetMicrophoneMute.class */
    @ProxyMethod("setMicrophoneMute")
    public static class SetMicrophoneMute extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(IAudioServiceProxy.TAG, "AudioService: setMicrophoneMute called, forcing unmute");
            if (args != null && args.length > 0) {
                args[0] = false;
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAudioServiceProxy$StartRecording.class */
    @ProxyMethod("startRecording")
    public static class StartRecording extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(IAudioServiceProxy.TAG, "AudioService: startRecording called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAudioServiceProxy$StopRecording.class */
    @ProxyMethod("stopRecording")
    public static class StopRecording extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(IAudioServiceProxy.TAG, "AudioService: stopRecording called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAudioServiceProxy$IsRecordingActive.class */
    @ProxyMethod("isRecordingActive")
    public static class IsRecordingActive extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(IAudioServiceProxy.TAG, "AudioService: isRecordingActive called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAudioServiceProxy$GetRecordingState.class */
    @ProxyMethod("getRecordingState")
    public static class GetRecordingState extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(IAudioServiceProxy.TAG, "AudioService: getRecordingState called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAudioServiceProxy$IsMicrophoneMutedForUser.class */
    @ProxyMethod("isMicrophoneMutedForUser")
    public static class IsMicrophoneMutedForUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(IAudioServiceProxy.TAG, "AudioService: isMicrophoneMutedForUser returning false");
            return false;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAudioServiceProxy$SetMicrophoneMuteForUser.class */
    @ProxyMethod("setMicrophoneMuteForUser")
    public static class SetMicrophoneMuteForUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(IAudioServiceProxy.TAG, "AudioService: setMicrophoneMuteForUser called, forcing unmute");
            if (args != null && args.length > 1) {
                args[1] = false;
            }
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAudioServiceProxy$GetRecordingStateForUser.class */
    @ProxyMethod("getRecordingStateForUser")
    public static class GetRecordingStateForUser extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(IAudioServiceProxy.TAG, "AudioService: getRecordingStateForUser called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAudioServiceProxy$RequestAudioFocus.class */
    @ProxyMethod("requestAudioFocus")
    public static class RequestAudioFocus extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(IAudioServiceProxy.TAG, "AudioService: requestAudioFocus called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAudioServiceProxy$RegisterAudioFocusClient.class */
    @ProxyMethod("registerAudioFocusClient")
    public static class RegisterAudioFocusClient extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(IAudioServiceProxy.TAG, "AudioService: registerAudioFocusClient called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAudioServiceProxy$UnregisterAudioFocusClient.class */
    @ProxyMethod("unregisterAudioFocusClient")
    public static class UnregisterAudioFocusClient extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(IAudioServiceProxy.TAG, "AudioService: unregisterAudioFocusClient called, allowing");
            return method.invoke(who, args);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/service/IAudioServiceProxy$AbandonAudioFocus.class */
    @ProxyMethod("abandonAudioFocus")
    public static class AbandonAudioFocus extends MethodHook {
        @Override // top.niunaijun.blackbox.fake.hook.MethodHook
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Slog.d(IAudioServiceProxy.TAG, "AudioService: abandonAudioFocus called, allowing");
            return method.invoke(who, args);
        }
    }

    /* 媒体音量：AudioManager.getStreamVolume 最终走 AudioService.getStreamVolume。 */
    @ProxyMethod("getStreamVolume")
    public static class GetStreamVolume extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            int volume = DeviceInfoManager.systemVolume();
            return volume > 0 ? volume : method.invoke(who, args);
        }
    }

    @ProxyMethod("isStreamMute")
    public static class IsStreamMute extends MethodHook {
        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (DeviceInfoManager.systemVolume() > 0) {
                return false;
            }
            return method.invoke(who, args);
        }
    }
}
