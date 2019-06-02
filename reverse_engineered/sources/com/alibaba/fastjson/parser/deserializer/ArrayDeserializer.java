package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class ArrayDeserializer implements ObjectDeserializer {
    public static final ArrayDeserializer instance = new ArrayDeserializer();

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 8) {
            lexer.nextToken(16);
            return null;
        } else if (lexer.token() == 4) {
            T bytesValue = lexer.bytesValue();
            lexer.nextToken(16);
            return bytesValue;
        } else {
            Object obj2;
            if (type instanceof GenericArrayType) {
                Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
                if (genericComponentType instanceof TypeVariable) {
                    TypeVariable typeVariable = (TypeVariable) genericComponentType;
                    Type type2 = defaultJSONParser.getContext().getType();
                    if (type2 instanceof ParameterizedType) {
                        T t;
                        ParameterizedType parameterizedType = (ParameterizedType) type2;
                        Type rawType = parameterizedType.getRawType();
                        if (rawType instanceof Class) {
                            TypeVariable[] typeParameters = ((Class) rawType).getTypeParameters();
                            T t2 = null;
                            for (int i = 0; i < typeParameters.length; i++) {
                                if (typeParameters[i].getName().equals(typeVariable.getName())) {
                                    t2 = parameterizedType.getActualTypeArguments()[i];
                                }
                            }
                            t = t2;
                        } else {
                            t = null;
                        }
                        if (t instanceof Class) {
                            obj2 = (Class) t;
                        } else {
                            obj2 = Object.class;
                        }
                    } else {
                        obj2 = Object.class;
                    }
                } else {
                    Class cls = (Class) genericComponentType;
                }
            } else {
                obj2 = ((Class) type).getComponentType();
            }
            Object jSONArray = new JSONArray();
            defaultJSONParser.parseArray(obj2, jSONArray, obj);
            return toObjectArray(defaultJSONParser, obj2, jSONArray);
        }
    }

    private <T> T toObjectArray(DefaultJSONParser defaultJSONParser, Class<?> cls, JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        int size = jSONArray.size();
        T newInstance = Array.newInstance(cls, size);
        for (int i = 0; i < size; i++) {
            Object obj = jSONArray.get(i);
            if (obj == jSONArray) {
                Array.set(newInstance, i, newInstance);
            } else if (cls.isArray()) {
                if (!cls.isInstance(obj)) {
                    obj = toObjectArray(defaultJSONParser, cls, (JSONArray) obj);
                }
                Array.set(newInstance, i, obj);
            } else {
                Object toArray;
                if (obj instanceof JSONArray) {
                    JSONArray jSONArray2 = (JSONArray) obj;
                    int size2 = jSONArray2.size();
                    Object obj2 = null;
                    for (int i2 = 0; i2 < size2; i2++) {
                        if (jSONArray2.get(i2) == jSONArray) {
                            jSONArray2.set(i, newInstance);
                            obj2 = 1;
                        }
                    }
                    if (obj2 != null) {
                        toArray = jSONArray2.toArray();
                        if (toArray != null) {
                            obj = TypeUtils.cast(obj, (Class) cls, defaultJSONParser.getConfig());
                        } else {
                            obj = toArray;
                        }
                        Array.set(newInstance, i, obj);
                    }
                }
                toArray = null;
                if (toArray != null) {
                    obj = toArray;
                } else {
                    obj = TypeUtils.cast(obj, (Class) cls, defaultJSONParser.getConfig());
                }
                Array.set(newInstance, i, obj);
            }
        }
        jSONArray.setRelatedArray(newInstance);
        jSONArray.setComponentType(cls);
        return newInstance;
    }

    public int getFastMatchToken() {
        return 14;
    }
}
