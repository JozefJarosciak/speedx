package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;

/* compiled from: WechatHandler */
/* renamed from: cn.sharesdk.wechat.utils.i */
public class C0703i {
    /* renamed from: a */
    private Platform f1687a;
    /* renamed from: b */
    private ShareParams f1688b;
    /* renamed from: c */
    private PlatformActionListener f1689c;
    /* renamed from: d */
    private AuthorizeListener f1690d;
    /* renamed from: e */
    private C0701g f1691e;

    public C0703i(Platform platform) {
        this.f1687a = platform;
    }

    /* renamed from: a */
    public void m2756a(AuthorizeListener authorizeListener) {
        this.f1690d = authorizeListener;
    }

    /* renamed from: a */
    public void m2755a(ShareParams shareParams, PlatformActionListener platformActionListener) {
        this.f1688b = shareParams;
        this.f1689c = platformActionListener;
    }

    /* renamed from: a */
    public void m2758a(C0701g c0701g) {
        this.f1691e = c0701g;
    }

    /* renamed from: a */
    public void m2757a(WechatResp wechatResp) {
        HashMap hashMap;
        Throwable th;
        switch (wechatResp.f1660f) {
            case -4:
                hashMap = new HashMap();
                hashMap.put("errCode", Integer.valueOf(wechatResp.f1660f));
                hashMap.put("errStr", wechatResp.f1661g);
                hashMap.put("transaction", wechatResp.f1662h);
                th = new Throwable(new Hashon().fromHashMap(hashMap));
                switch (wechatResp.mo2305a()) {
                    case 1:
                        if (this.f1690d != null) {
                            this.f1690d.onError(th);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            case -3:
                hashMap = new HashMap();
                hashMap.put("errCode", Integer.valueOf(wechatResp.f1660f));
                hashMap.put("errStr", wechatResp.f1661g);
                hashMap.put("transaction", wechatResp.f1662h);
                th = new Throwable(new Hashon().fromHashMap(hashMap));
                switch (wechatResp.mo2305a()) {
                    case 1:
                        if (this.f1690d != null) {
                            this.f1690d.onError(th);
                            return;
                        }
                        return;
                    case 2:
                        if (this.f1689c != null) {
                            this.f1689c.onError(this.f1687a, 9, th);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            case -2:
                switch (wechatResp.mo2305a()) {
                    case 1:
                        if (this.f1690d != null) {
                            this.f1690d.onCancel();
                            return;
                        }
                        return;
                    case 2:
                        if (this.f1689c != null) {
                            this.f1689c.onCancel(this.f1687a, 9);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            case 0:
                switch (wechatResp.mo2305a()) {
                    case 1:
                        if (this.f1690d != null) {
                            Bundle bundle = new Bundle();
                            wechatResp.mo2307b(bundle);
                            this.f1691e.m2739a(bundle, this.f1690d);
                            return;
                        }
                        return;
                    case 2:
                        if (this.f1689c != null) {
                            hashMap = new HashMap();
                            hashMap.put("ShareParams", this.f1688b);
                            this.f1689c.onComplete(this.f1687a, 9, hashMap);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            default:
                hashMap = new HashMap();
                hashMap.put("req", wechatResp.getClass().getSimpleName());
                hashMap.put("errCode", Integer.valueOf(wechatResp.f1660f));
                hashMap.put("errStr", wechatResp.f1661g);
                hashMap.put("transaction", wechatResp.f1662h);
                new Throwable(new Hashon().fromHashMap(hashMap)).printStackTrace();
                return;
        }
    }

    /* renamed from: a */
    public ShareParams m2754a() {
        return this.f1688b;
    }

    /* renamed from: b */
    public Platform m2759b() {
        return this.f1687a;
    }

    /* renamed from: c */
    public PlatformActionListener m2760c() {
        return this.f1689c;
    }
}
