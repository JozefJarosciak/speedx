package io.rong.imlib;

public enum RongIMClient$DiscussionInviteStatus {
    CLOSED(1),
    OPENED(0);
    
    private int value;

    private RongIMClient$DiscussionInviteStatus(int i) {
        this.value = 0;
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static RongIMClient$DiscussionInviteStatus setValue(int i) {
        for (RongIMClient$DiscussionInviteStatus rongIMClient$DiscussionInviteStatus : values()) {
            if (i == rongIMClient$DiscussionInviteStatus.getValue()) {
                return rongIMClient$DiscussionInviteStatus;
            }
        }
        return OPENED;
    }
}
