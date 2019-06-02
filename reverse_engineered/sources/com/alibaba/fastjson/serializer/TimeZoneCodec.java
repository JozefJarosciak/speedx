package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.TimeZone;

public class TimeZoneCodec implements ObjectDeserializer, ObjectSerializer {
    public static final TimeZoneCodec instance = new TimeZoneCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        if (obj == null) {
            jSONSerializer.writeNull();
        } else {
            jSONSerializer.write(((TimeZone) obj).getID());
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        String str = (String) defaultJSONParser.parse();
        if (str == null) {
            return null;
        }
        return TimeZone.getTimeZone(str);
    }

    public int getFastMatchToken() {
        return 4;
    }
}
