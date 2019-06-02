package io.rong.imlib;

public enum RongIMClient$MediaType {
    IMAGE(1),
    AUDIO(2),
    VIDEO(3),
    FILE(100);
    
    private int value;

    private RongIMClient$MediaType(int i) {
        this.value = 1;
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static RongIMClient$MediaType setValue(int i) {
        for (RongIMClient$MediaType rongIMClient$MediaType : values()) {
            if (i == rongIMClient$MediaType.getValue()) {
                return rongIMClient$MediaType;
            }
        }
        return IMAGE;
    }
}
