package black.android.app.servertransaction;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/servertransaction/BRTopResumedActivityChangeItem.class */
public class BRTopResumedActivityChangeItem {
    public static TopResumedActivityChangeItemStatic getWithException() {
        return (TopResumedActivityChangeItemStatic) BlackReflection.create(TopResumedActivityChangeItemStatic.class, (Object) null, true);
    }

    public static TopResumedActivityChangeItemStatic get() {
        return (TopResumedActivityChangeItemStatic) BlackReflection.create(TopResumedActivityChangeItemStatic.class, (Object) null, false);
    }

    public static TopResumedActivityChangeItemContext getWithException(Object caller) {
        return (TopResumedActivityChangeItemContext) BlackReflection.create(TopResumedActivityChangeItemContext.class, caller, true);
    }

    public static TopResumedActivityChangeItemContext get(Object caller) {
        return (TopResumedActivityChangeItemContext) BlackReflection.create(TopResumedActivityChangeItemContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(TopResumedActivityChangeItemContext.class);
    }
}
