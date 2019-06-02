package com.beastbikes.android.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.TaskStackBuilder;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.main.MainActivity;

/* compiled from: NotificationUtil */
/* renamed from: com.beastbikes.android.utils.q */
public class C2572q {
    /* renamed from: a */
    public static Notification m12886a(int i) {
        Context applicationContext = BeastBikes.j().getApplicationContext();
        Intent intent = new Intent(applicationContext, MainActivity.class);
        TaskStackBuilder create = TaskStackBuilder.create(applicationContext);
        create.addParentStack(MainActivity.class);
        create.addNextIntent(intent);
        PendingIntent pendingIntent = create.getPendingIntent(0, 134217728);
        Builder builder = new Builder(applicationContext);
        builder.setContentTitle(applicationContext.getString(C1373R.string.app_name));
        builder.setSmallIcon(C1373R.drawable.ic_launcher_small);
        builder.setLargeIcon(BitmapFactory.decodeResource(applicationContext.getResources(), C1373R.drawable.ic_launcher));
        builder.setTicker(applicationContext.getResources().getString(C1373R.string.activity_fragment_event_click_start_riding));
        builder.setContentText(applicationContext.getResources().getString(i));
        builder.setContentIntent(pendingIntent);
        return builder.build();
    }
}
