package top.niunaijun.blackbox.core.system.accounts;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/BUserAccounts.class */
public class BUserAccounts implements Parcelable {
    public final Object lock;
    public int userId;
    public List<BAccount> accounts;
    public static final Parcelable.Creator<BUserAccounts> CREATOR = new Parcelable.Creator<BUserAccounts>() { // from class: top.niunaijun.blackbox.core.system.accounts.BUserAccounts.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BUserAccounts createFromParcel(Parcel source) {
            return new BUserAccounts(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BUserAccounts[] newArray(int size) {
            return new BUserAccounts[size];
        }
    };

    public Account[] toAccounts() {
        List<Account> local = new ArrayList<>();
        for (BAccount account : this.accounts) {
            local.add(account.account);
        }
        return (Account[]) local.toArray(new Account[0]);
    }

    public BAccount addAccount(Account account) {
        BAccount bAccount = new BAccount();
        bAccount.account = account;
        this.accounts.add(bAccount);
        return bAccount;
    }

    public BAccount getAccount(Account account) {
        for (BAccount bAccount : this.accounts) {
            if (bAccount.isMatch(account)) {
                return bAccount;
            }
        }
        return null;
    }

    public boolean delAccount(Account account) {
        BAccount bAccount = getAccount(account);
        return this.accounts.remove(bAccount);
    }

    public Map<String, Integer> getVisibility(Account account) {
        BAccount bAccount = getAccount(account);
        if (bAccount == null) {
            return new HashMap();
        }
        return bAccount.visibility;
    }

    public Map<String, String> getAccountUserData(Account account) {
        BAccount bAccount = getAccount(account);
        if (bAccount == null) {
            return new HashMap();
        }
        return bAccount.accountUserData;
    }

    public Map<String, String> getAuthToken(Account account) {
        BAccount bAccount = getAccount(account);
        if (bAccount == null) {
            return new HashMap();
        }
        return bAccount.authTokens;
    }

    public Account[] getAccountsByType(String type) {
        List<Account> local = new ArrayList<>();
        for (BAccount account : this.accounts) {
            if (account.account.type.equals(type)) {
                local.add(account.account);
            }
        }
        return (Account[]) local.toArray(new Account[0]);
    }

    public void updateLastAuthenticatedTime(Account account) {
        BAccount bAccount = getAccount(account);
        if (bAccount != null) {
            bAccount.updateLastAuthenticatedTime = System.currentTimeMillis();
        }
    }

    public long findAccountLastAuthenticatedTime(Account account) {
        BAccount bAccount = getAccount(account);
        if (bAccount != null) {
            return bAccount.updateLastAuthenticatedTime;
        }
        return -1L;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeTypedList(this.accounts);
    }

    public void readFromParcel(Parcel source) {
        this.userId = source.readInt();
        this.accounts = source.createTypedArrayList(BAccount.CREATOR);
    }

    public BUserAccounts() {
        this.lock = new Object();
        this.accounts = new ArrayList();
    }

    protected BUserAccounts(Parcel in) {
        this.lock = new Object();
        this.accounts = new ArrayList();
        this.userId = in.readInt();
        this.accounts = in.createTypedArrayList(BAccount.CREATOR);
    }
}
