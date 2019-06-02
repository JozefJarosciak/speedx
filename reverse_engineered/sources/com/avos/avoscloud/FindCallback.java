package com.avos.avoscloud;

import java.util.List;

public abstract class FindCallback<T extends AVObject> extends AVCallback<List<T>> {
    public abstract void done(List<T> list, AVException aVException);

    protected final void internalDone0(List<T> list, AVException aVException) {
        done(list, aVException);
    }
}
