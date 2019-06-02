package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class ClassSerializer implements ObjectSerializer {
    public static final ClassSerializer instance = new ClassSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        jSONSerializer.getWriter().writeString(((Class) obj).getName());
    }
}
