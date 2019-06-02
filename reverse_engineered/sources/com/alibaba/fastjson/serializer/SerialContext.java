package com.alibaba.fastjson.serializer;

public class SerialContext {
    private final Object fieldName;
    private final Object object;
    private final SerialContext parent;

    public SerialContext(SerialContext serialContext, Object obj, Object obj2) {
        this.parent = serialContext;
        this.object = obj;
        this.fieldName = obj2;
    }

    public SerialContext getParent() {
        return this.parent;
    }

    public Object getObject() {
        return this.object;
    }

    public Object getFieldName() {
        return this.fieldName;
    }

    public String getPath() {
        if (this.parent == null) {
            return "$";
        }
        if (this.fieldName instanceof Integer) {
            return this.parent.getPath() + "[" + this.fieldName + "]";
        }
        return this.parent.getPath() + "." + this.fieldName;
    }

    public String toString() {
        return getPath();
    }
}
