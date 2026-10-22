package top.niunaijun.blackbox.proxy;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.entity.AppConfig;
import top.niunaijun.blackbox.utils.compat.BundleCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider.class */
public class ProxyContentProvider extends ContentProvider {
    static final /* synthetic */ boolean $assertionsDisabled;

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P0.class */
    public static class P0 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P1.class */
    public static class P1 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P10.class */
    public static class P10 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P11.class */
    public static class P11 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P12.class */
    public static class P12 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P13.class */
    public static class P13 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P14.class */
    public static class P14 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P15.class */
    public static class P15 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P16.class */
    public static class P16 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P17.class */
    public static class P17 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P18.class */
    public static class P18 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P19.class */
    public static class P19 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P2.class */
    public static class P2 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P20.class */
    public static class P20 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P21.class */
    public static class P21 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P22.class */
    public static class P22 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P23.class */
    public static class P23 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P24.class */
    public static class P24 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P25.class */
    public static class P25 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P26.class */
    public static class P26 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P27.class */
    public static class P27 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P28.class */
    public static class P28 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P29.class */
    public static class P29 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P3.class */
    public static class P3 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P30.class */
    public static class P30 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P31.class */
    public static class P31 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P32.class */
    public static class P32 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P33.class */
    public static class P33 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P34.class */
    public static class P34 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P35.class */
    public static class P35 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P36.class */
    public static class P36 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P37.class */
    public static class P37 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P38.class */
    public static class P38 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P39.class */
    public static class P39 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P4.class */
    public static class P4 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P40.class */
    public static class P40 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P41.class */
    public static class P41 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P42.class */
    public static class P42 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P43.class */
    public static class P43 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P44.class */
    public static class P44 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P45.class */
    public static class P45 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P46.class */
    public static class P46 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P47.class */
    public static class P47 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P48.class */
    public static class P48 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P49.class */
    public static class P49 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P5.class */
    public static class P5 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P6.class */
    public static class P6 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P7.class */
    public static class P7 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P8.class */
    public static class P8 extends ProxyContentProvider {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyContentProvider$P9.class */
    public static class P9 extends ProxyContentProvider {
    }

    static {
        $assertionsDisabled = !ProxyContentProvider.class.desiredAssertionStatus();
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return false;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        if (method.equals("_Black_|_init_process_")) {
            if (!$assertionsDisabled && extras == null) {
                throw new AssertionError();
            }
            extras.setClassLoader(AppConfig.class.getClassLoader());
            AppConfig appConfig = (AppConfig) extras.getParcelable(AppConfig.KEY);
            BlackBoxCore.currentActivityThread().initProcess(appConfig);
            Bundle bundle = new Bundle();
            BundleCompat.putBinder(bundle, "_Black_|_client_", BlackBoxCore.currentActivityThread());
            return bundle;
        }
        return super.call(method, arg, extras);
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
