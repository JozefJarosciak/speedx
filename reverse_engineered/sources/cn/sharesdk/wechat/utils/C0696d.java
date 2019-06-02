package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.C0621d;
import cn.sharesdk.wechat.utils.WXMediaMessage.C0690a;

/* compiled from: SendMessageReq */
/* renamed from: cn.sharesdk.wechat.utils.d */
public class C0696d extends C0692j {
    /* renamed from: a */
    public WXMediaMessage f1672a;
    /* renamed from: b */
    public int f1673b;

    /* renamed from: a */
    public int mo2302a() {
        return 2;
    }

    /* renamed from: a */
    public void mo2303a(Bundle bundle) {
        super.mo2303a(bundle);
        bundle.putAll(C0690a.m2678a(this.f1672a));
        bundle.putInt("_wxapi_sendmessagetowx_req_scene", this.f1673b);
    }

    /* renamed from: b */
    public boolean mo2304b() {
        if (this.f1672a.getType() == 8 && (this.f1672a.thumbData == null || this.f1672a.thumbData.length <= 0)) {
            C0621d.m2279a().d("checkArgs fail, thumbData should not be null when send emoji", new Object[0]);
            return false;
        } else if (this.f1672a.thumbData != null && this.f1672a.thumbData.length > 32768) {
            C0621d.m2279a().d("checkArgs fail, thumbData is invalid", new Object[0]);
            return false;
        } else if (this.f1672a.title == null || this.f1672a.title.length() <= 512) {
            if (this.f1672a.description != null && this.f1672a.description.length() > 1024) {
                this.f1672a.description = this.f1672a.description.substring(0, 1021) + "...";
            }
            if (this.f1672a.mediaObject != null) {
                return this.f1672a.mediaObject.checkArgs();
            }
            C0621d.m2279a().d("checkArgs fail, mediaObject is null", new Object[0]);
            return false;
        } else {
            C0621d.m2279a().d("checkArgs fail, title is invalid", new Object[0]);
            return false;
        }
    }
}
