package top.niunaijun.blackbox.core.system.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.AtomicFile;
import android.util.SparseArray;
import black.android.location.BRILocationListener;
import black.android.location.BRILocationListenerStub;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.env.BEnvironment;
import top.niunaijun.blackbox.core.system.ISystemService;
import top.niunaijun.blackbox.entity.location.BCell;
import top.niunaijun.blackbox.entity.location.BLocation;
import top.niunaijun.blackbox.entity.location.BLocationConfig;
import top.niunaijun.blackbox.utils.CloseUtils;
import top.niunaijun.blackbox.utils.FileUtils;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/location/BLocationManagerService.class */
public class BLocationManagerService extends IBLocationManagerService.Stub implements ISystemService {
    public static final String TAG = "BLocationManagerService";
    private static final BLocationManagerService sService = new BLocationManagerService();
    private final SparseArray<HashMap<String, BLocationConfig>> mLocationConfigs = new SparseArray<>();
    private final BLocationConfig mGlobalConfig = new BLocationConfig();
    private final Map<IBinder, LocationRecord> mLocationListeners = new HashMap();
    private final Executor mThreadPool = Executors.newCachedThreadPool();

    public static BLocationManagerService get() {
        return sService;
    }

    private BLocationConfig getOrCreateConfig(int userId, String pkg) {
        BLocationConfig bLocationConfig;
        synchronized (this.mLocationConfigs) {
            HashMap<String, BLocationConfig> pkgs = this.mLocationConfigs.get(userId);
            if (pkgs == null) {
                pkgs = new HashMap<>();
                this.mLocationConfigs.put(userId, pkgs);
            }
            BLocationConfig config = pkgs.get(pkg);
            if (config == null) {
                config = new BLocationConfig();
                config.pattern = 0;
                pkgs.put(pkg, config);
            }
            bLocationConfig = config;
        }
        return bLocationConfig;
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public int getPattern(int userId, String pkg) {
        int i;
        synchronized (this.mLocationConfigs) {
            BLocationConfig config = getOrCreateConfig(userId, pkg);
            i = config.pattern;
        }
        return i;
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public void setPattern(int userId, String pkg, int pattern) {
        synchronized (this.mLocationConfigs) {
            getOrCreateConfig(userId, pkg).pattern = pattern;
            save();
        }
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public void setCell(int userId, String pkg, BCell cell) {
        synchronized (this.mLocationConfigs) {
            getOrCreateConfig(userId, pkg).cell = cell;
            save();
        }
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public void setAllCell(int userId, String pkg, List<BCell> cells) {
        synchronized (this.mLocationConfigs) {
            getOrCreateConfig(userId, pkg).allCell = cells;
            save();
        }
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public void setNeighboringCell(int userId, String pkg, List<BCell> cells) {
        synchronized (this.mLocationConfigs) {
            getOrCreateConfig(userId, pkg).allCell = cells;
            save();
        }
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public List<BCell> getNeighboringCell(int userId, String pkg) {
        List<BCell> list;
        synchronized (this.mLocationConfigs) {
            list = getOrCreateConfig(userId, pkg).allCell;
        }
        return list;
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public void setGlobalCell(BCell cell) {
        synchronized (this.mGlobalConfig) {
            this.mGlobalConfig.cell = cell;
            save();
        }
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public void setGlobalAllCell(List<BCell> cells) {
        synchronized (this.mGlobalConfig) {
            this.mGlobalConfig.allCell = cells;
            save();
        }
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public void setGlobalNeighboringCell(List<BCell> cells) {
        synchronized (this.mGlobalConfig) {
            this.mGlobalConfig.neighboringCellInfo = cells;
            save();
        }
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public List<BCell> getGlobalNeighboringCell() {
        List<BCell> list;
        synchronized (this.mGlobalConfig) {
            list = this.mGlobalConfig.neighboringCellInfo;
        }
        return list;
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public BCell getCell(int userId, String pkg) {
        BLocationConfig config = getOrCreateConfig(userId, pkg);
        switch (config.pattern) {
            case 0:
            default:
                return null;
            case 1:
                return this.mGlobalConfig.cell;
            case 2:
                return config.cell;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public List<BCell> getAllCell(int userId, String pkg) {
        BLocationConfig config = getOrCreateConfig(userId, pkg);
        switch (config.pattern) {
            case 0:
            default:
                return null;
            case 1:
                return this.mGlobalConfig.allCell;
            case 2:
                return config.allCell;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public void setLocation(int userId, String pkg, BLocation location) {
        synchronized (this.mLocationConfigs) {
            getOrCreateConfig(userId, pkg).location = location;
            save();
        }
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public BLocation getLocation(int userId, String pkg) {
        BLocationConfig config = getOrCreateConfig(userId, pkg);
        switch (config.pattern) {
            case 0:
            default:
                return null;
            case 1:
                return this.mGlobalConfig.location;
            case 2:
                return config.location;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public void setGlobalLocation(BLocation location) {
        synchronized (this.mGlobalConfig) {
            this.mGlobalConfig.location = location;
            save();
        }
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public BLocation getGlobalLocation() {
        BLocation bLocation;
        synchronized (this.mGlobalConfig) {
            bLocation = this.mGlobalConfig.location;
        }
        return bLocation;
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public void requestLocationUpdates(final IBinder listener, String packageName, int userId) throws RemoteException {
        if (listener == null || !listener.pingBinder() || this.mLocationListeners.containsKey(listener)) {
            return;
        }
        listener.linkToDeath(new IBinder.DeathRecipient() { // from class: top.niunaijun.blackbox.core.system.location.BLocationManagerService.1
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                listener.unlinkToDeath(this, 0);
                BLocationManagerService.this.mLocationListeners.remove(listener);
            }
        }, 0);
        LocationRecord record = new LocationRecord(packageName, userId);
        this.mLocationListeners.put(listener, record);
        addTask(listener);
    }

    @Override // top.niunaijun.blackbox.core.system.location.IBLocationManagerService
    public void removeUpdates(IBinder listener) throws RemoteException {
        if (listener == null || !listener.pingBinder()) {
            return;
        }
        this.mLocationListeners.remove(listener);
    }

    private void addTask(IBinder locationListener) {
        this.mThreadPool.execute(() -> {
            BLocation location;
            BLocation lastLocation = null;
            long l = System.currentTimeMillis();
            while (locationListener.pingBinder()) {
                final IInterface iInterface = BRILocationListenerStub.get().asInterface(locationListener);
                LocationRecord locationRecord = this.mLocationListeners.get(locationListener);
                if (locationRecord != null && (location = getLocation(locationRecord.userId, locationRecord.packageName)) != null) {
                    if (location.equals(lastLocation) && System.currentTimeMillis() - l < 3000) {
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                        }
                    } else {
                        lastLocation = location;
                        l = System.currentTimeMillis();
                        final BLocation currentLocation = location;
                        BlackBoxCore.get().getHandler().post(() -> {
                            BRILocationListener.get(iInterface).onLocationChanged(currentLocation.convert2SystemLocation());
                        });
                    }
                }
            }
        });
    }

    public void save() {
        synchronized (this.mGlobalConfig) {
            synchronized (this.mLocationConfigs) {
                Parcel parcel = Parcel.obtain();
                AtomicFile atomicFile = new AtomicFile(BEnvironment.getFakeLocationConf());
                FileOutputStream fileOutputStream = null;
                try {
                    try {
                        this.mGlobalConfig.writeToParcel(parcel, 0);
                        parcel.writeInt(this.mLocationConfigs.size());
                        for (int i = 0; i < this.mLocationConfigs.size(); i++) {
                            int tmpUserId = this.mLocationConfigs.keyAt(i);
                            HashMap<String, BLocationConfig> configArrayMap = this.mLocationConfigs.valueAt(i);
                            parcel.writeInt(tmpUserId);
                            parcel.writeMap(configArrayMap);
                        }
                        parcel.setDataPosition(0);
                        fileOutputStream = atomicFile.startWrite();
                        FileUtils.writeParcelToOutput(parcel, fileOutputStream);
                        atomicFile.finishWrite(fileOutputStream);
                        parcel.recycle();
                        CloseUtils.close(fileOutputStream);
                    } catch (Throwable e) {
                        e.printStackTrace();
                        atomicFile.failWrite(fileOutputStream);
                        parcel.recycle();
                        CloseUtils.close(fileOutputStream);
                    }
                } catch (Throwable th) {
                    parcel.recycle();
                    CloseUtils.close(fileOutputStream);
                    throw th;
                }
            }
        }
    }

    public void loadConfig() {
        Parcel parcel = Parcel.obtain();
        InputStream is = null;
        try {
            try {
                File fakeLocationConf = BEnvironment.getFakeLocationConf();
                if (!fakeLocationConf.exists()) {
                    parcel.recycle();
                    return;
                }
                is = new FileInputStream(BEnvironment.getFakeLocationConf());
                byte[] bytes = FileUtils.toByteArray(is);
                parcel.unmarshall(bytes, 0, bytes.length);
                parcel.setDataPosition(0);
                synchronized (this.mGlobalConfig) {
                    this.mGlobalConfig.refresh(parcel);
                }
                synchronized (this.mLocationConfigs) {
                    this.mLocationConfigs.clear();
                    int size = parcel.readInt();
                    for (int i = 0; i < size; i++) {
                        int userId = parcel.readInt();
                        HashMap<String, BLocationConfig> configArrayMap = parcel.readHashMap(BLocationConfig.class.getClassLoader());
                        this.mLocationConfigs.put(userId, configArrayMap);
                        Slog.d(TAG, "load userId: " + userId + ", config: " + configArrayMap);
                    }
                }
                parcel.recycle();
                CloseUtils.close(is);
            } catch (Exception e) {
                e.printStackTrace();
                Slog.d(TAG, "bad config");
                FileUtils.deleteDir(BEnvironment.getFakeLocationConf());
                parcel.recycle();
                CloseUtils.close(is);
            }
        } catch (Throwable th) {
            parcel.recycle();
            CloseUtils.close(is);
            throw th;
        }
    }

    @Override // top.niunaijun.blackbox.core.system.ISystemService
    public void systemReady() {
        loadConfig();
        for (IBinder iBinder : this.mLocationListeners.keySet()) {
            addTask(iBinder);
        }
    }
}
