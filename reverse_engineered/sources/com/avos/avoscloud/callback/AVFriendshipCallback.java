package com.avos.avoscloud.callback;

import com.avos.avoscloud.AVCallback;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFriendship;

public abstract class AVFriendshipCallback extends AVCallback<AVFriendship> {
    public abstract void done(AVFriendship aVFriendship, AVException aVException);

    protected final void internalDone0(AVFriendship aVFriendship, AVException aVException) {
        done(aVFriendship, aVException);
    }
}
