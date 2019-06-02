package com.mapbox.mapboxsdk.telemetry;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.location.Location;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.mapbox.mapboxsdk.BuildConfig;
import com.mapbox.mapboxsdk.constants.MapboxConstants;
import com.mapbox.mapboxsdk.exceptions.TelemetryServiceNotConfiguredException;
import com.mapbox.mapboxsdk.location.LocationServices;
import io.rong.imlib.statistics.UserData;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Timer;
import java.util.UUID;
import java.util.Vector;
import okhttp3.C5608s;
import okhttp3.internal.C5586l;

public class MapboxEventManager {
    private static final int FLUSH_EVENTS_CAP = 1000;
    private static final C5608s JSON = C5608s.a("application/json; charset=utf-8");
    private static final int SESSION_ID_ROTATION_HOURS = 24;
    private static final String TAG = "MapboxEventManager";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", MapboxConstants.MAPBOX_LOCALE);
    private static long flushDelayInMillis = 180000;
    private static long flushDelayInitialInMillis = AbstractComponentTracker.LINGERING_TIMEOUT;
    private static long hourInMillis = 3600000;
    private static final double locationEventAccuracy = 1.0E7d;
    private static MapboxEventManager mapboxEventManager = null;
    private static MessageDigest messageDigest = null;
    private String accessToken = null;
    private Intent batteryStatus = null;
    private Context context = null;
    private DisplayMetrics displayMetrics = null;
    private final Vector<Hashtable<String, Object>> events = new Vector();
    private String eventsURL = MapboxEvent.MAPBOX_EVENTS_BASE_URL;
    private boolean initialized = false;
    private String mapboxSessionId = null;
    private long mapboxSessionIdLastSet = 0;
    private String mapboxVendorId = null;
    private final String operatingSystem = ("Android - " + VERSION.RELEASE);
    private boolean stagingEnv;
    private boolean telemetryEnabled;
    private Timer timer = null;
    private String userAgent = BuildConfig.MAPBOX_EVENTS_USER_AGENT_BASE;

    private MapboxEventManager() {
    }

    public void initialize(@NonNull Context context, @NonNull String str) {
        Log.i(TAG, "Telemetry initialize() called...");
        if (this.initialized) {
            Log.i(TAG, "Mapbox Telemetry has already been initialized.");
            return;
        }
        this.context = context.getApplicationContext();
        this.accessToken = str;
        validateTelemetryServiceConfigured();
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            Log.w(TAG, "Error getting Encryption Algorithm: " + e);
        }
        rotateSessionId();
        SharedPreferences sharedPreferences = context.getSharedPreferences(MapboxConstants.MAPBOX_SHARED_PREFERENCES_FILE, 0);
        Log.i(TAG, "Right before Telemetry set enabled in initialized()");
        setTelemetryEnabled(sharedPreferences.getBoolean(MapboxConstants.MAPBOX_SHARED_PREFERENCE_KEY_TELEMETRY_ENABLED, true));
        if (sharedPreferences.contains(MapboxConstants.MAPBOX_SHARED_PREFERENCE_KEY_VENDORID)) {
            this.mapboxVendorId = sharedPreferences.getString(MapboxConstants.MAPBOX_SHARED_PREFERENCE_KEY_VENDORID, "");
        }
        if (TextUtils.isEmpty(this.mapboxVendorId)) {
            this.mapboxVendorId = encodeString(UUID.randomUUID().toString());
            Editor edit = sharedPreferences.edit();
            edit.putString(MapboxConstants.MAPBOX_SHARED_PREFERENCE_KEY_VENDORID, this.mapboxVendorId);
            edit.apply();
            edit.commit();
        }
        this.displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(this.displayMetrics);
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Object string = applicationInfo.metaData.getString(MapboxConstants.KEY_META_DATA_STAGING_SERVER);
            CharSequence string2 = applicationInfo.metaData.getString(MapboxConstants.KEY_META_DATA_STAGING_ACCESS_TOKEN);
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                Log.d(TAG, "Looking in SharedPreferences for Staging Credentials");
                string = sharedPreferences.getString(MapboxConstants.MAPBOX_SHARED_PREFERENCE_KEY_TELEMETRY_STAGING_URL, null);
                string2 = sharedPreferences.getString(MapboxConstants.MAPBOX_SHARED_PREFERENCE_KEY_TELEMETRY_STAGING_ACCESS_TOKEN, null);
            }
            if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(r0))) {
                this.eventsURL = string;
                this.accessToken = str;
                this.stagingEnv = true;
            }
            string2 = getApplicationIdentifier();
            if (TextUtils.equals(this.userAgent, BuildConfig.MAPBOX_EVENTS_USER_AGENT_BASE) && !TextUtils.isEmpty(string2)) {
                this.userAgent = C5586l.a(String.format(MapboxConstants.MAPBOX_LOCALE, "%s %s", new Object[]{string2, this.userAgent}));
            }
        } catch (Exception e2) {
            Log.e(TAG, "Error Trying to load Staging Credentials: " + e2.toString());
        }
        this.batteryStatus = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        this.initialized = true;
    }

    public static MapboxEventManager getMapboxEventManager() {
        if (mapboxEventManager == null) {
            mapboxEventManager = new MapboxEventManager();
        }
        return mapboxEventManager;
    }

    private void validateTelemetryServiceConfigured() {
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 4);
            if (packageInfo.services != null) {
                ServiceInfo[] serviceInfoArr = packageInfo.services;
                int length = serviceInfoArr.length;
                int i = 0;
                while (i < length) {
                    if (!TextUtils.equals("com.mapbox.mapboxsdk.telemetry.TelemetryService", serviceInfoArr[i].name)) {
                        i++;
                    } else {
                        return;
                    }
                }
            }
        } catch (Exception e) {
            Log.w(MapboxConstants.TAG, "Error checking for Telemetry Service Config: " + e);
        }
        throw new TelemetryServiceNotConfiguredException();
    }

    public static String generateCreateDate() {
        return dateFormat.format(new Date());
    }

    public boolean isTelemetryEnabled() {
        return this.telemetryEnabled;
    }

    public void setTelemetryEnabled(boolean z) {
        Log.i(TAG, "setTelemetryEnabled(); this.telemetryEnabled = " + this.telemetryEnabled + "; telemetryEnabled = " + z);
        if (this.telemetryEnabled == z) {
            Log.d(TAG, "No need to start / stop telemetry as it's already in that state.");
            return;
        }
        if (z) {
            Log.d(TAG, "Starting Telemetry Up!");
            this.context.startService(new Intent(this.context, TelemetryService.class));
            if (LocationServices.getLocationServices(this.context).areLocationPermissionsGranted()) {
                Log.i(TAG, "Permissions are good, see if GPS is enabled and if not then setup Ambient.");
                if (LocationServices.getLocationServices(this.context).isGPSEnabled()) {
                    LocationServices.getLocationServices(this.context).toggleGPS(false);
                }
            } else {
                Log.i(TAG, "Permissions are not good.  Need to do some looping to check on stuff.");
                Handler handler = new Handler();
                handler.postDelayed(new MapboxEventManager$1(this, handler), AbstractComponentTracker.LINGERING_TIMEOUT);
            }
            this.timer = new Timer();
            this.timer.schedule(new MapboxEventManager$FlushEventsTimerTask(this, null), flushDelayInitialInMillis, flushDelayInMillis);
        } else {
            Log.d(TAG, "Shutting Telemetry Down");
            this.events.removeAllElements();
            this.context.stopService(new Intent(this.context, TelemetryService.class));
            if (this.timer != null) {
                this.timer.cancel();
                this.timer = null;
            }
        }
        this.telemetryEnabled = z;
        Editor edit = this.context.getSharedPreferences(MapboxConstants.MAPBOX_SHARED_PREFERENCES_FILE, 0).edit();
        edit.putBoolean(MapboxConstants.MAPBOX_SHARED_PREFERENCE_KEY_TELEMETRY_ENABLED, z);
        edit.apply();
        edit.commit();
    }

    void flushEventsQueueImmediately() {
        Log.i(TAG, "flushEventsQueueImmediately() called...");
        new MapboxEventManager$FlushTheEventsTask(this, null).execute(new Void[0]);
    }

    private void putEventOnQueue(@NonNull Hashtable<String, Object> hashtable) {
        if (hashtable != null) {
            this.events.add(hashtable);
            if (this.events.size() == 1000) {
                Log.d(TAG, "eventsSize == flushCap so send data.");
                flushEventsQueueImmediately();
            }
        }
    }

    public void addLocationEvent(Location location) {
        if (!Double.isNaN(location.getLatitude()) && !Double.isNaN(location.getLongitude()) && !Double.isNaN(location.getAltitude()) && !Double.isInfinite(location.getLatitude()) && !Double.isInfinite(location.getLongitude()) && !Double.isInfinite(location.getAltitude())) {
            Hashtable hashtable = new Hashtable();
            hashtable.put("event", MapboxEvent.TYPE_LOCATION);
            hashtable.put(MapboxEvent.ATTRIBUTE_CREATED, generateCreateDate());
            hashtable.put(MapboxEvent.ATTRIBUTE_SOURCE, "mapbox");
            hashtable.put(MapboxEvent.ATTRIBUTE_SESSION_ID, encodeString(this.mapboxSessionId));
            hashtable.put(MapboxEvent.KEY_LATITUDE, Double.valueOf(Math.floor(location.getLatitude() * locationEventAccuracy) / locationEventAccuracy));
            hashtable.put(MapboxEvent.KEY_LONGITUDE, Double.valueOf(Math.floor(location.getLongitude() * locationEventAccuracy) / locationEventAccuracy));
            hashtable.put(MapboxEvent.KEY_ALTITUDE, Double.valueOf(location.getAltitude()));
            hashtable.put(MapboxEvent.ATTRIBUTE_OPERATING_SYSTEM, this.operatingSystem);
            hashtable.put(MapboxEvent.ATTRIBUTE_APPLICATION_STATE, getApplicationState());
            putEventOnQueue(hashtable);
            rotateSessionId();
        }
    }

    public void pushEvent(Hashtable<String, Object> hashtable) {
        if (this.context != null && this.accessToken != null && hashtable != null) {
            String str = (String) hashtable.get("event");
            if (!TextUtils.isEmpty(str)) {
                if (str.equalsIgnoreCase(MapboxEvent.TYPE_MAP_LOAD)) {
                    hashtable.put("userId", this.mapboxVendorId);
                    hashtable.put(MapboxEvent.ATTRIBUTE_MODEL, Build.MODEL);
                    hashtable.put(MapboxEvent.ATTRIBUTE_OPERATING_SYSTEM, this.operatingSystem);
                    hashtable.put(MapboxEvent.ATTRIBUTE_RESOLUTION, Float.valueOf(this.displayMetrics.density));
                    hashtable.put(MapboxEvent.ATTRIBUTE_ACCESSIBILITY_FONT_SCALE, Float.valueOf(getAccesibilityFontScaleSize()));
                    hashtable.put(MapboxEvent.ATTRIBUTE_ORIENTATION, getOrientation());
                    hashtable.put(MapboxEvent.ATTRIBUTE_BATTERY_LEVEL, Integer.valueOf(getBatteryLevel()));
                    hashtable.put(MapboxEvent.ATTRIBUTE_PLUGGED_IN, Boolean.valueOf(isPluggedIn()));
                    hashtable.put(MapboxEvent.ATTRIBUTE_CARRIER, getCellularCarrier());
                    hashtable.put(MapboxEvent.ATTRIBUTE_CELLULAR_NETWORK_TYPE, getCellularNetworkType());
                    hashtable.put(MapboxEvent.ATTRIBUTE_WIFI, getConnectedToWifi());
                    putEventOnQueue(hashtable);
                    pushTurnstileEvent();
                    return;
                }
                if (str.equalsIgnoreCase(MapboxEvent.TYPE_MAP_CLICK)) {
                    hashtable.put(MapboxEvent.ATTRIBUTE_ORIENTATION, getOrientation());
                    hashtable.put(MapboxEvent.ATTRIBUTE_BATTERY_LEVEL, Integer.valueOf(getBatteryLevel()));
                    hashtable.put(MapboxEvent.ATTRIBUTE_PLUGGED_IN, Boolean.valueOf(isPluggedIn()));
                    hashtable.put(MapboxEvent.ATTRIBUTE_CARRIER, getCellularCarrier());
                    hashtable.put(MapboxEvent.ATTRIBUTE_CELLULAR_NETWORK_TYPE, getCellularNetworkType());
                    hashtable.put(MapboxEvent.ATTRIBUTE_WIFI, getConnectedToWifi());
                } else if (str.equalsIgnoreCase(MapboxEvent.TYPE_MAP_DRAGEND)) {
                    hashtable.put(MapboxEvent.ATTRIBUTE_ORIENTATION, getOrientation());
                    hashtable.put(MapboxEvent.ATTRIBUTE_BATTERY_LEVEL, Integer.valueOf(getBatteryLevel()));
                    hashtable.put(MapboxEvent.ATTRIBUTE_PLUGGED_IN, Boolean.valueOf(isPluggedIn()));
                    hashtable.put(MapboxEvent.ATTRIBUTE_CARRIER, getCellularCarrier());
                    hashtable.put(MapboxEvent.ATTRIBUTE_CELLULAR_NETWORK_TYPE, getCellularNetworkType());
                    hashtable.put(MapboxEvent.ATTRIBUTE_WIFI, getConnectedToWifi());
                } else {
                    Log.w(TAG, "This is not an event type in the Events Data Model.");
                    return;
                }
                putEventOnQueue(hashtable);
            }
        }
    }

    private void pushTurnstileEvent() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("event", MapboxEvent.TYPE_TURNSTILE);
        hashtable.put(MapboxEvent.ATTRIBUTE_CREATED, generateCreateDate());
        hashtable.put("userId", this.mapboxVendorId);
        hashtable.put(MapboxEvent.ATTRIBUTE_ENABLED_TELEMETRY, Boolean.valueOf(this.telemetryEnabled));
        this.events.add(hashtable);
        flushEventsQueueImmediately();
        Log.d(TAG, "turnstile event pushed.");
    }

    private String encodeString(String str) {
        try {
            if (messageDigest != null) {
                messageDigest.reset();
                messageDigest.update(str.getBytes("UTF-8"));
                byte[] digest = messageDigest.digest();
                StringBuilder stringBuilder = new StringBuilder();
                int length = digest.length;
                for (int i = 0; i < length; i++) {
                    stringBuilder.append(String.format("%02X", new Object[]{Byte.valueOf(digest[i])}));
                }
                str = stringBuilder.toString();
            }
        } catch (Exception e) {
            Log.w(TAG, "Error encoding string, will return in original form." + e);
        }
        return str;
    }

    private void rotateSessionId() {
        long currentTimeMillis = System.currentTimeMillis();
        if (TextUtils.isEmpty(this.mapboxSessionId) || currentTimeMillis - this.mapboxSessionIdLastSet > 24 * hourInMillis) {
            this.mapboxSessionId = UUID.randomUUID().toString();
            this.mapboxSessionIdLastSet = System.currentTimeMillis();
        }
    }

    private String getOrientation() {
        switch (this.context.getResources().getConfiguration().orientation) {
            case 1:
                return "Portrait";
            case 2:
                return "Landscape";
            default:
                return "";
        }
    }

    private int getBatteryLevel() {
        return Math.round((((float) this.batteryStatus.getIntExtra("level", -1)) / ((float) this.batteryStatus.getIntExtra("scale", -1))) * 100.0f);
    }

    private boolean isPluggedIn() {
        int intExtra = this.batteryStatus.getIntExtra("plugged", -1);
        if (intExtra == 2 || intExtra == 1) {
            return true;
        }
        return false;
    }

    private String getApplicationState() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return "";
        }
        String packageName = this.context.getPackageName();
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                return "Foreground";
            }
        }
        return "Background";
    }

    private float getAccesibilityFontScaleSize() {
        return this.context.getResources().getConfiguration().fontScale;
    }

    private String getCellularCarrier() {
        String networkOperatorName = ((TelephonyManager) this.context.getSystemService(UserData.PHONE_KEY)).getNetworkOperatorName();
        if (TextUtils.isEmpty(networkOperatorName)) {
            return "";
        }
        return networkOperatorName;
    }

    private String getCellularNetworkType() {
        switch (((TelephonyManager) this.context.getSystemService(UserData.PHONE_KEY)).getNetworkType()) {
            case 0:
                return "Unknown";
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA";
            case 5:
                return "EVDO_0";
            case 6:
                return "EVDO_A";
            case 7:
                return "1xRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "IDEN";
            case 12:
                return "EVDO_B";
            case 13:
                return "LTE";
            case 14:
                return "EHRPD";
            case 15:
                return "HSPAP";
            default:
                return "";
        }
    }

    public Boolean getConnectedToWifi() {
        Boolean valueOf = Boolean.valueOf(false);
        WifiManager wifiManager = (WifiManager) this.context.getSystemService(MapboxEvent.ATTRIBUTE_WIFI);
        if (wifiManager.isWifiEnabled()) {
            try {
                if (wifiManager.getConnectionInfo().getNetworkId() != -1) {
                    return Boolean.valueOf(true);
                }
            } catch (Exception e) {
                Log.w(TAG, "Error getting Wifi Connection Status: " + e);
                return Boolean.valueOf(false);
            }
        }
        return valueOf;
    }

    private String getApplicationIdentifier() {
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0);
            return String.format(MapboxConstants.MAPBOX_LOCALE, "%s/%s/%s", new Object[]{this.context.getPackageName(), packageInfo.versionName, Integer.valueOf(packageInfo.versionCode)});
        } catch (Exception e) {
            return "";
        }
    }
}
