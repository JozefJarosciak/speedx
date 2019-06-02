package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaBeanSerializer implements ObjectSerializer {
    private final FieldSerializer[] getters;
    private final FieldSerializer[] sortedGetters;

    public FieldSerializer[] getGetters() {
        return this.getters;
    }

    public JavaBeanSerializer(Class<?> cls) {
        this((Class) cls, (Map) null);
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this((Class) cls, createAliasMap(strArr));
    }

    static Map<String, String> createAliasMap(String... strArr) {
        Map<String, String> hashMap = new HashMap();
        for (Object obj : strArr) {
            hashMap.put(obj, obj);
        }
        return hashMap;
    }

    public JavaBeanSerializer(Class<?> cls, Map<String, String> map) {
        List arrayList = new ArrayList();
        for (FieldInfo createFieldSerializer : TypeUtils.computeGetters(cls, map, false)) {
            arrayList.add(createFieldSerializer(createFieldSerializer));
        }
        this.getters = (FieldSerializer[]) arrayList.toArray(new FieldSerializer[arrayList.size()]);
        arrayList = new ArrayList();
        for (FieldInfo createFieldSerializer2 : TypeUtils.computeGetters(cls, map, true)) {
            arrayList.add(createFieldSerializer(createFieldSerializer2));
        }
        this.sortedGetters = (FieldSerializer[]) arrayList.toArray(new FieldSerializer[arrayList.size()]);
    }

    protected boolean isWriteClassName(JSONSerializer jSONSerializer, Object obj, Type type, Object obj2) {
        return jSONSerializer.isWriteClassName(type, obj);
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj == null) {
            writer.writeNull();
        } else if (jSONSerializer.containsReference(obj)) {
            writeReference(jSONSerializer, obj);
        } else {
            FieldSerializer[] fieldSerializerArr;
            if (writer.isEnabled(SerializerFeature.SortField)) {
                fieldSerializerArr = this.sortedGetters;
            } else {
                fieldSerializerArr = this.getters;
            }
            SerialContext context = jSONSerializer.getContext();
            jSONSerializer.setContext(context, obj, obj2);
            boolean isWriteAsArray = jSONSerializer.isWriteAsArray(obj, type);
            char c = isWriteAsArray ? '[' : CoreConstants.CURLY_LEFT;
            char c2 = isWriteAsArray ? ']' : CoreConstants.CURLY_RIGHT;
            try {
                writer.append(c);
                if (fieldSerializerArr.length > 0 && writer.isEnabled(SerializerFeature.PrettyFormat)) {
                    jSONSerializer.incrementIndent();
                    jSONSerializer.println();
                }
                Object obj3 = null;
                if (isWriteClassName(jSONSerializer, obj, type, obj2) && obj.getClass() != type) {
                    writer.writeFieldName(JSON.DEFAULT_TYPE_KEY);
                    jSONSerializer.write(obj.getClass());
                    obj3 = 1;
                }
                obj3 = FilterUtils.writeBefore(jSONSerializer, obj, obj3 != null ? CoreConstants.COMMA_CHAR : '\u0000') == CoreConstants.COMMA_CHAR ? 1 : null;
                for (FieldSerializer fieldSerializer : fieldSerializerArr) {
                    if (jSONSerializer.isEnabled(SerializerFeature.SkipTransientField)) {
                        Field field = fieldSerializer.getField();
                        if (field != null && Modifier.isTransient(field.getModifiers())) {
                        }
                    }
                    if (FilterUtils.applyName(jSONSerializer, obj, fieldSerializer.getName())) {
                        Object propertyValue = fieldSerializer.getPropertyValue(obj);
                        if (FilterUtils.apply(jSONSerializer, obj, fieldSerializer.getName(), propertyValue)) {
                            String processKey = FilterUtils.processKey(jSONSerializer, obj, fieldSerializer.getName(), propertyValue);
                            Object processValue = FilterUtils.processValue(jSONSerializer, obj, fieldSerializer.getName(), propertyValue);
                            if (processValue != null || isWriteAsArray || fieldSerializer.isWriteNull() || jSONSerializer.isEnabled(SerializerFeature.WriteMapNullValue)) {
                                if (obj3 != null) {
                                    writer.append((char) CoreConstants.COMMA_CHAR);
                                    if (writer.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                }
                                if (processKey != fieldSerializer.getName()) {
                                    if (!isWriteAsArray) {
                                        writer.writeFieldName(processKey);
                                    }
                                    jSONSerializer.write(processValue);
                                } else if (propertyValue != processValue) {
                                    if (!isWriteAsArray) {
                                        fieldSerializer.writePrefix(jSONSerializer);
                                    }
                                    jSONSerializer.write(processValue);
                                } else if (isWriteAsArray) {
                                    fieldSerializer.writeValue(jSONSerializer, processValue);
                                } else {
                                    fieldSerializer.writeProperty(jSONSerializer, processValue);
                                }
                                obj3 = 1;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
                FilterUtils.writeAfter(jSONSerializer, obj, obj3 != null ? CoreConstants.COMMA_CHAR : '\u0000');
                if (fieldSerializerArr.length > 0 && writer.isEnabled(SerializerFeature.PrettyFormat)) {
                    jSONSerializer.decrementIdent();
                    jSONSerializer.println();
                }
                writer.append(c2);
                jSONSerializer.setContext(context);
            } catch (Throwable e) {
                throw new JSONException("write javaBean error", e);
            } catch (Throwable th) {
                jSONSerializer.setContext(context);
            }
        }
    }

    public void writeReference(JSONSerializer jSONSerializer, Object obj) {
        jSONSerializer.writeReference(obj);
    }

    public FieldSerializer createFieldSerializer(FieldInfo fieldInfo) {
        if (fieldInfo.getFieldClass() == Number.class) {
            return new NumberFieldSerializer(fieldInfo);
        }
        return new ObjectFieldSerializer(fieldInfo);
    }
}
