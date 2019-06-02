package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.lang.reflect.Type;

public class ArraySerializer implements ObjectSerializer {
    private final ObjectSerializer compObjectSerializer;
    private final Class<?> componentType;

    public ArraySerializer(Class<?> cls, ObjectSerializer objectSerializer) {
        this.componentType = cls;
        this.compObjectSerializer = objectSerializer;
    }

    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj != null) {
            Object[] objArr = (Object[]) obj;
            int length = objArr.length;
            SerialContext context = jSONSerializer.getContext();
            jSONSerializer.setContext(context, obj, obj2);
            try {
                writer.append('[');
                for (int i = 0; i < length; i++) {
                    if (i != 0) {
                        writer.append((char) CoreConstants.COMMA_CHAR);
                    }
                    Object obj3 = objArr[i];
                    if (obj3 == null) {
                        writer.append((CharSequence) "null");
                    } else if (obj3.getClass() == this.componentType) {
                        this.compObjectSerializer.write(jSONSerializer, obj3, Integer.valueOf(i), null);
                    } else {
                        jSONSerializer.getObjectWriter(obj3.getClass()).write(jSONSerializer, obj3, Integer.valueOf(i), null);
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
