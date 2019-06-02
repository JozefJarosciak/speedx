package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.C0621d;
import cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject;

public class WXVideoObject implements IMediaObject {
    public String videoLowBandUrl;
    public String videoUrl;

    public void serialize(Bundle bundle) {
        bundle.putString("_wxvideoobject_videoUrl", this.videoUrl);
        bundle.putString("_wxvideoobject_videoLowBandUrl", this.videoLowBandUrl);
    }

    public void unserialize(Bundle bundle) {
        this.videoUrl = bundle.getString("_wxvideoobject_videoUrl");
        this.videoLowBandUrl = bundle.getString("_wxvideoobject_videoLowBandUrl");
    }

    public int type() {
        return 4;
    }

    public boolean checkArgs() {
        if ((this.videoUrl == null || this.videoUrl.length() == 0) && (this.videoLowBandUrl == null || this.videoLowBandUrl.length() == 0)) {
            C0621d.m2279a().d("both arguments are null", new Object[0]);
            return false;
        } else if (this.videoUrl != null && this.videoUrl.length() > 10240) {
            C0621d.m2279a().d("checkArgs fail, videoUrl is too long", new Object[0]);
            return false;
        } else if (this.videoLowBandUrl == null || this.videoLowBandUrl.length() <= 10240) {
            return true;
        } else {
            C0621d.m2279a().d("checkArgs fail, videoLowBandUrl is too long", new Object[0]);
            return false;
        }
    }
}
