package top.niunaijun.blackbox.core.system.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/notification/IBNotificationManagerService.class */
public interface IBNotificationManagerService extends IInterface {
    public static final String DESCRIPTOR = "top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService";

    NotificationChannel getNotificationChannel(String str, int i) throws RemoteException;

    List<NotificationChannel> getNotificationChannels(String str, int i) throws RemoteException;

    List<NotificationChannelGroup> getNotificationChannelGroups(String str, int i) throws RemoteException;

    void createNotificationChannel(NotificationChannel notificationChannel, int i) throws RemoteException;

    void deleteNotificationChannel(String str, int i) throws RemoteException;

    void createNotificationChannelGroup(NotificationChannelGroup notificationChannelGroup, int i) throws RemoteException;

    void deleteNotificationChannelGroup(String str, int i) throws RemoteException;

    void enqueueNotificationWithTag(int i, String str, Notification notification, int i2) throws RemoteException;

    void cancelNotificationWithTag(int i, String str, int i2) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/notification/IBNotificationManagerService$Default.class */
    public static class Default implements IBNotificationManagerService {
        @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
        public NotificationChannel getNotificationChannel(String channelId, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
        public List<NotificationChannel> getNotificationChannels(String packageName, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
        public List<NotificationChannelGroup> getNotificationChannelGroups(String packageName, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
        public void createNotificationChannel(NotificationChannel notificationChannel, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
        public void deleteNotificationChannel(String channelId, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
        public void createNotificationChannelGroup(NotificationChannelGroup notificationChannelGroup, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
        public void deleteNotificationChannelGroup(String groupId, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
        public void enqueueNotificationWithTag(int id, String tag, Notification notification, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
        public void cancelNotificationWithTag(int id, String tag, int userId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/notification/IBNotificationManagerService$Stub.class */
    public static abstract class Stub extends Binder implements IBNotificationManagerService {
        static final int TRANSACTION_getNotificationChannel = 1;
        static final int TRANSACTION_getNotificationChannels = 2;
        static final int TRANSACTION_getNotificationChannelGroups = 3;
        static final int TRANSACTION_createNotificationChannel = 4;
        static final int TRANSACTION_deleteNotificationChannel = 5;
        static final int TRANSACTION_createNotificationChannelGroup = 6;
        static final int TRANSACTION_deleteNotificationChannelGroup = 7;
        static final int TRANSACTION_enqueueNotificationWithTag = 8;
        static final int TRANSACTION_cancelNotificationWithTag = 9;

        public Stub() {
            attachInterface(this, IBNotificationManagerService.DESCRIPTOR);
        }

        public static IBNotificationManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBNotificationManagerService.DESCRIPTOR);
            if (iin != null && (iin instanceof IBNotificationManagerService)) {
                return (IBNotificationManagerService) iin;
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
                data.enforceInterface(IBNotificationManagerService.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IBNotificationManagerService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            String _arg0 = data.readString();
                            int _arg1 = data.readInt();
                            NotificationChannel _result = getNotificationChannel(_arg0, _arg1);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result, 1);
                            return true;
                        case 2:
                            String _arg2 = data.readString();
                            int _arg3 = data.readInt();
                            List<NotificationChannel> _result2 = getNotificationChannels(_arg2, _arg3);
                            reply.writeNoException();
                            _Parcel.writeTypedList(reply, _result2, 1);
                            return true;
                        case 3:
                            String _arg4 = data.readString();
                            int _arg5 = data.readInt();
                            List<NotificationChannelGroup> _result3 = getNotificationChannelGroups(_arg4, _arg5);
                            reply.writeNoException();
                            _Parcel.writeTypedList(reply, _result3, 1);
                            return true;
                        case 4:
                            NotificationChannel _arg6 = (NotificationChannel) _Parcel.readTypedObject(data, NotificationChannel.CREATOR);
                            int _arg7 = data.readInt();
                            createNotificationChannel(_arg6, _arg7);
                            reply.writeNoException();
                            return true;
                        case 5:
                            String _arg8 = data.readString();
                            int _arg9 = data.readInt();
                            deleteNotificationChannel(_arg8, _arg9);
                            reply.writeNoException();
                            return true;
                        case 6:
                            NotificationChannelGroup _arg10 = (NotificationChannelGroup) _Parcel.readTypedObject(data, NotificationChannelGroup.CREATOR);
                            int _arg11 = data.readInt();
                            createNotificationChannelGroup(_arg10, _arg11);
                            reply.writeNoException();
                            return true;
                        case 7:
                            String _arg12 = data.readString();
                            int _arg13 = data.readInt();
                            deleteNotificationChannelGroup(_arg12, _arg13);
                            reply.writeNoException();
                            return true;
                        case 8:
                            int _arg14 = data.readInt();
                            String _arg15 = data.readString();
                            Notification _arg16 = (Notification) _Parcel.readTypedObject(data, Notification.CREATOR);
                            int _arg17 = data.readInt();
                            enqueueNotificationWithTag(_arg14, _arg15, _arg16, _arg17);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_cancelNotificationWithTag /* 9 */:
                            int _arg18 = data.readInt();
                            String _arg19 = data.readString();
                            int _arg20 = data.readInt();
                            cancelNotificationWithTag(_arg18, _arg19, _arg20);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/notification/IBNotificationManagerService$Stub$Proxy.class */
        private static class Proxy implements IBNotificationManagerService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBNotificationManagerService.DESCRIPTOR;
            }

            @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
            public NotificationChannel getNotificationChannel(String channelId, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBNotificationManagerService.DESCRIPTOR);
                    _data.writeString(channelId);
                    _data.writeInt(userId);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    NotificationChannel _result = (NotificationChannel) _Parcel.readTypedObject(_reply, NotificationChannel.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
            public List<NotificationChannel> getNotificationChannels(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBNotificationManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    List<NotificationChannel> _result = _reply.createTypedArrayList(NotificationChannel.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
            public List<NotificationChannelGroup> getNotificationChannelGroups(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBNotificationManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    List<NotificationChannelGroup> _result = _reply.createTypedArrayList(NotificationChannelGroup.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
            public void createNotificationChannel(NotificationChannel notificationChannel, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBNotificationManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, notificationChannel, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
            public void deleteNotificationChannel(String channelId, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBNotificationManagerService.DESCRIPTOR);
                    _data.writeString(channelId);
                    _data.writeInt(userId);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
            public void createNotificationChannelGroup(NotificationChannelGroup notificationChannelGroup, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBNotificationManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, notificationChannelGroup, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
            public void deleteNotificationChannelGroup(String groupId, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBNotificationManagerService.DESCRIPTOR);
                    _data.writeString(groupId);
                    _data.writeInt(userId);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
            public void enqueueNotificationWithTag(int id, String tag, Notification notification, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBNotificationManagerService.DESCRIPTOR);
                    _data.writeInt(id);
                    _data.writeString(tag);
                    _Parcel.writeTypedObject(_data, notification, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
            public void cancelNotificationWithTag(int id, String tag, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBNotificationManagerService.DESCRIPTOR);
                    _data.writeInt(id);
                    _data.writeString(tag);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_cancelNotificationWithTag, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/notification/IBNotificationManagerService$_Parcel.class */
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
