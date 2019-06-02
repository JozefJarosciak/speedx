package com.alibaba.fastjson;

class JSONStreamContext {
    static final int ArrayValue = 1005;
    static final int PropertyKey = 1002;
    static final int PropertyValue = 1003;
    static final int StartArray = 1004;
    static final int StartObject = 1001;
    private final JSONStreamContext parent;
    private int state;

    public JSONStreamContext(JSONStreamContext jSONStreamContext, int i) {
        this.parent = jSONStreamContext;
        this.state = i;
    }

    public JSONStreamContext getParent() {
        return this.parent;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }
}
