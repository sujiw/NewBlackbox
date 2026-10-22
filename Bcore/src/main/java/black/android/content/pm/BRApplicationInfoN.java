package black.android.content.pm;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/BRApplicationInfoN.class */
public class BRApplicationInfoN {
    public static ApplicationInfoNStatic getWithException() {
        return (ApplicationInfoNStatic) BlackReflection.create(ApplicationInfoNStatic.class, (Object) null, true);
    }

    public static ApplicationInfoNStatic get() {
        return (ApplicationInfoNStatic) BlackReflection.create(ApplicationInfoNStatic.class, (Object) null, false);
    }

    public static ApplicationInfoNContext getWithException(Object caller) {
        return (ApplicationInfoNContext) BlackReflection.create(ApplicationInfoNContext.class, caller, true);
    }

    public static ApplicationInfoNContext get(Object caller) {
        return (ApplicationInfoNContext) BlackReflection.create(ApplicationInfoNContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ApplicationInfoNContext.class);
    }
}
