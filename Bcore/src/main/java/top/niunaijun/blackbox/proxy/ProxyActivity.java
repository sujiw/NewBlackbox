package top.niunaijun.blackbox.proxy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.fake.hook.HookManager;
import top.niunaijun.blackbox.fake.service.HCallbackProxy;
import top.niunaijun.blackbox.proxy.record.ProxyActivityRecord;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity.class */
public class ProxyActivity extends Activity {
    public static final String TAG = "ProxyActivity";

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P0.class */
    public static class P0 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P1.class */
    public static class P1 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P10.class */
    public static class P10 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P11.class */
    public static class P11 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P12.class */
    public static class P12 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P13.class */
    public static class P13 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P14.class */
    public static class P14 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P15.class */
    public static class P15 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P16.class */
    public static class P16 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P17.class */
    public static class P17 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P18.class */
    public static class P18 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P19.class */
    public static class P19 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P2.class */
    public static class P2 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P20.class */
    public static class P20 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P21.class */
    public static class P21 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P22.class */
    public static class P22 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P23.class */
    public static class P23 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P24.class */
    public static class P24 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P25.class */
    public static class P25 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P26.class */
    public static class P26 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P27.class */
    public static class P27 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P28.class */
    public static class P28 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P29.class */
    public static class P29 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P3.class */
    public static class P3 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P30.class */
    public static class P30 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P31.class */
    public static class P31 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P32.class */
    public static class P32 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P33.class */
    public static class P33 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P34.class */
    public static class P34 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P35.class */
    public static class P35 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P36.class */
    public static class P36 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P37.class */
    public static class P37 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P38.class */
    public static class P38 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P39.class */
    public static class P39 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P4.class */
    public static class P4 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P40.class */
    public static class P40 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P41.class */
    public static class P41 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P42.class */
    public static class P42 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P43.class */
    public static class P43 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P44.class */
    public static class P44 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P45.class */
    public static class P45 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P46.class */
    public static class P46 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P47.class */
    public static class P47 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P48.class */
    public static class P48 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P49.class */
    public static class P49 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P5.class */
    public static class P5 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P6.class */
    public static class P6 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P7.class */
    public static class P7 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P8.class */
    public static class P8 extends ProxyActivity {
    }

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/ProxyActivity$P9.class */
    public static class P9 extends ProxyActivity {
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        finish();
        HookManager.get().checkEnv(HCallbackProxy.class);
        ProxyActivityRecord record = ProxyActivityRecord.create(getIntent());
        if (record.mTarget != null) {
            record.mTarget.setExtrasClassLoader(BlackBoxCore.getApplication().getClassLoader());
            startActivity(record.mTarget);
        }
    }
}
