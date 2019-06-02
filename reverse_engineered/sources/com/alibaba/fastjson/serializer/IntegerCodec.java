package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class IntegerCodec implements ObjectDeserializer, ObjectSerializer {
    public static IntegerCodec instance = new IntegerCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        Number number = (Number) obj;
        if (number != null) {
            writer.writeInt(number.intValue());
        } else if (writer.isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
            writer.write('0');
        } else {
            writer.writeNull();
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 8) {
            lexer.nextToken(16);
            return null;
        }
        T valueOf;
        if (lexer.token() == 2) {
            int intValue = lexer.intValue();
            lexer.nextToken(16);
            valueOf = Integer.valueOf(intValue);
        } else if (lexer.token() == 3) {
            BigDecimal decimalValue = lexer.decimalValue();
            lexer.nextToken(16);
            valueOf = Integer.valueOf(decimalValue.intValue());
        } else {
            valueOf = TypeUtils.castToInt(defaultJSONParser.parse());
        }
        if (type == AtomicInteger.class) {
            return new AtomicInteger(valueOf.intValue());
        }
        return valueOf;
    }

    public int getFastMatchToken() {
        return 2;
    }
}
