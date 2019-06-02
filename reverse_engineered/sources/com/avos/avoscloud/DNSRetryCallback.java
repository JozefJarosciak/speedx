package com.avos.avoscloud;

import com.avos.avoscloud.AVNetworkHelper.DNSUpdateCallback;
import com.avos.avoscloud.LogUtil.avlog;
import java.net.URISyntaxException;

public abstract class DNSRetryCallback extends GenericRetryCallback {
    String url;

    public abstract void executeRequest();

    public /* bridge */ /* synthetic */ void onFailure(Throwable th, String str) {
        super.onFailure(th, str);
    }

    public /* bridge */ /* synthetic */ void onSuccess(String str, AVException aVException) {
        super.onSuccess(str, aVException);
    }

    public DNSRetryCallback(String str, GenericObjectCallback genericObjectCallback) {
        super(genericObjectCallback);
        this.url = str;
    }

    public boolean isRetryNeeded(int i, Throwable th) {
        return AVUtils.checkDNSException(i, th);
    }

    public void retry(final Throwable th, final String str) {
        try {
            AVNetworkHelper.amendDNS(AVUtils.getHostName(this.url), new DNSUpdateCallback() {
                public void done(AVException aVException) {
                    if (aVException == null) {
                        DNSRetryCallback.this.executeRequest();
                        return;
                    }
                    if (AVOSCloud.showInternalDebugLog()) {
                        avlog.m3507e(aVException.getMessage());
                    }
                    DNSRetryCallback.this.callback.onFailure(th, str);
                }
            });
        } catch (URISyntaxException e) {
            this.callback.onFailure(th, str);
        }
    }
}
