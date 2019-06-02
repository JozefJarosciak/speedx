package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.lang.reflect.Type;

public class ShortArraySerializer implements ObjectSerializer {
    public static ShortArraySerializer instance = new ShortArraySerializer();

    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj != null) {
            short[] sArr = (short[]) obj;
            writer.write('[');
            for (int i = 0; i < sArr.length; i++) {
                if (i != 0) {
                    writer.write((char) CoreConstants.COMMA_CHAR);
                }
                writer.writeInt(sArr[i]);
            }
            writer.write(']');
        } else if (writer.isEnabled(SerializerFeature.WriteNullListAsEmpty)) {
            writer.write("[]");
        } else {
            writer.writeNull();
        }
    }
}
