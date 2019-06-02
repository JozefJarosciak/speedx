package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class AppendableSerializer implements ObjectSerializer {
    public static final AppendableSerializer instance = new AppendableSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        if (obj == null) {
            SerializeWriter writer = jSONSerializer.getWriter();
            if (writer.isEnabled(SerializerFeature.WriteNullStringAsEmpty)) {
                writer.writeString("");
                return;
            } else {
                writer.writeNull();
                return;
            }
        }
        jSONSerializer.write(obj.toString());
    }
}
