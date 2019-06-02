package org.java_websocket.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import org.apache.http.protocol.HTTP;
import org.java_websocket.exceptions.InvalidDataException;

public class Charsetfunctions {
    public static CodingErrorAction codingErrorAction = CodingErrorAction.REPORT;

    public static byte[] utf8Bytes(String str) {
        try {
            return str.getBytes("UTF8");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] asciiBytes(String str) {
        try {
            return str.getBytes(HTTP.ASCII);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String stringAscii(byte[] bArr) {
        return stringAscii(bArr, 0, bArr.length);
    }

    public static String stringAscii(byte[] bArr, int i, int i2) {
        try {
            return new String(bArr, i, i2, HTTP.ASCII);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String stringUtf8(byte[] bArr) throws InvalidDataException {
        return stringUtf8(ByteBuffer.wrap(bArr));
    }

    public static String stringUtf8(ByteBuffer byteBuffer) throws InvalidDataException {
        CharsetDecoder newDecoder = Charset.forName("UTF8").newDecoder();
        newDecoder.onMalformedInput(codingErrorAction);
        newDecoder.onUnmappableCharacter(codingErrorAction);
        try {
            byteBuffer.mark();
            String charBuffer = newDecoder.decode(byteBuffer).toString();
            byteBuffer.reset();
            return charBuffer;
        } catch (Throwable e) {
            throw new InvalidDataException(1007, e);
        }
    }

    public static void main(String[] strArr) throws InvalidDataException {
        stringUtf8(utf8Bytes("\u0000"));
        stringAscii(asciiBytes("\u0000"));
    }
}
