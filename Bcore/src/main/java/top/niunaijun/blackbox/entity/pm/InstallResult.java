package top.niunaijun.blackbox.entity.pm;

import android.os.Parcel;
import android.os.Parcelable;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/pm/InstallResult.class */
public class InstallResult implements Parcelable {
    public static final String TAG = "InstallResult";
    public boolean success;
    public String packageName;
    public String msg;
    public static final Parcelable.Creator<InstallResult> CREATOR = new Parcelable.Creator<InstallResult>() { // from class: top.niunaijun.blackbox.entity.pm.InstallResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstallResult createFromParcel(Parcel source) {
            return new InstallResult(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstallResult[] newArray(int size) {
            return new InstallResult[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeString(this.packageName);
        dest.writeString(this.msg);
    }

    public InstallResult() {
        this.success = true;
    }

    protected InstallResult(Parcel in) {
        this.success = true;
        this.success = in.readByte() != 0;
        this.packageName = in.readString();
        this.msg = in.readString();
    }

    public InstallResult installError(String packageName, String msg) {
        this.msg = msg;
        this.success = false;
        this.packageName = packageName;
        Slog.d(TAG, msg);
        return this;
    }

    public InstallResult installError(String msg) {
        this.msg = msg;
        this.success = false;
        Slog.d(TAG, msg);
        return this;
    }
}
