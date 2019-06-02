package com.avos.avoscloud;

public abstract class RequestMobileCodeCallback extends AVCallback<Void> {
    public abstract void done(AVException aVException);

    protected final void internalDone0(Void voidR, AVException aVException) {
        done(aVException);
    }
}
