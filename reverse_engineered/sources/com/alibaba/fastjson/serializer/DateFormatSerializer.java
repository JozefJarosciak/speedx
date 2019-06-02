package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class DateFormatSerializer implements ObjectSerializer {
    public static final DateFormatSerializer instance = new DateFormatSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj == null) {
            writer.writeNull();
            return;
        }
        String toPattern = ((SimpleDateFormat) obj).toPattern();
        if (!writer.isEnabled(SerializerFeature.WriteClassName) || obj.getClass() == type) {
            writer.writeString(toPattern);
            return;
        }
        writer.write((char) CoreConstants.CURLY_LEFT);
        writer.writeFieldName(JSON.DEFAULT_TYPE_KEY);
        jSONSerializer.write(obj.getClass().getName());
        writer.writeFieldValue((char) CoreConstants.COMMA_CHAR, "val", toPattern);
        writer.write((char) CoreConstants.CURLY_RIGHT);
    }
}
