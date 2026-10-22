package top.niunaijun.blackbox.entity.location;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/location/BCell.class */
public class BCell implements Parcelable {
    public int MCC;
    public int MNC;
    public int LAC;
    public int CID;
    public int TYPE;
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    public static final int NETWORK_TYPE_GPRS = 1;
    public static final int NETWORK_TYPE_EDGE = 2;
    public static final int NETWORK_TYPE_UMTS = 3;
    public static final int NETWORK_TYPE_CDMA = 4;
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    public static final int NETWORK_TYPE_EVDO_A = 6;
    public static final int NETWORK_TYPE_1xRTT = 7;
    public static final int PHONE_TYPE_NONE = 0;
    public static final int PHONE_TYPE_GSM = 1;
    public static final int PHONE_TYPE_CDMA = 2;
    public static final Parcelable.Creator<BCell> CREATOR = new Parcelable.Creator<BCell>() { // from class: top.niunaijun.blackbox.entity.location.BCell.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BCell createFromParcel(Parcel source) {
            return new BCell(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BCell[] newArray(int size) {
            return new BCell[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.MCC);
        dest.writeInt(this.MNC);
        dest.writeInt(this.LAC);
        dest.writeInt(this.CID);
        dest.writeInt(this.TYPE);
    }

    public BCell() {
    }

    public BCell(int MCC, int MNC, int LAC, int CID) {
        this.TYPE = 1;
        this.MCC = MCC;
        this.CID = CID;
        this.MNC = MNC;
        this.LAC = LAC;
    }

    public BCell(Parcel in) {
        this.MCC = in.readInt();
        this.MNC = in.readInt();
        this.LAC = in.readInt();
        this.CID = in.readInt();
        this.TYPE = in.readInt();
    }
}
