package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

public class JSONLibDataFormatSerializer implements ObjectSerializer {
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        if (obj == null) {
            jSONSerializer.getWriter().writeNull();
            return;
        }
        Date date = (Date) obj;
        Object jSONObject = new JSONObject();
        jSONObject.put("date", Integer.valueOf(date.getDate()));
        jSONObject.put("day", Integer.valueOf(date.getDay()));
        jSONObject.put("hours", Integer.valueOf(date.getHours()));
        jSONObject.put("minutes", Integer.valueOf(date.getMinutes()));
        jSONObject.put("month", Integer.valueOf(date.getMonth()));
        jSONObject.put("seconds", Integer.valueOf(date.getSeconds()));
        jSONObject.put("time", Long.valueOf(date.getTime()));
        jSONObject.put("timezoneOffset", Integer.valueOf(date.getTimezoneOffset()));
        jSONObject.put("year", Integer.valueOf(date.getYear()));
        jSONSerializer.write(jSONObject);
    }
}
