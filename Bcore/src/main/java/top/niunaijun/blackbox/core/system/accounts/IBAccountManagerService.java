package top.niunaijun.blackbox.core.system.accounts;

import android.accounts.Account;
import android.accounts.AuthenticatorDescription;
import android.accounts.IAccountManagerResponse;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.Map;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/IBAccountManagerService.class */
public interface IBAccountManagerService extends IInterface {
    public static final String DESCRIPTOR = "top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService";

    String getPassword(Account account, int i) throws RemoteException;

    String getUserData(Account account, String str, int i) throws RemoteException;

    AuthenticatorDescription[] getAuthenticatorTypes(int i) throws RemoteException;

    Account[] getAccountsForPackage(String str, int i, int i2) throws RemoteException;

    Account[] getAccountsByTypeForPackage(String str, String str2, int i) throws RemoteException;

    Account[] getAccountsAsUser(String str, int i) throws RemoteException;

    void getAccountByTypeAndFeatures(IAccountManagerResponse iAccountManagerResponse, String str, String[] strArr, int i) throws RemoteException;

    void getAccountsByFeatures(IAccountManagerResponse iAccountManagerResponse, String str, String[] strArr, int i) throws RemoteException;

    boolean addAccountExplicitly(Account account, String str, Bundle bundle, int i) throws RemoteException;

    void removeAccountAsUser(IAccountManagerResponse iAccountManagerResponse, Account account, boolean z, int i) throws RemoteException;

    boolean removeAccountExplicitly(Account account, int i) throws RemoteException;

    void copyAccountToUser(IAccountManagerResponse iAccountManagerResponse, Account account, int i, int i2) throws RemoteException;

    void invalidateAuthToken(String str, String str2, int i) throws RemoteException;

    String peekAuthToken(Account account, String str, int i) throws RemoteException;

    void setAuthToken(Account account, String str, String str2, int i) throws RemoteException;

    void setPassword(Account account, String str, int i) throws RemoteException;

    void clearPassword(Account account, int i) throws RemoteException;

    void setUserData(Account account, String str, String str2, int i) throws RemoteException;

    void updateAppPermission(Account account, String str, int i, boolean z) throws RemoteException;

    void getAuthToken(IAccountManagerResponse iAccountManagerResponse, Account account, String str, boolean z, boolean z2, Bundle bundle, int i) throws RemoteException;

    void addAccount(IAccountManagerResponse iAccountManagerResponse, String str, String str2, String[] strArr, boolean z, Bundle bundle, int i) throws RemoteException;

    void addAccountAsUser(IAccountManagerResponse iAccountManagerResponse, String str, String str2, String[] strArr, boolean z, Bundle bundle, int i) throws RemoteException;

    void updateCredentials(IAccountManagerResponse iAccountManagerResponse, Account account, String str, boolean z, Bundle bundle, int i) throws RemoteException;

    void editProperties(IAccountManagerResponse iAccountManagerResponse, String str, boolean z, int i) throws RemoteException;

    void confirmCredentialsAsUser(IAccountManagerResponse iAccountManagerResponse, Account account, Bundle bundle, boolean z, int i) throws RemoteException;

    boolean accountAuthenticated(Account account, int i) throws RemoteException;

    void getAuthTokenLabel(IAccountManagerResponse iAccountManagerResponse, String str, String str2, int i) throws RemoteException;

    Map getPackagesAndVisibilityForAccount(Account account, int i) throws RemoteException;

    boolean addAccountExplicitlyWithVisibility(Account account, String str, Bundle bundle, Map map, int i) throws RemoteException;

    boolean setAccountVisibility(Account account, String str, int i, int i2) throws RemoteException;

    int getAccountVisibility(Account account, String str, int i) throws RemoteException;

    Map getAccountsAndVisibilityForPackage(String str, String str2, int i) throws RemoteException;

    void registerAccountListener(String[] strArr, String str, int i) throws RemoteException;

    void unregisterAccountListener(String[] strArr, String str, int i) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/IBAccountManagerService$Default.class */
    public static class Default implements IBAccountManagerService {
        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public String getPassword(Account account, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public String getUserData(Account account, String key, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public AuthenticatorDescription[] getAuthenticatorTypes(int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public Account[] getAccountsForPackage(String packageName, int uid, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public Account[] getAccountsByTypeForPackage(String type, String packageName, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public Account[] getAccountsAsUser(String accountType, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void getAccountByTypeAndFeatures(IAccountManagerResponse response, String accountType, String[] features, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void getAccountsByFeatures(IAccountManagerResponse response, String accountType, String[] features, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public boolean addAccountExplicitly(Account account, String password, Bundle extras, int userId) throws RemoteException {
            return false;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void removeAccountAsUser(IAccountManagerResponse response, Account account, boolean expectActivityLaunch, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public boolean removeAccountExplicitly(Account account, int userId) throws RemoteException {
            return false;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void copyAccountToUser(IAccountManagerResponse response, Account account, int userFrom, int userTo) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void invalidateAuthToken(String accountType, String authToken, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public String peekAuthToken(Account account, String authTokenType, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void setAuthToken(Account account, String authTokenType, String authToken, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void setPassword(Account account, String password, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void clearPassword(Account account, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void setUserData(Account account, String key, String value, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void updateAppPermission(Account account, String authTokenType, int uid, boolean value) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void getAuthToken(IAccountManagerResponse response, Account account, String authTokenType, boolean notifyOnAuthFailure, boolean expectActivityLaunch, Bundle options, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void addAccount(IAccountManagerResponse response, String accountType, String authTokenType, String[] requiredFeatures, boolean expectActivityLaunch, Bundle options, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void addAccountAsUser(IAccountManagerResponse response, String accountType, String authTokenType, String[] requiredFeatures, boolean expectActivityLaunch, Bundle options, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void updateCredentials(IAccountManagerResponse response, Account account, String authTokenType, boolean expectActivityLaunch, Bundle options, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void editProperties(IAccountManagerResponse response, String accountType, boolean expectActivityLaunch, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void confirmCredentialsAsUser(IAccountManagerResponse response, Account account, Bundle options, boolean expectActivityLaunch, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public boolean accountAuthenticated(Account account, int userId) throws RemoteException {
            return false;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void getAuthTokenLabel(IAccountManagerResponse response, String accountType, String authTokenType, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public Map getPackagesAndVisibilityForAccount(Account account, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public boolean addAccountExplicitlyWithVisibility(Account account, String password, Bundle extras, Map visibility, int userId) throws RemoteException {
            return false;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public boolean setAccountVisibility(Account a, String packageName, int newVisibility, int userId) throws RemoteException {
            return false;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public int getAccountVisibility(Account a, String packageName, int userId) throws RemoteException {
            return 0;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public Map getAccountsAndVisibilityForPackage(String packageName, String accountType, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void registerAccountListener(String[] accountTypes, String opPackageName, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
        public void unregisterAccountListener(String[] accountTypes, String opPackageName, int userId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/IBAccountManagerService$Stub.class */
    public static abstract class Stub extends Binder implements IBAccountManagerService {
        static final int TRANSACTION_getPassword = 1;
        static final int TRANSACTION_getUserData = 2;
        static final int TRANSACTION_getAuthenticatorTypes = 3;
        static final int TRANSACTION_getAccountsForPackage = 4;
        static final int TRANSACTION_getAccountsByTypeForPackage = 5;
        static final int TRANSACTION_getAccountsAsUser = 6;
        static final int TRANSACTION_getAccountByTypeAndFeatures = 7;
        static final int TRANSACTION_getAccountsByFeatures = 8;
        static final int TRANSACTION_addAccountExplicitly = 9;
        static final int TRANSACTION_removeAccountAsUser = 10;
        static final int TRANSACTION_removeAccountExplicitly = 11;
        static final int TRANSACTION_copyAccountToUser = 12;
        static final int TRANSACTION_invalidateAuthToken = 13;
        static final int TRANSACTION_peekAuthToken = 14;
        static final int TRANSACTION_setAuthToken = 15;
        static final int TRANSACTION_setPassword = 16;
        static final int TRANSACTION_clearPassword = 17;
        static final int TRANSACTION_setUserData = 18;
        static final int TRANSACTION_updateAppPermission = 19;
        static final int TRANSACTION_getAuthToken = 20;
        static final int TRANSACTION_addAccount = 21;
        static final int TRANSACTION_addAccountAsUser = 22;
        static final int TRANSACTION_updateCredentials = 23;
        static final int TRANSACTION_editProperties = 24;
        static final int TRANSACTION_confirmCredentialsAsUser = 25;
        static final int TRANSACTION_accountAuthenticated = 26;
        static final int TRANSACTION_getAuthTokenLabel = 27;
        static final int TRANSACTION_getPackagesAndVisibilityForAccount = 28;
        static final int TRANSACTION_addAccountExplicitlyWithVisibility = 29;
        static final int TRANSACTION_setAccountVisibility = 30;
        static final int TRANSACTION_getAccountVisibility = 31;
        static final int TRANSACTION_getAccountsAndVisibilityForPackage = 32;
        static final int TRANSACTION_registerAccountListener = 33;
        static final int TRANSACTION_unregisterAccountListener = 34;

        public Stub() {
            attachInterface(this, IBAccountManagerService.DESCRIPTOR);
        }

        public static IBAccountManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBAccountManagerService.DESCRIPTOR);
            if (iin != null && (iin instanceof IBAccountManagerService)) {
                return (IBAccountManagerService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code >= 1 && code <= 16777215) {
                data.enforceInterface(IBAccountManagerService.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IBAccountManagerService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            Account _arg0 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            int _arg1 = data.readInt();
                            String _result = getPassword(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeString(_result);
                            return true;
                        case 2:
                            Account _arg2 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg3 = data.readString();
                            int _arg4 = data.readInt();
                            String _result2 = getUserData(_arg2, _arg3, _arg4);
                            reply.writeNoException();
                            reply.writeString(_result2);
                            return true;
                        case 3:
                            int _arg5 = data.readInt();
                            AuthenticatorDescription[] _result3 = getAuthenticatorTypes(_arg5);
                            reply.writeNoException();
                            reply.writeTypedArray(_result3, 1);
                            return true;
                        case 4:
                            String _arg6 = data.readString();
                            int _arg7 = data.readInt();
                            int _arg8 = data.readInt();
                            Account[] _result4 = getAccountsForPackage(_arg6, _arg7, _arg8);
                            reply.writeNoException();
                            reply.writeTypedArray(_result4, 1);
                            return true;
                        case 5:
                            String _arg9 = data.readString();
                            String _arg10 = data.readString();
                            int _arg11 = data.readInt();
                            Account[] _result5 = getAccountsByTypeForPackage(_arg9, _arg10, _arg11);
                            reply.writeNoException();
                            reply.writeTypedArray(_result5, 1);
                            return true;
                        case 6:
                            String _arg12 = data.readString();
                            int _arg13 = data.readInt();
                            Account[] _result6 = getAccountsAsUser(_arg12, _arg13);
                            reply.writeNoException();
                            reply.writeTypedArray(_result6, 1);
                            return true;
                        case 7:
                            IAccountManagerResponse _arg14 = IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                            String _arg15 = data.readString();
                            String[] _arg16 = data.createStringArray();
                            int _arg17 = data.readInt();
                            getAccountByTypeAndFeatures(_arg14, _arg15, _arg16, _arg17);
                            reply.writeNoException();
                            return true;
                        case 8:
                            IAccountManagerResponse _arg18 = IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                            String _arg19 = data.readString();
                            String[] _arg20 = data.createStringArray();
                            int _arg21 = data.readInt();
                            getAccountsByFeatures(_arg18, _arg19, _arg20, _arg21);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_addAccountExplicitly /* 9 */:
                            Account _arg22 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg23 = data.readString();
                            Bundle _arg24 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            int _arg25 = data.readInt();
                            boolean _result7 = addAccountExplicitly(_arg22, _arg23, _arg24, _arg25);
                            reply.writeNoException();
                            reply.writeInt(_result7 ? 1 : 0);
                            return true;
                        case 10:
                            IAccountManagerResponse _arg26 = IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                            Account _arg27 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            boolean _arg28 = 0 != data.readInt();
                            int _arg29 = data.readInt();
                            removeAccountAsUser(_arg26, _arg27, _arg28, _arg29);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_removeAccountExplicitly /* 11 */:
                            Account _arg30 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            int _arg31 = data.readInt();
                            boolean _result8 = removeAccountExplicitly(_arg30, _arg31);
                            reply.writeNoException();
                            reply.writeInt(_result8 ? 1 : 0);
                            return true;
                        case TRANSACTION_copyAccountToUser /* 12 */:
                            IAccountManagerResponse _arg32 = IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                            Account _arg33 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            int _arg34 = data.readInt();
                            int _arg35 = data.readInt();
                            copyAccountToUser(_arg32, _arg33, _arg34, _arg35);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_invalidateAuthToken /* 13 */:
                            String _arg36 = data.readString();
                            String _arg37 = data.readString();
                            int _arg38 = data.readInt();
                            invalidateAuthToken(_arg36, _arg37, _arg38);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_peekAuthToken /* 14 */:
                            Account _arg39 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg40 = data.readString();
                            int _arg41 = data.readInt();
                            String _result9 = peekAuthToken(_arg39, _arg40, _arg41);
                            reply.writeNoException();
                            reply.writeString(_result9);
                            return true;
                        case TRANSACTION_setAuthToken /* 15 */:
                            Account _arg42 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg43 = data.readString();
                            String _arg44 = data.readString();
                            int _arg45 = data.readInt();
                            setAuthToken(_arg42, _arg43, _arg44, _arg45);
                            reply.writeNoException();
                            return true;
                        case 16:
                            Account _arg46 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg47 = data.readString();
                            int _arg48 = data.readInt();
                            setPassword(_arg46, _arg47, _arg48);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_clearPassword /* 17 */:
                            Account _arg49 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            int _arg50 = data.readInt();
                            clearPassword(_arg49, _arg50);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_setUserData /* 18 */:
                            Account _arg51 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg52 = data.readString();
                            String _arg53 = data.readString();
                            int _arg54 = data.readInt();
                            setUserData(_arg51, _arg52, _arg53, _arg54);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_updateAppPermission /* 19 */:
                            Account _arg55 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg56 = data.readString();
                            int _arg57 = data.readInt();
                            boolean _arg58 = 0 != data.readInt();
                            updateAppPermission(_arg55, _arg56, _arg57, _arg58);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_getAuthToken /* 20 */:
                            IAccountManagerResponse _arg59 = IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                            Account _arg60 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg61 = data.readString();
                            boolean _arg62 = 0 != data.readInt();
                            boolean _arg63 = 0 != data.readInt();
                            Bundle _arg64 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            int _arg65 = data.readInt();
                            getAuthToken(_arg59, _arg60, _arg61, _arg62, _arg63, _arg64, _arg65);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_addAccount /* 21 */:
                            IAccountManagerResponse _arg66 = IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                            String _arg67 = data.readString();
                            String _arg68 = data.readString();
                            String[] _arg69 = data.createStringArray();
                            boolean _arg70 = 0 != data.readInt();
                            Bundle _arg71 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            int _arg72 = data.readInt();
                            addAccount(_arg66, _arg67, _arg68, _arg69, _arg70, _arg71, _arg72);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_addAccountAsUser /* 22 */:
                            IAccountManagerResponse _arg73 = IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                            String _arg74 = data.readString();
                            String _arg75 = data.readString();
                            String[] _arg76 = data.createStringArray();
                            boolean _arg77 = 0 != data.readInt();
                            Bundle _arg78 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            int _arg79 = data.readInt();
                            addAccountAsUser(_arg73, _arg74, _arg75, _arg76, _arg77, _arg78, _arg79);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_updateCredentials /* 23 */:
                            IAccountManagerResponse _arg80 = IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                            Account _arg81 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg82 = data.readString();
                            boolean _arg83 = 0 != data.readInt();
                            Bundle _arg84 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            int _arg85 = data.readInt();
                            updateCredentials(_arg80, _arg81, _arg82, _arg83, _arg84, _arg85);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_editProperties /* 24 */:
                            IAccountManagerResponse _arg86 = IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                            String _arg87 = data.readString();
                            boolean _arg88 = 0 != data.readInt();
                            int _arg89 = data.readInt();
                            editProperties(_arg86, _arg87, _arg88, _arg89);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_confirmCredentialsAsUser /* 25 */:
                            IAccountManagerResponse _arg90 = IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                            Account _arg91 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            Bundle _arg92 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            boolean _arg93 = 0 != data.readInt();
                            int _arg94 = data.readInt();
                            confirmCredentialsAsUser(_arg90, _arg91, _arg92, _arg93, _arg94);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_accountAuthenticated /* 26 */:
                            Account _arg95 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            int _arg96 = data.readInt();
                            boolean _result10 = accountAuthenticated(_arg95, _arg96);
                            reply.writeNoException();
                            reply.writeInt(_result10 ? 1 : 0);
                            return true;
                        case TRANSACTION_getAuthTokenLabel /* 27 */:
                            IAccountManagerResponse _arg97 = IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                            String _arg98 = data.readString();
                            String _arg99 = data.readString();
                            int _arg100 = data.readInt();
                            getAuthTokenLabel(_arg97, _arg98, _arg99, _arg100);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_getPackagesAndVisibilityForAccount /* 28 */:
                            Account _arg101 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            int _arg102 = data.readInt();
                            Map _result11 = getPackagesAndVisibilityForAccount(_arg101, _arg102);
                            reply.writeNoException();
                            reply.writeMap(_result11);
                            return true;
                        case TRANSACTION_addAccountExplicitlyWithVisibility /* 29 */:
                            Account _arg103 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg104 = data.readString();
                            Bundle _arg105 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            ClassLoader cl = getClass().getClassLoader();
                            Map _arg106 = data.readHashMap(cl);
                            int _arg107 = data.readInt();
                            boolean _result12 = addAccountExplicitlyWithVisibility(_arg103, _arg104, _arg105, _arg106, _arg107);
                            reply.writeNoException();
                            reply.writeInt(_result12 ? 1 : 0);
                            return true;
                        case TRANSACTION_setAccountVisibility /* 30 */:
                            Account _arg108 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg109 = data.readString();
                            int _arg110 = data.readInt();
                            int _arg111 = data.readInt();
                            boolean _result13 = setAccountVisibility(_arg108, _arg109, _arg110, _arg111);
                            reply.writeNoException();
                            reply.writeInt(_result13 ? 1 : 0);
                            return true;
                        case TRANSACTION_getAccountVisibility /* 31 */:
                            Account _arg112 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg113 = data.readString();
                            int _arg114 = data.readInt();
                            int _result14 = getAccountVisibility(_arg112, _arg113, _arg114);
                            reply.writeNoException();
                            reply.writeInt(_result14);
                            return true;
                        case 32:
                            String _arg115 = data.readString();
                            String _arg116 = data.readString();
                            int _arg117 = data.readInt();
                            Map _result15 = getAccountsAndVisibilityForPackage(_arg115, _arg116, _arg117);
                            reply.writeNoException();
                            reply.writeMap(_result15);
                            return true;
                        case TRANSACTION_registerAccountListener /* 33 */:
                            String[] _arg118 = data.createStringArray();
                            String _arg119 = data.readString();
                            int _arg120 = data.readInt();
                            registerAccountListener(_arg118, _arg119, _arg120);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_unregisterAccountListener /* 34 */:
                            String[] _arg121 = data.createStringArray();
                            String _arg122 = data.readString();
                            int _arg123 = data.readInt();
                            unregisterAccountListener(_arg121, _arg122, _arg123);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/IBAccountManagerService$Stub$Proxy.class */
        private static class Proxy implements IBAccountManagerService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBAccountManagerService.DESCRIPTOR;
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public String getPassword(Account account, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public String getUserData(Account account, String key, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeString(key);
                    _data.writeInt(userId);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public AuthenticatorDescription[] getAuthenticatorTypes(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    AuthenticatorDescription[] _result = (AuthenticatorDescription[]) _reply.createTypedArray(AuthenticatorDescription.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public Account[] getAccountsForPackage(String packageName, int uid, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(uid);
                    _data.writeInt(userId);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    Account[] _result = (Account[]) _reply.createTypedArray(Account.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public Account[] getAccountsByTypeForPackage(String type, String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeString(type);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    Account[] _result = (Account[]) _reply.createTypedArray(Account.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public Account[] getAccountsAsUser(String accountType, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeString(accountType);
                    _data.writeInt(userId);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    Account[] _result = (Account[]) _reply.createTypedArray(Account.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void getAccountByTypeAndFeatures(IAccountManagerResponse response, String accountType, String[] features, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _data.writeString(accountType);
                    _data.writeStringArray(features);
                    _data.writeInt(userId);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void getAccountsByFeatures(IAccountManagerResponse response, String accountType, String[] features, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _data.writeString(accountType);
                    _data.writeStringArray(features);
                    _data.writeInt(userId);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public boolean addAccountExplicitly(Account account, String password, Bundle extras, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeString(password);
                    _Parcel.writeTypedObject(_data, extras, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_addAccountExplicitly, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = 0 != _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void removeAccountAsUser(IAccountManagerResponse response, Account account, boolean expectActivityLaunch, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeInt(expectActivityLaunch ? 1 : 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public boolean removeAccountExplicitly(Account account, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_removeAccountExplicitly, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = 0 != _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void copyAccountToUser(IAccountManagerResponse response, Account account, int userFrom, int userTo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeInt(userFrom);
                    _data.writeInt(userTo);
                    this.mRemote.transact(Stub.TRANSACTION_copyAccountToUser, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void invalidateAuthToken(String accountType, String authToken, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeString(accountType);
                    _data.writeString(authToken);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_invalidateAuthToken, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public String peekAuthToken(Account account, String authTokenType, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeString(authTokenType);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_peekAuthToken, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void setAuthToken(Account account, String authTokenType, String authToken, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeString(authTokenType);
                    _data.writeString(authToken);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_setAuthToken, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void setPassword(Account account, String password, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeString(password);
                    _data.writeInt(userId);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void clearPassword(Account account, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_clearPassword, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void setUserData(Account account, String key, String value, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeString(key);
                    _data.writeString(value);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_setUserData, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void updateAppPermission(Account account, String authTokenType, int uid, boolean value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeString(authTokenType);
                    _data.writeInt(uid);
                    _data.writeInt(value ? 1 : 0);
                    this.mRemote.transact(Stub.TRANSACTION_updateAppPermission, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void getAuthToken(IAccountManagerResponse response, Account account, String authTokenType, boolean notifyOnAuthFailure, boolean expectActivityLaunch, Bundle options, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeString(authTokenType);
                    _data.writeInt(notifyOnAuthFailure ? 1 : 0);
                    _data.writeInt(expectActivityLaunch ? 1 : 0);
                    _Parcel.writeTypedObject(_data, options, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getAuthToken, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void addAccount(IAccountManagerResponse response, String accountType, String authTokenType, String[] requiredFeatures, boolean expectActivityLaunch, Bundle options, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _data.writeString(accountType);
                    _data.writeString(authTokenType);
                    _data.writeStringArray(requiredFeatures);
                    _data.writeInt(expectActivityLaunch ? 1 : 0);
                    _Parcel.writeTypedObject(_data, options, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_addAccount, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void addAccountAsUser(IAccountManagerResponse response, String accountType, String authTokenType, String[] requiredFeatures, boolean expectActivityLaunch, Bundle options, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _data.writeString(accountType);
                    _data.writeString(authTokenType);
                    _data.writeStringArray(requiredFeatures);
                    _data.writeInt(expectActivityLaunch ? 1 : 0);
                    _Parcel.writeTypedObject(_data, options, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_addAccountAsUser, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void updateCredentials(IAccountManagerResponse response, Account account, String authTokenType, boolean expectActivityLaunch, Bundle options, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeString(authTokenType);
                    _data.writeInt(expectActivityLaunch ? 1 : 0);
                    _Parcel.writeTypedObject(_data, options, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_updateCredentials, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void editProperties(IAccountManagerResponse response, String accountType, boolean expectActivityLaunch, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _data.writeString(accountType);
                    _data.writeInt(expectActivityLaunch ? 1 : 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_editProperties, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void confirmCredentialsAsUser(IAccountManagerResponse response, Account account, Bundle options, boolean expectActivityLaunch, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _Parcel.writeTypedObject(_data, options, 0);
                    _data.writeInt(expectActivityLaunch ? 1 : 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_confirmCredentialsAsUser, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public boolean accountAuthenticated(Account account, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_accountAuthenticated, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = 0 != _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void getAuthTokenLabel(IAccountManagerResponse response, String accountType, String authTokenType, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _data.writeString(accountType);
                    _data.writeString(authTokenType);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getAuthTokenLabel, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public Map getPackagesAndVisibilityForAccount(Account account, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getPackagesAndVisibilityForAccount, _data, _reply, 0);
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    Map _result = _reply.readHashMap(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public boolean addAccountExplicitlyWithVisibility(Account account, String password, Bundle extras, Map visibility, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeString(password);
                    _Parcel.writeTypedObject(_data, extras, 0);
                    _data.writeMap(visibility);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_addAccountExplicitlyWithVisibility, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = 0 != _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public boolean setAccountVisibility(Account a, String packageName, int newVisibility, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, a, 0);
                    _data.writeString(packageName);
                    _data.writeInt(newVisibility);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_setAccountVisibility, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = 0 != _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public int getAccountVisibility(Account a, String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, a, 0);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getAccountVisibility, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public Map getAccountsAndVisibilityForPackage(String packageName, String accountType, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(accountType);
                    _data.writeInt(userId);
                    this.mRemote.transact(32, _data, _reply, 0);
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    Map _result = _reply.readHashMap(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void registerAccountListener(String[] accountTypes, String opPackageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeStringArray(accountTypes);
                    _data.writeString(opPackageName);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_registerAccountListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.accounts.IBAccountManagerService
            public void unregisterAccountListener(String[] accountTypes, String opPackageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBAccountManagerService.DESCRIPTOR);
                    _data.writeStringArray(accountTypes);
                    _data.writeString(opPackageName);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_unregisterAccountListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/accounts/IBAccountManagerService$_Parcel.class */
    public static class _Parcel {
        private static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> c) {
            if (parcel.readInt() != 0) {
                return c.createFromParcel(parcel);
            }
            return null;
        }

        private static <T extends Parcelable> void writeTypedObject(Parcel parcel, T value, int parcelableFlags) {
            if (value != null) {
                parcel.writeInt(1);
                value.writeToParcel(parcel, parcelableFlags);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
