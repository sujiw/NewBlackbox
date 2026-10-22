package top.niunaijun.blackbox.utils.compat;

import android.app.ActivityManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import java.util.Locale;
import top.niunaijun.blackbox.BlackBoxCore;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/compat/TaskDescriptionCompat.class */
public class TaskDescriptionCompat {
    public static ActivityManager.TaskDescription fix(ActivityManager.TaskDescription td) {
        String label = td.getLabel();
        Bitmap icon = td.getIcon();
        if (label != null && icon != null) {
            return td;
        }
        String label2 = getTaskDescriptionLabel(BlackBoxCore.getUserId(), getApplicationLabel());
        return new ActivityManager.TaskDescription(label2, (Bitmap) null, td.getPrimaryColor());
    }

    public static String getTaskDescriptionLabel(int userId, CharSequence label) {
        return String.format(Locale.CHINA, "[B%d]%s", Integer.valueOf(userId), label);
    }

    private static CharSequence getApplicationLabel() {
        try {
            PackageManager pm = BlackBoxCore.getPackageManager();
            return pm.getApplicationLabel(pm.getApplicationInfo(BlackBoxCore.getAppPackageName(), 0));
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    private static Drawable getApplicationIcon() {
        return null;
    }
}
