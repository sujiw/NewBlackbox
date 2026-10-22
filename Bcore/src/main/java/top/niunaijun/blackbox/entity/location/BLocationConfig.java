package top.niunaijun.blackbox.entity.location;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/location/BLocationConfig.class */
public class BLocationConfig implements Parcelable {
    public int pattern;
    public BCell cell;
    public List<BCell> allCell;
    public List<BCell> neighboringCellInfo;
    public BLocation location;
    public static final Parcelable.Creator<BLocationConfig> CREATOR = new Parcelable.Creator<BLocationConfig>() { // from class: top.niunaijun.blackbox.entity.location.BLocationConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BLocationConfig createFromParcel(Parcel source) {
            return new BLocationConfig(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BLocationConfig[] newArray(int size) {
            return new BLocationConfig[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BLocationConfig() {
    }

    public BLocationConfig(Parcel in) {
        refresh(in);
    }

    public void refresh(Parcel in) {
        this.pattern = in.readInt();
        this.cell = (BCell) in.readParcelable(BCell.class.getClassLoader());
        this.allCell = in.createTypedArrayList(BCell.CREATOR);
        this.neighboringCellInfo = in.createTypedArrayList(BCell.CREATOR);
        this.location = (BLocation) in.readParcelable(BLocation.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pattern);
        dest.writeParcelable(this.cell, flags);
        dest.writeTypedList(this.allCell);
        dest.writeTypedList(this.neighboringCellInfo);
        dest.writeParcelable(this.location, flags);
    }
}
