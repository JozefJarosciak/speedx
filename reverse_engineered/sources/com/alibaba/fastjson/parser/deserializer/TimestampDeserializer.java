package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

public class TimestampDeserializer extends AbstractDateDeserializer implements ObjectDeserializer {
    public static final TimestampDeserializer instance = new TimestampDeserializer();

    protected <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Date) {
            return new Timestamp(((Date) obj2).getTime());
        }
        if (obj2 instanceof Number) {
            return new Timestamp(((Number) obj2).longValue());
        }
        if (obj2 instanceof String) {
            String str = (String) obj2;
            if (str.length() == 0) {
                return null;
            }
            try {
                return new Timestamp(defaultJSONParser.getDateFormat().parse(str).getTime());
            } catch (ParseException e) {
                return new Timestamp(Long.parseLong(str));
            }
        }
        throw new JSONException("parse error");
    }

    public int getFastMatchToken() {
        return 2;
    }
}
