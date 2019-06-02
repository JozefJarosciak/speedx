package com.beastbikes.android.authentication;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Base64;
import com.avos.avoscloud.AVUser.AVThirdPartyUserAuth;
import com.avos.avoscloud.AVUtils;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.utils.C2573r;
import com.beastbikes.framework.android.ApplicationContext;
import com.beastbikes.framework.android.p088g.C2802e;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import io.rong.imlib.statistics.UserData;
import java.io.File;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AVUser implements Serializable {
    public static final int SIGN_TYPE_EMAIL = 1;
    public static final int SIGN_TYPE_FACEBOOK = 64;
    public static final int SIGN_TYPE_GOOGLE_PLUS = 128;
    public static final int SIGN_TYPE_PHONE = 2;
    public static final int SIGN_TYPE_QQ = 8;
    public static final int SIGN_TYPE_STRAVA = 256;
    public static final int SIGN_TYPE_TWITTER = 32;
    public static final int SIGN_TYPE_WECHAT = 16;
    public static final int SIGN_TYPE_WEIBO = 4;
    public static final String SP_KEY = AVUser.class.getSimpleName();
    public static final String SP_KEY_PWD = (AVUser.class.getSimpleName() + "PWD");
    private static AVUser instance = null;
    private static final Logger logger = LoggerFactory.getLogger(AVUser.class);
    private int achievementNum;
    private String avatar;
    private String birthDay;
    private String city;
    private String clubId;
    private int clubLevel;
    private String clubName;
    private String country;
    private String displayName;
    private boolean edited = false;
    private String email;
    private int fansNum;
    private int followStatus;
    private int followerNum;
    private int ftp;
    private int gender;
    private String geoCode;
    private double height;
    private boolean isAuthenticated = false;
    private int maxHeartRate;
    private String mobilePhoneNumber;
    private String objectId;
    private String password;
    private String province;
    private String sessionToken;
    private int signType;
    private int speedxId;
    private String thirdToken;
    private double totalMileage;
    private int trainingId;
    private int trainingType;
    private String username;
    private double weight;

    public String getSessionToken() {
        return this.sessionToken;
    }

    public void setSessionToken(String str) {
        this.sessionToken = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getMobilePhoneNumber() {
        return this.mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String str) {
        this.mobilePhoneNumber = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getPassword() {
        return this.password;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public int getSignType() {
        return this.signType;
    }

    public void setSignType(int i) {
        this.signType = i;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public String getThirdToken() {
        return this.thirdToken;
    }

    public void setThirdToken(String str) {
        this.thirdToken = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getClubName() {
        return this.clubName;
    }

    public void setClubName(String str) {
        this.clubName = str;
    }

    public String getGeoCode() {
        return this.geoCode;
    }

    public void setGeoCode(String str) {
        this.geoCode = str;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double d) {
        this.weight = d;
    }

    public int getFollowStatus() {
        return this.followStatus;
    }

    public void setFollowStatus(int i) {
        this.followStatus = i;
    }

    public int getFollowerNum() {
        return this.followerNum;
    }

    public void setFollowerNum(int i) {
        this.followerNum = i;
    }

    public int getFansNum() {
        return this.fansNum;
    }

    public void setFansNum(int i) {
        this.fansNum = i;
    }

    public int getTrainingType() {
        return this.trainingType;
    }

    public void setTrainingType(int i) {
        this.trainingType = i;
    }

    public int getTrainingId() {
        return this.trainingId;
    }

    public void setTrainingId(int i) {
        this.trainingId = i;
    }

    public int getClubLevel() {
        return this.clubLevel;
    }

    public void setClubLevel(int i) {
        this.clubLevel = i;
    }

    public double getTotalMileage() {
        return this.totalMileage;
    }

    public void setTotalMileage(double d) {
        this.totalMileage = d;
    }

    public int getFtp() {
        return this.ftp;
    }

    public void setFtp(int i) {
        this.ftp = i;
    }

    public static AVUser getCurrentUser() {
        if (instance == null) {
            String string = PreferenceManager.getDefaultSharedPreferences(ApplicationContext.m5242j()).getString(SP_KEY, null);
            if (string != null) {
                instance = json2Object(string);
            }
        }
        return instance;
    }

    public static void saveCurrentUser(AVUser aVUser) {
        instance = aVUser;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(ApplicationContext.m5242j());
        if (aVUser == null) {
            defaultSharedPreferences.edit().remove(SP_KEY).commit();
            defaultSharedPreferences.edit().remove(SP_KEY_PWD).commit();
            return;
        }
        savePwd(aVUser.getPassword());
        defaultSharedPreferences.edit().putString(SP_KEY, object2JsonString(aVUser)).commit();
    }

    public static void updateCurrentUser(AVUser aVUser) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(ApplicationContext.m5242j());
        defaultSharedPreferences.edit().putString(SP_KEY, object2JsonString(aVUser)).commit();
    }

    private static String object2JsonString(AVUser aVUser) {
        String str = null;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sessionToken", aVUser.getSessionToken());
            jSONObject.put(UserData.USERNAME_KEY, aVUser.getUsername());
            jSONObject.put("email", aVUser.getEmail());
            jSONObject.put(AVUtils.objectIdTag, aVUser.getObjectId());
            jSONObject.put("signType", aVUser.getSignType());
            jSONObject.put("password", aVUser.getPassword());
            jSONObject.put("avatar", aVUser.getAvatar());
            jSONObject.put("displayName", aVUser.getDisplayName());
            jSONObject.put(GeocodingCriteria.TYPE_COUNTRY, aVUser.getCountry());
            jSONObject.put("province", aVUser.getProvince());
            jSONObject.put("city", aVUser.getCity());
            jSONObject.put("clubName", aVUser.getClubName());
            jSONObject.put("thirdToken", aVUser.getThirdToken());
            jSONObject.put("geoCode", aVUser.getGeoCode());
            jSONObject.put("weight", aVUser.getWeight());
            jSONObject.put("clubId", aVUser.getClubId());
            jSONObject.put("fansNum", aVUser.getFansNum());
            jSONObject.put("followerNum", aVUser.getFollowerNum());
            jSONObject.put("followStatus", aVUser.getFollowStatus());
            jSONObject.put("speedxId", aVUser.getSpeedxId());
            jSONObject.put("maxHeartRate", aVUser.getMaxHeartRate());
            jSONObject.put("mobilePhoneNumber", aVUser.getMobilePhoneNumber());
            jSONObject.put("edited", aVUser.isEdited());
            jSONObject.put("isAuthenticated", aVUser.isAuthenticated());
            jSONObject.put("trainingId", aVUser.getTrainingId());
            jSONObject.put("trainingType", aVUser.getTrainingType());
            jSONObject.put("clubLevel", aVUser.getClubLevel());
            jSONObject.put("totalMileage", aVUser.getTotalMileage());
            jSONObject.put("ftp", aVUser.getFtp());
            jSONObject.put(Property.ICON_TEXT_FIT_HEIGHT, aVUser.getHeight());
            jSONObject.put(UserData.GENDER_KEY, aVUser.getGender());
            jSONObject.put("birthDay", aVUser.getBirthDay());
            str = jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    private static AVUser json2Object(String str) {
        AVUser aVUser;
        JSONException e;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("signType");
            if (optInt != 0) {
                aVUser = new AVUser();
                try {
                    aVUser.setPassword(jSONObject.optString("password"));
                    aVUser.setObjectId(jSONObject.optString(AVUtils.objectIdTag));
                    aVUser.setUsername(jSONObject.optString(UserData.USERNAME_KEY));
                    aVUser.setSessionToken(jSONObject.optString("sessionToken"));
                    aVUser.setEmail(jSONObject.optString("email"));
                    aVUser.setAvatar(jSONObject.optString("avatar"));
                    aVUser.setDisplayName(jSONObject.optString("displayName"));
                    aVUser.setCountry(jSONObject.optString(GeocodingCriteria.TYPE_COUNTRY));
                    aVUser.setProvince(jSONObject.optString("province"));
                    aVUser.setCity(jSONObject.optString("city"));
                    aVUser.setClubName(jSONObject.optString("clubName"));
                    aVUser.setThirdToken(jSONObject.optString("thirdToken"));
                    aVUser.setSignType(optInt);
                    aVUser.setGeoCode(jSONObject.optString("geoCode"));
                    aVUser.setWeight(jSONObject.optDouble("weight"));
                    aVUser.setClubId(jSONObject.optString("clubId"));
                    aVUser.setFansNum(jSONObject.optInt("fansNum"));
                    aVUser.setFollowerNum(jSONObject.optInt("followerNum"));
                    aVUser.setFollowStatus(jSONObject.optInt("followStatus"));
                    aVUser.setSpeedxId(jSONObject.optInt("speedxId"));
                    aVUser.setMaxHeartRate(jSONObject.optInt("maxHeartRate"));
                    aVUser.setMobilePhoneNumber(jSONObject.optString("mobilePhoneNumber"));
                    aVUser.setEdited(jSONObject.optBoolean("edited"));
                    aVUser.setAuthenticated(jSONObject.optBoolean("isAuthenticated"));
                    aVUser.setTrainingId(jSONObject.optInt("trainingId"));
                    aVUser.setTrainingType(jSONObject.optInt("trainingType"));
                    aVUser.setClubLevel(jSONObject.optInt("clubLevel"));
                    aVUser.setTotalMileage(jSONObject.optDouble("totalMileage"));
                    aVUser.setFtp(jSONObject.optInt("ftp"));
                    aVUser.setHeight(jSONObject.optDouble(Property.ICON_TEXT_FIT_HEIGHT));
                    aVUser.setGender(jSONObject.optInt(UserData.GENDER_KEY));
                    aVUser.setBirthDay(jSONObject.optString("birthDay"));
                } catch (JSONException e2) {
                    e = e2;
                    e.printStackTrace();
                    return aVUser;
                }
            }
            aVUser = null;
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            aVUser = null;
            e = jSONException;
            e.printStackTrace();
            return aVUser;
        }
        return aVUser;
    }

    private static void savePwd(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(ApplicationContext.m5242j());
                defaultSharedPreferences.edit().putString(SP_KEY_PWD, Base64.encodeToString(C2573r.a(str.getBytes(), C2573r.a(ApplicationContext.m5242j().getApplicationContext().getResources().getAssets().open("rsa_public_key.pem"))), 0)).apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getPwd() {
        try {
            Object string = PreferenceManager.getDefaultSharedPreferences(ApplicationContext.m5242j()).getString(SP_KEY_PWD, null);
            if (!TextUtils.isEmpty(string)) {
                return new String(C2573r.a(Base64.decode(string, 0), C2573r.b(BeastBikes.getUserPrivateKey())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AVUser initAVCloudUser() {
        Throwable e;
        Throwable th;
        AVUser currentUser = getCurrentUser();
        if (currentUser != null) {
            return null;
        }
        byte[] a = C2802e.a(currentUserArchivePath());
        if (a == null || a.length == 0) {
            try {
                Object obj = "";
            } catch (Exception e2) {
                e = e2;
                logger.error("Init AVUser error :", e);
                return currentUser;
            }
        }
        obj = new String(a);
        if (TextUtils.isEmpty(obj)) {
            return currentUser;
        }
        try {
            AVUser aVUser;
            JSONObject jSONObject = new JSONObject(obj);
            if (jSONObject != null) {
                JSONObject optJSONObject = jSONObject.optJSONObject("serverData");
                if (optJSONObject != null) {
                    aVUser = new AVUser();
                    try {
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("authData");
                        if (optJSONObject2 != null) {
                            jSONObject = optJSONObject2.optJSONObject(AVThirdPartyUserAuth.SNS_TENCENT_WEIBO);
                            optJSONObject2 = optJSONObject2.optJSONObject(AVThirdPartyUserAuth.SNS_SINA_WEIBO);
                            if (jSONObject != null) {
                                aVUser.setSignType(8);
                                aVUser.setUsername(jSONObject.optString("openid"));
                            } else if (optJSONObject2 != null) {
                                aVUser.setSignType(4);
                                aVUser.setUsername(optJSONObject2.optString("uid"));
                            }
                            aVUser.setPassword("50b6w2e2hi");
                            currentUser = aVUser;
                            saveCurrentUser(currentUser);
                            return currentUser;
                        }
                        if (jSONObject.has("email")) {
                            aVUser.setSignType(1);
                            aVUser.setEmail(jSONObject.optString("email"));
                        }
                        currentUser = aVUser;
                        saveCurrentUser(currentUser);
                        return currentUser;
                    } catch (Throwable e3) {
                        th = e3;
                        currentUser = aVUser;
                        e = th;
                        logger.error("Init AVUser error :", e);
                        saveCurrentUser(currentUser);
                        return currentUser;
                    } catch (Throwable e32) {
                        th = e32;
                        currentUser = aVUser;
                        e = th;
                        logger.error("Init AVUser error :", e);
                        return currentUser;
                    }
                }
            }
            aVUser = currentUser;
            currentUser = aVUser;
        } catch (JSONException e4) {
            e = e4;
            logger.error("Init AVUser error :", e);
            saveCurrentUser(currentUser);
            return currentUser;
        }
        saveCurrentUser(currentUser);
        return currentUser;
    }

    private static File currentUserArchivePath() {
        return new File(C2802e.a() + "/currentUser");
    }

    public String getClubId() {
        return this.clubId;
    }

    public void setClubId(String str) {
        this.clubId = str;
    }

    public int getSpeedxId() {
        return this.speedxId;
    }

    public void setSpeedxId(int i) {
        this.speedxId = i;
    }

    public int getMaxHeartRate() {
        return this.maxHeartRate;
    }

    public void setMaxHeartRate(int i) {
        this.maxHeartRate = i;
    }

    public boolean isEdited() {
        return this.edited;
    }

    public void setEdited(boolean z) {
        this.edited = z;
    }

    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    public void setAuthenticated(boolean z) {
        this.isAuthenticated = z;
    }

    public int getAchievementNum() {
        return this.achievementNum;
    }

    public void setAchievementNum(int i) {
        this.achievementNum = i;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double d) {
        this.height = d;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int i) {
        this.gender = i;
    }

    public String getBirthDay() {
        return this.birthDay;
    }

    public void setBirthDay(String str) {
        this.birthDay = str;
    }
}
