package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONAware;
import java.io.IOException;
import java.lang.reflect.Type;

public class JSONAwareSerializer implements ObjectSerializer {
    public static JSONAwareSerializer instance = new JSONAwareSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        jSONSerializer.getWriter().write(((JSONAware) obj).toJSONString());
    }
}
