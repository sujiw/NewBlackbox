package top.niunaijun.blackbox.entity;

import android.app.job.JobInfo;
import android.app.job.JobService;
import android.content.pm.ServiceInfo;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/JobRecord.class */
public class JobRecord implements Parcelable {
    public JobInfo mJobInfo;
    public ServiceInfo mServiceInfo;
    public JobService mJobService;
    public static final Parcelable.Creator<JobRecord> CREATOR = new Parcelable.Creator<JobRecord>() { // from class: top.niunaijun.blackbox.entity.JobRecord.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JobRecord createFromParcel(Parcel source) {
            return new JobRecord(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JobRecord[] newArray(int size) {
            return new JobRecord[size];
        }
    };

    public JobRecord() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mJobInfo, flags);
        dest.writeParcelable(this.mServiceInfo, flags);
    }

    protected JobRecord(Parcel in) {
        this.mJobInfo = (JobInfo) in.readParcelable(JobInfo.class.getClassLoader());
        this.mServiceInfo = (ServiceInfo) in.readParcelable(ServiceInfo.class.getClassLoader());
    }
}
