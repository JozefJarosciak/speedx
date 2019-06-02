package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.util.FieldInfo;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class FieldSerializer {
    private final String double_quoted_fieldPrefix;
    protected final FieldInfo fieldInfo;
    private final String single_quoted_fieldPrefix;
    private final String un_quoted_fieldPrefix;
    private boolean writeNull = false;

    public abstract void writeProperty(JSONSerializer jSONSerializer, Object obj) throws Exception;

    public abstract void writeValue(JSONSerializer jSONSerializer, Object obj) throws Exception;

    public FieldSerializer(FieldInfo fieldInfo) {
        this.fieldInfo = fieldInfo;
        fieldInfo.setAccessible(true);
        this.double_quoted_fieldPrefix = CoreConstants.DOUBLE_QUOTE_CHAR + fieldInfo.getName() + "\":";
        this.single_quoted_fieldPrefix = CoreConstants.SINGLE_QUOTE_CHAR + fieldInfo.getName() + "':";
        this.un_quoted_fieldPrefix = fieldInfo.getName() + ":";
        JSONField jSONField = (JSONField) fieldInfo.getAnnotation(JSONField.class);
        if (jSONField != null) {
            for (SerializerFeature serializerFeature : jSONField.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.WriteMapNullValue) {
                    this.writeNull = true;
                }
            }
        }
    }

    public boolean isWriteNull() {
        return this.writeNull;
    }

    public Field getField() {
        return this.fieldInfo.getField();
    }

    public String getName() {
        return this.fieldInfo.getName();
    }

    public Method getMethod() {
        return this.fieldInfo.getMethod();
    }

    public void writePrefix(JSONSerializer jSONSerializer) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (!jSONSerializer.isEnabled(SerializerFeature.QuoteFieldNames)) {
            writer.write(this.un_quoted_fieldPrefix);
        } else if (jSONSerializer.isEnabled(SerializerFeature.UseSingleQuotes)) {
            writer.write(this.single_quoted_fieldPrefix);
        } else {
            writer.write(this.double_quoted_fieldPrefix);
        }
    }

    public Object getPropertyValue(Object obj) throws Exception {
        return this.fieldInfo.get(obj);
    }
}
