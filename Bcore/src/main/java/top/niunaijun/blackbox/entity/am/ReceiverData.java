package top.niunaijun.blackbox.entity.am;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/am/ReceiverData.class */
public class ReceiverData implements Parcelable {
    public Intent intent;
    public ActivityInfo activityInfo;
    public PendingResultData data;
    public static final Parcelable.Creator<ReceiverData> CREATOR = new Parcelable.Creator<ReceiverData>() { // from class: top.niunaijun.blackbox.entity.am.ReceiverData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ReceiverData createFromParcel(Parcel source) {
            return new ReceiverData(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ReceiverData[] newArray(int size) {
            return new ReceiverData[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.intent, flags);
        dest.writeParcelable(this.activityInfo, flags);
        dest.writeParcelable(this.data, flags);
    }

    public void readFromParcel(Parcel source) {
        this.intent = (Intent) source.readParcelable(Intent.class.getClassLoader());
        this.activityInfo = (ActivityInfo) source.readParcelable(ActivityInfo.class.getClassLoader());
        this.data = (PendingResultData) source.readParcelable(PendingResultData.class.getClassLoader());
    }

    public ReceiverData() {
    }

    protected ReceiverData(Parcel in) {
        this.intent = (Intent) in.readParcelable(Intent.class.getClassLoader());
        this.activityInfo = (ActivityInfo) in.readParcelable(ActivityInfo.class.getClassLoader());
        this.data = (PendingResultData) in.readParcelable(PendingResultData.class.getClassLoader());
    }
}
