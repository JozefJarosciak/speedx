package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanSerializer implements ObjectSerializer {
    public static final AtomicBooleanSerializer instance = new AtomicBooleanSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (((AtomicBoolean) obj).get()) {
            writer.append((CharSequence) "true");
        } else {
            writer.append((CharSequence) "false");
        }
    }
}
