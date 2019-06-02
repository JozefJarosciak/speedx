package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.lang.reflect.Type;

public class ObjectArraySerializer implements ObjectSerializer {
    public static final ObjectArraySerializer instance = new ObjectArraySerializer();

    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        int i = 0;
        Class cls = null;
        SerializeWriter writer = jSONSerializer.getWriter();
        Object[] objArr = (Object[]) obj;
        if (obj != null) {
            int length = objArr.length;
            int i2 = length - 1;
            if (i2 == -1) {
                writer.append((CharSequence) "[]");
                return;
            }
            SerialContext context = jSONSerializer.getContext();
            jSONSerializer.setContext(context, obj, obj2);
            writer.append('[');
            if (writer.isEnabled(SerializerFeature.PrettyFormat)) {
                jSONSerializer.incrementIndent();
                jSONSerializer.println();
                while (i < length) {
                    if (i != 0) {
                        writer.write((char) CoreConstants.COMMA_CHAR);
                        jSONSerializer.println();
                    }
                    jSONSerializer.write(objArr[i]);
                    i++;
                }
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
                writer.write(']');
                return;
            }
            length = 0;
            ObjectSerializer objectSerializer = null;
            while (length < i2) {
                try {
                    Object obj3 = objArr[length];
                    if (obj3 == null) {
                        writer.append((CharSequence) "null,");
                    } else {
                        if (jSONSerializer.containsReference(obj3)) {
                            jSONSerializer.writeReference(obj3);
                        } else {
                            Class cls2 = obj3.getClass();
                            if (cls2 == cls) {
                                objectSerializer.write(jSONSerializer, obj3, null, null);
                            } else {
                                objectSerializer = jSONSerializer.getObjectWriter(cls2);
                                objectSerializer.write(jSONSerializer, obj3, null, null);
                                cls = cls2;
                            }
                        }
                        writer.append((char) CoreConstants.COMMA_CHAR);
                    }
                    length++;
                } finally {
                    jSONSerializer.setContext(context);
                }
            }
            Object obj4 = objArr[i2];
            if (obj4 == null) {
                writer.append((CharSequence) "null]");
            } else {
                if (jSONSerializer.containsReference(obj4)) {
                    jSONSerializer.writeReference(obj4);
                } else {
                    jSONSerializer.writeWithFieldName(obj4, Integer.valueOf(i2));
                }
                writer.append(']');
            }
            jSONSerializer.setContext(context);
        } else if (writer.isEnabled(SerializerFeature.WriteNullListAsEmpty)) {
            writer.write("[]");
        } else {
            writer.writeNull();
        }
    }
}
