package com.avos.avoscloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.avos.avoscloud.LogUtil.avlog;
import com.avos.avoscloud.LogUtil.log;
import io.rong.imlib.statistics.UserData;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@JSONType(asm = false, ignores = {"query", "password"})
public class AVUser extends AVObject {
    public static final String FOLLOWEE_TAG = "followee";
    public static final String FOLLOWER_TAG = "follower";
    public static final String LOG_TAG = AVUser.class.getSimpleName();
    private static final String accessTokenTag = "access_token";
    private static final String anonymousTag = "anonymous";
    private static final String authDataTag = "authData";
    private static transient boolean enableAutomatic = false;
    private static final String expiresAtTag = "expires_at";
    private static Class<? extends AVUser> subClazz;
    private boolean anonymous;
    private String email;
    private transient String facebookToken;
    private transient boolean isNew;
    private String mobilePhoneNumber;
    private transient boolean needTransferFromAnonymousUser;
    private transient String password;
    private transient String qqWeiboToken;
    private String sessionToken;
    private transient String sinaWeiboToken;
    private transient String twitterToken;
    private String username;

    public static class AVThirdPartyUserAuth {
        public static final String SNS_SINA_WEIBO = "weibo";
        public static final String SNS_TENCENT_WEIBO = "qq";
        public static final String SNS_TENCENT_WEIXIN = "weixin";
        String accessToken;
        String expiredAt;
        String snsType;
        String userId;

        public AVThirdPartyUserAuth(String str, String str2, String str3, String str4) {
            this.accessToken = str;
            this.snsType = str3;
            this.expiredAt = str2;
            this.userId = str4;
        }

        protected static String platformUserIdTag(String str) {
            if (SNS_TENCENT_WEIBO.equalsIgnoreCase(str) || SNS_TENCENT_WEIXIN.equalsIgnoreCase(str)) {
                return "openid";
            }
            return "uid";
        }

        public String getAccessToken() {
            return this.accessToken;
        }

        public void setAccessToken(String str) {
            this.accessToken = str;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public String getExpireAt() {
            return this.expiredAt;
        }

        public void setExpireAt(String str) {
            this.expiredAt = str;
        }

        public String getSnsType() {
            return this.snsType;
        }

        public void setSnsType(String str) {
            this.snsType = str;
        }
    }

    private static File currentUserArchivePath() {
        return new File(AVPersistenceUtils.getPaasDocumentDir() + "/currentUser");
    }

    private static boolean userArchiveExist() {
        return currentUserArchivePath().exists();
    }

    public String getFacebookToken() {
        return this.facebookToken;
    }

    void setFacebookToken(String str) {
        this.facebookToken = str;
    }

    public String getTwitterToken() {
        return this.twitterToken;
    }

    void setTwitterToken(String str) {
        this.twitterToken = str;
    }

    public String getQqWeiboToken() {
        return this.qqWeiboToken;
    }

    void setQqWeiboToken(String str) {
        this.qqWeiboToken = str;
    }

    String getPassword() {
        return this.password;
    }

    static void setEnableAutomatic(boolean z) {
        enableAutomatic = z;
    }

    void setNew(boolean z) {
        this.isNew = z;
    }

    public AVUser() {
        super(userClassName());
    }

    protected void rebuildInstanceData() {
        super.rebuildInstanceData();
        this.sessionToken = (String) get("sessionToken");
        this.username = (String) get(UserData.USERNAME_KEY);
        processAuthData(null);
        this.email = (String) get("email");
        this.mobilePhoneNumber = (String) get("mobilePhoneNumber");
    }

    public static void enableAutomaticUser() {
        enableAutomatic = true;
    }

    public static boolean isEnableAutomatic() {
        return enableAutomatic;
    }

    public static void disableAutomaticUser() {
        enableAutomatic = false;
    }

    public static synchronized void changeCurrentUser(AVUser aVUser, boolean z) {
        synchronized (AVUser.class) {
            if (aVUser != null) {
                aVUser.password = null;
            }
            File currentUserArchivePath = currentUserArchivePath();
            if (aVUser != null && z) {
                try {
                    aVUser.lock.readLock().lock();
                    String toJSONString = JSON.toJSONString((Object) aVUser, ObjectValueFilter.instance, SerializerFeature.WriteClassName, SerializerFeature.DisableCircularReferenceDetect);
                    if (AVOSCloud.showInternalDebugLog()) {
                        log.m3514d(toJSONString);
                    }
                    AVPersistenceUtils.saveContentToFile(toJSONString, currentUserArchivePath);
                    aVUser.lock.readLock().unlock();
                } catch (Exception e) {
                    log.m3522e(LOG_TAG, "", e);
                    aVUser.lock.readLock().unlock();
                } catch (Throwable th) {
                    aVUser.lock.readLock().unlock();
                }
            } else if (z) {
                AVPersistenceUtils.removeLock(currentUserArchivePath.getAbsolutePath());
                currentUserArchivePath.delete();
            }
            PaasClient.storageInstance().setCurrentUser(aVUser);
        }
    }

    public static AVUser getCurrentUser() {
        return getCurrentUser(AVUser.class);
    }

    public static <T extends AVUser> T getCurrentUser(Class<T> cls) {
        T cast;
        T currentUser = PaasClient.storageInstance().getCurrentUser();
        if (currentUser != null) {
            if (!cls.isAssignableFrom(currentUser.getClass())) {
                cast = cast(currentUser, cls);
            }
            cast = currentUser;
        } else {
            if (userArchiveExist()) {
                synchronized (AVUser.class) {
                    String readContentFromFile = AVPersistenceUtils.readContentFromFile(currentUserArchivePath());
                    if (readContentFromFile == null) {
                        cast = currentUser;
                    } else if (readContentFromFile.indexOf("@type") > 0) {
                        try {
                            cast = (AVUser) JSON.parse(readContentFromFile);
                            if (cls.isAssignableFrom(cast.getClass())) {
                                currentUser = cast;
                            } else {
                                currentUser = cast(cast, cls);
                            }
                            PaasClient.storageInstance().setCurrentUser(currentUser);
                            cast = currentUser;
                        } catch (Exception e) {
                            log.m3522e(LOG_TAG, readContentFromFile, e);
                            cast = currentUser;
                        }
                    } else {
                        currentUser = newAVUser(cls, null);
                        AVUtils.copyPropertiesFromJsonStringToAVObject(readContentFromFile, currentUser);
                        changeCurrentUser(currentUser, true);
                        cast = currentUser;
                    }
                }
            }
            cast = currentUser;
        }
        if (!enableAutomatic || cast != null) {
            return cast;
        }
        cast = newAVUser(cls, null);
        changeCurrentUser(cast, false);
        return cast;
    }

    static String userClassName() {
        return AVPowerfulUtils.getAVClassName(AVUser.class.getSimpleName());
    }

    void setNewFlag(boolean z) {
        this.isNew = z;
    }

    public String getEmail() {
        return this.email;
    }

    public static <T extends AVUser> AVQuery<T> getUserQuery(Class<T> cls) {
        return new AVQuery(userClassName(), cls);
    }

    public static AVQuery<AVUser> getQuery() {
        return AVObject.getQuery(AVUser.class);
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    void setSessionToken(String str) {
        this.sessionToken = str;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAuthenticated() {
        return (AVUtils.isBlankString(this.sessionToken) && AVUtils.isBlankString(this.sinaWeiboToken) && AVUtils.isBlankString(this.qqWeiboToken)) ? false : true;
    }

    public boolean isAnonymous() {
        return this.anonymous;
    }

    protected void setAnonymous(boolean z) {
        this.anonymous = z;
    }

    public boolean isNew() {
        return this.isNew;
    }

    public static AVUser logIn(String str, String str2) throws AVException {
        return logIn(str, str2, AVUser.class);
    }

    public static <T extends AVUser> T logIn(String str, String str2, Class<T> cls) throws AVException {
        final AVUser[] aVUserArr = new AVUser[]{null};
        logInInBackground(str, str2, true, new LogInCallback<T>() {
            public void done(T t, AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(aVException);
                } else {
                    aVUserArr[0] = t;
                }
            }

            public boolean mustRunOnUIThread() {
                return false;
            }
        }, cls);
        if (!AVExceptionHolder.exists()) {
            return aVUserArr[0];
        }
        throw AVExceptionHolder.remove();
    }

    private static String logInPath() {
        return "login";
    }

    public static void logInInBackground(String str, String str2, LogInCallback<AVUser> logInCallback) {
        logInInBackground(str, str2, logInCallback, AVUser.class);
    }

    public static <T extends AVUser> void logInInBackground(String str, String str2, LogInCallback<T> logInCallback, Class<T> cls) {
        logInInBackground(str, str2, false, logInCallback, cls);
    }

    private static Map<String, String> createUserMap(String str, String str2, String str3) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(UserData.USERNAME_KEY, str);
        if (AVUtils.isBlankString(str)) {
            throw new IllegalArgumentException("Blank username.");
        }
        if (!AVUtils.isBlankString(str2)) {
            hashMap.put("password", str2);
        }
        if (!AVUtils.isBlankString(str3)) {
            hashMap.put("email", str3);
        }
        return hashMap;
    }

    private static Map<String, String> createUserMap(String str, String str2, String str3, String str4, String str5) {
        Map<String, String> hashMap = new HashMap();
        if (AVUtils.isBlankString(str) && AVUtils.isBlankString(str4)) {
            throw new IllegalArgumentException("Blank username and blank mobile phone number");
        }
        if (!AVUtils.isBlankString(str)) {
            hashMap.put(UserData.USERNAME_KEY, str);
        }
        if (!AVUtils.isBlankString(str2)) {
            hashMap.put("password", str2);
        }
        if (!AVUtils.isBlankString(str3)) {
            hashMap.put("email", str3);
        }
        if (!AVUtils.isBlankString(str4)) {
            hashMap.put("mobilePhoneNumber", str4);
        }
        if (!AVUtils.isBlankString(str5)) {
            hashMap.put("smsCode", str5);
        }
        return hashMap;
    }

    private static <T extends AVUser> void logInInBackground(String str, String str2, boolean z, final LogInCallback<T> logInCallback, Class<T> cls) {
        Map createUserMap = createUserMap(str, str2, "");
        final AVUser newAVUser = newAVUser(cls, logInCallback);
        if (newAVUser != null) {
            newAVUser.put(UserData.USERNAME_KEY, str, false);
            PaasClient.storageInstance().postObject(logInPath(), JSON.toJSONString(createUserMap), z, false, new GenericObjectCallback() {
                public void onSuccess(String str, AVException aVException) {
                    Object obj = newAVUser;
                    if (AVUtils.isBlankContent(str)) {
                        aVException = new AVException(101, "User is not found.");
                        obj = null;
                    } else {
                        AVUtils.copyPropertiesFromJsonStringToAVObject(str, newAVUser);
                        newAVUser.processAuthData(null);
                        AVUser.changeCurrentUser(newAVUser, true);
                    }
                    if (logInCallback != null) {
                        logInCallback.internalDone(obj, aVException);
                    }
                }

                public void onFailure(Throwable th, String str) {
                    if (logInCallback != null) {
                        logInCallback.internalDone(null, AVErrorUtils.createException(th, str));
                    }
                }
            }, null, null);
        }
    }

    public static AVUser loginByMobilePhoneNumber(String str, String str2) throws AVException {
        return loginByMobilePhoneNumber(str, str2, AVUser.class);
    }

    public static <T extends AVUser> T loginByMobilePhoneNumber(String str, String str2, Class<T> cls) throws AVException {
        final AVUser[] aVUserArr = new AVUser[]{null};
        loginByMobilePhoneNumberInBackground(str, str2, true, new LogInCallback<T>() {
            public void done(T t, AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(aVException);
                } else {
                    aVUserArr[0] = t;
                }
            }

            public boolean mustRunOnUIThread() {
                return false;
            }
        }, cls);
        if (!AVExceptionHolder.exists()) {
            return aVUserArr[0];
        }
        throw AVExceptionHolder.remove();
    }

    public static void loginByMobilePhoneNumberInBackground(String str, String str2, LogInCallback<AVUser> logInCallback) {
        loginByMobilePhoneNumberInBackground(str, str2, false, logInCallback, AVUser.class);
    }

    public static <T extends AVUser> void loginByMobilePhoneNumberInBackground(String str, String str2, LogInCallback<T> logInCallback, Class<T> cls) {
        loginByMobilePhoneNumberInBackground(str, str2, false, logInCallback, cls);
    }

    private static <T extends AVUser> void loginByMobilePhoneNumberInBackground(String str, String str2, boolean z, final LogInCallback<T> logInCallback, Class<T> cls) {
        Map createUserMap = createUserMap(null, str2, null, str, null);
        final AVUser newAVUser = newAVUser(cls, logInCallback);
        if (newAVUser != null) {
            newAVUser.setMobilePhoneNumber(str);
            PaasClient.storageInstance().postObject(logInPath(), JSON.toJSONString(createUserMap), z, false, new GenericObjectCallback() {
                public void onSuccess(String str, AVException aVException) {
                    Object obj = newAVUser;
                    if (AVUtils.isBlankContent(str)) {
                        obj = null;
                        aVException = new AVException(101, "User is not found.");
                    } else {
                        AVUtils.copyPropertiesFromJsonStringToAVObject(str, newAVUser);
                        AVUser.changeCurrentUser(newAVUser, true);
                    }
                    if (logInCallback != null) {
                        logInCallback.internalDone(obj, aVException);
                    }
                }

                public void onFailure(Throwable th, String str) {
                    if (logInCallback != null) {
                        logInCallback.internalDone(null, AVErrorUtils.createException(th, str));
                    }
                }
            }, null, null);
        }
    }

    public static AVUser loginBySMSCode(String str, String str2) throws AVException {
        return loginBySMSCode(str, str2, AVUser.class);
    }

    public static <T extends AVUser> T loginBySMSCode(String str, String str2, Class<T> cls) throws AVException {
        final AVUser[] aVUserArr = new AVUser[]{null};
        loginBySMSCodeInBackground(str, str2, true, new LogInCallback<T>() {
            public void done(T t, AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(aVException);
                } else {
                    aVUserArr[0] = t;
                }
            }

            public boolean mustRunOnUIThread() {
                return false;
            }
        }, cls);
        if (!AVExceptionHolder.exists()) {
            return aVUserArr[0];
        }
        throw AVExceptionHolder.remove();
    }

    public static void loginBySMSCodeInBackground(String str, String str2, LogInCallback<AVUser> logInCallback) {
        loginBySMSCodeInBackground(str, str2, false, logInCallback, AVUser.class);
    }

    public static <T extends AVUser> void loginBySMSCodeInBackground(String str, String str2, LogInCallback<T> logInCallback, Class<T> cls) {
        loginBySMSCodeInBackground(str, str2, false, logInCallback, cls);
    }

    private static <T extends AVUser> void loginBySMSCodeInBackground(String str, String str2, boolean z, final LogInCallback<T> logInCallback, Class<T> cls) {
        Map createUserMap = createUserMap(null, null, "", str, str2);
        final AVUser newAVUser = newAVUser(cls, logInCallback);
        if (newAVUser != null) {
            newAVUser.setMobilePhoneNumber(str);
            PaasClient.storageInstance().postObject(logInPath(), JSON.toJSONString(createUserMap), z, false, new GenericObjectCallback() {
                public void onSuccess(String str, AVException aVException) {
                    Object obj = newAVUser;
                    if (AVUtils.isBlankContent(str)) {
                        obj = null;
                        aVException = new AVException(101, "User is not found.");
                    } else {
                        AVUtils.copyPropertiesFromJsonStringToAVObject(str, newAVUser);
                        AVUser.changeCurrentUser(newAVUser, true);
                    }
                    if (logInCallback != null) {
                        logInCallback.internalDone(obj, aVException);
                    }
                }

                public void onFailure(Throwable th, String str) {
                    if (logInCallback != null) {
                        logInCallback.internalDone(null, AVErrorUtils.createException(th, str));
                    }
                }
            }, null, null);
        }
    }

    public static AVUser signUpOrLoginByMobilePhone(String str, String str2) throws AVException {
        return signUpOrLoginByMobilePhone(str, str2, AVUser.class);
    }

    public static <T extends AVUser> T signUpOrLoginByMobilePhone(String str, String str2, Class<T> cls) throws AVException {
        final AVUser[] aVUserArr = new AVUser[]{null};
        signUpOrLoginByMobilePhoneInBackground(str, str2, true, cls, new LogInCallback<T>() {
            public void done(T t, AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(aVException);
                } else {
                    aVUserArr[0] = t;
                }
            }

            public boolean mustRunOnUIThread() {
                return false;
            }
        });
        if (!AVExceptionHolder.exists()) {
            return aVUserArr[0];
        }
        throw AVExceptionHolder.remove();
    }

    public static void signUpOrLoginByMobilePhoneInBackground(String str, String str2, LogInCallback<AVUser> logInCallback) {
        signUpOrLoginByMobilePhoneInBackground(str, str2, AVUser.class, logInCallback);
    }

    public static <T extends AVUser> void signUpOrLoginByMobilePhoneInBackground(String str, String str2, Class<T> cls, LogInCallback<T> logInCallback) {
        signUpOrLoginByMobilePhoneInBackground(str, str2, false, cls, logInCallback);
    }

    private static <T extends AVUser> void signUpOrLoginByMobilePhoneInBackground(String str, String str2, boolean z, Class<T> cls, final LogInCallback<T> logInCallback) {
        if (!AVUtils.isBlankString(str2)) {
            Map createUserMap = createUserMap(null, null, "", str, str2);
            final AVUser newAVUser = newAVUser(cls, logInCallback);
            if (newAVUser != null) {
                newAVUser.setMobilePhoneNumber(str);
                PaasClient.storageInstance().postObject("usersByMobilePhone", JSON.toJSONString(createUserMap), z, false, new GenericObjectCallback() {
                    public void onSuccess(String str, AVException aVException) {
                        Object obj = newAVUser;
                        if (AVUtils.isBlankContent(str)) {
                            obj = null;
                            aVException = new AVException(101, "User is not found.");
                        } else {
                            AVUtils.copyPropertiesFromJsonStringToAVObject(str, newAVUser);
                            AVUser.changeCurrentUser(newAVUser, true);
                        }
                        if (logInCallback != null) {
                            logInCallback.internalDone(obj, aVException);
                        }
                    }

                    public void onFailure(Throwable th, String str) {
                        if (logInCallback != null) {
                            logInCallback.internalDone(null, AVErrorUtils.createException(th, str));
                        }
                    }
                }, null, null);
            }
        } else if (logInCallback != null) {
            logInCallback.internalDone(null, new AVException(-1, "SMS Code can't be empty"));
        } else {
            avlog.m3507e("SMS Code can't be empty");
        }
    }

    public static <T extends AVUser> T newAVUser(Class<T> cls, LogInCallback<T> logInCallback) {
        try {
            return (AVUser) cls.newInstance();
        } catch (Throwable e) {
            if (logInCallback != null) {
                logInCallback.internalDone(null, AVErrorUtils.createException(e, null));
                return null;
            }
            throw new AVRuntimeException("Create user instance failed.", e);
        }
    }

    protected static <T extends AVUser> T newAVUser() {
        return newAVUser(subClazz == null ? AVUser.class : subClazz, null);
    }

    public static void logOut() {
        changeCurrentUser(null, true);
        PaasClient.storageInstance().setDefaultACL(null);
    }

    public void put(String str, Object obj) {
        super.put(str, obj);
    }

    public void remove(String str) {
        super.remove(str);
    }

    public static void requestPasswordReset(String str) {
        requestPasswordResetInBackground(str, true, null);
    }

    public static void requestPasswordResetInBackground(String str, RequestPasswordResetCallback requestPasswordResetCallback) {
        requestPasswordResetInBackground(str, false, requestPasswordResetCallback);
    }

    private static void requestPasswordResetInBackground(String str, boolean z, final RequestPasswordResetCallback requestPasswordResetCallback) {
        Map hashMap = new HashMap();
        hashMap.put("email", str);
        PaasClient.storageInstance().postObject("requestPasswordReset", AVUtils.jsonStringFromMapWithNull(hashMap), z, false, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                if (requestPasswordResetCallback != null) {
                    requestPasswordResetCallback.internalDone(null, null);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (requestPasswordResetCallback != null) {
                    requestPasswordResetCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        }, null, null);
    }

    public void updatePassword(String str, String str2) throws AVException {
        updatePasswordInBackground(str, str2, new UpdatePasswordCallback() {
            public void done(AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(aVException);
                }
            }

            public boolean mustRunOnUIThread() {
                return false;
            }
        }, true);
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    public void updatePasswordInBackground(String str, String str2, UpdatePasswordCallback updatePasswordCallback) {
        updatePasswordInBackground(str, str2, updatePasswordCallback, false);
    }

    private void updatePasswordInBackground(String str, String str2, final UpdatePasswordCallback updatePasswordCallback, boolean z) {
        if (!isAuthenticated() || AVUtils.isBlankString(getObjectId())) {
            updatePasswordCallback.internalDone(AVErrorUtils.sessionMissingException());
            return;
        }
        String format = String.format("users/%s/updatePassword", new Object[]{getObjectId()});
        Map hashMap = new HashMap();
        hashMap.put("old_password", str);
        hashMap.put("new_password", str2);
        PaasClient.storageInstance().putObject(format, AVUtils.restfulServerData(hashMap), z, headerMap(), new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                updatePasswordCallback.internalDone(aVException);
            }

            public void onFailure(Throwable th, String str) {
                updatePasswordCallback.internalDone(AVErrorUtils.createException(th, str));
            }
        }, getObjectId(), getObjectId());
    }

    public static void requestPasswordResetBySmsCode(String str) throws AVException {
        requestPasswordResetBySmsCodeInBackground(str, true, new RequestMobileCodeCallback() {
            public void done(AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(aVException);
                }
            }

            public boolean mustRunOnUIThread() {
                return false;
            }
        });
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    public static void requestPasswordResetBySmsCodeInBackground(String str, RequestMobileCodeCallback requestMobileCodeCallback) {
        requestPasswordResetBySmsCodeInBackground(str, false, requestMobileCodeCallback);
    }

    protected static void requestPasswordResetBySmsCodeInBackground(String str, boolean z, final RequestMobileCodeCallback requestMobileCodeCallback) {
        if (AVUtils.isBlankString(str) || !AVUtils.checkMobilePhoneNumber(str)) {
            requestMobileCodeCallback.internalDone(new AVException(127, "Invalid Phone Number"));
            return;
        }
        Map hashMap = new HashMap();
        hashMap.put("mobilePhoneNumber", str);
        PaasClient.storageInstance().postObject("requestPasswordResetBySmsCode", AVUtils.jsonStringFromMapWithNull(hashMap), z, false, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                if (requestMobileCodeCallback != null) {
                    requestMobileCodeCallback.internalDone(null, null);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (requestMobileCodeCallback != null) {
                    requestMobileCodeCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        }, null, null);
    }

    public static void resetPasswordBySmsCode(String str, String str2) throws AVException {
        resetPasswordBySmsCodeInBackground(str, str2, true, new UpdatePasswordCallback() {
            public void done(AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(aVException);
                }
            }

            public boolean mustRunOnUIThread() {
                return false;
            }
        });
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    public static void resetPasswordBySmsCodeInBackground(String str, String str2, UpdatePasswordCallback updatePasswordCallback) {
        resetPasswordBySmsCodeInBackground(str, str2, false, updatePasswordCallback);
    }

    protected static void resetPasswordBySmsCodeInBackground(String str, String str2, boolean z, final UpdatePasswordCallback updatePasswordCallback) {
        if (AVUtils.isBlankString(str) || !AVUtils.checkMobileVerifyCode(str)) {
            updatePasswordCallback.internalDone(new AVException(127, "Invalid Verify Code"));
            return;
        }
        String format = String.format("resetPasswordBySmsCode/%s", new Object[]{str});
        Map hashMap = new HashMap();
        hashMap.put("password", str2);
        PaasClient.storageInstance().putObject(format, AVUtils.restfulServerData(hashMap), z, null, new GenericObjectCallback() {
            public void onFailure(Throwable th, String str) {
                if (updatePasswordCallback != null) {
                    updatePasswordCallback.internalDone(new AVException(str, th));
                }
            }

            public void onSuccess(String str, AVException aVException) {
                updatePasswordCallback.done(aVException);
            }
        }, null, null);
    }

    public static void requestEmailVerify(String str) {
        requestEmailVerfiyInBackground(str, true, null);
    }

    public static void requestEmailVerfiyInBackground(String str, RequestEmailVerifyCallback requestEmailVerifyCallback) {
        requestEmailVerfiyInBackground(str, false, requestEmailVerifyCallback);
    }

    private static void requestEmailVerfiyInBackground(String str, boolean z, final RequestEmailVerifyCallback requestEmailVerifyCallback) {
        if (AVUtils.isBlankString(str) || !AVUtils.checkEmailAddress(str)) {
            requestEmailVerifyCallback.internalDone(new AVException((int) AVException.INVALID_EMAIL_ADDRESS, "Invalid Email"));
            return;
        }
        Map hashMap = new HashMap();
        hashMap.put("email", str);
        PaasClient.storageInstance().postObject("requestEmailVerify", AVUtils.jsonStringFromMapWithNull(hashMap), z, false, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                if (requestEmailVerifyCallback != null) {
                    requestEmailVerifyCallback.internalDone(null, null);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (requestEmailVerifyCallback != null) {
                    requestEmailVerifyCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        }, null, null);
    }

    public static void requestMobilePhoneVerify(String str) throws AVException {
        requestMobilePhoneVerifyInBackground(str, true, new RequestMobileCodeCallback() {
            public void done(AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(aVException);
                }
            }

            public boolean mustRunOnUIThread() {
                return false;
            }
        });
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    @Deprecated
    public static void requestMobilePhoneVerifyInBackgroud(String str, RequestMobileCodeCallback requestMobileCodeCallback) {
        requestMobilePhoneVerifyInBackground(str, false, requestMobileCodeCallback);
    }

    public static void requestMobilePhoneVerifyInBackground(String str, RequestMobileCodeCallback requestMobileCodeCallback) {
        requestMobilePhoneVerifyInBackground(str, false, requestMobileCodeCallback);
    }

    private static void requestMobilePhoneVerifyInBackground(String str, boolean z, final RequestMobileCodeCallback requestMobileCodeCallback) {
        if (AVUtils.isBlankString(str) || !AVUtils.checkMobilePhoneNumber(str)) {
            requestMobileCodeCallback.internalDone(new AVException(127, "Invalid Phone Number"));
            return;
        }
        Map hashMap = new HashMap();
        hashMap.put("mobilePhoneNumber", str);
        PaasClient.storageInstance().postObject("requestMobilePhoneVerify", AVUtils.jsonStringFromMapWithNull(hashMap), z, false, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                if (requestMobileCodeCallback != null) {
                    requestMobileCodeCallback.internalDone(null, null);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (requestMobileCodeCallback != null) {
                    requestMobileCodeCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        }, null, null);
    }

    public static void requestLoginSmsCode(String str) throws AVException {
        requestLoginSmsCodeInBackground(str, true, new RequestMobileCodeCallback() {
            public void done(AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(aVException);
                }
            }

            public boolean mustRunOnUIThread() {
                return false;
            }
        });
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    public static void requestLoginSmsCodeInBackground(String str, RequestMobileCodeCallback requestMobileCodeCallback) {
        requestLoginSmsCodeInBackground(str, false, requestMobileCodeCallback);
    }

    private static void requestLoginSmsCodeInBackground(String str, boolean z, final RequestMobileCodeCallback requestMobileCodeCallback) {
        if (AVUtils.isBlankString(str) || !AVUtils.checkMobilePhoneNumber(str)) {
            requestMobileCodeCallback.internalDone(new AVException(127, "Invalid Phone Number"));
            return;
        }
        Map hashMap = new HashMap();
        hashMap.put("mobilePhoneNumber", str);
        PaasClient.storageInstance().postObject("requestLoginSmsCode", AVUtils.jsonStringFromMapWithNull(hashMap), z, false, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                if (requestMobileCodeCallback != null) {
                    requestMobileCodeCallback.internalDone(null, null);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (requestMobileCodeCallback != null) {
                    requestMobileCodeCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        }, null, null);
    }

    public static void verifyMobilePhone(String str) throws AVException {
        verifyMobilePhoneInBackground(true, str, new AVMobilePhoneVerifyCallback() {
            public void done(AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(aVException);
                }
            }

            public boolean mustRunOnUIThread() {
                return false;
            }
        });
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    @Deprecated
    public static void verifyMobilePhoneInBackgroud(String str, AVMobilePhoneVerifyCallback aVMobilePhoneVerifyCallback) {
        verifyMobilePhoneInBackground(false, str, aVMobilePhoneVerifyCallback);
    }

    public static void verifyMobilePhoneInBackground(String str, AVMobilePhoneVerifyCallback aVMobilePhoneVerifyCallback) {
        verifyMobilePhoneInBackground(false, str, aVMobilePhoneVerifyCallback);
    }

    private static void verifyMobilePhoneInBackground(boolean z, String str, final AVMobilePhoneVerifyCallback aVMobilePhoneVerifyCallback) {
        if (AVUtils.isBlankString(str) || !AVUtils.checkMobileVerifyCode(str)) {
            aVMobilePhoneVerifyCallback.internalDone(new AVException(127, "Invalid Verify Code"));
            return;
        }
        PaasClient.storageInstance().postObject(String.format("verifyMobilePhone/%s", new Object[]{str}), AVUtils.restfulServerData(null), z, false, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                if (aVMobilePhoneVerifyCallback != null) {
                    aVMobilePhoneVerifyCallback.internalDone(null, null);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (aVMobilePhoneVerifyCallback != null) {
                    aVMobilePhoneVerifyCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        }, null, null);
    }

    public void setEmail(String str) {
        this.email = str;
        put("email", str);
    }

    public void setPassword(String str) {
        this.password = str;
        put("password", str);
        markAnonymousUserTransfer();
    }

    public void setUsername(String str) {
        this.username = str;
        put(UserData.USERNAME_KEY, str);
        markAnonymousUserTransfer();
    }

    public String getMobilePhoneNumber() {
        return this.mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String str) {
        this.mobilePhoneNumber = str;
        put("mobilePhoneNumber", str);
    }

    public boolean isMobilePhoneVerified() {
        return getBoolean("mobilePhoneVerified");
    }

    void setMobilePhoneVerified(boolean z) {
        put("mobileVerified", Boolean.valueOf(z));
    }

    private String signUpPath() {
        return "users";
    }

    private void signUp(boolean z, final SignUpCallback signUpCallback) {
        if (z) {
            try {
                save();
                if (signUpCallback != null) {
                    signUpCallback.internalDone(null);
                    return;
                }
                return;
            } catch (AVException e) {
                if (signUpCallback != null) {
                    signUpCallback.internalDone(e);
                    return;
                }
                return;
            }
        }
        saveInBackground(new SaveCallback() {
            public void done(AVException aVException) {
                if (signUpCallback != null) {
                    signUpCallback.internalDone(aVException);
                }
            }
        });
    }

    public void signUp() throws AVException {
        signUp(true, new SignUpCallback() {
            public void done(AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(aVException);
                }
            }

            protected boolean mustRunOnUIThread() {
                return false;
            }
        });
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    public void signUpInBackground(SignUpCallback signUpCallback) {
        signUp(false, signUpCallback);
    }

    void setSinaWeiboToken(String str) {
        this.sinaWeiboToken = str;
    }

    public String getSinaWeiboToken() {
        return this.sinaWeiboToken;
    }

    void setQQWeiboToken(String str) {
        this.qqWeiboToken = str;
    }

    public String getQQWeiboToken() {
        return this.qqWeiboToken;
    }

    protected void onSaveSuccess() {
        super.onSaveSuccess();
        processAuthData(null);
        if (!AVUtils.isBlankString(this.sessionToken)) {
            changeCurrentUser(this, true);
        }
    }

    protected void onDataSynchronized() {
        processAuthData(null);
        if (!AVUtils.isBlankString(this.sessionToken)) {
            changeCurrentUser(this, true);
        }
    }

    protected Map<String, String> headerMap() {
        Map<String, String> hashMap = new HashMap();
        if (!AVUtils.isBlankString(this.sessionToken)) {
            hashMap.put(PaasClient.sessionTokenField, this.sessionToken);
        }
        return hashMap;
    }

    static AVUser userFromSinaWeibo(String str, String str2) {
        AVUser newAVUser = newAVUser();
        newAVUser.sinaWeiboToken = str;
        newAVUser.username = str2;
        return newAVUser;
    }

    static AVUser userFromQQWeibo(String str, String str2) {
        AVUser newAVUser = newAVUser();
        newAVUser.qqWeiboToken = str;
        newAVUser.username = str2;
        return newAVUser;
    }

    private boolean checkUserAuthentication(AVCallback aVCallback) {
        if (isAuthenticated() && !AVUtils.isBlankString(getObjectId())) {
            return true;
        }
        if (aVCallback != null) {
            aVCallback.internalDone(AVErrorUtils.createException(206, "No valid session token, make sure signUp or login has been called."));
        }
        return false;
    }

    public void followInBackground(String str, FollowCallback followCallback) {
        followInBackground(str, null, followCallback);
    }

    public void followInBackground(String str, Map<String, Object> map, final FollowCallback followCallback) {
        if (checkUserAuthentication(followCallback)) {
            String followEndPoint = AVPowerfulUtils.getFollowEndPoint(getObjectId(), str);
            String str2 = "";
            if (map != null) {
                str2 = AVUtils.restfulServerData(map);
            }
            PaasClient.storageInstance().postObject(followEndPoint, str2, false, new GenericObjectCallback() {
                public void onSuccess(String str, AVException aVException) {
                    super.onSuccess(str, aVException);
                    if (followCallback != null) {
                        followCallback.internalDone(AVUser.this, null);
                    }
                }

                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    if (followCallback != null) {
                        followCallback.internalDone(null, AVErrorUtils.createException(th, str));
                    }
                }
            });
        }
    }

    public void unfollowInBackground(String str, final FollowCallback followCallback) {
        if (checkUserAuthentication(followCallback)) {
            PaasClient.storageInstance().deleteObject(AVPowerfulUtils.getFollowEndPoint(getObjectId(), str), false, new GenericObjectCallback() {
                public void onSuccess(String str, AVException aVException) {
                    super.onSuccess(str, aVException);
                    if (followCallback != null) {
                        followCallback.internalDone(AVUser.this, null);
                    }
                }

                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    if (followCallback != null) {
                        followCallback.internalDone(null, AVErrorUtils.createException(th, str));
                    }
                }
            }, null, null);
        }
    }

    private List<AVUser> processResultByTag(String str, String str2) {
        List<AVUser> linkedList = new LinkedList();
        if (AVUtils.isBlankString(str)) {
            return linkedList;
        }
        processResultList(((AVFollowResponse) JSON.parseObject(str, new AVFollowResponse().getClass())).results, linkedList, str2);
        return linkedList;
    }

    private Map<String, List<AVUser>> processFollowerAndFollowee(String str) {
        Map<String, List<AVUser>> hashMap = new HashMap();
        if (AVUtils.isBlankString(str)) {
            return hashMap;
        }
        AVFollowResponse aVFollowResponse = (AVFollowResponse) JSON.parseObject(str, new AVFollowResponse().getClass());
        List linkedList = new LinkedList();
        List linkedList2 = new LinkedList();
        processResultList(aVFollowResponse.followers, linkedList, FOLLOWER_TAG);
        processResultList(aVFollowResponse.followees, linkedList2, FOLLOWEE_TAG);
        hashMap.put(FOLLOWER_TAG, linkedList);
        hashMap.put(FOLLOWEE_TAG, linkedList2);
        return hashMap;
    }

    private void processResultList(Map[] mapArr, List<AVUser> list, String str) {
        for (Map map : mapArr) {
            if (!(map == null || map.isEmpty())) {
                list.add((AVUser) AVUtils.getObjectFrom(map.get(str)));
            }
        }
    }

    public static <T extends AVUser> AVQuery<T> followerQuery(String str, Class<T> cls) {
        if (AVUtils.isBlankString(str)) {
            throw new IllegalArgumentException("Blank user objectId.");
        }
        AVQuery aVFellowshipQuery = new AVFellowshipQuery("_Follower", cls);
        aVFellowshipQuery.whereEqualTo("user", AVObject.createWithoutData("_User", str));
        aVFellowshipQuery.setFriendshipTag(FOLLOWER_TAG);
        return aVFellowshipQuery;
    }

    public <T extends AVUser> AVQuery<T> followerQuery(Class<T> cls) throws AVException {
        if (!AVUtils.isBlankString(getObjectId())) {
            return followerQuery(getObjectId(), cls);
        }
        throw AVErrorUtils.sessionMissingException();
    }

    public static <T extends AVUser> AVQuery<T> followeeQuery(String str, Class<T> cls) {
        if (AVUtils.isBlankString(str)) {
            throw new IllegalArgumentException("Blank user objectId.");
        }
        AVQuery aVFellowshipQuery = new AVFellowshipQuery("_Followee", cls);
        aVFellowshipQuery.whereEqualTo("user", AVObject.createWithoutData("_User", str));
        aVFellowshipQuery.setFriendshipTag(FOLLOWEE_TAG);
        return aVFellowshipQuery;
    }

    public <T extends AVUser> AVQuery<T> followeeQuery(Class<T> cls) throws AVException {
        if (!AVUtils.isBlankString(getObjectId())) {
            return followeeQuery(getObjectId(), cls);
        }
        throw AVErrorUtils.sessionMissingException();
    }

    public AVFriendshipQuery friendshipQuery() {
        return friendshipQuery(subClazz == null ? AVUser.class : subClazz);
    }

    public <T extends AVUser> AVFriendshipQuery friendshipQuery(Class<T> cls) {
        return new AVFriendshipQuery(this.objectId, cls);
    }

    public static <T extends AVUser> AVFriendshipQuery friendshipQuery(String str) {
        return new AVFriendshipQuery(str, subClazz == null ? AVUser.class : subClazz);
    }

    public static <T extends AVUser> AVFriendshipQuery friendshipQuery(String str, Class<T> cls) {
        return new AVFriendshipQuery(str, cls);
    }

    @Deprecated
    public void getFollowersInBackground(final FindCallback findCallback) {
        if (checkUserAuthentication(findCallback)) {
            PaasClient.storageInstance().getObject(AVPowerfulUtils.getFollowersEndPoint(getObjectId()), null, false, null, new GenericObjectCallback() {
                public void onSuccess(String str, AVException aVException) {
                    super.onSuccess(str, aVException);
                    List access$000 = AVUser.this.processResultByTag(str, AVUser.FOLLOWER_TAG);
                    if (findCallback != null) {
                        findCallback.internalDone(access$000, null);
                    }
                }

                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    if (findCallback != null) {
                        findCallback.internalDone(null, AVErrorUtils.createException(th, str));
                    }
                }
            });
        }
    }

    @Deprecated
    public void getMyFolloweesInBackground(final FindCallback findCallback) {
        if (checkUserAuthentication(findCallback)) {
            PaasClient.storageInstance().getObject(AVPowerfulUtils.getFolloweesEndPoint(getObjectId()), null, false, null, new GenericObjectCallback() {
                public void onSuccess(String str, AVException aVException) {
                    super.onSuccess(str, aVException);
                    List access$000 = AVUser.this.processResultByTag(str, AVUser.FOLLOWEE_TAG);
                    if (findCallback != null) {
                        findCallback.internalDone(access$000, null);
                    }
                }

                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    if (findCallback != null) {
                        findCallback.internalDone(null, AVErrorUtils.createException(th, str));
                    }
                }
            });
        }
    }

    public void getFollowersAndFolloweesInBackground(final FollowersAndFolloweesCallback followersAndFolloweesCallback) {
        if (checkUserAuthentication(followersAndFolloweesCallback)) {
            PaasClient.storageInstance().getObject(AVPowerfulUtils.getFollowersAndFollowees(getObjectId()), null, false, null, new GenericObjectCallback() {
                public void onSuccess(String str, AVException aVException) {
                    super.onSuccess(str, aVException);
                    Map access$100 = AVUser.this.processFollowerAndFollowee(str);
                    if (followersAndFolloweesCallback != null) {
                        followersAndFolloweesCallback.internalDone(access$100, null);
                    }
                }

                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    if (followersAndFolloweesCallback != null) {
                        followersAndFolloweesCallback.internalDone(null, AVErrorUtils.createException(th, str));
                    }
                }
            });
        }
    }

    public static <T extends AVUser> T cast(AVUser aVUser, Class<T> cls) {
        try {
            return (AVUser) AVObject.cast(aVUser, cls);
        } catch (Exception e) {
            log.m3520e("ClassCast Exception", e);
            return null;
        }
    }

    public static void alwaysUseSubUserClass(Class<? extends AVUser> cls) {
        subClazz = cls;
    }

    private static Map<String, Object> authData(AVThirdPartyUserAuth aVThirdPartyUserAuth) {
        Map<String, Object> hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        hashMap2.put(accessTokenTag, aVThirdPartyUserAuth.accessToken);
        hashMap2.put(expiresAtTag, aVThirdPartyUserAuth.expiredAt);
        if (!AVUtils.isBlankString(aVThirdPartyUserAuth.snsType)) {
            hashMap2.put(AVThirdPartyUserAuth.platformUserIdTag(aVThirdPartyUserAuth.snsType), aVThirdPartyUserAuth.userId);
        }
        hashMap.put(aVThirdPartyUserAuth.snsType, hashMap2);
        return hashMap;
    }

    public static void loginWithAuthData(AVThirdPartyUserAuth aVThirdPartyUserAuth, LogInCallback<AVUser> logInCallback) {
        loginWithAuthData(AVUser.class, aVThirdPartyUserAuth, logInCallback);
    }

    public static <T extends AVUser> void loginWithAuthData(final Class<T> cls, final AVThirdPartyUserAuth aVThirdPartyUserAuth, final LogInCallback<T> logInCallback) {
        if (aVThirdPartyUserAuth != null) {
            Map hashMap = new HashMap();
            hashMap.put(authDataTag, authData(aVThirdPartyUserAuth));
            PaasClient.storageInstance().postObject("users", JSON.toJSONString(hashMap), false, false, new GenericObjectCallback() {
                public void onSuccess(String str, AVException aVException) {
                    if (aVException == null) {
                        AVObject newAVUser = AVUser.newAVUser(cls, logInCallback);
                        if (newAVUser != null) {
                            AVUtils.copyPropertiesFromJsonStringToAVObject(str, newAVUser);
                            newAVUser.processAuthData(aVThirdPartyUserAuth);
                            AVUser.changeCurrentUser(newAVUser, true);
                            if (logInCallback != null) {
                                logInCallback.internalDone(newAVUser, null);
                            }
                        }
                    }
                }

                public void onFailure(Throwable th, String str) {
                    if (logInCallback != null) {
                        logInCallback.internalDone(null, AVErrorUtils.createException(th, str));
                    }
                }
            }, null, null);
        } else if (logInCallback != null) {
            logInCallback.internalDone(null, AVErrorUtils.createException(-1, "NULL userInfo."));
        }
    }

    public static void associateWithAuthData(AVUser aVUser, AVThirdPartyUserAuth aVThirdPartyUserAuth, SaveCallback saveCallback) {
        if (aVThirdPartyUserAuth != null) {
            Map authData = authData(aVThirdPartyUserAuth);
            if (aVUser.get(authDataTag) != null && (aVUser.get(authDataTag) instanceof Map)) {
                authData.putAll((Map) aVUser.get(authDataTag));
            }
            aVUser.put(authDataTag, authData);
            aVUser.markAnonymousUserTransfer();
            aVUser.saveInBackground(saveCallback);
        } else if (saveCallback != null) {
            saveCallback.internalDone(AVErrorUtils.createException(-1, "NULL userInfo."));
        }
    }

    public static void dissociateAuthData(final AVUser aVUser, final String str, final SaveCallback saveCallback) {
        Map map = (Map) aVUser.get(authDataTag);
        if (map != null) {
            map.remove(str);
        }
        aVUser.put(authDataTag, map);
        if (aVUser.isAuthenticated() && !AVUtils.isBlankString(aVUser.getObjectId())) {
            aVUser.saveInBackground(new SaveCallback() {
                public void done(AVException aVException) {
                    aVUser.processAuthData(new AVThirdPartyUserAuth(null, null, str, null));
                    if (saveCallback != null) {
                        saveCallback.internalDone(aVException);
                    }
                }
            });
        } else if (saveCallback != null) {
            saveCallback.internalDone(new AVException(206, "the user object missing a valid session"));
        }
    }

    protected void processAuthData(AVThirdPartyUserAuth aVThirdPartyUserAuth) {
        Map map = (Map) get(authDataTag);
        if (this.needTransferFromAnonymousUser) {
            if (map == null || !map.containsKey(anonymousTag)) {
                this.anonymous = false;
            } else {
                map.remove(anonymousTag);
            }
            this.needTransferFromAnonymousUser = false;
        }
        if (map != null) {
            if (map.containsKey(AVThirdPartyUserAuth.SNS_SINA_WEIBO)) {
                this.sinaWeiboToken = (String) ((Map) map.get(AVThirdPartyUserAuth.SNS_SINA_WEIBO)).get(accessTokenTag);
            } else {
                this.sinaWeiboToken = null;
            }
            if (map.containsKey(AVThirdPartyUserAuth.SNS_TENCENT_WEIBO)) {
                this.qqWeiboToken = (String) ((Map) map.get(AVThirdPartyUserAuth.SNS_TENCENT_WEIBO)).get(accessTokenTag);
            } else {
                this.qqWeiboToken = null;
            }
            if (map.containsKey(anonymousTag)) {
                this.anonymous = true;
            } else {
                this.anonymous = false;
            }
        }
        if (aVThirdPartyUserAuth == null) {
            return;
        }
        if (aVThirdPartyUserAuth.snsType.equals(AVThirdPartyUserAuth.SNS_SINA_WEIBO)) {
            this.sinaWeiboToken = aVThirdPartyUserAuth.accessToken;
        } else if (aVThirdPartyUserAuth.snsType.equals(AVThirdPartyUserAuth.SNS_TENCENT_WEIBO)) {
            this.qqWeiboToken = aVThirdPartyUserAuth.accessToken;
        }
    }

    private void markAnonymousUserTransfer() {
        if (isAnonymous()) {
            this.needTransferFromAnonymousUser = true;
        }
    }
}
