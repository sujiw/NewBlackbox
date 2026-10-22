package top.niunaijun.blackbox.core.system.os;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.storage.StorageVolume;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/os/IBStorageManagerService.class */
public interface IBStorageManagerService extends IInterface {
    public static final String DESCRIPTOR = "top.niunaijun.blackbox.core.system.os.IBStorageManagerService";

    StorageVolume[] getVolumeList(int i, String str, int i2, int i3) throws RemoteException;

    Uri getUriForFile(String str) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/os/IBStorageManagerService$Default.class */
    public static class Default implements IBStorageManagerService {
        @Override // top.niunaijun.blackbox.core.system.os.IBStorageManagerService
        public StorageVolume[] getVolumeList(int uid, String packageName, int flags, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.os.IBStorageManagerService
        public Uri getUriForFile(String file) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/os/IBStorageManagerService$Stub.class */
    public static abstract class Stub extends Binder implements IBStorageManagerService {
        static final int TRANSACTION_getVolumeList = 1;
        static final int TRANSACTION_getUriForFile = 2;

        public Stub() {
            attachInterface(this, IBStorageManagerService.DESCRIPTOR);
        }

        public static IBStorageManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBStorageManagerService.DESCRIPTOR);
            if (iin != null && (iin instanceof IBStorageManagerService)) {
                return (IBStorageManagerService) iin;
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
                data.enforceInterface(IBStorageManagerService.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IBStorageManagerService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            int _arg0 = data.readInt();
                            String _arg1 = data.readString();
                            int _arg2 = data.readInt();
                            int _arg3 = data.readInt();
                            StorageVolume[] _result = getVolumeList(_arg0, _arg1, _arg2, _arg3);
                            reply.writeNoException();
                            reply.writeTypedArray(_result, 1);
                            return true;
                        case 2:
                            String _arg4 = data.readString();
                            Uri _result2 = getUriForFile(_arg4);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result2, 1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/os/IBStorageManagerService$Stub$Proxy.class */
        private static class Proxy implements IBStorageManagerService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBStorageManagerService.DESCRIPTOR;
            }

            @Override // top.niunaijun.blackbox.core.system.os.IBStorageManagerService
            public StorageVolume[] getVolumeList(int uid, String packageName, int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBStorageManagerService.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeString(packageName);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    StorageVolume[] _result = (StorageVolume[]) _reply.createTypedArray(StorageVolume.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.os.IBStorageManagerService
            public Uri getUriForFile(String file) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBStorageManagerService.DESCRIPTOR);
                    _data.writeString(file);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    Uri _result = (Uri) _Parcel.readTypedObject(_reply, Uri.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/os/IBStorageManagerService$_Parcel.class */
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
