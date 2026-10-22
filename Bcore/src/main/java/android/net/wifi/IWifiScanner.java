package android.net.wifi;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.jar:android/net/wifi/IWifiScanner.class */
public interface IWifiScanner extends IInterface {
    Messenger getMessenger() throws RemoteException;

    Bundle getAvailableChannels(int i) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:android/net/wifi/IWifiScanner$Default.class */
    public static class Default implements IWifiScanner {
        @Override // android.net.wifi.IWifiScanner
        public Messenger getMessenger() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.IWifiScanner
        public Bundle getAvailableChannels(int band) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/net/wifi/IWifiScanner$Stub.class */
    public static abstract class Stub extends Binder implements IWifiScanner {
        public static final String DESCRIPTOR = "android.net.wifi.IWifiScanner";
        static final int TRANSACTION_getMessenger = 1;
        static final int TRANSACTION_getAvailableChannels = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWifiScanner asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IWifiScanner)) {
                return (IWifiScanner) iin;
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
                            Messenger _result = getMessenger();
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result, 1);
                            return true;
                        case 2:
                            int _arg0 = data.readInt();
                            Bundle _result2 = getAvailableChannels(_arg0);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result2, 1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:android/net/wifi/IWifiScanner$Stub$Proxy.class */
        private static class Proxy implements IWifiScanner {
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

            @Override // android.net.wifi.IWifiScanner
            public Messenger getMessenger() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    Messenger _result = (Messenger) _Parcel.readTypedObject(_reply, Messenger.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.IWifiScanner
            public Bundle getAvailableChannels(int band) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(band);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    Bundle _result = (Bundle) _Parcel.readTypedObject(_reply, Bundle.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/net/wifi/IWifiScanner$_Parcel.class */
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
