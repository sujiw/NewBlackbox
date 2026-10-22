package top.niunaijun.blackbox.core;

import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import top.niunaijun.blackbox.entity.am.ReceiverData;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/IBActivityThread.class */
public interface IBActivityThread extends IInterface {
    public static final String DESCRIPTOR = "top.niunaijun.blackbox.core.IBActivityThread";

    IBinder getActivityThread() throws RemoteException;

    void bindApplication() throws RemoteException;

    void restartJobService(String str) throws RemoteException;

    IBinder acquireContentProviderClient(ProviderInfo providerInfo) throws RemoteException;

    IBinder peekService(Intent intent) throws RemoteException;

    void stopService(Intent intent) throws RemoteException;

    void finishActivity(IBinder iBinder) throws RemoteException;

    void handleNewIntent(IBinder iBinder, Intent intent) throws RemoteException;

    void scheduleReceiver(ReceiverData receiverData) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/IBActivityThread$Default.class */
    public static class Default implements IBActivityThread {
        @Override // top.niunaijun.blackbox.core.IBActivityThread
        public IBinder getActivityThread() throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.IBActivityThread
        public void bindApplication() throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.IBActivityThread
        public void restartJobService(String selfId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.IBActivityThread
        public IBinder acquireContentProviderClient(ProviderInfo providerInfo) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.IBActivityThread
        public IBinder peekService(Intent intent) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.IBActivityThread
        public void stopService(Intent componentName) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.IBActivityThread
        public void finishActivity(IBinder token) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.IBActivityThread
        public void handleNewIntent(IBinder token, Intent intent) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.IBActivityThread
        public void scheduleReceiver(ReceiverData data) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/IBActivityThread$Stub.class */
    public static abstract class Stub extends Binder implements IBActivityThread {
        static final int TRANSACTION_getActivityThread = 1;
        static final int TRANSACTION_bindApplication = 2;
        static final int TRANSACTION_restartJobService = 3;
        static final int TRANSACTION_acquireContentProviderClient = 4;
        static final int TRANSACTION_peekService = 5;
        static final int TRANSACTION_stopService = 6;
        static final int TRANSACTION_finishActivity = 7;
        static final int TRANSACTION_handleNewIntent = 8;
        static final int TRANSACTION_scheduleReceiver = 9;

        public Stub() {
            attachInterface(this, IBActivityThread.DESCRIPTOR);
        }

        public static IBActivityThread asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBActivityThread.DESCRIPTOR);
            if (iin != null && (iin instanceof IBActivityThread)) {
                return (IBActivityThread) iin;
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
                data.enforceInterface(IBActivityThread.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IBActivityThread.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            IBinder _result = getActivityThread();
                            reply.writeNoException();
                            reply.writeStrongBinder(_result);
                            return true;
                        case 2:
                            bindApplication();
                            reply.writeNoException();
                            return true;
                        case 3:
                            String _arg0 = data.readString();
                            restartJobService(_arg0);
                            reply.writeNoException();
                            return true;
                        case 4:
                            ProviderInfo _arg1 = (ProviderInfo) _Parcel.readTypedObject(data, ProviderInfo.CREATOR);
                            IBinder _result2 = acquireContentProviderClient(_arg1);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result2);
                            return true;
                        case 5:
                            Intent _arg2 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            IBinder _result3 = peekService(_arg2);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result3);
                            return true;
                        case 6:
                            Intent _arg3 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            stopService(_arg3);
                            reply.writeNoException();
                            return true;
                        case 7:
                            IBinder _arg4 = data.readStrongBinder();
                            finishActivity(_arg4);
                            reply.writeNoException();
                            return true;
                        case 8:
                            IBinder _arg5 = data.readStrongBinder();
                            Intent _arg6 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            handleNewIntent(_arg5, _arg6);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_scheduleReceiver /* 9 */:
                            ReceiverData _arg7 = (ReceiverData) _Parcel.readTypedObject(data, ReceiverData.CREATOR);
                            scheduleReceiver(_arg7);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/IBActivityThread$Stub$Proxy.class */
        private static class Proxy implements IBActivityThread {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBActivityThread.DESCRIPTOR;
            }

            @Override // top.niunaijun.blackbox.core.IBActivityThread
            public IBinder getActivityThread() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityThread.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    IBinder _result = _reply.readStrongBinder();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.IBActivityThread
            public void bindApplication() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityThread.DESCRIPTOR);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.IBActivityThread
            public void restartJobService(String selfId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityThread.DESCRIPTOR);
                    _data.writeString(selfId);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.IBActivityThread
            public IBinder acquireContentProviderClient(ProviderInfo providerInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityThread.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, providerInfo, 0);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    IBinder _result = _reply.readStrongBinder();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.IBActivityThread
            public IBinder peekService(Intent intent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityThread.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    IBinder _result = _reply.readStrongBinder();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.IBActivityThread
            public void stopService(Intent componentName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityThread.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, componentName, 0);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.IBActivityThread
            public void finishActivity(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityThread.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.IBActivityThread
            public void handleNewIntent(IBinder token, Intent intent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityThread.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.IBActivityThread
            public void scheduleReceiver(ReceiverData data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityThread.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, data, 0);
                    this.mRemote.transact(Stub.TRANSACTION_scheduleReceiver, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/IBActivityThread$_Parcel.class */
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
