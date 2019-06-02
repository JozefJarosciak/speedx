package io.rong.imlib;

public enum RongIMClient$BlacklistStatus {
    IN_BLACK_LIST(0),
    NOT_IN_BLACK_LIST(1);
    
    private int value;

    private RongIMClient$BlacklistStatus(int i) {
        this.value = 1;
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static RongIMClient$BlacklistStatus setValue(int i) {
        for (RongIMClient$BlacklistStatus rongIMClient$BlacklistStatus : values()) {
            if (i == rongIMClient$BlacklistStatus.getValue()) {
                return rongIMClient$BlacklistStatus;
            }
        }
        return NOT_IN_BLACK_LIST;
    }
}
