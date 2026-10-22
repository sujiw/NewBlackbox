package black.android.content.pm;

import android.os.Parcelable;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BConstructor;
import top.niunaijun.blackreflection.annotation.BMethod;
import top.niunaijun.blackreflection.annotation.BStaticField;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/ParceledListSlice.class */
@BClassName("android.content.pm.ParceledListSlice")
public interface ParceledListSlice {
    @BConstructor
    Object _new();

    @BConstructor
    Object _new(List<?> list);

    @BStaticField
    Parcelable.Creator CREATOR();

    @BMethod
    Boolean append(Object obj);

    @BMethod
    List<?> getList();

    @BMethod
    Boolean isLastSlice();

    @BMethod
    Parcelable populateList();

    @BMethod
    void setLastSlice(boolean z);
}
