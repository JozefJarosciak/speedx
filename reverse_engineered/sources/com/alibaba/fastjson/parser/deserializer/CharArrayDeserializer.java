package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import java.lang.reflect.Type;

public class CharArrayDeserializer implements ObjectDeserializer {
    public static final CharArrayDeserializer instance = new CharArrayDeserializer();

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return deserialze(defaultJSONParser);
    }

    public static <T> T deserialze(DefaultJSONParser defaultJSONParser) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 4) {
            String stringVal = lexer.stringVal();
            lexer.nextToken(16);
            return stringVal.toCharArray();
        } else if (lexer.token() == 2) {
            Number integerValue = lexer.integerValue();
            lexer.nextToken(16);
            return integerValue.toString().toCharArray();
        } else {
            Object parse = defaultJSONParser.parse();
            if (parse == null) {
                return null;
            }
            return JSON.toJSONString(parse).toCharArray();
        }
    }

    public int getFastMatchToken() {
        return 4;
    }
}
