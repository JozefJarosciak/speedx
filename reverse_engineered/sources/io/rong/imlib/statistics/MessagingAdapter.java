package io.rong.imlib.statistics;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import io.rong.imlib.statistics.DeviceId.Type;

public class MessagingAdapter {
    private static final String MESSAGING_CLASS_NAME = "ly.count.android.sdk.messaging.CountlyMessaging";
    private static final String TAG = "MessagingAdapter";

    public static boolean isMessagingAvailable() {
        try {
            Class.forName(MESSAGING_CLASS_NAME);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean init(Activity activity, Class<? extends Activity> cls, String str, String[] strArr) {
        try {
            Class.forName(MESSAGING_CLASS_NAME).getMethod("init", new Class[]{Activity.class, Class.class, String.class, String[].class}).invoke(null, new Object[]{activity, cls, str, strArr});
            return true;
        } catch (Throwable th) {
            Log.e(TAG, "Couldn't init Statistics Messaging", th);
            return false;
        }
    }

    public static boolean storeConfiguration(Context context, String str, String str2, String str3, Type type) {
        try {
            Class.forName(MESSAGING_CLASS_NAME).getMethod("storeConfiguration", new Class[]{Context.class, String.class, String.class, String.class, Type.class}).invoke(null, new Object[]{context, str, str2, str3, type});
            return true;
        } catch (Throwable th) {
            Log.e(TAG, "Couldn't store configuration in Statistics Messaging", th);
            return false;
        }
    }
}
