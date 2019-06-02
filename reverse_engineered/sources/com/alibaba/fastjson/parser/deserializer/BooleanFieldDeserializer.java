package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Map;

public class BooleanFieldDeserializer extends FieldDeserializer {
    public BooleanFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
    }

    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        boolean z = true;
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 6) {
            lexer.nextToken(16);
            if (obj == null) {
                map.put(this.fieldInfo.getName(), Boolean.TRUE);
            } else {
                setValue(obj, true);
            }
        } else if (lexer.token() == 2) {
            int intValue = lexer.intValue();
            lexer.nextToken(16);
            if (intValue != 1) {
                z = false;
            }
            if (obj == null) {
                map.put(this.fieldInfo.getName(), Boolean.valueOf(z));
            } else {
                setValue(obj, z);
            }
        } else if (lexer.token() == 8) {
            lexer.nextToken(16);
            if (getFieldClass() != Boolean.TYPE && obj != null) {
                setValue(obj, null);
            }
        } else if (lexer.token() == 7) {
            lexer.nextToken(16);
            if (obj == null) {
                map.put(this.fieldInfo.getName(), Boolean.FALSE);
            } else {
                setValue(obj, false);
            }
        } else {
            Boolean castToBoolean = TypeUtils.castToBoolean(defaultJSONParser.parse());
            if (castToBoolean != null || getFieldClass() != Boolean.TYPE) {
                if (obj == null) {
                    map.put(this.fieldInfo.getName(), castToBoolean);
                } else {
                    setValue(obj, (Object) castToBoolean);
                }
            }
        }
    }

    public int getFastMatchToken() {
        return 6;
    }
}
