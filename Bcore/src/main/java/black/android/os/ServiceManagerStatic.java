package black.android.os;

import android.os.IBinder;
import android.os.IInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/os/ServiceManagerStatic.class */
@BClassNameNotProcess("android.os.ServiceManager")
public interface ServiceManagerStatic {
    @BMethodCheckNotProcess
    Method _check_addService(String str, IBinder iBinder);

    Void addService(String str, IBinder iBinder);

    @BMethodCheckNotProcess
    Method _check_checkService();

    IBinder checkService();

    @BMethodCheckNotProcess
    Method _check_getIServiceManager();

    IInterface getIServiceManager();

    @BMethodCheckNotProcess
    Method _check_getService(String str);

    IBinder getService(String str);

    @BMethodCheckNotProcess
    Method _check_listServices();

    String[] listServices();

    @BFieldSetNotProcess
    void _set_sCache(Object obj);

    @BFieldCheckNotProcess
    Field _check_sCache();

    @BFieldNotProcess
    Map<String, IBinder> sCache();

    @BFieldSetNotProcess
    void _set_sServiceManager(Object obj);

    @BFieldCheckNotProcess
    Field _check_sServiceManager();

    @BFieldNotProcess
    IInterface sServiceManager();
}
