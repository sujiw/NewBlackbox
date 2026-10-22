package top.niunaijun.blackbox.entity.am;

import android.app.ActivityManager;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/am/RunningAppProcessInfo.class */
public class RunningAppProcessInfo implements Parcelable {
    public List<ActivityManager.RunningAppProcessInfo> mAppProcessInfoList;
    public static final Parcelable.Creator<RunningAppProcessInfo> CREATOR = new Parcelable.Creator<RunningAppProcessInfo>() { // from class: top.niunaijun.blackbox.entity.am.RunningAppProcessInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RunningAppProcessInfo createFromParcel(Parcel source) {
            return new RunningAppProcessInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RunningAppProcessInfo[] newArray(int size) {
            return new RunningAppProcessInfo[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mAppProcessInfoList);
    }

    public void readFromParcel(Parcel source) {
        this.mAppProcessInfoList = source.createTypedArrayList(ActivityManager.RunningAppProcessInfo.CREATOR);
    }

    public RunningAppProcessInfo() {
        this.mAppProcessInfoList = new ArrayList();
    }

    protected RunningAppProcessInfo(Parcel in) {
        this.mAppProcessInfoList = in.createTypedArrayList(ActivityManager.RunningAppProcessInfo.CREATOR);
    }
}
