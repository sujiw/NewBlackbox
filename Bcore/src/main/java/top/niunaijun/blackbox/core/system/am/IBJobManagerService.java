package top.niunaijun.blackbox.core.system.am;

import android.app.job.JobInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import top.niunaijun.blackbox.entity.JobRecord;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/IBJobManagerService.class */
public interface IBJobManagerService extends IInterface {
    public static final String DESCRIPTOR = "top.niunaijun.blackbox.core.system.am.IBJobManagerService";

    JobInfo schedule(JobInfo jobInfo, int i) throws RemoteException;

    JobRecord queryJobRecord(String str, int i, int i2) throws RemoteException;

    void cancelAll(String str, int i) throws RemoteException;

    int cancel(String str, int i, int i2) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/IBJobManagerService$Default.class */
    public static class Default implements IBJobManagerService {
        @Override // top.niunaijun.blackbox.core.system.am.IBJobManagerService
        public JobInfo schedule(JobInfo info, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBJobManagerService
        public JobRecord queryJobRecord(String processName, int jobId, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBJobManagerService
        public void cancelAll(String processName, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBJobManagerService
        public int cancel(String processName, int jobId, int userId) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/IBJobManagerService$Stub.class */
    public static abstract class Stub extends Binder implements IBJobManagerService {
        static final int TRANSACTION_schedule = 1;
        static final int TRANSACTION_queryJobRecord = 2;
        static final int TRANSACTION_cancelAll = 3;
        static final int TRANSACTION_cancel = 4;

        public Stub() {
            attachInterface(this, IBJobManagerService.DESCRIPTOR);
        }

        public static IBJobManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBJobManagerService.DESCRIPTOR);
            if (iin != null && (iin instanceof IBJobManagerService)) {
                return (IBJobManagerService) iin;
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
                data.enforceInterface(IBJobManagerService.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IBJobManagerService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            JobInfo _arg0 = (JobInfo) _Parcel.readTypedObject(data, JobInfo.CREATOR);
                            int _arg1 = data.readInt();
                            JobInfo _result = schedule(_arg0, _arg1);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result, 1);
                            return true;
                        case 2:
                            String _arg2 = data.readString();
                            int _arg3 = data.readInt();
                            int _arg4 = data.readInt();
                            JobRecord _result2 = queryJobRecord(_arg2, _arg3, _arg4);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result2, 1);
                            return true;
                        case 3:
                            String _arg5 = data.readString();
                            int _arg6 = data.readInt();
                            cancelAll(_arg5, _arg6);
                            reply.writeNoException();
                            return true;
                        case 4:
                            String _arg7 = data.readString();
                            int _arg8 = data.readInt();
                            int _arg9 = data.readInt();
                            int _result3 = cancel(_arg7, _arg8, _arg9);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/IBJobManagerService$Stub$Proxy.class */
        private static class Proxy implements IBJobManagerService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBJobManagerService.DESCRIPTOR;
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBJobManagerService
            public JobInfo schedule(JobInfo info, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBJobManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, info, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    JobInfo _result = (JobInfo) _Parcel.readTypedObject(_reply, JobInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBJobManagerService
            public JobRecord queryJobRecord(String processName, int jobId, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBJobManagerService.DESCRIPTOR);
                    _data.writeString(processName);
                    _data.writeInt(jobId);
                    _data.writeInt(userId);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    JobRecord _result = (JobRecord) _Parcel.readTypedObject(_reply, JobRecord.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBJobManagerService
            public void cancelAll(String processName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBJobManagerService.DESCRIPTOR);
                    _data.writeString(processName);
                    _data.writeInt(userId);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBJobManagerService
            public int cancel(String processName, int jobId, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBJobManagerService.DESCRIPTOR);
                    _data.writeString(processName);
                    _data.writeInt(jobId);
                    _data.writeInt(userId);
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

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/IBJobManagerService$_Parcel.class */
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
