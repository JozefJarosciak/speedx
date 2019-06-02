package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Map;

public class LongFieldDeserializer extends FieldDeserializer {
    private final ObjectDeserializer fieldValueDeserilizer;

    public LongFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
        this.fieldValueDeserilizer = parserConfig.getDeserializer(fieldInfo);
    }

    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 2) {
            long longValue = lexer.longValue();
            lexer.nextToken(16);
            if (obj == null) {
                map.put(this.fieldInfo.getName(), Long.valueOf(longValue));
                return;
            } else {
                setValue(obj, longValue);
                return;
            }
        }
        Object obj2;
        if (lexer.token() == 8) {
            obj2 = null;
            lexer.nextToken(16);
        } else {
            obj2 = TypeUtils.castToLong(defaultJSONParser.parse());
        }
        if (obj2 != null || getFieldClass() != Long.TYPE) {
            if (obj == null) {
                map.put(this.fieldInfo.getName(), obj2);
            } else {
                setValue(obj, obj2);
            }
        }
    }

    public int getFastMatchToken() {
        return this.fieldValueDeserilizer.getFastMatchToken();
    }
}
