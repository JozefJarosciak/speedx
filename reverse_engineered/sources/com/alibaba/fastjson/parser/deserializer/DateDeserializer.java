package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONScanner;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

public class DateDeserializer extends AbstractDateDeserializer implements ObjectDeserializer {
    public static final DateDeserializer instance = new DateDeserializer();

    protected <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Date) {
            return obj2;
        }
        if (obj2 instanceof Number) {
            return new Date(((Number) obj2).longValue());
        }
        if (obj2 instanceof String) {
            obj2 = (String) obj2;
            if (obj2.length() == 0) {
                return null;
            }
            JSONScanner jSONScanner = new JSONScanner(obj2);
            try {
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    obj2 = jSONScanner.getCalendar().getTime();
                    return obj2;
                }
                jSONScanner.close();
                try {
                    return defaultJSONParser.getDateFormat().parse(obj2);
                } catch (ParseException e) {
                    return new Date(Long.parseLong(obj2));
                }
            } finally {
                jSONScanner.close();
            }
        } else {
            throw new JSONException("parse error");
        }
    }

    public int getFastMatchToken() {
        return 2;
    }
}
