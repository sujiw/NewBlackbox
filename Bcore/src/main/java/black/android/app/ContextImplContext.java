package black.android.app;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/app/ContextImplContext.class */
@BClassNameNotProcess("android.app.ContextImpl")
public interface ContextImplContext {
    @BMethodCheckNotProcess
    Method _check_getReceiverRestrictedContext();

    Context getReceiverRestrictedContext();

    @BMethodCheckNotProcess
    Method _check_setOuterContext(Context context);

    Void setOuterContext(Context context);

    @BMethodCheckNotProcess
    Method _check_getAttributionSource();

    Object getAttributionSource();

    @BMethodCheckNotProcess
    Method _check_getPackageManager();

    PackageManager getPackageManager();

    @BFieldSetNotProcess
    void _set_mBasePackageName(Object obj);

    @BFieldCheckNotProcess
    Field _check_mBasePackageName();

    @BFieldNotProcess
    String mBasePackageName();

    @BFieldSetNotProcess
    void _set_mContentResolver(Object obj);

    @BFieldCheckNotProcess
    Field _check_mContentResolver();

    @BFieldNotProcess
    ContentResolver mContentResolver();

    @BFieldSetNotProcess
    void _set_mPackageInfo(Object obj);

    @BFieldCheckNotProcess
    Field _check_mPackageInfo();

    @BFieldNotProcess
    Object mPackageInfo();

    @BFieldSetNotProcess
    void _set_mPackageManager(Object obj);

    @BFieldCheckNotProcess
    Field _check_mPackageManager();

    @BFieldNotProcess
    PackageManager mPackageManager();
}
