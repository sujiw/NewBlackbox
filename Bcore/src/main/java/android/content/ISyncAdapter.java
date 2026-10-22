package android.content;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.jar:android/content/ISyncAdapter.class */
public interface ISyncAdapter extends IInterface {
    void startSync(ISyncContext iSyncContext, String str, Account account, Bundle bundle) throws RemoteException;

    void cancelSync(ISyncContext iSyncContext) throws RemoteException;

    void initialize(Account account, String str) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:android/content/ISyncAdapter$Default.class */
    public static class Default implements ISyncAdapter {
        @Override // android.content.ISyncAdapter
        public void startSync(ISyncContext syncContext, String authority, Account account, Bundle extras) throws RemoteException {
        }

        @Override // android.content.ISyncAdapter
        public void cancelSync(ISyncContext syncContext) throws RemoteException {
        }

        @Override // android.content.ISyncAdapter
        public void initialize(Account account, String authority) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/ISyncAdapter$Stub.class */
    public static abstract class Stub extends Binder implements ISyncAdapter {
        public static final String DESCRIPTOR = "android.content.ISyncAdapter";
        static final int TRANSACTION_startSync = 1;
        static final int TRANSACTION_cancelSync = 2;
        static final int TRANSACTION_initialize = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISyncAdapter asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ISyncAdapter)) {
                return (ISyncAdapter) iin;
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
                            ISyncContext _arg0 = ISyncContext.Stub.asInterface(data.readStrongBinder());
                            String _arg1 = data.readString();
                            Account _arg2 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            Bundle _arg3 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            startSync(_arg0, _arg1, _arg2, _arg3);
                            reply.writeNoException();
                            return true;
                        case 2:
                            ISyncContext _arg4 = ISyncContext.Stub.asInterface(data.readStrongBinder());
                            cancelSync(_arg4);
                            reply.writeNoException();
                            return true;
                        case 3:
                            Account _arg5 = (Account) _Parcel.readTypedObject(data, Account.CREATOR);
                            String _arg6 = data.readString();
                            initialize(_arg5, _arg6);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:android/content/ISyncAdapter$Stub$Proxy.class */
        private static class Proxy implements ISyncAdapter {
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

            @Override // android.content.ISyncAdapter
            public void startSync(ISyncContext syncContext, String authority, Account account, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongInterface(syncContext);
                    _data.writeString(authority);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _Parcel.writeTypedObject(_data, extras, 0);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.ISyncAdapter
            public void cancelSync(ISyncContext syncContext) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongInterface(syncContext);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.ISyncAdapter
            public void initialize(Account account, String authority) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, account, 0);
                    _data.writeString(authority);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/ISyncAdapter$_Parcel.class */
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
