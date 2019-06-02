package com.beastbikes.android.ble;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent.CanceledException;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TargetApi(19)
public class BleNotificationService extends NotificationListenerService {
    /* renamed from: a */
    private static final Logger f7320a = LoggerFactory.getLogger(BleNotificationService.class);
    /* renamed from: b */
    private SharedPreferences f7321b;

    public void onCreate() {
        super.onCreate();
        f7320a.trace("BleNotificationService onCreate...");
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f7321b = getSharedPreferences(currentUser.getObjectId(), 0);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        f7320a.trace("BleNotificationService onDestroy...");
    }

    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        byte b = (byte) 1;
        if (statusBarNotification != null) {
            f7320a.trace("onNotificationPosted Notification = [" + statusBarNotification.toString() + "]");
            C1614a b2 = C1661h.m8999a().m9004b();
            if (b2 != null) {
                Notification notification = statusBarNotification.getNotification();
                if (notification != null) {
                    BluetoothDevice b3 = b2.m8734b();
                    if (statusBarNotification.getPackageName().equals("com.android.settings") && b3 != null) {
                        Bundle bundle = notification.extras;
                        if (bundle != null) {
                            Object string = bundle.getString(NotificationCompat.EXTRA_TEXT);
                            CharSequence name = b3.getName();
                            if (!TextUtils.isEmpty(string) && string.contains(name)) {
                                try {
                                    notification.contentIntent.send();
                                    f7320a.info("确认是配对请求的通知，主动弹出成功");
                                } catch (CanceledException e) {
                                    e.printStackTrace();
                                    f7320a.info("确认是配对请求的通知，主动弹出失败");
                                }
                            }
                        }
                    }
                    if (this.f7321b != null && this.f7321b.getInt("beast.ble.message.on", 1) == 1) {
                        String packageName = statusBarNotification.getPackageName();
                        if (!TextUtils.isEmpty(packageName) && !packageName.equals("android") && !packageName.contains("systemui") && !packageName.contains("miui")) {
                            Bundle bundle2 = notification.extras;
                            if (bundle2 != null) {
                                if (packageName.equals("com.android.incallui")) {
                                    b = (byte) 0;
                                }
                                Object string2 = bundle2.getString(NotificationCompat.EXTRA_TITLE);
                                if (TextUtils.isEmpty(string2) && b == (byte) 0) {
                                    string2 = getString(C1373R.string.label_call_in_msg);
                                }
                                if (!TextUtils.isEmpty(string2)) {
                                    String string3 = bundle2.getString(NotificationCompat.EXTRA_TEXT);
                                    Intent intent = new Intent("ble.action.notification.posted.or.removed");
                                    intent.putExtra("notification_title", string2);
                                    intent.putExtra("notification_text", string3);
                                    intent.putExtra("notification_type", b);
                                    sendBroadcast(intent);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        Object packageName = statusBarNotification.getPackageName();
        f7320a.trace("onNotificationRemoved Notification = [" + statusBarNotification.toString() + "]");
        if (!TextUtils.isEmpty(packageName) && packageName.equals("com.android.incallui")) {
            Notification notification = statusBarNotification.getNotification();
            if (notification != null) {
                Bundle bundle = notification.extras;
                Object string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (!TextUtils.isEmpty(string)) {
                    String string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                    Intent intent = new Intent("ble.action.notification.posted.or.removed");
                    intent.putExtra("notification_title", string);
                    intent.putExtra("notification_text", string2);
                    intent.putExtra("notification_type", (byte) 2);
                    sendBroadcast(intent);
                }
            }
        }
    }
}
