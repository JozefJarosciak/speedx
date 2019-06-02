package com.loopj.android.http;

import java.lang.ref.WeakReference;

public class RequestHandle {
    private final WeakReference<AsyncHttpRequest> request;

    public RequestHandle(AsyncHttpRequest asyncHttpRequest) {
        this.request = new WeakReference(asyncHttpRequest);
    }

    public boolean cancel(boolean z) {
        AsyncHttpRequest asyncHttpRequest = (AsyncHttpRequest) this.request.get();
        return asyncHttpRequest == null || asyncHttpRequest.cancel(z);
    }

    public boolean isFinished() {
        AsyncHttpRequest asyncHttpRequest = (AsyncHttpRequest) this.request.get();
        return asyncHttpRequest == null || asyncHttpRequest.isDone();
    }

    public boolean isCancelled() {
        AsyncHttpRequest asyncHttpRequest = (AsyncHttpRequest) this.request.get();
        return asyncHttpRequest == null || asyncHttpRequest.isCancelled();
    }

    public boolean shouldBeGarbageCollected() {
        boolean z = isCancelled() || isFinished();
        if (z) {
            this.request.clear();
        }
        return z;
    }
}
