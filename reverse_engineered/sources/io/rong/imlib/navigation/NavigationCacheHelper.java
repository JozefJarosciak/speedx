package io.rong.imlib.navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import io.rong.common.RFLog;
import io.rong.common.RLog;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.apache.commons.cli.HelpFormatter;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NavigationCacheHelper {
    private static final String APP_KEY = "appKey";
    private static final String CACHED_TIME = "cached_time";
    private static final String CMP_SERVER = "server";
    private static final String CODE = "code";
    private static final String LOCATION_CONFIG = "location";
    private static final String LOG_MONITOR = "monitor";
    private static final String MEDIA_SERVER = "uploadServer";
    private static final String NAVIGATION_PREFERENCE = "RongNavigation";
    private static final String NAVI_TAG = "navi";
    private static final String TAG = "NavigationCacheHelper";
    private static final long TIME_OUT = 7200000;
    private static final String TOKEN = "token";
    private static final String VOIP_CALL_INFO = "voipCallInfo";
    private static final String VOIP_SERVER = "voipServer";
    private static long sCacheTime = 0;

    public static boolean isCacheValid(Context context, String str, String str2) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAVIGATION_PREFERENCE, 0);
        String string = sharedPreferences.getString("appKey", null);
        String string2 = sharedPreferences.getString(TOKEN, null);
        String string3 = sharedPreferences.getString(CMP_SERVER, null);
        sCacheTime = sharedPreferences.getLong(CACHED_TIME, 0);
        if (string == null || !string.equals(str) || string2 == null || !string2.equals(str2) || string3 == null) {
            return false;
        }
        return true;
    }

    public static boolean isCacheTimeout(Context context) {
        if ((System.currentTimeMillis() - ((long) TimeZone.getDefault().getRawOffset())) - context.getSharedPreferences(NAVIGATION_PREFERENCE, 0).getLong(CACHED_TIME, 0) > TIME_OUT) {
            return true;
        }
        return false;
    }

    public static long getCachedTime() {
        return sCacheTime;
    }

    public static void clearCache(Context context) {
        context.getSharedPreferences(NAVIGATION_PREFERENCE, 0).edit().clear().apply();
    }

    public static String getCMPServer(Context context) {
        return context.getSharedPreferences(NAVIGATION_PREFERENCE, 0).getString(CMP_SERVER, null);
    }

    public static void updateTime(Context context, long j) {
        Editor edit = context.getSharedPreferences(NAVIGATION_PREFERENCE, 0).edit();
        edit.putLong(CACHED_TIME, j);
        edit.apply();
    }

    public static String getVoIPCallInfo(Context context) {
        return context.getSharedPreferences(NAVIGATION_PREFERENCE, 0).getString(VOIP_CALL_INFO, null);
    }

    public static String getVoIPAddress(Context context) {
        return context.getSharedPreferences(NAVIGATION_PREFERENCE, 0).getString(VOIP_SERVER, null);
    }

    public static String getMediaServer(Context context) {
        return context.getSharedPreferences(NAVIGATION_PREFERENCE, 0).getString(MEDIA_SERVER, null);
    }

    public static LocationConfig getLocationConfig(Context context) {
        int i = 0;
        Object string = context.getSharedPreferences(NAVIGATION_PREFERENCE, 0).getString("location", null);
        if (!TextUtils.isEmpty(string)) {
            try {
                LocationConfig locationConfig = new LocationConfig();
                JSONObject jSONObject = new JSONObject(string);
                locationConfig.setConfigure(jSONObject.optBoolean("configure"));
                if (jSONObject.has("conversationTypes")) {
                    JSONArray optJSONArray = jSONObject.optJSONArray("conversationTypes");
                    int[] iArr = new int[optJSONArray.length()];
                    while (i < optJSONArray.length()) {
                        iArr[i] = optJSONArray.optInt(i);
                        i++;
                    }
                    locationConfig.setConversationTypes(iArr);
                }
                locationConfig.setMaxParticipant(jSONObject.optInt("maxParticipant"));
                locationConfig.setDistanceFilter(jSONObject.optInt("distanceFilter"));
                locationConfig.setRefreshInterval(jSONObject.optInt("refreshInterval"));
                return locationConfig;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void cacheRequest(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences(NAVIGATION_PREFERENCE, 0).edit();
        edit.putLong(CACHED_TIME, System.currentTimeMillis() - ((long) TimeZone.getDefault().getRawOffset()));
        edit.putString("appKey", str);
        edit.putString(TOKEN, str2);
        edit.apply();
    }

    private static boolean isValidCmp(String str) {
        try {
            return Pattern.compile("(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])").matcher(str).find();
        } catch (PatternSyntaxException e) {
            RLog.m19420e(TAG, "isValidCmp : " + str);
            e.printStackTrace();
            return false;
        }
    }

    public static int decode2File(Context context, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            RLog.m19420e(TAG, "decode2File : navi data is empty.");
            return 30008;
        } else if (!str.contains(CODE)) {
            RLog.m19420e(TAG, "decode2File : code is empty.");
            return 30008;
        } else if (!str.contains(CODE)) {
            return 30008;
        } else {
            Editor edit = context.getSharedPreferences(NAVIGATION_PREFERENCE, 0).edit();
            Object decode = decode(str, "<code>", "</code>");
            if (TextUtils.isEmpty(decode)) {
                RLog.m19420e(TAG, "decode2File : code is empty.");
                return 30008;
            }
            try {
                int parseInt = Integer.parseInt(decode);
                if (parseInt != 200) {
                    RLog.m19424w(TAG, "decode2File : code & httpCode " + parseInt + HelpFormatter.DEFAULT_OPT_PREFIX + i);
                    return ((parseInt == HttpStatus.SC_UNAUTHORIZED && i == HttpStatus.SC_FORBIDDEN) || (parseInt == HttpStatus.SC_FORBIDDEN && i == HttpStatus.SC_UNAUTHORIZED)) ? 31004 : 30007;
                } else {
                    Object decode2 = decode(str, "<server>", "</server>");
                    if (TextUtils.isEmpty(decode2)) {
                        RLog.m19420e(TAG, "decode2File : cmp is invalid");
                        return 30008;
                    }
                    edit.putString(CMP_SERVER, decode2);
                    Object decode3 = decode(str, "<uploadServer>", "</uploadServer>");
                    if (!TextUtils.isEmpty(decode3)) {
                        edit.putString(MEDIA_SERVER, decode3);
                    }
                    decode3 = decode(str, "<location>", "</location>");
                    if (!TextUtils.isEmpty(decode3)) {
                        edit.putString("location", decode3.replaceAll("&quot;", "\""));
                    }
                    decode3 = decode(str, "<voipCallInfo>", "</voipCallInfo>");
                    if (!TextUtils.isEmpty(decode3)) {
                        edit.putString(VOIP_CALL_INFO, decode3.replaceAll("&quot;", "\""));
                    }
                    decode3 = decode(str, "<monitor>", "</monitor>");
                    if (!TextUtils.isEmpty(decode3)) {
                        RFLog.setMode(context, Integer.valueOf(decode3).intValue());
                    }
                    edit.apply();
                    return 0;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                RLog.m19424w(TAG, "decode2File : NumberFormatException " + e.getMessage());
                return 30007;
            }
        }
    }

    private static String decode(String str, String str2, String str3) {
        int indexOf = str.indexOf(str2) + str2.length();
        int indexOf2 = str.indexOf(str3);
        if (indexOf >= indexOf2 || indexOf2 == 0) {
            return null;
        }
        return str.substring(indexOf, indexOf2);
    }

    public static String decode2cmp(Context context, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            RLog.m19420e(TAG, "decode2cmp : navi data invalid");
            return null;
        } else if (!str.contains(CMP_SERVER) || !str.contains(CODE)) {
            RLog.m19420e(TAG, "decode2cmp : data - " + str);
            RLog.m19420e(TAG, "decode2cmp : cmp or code invalid");
            return null;
        } else if (!str.contains(CODE)) {
            return null;
        } else {
            String str2 = "";
            Object decode = decode(str, "<code>", "</code>");
            if (TextUtils.isEmpty(decode)) {
                RLog.m19420e(TAG, "decode2cmp : code invalid");
                return null;
            }
            try {
                int parseInt = Integer.parseInt(decode);
                if (parseInt != 200) {
                    RLog.m19424w(TAG, "decode2cmp : code & httpCode " + parseInt + HelpFormatter.DEFAULT_OPT_PREFIX + i);
                    return (!(parseInt == HttpStatus.SC_UNAUTHORIZED && i == HttpStatus.SC_FORBIDDEN) && parseInt == HttpStatus.SC_FORBIDDEN && i == HttpStatus.SC_UNAUTHORIZED) ? null : null;
                } else {
                    str2 = decode(str, "<server>", "</server>");
                    if (!TextUtils.isEmpty(str2)) {
                        return str2;
                    }
                    RLog.m19420e(TAG, "decode2cmp : data - " + str);
                    RLog.m19420e(TAG, "decode2cmp : cmp invalid - " + str2);
                    return null;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                RLog.m19424w(TAG, "decode2cmp : NumberFormatException " + e.getMessage());
                return null;
            }
        }
    }
}
