package top.niunaijun.blackbox.core.system.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.AtomicFile;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import top.niunaijun.blackbox.core.env.BEnvironment;
import top.niunaijun.blackbox.utils.FileUtils;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/SharedUserSetting.class */
public final class SharedUserSetting implements Parcelable {
    public static final String TAG = "SharedUserSetting";
    String name;
    int userId;
    int seInfoTargetSdkVersion;
    public static final Map<String, SharedUserSetting> sSharedUsers = new HashMap();
    public static final Parcelable.Creator<SharedUserSetting> CREATOR = new Parcelable.Creator<SharedUserSetting>() { // from class: top.niunaijun.blackbox.core.system.pm.SharedUserSetting.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SharedUserSetting createFromParcel(Parcel source) {
            SharedUserSetting setting = new SharedUserSetting();
            setting.readFromParcel(source);
            return setting;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SharedUserSetting[] newArray(int size) {
            return new SharedUserSetting[size];
        }
    };

    SharedUserSetting(String _name) {
        this.name = _name;
    }

    SharedUserSetting() {
    }

    public String toString() {
        return "SharedUserSetting{" + Integer.toHexString(System.identityHashCode(this)) + " " + this.name + "/" + this.userId + "}";
    }

    public static void saveSharedUsers() {
        Parcel parcel = Parcel.obtain();
        FileOutputStream fileOutputStream = null;
        AtomicFile atomicFile = new AtomicFile(BEnvironment.getSharedUserConf());
        try {
            parcel.writeMap(sSharedUsers);
            fileOutputStream = atomicFile.startWrite();
            FileUtils.writeParcelToOutput(parcel, fileOutputStream);
            atomicFile.finishWrite(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            atomicFile.failWrite(fileOutputStream);
        } finally {
            parcel.recycle();
        }
    }

    public static void loadSharedUsers() {
        Parcel parcel = Parcel.obtain();
        try {
            try {
                byte[] sharedUsersBytes = FileUtils.toByteArray(BEnvironment.getSharedUserConf());
                if (sharedUsersBytes == null || sharedUsersBytes.length == 0) {
                    parcel.recycle();
                    return;
                }
                if (sharedUsersBytes.length < 100) {
                    Slog.w(TAG, "Detected old format SharedUserSetting data, clearing for fresh start");
                    BEnvironment.getSharedUserConf().delete();
                    synchronized (sSharedUsers) {
                        sSharedUsers.clear();
                    }
                    parcel.recycle();
                    return;
                }
                parcel.unmarshall(sharedUsersBytes, 0, sharedUsersBytes.length);
                parcel.setDataPosition(0);
                HashMap hashMap = parcel.readHashMap(SharedUserSetting.class.getClassLoader());
                synchronized (sSharedUsers) {
                    sSharedUsers.clear();
                    sSharedUsers.putAll(hashMap);
                }
                parcel.recycle();
            } catch (Exception e) {
                try {
                    BEnvironment.getSharedUserConf().delete();
                } catch (Exception e2) {
                }
                synchronized (sSharedUsers) {
                    sSharedUsers.clear();
                    parcel.recycle();
                }
            }
        } catch (Throwable th) {
            parcel.recycle();
            throw th;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.userId);
        dest.writeInt(this.seInfoTargetSdkVersion);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.userId = source.readInt();
        this.seInfoTargetSdkVersion = source.readInt();
    }
}
