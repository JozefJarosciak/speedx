package com.avos.avoscloud;

public abstract class GetCallback<T extends AVObject> extends AVCallback<T> {
    public abstract void done(T t, AVException aVException);

    protected final void internalDone0(T t, AVException aVException) {
        done(t, aVException);
    }
}
