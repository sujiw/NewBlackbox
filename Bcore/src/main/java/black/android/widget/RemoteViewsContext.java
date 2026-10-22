package black.android.widget;

import android.content.pm.ApplicationInfo;
import java.lang.reflect.Field;
import java.util.ArrayList;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/widget/RemoteViewsContext.class */
@BClassNameNotProcess("android.widget.RemoteViews")
public interface RemoteViewsContext {
    @BFieldSetNotProcess
    void _set_mActions(Object obj);

    @BFieldCheckNotProcess
    Field _check_mActions();

    @BFieldNotProcess
    ArrayList<Object> mActions();

    @BFieldSetNotProcess
    void _set_mApplication(Object obj);

    @BFieldCheckNotProcess
    Field _check_mApplication();

    @BFieldNotProcess
    ApplicationInfo mApplication();

    @BFieldSetNotProcess
    void _set_mPackage(Object obj);

    @BFieldCheckNotProcess
    Field _check_mPackage();

    @BFieldNotProcess
    String mPackage();
}
