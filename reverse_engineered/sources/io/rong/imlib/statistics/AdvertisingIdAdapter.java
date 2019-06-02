package io.rong.imlib.statistics;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import io.rong.imlib.statistics.DeviceId.Type;

public class AdvertisingIdAdapter {
    private static final String ADVERTISING_ID_CLIENT_CLASS_NAME = "com.google.android.gms.ads.identifier.AdvertisingIdClient";
    private static final String TAG = "AdvertisingIdAdapter";
    private static Handler handler;

    public static boolean isAdvertisingIdAvailable() {
        try {
            Class.forName(ADVERTISING_ID_CLIENT_CLASS_NAME);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static void setAdvertisingId(final Context context, final StatisticsStore statisticsStore, final DeviceId deviceId) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    deviceId.setId(Type.ADVERTISING_ID, AdvertisingIdAdapter.getAdvertisingId(context));
                } catch (Throwable th) {
                    if (th.getCause() == null || !th.getCause().getClass().toString().contains("GooglePlayServicesAvailabilityException")) {
                        if (th.getCause() == null || !th.getCause().getClass().toString().contains("GooglePlayServicesNotAvailableException")) {
                            Log.e(AdvertisingIdAdapter.TAG, "Couldn't get advertising ID", th);
                            return;
                        }
                        if (Statistics.sharedInstance().isLoggingEnabled()) {
                            Log.w(AdvertisingIdAdapter.TAG, "Advertising ID cannot be determined because Play Services are not available");
                        }
                        deviceId.switchToIdType(Type.OPEN_UDID, context, statisticsStore);
                    } else if (Statistics.sharedInstance().isLoggingEnabled()) {
                        Log.i(AdvertisingIdAdapter.TAG, "Advertising ID cannot be determined yet");
                    }
                }
            }
        }).start();
    }

    private static String getAdvertisingId(Context context) throws Throwable {
        Object invoke = Class.forName(ADVERTISING_ID_CLIENT_CLASS_NAME).getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{context});
        if (invoke != null) {
            return (String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
        }
        return null;
    }
}
