package com.avos.avoscloud;

public abstract class RequestEmailVerifyCallback extends AVCallback<Void> {
    public abstract void done(AVException aVException);

    protected void internalDone0(Void voidR, AVException aVException) {
        done(aVException);
    }
}
