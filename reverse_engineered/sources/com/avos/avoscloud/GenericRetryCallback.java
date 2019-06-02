package com.avos.avoscloud;

abstract class GenericRetryCallback extends GenericObjectCallback {
    GenericObjectCallback callback;

    public GenericRetryCallback(GenericObjectCallback genericObjectCallback) {
        this.callback = genericObjectCallback;
    }

    public void onSuccess(String str, AVException aVException) {
        if (this.callback != null) {
            this.callback.onSuccess(str, aVException);
        }
    }

    public void onFailure(Throwable th, String str) {
        if (this.callback != null) {
            this.callback.onFailure(th, str);
        }
    }
}
