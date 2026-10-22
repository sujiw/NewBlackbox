package black.android.content;

import android.os.Bundle;
import android.os.IBinder;
import top.niunaijun.blackreflection.annotation.BClassName;
import top.niunaijun.blackreflection.annotation.BConstructor;
import top.niunaijun.blackreflection.annotation.BField;
import top.niunaijun.blackreflection.annotation.BMethod;
import top.niunaijun.blackreflection.annotation.BParamClassName;

/* JADX INFO: loaded from: classes.jar:black/android/content/BroadcastReceiver.class */
@BClassName("android.content.BroadcastReceiver")
public interface BroadcastReceiver {

    /* JADX INFO: loaded from: classes.jar:black/android/content/BroadcastReceiver$PendingResult.class */
    @BClassName("android.content.BroadcastReceiver$PendingResult")
    public interface PendingResult {
        @BConstructor
        android.content.BroadcastReceiver.PendingResult _new(int i, String str, Bundle bundle, int i2, boolean z, boolean z2, IBinder iBinder, int i3);

        @BField
        boolean mAbortBroadcast();

        @BField
        boolean mFinished();

        @BField
        boolean mInitialStickyHint();

        @BField
        boolean mOrderedHint();

        @BField
        int mResultCode();

        @BField
        String mResultData();

        @BField
        Bundle mResultExtras();

        @BField
        int mSendingUser();

        @BField
        IBinder mToken();

        @BField
        int mType();
    }

    /* JADX INFO: loaded from: classes.jar:black/android/content/BroadcastReceiver$PendingResultM.class */
    @BClassName("android.content.BroadcastReceiver$PendingResult")
    public interface PendingResultM {
        @BConstructor
        android.content.BroadcastReceiver.PendingResult _new(int i, String str, Bundle bundle, int i2, boolean z, boolean z2, IBinder iBinder, int i3, int i4);

        @BField
        boolean mAbortBroadcast();

        @BField
        boolean mFinished();

        @BField
        int mFlags();

        @BField
        boolean mInitialStickyHint();

        @BField
        boolean mOrderedHint();

        @BField
        int mResultCode();

        @BField
        String mResultData();

        @BField
        Bundle mResultExtras();

        @BField
        int mSendingUser();

        @BField
        IBinder mToken();

        @BField
        int mType();
    }

    @BMethod
    android.content.BroadcastReceiver.PendingResult getPendingResult();

    @BMethod
    void setPendingResult(@BParamClassName("android.content.BroadcastReceiver$PendingResult") Object obj);
}
