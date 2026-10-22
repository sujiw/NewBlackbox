package black.android.app.servertransaction;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/servertransaction/BRClientTransaction.class */
public class BRClientTransaction {
    public static ClientTransactionStatic getWithException() {
        return (ClientTransactionStatic) BlackReflection.create(ClientTransactionStatic.class, (Object) null, true);
    }

    public static ClientTransactionStatic get() {
        return (ClientTransactionStatic) BlackReflection.create(ClientTransactionStatic.class, (Object) null, false);
    }

    public static ClientTransactionContext getWithException(Object caller) {
        return (ClientTransactionContext) BlackReflection.create(ClientTransactionContext.class, caller, true);
    }

    public static ClientTransactionContext get(Object caller) {
        return (ClientTransactionContext) BlackReflection.create(ClientTransactionContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ClientTransactionContext.class);
    }
}
