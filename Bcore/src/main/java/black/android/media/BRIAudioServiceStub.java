package black.android.media;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/media/BRIAudioServiceStub.class */
public class BRIAudioServiceStub {
    public static IAudioServiceStubStatic getWithException() {
        return (IAudioServiceStubStatic) BlackReflection.create(IAudioServiceStubStatic.class, (Object) null, true);
    }

    public static IAudioServiceStubStatic get() {
        return (IAudioServiceStubStatic) BlackReflection.create(IAudioServiceStubStatic.class, (Object) null, false);
    }

    public static IAudioServiceStubContext getWithException(Object caller) {
        return (IAudioServiceStubContext) BlackReflection.create(IAudioServiceStubContext.class, caller, true);
    }

    public static IAudioServiceStubContext get(Object caller) {
        return (IAudioServiceStubContext) BlackReflection.create(IAudioServiceStubContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(IAudioServiceStubContext.class);
    }
}
