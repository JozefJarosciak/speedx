package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.awt.Font;
import java.io.IOException;
import java.lang.reflect.Type;

public class FontCodec implements ObjectDeserializer, ObjectSerializer {
    public static final FontCodec instance = new FontCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        char c = CoreConstants.CURLY_LEFT;
        SerializeWriter writer = jSONSerializer.getWriter();
        Font font = (Font) obj;
        if (font == null) {
            writer.writeNull();
            return;
        }
        if (writer.isEnabled(SerializerFeature.WriteClassName)) {
            writer.write((char) CoreConstants.CURLY_LEFT);
            writer.writeFieldName(JSON.DEFAULT_TYPE_KEY);
            writer.writeString(Font.class.getName());
            c = CoreConstants.COMMA_CHAR;
        }
        writer.writeFieldValue(c, "name", font.getName());
        writer.writeFieldValue((char) CoreConstants.COMMA_CHAR, "style", font.getStyle());
        writer.writeFieldValue((char) CoreConstants.COMMA_CHAR, "size", font.getSize());
        writer.write((char) CoreConstants.CURLY_RIGHT);
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        String str = null;
        int i = 0;
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 8) {
            lexer.nextToken(16);
            return null;
        } else if (lexer.token() == 12 || lexer.token() == 16) {
            lexer.nextToken();
            int i2 = 0;
            while (lexer.token() != 13) {
                if (lexer.token() == 4) {
                    String stringVal = lexer.stringVal();
                    lexer.nextTokenWithColon(2);
                    if (stringVal.equalsIgnoreCase("name")) {
                        if (lexer.token() == 4) {
                            str = lexer.stringVal();
                            lexer.nextToken();
                        } else {
                            throw new JSONException("syntax error");
                        }
                    } else if (stringVal.equalsIgnoreCase("style")) {
                        if (lexer.token() == 2) {
                            i = lexer.intValue();
                            lexer.nextToken();
                        } else {
                            throw new JSONException("syntax error");
                        }
                    } else if (!stringVal.equalsIgnoreCase("size")) {
                        throw new JSONException("syntax error, " + stringVal);
                    } else if (lexer.token() == 2) {
                        i2 = lexer.intValue();
                        lexer.nextToken();
                    } else {
                        throw new JSONException("syntax error");
                    }
                    if (lexer.token() == 16) {
                        lexer.nextToken(4);
                    }
                } else {
                    throw new JSONException("syntax error");
                }
            }
            lexer.nextToken();
            return new Font(str, i, i2);
        } else {
            throw new JSONException("syntax error");
        }
    }

    public int getFastMatchToken() {
        return 12;
    }
}
