package io.rong.imlib.statistics;

import android.content.Context;
import android.util.Log;

public class DeviceId {
    private static final String PREFERENCE_KEY_ID_TYPE = "ly.count.android.api.DeviceId.type";
    private static final String TAG = "DeviceId";
    private String id;
    private Type type;

    public enum Type {
        DEVELOPER_SUPPLIED,
        OPEN_UDID,
        ADVERTISING_ID
    }

    public DeviceId(Type type) {
        if (type == null) {
            throw new IllegalStateException("Please specify DeviceId.Type, that is which type of device ID generation you want to use");
        } else if (type == Type.DEVELOPER_SUPPLIED) {
            throw new IllegalStateException("Please use another DeviceId constructor for device IDs supplied by developer");
        } else {
            this.type = type;
        }
    }

    public DeviceId(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalStateException("Please make sure that device ID is not null or empty");
        }
        this.type = Type.DEVELOPER_SUPPLIED;
        this.id = str;
    }

    public void init(Context context, StatisticsStore statisticsStore, boolean z) {
        Type retrieveOverriddenType = retrieveOverriddenType(statisticsStore);
        if (!(retrieveOverriddenType == null || retrieveOverriddenType == this.type)) {
            if (Statistics.sharedInstance().isLoggingEnabled()) {
                Log.i(TAG, "Overridden device ID generation strategy detected: " + retrieveOverriddenType + ", using it instead of " + this.type);
            }
            this.type = retrieveOverriddenType;
        }
        switch (this.type) {
            case OPEN_UDID:
                if (OpenUDIDAdapter.isOpenUDIDAvailable()) {
                    if (Statistics.sharedInstance().isLoggingEnabled()) {
                        Log.i(TAG, "Using OpenUDID");
                    }
                    if (!OpenUDIDAdapter.isInitialized()) {
                        OpenUDIDAdapter.sync(context);
                        return;
                    }
                    return;
                } else if (z) {
                    throw new IllegalStateException("OpenUDID is not available, please make sure that you have it in your classpath");
                } else {
                    return;
                }
            case ADVERTISING_ID:
                if (AdvertisingIdAdapter.isAdvertisingIdAvailable()) {
                    if (Statistics.sharedInstance().isLoggingEnabled()) {
                        Log.i(TAG, "Using Advertising ID");
                    }
                    AdvertisingIdAdapter.setAdvertisingId(context, statisticsStore, this);
                    return;
                } else if (OpenUDIDAdapter.isOpenUDIDAvailable()) {
                    if (Statistics.sharedInstance().isLoggingEnabled()) {
                        Log.i(TAG, "Advertising ID is not available, falling back to OpenUDID");
                    }
                    if (!OpenUDIDAdapter.isInitialized()) {
                        OpenUDIDAdapter.sync(context);
                        return;
                    }
                    return;
                } else {
                    if (Statistics.sharedInstance().isLoggingEnabled()) {
                        Log.w(TAG, "Advertising ID is not available, neither OpenUDID is");
                    }
                    if (z) {
                        throw new IllegalStateException("OpenUDID is not available, please make sure that you have it in your classpath");
                    }
                    return;
                }
            default:
                return;
        }
    }

    private void storeOverriddenType(StatisticsStore statisticsStore, Type type) {
        statisticsStore.setPreference(PREFERENCE_KEY_ID_TYPE, type == null ? null : type.toString());
    }

    private Type retrieveOverriddenType(StatisticsStore statisticsStore) {
        String preference = statisticsStore.getPreference(PREFERENCE_KEY_ID_TYPE);
        if (preference == null) {
            return null;
        }
        if (preference.equals(Type.DEVELOPER_SUPPLIED.toString())) {
            return Type.DEVELOPER_SUPPLIED;
        }
        if (preference.equals(Type.OPEN_UDID.toString())) {
            return Type.OPEN_UDID;
        }
        if (preference.equals(Type.ADVERTISING_ID.toString())) {
            return Type.ADVERTISING_ID;
        }
        return null;
    }

    public String getId() {
        if (this.id == null && this.type == Type.OPEN_UDID) {
            this.id = OpenUDIDAdapter.getOpenUDID();
        }
        return this.id;
    }

    protected void setId(Type type, String str) {
        if (Statistics.sharedInstance().isLoggingEnabled()) {
            Log.w(TAG, "Device ID is " + str + " (type " + type + ")");
        }
        this.type = type;
        this.id = str;
    }

    protected void switchToIdType(Type type, Context context, StatisticsStore statisticsStore) {
        if (Statistics.sharedInstance().isLoggingEnabled()) {
            Log.w(TAG, "Switching to device ID generation strategy " + type + " from " + this.type);
        }
        this.type = type;
        storeOverriddenType(statisticsStore, type);
        init(context, statisticsStore, false);
    }

    public Type getType() {
        return this.type;
    }

    static boolean deviceIDEqualsNullSafe(String str, Type type, DeviceId deviceId) {
        if (type != null && type != Type.DEVELOPER_SUPPLIED) {
            return true;
        }
        String id = deviceId == null ? null : deviceId.getId();
        if (id == null && str == null) {
            return true;
        }
        if (id == null || !id.equals(str)) {
            return false;
        }
        return true;
    }
}
