package black.android.app;

import top.niunaijun.blackreflection.BlackReflection;
import top.niunaijun.blackreflection.utils.ClassUtil;

/* JADX INFO: loaded from: classes.jar:black/android/app/BRClientTransactionHandler.class */
public class BRClientTransactionHandler {
    public static ClientTransactionHandlerStatic getWithException() {
        return (ClientTransactionHandlerStatic) BlackReflection.create(ClientTransactionHandlerStatic.class, (Object) null, true);
    }

    public static ClientTransactionHandlerStatic get() {
        return (ClientTransactionHandlerStatic) BlackReflection.create(ClientTransactionHandlerStatic.class, (Object) null, false);
    }

    public static ClientTransactionHandlerContext getWithException(Object caller) {
        return (ClientTransactionHandlerContext) BlackReflection.create(ClientTransactionHandlerContext.class, caller, true);
    }

    public static ClientTransactionHandlerContext get(Object caller) {
        return (ClientTransactionHandlerContext) BlackReflection.create(ClientTransactionHandlerContext.class, caller, false);
    }

    public static Class getRealClass() {
        return ClassUtil.classReady(ClientTransactionHandlerContext.class);
    }
}
