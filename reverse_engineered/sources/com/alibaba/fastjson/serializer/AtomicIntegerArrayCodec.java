package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayCodec implements ObjectDeserializer, ObjectSerializer {
    public static final AtomicIntegerArrayCodec instance = new AtomicIntegerArrayCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj != null) {
            AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
            int length = atomicIntegerArray.length();
            writer.append('[');
            for (int i = 0; i < length; i++) {
                int i2 = atomicIntegerArray.get(i);
                if (i != 0) {
                    writer.write((char) CoreConstants.COMMA_CHAR);
                }
                writer.writeInt(i2);
            }
            writer.append(']');
        } else if (writer.isEnabled(SerializerFeature.WriteNullListAsEmpty)) {
            writer.write("[]");
        } else {
            writer.writeNull();
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (defaultJSONParser.getLexer().token() == 8) {
            defaultJSONParser.getLexer().nextToken(16);
            return null;
        }
        Collection jSONArray = new JSONArray();
        defaultJSONParser.parseArray(jSONArray);
        T atomicIntegerArray = new AtomicIntegerArray(jSONArray.size());
        for (int i = 0; i < jSONArray.size(); i++) {
            atomicIntegerArray.set(i, jSONArray.getInteger(i).intValue());
        }
        return atomicIntegerArray;
    }

    public int getFastMatchToken() {
        return 14;
    }
}
