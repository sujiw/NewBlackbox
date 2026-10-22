package android.app.job;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.jar:android/app/job/IJobCallback.class */
public interface IJobCallback extends IInterface {
    void acknowledgeStartMessage(int i, boolean z) throws RemoteException;

    void acknowledgeStopMessage(int i, boolean z) throws RemoteException;

    JobWorkItem dequeueWork(int i) throws RemoteException;

    boolean completeWork(int i, int i2) throws RemoteException;

    void jobFinished(int i, boolean z) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:android/app/job/IJobCallback$Default.class */
    public static class Default implements IJobCallback {
        @Override // android.app.job.IJobCallback
        public void acknowledgeStartMessage(int jobId, boolean ongoing) throws RemoteException {
        }

        @Override // android.app.job.IJobCallback
        public void acknowledgeStopMessage(int jobId, boolean reschedule) throws RemoteException {
        }

        @Override // android.app.job.IJobCallback
        public JobWorkItem dequeueWork(int jobId) throws RemoteException {
            return null;
        }

        @Override // android.app.job.IJobCallback
        public boolean completeWork(int jobId, int workId) throws RemoteException {
            return false;
        }

        @Override // android.app.job.IJobCallback
        public void jobFinished(int jobId, boolean reschedule) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/app/job/IJobCallback$Stub.class */
    public static abstract class Stub extends Binder implements IJobCallback {
        public static final String DESCRIPTOR = "android.app.job.IJobCallback";
        static final int TRANSACTION_acknowledgeStartMessage = 1;
        static final int TRANSACTION_acknowledgeStopMessage = 2;
        static final int TRANSACTION_dequeueWork = 3;
        static final int TRANSACTION_completeWork = 4;
        static final int TRANSACTION_jobFinished = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IJobCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IJobCallback)) {
                return (IJobCallback) iin;
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
                            int _arg0 = data.readInt();
                            boolean _arg1 = 0 != data.readInt();
                            acknowledgeStartMessage(_arg0, _arg1);
                            reply.writeNoException();
                            return true;
                        case 2:
                            int _arg2 = data.readInt();
                            boolean _arg3 = 0 != data.readInt();
                            acknowledgeStopMessage(_arg2, _arg3);
                            reply.writeNoException();
                            return true;
                        case 3:
                            int _arg4 = data.readInt();
                            JobWorkItem _result = dequeueWork(_arg4);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result, 1);
                            return true;
                        case 4:
                            int _arg5 = data.readInt();
                            int _arg6 = data.readInt();
                            boolean _result2 = completeWork(_arg5, _arg6);
                            reply.writeNoException();
                            reply.writeInt(_result2 ? 1 : 0);
                            return true;
                        case 5:
                            int _arg7 = data.readInt();
                            boolean _arg8 = 0 != data.readInt();
                            jobFinished(_arg7, _arg8);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:android/app/job/IJobCallback$Stub$Proxy.class */
        private static class Proxy implements IJobCallback {
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

            @Override // android.app.job.IJobCallback
            public void acknowledgeStartMessage(int jobId, boolean ongoing) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(jobId);
                    _data.writeInt(ongoing ? 1 : 0);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.job.IJobCallback
            public void acknowledgeStopMessage(int jobId, boolean reschedule) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(jobId);
                    _data.writeInt(reschedule ? 1 : 0);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.job.IJobCallback
            public JobWorkItem dequeueWork(int jobId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(jobId);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    JobWorkItem _result = (JobWorkItem) _Parcel.readTypedObject(_reply, JobWorkItem.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.job.IJobCallback
            public boolean completeWork(int jobId, int workId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(jobId);
                    _data.writeInt(workId);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = 0 != _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.job.IJobCallback
            public void jobFinished(int jobId, boolean reschedule) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(jobId);
                    _data.writeInt(reschedule ? 1 : 0);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/app/job/IJobCallback$_Parcel.class */
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
