package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateSerializer implements ObjectSerializer {
    public static final DateSerializer instance = new DateSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj == null) {
            writer.writeNull();
        } else if (!writer.isEnabled(SerializerFeature.WriteClassName) || obj.getClass() == type) {
            Date date = (Date) obj;
            if (writer.isEnabled(SerializerFeature.WriteDateUseDateFormat)) {
                DateFormat dateFormat = jSONSerializer.getDateFormat();
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(JSON.DEFFAULT_DATE_FORMAT);
                }
                writer.writeString(dateFormat.format(date));
                return;
            }
            long time = date.getTime();
            if (jSONSerializer.isEnabled(SerializerFeature.UseISO8601DateFormat)) {
                char[] toCharArray;
                if (jSONSerializer.isEnabled(SerializerFeature.UseSingleQuotes)) {
                    writer.append((char) CoreConstants.SINGLE_QUOTE_CHAR);
                } else {
                    writer.append((char) CoreConstants.DOUBLE_QUOTE_CHAR);
                }
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(time);
                int i = instance.get(1);
                int i2 = instance.get(2) + 1;
                int i3 = instance.get(5);
                int i4 = instance.get(11);
                int i5 = instance.get(12);
                int i6 = instance.get(13);
                int i7 = instance.get(14);
                if (i7 != 0) {
                    toCharArray = "0000-00-00T00:00:00.000".toCharArray();
                    IOUtils.getChars(i7, 23, toCharArray);
                    IOUtils.getChars(i6, 19, toCharArray);
                    IOUtils.getChars(i5, 16, toCharArray);
                    IOUtils.getChars(i4, 13, toCharArray);
                    IOUtils.getChars(i3, 10, toCharArray);
                    IOUtils.getChars(i2, 7, toCharArray);
                    IOUtils.getChars(i, 4, toCharArray);
                } else if (i6 == 0 && i5 == 0 && i4 == 0) {
                    toCharArray = "0000-00-00".toCharArray();
                    IOUtils.getChars(i3, 10, toCharArray);
                    IOUtils.getChars(i2, 7, toCharArray);
                    IOUtils.getChars(i, 4, toCharArray);
                } else {
                    toCharArray = "0000-00-00T00:00:00".toCharArray();
                    IOUtils.getChars(i6, 19, toCharArray);
                    IOUtils.getChars(i5, 16, toCharArray);
                    IOUtils.getChars(i4, 13, toCharArray);
                    IOUtils.getChars(i3, 10, toCharArray);
                    IOUtils.getChars(i2, 7, toCharArray);
                    IOUtils.getChars(i, 4, toCharArray);
                }
                writer.write(toCharArray);
                if (jSONSerializer.isEnabled(SerializerFeature.UseSingleQuotes)) {
                    writer.append((char) CoreConstants.SINGLE_QUOTE_CHAR);
                    return;
                } else {
                    writer.append((char) CoreConstants.DOUBLE_QUOTE_CHAR);
                    return;
                }
            }
            writer.writeLong(time);
        } else if (obj.getClass() == Date.class) {
            writer.write("new Date(");
            writer.writeLongAndChar(((Date) obj).getTime(), CoreConstants.RIGHT_PARENTHESIS_CHAR);
        } else {
            writer.write((char) CoreConstants.CURLY_LEFT);
            writer.writeFieldName(JSON.DEFAULT_TYPE_KEY);
            jSONSerializer.write(obj.getClass().getName());
            writer.writeFieldValue((char) CoreConstants.COMMA_CHAR, "val", ((Date) obj).getTime());
            writer.write((char) CoreConstants.CURLY_RIGHT);
        }
    }
}
