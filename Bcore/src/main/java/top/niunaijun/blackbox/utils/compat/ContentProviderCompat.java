package top.niunaijun.blackbox.utils.compat;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/compat/ContentProviderCompat.class */
public class ContentProviderCompat {
    public static Bundle call(Context context, Uri uri, String method, String arg, Bundle extras, int retryCount) throws IllegalAccessException {
        if (Build.VERSION.SDK_INT < 17) {
            return context.getContentResolver().call(uri, method, arg, extras);
        }
        ContentProviderClient client = acquireContentProviderClientRetry(context, uri, retryCount);
        try {
            try {
                if (client == null) {
                    throw new IllegalAccessException();
                }
                Bundle bundleCall = client.call(method, arg, extras);
                releaseQuietly(client);
                return bundleCall;
            } catch (RemoteException e) {
                throw new IllegalAccessException(e.getMessage());
            }
        } catch (Throwable th) {
            releaseQuietly(client);
            throw th;
        }
    }

    private static ContentProviderClient acquireContentProviderClient(Context context, Uri uri) {
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                return context.getContentResolver().acquireUnstableContentProviderClient(uri);
            }
            return context.getContentResolver().acquireContentProviderClient(uri);
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ContentProviderClient acquireContentProviderClientRetry(Context context, Uri uri, int retryCount) {
        ContentProviderClient client = acquireContentProviderClient(context, uri);
        if (client == null) {
            int retry = 0;
            while (retry < retryCount && client == null) {
                SystemClock.sleep(400L);
                retry++;
                client = acquireContentProviderClient(context, uri);
            }
        }
        return client;
    }

    public static ContentProviderClient acquireContentProviderClientRetry(Context context, String name, int retryCount) {
        ContentProviderClient client = acquireContentProviderClient(context, name);
        if (client == null) {
            int retry = 0;
            while (retry < retryCount && client == null) {
                SystemClock.sleep(400L);
                retry++;
                client = acquireContentProviderClient(context, name);
            }
        }
        return client;
    }

    private static ContentProviderClient acquireContentProviderClient(Context context, String name) {
        if (Build.VERSION.SDK_INT >= 16) {
            return context.getContentResolver().acquireUnstableContentProviderClient(name);
        }
        return context.getContentResolver().acquireContentProviderClient(name);
    }

    private static void releaseQuietly(ContentProviderClient client) {
        if (client != null) {
            try {
                if (Build.VERSION.SDK_INT >= 24) {
                    client.close();
                } else {
                    client.release();
                }
            } catch (Exception e) {
            }
        }
    }
}
