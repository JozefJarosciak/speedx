package com.avos.avoscloud;

public abstract class CloudQueryCallback<T extends AVCloudQueryResult> extends AVCallback<AVCloudQueryResult> {
    public abstract void done(AVCloudQueryResult aVCloudQueryResult, AVException aVException);

    protected final void internalDone0(AVCloudQueryResult aVCloudQueryResult, AVException aVException) {
        done(aVCloudQueryResult, aVException);
    }
}
