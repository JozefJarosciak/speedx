package cn.sharesdk.wechat.favorite;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.p011b.p013b.C0597f.C0596a;
import cn.sharesdk.framework.utils.C0621d;
import cn.sharesdk.wechat.utils.C0701g;
import cn.sharesdk.wechat.utils.C0703i;
import cn.sharesdk.wechat.utils.WechatClientNotExistException;
import cn.sharesdk.wechat.utils.WechatHelper;
import cn.sharesdk.wechat.utils.WechatTimelineNotSupportedException;
import com.alipay.sdk.packet.C0861d;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.framework.ui.android.WebActivity;
import java.util.HashMap;

public class WechatFavorite extends Platform {
    public static final String NAME = WechatFavorite.class.getSimpleName();
    /* renamed from: a */
    private String f1639a;
    /* renamed from: b */
    private String f1640b;

    /* renamed from: cn.sharesdk.wechat.favorite.WechatFavorite$1 */
    class C06871 implements AuthorizeListener {
        /* renamed from: a */
        final /* synthetic */ WechatFavorite f1638a;

        C06871(WechatFavorite wechatFavorite) {
            this.f1638a = wechatFavorite;
        }

        public void onError(Throwable th) {
            if (this.f1638a.listener != null) {
                this.f1638a.listener.onError(this.f1638a, 1, th);
            }
        }

        public void onComplete(Bundle bundle) {
            this.f1638a.afterRegister(1, null);
        }

        public void onCancel() {
            if (this.f1638a.listener != null) {
                this.f1638a.listener.onCancel(this.f1638a, 1);
            }
        }
    }

    public static class ShareParams extends cn.sharesdk.wechat.utils.WechatHelper.ShareParams {
        public ShareParams() {
            this.scene = 2;
        }
    }

    public WechatFavorite(Context context) {
        super(context);
    }

    protected void initDevInfo(String str) {
        this.f1639a = getDevinfo(C0861d.f2134f);
        this.f1640b = getDevinfo("AppSecret");
        if (this.f1639a == null || this.f1639a.length() <= 0) {
            this.f1639a = getDevinfo("Wechat", C0861d.f2134f);
            if (this.f1639a == null || this.f1639a.length() <= 0) {
                this.f1639a = getDevinfo("WechatMoments", C0861d.f2134f);
                if (this.f1639a != null && this.f1639a.length() > 0) {
                    copyDevinfo("WechatMoments", NAME);
                    this.f1639a = getDevinfo(C0861d.f2134f);
                    C0621d.m2279a().d("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
                    return;
                }
                return;
            }
            copyDevinfo("Wechat", NAME);
            this.f1639a = getDevinfo(C0861d.f2134f);
            C0621d.m2279a().d("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    public int getPlatformId() {
        return 37;
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 1;
    }

    protected void setNetworkDevinfo() {
        this.f1639a = getNetworkDevinfo("app_id", C0861d.f2134f);
        this.f1640b = getNetworkDevinfo("app_secret", "AppSecret");
        if (this.f1639a == null || this.f1639a.length() <= 0) {
            this.f1639a = getNetworkDevinfo(22, "app_id", C0861d.f2134f);
            if (this.f1639a == null || this.f1639a.length() <= 0) {
                this.f1639a = getNetworkDevinfo(23, "app_id", C0861d.f2134f);
                if (this.f1639a != null && this.f1639a.length() > 0) {
                    copyNetworkDevinfo(23, 37);
                    this.f1639a = getNetworkDevinfo("app_id", C0861d.f2134f);
                    C0621d.m2279a().d("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
                }
            } else {
                copyNetworkDevinfo(22, 37);
                this.f1639a = getNetworkDevinfo("app_id", C0861d.f2134f);
                C0621d.m2279a().d("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
            }
        }
        if (this.f1640b == null || this.f1640b.length() <= 0) {
            this.f1640b = getNetworkDevinfo(22, "app_secret", "AppSecret");
            if (this.f1640b == null || this.f1640b.length() <= 0) {
                this.f1640b = getNetworkDevinfo(23, "app_secret", "AppSecret");
                if (this.f1640b != null && this.f1640b.length() > 0) {
                    copyNetworkDevinfo(23, 37);
                    this.f1640b = getNetworkDevinfo("app_secret", "AppSecret");
                    C0621d.m2279a().d("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
                    return;
                }
                return;
            }
            copyNetworkDevinfo(22, 37);
            this.f1640b = getNetworkDevinfo("app_secret", "AppSecret");
            C0621d.m2279a().d("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    protected void doAuthorize(String[] strArr) {
        if (!TextUtils.isEmpty(this.f1639a) && !TextUtils.isEmpty(this.f1640b)) {
            WechatHelper a = WechatHelper.m2681a();
            a.m2704a(this.context, this.f1639a);
            if (a.m2708c()) {
                if (a.m2709d()) {
                    C0701g c0701g = new C0701g(this, 37);
                    c0701g.m2742a(this.f1639a, this.f1640b);
                    C0703i c0703i = new C0703i(this);
                    c0703i.m2758a(c0701g);
                    c0703i.m2756a(new C06871(this));
                    try {
                        a.m2702a(c0703i);
                    } catch (Throwable th) {
                        if (this.listener != null) {
                            this.listener.onError(this, 1, th);
                        }
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 1, new WechatTimelineNotSupportedException());
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 1, new WechatClientNotExistException());
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
        }
    }

    /* renamed from: c */
    private boolean m2664c() {
        if (TextUtils.isEmpty(getDb().get("refresh_token"))) {
            return false;
        }
        C0701g c0701g = new C0701g(this, 37);
        c0701g.m2742a(this.f1639a, this.f1640b);
        return c0701g.m2743a();
    }

    protected boolean checkAuthorize(int i, Object obj) {
        WechatHelper a = WechatHelper.m2681a();
        a.m2704a(this.context, this.f1639a);
        if (a.m2708c()) {
            if (a.m2709d()) {
                if (i == 9 || isAuthValid() || m2664c()) {
                    return true;
                }
                innerAuthorize(i, obj);
                return false;
            } else if (this.listener == null) {
                return false;
            } else {
                this.listener.onError(this, 1, new WechatTimelineNotSupportedException());
                return false;
            }
        } else if (this.listener == null) {
            return false;
        } else {
            this.listener.onError(this, i, new WechatClientNotExistException());
            return false;
        }
    }

    @Deprecated
    public boolean isValid() {
        WechatHelper a = WechatHelper.m2681a();
        a.m2704a(this.context, this.f1639a);
        if (a.m2708c() && super.isValid()) {
            return true;
        }
        return false;
    }

    public boolean isClientValid() {
        WechatHelper a = WechatHelper.m2681a();
        a.m2704a(this.context, this.f1639a);
        if (a.m2708c()) {
            return true;
        }
        return false;
    }

    protected void doShare(cn.sharesdk.framework.Platform.ShareParams shareParams) {
        shareParams.set("scene", Integer.valueOf(2));
        WechatHelper a = WechatHelper.m2681a();
        a.m2704a(this.context, this.f1639a);
        C0703i c0703i = new C0703i(this);
        c0703i.m2755a(shareParams, this.listener);
        try {
            a.m2706b(c0703i);
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 9, th);
            }
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
        if (!TextUtils.isEmpty(this.f1639a) && !TextUtils.isEmpty(this.f1640b)) {
            C0701g c0701g = new C0701g(this, 37);
            c0701g.m2742a(this.f1639a, this.f1640b);
            try {
                c0701g.m2740a(this.listener);
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
                if (this.listener != null) {
                    this.listener.onError(this, 8, th);
                }
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
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
        String text = shareParams.getText();
        c0596a.f1301b = text;
        CharSequence imageUrl = shareParams.getImageUrl();
        String imagePath = shareParams.getImagePath();
        Bitmap imageData = shareParams.getImageData();
        if (!TextUtils.isEmpty(imageUrl)) {
            c0596a.f1303d.add(imageUrl);
        } else if (imagePath != null) {
            c0596a.f1304e.add(imagePath);
        } else if (imageData != null) {
            c0596a.f1305f.add(imageData);
        }
        String url = shareParams.getUrl();
        if (url != null) {
            c0596a.f1302c.add(url);
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put(WebActivity.EXTRA_TITLE, shareParams.getTitle());
        hashMap2.put("url", url);
        hashMap2.put("extInfo", null);
        hashMap2.put("content", text);
        hashMap2.put(AVStatus.IMAGE_TAG, c0596a.f1303d);
        hashMap2.put("musicFileUrl", url);
        c0596a.f1306g = hashMap2;
        return c0596a;
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
        return false;
    }
}
