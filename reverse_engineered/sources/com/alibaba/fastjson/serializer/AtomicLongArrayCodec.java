package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLongArray;

public class AtomicLongArrayCodec implements ObjectDeserializer, ObjectSerializer {
    public static final AtomicLongArrayCodec instance = new AtomicLongArrayCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj != null) {
            AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
            int length = atomicLongArray.length();
            writer.append('[');
            for (int i = 0; i < length; i++) {
                long j = atomicLongArray.get(i);
                if (i != 0) {
                    writer.write((char) CoreConstants.COMMA_CHAR);
                }
                writer.writeLong(j);
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
        T atomicLongArray = new AtomicLongArray(jSONArray.size());
        for (int i = 0; i < jSONArray.size(); i++) {
            atomicLongArray.set(i, jSONArray.getLong(i).longValue());
        }
        return atomicLongArray;
    }

    public int getFastMatchToken() {
        return 14;
    }
}
