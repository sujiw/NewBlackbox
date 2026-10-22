package top.niunaijun.blackbox.entity;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/UnbindRecord.class */
public class UnbindRecord implements Parcelable {
    private int mBindCount;
    private int mStartId;
    private ComponentName mComponentName;
    public static final Parcelable.Creator<UnbindRecord> CREATOR = new Parcelable.Creator<UnbindRecord>() { // from class: top.niunaijun.blackbox.entity.UnbindRecord.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UnbindRecord createFromParcel(Parcel source) {
            return new UnbindRecord(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UnbindRecord[] newArray(int size) {
            return new UnbindRecord[size];
        }
    };

    public int getStartId() {
        return this.mStartId;
    }

    public void setStartId(int startId) {
        this.mStartId = startId;
    }

    public int getBindCount() {
        return this.mBindCount;
    }

    public void setBindCount(int bindCount) {
        this.mBindCount = bindCount;
    }

    public ComponentName getComponentName() {
        return this.mComponentName;
    }

    public void setComponentName(ComponentName componentName) {
        this.mComponentName = componentName;
    }

    public static Parcelable.Creator<UnbindRecord> getCREATOR() {
        return CREATOR;
    }

    public UnbindRecord() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mBindCount);
        dest.writeInt(this.mStartId);
        dest.writeParcelable(this.mComponentName, flags);
    }

    protected UnbindRecord(Parcel in) {
        this.mBindCount = in.readInt();
        this.mStartId = in.readInt();
        this.mComponentName = (ComponentName) in.readParcelable(ComponentName.class.getClassLoader());
    }
}
