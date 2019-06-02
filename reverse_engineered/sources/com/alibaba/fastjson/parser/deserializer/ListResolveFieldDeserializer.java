package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public final class ListResolveFieldDeserializer extends FieldDeserializer {
    private final int index;
    private final List list;
    private final DefaultJSONParser parser;

    public ListResolveFieldDeserializer(DefaultJSONParser defaultJSONParser, List list, int i) {
        super(null, null);
        this.parser = defaultJSONParser;
        this.index = i;
        this.list = list;
    }

    public void setValue(Object obj, Object obj2) {
        this.list.set(this.index, obj2);
        if (this.list instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) this.list;
            Object relatedArray = jSONArray.getRelatedArray();
            if (relatedArray != null && Array.getLength(relatedArray) > this.index) {
                if (jSONArray.getComponentType() != null) {
                    obj2 = TypeUtils.cast(obj2, jSONArray.getComponentType(), this.parser.getConfig());
                }
                Array.set(relatedArray, this.index, obj2);
            }
        }
    }

    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
    }
}
