package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetAddress;

public class InetAddressCodec implements ObjectDeserializer, ObjectSerializer {
    public static InetAddressCodec instance = new InetAddressCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        if (obj == null) {
            jSONSerializer.writeNull();
        } else {
            jSONSerializer.write(((InetAddress) obj).getHostAddress());
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        String str = (String) defaultJSONParser.parse();
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return null;
        }
        try {
            return InetAddress.getByName(str);
        } catch (Throwable e) {
            throw new JSONException("deserialize error", e);
        }
    }

    public int getFastMatchToken() {
        return 4;
    }
}
