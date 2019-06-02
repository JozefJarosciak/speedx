package com.avos.avoscloud;

import com.avos.avoscloud.AVQuery.CachePolicy;
import com.loopj.android.http.RequestParams;
import java.util.Map;

class DataFetchCallbackWithFailureRetry<T extends AVObject> extends GenericRetryCallback {
    Map<String, String> inputHeader;
    RequestParams parameters;
    CachePolicy policy;
    String relativePath;
    boolean retry = true;
    boolean sync;

    public DataFetchCallbackWithFailureRetry(String str, RequestParams requestParams, boolean z, Map<String, String> map, GenericObjectCallback genericObjectCallback, CachePolicy cachePolicy) {
        super(genericObjectCallback);
        this.relativePath = str;
        this.parameters = requestParams;
        this.sync = z;
        this.inputHeader = map;
        this.callback = genericObjectCallback;
        this.policy = cachePolicy;
    }

    public boolean isRetryNeeded(int i, Throwable th) {
        return this.retry && th != null && isLastModifyCacheMissingError(th.getMessage());
    }

    public void retry(Throwable th, String str) {
        PaasClient.storageInstance().getObject(this.relativePath, this.parameters, this.sync, this.inputHeader, this.callback, this.policy, false, false);
    }

    public void onFailure(Throwable th, String str) {
        if (this.retry && th != null && isLastModifyCacheMissingError(th.getMessage())) {
            this.retry = false;
            PaasClient.storageInstance().getObject(this.relativePath, this.parameters, this.sync, this.inputHeader, this.callback, this.policy, false);
        } else if (this.callback != null) {
            this.callback.onFailure(th, str);
        }
    }

    private boolean isLastModifyCacheMissingError(String str) {
        return AVException.cacheMissingErrorString.equalsIgnoreCase(str);
    }
}
