package top.niunaijun.blackbox.proxy.record;

import android.content.Intent;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/record/ProxyBroadcastRecord.class */
public class ProxyBroadcastRecord {
    public Intent mIntent;
    public int mUserId;

    public ProxyBroadcastRecord(Intent intent, int userId) {
        this.mIntent = intent;
        this.mUserId = userId;
    }

    public static void saveStub(Intent shadow, Intent target, int userId) {
        shadow.putExtra("_B_|_target_", target);
        shadow.putExtra("_B_|_user_id_", userId);
    }

    public static ProxyBroadcastRecord create(Intent intent) {
        Intent target = (Intent) intent.getParcelableExtra("_B_|_target_");
        int userId = intent.getIntExtra("_B_|_user_id_", 0);
        return new ProxyBroadcastRecord(target, userId);
    }

    public String toString() {
        return "ProxyBroadcastRecord{mIntent=" + this.mIntent + ", mUserId=" + this.mUserId + '}';
    }
}
