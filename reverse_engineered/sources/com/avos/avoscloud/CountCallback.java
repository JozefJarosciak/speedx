package com.avos.avoscloud;

public abstract class CountCallback extends AVCallback<Integer> {
    public abstract void done(int i, AVException aVException);

    protected final void internalDone0(Integer num, AVException aVException) {
        done(num.intValue(), aVException);
    }
}
