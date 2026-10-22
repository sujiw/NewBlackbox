package black.android.media;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/media/BRAudioManager.class */
public class BRAudioManager {
    public static AudioManagerStatic getWithException() {
        return (AudioManagerStatic) BlackReflection.create(AudioManagerStatic.class, (Object) null, true);
    }

    public static AudioManagerStatic get() {
        return (AudioManagerStatic) BlackReflection.create(AudioManagerStatic.class, (Object) null, false);
    }

    public static AudioManagerContext getWithException(Object caller) {
        return (AudioManagerContext) BlackReflection.create(AudioManagerContext.class, caller, true);
    }

    public static AudioManagerContext get(Object caller) {
        return (AudioManagerContext) BlackReflection.create(AudioManagerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(AudioManagerContext.class);
    }
}
