package top.niunaijun.blackbox.core.system.notification;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.os.Binder;
import android.os.RemoteException;
import black.android.app.BRNotificationChannel;
import black.android.app.BRNotificationChannelGroup;
import black.android.app.BRNotificationO;
import black.android.app.NotificationChannelContext;
import black.android.app.NotificationChannelGroupContext;
import black.android.app.NotificationOContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.core.system.BProcessManagerService;
import top.niunaijun.blackbox.core.system.ISystemService;
import top.niunaijun.blackbox.core.system.ProcessRecord;
import top.niunaijun.blackbox.utils.compat.BuildCompat;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/core/system/notification/BNotificationManagerService.class */
public class BNotificationManagerService extends IBNotificationManagerService.Stub implements ISystemService {
    private static final BNotificationManagerService sService = new BNotificationManagerService();
    public static final String CHANNEL_BLACK = "@black-";
    public static final String GROUP_BLACK = "@black-group-";
    private NotificationChannelManager mNotificationChannelManager;
    private final Map<String, NotificationRecord> mNotificationRecords = new HashMap();
    private final NotificationManager mRealNotificationManager = (NotificationManager) BlackBoxCore.getContext().getSystemService("notification");

    public static BNotificationManagerService get() {
        return sService;
    }

    @Override // top.niunaijun.blackbox.core.system.ISystemService
    public void systemReady() {
        this.mNotificationChannelManager = NotificationChannelManager.get();
    }

    private NotificationRecord getNotificationRecord(String packageName, int userId) {
        NotificationRecord notificationRecord;
        String key = packageName + "-" + userId;
        synchronized (this.mNotificationRecords) {
            NotificationRecord notificationRecord2 = this.mNotificationRecords.get(key);
            if (notificationRecord2 == null) {
                notificationRecord2 = new NotificationRecord();
                this.mNotificationRecords.put(key, notificationRecord2);
            }
            notificationRecord = notificationRecord2;
        }
        return notificationRecord;
    }

    private void removeNotificationRecord(String packageName, int userId) {
        String key = packageName + "-" + userId;
        synchronized (this.mNotificationRecords) {
            this.mNotificationRecords.remove(key);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
    @TargetApi(26)
    public NotificationChannel getNotificationChannel(String channelId, int userId) throws RemoteException {
        NotificationChannel notificationChannel;
        int callingPid = getCallingPid();
        ProcessRecord processByPid = BProcessManagerService.get().findProcessByPid(callingPid);
        if (processByPid == null) {
            return null;
        }
        NotificationRecord notificationRecord = getNotificationRecord(processByPid.getPackageName(), userId);
        synchronized (notificationRecord.mNotificationChannels) {
            notificationChannel = notificationRecord.mNotificationChannels.get(channelId);
        }
        return notificationChannel;
    }

    @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
    public List<NotificationChannel> getNotificationChannels(String packageName, int userId) throws RemoteException {
        ArrayList arrayList;
        NotificationRecord notificationRecord = getNotificationRecord(packageName, userId);
        synchronized (notificationRecord.mNotificationChannels) {
            arrayList = new ArrayList(notificationRecord.mNotificationChannels.values());
        }
        return arrayList;
    }

    @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
    public List<NotificationChannelGroup> getNotificationChannelGroups(String packageName, int userId) throws RemoteException {
        ArrayList arrayList;
        NotificationRecord notificationRecord = getNotificationRecord(packageName, userId);
        synchronized (notificationRecord.mNotificationChannelGroups) {
            arrayList = new ArrayList(notificationRecord.mNotificationChannelGroups.values());
        }
        return arrayList;
    }

    @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
    @TargetApi(26)
    public void createNotificationChannel(NotificationChannel notificationChannel, int userId) {
        int callingPid = getCallingPid();
        ProcessRecord processByPid = BProcessManagerService.get().findProcessByPid(callingPid);
        if (processByPid == null) {
            return;
        }
        handleNotificationChannel(notificationChannel, userId);
        this.mRealNotificationManager.createNotificationChannel(notificationChannel);
        resetNotificationChannel(notificationChannel);
        NotificationRecord notificationRecord = getNotificationRecord(processByPid.getPackageName(), userId);
        synchronized (notificationRecord.mNotificationChannels) {
            notificationRecord.mNotificationChannels.put(notificationChannel.getId(), notificationChannel);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
    @TargetApi(26)
    public void deleteNotificationChannel(String channelId, int userId) {
        int callingPid = getCallingPid();
        ProcessRecord processByPid = BProcessManagerService.get().findProcessByPid(callingPid);
        if (processByPid == null) {
            return;
        }
        NotificationRecord notificationRecord = getNotificationRecord(processByPid.getPackageName(), userId);
        synchronized (notificationRecord.mNotificationChannels) {
            NotificationChannel remove = notificationRecord.mNotificationChannels.remove(channelId);
            if (remove != null) {
                String blackChannelId = getBlackChannelId(remove.getId(), userId);
                this.mRealNotificationManager.deleteNotificationChannel(blackChannelId);
            }
        }
    }

    @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
    @TargetApi(26)
    public void createNotificationChannelGroup(NotificationChannelGroup notificationChannelGroup, int userId) {
        int callingPid = getCallingPid();
        ProcessRecord processByPid = BProcessManagerService.get().findProcessByPid(callingPid);
        if (processByPid == null) {
            return;
        }
        handleNotificationGroup(notificationChannelGroup, userId);
        this.mRealNotificationManager.createNotificationChannelGroup(notificationChannelGroup);
        resetNotificationGroup(notificationChannelGroup);
        NotificationRecord notificationRecord = getNotificationRecord(processByPid.getPackageName(), userId);
        synchronized (notificationRecord.mNotificationChannelGroups) {
            notificationRecord.mNotificationChannelGroups.put(notificationChannelGroup.getId(), notificationChannelGroup);
        }
    }

    @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
    @TargetApi(26)
    public void deleteNotificationChannelGroup(String groupId, int userId) {
        int callingPid = getCallingPid();
        ProcessRecord processByPid = BProcessManagerService.get().findProcessByPid(callingPid);
        if (processByPid == null) {
            return;
        }
        NotificationRecord notificationRecord = getNotificationRecord(processByPid.getPackageName(), userId);
        synchronized (notificationRecord.mNotificationChannelGroups) {
            NotificationChannelGroup remove = notificationRecord.mNotificationChannelGroups.remove(groupId);
            if (remove != null) {
                String blackGroupId = getBlackGroupId(remove.getId(), userId);
                this.mRealNotificationManager.deleteNotificationChannelGroup(blackGroupId);
            }
        }
    }

    @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
    public void enqueueNotificationWithTag(int id, String tag, Notification notification, int userId) {
        ProcessRecord processByPid = BProcessManagerService.get().findProcessByPid(Binder.getCallingPid());
        if (processByPid == null) {
            return;
        }
        int notificationId = getNotificationId(userId, id, processByPid.getPackageName());
        if (BuildCompat.isOreo()) {
            NotificationOContext notificationOContext = BRNotificationO.get(notification);
            if (notificationOContext._check_mChannelId() != null) {
                String blackChannelId = getBlackChannelId(notificationOContext.mChannelId(), userId);
                notificationOContext._set_mChannelId(blackChannelId);
            }
            if (notificationOContext._check_mGroupKey() != null) {
                String blackGroupId = getBlackGroupId(notificationOContext.mGroupKey(), userId);
                notificationOContext._set_mGroupKey(blackGroupId);
            }
        }
        NotificationRecord notificationRecord = getNotificationRecord(processByPid.getPackageName(), userId);
        synchronized (notificationRecord.mIds) {
            notificationRecord.mIds.add(Integer.valueOf(notificationId));
        }
        this.mRealNotificationManager.notify(notificationId, notification);
    }

    @Override // top.niunaijun.blackbox.core.system.notification.IBNotificationManagerService
    public void cancelNotificationWithTag(int id, String tag, int userId) throws RemoteException {
        ProcessRecord processByPid = BProcessManagerService.get().findProcessByPid(Binder.getCallingPid());
        if (processByPid == null) {
            return;
        }
        int notificationId = getNotificationId(userId, id, processByPid.getPackageName());
        this.mRealNotificationManager.cancel(notificationId);
        NotificationRecord notificationRecord = getNotificationRecord(processByPid.getPackageName(), userId);
        synchronized (notificationRecord.mIds) {
            notificationRecord.mIds.remove(Integer.valueOf(notificationId));
        }
    }

    @TargetApi(26)
    private void handleNotificationChannel(NotificationChannel notificationChannel, int userId) {
        NotificationChannelContext channelContext = BRNotificationChannel.get(notificationChannel);
        String channelId = channelContext.mId();
        String blackChannelId = getBlackChannelId(channelId, userId);
        channelContext._set_mId(blackChannelId);
        notificationChannel.setGroup(getBlackGroupId(notificationChannel.getGroup(), userId));
    }

    private void resetNotificationChannel(NotificationChannel notificationChannel) {
        NotificationChannelContext channelContext = BRNotificationChannel.get(notificationChannel);
        String channelId = channelContext.mId();
        String realChannelId = getRealChannelId(channelId);
        channelContext._set_mId(realChannelId);
    }

    private void handleNotificationGroup(NotificationChannelGroup notificationChannelGroup, int userId) {
        NotificationChannelGroupContext groupContext = BRNotificationChannelGroup.get(notificationChannelGroup);
        String groupId = groupContext.mId();
        String blackGroupId = getBlackGroupId(groupId, userId);
        groupContext._set_mId(blackGroupId);
        List<NotificationChannel> notificationChannels = groupContext.mChannels();
        if (notificationChannels != null) {
            for (NotificationChannel notificationChannel : notificationChannels) {
                createNotificationChannel(notificationChannel, userId);
            }
        }
    }

    private void resetNotificationGroup(NotificationChannelGroup notificationChannelGroup) {
        NotificationChannelGroupContext groupContext = BRNotificationChannelGroup.get(notificationChannelGroup);
        String groupId = groupContext.mId();
        String realGroupId = getRealGroupId(groupId);
        groupContext._set_mId(realGroupId);
        List<NotificationChannel> notificationChannels = groupContext.mChannels();
        if (notificationChannels != null) {
            for (NotificationChannel notificationChannel : notificationChannels) {
                resetNotificationChannel(notificationChannel);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public void deletePackageNotification(String packageName, int userId) {
        NotificationRecord notificationRecord = getNotificationRecord(packageName, userId);
        if (BuildCompat.isOreo()) {
            for (NotificationChannelGroup value : notificationRecord.mNotificationChannelGroups.values()) {
                String blackGroupId = getBlackGroupId(value.getId(), userId);
                this.mRealNotificationManager.deleteNotificationChannelGroup(blackGroupId);
            }
            for (NotificationChannel value2 : notificationRecord.mNotificationChannels.values()) {
                String blackChannelId = getBlackChannelId(value2.getId(), userId);
                this.mRealNotificationManager.deleteNotificationChannel(blackChannelId);
            }
        }
        for (Integer id : notificationRecord.mIds) {
            this.mRealNotificationManager.cancel(id.intValue());
        }
        removeNotificationRecord(packageName, userId);
    }

    private String getBlackChannelId(String channelId, int userId) {
        if (channelId == null || channelId.contains(CHANNEL_BLACK)) {
            return channelId;
        }
        return channelId + CHANNEL_BLACK + userId;
    }

    private String getRealChannelId(String channelId) {
        if (channelId == null || !channelId.contains(CHANNEL_BLACK)) {
            return channelId;
        }
        return channelId.split(CHANNEL_BLACK)[0];
    }

    private String getBlackGroupId(String groupId, int userId) {
        if (groupId == null || groupId.contains(GROUP_BLACK)) {
            return groupId;
        }
        return groupId + GROUP_BLACK + userId;
    }

    private String getRealGroupId(String groupId) {
        if (groupId == null || !groupId.contains(GROUP_BLACK)) {
            return groupId;
        }
        return groupId.split(GROUP_BLACK)[0];
    }

    public static int getNotificationId(int userId, int notificationId, String packageName) {
        return (packageName + userId + notificationId).hashCode();
    }
}
