package black.android.content;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/content/IContentService.class */
@BClassName("android.content.IContentService")
public interface IContentService {

    /* JADX INFO: loaded from: classes.jar:black/android/content/IContentService$Stub.class */
    @BClassName("android.content.IContentService$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
