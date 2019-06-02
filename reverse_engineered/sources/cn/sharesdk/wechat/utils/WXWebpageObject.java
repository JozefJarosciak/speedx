package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.C0621d;
import cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject;

public class WXWebpageObject implements IMediaObject {
    public String webpageUrl;

    public WXWebpageObject(String str) {
        this.webpageUrl = str;
    }

    public void serialize(Bundle bundle) {
        bundle.putString("_wxwebpageobject_webpageUrl", this.webpageUrl);
    }

    public void unserialize(Bundle bundle) {
        this.webpageUrl = bundle.getString("_wxwebpageobject_webpageUrl");
    }

    public int type() {
        return 5;
    }

    public boolean checkArgs() {
        if (this.webpageUrl != null && this.webpageUrl.length() != 0 && this.webpageUrl.length() <= 10240) {
            return true;
        }
        C0621d.m2279a().d("checkArgs fail, webpageUrl is invalid", new Object[0]);
        return false;
    }
}
