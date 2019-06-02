package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ASMJavaBeanDeserializer implements ObjectDeserializer {
    protected InnerJavaBeanDeserializer serializer;

    public final class InnerJavaBeanDeserializer extends JavaBeanDeserializer {
        private InnerJavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls) {
            super(parserConfig, cls);
        }

        public boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
            return ASMJavaBeanDeserializer.this.parseField(defaultJSONParser, str, obj, type, map);
        }

        public FieldDeserializer createFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
            return ASMJavaBeanDeserializer.this.createFieldDeserializer(parserConfig, cls, fieldInfo);
        }
    }

    public abstract Object createInstance(DefaultJSONParser defaultJSONParser, Type type);

    public ASMJavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls) {
        this.serializer = new InnerJavaBeanDeserializer(parserConfig, cls);
        this.serializer.getFieldDeserializerMap();
    }

    public InnerJavaBeanDeserializer getInnterSerializer() {
        return this.serializer;
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return this.serializer.deserialze(defaultJSONParser, type, obj);
    }

    public int getFastMatchToken() {
        return this.serializer.getFastMatchToken();
    }

    public Object createInstance(DefaultJSONParser defaultJSONParser) {
        return this.serializer.createInstance(defaultJSONParser, this.serializer.getClazz());
    }

    public FieldDeserializer createFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        return parserConfig.createFieldDeserializer(parserConfig, cls, fieldInfo);
    }

    public FieldDeserializer getFieldDeserializer(String str) {
        return (FieldDeserializer) this.serializer.getFieldDeserializerMap().get(str);
    }

    public Type getFieldType(String str) {
        return ((FieldDeserializer) this.serializer.getFieldDeserializerMap().get(str)).getFieldType();
    }

    public boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        Map fieldDeserializerMap = this.serializer.getFieldDeserializerMap();
        FieldDeserializer fieldDeserializer = (FieldDeserializer) fieldDeserializerMap.get(str);
        if (fieldDeserializer == null) {
            for (Entry entry : fieldDeserializerMap.entrySet()) {
                if (((String) entry.getKey()).equalsIgnoreCase(str)) {
                    fieldDeserializer = (FieldDeserializer) entry.getValue();
                    break;
                }
            }
        }
        if (fieldDeserializer == null) {
            this.serializer.parseExtra(defaultJSONParser, obj, str);
            return false;
        }
        lexer.nextTokenWithColon(fieldDeserializer.getFastMatchToken());
        fieldDeserializer.parseField(defaultJSONParser, obj, type, map);
        return true;
    }

    public Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        return this.serializer.deserialze(defaultJSONParser, type, obj, obj2);
    }
}
