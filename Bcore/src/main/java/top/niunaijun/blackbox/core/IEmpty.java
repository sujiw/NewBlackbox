package top.niunaijun.blackbox.core;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/IEmpty.class */
public interface IEmpty extends IInterface {
    public static final String DESCRIPTOR = "top.niunaijun.blackbox.core.IEmpty";

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/IEmpty$Default.class */
    public static class Default implements IEmpty {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/IEmpty$Stub.class */
    public static abstract class Stub extends Binder implements IEmpty {
        public Stub() {
            attachInterface(this, IEmpty.DESCRIPTOR);
        }

        public static IEmpty asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IEmpty.DESCRIPTOR);
            if (iin != null && (iin instanceof IEmpty)) {
                return (IEmpty) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1598968902:
                    reply.writeString(IEmpty.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                    }
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/IEmpty$Stub$Proxy.class */
        private static class Proxy implements IEmpty {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IEmpty.DESCRIPTOR;
            }
        }
    }
}
