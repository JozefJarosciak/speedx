package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerSerializer implements ObjectSerializer {
    public static final AtomicIntegerSerializer instance = new AtomicIntegerSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        jSONSerializer.getWriter().writeInt(((AtomicInteger) obj).get());
    }
}
