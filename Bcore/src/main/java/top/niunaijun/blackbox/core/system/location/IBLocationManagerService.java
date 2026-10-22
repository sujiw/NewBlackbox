package top.niunaijun.blackbox.core.system.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;
import top.niunaijun.blackbox.entity.location.BCell;
import top.niunaijun.blackbox.entity.location.BLocation;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/location/IBLocationManagerService.class */
public interface IBLocationManagerService extends IInterface {
    public static final String DESCRIPTOR = "top.niunaijun.blackbox.core.system.location.IBLocationManagerService";

    int getPattern(int i, String str) throws RemoteException;

    void setPattern(int i, String str, int i2) throws RemoteException;

    void setCell(int i, String str, BCell bCell) throws RemoteException;

    void setAllCell(int i, String str, List<BCell> list) throws RemoteException;

    void setNeighboringCell(int i, String str, List<BCell> list) throws RemoteException;

    List<BCell> getNeighboringCell(int i, String str) throws RemoteException;

    void setGlobalCell(BCell bCell) throws RemoteException;

    void setGlobalAllCell(List<BCell> list) throws RemoteException;

    void setGlobalNeighboringCell(List<BCell> list) throws RemoteException;

    List<BCell> getGlobalNeighboringCell() throws RemoteException;

    BCell getCell(int i, String str) throws RemoteException;

    List<BCell> getAllCell(int i, String str) throws RemoteException;

    void setLocation(int i, String str, BLocation bLocation) throws RemoteException;

    BLocation getLocation(int i, String str) throws RemoteException;

    void setGlobalLocation(BLocation bLocation) throws RemoteException;

    BLocation getGlobalLocation() throws RemoteException;

    void requestLocationUpdates(IBinder iBinder, String str, int i) throws RemoteException;

    void removeUpdates(IBinder iBinder) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/location/IBLocationManagerService$Default.class */
    public static class Default implements IBLocationManagerService {
        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public int getPattern(int userId, String pkg) throws RemoteException {
            return 0;
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public void setPattern(int userId, String pkg, int mode) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public void setCell(int userId, String pkg, BCell cell) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public void setAllCell(int userId, String pkg, List<BCell> cell) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public void setNeighboringCell(int userId, String pkg, List<BCell> cells) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public List<BCell> getNeighboringCell(int userId, String pkg) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public void setGlobalCell(BCell cell) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public void setGlobalAllCell(List<BCell> cell) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public void setGlobalNeighboringCell(List<BCell> cell) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public List<BCell> getGlobalNeighboringCell() throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public BCell getCell(int userId, String pkg) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public List<BCell> getAllCell(int userId, String pkg) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public void setLocation(int userId, String pkg, BLocation location) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public BLocation getLocation(int userId, String pkg) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public void setGlobalLocation(BLocation location) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public BLocation getGlobalLocation() throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public void requestLocationUpdates(IBinder listener, String packageName, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
        public void removeUpdates(IBinder listener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/location/IBLocationManagerService$Stub.class */
    public static abstract class Stub extends Binder implements IBLocationManagerService {
        static final int TRANSACTION_getPattern = 1;
        static final int TRANSACTION_setPattern = 2;
        static final int TRANSACTION_setCell = 3;
        static final int TRANSACTION_setAllCell = 4;
        static final int TRANSACTION_setNeighboringCell = 5;
        static final int TRANSACTION_getNeighboringCell = 6;
        static final int TRANSACTION_setGlobalCell = 7;
        static final int TRANSACTION_setGlobalAllCell = 8;
        static final int TRANSACTION_setGlobalNeighboringCell = 9;
        static final int TRANSACTION_getGlobalNeighboringCell = 10;
        static final int TRANSACTION_getCell = 11;
        static final int TRANSACTION_getAllCell = 12;
        static final int TRANSACTION_setLocation = 13;
        static final int TRANSACTION_getLocation = 14;
        static final int TRANSACTION_setGlobalLocation = 15;
        static final int TRANSACTION_getGlobalLocation = 16;
        static final int TRANSACTION_requestLocationUpdates = 17;
        static final int TRANSACTION_removeUpdates = 18;

        public Stub() {
            attachInterface(this, IBLocationManagerService.DESCRIPTOR);
        }

        public static IBLocationManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBLocationManagerService.DESCRIPTOR);
            if (iin != null && (iin instanceof IBLocationManagerService)) {
                return (IBLocationManagerService) iin;
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
                data.enforceInterface(IBLocationManagerService.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IBLocationManagerService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            int _arg0 = data.readInt();
                            String _arg1 = data.readString();
                            int _result = getPattern(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            int _arg2 = data.readInt();
                            String _arg3 = data.readString();
                            int _arg4 = data.readInt();
                            setPattern(_arg2, _arg3, _arg4);
                            reply.writeNoException();
                            return true;
                        case 3:
                            int _arg5 = data.readInt();
                            String _arg6 = data.readString();
                            BCell _arg7 = (BCell) _Parcel.readTypedObject(data, BCell.CREATOR);
                            setCell(_arg5, _arg6, _arg7);
                            reply.writeNoException();
                            return true;
                        case 4:
                            int _arg8 = data.readInt();
                            String _arg9 = data.readString();
                            List<BCell> _arg10 = data.createTypedArrayList(BCell.CREATOR);
                            setAllCell(_arg8, _arg9, _arg10);
                            reply.writeNoException();
                            return true;
                        case 5:
                            int _arg11 = data.readInt();
                            String _arg12 = data.readString();
                            List<BCell> _arg13 = data.createTypedArrayList(BCell.CREATOR);
                            setNeighboringCell(_arg11, _arg12, _arg13);
                            reply.writeNoException();
                            return true;
                        case 6:
                            int _arg14 = data.readInt();
                            String _arg15 = data.readString();
                            List<BCell> _result2 = getNeighboringCell(_arg14, _arg15);
                            reply.writeNoException();
                            _Parcel.writeTypedList(reply, _result2, 1);
                            return true;
                        case 7:
                            BCell _arg16 = (BCell) _Parcel.readTypedObject(data, BCell.CREATOR);
                            setGlobalCell(_arg16);
                            reply.writeNoException();
                            return true;
                        case 8:
                            List<BCell> _arg17 = data.createTypedArrayList(BCell.CREATOR);
                            setGlobalAllCell(_arg17);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_setGlobalNeighboringCell /* 9 */:
                            List<BCell> _arg18 = data.createTypedArrayList(BCell.CREATOR);
                            setGlobalNeighboringCell(_arg18);
                            reply.writeNoException();
                            return true;
                        case 10:
                            List<BCell> _result3 = getGlobalNeighboringCell();
                            reply.writeNoException();
                            _Parcel.writeTypedList(reply, _result3, 1);
                            return true;
                        case TRANSACTION_getCell /* 11 */:
                            int _arg19 = data.readInt();
                            String _arg20 = data.readString();
                            BCell _result4 = getCell(_arg19, _arg20);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result4, 1);
                            return true;
                        case TRANSACTION_getAllCell /* 12 */:
                            int _arg21 = data.readInt();
                            String _arg22 = data.readString();
                            List<BCell> _result5 = getAllCell(_arg21, _arg22);
                            reply.writeNoException();
                            _Parcel.writeTypedList(reply, _result5, 1);
                            return true;
                        case TRANSACTION_setLocation /* 13 */:
                            int _arg23 = data.readInt();
                            String _arg24 = data.readString();
                            BLocation _arg25 = (BLocation) _Parcel.readTypedObject(data, BLocation.CREATOR);
                            setLocation(_arg23, _arg24, _arg25);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_getLocation /* 14 */:
                            int _arg26 = data.readInt();
                            String _arg27 = data.readString();
                            BLocation _result6 = getLocation(_arg26, _arg27);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result6, 1);
                            return true;
                        case TRANSACTION_setGlobalLocation /* 15 */:
                            BLocation _arg28 = (BLocation) _Parcel.readTypedObject(data, BLocation.CREATOR);
                            setGlobalLocation(_arg28);
                            reply.writeNoException();
                            return true;
                        case 16:
                            BLocation _result7 = getGlobalLocation();
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result7, 1);
                            return true;
                        case TRANSACTION_requestLocationUpdates /* 17 */:
                            IBinder _arg29 = data.readStrongBinder();
                            String _arg30 = data.readString();
                            int _arg31 = data.readInt();
                            requestLocationUpdates(_arg29, _arg30, _arg31);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_removeUpdates /* 18 */:
                            IBinder _arg32 = data.readStrongBinder();
                            removeUpdates(_arg32);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/location/IBLocationManagerService$Stub$Proxy.class */
        private static class Proxy implements IBLocationManagerService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBLocationManagerService.DESCRIPTOR;
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public int getPattern(int userId, String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(pkg);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public void setPattern(int userId, String pkg, int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(pkg);
                    _data.writeInt(mode);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public void setCell(int userId, String pkg, BCell cell) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(pkg);
                    _Parcel.writeTypedObject(_data, cell, 0);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public void setAllCell(int userId, String pkg, List<BCell> cell) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(pkg);
                    _Parcel.writeTypedList(_data, cell, 0);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public void setNeighboringCell(int userId, String pkg, List<BCell> cells) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(pkg);
                    _Parcel.writeTypedList(_data, cells, 0);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public List<BCell> getNeighboringCell(int userId, String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(pkg);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    List<BCell> _result = _reply.createTypedArrayList(BCell.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public void setGlobalCell(BCell cell) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, cell, 0);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public void setGlobalAllCell(List<BCell> cell) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _Parcel.writeTypedList(_data, cell, 0);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public void setGlobalNeighboringCell(List<BCell> cell) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _Parcel.writeTypedList(_data, cell, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setGlobalNeighboringCell, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public List<BCell> getGlobalNeighboringCell() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                    List<BCell> _result = _reply.createTypedArrayList(BCell.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public BCell getCell(int userId, String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(pkg);
                    this.mRemote.transact(Stub.TRANSACTION_getCell, _data, _reply, 0);
                    _reply.readException();
                    BCell _result = (BCell) _Parcel.readTypedObject(_reply, BCell.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public List<BCell> getAllCell(int userId, String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(pkg);
                    this.mRemote.transact(Stub.TRANSACTION_getAllCell, _data, _reply, 0);
                    _reply.readException();
                    List<BCell> _result = _reply.createTypedArrayList(BCell.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public void setLocation(int userId, String pkg, BLocation location) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(pkg);
                    _Parcel.writeTypedObject(_data, location, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setLocation, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public BLocation getLocation(int userId, String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(pkg);
                    this.mRemote.transact(Stub.TRANSACTION_getLocation, _data, _reply, 0);
                    _reply.readException();
                    BLocation _result = (BLocation) _Parcel.readTypedObject(_reply, BLocation.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public void setGlobalLocation(BLocation location) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, location, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setGlobalLocation, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public BLocation getGlobalLocation() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                    BLocation _result = (BLocation) _Parcel.readTypedObject(_reply, BLocation.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public void requestLocationUpdates(IBinder listener, String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _data.writeStrongBinder(listener);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_requestLocationUpdates, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
            public void removeUpdates(IBinder listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBLocationManagerService.DESCRIPTOR);
                    _data.writeStrongBinder(listener);
                    this.mRemote.transact(Stub.TRANSACTION_removeUpdates, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/location/IBLocationManagerService$_Parcel.class */
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
