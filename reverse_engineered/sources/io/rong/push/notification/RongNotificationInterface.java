package io.rong.push.notification;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.avos.avoscloud.AVStatus;
import com.baidu.mapapi.UIMsg.m_AppUI;
import io.rong.push.PushConst;
import io.rong.push.RongPushClient.ConversationType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.protocol.HttpRequestExecutor;

public class RongNotificationInterface {
    private static final int NEGLECT_TIME = 1000;
    private static final int NEW_NOTIFICATION_LEVEL = 11;
    private static int NOTIFICATION_ID = 1000;
    private static final int PUSH_REQUEST_CODE = 200;
    private static int PUSH_SERVICE_NOTIFICATION_ID = m_AppUI.MSG_APP_DATA_OK;
    private static int VOIP_NOTIFICATION_ID = HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE;
    private static boolean isInNeglectTime = false;
    private static HashMap<String, List<PushNotificationMessage>> messageCache = new HashMap();
    private static Timer timer = new Timer();

    /* renamed from: io.rong.push.notification.RongNotificationInterface$1 */
    static class C54121 extends TimerTask {
        C54121() {
        }

        public void run() {
            RongNotificationInterface.isInNeglectTime = false;
        }
    }

    private enum SoundType {
        DEFAULT(0),
        SILENT(1),
        VOIP(2);
        
        int value;

        private SoundType(int i) {
            this.value = i;
        }
    }

    public static void sendNotification(Context context, PushNotificationMessage pushNotificationMessage) {
        if (messageCache == null) {
            messageCache = new HashMap();
        }
        ConversationType conversationType = pushNotificationMessage.getConversationType();
        String objectName = pushNotificationMessage.getObjectName();
        String str = "";
        boolean z = false;
        SoundType soundType = SoundType.DEFAULT;
        if (!TextUtils.isEmpty(objectName) && conversationType != null) {
            SoundType soundType2;
            String pushContent;
            int i;
            int i2;
            if (isInNeglectTime) {
                soundType2 = SoundType.SILENT;
            } else {
                soundType2 = soundType;
            }
            int i3;
            if (conversationType.equals(ConversationType.SYSTEM) || conversationType.equals(ConversationType.PUSH_SERVICE)) {
                CharSequence pushTitle = pushNotificationMessage.getPushTitle();
                if (TextUtils.isEmpty(pushTitle)) {
                    pushTitle = (String) context.getPackageManager().getApplicationLabel(context.getApplicationInfo());
                }
                pushContent = pushNotificationMessage.getPushContent();
                i3 = PUSH_SERVICE_NOTIFICATION_ID;
                PUSH_SERVICE_NOTIFICATION_ID++;
                i = i3;
                str = pushContent;
                pushContent = pushTitle;
                i2 = 300;
            } else if (!objectName.equals("RC:VCInvite") && !objectName.equals("RC:VCModifyMem") && !objectName.equals("RC:VCHangup")) {
                boolean z2;
                List list = (List) messageCache.get(pushNotificationMessage.getTargetId());
                if (list == null) {
                    list = new ArrayList();
                    list.add(pushNotificationMessage);
                    messageCache.put(pushNotificationMessage.getTargetId(), list);
                } else {
                    list.add(pushNotificationMessage);
                }
                if (messageCache.size() > 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                pushContent = getNotificationTitle(context);
                i = NOTIFICATION_ID;
                z = z2;
                i2 = 200;
            } else if (objectName.equals("RC:VCHangup")) {
                removeNotification(context, VOIP_NOTIFICATION_ID);
                return;
            } else {
                i3 = VOIP_NOTIFICATION_ID;
                soundType2 = SoundType.VOIP;
                String str2 = (String) context.getPackageManager().getApplicationLabel(context.getApplicationInfo());
                i = i3;
                str = pushNotificationMessage.getPushContent();
                pushContent = str2;
                i2 = 400;
            }
            Notification createNotification = createNotification(context, pushContent, createPendingIntent(context, pushNotificationMessage, i2, z), str, soundType2);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (createNotification != null) {
                notificationManager.notify(i, createNotification);
            }
            if (!isInNeglectTime) {
                timer.schedule(new C54121(), 1000);
                isInNeglectTime = true;
            }
        }
    }

    public static void removeAllNotification(Context context) {
        messageCache.clear();
        ((NotificationManager) context.getSystemService("notification")).cancelAll();
        NOTIFICATION_ID = 1000;
    }

    public static void removeAllPushNotification(Context context) {
        messageCache.clear();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        notificationManager.cancel(NOTIFICATION_ID);
        notificationManager.cancel(VOIP_NOTIFICATION_ID);
    }

    public static void removeAllPushServiceNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        for (int i = PUSH_SERVICE_NOTIFICATION_ID; i >= 1000; i--) {
            notificationManager.cancel(i);
        }
        PUSH_SERVICE_NOTIFICATION_ID = m_AppUI.MSG_APP_DATA_OK;
    }

    public static void removeNotification(Context context, int i) {
        if (i >= 0) {
            if (i >= NOTIFICATION_ID && i < PUSH_SERVICE_NOTIFICATION_ID) {
                messageCache.clear();
            }
            ((NotificationManager) context.getSystemService("notification")).cancel(i);
        }
    }

    private static PendingIntent createPendingIntent(Context context, PushNotificationMessage pushNotificationMessage, int i, boolean z) {
        Intent intent = new Intent();
        intent.setAction(PushConst.ACTION_PUSH_MESSAGE_CLICKED);
        intent.putExtra(AVStatus.MESSAGE_TAG, pushNotificationMessage);
        intent.putExtra("isMulti", z);
        intent.setPackage(context.getPackageName());
        return PendingIntent.getBroadcast(context, i, intent, 134217728);
    }

    private static String getNotificationContent(Context context) {
        String string = context.getResources().getString(context.getResources().getIdentifier("rc_notification_new_msg", "string", context.getPackageName()));
        String string2 = context.getResources().getString(context.getResources().getIdentifier("rc_notification_new_plural_msg", "string", context.getPackageName()));
        List list;
        if (messageCache.size() == 1) {
            list = (List) messageCache.values().iterator().next();
            PushNotificationMessage pushNotificationMessage = (PushNotificationMessage) list.get(0);
            if (list.size() == 1) {
                return pushNotificationMessage.getPushContent();
            }
            return String.format(string, new Object[]{pushNotificationMessage.getTargetUserName(), Integer.valueOf(list.size())});
        }
        int i = 0;
        for (List list2 : messageCache.values()) {
            i = list2.size() + i;
        }
        return String.format(string2, new Object[]{Integer.valueOf(messageCache.size()), Integer.valueOf(i)});
    }

    private static String getNotificationTitle(Context context) {
        if (messageCache.size() == 1) {
            return ((PushNotificationMessage) ((List) messageCache.values().iterator().next()).get(0)).getTargetUserName();
        }
        return (String) context.getPackageManager().getApplicationLabel(context.getApplicationInfo());
    }

    private static Notification createNotification(Context context, String str, PendingIntent pendingIntent, String str2, SoundType soundType) {
        boolean z = false;
        CharSequence string = context.getResources().getString(context.getResources().getIdentifier("rc_notification_ticker_text", "string", context.getPackageName()));
        if (TextUtils.isEmpty(str2)) {
            str2 = getNotificationContent(context);
        }
        if (VERSION.SDK_INT < 11) {
            try {
                Notification notification = new Notification(context.getApplicationInfo().icon, string, System.currentTimeMillis());
                Notification.class.getMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification, new Object[]{context, str, str2, pendingIntent});
                notification.flags = 16;
                notification.defaults = -1;
                return notification;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        if (VERSION.SDK_INT >= 21) {
            z = true;
        }
        int identifier = context.getResources().getIdentifier("notification_small_icon", "drawable", context.getPackageName());
        if (identifier <= 0 || !r0) {
            identifier = context.getApplicationInfo().icon;
        }
        Bitmap bitmap = ((BitmapDrawable) context.getApplicationInfo().loadIcon(context.getPackageManager())).getBitmap();
        Builder builder = new Builder(context);
        builder.setLargeIcon(bitmap);
        builder.setSmallIcon(identifier);
        builder.setTicker(string);
        builder.setContentTitle(str);
        builder.setContentText(str2);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        if (soundType.equals(SoundType.VOIP)) {
            builder.setSound(RingtoneManager.getDefaultUri(1));
        } else if (soundType.equals(SoundType.SILENT)) {
            builder.setDefaults(4);
        } else {
            builder.setDefaults(-1);
        }
        return builder.build();
    }
}
