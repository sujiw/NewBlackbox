package top.niunaijun.blackbox.entity;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/AppConfig.class */
public class AppConfig implements Parcelable {
    public static final String KEY = "BlackBox_client_config";
    public String packageName;
    public String processName;
    public int bpid;
    public int buid;
    public int uid;
    public int userId;
    public int callingBUid;
    public IBinder token;
    /** Raw json of the per-app proxy, null when the app uses a direct connection. */
    public String proxyConfig;
    /** Raw json of the per-app advertising id, null when not overridden. */
    public String gaidConfig;
    /** Raw json of the per-app device fingerprint, null when not overridden. */
    public String deviceConfig;
    public static final Parcelable.Creator<AppConfig> CREATOR = new Parcelable.Creator<AppConfig>() { // from class: top.niunaijun.blackbox.entity.AppConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppConfig createFromParcel(Parcel source) {
            return new AppConfig(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppConfig[] newArray(int size) {
            return new AppConfig[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.packageName);
        dest.writeString(this.processName);
        dest.writeInt(this.bpid);
        dest.writeInt(this.buid);
        dest.writeInt(this.uid);
        dest.writeInt(this.userId);
        dest.writeInt(this.callingBUid);
        dest.writeStrongBinder(this.token);
        dest.writeString(this.proxyConfig);
        dest.writeString(this.gaidConfig);
        dest.writeString(this.deviceConfig);
    }

    public AppConfig() {
    }

    protected AppConfig(Parcel in) {
        this.packageName = in.readString();
        this.processName = in.readString();
        this.bpid = in.readInt();
        this.buid = in.readInt();
        this.uid = in.readInt();
        this.userId = in.readInt();
        this.callingBUid = in.readInt();
        this.token = in.readStrongBinder();
        this.proxyConfig = in.readString();
        this.gaidConfig = in.readString();
        this.deviceConfig = in.readString();
    }
}
