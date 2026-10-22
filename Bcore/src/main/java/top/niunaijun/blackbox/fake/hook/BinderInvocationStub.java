package top.niunaijun.blackbox.fake.hook;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import black.android.os.BRServiceManager;
import java.io.FileDescriptor;
import java.util.Map;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/hook/BinderInvocationStub.class */
public abstract class BinderInvocationStub extends ClassInvocationStub implements IBinder {
    private IBinder mBaseBinder;

    public BinderInvocationStub(IBinder baseBinder) {
        this.mBaseBinder = baseBinder;
    }

    @Override // top.niunaijun.blackbox.fake.hook.ClassInvocationStub
    protected void onBindMethod() {
    }

    @Override // android.os.IBinder
    @Nullable
    public String getInterfaceDescriptor() throws RemoteException {
        return this.mBaseBinder.getInterfaceDescriptor();
    }

    @Override // android.os.IBinder
    public boolean pingBinder() {
        return this.mBaseBinder.pingBinder();
    }

    @Override // android.os.IBinder
    public boolean isBinderAlive() {
        return this.mBaseBinder.isBinderAlive();
    }

    @Override // android.os.IBinder
    @Nullable
    public IInterface queryLocalInterface(@NonNull String descriptor) {
        return (IInterface) getProxyInvocation();
    }

    @Override // android.os.IBinder
    public void dump(@NonNull FileDescriptor fd, @Nullable String[] args) throws RemoteException {
        this.mBaseBinder.dump(fd, args);
    }

    @Override // android.os.IBinder
    public void dumpAsync(@NonNull FileDescriptor fd, @Nullable String[] args) throws RemoteException {
        this.mBaseBinder.dumpAsync(fd, args);
    }

    @Override // android.os.IBinder
    public boolean transact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        return this.mBaseBinder.transact(code, data, reply, flags);
    }

    @Override // android.os.IBinder
    public void linkToDeath(@NonNull IBinder.DeathRecipient recipient, int flags) throws RemoteException {
        this.mBaseBinder.linkToDeath(recipient, flags);
    }

    @Override // android.os.IBinder
    public boolean unlinkToDeath(@NonNull IBinder.DeathRecipient recipient, int flags) {
        return this.mBaseBinder.unlinkToDeath(recipient, flags);
    }

    protected void replaceSystemService(String name) {
        Map<String, IBinder> services = BRServiceManager.get().sCache();
        services.put(name, this);
    }
}
