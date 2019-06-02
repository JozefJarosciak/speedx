package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.lang.reflect.Type;

public class LongArraySerializer implements ObjectSerializer {
    public static LongArraySerializer instance = new LongArraySerializer();

    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj != null) {
            long[] jArr = (long[]) obj;
            writer.write('[');
            for (int i = 0; i < jArr.length; i++) {
                if (i != 0) {
                    writer.write((char) CoreConstants.COMMA_CHAR);
                }
                writer.writeLong(jArr[i]);
            }
            writer.write(']');
        } else if (writer.isEnabled(SerializerFeature.WriteNullListAsEmpty)) {
            writer.write("[]");
        } else {
            writer.writeNull();
        }
    }
}
