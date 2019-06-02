package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

public class FileCodec implements ObjectDeserializer, ObjectSerializer {
    public static FileCodec instance = new FileCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj == null) {
            writer.writeNull();
        } else {
            jSONSerializer.write(((File) obj).getPath());
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Object parse = defaultJSONParser.parse();
        if (parse == null) {
            return null;
        }
        return new File((String) parse);
    }

    public int getFastMatchToken() {
        return 4;
    }
}
