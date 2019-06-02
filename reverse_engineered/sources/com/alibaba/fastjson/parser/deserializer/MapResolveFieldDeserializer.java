package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.util.Map;

public final class MapResolveFieldDeserializer extends FieldDeserializer {
    private final String key;
    private final Map map;

    public MapResolveFieldDeserializer(Map map, String str) {
        super(null, null);
        this.key = str;
        this.map = map;
    }

    public void setValue(Object obj, Object obj2) {
        this.map.put(this.key, obj2);
    }

    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
    }
}
