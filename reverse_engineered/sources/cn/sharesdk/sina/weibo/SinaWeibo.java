package cn.sharesdk.sina.weibo;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.p011b.p013b.C0597f.C0596a;
import cn.sharesdk.framework.utils.C0621d;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.cons.C0845b;
import com.baidu.mapapi.SDKInitializer;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Hashon;
import io.rong.imlib.statistics.UserData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SinaWeibo extends Platform {
    public static final String NAME = SinaWeibo.class.getSimpleName();
    /* renamed from: a */
    private String f1544a;
    /* renamed from: b */
    private String f1545b;
    /* renamed from: c */
    private String f1546c;
    /* renamed from: d */
    private boolean f1547d;

    public static class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
        @Deprecated
        public String imageUrl;
        @Deprecated
        public float latitude;
        @Deprecated
        public float longitude;
    }

    public SinaWeibo(Context context) {
        super(context);
    }

    protected void initDevInfo(String str) {
        this.f1544a = getDevinfo("AppKey");
        this.f1545b = getDevinfo("AppSecret");
        this.f1546c = getDevinfo("RedirectUrl");
        this.f1547d = "true".equals(getDevinfo("ShareByAppClient"));
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 1;
    }

    protected int getPlatformId() {
        return 1;
    }

    protected void setNetworkDevinfo() {
        this.f1544a = getNetworkDevinfo(C0845b.f2067h, "AppKey");
        this.f1545b = getNetworkDevinfo("app_secret", "AppSecret");
        this.f1546c = getNetworkDevinfo("redirect_uri", "RedirectUrl");
    }

    public boolean isClientValid() {
        return C0665d.m2538a((Platform) this).m2558b();
    }

    /* renamed from: c */
    private boolean m2500c() {
        if (TextUtils.isEmpty(getDb().get("refresh_token"))) {
            return false;
        }
        C0665d a = C0665d.m2538a((Platform) this);
        a.m2553a(this.f1544a, this.f1545b);
        a.m2552a(this.f1546c);
        return a.m2555a();
    }

    protected boolean checkAuthorize(int i, Object obj) {
        C0665d a = C0665d.m2538a((Platform) this);
        if (i == 9 && this.f1547d && a.m2558b()) {
            return true;
        }
        if (isAuthValid() || m2500c()) {
            a.m2553a(this.f1544a, this.f1545b);
            a.m2557b(this.db.getToken());
            return true;
        }
        innerAuthorize(i, obj);
        return false;
    }

    protected void doAuthorize(String[] strArr) {
        final C0665d a = C0665d.m2538a((Platform) this);
        a.m2553a(this.f1544a, this.f1545b);
        a.m2552a(this.f1546c);
        a.m2554a(strArr);
        a.m2551a(new AuthorizeListener(this) {
            /* renamed from: b */
            final /* synthetic */ SinaWeibo f1543b;

            public void onComplete(Bundle bundle) {
                long parseLong;
                String string = bundle.getString("uid");
                String string2 = bundle.getString("access_token");
                String string3 = bundle.getString("expires_in");
                String string4 = bundle.getString("refresh_token");
                this.f1543b.db.put("nickname", bundle.getString("userName"));
                this.f1543b.db.put("remind_in", bundle.getString("remind_in"));
                this.f1543b.db.putToken(string2);
                try {
                    parseLong = C4275R.parseLong(string3);
                } catch (Throwable th) {
                    parseLong = 0;
                }
                this.f1543b.db.putExpiresIn(parseLong);
                this.f1543b.db.put("refresh_token", string4);
                this.f1543b.db.putUserId(string);
                a.m2557b(string2);
                this.f1543b.afterRegister(1, null);
            }

            public void onError(Throwable th) {
                if (this.f1543b.listener != null) {
                    this.f1543b.listener.onError(this.f1543b, 1, th);
                }
            }

            public void onCancel() {
                if (this.f1543b.listener != null) {
                    this.f1543b.listener.onCancel(this.f1543b, 1);
                }
            }
        }, isSSODisable());
    }

    protected void follow(String str) {
        try {
            HashMap d = C0665d.m2538a((Platform) this).m2562d(str);
            if (d == null) {
                if (this.listener != null) {
                    this.listener.onError(this, 6, new Throwable());
                }
            } else if (!d.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) || ((Integer) d.get(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE)).intValue() == 0) {
                if (this.listener != null) {
                    this.listener.onComplete(this, 6, d);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 6, new Throwable(new Hashon().fromHashMap(d)));
            }
        } catch (Throwable th) {
            this.listener.onError(this, 6, th);
        }
    }

    protected void timeline(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get("nickname");
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap a = C0665d.m2538a((Platform) this).m2547a(i, i2, str);
                if (a == null) {
                    if (this.listener != null) {
                        this.listener.onError(this, 7, new Throwable());
                    }
                } else if (!a.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) || ((Integer) a.get(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE)).intValue() == 0) {
                    if (this.listener != null) {
                        this.listener.onComplete(this, 7, a);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 7, new Throwable(new Hashon().fromHashMap(a)));
                }
            } catch (Throwable th) {
                this.listener.onError(this, 7, th);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 7, new RuntimeException("Both weibo id and screen_name are null"));
        }
    }

    protected void userInfor(String str) {
        Object obj = 1;
        Object obj2 = null;
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
            obj2 = 1;
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get("nickname");
        } else {
            obj = obj2;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap c = C0665d.m2538a((Platform) this).m2560c(str);
                if (c == null) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable());
                    }
                } else if (!c.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) || ((Integer) c.get(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE)).intValue() == 0) {
                    if (obj != null) {
                        this.db.putUserId(String.valueOf(c.get("id")));
                        this.db.put("nickname", String.valueOf(c.get("screen_name")));
                        this.db.put("icon", String.valueOf(c.get("avatar_hd")));
                        if (String.valueOf(c.get("verified")).equals("true")) {
                            this.db.put("secretType", C0844a.f2048d);
                        } else {
                            this.db.put("secretType", "0");
                        }
                        this.db.put("secret", String.valueOf(c.get("verified_reason")));
                        String valueOf = String.valueOf(c.get(UserData.GENDER_KEY));
                        if (valueOf.equals(ANSIConstants.ESC_END)) {
                            this.db.put(UserData.GENDER_KEY, "0");
                        } else if (valueOf.equals("f")) {
                            this.db.put(UserData.GENDER_KEY, C0844a.f2048d);
                        } else {
                            this.db.put(UserData.GENDER_KEY, "2");
                        }
                        this.db.put("snsUserUrl", "http://weibo.com/" + String.valueOf(c.get("profile_url")));
                        this.db.put("resume", String.valueOf(c.get("description")));
                        this.db.put("followerCount", String.valueOf(c.get("followers_count")));
                        this.db.put("favouriteCount", String.valueOf(c.get("friends_count")));
                        this.db.put("shareCount", String.valueOf(c.get("statuses_count")));
                        this.db.put("snsregat", String.valueOf(C4275R.dateToLong(String.valueOf(c.get("created_at")))));
                    }
                    if (this.listener != null) {
                        this.listener.onComplete(this, 8, c);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(c)));
                }
            } catch (Throwable th) {
                this.listener.onError(this, 8, th);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new RuntimeException("Both weibo id and screen_name are null"));
        }
    }

    protected void getFriendList(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get("nickname");
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap b = C0665d.m2538a((Platform) this).m2556b(i, i2, str);
                if (b == null) {
                    if (this.listener != null) {
                        this.listener.onError(this, 2, new Throwable());
                    }
                } else if (!b.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) || ((Integer) b.get(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE)).intValue() == 0) {
                    if (this.listener != null) {
                        this.listener.onComplete(this, 2, b);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 2, new Throwable(new Hashon().fromHashMap(b)));
                }
            } catch (Throwable th) {
                this.listener.onError(this, 2, th);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 2, new RuntimeException("Both weibo id and screen_name are null"));
        }
    }

    protected void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        try {
            HashMap a = C0665d.m2538a((Platform) this).m2549a(str, str2, (HashMap) hashMap, (HashMap) hashMap2);
            if (a == null || a.size() <= 0) {
                if (this.listener != null) {
                    this.listener.onError(this, i, new Throwable());
                }
            } else if (!a.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) || ((Integer) a.get(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE)).intValue() == 0) {
                if (this.listener != null) {
                    this.listener.onComplete(this, i, a);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, i, new Throwable(new Hashon().fromHashMap(a)));
            }
        } catch (Throwable th) {
            this.listener.onError(this, i, th);
        }
    }

    protected void doShare(cn.sharesdk.framework.Platform.ShareParams shareParams) {
        String string;
        C0665d a;
        String imagePath;
        String imageUrl;
        HashMap a2;
        CharSequence text = shareParams.getText();
        if (TextUtils.isEmpty(text)) {
            int stringRes = C4275R.getStringRes(getContext(), "ssdk_weibo_upload_content");
            if (stringRes > 0) {
                string = getContext().getString(stringRes);
                a = C0665d.m2538a((Platform) this);
                a.m2553a(this.f1544a, this.f1545b);
                imagePath = shareParams.getImagePath();
                imageUrl = shareParams.getImageUrl();
                if (this.f1547d || !a.m2558b()) {
                    float latitude = shareParams.getLatitude();
                    a2 = a.m2548a(getShortLintk(string, false), imageUrl, imagePath, shareParams.getLongitude(), latitude);
                    if (a2 != null) {
                        if (this.listener != null) {
                            this.listener.onError(this, 9, new Throwable());
                        }
                    } else if (a2.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) || ((Integer) a2.get(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE)).intValue() == 0) {
                        a2.put("ShareParams", shareParams);
                        if (this.listener != null) {
                            this.listener.onComplete(this, 9, a2);
                        }
                    } else if (this.listener != null) {
                        this.listener.onError(this, 9, new Throwable(new Hashon().fromHashMap(a2)));
                        return;
                    } else {
                        return;
                    }
                }
                try {
                    a.m2550a(shareParams, this.listener);
                    return;
                } catch (Throwable th) {
                    this.listener.onError(this, 9, th);
                    return;
                }
            }
        }
        CharSequence charSequence = text;
        a = C0665d.m2538a((Platform) this);
        a.m2553a(this.f1544a, this.f1545b);
        imagePath = shareParams.getImagePath();
        imageUrl = shareParams.getImageUrl();
        if (this.f1547d) {
        }
        try {
            float latitude2 = shareParams.getLatitude();
            a2 = a.m2548a(getShortLintk(string, false), imageUrl, imagePath, shareParams.getLongitude(), latitude2);
            if (a2 != null) {
                if (a2.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE)) {
                }
                a2.put("ShareParams", shareParams);
                if (this.listener != null) {
                    this.listener.onComplete(this, 9, a2);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 9, new Throwable());
            }
        } catch (Throwable th2) {
            this.listener.onError(this, 9, th2);
        }
    }

    protected C0596a filterShareContent(cn.sharesdk.framework.Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        C0596a c0596a = new C0596a();
        c0596a.f1301b = shareParams.getText();
        if (hashMap != null) {
            c0596a.f1300a = String.valueOf(hashMap.get("id"));
            c0596a.f1303d.add(String.valueOf(hashMap.get("original_pic")));
            c0596a.f1306g = hashMap;
        }
        return c0596a;
    }

    protected HashMap<String, Object> getFollowings(int i, int i2, String str) {
        HashMap<String, Object> hashMap = null;
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get("nickname");
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap b = C0665d.m2538a((Platform) this).m2556b(i, i2, str);
                if (!(b == null || b.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE))) {
                    b.put("current_cursor", Integer.valueOf(i2));
                    hashMap = filterFriendshipInfo(2, b);
                }
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
        }
        return hashMap;
    }

    protected HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        HashMap<String, Object> hashMap = null;
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get("nickname");
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap c = C0665d.m2538a((Platform) this).m2559c(i, i2, str);
                if (!(c == null || c.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE))) {
                    c.put("page_count", Integer.valueOf(i));
                    c.put("current_cursor", Integer.valueOf(i2));
                    hashMap = filterFriendshipInfo(10, c);
                }
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
        }
        return hashMap;
    }

    protected HashMap<String, Object> getFollowers(int i, int i2, String str) {
        HashMap<String, Object> hashMap = null;
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get("nickname");
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap d = C0665d.m2538a((Platform) this).m2561d(i, i2, str);
                if (!(d == null || d.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE))) {
                    d.put("current_cursor", Integer.valueOf(i2));
                    hashMap = filterFriendshipInfo(11, d);
                }
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
        }
        return hashMap;
    }

    protected HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        HashMap<String, Object> hashMap2 = new HashMap();
        switch (i) {
            case 2:
                hashMap2.put("type", "FOLLOWING");
                break;
            case 10:
                hashMap2.put("type", "FRIENDS");
                break;
            case 11:
                hashMap2.put("type", "FOLLOWERS");
                break;
            default:
                return null;
        }
        hashMap2.put("snsplat", Integer.valueOf(getPlatformId()));
        hashMap2.put("snsuid", this.db.getUserId());
        int parseInt = Integer.parseInt(String.valueOf(hashMap.get("current_cursor")));
        int parseInt2 = Integer.parseInt(String.valueOf(hashMap.get("total_number")));
        if (parseInt2 == 0) {
            return null;
        }
        Object obj = hashMap.get("users");
        if (obj == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) obj;
        if (arrayList2.size() <= 0) {
            return null;
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            HashMap hashMap3 = (HashMap) it.next();
            if (hashMap3 != null) {
                HashMap hashMap4 = new HashMap();
                hashMap4.put("snsuid", String.valueOf(hashMap3.get("id")));
                hashMap4.put("nickname", String.valueOf(hashMap3.get("screen_name")));
                hashMap4.put("icon", String.valueOf(hashMap3.get("avatar_hd")));
                if (String.valueOf(hashMap3.get("verified")).equals("true")) {
                    hashMap4.put("secretType", C0844a.f2048d);
                } else {
                    hashMap4.put("secretType", "0");
                }
                hashMap4.put("secret", String.valueOf(hashMap3.get("verified_reason")));
                String valueOf = String.valueOf(hashMap3.get(UserData.GENDER_KEY));
                if (valueOf.equals(ANSIConstants.ESC_END)) {
                    hashMap4.put(UserData.GENDER_KEY, "0");
                } else if (valueOf.equals("f")) {
                    hashMap4.put(UserData.GENDER_KEY, C0844a.f2048d);
                } else {
                    hashMap4.put(UserData.GENDER_KEY, "2");
                }
                hashMap4.put("snsUserUrl", "http://weibo.com/" + String.valueOf(hashMap3.get("profile_url")));
                hashMap4.put("resume", String.valueOf(hashMap3.get("description")));
                hashMap4.put("followerCount", String.valueOf(hashMap3.get("followers_count")));
                hashMap4.put("favouriteCount", String.valueOf(hashMap3.get("friends_count")));
                hashMap4.put("shareCount", String.valueOf(hashMap3.get("statuses_count")));
                hashMap4.put("snsregat", String.valueOf(C4275R.dateToLong(String.valueOf(hashMap3.get("created_at")))));
                arrayList.add(hashMap4);
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        if (10 == i) {
            hashMap2.put("nextCursor", ((Integer) hashMap.get("page_count")).intValue() * (parseInt + 1) >= parseInt2 ? parseInt + "_true" : (parseInt + 1) + "_false");
        } else {
            int size = arrayList.size() + parseInt;
            if (size >= parseInt2) {
                obj = parseInt2 + "_true";
            } else {
                obj = size + "_false";
            }
            hashMap2.put("nextCursor", obj);
        }
        hashMap2.put("list", arrayList);
        return hashMap2;
    }

    public boolean hasShareCallback() {
        return true;
    }
}
