package top.niunaijun.blackbox.core.system.pm;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackageUserState.class */
public class BPackageUserState implements Parcelable {
    public boolean installed;
    public boolean stopped;
    public boolean hidden;
    public static final Parcelable.Creator<BPackageUserState> CREATOR = new Parcelable.Creator<BPackageUserState>() { // from class: top.niunaijun.blackbox.core.system.pm.BPackageUserState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BPackageUserState createFromParcel(Parcel source) {
            return new BPackageUserState(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BPackageUserState[] newArray(int size) {
            return new BPackageUserState[size];
        }
    };

    public BPackageUserState() {
        this.installed = false;
        this.stopped = true;
        this.hidden = false;
    }

    public static BPackageUserState create() {
        BPackageUserState state = new BPackageUserState();
        state.installed = true;
        return state;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.installed ? (byte) 1 : (byte) 0);
        dest.writeByte(this.stopped ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hidden ? (byte) 1 : (byte) 0);
    }

    protected BPackageUserState(Parcel in) {
        this.installed = in.readByte() != 0;
        this.stopped = in.readByte() != 0;
        this.hidden = in.readByte() != 0;
    }

    public BPackageUserState(BPackageUserState state) {
        this.installed = state.installed;
        this.stopped = state.stopped;
        this.hidden = state.hidden;
    }
}
