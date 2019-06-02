package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;

public abstract class AbstractDateDeserializer implements ObjectDeserializer {
    protected abstract <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2);

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Object valueOf;
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 2) {
            valueOf = Long.valueOf(lexer.longValue());
            lexer.nextToken(16);
        } else if (lexer.token() == 4) {
            valueOf = lexer.stringVal();
            lexer.nextToken(16);
            if (lexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                JSONScanner jSONScanner = new JSONScanner(valueOf);
                if (jSONScanner.scanISO8601DateIfMatch()) {
                    valueOf = jSONScanner.getCalendar().getTime();
                }
                jSONScanner.close();
            }
        } else if (lexer.token() == 8) {
            lexer.nextToken();
            valueOf = null;
        } else if (lexer.token() == 12) {
            lexer.nextToken();
            if (lexer.token() == 4) {
                if (JSON.DEFAULT_TYPE_KEY.equals(lexer.stringVal())) {
                    lexer.nextToken();
                    defaultJSONParser.accept(17);
                    Class loadClass = TypeUtils.loadClass(lexer.stringVal());
                    if (loadClass != null) {
                        type = loadClass;
                    }
                    defaultJSONParser.accept(4);
                    defaultJSONParser.accept(16);
                }
                lexer.nextTokenWithColon(2);
                if (lexer.token() == 2) {
                    long longValue = lexer.longValue();
                    lexer.nextToken();
                    valueOf = Long.valueOf(longValue);
                    defaultJSONParser.accept(13);
                } else {
                    throw new JSONException("syntax error : " + lexer.tokenName());
                }
            }
            throw new JSONException("syntax error");
        } else if (defaultJSONParser.getResolveStatus() == 2) {
            defaultJSONParser.setResolveStatus(0);
            defaultJSONParser.accept(16);
            if (lexer.token() != 4) {
                throw new JSONException("syntax error");
            } else if ("val".equals(lexer.stringVal())) {
                lexer.nextToken();
                defaultJSONParser.accept(17);
                valueOf = defaultJSONParser.parse();
                defaultJSONParser.accept(13);
            } else {
                throw new JSONException("syntax error");
            }
        } else {
            valueOf = defaultJSONParser.parse();
        }
        return cast(defaultJSONParser, type, obj, valueOf);
    }
}
