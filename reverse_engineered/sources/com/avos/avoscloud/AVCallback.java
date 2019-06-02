package com.avos.avoscloud;

import com.avos.avoscloud.LogUtil.log;

public abstract class AVCallback<T> {
    protected abstract void internalDone0(T t, AVException aVException);

    public void internalDone(final T t, final AVException aVException) {
        if (!mustRunOnUIThread() || AVUtils.isMainThread()) {
            internalDone0(t, aVException);
        } else if (!AVOSCloud.handler.post(new Runnable() {
            public void run() {
                AVCallback.this.internalDone0(t, aVException);
            }
        })) {
            log.m3519e("Post runnable to handler failed.");
        }
    }

    protected boolean mustRunOnUIThread() {
        return true;
    }

    public void internalDone(AVException aVException) {
        internalDone(null, aVException);
    }
}
