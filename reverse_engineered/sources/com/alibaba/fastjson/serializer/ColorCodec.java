package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.Type;

public class ColorCodec implements ObjectDeserializer, ObjectSerializer {
    public static final ColorCodec instance = new ColorCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        char c = CoreConstants.CURLY_LEFT;
        SerializeWriter writer = jSONSerializer.getWriter();
        Color color = (Color) obj;
        if (color == null) {
            writer.writeNull();
            return;
        }
        if (writer.isEnabled(SerializerFeature.WriteClassName)) {
            writer.write((char) CoreConstants.CURLY_LEFT);
            writer.writeFieldName(JSON.DEFAULT_TYPE_KEY);
            writer.writeString(Color.class.getName());
            c = CoreConstants.COMMA_CHAR;
        }
        writer.writeFieldValue(c, "r", color.getRed());
        writer.writeFieldValue((char) CoreConstants.COMMA_CHAR, "g", color.getGreen());
        writer.writeFieldValue((char) CoreConstants.COMMA_CHAR, "b", color.getBlue());
        if (color.getAlpha() > 0) {
            writer.writeFieldValue((char) CoreConstants.COMMA_CHAR, "alpha", color.getAlpha());
        }
        writer.write((char) CoreConstants.CURLY_RIGHT);
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        int i = 0;
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 12 || lexer.token() == 16) {
            lexer.nextToken();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (lexer.token() != 13) {
                if (lexer.token() == 4) {
                    String stringVal = lexer.stringVal();
                    lexer.nextTokenWithColon(2);
                    if (lexer.token() == 2) {
                        int intValue = lexer.intValue();
                        lexer.nextToken();
                        if (stringVal.equalsIgnoreCase("r")) {
                            i4 = intValue;
                        } else if (stringVal.equalsIgnoreCase("g")) {
                            i3 = intValue;
                        } else if (stringVal.equalsIgnoreCase("b")) {
                            i2 = intValue;
                        } else if (stringVal.equalsIgnoreCase("alpha")) {
                            i = intValue;
                        } else {
                            throw new JSONException("syntax error, " + stringVal);
                        }
                        if (lexer.token() == 16) {
                            lexer.nextToken(4);
                        }
                    } else {
                        throw new JSONException("syntax error");
                    }
                }
                throw new JSONException("syntax error");
            }
            lexer.nextToken();
            return new Color(i4, i3, i2, i);
        }
        throw new JSONException("syntax error");
    }

    public int getFastMatchToken() {
        return 12;
    }
}
