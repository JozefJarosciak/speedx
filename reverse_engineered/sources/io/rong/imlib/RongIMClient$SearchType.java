package io.rong.imlib;

public enum RongIMClient$SearchType {
    EXACT(0),
    FUZZY(1);
    
    private int value;

    private RongIMClient$SearchType(int i) {
        this.value = 1;
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }
}
