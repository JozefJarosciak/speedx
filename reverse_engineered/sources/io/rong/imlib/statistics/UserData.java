package io.rong.imlib.statistics;

import android.util.Log;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

public class UserData {
    public static final String BYEAR_KEY = "byear";
    public static final String CUSTOM_KEY = "custom";
    public static final String EMAIL_KEY = "email";
    public static final String GENDER_KEY = "gender";
    public static final String NAME_KEY = "name";
    public static final String ORG_KEY = "organization";
    public static final String PHONE_KEY = "phone";
    public static final String PICTURE_KEY = "picture";
    public static final String PICTURE_PATH_KEY = "picturePath";
    public static final String USERNAME_KEY = "username";
    public static int byear = 0;
    public static Map<String, String> custom;
    public static String email;
    public static String gender;
    public static boolean isSynced = true;
    public static String name;
    public static String org;
    public static String phone;
    public static String picture;
    public static String picturePath;
    public static String username;

    static void setData(Map<String, String> map) {
        if (map.containsKey("name")) {
            name = (String) map.get("name");
        }
        if (map.containsKey(USERNAME_KEY)) {
            username = (String) map.get(USERNAME_KEY);
        }
        if (map.containsKey("email")) {
            email = (String) map.get("email");
        }
        if (map.containsKey(ORG_KEY)) {
            org = (String) map.get(ORG_KEY);
        }
        if (map.containsKey(PHONE_KEY)) {
            phone = (String) map.get(PHONE_KEY);
        }
        if (map.containsKey(PICTURE_PATH_KEY)) {
            picturePath = (String) map.get(PICTURE_PATH_KEY);
        }
        if (!(picturePath == null || new File(picturePath).isFile())) {
            if (Statistics.sharedInstance().isLoggingEnabled()) {
                Log.w("Statistics", "Provided file " + picturePath + " can not be opened");
            }
            picturePath = null;
        }
        if (map.containsKey(PICTURE_KEY)) {
            picture = (String) map.get(PICTURE_KEY);
        }
        if (map.containsKey(GENDER_KEY)) {
            gender = (String) map.get(GENDER_KEY);
        }
        if (map.containsKey(BYEAR_KEY)) {
            try {
                byear = Integer.parseInt((String) map.get(BYEAR_KEY));
            } catch (NumberFormatException e) {
                if (Statistics.sharedInstance().isLoggingEnabled()) {
                    Log.w("Statistics", "Incorrect byear number format");
                }
                byear = 0;
            }
        }
        isSynced = false;
    }

    static void setCustomData(Map<String, String> map) {
        custom = new HashMap();
        custom.putAll(map);
        isSynced = false;
    }

    static String getDataForRequest() {
        if (!isSynced) {
            isSynced = true;
            JSONObject toJSON = toJSON();
            if (toJSON != null) {
                String jSONObject = toJSON.toString();
                try {
                    jSONObject = URLEncoder.encode(jSONObject, "UTF-8");
                    if (jSONObject == null || jSONObject.equals("")) {
                        jSONObject = "";
                        if (picturePath != null) {
                            jSONObject = jSONObject + "&user_details&picturePath=" + URLEncoder.encode(picturePath, "UTF-8");
                        }
                        if (jSONObject != null) {
                            return jSONObject;
                        }
                    }
                    jSONObject = "&user_details=" + jSONObject;
                    if (picturePath != null) {
                        jSONObject = jSONObject + "&picturePath=" + URLEncoder.encode(picturePath, "UTF-8");
                    }
                    if (jSONObject != null) {
                        return jSONObject;
                    }
                } catch (UnsupportedEncodingException e) {
                }
            }
        }
        return "";
    }

    static JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (name != null) {
                if (name == "") {
                    jSONObject.put("name", JSONObject.NULL);
                } else {
                    jSONObject.put("name", name);
                }
            }
            if (username != null) {
                if (username == "") {
                    jSONObject.put(USERNAME_KEY, JSONObject.NULL);
                } else {
                    jSONObject.put(USERNAME_KEY, username);
                }
            }
            if (email != null) {
                if (email == "") {
                    jSONObject.put("email", JSONObject.NULL);
                } else {
                    jSONObject.put("email", email);
                }
            }
            if (org != null) {
                if (org == "") {
                    jSONObject.put(ORG_KEY, JSONObject.NULL);
                } else {
                    jSONObject.put(ORG_KEY, org);
                }
            }
            if (phone != null) {
                if (phone == "") {
                    jSONObject.put(PHONE_KEY, JSONObject.NULL);
                } else {
                    jSONObject.put(PHONE_KEY, phone);
                }
            }
            if (picture != null) {
                if (picture == "") {
                    jSONObject.put(PICTURE_KEY, JSONObject.NULL);
                } else {
                    jSONObject.put(PICTURE_KEY, picture);
                }
            }
            if (gender != null) {
                if (gender == "") {
                    jSONObject.put(GENDER_KEY, JSONObject.NULL);
                } else {
                    jSONObject.put(GENDER_KEY, gender);
                }
            }
            if (byear != 0) {
                if (byear > 0) {
                    jSONObject.put(BYEAR_KEY, byear);
                } else {
                    jSONObject.put(BYEAR_KEY, JSONObject.NULL);
                }
            }
            if (custom != null) {
                if (custom.isEmpty()) {
                    jSONObject.put(CUSTOM_KEY, JSONObject.NULL);
                } else {
                    jSONObject.put(CUSTOM_KEY, new JSONObject(custom));
                }
            }
        } catch (Throwable e) {
            if (Statistics.sharedInstance().isLoggingEnabled()) {
                Log.w("Statistics", "Got exception converting an UserData to JSON", e);
            }
        }
        return jSONObject;
    }

    static void fromJSON(JSONObject jSONObject) {
        if (jSONObject != null) {
            name = jSONObject.optString("name", null);
            username = jSONObject.optString(USERNAME_KEY, null);
            email = jSONObject.optString("email", null);
            org = jSONObject.optString(ORG_KEY, null);
            phone = jSONObject.optString(PHONE_KEY, null);
            picture = jSONObject.optString(PICTURE_KEY, null);
            gender = jSONObject.optString(GENDER_KEY, null);
            byear = jSONObject.optInt(BYEAR_KEY, 0);
            if (!jSONObject.isNull(CUSTOM_KEY)) {
                try {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(CUSTOM_KEY);
                    HashMap hashMap = new HashMap(jSONObject2.length());
                    Iterator keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        if (!jSONObject2.isNull(str)) {
                            hashMap.put(str, jSONObject2.getString(str));
                        }
                    }
                } catch (Throwable e) {
                    if (Statistics.sharedInstance().isLoggingEnabled()) {
                        Log.w("Statistics", "Got exception converting an Custom Json to Custom User data", e);
                    }
                }
            }
        }
    }

    public static String getPicturePathFromQuery(URL url) {
        String query = url.getQuery();
        if (query == null) {
            return "";
        }
        String[] split = query.split(C0869a.f2161b);
        query = "";
        if (!url.getQuery().contains(PICTURE_PATH_KEY)) {
            return query;
        }
        for (String str : split) {
            int indexOf = str.indexOf(SimpleComparison.EQUAL_TO_OPERATION);
            if (str.substring(0, indexOf).equals(PICTURE_PATH_KEY)) {
                try {
                    return URLDecoder.decode(str.substring(indexOf + 1), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    return "";
                }
            }
        }
        return query;
    }
}
