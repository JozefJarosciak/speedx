package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.util.Collection;

public class JSONArrayDeserializer implements ObjectDeserializer {
    public static final JSONArrayDeserializer instance = new JSONArrayDeserializer();

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Collection jSONArray = new JSONArray();
        defaultJSONParser.parseArray(jSONArray);
        return jSONArray;
    }

    public int getFastMatchToken() {
        return 14;
    }
}
