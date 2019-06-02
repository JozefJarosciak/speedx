package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class EnumDeserializer implements ObjectDeserializer {
    private final Class<?> enumClass;
    private final Map<String, Enum> nameMap = new HashMap();
    private final Map<Integer, Enum> ordinalMap = new HashMap();

    public EnumDeserializer(Class<?> cls) {
        this.enumClass = cls;
        try {
            for (Object obj : (Object[]) cls.getMethod("values", new Class[0]).invoke(null, new Object[0])) {
                Enum enumR = (Enum) obj;
                this.ordinalMap.put(Integer.valueOf(enumR.ordinal()), enumR);
                this.nameMap.put(enumR.name(), enumR);
            }
        } catch (Exception e) {
            throw new JSONException("init enum values error, " + cls.getName());
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        try {
            JSONLexer lexer = defaultJSONParser.getLexer();
            if (lexer.token() == 2) {
                Integer valueOf = Integer.valueOf(lexer.intValue());
                lexer.nextToken(16);
                T t = this.ordinalMap.get(valueOf);
                if (t != null) {
                    return t;
                }
                throw new JSONException("parse enum " + this.enumClass.getName() + " error, value : " + valueOf);
            } else if (lexer.token() == 4) {
                String stringVal = lexer.stringVal();
                lexer.nextToken(16);
                if (stringVal.length() == 0) {
                    return null;
                }
                this.nameMap.get(stringVal);
                return Enum.valueOf(this.enumClass, stringVal);
            } else if (lexer.token() == 8) {
                lexer.nextToken(16);
                return null;
            } else {
                throw new JSONException("parse enum " + this.enumClass.getName() + " error, value : " + defaultJSONParser.parse());
            }
        } catch (JSONException e) {
            throw e;
        } catch (Throwable th) {
            JSONException jSONException = new JSONException(th.getMessage(), th);
        }
    }

    public int getFastMatchToken() {
        return 2;
    }
}
