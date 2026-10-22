package top.niunaijun.blackbox.utils.compat;

import android.content.Context;
import android.content.ContextWrapper;
import black.android.app.BRContextImpl;
import black.android.app.BRContextImplKitkat;
import black.android.content.AttributionSourceStateContext;
import black.android.content.BRAttributionSource;
import black.android.content.BRAttributionSourceState;
import black.android.content.BRContentResolver;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.utils.Slog;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/compat/ContextCompat.class */
public class ContextCompat {
    public static final String TAG = "ContextCompat";

    public static void fixAttributionSourceState(Object obj, int uid) {
        if (obj != null && BRAttributionSource.get(obj)._check_mAttributionSourceState() != null) {
            Object mAttributionSourceState = BRAttributionSource.get(obj).mAttributionSourceState();
            AttributionSourceStateContext attributionSourceStateContext = BRAttributionSourceState.get(mAttributionSourceState);
            attributionSourceStateContext._set_packageName(BlackBoxCore.getHostPkg());
            attributionSourceStateContext._set_uid(Integer.valueOf(uid));
            fixAttributionSourceState(BRAttributionSource.get(obj).getNext(), uid);
        }
    }

    public static void fix(Context context) {
        try {
            if (context == null) {
                Slog.w(TAG, "Context is null, skipping ContextCompat.fix");
                return;
            }
            if (BlackBoxCore.getAppConfig() == null) {
                // Host / server process: the app config is not bound, so
                // BActivityThread.getBUid() falls back to FIRST_APPLICATION_UID.
                // Rewriting the attribution source to that uid no longer matches
                // the real calling uid and makes system providers (settings,
                // which TextKeyListener and accessibility read) throw
                // "Calling uid ... doesn't match source uid ...".
                return;
            }
            int deep = 0;
            while (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
                deep++;
                if (deep >= 10) {
                    return;
                }
            }
            if (context == null) {
                Slog.w(TAG, "Base context is null after unwrapping, skipping ContextCompat.fix");
                return;
            }
            BRContextImpl.get(context)._set_mPackageManager(null);
            try {
                context.getPackageManager();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            BRContextImpl.get(context)._set_mBasePackageName(BlackBoxCore.getHostPkg());
            BRContextImplKitkat.get(context)._set_mOpPackageName(BlackBoxCore.getHostPkg());
            try {
                BRContentResolver.get(context.getContentResolver())._set_mPackageName(BlackBoxCore.getHostPkg());
            } catch (Exception e2) {
                Slog.w(TAG, "Failed to fix content resolver: " + e2.getMessage());
            }
            if (BuildCompat.isS()) {
                try {
                    fixAttributionSourceState(BRContextImpl.get(context).getAttributionSource(), BlackBoxCore.getBUid());
                } catch (Exception e3) {
                    Slog.w(TAG, "Failed to fix attribution source state: " + e3.getMessage());
                }
            }
        } catch (Exception e4) {
            Slog.e(TAG, "Error in ContextCompat.fix: " + e4.getMessage());
            e4.printStackTrace();
        }
    }
}
