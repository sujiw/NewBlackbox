package top.niunaijun.blackbox.entity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/ServiceRecord.class */
public class ServiceRecord {
    private Service mService;
    private Map<Intent.FilterComparison, BoundInfo> mBounds = new HashMap();
    private boolean rebind;
    private int mStartId;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/entity/ServiceRecord$BoundInfo.class */
    public class BoundInfo {
        private IBinder mIBinder;
        private AtomicInteger mBindCount = new AtomicInteger(0);

        public BoundInfo() {
        }

        public int incrementAndGetBindCount() {
            return this.mBindCount.incrementAndGet();
        }

        public int decrementAndGetBindCount() {
            return this.mBindCount.decrementAndGet();
        }

        public IBinder getIBinder() {
            return this.mIBinder;
        }

        public void setIBinder(IBinder IBinder) {
            this.mIBinder = IBinder;
        }
    }

    public int getStartId() {
        return this.mStartId;
    }

    public void setStartId(int startId) {
        this.mStartId = startId;
    }

    public Service getService() {
        return this.mService;
    }

    public void setService(Service service) {
        this.mService = service;
    }

    public IBinder getBinder(Intent intent) {
        BoundInfo boundInfo = getOrCreateBoundInfo(intent);
        return boundInfo.getIBinder();
    }

    public boolean hasBinder(Intent intent) {
        BoundInfo boundInfo = getOrCreateBoundInfo(intent);
        return boundInfo.getIBinder() != null;
    }

    public void addBinder(Intent intent, final IBinder iBinder) {
        final Intent.FilterComparison filterComparison = new Intent.FilterComparison(intent);
        BoundInfo boundInfo = getOrCreateBoundInfo(intent);
        if (boundInfo == null) {
            boundInfo = new BoundInfo();
            this.mBounds.put(filterComparison, boundInfo);
        }
        boundInfo.setIBinder(iBinder);
        try {
            iBinder.linkToDeath(new IBinder.DeathRecipient() { // from class: top.niunaijun.blackbox.entity.ServiceRecord.1
                @Override // android.os.IBinder.DeathRecipient
                public void binderDied() {
                    iBinder.unlinkToDeath(this, 0);
                    ServiceRecord.this.mBounds.remove(filterComparison);
                }
            }, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int incrementAndGetBindCount(Intent intent) {
        BoundInfo boundInfo = getOrCreateBoundInfo(intent);
        return boundInfo.incrementAndGetBindCount();
    }

    public boolean decreaseConnectionCount(Intent intent) {
        Intent.FilterComparison filterComparison = new Intent.FilterComparison(intent);
        BoundInfo boundInfo = this.mBounds.get(filterComparison);
        if (boundInfo == null) {
            return true;
        }
        int i = boundInfo.decrementAndGetBindCount();
        if (i <= 0) {
            return true;
        }
        return false;
    }

    public BoundInfo getOrCreateBoundInfo(Intent intent) {
        Intent.FilterComparison filterComparison = new Intent.FilterComparison(intent);
        BoundInfo boundInfo = this.mBounds.get(filterComparison);
        if (boundInfo == null) {
            boundInfo = new BoundInfo();
            this.mBounds.put(filterComparison, boundInfo);
        }
        return boundInfo;
    }

    public boolean isRebind() {
        return this.rebind;
    }

    public void setRebind(boolean rebind) {
        this.rebind = rebind;
    }
}
