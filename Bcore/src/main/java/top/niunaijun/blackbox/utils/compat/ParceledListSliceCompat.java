package top.niunaijun.blackbox.utils.compat;

import black.android.content.pm.BRParceledListSlice;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/compat/ParceledListSliceCompat.class */
public class ParceledListSliceCompat {
    public static boolean isReturnParceledListSlice(Method method) {
        return method != null && method.getReturnType() == BRParceledListSlice.getRealClass();
    }

    public static boolean isParceledListSlice(Object obj) {
        return obj != null && obj.getClass() == BRParceledListSlice.getRealClass();
    }

    public static Object create(List<?> list) {
        Object slice = BRParceledListSlice.get()._new(list);
        if (slice != null) {
            return slice;
        }
        Object slice2 = BRParceledListSlice.get()._new();
        for (Object item : list) {
            BRParceledListSlice.get(slice2).append(item);
        }
        BRParceledListSlice.get(slice2).setLastSlice(true);
        return slice2;
    }
}
