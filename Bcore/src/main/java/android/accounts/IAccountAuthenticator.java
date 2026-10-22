package android.accounts;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.jar:android/accounts/IAccountAuthenticator.class */
public interface IAccountAuthenticator extends IInterface {
    void addAccount(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, String str, String str2, String[] strArr, Bundle bundle) throws RemoteException;

    void confirmCredentials(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, Bundle bundle) throws RemoteException;

    void getAuthToken(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, String str, Bundle bundle) throws RemoteException;

    void getAuthTokenLabel(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, String str) throws RemoteException;

    void updateCredentials(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, String str, Bundle bundle) throws RemoteException;

    void editProperties(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, String str) throws RemoteException;

    void hasFeatures(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, String[] strArr) throws RemoteException;

    void getAccountRemovalAllowed(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account) throws RemoteException;

    void getAccountCredentialsForCloning(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account) throws RemoteException;

    void addAccountFromCredentials(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, Bundle bundle) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:android/accounts/IAccountAuthenticator$Default.class */
    public static class Default implements IAccountAuthenticator {
        @Override // android.accounts.IAccountAuthenticator
        public void addAccount(IAccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws RemoteException {
        }

        @Override // android.accounts.IAccountAuthenticator
        public void confirmCredentials(IAccountAuthenticatorResponse response, Account account, Bundle options) throws RemoteException {
        }

        @Override // android.accounts.IAccountAuthenticator
        public void getAuthToken(IAccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws RemoteException {
        }

        @Override // android.accounts.IAccountAuthenticator
        public void getAuthTokenLabel(IAccountAuthenticatorResponse response, String authTokenType) throws RemoteException {
        }

        @Override // android.accounts.IAccountAuthenticator
        public void updateCredentials(IAccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws RemoteException {
        }

        @Override // android.accounts.IAccountAuthenticator
        public void editProperties(IAccountAuthenticatorResponse response, String accountType) throws RemoteException {
        }

        @Override // android.accounts.IAccountAuthenticator
        public void hasFeatures(IAccountAuthenticatorResponse response, Account account, String[] features) throws RemoteException {
        }

        @Override // android.accounts.IAccountAuthenticator
        public void getAccountRemovalAllowed(IAccountAuthenticatorResponse response, Account account) throws RemoteException {
        }

        @Override // android.accounts.IAccountAuthenticator
        public void getAccountCredentialsForCloning(IAccountAuthenticatorResponse response, Account account) throws RemoteException {
        }

        @Override // android.accounts.IAccountAuthenticator
        public void addAccountFromCredentials(IAccountAuthenticatorResponse response, Account account, Bundle accountCredentials) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/accounts/IAccountAuthenticator$Stub.class */
    public static abstract class Stub extends Binder implements IAccountAuthenticator {
        public static final String DESCRIPTOR = "android.accounts.IAccountAuthenticator";
        static final int TRANSACTION_addAccount = 1;
        static final int TRANSACTION_confirmCredentials = 2;
        static final int TRANSACTION_getAuthToken = 3;
        static final int TRANSACTION_getAuthTokenLabel = 4;
        static final int TRANSACTION_updateCredentials = 5;
        static final int TRANSACTION_editProperties = 6;
        static final int TRANSACTION_hasFeatures = 7;
        static final int TRANSACTION_getAccountRemovalAllowed = 8;
        static final int TRANSACTION_getAccountCredentialsForCloning = 9;
        static final int TRANSACTION_addAccountFromCredentials = 10;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAccountAuthenticator asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAccountAuthenticator)) {
                return (IAccountAuthenticator) iin;
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
                data.enforceInterface(DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            IAccountAuthenticatorResponse _arg0 = IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                            String _arg1 = data.readString();
                            String _arg2 = data.readString();
                            String[] _arg3 = data.createStringArray();
                            Bundle _arg4 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            addAccount(_arg0, _arg1, _arg2, _arg3, _arg4);
                            reply.writeNoException();
                            return true;
                        case 2:
                            IAccountAuthenticatorResponse _arg5 = IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                            Account _arg6 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            Bundle _arg7 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            confirmCredentials(_arg5, _arg6, _arg7);
                            reply.writeNoException();
                            return true;
                        case 3:
                            IAccountAuthenticatorResponse _arg8 = IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                            Account _arg9 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg10 = data.readString();
                            Bundle _arg11 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            getAuthToken(_arg8, _arg9, _arg10, _arg11);
                            reply.writeNoException();
                            return true;
                        case 4:
                            IAccountAuthenticatorResponse _arg12 = IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                            String _arg13 = data.readString();
                            getAuthTokenLabel(_arg12, _arg13);
                            reply.writeNoException();
                            return true;
                        case 5:
                            IAccountAuthenticatorResponse _arg14 = IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                            Account _arg15 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg16 = data.readString();
                            Bundle _arg17 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            updateCredentials(_arg14, _arg15, _arg16, _arg17);
                            reply.writeNoException();
                            return true;
                        case 6:
                            IAccountAuthenticatorResponse _arg18 = IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                            String _arg19 = data.readString();
                            editProperties(_arg18, _arg19);
                            reply.writeNoException();
                            return true;
                        case 7:
                            IAccountAuthenticatorResponse _arg20 = IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                            Account _arg21 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String[] _arg22 = data.createStringArray();
                            hasFeatures(_arg20, _arg21, _arg22);
                            reply.writeNoException();
                            return true;
                        case 8:
                            IAccountAuthenticatorResponse _arg23 = IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                            Account _arg24 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            getAccountRemovalAllowed(_arg23, _arg24);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_getAccountCredentialsForCloning /* 9 */:
                            IAccountAuthenticatorResponse _arg25 = IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                            Account _arg26 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            getAccountCredentialsForCloning(_arg25, _arg26);
                            reply.writeNoException();
                            return true;
                        case 10:
                            IAccountAuthenticatorResponse _arg27 = IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                            Account _arg28 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            Bundle _arg29 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            addAccountFromCredentials(_arg27, _arg28, _arg29);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:android/accounts/IAccountAuthenticator$Stub$Proxy.class */
        private static class Proxy implements IAccountAuthenticator {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.accounts.IAccountAuthenticator
            public void addAccount(IAccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _data.writeString(accountType);
                    _data.writeString(authTokenType);
                    _data.writeStringArray(requiredFeatures);
                    _Parcel.writeTypedObject(_data, options, 0);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.accounts.IAccountAuthenticator
            public void confirmCredentials(IAccountAuthenticatorResponse response, Account account, Bundle options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _Parcel.writeTypedObject(_data, options, 0);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.accounts.IAccountAuthenticator
            public void getAuthToken(IAccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeString(authTokenType);
                    _Parcel.writeTypedObject(_data, options, 0);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.accounts.IAccountAuthenticator
            public void getAuthTokenLabel(IAccountAuthenticatorResponse response, String authTokenType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _data.writeString(authTokenType);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.accounts.IAccountAuthenticator
            public void updateCredentials(IAccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeString(authTokenType);
                    _Parcel.writeTypedObject(_data, options, 0);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.accounts.IAccountAuthenticator
            public void editProperties(IAccountAuthenticatorResponse response, String accountType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _data.writeString(accountType);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.accounts.IAccountAuthenticator
            public void hasFeatures(IAccountAuthenticatorResponse response, Account account, String[] features) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeStringArray(features);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.accounts.IAccountAuthenticator
            public void getAccountRemovalAllowed(IAccountAuthenticatorResponse response, Account account) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _Parcel.writeTypedObject(_data, account, 0);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.accounts.IAccountAuthenticator
            public void getAccountCredentialsForCloning(IAccountAuthenticatorResponse response, Account account) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _Parcel.writeTypedObject(_data, account, 0);
                    this.mRemote.transact(Stub.TRANSACTION_getAccountCredentialsForCloning, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.accounts.IAccountAuthenticator
            public void addAccountFromCredentials(IAccountAuthenticatorResponse response, Account account, Bundle accountCredentials) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongInterface(response);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _Parcel.writeTypedObject(_data, accountCredentials, 0);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/accounts/IAccountAuthenticator$_Parcel.class */
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
