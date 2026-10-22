package android.accounts;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.jar:android/accounts/IAccountAuthenticatorResponse.class */
public interface IAccountAuthenticatorResponse extends IInterface {
    void onResult(Bundle bundle) throws RemoteException;

    void onRequestContinued() throws RemoteException;

    void onError(int i, String str) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:android/accounts/IAccountAuthenticatorResponse$Default.class */
    public static class Default implements IAccountAuthenticatorResponse {
        @Override // android.accounts.IAccountAuthenticatorResponse
        public void onResult(Bundle value) throws RemoteException {
        }

        @Override // android.accounts.IAccountAuthenticatorResponse
        public void onRequestContinued() throws RemoteException {
        }

        @Override // android.accounts.IAccountAuthenticatorResponse
        public void onError(int errorCode, String errorMessage) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/accounts/IAccountAuthenticatorResponse$Stub.class */
    public static abstract class Stub extends Binder implements IAccountAuthenticatorResponse {
        public static final String DESCRIPTOR = "android.accounts.IAccountAuthenticatorResponse";
        static final int TRANSACTION_onResult = 1;
        static final int TRANSACTION_onRequestContinued = 2;
        static final int TRANSACTION_onError = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAccountAuthenticatorResponse asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAccountAuthenticatorResponse)) {
                return (IAccountAuthenticatorResponse) iin;
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
                            Bundle _arg0 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            onResult(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            onRequestContinued();
                            reply.writeNoException();
                            return true;
                        case 3:
                            int _arg1 = data.readInt();
                            String _arg2 = data.readString();
                            onError(_arg1, _arg2);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:android/accounts/IAccountAuthenticatorResponse$Stub$Proxy.class */
        private static class Proxy implements IAccountAuthenticatorResponse {
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

            @Override // android.accounts.IAccountAuthenticatorResponse
            public void onResult(Bundle value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, value, 0);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.accounts.IAccountAuthenticatorResponse
            public void onRequestContinued() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.accounts.IAccountAuthenticatorResponse
            public void onError(int errorCode, String errorMessage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(errorCode);
                    _data.writeString(errorMessage);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/accounts/IAccountAuthenticatorResponse$_Parcel.class */
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
