package com.beastbikes.android.modules.cycling.club.biz;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.club.dao.C2061a;
import com.beastbikes.android.modules.cycling.club.dao.entity.Club;
import com.beastbikes.android.modules.cycling.club.dto.ApplyDTO;
import com.beastbikes.android.modules.cycling.club.dto.C2065d;
import com.beastbikes.android.modules.cycling.club.dto.C2067f;
import com.beastbikes.android.modules.cycling.club.dto.C2068g;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.modules.cycling.club.dto.ClubRankBean;
import com.beastbikes.android.modules.cycling.ranking.dto.C2173a;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.modules.social.im.p074a.C2341a;
import com.beastbikes.android.utils.C2563k;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.persistence.PersistenceException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClubManager extends C1397a implements C1371a {
    /* renamed from: a */
    private static final Logger f9329a = LoggerFactory.getLogger(ClubManager.class);
    /* renamed from: b */
    private final C2061a f9330b;
    /* renamed from: c */
    private SharedPreferences f9331c;
    /* renamed from: d */
    private C1404f f9332d;
    /* renamed from: e */
    private Activity f9333e;
    /* renamed from: f */
    private Context f9334f;

    public enum CLUB_ORDERBY {
        RECOMMEND,
        SCORE,
        NONE
    }

    public ClubManager(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        this.f9330b = new C2061a(((BeastBikes) activity.getApplicationContext()).d());
        if (AVUser.getCurrentUser() != null) {
            this.f9331c = activity.getSharedPreferences(AVUser.getCurrentUser().getObjectId(), 0);
        }
        this.f9334f = activity;
        this.f9333e = activity;
        this.f9332d = (C1404f) new C1772d(activity).m9399a(C1404f.class, C1768c.f8075a, C1768c.m9391a(activity));
    }

    public ClubManager(Context context) {
        super((C1372b) context.getApplicationContext());
        this.f9334f = context;
        this.f9330b = new C2061a(((BeastBikes) context.getApplicationContext()).d());
        if (AVUser.getCurrentUser() != null) {
            this.f9331c = context.getSharedPreferences(AVUser.getCurrentUser().getObjectId(), 0);
        }
        this.f9332d = (C1404f) new C1772d(context).m9399a(C1404f.class, C1768c.f8075a, C1768c.m9391a(context));
    }

    /* renamed from: a */
    public int m10530a() {
        return this.f9331c.getInt("beast.club.level", 0);
    }

    /* renamed from: a */
    public ClubInfoCompact m10533a(String str) throws BusinessException {
        try {
            Club a = this.f9330b.m10619a(str);
            if (a == null) {
                return null;
            }
            ClubInfoCompact clubInfoCompact = new ClubInfoCompact();
            clubInfoCompact.setObjectId(a.getClubId());
            clubInfoCompact.setName(a.getClubName());
            clubInfoCompact.setLogo(a.getClubLogo());
            clubInfoCompact.setDesc(a.getClubDesc());
            clubInfoCompact.setManagerId(a.getClubManagerId());
            clubInfoCompact.setProvince(a.getClubProvince());
            clubInfoCompact.setCity(a.getClubCity());
            clubInfoCompact.setMilestone(a.getClubMilestone());
            clubInfoCompact.setNotice(a.getClubNotice());
            clubInfoCompact.setMaxMembers(a.getMaxMembers());
            clubInfoCompact.setMembers(a.getClubMembers());
            clubInfoCompact.setActivities(a.getActivities());
            clubInfoCompact.setScore(a.getClubScore());
            clubInfoCompact.setLevel(a.getLevel());
            clubInfoCompact.setStatus(a.getStatus());
            clubInfoCompact.setRank(a.getRank());
            clubInfoCompact.setType(a.getType());
            clubInfoCompact.setLinkTo(a.getLinkTo());
            if (a.getIsPrivate() == 0) {
                clubInfoCompact.setIsPrivate(false);
            } else {
                clubInfoCompact.setIsPrivate(true);
            }
            clubInfoCompact.setClubLevel(a.getClubLevel());
            return clubInfoCompact;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: b */
    public Club m10544b(String str) throws BusinessException {
        Club club = null;
        try {
            JSONObject a = this.f9332d.a();
            if (a != null && a.optInt("code") == 0) {
                club = this.f9330b.m10619a(str);
                if (club == null) {
                    club = new Club();
                    club.setId(UUID.randomUUID().toString());
                }
                a = a.optJSONObject(C0882j.f2229c);
                club.setClubId(a.optString("clubId"));
                club.setUserId(str);
                club.setLevel(a.optInt("level"));
                club.setStatus(a.optInt("status"));
                this.f9330b.mo3187a(club);
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser != null && (club.getStatus() == 1 || club.getStatus() == 4)) {
                    currentUser.setClubId(club.getClubId());
                }
                if (!TextUtils.isEmpty(club.getClubId())) {
                    new C2341a(this.f9334f).m11958d(str, club.getClubId());
                }
                Editor edit = this.f9331c.edit();
                edit.putString("beast.club.user.id", str);
                edit.putString("beast.club.id", a.optString("clubId"));
                edit.putInt("beast.club.level", a.optInt("level"));
                edit.putInt("beast.club.status", a.optInt("status"));
                edit.apply();
            }
            return club;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: c */
    public ClubInfoCompact m10547c(String str) throws BusinessException {
        try {
            JSONObject a = this.f9332d.a(str);
            if (a == null) {
                return null;
            }
            if (a.optInt("code") == 0) {
                ClubInfoCompact clubInfoCompact = new ClubInfoCompact(a.optJSONObject(C0882j.f2229c));
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser == null) {
                    return null;
                }
                Club a2 = this.f9330b.m10619a(currentUser.getObjectId());
                if (a2 != null && a2.getClubId().equals(clubInfoCompact.getObjectId())) {
                    a2.setClubDesc(clubInfoCompact.getDesc());
                    a2.setClubName(clubInfoCompact.getName());
                    a2.setClubId(clubInfoCompact.getObjectId());
                    a2.setActivities(clubInfoCompact.getActivities());
                    a2.setClubCity(clubInfoCompact.getCity());
                    a2.setClubDesc(clubInfoCompact.getDesc());
                    a2.setClubLogo(clubInfoCompact.getLogo());
                    a2.setClubManagerId(clubInfoCompact.getManagerId());
                    a2.setClubMembers(clubInfoCompact.getMembers());
                    a2.setClubMilestone(clubInfoCompact.getMilestone());
                    a2.setClubName(clubInfoCompact.getName());
                    a2.setClubNotice(clubInfoCompact.getNotice());
                    a2.setClubProvince(clubInfoCompact.getProvince());
                    a2.setClubScore(clubInfoCompact.getScore());
                    a2.setMaxMembers(clubInfoCompact.getMaxMembers());
                    a2.setStatus(a2.getStatus());
                    a2.setLevel(a2.getLevel());
                    a2.setClubLevel(clubInfoCompact.getClubLevel());
                    a2.setType(clubInfoCompact.getType());
                    a2.setLinkTo(clubInfoCompact.getLinkTo());
                    if (clubInfoCompact.getIsPrivate()) {
                        a2.setIsPrivate(1);
                    } else {
                        a2.setIsPrivate(0);
                    }
                    clubInfoCompact.setStatus(a2.getStatus());
                    clubInfoCompact.setLevel(a2.getLevel());
                    this.f9330b.mo3187a(a2);
                }
                return clubInfoCompact;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            Toasts.showOnUiThread(this.f9333e, optString);
            return null;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public List<ClubInfoCompact> m10538a(CLUB_ORDERBY club_orderby, String str, String str2, String str3, int i, int i2) throws BusinessException {
        String str4;
        String str5;
        String str6 = "";
        switch (club_orderby) {
            case RECOMMEND:
                str6 = "recommend";
                break;
            case SCORE:
                str6 = "score";
                break;
            case NONE:
                str6 = "";
                break;
        }
        if (TextUtils.isEmpty(str)) {
            str4 = "";
        } else {
            str4 = str;
        }
        if (TextUtils.isEmpty(str3)) {
            str5 = "";
        } else {
            str5 = str3;
        }
        try {
            JSONObject a = this.f9332d.a(str6, str4, str2, str5, i, i2);
            if (a == null) {
                return null;
            }
            if (a.optInt("code") == 0) {
                List<ClubInfoCompact> arrayList = new ArrayList();
                JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    arrayList.add(new ClubInfoCompact(optJSONArray.optJSONObject(i3)));
                }
                return arrayList;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9333e, optString);
            }
            return null;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public boolean m10541a(int i, String str, String str2, ClubInfoCompact clubInfoCompact) throws BusinessException {
        boolean z = false;
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        try {
            JSONObject a = this.f9332d.a(str, i, str2);
            if (a != null) {
                if (a.optInt("code") == 0) {
                    z = a.optBoolean(C0882j.f2229c);
                    if (z) {
                        AVUser currentUser = AVUser.getCurrentUser();
                        if (currentUser != null) {
                            Editor edit = this.f9331c.edit();
                            switch (i) {
                                case 0:
                                    Club a2 = this.f9330b.m10619a(currentUser.getObjectId());
                                    if (a2 == null) {
                                        a2 = new Club();
                                        a2.setId(UUID.randomUUID().toString());
                                        a2.setUserId(currentUser.getObjectId());
                                    }
                                    a2.setClubId(str);
                                    a2.setLevel(0);
                                    a2.setStatus(2);
                                    edit.remove("beast.club.status").commit();
                                    edit.putInt("beast.club.status", 2);
                                    if (clubInfoCompact != null) {
                                        a2.setClubName(clubInfoCompact.getName());
                                        a2.setClubLogo(clubInfoCompact.getLogo());
                                        a2.setClubDesc(clubInfoCompact.getDesc());
                                        a2.setClubManagerId(clubInfoCompact.getManagerId());
                                        a2.setMaxMembers(clubInfoCompact.getMaxMembers());
                                        a2.setClubMembers(clubInfoCompact.getMembers());
                                        a2.setClubMilestone(clubInfoCompact.getMilestone());
                                        a2.setClubNotice(clubInfoCompact.getNotice());
                                        a2.setClubProvince(clubInfoCompact.getProvince());
                                        a2.setClubCity(clubInfoCompact.getCity());
                                        a2.setClubScore(clubInfoCompact.getScore());
                                        a2.setActivities(clubInfoCompact.getActivities());
                                        a2.setRank(clubInfoCompact.getRank());
                                        a2.setType(clubInfoCompact.getType());
                                        a2.setLinkTo(clubInfoCompact.getLinkTo());
                                        currentUser.setClubName(clubInfoCompact.getName());
                                        currentUser.setClubId(str);
                                        AVUser.saveCurrentUser(currentUser);
                                    }
                                    this.f9330b.mo3187a(a2);
                                    edit.putString("beast.club.user.id", currentUser.getObjectId());
                                    edit.putString("beast.club.id", str);
                                    edit.putInt("beast.club.level", 0);
                                    break;
                                case 1:
                                case 2:
                                    currentUser.setClubId("");
                                    currentUser.setClubName("");
                                    AVUser.saveCurrentUser(currentUser);
                                    this.f9330b.m10620b(str);
                                    edit.putInt("beast.club.dot.activity", 0);
                                    edit.putInt("beast.club.dot.more", 0);
                                    edit.putInt("beast.club.feed.dot.total.count", 0);
                                    edit.putInt("beast.club.dot.group.chat", 0);
                                    edit.putInt("beast.rongcloud.new.message.count", this.f9331c.getInt("beast.rongcloud.new.message.count", 0) - this.f9331c.getInt("beast.club.dot.group.chat", 0));
                                    edit.putInt("beast.club.status", 5);
                                    edit.putInt("beast.club.level", 0);
                                    break;
                            }
                            edit.apply();
                        }
                    }
                } else {
                    String optString = a.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        Toasts.showOnUiThread(this.f9333e, optString);
                    }
                }
            }
            return z;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public ClubInfoCompact m10532a(int i) {
        try {
            JSONObject a = this.f9332d.a(i);
            if (a == null) {
                return null;
            }
            if (a.optInt("code") == 0) {
                JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
                ClubInfoCompact clubInfoCompact = new ClubInfoCompact(optJSONObject.optJSONObject("myClub"));
                clubInfoCompact.setRank(optJSONObject.optInt("rank"));
                return clubInfoCompact;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            Toasts.showOnUiThread(this.f9333e, optString);
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public List<RankDTO> m10540a(String str, int i, int i2, int i3) throws BusinessException {
        List<RankDTO> list = null;
        String str2 = "joined";
        switch (i) {
            case 0:
                str2 = "joined";
                break;
            case 1:
                str2 = "milestone";
                break;
            case 2:
                str2 = "milestone";
                break;
        }
        try {
            JSONObject a = this.f9332d.a(str, i2, i3, str2);
            if (a != null) {
                if (a.optInt("code") == 0) {
                    a = a.optJSONObject(C0882j.f2229c);
                    list = new ArrayList();
                    list.add(new RankDTO(a.optJSONObject("manager")));
                    JSONArray optJSONArray = a.optJSONArray("members");
                    for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i4);
                        RankDTO rankDTO = new RankDTO();
                        rankDTO.setUserId(optJSONObject.optString("userId"));
                        rankDTO.setScore(optJSONObject.optDouble("score"));
                        rankDTO.setManager(optJSONObject.optBoolean("ismanager"));
                        rankDTO.setLevel(optJSONObject.optInt("level"));
                        rankDTO.setMilestone(optJSONObject.optDouble("milestone"));
                        rankDTO.setJoined(optJSONObject.optString("joined"));
                        rankDTO.setRemarks(optJSONObject.optString("remarks"));
                        optJSONObject = optJSONObject.optJSONObject("user");
                        if (optJSONObject != null) {
                            if (optJSONObject.has("avatarImage")) {
                                rankDTO.setAvatarUrl(optJSONObject.optString("avatarImage"));
                            } else {
                                rankDTO.setAvatarUrl(optJSONObject.optString("avatar"));
                            }
                            rankDTO.setNickname(optJSONObject.optString("nickname"));
                            rankDTO.setProvince(optJSONObject.optString("province"));
                            rankDTO.setCity(optJSONObject.optString("city"));
                        }
                        rankDTO.setRankType(0);
                        rankDTO.setOrdinal(i4 + 1);
                        list.add(rankDTO);
                    }
                } else {
                    String optString = a.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        Toasts.showOnUiThread(this.f9333e, optString);
                    }
                }
            }
            return list;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: d */
    public boolean m10551d(String str) throws BusinessException {
        boolean z = false;
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject b = this.f9332d.b(str);
                if (b != null) {
                    if (b.optInt("code") == 0) {
                        Editor edit = this.f9331c.edit();
                        edit.putString("beast.club.notices", str);
                        edit.apply();
                        z = b.optBoolean(C0882j.f2229c);
                        if (z) {
                            try {
                                AVUser currentUser = AVUser.getCurrentUser();
                                if (currentUser != null) {
                                    Club a = this.f9330b.m10619a(currentUser.getObjectId());
                                    if (a != null) {
                                        a.setClubNotice(str);
                                        this.f9330b.m9031d(a);
                                    }
                                }
                            } catch (PersistenceException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        String optString = b.optString(AVStatus.MESSAGE_TAG);
                        if (!TextUtils.isEmpty(optString)) {
                            Toasts.showOnUiThread(this.f9333e, optString);
                        }
                    }
                }
            } catch (Throwable e2) {
                throw new BusinessException(e2);
            }
        }
        return z;
    }

    /* renamed from: a */
    public boolean m10543a(String str, String str2, String str3, String str4, String str5, String str6, int i, String str7) throws BusinessException {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            return false;
        }
        Club club;
        Club club2 = null;
        try {
            club2 = this.f9330b.m10619a(currentUser.getObjectId());
        } catch (PersistenceException e) {
        }
        if (club2 == null) {
            club2 = new Club();
            club2.setId(UUID.randomUUID().toString());
            club2.setUserId(currentUser.getObjectId());
            club2.setLevel(128);
            club2.setStatus(4);
            club = club2;
        } else {
            club = club2;
        }
        if (!TextUtils.isEmpty(str7)) {
            club.setClubLogo(str7);
            C2580w.m12905a(BeastBikes.j().getApplicationContext(), "更换俱乐部logo", null);
        }
        if (!TextUtils.isEmpty(str2)) {
            club.setClubName(str2);
            C2580w.m12905a(BeastBikes.j().getApplicationContext(), "更改俱乐部名称", null);
        }
        if (!TextUtils.isEmpty(str3)) {
            club.setClubProvince(str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            club.setClubCity(str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            club.setClubDesc(str5);
            C2580w.m12905a(BeastBikes.j().getApplicationContext(), "更改俱乐部简介", null);
        }
        if (!TextUtils.isEmpty(str6)) {
            club.setClubNotice(str6);
        }
        club.setIsPrivate(i);
        try {
            JSONObject a = this.f9332d.a(str2, str, str3, str4, str5, str6, i);
            if (a == null) {
                return false;
            }
            if (a.optInt("code") == 0) {
                Editor edit = this.f9331c.edit();
                edit.putString("beast.club.name", club.getClubName());
                edit.putString("beast.club.desc", club.getClubDesc());
                edit.apply();
                boolean optBoolean = a.optBoolean(C0882j.f2229c);
                if (!optBoolean) {
                    return optBoolean;
                }
                this.f9330b.m9031d(club);
                return optBoolean;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9333e, optString);
            }
            return false;
        } catch (Throwable e2) {
            throw new BusinessException(e2);
        }
    }

    /* renamed from: a */
    public ClubInfoCompact m10534a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, double d, double d2) throws BusinessException {
        try {
            JSONObject a = this.f9332d.a(str2, str, str3, str4, str5, str6, str7, str8, str9, i, d, d2);
            if (a == null) {
                return null;
            }
            if (a.optInt("code") == 0) {
                ClubInfoCompact clubInfoCompact = new ClubInfoCompact(a.optJSONObject(C0882j.f2229c));
                clubInfoCompact.setStatus(4);
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser == null) {
                    return clubInfoCompact;
                }
                Club a2 = this.f9330b.m10619a(currentUser.getObjectId());
                if (a2 == null) {
                    a2 = new Club();
                    a2.setId(UUID.randomUUID().toString());
                    a2.setUserId(currentUser.getObjectId());
                }
                a2.setClubId(clubInfoCompact.getObjectId());
                a2.setClubName(clubInfoCompact.getName());
                a2.setClubLogo(clubInfoCompact.getLogo());
                a2.setClubDesc(clubInfoCompact.getDesc());
                a2.setClubManagerId(clubInfoCompact.getManagerId());
                a2.setMaxMembers(clubInfoCompact.getMaxMembers());
                a2.setClubMembers(clubInfoCompact.getMembers());
                a2.setClubMilestone(clubInfoCompact.getMilestone());
                a2.setClubNotice(clubInfoCompact.getNotice());
                a2.setClubProvince(clubInfoCompact.getProvince());
                a2.setClubCity(clubInfoCompact.getCity());
                a2.setClubScore(clubInfoCompact.getScore());
                a2.setActivities(clubInfoCompact.getActivities());
                a2.setRank(clubInfoCompact.getRank());
                a2.setLevel(128);
                a2.setStatus(clubInfoCompact.getStatus());
                currentUser.setClubId(clubInfoCompact.getObjectId());
                currentUser.setClubName(clubInfoCompact.getName());
                this.f9330b.mo3187a(a2);
                Editor edit = this.f9331c.edit();
                edit.putString("beast.club.id", clubInfoCompact.getObjectId());
                edit.putString("beast.club.user.id", currentUser.getObjectId());
                edit.putInt("beast.club.level", a2.getLevel());
                edit.putInt("beast.club.status", clubInfoCompact.getStatus());
                edit.apply();
                return clubInfoCompact;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9333e, optString);
            }
            return null;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: b */
    public JSONObject m10545b() throws BusinessException {
        try {
            JSONObject b = this.f9332d.b();
            if (b == null) {
                return null;
            }
            if (b.optInt("code") == 0) {
                return b.optJSONObject(C0882j.f2229c);
            }
            String optString = b.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            Toasts.showOnUiThread(this.f9333e, optString);
            return null;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public List<ApplyDTO> m10535a(int i, int i2) throws BusinessException {
        List<ApplyDTO> list = null;
        try {
            JSONObject a = this.f9332d.a(i, i2);
            if (a != null) {
                if (a.optInt("code") == 0) {
                    JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                    list = new ArrayList();
                    for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                        list.add(new ApplyDTO(optJSONArray.optJSONObject(i3)));
                    }
                } else {
                    String optString = a.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        Toasts.showOnUiThread(this.f9333e, optString);
                    }
                }
            }
            return list;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public boolean m10542a(String str, int i) throws BusinessException {
        boolean z = false;
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject a = this.f9332d.a(str, i);
                if (a != null) {
                    if (a.optInt("code") == 0) {
                        z = a.optBoolean(C0882j.f2229c);
                        if (z) {
                            int i2 = this.f9331c.getInt("beast.club.dot.more", 0);
                            if (i2 > 0) {
                                this.f9331c.edit().putInt("beast.club.dot.more", i2 - 1).apply();
                            }
                        }
                    } else {
                        String optString = a.optString(AVStatus.MESSAGE_TAG);
                        if (!TextUtils.isEmpty(optString)) {
                            Toasts.showOnUiThread(this.f9333e, optString);
                        }
                    }
                }
            } catch (Throwable e) {
                throw new BusinessException(e);
            }
        }
        return z;
    }

    /* renamed from: b */
    public boolean m10546b(String str, int i) throws BusinessException {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject b = this.f9332d.b(str, i);
            if (b == null) {
                return false;
            }
            if (b.optInt("code") == 0) {
                return b.optBoolean(C0882j.f2229c);
            }
            String optString = b.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            Toasts.showOnUiThread(this.f9333e, optString);
            return false;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public List<ClubRankBean> m10536a(int i, int i2, int i3) throws BusinessException {
        JSONObject a = this.f9332d.a(i, i2, i3);
        if (a == null) {
            return null;
        }
        if (a.optInt("code") == 0) {
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            List<ClubRankBean> arrayList = new ArrayList();
            for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                arrayList.add(new ClubRankBean(optJSONArray.optJSONObject(i4)));
            }
            return arrayList;
        }
        String optString = a.optString(AVStatus.MESSAGE_TAG);
        if (TextUtils.isEmpty(optString)) {
            return null;
        }
        Toasts.showOnUiThread(this.f9333e, optString);
        return null;
    }

    /* renamed from: a */
    public List<C2173a> m10537a(int i, int i2, int i3, String str) throws BusinessException {
        JSONObject a = this.f9332d.a(i, i2, i3, str);
        if (a == null) {
            return null;
        }
        if (a.optInt("code") == 0) {
            List<C2173a> arrayList = new ArrayList();
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                arrayList.add(new C2173a(optJSONArray.optJSONObject(i4)));
            }
            return arrayList;
        }
        String optString = a.optString(AVStatus.MESSAGE_TAG);
        if (TextUtils.isEmpty(optString)) {
            return null;
        }
        Toasts.showOnUiThread(this.f9333e, optString);
        return null;
    }

    /* renamed from: a */
    public List<C2067f> m10539a(String str, int i, int i2) throws BusinessException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject a = this.f9332d.a(str, i, i2);
        if (a == null) {
            return null;
        }
        if (a.optInt("code") == 0) {
            List<C2067f> arrayList = new ArrayList();
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                arrayList.add(new C2067f(optJSONArray.optJSONObject(i3)));
            }
            return arrayList;
        }
        String optString = a.optString(AVStatus.MESSAGE_TAG);
        if (TextUtils.isEmpty(optString)) {
            return null;
        }
        Toasts.showOnUiThread(this.f9333e, optString);
        return null;
    }

    /* renamed from: a */
    public int m10531a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        JSONObject a = this.f9332d.a(str, str2);
        if (a == null) {
            return -1;
        }
        if (a.optInt("code") == 0) {
            return a.optInt(C0882j.f2229c);
        }
        String optString = a.optString(AVStatus.MESSAGE_TAG);
        if (TextUtils.isEmpty(optString)) {
            return -1;
        }
        Toasts.showOnUiThread(this.f9333e, optString);
        return -1;
    }

    /* renamed from: c */
    public List<C2065d> m10548c() throws BusinessException {
        List<C2065d> list = null;
        JSONObject c = this.f9332d.c();
        if (c != null) {
            try {
                if (c.optInt("code") == 0) {
                    JSONArray optJSONArray = c.optJSONArray(C0882j.f2229c);
                    if (!C2563k.m12868a(optJSONArray)) {
                        list = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            list.add(new C2065d(optJSONArray.optJSONObject(i)));
                        }
                    }
                }
            } catch (Exception e) {
                throw new BusinessException();
            }
        }
        return list;
    }

    /* renamed from: d */
    public C2068g m10550d() throws BusinessException {
        C2068g c2068g = null;
        JSONObject d = this.f9332d.d();
        if (d != null) {
            try {
                if (d.optInt("code") == 0) {
                    d = d.optJSONObject(C0882j.f2229c);
                    if (!C2563k.m12869a(d)) {
                        JSONArray optJSONArray = d.optJSONArray("privileges");
                        List arrayList = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            arrayList.add(optJSONArray.optJSONObject(i).optString("name"));
                        }
                        c2068g = new C2068g(d.optInt("curLevel"), arrayList);
                    }
                }
            } catch (Exception e) {
                throw new BusinessException();
            }
        }
        return c2068g;
    }

    /* renamed from: e */
    public void m10552e() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            try {
                Club a = this.f9330b.m10619a(currentUser.getObjectId());
                if (a != null) {
                    a.setStatus(1);
                    currentUser.setClubId(a.getClubId());
                    this.f9330b.mo3187a(a);
                    if (this.f9331c != null) {
                        this.f9331c.edit().putInt("beast.club.status", 1).apply();
                    }
                }
            } catch (PersistenceException e) {
                f9329a.error("Update club status to apply pass error, " + e);
            }
        }
    }

    /* renamed from: f */
    public void m10553f() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            try {
                Club a = this.f9330b.m10619a(currentUser.getObjectId());
                if (a != null) {
                    a.setStatus(0);
                    currentUser.setClubId(a.getClubId());
                    this.f9330b.m10620b(a.getClubId());
                    if (this.f9331c != null) {
                        this.f9331c.edit().putInt("beast.club.status", 6).apply();
                    }
                }
            } catch (PersistenceException e) {
                f9329a.error("Update club status to apply pass error, " + e);
            }
        }
    }

    /* renamed from: c */
    public boolean m10549c(String str, int i) throws BusinessException {
        JSONObject c = this.f9332d.c(str, i);
        if (c == null) {
            return false;
        }
        try {
            if (c.optInt("code") == 0) {
                return c.optBoolean(C0882j.f2229c);
            }
            String optString = c.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            Toasts.showOnUiThread(this.f9333e, optString);
            return false;
        } catch (Exception e) {
            throw new BusinessException();
        }
    }

    /* renamed from: g */
    public boolean m10554g() throws BusinessException {
        JSONObject g = this.f9332d.g();
        if (g == null) {
            return false;
        }
        try {
            if (g.optInt("code") == 0) {
                return g.optBoolean(C0882j.f2229c);
            }
            String optString = g.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            Toasts.showOnUiThread(this.f9333e, optString);
            return false;
        } catch (Exception e) {
            throw new BusinessException();
        }
    }

    /* renamed from: h */
    public boolean m10555h() throws BusinessException {
        JSONObject f = this.f9332d.f();
        if (f == null) {
            return false;
        }
        try {
            if (f.optInt("code") == 0) {
                return f.optBoolean(C0882j.f2229c);
            }
            String optString = f.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            Toasts.showOnUiThread(this.f9333e, optString);
            return false;
        } catch (Exception e) {
            throw new BusinessException();
        }
    }

    /* renamed from: i */
    public String m10556i() throws BusinessException {
        JSONObject e = this.f9332d.e();
        if (e == null) {
            return null;
        }
        try {
            if (e.optInt("code") == 0) {
                JSONArray optJSONArray = e.optJSONArray(C0882j.f2229c);
                if (optJSONArray.length() <= 0) {
                    return null;
                }
                e = optJSONArray.getJSONObject(optJSONArray.length() - 1);
                if (e != null) {
                    return e.optString("nickname");
                }
                return null;
            }
            String optString = e.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            Toasts.showOnUiThread(this.f9333e, optString);
            return null;
        } catch (Exception e2) {
            throw new BusinessException();
        }
    }
}
