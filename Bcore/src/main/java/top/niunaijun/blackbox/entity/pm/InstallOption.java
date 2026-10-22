package top.niunaijun.blackbox.entity.pm;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/pm/InstallOption.class */
public class InstallOption implements Parcelable {
    public static final int FLAG_SYSTEM = 1;
    public static final int FLAG_STORAGE = 2;
    public static final int FLAG_XPOSED = 4;
    public static final int FLAG_URI_FILE = 8;
    public int flags;
    public static final Parcelable.Creator<InstallOption> CREATOR = new Parcelable.Creator<InstallOption>() { // from class: top.niunaijun.blackbox.entity.pm.InstallOption.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstallOption createFromParcel(Parcel source) {
            return new InstallOption(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstallOption[] newArray(int size) {
            return new InstallOption[size];
        }
    };

    public static InstallOption installBySystem() {
        InstallOption installOption = new InstallOption();
        installOption.flags |= 1;
        return installOption;
    }

    public static InstallOption installByStorage() {
        InstallOption installOption = new InstallOption();
        installOption.flags |= 2;
        return installOption;
    }

    public InstallOption makeXposed() {
        this.flags |= 4;
        return this;
    }

    public InstallOption makeUriFile() {
        this.flags |= 8;
        return this;
    }

    public boolean isFlag(int flag) {
        return (this.flags & flag) != 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.flags);
    }

    public InstallOption() {
        this.flags = 0;
    }

    protected InstallOption(Parcel in) {
        this.flags = 0;
        this.flags = in.readInt();
    }
}
