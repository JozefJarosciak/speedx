package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

public class BigDecimalCodec implements ObjectDeserializer, ObjectSerializer {
    public static final BigDecimalCodec instance = new BigDecimalCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj != null) {
            BigDecimal bigDecimal = (BigDecimal) obj;
            writer.write(bigDecimal.toString());
            if (writer.isEnabled(SerializerFeature.WriteClassName) && type != BigDecimal.class && bigDecimal.scale() == 0) {
                writer.write((char) CoreConstants.DOT);
            }
        } else if (writer.isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
            writer.write('0');
        } else {
            writer.writeNull();
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return deserialze(defaultJSONParser);
    }

    public static <T> T deserialze(DefaultJSONParser defaultJSONParser) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() == 2) {
            long longValue = lexer.longValue();
            lexer.nextToken(16);
            return new BigDecimal(longValue);
        } else if (lexer.token() == 3) {
            T decimalValue = lexer.decimalValue();
            lexer.nextToken(16);
            return decimalValue;
        } else {
            Object parse = defaultJSONParser.parse();
            if (parse == null) {
                return null;
            }
            return TypeUtils.castToBigDecimal(parse);
        }
    }

    public int getFastMatchToken() {
        return 2;
    }
}
