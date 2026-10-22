package top.niunaijun.blackbox.entity.pm;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
import top.niunaijun.blackbox.BlackBoxCore;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/pm/InstalledPackage.class */
public class InstalledPackage implements Parcelable {
    public int userId;
    public String packageName;
    public static final Parcelable.Creator<InstalledPackage> CREATOR = new Parcelable.Creator<InstalledPackage>() { // from class: top.niunaijun.blackbox.entity.pm.InstalledPackage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstalledPackage createFromParcel(Parcel source) {
            return new InstalledPackage(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstalledPackage[] newArray(int size) {
            return new InstalledPackage[size];
        }
    };

    public ApplicationInfo getApplication() {
        return BlackBoxCore.getBPackageManager().getApplicationInfo(this.packageName, 128, this.userId);
    }

    public PackageInfo getPackageInfo() {
        return BlackBoxCore.getBPackageManager().getPackageInfo(this.packageName, 128, this.userId);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeString(this.packageName);
    }

    public InstalledPackage() {
    }

    public InstalledPackage(String packageName) {
        this.packageName = packageName;
    }

    protected InstalledPackage(Parcel in) {
        this.userId = in.readInt();
        this.packageName = in.readString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InstalledPackage that = (InstalledPackage) o;
        return Objects.equals(this.packageName, that.packageName);
    }

    public int hashCode() {
        return Objects.hash(this.packageName);
    }
}
