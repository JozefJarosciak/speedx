package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import java.beans.Introspector;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DeserializeBeanInfo {
    private final Class<?> clazz;
    private Constructor<?> creatorConstructor;
    private Constructor<?> defaultConstructor;
    private Method factoryMethod;
    private final List<FieldInfo> fieldList = new ArrayList();
    private final List<FieldInfo> sortedFieldList = new ArrayList();

    public DeserializeBeanInfo(Class<?> cls) {
        this.clazz = cls;
    }

    public Constructor<?> getDefaultConstructor() {
        return this.defaultConstructor;
    }

    public void setDefaultConstructor(Constructor<?> constructor) {
        this.defaultConstructor = constructor;
    }

    public Constructor<?> getCreatorConstructor() {
        return this.creatorConstructor;
    }

    public void setCreatorConstructor(Constructor<?> constructor) {
        this.creatorConstructor = constructor;
    }

    public Method getFactoryMethod() {
        return this.factoryMethod;
    }

    public void setFactoryMethod(Method method) {
        this.factoryMethod = method;
    }

    public Class<?> getClazz() {
        return this.clazz;
    }

    public List<FieldInfo> getFieldList() {
        return this.fieldList;
    }

    public List<FieldInfo> getSortedFieldList() {
        return this.sortedFieldList;
    }

    public FieldInfo getField(String str) {
        for (FieldInfo fieldInfo : this.fieldList) {
            if (fieldInfo.getName().equals(str)) {
                return fieldInfo;
            }
        }
        return null;
    }

    public boolean add(FieldInfo fieldInfo) {
        for (FieldInfo name : this.fieldList) {
            if (name.getName().equals(fieldInfo.getName())) {
                return false;
            }
        }
        this.fieldList.add(fieldInfo);
        this.sortedFieldList.add(fieldInfo);
        Collections.sort(this.sortedFieldList);
        return true;
    }

    public static DeserializeBeanInfo computeSetters(Class<?> cls, Type type) {
        int i;
        DeserializeBeanInfo deserializeBeanInfo = new DeserializeBeanInfo(cls);
        Constructor defaultConstructor = getDefaultConstructor(cls);
        if (defaultConstructor != null) {
            defaultConstructor.setAccessible(true);
            deserializeBeanInfo.setDefaultConstructor(defaultConstructor);
        } else if (!(defaultConstructor != null || cls.isInterface() || Modifier.isAbstract(cls.getModifiers()))) {
            Constructor creatorConstructor = getCreatorConstructor(cls);
            JSONField jSONField;
            if (creatorConstructor != null) {
                creatorConstructor.setAccessible(true);
                deserializeBeanInfo.setCreatorConstructor(creatorConstructor);
                for (i = 0; i < creatorConstructor.getParameterTypes().length; i++) {
                    jSONField = null;
                    for (Annotation annotation : creatorConstructor.getParameterAnnotations()[i]) {
                        if (annotation instanceof JSONField) {
                            jSONField = (JSONField) annotation;
                            break;
                        }
                    }
                    if (jSONField == null) {
                        throw new JSONException("illegal json creator");
                    }
                    deserializeBeanInfo.add(new FieldInfo(jSONField.name(), (Class) cls, creatorConstructor.getParameterTypes()[i], creatorConstructor.getGenericParameterTypes()[i], getField(cls, jSONField.name())));
                }
                return deserializeBeanInfo;
            }
            Method factoryMethod = getFactoryMethod(cls);
            if (factoryMethod != null) {
                factoryMethod.setAccessible(true);
                deserializeBeanInfo.setFactoryMethod(factoryMethod);
                for (i = 0; i < factoryMethod.getParameterTypes().length; i++) {
                    jSONField = null;
                    for (Annotation annotation2 : factoryMethod.getParameterAnnotations()[i]) {
                        if (annotation2 instanceof JSONField) {
                            jSONField = (JSONField) annotation2;
                            break;
                        }
                    }
                    if (jSONField == null) {
                        throw new JSONException("illegal json creator");
                    }
                    deserializeBeanInfo.add(new FieldInfo(jSONField.name(), (Class) cls, factoryMethod.getParameterTypes()[i], factoryMethod.getGenericParameterTypes()[i], getField(cls, jSONField.name())));
                }
                return deserializeBeanInfo;
            }
            throw new JSONException("default constructor not found. " + cls);
        }
        for (Method method : TypeUtils.getAllMethods(cls)) {
            JSONField jSONField2;
            Field field;
            String name = method.getName();
            if (name.length() >= 4 && !Modifier.isStatic(method.getModifiers()) && ((method.getReturnType().equals(Void.TYPE) || method.getReturnType().equals(cls)) && method.getParameterTypes().length == 1)) {
                jSONField2 = (JSONField) method.getAnnotation(JSONField.class);
                if (jSONField2 == null) {
                    jSONField2 = TypeUtils.getSupperMethodAnnotation(cls, method);
                }
                if (jSONField2 != null) {
                    if (jSONField2.deserialize()) {
                        if (jSONField2.name().length() != 0) {
                            deserializeBeanInfo.add(new FieldInfo(jSONField2.name(), method, null, (Class) cls, type));
                            method.setAccessible(true);
                        }
                    }
                }
                if (name.startsWith("set")) {
                    char charAt = name.charAt(3);
                    if (Character.isUpperCase(charAt)) {
                        if (TypeUtils.compatibleWithJavaBean) {
                            name = Introspector.decapitalize(name.substring(3));
                        } else {
                            name = Character.toLowerCase(name.charAt(3)) + name.substring(4);
                        }
                    } else if (charAt == '_') {
                        name = name.substring(4);
                    } else if (charAt == 'f') {
                        name = name.substring(3);
                    }
                    field = getField(cls, name);
                    if (field == null && method.getParameterTypes()[0] == Boolean.TYPE) {
                        field = getField(cls, "is" + Character.toUpperCase(name.charAt(0)) + name.substring(1));
                    }
                    if (field != null) {
                        jSONField2 = (JSONField) field.getAnnotation(JSONField.class);
                        if (!(jSONField2 == null || jSONField2.name().length() == 0)) {
                            deserializeBeanInfo.add(new FieldInfo(jSONField2.name(), method, field, (Class) cls, type));
                        }
                    }
                    deserializeBeanInfo.add(new FieldInfo(name, method, null, (Class) cls, type));
                    method.setAccessible(true);
                }
            }
        }
        for (Field field2 : cls.getFields()) {
            if (!Modifier.isStatic(field2.getModifiers())) {
                Object obj = null;
                for (FieldInfo name2 : deserializeBeanInfo.getFieldList()) {
                    if (name2.getName().equals(field2.getName())) {
                        obj = 1;
                    }
                }
                if (obj == null) {
                    name = field2.getName();
                    jSONField2 = (JSONField) field2.getAnnotation(JSONField.class);
                    if (!(jSONField2 == null || jSONField2.name().length() == 0)) {
                        name = jSONField2.name();
                    }
                    deserializeBeanInfo.add(new FieldInfo(name, null, field2, (Class) cls, type));
                }
            }
        }
        for (Method method2 : TypeUtils.getAllMethods(cls)) {
            String name3 = method2.getName();
            if (name3.length() >= 4 && !Modifier.isStatic(method2.getModifiers()) && name3.startsWith("get") && Character.isUpperCase(name3.charAt(3)) && method2.getParameterTypes().length == 0 && (Collection.class.isAssignableFrom(method2.getReturnType()) || Map.class.isAssignableFrom(method2.getReturnType()) || AtomicBoolean.class == method2.getReturnType() || AtomicInteger.class == method2.getReturnType() || AtomicLong.class == method2.getReturnType())) {
                name = Character.toLowerCase(name3.charAt(3)) + name3.substring(4);
                if (deserializeBeanInfo.getField(name) == null) {
                    deserializeBeanInfo.add(new FieldInfo(name, method2, null, (Class) cls, type));
                    method2.setAccessible(true);
                }
            }
        }
        return deserializeBeanInfo;
    }

    public static Field getField(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static Constructor<?> getDefaultConstructor(Class<?> cls) {
        if (Modifier.isAbstract(cls.getModifiers())) {
            return null;
        }
        for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
            if (constructor.getParameterTypes().length == 0) {
                break;
            }
        }
        Constructor<?> constructor2 = null;
        if (constructor2 == null && cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers())) {
            for (Constructor<?> constructor3 : cls.getDeclaredConstructors()) {
                if (constructor3.getParameterTypes().length == 1 && constructor3.getParameterTypes()[0].equals(cls.getDeclaringClass())) {
                    return constructor3;
                }
            }
        }
        return constructor2;
    }

    public static Constructor<?> getCreatorConstructor(Class<?> cls) {
        Constructor[] declaredConstructors = cls.getDeclaredConstructors();
        int length = declaredConstructors.length;
        int i = 0;
        while (i < length) {
            Constructor<?> constructor = declaredConstructors[i];
            if (((JSONCreator) constructor.getAnnotation(JSONCreator.class)) == null) {
                i++;
            } else if (null == null) {
                return constructor;
            } else {
                throw new JSONException("multi-json creator");
            }
        }
        return null;
    }

    public static Method getFactoryMethod(Class<?> cls) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        int length = declaredMethods.length;
        int i = 0;
        while (i < length) {
            Method method = declaredMethods[i];
            if (!Modifier.isStatic(method.getModifiers()) || !cls.isAssignableFrom(method.getReturnType()) || ((JSONCreator) method.getAnnotation(JSONCreator.class)) == null) {
                i++;
            } else if (null == null) {
                return method;
            } else {
                throw new JSONException("multi-json creator");
            }
        }
        return null;
    }
}
