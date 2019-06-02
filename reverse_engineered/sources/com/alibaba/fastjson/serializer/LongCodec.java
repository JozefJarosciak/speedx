package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLong;

public class LongCodec implements ObjectDeserializer, ObjectSerializer {
    public static LongCodec instance = new LongCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj != null) {
            long longValue = ((Long) obj).longValue();
            writer.writeLong(longValue);
            if (jSONSerializer.isEnabled(SerializerFeature.WriteClassName) && longValue <= 2147483647L && longValue >= -2147483648L && type != Long.class) {
                writer.write('L');
            }
        } else if (writer.isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
            writer.write('0');
        } else {
            writer.writeNull();
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T valueOf;
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 2) {
            long longValue = lexer.longValue();
            lexer.nextToken(16);
            valueOf = Long.valueOf(longValue);
        } else {
            Object parse = defaultJSONParser.parse();
            if (parse == null) {
                return null;
            }
            valueOf = TypeUtils.castToLong(parse);
        }
        if (type == AtomicLong.class) {
            return new AtomicLong(valueOf.longValue());
        }
        return valueOf;
    }

    public int getFastMatchToken() {
        return 2;
    }
}
