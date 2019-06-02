package com.avos.avoscloud;

import java.util.Map;

public abstract class FollowersAndFolloweesCallback<T extends AVObject> extends AVCallback<Map<String, T>> {
    public abstract void done(Map<String, T> map, AVException aVException);

    protected final void internalDone0(Map<String, T> map, AVException aVException) {
        done(map, aVException);
    }
}
