package io.rong.push;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.avos.avoscloud.AVStatus;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.GcmReceiver;
import com.huawei.android.pushagent.PushBootReceiver;
import com.huawei.android.pushagent.PushEventReceiver;
import com.xiaomi.mipush.sdk.MessageHandleService;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import com.xiaomi.push.service.receivers.PingReceiver;
import io.rong.imlib.common.DeviceUtils;
import io.rong.imlib.common.RongLibConst;
import io.rong.imlib.statistics.Statistics;
import io.rong.push.common.RLog;
import io.rong.push.common.RongException;
import io.rong.push.core.PushRegistrationService;
import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;
import io.rong.push.notification.RongNotificationInterface;
import io.rong.push.platform.HWReceiver;
import io.rong.push.platform.MiMessageReceiver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RongPushClient {
    private static final String TAG = "RongPushClient";
    private static final ArrayList<String> registeredType = new ArrayList();

    public enum ConversationType {
        NONE(0, "none"),
        PRIVATE(1, AVStatus.INBOX_PRIVATE),
        DISCUSSION(2, "discussion"),
        GROUP(3, "group"),
        CHATROOM(4, "chatroom"),
        CUSTOMER_SERVICE(5, "customer_service"),
        SYSTEM(6, "system"),
        APP_PUBLIC_SERVICE(7, "app_public_service"),
        PUBLIC_SERVICE(8, "public_service"),
        PUSH_SERVICE(9, "push_service");
        
        private String name;
        private int value;

        private ConversationType(int i, String str) {
            this.value = 1;
            this.name = "";
            this.value = i;
            this.name = str;
        }

        public int getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }

        public static ConversationType setValue(int i) {
            for (ConversationType conversationType : values()) {
                if (i == conversationType.getValue()) {
                    return conversationType;
                }
            }
            return PRIVATE;
        }
    }

    public static void registerGCM(Context context) throws RongException {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0);
        try {
            checkPlayServices(context);
            sharedPreferences.edit().putBoolean("isGCMEnabled", true).commit();
            registeredType.add("GCM");
        } catch (Exception e) {
            throw new RongException("Failed registerGCM.", e);
        }
    }

    public static void registerMiPush(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0).edit();
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Failed registerMiPush. appKey or appId can't be empty.");
        }
        registeredType.add("MI");
        edit.putBoolean("isMiEnabled", true);
        edit.putString("MiAppId", str);
        edit.putString("MiAppKey", str2);
        edit.commit();
    }

    public static void registerHWPush(Context context) {
        Editor edit = context.getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0).edit();
        registeredType.add("HW");
        edit.putBoolean("isHWEnabled", true);
        edit.commit();
    }

    public static void init(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            throw new ExceptionInInitializerError("appKey can't be empty!");
        }
        Boolean valueOf = Boolean.valueOf(isConfigChanged(context));
        String string = context.getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0).getString("pushType", "");
        RLog.d(TAG, "init. the push type is:" + string);
        try {
            if (TextUtils.isEmpty(string) || string.equals("RONG") || valueOf.booleanValue()) {
                RLog.d(TAG, "send to pushService.");
                String supportedPushTypes = getSupportedPushTypes();
                Intent intent = new Intent(context, PushService.class);
                intent.setAction(PushConst.ACTION_INIT_PUSH);
                intent.putExtra("deviceId", DeviceUtils.getDeviceId(context, str));
                intent.putExtra(RongLibConst.KEY_APPKEY, str);
                intent.putExtra("enabledPushTypes", supportedPushTypes);
                context.startService(intent);
                return;
            }
            RLog.e(TAG, "send to PushRegistrationService.");
            Intent intent2 = new Intent(context, PushRegistrationService.class);
            intent2.putExtra("pushType", string);
            context.startService(intent2);
        } catch (SecurityException e) {
            RLog.e(TAG, "SecurityException. Failed to start pushService.");
        }
    }

    public static void init(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new ExceptionInInitializerError("appKey or pushDomain can't be empty!");
        }
        Boolean valueOf = Boolean.valueOf(isConfigChanged(context));
        String string = context.getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0).getString("pushType", "");
        RLog.d(TAG, "init with domain. the push type is:" + string);
        try {
            if (TextUtils.isEmpty(string) || string.equals("RONG") || valueOf.booleanValue()) {
                RLog.d(TAG, "send to pushService.");
                String supportedPushTypes = getSupportedPushTypes();
                Intent intent = new Intent(context, PushService.class);
                intent.setAction(PushConst.ACTION_INIT_PUSH);
                intent.putExtra("deviceId", DeviceUtils.getDeviceId(context, str));
                intent.putExtra(RongLibConst.KEY_APPKEY, str);
                intent.putExtra("enabledPushTypes", supportedPushTypes);
                intent.putExtra("pushDomain", str2);
                context.startService(intent);
                return;
            }
            RLog.e(TAG, "send to PushRegistrationService.");
            Intent intent2 = new Intent(context, PushRegistrationService.class);
            intent2.putExtra("pushType", string);
            context.startService(intent2);
        } catch (SecurityException e) {
            RLog.e(TAG, "SecurityException. Failed to start pushService.");
        }
    }

    public static void clearAllNotifications(Context context) {
        RLog.i(TAG, "clearAllNotifications");
        if (context.getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0).getString("pushType", "").equals("MI")) {
            MiPushClient.clearNotification(context);
        }
        RongNotificationInterface.removeAllNotification(context);
    }

    public static void clearAllPushNotifications(Context context) {
        RLog.i(TAG, "clearAllPushNotifications");
        if (context.getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0).getString("pushType", "").equals("MI")) {
            MiPushClient.clearNotification(context);
        }
        RongNotificationInterface.removeAllPushNotification(context);
    }

    public static void clearAllPushServiceNotifications(Context context) {
        if (context.getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0).getString("pushType", "").equals("MI")) {
            MiPushClient.clearNotification(context);
        }
        RongNotificationInterface.removeAllPushServiceNotification(context);
    }

    public static void clearNotificationById(Context context, int i) {
        if (context.getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0).getString("pushType", "").equals("MI")) {
            MiPushClient.clearNotification(context, i);
        }
        RongNotificationInterface.removeNotification(context, i);
    }

    public static void recordNotificationEvent(String str) {
        Map hashMap = new HashMap();
        if (str == null || str.equals("")) {
            RLog.e(TAG, "pushId can't be null!");
        } else if (Statistics.sharedInstance().isInitialized()) {
            RLog.i(TAG, "recordNotificationEvent");
            hashMap.put("id", str);
            hashMap.put("osName", "Android");
            Statistics.sharedInstance().recordEvent("pushEvent", hashMap);
        } else {
            RLog.e(TAG, "Statistics should be initialized firstly!");
        }
    }

    public static void stopRongPush(Context context) {
        Intent intent = new Intent(context, PushService.class);
        intent.setAction(PushConst.ACTION_STOP_PUSH);
        context.startService(intent);
    }

    public static void sendNotification(Context context, PushNotificationMessage pushNotificationMessage) {
        RongNotificationInterface.sendNotification(context, pushNotificationMessage);
    }

    public static void checkManifest(Context context) throws RongException {
        checkService(context);
        checkReceivers(context);
    }

    private static boolean isConfigChanged(Context context) {
        boolean z;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0);
        if (!sharedPreferences.getBoolean("isGCMEnabled", false) || registeredType.contains("GCM")) {
            z = false;
        } else {
            sharedPreferences.edit().remove("isGCMEnabled").commit();
            z = true;
        }
        if (sharedPreferences.getBoolean("isMiEnabled", false) && !registeredType.contains("MI")) {
            sharedPreferences.edit().remove("isMiEnabled").commit();
            z = true;
        }
        if (sharedPreferences.getBoolean("isHWEnabled", false) && !registeredType.contains("HW")) {
            sharedPreferences.edit().remove("isHWEnabled").commit();
            z = true;
        }
        if (z) {
            context.getSharedPreferences(PushConst.PUSH_SHARE_PREFERENCE, 0).edit().clear().commit();
        }
        return z;
    }

    private static void checkReceivers(Context context) throws RongException {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0);
        Intent intent = new Intent(PushConst.ACTION_HEARTBEAT);
        intent.setPackage(context.getPackageName());
        findAndCheckReceiverInfo(context.getPackageManager(), intent, PushReceiver.class);
        intent = new Intent(PushConst.ACTION_RONG_PUSH_MESSAGE_ARRIVED);
        intent.setPackage(context.getPackageName());
        boolean z = false;
        for (ResolveInfo resolveInfo : context.getPackageManager().queryBroadcastReceivers(intent, 64)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null) {
                try {
                    if (!TextUtils.isEmpty(activityInfo.name) && PushMessageReceiver.class.isAssignableFrom(Class.forName(activityInfo.name)) && activityInfo.enabled) {
                        z = true;
                        if (z) {
                            break;
                        }
                    }
                } catch (ClassNotFoundException e) {
                    z = false;
                }
            }
            z = false;
            if (z) {
                break;
            }
        }
        if (z) {
            if (sharedPreferences.getBoolean("isGCMEnabled", false)) {
                intent = new Intent("com.google.android.c2dm.intent.RECEIVE");
                intent.setPackage(context.getPackageName());
                findAndCheckReceiverInfo(context.getPackageManager(), intent, GcmReceiver.class);
            }
            if (sharedPreferences.getBoolean("isMiEnabled", false)) {
                intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
                intent.setPackage(context.getPackageName());
                findAndCheckReceiverInfo(context.getPackageManager(), intent, MiMessageReceiver.class);
                intent = new Intent("android.net.conn.CONNECTIVITY_CHANGE");
                intent.setPackage(context.getPackageName());
                findAndCheckReceiverInfo(context.getPackageManager(), intent, NetworkStatusReceiver.class);
                intent = new Intent("com.xiaomi.push.PING_TIMER");
                intent.setPackage(context.getPackageName());
                findAndCheckReceiverInfo(context.getPackageManager(), intent, PingReceiver.class);
            }
            if (sharedPreferences.getBoolean("isHWEnabled", false)) {
                intent = new Intent("com.huawei.intent.action.PUSH");
                intent.setPackage(context.getPackageName());
                findAndCheckReceiverInfo(context.getPackageManager(), intent, PushEventReceiver.class);
                intent = new Intent("com.huawei.android.push.intent.REGISTER");
                intent.setPackage(context.getPackageName());
                findAndCheckReceiverInfo(context.getPackageManager(), intent, PushBootReceiver.class);
                intent = new Intent("com.huawei.android.push.intent.REGISTRATION");
                intent.setPackage(context.getPackageName());
                findAndCheckReceiverInfo(context.getPackageManager(), intent, HWReceiver.class);
                return;
            }
            return;
        }
        throw new RongException("Receiver: none of the subclasses of PushMessageReceiver is enabled or defined.");
    }

    private static void checkService(Context context) throws RongException {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PushConst.APP_PUSH_INFORMATION, 0);
        ArrayList arrayList = new ArrayList();
        arrayList.add("io.rong.push.core.PushRegistrationService");
        arrayList.add("io.rong.push.PushService");
        arrayList.add("io.rong.push.core.MessageHandleService");
        if (sharedPreferences.getBoolean("isGCMEnabled", false)) {
            arrayList.add("io.rong.push.platform.RongGcmListenerService");
            arrayList.add("io.rong.push.platform.RongGCMInstanceIDListenerService");
        }
        if (sharedPreferences.getBoolean("isMiEnabled", false)) {
            arrayList.add("com.xiaomi.push.service.XMPushService");
            arrayList.add(PushMessageHandler.class.getCanonicalName());
            arrayList.add(MessageHandleService.class.getCanonicalName());
        }
        if (sharedPreferences.getBoolean("isHWEnabled", false)) {
            arrayList.add("com.huawei.android.pushagent.PushService");
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4);
            if (packageInfo != null && packageInfo.services != null) {
                ServiceInfo[] serviceInfoArr = packageInfo.services;
                for (ServiceInfo serviceInfo : serviceInfoArr) {
                    if (!TextUtils.isEmpty(serviceInfo.name) && arrayList.contains(serviceInfo.name)) {
                        arrayList.remove(serviceInfo.name);
                        if (arrayList.isEmpty()) {
                            break;
                        }
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                throw new RongException(String.format("<service android:name=\"%1$s\" /> is missing.", new Object[]{arrayList.iterator().next()}));
            }
        } catch (Exception e) {
            throw new RongException("can't find packageName.", e);
        }
    }

    private static void findAndCheckReceiverInfo(PackageManager packageManager, Intent intent, Class<?> cls) throws RongException {
        int i;
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(intent, 16384)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null && cls.getCanonicalName().equals(activityInfo.name)) {
                i = 1;
                break;
            }
        }
        i = 0;
        if (i == 0) {
            throw new RongException(String.format("<receiver android:name=\"%1$s\" /> is missing or disabled.", new Object[]{cls.getCanonicalName()}));
        }
    }

    private static String getSupportedPushTypes() {
        Iterator it = registeredType.iterator();
        String str = "";
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (!str.isEmpty()) {
                str2 = (str + "|") + str2;
            }
            str = str2;
        }
        return str;
    }

    private static boolean checkPlayServices(Context context) throws Exception {
        int isGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable == 0) {
            return true;
        }
        throw new Exception(Integer.toString(isGooglePlayServicesAvailable));
    }
}
