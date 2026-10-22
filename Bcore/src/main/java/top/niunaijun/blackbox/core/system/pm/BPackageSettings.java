package top.niunaijun.blackbox.core.system.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.AtomicFile;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import top.niunaijun.blackbox.core.env.BEnvironment;
import top.niunaijun.blackbox.entity.pm.InstallOption;
import top.niunaijun.blackbox.utils.CloseUtils;
import top.niunaijun.blackbox.utils.FileUtils;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/BPackageSettings.class */
public class BPackageSettings implements Parcelable {
    public BPackage pkg;
    public int appId;
    public InstallOption installOption;
    public Map<Integer, BPackageUserState> userState;
    static final BPackageUserState DEFAULT_USER_STATE = new BPackageUserState();
    public static final Parcelable.Creator<BPackageSettings> CREATOR = new Parcelable.Creator<BPackageSettings>() { // from class: top.niunaijun.blackbox.core.system.pm.BPackageSettings.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BPackageSettings createFromParcel(Parcel source) {
            return new BPackageSettings(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BPackageSettings[] newArray(int size) {
            return new BPackageSettings[size];
        }
    };

    public BPackageSettings() {
        this.userState = new HashMap();
    }

    public List<BPackageUserState> getUserState() {
        return new ArrayList(this.userState.values());
    }

    public List<Integer> getUserIds() {
        return new ArrayList(this.userState.keySet());
    }

    public void setInstalled(boolean inst, int userId) {
        modifyUserState(userId).installed = inst;
    }

    public boolean getInstalled(int userId) {
        return readUserState(userId).installed;
    }

    public boolean getStopped(int userId) {
        return readUserState(userId).stopped;
    }

    public void setStopped(boolean stop, int userId) {
        modifyUserState(userId).stopped = stop;
    }

    public boolean getHidden(int userId) {
        return readUserState(userId).hidden;
    }

    public void setHidden(boolean hidden, int userId) {
        modifyUserState(userId).hidden = hidden;
    }

    public void removeUser(int userId) {
        this.userState.remove(Integer.valueOf(userId));
    }

    public BPackageUserState readUserState(int userId) {
        BPackageUserState state = this.userState.get(Integer.valueOf(userId));
        if (state == null) {
            state = new BPackageUserState();
        }
        BPackageUserState state2 = new BPackageUserState(state);
        if (this.installOption.isFlag(4) && BXposedManagerService.get().isModuleEnable(this.pkg.packageName) && BXposedManagerService.get().isXPEnable()) {
            state2.installed = true;
        }
        if (userId == -1) {
            state2.installed = true;
        }
        return state2;
    }

    private BPackageUserState modifyUserState(int userId) {
        BPackageUserState state = this.userState.get(Integer.valueOf(userId));
        if (state == null) {
            state = new BPackageUserState();
            this.userState.put(Integer.valueOf(userId), state);
        }
        return state;
    }

    public boolean save() {
        synchronized (this) {
            Parcel parcel = Parcel.obtain();
            AtomicFile atomicFile = new AtomicFile(BEnvironment.getPackageConf(this.pkg.packageName));
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    writeToParcel(parcel, 0);
                    parcel.setDataPosition(0);
                    fileOutputStream = atomicFile.startWrite();
                    FileUtils.writeParcelToOutput(parcel, fileOutputStream);
                    atomicFile.finishWrite(fileOutputStream);
                    parcel.recycle();
                    CloseUtils.close(fileOutputStream);
                } catch (Throwable th) {
                    parcel.recycle();
                    CloseUtils.close(fileOutputStream);
                    throw th;
                }
            } catch (Throwable e) {
                e.printStackTrace();
                atomicFile.failWrite(fileOutputStream);
                parcel.recycle();
                CloseUtils.close(fileOutputStream);
                return false;
            }
        }
        return true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.pkg, flags);
        dest.writeInt(this.appId);
        dest.writeParcelable(this.installOption, flags);
        dest.writeInt(this.userState.size());
        for (Map.Entry<Integer, BPackageUserState> entry : this.userState.entrySet()) {
            dest.writeValue(entry.getKey());
            dest.writeParcelable(entry.getValue(), flags);
        }
    }

    protected BPackageSettings(Parcel in) {
        this.userState = new HashMap();
        this.pkg = (BPackage) in.readParcelable(BPackage.class.getClassLoader());
        this.appId = in.readInt();
        this.installOption = (InstallOption) in.readParcelable(InstallOption.class.getClassLoader());
        int userStateSize = in.readInt();
        this.userState = new HashMap(userStateSize);
        for (int i = 0; i < userStateSize; i++) {
            Integer key = (Integer) in.readValue(Integer.class.getClassLoader());
            BPackageUserState value = (BPackageUserState) in.readParcelable(BPackageUserState.class.getClassLoader());
            this.userState.put(key, value);
        }
    }
}
