package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class CollectionDeserializer implements ObjectDeserializer {
    public static final CollectionDeserializer instance = new CollectionDeserializer();

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (defaultJSONParser.getLexer().token() == 8) {
            defaultJSONParser.getLexer().nextToken(16);
            return null;
        }
        T arrayList;
        Type type2;
        Class rawClass = getRawClass(type);
        if (rawClass == AbstractCollection.class) {
            arrayList = new ArrayList();
        } else if (rawClass.isAssignableFrom(HashSet.class)) {
            arrayList = new HashSet();
        } else if (rawClass.isAssignableFrom(LinkedHashSet.class)) {
            arrayList = new LinkedHashSet();
        } else if (rawClass.isAssignableFrom(TreeSet.class)) {
            arrayList = new TreeSet();
        } else if (rawClass.isAssignableFrom(ArrayList.class)) {
            arrayList = new ArrayList();
        } else if (rawClass.isAssignableFrom(EnumSet.class)) {
            Class cls;
            if (type instanceof ParameterizedType) {
                cls = ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                cls = Object.class;
            }
            arrayList = EnumSet.noneOf(cls);
        } else {
            try {
                Collection collection = (Collection) rawClass.newInstance();
            } catch (Exception e) {
                throw new JSONException("create instane error, class " + rawClass.getName());
            }
        }
        if (type instanceof ParameterizedType) {
            type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        } else {
            type2 = Object.class;
        }
        defaultJSONParser.parseArray(type2, arrayList, obj);
        return arrayList;
    }

    public Class<?> getRawClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getRawClass(((ParameterizedType) type).getRawType());
        }
        throw new JSONException("TODO");
    }

    public int getFastMatchToken() {
        return 14;
    }
}
