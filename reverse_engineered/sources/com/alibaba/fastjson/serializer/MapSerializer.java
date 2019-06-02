package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class MapSerializer implements ObjectSerializer {
    public static MapSerializer instance = new MapSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj == null) {
            writer.writeNull();
            return;
        }
        Map map = (Map) obj;
        Map map2;
        if (!writer.isEnabled(SerializerFeature.SortField) || (map instanceof SortedMap) || (map instanceof LinkedHashMap)) {
            map2 = map;
        } else {
            try {
                map2 = new TreeMap(map);
            } catch (Exception e) {
                map2 = map;
            }
        }
        if (jSONSerializer.containsReference(obj)) {
            jSONSerializer.writeReference(obj);
            return;
        }
        SerialContext context = jSONSerializer.getContext();
        jSONSerializer.setContext(context, obj, obj2);
        try {
            writer.write((char) CoreConstants.CURLY_LEFT);
            jSONSerializer.incrementIndent();
            Class cls = null;
            ObjectSerializer objectSerializer = null;
            Object obj3 = 1;
            if (writer.isEnabled(SerializerFeature.WriteClassName)) {
                writer.writeFieldName(JSON.DEFAULT_TYPE_KEY);
                writer.writeString(obj.getClass().getName());
                obj3 = null;
            }
            Object obj4 = obj3;
            for (Entry entry : r3.entrySet()) {
                Object processValue;
                String str;
                Class cls2;
                ObjectSerializer objectSerializer2;
                Class cls3;
                Object value = entry.getValue();
                Object key = entry.getKey();
                List propertyPreFiltersDirect = jSONSerializer.getPropertyPreFiltersDirect();
                if (propertyPreFiltersDirect != null && propertyPreFiltersDirect.size() > 0) {
                    if (key == null || (key instanceof String)) {
                        if (!FilterUtils.applyName(jSONSerializer, obj, (String) key)) {
                        }
                    } else if ((key.getClass().isPrimitive() || (key instanceof Number)) && !FilterUtils.applyName(jSONSerializer, obj, JSON.toJSONString(key))) {
                    }
                }
                propertyPreFiltersDirect = jSONSerializer.getPropertyFiltersDirect();
                if (propertyPreFiltersDirect != null && propertyPreFiltersDirect.size() > 0) {
                    if (key == null || (key instanceof String)) {
                        if (!FilterUtils.apply(jSONSerializer, obj, (String) key, value)) {
                        }
                    } else if ((key.getClass().isPrimitive() || (key instanceof Number)) && !FilterUtils.apply(jSONSerializer, obj, JSON.toJSONString(key), value)) {
                    }
                }
                propertyPreFiltersDirect = jSONSerializer.getNameFiltersDirect();
                if (propertyPreFiltersDirect != null && propertyPreFiltersDirect.size() > 0) {
                    if (key == null || (key instanceof String)) {
                        key = FilterUtils.processKey(jSONSerializer, obj, (String) key, value);
                    } else if (key.getClass().isPrimitive() || (key instanceof Number)) {
                        key = FilterUtils.processKey(jSONSerializer, obj, JSON.toJSONString(key), value);
                    }
                }
                propertyPreFiltersDirect = jSONSerializer.getValueFiltersDirect();
                if (propertyPreFiltersDirect != null && propertyPreFiltersDirect.size() > 0) {
                    if (key == null || (key instanceof String)) {
                        processValue = FilterUtils.processValue(jSONSerializer, obj, (String) key, value);
                        if (processValue == null || jSONSerializer.isEnabled(SerializerFeature.WriteMapNullValue)) {
                            if (key instanceof String) {
                                str = (String) key;
                                if (obj4 == null) {
                                    writer.write((char) CoreConstants.COMMA_CHAR);
                                }
                                if (writer.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                writer.writeFieldName(str, true);
                            } else {
                                if (obj4 == null) {
                                    writer.write((char) CoreConstants.COMMA_CHAR);
                                }
                                if (!writer.isEnabled(SerializerFeature.BrowserCompatible) || writer.isEnabled(SerializerFeature.WriteNonStringKeyAsString)) {
                                    jSONSerializer.write(JSON.toJSONString(key));
                                } else {
                                    jSONSerializer.write(key);
                                }
                                writer.write((char) CoreConstants.COLON_CHAR);
                            }
                            obj4 = null;
                            if (processValue == null) {
                                writer.writeNull();
                            } else {
                                cls2 = processValue.getClass();
                                if (cls2 == cls) {
                                    objectSerializer.write(jSONSerializer, processValue, key, null);
                                    objectSerializer2 = objectSerializer;
                                    cls3 = cls;
                                } else {
                                    objectSerializer2 = jSONSerializer.getObjectWriter(cls2);
                                    objectSerializer2.write(jSONSerializer, processValue, key, null);
                                    cls3 = cls2;
                                }
                                objectSerializer = objectSerializer2;
                                cls = cls3;
                            }
                        }
                    } else if (key.getClass().isPrimitive() || (key instanceof Number)) {
                        processValue = FilterUtils.processValue(jSONSerializer, obj, JSON.toJSONString(key), value);
                        if (processValue == null) {
                        }
                        if (key instanceof String) {
                            if (obj4 == null) {
                                writer.write((char) CoreConstants.COMMA_CHAR);
                            }
                            if (writer.isEnabled(SerializerFeature.BrowserCompatible)) {
                            }
                            jSONSerializer.write(JSON.toJSONString(key));
                            writer.write((char) CoreConstants.COLON_CHAR);
                        } else {
                            str = (String) key;
                            if (obj4 == null) {
                                writer.write((char) CoreConstants.COMMA_CHAR);
                            }
                            if (writer.isEnabled(SerializerFeature.PrettyFormat)) {
                                jSONSerializer.println();
                            }
                            writer.writeFieldName(str, true);
                        }
                        obj4 = null;
                        if (processValue == null) {
                            cls2 = processValue.getClass();
                            if (cls2 == cls) {
                                objectSerializer2 = jSONSerializer.getObjectWriter(cls2);
                                objectSerializer2.write(jSONSerializer, processValue, key, null);
                                cls3 = cls2;
                            } else {
                                objectSerializer.write(jSONSerializer, processValue, key, null);
                                objectSerializer2 = objectSerializer;
                                cls3 = cls;
                            }
                            objectSerializer = objectSerializer2;
                            cls = cls3;
                        } else {
                            writer.writeNull();
                        }
                    }
                }
                processValue = value;
                if (processValue == null) {
                }
                if (key instanceof String) {
                    str = (String) key;
                    if (obj4 == null) {
                        writer.write((char) CoreConstants.COMMA_CHAR);
                    }
                    if (writer.isEnabled(SerializerFeature.PrettyFormat)) {
                        jSONSerializer.println();
                    }
                    writer.writeFieldName(str, true);
                } else {
                    if (obj4 == null) {
                        writer.write((char) CoreConstants.COMMA_CHAR);
                    }
                    if (writer.isEnabled(SerializerFeature.BrowserCompatible)) {
                    }
                    jSONSerializer.write(JSON.toJSONString(key));
                    writer.write((char) CoreConstants.COLON_CHAR);
                }
                obj4 = null;
                if (processValue == null) {
                    writer.writeNull();
                } else {
                    cls2 = processValue.getClass();
                    if (cls2 == cls) {
                        objectSerializer.write(jSONSerializer, processValue, key, null);
                        objectSerializer2 = objectSerializer;
                        cls3 = cls;
                    } else {
                        objectSerializer2 = jSONSerializer.getObjectWriter(cls2);
                        objectSerializer2.write(jSONSerializer, processValue, key, null);
                        cls3 = cls2;
                    }
                    objectSerializer = objectSerializer2;
                    cls = cls3;
                }
            }
            jSONSerializer.decrementIdent();
            if (writer.isEnabled(SerializerFeature.PrettyFormat) && r3.size() > 0) {
                jSONSerializer.println();
            }
            writer.write((char) CoreConstants.CURLY_RIGHT);
        } finally {
            jSONSerializer.setContext(context);
        }
    }
}
