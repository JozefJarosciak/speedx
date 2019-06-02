package com.avos.avoscloud;

import android.os.Looper;
import com.avos.avoscloud.AVQuery.CachePolicy;
import com.avos.avoscloud.LogUtil.avlog;
import com.avos.avoscloud.LogUtil.log;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.apache.http.Header;
import org.apache.http.HttpStatus;

public class GetHttpResponseHandler extends AsyncHttpResponseHandler {
    private String absoluteURLString;
    private GenericObjectCallback callback;
    private CachePolicy policy;

    /* renamed from: com.avos.avoscloud.GetHttpResponseHandler$1 */
    class C09761 extends GenericObjectCallback {
        C09761() {
        }

        public void onSuccess(String str, AVException aVException) {
            GetHttpResponseHandler.this.getCallback().onSuccess(str, null);
        }

        public void onFailure(Throwable th, String str) {
            PaasClient.removeLastModifyForUrl(GetHttpResponseHandler.this.absoluteURLString);
            GetHttpResponseHandler.this.getCallback().onFailure(th, str);
        }
    }

    public GetHttpResponseHandler(GenericObjectCallback genericObjectCallback) {
        super(Looper.getMainLooper());
        this.policy = CachePolicy.IGNORE_CACHE;
        this.callback = genericObjectCallback;
    }

    public GetHttpResponseHandler(GenericObjectCallback genericObjectCallback, CachePolicy cachePolicy, String str) {
        this(genericObjectCallback);
        this.policy = cachePolicy;
        this.absoluteURLString = str;
    }

    void setCallback(GenericObjectCallback genericObjectCallback) {
        this.callback = genericObjectCallback;
    }

    GenericObjectCallback getCallback() {
        return this.callback;
    }

    private boolean isNotModifiedStatus(int i) {
        return i == HttpStatus.SC_NOT_MODIFIED;
    }

    private boolean isUnAuthorize(int i) {
        return i == HttpStatus.SC_UNAUTHORIZED;
    }

    private void tryLastModifyCache(String str) {
        if (getCallback() != null) {
            if (str == null) {
                log.m3514d("null last-modified value");
            } else if (PaasClient.getLastModify(this.absoluteURLString) == null) {
                PaasClient.updateLastModify(this.absoluteURLString, str);
            }
            AVCacheManager.sharedInstance().get(this.absoluteURLString, Long.MAX_VALUE, str, new C09761());
        }
    }

    public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        String stringFromBytes = AVUtils.stringFromBytes(bArr);
        if (AVOSCloud.isDebugLogEnabled()) {
            avlog.m3506d(stringFromBytes);
        }
        if (!AVUtils.checkResponseType(i, stringFromBytes, PaasClient.extractContentType(headerArr), getCallback())) {
            int errorCode = AVErrorUtils.errorCode(stringFromBytes);
            if (errorCode <= 0) {
                if (!(this.policy == CachePolicy.IGNORE_CACHE || AVUtils.isBlankString(this.absoluteURLString))) {
                    AVCacheManager.sharedInstance().save(this.absoluteURLString, stringFromBytes, null);
                }
                if (!AVUtils.isBlankString(this.absoluteURLString) && PaasClient.isLastModifyEnabled()) {
                    String lastModifyFromHeaders = PaasClient.lastModifyFromHeaders(headerArr);
                    if (AVCacheManager.sharedInstance().save(this.absoluteURLString, stringFromBytes, lastModifyFromHeaders)) {
                        PaasClient.updateLastModify(this.absoluteURLString, lastModifyFromHeaders);
                    }
                }
                if (getCallback() != null) {
                    getCallback().onSuccess(stringFromBytes, null);
                }
                ArchiveRequestTaskController.schedule();
            } else if (getCallback() != null) {
                getCallback().onFailure(AVErrorUtils.createException(errorCode, stringFromBytes), stringFromBytes);
            }
        }
    }

    public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        String stringFromBytes = AVUtils.stringFromBytes(bArr);
        if (isNotModifiedStatus(i)) {
            if (AVOSCloud.showInternalDebugLog()) {
                avlog.m3509i("Last modify matched.");
            }
            tryLastModifyCache(PaasClient.lastModifyFromHeaders(headerArr));
            return;
        }
        if (isUnAuthorize(i)) {
            avlog.m3507e(stringFromBytes + "\nerror:" + th + " for request:" + this.absoluteURLString);
        }
        if (AVOSCloud.isDebugLogEnabled()) {
            avlog.m3507e(stringFromBytes + "\nerror:" + th);
        }
        if (!AVUtils.checkResponseType(i, stringFromBytes, PaasClient.extractContentType(headerArr), getCallback()) && getCallback() != null) {
            getCallback().onFailure(i, th, stringFromBytes);
        }
    }
}
