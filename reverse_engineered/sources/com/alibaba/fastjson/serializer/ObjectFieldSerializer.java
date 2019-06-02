package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.util.FieldInfo;
import java.util.Collection;

public class ObjectFieldSerializer extends FieldSerializer {
    private ObjectSerializer fieldSerializer;
    private String format;
    private Class<?> runtimeFieldClass;
    boolean writeEnumUsingToString = false;
    boolean writeNullBooleanAsFalse = false;
    boolean writeNullListAsEmpty = false;
    boolean writeNullStringAsEmpty = false;
    private boolean writeNumberAsZero = false;

    public ObjectFieldSerializer(FieldInfo fieldInfo) {
        super(fieldInfo);
        JSONField jSONField = (JSONField) fieldInfo.getAnnotation(JSONField.class);
        if (jSONField != null) {
            this.format = jSONField.format();
            if (this.format.trim().length() == 0) {
                this.format = null;
            }
            for (SerializerFeature serializerFeature : jSONField.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.WriteNullNumberAsZero) {
                    this.writeNumberAsZero = true;
                } else if (serializerFeature == SerializerFeature.WriteNullStringAsEmpty) {
                    this.writeNullStringAsEmpty = true;
                } else if (serializerFeature == SerializerFeature.WriteNullBooleanAsFalse) {
                    this.writeNullBooleanAsFalse = true;
                } else if (serializerFeature == SerializerFeature.WriteNullListAsEmpty) {
                    this.writeNullListAsEmpty = true;
                } else if (serializerFeature == SerializerFeature.WriteEnumUsingToString) {
                    this.writeEnumUsingToString = true;
                }
            }
        }
    }

    public void writeProperty(JSONSerializer jSONSerializer, Object obj) throws Exception {
        writePrefix(jSONSerializer);
        writeValue(jSONSerializer, obj);
    }

    public void writeValue(JSONSerializer jSONSerializer, Object obj) throws Exception {
        if (this.format != null) {
            jSONSerializer.writeWithFormat(obj, this.format);
            return;
        }
        if (this.fieldSerializer == null) {
            if (obj == null) {
                this.runtimeFieldClass = this.fieldInfo.getFieldClass();
            } else {
                this.runtimeFieldClass = obj.getClass();
            }
            this.fieldSerializer = jSONSerializer.getObjectWriter(this.runtimeFieldClass);
        }
        if (obj == null) {
            if (this.writeNumberAsZero && Number.class.isAssignableFrom(this.runtimeFieldClass)) {
                jSONSerializer.getWriter().write('0');
            } else if (this.writeNullStringAsEmpty && String.class == this.runtimeFieldClass) {
                jSONSerializer.getWriter().write("\"\"");
            } else if (this.writeNullBooleanAsFalse && Boolean.class == this.runtimeFieldClass) {
                jSONSerializer.getWriter().write("false");
            } else if (this.writeNullListAsEmpty && Collection.class.isAssignableFrom(this.runtimeFieldClass)) {
                jSONSerializer.getWriter().write("[]");
            } else {
                this.fieldSerializer.write(jSONSerializer, null, this.fieldInfo.getName(), null);
            }
        } else if (this.writeEnumUsingToString && this.runtimeFieldClass.isEnum()) {
            jSONSerializer.getWriter().writeString(((Enum) obj).name());
        } else {
            Class cls = obj.getClass();
            if (cls == this.runtimeFieldClass) {
                this.fieldSerializer.write(jSONSerializer, obj, this.fieldInfo.getName(), this.fieldInfo.getFieldType());
            } else {
                jSONSerializer.getObjectWriter(cls).write(jSONSerializer, obj, this.fieldInfo.getName(), this.fieldInfo.getFieldType());
            }
        }
    }
}
