package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class ByteSerializer implements ObjectSerializer {
    public static ByteSerializer instance = new ByteSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (((Number) obj) != null) {
            writer.writeInt(((Number) obj).shortValue());
            if (jSONSerializer.isEnabled(SerializerFeature.WriteClassName)) {
                writer.write('B');
            }
        } else if (writer.isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
            writer.write('0');
        } else {
            writer.writeNull();
        }
    }
}
