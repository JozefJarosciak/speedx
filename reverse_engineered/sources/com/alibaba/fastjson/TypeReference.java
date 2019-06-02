package com.alibaba.fastjson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class TypeReference<T> {
    public static final Type LIST_STRING = new C07111().getType();
    private final Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    /* renamed from: com.alibaba.fastjson.TypeReference$1 */
    static class C07111 extends TypeReference<List<String>> {
        C07111() {
        }
    }

    protected TypeReference() {
    }

    public Type getType() {
        return this.type;
    }
}
