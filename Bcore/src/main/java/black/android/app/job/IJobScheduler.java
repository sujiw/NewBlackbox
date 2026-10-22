package black.android.app.job;

import android.os.IBinder;
import android.os.IInterface;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BStaticMethod;

/* JADX INFO: loaded from: classes.jar:black/android/app/job/IJobScheduler.class */
@BClassName("android.app.job.IJobScheduler")
public interface IJobScheduler {

    /* JADX INFO: loaded from: classes.jar:black/android/app/job/IJobScheduler$Stub.class */
    @BClassName("android.app.job.IJobScheduler$Stub")
    public interface Stub {
        @BStaticMethod
        IInterface asInterface(IBinder iBinder);
    }
}
