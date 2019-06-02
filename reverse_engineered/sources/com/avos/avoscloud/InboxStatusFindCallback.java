package com.avos.avoscloud;

public abstract class InboxStatusFindCallback extends FindCallback<AVStatus> {
    boolean end;

    public boolean isEnd() {
        return this.end;
    }

    protected void setEnd(boolean z) {
        this.end = z;
    }
}
