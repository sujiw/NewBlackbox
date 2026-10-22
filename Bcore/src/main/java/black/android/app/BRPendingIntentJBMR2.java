package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRPendingIntentJBMR2.class */
public class BRPendingIntentJBMR2 {
    public static PendingIntentJBMR2Static getWithException() {
        return (PendingIntentJBMR2Static) BlackReflection.create(PendingIntentJBMR2Static.class, (Object) null, true);
    }

    public static PendingIntentJBMR2Static get() {
        return (PendingIntentJBMR2Static) BlackReflection.create(PendingIntentJBMR2Static.class, (Object) null, false);
    }

    public static PendingIntentJBMR2Context getWithException(Object caller) {
        return (PendingIntentJBMR2Context) BlackReflection.create(PendingIntentJBMR2Context.class, caller, true);
    }

    public static PendingIntentJBMR2Context get(Object caller) {
        return (PendingIntentJBMR2Context) BlackReflection.create(PendingIntentJBMR2Context.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(PendingIntentJBMR2Context.class);
    }
}
