package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.jar.Attributes;

/* JADX INFO: loaded from: classes.jar:android/content/pm/ManifestDigest.class */
public class ManifestDigest implements Parcelable {
    public static final Parcelable.Creator<ManifestDigest> CREATOR = new Parcelable.Creator<ManifestDigest>() { // from class: android.content.pm.ManifestDigest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ManifestDigest createFromParcel(Parcel source) {
            return new ManifestDigest(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ManifestDigest[] newArray(int size) {
            return new ManifestDigest[size];
        }
    };

    ManifestDigest(byte[] digest) {
        throw new RuntimeException("Stub!");
    }

    private ManifestDigest(Parcel source) {
        throw new RuntimeException("Stub!");
    }

    static ManifestDigest fromAttributes(Attributes attributes) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public boolean equals(Object o) {
        throw new RuntimeException("Stub!");
    }

    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public String toString() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }
}
