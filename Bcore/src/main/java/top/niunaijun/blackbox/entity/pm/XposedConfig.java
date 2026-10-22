package top.niunaijun.blackbox.entity.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/pm/XposedConfig.class */
public class XposedConfig implements Parcelable {
    public boolean enable;
    public Map<String, Boolean> moduleState;
    public static final Parcelable.Creator<XposedConfig> CREATOR = new Parcelable.Creator<XposedConfig>() { // from class: top.niunaijun.blackbox.entity.pm.XposedConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public XposedConfig createFromParcel(Parcel source) {
            return new XposedConfig(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public XposedConfig[] newArray(int size) {
            return new XposedConfig[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.enable ? (byte) 1 : (byte) 0);
        dest.writeInt(this.moduleState.size());
        for (Map.Entry<String, Boolean> entry : this.moduleState.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeValue(entry.getValue());
        }
    }

    public XposedConfig() {
        this.moduleState = new HashMap();
    }

    public XposedConfig(Parcel in) {
        this.moduleState = new HashMap();
        this.enable = in.readByte() != 0;
        int mModuleStateSize = in.readInt();
        this.moduleState = new HashMap(mModuleStateSize);
        for (int i = 0; i < mModuleStateSize; i++) {
            String key = in.readString();
            Boolean value = (Boolean) in.readValue(Boolean.class.getClassLoader());
            this.moduleState.put(key, value);
        }
    }
}
