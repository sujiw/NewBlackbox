package top.niunaijun.blackbox.utils;

import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/TransactionThrottler.class */
public class TransactionThrottler {
    private static final String TAG = "TransactionThrottler";
    private static final int MAX_FAILURES_PER_WINDOW = 50;
    private static final long WINDOW_DURATION_MS = 15000;
    private static final long THROTTLE_DURATION_MS = 1000;
    private final AtomicInteger failureCount = new AtomicInteger(0);
    private final AtomicLong windowStartTime = new AtomicLong(0);
    private final AtomicLong lastThrottleTime = new AtomicLong(0);

    public boolean shouldThrottle() {
        long currentTime = System.currentTimeMillis();
        long windowStart = this.windowStartTime.get();
        if (currentTime - windowStart > WINDOW_DURATION_MS) {
            this.failureCount.set(0);
            this.windowStartTime.set(currentTime);
            return false;
        }
        long lastThrottle = this.lastThrottleTime.get();
        if (currentTime - lastThrottle < 1000) {
            return true;
        }
        if (this.failureCount.get() >= 50) {
            this.lastThrottleTime.set(currentTime);
            Log.w(TAG, "Throttling transactions due to " + this.failureCount.get() + " failures in window");
            return true;
        }
        return false;
    }

    public void recordFailure() {
        long currentTime = System.currentTimeMillis();
        long windowStart = this.windowStartTime.get();
        if (windowStart == 0) {
            this.windowStartTime.set(currentTime);
        }
        if (currentTime - windowStart > WINDOW_DURATION_MS) {
            this.failureCount.set(1);
            this.windowStartTime.set(currentTime);
        } else {
            this.failureCount.incrementAndGet();
        }
        Log.d(TAG, "Recorded transaction failure, count: " + this.failureCount.get());
    }

    public void reset() {
        this.failureCount.set(0);
        this.windowStartTime.set(0L);
        this.lastThrottleTime.set(0L);
        Log.d(TAG, "Reset transaction throttler");
    }

    public int getFailureCount() {
        return this.failureCount.get();
    }
}
