package com.beastbikes.android.modules.social.im.p074a;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.social.im.dao.C2342a;
import com.beastbikes.android.modules.social.im.dao.entity.Friend;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.persistence.PersistenceException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: FriendManager */
/* renamed from: com.beastbikes.android.modules.social.im.a.a */
public class C2341a extends C1397a {
    /* renamed from: a */
    private static final Logger f11122a = LoggerFactory.getLogger(C2341a.class);
    /* renamed from: b */
    private final C2342a f11123b;
    /* renamed from: c */
    private final C1433b f11124c;
    /* renamed from: d */
    private Activity f11125d;
    /* renamed from: e */
    private SharedPreferences f11126e;

    public C2341a(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        this.f11123b = new C2342a(((BeastBikes) activity.getApplicationContext()).d());
        this.f11125d = activity;
        this.f11124c = (C1433b) new C1772d(activity).m9399a(C1433b.class, C1768c.f8075a, C1768c.m9391a(activity));
        if (AVUser.getCurrentUser() != null) {
            this.f11126e = activity.getSharedPreferences(AVUser.getCurrentUser().getObjectId(), 0);
        }
    }

    public C2341a(Context context) {
        super((C1372b) context.getApplicationContext());
        this.f11123b = new C2342a(((BeastBikes) context.getApplicationContext()).d());
        this.f11124c = (C1433b) new C1772d(context).m9399a(C1433b.class, C1768c.f8075a, C1768c.m9391a(context));
        if (AVUser.getCurrentUser() != null) {
            this.f11126e = context.getSharedPreferences(AVUser.getCurrentUser().getObjectId(), 0);
        }
    }

    /* renamed from: a */
    public void m11950a(String str, FriendDTO friendDTO) {
        Friend friend = new Friend();
        friend.setId(str + ":" + friendDTO.getFriendId());
        friend.setUserId(str);
        friend.setFriendId(friendDTO.getFriendId());
        friend.setAvatar(friendDTO.getAvatar());
        friend.setNickname(friendDTO.getNickname());
        friend.setStatus(0);
        friend.setCreateTime(friendDTO.getCreateTime());
        friend.setRemarks(friendDTO.getRemarks());
        try {
            this.f11123b.mo3187a(friend);
        } catch (PersistenceException e) {
            f11122a.error("save friend error");
        }
    }

    /* renamed from: a */
    public boolean m11953a(String str, String str2) {
        boolean z = false;
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject a = this.f11124c.a(str, str2);
                if (a != null) {
                    z = a.optBoolean(C0882j.f2229c);
                }
            } catch (Exception e) {
                f11122a.error("Add friend by " + str + " is error");
            }
        }
        return z;
    }

    /* renamed from: a */
    public boolean m11951a() {
        try {
            JSONObject a = this.f11124c.a();
            if (a == null) {
                return false;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f11125d, optString);
            }
            if (a.optInt("code") == 0 && a.optBoolean(C0882j.f2229c)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            f11122a.error("clean friend requests is error");
            return false;
        }
    }

    /* renamed from: a */
    public boolean m11952a(int i, int i2) {
        try {
            JSONObject a = this.f11124c.a(i, i2);
            if (a == null) {
                return false;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f11125d, optString);
            }
            if (a.optInt("code") == 0 && a.optBoolean(C0882j.f2229c)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            f11122a.error("clean friend requests is error");
            return false;
        }
    }

    /* renamed from: b */
    public List<FriendDTO> m11955b(int i, int i2) {
        try {
            JSONObject b = this.f11124c.b(i, i2);
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
                    arrayList.add(new FriendDTO(optJSONArray.optJSONObject(i3)));
                }
                return arrayList;
            }
            String optString = b.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            Toasts.showOnUiThread(this.f11125d, optString);
            return null;
        } catch (Exception e) {
            f11122a.error("Get friend request list error");
            return null;
        }
    }

    /* renamed from: a */
    public List<FriendDTO> m11949a(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject a = this.f11124c.a(str, i, i2);
            if (a == null) {
                return null;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f11125d, optString);
            }
            if (a.optInt("code") != 0) {
                return null;
            }
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            List<FriendDTO> arrayList = new ArrayList();
            for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                arrayList.add(new FriendDTO(optJSONArray.optJSONObject(i3)));
            }
            return arrayList;
        } catch (Exception e) {
            f11122a.error("Search friend by " + str + " is error");
            return null;
        }
    }

    /* renamed from: b */
    public String m11954b() {
        try {
            JSONObject b = this.f11124c.b();
            if (b == null) {
                return "";
            }
            if (b.optInt("code") == 0) {
                b = b.optJSONObject(C0882j.f2229c);
                if (b == null) {
                    return "";
                }
                return b.optString("token");
            }
            return "";
        } catch (Exception e) {
            f11122a.error("Get chat token is error");
        }
    }

    /* renamed from: b */
    public JSONObject m11956b(String str, String str2) {
        try {
            return this.f11124c.b(str, str2);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: c */
    public boolean m11957c(String str, String str2) throws BusinessException {
        try {
            this.f11126e.edit().putString(str2, str).commit();
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                UserInfo userInfo = new UserInfo(currentUser.getObjectId(), str, Uri.parse(currentUser.getAvatar()));
                RongIM.getInstance().setCurrentUserInfo(userInfo);
                RongIM.getInstance().refreshUserInfoCache(userInfo);
            }
            JSONObject c = this.f11124c.c(str2, str);
            if (c == null) {
                return false;
            }
            String optString = c.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f11125d, optString);
            }
            if (c.optInt("code") == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            f11122a.error("setClubChatNick is error " + e.toString());
            return false;
        }
    }

    /* renamed from: d */
    public String m11958d(String str, String str2) throws BusinessException {
        try {
            JSONObject d = this.f11124c.d(str2, str);
            if (d == null) {
                return "";
            }
            if (d.optInt("code") == 0) {
                d = d.optJSONObject(C0882j.f2229c);
                if (d == null) {
                    return "";
                }
                this.f11126e.edit().putString(str2, d.optString("clubChatNick")).commit();
                return d.optString("clubChatNick");
            }
            return "";
        } catch (Exception e) {
            f11122a.error("Get chat token is error");
        }
    }

    /* renamed from: a */
    public ProfileDTO m11948a(String str) throws BusinessException {
        try {
            JSONObject a = this.f11124c.a(str);
            if (a == null || a.optInt("code") != 0) {
                return null;
            }
            a = a.optJSONObject(C0882j.f2229c);
            if (a == null) {
                return null;
            }
            JSONObject optJSONObject = a.optJSONObject(str);
            if (optJSONObject != null) {
                return new ProfileDTO(optJSONObject.optString("uid"), optJSONObject.optString("nickname"), optJSONObject.optString("avatar"));
            }
            return null;
        } catch (Exception e) {
            f11122a.error("Get chat token is error");
            return null;
        }
    }
}
