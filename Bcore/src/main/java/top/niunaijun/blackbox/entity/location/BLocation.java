package top.niunaijun.blackbox.entity.location;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/location/BLocation.class */
public class BLocation implements Parcelable {
    private double mLatitude;
    private double mLongitude;
    private double mAltitude;
    private float mSpeed;
    private float mBearing;
    private float mAccuracy;
    public static final Parcelable.Creator<BLocation> CREATOR = new Parcelable.Creator<BLocation>() { // from class: top.niunaijun.blackbox.entity.location.BLocation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BLocation createFromParcel(Parcel source) {
            return new BLocation(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BLocation[] newArray(int size) {
            return new BLocation[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.mLatitude);
        dest.writeDouble(this.mLongitude);
        dest.writeDouble(this.mAltitude);
        dest.writeFloat(this.mSpeed);
        dest.writeFloat(this.mBearing);
        dest.writeFloat(this.mAccuracy);
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public BLocation() {
        this.mLatitude = 0.0d;
        this.mLongitude = 0.0d;
        this.mAltitude = 0.0d;
        this.mSpeed = 0.0f;
        this.mBearing = 0.0f;
        this.mAccuracy = 0.0f;
    }

    public BLocation(double latitude, double mLongitude) {
        this.mLatitude = 0.0d;
        this.mLongitude = 0.0d;
        this.mAltitude = 0.0d;
        this.mSpeed = 0.0f;
        this.mBearing = 0.0f;
        this.mAccuracy = 0.0f;
        this.mLatitude = latitude;
        this.mLongitude = mLongitude;
    }

    public BLocation(Parcel in) {
        this.mLatitude = 0.0d;
        this.mLongitude = 0.0d;
        this.mAltitude = 0.0d;
        this.mSpeed = 0.0f;
        this.mBearing = 0.0f;
        this.mAccuracy = 0.0f;
        this.mLatitude = in.readDouble();
        this.mLongitude = in.readDouble();
        this.mAltitude = in.readDouble();
        this.mAccuracy = in.readFloat();
        this.mSpeed = in.readFloat();
        this.mBearing = in.readFloat();
    }

    public boolean isEmpty() {
        return this.mLatitude == 0.0d && this.mLongitude == 0.0d;
    }

    public String toString() {
        return "BLocation{latitude: " + this.mLatitude + ", longitude: " + this.mLongitude + ", altitude: " + this.mAltitude + ", speed: " + this.mSpeed + ", bearing: " + this.mBearing + ", accuracy: " + this.mAccuracy + '}';
    }

    public Location convert2SystemLocation() {
        Location location = new Location("gps");
        location.setLatitude(this.mLatitude);
        location.setLongitude(this.mLongitude);
        location.setSpeed(this.mSpeed);
        location.setBearing(this.mBearing);
        location.setAccuracy(40.0f);
        location.setTime(System.currentTimeMillis());
        Bundle extraBundle = new Bundle();
        extraBundle.putInt("satellites", 10);
        extraBundle.putInt("satellitesvalue", 10);
        location.setExtras(extraBundle);
        return location;
    }
}
