package com.beastbikes.android.widget.p168e;

import android.net.Uri;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import com.beastbikes.android.widget.p168e.p169a.C2642c;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent.C3117a;
import com.facebook.share.widget.ShareDialog;
import com.twitter.sdk.android.tweetcomposer.m$a;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: CommonShareLink */
/* renamed from: com.beastbikes.android.widget.e.c */
public class C2653c {
    /* renamed from: a */
    private BaseFragmentActivity f12375a;
    /* renamed from: b */
    private C2642c f12376b;
    /* renamed from: c */
    private Logger f12377c = LoggerFactory.getLogger(C2653c.class);

    /* compiled from: CommonShareLink */
    /* renamed from: com.beastbikes.android.widget.e.c$1 */
    class C26491 implements PlatformActionListener {
        /* renamed from: a */
        final /* synthetic */ C2653c f12371a;

        C26491(C2653c c2653c) {
            this.f12371a = c2653c;
        }

        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            this.f12371a.f12377c.info("WeiboBuildShare onComplete");
        }

        public void onError(Platform platform, int i, Throwable th) {
            this.f12371a.f12377c.error("WeiboBuildShare onError" + th.getMessage());
        }

        public void onCancel(Platform platform, int i) {
            this.f12371a.f12377c.error("WeiboBuildShare onCancel");
        }
    }

    /* compiled from: CommonShareLink */
    /* renamed from: com.beastbikes.android.widget.e.c$2 */
    class C26502 implements PlatformActionListener {
        /* renamed from: a */
        final /* synthetic */ C2653c f12372a;

        C26502(C2653c c2653c) {
            this.f12372a = c2653c;
        }

        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            this.f12372a.f12377c.info("WeiboBuildShare onComplete");
        }

        public void onError(Platform platform, int i, Throwable th) {
            this.f12372a.f12377c.error("WeiboBuildShare onError" + th.getMessage());
        }

        public void onCancel(Platform platform, int i) {
            this.f12372a.f12377c.error("WeiboBuildShare onCancel");
        }
    }

    /* compiled from: CommonShareLink */
    /* renamed from: com.beastbikes.android.widget.e.c$3 */
    class C26513 implements PlatformActionListener {
        /* renamed from: a */
        final /* synthetic */ C2653c f12373a;

        C26513(C2653c c2653c) {
            this.f12373a = c2653c;
        }

        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            this.f12373a.f12377c.info("QQBuildShare onComplete");
        }

        public void onError(Platform platform, int i, Throwable th) {
            this.f12373a.f12377c.error("QQBuildShare onError" + th.getMessage());
        }

        public void onCancel(Platform platform, int i) {
            this.f12373a.f12377c.error("QQBuildShare onCancel");
        }
    }

    /* compiled from: CommonShareLink */
    /* renamed from: com.beastbikes.android.widget.e.c$4 */
    class C26524 implements PlatformActionListener {
        /* renamed from: a */
        final /* synthetic */ C2653c f12374a;

        C26524(C2653c c2653c) {
            this.f12374a = c2653c;
        }

        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            this.f12374a.f12377c.info("WeiboBuildShare onComplete");
        }

        public void onError(Platform platform, int i, Throwable th) {
            this.f12374a.f12377c.error("WeiboBuildShare onError" + th.getMessage());
        }

        public void onCancel(Platform platform, int i) {
            this.f12374a.f12377c.error("WeiboBuildShare onCancel");
        }
    }

    public C2653c(BaseFragmentActivity baseFragmentActivity, C2642c c2642c) {
        this.f12376b = c2642c;
        this.f12375a = baseFragmentActivity;
    }

    /* renamed from: a */
    public void m13183a() {
        ShareParams shareParams = new Wechat.ShareParams();
        shareParams.setText(this.f12376b.m13163d());
        shareParams.setImageUrl(this.f12376b.m13157a());
        shareParams.setUrl(this.f12376b.m13161c());
        shareParams.setTitle(this.f12376b.m13159b());
        shareParams.setComment(this.f12376b.m13163d());
        shareParams.setShareType(4);
        Platform platform = ShareSDK.getPlatform(Wechat.NAME);
        platform.setPlatformActionListener(new C26491(this));
        platform.share(shareParams);
    }

    /* renamed from: b */
    public void m13184b() {
        ShareParams shareParams = new WechatMoments.ShareParams();
        shareParams.setText(this.f12376b.m13163d());
        shareParams.setImageUrl(this.f12376b.m13157a());
        shareParams.setUrl(this.f12376b.m13161c());
        shareParams.setTitle(this.f12376b.m13159b());
        shareParams.setComment(this.f12376b.m13163d());
        shareParams.setShareType(4);
        Platform platform = ShareSDK.getPlatform(WechatMoments.NAME);
        platform.setPlatformActionListener(new C26502(this));
        platform.share(shareParams);
    }

    /* renamed from: c */
    public void m13185c() {
        ShareParams shareParams = new QQ.ShareParams();
        shareParams.setText(this.f12376b.m13163d());
        shareParams.setTitleUrl(this.f12376b.m13161c());
        shareParams.setSite("SpeedX");
        shareParams.setSiteUrl(this.f12376b.m13161c());
        shareParams.setImageUrl(this.f12376b.m13157a());
        shareParams.setUrl(this.f12376b.m13161c());
        shareParams.setTitle(this.f12376b.m13159b());
        shareParams.setComment(this.f12376b.m13163d());
        Platform platform = ShareSDK.getPlatform(QQ.NAME);
        platform.setPlatformActionListener(new C26513(this));
        platform.share(shareParams);
    }

    /* renamed from: d */
    public void m13186d() {
        ShareParams shareParams = new SinaWeibo.ShareParams();
        shareParams.setText(this.f12376b.m13163d() + "  " + this.f12376b.m13161c());
        shareParams.setImageUrl(this.f12376b.m13157a());
        shareParams.setUrl(this.f12376b.m13161c());
        shareParams.setTitle(this.f12376b.m13159b());
        shareParams.setComment(this.f12376b.m13163d());
        Platform platform = ShareSDK.getPlatform(SinaWeibo.NAME);
        platform.setPlatformActionListener(new C26524(this));
        platform.share(shareParams);
    }

    /* renamed from: e */
    public void m13187e() {
        ShareContent a = ((C3117a) new C3117a().m15155b(Uri.parse(this.f12376b.m13157a())).m15153a(this.f12376b.m13159b()).m15138a(Uri.parse(this.f12376b.m13161c()))).m15154a();
        if (this.f12375a != null) {
            ShareDialog.m15248a(this.f12375a, a);
        }
    }

    /* renamed from: f */
    public void m13188f() {
        try {
            new m$a(this.f12375a).a(this.f12376b.m13163d()).a(new URL(this.f12376b.m13161c())).d();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
