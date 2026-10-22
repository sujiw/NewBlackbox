package android.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ProviderInfo;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArrayMap;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.jar:android/app/ActivityThread.class */
public class ActivityThread {
    public H mH = null;
    public AppBindData mBoundApplication;
    public Application mInitialApplication;
    public Instrumentation mInstrumentation;
    public Map<String, WeakReference<?>> mPackages;
    public Map<IBinder, ActivityClientRecord> mActivities;
    public ArrayMap<ProviderKey, Object> mProviderMap;

    /* JADX INFO: loaded from: classes.jar:android/app/ActivityThread$ActivityClientRecord.class */
    public static final class ActivityClientRecord {
        public Activity activity;
        public IBinder token;
        public ActivityInfo activityInfo;
        public Intent intent;
    }

    /* JADX INFO: loaded from: classes.jar:android/app/ActivityThread$H.class */
    static class H extends Handler {
        H() {
        }
    }

    public static ActivityThread currentActivityThread() {
        throw new RuntimeException();
    }

    public String getProcessName() {
        throw new RuntimeException();
    }

    public Handler getHandler() {
        throw new RuntimeException();
    }

    public ContentProviderHolder installProvider(Context context, ContentProviderHolder holder, ProviderInfo info, boolean noisy, boolean noReleaseNeeded, boolean stable) {
        throw new RuntimeException();
    }

    /* JADX INFO: loaded from: classes.jar:android/app/ActivityThread$AppBindData.class */
    static final class AppBindData {
        AppBindData() {
        }
    }

    /* JADX INFO: loaded from: classes.jar:android/app/ActivityThread$ProviderKey.class */
    public static final class ProviderKey {
        public final String authority;
        public final int userId;

        public ProviderKey(String authority, int userId) {
            this.authority = authority;
            this.userId = userId;
        }

        public boolean equals(Object o) {
            if (o instanceof ProviderKey) {
                ProviderKey other = (ProviderKey) o;
                return Objects.equals(this.authority, other.authority) && this.userId == other.userId;
            }
            return false;
        }

        public int hashCode() {
            return (this.authority != null ? this.authority.hashCode() : 0) ^ this.userId;
        }
    }
}
