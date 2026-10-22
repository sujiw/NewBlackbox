package top.niunaijun.blackbox.fake.frameworks;

import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.utils.Reflector;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/fake/frameworks/BlackManager.class */
public abstract class BlackManager<Service extends IInterface> {
    public static final String TAG = "BlackManager";
    private Service mService;
    private final AtomicBoolean mServiceCreationFailed = new AtomicBoolean(false);
    private long mLastRetryTime = 0;
    private long mLastServiceCreationTime = 0;
    private static final long RETRY_TIMEOUT_MS = 2000;
    private static final long MIN_SERVICE_CREATION_INTERVAL_MS = 50;
    private static final long GLOBAL_FAILURE_RESET_INTERVAL_MS = 30000;
    private static final AtomicInteger globalServiceFailureCount = new AtomicInteger(0);
    private static long lastGlobalFailureReset = 0;

    protected abstract String getServiceName();

    public Service getService() {
        if (this.mServiceCreationFailed.get()) {
            if (System.currentTimeMillis() - this.mLastRetryTime < RETRY_TIMEOUT_MS) {
                Log.d(TAG, "Skipping service creation for " + getServiceName() + " due to recent failure");
                return null;
            }
            this.mServiceCreationFailed.set(false);
        }
        if (this.mService != null && this.mService.asBinder().pingBinder() && this.mService.asBinder().isBinderAlive()) {
            return this.mService;
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.mLastServiceCreationTime < MIN_SERVICE_CREATION_INTERVAL_MS) {
            Log.d(TAG, "Rate limiting service creation for " + getServiceName());
            return this.mService;
        }
        try {
            IBinder binder = BlackBoxCore.get().getService(getServiceName());
            if (binder == null) {
                Log.w(TAG, "Failed to get binder for service: " + getServiceName());
                markServiceCreationFailed();
                return null;
            }
            if (!binder.isBinderAlive()) {
                Log.w(TAG, "Binder is not alive for service: " + getServiceName());
                markServiceCreationFailed();
                return null;
            }
            String stubClassName = getTClass().getName() + "$Stub";
            Log.d(TAG, "Creating service for: " + stubClassName);
            this.mService = (Service) Reflector.on(stubClassName).method("asInterface", IBinder.class).call(binder);
            if (this.mService != null) {
                try {
                    if (!this.mService.asBinder().isBinderAlive()) {
                        Log.w(TAG, "Service binder is not alive after creation: " + getServiceName());
                        this.mService = null;
                        markServiceCreationFailed();
                        return null;
                    }
                    final Service serviceRef = this.mService;
                    try {
                        serviceRef.asBinder().linkToDeath(new IBinder.DeathRecipient() { // from class: top.niunaijun.blackbox.fake.frameworks.BlackManager.1
                            @Override // android.os.IBinder.DeathRecipient
                            public void binderDied() {
                                try {
                                    if (serviceRef != null && serviceRef.asBinder() != null) {
                                        serviceRef.asBinder().unlinkToDeath(this, 0);
                                    }
                                } catch (Exception e) {
                                    Log.w(BlackManager.TAG, "Error unlinking death recipient for " + BlackManager.this.getServiceName(), e);
                                }
                                BlackManager.this.mService = null;
                                Log.w(BlackManager.TAG, "Service died: " + BlackManager.this.getServiceName());
                            }
                        }, 0);
                    } catch (Exception e) {
                        Log.w(TAG, "Error linking death recipient for " + getServiceName(), e);
                    }
                    Log.d(TAG, "Successfully created service: " + getServiceName());
                    this.mServiceCreationFailed.set(false);
                    this.mLastServiceCreationTime = currentTime;
                } catch (Exception e2) {
                    Log.w(TAG, "Error checking service binder health: " + getServiceName(), e2);
                    this.mService = null;
                    markServiceCreationFailed();
                    return null;
                }
                return this.mService;
            }
            Log.w(TAG, "Failed to create service instance for: " + getServiceName());
            markServiceCreationFailed();
            return this.mService;
        } catch (Throwable e3) {
            Log.e(TAG, "Error creating service for " + getServiceName(), e3);
            markServiceCreationFailed();
            return null;
        }
    }

    private void markServiceCreationFailed() {
        this.mServiceCreationFailed.set(true);
        this.mLastRetryTime = System.currentTimeMillis();
    }

    public void clearServiceCache() {
        this.mService = null;
        Log.d(TAG, "Cleared service cache for " + getServiceName());
    }

    public boolean isServiceHealthy() {
        if (this.mService == null) {
            return false;
        }
        try {
            return this.mService.asBinder().pingBinder() && this.mService.asBinder().isBinderAlive();
        } catch (Exception e) {
            Log.w(TAG, "Service health check failed for " + getServiceName(), e);
            return false;
        }
    }

    private Class<Service> getTClass() {
        return (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
