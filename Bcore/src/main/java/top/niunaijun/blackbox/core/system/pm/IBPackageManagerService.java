package top.niunaijun.blackbox.core.system.pm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;
import top.niunaijun.blackbox.entity.pm.InstallOption;
import top.niunaijun.blackbox.entity.pm.InstallResult;
import top.niunaijun.blackbox.entity.pm.InstalledPackage;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBPackageManagerService.class */
public interface IBPackageManagerService extends IInterface {
    public static final String DESCRIPTOR = "top.niunaijun.blackbox.core.system.pm.IBPackageManagerService";

    ResolveInfo resolveService(Intent intent, int i, String str, int i2) throws RemoteException;

    ResolveInfo resolveActivity(Intent intent, int i, String str, int i2) throws RemoteException;

    ProviderInfo resolveContentProvider(String str, int i, int i2) throws RemoteException;

    ResolveInfo resolveIntent(Intent intent, String str, int i, int i2) throws RemoteException;

    ApplicationInfo getApplicationInfo(String str, int i, int i2) throws RemoteException;

    PackageInfo getPackageInfo(String str, int i, int i2) throws RemoteException;

    ServiceInfo getServiceInfo(ComponentName componentName, int i, int i2) throws RemoteException;

    ActivityInfo getReceiverInfo(ComponentName componentName, int i, int i2) throws RemoteException;

    ActivityInfo getActivityInfo(ComponentName componentName, int i, int i2) throws RemoteException;

    ProviderInfo getProviderInfo(ComponentName componentName, int i, int i2) throws RemoteException;

    List<ApplicationInfo> getInstalledApplications(int i, int i2) throws RemoteException;

    List<PackageInfo> getInstalledPackages(int i, int i2) throws RemoteException;

    List<ResolveInfo> queryIntentActivities(Intent intent, int i, String str, int i2) throws RemoteException;

    List<ResolveInfo> queryBroadcastReceivers(Intent intent, int i, String str, int i2) throws RemoteException;

    List<ResolveInfo> queryIntentServices(Intent intent, int i, int i2) throws RemoteException;

    List<ProviderInfo> queryContentProviders(String str, int i, int i2, int i3) throws RemoteException;

    InstallResult installPackageAsUser(String str, InstallOption installOption, int i) throws RemoteException;

    void uninstallPackageAsUser(String str, int i) throws RemoteException;

    void uninstallPackage(String str) throws RemoteException;

    void clearPackage(String str, int i) throws RemoteException;

    void stopPackage(String str, int i) throws RemoteException;

    void deleteUser(int i) throws RemoteException;

    boolean isInstalled(String str, int i) throws RemoteException;

    List<InstalledPackage> getInstalledPackagesAsUser(int i) throws RemoteException;

    String[] getPackagesForUid(int i, int i2) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBPackageManagerService$Default.class */
    public static class Default implements IBPackageManagerService {
        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public ResolveInfo resolveService(Intent intent, int flags, String resolvedType, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public ResolveInfo resolveActivity(Intent intent, int flags, String resolvedType, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public ProviderInfo resolveContentProvider(String authority, int flag, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public ResolveInfo resolveIntent(Intent intent, String resolvedType, int flags, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public ApplicationInfo getApplicationInfo(String packageName, int flags, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public PackageInfo getPackageInfo(String packageName, int flags, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public ServiceInfo getServiceInfo(ComponentName component, int flags, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public ActivityInfo getReceiverInfo(ComponentName componentName, int flags, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public ActivityInfo getActivityInfo(ComponentName component, int flags, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public ProviderInfo getProviderInfo(ComponentName component, int flags, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public List<ApplicationInfo> getInstalledApplications(int flags, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public List<PackageInfo> getInstalledPackages(int flags, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public List<ResolveInfo> queryIntentActivities(Intent intent, int flags, String resolvedType, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public List<ResolveInfo> queryBroadcastReceivers(Intent intent, int flags, String resolvedType, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public List<ResolveInfo> queryIntentServices(Intent intent, int flags, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public List<ProviderInfo> queryContentProviders(String processName, int uid, int flags, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public InstallResult installPackageAsUser(String file, InstallOption option, int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public void uninstallPackageAsUser(String packageName, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public void uninstallPackage(String packageName) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public void clearPackage(String packageName, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public void stopPackage(String packageName, int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public void deleteUser(int userId) throws RemoteException {
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public boolean isInstalled(String packageName, int userId) throws RemoteException {
            return false;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public List<InstalledPackage> getInstalledPackagesAsUser(int userId) throws RemoteException {
            return null;
        }

        @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
        public String[] getPackagesForUid(int uid, int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBPackageManagerService$Stub.class */
    public static abstract class Stub extends Binder implements IBPackageManagerService {
        static final int TRANSACTION_resolveService = 1;
        static final int TRANSACTION_resolveActivity = 2;
        static final int TRANSACTION_resolveContentProvider = 3;
        static final int TRANSACTION_resolveIntent = 4;
        static final int TRANSACTION_getApplicationInfo = 5;
        static final int TRANSACTION_getPackageInfo = 6;
        static final int TRANSACTION_getServiceInfo = 7;
        static final int TRANSACTION_getReceiverInfo = 8;
        static final int TRANSACTION_getActivityInfo = 9;
        static final int TRANSACTION_getProviderInfo = 10;
        static final int TRANSACTION_getInstalledApplications = 11;
        static final int TRANSACTION_getInstalledPackages = 12;
        static final int TRANSACTION_queryIntentActivities = 13;
        static final int TRANSACTION_queryBroadcastReceivers = 14;
        static final int TRANSACTION_queryIntentServices = 15;
        static final int TRANSACTION_queryContentProviders = 16;
        static final int TRANSACTION_installPackageAsUser = 17;
        static final int TRANSACTION_uninstallPackageAsUser = 18;
        static final int TRANSACTION_uninstallPackage = 19;
        static final int TRANSACTION_clearPackage = 20;
        static final int TRANSACTION_stopPackage = 21;
        static final int TRANSACTION_deleteUser = 22;
        static final int TRANSACTION_isInstalled = 23;
        static final int TRANSACTION_getInstalledPackagesAsUser = 24;
        static final int TRANSACTION_getPackagesForUid = 25;

        public Stub() {
            attachInterface(this, IBPackageManagerService.DESCRIPTOR);
        }

        public static IBPackageManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBPackageManagerService.DESCRIPTOR);
            if (iin != null && (iin instanceof IBPackageManagerService)) {
                return (IBPackageManagerService) iin;
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
                data.enforceInterface(IBPackageManagerService.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IBPackageManagerService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            Intent _arg0 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            int _arg1 = data.readInt();
                            String _arg2 = data.readString();
                            int _arg3 = data.readInt();
                            ResolveInfo _result = resolveService(_arg0, _arg1, _arg2, _arg3);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result, 1);
                            return true;
                        case 2:
                            Intent _arg4 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            int _arg5 = data.readInt();
                            String _arg6 = data.readString();
                            int _arg7 = data.readInt();
                            ResolveInfo _result2 = resolveActivity(_arg4, _arg5, _arg6, _arg7);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result2, 1);
                            return true;
                        case 3:
                            String _arg8 = data.readString();
                            int _arg9 = data.readInt();
                            int _arg10 = data.readInt();
                            ProviderInfo _result3 = resolveContentProvider(_arg8, _arg9, _arg10);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result3, 1);
                            return true;
                        case 4:
                            Intent _arg11 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            String _arg12 = data.readString();
                            int _arg13 = data.readInt();
                            int _arg14 = data.readInt();
                            ResolveInfo _result4 = resolveIntent(_arg11, _arg12, _arg13, _arg14);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result4, 1);
                            return true;
                        case 5:
                            String _arg15 = data.readString();
                            int _arg16 = data.readInt();
                            int _arg17 = data.readInt();
                            ApplicationInfo _result5 = getApplicationInfo(_arg15, _arg16, _arg17);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result5, 1);
                            return true;
                        case 6:
                            String _arg18 = data.readString();
                            int _arg19 = data.readInt();
                            int _arg20 = data.readInt();
                            PackageInfo _result6 = getPackageInfo(_arg18, _arg19, _arg20);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result6, 1);
                            return true;
                        case 7:
                            ComponentName _arg21 = (ComponentName) _Parcel.readTypedObject(data, ComponentName.CREATOR);
                            int _arg22 = data.readInt();
                            int _arg23 = data.readInt();
                            ServiceInfo _result7 = getServiceInfo(_arg21, _arg22, _arg23);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result7, 1);
                            return true;
                        case 8:
                            ComponentName _arg24 = (ComponentName) _Parcel.readTypedObject(data, ComponentName.CREATOR);
                            int _arg25 = data.readInt();
                            int _arg26 = data.readInt();
                            ActivityInfo _result8 = getReceiverInfo(_arg24, _arg25, _arg26);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result8, 1);
                            return true;
                        case TRANSACTION_getActivityInfo /* 9 */:
                            ComponentName _arg27 = (ComponentName) _Parcel.readTypedObject(data, ComponentName.CREATOR);
                            int _arg28 = data.readInt();
                            int _arg29 = data.readInt();
                            ActivityInfo _result9 = getActivityInfo(_arg27, _arg28, _arg29);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result9, 1);
                            return true;
                        case 10:
                            ComponentName _arg30 = (ComponentName) _Parcel.readTypedObject(data, ComponentName.CREATOR);
                            int _arg31 = data.readInt();
                            int _arg32 = data.readInt();
                            ProviderInfo _result10 = getProviderInfo(_arg30, _arg31, _arg32);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result10, 1);
                            return true;
                        case TRANSACTION_getInstalledApplications /* 11 */:
                            int _arg33 = data.readInt();
                            int _arg34 = data.readInt();
                            List<ApplicationInfo> _result11 = getInstalledApplications(_arg33, _arg34);
                            reply.writeNoException();
                            _Parcel.writeTypedList(reply, _result11, 1);
                            return true;
                        case TRANSACTION_getInstalledPackages /* 12 */:
                            int _arg35 = data.readInt();
                            int _arg36 = data.readInt();
                            List<PackageInfo> _result12 = getInstalledPackages(_arg35, _arg36);
                            reply.writeNoException();
                            _Parcel.writeTypedList(reply, _result12, 1);
                            return true;
                        case TRANSACTION_queryIntentActivities /* 13 */:
                            Intent _arg37 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            int _arg38 = data.readInt();
                            String _arg39 = data.readString();
                            int _arg40 = data.readInt();
                            List<ResolveInfo> _result13 = queryIntentActivities(_arg37, _arg38, _arg39, _arg40);
                            reply.writeNoException();
                            _Parcel.writeTypedList(reply, _result13, 1);
                            return true;
                        case TRANSACTION_queryBroadcastReceivers /* 14 */:
                            Intent _arg41 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            int _arg42 = data.readInt();
                            String _arg43 = data.readString();
                            int _arg44 = data.readInt();
                            List<ResolveInfo> _result14 = queryBroadcastReceivers(_arg41, _arg42, _arg43, _arg44);
                            reply.writeNoException();
                            _Parcel.writeTypedList(reply, _result14, 1);
                            return true;
                        case TRANSACTION_queryIntentServices /* 15 */:
                            Intent _arg45 = (Intent) _Parcel.readTypedObject(data, Intent.CREATOR);
                            int _arg46 = data.readInt();
                            int _arg47 = data.readInt();
                            List<ResolveInfo> _result15 = queryIntentServices(_arg45, _arg46, _arg47);
                            reply.writeNoException();
                            _Parcel.writeTypedList(reply, _result15, 1);
                            return true;
                        case 16:
                            String _arg48 = data.readString();
                            int _arg49 = data.readInt();
                            int _arg50 = data.readInt();
                            int _arg51 = data.readInt();
                            List<ProviderInfo> _result16 = queryContentProviders(_arg48, _arg49, _arg50, _arg51);
                            reply.writeNoException();
                            _Parcel.writeTypedList(reply, _result16, 1);
                            return true;
                        case TRANSACTION_installPackageAsUser /* 17 */:
                            String _arg52 = data.readString();
                            InstallOption _arg53 = (InstallOption) _Parcel.readTypedObject(data, InstallOption.CREATOR);
                            int _arg54 = data.readInt();
                            InstallResult _result17 = installPackageAsUser(_arg52, _arg53, _arg54);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _result17, 1);
                            return true;
                        case TRANSACTION_uninstallPackageAsUser /* 18 */:
                            String _arg55 = data.readString();
                            int _arg56 = data.readInt();
                            uninstallPackageAsUser(_arg55, _arg56);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_uninstallPackage /* 19 */:
                            String _arg57 = data.readString();
                            uninstallPackage(_arg57);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_clearPackage /* 20 */:
                            String _arg58 = data.readString();
                            int _arg59 = data.readInt();
                            clearPackage(_arg58, _arg59);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_stopPackage /* 21 */:
                            String _arg60 = data.readString();
                            int _arg61 = data.readInt();
                            stopPackage(_arg60, _arg61);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_deleteUser /* 22 */:
                            int _arg62 = data.readInt();
                            deleteUser(_arg62);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_isInstalled /* 23 */:
                            String _arg63 = data.readString();
                            int _arg64 = data.readInt();
                            boolean _result18 = isInstalled(_arg63, _arg64);
                            reply.writeNoException();
                            reply.writeInt(_result18 ? 1 : 0);
                            return true;
                        case TRANSACTION_getInstalledPackagesAsUser /* 24 */:
                            int _arg65 = data.readInt();
                            List<InstalledPackage> _result19 = getInstalledPackagesAsUser(_arg65);
                            reply.writeNoException();
                            _Parcel.writeTypedList(reply, _result19, 1);
                            return true;
                        case TRANSACTION_getPackagesForUid /* 25 */:
                            int _arg66 = data.readInt();
                            int _arg67 = data.readInt();
                            String[] _result20 = getPackagesForUid(_arg66, _arg67);
                            reply.writeNoException();
                            reply.writeStringArray(_result20);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBPackageManagerService$Stub$Proxy.class */
        private static class Proxy implements IBPackageManagerService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBPackageManagerService.DESCRIPTOR;
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public ResolveInfo resolveService(Intent intent, int flags, String resolvedType, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    _data.writeInt(flags);
                    _data.writeString(resolvedType);
                    _data.writeInt(userId);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    ResolveInfo _result = (ResolveInfo) _Parcel.readTypedObject(_reply, ResolveInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public ResolveInfo resolveActivity(Intent intent, int flags, String resolvedType, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    _data.writeInt(flags);
                    _data.writeString(resolvedType);
                    _data.writeInt(userId);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    ResolveInfo _result = (ResolveInfo) _Parcel.readTypedObject(_reply, ResolveInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public ProviderInfo resolveContentProvider(String authority, int flag, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeString(authority);
                    _data.writeInt(flag);
                    _data.writeInt(userId);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    ProviderInfo _result = (ProviderInfo) _Parcel.readTypedObject(_reply, ProviderInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public ResolveInfo resolveIntent(Intent intent, String resolvedType, int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    _data.writeString(resolvedType);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    ResolveInfo _result = (ResolveInfo) _Parcel.readTypedObject(_reply, ResolveInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public ApplicationInfo getApplicationInfo(String packageName, int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    ApplicationInfo _result = (ApplicationInfo) _Parcel.readTypedObject(_reply, ApplicationInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public PackageInfo getPackageInfo(String packageName, int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    PackageInfo _result = (PackageInfo) _Parcel.readTypedObject(_reply, PackageInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public ServiceInfo getServiceInfo(ComponentName component, int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, component, 0);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    ServiceInfo _result = (ServiceInfo) _Parcel.readTypedObject(_reply, ServiceInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public ActivityInfo getReceiverInfo(ComponentName componentName, int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, componentName, 0);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    ActivityInfo _result = (ActivityInfo) _Parcel.readTypedObject(_reply, ActivityInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public ActivityInfo getActivityInfo(ComponentName component, int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, component, 0);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getActivityInfo, _data, _reply, 0);
                    _reply.readException();
                    ActivityInfo _result = (ActivityInfo) _Parcel.readTypedObject(_reply, ActivityInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public ProviderInfo getProviderInfo(ComponentName component, int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, component, 0);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                    ProviderInfo _result = (ProviderInfo) _Parcel.readTypedObject(_reply, ProviderInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public List<ApplicationInfo> getInstalledApplications(int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getInstalledApplications, _data, _reply, 0);
                    _reply.readException();
                    List<ApplicationInfo> _result = _reply.createTypedArrayList(ApplicationInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public List<PackageInfo> getInstalledPackages(int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getInstalledPackages, _data, _reply, 0);
                    _reply.readException();
                    List<PackageInfo> _result = _reply.createTypedArrayList(PackageInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public List<ResolveInfo> queryIntentActivities(Intent intent, int flags, String resolvedType, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    _data.writeInt(flags);
                    _data.writeString(resolvedType);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_queryIntentActivities, _data, _reply, 0);
                    _reply.readException();
                    List<ResolveInfo> _result = _reply.createTypedArrayList(ResolveInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public List<ResolveInfo> queryBroadcastReceivers(Intent intent, int flags, String resolvedType, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    _data.writeInt(flags);
                    _data.writeString(resolvedType);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_queryBroadcastReceivers, _data, _reply, 0);
                    _reply.readException();
                    List<ResolveInfo> _result = _reply.createTypedArrayList(ResolveInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public List<ResolveInfo> queryIntentServices(Intent intent, int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _Parcel.writeTypedObject(_data, intent, 0);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_queryIntentServices, _data, _reply, 0);
                    _reply.readException();
                    List<ResolveInfo> _result = _reply.createTypedArrayList(ResolveInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public List<ProviderInfo> queryContentProviders(String processName, int uid, int flags, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeString(processName);
                    _data.writeInt(uid);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                    List<ProviderInfo> _result = _reply.createTypedArrayList(ProviderInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public InstallResult installPackageAsUser(String file, InstallOption option, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeString(file);
                    _Parcel.writeTypedObject(_data, option, 0);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_installPackageAsUser, _data, _reply, 0);
                    _reply.readException();
                    InstallResult _result = (InstallResult) _Parcel.readTypedObject(_reply, InstallResult.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public void uninstallPackageAsUser(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_uninstallPackageAsUser, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public void uninstallPackage(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    this.mRemote.transact(Stub.TRANSACTION_uninstallPackage, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public void clearPackage(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_clearPackage, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public void stopPackage(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_stopPackage, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public void deleteUser(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_deleteUser, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public boolean isInstalled(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_isInstalled, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = 0 != _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public List<InstalledPackage> getInstalledPackagesAsUser(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getInstalledPackagesAsUser, _data, _reply, 0);
                    _reply.readException();
                    List<InstalledPackage> _result = _reply.createTypedArrayList(InstalledPackage.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // top.niunaijun.blackbox.core.system.pm.IBPackageManagerService
            public String[] getPackagesForUid(int uid, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBPackageManagerService.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeInt(userId);
                    this.mRemote.transact(Stub.TRANSACTION_getPackagesForUid, _data, _reply, 0);
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/pm/IBPackageManagerService$_Parcel.class */
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
