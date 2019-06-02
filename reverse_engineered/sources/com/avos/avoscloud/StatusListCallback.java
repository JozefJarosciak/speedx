package com.avos.avoscloud;

import java.util.List;

public abstract class StatusListCallback extends AVCallback<List<AVStatus>> {
    public abstract void done(List<AVStatus> list, AVException aVException);

    protected final void internalDone0(List<AVStatus> list, AVException aVException) {
        done(list, aVException);
    }
}
