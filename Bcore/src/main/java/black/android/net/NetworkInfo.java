package black.android.net;

import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BConstructor;
import top.niunaijun.blackreflection.annotation.BField;

/* JADX INFO: loaded from: classes.jar:black/android/net/NetworkInfo.class */
@BClassName("android.net.NetworkInfo")
public interface NetworkInfo {
    @BConstructor
    NetworkInfo _new(int i, int i2, String str, String str2);

    @BConstructor
    NetworkInfo _new(int i);

    @BField
    android.net.NetworkInfo.DetailedState mDetailedState();

    @BField
    boolean mIsAvailable();

    @BField
    int mNetworkType();

    @BField
    android.net.NetworkInfo.State mState();

    @BField
    String mTypeName();
}
