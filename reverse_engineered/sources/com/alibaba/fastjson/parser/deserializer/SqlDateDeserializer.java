package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONScanner;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

public class SqlDateDeserializer extends AbstractDateDeserializer implements ObjectDeserializer {
    public static final SqlDateDeserializer instance = new SqlDateDeserializer();

    protected <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Date) {
            return new java.sql.Date(((Date) obj2).getTime());
        }
        if (obj2 instanceof Number) {
            return new java.sql.Date(((Number) obj2).longValue());
        }
        if (obj2 instanceof String) {
            String str = (String) obj2;
            if (str.length() == 0) {
                return null;
            }
            long timeInMillis;
            JSONScanner jSONScanner = new JSONScanner(str);
            try {
                if (jSONScanner.scanISO8601DateIfMatch()) {
                    timeInMillis = jSONScanner.getCalendar().getTimeInMillis();
                } else {
                    T date = new java.sql.Date(defaultJSONParser.getDateFormat().parse(str).getTime());
                    jSONScanner.close();
                    return date;
                }
            } catch (ParseException e) {
                timeInMillis = Long.parseLong(str);
            } catch (Throwable th) {
                jSONScanner.close();
            }
            jSONScanner.close();
            return new java.sql.Date(timeInMillis);
        }
        throw new JSONException("parse error : " + obj2);
    }

    public int getFastMatchToken() {
        return 2;
    }
}
