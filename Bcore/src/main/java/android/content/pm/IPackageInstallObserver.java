package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.jar:android/content/pm/IPackageInstallObserver.class */
public interface IPackageInstallObserver extends IInterface {
    public static final String DESCRIPTOR = "android.content.pm.IPackageInstallObserver";

    void packageInstalled(String str, int i) throws RemoteException;

    /* JADX INFO: loaded from: classes.jar:android/content/pm/IPackageInstallObserver$Default.class */
    public static class Default implements IPackageInstallObserver {
        @Override // android.content.pm.IPackageInstallObserver
        public void packageInstalled(String packageName, int returnCode) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/content/pm/IPackageInstallObserver$Stub.class */
    public static abstract class Stub extends Binder implements IPackageInstallObserver {
        static final int TRANSACTION_packageInstalled = 1;

        public Stub() {
            attachInterface(this, IPackageInstallObserver.DESCRIPTOR);
        }

        public static IPackageInstallObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPackageInstallObserver.DESCRIPTOR);
            if (iin != null && (iin instanceof IPackageInstallObserver)) {
                return (IPackageInstallObserver) iin;
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
                data.enforceInterface(IPackageInstallObserver.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IPackageInstallObserver.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            String _arg0 = data.readString();
                            int _arg1 = data.readInt();
                            packageInstalled(_arg0, _arg1);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: classes.jar:android/content/pm/IPackageInstallObserver$Stub$Proxy.class */
        private static class Proxy implements IPackageInstallObserver {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPackageInstallObserver.DESCRIPTOR;
            }

            @Override // android.content.pm.IPackageInstallObserver
            public void packageInstalled(String packageName, int returnCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPackageInstallObserver.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(returnCode);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
