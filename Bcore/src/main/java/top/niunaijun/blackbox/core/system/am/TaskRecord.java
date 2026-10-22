package top.niunaijun.blackbox.core.system.am;

import android.content.Intent;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/am/TaskRecord.class */
public class TaskRecord {
    public int id;
    public int userId;
    public String taskAffinity;
    public Intent rootIntent;
    public final List<ActivityRecord> activities = new LinkedList();

    public TaskRecord(int id, int userId, String taskAffinity) {
        this.id = id;
        this.userId = userId;
        this.taskAffinity = taskAffinity;
    }

    public boolean needNewTask() {
        for (ActivityRecord activity : this.activities) {
            if (!activity.finished) {
                return false;
            }
        }
        return true;
    }

    public void addTopActivity(ActivityRecord record) {
        this.activities.add(record);
    }

    public void removeActivity(ActivityRecord record) {
        this.activities.remove(record);
    }

    public ActivityRecord getTopActivityRecord() {
        for (int i = this.activities.size() - 1; i >= 0; i--) {
            ActivityRecord activityRecord = this.activities.get(i);
            if (!activityRecord.finished) {
                return activityRecord;
            }
        }
        return null;
    }
}
