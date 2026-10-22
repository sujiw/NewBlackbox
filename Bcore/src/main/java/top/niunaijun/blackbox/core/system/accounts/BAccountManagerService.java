package top.niunaijun.blackbox.core.system.accounts;

import android.accounts.Account;
import android.accounts.AuthenticatorDescription;
import android.accounts.IAccountAuthenticator;
import android.accounts.IAccountAuthenticatorResponse;
import android.accounts.IAccountManagerResponse;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.annotation.NonNull;
import androidx.core.util.AtomicFile;
import androidx.core.util.Preconditions;
import black.com.android.internal.BRRstyleable;
import black.com.android.internal.RstyleableStatic;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.env.BEnvironment;
import top.niunaijun.blackbox.core.system.BProcessManagerService;
import top.niunaijun.blackbox.core.system.ISystemService;
import top.niunaijun.blackbox.core.system.ProcessRecord;
import top.niunaijun.blackbox.core.system.pm.BPackageManagerService;
import top.niunaijun.blackbox.core.system.pm.PackageMonitor;
import top.niunaijun.blackbox.utils.ArrayUtils;
import top.niunaijun.blackbox.utils.CloseUtils;
import top.niunaijun.blackbox.utils.FileUtils;
import top.niunaijun.blackbox.utils.Slog;
import top.niunaijun.blackbox.utils.compat.AccountManagerCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/BAccountManagerService.class */
@SuppressLint({"InlinedApi"})
public class BAccountManagerService extends IBAccountManagerService.Stub implements ISystemService, PackageMonitor {
    private static final String TAG = "AccountManagerService";
    private static BAccountManagerService sService = new BAccountManagerService();
    private static final Account[] EMPTY_ACCOUNT_ARRAY = new Account[0];
    private static final int MESSAGE_TIMED_OUT = 3;
    private static final int MESSAGE_COPY_SHARED_ACCOUNT = 4;
    private final Map<Integer, BUserAccounts> mUserAccountsMap = new HashMap();
    private final AuthenticatorCache mAuthenticatorCache = new AuthenticatorCache();
    private final LinkedList<TokenCache> mTokenCaches = new LinkedList<>();
    private final LinkedHashMap<String, Session> mSessions = new LinkedHashMap<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Context mContext = BlackBoxCore.getContext();
    private final BPackageManagerService mPms = BPackageManagerService.get();

    public static BAccountManagerService get() {
        return sService;
    }

    @Override // top.niunaijun.blackbox.core.system.ISystemService
    public void systemReady() {
        loadAccounts();
        loadAuthenticatorCache(null);
        this.mPms.addPackageMonitor(this);
    }

    @Override // top.niunaijun.blackbox.core.system.pm.PackageMonitor
    public void onPackageUninstalled(String packageName, boolean isRemove, int userId) {
        loadAuthenticatorCache(null);
    }

    @Override // top.niunaijun.blackbox.core.system.pm.PackageMonitor
    public void onPackageInstalled(String packageName, int userId) {
        loadAuthenticatorCache(packageName);
    }

    private void loadAccounts() {
        Parcel parcel = Parcel.obtain();
        InputStream is = null;
        try {
            try {
                File userInfoConf = BEnvironment.getAccountsConf();
                if (!userInfoConf.exists()) {
                    parcel.recycle();
                    return;
                }
                is = new FileInputStream(BEnvironment.getAccountsConf());
                byte[] bytes = FileUtils.toByteArray(is);
                parcel.unmarshall(bytes, 0, bytes.length);
                parcel.setDataPosition(0);
                HashMap<Integer, BUserAccounts> accountsMap = parcel.readHashMap(BUserAccounts.class.getClassLoader());
                if (accountsMap == null) {
                    parcel.recycle();
                    CloseUtils.close(is);
                    return;
                }
                synchronized (this.mUserAccountsMap) {
                    try {
                        this.mUserAccountsMap.clear();
                        for (Integer key : accountsMap.keySet()) {
                            this.mUserAccountsMap.put(key, accountsMap.get(key));
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                parcel.recycle();
                CloseUtils.close(is);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                parcel.recycle();
                CloseUtils.close(is);
                return;
            }
        } catch (Throwable th2) {
            parcel.recycle();
            CloseUtils.close(is);
            throw th2;
        }
    }

    private void saveAllAccounts() {
        synchronized (this.mUserAccountsMap) {
            Parcel parcel = Parcel.obtain();
            AtomicFile atomicFile = new AtomicFile(BEnvironment.getAccountsConf());
            FileOutputStream fileOutputStream = null;
            try {
                parcel.writeMap(this.mUserAccountsMap);
                try {
                    try {
                        fileOutputStream = atomicFile.startWrite();
                        FileUtils.writeParcelToOutput(parcel, fileOutputStream);
                        atomicFile.finishWrite(fileOutputStream);
                        CloseUtils.close(fileOutputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                        atomicFile.failWrite(fileOutputStream);
                        CloseUtils.close(fileOutputStream);
                    }
                    parcel.recycle();
                } catch (Throwable th) {
                    CloseUtils.close(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                parcel.recycle();
                throw th2;
            }
        }
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public String getPassword(Account account, int userId) throws RemoteException {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "getPassword: " + account + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        }
        BUserAccounts accounts = getUserAccounts(userId);
        return readPasswordInternal(accounts, account);
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public String getUserData(Account account, String key, int userId) throws RemoteException {
        if (Log.isLoggable(TAG, 2)) {
            String msg = String.format("getUserData( account: %s, key: %s, callerUid: %s, pid: %s", account, key, Integer.valueOf(Binder.getCallingUid()), Integer.valueOf(Binder.getCallingPid()));
            Log.v(TAG, msg);
        }
        Objects.requireNonNull(account, "account cannot be null");
        Objects.requireNonNull(key, "key cannot be null");
        BUserAccounts accounts = getUserAccounts(userId);
        return readUserDataInternal(accounts, account, key);
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public AuthenticatorDescription[] getAuthenticatorTypes(int userId) throws RemoteException {
        BUserAccounts userAccounts = getUserAccounts(userId);
        List<AuthenticatorDescription> authenticatorDescriptions = new ArrayList<>();
        synchronized (userAccounts.lock) {
            for (BAccount account : userAccounts.accounts) {
                AuthenticatorInfo authenticatorInfo = this.mAuthenticatorCache.authenticators.get(account.account.type);
                if (authenticatorInfo != null) {
                    authenticatorDescriptions.add(authenticatorInfo.desc);
                }
            }
        }
        return (AuthenticatorDescription[]) authenticatorDescriptions.toArray(new AuthenticatorDescription[0]);
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public Account[] getAccountsForPackage(String packageName, int uid, int userId) throws RemoteException {
        BUserAccounts userAccounts = getUserAccounts(userId);
        List<Account> accounts = new ArrayList<>();
        synchronized (userAccounts.lock) {
            for (BAccount account : userAccounts.accounts) {
                Integer visibility = account.visibility.get(packageName);
                if (visibility != null && visibility.intValue() == 1) {
                    accounts.add(account.account);
                }
            }
        }
        return (Account[]) accounts.toArray(new Account[0]);
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public Account[] getAccountsByTypeForPackage(String type, String packageName, int userId) throws RemoteException {
        Integer visibility;
        BUserAccounts userAccounts = getUserAccounts(userId);
        List<Account> accounts = new ArrayList<>();
        synchronized (userAccounts.lock) {
            for (BAccount account : userAccounts.accounts) {
                if (account.account.type.equals(type) && (visibility = account.visibility.get(packageName)) != null && visibility.intValue() == 1) {
                    accounts.add(account.account);
                }
            }
        }
        return (Account[]) accounts.toArray(new Account[0]);
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public Account[] getAccountsAsUser(String accountType, int userId) throws RemoteException {
        BUserAccounts userAccounts = getUserAccounts(userId);
        List<Account> accounts = new ArrayList<>();
        synchronized (userAccounts.lock) {
            for (BAccount account : userAccounts.accounts) {
                if (account.account.type.equals(accountType)) {
                    accounts.add(account.account);
                }
            }
        }
        return (Account[]) accounts.toArray(new Account[0]);
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void getAccountByTypeAndFeatures(final IAccountManagerResponse response, String accountType, String[] features, final int userId) throws RemoteException {
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        }
        if (accountType == null) {
            throw new IllegalArgumentException("accountType is null");
        }
        final String opPackageName = getCallingPackageName();
        BUserAccounts userAccounts = getUserAccounts(userId);
        if (ArrayUtils.isEmpty(features)) {
            Account[] accountsWithManagedNotVisible = getAccountsFromCache(userAccounts, accountType, opPackageName, true);
            handleGetAccountsResult(response, accountsWithManagedNotVisible, opPackageName, userId);
        } else {
            IAccountManagerResponse retrieveAccountsResponse = new IAccountManagerResponse.Stub() { // from class: top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.1
                @Override // android.accounts.IAccountManagerResponse
                public void onResult(Bundle value) throws RemoteException {
                    Parcelable[] parcelables = value.getParcelableArray("accounts");
                    Account[] accounts = new Account[parcelables.length];
                    for (int i = 0; i < parcelables.length; i++) {
                        accounts[i] = (Account) parcelables[i];
                    }
                    BAccountManagerService.this.handleGetAccountsResult(response, accounts, opPackageName, userId);
                }

                @Override // android.accounts.IAccountManagerResponse
                public void onError(int errorCode, String errorMessage) throws RemoteException {
                }
            };
            new GetAccountsByTypeAndFeatureSession(userAccounts, retrieveAccountsResponse, accountType, features, userId, opPackageName, true).bind();
        }
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void getAccountsByFeatures(IAccountManagerResponse response, String type, String[] features, int userId) throws RemoteException {
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        }
        if (type == null) {
            throw new IllegalArgumentException("accountType is null");
        }
        String opPackageName = getCallingPackageName();
        BUserAccounts userAccounts = getUserAccounts(userId);
        if (features == null || features.length == 0) {
            Account[] accounts = getAccountsFromCache(userAccounts, type, opPackageName, false);
            Bundle result = new Bundle();
            result.putParcelableArray("accounts", accounts);
            onResult(response, result);
            return;
        }
        new GetAccountsByTypeAndFeatureSession(userAccounts, response, type, features, userId, opPackageName, false).bind();
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public boolean addAccountExplicitly(Account account, String password, Bundle extras, int userId) throws RemoteException {
        return addAccountExplicitlyWithVisibility(account, password, extras, null, userId);
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void removeAccountAsUser(IAccountManagerResponse response, Account account, boolean expectActivityLaunch, int userId) throws RemoteException {
        Preconditions.checkArgument(account != null, "account cannot be null");
        Preconditions.checkArgument(response != null, "response cannot be null");
        BUserAccounts accounts = getUserAccounts(userId);
        new RemoveAccountSession(accounts, response, account, expectActivityLaunch).bind();
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public boolean removeAccountExplicitly(Account account, int userId) throws RemoteException {
        int callingUid = Binder.getCallingUid();
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "removeAccountExplicitly: " + account + ", caller's uid " + callingUid + ", pid " + Binder.getCallingPid());
        }
        if (account == null) {
            Log.e(TAG, "account is null");
            return false;
        }
        BUserAccounts accounts = getUserAccounts(userId);
        return removeAccountInternal(accounts, account);
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void copyAccountToUser(final IAccountManagerResponse response, final Account account, final int userFrom, int userTo) throws RemoteException {
        BUserAccounts fromAccounts = getUserAccounts(userFrom);
        final BUserAccounts toAccounts = getUserAccounts(userTo);
        if (fromAccounts == null || toAccounts == null) {
            if (response != null) {
                Bundle result = new Bundle();
                result.putBoolean("booleanResult", false);
                try {
                    response.onResult(result);
                    return;
                } catch (RemoteException e) {
                    Slog.w(TAG, "Failed to report error back to the client." + e);
                    return;
                }
            }
            return;
        }
        Slog.d(TAG, "Copying account " + account.toString() + " from user " + userFrom + " to user " + userTo);
        new Session(fromAccounts, response, account.type, false, false, account.name, false, false) { // from class: top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.2
            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
            protected String toDebugString(long now) {
                return super.toDebugString(now) + ", getAccountCredentialsForClone, " + account.type;
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
            public void run() throws RemoteException {
                this.mAuthenticator.getAccountCredentialsForCloning(this, account);
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session, android.accounts.IAccountAuthenticatorResponse
            public void onResult(Bundle result2) {
                if (result2 != null && result2.getBoolean("booleanResult", false)) {
                    BAccountManagerService.this.completeCloningAccount(response, result2, account, toAccounts, userFrom);
                } else {
                    super.onResult(result2);
                }
            }
        }.bind();
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void invalidateAuthToken(String accountType, String authToken, int userId) throws RemoteException {
        BUserAccounts accounts = getUserAccounts(userId);
        synchronized (accounts.lock) {
            boolean changed = false;
            for (BAccount account : accounts.accounts) {
                if (account.account.type.equals(accountType)) {
                    account.accountUserData.values().remove(authToken);
                    changed = true;
                }
            }
            if (changed) {
                saveAllAccounts();
            }
        }
        synchronized (this.mTokenCaches) {
            Iterator<TokenCache> iterator = this.mTokenCaches.iterator();
            while (iterator.hasNext()) {
                TokenCache next = iterator.next();
                if (next.account.type.equals(accountType) && next.userId == userId && next.authToken.equals(authToken)) {
                    iterator.remove();
                }
            }
        }
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public String peekAuthToken(Account account, String authTokenType, int userId) throws RemoteException {
        String str;
        Objects.requireNonNull(account, "account cannot be null");
        Objects.requireNonNull(authTokenType, "authTokenType cannot be null");
        BUserAccounts accounts = getUserAccounts(userId);
        if (accounts == null) {
            return null;
        }
        synchronized (accounts.lock) {
            str = accounts.getAuthToken(account).get(authTokenType);
        }
        return str;
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void setAuthToken(Account account, String authTokenType, String authToken, int userId) throws RemoteException {
        Objects.requireNonNull(account, "account cannot be null");
        Objects.requireNonNull(authTokenType, "authTokenType cannot be null");
        BUserAccounts accounts = getUserAccounts(userId);
        if (accounts == null) {
            return;
        }
        synchronized (accounts.lock) {
            accounts.getAuthToken(account).put(authTokenType, authToken);
            saveAllAccounts();
        }
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void setPassword(Account account, String password, int userId) throws RemoteException {
        Objects.requireNonNull(account, "account cannot be null");
        BUserAccounts accounts = getUserAccounts(userId);
        if (accounts == null) {
            return;
        }
        synchronized (accounts.lock) {
            BAccount bAccount = accounts.getAccount(account);
            bAccount.password = password;
            bAccount.authTokens.clear();
            saveAllAccounts();
        }
        synchronized (this.mTokenCaches) {
            Iterator<TokenCache> iterator = this.mTokenCaches.iterator();
            while (iterator.hasNext()) {
                TokenCache next = iterator.next();
                if (next.account.equals(account) && next.userId == userId) {
                    iterator.remove();
                }
            }
        }
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void clearPassword(Account account, int userId) throws RemoteException {
        setPassword(account, null, userId);
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void setUserData(Account account, String key, String value, int userId) throws RemoteException {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        }
        BUserAccounts accounts = getUserAccounts(userId);
        if (accounts == null) {
            return;
        }
        synchronized (accounts.lock) {
            accounts.getAccountUserData(account).put(key, value);
            saveAllAccounts();
        }
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void updateAppPermission(Account account, String authTokenType, int uid, boolean value) throws RemoteException {
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void getAuthToken(IAccountManagerResponse response, final Account account, final String authTokenType, final boolean notifyOnAuthFailure, boolean expectActivityLaunch, final Bundle loginOptions, int userId) throws RemoteException {
        String token;
        String authToken;
        Preconditions.checkArgument(response != null, "response cannot be null");
        try {
            if (account == null) {
                Slog.w(TAG, "getAuthToken called with null account");
                response.onError(7, "account is null");
                return;
            }
            if (authTokenType == null) {
                Slog.w(TAG, "getAuthToken called with null authTokenType");
                response.onError(7, "authTokenType is null");
                return;
            }
            BUserAccounts accounts = getUserAccounts(userId);
            AuthenticatorInfo authenticatorInfo = this.mAuthenticatorCache.authenticators.get(account.type);
            final boolean customTokens = authenticatorInfo != null && authenticatorInfo.desc.customTokens;
            final String callerPkg = loginOptions.getString(AccountManagerCompat.KEY_ANDROID_PACKAGE_NAME);
            loginOptions.putInt("callerUid", Binder.getCallingUid());
            loginOptions.putInt("callerPid", Binder.getCallingPid());
            if (notifyOnAuthFailure) {
                loginOptions.putBoolean(AccountManagerCompat.KEY_NOTIFY_ON_FAILURE, true);
            }
            if (!customTokens && (authToken = readAuthTokenInternal(accounts, account, authTokenType)) != null) {
                Bundle result = new Bundle();
                result.putString("authtoken", authToken);
                result.putString("authAccount", account.name);
                result.putString("accountType", account.type);
                onResult(response, result);
                return;
            }
            if (customTokens && (token = readCachedTokenInternal(accounts, account, authTokenType, callerPkg)) != null) {
                if (Log.isLoggable(TAG, 2)) {
                    Log.v(TAG, "getAuthToken: cache hit ofr custom token authenticator.");
                }
                Bundle result2 = new Bundle();
                result2.putString("authtoken", token);
                result2.putString("authAccount", account.name);
                result2.putString("accountType", account.type);
                onResult(response, result2);
                return;
            }
            new Session(accounts, response, account.type, expectActivityLaunch, false, account.name, false, false) { // from class: top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.3
                @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
                protected String toDebugString(long now) {
                    if (loginOptions != null) {
                        loginOptions.keySet();
                    }
                    return super.toDebugString(now) + ", getAuthToken, " + account.toString() + ", authTokenType " + authTokenType + ", loginOptions " + loginOptions + ", notifyOnAuthFailure " + notifyOnAuthFailure;
                }

                @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
                public void run() throws RemoteException {
                    this.mAuthenticator.getAuthToken(this, account, authTokenType, loginOptions);
                }

                @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session, android.accounts.IAccountAuthenticatorResponse
                public void onResult(Bundle result3) {
                    if (result3 != null) {
                        String authToken2 = result3.getString("authtoken");
                        if (authToken2 != null) {
                            String name = result3.getString("authAccount");
                            String type = result3.getString("accountType");
                            if (TextUtils.isEmpty(type) || TextUtils.isEmpty(name)) {
                                onError(5, "the type and name should not be empty");
                                return;
                            }
                            Account resultAccount = new Account(name, type);
                            if (!customTokens) {
                                BAccountManagerService.this.saveAuthTokenToDatabase(this.mAccounts, resultAccount, authTokenType, authToken2);
                            }
                            long expiryMillis = result3.getLong(AccountManagerCompat.KEY_CUSTOM_TOKEN_EXPIRY, 0L);
                            if (customTokens && expiryMillis > System.currentTimeMillis()) {
                                BAccountManagerService.this.saveCachedToken(this.mAccounts, account, callerPkg, authTokenType, authToken2, expiryMillis);
                            }
                        }
                        Intent intent = (Intent) result3.getParcelable("intent");
                        if (intent == null || !notifyOnAuthFailure || !customTokens) {
                        }
                    }
                    super.onResult(result3);
                }
            }.bind();
        } catch (RemoteException e) {
            Slog.w(TAG, "Failed to report error back to the client." + e);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void addAccount(IAccountManagerResponse response, final String accountType, final String authTokenType, final String[] requiredFeatures, boolean expectActivityLaunch, Bundle optionsIn, int userId) throws RemoteException {
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        }
        if (accountType == null) {
            throw new IllegalArgumentException("accountType is null");
        }
        final Bundle options = optionsIn == null ? new Bundle() : optionsIn;
        BUserAccounts accounts = getUserAccounts(userId);
        new Session(accounts, response, accountType, expectActivityLaunch, true, null, false, true) { // from class: top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.4
            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
            public void run() throws RemoteException {
                this.mAuthenticator.addAccount(this, this.mAccountType, authTokenType, requiredFeatures, options);
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
            protected String toDebugString(long now) {
                return super.toDebugString(now) + ", addAccount, accountType " + accountType + ", requiredFeatures " + Arrays.toString(requiredFeatures);
            }
        }.bind();
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void addAccountAsUser(IAccountManagerResponse response, String accountType, String authTokenType, String[] requiredFeatures, boolean expectActivityLaunch, Bundle options, int userId) throws RemoteException {
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void updateCredentials(IAccountManagerResponse response, final Account account, final String authTokenType, boolean expectActivityLaunch, final Bundle loginOptions, int userId) throws RemoteException {
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        }
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        }
        clearCallingIdentity();
        BUserAccounts accounts = getUserAccounts(userId);
        new Session(accounts, response, account.type, expectActivityLaunch, true, account.name, false, true) { // from class: top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.5
            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
            public void run() throws RemoteException {
                this.mAuthenticator.updateCredentials(this, account, authTokenType, loginOptions);
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
            protected String toDebugString(long now) {
                if (loginOptions != null) {
                    loginOptions.keySet();
                }
                return super.toDebugString(now) + ", updateCredentials, " + account.toString() + ", authTokenType " + authTokenType + ", loginOptions " + loginOptions;
            }
        }.bind();
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void editProperties(IAccountManagerResponse response, final String accountType, boolean expectActivityLaunch, int userId) throws RemoteException {
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        }
        if (accountType == null) {
            throw new IllegalArgumentException("accountType is null");
        }
        BUserAccounts accounts = getUserAccounts(userId);
        new Session(accounts, response, accountType, expectActivityLaunch, true, null, false, false) { // from class: top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.6
            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
            public void run() throws RemoteException {
                this.mAuthenticator.editProperties(this, this.mAccountType);
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
            protected String toDebugString(long now) {
                return super.toDebugString(now) + ", editProperties, accountType " + accountType;
            }
        }.bind();
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void confirmCredentialsAsUser(IAccountManagerResponse response, Account account, Bundle options, boolean expectActivityLaunch, int userId) throws RemoteException {
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public boolean accountAuthenticated(Account account, int userId) throws RemoteException {
        Objects.requireNonNull(account, "account cannot be null");
        BUserAccounts userAccounts = getUserAccounts(userId);
        if (userAccounts == null) {
            return false;
        }
        return updateLastAuthenticatedTime(userAccounts, account);
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void getAuthTokenLabel(IAccountManagerResponse response, final String accountType, final String authTokenType, int userId) throws RemoteException {
        Preconditions.checkArgument(accountType != null, "accountType cannot be null");
        Preconditions.checkArgument(authTokenType != null, "authTokenType cannot be null");
        BUserAccounts accounts = getUserAccounts(userId);
        new Session(accounts, response, accountType, false, false, null, false, false) { // from class: top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.7
            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
            protected String toDebugString(long now) {
                return super.toDebugString(now) + ", getAuthTokenLabel, " + accountType + ", authTokenType " + authTokenType;
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
            public void run() throws RemoteException {
                this.mAuthenticator.getAuthTokenLabel(this, authTokenType);
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session, android.accounts.IAccountAuthenticatorResponse
            public void onResult(Bundle result) {
                if (result != null) {
                    String label = result.getString("authTokenLabelKey");
                    Bundle bundle = new Bundle();
                    bundle.putString("authTokenLabelKey", label);
                    super.onResult(bundle);
                    return;
                }
                super.onResult(result);
            }
        }.bind();
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public Map getPackagesAndVisibilityForAccount(Account account, int userId) throws RemoteException {
        return new HashMap();
    }

    protected void saveCachedToken(BUserAccounts accounts, Account account, String callerPkg, String tokenType, String token, long expiryMillis) {
        if (account == null || tokenType == null || callerPkg == null) {
            return;
        }
        TokenCache cache = new TokenCache(accounts.userId, account, callerPkg, tokenType, token, expiryMillis);
        synchronized (this.mTokenCaches) {
            this.mTokenCaches.add(cache);
        }
    }

    protected void saveAuthTokenToDatabase(BUserAccounts accounts, Account account, String authTokenType, String authToken) {
        if (accounts == null) {
            return;
        }
        synchronized (accounts.lock) {
            accounts.getAuthToken(account).put(authTokenType, authToken);
            saveAllAccounts();
        }
    }

    protected String readCachedTokenInternal(BUserAccounts accounts, Account account, String tokenType, String callingPackage) {
        long nowTime = System.currentTimeMillis();
        synchronized (this.mTokenCaches) {
            Iterator<TokenCache> iterator = this.mTokenCaches.iterator();
            while (iterator.hasNext()) {
                TokenCache next = iterator.next();
                if (next.userId == accounts.userId && next.account.equals(account) && next.authTokenType.equals(tokenType) && next.packageName.equals(callingPackage)) {
                    if (next.expiryEpochMillis > nowTime) {
                        return next.authToken;
                    }
                    iterator.remove();
                }
            }
            return null;
        }
    }

    protected String readAuthTokenInternal(BUserAccounts accounts, Account account, String authTokenType) {
        String str;
        if (accounts == null) {
            return null;
        }
        synchronized (accounts.lock) {
            Map<String, String> authToken = accounts.getAuthToken(account);
            str = authToken.get(authTokenType);
        }
        return str;
    }

    private void completeCloningAccount(IAccountManagerResponse response, final Bundle accountCredentials, final Account account, BUserAccounts targetUser, final int parentUserId) {
        new Session(targetUser, response, account.type, false, false, account.name, false, false) { // from class: top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.8
            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
            protected String toDebugString(long now) {
                return super.toDebugString(now) + ", getAccountCredentialsForClone, " + account.type;
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
            public void run() throws RemoteException {
                for (Account acc : BAccountManagerService.this.getAccounts(parentUserId, BAccountManagerService.this.mContext.getPackageName())) {
                    if (acc.equals(account)) {
                        this.mAuthenticator.addAccountFromCredentials(this, account, accountCredentials);
                        return;
                    }
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session, android.accounts.IAccountAuthenticatorResponse
            public void onResult(Bundle result) {
                super.onResult(result);
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session, android.accounts.IAccountAuthenticatorResponse
            public void onError(int errorCode, String errorMessage) {
                super.onError(errorCode, errorMessage);
            }
        }.bind();
    }

    public Account[] getAccounts(int userId, String opPackageName) {
        BUserAccounts userAccounts = getUserAccounts(userId);
        return (Account[]) userAccounts.accounts.toArray(new Account[0]);
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public boolean addAccountExplicitlyWithVisibility(Account account, String password, Bundle extras, Map packageToVisibility, int userId) {
        BUserAccounts accounts = getUserAccounts(userId);
        return addAccountInternal(accounts, account, password, extras, packageToVisibility);
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public boolean setAccountVisibility(Account account, String packageName, int newVisibility, int userId) throws RemoteException {
        Objects.requireNonNull(account, "account cannot be null");
        Objects.requireNonNull(packageName, "packageName cannot be null");
        BUserAccounts userAccounts = getUserAccounts(userId);
        if (userAccounts == null) {
            return false;
        }
        return setAccountVisibility(account, packageName, newVisibility, userAccounts);
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public int getAccountVisibility(Account account, String packageName, int userId) throws RemoteException {
        Objects.requireNonNull(account, "account cannot be null");
        Objects.requireNonNull(packageName, "packageName cannot be null");
        BUserAccounts accounts = getUserAccounts(userId);
        if ("android:accounts:key_legacy_visible".equals(packageName)) {
            int visibility = getAccountVisibilityFromCache(account, packageName, accounts);
            if (0 != visibility) {
                return visibility;
            }
            return 2;
        }
        if ("android:accounts:key_legacy_not_visible".equals(packageName)) {
            int visibility2 = getAccountVisibilityFromCache(account, packageName, accounts);
            if (0 != visibility2) {
                return visibility2;
            }
            return 4;
        }
        return resolveAccountVisibility(account, packageName, accounts).intValue();
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public Map getAccountsAndVisibilityForPackage(String packageName, String accountType, int userId) throws RemoteException {
        Integer integer;
        Map<Account, Integer> hashMap = new HashMap<>();
        BUserAccounts userAccounts = getUserAccounts(userId);
        synchronized (userAccounts.lock) {
            for (BAccount account : userAccounts.accounts) {
                if (account.account.type.equals(accountType) && (integer = userAccounts.getVisibility(account.account).get(packageName)) != null) {
                    hashMap.put(account.account, integer);
                }
            }
        }
        return hashMap;
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void registerAccountListener(String[] accountTypes, String opPackageName, int userId) throws RemoteException {
    }

    @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
    public void unregisterAccountListener(String[] accountTypes, String opPackageName, int userId) throws RemoteException {
    }

    private boolean addAccountInternal(BUserAccounts accounts, Account account, String password, Bundle extras, Map<String, Integer> packageToVisibility) {
        if (accounts == null) {
            accounts = new BUserAccounts();
        }
        synchronized (accounts.lock) {
            if (accounts.getAccount(account) != null) {
                Slog.d(TAG, "skipping since insertExtra failed for key " + account);
                return false;
            }
            BAccount bAccount = accounts.addAccount(account);
            bAccount.password = password;
            if (extras != null) {
                for (String key : extras.keySet()) {
                    String value = extras.getString(key);
                    bAccount.insertExtra(key, value);
                }
            }
            if (packageToVisibility != null) {
                for (Map.Entry<String, Integer> entry : packageToVisibility.entrySet()) {
                    setAccountVisibility(account, entry.getKey(), entry.getValue().intValue(), accounts);
                }
            }
            saveAllAccounts();
            return true;
        }
    }

    private boolean setAccountVisibility(Account account, String packageName, int newVisibility, BUserAccounts accounts) {
        synchronized (accounts.lock) {
            BAccount bAccount = accounts.getAccount(account);
            if (bAccount == null) {
                return false;
            }
            bAccount.visibility.put(packageName, Integer.valueOf(newVisibility));
            return true;
        }
    }

    protected Account[] getAccountsFromCache(BUserAccounts userAccounts, String accountType, String callingPackage, boolean includeManagedNotVisible) {
        Account[] accounts;
        if (accountType != null) {
            synchronized (userAccounts.lock) {
                accounts = userAccounts.getAccountsByType(accountType);
            }
            if (accounts == null) {
                return EMPTY_ACCOUNT_ARRAY;
            }
            return filterAccounts(userAccounts, (Account[]) Arrays.copyOf(accounts, accounts.length), callingPackage, includeManagedNotVisible);
        }
        int totalLength = 0;
        synchronized (this.mUserAccountsMap) {
            for (BUserAccounts bUserAccounts : this.mUserAccountsMap.values()) {
                totalLength += bUserAccounts.toAccounts().length;
            }
            if (totalLength == 0) {
                return EMPTY_ACCOUNT_ARRAY;
            }
            Account[] accountsArray = new Account[totalLength];
            int totalLength2 = 0;
            for (BUserAccounts bUserAccounts2 : this.mUserAccountsMap.values()) {
                Account[] accountsOfType = bUserAccounts2.toAccounts();
                System.arraycopy(accountsOfType, 0, accountsArray, totalLength2, accountsOfType.length);
                totalLength2 += accountsOfType.length;
            }
            return filterAccounts(userAccounts, accountsArray, callingPackage, includeManagedNotVisible);
        }
    }

    @NonNull
    private Account[] filterAccounts(BUserAccounts accounts, Account[] unfiltered, String callingPackage, boolean includeManagedNotVisible) {
        Map<Account, Integer> firstPass = new LinkedHashMap<>();
        for (Account account : unfiltered) {
            int visibility = resolveAccountVisibility(account, callingPackage, accounts).intValue();
            if (visibility == 1 || visibility == 2 || (includeManagedNotVisible && visibility == 4)) {
                firstPass.put(account, Integer.valueOf(visibility));
            }
        }
        return (Account[]) firstPass.keySet().toArray(new Account[0]);
    }

    private Integer resolveAccountVisibility(Account account, @NonNull String packageName, BUserAccounts accounts) {
        int visibility;
        if (accounts == null) {
            return 3;
        }
        BAccount bAccount = accounts.getAccount(account);
        if (bAccount != null && 0 != (visibility = getAccountVisibilityFromCache(account, packageName, accounts))) {
            return Integer.valueOf(visibility);
        }
        return 3;
    }

    private int getAccountVisibilityFromCache(Account account, String packageName, BUserAccounts accounts) {
        int iIntValue;
        synchronized (accounts.lock) {
            Map<String, Integer> accountVisibility = getPackagesAndVisibilityForAccountLocked(account, accounts);
            Integer visibility = accountVisibility.get(packageName);
            iIntValue = visibility != null ? visibility.intValue() : 0;
        }
        return iIntValue;
    }

    @NonNull
    private Map<String, Integer> getPackagesAndVisibilityForAccountLocked(Account account, BUserAccounts accounts) {
        return accounts.getVisibility(account);
    }

    private void handleGetAccountsResult(IAccountManagerResponse response, Account[] accounts, String callingPackage, int userId) {
        if (needToStartChooseAccountActivity(accounts, callingPackage, userId)) {
            return;
        }
        if (accounts.length == 1) {
            Bundle bundle = new Bundle();
            bundle.putString("authAccount", accounts[0].name);
            bundle.putString("accountType", accounts[0].type);
            onResult(response, bundle);
            return;
        }
        onResult(response, new Bundle());
    }

    private boolean needToStartChooseAccountActivity(Account[] accounts, String callingPackage, int userId) {
        if (accounts.length < 1) {
            return false;
        }
        if (accounts.length > 1) {
            return true;
        }
        Account account = accounts[0];
        BUserAccounts userAccounts = getUserAccounts(userId);
        int visibility = resolveAccountVisibility(account, callingPackage, userAccounts).intValue();
        return visibility == 4;
    }

    private String readUserDataInternal(BUserAccounts accounts, Account account, String key) {
        String str;
        if (accounts == null) {
            return null;
        }
        synchronized (accounts.lock) {
            Map<String, String> accountUserData = accounts.getAccountUserData(account);
            str = accountUserData.get(key);
        }
        return str;
    }

    public String readPasswordInternal(BUserAccounts accounts, Account account) {
        if (accounts == null) {
            return null;
        }
        synchronized (accounts.lock) {
            BAccount bAccount = accounts.getAccount(account);
            if (bAccount == null) {
                return null;
            }
            return bAccount.password;
        }
    }

    public BUserAccounts getUserAccounts(int userId) {
        BUserAccounts bUserAccounts;
        synchronized (this.mUserAccountsMap) {
            BUserAccounts bUserAccounts2 = this.mUserAccountsMap.get(Integer.valueOf(userId));
            if (bUserAccounts2 == null) {
                BUserAccounts bUserAccounts3 = new BUserAccounts();
                this.mUserAccountsMap.put(Integer.valueOf(userId), bUserAccounts3);
            }
            bUserAccounts = this.mUserAccountsMap.get(Integer.valueOf(userId));
        }
        return bUserAccounts;
    }

    private boolean isAccountPresentForCaller(String accountName, String accountType, int userId) {
        BUserAccounts userAccounts = getUserAccounts(userId);
        if (userAccounts != null) {
            BAccount account = userAccounts.getAccount(new Account(accountName, accountType));
            return account != null;
        }
        return false;
    }

    private boolean removeAccountInternal(BUserAccounts accounts, Account account) {
        boolean del;
        synchronized (accounts.lock) {
            del = accounts.delAccount(account);
            if (del) {
                saveAllAccounts();
            }
        }
        return del;
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/BAccountManagerService$RemoveAccountSession.class */
    private class RemoveAccountSession extends Session {
        final Account mAccount;

        public RemoveAccountSession(BUserAccounts accounts, IAccountManagerResponse response, Account account, boolean expectActivityLaunch) {
            super(accounts, response, account.type, expectActivityLaunch, true, account.name, false, false);
            this.mAccount = account;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
        protected String toDebugString(long now) {
            return super.toDebugString(now) + ", removeAccount, account " + this.mAccount;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
        public void run() throws RemoteException {
            this.mAuthenticator.getAccountRemovalAllowed(this, this.mAccount);
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session, android.accounts.IAccountAuthenticatorResponse
        public void onResult(Bundle result) {
            if (result != null && result.containsKey("booleanResult") && !result.containsKey("intent")) {
                boolean removalAllowed = result.getBoolean("booleanResult");
                if (removalAllowed) {
                    BAccountManagerService.this.removeAccountInternal(this.mAccounts, this.mAccount);
                }
                IAccountManagerResponse response = getResponseAndClose();
                if (response != null) {
                    if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                        Log.v(BAccountManagerService.TAG, getClass().getSimpleName() + " calling onResult() on response " + response);
                    }
                    try {
                        response.onResult(result);
                    } catch (RemoteException e) {
                        Slog.e(BAccountManagerService.TAG, "Error calling onResult()", e);
                    }
                }
            }
            super.onResult(result);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/BAccountManagerService$GetAccountsByTypeAndFeatureSession.class */
    private class GetAccountsByTypeAndFeatureSession extends Session {
        private final String[] mFeatures;
        private volatile Account[] mAccountsOfType;
        private volatile ArrayList<Account> mAccountsWithFeatures;
        private volatile int mCurrentAccount;
        private final int mUserId;
        private final String mPackageName;
        private final boolean mIncludeManagedNotVisible;

        public GetAccountsByTypeAndFeatureSession(BUserAccounts accounts, IAccountManagerResponse response, String type, String[] features, int userId, String packageName, boolean includeManagedNotVisible) {
            super(accounts, response, type, false, true, null, false, false);
            this.mAccountsOfType = null;
            this.mAccountsWithFeatures = null;
            this.mCurrentAccount = 0;
            this.mUserId = userId;
            this.mFeatures = features;
            this.mPackageName = packageName;
            this.mIncludeManagedNotVisible = includeManagedNotVisible;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
        public void run() throws RemoteException {
            this.mAccountsOfType = BAccountManagerService.this.getAccountsFromCache(this.mAccounts, this.mAccountType, this.mPackageName, this.mIncludeManagedNotVisible);
            this.mAccountsWithFeatures = new ArrayList<>(this.mAccountsOfType.length);
            this.mCurrentAccount = 0;
            checkAccount();
        }

        public void checkAccount() {
            if (this.mCurrentAccount >= this.mAccountsOfType.length) {
                sendResult();
                return;
            }
            IAccountAuthenticator accountAuthenticator = this.mAuthenticator;
            if (accountAuthenticator == null) {
                if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                    Log.v(BAccountManagerService.TAG, "checkAccount: aborting session since we are no longer connected to the authenticator, " + toDebugString());
                }
            } else {
                try {
                    accountAuthenticator.hasFeatures(this, this.mAccountsOfType[this.mCurrentAccount], this.mFeatures);
                } catch (RemoteException e) {
                    onError(1, "remote exception");
                }
            }
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session, android.accounts.IAccountAuthenticatorResponse
        public void onResult(Bundle result) {
            this.mNumResults++;
            if (result == null) {
                onError(5, "null bundle");
                return;
            }
            if (result.getBoolean("booleanResult", false)) {
                this.mAccountsWithFeatures.add(this.mAccountsOfType[this.mCurrentAccount]);
            }
            this.mCurrentAccount++;
            checkAccount();
        }

        public void sendResult() {
            IAccountManagerResponse response = getResponseAndClose();
            if (response != null) {
                try {
                    Account[] accounts = new Account[this.mAccountsWithFeatures.size()];
                    for (int i = 0; i < accounts.length; i++) {
                        accounts[i] = this.mAccountsWithFeatures.get(i);
                    }
                    if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                        Log.v(BAccountManagerService.TAG, getClass().getSimpleName() + " calling onResult() on response " + response);
                    }
                    Bundle result = new Bundle();
                    result.putParcelableArray("accounts", accounts);
                    response.onResult(result);
                } catch (RemoteException e) {
                    if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                        Log.v(BAccountManagerService.TAG, "failure while notifying response", e);
                    }
                }
            }
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.BAccountManagerService.Session
        protected String toDebugString(long now) {
            return super.toDebugString(now) + ", getAccountsByTypeAndFeatures, " + (this.mFeatures != null ? TextUtils.join(",", this.mFeatures) : null);
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/BAccountManagerService$AuthenticatorInfo.class */
    private static final class AuthenticatorInfo {
        final AuthenticatorDescription desc;
        final ServiceInfo serviceInfo;

        AuthenticatorInfo(AuthenticatorDescription desc, ServiceInfo info) {
            this.desc = desc;
            this.serviceInfo = info;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/BAccountManagerService$AuthenticatorCache.class */
    private static final class AuthenticatorCache {
        final Map<String, AuthenticatorInfo> authenticators = new HashMap();

        private AuthenticatorCache() {
        }
    }

    private static AuthenticatorDescription parseAuthenticatorDescription(Resources resources, String packageName, AttributeSet attributeSet) {
        RstyleableStatic rstyleableStatic = BRRstyleable.get();
        TypedArray array = resources.obtainAttributes(attributeSet, rstyleableStatic.AccountAuthenticator());
        try {
            String accountType = array.getString(rstyleableStatic.AccountAuthenticator_accountType().intValue());
            int label = array.getResourceId(rstyleableStatic.AccountAuthenticator_label().intValue(), 0);
            int icon = array.getResourceId(rstyleableStatic.AccountAuthenticator_icon().intValue(), 0);
            int smallIcon = array.getResourceId(rstyleableStatic.AccountAuthenticator_smallIcon().intValue(), 0);
            int accountPreferences = array.getResourceId(rstyleableStatic.AccountAuthenticator_accountPreferences().intValue(), 0);
            boolean customTokens = array.getBoolean(rstyleableStatic.AccountAuthenticator_customTokens().intValue(), false);
            if (TextUtils.isEmpty(accountType)) {
                return null;
            }
            return new AuthenticatorDescription(accountType, packageName, label, icon, smallIcon, accountPreferences, customTokens);
        } finally {
            array.recycle();
        }
    }

    public void loadAuthenticatorCache(String packageName) {
        this.mAuthenticatorCache.authenticators.clear();
        Intent intent = new Intent("android.accounts.AccountAuthenticator");
        if (packageName != null) {
            intent.setPackage(packageName);
        }
        generateServicesMap(this.mPms.queryIntentServices(intent, 128, -1), this.mAuthenticatorCache.authenticators, new RegisteredServicesParser());
    }

    private void generateServicesMap(List<ResolveInfo> services, Map<String, AuthenticatorInfo> map, RegisteredServicesParser accountParser) {
        int type;
        AuthenticatorDescription desc;
        for (ResolveInfo info : services) {
            XmlResourceParser parser = accountParser.getParser(this.mContext, info.serviceInfo, "android.accounts.AccountAuthenticator");
            if (parser != null) {
                try {
                    AttributeSet attributeSet = Xml.asAttributeSet(parser);
                    do {
                        type = parser.next();
                        if (type == 1) {
                            break;
                        }
                    } while (type != 2);
                    if ("account-authenticator".equals(parser.getName()) && (desc = parseAuthenticatorDescription(accountParser.getResources(this.mContext, info.serviceInfo.applicationInfo), info.serviceInfo.packageName, attributeSet)) != null) {
                        map.put(desc.type, new AuthenticatorInfo(desc, info.serviceInfo));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/BAccountManagerService$Session.class */
    private abstract class Session extends IAccountAuthenticatorResponse.Stub implements IBinder.DeathRecipient, ServiceConnection {
        IAccountManagerResponse mResponse;
        final String mAccountType;
        final boolean mExpectActivityLaunch;
        final long mCreationTime;
        final String mAccountName;
        final boolean mAuthDetailsRequired;
        final boolean mUpdateLastAuthenticatedTime;
        public int mNumResults;
        private int mNumRequestContinued;
        private int mNumErrors;
        IAccountAuthenticator mAuthenticator;
        private final boolean mStripAuthTokenFromResult;
        protected final BUserAccounts mAccounts;

        public abstract void run() throws RemoteException;

        public Session(BUserAccounts accounts, IAccountManagerResponse response, String accountType, boolean expectActivityLaunch, boolean stripAuthTokenFromResult, String accountName, boolean authDetailsRequired, boolean updateLastAuthenticatedTime) {
            this.mNumResults = 0;
            this.mNumRequestContinued = 0;
            this.mNumErrors = 0;
            this.mAuthenticator = null;
            if (accountType == null) {
                throw new IllegalArgumentException("accountType is null");
            }
            this.mAccounts = accounts;
            this.mStripAuthTokenFromResult = stripAuthTokenFromResult;
            this.mResponse = response;
            this.mAccountType = accountType;
            this.mExpectActivityLaunch = expectActivityLaunch;
            this.mCreationTime = SystemClock.elapsedRealtime();
            this.mAccountName = accountName;
            this.mAuthDetailsRequired = authDetailsRequired;
            this.mUpdateLastAuthenticatedTime = updateLastAuthenticatedTime;
            synchronized (BAccountManagerService.this.mSessions) {
                BAccountManagerService.this.mSessions.put(toString(), this);
            }
            if (response != null) {
                try {
                    response.asBinder().linkToDeath(this, 0);
                } catch (RemoteException e) {
                    this.mResponse = null;
                    binderDied();
                }
            }
        }

        IAccountManagerResponse getResponseAndClose() {
            if (this.mResponse == null) {
                return null;
            }
            IAccountManagerResponse response = this.mResponse;
            close();
            return response;
        }

        protected boolean checkKeyIntent(int authUid, Intent intent) {
            if (intent.getClipData() == null) {
                intent.setClipData(ClipData.newPlainText(null, null));
            }
            intent.setFlags(intent.getFlags() & (-196));
            long bid = Binder.clearCallingIdentity();
            try {
                ResolveInfo resolveInfo = BAccountManagerService.this.mPms.resolveActivity(intent, 0, null, this.mAccounts.userId);
                if (resolveInfo == null) {
                    Binder.restoreCallingIdentity(bid);
                    return false;
                }
                Binder.restoreCallingIdentity(bid);
                return true;
            } catch (Throwable th) {
                Binder.restoreCallingIdentity(bid);
                throw th;
            }
        }

        private void close() {
            synchronized (BAccountManagerService.this.mSessions) {
                if (BAccountManagerService.this.mSessions.remove(toString()) == null) {
                    return;
                }
                if (this.mResponse != null) {
                    this.mResponse.asBinder().unlinkToDeath(this, 0);
                    this.mResponse = null;
                }
                cancelTimeout();
                unbind();
            }
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.mResponse = null;
            close();
        }

        protected String toDebugString() {
            return toDebugString(SystemClock.elapsedRealtime());
        }

        protected String toDebugString(long now) {
            return "Session: expectLaunch " + this.mExpectActivityLaunch + ", connected " + (this.mAuthenticator != null) + ", stats (" + this.mNumResults + "/" + this.mNumRequestContinued + "/" + this.mNumErrors + "), lifetime " + ((now - this.mCreationTime) / 1000.0d);
        }

        void bind() {
            if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                Log.v(BAccountManagerService.TAG, "initiating bind to authenticator type " + this.mAccountType);
            }
            if (!bindToAuthenticator(this.mAccountType)) {
                Log.d(BAccountManagerService.TAG, "bind attempt failed for " + toDebugString());
                onError(1, "bind failure");
            }
        }

        private void unbind() {
            if (this.mAuthenticator != null) {
                this.mAuthenticator = null;
                BAccountManagerService.this.mContext.unbindService(this);
            }
        }

        public void cancelTimeout() {
            BAccountManagerService.this.mHandler.removeMessages(3, this);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            this.mAuthenticator = IAccountAuthenticator.Stub.asInterface(service);
            try {
                run();
            } catch (RemoteException e) {
                onError(1, "remote exception");
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            this.mAuthenticator = null;
            IAccountManagerResponse response = getResponseAndClose();
            if (response != null) {
                try {
                    response.onError(1, "disconnected");
                } catch (RemoteException e) {
                    if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                        Log.v(BAccountManagerService.TAG, "Session.onServiceDisconnected: caught RemoteException while responding", e);
                    }
                }
            }
        }

        public void onTimedOut() {
            IAccountManagerResponse response = getResponseAndClose();
            if (response != null) {
                try {
                    response.onError(1, "timeout");
                } catch (RemoteException e) {
                    if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                        Log.v(BAccountManagerService.TAG, "Session.onTimedOut: caught RemoteException while responding", e);
                    }
                }
            }
        }

        public void onResult(Bundle result) {
            IAccountManagerResponse response;
            this.mNumResults++;
            Intent intent = null;
            if (result != null) {
                boolean isSuccessfulConfirmCreds = result.getBoolean("booleanResult", false);
                boolean isSuccessfulUpdateCredsOrAddAccount = result.containsKey("authAccount") && result.containsKey("accountType");
                boolean needUpdate = this.mUpdateLastAuthenticatedTime && (isSuccessfulConfirmCreds || isSuccessfulUpdateCredsOrAddAccount);
                if (needUpdate || this.mAuthDetailsRequired) {
                    boolean accountPresent = BAccountManagerService.this.isAccountPresentForCaller(this.mAccountName, this.mAccountType, this.mAccounts.userId);
                    if (needUpdate && accountPresent) {
                        BAccountManagerService.this.updateLastAuthenticatedTime(this.mAccounts, new Account(this.mAccountName, this.mAccountType));
                    }
                    if (this.mAuthDetailsRequired) {
                        long lastAuthenticatedTime = -1;
                        if (accountPresent) {
                            lastAuthenticatedTime = this.mAccounts.findAccountLastAuthenticatedTime(new Account(this.mAccountName, this.mAccountType));
                        }
                        result.putLong(AccountManagerCompat.KEY_LAST_AUTHENTICATED_TIME, lastAuthenticatedTime);
                    }
                }
            }
            if (result != null) {
                Intent intent2 = (Intent) result.getParcelable("intent");
                intent = intent2;
                if (intent2 != null && !checkKeyIntent(Binder.getCallingUid(), intent)) {
                    onError(5, "invalid intent in bundle returned");
                    return;
                }
            }
            if (result != null && !TextUtils.isEmpty(result.getString("authtoken"))) {
                String accountName = result.getString("authAccount");
                String accountType = result.getString("accountType");
                if (!TextUtils.isEmpty(accountName) && !TextUtils.isEmpty(accountType)) {
                    new Account(accountName, accountType);
                }
            }
            if (this.mExpectActivityLaunch && result != null && result.containsKey("intent")) {
                response = this.mResponse;
            } else {
                response = getResponseAndClose();
            }
            if (response != null) {
                try {
                    if (result == null) {
                        if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                            Log.v(BAccountManagerService.TAG, getClass().getSimpleName() + " calling onError() on response " + response);
                        }
                        response.onError(5, "null bundle returned");
                    } else {
                        if (this.mStripAuthTokenFromResult) {
                            result.remove("authtoken");
                        }
                        if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                            Log.v(BAccountManagerService.TAG, getClass().getSimpleName() + " calling onResult() on response " + response);
                        }
                        if (result.getInt("errorCode", -1) > 0 && intent == null) {
                            response.onError(result.getInt("errorCode"), result.getString("errorMessage"));
                        } else {
                            response.onResult(result);
                        }
                    }
                } catch (RemoteException e) {
                    if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                        Log.v(BAccountManagerService.TAG, "failure while notifying response", e);
                    }
                }
            }
        }

        @Override // android.accounts.IAccountAuthenticatorResponse
        public void onRequestContinued() {
            this.mNumRequestContinued++;
        }

        public void onError(int errorCode, String errorMessage) {
            this.mNumErrors++;
            IAccountManagerResponse response = getResponseAndClose();
            if (response != null) {
                if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                    Log.v(BAccountManagerService.TAG, getClass().getSimpleName() + " calling onError() on response " + response);
                }
                try {
                    response.onError(errorCode, errorMessage);
                    return;
                } catch (RemoteException e) {
                    if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                        Log.v(BAccountManagerService.TAG, "Session.onError: caught RemoteException while responding", e);
                        return;
                    }
                    return;
                }
            }
            if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                Log.v(BAccountManagerService.TAG, "Session.onError: already closed");
            }
        }

        private boolean bindToAuthenticator(String authenticatorType) {
            AuthenticatorInfo authenticatorInfo = BAccountManagerService.this.mAuthenticatorCache.authenticators.get(authenticatorType);
            if (authenticatorInfo == null) {
                if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                    Log.v(BAccountManagerService.TAG, "there is no authenticator for " + authenticatorType + ", bailing out");
                    return false;
                }
                return false;
            }
            Intent intent = new Intent();
            intent.setAction("android.accounts.AccountAuthenticator");
            ComponentName componentName = new ComponentName(authenticatorInfo.serviceInfo.packageName, authenticatorInfo.serviceInfo.name);
            intent.setComponent(componentName);
            intent.putExtra("_B_|_UserId", this.mAccounts.userId);
            if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                Log.v(BAccountManagerService.TAG, "performing bindService to " + componentName);
            }
            if (!BAccountManagerService.this.mContext.bindService(intent, this, 1)) {
                if (Log.isLoggable(BAccountManagerService.TAG, 2)) {
                    Log.v(BAccountManagerService.TAG, "bindService to " + componentName + " failed");
                    return false;
                }
                return false;
            }
            return true;
        }
    }

    private void onResult(IAccountManagerResponse response, Bundle result) {
        if (result == null) {
            Log.e(TAG, "the result is unexpectedly null", new Exception());
        }
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, getClass().getSimpleName() + " calling onResult() on response " + response);
        }
        try {
            response.onResult(result);
        } catch (RemoteException e) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "failure while notifying response", e);
            }
        }
    }

    private boolean updateLastAuthenticatedTime(BUserAccounts userAccounts, Account account) {
        userAccounts.updateLastAuthenticatedTime(account);
        return true;
    }

    private String getCallingPackageName() {
        int callingPid = Binder.getCallingPid();
        ProcessRecord processByPid = BProcessManagerService.get().findProcessByPid(callingPid);
        if (processByPid == null) {
            throw new IllegalArgumentException("ProcessRecord is null, PID: " + callingPid);
        }
        return processByPid.getPackageName();
    }
}
