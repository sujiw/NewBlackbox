package top.niunaijun.blackbox.core.system.accounts;

import android.accounts.Account;
import java.util.Objects;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/TokenCache.class */
public class TokenCache {
    public int userId;
    public Account account;
    public long expiryEpochMillis;
    public String authToken;
    public String authTokenType;
    public String packageName;

    public TokenCache(int userId, Account account, String callerPkg, String tokenType, String token, long expiryMillis) {
        this.userId = userId;
        this.account = account;
        this.expiryEpochMillis = expiryMillis;
        this.authToken = token;
        this.authTokenType = tokenType;
        this.packageName = callerPkg;
    }

    public TokenCache(int userId, Account account, String authTokenType, String packageName) {
        this.userId = userId;
        this.account = account;
        this.authToken = this.authToken;
        this.authTokenType = authTokenType;
        this.packageName = packageName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TokenCache)) {
            return false;
        }
        TokenCache that = (TokenCache) o;
        return this.userId == that.userId && this.expiryEpochMillis == that.expiryEpochMillis && Objects.equals(this.account, that.account) && Objects.equals(this.authToken, that.authToken) && Objects.equals(this.authTokenType, that.authTokenType) && Objects.equals(this.packageName, that.packageName);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.userId), this.account, Long.valueOf(this.expiryEpochMillis), this.authToken, this.authTokenType, this.packageName);
    }
}
