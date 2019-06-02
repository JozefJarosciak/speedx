package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.ThreadLocalCache;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

public class SerialWriterStringEncoder {
    private final CharsetEncoder encoder;

    public SerialWriterStringEncoder(Charset charset) {
        this(charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE));
    }

    public SerialWriterStringEncoder(CharsetEncoder charsetEncoder) {
        this.encoder = charsetEncoder;
    }

    public byte[] encode(char[] cArr, int i, int i2) {
        if (i2 == 0) {
            return new byte[0];
        }
        this.encoder.reset();
        return encode(cArr, i, i2, ThreadLocalCache.getBytes(scale(i2, this.encoder.maxBytesPerChar())));
    }

    public CharsetEncoder getEncoder() {
        return this.encoder;
    }

    public byte[] encode(char[] cArr, int i, int i2, byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        try {
            CoderResult encode = this.encoder.encode(CharBuffer.wrap(cArr, i, i2), wrap, true);
            if (!encode.isUnderflow()) {
                encode.throwException();
            }
            encode = this.encoder.flush(wrap);
            if (!encode.isUnderflow()) {
                encode.throwException();
            }
            int position = wrap.position();
            Object obj = new byte[position];
            System.arraycopy(bArr, 0, obj, 0, position);
            return obj;
        } catch (Throwable e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    private static int scale(int i, float f) {
        return (int) (((double) i) * ((double) f));
    }
}
