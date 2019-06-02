package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongSerializer implements ObjectSerializer {
    public static final AtomicLongSerializer instance = new AtomicLongSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        jSONSerializer.getWriter().writeLong(((AtomicLong) obj).get());
    }
}
