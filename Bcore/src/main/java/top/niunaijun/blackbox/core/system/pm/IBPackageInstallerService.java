package top.niunaijun.blackbox.core.system.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBPackageInstallerService.class */
public interface IBPackageInstallerService extends IInterface {
    public static final String DESCRIPTOR = "top.niunaijun.blackbox.core.system.pm.IBPackageInstallerService";

    int installPackageAsUser(BPackageSettings bPackageSettings, int i) throws RemoteException;

    int uninstallPackageAsUser(BPackageSettings bPackageSettings, boolean z, int i) throws RemoteException;

    int clearPackage(BPackageSettings bPackageSettings, int i) throws RemoteException;

    int updatePackage(BPackageSettings bPackageSettings) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBPackageInstallerService$Default.class */
    public static class Default implements IBPackageInstallerService {
        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageInstallerService
        public int installPackageAsUser(BPackageSettings ps, int userId) throws RemoteException {
            return 0;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageInstallerService
        public int uninstallPackageAsUser(BPackageSettings ps, boolean removeApp, int userId) throws RemoteException {
            return 0;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageInstallerService
        public int clearPackage(BPackageSettings ps, int userId) throws RemoteException {
            return 0;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageInstallerService
        public int updatePackage(BPackageSettings ps) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBPackageInstallerService$Stub.class */
    public static abstract class Stub extends Binder implements IBPackageInstallerService {
        static final int TRANSACTION_installPackageAsUser = 1;
        static final int TRANSACTION_uninstallPackageAsUser = 2;
        static final int TRANSACTION_clearPackage = 3;
        static final int TRANSACTION_updatePackage = 4;

        public Stub() {
            attachInterface(this, IBPackageInstallerService.DESCRIPTOR);
        }

        public static IBPackageInstallerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBPackageInstallerService.DESCRIPTOR);
            if (iin != null && (iin instanceof IBPackageInstallerService)) {
                return (IBPackageInstallerService) iin;
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
                data.enforceInterface(IBPackageInstallerService.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IBPackageInstallerService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            BPackageSettings _arg0 = (BPackageSettings) _Parcel.readTypedObject(data, BPackageSettings.CREATOR);
                            int _arg1 = data.readInt();
                            int _result = installPackageAsUser(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            BPackageSettings _arg2 = (BPackageSettings) _Parcel.readTypedObject(data, BPackageSettings.CREATOR);
                            boolean _arg3 = 0 != data.readInt();
                            int _arg4 = data.readInt();
                            int _result2 = uninstallPackageAsUser(_arg2, _arg3, _arg4);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 3:
                            BPackageSettings _arg5 = (BPackageSettings) _Parcel.readTypedObject(data, BPackageSettings.CREATOR);
                            int _arg6 = data.readInt();
                            int _result3 = clearPackage(_arg5, _arg6);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 4:
                            BPackageSettings _arg7 = (BPackageSettings) _Parcel.readTypedObject(data, BPackageSettings.CREATOR);
                            int _result4 = updatePackage(_arg7);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBPackageInstallerService$Stub$Proxy.class */
        private static class Proxy implements IBPackageInstallerService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBPackageInstallerService.DESCRIPTOR;
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageInstallerService
            public int installPackageAsUser(BPackageSettings ps, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageInstallerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, ps, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageInstallerService
            public int uninstallPackageAsUser(BPackageSettings ps, boolean removeApp, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageInstallerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, ps, 0);
                    _data.writeInt(removeApp ? 1 : 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageInstallerService
            public int clearPackage(BPackageSettings ps, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageInstallerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, ps, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageInstallerService
            public int updatePackage(BPackageSettings ps) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageInstallerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, ps, 0);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBPackageInstallerService$_Parcel.class */
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
