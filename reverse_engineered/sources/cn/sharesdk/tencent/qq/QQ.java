package cn.sharesdk.tencent.qq;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.p011b.p013b.C0597f.C0596a;
import cn.sharesdk.framework.utils.C0621d;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.framework.ui.android.WebActivity;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import io.rong.imlib.statistics.UserData;
import java.util.HashMap;

public class QQ extends Platform {
    public static final String NAME = QQ.class.getSimpleName();
    /* renamed from: a */
    private String f1584a;
    /* renamed from: b */
    private boolean f1585b;
    /* renamed from: c */
    private boolean f1586c = true;

    public static class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
        @Deprecated
        public String imageUrl;
        @Deprecated
        public String musicUrl;
        @Deprecated
        public String title;
        @Deprecated
        public String titleUrl;
    }

    public QQ(Context context) {
        super(context);
    }

    protected void initDevInfo(String str) {
        this.f1584a = getDevinfo(C0861d.f2134f);
        this.f1585b = "true".equals(getDevinfo("ShareByAppClient"));
        if (this.f1584a == null || this.f1584a.length() <= 0) {
            this.f1584a = getDevinfo("QZone", C0861d.f2134f);
            if (this.f1584a != null && this.f1584a.length() > 0) {
                copyDevinfo("QZone", NAME);
                this.f1584a = getDevinfo(C0861d.f2134f);
                this.f1585b = "true".equals(getDevinfo("ShareByAppClient"));
                C0621d.m2279a().d("Try to use the dev info of QZone, this will cause Id and SortId field are always 0.", new Object[0]);
            }
        }
    }

    public int getPlatformId() {
        return 24;
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 2;
    }

    protected void setNetworkDevinfo() {
        this.f1584a = getNetworkDevinfo("app_id", C0861d.f2134f);
        if (this.f1584a == null || this.f1584a.length() <= 0) {
            this.f1584a = getNetworkDevinfo(6, "app_id", C0861d.f2134f);
            if (this.f1584a != null && this.f1584a.length() > 0) {
                copyNetworkDevinfo(6, 24);
                this.f1584a = getNetworkDevinfo("app_id", C0861d.f2134f);
                C0621d.m2279a().d("Try to use the dev info of QZone, this will cause Id and SortId field are always 0.", new Object[0]);
            }
        }
    }

    public boolean isClientValid() {
        C0673b a = C0673b.m2597a((Platform) this);
        a.m2603a(this.f1584a);
        return a.m2606a();
    }

    protected void doAuthorize(String[] strArr) {
        final C0673b a = C0673b.m2597a((Platform) this);
        a.m2603a(this.f1584a);
        a.m2605a(strArr);
        a.m2602a(new AuthorizeListener(this) {
            /* renamed from: b */
            final /* synthetic */ QQ f1581b;

            public void onError(Throwable th) {
                if (this.f1581b.listener != null) {
                    this.f1581b.listener.onError(this.f1581b, 1, th);
                }
            }

            public void onComplete(Bundle bundle) {
                String string = bundle.getString("open_id");
                String string2 = bundle.getString("access_token");
                String string3 = bundle.getString("expires_in");
                this.f1581b.db.putToken(string2);
                this.f1581b.db.putTokenSecret("");
                try {
                    this.f1581b.db.putExpiresIn(C4275R.parseLong(string3));
                } catch (Throwable th) {
                    C0621d.m2279a().d(th);
                }
                this.f1581b.db.putUserId(string);
                string3 = bundle.getString("pf");
                String string4 = bundle.getString("pfkey");
                String string5 = bundle.getString("pay_token");
                this.f1581b.db.put("pf", string3);
                this.f1581b.db.put("pfkey", string4);
                this.f1581b.db.put("pay_token", string5);
                a.m2607b(string);
                a.m2609d(string2);
                this.f1581b.afterRegister(1, null);
            }

            public void onCancel() {
                if (this.f1581b.listener != null) {
                    this.f1581b.listener.onCancel(this.f1581b, 1);
                }
            }
        }, isSSODisable());
    }

    protected boolean checkAuthorize(int i, Object obj) {
        if (isAuthValid() || (i == 9 && obj != null && (obj instanceof cn.sharesdk.framework.Platform.ShareParams) && !((cn.sharesdk.framework.Platform.ShareParams) obj).isShareTencentWeibo())) {
            C0673b a = C0673b.m2597a((Platform) this);
            a.m2603a(this.f1584a);
            a.m2607b(this.db.getUserId());
            a.m2609d(this.db.getToken());
            return true;
        }
        innerAuthorize(i, obj);
        return false;
    }

    protected void doShare(final cn.sharesdk.framework.Platform.ShareParams shareParams) {
        Object title = shareParams.getTitle();
        String text = shareParams.getText();
        Object imagePath = shareParams.getImagePath();
        Object imageUrl = shareParams.getImageUrl();
        Object musicUrl = shareParams.getMusicUrl();
        String titleUrl = shareParams.getTitleUrl();
        boolean isShareTencentWeibo = shareParams.isShareTencentWeibo();
        int hidden = shareParams.getHidden();
        if (!TextUtils.isEmpty(title) || !TextUtils.isEmpty(text) || !TextUtils.isEmpty(imagePath) || !TextUtils.isEmpty(imageUrl) || !TextUtils.isEmpty(musicUrl)) {
            C0673b a = C0673b.m2597a((Platform) this);
            PlatformActionListener c06692 = new PlatformActionListener(this) {
                /* renamed from: b */
                final /* synthetic */ QQ f1583b;

                public void onError(Platform platform, int i, Throwable th) {
                    if (this.f1583b.listener != null) {
                        this.f1583b.listener.onError(this.f1583b, 9, th);
                    }
                }

                public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                    HashMap hashMap2 = new HashMap();
                    if (hashMap != null) {
                        hashMap2.putAll(hashMap);
                    }
                    hashMap2.put("ShareParams", shareParams);
                    if (this.f1583b.listener != null) {
                        this.f1583b.listener.onComplete(this.f1583b, 9, hashMap2);
                    }
                }

                public void onCancel(Platform platform, int i) {
                    if (this.f1583b.listener != null) {
                        this.f1583b.listener.onCancel(this.f1583b, 9);
                    }
                }
            };
            if (!TextUtils.isEmpty(titleUrl)) {
                titleUrl = getShortLintk(titleUrl, false);
            }
            if (!TextUtils.isEmpty(text)) {
                text = getShortLintk(text, false);
            }
            a.m2604a(title, titleUrl, text, imagePath, imageUrl, musicUrl, this.f1585b, c06692, isShareTencentWeibo, hidden);
        } else if (this.listener != null) {
            this.listener.onError(this, 9, new Throwable("qq share must have one param at least"));
        }
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
        if (str == null || str.length() < 0) {
            str = this.db.getUserId();
        }
        if (str != null && str.length() >= 0) {
            try {
                HashMap e = C0673b.m2597a((Platform) this).m2610e(str);
                if (e == null || e.size() <= 0) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable());
                    }
                } else if (e.containsKey("ret")) {
                    if (((Integer) e.get("ret")).intValue() == 0) {
                        if (str == this.db.getUserId()) {
                            this.db.put("nickname", String.valueOf(e.get("nickname")));
                            if (e.containsKey("figureurl_qq_1")) {
                                this.db.put("icon", String.valueOf(e.get("figureurl_qq_1")));
                            } else if (e.containsKey("figureurl_qq_2")) {
                                this.db.put("icon", String.valueOf(e.get("figureurl_qq_2")));
                            }
                            if (e.containsKey("figureurl_2")) {
                                this.db.put("iconQzone", String.valueOf(e.get("figureurl_2")));
                            } else if (e.containsKey("figureurl_1")) {
                                this.db.put("iconQzone", String.valueOf(e.get("figureurl_1")));
                            } else if (e.containsKey("figureurl")) {
                                this.db.put("iconQzone", String.valueOf(e.get("figureurl")));
                            }
                            this.db.put("secretType", String.valueOf(e.get("is_yellow_vip")));
                            if (String.valueOf(e.get("is_yellow_vip")).equals(C0844a.f2048d)) {
                                this.db.put("snsUserLevel", String.valueOf(e.get("level")));
                            }
                            String valueOf = String.valueOf(e.get(UserData.GENDER_KEY));
                            if (valueOf.equals("男")) {
                                this.db.put(UserData.GENDER_KEY, "0");
                            } else if (valueOf.equals("女")) {
                                this.db.put(UserData.GENDER_KEY, C0844a.f2048d);
                            } else {
                                this.db.put(UserData.GENDER_KEY, "2");
                            }
                        }
                        if (this.listener != null) {
                            this.listener.onComplete(this, 8, e);
                        }
                    } else if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(e)));
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 8, new Throwable());
                }
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(this, 8, th);
                }
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new RuntimeException("qq account is null"));
        }
    }

    protected void getFriendList(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 2);
        }
    }

    protected void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        if (this.listener != null) {
            this.listener.onCancel(this, i);
        }
    }

    protected C0596a filterShareContent(cn.sharesdk.framework.Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        C0596a c0596a = new C0596a();
        String titleUrl = shareParams.getTitleUrl();
        c0596a.f1302c.add(titleUrl);
        c0596a.f1300a = this.f1584a;
        Object text = shareParams.getText();
        if (!TextUtils.isEmpty(text)) {
            c0596a.f1301b = text;
        }
        CharSequence imageUrl = shareParams.getImageUrl();
        CharSequence imagePath = shareParams.getImagePath();
        if (!TextUtils.isEmpty(imagePath)) {
            c0596a.f1304e.add(imagePath);
        } else if (!TextUtils.isEmpty(imageUrl)) {
            c0596a.f1303d.add(imageUrl);
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put(WebActivity.EXTRA_TITLE, shareParams.getTitle());
        hashMap2.put("url", titleUrl);
        hashMap2.put("imageLocalUrl", imagePath);
        hashMap2.put("summary", text);
        hashMap2.put("appName", DeviceHelper.getInstance(this.context).getAppName());
        c0596a.f1306g = hashMap2;
        return c0596a;
    }

    protected String uploadImageToFileServer(String str) {
        return super.uploadImageToFileServer(str);
    }

    protected HashMap<String, Object> getFollowings(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> getFollowers(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        return null;
    }

    public boolean hasShareCallback() {
        return this.f1586c;
    }
}
