package android.content.pm;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.jar:android/content/pm/IPackageInstallObserver2.class */
public interface IPackageInstallObserver2 extends IInterface {
    void onUserActionRequired(Intent intent) throws RemoteException;

    void onPackageInstalled(String str, int i, String str2, Bundle bundle) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:android/content/pm/IPackageInstallObserver2$Default.class */
    public static class Default implements IPackageInstallObserver2 {
        @Override // android.content.pm.IPackageInstallObserver2
        public void onUserActionRequired(Intent intent) throws RemoteException {
        }

        @Override // android.content.pm.IPackageInstallObserver2
        public void onPackageInstalled(String basePackageName, int returnCode, String msg, Bundle extras) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/IPackageInstallObserver2$Stub.class */
    public static abstract class Stub extends Binder implements IPackageInstallObserver2 {
        public static final String DESCRIPTOR = "android.content.pm.IPackageInstallObserver2";
        static final int TRANSACTION_onUserActionRequired = 1;
        static final int TRANSACTION_onPackageInstalled = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPackageInstallObserver2 asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IPackageInstallObserver2)) {
                return (IPackageInstallObserver2) iin;
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
                            Intent _arg0 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            onUserActionRequired(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            String _arg1 = data.readString();
                            int _arg2 = data.readInt();
                            String _arg3 = data.readString();
                            Bundle _arg4 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            onPackageInstalled(_arg1, _arg2, _arg3, _arg4);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:android/content/pm/IPackageInstallObserver2$Stub$Proxy.class */
        private static class Proxy implements IPackageInstallObserver2 {
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

            @Override // android.content.pm.IPackageInstallObserver2
            public void onUserActionRequired(Intent intent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstallObserver2
            public void onPackageInstalled(String basePackageName, int returnCode, String msg, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(basePackageName);
                    _data.writeInt(returnCode);
                    _data.writeString(msg);
                    _Parcel.writeTypedObject(_data, extras, 0);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/IPackageInstallObserver2$_Parcel.class */
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
