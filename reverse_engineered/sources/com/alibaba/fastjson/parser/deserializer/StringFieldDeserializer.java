package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.Type;
import java.util.Map;

public class StringFieldDeserializer extends FieldDeserializer {
    private final ObjectDeserializer fieldValueDeserilizer;

    public StringFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
        this.fieldValueDeserilizer = parserConfig.getDeserializer(fieldInfo);
    }

    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        String stringVal;
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 4) {
            stringVal = lexer.stringVal();
            lexer.nextToken(16);
        } else {
            Object parse = defaultJSONParser.parse();
            if (parse == null) {
                stringVal = null;
            } else {
                stringVal = parse.toString();
            }
        }
        if (obj == null) {
            map.put(this.fieldInfo.getName(), stringVal);
        } else {
            setValue(obj, stringVal);
        }
    }

    public int getFastMatchToken() {
        return this.fieldValueDeserilizer.getFastMatchToken();
    }
}
