package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class FieldDeserializer {
    protected final Class<?> clazz;
    protected final FieldInfo fieldInfo;

    public abstract void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map);

    public FieldDeserializer(Class<?> cls, FieldInfo fieldInfo) {
        this.clazz = cls;
        this.fieldInfo = fieldInfo;
    }

    public Method getMethod() {
        return this.fieldInfo.getMethod();
    }

    public Field getField() {
        return this.fieldInfo.getField();
    }

    public Class<?> getFieldClass() {
        return this.fieldInfo.getFieldClass();
    }

    public Type getFieldType() {
        return this.fieldInfo.getFieldType();
    }

    public int getFastMatchToken() {
        return 0;
    }

    public void setValue(Object obj, boolean z) {
        setValue(obj, Boolean.valueOf(z));
    }

    public void setValue(Object obj, int i) {
        setValue(obj, Integer.valueOf(i));
    }

    public void setValue(Object obj, long j) {
        setValue(obj, Long.valueOf(j));
    }

    public void setValue(Object obj, String str) {
        setValue(obj, (Object) str);
    }

    public void setValue(Object obj, Object obj2) {
        Method method = this.fieldInfo.getMethod();
        if (method != null) {
            try {
                if (!this.fieldInfo.isGetOnly()) {
                    method.invoke(obj, new Object[]{obj2});
                    return;
                } else if (this.fieldInfo.getFieldClass() == AtomicInteger.class) {
                    AtomicInteger atomicInteger = (AtomicInteger) method.invoke(obj, new Object[0]);
                    if (atomicInteger != null) {
                        atomicInteger.set(((AtomicInteger) obj2).get());
                        return;
                    }
                    return;
                } else if (this.fieldInfo.getFieldClass() == AtomicLong.class) {
                    AtomicLong atomicLong = (AtomicLong) method.invoke(obj, new Object[0]);
                    if (atomicLong != null) {
                        atomicLong.set(((AtomicLong) obj2).get());
                        return;
                    }
                    return;
                } else if (this.fieldInfo.getFieldClass() == AtomicBoolean.class) {
                    AtomicBoolean atomicBoolean = (AtomicBoolean) method.invoke(obj, new Object[0]);
                    if (atomicBoolean != null) {
                        atomicBoolean.set(((AtomicBoolean) obj2).get());
                        return;
                    }
                    return;
                } else if (Map.class.isAssignableFrom(method.getReturnType())) {
                    Map map = (Map) method.invoke(obj, new Object[0]);
                    if (map != null) {
                        map.putAll((Map) obj2);
                        return;
                    }
                    return;
                } else {
                    Collection collection = (Collection) method.invoke(obj, new Object[0]);
                    if (collection != null) {
                        collection.addAll((Collection) obj2);
                        return;
                    }
                    return;
                }
            } catch (Throwable e) {
                throw new JSONException("set property error, " + this.fieldInfo.getName(), e);
            }
        }
        Field field = this.fieldInfo.getField();
        if (field != null) {
            try {
                field.set(obj, obj2);
            } catch (Throwable e2) {
                throw new JSONException("set property error, " + this.fieldInfo.getName(), e2);
            }
        }
    }
}
