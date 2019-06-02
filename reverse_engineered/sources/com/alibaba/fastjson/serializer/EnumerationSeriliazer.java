package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Enumeration;

public class EnumerationSeriliazer implements ObjectSerializer {
    public static EnumerationSeriliazer instance = new EnumerationSeriliazer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        int i = 0;
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj != null) {
            Type type2;
            if (jSONSerializer.isEnabled(SerializerFeature.WriteClassName) && (type instanceof ParameterizedType)) {
                type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                type2 = null;
            }
            Enumeration enumeration = (Enumeration) obj;
            SerialContext context = jSONSerializer.getContext();
            jSONSerializer.setContext(context, obj, obj2);
            try {
                writer.append('[');
                while (enumeration.hasMoreElements()) {
                    Object nextElement = enumeration.nextElement();
                    int i2 = i + 1;
                    if (i != 0) {
                        writer.append((char) CoreConstants.COMMA_CHAR);
                    }
                    if (nextElement == null) {
                        writer.writeNull();
                        i = i2;
                    } else {
                        jSONSerializer.getObjectWriter(nextElement.getClass()).write(jSONSerializer, nextElement, Integer.valueOf(i2 - 1), type2);
                        i = i2;
                    }
                }
                writer.append(']');
            } finally {
                jSONSerializer.setContext(context);
            }
        } else if (writer.isEnabled(SerializerFeature.WriteNullListAsEmpty)) {
            writer.write("[]");
        } else {
            writer.writeNull();
        }
    }
}
