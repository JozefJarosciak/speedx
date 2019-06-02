package com.beastbikes.android.modules.social.im;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.Builder;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.main.MainActivity;
import io.rong.imkit.RongContext;
import io.rong.push.RongPushClient.ConversationType;
import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

public class RongPushReceiver extends PushMessageReceiver {
    /* renamed from: a */
    private SharedPreferences f11121a;

    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f11121a = context.getSharedPreferences(currentUser.getObjectId(), 0);
            if (pushNotificationMessage.getConversationType() == ConversationType.GROUP) {
                this.f11121a.edit().putInt("beast.club.dot.group.chat", this.f11121a.getInt("beast.club.dot.group.chat", 0) + 1).apply();
            } else {
                Intent intent = null;
                if (!(!BeastBikes.f3996a || context == null || RongContext.getInstance() == null)) {
                    intent = new Intent("android.intent.action.VIEW", Uri.parse("rong://" + context.getApplicationInfo().packageName).buildUpon().appendPath("conversationlist").build());
                }
                if (intent == null) {
                    intent = new Intent(context, MainActivity.class);
                    intent.putExtra("RONGCLOUDPUSHRONGCLOUDPUSHKEY", "RONGCLOUDPUSHVALUE");
                    intent.setFlags(603979776);
                }
                PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 134217728);
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                Builder builder = new Builder(context);
                builder.setSmallIcon(C1373R.drawable.ic_launcher_small);
                builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), C1373R.drawable.ic_launcher));
                builder.setContentTitle(pushNotificationMessage.getSenderName());
                builder.setTicker(pushNotificationMessage.getSenderName());
                builder.setContentText(pushNotificationMessage.getPushContent());
                builder.setContentIntent(activity);
                Notification build = builder.build();
                build.defaults = 7;
                build.flags = 16;
                notificationManager.notify(1000, build);
            }
        }
        return true;
    }

    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        return false;
    }
}
