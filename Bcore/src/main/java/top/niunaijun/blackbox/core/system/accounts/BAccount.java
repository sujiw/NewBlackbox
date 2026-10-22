package top.niunaijun.blackbox.core.system.accounts;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/BAccount.class */
public class BAccount implements Parcelable {
    public Account account;
    public String password;
    public HashMap<String, String> accountUserData;
    public HashMap<String, Integer> visibility;
    public HashMap<String, String> authTokens;
    public long updateLastAuthenticatedTime;
    public static final Parcelable.Creator<BAccount> CREATOR = new Parcelable.Creator<BAccount>() { // from class: top.niunaijun.blackbox.core.system.accounts.BAccount.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BAccount createFromParcel(Parcel source) {
            return new BAccount(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BAccount[] newArray(int size) {
            return new BAccount[size];
        }
    };

    public boolean isMatch(Account account) {
        if (account == null) {
            return false;
        }
        return account.equals(this.account);
    }

    public void insertExtra(String key, String value) {
        this.accountUserData.put(key, value);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.account, flags);
        dest.writeString(this.password);
        dest.writeSerializable(this.accountUserData);
        dest.writeSerializable(this.visibility);
        dest.writeSerializable(this.authTokens);
        dest.writeLong(this.updateLastAuthenticatedTime);
    }

    public void readFromParcel(Parcel source) {
        this.account = (Account) source.readParcelable(Account.class.getClassLoader());
        this.password = source.readString();
        this.accountUserData = (HashMap) source.readSerializable();
        this.visibility = (HashMap) source.readSerializable();
        this.authTokens = (HashMap) source.readSerializable();
        this.updateLastAuthenticatedTime = source.readLong();
    }

    public BAccount() {
        this.accountUserData = new LinkedHashMap();
        this.visibility = new LinkedHashMap();
        this.authTokens = new LinkedHashMap();
    }

    protected BAccount(Parcel in) {
        this.accountUserData = new LinkedHashMap();
        this.visibility = new LinkedHashMap();
        this.authTokens = new LinkedHashMap();
        this.account = (Account) in.readParcelable(Account.class.getClassLoader());
        this.password = in.readString();
        this.accountUserData = (HashMap) in.readSerializable();
        this.visibility = (HashMap) in.readSerializable();
        this.authTokens = (HashMap) in.readSerializable();
        this.updateLastAuthenticatedTime = in.readLong();
    }
}
