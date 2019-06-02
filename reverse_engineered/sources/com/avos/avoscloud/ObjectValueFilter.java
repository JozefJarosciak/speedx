package com.avos.avoscloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.json.JSONArray;
import org.json.JSONObject;

public class ObjectValueFilter implements ValueFilter {
    public static final ObjectValueFilter instance = new ObjectValueFilter();

    public Object process(Object obj, String str, Object obj2) {
        if ((obj2 instanceof JSONObject) || (obj2 instanceof JSONArray)) {
            return JSON.parse(obj2.toString());
        }
        return obj2;
    }
}
