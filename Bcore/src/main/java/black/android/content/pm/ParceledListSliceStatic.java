package black.android.content.pm;

import android.os.Parcelable;
import java.lang.reflect.Field;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BConstructorNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/ParceledListSliceStatic.class */
@BClassNameNotProcess("android.content.pm.ParceledListSlice")
public interface ParceledListSliceStatic {
    @BFieldSetNotProcess
    void _set_CREATOR(Object obj);

    @BFieldCheckNotProcess
    Field _check_CREATOR();

    @BFieldNotProcess
    Parcelable.Creator CREATOR();

    @BConstructorNotProcess
    Object _new();

    @BConstructorNotProcess
    Object _new(List<?> list);
}
