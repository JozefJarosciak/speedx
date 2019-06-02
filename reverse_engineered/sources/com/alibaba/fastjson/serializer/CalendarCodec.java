package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.DateDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

public class CalendarCodec implements ObjectDeserializer, ObjectSerializer {
    public static final CalendarCodec instance = new CalendarCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        jSONSerializer.write(((Calendar) obj).getTime());
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Date date = (Date) DateDeserializer.instance.deserialze(defaultJSONParser, type, obj);
        T instance = Calendar.getInstance();
        instance.setTime(date);
        return instance;
    }

    public int getFastMatchToken() {
        return 2;
    }
}
