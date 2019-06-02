package com.beastbikes.android.modules.user.p077a;

import android.app.Activity;
import android.content.Context;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.ble.biz.C1389k;
import com.beastbikes.android.ble.dto.BleCyclingDTO;
import com.beastbikes.android.locale.googlemaputils.C1856b;
import com.beastbikes.android.modules.cycling.activity.biz.C1399d;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.android.modules.social.im.p074a.C1434c;
import com.beastbikes.android.modules.user.dao.entity.LocalUser;
import com.beastbikes.android.modules.user.dto.HistogramDTO;
import com.beastbikes.android.modules.user.dto.MedalDTO;
import com.beastbikes.android.modules.user.dto.PersonalRecordResponseDTO;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.modules.user.dto.SeekFriendDTO;
import com.beastbikes.android.modules.user.dto.UserDetailDTO;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.android.p088g.C2799c;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.persistence.C1663b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: UserManager */
/* renamed from: com.beastbikes.android.modules.user.a.c */
public class C2389c extends C1397a {
    /* renamed from: a */
    private final Logger f11333a = LoggerFactory.getLogger(C2389c.class);
    /* renamed from: b */
    private final C1663b<LocalUser> f11334b;
    /* renamed from: c */
    private C1399d f11335c;
    /* renamed from: d */
    private C1441d f11336d;
    /* renamed from: e */
    private C1389k f11337e;
    /* renamed from: f */
    private Context f11338f;
    /* renamed from: g */
    private Activity f11339g;

    /* compiled from: UserManager */
    /* renamed from: com.beastbikes.android.modules.user.a.c$a */
    public interface C2388a {
        /* renamed from: a */
        void m12117a(ProfileDTO profileDTO);
    }

    public C2389c(Context context) {
        super((C1372b) context.getApplicationContext());
        this.f11338f = context;
        this.f11334b = ((BeastBikes) context.getApplicationContext()).d().a(LocalUser.class);
        C1772d c1772d = new C1772d(context);
        this.f11335c = (C1399d) c1772d.m9399a(C1399d.class, C1768c.f8075a, C1768c.m9391a(context));
        this.f11336d = (C1441d) c1772d.m9399a(C1441d.class, C1768c.f8075a, C1768c.m9391a(context));
        this.f11337e = (C1389k) c1772d.m9398a(C1389k.class, "http://api.map.baidu.com/");
    }

    public C2389c(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        this.f11334b = ((BeastBikes) activity.getApplicationContext()).d().a(LocalUser.class);
        this.f11339g = activity;
        this.f11338f = activity;
        C1772d c1772d = new C1772d(activity);
        this.f11335c = (C1399d) c1772d.m9399a(C1399d.class, C1768c.f8075a, C1768c.m9391a(this.f11339g));
        this.f11336d = (C1441d) c1772d.m9399a(C1441d.class, C1768c.f8075a, C1768c.m9391a(this.f11339g));
        this.f11337e = (C1389k) c1772d.m9398a(C1389k.class, "http://api.map.baidu.com/");
    }

    /* renamed from: a */
    public LocalUser m12118a(String str) throws BusinessException {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return (LocalUser) this.f11334b.mo3189c(str);
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: b */
    public ProfileDTO m12131b(String str) throws BusinessException {
        LocalUser a = m12118a(str);
        if (a == null || TextUtils.isEmpty(a.getUserId()) || a.getUserId().equals("null")) {
            return null;
        }
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setUserId(a.getUserId());
        profileDTO.setUsername(a.getUsername());
        profileDTO.setNickname(a.getNickname());
        profileDTO.setEmail(a.getEmail());
        profileDTO.setGender(a.getGender());
        profileDTO.setWeight(a.getWeight());
        profileDTO.setHeight(a.getHeight());
        profileDTO.setProvince(a.getProvince());
        profileDTO.setCity(a.getCity());
        profileDTO.setDistrict(a.getDistrict());
        profileDTO.setTotalDistance(a.getTotalDistance());
        profileDTO.setMonthlyDistance(a.getMonthlyDistance());
        profileDTO.setLatestActivityTime(a.getLatestActivityTime());
        profileDTO.setAvatar(a.getAvatar());
        profileDTO.setBirthday(a.getBirthday());
        profileDTO.setEdited(a.getEdited() == 1);
        profileDTO.setObjectId(a.getObjectId());
        profileDTO.setGridNum((int) a.getGridNum());
        profileDTO.setSameNum((int) a.getSameGrid());
        profileDTO.setClubName(a.getClubName());
        profileDTO.setUserIntId((int) a.getUserIntId());
        profileDTO.setUpdatedAt(a.getUpdatedAt());
        profileDTO.setCreatedAt(a.getCreatedAt());
        profileDTO.setClubId(a.getClubId());
        profileDTO.setIsOk((int) a.getIsOk());
        profileDTO.setFansNum(a.getFansNum());
        profileDTO.setFollowNum(a.getFollowerNum());
        profileDTO.setFollowStatu(a.getFollowStatus());
        profileDTO.setMedalNum(a.getMedalNum());
        profileDTO.setSpeedxId(a.getSpeedxId());
        profileDTO.setMobile(a.getMobile());
        profileDTO.setMaxHeartRate(a.getMaxHeartRate());
        profileDTO.setAchievementNum(a.getAchievementNum());
        return profileDTO;
    }

    /* renamed from: c */
    public ProfileDTO m12136c(String str) throws BusinessException {
        return m12122a(str, null);
    }

    /* renamed from: a */
    public ProfileDTO m12122a(String str, C2388a c2388a) throws BusinessException {
        ProfileDTO profileDTO = null;
        if (c2388a != null) {
            try {
                c2388a.m12117a(m12131b(str));
            } catch (Throwable e) {
                throw new BusinessException(e);
            }
        }
        JSONObject a = this.f11336d.a(str);
        if (a != null) {
            if (a.optInt("code") == 0) {
                profileDTO = new ProfileDTO(a.optJSONObject(C0882j.f2229c));
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser != null && TextUtils.equals(profileDTO.getUserId(), currentUser.getObjectId())) {
                    try {
                        m12134b(profileDTO);
                    } catch (BusinessException e2) {
                        this.f11333a.error("Update LocalUser error, error = " + e2);
                    }
                }
            } else {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(optString)) {
                    Toasts.showOnUiThread(this.f11339g, optString);
                }
            }
        }
        return profileDTO;
    }

    /* renamed from: d */
    public UserDetailDTO m12138d(String str) throws BusinessException {
        UserDetailDTO userDetailDTO = null;
        try {
            JSONObject a = this.f11335c.a(str);
            if (a != null) {
                if (a.optInt("code") == 0) {
                    userDetailDTO = new UserDetailDTO(a.optJSONObject(C0882j.f2229c));
                } else {
                    String optString = a.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        Toasts.showOnUiThread(this.f11339g, optString);
                    }
                }
            }
            return userDetailDTO;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public UserDetailDTO m12123a() {
        try {
            JSONObject d = this.f11335c.d();
            if (d == null) {
                return null;
            }
            if (d.optInt("code") == 0) {
                return new UserDetailDTO(d.optJSONObject(C0882j.f2229c), true);
            }
            String optString = d.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            Toasts.showOnUiThread(this.f11339g, optString);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public PersonalRecordResponseDTO m12120a(String str, int i) {
        try {
            JSONObject c = this.f11336d.c(str, i);
            if (c == null || c.optInt("code") != 0) {
                return null;
            }
            JSONObject optJSONObject = c.optJSONObject(C0882j.f2229c);
            if (optJSONObject != null) {
                return new PersonalRecordResponseDTO(optJSONObject);
            }
            return null;
        } catch (Exception e) {
            this.f11333a.error("getPersonalRecordInfo error e: " + e);
        }
    }

    /* renamed from: a */
    public ProfileDTO m12121a(ProfileDTO profileDTO) throws BusinessException {
        if (profileDTO == null) {
            return null;
        }
        try {
            JSONObject a = this.f11336d.a(profileDTO.getNickname(), profileDTO.getCountry(), profileDTO.getCity(), profileDTO.getProvince(), profileDTO.getAvatar());
            if (a == null || a.optInt("code") != 0) {
                return null;
            }
            ProfileDTO profileDTO2;
            JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
            if (optJSONObject != null) {
                profileDTO2 = new ProfileDTO(optJSONObject);
            } else {
                profileDTO2 = null;
            }
            if (profileDTO2 == null) {
                return profileDTO2;
            }
            LocalUser a2 = m12118a(profileDTO2.getUserId());
            if (a2 == null) {
                return profileDTO2;
            }
            profileDTO2.setFansNum(a2.getFansNum());
            profileDTO2.setFollowNum(a2.getFollowerNum());
            profileDTO2.setFollowStatu(a2.getFollowStatus());
            profileDTO2.setMedalNum(a2.getMedalNum());
            m12134b(profileDTO2);
            return profileDTO2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public void m12134b(ProfileDTO profileDTO) throws BusinessException {
        if (profileDTO != null) {
            C1434c.c().a(profileDTO);
            LocalUser localUser = new LocalUser();
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
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
                localUser.setMaxHeartRate(profileDTO.getMaxHeartRate());
                localUser.setMobile(profileDTO.getMobile());
                localUser.setAchievementNum(profileDTO.getAchievementNum());
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
                currentUser.setUsername(profileDTO.getUsername());
                currentUser.setAvatar(profileDTO.getAvatar());
                currentUser.setDisplayName(profileDTO.getNickname());
                currentUser.setCountry(profileDTO.getCountry());
                currentUser.setProvince(profileDTO.getProvince());
                currentUser.setCity(profileDTO.getCity());
                currentUser.setWeight(profileDTO.getWeight());
                currentUser.setEdited(profileDTO.isEdited());
                currentUser.setSpeedxId(profileDTO.getSpeedxId());
                currentUser.setMaxHeartRate(profileDTO.getMaxHeartRate());
                currentUser.setMobilePhoneNumber(profileDTO.getMobile());
                currentUser.setAchievementNum(profileDTO.getAchievementNum());
                NetworkInfo a = C2799c.m13753a(this.f11338f);
                if (a != null && a.isConnected()) {
                    currentUser.setTrainingId(profileDTO.getTrainingId());
                    currentUser.setTrainingType(profileDTO.getTrainingType());
                }
                currentUser.setTotalMileage(profileDTO.getTotalDistance());
                currentUser.setClubLevel(profileDTO.getClubLevel());
                currentUser.setHeight(profileDTO.getHeight());
                currentUser.setBirthDay(profileDTO.getBirthday());
                currentUser.setGender(profileDTO.getGender());
                currentUser.setFtp(profileDTO.getFtp());
                AVUser.saveCurrentUser(currentUser);
                try {
                    this.f11334b.mo3187a(localUser);
                    this.f11339g.getSharedPreferences(profileDTO.getObjectId(), 0).edit().putLong("com.beastbikes.android.home.update_userinfo", System.currentTimeMillis()).apply();
                } catch (Throwable e) {
                    throw new BusinessException(e);
                }
            }
        }
    }

    /* renamed from: b */
    public void m12133b() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            try {
                LocalUser a = m12118a(currentUser.getObjectId());
                if (a != null) {
                    a.setMedalNum(a.getMedalNum() + 1);
                    m12129a(a);
                    this.f11339g.getSharedPreferences(currentUser.getObjectId(), 0).edit().putLong("com.beastbikes.android.home.update_userinfo", System.currentTimeMillis()).commit();
                }
            } catch (BusinessException e) {
            }
        }
    }

    /* renamed from: a */
    public void m12129a(LocalUser... localUserArr) throws BusinessException {
        try {
            this.f11334b.mo3187a(localUserArr);
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public void m12128a(LocalUser localUser) throws BusinessException {
        if (localUser != null) {
            try {
                this.f11334b.mo3188b(localUser);
            } catch (Throwable e) {
                throw new BusinessException(e);
            }
        }
    }

    /* renamed from: a */
    public HistogramDTO m12119a(int i, String str, int i2) {
        JSONObject a;
        if (i == 0) {
            a = this.f11336d.a(str, i2);
        } else if (i == 1) {
            a = this.f11336d.b(str, i2);
        } else {
            a = null;
        }
        if (a == null || a.optInt("code") != 0) {
            return null;
        }
        a = a.optJSONObject(C0882j.f2229c);
        if (a != null) {
            return new HistogramDTO(a);
        }
        return null;
    }

    /* renamed from: a */
    public List<FriendDTO> m12126a(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject a = this.f11336d.a(str, i, i2);
        if (a == null) {
            return null;
        }
        if (a.optInt("code") == 0) {
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            List<FriendDTO> arrayList = new ArrayList();
            for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                if (optJSONObject != null) {
                    arrayList.add(new FriendDTO(optJSONObject));
                }
            }
            return arrayList;
        }
        String optString = a.optString(AVStatus.MESSAGE_TAG);
        if (TextUtils.isEmpty(optString)) {
            return null;
        }
        Toasts.showOnUiThread(this.f11339g, optString);
        return null;
    }

    /* renamed from: e */
    public File m12139e(String str) {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + System.currentTimeMillis());
        RandomAccessFile randomAccessFile = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rwd");
            try {
                randomAccessFile2.seek(file.length());
                randomAccessFile2.write(str.getBytes());
                randomAccessFile2.close();
            } catch (IOException e) {
                randomAccessFile = randomAccessFile2;
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return file;
            }
        } catch (IOException e3) {
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            return file;
        }
        return file;
    }

    /* renamed from: a */
    public List<FriendDTO> m12125a(int i, String str, String str2, List<SeekFriendDTO> list) {
        File file;
        JSONObject a;
        int i2 = 0;
        if (list == null || list.size() <= 0) {
            file = null;
        } else {
            JSONArray jSONArray = new JSONArray();
            for (int i3 = 0; i3 < list.size(); i3++) {
                SeekFriendDTO seekFriendDTO = (SeekFriendDTO) list.get(i3);
                JSONArray jSONArray2 = new JSONArray();
                jSONArray2.put(seekFriendDTO.getSeekValue());
                jSONArray2.put(seekFriendDTO.getNickName());
                jSONArray.put(jSONArray2);
            }
            file = m12139e(jSONArray.toString());
        }
        if (file != null) {
            a = this.f11336d.a(i, str, str2, file);
        } else {
            a = this.f11336d.a(i, str, str2);
        }
        if (a == null) {
            return null;
        }
        if (file != null && file.exists()) {
            file.delete();
        }
        if (a.optInt("code") == 0) {
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            List<FriendDTO> arrayList = new ArrayList();
            while (i2 < optJSONArray.length()) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    arrayList.add(new FriendDTO(optJSONObject));
                }
                i2++;
            }
            return arrayList;
        }
        String optString = a.optString(AVStatus.MESSAGE_TAG);
        if (!TextUtils.isEmpty(optString)) {
            Toasts.showOnUiThread(this.f11339g, optString);
        }
        return null;
    }

    /* renamed from: b */
    public List<FriendDTO> m12132b(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject b = this.f11336d.b(str, i, i2);
        if (b == null) {
            return null;
        }
        if (b.optInt("code") == 0) {
            JSONArray optJSONArray = b.optJSONArray(C0882j.f2229c);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            List<FriendDTO> arrayList = new ArrayList();
            for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                if (optJSONObject != null) {
                    arrayList.add(new FriendDTO(optJSONObject));
                }
            }
            return arrayList;
        }
        String optString = b.optString(AVStatus.MESSAGE_TAG);
        if (TextUtils.isEmpty(optString)) {
            return null;
        }
        Toasts.showOnUiThread(this.f11339g, optString);
        return null;
    }

    /* renamed from: f */
    public boolean m12140f(String str) {
        boolean z = false;
        if (!TextUtils.isEmpty(str)) {
            JSONObject b = this.f11336d.b(str);
            if (b != null) {
                if (b.optInt("code") == 0) {
                    z = b.optBoolean(C0882j.f2229c);
                    if (z) {
                        C2580w.m12905a(this.f11338f, "", "click_follow");
                        AVUser currentUser = AVUser.getCurrentUser();
                        if (currentUser != null) {
                            try {
                                LocalUser a = m12118a(currentUser.getObjectId());
                                if (a != null) {
                                    a.setFollowerNum(a.getFollowerNum() + 1);
                                }
                                m12129a(a);
                                this.f11338f.getSharedPreferences(currentUser.getObjectId(), 0).edit().putLong("com.beastbikes.android.home.update_userinfo", System.currentTimeMillis()).commit();
                            } catch (BusinessException e) {
                            }
                        }
                    }
                } else {
                    String optString = b.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        Toasts.showOnUiThread(this.f11339g, optString);
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: g */
    public boolean m12141g(String str) {
        boolean z = false;
        if (!TextUtils.isEmpty(str)) {
            JSONObject c = this.f11336d.c(str);
            if (c != null) {
                if (c.optInt("code") == 0) {
                    z = c.optBoolean(C0882j.f2229c);
                    if (z) {
                        C2580w.m12905a(this.f11338f, "", "click_cancel attention");
                        AVUser currentUser = AVUser.getCurrentUser();
                        if (currentUser != null) {
                            try {
                                LocalUser a = m12118a(currentUser.getObjectId());
                                if (a != null) {
                                    a.setFollowerNum(a.getFollowerNum() - 1);
                                    m12129a(a);
                                    this.f11338f.getSharedPreferences(currentUser.getObjectId(), 0).edit().putLong("com.beastbikes.android.home.update_userinfo", System.currentTimeMillis()).apply();
                                }
                            } catch (BusinessException e) {
                            }
                        }
                    }
                } else {
                    String optString = c.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        Toasts.showOnUiThread(this.f11339g, optString);
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    public boolean m12130a(String str, String str2, int i, String str3, String str4) throws BusinessException {
        String str5;
        String str6;
        String str7 = null;
        if (i == 1) {
            str5 = null;
            str6 = null;
        } else {
            str7 = str4;
            str5 = str2;
            str6 = str;
        }
        JSONObject a = this.f11336d.a(str6, str5, i, str3, str7);
        if (a == null) {
            return false;
        }
        if (a.optInt("code") == 0) {
            return true;
        }
        String optString = a.optString(AVStatus.MESSAGE_TAG);
        if (!TextUtils.isEmpty(optString)) {
            Toasts.showOnUiThread(this.f11339g, optString);
        }
        return false;
    }

    /* renamed from: a */
    public List<MedalDTO> m12124a(int i, int i2, int i3, String str) {
        JSONObject a = this.f11336d.a(i, i2, i3, str);
        if (a == null) {
            return null;
        }
        if (a.optInt("code") == 0) {
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            List<MedalDTO> arrayList = new ArrayList();
            for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i4);
                if (optJSONObject != null) {
                    arrayList.add(new MedalDTO(optJSONObject));
                }
            }
            return arrayList;
        }
        String optString = a.optString(AVStatus.MESSAGE_TAG);
        if (TextUtils.isEmpty(optString)) {
            return null;
        }
        Toasts.showOnUiThread(this.f11339g, optString);
        return null;
    }

    /* renamed from: a */
    public void m12127a(double d, double d2, String str) throws BusinessException {
        JSONObject a = this.f11336d.a(d, d2, str);
        if (a != null) {
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f11339g, optString);
            }
        }
    }

    /* renamed from: h */
    public BleCyclingDTO m12142h(String str) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            return null;
        }
        JSONObject a = this.f11336d.a(str, currentUser.getObjectId());
        if (a == null || a.optInt("code") != 0) {
            return null;
        }
        a = a.optJSONObject(C0882j.f2229c);
        if (a != null) {
            return new BleCyclingDTO(a);
        }
        return null;
    }

    /* renamed from: b */
    public boolean m12135b(String str, int i) {
        if (i <= 0 || TextUtils.isEmpty(str)) {
            return false;
        }
        JSONObject a = this.f11336d.a(i);
        if (a == null || a.optInt("code") != 0) {
            return false;
        }
        a = a.optJSONObject(C0882j.f2229c);
        if (a == null) {
            return false;
        }
        int optInt = a.optInt("cardiacRate");
        AVUser currentUser = AVUser.getCurrentUser();
        currentUser.setMaxHeartRate(optInt);
        AVUser.saveCurrentUser(currentUser);
        return true;
    }

    /* renamed from: c */
    public JSONObject m12137c() {
        return this.f11336d.a();
    }

    /* renamed from: i */
    public C1856b m12143i(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject a = this.f11337e.a(str, "json", "wgs84ll", 1, "cYN62WkIhrX1d5f8VouoXqg9yzlvWMGs", "2C:5F:D0:D0:9C:19:E7:81:2D:90:D0:F3:9C:90:EF:03:37:91:E7:7C;com.beastbikes.android");
        if (a == null) {
            return null;
        }
        a = a.optJSONObject(C0882j.f2229c);
        if (a == null) {
            return null;
        }
        JSONObject optJSONObject = a.optJSONObject("addressComponent");
        if (optJSONObject == null) {
            return null;
        }
        C1856b c1856b = new C1856b();
        c1856b.m9673a(optJSONObject.optString("province"));
        c1856b.m9675b(optJSONObject.optString("city"));
        c1856b.m9677c(optJSONObject.optString("district"));
        return c1856b;
    }
}
