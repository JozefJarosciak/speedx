package com.google.gson;

import ch.qos.logback.core.CoreConstants;
import com.google.gson.internal.bind.util.ISO8601Utils;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

final class DefaultDateTypeAdapter implements JsonDeserializer<Date>, JsonSerializer<Date> {
    private final DateFormat enUsFormat;
    private final DateFormat localFormat;

    DefaultDateTypeAdapter() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    DefaultDateTypeAdapter(String str) {
        this(new SimpleDateFormat(str, Locale.US), new SimpleDateFormat(str));
    }

    DefaultDateTypeAdapter(int i) {
        this(DateFormat.getDateInstance(i, Locale.US), DateFormat.getDateInstance(i));
    }

    public DefaultDateTypeAdapter(int i, int i2) {
        this(DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    DefaultDateTypeAdapter(DateFormat dateFormat, DateFormat dateFormat2) {
        this.enUsFormat = dateFormat;
        this.localFormat = dateFormat2;
    }

    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonPrimitive;
        synchronized (this.localFormat) {
            jsonPrimitive = new JsonPrimitive(this.enUsFormat.format(date));
        }
        return jsonPrimitive;
    }

    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement instanceof JsonPrimitive) {
            Date deserializeToDate = deserializeToDate(jsonElement);
            if (type == Date.class) {
                return deserializeToDate;
            }
            if (type == Timestamp.class) {
                return new Timestamp(deserializeToDate.getTime());
            }
            if (type == java.sql.Date.class) {
                return new java.sql.Date(deserializeToDate.getTime());
            }
            throw new IllegalArgumentException(getClass() + " cannot deserialize to " + type);
        }
        throw new JsonParseException("The date should be a string value");
    }

    private Date deserializeToDate(JsonElement jsonElement) {
        Date parse;
        synchronized (this.localFormat) {
            try {
                parse = this.localFormat.parse(jsonElement.getAsString());
            } catch (ParseException e) {
                try {
                    parse = this.enUsFormat.parse(jsonElement.getAsString());
                } catch (ParseException e2) {
                    try {
                        parse = ISO8601Utils.parse(jsonElement.getAsString(), new ParsePosition(0));
                    } catch (Throwable e3) {
                        throw new JsonSyntaxException(jsonElement.getAsString(), e3);
                    }
                }
            }
        }
        return parse;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DefaultDateTypeAdapter.class.getSimpleName());
        stringBuilder.append(CoreConstants.LEFT_PARENTHESIS_CHAR).append(this.localFormat.getClass().getSimpleName()).append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
        return stringBuilder.toString();
    }
}
