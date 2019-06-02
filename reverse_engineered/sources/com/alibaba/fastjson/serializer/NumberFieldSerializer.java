package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.util.FieldInfo;

final class NumberFieldSerializer extends FieldSerializer {
    public NumberFieldSerializer(FieldInfo fieldInfo) {
        super(fieldInfo);
    }

    public void writeProperty(JSONSerializer jSONSerializer, Object obj) throws Exception {
        writePrefix(jSONSerializer);
        writeValue(jSONSerializer, obj);
    }

    public void writeValue(JSONSerializer jSONSerializer, Object obj) throws Exception {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj != null) {
            writer.append(obj.toString());
        } else if (writer.isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
            writer.write('0');
        } else {
            writer.writeNull();
        }
    }
}
