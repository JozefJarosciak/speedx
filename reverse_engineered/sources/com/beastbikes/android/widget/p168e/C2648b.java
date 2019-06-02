package com.beastbikes.android.widget.p168e;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.net.Uri;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.utils.C2553b;
import com.beastbikes.android.widget.p168e.p169a.C2641b;
import com.facebook.share.model.SharePhoto.C3125a;
import com.facebook.share.model.SharePhotoContent.C3127a;
import com.facebook.share.widget.ShareDialog;
import com.twitter.sdk.android.tweetcomposer.m$a;
import java.io.File;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: CommonShareImage */
/* renamed from: com.beastbikes.android.widget.e.b */
public class C2648b {
    /* renamed from: a */
    private String f12368a;
    /* renamed from: b */
    private Activity f12369b;
    /* renamed from: c */
    private Logger f12370c = LoggerFactory.getLogger(C2648b.class);

    /* compiled from: CommonShareImage */
    /* renamed from: com.beastbikes.android.widget.e.b$1 */
    class C26441 implements PlatformActionListener {
        /* renamed from: a */
        final /* synthetic */ C2648b f12364a;

        C26441(C2648b c2648b) {
            this.f12364a = c2648b;
        }

        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            this.f12364a.f12370c.info("wechatBuildShare onComplete");
        }

        public void onError(Platform platform, int i, Throwable th) {
            this.f12364a.f12370c.error("wechatBuildShare onError:" + th.getMessage());
        }

        public void onCancel(Platform platform, int i) {
            this.f12364a.f12370c.error("wechatBuildShare onCancel");
        }
    }

    /* compiled from: CommonShareImage */
    /* renamed from: com.beastbikes.android.widget.e.b$2 */
    class C26452 implements PlatformActionListener {
        /* renamed from: a */
        final /* synthetic */ C2648b f12365a;

        C26452(C2648b c2648b) {
            this.f12365a = c2648b;
        }

        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            this.f12365a.f12370c.info("WechatMomentsBuildShare onComplete");
        }

        public void onError(Platform platform, int i, Throwable th) {
            this.f12365a.f12370c.error("WechatMomentsBuildShare onError:" + th.getMessage());
        }

        public void onCancel(Platform platform, int i) {
            this.f12365a.f12370c.error("WechatMomentsBuildShare onCancel");
        }
    }

    /* compiled from: CommonShareImage */
    /* renamed from: com.beastbikes.android.widget.e.b$3 */
    class C26463 implements PlatformActionListener {
        /* renamed from: a */
        final /* synthetic */ C2648b f12366a;

        C26463(C2648b c2648b) {
            this.f12366a = c2648b;
        }

        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            this.f12366a.f12370c.info("QQBuildShare onComplete");
        }

        public void onError(Platform platform, int i, Throwable th) {
            this.f12366a.f12370c.error("QQBuildShare onError:" + th.getMessage());
        }

        public void onCancel(Platform platform, int i) {
            this.f12366a.f12370c.error("QQBuildShare onCancel");
        }
    }

    /* compiled from: CommonShareImage */
    /* renamed from: com.beastbikes.android.widget.e.b$4 */
    class C26474 implements PlatformActionListener {
        /* renamed from: a */
        final /* synthetic */ C2648b f12367a;

        C26474(C2648b c2648b) {
            this.f12367a = c2648b;
        }

        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            this.f12367a.f12370c.info("WeiboBuildShare", "onComplete");
        }

        public void onError(Platform platform, int i, Throwable th) {
            this.f12367a.f12370c.error("WeiboBuildShare", "onError");
        }

        public void onCancel(Platform platform, int i) {
            this.f12367a.f12370c.info("WeiboBuildShare", "onCancel");
        }
    }

    public C2648b(Activity activity, C2641b c2641b) {
        this.f12368a = c2641b.m13155a();
        this.f12369b = activity;
    }

    /* renamed from: a */
    public void m13175a() {
        ShareParams shareParams = new Wechat.ShareParams();
        shareParams.setImagePath(this.f12368a);
        shareParams.setShareType(2);
        Platform platform = ShareSDK.getPlatform(Wechat.NAME);
        platform.setPlatformActionListener(new C26441(this));
        platform.share(shareParams);
    }

    /* renamed from: b */
    public void m13176b() {
        ShareParams shareParams = new WechatMoments.ShareParams();
        shareParams.setImagePath(this.f12368a);
        shareParams.setShareType(2);
        Platform platform = ShareSDK.getPlatform(WechatMoments.NAME);
        platform.setPlatformActionListener(new C26452(this));
        platform.share(shareParams);
    }

    /* renamed from: c */
    public void m13177c() {
        ShareParams shareParams = new QQ.ShareParams();
        shareParams.setImagePath(this.f12368a);
        shareParams.setShareType(2);
        Platform platform = ShareSDK.getPlatform(QQ.NAME);
        platform.setPlatformActionListener(new C26463(this));
        platform.share(shareParams);
    }

    /* renamed from: d */
    public void m13178d() {
        ShareParams shareParams = new SinaWeibo.ShareParams();
        shareParams.setText(this.f12369b.getResources().getString(C1373R.string.weibo_topic) + " " + this.f12369b.getResources().getString(C1373R.string.weibo_offical));
        shareParams.setImagePath(this.f12368a);
        Platform platform = ShareSDK.getPlatform(SinaWeibo.NAME);
        platform.setPlatformActionListener(new C26474(this));
        platform.share(shareParams);
    }

    /* renamed from: e */
    public void m13179e() {
        if (C2656e.m13193a(this.f12369b).booleanValue()) {
            try {
                ShareDialog.m15248a(this.f12369b, new C3127a().m15210a(new C3125a().m15194a(BitmapFactory.decodeFile(this.f12368a)).m15201c()).m15211a());
            } catch (Exception e) {
                this.f12370c.error(e.getMessage());
            }
        }
    }

    /* renamed from: f */
    public void m13180f() {
        new m$a(this.f12369b).a(Uri.fromFile(new File(this.f12368a))).d();
    }

    /* renamed from: g */
    public void m13181g() {
        C2553b.m12784a(this.f12368a);
    }
}
