package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.IOException;
import java.lang.reflect.Type;

public class StringCodec implements ObjectDeserializer, ObjectSerializer {
    public static StringCodec instance = new StringCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        write(jSONSerializer, (String) obj);
    }

    public void write(JSONSerializer jSONSerializer, String str) {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (str != null) {
            writer.writeString(str);
        } else if (writer.isEnabled(SerializerFeature.WriteNullStringAsEmpty)) {
            writer.writeString("");
        } else {
            writer.writeNull();
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return deserialze(defaultJSONParser);
    }

    public static <T> T deserialze(DefaultJSONParser defaultJSONParser) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        T stringVal;
        if (lexer.token() == 4) {
            stringVal = lexer.stringVal();
            lexer.nextToken(16);
            return stringVal;
        } else if (lexer.token() == 2) {
            stringVal = lexer.numberString();
            lexer.nextToken(16);
            return stringVal;
        } else {
            Object parse = defaultJSONParser.parse();
            if (parse == null) {
                return null;
            }
            return parse.toString();
        }
    }

    public int getFastMatchToken() {
        return 4;
    }
}
