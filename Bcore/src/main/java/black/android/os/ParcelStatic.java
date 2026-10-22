package black.android.os;

import java.lang.reflect.Field;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldCheckNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldNotProcess;
import top.niunaijun.blackreflection.annotation.BFieldSetNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/os/ParcelStatic.class */
@BClassNameNotProcess("android.os.Parcel")
public interface ParcelStatic {
    @BFieldSetNotProcess
    void _set_VAL_PARCELABLE(Object obj);

    @BFieldCheckNotProcess
    Field _check_VAL_PARCELABLE();

    @BFieldNotProcess
    Integer VAL_PARCELABLE();

    @BFieldSetNotProcess
    void _set_VAL_PARCELABLEARRAY(Object obj);

    @BFieldCheckNotProcess
    Field _check_VAL_PARCELABLEARRAY();

    @BFieldNotProcess
    Integer VAL_PARCELABLEARRAY();
}
