package top.niunaijun.blackbox.entity.am;

import android.app.ActivityManager;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/am/RunningServiceInfo.class */
public class RunningServiceInfo implements Parcelable {
    public List<ActivityManager.RunningServiceInfo> mRunningServiceInfoList;
    public static final Parcelable.Creator<RunningServiceInfo> CREATOR = new Parcelable.Creator<RunningServiceInfo>() { // from class: top.niunaijun.blackbox.entity.am.RunningServiceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RunningServiceInfo createFromParcel(Parcel source) {
            return new RunningServiceInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RunningServiceInfo[] newArray(int size) {
            return new RunningServiceInfo[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mRunningServiceInfoList);
    }

    public void readFromParcel(Parcel source) {
        this.mRunningServiceInfoList = source.createTypedArrayList(ActivityManager.RunningServiceInfo.CREATOR);
    }

    public RunningServiceInfo() {
        this.mRunningServiceInfoList = new ArrayList();
    }

    protected RunningServiceInfo(Parcel in) {
        this.mRunningServiceInfoList = in.createTypedArrayList(ActivityManager.RunningServiceInfo.CREATOR);
    }
}
