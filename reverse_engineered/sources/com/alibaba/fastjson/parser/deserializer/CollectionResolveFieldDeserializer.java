package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

public final class CollectionResolveFieldDeserializer extends FieldDeserializer {
    private final Collection collection;

    public CollectionResolveFieldDeserializer(DefaultJSONParser defaultJSONParser, Collection collection) {
        super(null, null);
        this.collection = collection;
    }

    public void setValue(Object obj, Object obj2) {
        this.collection.add(obj2);
    }

    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
    }
}
