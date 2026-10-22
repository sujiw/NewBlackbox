package black.android.telephony;

import java.lang.reflect.Method;
import top.niunaijun.blackreflection.annotation.BClassNameNotProcess;
import top.niunaijun.blackreflection.annotation.BMethodCheckNotProcess;

/* JADX INFO: loaded from: classes.jar:black/android/telephony/SmsManagerContext.class */
@BClassNameNotProcess("android.telephony.SmsManager")
public interface SmsManagerContext {
    @BMethodCheckNotProcess
    Method _check_getAutoPersisting();

    Boolean getAutoPersisting();

    @BMethodCheckNotProcess
    Method _check_setAutoPersisting(boolean z);

    Void setAutoPersisting(boolean z);
}
