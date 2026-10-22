package top.niunaijun.blackbox.core.system.am;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import top.niunaijun.blackbox.entity.AppConfig;
import top.niunaijun.blackbox.entity.UnbindRecord;
import top.niunaijun.blackbox.entity.am.PendingResultData;
import top.niunaijun.blackbox.entity.am.RunningAppProcessInfo;
import top.niunaijun.blackbox.entity.am.RunningServiceInfo;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/IBActivityManagerService.class */
public interface IBActivityManagerService extends IInterface {
    public static final String DESCRIPTOR = "top.niunaijun.blackbox.core.system.am.IBActivityManagerService";

    AppConfig initProcess(String str, String str2, int i) throws RemoteException;

    void restartProcess(String str, String str2, int i) throws RemoteException;

    void startActivity(Intent intent, int i) throws RemoteException;

    int startActivityAms(int i, Intent intent, String str, IBinder iBinder, String str2, int i2, int i3, Bundle bundle) throws RemoteException;

    int startActivities(int i, Intent[] intentArr, String[] strArr, IBinder iBinder, Bundle bundle) throws RemoteException;

    ComponentName startService(Intent intent, String str, boolean z, int i) throws RemoteException;

    int stopService(Intent intent, String str, int i) throws RemoteException;

    Intent bindService(Intent intent, IBinder iBinder, String str, int i) throws RemoteException;

    void unbindService(IBinder iBinder, int i) throws RemoteException;

    void stopServiceToken(ComponentName componentName, IBinder iBinder, int i) throws RemoteException;

    void onStartCommand(Intent intent, int i) throws RemoteException;

    UnbindRecord onServiceUnbind(Intent intent, int i) throws RemoteException;

    void onServiceDestroy(Intent intent, int i) throws RemoteException;

    IBinder acquireContentProviderClient(ProviderInfo providerInfo) throws RemoteException;

    Intent sendBroadcast(Intent intent, String str, int i) throws RemoteException;

    IBinder peekService(Intent intent, String str, int i) throws RemoteException;

    void onActivityCreated(int i, IBinder iBinder, IBinder iBinder2) throws RemoteException;

    void onActivityResumed(IBinder iBinder) throws RemoteException;

    void onActivityDestroyed(IBinder iBinder) throws RemoteException;

    void onFinishActivity(IBinder iBinder) throws RemoteException;

    RunningAppProcessInfo getRunningAppProcesses(String str, int i) throws RemoteException;

    RunningServiceInfo getRunningServices(String str, int i) throws RemoteException;

    void scheduleBroadcastReceiver(Intent intent, PendingResultData pendingResultData, int i) throws RemoteException;

    void finishBroadcast(PendingResultData pendingResultData) throws RemoteException;

    String getCallingPackage(IBinder iBinder, int i) throws RemoteException;

    ComponentName getCallingActivity(IBinder iBinder, int i) throws RemoteException;

    void getIntentSender(IBinder iBinder, String str, int i, int i2) throws RemoteException;

    String getPackageForIntentSender(IBinder iBinder, int i) throws RemoteException;

    int getUidForIntentSender(IBinder iBinder, int i) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/IBActivityManagerService$Default.class */
    public static class Default implements IBActivityManagerService {
        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public AppConfig initProcess(String packageName, String processName, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public void restartProcess(String packageName, String processName, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public void startActivity(Intent intent, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public int startActivityAms(int userId, Intent intent, String resolvedType, IBinder resultTo, String resultWho, int requestCode, int flags, Bundle options) throws RemoteException {
            return 0;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public int startActivities(int userId, Intent[] intent, String[] resolvedType, IBinder resultTo, Bundle options) throws RemoteException {
            return 0;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public ComponentName startService(Intent intent, String resolvedType, boolean requireForeground, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public int stopService(Intent intent, String resolvedType, int userId) throws RemoteException {
            return 0;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public Intent bindService(Intent service, IBinder binder, String resolvedType, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public void unbindService(IBinder binder, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public void stopServiceToken(ComponentName className, IBinder token, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public void onStartCommand(Intent proxyIntent, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public UnbindRecord onServiceUnbind(Intent proxyIntent, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public void onServiceDestroy(Intent proxyIntent, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public IBinder acquireContentProviderClient(ProviderInfo providerInfo) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public Intent sendBroadcast(Intent intent, String resolvedType, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public IBinder peekService(Intent intent, String resolvedType, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public void onActivityCreated(int taskId, IBinder token, IBinder activityRecord) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public void onActivityResumed(IBinder token) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public void onActivityDestroyed(IBinder token) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public void onFinishActivity(IBinder token) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public RunningAppProcessInfo getRunningAppProcesses(String callerPackage, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public RunningServiceInfo getRunningServices(String callerPackage, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public void scheduleBroadcastReceiver(Intent intent, PendingResultData pendingResultData, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public void finishBroadcast(PendingResultData data) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public String getCallingPackage(IBinder token, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public ComponentName getCallingActivity(IBinder token, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public void getIntentSender(IBinder target, String packageName, int uid, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public String getPackageForIntentSender(IBinder target, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
        public int getUidForIntentSender(IBinder target, int userId) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/IBActivityManagerService$Stub.class */
    public static abstract class Stub extends Binder implements IBActivityManagerService {
        static final int TRANSACTION_initProcess = 1;
        static final int TRANSACTION_restartProcess = 2;
        static final int TRANSACTION_startActivity = 3;
        static final int TRANSACTION_startActivityAms = 4;
        static final int TRANSACTION_startActivities = 5;
        static final int TRANSACTION_startService = 6;
        static final int TRANSACTION_stopService = 7;
        static final int TRANSACTION_bindService = 8;
        static final int TRANSACTION_unbindService = 9;
        static final int TRANSACTION_stopServiceToken = 10;
        static final int TRANSACTION_onStartCommand = 11;
        static final int TRANSACTION_onServiceUnbind = 12;
        static final int TRANSACTION_onServiceDestroy = 13;
        static final int TRANSACTION_acquireContentProviderClient = 14;
        static final int TRANSACTION_sendBroadcast = 15;
        static final int TRANSACTION_peekService = 16;
        static final int TRANSACTION_onActivityCreated = 17;
        static final int TRANSACTION_onActivityResumed = 18;
        static final int TRANSACTION_onActivityDestroyed = 19;
        static final int TRANSACTION_onFinishActivity = 20;
        static final int TRANSACTION_getRunningAppProcesses = 21;
        static final int TRANSACTION_getRunningServices = 22;
        static final int TRANSACTION_scheduleBroadcastReceiver = 23;
        static final int TRANSACTION_finishBroadcast = 24;
        static final int TRANSACTION_getCallingPackage = 25;
        static final int TRANSACTION_getCallingActivity = 26;
        static final int TRANSACTION_getIntentSender = 27;
        static final int TRANSACTION_getPackageForIntentSender = 28;
        static final int TRANSACTION_getUidForIntentSender = 29;

        public Stub() {
            attachInterface(this, IBActivityManagerService.DESCRIPTOR);
        }

        public static IBActivityManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBActivityManagerService.DESCRIPTOR);
            if (iin != null && (iin instanceof IBActivityManagerService)) {
                return (IBActivityManagerService) iin;
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
                data.enforceInterface(IBActivityManagerService.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IBActivityManagerService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            String _arg0 = data.readString();
                            String _arg1 = data.readString();
                            int _arg2 = data.readInt();
                            AppConfig _result = initProcess(_arg0, _arg1, _arg2);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result, 1);
                            return true;
                        case 2:
                            String _arg3 = data.readString();
                            String _arg4 = data.readString();
                            int _arg5 = data.readInt();
                            restartProcess(_arg3, _arg4, _arg5);
                            reply.writeNoException();
                            return true;
                        case 3:
                            Intent _arg6 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            int _arg7 = data.readInt();
                            startActivity(_arg6, _arg7);
                            reply.writeNoException();
                            return true;
                        case 4:
                            int _arg8 = data.readInt();
                            Intent _arg9 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            String _arg10 = data.readString();
                            IBinder _arg11 = data.readStrongBinder();
                            String _arg12 = data.readString();
                            int _arg13 = data.readInt();
                            int _arg14 = data.readInt();
                            Bundle _arg15 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            int _result2 = startActivityAms(_arg8, _arg9, _arg10, _arg11, _arg12, _arg13, _arg14, _arg15);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 5:
                            int _arg16 = data.readInt();
                            Intent[] _arg17 = (Intent[]) data.createTypedArray(Intent.CREATOR);
                            String[] _arg18 = data.createStringArray();
                            IBinder _arg19 = data.readStrongBinder();
                            Bundle _arg20 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            int _result3 = startActivities(_arg16, _arg17, _arg18, _arg19, _arg20);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 6:
                            Intent _arg21 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            String _arg22 = data.readString();
                            boolean _arg23 = 0 != data.readInt();
                            int _arg24 = data.readInt();
                            ComponentName _result4 = startService(_arg21, _arg22, _arg23, _arg24);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result4, 1);
                            return true;
                        case 7:
                            Intent _arg25 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            String _arg26 = data.readString();
                            int _arg27 = data.readInt();
                            int _result5 = stopService(_arg25, _arg26, _arg27);
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 8:
                            Intent _arg28 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            IBinder _arg29 = data.readStrongBinder();
                            String _arg30 = data.readString();
                            int _arg31 = data.readInt();
                            Intent _result6 = bindService(_arg28, _arg29, _arg30, _arg31);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result6, 1);
                            return true;
                        case TRANSACTION_unbindService /* 9 */:
                            IBinder _arg32 = data.readStrongBinder();
                            int _arg33 = data.readInt();
                            unbindService(_arg32, _arg33);
                            reply.writeNoException();
                            return true;
                        case 10:
                            ComponentName _arg34 = (ComponentName) _Parcel.readTypedObject(data, ComponentName.CREATOR);
                            IBinder _arg35 = data.readStrongBinder();
                            int _arg36 = data.readInt();
                            stopServiceToken(_arg34, _arg35, _arg36);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_onStartCommand /* 11 */:
                            Intent _arg37 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            int _arg38 = data.readInt();
                            onStartCommand(_arg37, _arg38);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_onServiceUnbind /* 12 */:
                            Intent _arg39 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            int _arg40 = data.readInt();
                            UnbindRecord _result7 = onServiceUnbind(_arg39, _arg40);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result7, 1);
                            return true;
                        case TRANSACTION_onServiceDestroy /* 13 */:
                            Intent _arg41 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            int _arg42 = data.readInt();
                            onServiceDestroy(_arg41, _arg42);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_acquireContentProviderClient /* 14 */:
                            ProviderInfo _arg43 = (ProviderInfo) _Parcel.readTypedObject(data, ProviderInfo.CREATOR);
                            IBinder _result8 = acquireContentProviderClient(_arg43);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result8);
                            return true;
                        case TRANSACTION_sendBroadcast /* 15 */:
                            Intent _arg44 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            String _arg45 = data.readString();
                            int _arg46 = data.readInt();
                            Intent _result9 = sendBroadcast(_arg44, _arg45, _arg46);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result9, 1);
                            return true;
                        case 16:
                            Intent _arg47 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            String _arg48 = data.readString();
                            int _arg49 = data.readInt();
                            IBinder _result10 = peekService(_arg47, _arg48, _arg49);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result10);
                            return true;
                        case TRANSACTION_onActivityCreated /* 17 */:
                            int _arg50 = data.readInt();
                            IBinder _arg51 = data.readStrongBinder();
                            IBinder _arg52 = data.readStrongBinder();
                            onActivityCreated(_arg50, _arg51, _arg52);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_onActivityResumed /* 18 */:
                            IBinder _arg53 = data.readStrongBinder();
                            onActivityResumed(_arg53);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_onActivityDestroyed /* 19 */:
                            IBinder _arg54 = data.readStrongBinder();
                            onActivityDestroyed(_arg54);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_onFinishActivity /* 20 */:
                            IBinder _arg55 = data.readStrongBinder();
                            onFinishActivity(_arg55);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_getRunningAppProcesses /* 21 */:
                            String _arg56 = data.readString();
                            int _arg57 = data.readInt();
                            RunningAppProcessInfo _result11 = getRunningAppProcesses(_arg56, _arg57);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result11, 1);
                            return true;
                        case TRANSACTION_getRunningServices /* 22 */:
                            String _arg58 = data.readString();
                            int _arg59 = data.readInt();
                            RunningServiceInfo _result12 = getRunningServices(_arg58, _arg59);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result12, 1);
                            return true;
                        case TRANSACTION_scheduleBroadcastReceiver /* 23 */:
                            Intent _arg60 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            PendingResultData _arg61 = (PendingResultData) _Parcel.readTypedObject(data, PendingResultData.CREATOR);
                            int _arg62 = data.readInt();
                            scheduleBroadcastReceiver(_arg60, _arg61, _arg62);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_finishBroadcast /* 24 */:
                            PendingResultData _arg63 = (PendingResultData) _Parcel.readTypedObject(data, PendingResultData.CREATOR);
                            finishBroadcast(_arg63);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_getCallingPackage /* 25 */:
                            IBinder _arg64 = data.readStrongBinder();
                            int _arg65 = data.readInt();
                            String _result13 = getCallingPackage(_arg64, _arg65);
                            reply.writeNoException();
                            reply.writeString(_result13);
                            return true;
                        case TRANSACTION_getCallingActivity /* 26 */:
                            IBinder _arg66 = data.readStrongBinder();
                            int _arg67 = data.readInt();
                            ComponentName _result14 = getCallingActivity(_arg66, _arg67);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result14, 1);
                            return true;
                        case TRANSACTION_getIntentSender /* 27 */:
                            IBinder _arg68 = data.readStrongBinder();
                            String _arg69 = data.readString();
                            int _arg70 = data.readInt();
                            int _arg71 = data.readInt();
                            getIntentSender(_arg68, _arg69, _arg70, _arg71);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_getPackageForIntentSender /* 28 */:
                            IBinder _arg72 = data.readStrongBinder();
                            int _arg73 = data.readInt();
                            String _result15 = getPackageForIntentSender(_arg72, _arg73);
                            reply.writeNoException();
                            reply.writeString(_result15);
                            return true;
                        case TRANSACTION_getUidForIntentSender /* 29 */:
                            IBinder _arg74 = data.readStrongBinder();
                            int _arg75 = data.readInt();
                            int _result16 = getUidForIntentSender(_arg74, _arg75);
                            reply.writeNoException();
                            reply.writeInt(_result16);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/IBActivityManagerService$Stub$Proxy.class */
        private static class Proxy implements IBActivityManagerService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBActivityManagerService.DESCRIPTOR;
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public AppConfig initProcess(String packageName, String processName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(processName);
                    _data.writeInt(userId);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    AppConfig _result = (AppConfig) _Parcel.readTypedObject(_reply, AppConfig.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public void restartProcess(String packageName, String processName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(processName);
                    _data.writeInt(userId);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public void startActivity(Intent intent, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public int startActivityAms(int userId, Intent intent, String resolvedType, IBinder resultTo, String resultWho, int requestCode, int flags, Bundle options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    _data.writeString(resolvedType);
                    _data.writeStrongBinder(resultTo);
                    _data.writeString(resultWho);
                    _data.writeInt(requestCode);
                    _data.writeInt(flags);
                    _Parcel.writeTypedObject(_data, options, 0);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public int startActivities(int userId, Intent[] intent, String[] resolvedType, IBinder resultTo, Bundle options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeTypedArray(intent, 0);
                    _data.writeStringArray(resolvedType);
                    _data.writeStrongBinder(resultTo);
                    _Parcel.writeTypedObject(_data, options, 0);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public ComponentName startService(Intent intent, String resolvedType, boolean requireForeground, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    _data.writeString(resolvedType);
                    _data.writeInt(requireForeground ? 1 : 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    ComponentName _result = (ComponentName) _Parcel.readTypedObject(_reply, ComponentName.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public int stopService(Intent intent, String resolvedType, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    _data.writeString(resolvedType);
                    _data.writeInt(userId);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public Intent bindService(Intent service, IBinder binder, String resolvedType, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, service, 0);
                    _data.writeStrongBinder(binder);
                    _data.writeString(resolvedType);
                    _data.writeInt(userId);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    Intent _result = (Intent) _Parcel.readTypedObject(_reply, Intent.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public void unbindService(IBinder binder, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeStrongBinder(binder);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_unbindService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public void stopServiceToken(ComponentName className, IBinder token, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, className, 0);
                    _data.writeStrongBinder(token);
                    _data.writeInt(userId);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public void onStartCommand(Intent proxyIntent, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, proxyIntent, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_onStartCommand, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public UnbindRecord onServiceUnbind(Intent proxyIntent, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, proxyIntent, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_onServiceUnbind, _data, _reply, 0);
                    _reply.readException();
                    UnbindRecord _result = (UnbindRecord) _Parcel.readTypedObject(_reply, UnbindRecord.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public void onServiceDestroy(Intent proxyIntent, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, proxyIntent, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_onServiceDestroy, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public IBinder acquireContentProviderClient(ProviderInfo providerInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, providerInfo, 0);
                    this.mRemote.transact(Stub.TRANSACTION_acquireContentProviderClient, _data, _reply, 0);
                    _reply.readException();
                    IBinder _result = _reply.readStrongBinder();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public Intent sendBroadcast(Intent intent, String resolvedType, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    _data.writeString(resolvedType);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_sendBroadcast, _data, _reply, 0);
                    _reply.readException();
                    Intent _result = (Intent) _Parcel.readTypedObject(_reply, Intent.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public IBinder peekService(Intent intent, String resolvedType, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    _data.writeString(resolvedType);
                    _data.writeInt(userId);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                    IBinder _result = _reply.readStrongBinder();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public void onActivityCreated(int taskId, IBinder token, IBinder activityRecord) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeInt(taskId);
                    _data.writeStrongBinder(token);
                    _data.writeStrongBinder(activityRecord);
                    this.mRemote.transact(Stub.TRANSACTION_onActivityCreated, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public void onActivityResumed(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    this.mRemote.transact(Stub.TRANSACTION_onActivityResumed, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public void onActivityDestroyed(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    this.mRemote.transact(Stub.TRANSACTION_onActivityDestroyed, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public void onFinishActivity(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    this.mRemote.transact(Stub.TRANSACTION_onFinishActivity, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public RunningAppProcessInfo getRunningAppProcesses(String callerPackage, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeString(callerPackage);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getRunningAppProcesses, _data, _reply, 0);
                    _reply.readException();
                    RunningAppProcessInfo _result = (RunningAppProcessInfo) _Parcel.readTypedObject(_reply, RunningAppProcessInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public RunningServiceInfo getRunningServices(String callerPackage, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeString(callerPackage);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getRunningServices, _data, _reply, 0);
                    _reply.readException();
                    RunningServiceInfo _result = (RunningServiceInfo) _Parcel.readTypedObject(_reply, RunningServiceInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public void scheduleBroadcastReceiver(Intent intent, PendingResultData pendingResultData, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    _Parcel.writeTypedObject(_data, pendingResultData, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_scheduleBroadcastReceiver, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public void finishBroadcast(PendingResultData data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, data, 0);
                    this.mRemote.transact(Stub.TRANSACTION_finishBroadcast, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public String getCallingPackage(IBinder token, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getCallingPackage, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public ComponentName getCallingActivity(IBinder token, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getCallingActivity, _data, _reply, 0);
                    _reply.readException();
                    ComponentName _result = (ComponentName) _Parcel.readTypedObject(_reply, ComponentName.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public void getIntentSender(IBinder target, String packageName, int uid, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeStrongBinder(target);
                    _data.writeString(packageName);
                    _data.writeInt(uid);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getIntentSender, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public String getPackageForIntentSender(IBinder target, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeStrongBinder(target);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getPackageForIntentSender, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.am.IBActivityManagerService
            public int getUidForIntentSender(IBinder target, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBActivityManagerService.DESCRIPTOR);
                    _data.writeStrongBinder(target);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getUidForIntentSender, _data, _reply, 0);
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

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/IBActivityManagerService$_Parcel.class */
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
