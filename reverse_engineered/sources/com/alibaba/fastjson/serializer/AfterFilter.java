package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;

public abstract class AfterFilter implements SerializeFilter {
    private static final Character COMMA = Character.valueOf(CoreConstants.COMMA_CHAR);
    private static final ThreadLocal<Character> seperatorLocal = new ThreadLocal();
    private static final ThreadLocal<JSONSerializer> serializerLocal = new ThreadLocal();

    public abstract void writeAfter(Object obj);

    final char writeAfter(JSONSerializer jSONSerializer, Object obj, char c) {
        serializerLocal.set(jSONSerializer);
        seperatorLocal.set(Character.valueOf(c));
        writeAfter(obj);
        serializerLocal.set(null);
        return ((Character) seperatorLocal.get()).charValue();
    }

    protected final void writeKeyValue(String str, Object obj) {
        JSONSerializer jSONSerializer = (JSONSerializer) serializerLocal.get();
        char charValue = ((Character) seperatorLocal.get()).charValue();
        jSONSerializer.writeKeyValue(charValue, str, obj);
        if (charValue != CoreConstants.COMMA_CHAR) {
            seperatorLocal.set(COMMA);
        }
    }
}
