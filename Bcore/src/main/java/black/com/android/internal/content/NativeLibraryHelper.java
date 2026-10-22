package black.com.android.internal.content;

import java.io.File;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BField;
import top.niunaijun.blackreflection.annotation.BParamClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/com/android/internal/content/NativeLibraryHelper.class */
@BClassName("com.android.internal.content.NativeLibraryHelper")
public interface NativeLibraryHelper {

    /* JADX INFO: loaded from: classes.jar:black/com/android/internal/content/NativeLibraryHelper$Handle.class */
    @BClassName("com.android.internal.content.NativeLibraryHelper$Handle")
    public interface Handle {
        @BField
        boolean extractNativeLibs();

        @BStaticMethod
        Object create(File file);
    }

    @BStaticMethod
    Integer copyNativeBinaries(Handle handle, File file, String str);

    @BStaticMethod
    Integer findSupportedAbi(Handle handle, @BParamClassName("[Ljava.lang.String;") String[] strArr);
}
