package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class DateFormatDeserializer extends AbstractDateDeserializer implements ObjectDeserializer {
    public static final DateFormatDeserializer instance = new DateFormatDeserializer();

    protected <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof String) {
            String str = (String) obj2;
            if (str.length() != 0) {
                return new SimpleDateFormat(str);
            }
            return null;
        }
        throw new JSONException("parse error");
    }

    public int getFastMatchToken() {
        return 4;
    }
}
