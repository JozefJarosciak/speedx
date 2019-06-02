package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;

public class JSONObjectDeserializer implements ObjectDeserializer {
    public static final JSONObjectDeserializer instance = new JSONObjectDeserializer();

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return defaultJSONParser.parseObject();
    }

    public int getFastMatchToken() {
        return 12;
    }
}
