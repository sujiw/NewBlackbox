package black.android.content.pm;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BConstructorNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/UserInfoStatic.class */
@BClassNameNotProcess("android.content.pm.UserInfo")
public interface UserInfoStatic {
    @BFieldSetNotProcess
    void _set_FLAG_PRIMARY(Object obj);

    @BFieldCheckNotProcess
    Field _check_FLAG_PRIMARY();

    @BFieldNotProcess
    Integer FLAG_PRIMARY();

    @BConstructorNotProcess
    Object _new(int i, String str, int i2);
}
