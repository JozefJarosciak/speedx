package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.lang.reflect.Type;

public class DoubleArraySerializer implements ObjectSerializer {
    public static final DoubleArraySerializer instance = new DoubleArraySerializer();

    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj != null) {
            double[] dArr = (double[]) obj;
            int length = dArr.length - 1;
            if (length == -1) {
                writer.append((CharSequence) "[]");
                return;
            }
            writer.append('[');
            for (int i = 0; i < length; i++) {
                double d = dArr[i];
                if (Double.isNaN(d)) {
                    writer.writeNull();
                } else {
                    writer.append(Double.toString(d));
                }
                writer.append((char) CoreConstants.COMMA_CHAR);
            }
            double d2 = dArr[length];
            if (Double.isNaN(d2)) {
                writer.writeNull();
            } else {
                writer.append(Double.toString(d2));
            }
            writer.append(']');
        } else if (writer.isEnabled(SerializerFeature.WriteNullListAsEmpty)) {
            writer.write("[]");
        } else {
            writer.writeNull();
        }
    }
}
