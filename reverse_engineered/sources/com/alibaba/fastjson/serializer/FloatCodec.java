package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;

public class FloatCodec implements ObjectDeserializer, ObjectSerializer {
    public static FloatCodec instance = new FloatCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj != null) {
            float floatValue = ((Float) obj).floatValue();
            if (Float.isNaN(floatValue)) {
                writer.writeNull();
            } else if (Float.isInfinite(floatValue)) {
                writer.writeNull();
            } else {
                String f = Float.toString(floatValue);
                if (f.endsWith(".0")) {
                    f = f.substring(0, f.length() - 2);
                }
                writer.write(f);
                if (jSONSerializer.isEnabled(SerializerFeature.WriteClassName)) {
                    writer.write('F');
                }
            }
        } else if (jSONSerializer.isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
            writer.write('0');
        } else {
            writer.writeNull();
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return deserialze(defaultJSONParser);
    }

    public static <T> T deserialze(DefaultJSONParser defaultJSONParser) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 2) {
            String numberString = lexer.numberString();
            lexer.nextToken(16);
            return Float.valueOf(Float.parseFloat(numberString));
        } else if (lexer.token() == 3) {
            float floatValue = lexer.floatValue();
            lexer.nextToken(16);
            return Float.valueOf(floatValue);
        } else {
            Object parse = defaultJSONParser.parse();
            if (parse == null) {
                return null;
            }
            return TypeUtils.castToFloat(parse);
        }
    }

    public int getFastMatchToken() {
        return 2;
    }
}
