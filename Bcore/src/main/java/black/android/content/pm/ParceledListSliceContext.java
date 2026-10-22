package black.android.content.pm;

import android.os.Parcelable;
import java.lang.reflect.Method;
import java.util.List;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/content/pm/ParceledListSliceContext.class */
@BClassNameNotProcess("android.content.pm.ParceledListSlice")
public interface ParceledListSliceContext {
    @BMethodCheckNotProcess
    Method _check_append(Object obj);

    Boolean append(Object obj);

    @BMethodCheckNotProcess
    Method _check_getList();

    List<?> getList();

    @BMethodCheckNotProcess
    Method _check_isLastSlice();

    Boolean isLastSlice();

    @BMethodCheckNotProcess
    Method _check_populateList();

    Parcelable populateList();

    @BMethodCheckNotProcess
    Method _check_setLastSlice(boolean z);

    Void setLastSlice(boolean z);
}
