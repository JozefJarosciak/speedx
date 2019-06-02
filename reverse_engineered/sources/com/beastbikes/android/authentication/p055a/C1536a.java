package com.beastbikes.android.authentication.p055a;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.avos.avoscloud.AVUtils;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.authentication.AuthenticationException;
import com.beastbikes.android.authentication.C1541b;
import com.beastbikes.android.modules.cycling.club.biz.C2057d;
import com.beastbikes.android.modules.user.dao.entity.LocalAccounts;
import com.beastbikes.android.modules.user.dao.entity.LocalUser;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.p057b.C1381a;
import com.beastbikes.android.utils.C2563k;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.persistence.C1663b;
import com.beastbikes.framework.persistence.PersistenceException;
import com.beastbikes.framework.persistence.android.ormlite.C1664a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: AuthenticationManager */
/* renamed from: com.beastbikes.android.authentication.a.a */
public class C1536a extends C1397a {
    /* renamed from: a */
    private static final Logger f7231a = LoggerFactory.getLogger(C1536a.class);
    /* renamed from: b */
    private final C1663b<LocalUser> f7232b;
    /* renamed from: c */
    private C1375b f7233c;
    /* renamed from: d */
    private C1664a<LocalAccounts> f7234d;
    /* renamed from: e */
    private Context f7235e;

    /* compiled from: AuthenticationManager */
    /* renamed from: com.beastbikes.android.authentication.a.a$4 */
    class C15344 extends AsyncTask<String, Void, JSONObject> {
        /* renamed from: a */
        final /* synthetic */ C1536a f7230a;

        C15344(C1536a c1536a) {
            this.f7230a = c1536a;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m8447a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m8448a((JSONObject) obj);
        }

        /* renamed from: a */
        protected JSONObject m8447a(String... strArr) {
            try {
                return this.f7230a.f7233c.a();
            } catch (Exception e) {
                return null;
            }
        }

        /* renamed from: a */
        protected void m8448a(JSONObject jSONObject) {
            super.onPostExecute(jSONObject);
            if (jSONObject != null) {
                C1536a.f7231a.info(jSONObject.toString());
            }
        }
    }

    /* compiled from: AuthenticationManager */
    /* renamed from: com.beastbikes.android.authentication.a.a$a */
    public interface C1535a {
        /* renamed from: a */
        void mo3121a(AuthenticationException authenticationException, AccountDTO accountDTO);

        /* renamed from: b */
        void mo3122b(AuthenticationException authenticationException, AccountDTO accountDTO);
    }

    public C1536a(Context context) {
        super((C1372b) context.getApplicationContext());
        BeastBikes beastBikes = (BeastBikes) context.getApplicationContext();
        this.f7235e = beastBikes;
        C1381a d = beastBikes.d();
        this.f7232b = d.a(LocalUser.class);
        this.f7234d = d.a(LocalAccounts.class);
        this.f7233c = (C1375b) new C1772d(context).m9399a(C1375b.class, C1768c.f8075a, C1768c.m9391a(context));
    }

    /* renamed from: a */
    public void m8465a(final AccountDTO accountDTO, final C1535a c1535a) {
        new AsyncTask<String, Void, JSONObject>(this) {
            /* renamed from: c */
            final /* synthetic */ C1536a f7223c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m8443a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m8444a((JSONObject) obj);
            }

            /* renamed from: a */
            protected JSONObject m8443a(String... strArr) {
                try {
                    switch (accountDTO.getAuthType()) {
                        case 1:
                        case 2:
                            return this.f7223c.f7233c.a(accountDTO.getUsername(), accountDTO.getPassword(), accountDTO.getAuthType(), accountDTO.getNickname());
                        default:
                            return this.f7223c.f7233c.a(accountDTO.getAuthKey(), accountDTO.getAccessToken(), accountDTO.getAuthType(), accountDTO.getThirdNick());
                    }
                } catch (Exception e) {
                    return null;
                }
            }

            /* renamed from: a */
            protected void m8444a(JSONObject jSONObject) {
                super.onPostExecute(jSONObject);
                if (jSONObject != null) {
                    C1536a.f7231a.info("signIn() result: " + jSONObject.toString());
                    if (jSONObject.optInt("code") != 0) {
                        if (c1535a != null) {
                            c1535a.mo3121a(new AuthenticationException(jSONObject.optInt("code"), jSONObject.optString(AVStatus.MESSAGE_TAG)), accountDTO);
                        }
                    } else if (jSONObject.optJSONObject(C0882j.f2229c) != null || c1535a == null) {
                        this.f7223c.m8453a(jSONObject, accountDTO);
                        if (c1535a != null) {
                            c1535a.mo3121a(null, accountDTO);
                        }
                        C2057d.m10601c();
                        C1768c.m9393b();
                    } else {
                        c1535a.mo3121a(new AuthenticationException(0, "server connect error!"), accountDTO);
                    }
                } else if (c1535a != null) {
                    c1535a.mo3121a(new AuthenticationException(0, "server connect error!"), accountDTO);
                }
            }
        }.execute(new String[0]);
    }

    /* renamed from: a */
    public void m8466a(final AccountDTO accountDTO, final String str, final C1535a c1535a) {
        new AsyncTask<String, Void, JSONObject>(this) {
            /* renamed from: d */
            final /* synthetic */ C1536a f7227d;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m8445a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m8446a((JSONObject) obj);
            }

            /* renamed from: a */
            protected JSONObject m8445a(String... strArr) {
                try {
                    return this.f7227d.f7233c.a(accountDTO.getNickname(), accountDTO.getUsername(), accountDTO.getPassword(), str, accountDTO.getAuthKey(), accountDTO.getAccessToken(), accountDTO.getAuthType(), accountDTO.getThirdNick());
                } catch (Exception e) {
                    return null;
                }
            }

            /* renamed from: a */
            protected void m8446a(JSONObject jSONObject) {
                super.onPostExecute(jSONObject);
                if (jSONObject == null) {
                    c1535a.mo3122b(new AuthenticationException(0, "server connect error!"), accountDTO);
                } else if (jSONObject.optInt("code") != 0) {
                    c1535a.mo3122b(new AuthenticationException(jSONObject.optInt("code"), jSONObject.optString(AVStatus.MESSAGE_TAG)), accountDTO);
                } else if (jSONObject.optJSONObject(C0882j.f2229c) == null) {
                    c1535a.mo3122b(new AuthenticationException(0, "server connect error!"), accountDTO);
                } else {
                    this.f7227d.m8453a(jSONObject, accountDTO);
                    C1768c.m9393b();
                    c1535a.mo3122b(null, accountDTO);
                }
            }
        }.execute(new String[0]);
    }

    /* renamed from: a */
    private ProfileDTO m8453a(JSONObject jSONObject, AccountDTO accountDTO) {
        if (jSONObject == null) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
        if (optJSONObject == null) {
            return null;
        }
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("user_info");
        if (optJSONObject2 == null) {
            return null;
        }
        ProfileDTO profileDTO = new ProfileDTO(optJSONObject2);
        AVUser aVUser = new AVUser();
        aVUser.setPassword(accountDTO.getPassword());
        aVUser.setSessionToken(optJSONObject.optString(MapboxEvent.ATTRIBUTE_SESSION_ID));
        aVUser.setUsername(profileDTO.getUsername());
        String userId = profileDTO.getUserId();
        if (TextUtils.isEmpty(userId)) {
            userId = optJSONObject2.optString(AVUtils.objectIdTag);
        }
        aVUser.setObjectId(userId);
        aVUser.setSignType(accountDTO.getAuthType());
        aVUser.setAvatar(profileDTO.getAvatar());
        aVUser.setDisplayName(profileDTO.getNickname());
        aVUser.setClubName(profileDTO.getClubName());
        aVUser.setCity(profileDTO.getCity());
        aVUser.setGeoCode(optJSONObject2.optString("geoCode"));
        aVUser.setWeight(profileDTO.getWeight());
        aVUser.setClubId(optJSONObject2.optString("clubId"));
        aVUser.setEmail(optJSONObject2.optString("email"));
        aVUser.setFansNum(optJSONObject2.optInt("fansNum"));
        aVUser.setFollowStatus(optJSONObject2.optInt("followStatu"));
        aVUser.setFollowerNum(optJSONObject2.optInt("followNum"));
        aVUser.setSpeedxId(optJSONObject2.optInt("speedxId"));
        aVUser.setMobilePhoneNumber(profileDTO.getMobile());
        aVUser.setMaxHeartRate(profileDTO.getMaxHeartRate());
        aVUser.setEdited(profileDTO.isEdited());
        aVUser.setTotalMileage(profileDTO.getTotalDistance());
        aVUser.setClubLevel(profileDTO.getClubLevel());
        aVUser.setHeight(profileDTO.getHeight());
        aVUser.setBirthDay(profileDTO.getBirthday());
        aVUser.setGender(profileDTO.getGender());
        aVUser.setFtp(profileDTO.getFtp());
        aVUser.setTrainingId(profileDTO.getTrainingId());
        aVUser.setTrainingType(profileDTO.getTrainingType());
        AVUser.saveCurrentUser(aVUser);
        m8454a(profileDTO);
        return profileDTO;
    }

    /* renamed from: a */
    public JSONObject m8460a(String str) {
        try {
            return this.f7233c.a(str);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public JSONObject m8462a(String str, String str2) {
        try {
            return this.f7233c.b(str, str2);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public JSONObject m8463a(String str, String str2, String str3) {
        try {
            return this.f7233c.a(str, str2, str3);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    private void m8454a(final ProfileDTO profileDTO) {
        if (!TextUtils.isEmpty(profileDTO.getUserId())) {
            new Thread(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C1536a f7229b;

                public void run() {
                    LocalUser localUser = new LocalUser();
                    localUser.setId(profileDTO.getUserId());
                    localUser.setEmail(profileDTO.getEmail());
                    localUser.setUsername(profileDTO.getUsername());
                    localUser.setNickname(profileDTO.getNickname());
                    localUser.setProvince(profileDTO.getProvince());
                    localUser.setCity(profileDTO.getCity());
                    localUser.setGender(profileDTO.getGender());
                    localUser.setDistrict(profileDTO.getDistrict());
                    localUser.setWeight(profileDTO.getWeight());
                    localUser.setHeight(profileDTO.getHeight());
                    localUser.setTotalDistance(profileDTO.getTotalDistance());
                    localUser.setUserId(profileDTO.getUserId());
                    localUser.setUserIntId((long) profileDTO.getUserIntId());
                    localUser.setUpdatedAt(profileDTO.getUpdatedAt());
                    localUser.setCreatedAt(profileDTO.getCreatedAt());
                    localUser.setClubId(profileDTO.getClubId());
                    localUser.setObjectId(profileDTO.getObjectId());
                    localUser.setIsOk((long) profileDTO.getIsOk());
                    localUser.setGridNum((long) profileDTO.getGridNum());
                    localUser.setBirthday(profileDTO.getBirthday());
                    localUser.setFansNum(profileDTO.getFansNum());
                    localUser.setFollowerNum(profileDTO.getFollowNum());
                    localUser.setFollowStatus(profileDTO.getFollowStatu());
                    localUser.setSpeedxId(profileDTO.getSpeedxId());
                    if (profileDTO.isEdited()) {
                        localUser.setEdited(1);
                    } else {
                        localUser.setEdited(0);
                    }
                    localUser.setWeeklyDistance(profileDTO.getWeeklyDistance());
                    localUser.setSameGrid((long) profileDTO.getSameNum());
                    localUser.setMonthlyDistance(profileDTO.getMonthlyDistance());
                    localUser.setClubName(profileDTO.getClubName());
                    localUser.setAvatar(profileDTO.getAvatar());
                    localUser.setMedalNum(profileDTO.getMedalNum());
                    localUser.setMaxHeartRate(profileDTO.getMaxHeartRate());
                    localUser.setMobile(profileDTO.getMobile());
                    try {
                        this.f7229b.f7232b.mo3187a(localUser);
                    } catch (PersistenceException e) {
                        C1536a.f7231a.error("Persist user error, " + e);
                    }
                }
            }).start();
        }
    }

    /* renamed from: a */
    public void m8464a() {
        AVUser.saveCurrentUser(null);
        new C15344(this).execute(new String[0]);
    }

    /* renamed from: b */
    public List<AccountDTO> m8467b() {
        if (AVUser.getCurrentUser() == null) {
            return null;
        }
        List<AccountDTO> arrayList = new ArrayList();
        try {
            List b = this.f7234d.m9025b("WHERE user_id=?", r3.getObjectId());
            if (b == null || b.size() == 0) {
                return null;
            }
            for (int i = 0; i < b.size(); i++) {
                arrayList.add(new AccountDTO((LocalAccounts) b.get(i)));
            }
            return arrayList;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public List<AccountDTO> m8471c() {
        try {
            JSONObject b = this.f7233c.b();
            if (b == null) {
                Toasts.showOnUiThreadWithResId(this.f7235e, C1373R.string.account_failure);
                return null;
            } else if (b.optInt("code") == 0) {
                JSONArray optJSONArray = b.optJSONArray(C0882j.f2229c);
                List<AccountDTO> arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    AccountDTO accountDTO = new AccountDTO(optJSONArray.optJSONObject(i));
                    arrayList.add(accountDTO);
                    LocalAccounts localAccounts = new LocalAccounts(accountDTO);
                    this.f7234d.mo3187a(localAccounts);
                }
                return arrayList;
            } else {
                Object optString = b.optString(AVStatus.MESSAGE_TAG);
                if (TextUtils.isEmpty(optString)) {
                    return null;
                }
                Toasts.showOnUiThreadWithText(this.f7235e, optString);
                return null;
            }
        } catch (Exception e) {
            Toasts.showOnUiThreadWithResId(this.f7235e, C1373R.string.account_failure);
            return null;
        }
    }

    /* renamed from: a */
    public List<AccountDTO> m8459a(String str, String str2, String str3, int i, String str4) throws BusinessException {
        int i2 = 1;
        int i3 = -1;
        try {
            switch (str3.hashCode()) {
                case -1808126181:
                    if (str3.equals("Strava")) {
                        i3 = 8;
                        break;
                    }
                    break;
                case -1707903162:
                    if (str3.equals("Wechat")) {
                        i3 = 4;
                        break;
                    }
                    break;
                case -1298415092:
                    if (str3.equals("mobilephone")) {
                        i3 = 1;
                        break;
                    }
                    break;
                case 2592:
                    if (str3.equals("QQ")) {
                        i3 = 3;
                        break;
                    }
                    break;
                case 3343799:
                    if (str3.equals("mail")) {
                        i3 = 0;
                        break;
                    }
                    break;
                case 318270399:
                    if (str3.equals("SinaWeibo")) {
                        i3 = 2;
                        break;
                    }
                    break;
                case 458192787:
                    if (str3.equals("GooglePlus")) {
                        i3 = 7;
                        break;
                    }
                    break;
                case 561774310:
                    if (str3.equals("Facebook")) {
                        i3 = 5;
                        break;
                    }
                    break;
                case 748307027:
                    if (str3.equals("Twitter")) {
                        i3 = 6;
                        break;
                    }
                    break;
            }
            switch (i3) {
                case 0:
                    break;
                case 1:
                    i2 = 2;
                    break;
                case 2:
                    i2 = 4;
                    break;
                case 3:
                    i2 = 8;
                    break;
                case 4:
                    i2 = 16;
                    break;
                case 5:
                    i2 = 64;
                    break;
                case 6:
                    i2 = 32;
                    break;
                case 7:
                    i2 = 128;
                    break;
                case 8:
                    i2 = 256;
                    break;
                default:
                    i2 = 0;
                    break;
            }
            JSONObject a = this.f7233c.a(str, str2, i2, i, str4);
            if (a == null) {
                C1541b.m8482a(this.f7235e, i2);
                return null;
            } else if (a.optInt("code") == 0) {
                JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
                JSONArray optJSONArray = optJSONObject.optJSONArray("bind_status");
                List<AccountDTO> arrayList = new ArrayList();
                for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                    AccountDTO accountDTO = new AccountDTO(optJSONArray.optJSONObject(i4));
                    arrayList.add(accountDTO);
                    LocalAccounts localAccounts = new LocalAccounts(accountDTO);
                    this.f7234d.mo3187a(localAccounts);
                }
                Object optString = optJSONObject.optString(MapboxEvent.ATTRIBUTE_SESSION_ID);
                if (TextUtils.isEmpty(optString)) {
                    return arrayList;
                }
                AVUser currentUser = AVUser.getCurrentUser();
                currentUser.setSessionToken(optString);
                AVUser.saveCurrentUser(currentUser);
                return arrayList;
            } else {
                C1541b.m8482a(this.f7235e, i2);
                String optString2 = a.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(AVStatus.MESSAGE_TAG)) {
                    Toasts.showOnUiThreadWithText(this.f7235e, optString2);
                }
                return null;
            }
        } catch (Throwable e) {
            Toasts.showOnUiThreadWithResId(this.f7235e, C1373R.string.account_failure);
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public List<AccountDTO> m8457a(String str, int i, String str2) throws BusinessException {
        return m8458a(str, str, i, str2);
    }

    /* renamed from: a */
    public List<AccountDTO> m8458a(String str, String str2, int i, String str3) throws BusinessException {
        List<AccountDTO> list = null;
        try {
            JSONObject b = this.f7233c.b(str, str2, i, str3);
            if (b != null) {
                if (b.optInt("code") == 0) {
                    C1541b.m8482a(this.f7235e, i);
                    JSONArray optJSONArray = b.optJSONArray(C0882j.f2229c);
                    list = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        AccountDTO accountDTO = new AccountDTO(optJSONArray.optJSONObject(i2));
                        list.add(accountDTO);
                        LocalAccounts localAccounts = new LocalAccounts(accountDTO);
                        this.f7234d.mo3187a(localAccounts);
                    }
                } else {
                    String optString = b.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(AVStatus.MESSAGE_TAG)) {
                        Toasts.showOnUiThreadWithText(this.f7235e, optString);
                    }
                }
            }
            return list;
        } catch (Throwable e) {
            Toasts.showOnUiThreadWithResId(this.f7235e, C1373R.string.account_failure);
            throw new BusinessException(e);
        }
    }

    /* renamed from: b */
    public boolean m8468b(String str, int i, String str2) throws BusinessException {
        try {
            JSONObject a = this.f7233c.a(str, i, str2);
            if (a == null) {
                return false;
            }
            if (a.optInt("code", -1) == 0) {
                return true;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(AVStatus.MESSAGE_TAG)) {
                return false;
            }
            Toasts.showOnUiThreadWithText(this.f7235e, optString);
            return false;
        } catch (Throwable e) {
            Toasts.showOnUiThreadWithResId(this.f7235e, C1373R.string.account_failure);
            throw new BusinessException(e);
        }
    }

    /* renamed from: b */
    public boolean m8469b(String str, String str2) throws BusinessException {
        try {
            JSONObject a = this.f7233c.a(str, str2);
            if (a == null) {
                return false;
            }
            if (a.optInt("code", -1) == 0) {
                return true;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(AVStatus.MESSAGE_TAG)) {
                return false;
            }
            Toasts.showOnUiThreadWithText(this.f7235e, optString);
            return false;
        } catch (Throwable e) {
            Toasts.showOnUiThreadWithResId(this.f7235e, C1373R.string.account_failure);
            throw new BusinessException(e);
        }
    }

    /* renamed from: c */
    public boolean m8472c(String str, String str2) throws BusinessException {
        try {
            JSONObject c = this.f7233c.c(str, str2);
            if (c == null) {
                return false;
            }
            if (c.optInt("code", -1) == 0) {
                JSONObject optJSONObject = c.optJSONObject(C0882j.f2229c);
                if (!C2563k.m12869a(optJSONObject)) {
                    String optString = optJSONObject.optString(MapboxEvent.ATTRIBUTE_SESSION_ID);
                    AVUser currentUser = AVUser.getCurrentUser();
                    currentUser.setSessionToken(optString);
                    currentUser.setPassword(str);
                    AVUser.saveCurrentUser(currentUser);
                    return true;
                }
            }
            String optString2 = c.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(AVStatus.MESSAGE_TAG)) {
                return false;
            }
            Toasts.showOnUiThreadWithText(this.f7235e, optString2);
            return false;
        } catch (Throwable e) {
            Toasts.showOnUiThreadWithResId(this.f7235e, C1373R.string.account_failure);
            throw new BusinessException(e);
        }
    }

    /* renamed from: b */
    public boolean m8470b(String str, String str2, int i, String str3) throws BusinessException {
        try {
            JSONObject c = this.f7233c.c(str, str2, i, str3);
            if (c == null) {
                return false;
            }
            if (c.optInt("code", -1) == 0) {
                return c.optBoolean(C0882j.f2229c);
            }
            String optString = c.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(AVStatus.MESSAGE_TAG)) {
                return false;
            }
            Toasts.showOnUiThreadWithText(this.f7235e, optString);
            return false;
        } catch (Throwable e) {
            Toasts.showOnUiThreadWithResId(this.f7235e, C1373R.string.account_failure);
            throw new BusinessException(e);
        }
    }

    /* renamed from: d */
    public String m8473d() {
        try {
            JSONObject c = this.f7233c.c();
            if (c == null) {
                f7231a.error("getSessionToken() result is null");
                return null;
            }
            int optInt = c.optInt("code", -1);
            if (optInt == 0) {
                c = c.optJSONObject(C0882j.f2229c);
                if (c != null) {
                    return c.optString("token");
                }
                f7231a.error("getSessionToken() result result is null");
                return null;
            }
            f7231a.error("getSessionToken() result code != 0, code = " + optInt + ", msg = " + c.optString(AVStatus.MESSAGE_TAG));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public JSONObject m8461a(String str, int i) {
        try {
            return this.f7233c.a(str, i);
        } catch (Exception e) {
            e.printStackTrace();
            f7231a.error("accountCheck error e: " + e);
            return null;
        }
    }
}
