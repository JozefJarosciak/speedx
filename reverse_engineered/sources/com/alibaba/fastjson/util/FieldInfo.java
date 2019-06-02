package com.alibaba.fastjson.util;

import com.alibaba.fastjson.annotation.JSONField;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class FieldInfo implements Comparable<FieldInfo> {
    private final Class<?> declaringClass;
    private final Field field;
    private final Class<?> fieldClass;
    private final Type fieldType;
    private boolean getOnly;
    private final Method method;
    private final String name;

    public FieldInfo(String str, Class<?> cls, Class<?> cls2, Type type, Field field) {
        this.getOnly = false;
        this.name = str;
        this.declaringClass = cls;
        this.fieldClass = cls2;
        this.fieldType = type;
        this.method = null;
        this.field = field;
        if (field != null) {
            field.setAccessible(true);
        }
    }

    public FieldInfo(String str, Method method, Field field) {
        this(str, method, field, null, null);
    }

    public FieldInfo(String str, Method method, Field field, Class<?> cls, Type type) {
        Class cls2;
        Type type2;
        Class cls3;
        this.getOnly = false;
        this.name = str;
        this.method = method;
        this.field = field;
        if (method != null) {
            method.setAccessible(true);
        }
        if (field != null) {
            field.setAccessible(true);
        }
        if (method != null) {
            if (method.getParameterTypes().length == 1) {
                cls2 = method.getParameterTypes()[0];
                type2 = method.getGenericParameterTypes()[0];
            } else {
                cls2 = method.getReturnType();
                type2 = method.getGenericReturnType();
                this.getOnly = true;
            }
            this.declaringClass = method.getDeclaringClass();
            cls3 = cls2;
        } else {
            cls3 = field.getType();
            type2 = field.getGenericType();
            this.declaringClass = field.getDeclaringClass();
        }
        if (cls != null && cls3 == Object.class && (type2 instanceof TypeVariable)) {
            Type inheritGenericType = getInheritGenericType(cls, (TypeVariable) type2);
            if (inheritGenericType != null) {
                this.fieldClass = TypeUtils.getClass(inheritGenericType);
                this.fieldType = inheritGenericType;
                return;
            }
        }
        Type fieldType = getFieldType(cls, type, type2);
        if (fieldType != type2) {
            if (fieldType instanceof ParameterizedType) {
                cls2 = TypeUtils.getClass(fieldType);
            } else if (fieldType instanceof Class) {
                cls2 = TypeUtils.getClass(fieldType);
            }
            this.fieldType = fieldType;
            this.fieldClass = cls2;
        }
        cls2 = cls3;
        this.fieldType = fieldType;
        this.fieldClass = cls2;
    }

    public static Type getFieldType(Class<?> cls, Type type, Type type2) {
        if (cls == null || type == null || !(type instanceof ParameterizedType)) {
            return type2;
        }
        ParameterizedType parameterizedType;
        TypeVariable typeVariable;
        if (type2 instanceof TypeVariable) {
            parameterizedType = (ParameterizedType) type;
            typeVariable = (TypeVariable) type2;
            for (int i = 0; i < cls.getTypeParameters().length; i++) {
                if (cls.getTypeParameters()[i].getName().equals(typeVariable.getName())) {
                    return parameterizedType.getActualTypeArguments()[i];
                }
            }
        }
        if (!(type2 instanceof ParameterizedType)) {
            return type2;
        }
        parameterizedType = (ParameterizedType) type2;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        int i2 = 0;
        Object obj = null;
        while (i2 < actualTypeArguments.length) {
            Object obj2;
            Type type3 = actualTypeArguments[i2];
            if (type3 instanceof TypeVariable) {
                typeVariable = (TypeVariable) type3;
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType2 = (ParameterizedType) type;
                    Object obj3 = obj;
                    for (int i3 = 0; i3 < cls.getTypeParameters().length; i3++) {
                        if (cls.getTypeParameters()[i3].getName().equals(typeVariable.getName())) {
                            actualTypeArguments[i2] = parameterizedType2.getActualTypeArguments()[i3];
                            obj3 = 1;
                        }
                    }
                    obj2 = obj3;
                    i2++;
                    obj = obj2;
                }
            }
            obj2 = obj;
            i2++;
            obj = obj2;
        }
        if (obj != null) {
            return new ParameterizedTypeImpl(actualTypeArguments, parameterizedType.getOwnerType(), parameterizedType.getRawType());
        }
        return type2;
    }

    public static Type getInheritGenericType(Class<?> cls, TypeVariable<?> typeVariable) {
        Type genericDeclaration = typeVariable.getGenericDeclaration();
        Type genericSuperclass;
        do {
            genericSuperclass = cls.getGenericSuperclass();
            if (genericSuperclass == null) {
                return null;
            }
            if (genericSuperclass instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                if (parameterizedType.getRawType() == genericDeclaration) {
                    TypeVariable[] typeParameters = genericDeclaration.getTypeParameters();
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    for (int i = 0; i < typeParameters.length; i++) {
                        if (typeParameters[i] == typeVariable) {
                            return actualTypeArguments[i];
                        }
                    }
                    return null;
                }
            }
            cls = TypeUtils.getClass(genericSuperclass);
        } while (genericSuperclass != null);
        return null;
    }

    public String toString() {
        return this.name;
    }

    public Class<?> getDeclaringClass() {
        return this.declaringClass;
    }

    public Class<?> getFieldClass() {
        return this.fieldClass;
    }

    public Type getFieldType() {
        return this.fieldType;
    }

    public String getName() {
        return this.name;
    }

    public Method getMethod() {
        return this.method;
    }

    public Field getField() {
        return this.field;
    }

    public int compareTo(FieldInfo fieldInfo) {
        return this.name.compareTo(fieldInfo.name);
    }

    public <T extends Annotation> T getAnnotation(Class<T> cls) {
        T t = null;
        if (this.method != null) {
            t = this.method.getAnnotation(cls);
        }
        if (t != null || this.field == null) {
            return t;
        }
        return this.field.getAnnotation(cls);
    }

    public String getFormat() {
        JSONField jSONField = (JSONField) getAnnotation(JSONField.class);
        if (jSONField == null) {
            return null;
        }
        String format = jSONField.format();
        if (format.trim().length() == 0) {
            return null;
        }
        return format;
    }

    public Object get(Object obj) throws IllegalAccessException, InvocationTargetException {
        if (this.method != null) {
            return this.method.invoke(obj, new Object[0]);
        }
        return this.field.get(obj);
    }

    public void set(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        if (this.method != null) {
            this.method.invoke(obj, new Object[]{obj2});
            return;
        }
        this.field.set(obj, obj2);
    }

    public void setAccessible(boolean z) throws SecurityException {
        if (this.method != null) {
            this.method.setAccessible(z);
        } else {
            this.field.setAccessible(z);
        }
    }

    public boolean isGetOnly() {
        return this.getOnly;
    }
}
