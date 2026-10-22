package top.niunaijun.blackbox.core.system.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;
import top.niunaijun.blackbox.entity.pm.InstalledModule;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBXposedManagerService.class */
public interface IBXposedManagerService extends IInterface {
    public static final String DESCRIPTOR = "top.niunaijun.blackbox.core.system.pm.IBXposedManagerService";

    boolean isXPEnable() throws RemoteException;

    void setXPEnable(boolean z) throws RemoteException;

    boolean isModuleEnable(String str) throws RemoteException;

    void setModuleEnable(String str, boolean z) throws RemoteException;

    List<InstalledModule> getInstalledModules() throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBXposedManagerService$Default.class */
    public static class Default implements IBXposedManagerService {
        @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
        public boolean isXPEnable() throws RemoteException {
            return false;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
        public void setXPEnable(boolean enable) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
        public boolean isModuleEnable(String packageName) throws RemoteException {
            return false;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
        public void setModuleEnable(String packageName, boolean enable) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
        public List<InstalledModule> getInstalledModules() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBXposedManagerService$Stub.class */
    public static abstract class Stub extends Binder implements IBXposedManagerService {
        static final int TRANSACTION_isXPEnable = 1;
        static final int TRANSACTION_setXPEnable = 2;
        static final int TRANSACTION_isModuleEnable = 3;
        static final int TRANSACTION_setModuleEnable = 4;
        static final int TRANSACTION_getInstalledModules = 5;

        public Stub() {
            attachInterface(this, IBXposedManagerService.DESCRIPTOR);
        }

        public static IBXposedManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBXposedManagerService.DESCRIPTOR);
            if (iin != null && (iin instanceof IBXposedManagerService)) {
                return (IBXposedManagerService) iin;
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
                data.enforceInterface(IBXposedManagerService.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IBXposedManagerService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            boolean _result = isXPEnable();
                            reply.writeNoException();
                            reply.writeInt(_result ? 1 : 0);
                            return true;
                        case 2:
                            boolean _arg0 = 0 != data.readInt();
                            setXPEnable(_arg0);
                            reply.writeNoException();
                            return true;
                        case 3:
                            String _arg1 = data.readString();
                            boolean _result2 = isModuleEnable(_arg1);
                            reply.writeNoException();
                            reply.writeInt(_result2 ? 1 : 0);
                            return true;
                        case 4:
                            String _arg2 = data.readString();
                            boolean _arg3 = 0 != data.readInt();
                            setModuleEnable(_arg2, _arg3);
                            reply.writeNoException();
                            return true;
                        case 5:
                            List<InstalledModule> _result3 = getInstalledModules();
                            reply.writeNoException();
                            _Parcel.writeTypedList(reply, _result3, 1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBXposedManagerService$Stub$Proxy.class */
        private static class Proxy implements IBXposedManagerService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBXposedManagerService.DESCRIPTOR;
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
            public boolean isXPEnable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBXposedManagerService.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = 0 != _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
            public void setXPEnable(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBXposedManagerService.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
            public boolean isModuleEnable(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBXposedManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = 0 != _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
            public void setModuleEnable(String packageName, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBXposedManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(enable ? 1 : 0);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBXposedManagerService
            public List<InstalledModule> getInstalledModules() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBXposedManagerService.DESCRIPTOR);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    List<InstalledModule> _result = _reply.createTypedArrayList(InstalledModule.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBXposedManagerService$_Parcel.class */
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

        private static <T extends Parcelable> void writeTypedList(Parcel parcel, List<T> value, int parcelableFlags) {
            if (value == null) {
                parcel.writeInt(-1);
                return;
            }
            int N = value.size();
            parcel.writeInt(N);
            for (int i = 0; i < N; i++) {
                writeTypedObject(parcel, value.get(i), parcelableFlags);
            }
        }
    }
}
