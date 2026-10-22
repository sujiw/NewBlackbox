package top.niunaijun.blackbox.entity.am;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import black.android.content.BRBroadcastReceiverPendingResult;
import black.android.content.BRBroadcastReceiverPendingResultM;
import black.android.content.BroadcastReceiverPendingResultContext;
import black.android.content.BroadcastReceiverPendingResultMContext;
import java.util.UUID;
import top.niunaijun.blackbox.utils.compat.BuildCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/am/PendingResultData.class */
public class PendingResultData implements Parcelable {
    public int mType;
    public boolean mOrderedHint;
    public boolean mInitialStickyHint;
    public IBinder mToken;
    public int mSendingUser;
    public int mFlags;
    public int mResultCode;
    public String mResultData;
    public Bundle mResultExtras;
    public boolean mAbortBroadcast;
    public boolean mFinished;
    public String mBToken;
    public static final Parcelable.Creator<PendingResultData> CREATOR = new Parcelable.Creator<PendingResultData>() { // from class: top.niunaijun.blackbox.entity.am.PendingResultData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PendingResultData createFromParcel(Parcel source) {
            return new PendingResultData(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PendingResultData[] newArray(int size) {
            return new PendingResultData[size];
        }
    };

    public PendingResultData(BroadcastReceiver.PendingResult pendingResult) {
        this.mBToken = UUID.randomUUID().toString();
        if (BuildCompat.isM()) {
            BroadcastReceiverPendingResultMContext resultMContext = BRBroadcastReceiverPendingResultM.get(pendingResult);
            this.mType = resultMContext.mType().intValue();
            this.mOrderedHint = resultMContext.mOrderedHint().booleanValue();
            this.mInitialStickyHint = resultMContext.mInitialStickyHint().booleanValue();
            this.mToken = resultMContext.mToken();
            this.mSendingUser = resultMContext.mSendingUser().intValue();
            this.mFlags = resultMContext.mFlags().intValue();
            this.mResultData = resultMContext.mResultData();
            this.mResultExtras = resultMContext.mResultExtras();
            this.mAbortBroadcast = resultMContext.mAbortBroadcast().booleanValue();
            this.mFinished = resultMContext.mFinished().booleanValue();
            return;
        }
        BroadcastReceiverPendingResultContext resultContext = BRBroadcastReceiverPendingResult.get(pendingResult);
        this.mType = resultContext.mType().intValue();
        this.mOrderedHint = resultContext.mOrderedHint().booleanValue();
        this.mInitialStickyHint = resultContext.mInitialStickyHint().booleanValue();
        this.mToken = resultContext.mToken();
        this.mSendingUser = resultContext.mSendingUser().intValue();
        this.mResultData = resultContext.mResultData();
        this.mResultExtras = resultContext.mResultExtras();
        this.mAbortBroadcast = resultContext.mAbortBroadcast().booleanValue();
        this.mFinished = resultContext.mFinished().booleanValue();
    }

    public BroadcastReceiver.PendingResult build() {
        if (BuildCompat.isM()) {
            return BRBroadcastReceiverPendingResultM.get()._new(this.mResultCode, this.mResultData, this.mResultExtras, this.mType, this.mOrderedHint, this.mInitialStickyHint, this.mToken, this.mSendingUser, this.mFlags);
        }
        return BRBroadcastReceiverPendingResult.get()._new(this.mResultCode, this.mResultData, this.mResultExtras, this.mType, this.mOrderedHint, this.mInitialStickyHint, this.mToken, this.mSendingUser);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mType);
        dest.writeByte(this.mOrderedHint ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mInitialStickyHint ? (byte) 1 : (byte) 0);
        dest.writeStrongBinder(this.mToken);
        dest.writeInt(this.mSendingUser);
        dest.writeInt(this.mFlags);
        dest.writeInt(this.mResultCode);
        dest.writeString(this.mResultData);
        dest.writeBundle(this.mResultExtras);
        dest.writeByte(this.mAbortBroadcast ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mFinished ? (byte) 1 : (byte) 0);
        dest.writeString(this.mBToken);
    }

    public void readFromParcel(Parcel source) {
        this.mType = source.readInt();
        this.mOrderedHint = source.readByte() != 0;
        this.mInitialStickyHint = source.readByte() != 0;
        this.mToken = source.readStrongBinder();
        this.mSendingUser = source.readInt();
        this.mFlags = source.readInt();
        this.mResultCode = source.readInt();
        this.mResultData = source.readString();
        this.mResultExtras = source.readBundle();
        this.mAbortBroadcast = source.readByte() != 0;
        this.mFinished = source.readByte() != 0;
        this.mBToken = source.readString();
    }

    protected PendingResultData(Parcel in) {
        this.mType = in.readInt();
        this.mOrderedHint = in.readByte() != 0;
        this.mInitialStickyHint = in.readByte() != 0;
        this.mToken = in.readStrongBinder();
        this.mSendingUser = in.readInt();
        this.mFlags = in.readInt();
        this.mResultCode = in.readInt();
        this.mResultData = in.readString();
        this.mResultExtras = in.readBundle();
        this.mAbortBroadcast = in.readByte() != 0;
        this.mFinished = in.readByte() != 0;
        this.mBToken = in.readString();
    }

    public String toString() {
        return "PendingResultData{mType=" + this.mType + ", mOrderedHint=" + this.mOrderedHint + ", mInitialStickyHint=" + this.mInitialStickyHint + ", mToken=" + this.mToken + ", mSendingUser=" + this.mSendingUser + ", mFlags=" + this.mFlags + ", mResultCode=" + this.mResultCode + ", mResultData='" + this.mResultData + "', mResultExtras=" + this.mResultExtras + ", mAbortBroadcast=" + this.mAbortBroadcast + ", mFinished=" + this.mFinished + '}';
    }
}
