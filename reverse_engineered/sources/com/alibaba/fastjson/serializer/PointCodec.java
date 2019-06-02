package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.awt.Point;
import java.io.IOException;
import java.lang.reflect.Type;

public class PointCodec implements ObjectDeserializer, ObjectSerializer {
    public static final PointCodec instance = new PointCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        char c = CoreConstants.CURLY_LEFT;
        SerializeWriter writer = jSONSerializer.getWriter();
        Point point = (Point) obj;
        if (point == null) {
            writer.writeNull();
            return;
        }
        if (writer.isEnabled(SerializerFeature.WriteClassName)) {
            writer.write((char) CoreConstants.CURLY_LEFT);
            writer.writeFieldName(JSON.DEFAULT_TYPE_KEY);
            writer.writeString(Point.class.getName());
            c = CoreConstants.COMMA_CHAR;
        }
        writer.writeFieldValue(c, "x", point.getX());
        writer.writeFieldValue((char) CoreConstants.COMMA_CHAR, "y", point.getY());
        writer.write((char) CoreConstants.CURLY_RIGHT);
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
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
                    if (JSON.DEFAULT_TYPE_KEY.equals(stringVal)) {
                        defaultJSONParser.acceptType("java.awt.Point");
                    } else {
                        lexer.nextTokenWithColon(2);
                        if (lexer.token() == 2) {
                            int intValue = lexer.intValue();
                            lexer.nextToken();
                            if (stringVal.equalsIgnoreCase("x")) {
                                i2 = intValue;
                            } else if (stringVal.equalsIgnoreCase("y")) {
                                i = intValue;
                            } else {
                                throw new JSONException("syntax error, " + stringVal);
                            }
                            if (lexer.token() == 16) {
                                lexer.nextToken(4);
                            }
                        } else {
                            throw new JSONException("syntax error : " + lexer.tokenName());
                        }
                    }
                }
                throw new JSONException("syntax error");
            }
            lexer.nextToken();
            return new Point(i2, i);
        } else {
            throw new JSONException("syntax error");
        }
    }

    public int getFastMatchToken() {
        return 12;
    }
}
