package com.avos.avoscloud;

import android.os.Looper;
import com.avos.avoscloud.LogUtil.avlog;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.apache.http.Header;

public class PostHttpResponseHandler extends AsyncHttpResponseHandler {
    private GenericObjectCallback callback;

    PostHttpResponseHandler(GenericObjectCallback genericObjectCallback) {
        super(Looper.getMainLooper());
        this.callback = genericObjectCallback;
    }

    void setCallback(GenericObjectCallback genericObjectCallback) {
        this.callback = genericObjectCallback;
    }

    GenericObjectCallback getCallback() {
        return this.callback;
    }

    public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        String stringFromBytes = AVUtils.stringFromBytes(bArr);
        if (AVOSCloud.isDebugLogEnabled()) {
            avlog.m3506d(stringFromBytes);
        }
        if (!AVUtils.checkResponseType(i, stringFromBytes, PaasClient.extractContentType(headerArr), getCallback())) {
            int errorCode = AVErrorUtils.errorCode(stringFromBytes);
            if (errorCode <= 0) {
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
        if (AVOSCloud.isDebugLogEnabled()) {
            avlog.m3507e(stringFromBytes + "\nerror:" + th);
        }
        if (!AVUtils.checkResponseType(i, stringFromBytes, PaasClient.extractContentType(headerArr), getCallback()) && getCallback() != null) {
            getCallback().onFailure(i, th, stringFromBytes);
        }
    }
}
