package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class InetSocketAddressCodec implements ObjectDeserializer, ObjectSerializer {
    public static InetSocketAddressCodec instance = new InetSocketAddressCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        if (obj == null) {
            jSONSerializer.writeNull();
            return;
        }
        SerializeWriter writer = jSONSerializer.getWriter();
        InetSocketAddress inetSocketAddress = (InetSocketAddress) obj;
        Object address = inetSocketAddress.getAddress();
        writer.write((char) CoreConstants.CURLY_LEFT);
        if (address != null) {
            writer.writeFieldName(GeocodingCriteria.TYPE_ADDRESS);
            jSONSerializer.write(address);
            writer.write((char) CoreConstants.COMMA_CHAR);
        }
        writer.writeFieldName("port");
        writer.writeInt(inetSocketAddress.getPort());
        writer.write((char) CoreConstants.CURLY_RIGHT);
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T t = null;
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 8) {
            lexer.nextToken();
            return null;
        }
        defaultJSONParser.accept(12);
        int i = 0;
        while (true) {
            T t2;
            int i2;
            String stringVal = lexer.stringVal();
            lexer.nextToken(17);
            if (stringVal.equals(GeocodingCriteria.TYPE_ADDRESS)) {
                defaultJSONParser.accept(17);
                int i3 = i;
                t2 = (InetAddress) defaultJSONParser.parseObject(InetAddress.class);
                i2 = i3;
            } else if (stringVal.equals("port")) {
                defaultJSONParser.accept(17);
                if (lexer.token() != 2) {
                    throw new JSONException("port is not int");
                }
                i2 = lexer.intValue();
                lexer.nextToken();
                t2 = t;
            } else {
                defaultJSONParser.accept(17);
                defaultJSONParser.parse();
                i2 = i;
                t2 = t;
            }
            if (lexer.token() == 16) {
                lexer.nextToken();
                t = t2;
                i = i2;
            } else {
                defaultJSONParser.accept(13);
                return new InetSocketAddress(t2, i2);
            }
        }
    }

    public int getFastMatchToken() {
        return 12;
    }
}
