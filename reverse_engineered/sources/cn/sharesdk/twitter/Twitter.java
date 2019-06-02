package cn.sharesdk.twitter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.p011b.p013b.C0597f.C0596a;
import cn.sharesdk.framework.utils.C0621d;
import com.alipay.sdk.cons.C0844a;
import com.baidu.mapapi.SDKInitializer;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Hashon;
import io.rong.imlib.statistics.UserData;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Twitter extends Platform {
    public static final String NAME = Twitter.class.getSimpleName();
    /* renamed from: a */
    private String f1627a;
    /* renamed from: b */
    private String f1628b;
    /* renamed from: c */
    private String f1629c;

    public static class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
    }

    public Twitter(Context context) {
        super(context);
    }

    protected void initDevInfo(String str) {
        this.f1627a = getDevinfo("ConsumerKey");
        this.f1628b = getDevinfo("ConsumerSecret");
        this.f1629c = getDevinfo("CallbackUrl");
    }

    public int getPlatformId() {
        return 11;
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 2;
    }

    protected void setNetworkDevinfo() {
        this.f1627a = getNetworkDevinfo("consumer_key", "ConsumerKey");
        this.f1628b = getNetworkDevinfo("consumer_secret", "ConsumerSecret");
        this.f1629c = getNetworkDevinfo("redirect_uri", "CallbackUrl");
    }

    protected void doShare(cn.sharesdk.framework.Platform.ShareParams shareParams) {
        C0686b a = C0686b.m2647a((Platform) this);
        HashMap hashMap = null;
        String shortLintk = getShortLintk(shareParams.getText(), false);
        try {
            String[] imageArray = shareParams.getImageArray();
            String imagePath = shareParams.getImagePath();
            Object imageUrl = shareParams.getImageUrl();
            if (imageArray != null && imageArray.length > 0) {
                hashMap = a.m2650a(shortLintk, imageArray);
            } else if (!TextUtils.isEmpty(imagePath) && new File(imagePath).exists()) {
                hashMap = a.m2659e(shortLintk, imagePath);
            } else if (TextUtils.isEmpty(imageUrl)) {
                hashMap = a.m2656c(shortLintk);
            } else {
                String downloadBitmap = BitmapHelper.downloadBitmap(getContext(), imageUrl);
                if (new File(downloadBitmap).exists()) {
                    hashMap = a.m2659e(shortLintk, downloadBitmap);
                }
            }
            if (hashMap == null) {
                if (this.listener != null) {
                    this.listener.onError(this, 8, new Throwable("response is null"));
                }
            } else if (!hashMap.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) && !hashMap.containsKey("error")) {
                hashMap.put("ShareParams", shareParams);
                if (this.listener != null) {
                    this.listener.onComplete(this, 9, hashMap);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(hashMap)));
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 9, th);
            }
        }
    }

    protected void doAuthorize(String[] strArr) {
        final C0686b a = C0686b.m2647a((Platform) this);
        a.m2653a(this.f1627a, this.f1628b, this.f1629c);
        a.m2651a(new AuthorizeListener(this) {
            /* renamed from: b */
            final /* synthetic */ Twitter f1626b;

            public void onComplete(Bundle bundle) {
                String string = bundle.getString("oauth_token");
                String string2 = bundle.getString("oauth_token_secret");
                String string3 = bundle.getString("user_id");
                String string4 = bundle.getString("screen_name");
                this.f1626b.db.putToken(string);
                this.f1626b.db.putTokenSecret(string2);
                this.f1626b.db.putUserId(string3);
                this.f1626b.db.put("nickname", string4);
                a.m2652a(string, string2);
                this.f1626b.afterRegister(1, null);
            }

            public void onError(Throwable th) {
                if (this.f1626b.listener != null) {
                    this.f1626b.listener.onError(this.f1626b, 1, th);
                }
            }

            public void onCancel() {
                if (this.f1626b.listener != null) {
                    this.f1626b.listener.onCancel(this.f1626b, 1);
                }
            }
        });
    }

    protected boolean checkAuthorize(int i, Object obj) {
        if (isAuthValid()) {
            C0686b a = C0686b.m2647a((Platform) this);
            a.m2653a(this.f1627a, this.f1628b, this.f1629c);
            String token = this.db.getToken();
            String tokenSecret = this.db.getTokenSecret();
            if (!(token == null || tokenSecret == null)) {
                a.m2652a(token, tokenSecret);
                return true;
            }
        }
        innerAuthorize(i, obj);
        return false;
    }

    protected void follow(String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 6);
        }
    }

    protected void timeline(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 7);
        }
    }

    protected void userInfor(String str) {
        try {
            HashMap b = C0686b.m2647a((Platform) this).m2654b(str);
            if (b == null || b.size() <= 0) {
                if (this.listener != null) {
                    this.listener.onError(this, 8, new Throwable("response is null"));
                }
            } else if (!b.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) && !b.containsKey("error")) {
                if (str == null) {
                    this.db.put("nickname", String.valueOf(b.get("screen_name")));
                    this.db.put("icon", String.valueOf(b.get("profile_image_url")));
                    this.db.put(UserData.GENDER_KEY, "2");
                    this.db.put("resume", String.valueOf(b.get("description")));
                    this.db.put("secretType", "true".equals(String.valueOf(b.get("verified"))) ? C0844a.f2048d : "0");
                    this.db.put("followerCount", String.valueOf(b.get("followers_count")));
                    this.db.put("favouriteCount", String.valueOf(b.get("friends_count")));
                    this.db.put("shareCount", String.valueOf(b.get("statuses_count")));
                    this.db.put("snsregat", String.valueOf(C4275R.dateToLong(String.valueOf(b.get("created_at")))));
                    this.db.put("snsUserUrl", "https://twitter.com/" + b.get("screen_name"));
                }
                if (this.listener != null) {
                    this.listener.onComplete(this, 8, b);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(b)));
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 8, th);
            }
        }
    }

    protected void getFriendList(int i, int i2, String str) {
        Object obj = null;
        if (TextUtils.isEmpty(null)) {
            obj = this.db.getUserId();
        }
        if (TextUtils.isEmpty(obj)) {
            obj = this.db.getUserName();
        }
        if (TextUtils.isEmpty(obj) && this.listener != null) {
            this.listener.onError(this, 2, new Throwable("The account do not authorize!"));
        }
        C0686b a = C0686b.m2647a((Platform) this);
        try {
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            HashMap b = a.m2655b(obj, str);
            if (b == null || b.size() <= 0) {
                if (this.listener != null) {
                    this.listener.onError(this, 2, new Throwable("response is null"));
                }
            } else if (b.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) || b.containsKey("error")) {
                if (this.listener != null) {
                    this.listener.onError(this, 2, new Throwable(new Hashon().fromHashMap(b)));
                }
            } else if (this.listener != null) {
                this.listener.onComplete(this, 2, b);
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 2, th);
            }
        }
    }

    protected void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        try {
            HashMap a = C0686b.m2647a((Platform) this).m2649a(str, str2, hashMap, hashMap2);
            if (a == null || a.size() <= 0) {
                if (this.listener != null) {
                    this.listener.onError(this, i, new Throwable("response is null"));
                }
            } else if (a.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) || a.containsKey("error")) {
                if (this.listener != null) {
                    this.listener.onError(this, i, new Throwable(new Hashon().fromHashMap(a)));
                }
            } else if (this.listener != null) {
                this.listener.onComplete(this, i, a);
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, i, th);
            }
        }
    }

    protected C0596a filterShareContent(cn.sharesdk.framework.Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        C0596a c0596a = new C0596a();
        c0596a.f1301b = shareParams.getText();
        if (hashMap != null) {
            HashMap hashMap2 = (HashMap) hashMap.get("entities");
            if (hashMap2 != null) {
                ArrayList arrayList = (ArrayList) hashMap2.get("media");
                if (!(arrayList == null || arrayList.size() <= 0 || ((HashMap) arrayList.get(0)) == null)) {
                    c0596a.f1303d.add(String.valueOf(hashMap.get("media_url")));
                }
            }
            c0596a.f1300a = String.valueOf(hashMap.get("id"));
            c0596a.f1306g = hashMap;
        }
        return c0596a;
    }

    protected HashMap<String, Object> getFollowers(int i, int i2, String str) {
        Object userId;
        HashMap<String, Object> hashMap = null;
        if (TextUtils.isEmpty(null)) {
            userId = this.db.getUserId();
        } else {
            userId = null;
        }
        if (TextUtils.isEmpty(userId)) {
            userId = this.db.getUserName();
        }
        if (!TextUtils.isEmpty(userId)) {
            C0686b a = C0686b.m2647a((Platform) this);
            try {
                if (TextUtils.isEmpty(str)) {
                    str = "0";
                }
                HashMap c = a.m2657c(userId, str);
                if (!(c == null || c.size() <= 0 || c.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) || c.containsKey("error"))) {
                    hashMap = filterFriendshipInfo(11, c);
                }
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
        }
        return hashMap;
    }

    protected HashMap<String, Object> getFollowings(int i, int i2, String str) {
        Object userId;
        HashMap<String, Object> hashMap = null;
        if (TextUtils.isEmpty(null)) {
            userId = this.db.getUserId();
        } else {
            userId = null;
        }
        if (TextUtils.isEmpty(userId)) {
            userId = this.db.getUserName();
        }
        if (!TextUtils.isEmpty(userId)) {
            C0686b a = C0686b.m2647a((Platform) this);
            try {
                if (TextUtils.isEmpty(str)) {
                    str = "0";
                }
                HashMap b = a.m2655b(userId, str);
                if (!(b == null || b.size() <= 0 || b.containsKey(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) || b.containsKey("error"))) {
                    hashMap = filterFriendshipInfo(2, b);
                }
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
        }
        return hashMap;
    }

    protected HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        CharSequence valueOf;
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
        if (hashMap.containsKey("next_cursor")) {
            valueOf = String.valueOf(hashMap.get("next_cursor"));
        } else {
            valueOf = null;
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
                hashMap4.put("icon", String.valueOf(hashMap3.get("profile_image_url")));
                hashMap4.put(UserData.GENDER_KEY, "2");
                hashMap4.put("resume", String.valueOf(hashMap3.get("description")));
                hashMap4.put("secretType", "true".equals(String.valueOf(hashMap3.get("verified"))) ? C0844a.f2048d : "0");
                hashMap4.put("followerCount", String.valueOf(hashMap3.get("followers_count")));
                hashMap4.put("favouriteCount", String.valueOf(hashMap3.get("friends_count")));
                hashMap4.put("shareCount", String.valueOf(hashMap3.get("statuses_count")));
                hashMap4.put("snsregat", String.valueOf(C4275R.dateToLong(String.valueOf(hashMap3.get("created_at")))));
                hashMap4.put("snsUserUrl", "https://twitter.com/" + hashMap3.get("screen_name"));
                arrayList.add(hashMap4);
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        obj = valueOf + "_false";
        if (TextUtils.isEmpty(valueOf) || "0".equals(valueOf)) {
            obj = "0_true";
        }
        hashMap2.put("nextCursor", obj);
        hashMap2.put("list", arrayList);
        return hashMap2;
    }

    public boolean hasShareCallback() {
        return true;
    }
}
