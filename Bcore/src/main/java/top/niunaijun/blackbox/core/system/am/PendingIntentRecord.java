package top.niunaijun.blackbox.core.system.am;

import java.util.Objects;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/PendingIntentRecord.class */
public class PendingIntentRecord {
    public int uid;
    public String packageName;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PendingIntentRecord)) {
            return false;
        }
        PendingIntentRecord that = (PendingIntentRecord) o;
        return this.uid == that.uid && Objects.equals(this.packageName, that.packageName);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.uid), this.packageName);
    }
}
