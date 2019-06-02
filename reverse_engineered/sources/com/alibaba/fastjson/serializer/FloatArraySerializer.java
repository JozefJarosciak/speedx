package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.lang.reflect.Type;

public class FloatArraySerializer implements ObjectSerializer {
    public static final FloatArraySerializer instance = new FloatArraySerializer();

    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj != null) {
            float[] fArr = (float[]) obj;
            int length = fArr.length - 1;
            if (length == -1) {
                writer.append((CharSequence) "[]");
                return;
            }
            writer.append('[');
            for (int i = 0; i < length; i++) {
                float f = fArr[i];
                if (Float.isNaN(f)) {
                    writer.writeNull();
                } else {
                    writer.append(Float.toString(f));
                }
                writer.append((char) CoreConstants.COMMA_CHAR);
            }
            float f2 = fArr[length];
            if (Float.isNaN(f2)) {
                writer.writeNull();
            } else {
                writer.append(Float.toString(f2));
            }
            writer.append(']');
        } else if (writer.isEnabled(SerializerFeature.WriteNullListAsEmpty)) {
            writer.write("[]");
        } else {
            writer.writeNull();
        }
    }
}
