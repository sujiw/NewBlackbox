package black.android.media;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/media/BRIAudioService.class */
public class BRIAudioService {
    public static IAudioServiceStatic getWithException() {
        return (IAudioServiceStatic) BlackReflection.create(IAudioServiceStatic.class, (Object) null, true);
    }

    public static IAudioServiceStatic get() {
        return (IAudioServiceStatic) BlackReflection.create(IAudioServiceStatic.class, (Object) null, false);
    }

    public static IAudioServiceContext getWithException(Object caller) {
        return (IAudioServiceContext) BlackReflection.create(IAudioServiceContext.class, caller, true);
    }

    public static IAudioServiceContext get(Object caller) {
        return (IAudioServiceContext) BlackReflection.create(IAudioServiceContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IAudioServiceContext.class);
    }
}
