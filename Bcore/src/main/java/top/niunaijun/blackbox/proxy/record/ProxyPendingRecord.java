package top.niunaijun.blackbox.proxy.record;

import android.content.Intent;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/proxy/record/ProxyPendingRecord.class */
public class ProxyPendingRecord {
    public int mUserId;
    public Intent mTarget;

    public ProxyPendingRecord(Intent target, int userId) {
        this.mUserId = userId;
        this.mTarget = target;
    }

    public static void saveStub(Intent shadow, Intent target, int userId) {
        shadow.putExtra("_B_|_P_user_id_", userId);
        shadow.putExtra("_B_|_P_target_", target);
    }

    public static ProxyPendingRecord create(Intent intent) {
        int userId = intent.getIntExtra("_B_|_P_user_id_", 0);
        Intent target = (Intent) intent.getParcelableExtra("_B_|_P_target_");
        return new ProxyPendingRecord(target, userId);
    }

    public String toString() {
        return "ProxyPendingActivityRecord{mUserId=" + this.mUserId + ", mTarget=" + this.mTarget + '}';
    }
}
