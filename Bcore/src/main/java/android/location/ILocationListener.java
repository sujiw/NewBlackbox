package android.location;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.jar:android/location/ILocationListener.class */
public interface ILocationListener extends IInterface {
    void onLocationChanged(Location location) throws RemoteException;

    void onStatusChanged(String str, int i, Bundle bundle) throws RemoteException;

    void onProviderEnabled(String str) throws RemoteException;

    void onProviderDisabled(String str) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:android/location/ILocationListener$Default.class */
    public static class Default implements ILocationListener {
        @Override // android.location.ILocationListener
        public void onLocationChanged(Location location) throws RemoteException {
        }

        @Override // android.location.ILocationListener
        public void onStatusChanged(String provider, int status, Bundle extras) throws RemoteException {
        }

        @Override // android.location.ILocationListener
        public void onProviderEnabled(String provider) throws RemoteException {
        }

        @Override // android.location.ILocationListener
        public void onProviderDisabled(String provider) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/location/ILocationListener$Stub.class */
    public static abstract class Stub extends Binder implements ILocationListener {
        public static final String DESCRIPTOR = "android.location.ILocationListener";
        static final int TRANSACTION_onLocationChanged = 1;
        static final int TRANSACTION_onStatusChanged = 2;
        static final int TRANSACTION_onProviderEnabled = 3;
        static final int TRANSACTION_onProviderDisabled = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocationListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ILocationListener)) {
                return (ILocationListener) iin;
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
                            Location _arg0 = (Location) _Parcel.readTypedObject(data, Location.CREATOR);
                            onLocationChanged(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            String _arg1 = data.readString();
                            int _arg2 = data.readInt();
                            Bundle _arg3 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            onStatusChanged(_arg1, _arg2, _arg3);
                            reply.writeNoException();
                            return true;
                        case 3:
                            String _arg4 = data.readString();
                            onProviderEnabled(_arg4);
                            reply.writeNoException();
                            return true;
                        case 4:
                            String _arg5 = data.readString();
                            onProviderDisabled(_arg5);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:android/location/ILocationListener$Stub$Proxy.class */
        private static class Proxy implements ILocationListener {
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

            @Override // android.location.ILocationListener
            public void onLocationChanged(Location location) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, location, 0);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationListener
            public void onStatusChanged(String provider, int status, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    _data.writeInt(status);
                    _Parcel.writeTypedObject(_data, extras, 0);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationListener
            public void onProviderEnabled(String provider) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationListener
            public void onProviderDisabled(String provider) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/location/ILocationListener$_Parcel.class */
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
