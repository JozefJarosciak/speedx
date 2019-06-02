package com.alibaba.fastjson.util;

import ch.qos.logback.core.net.SyslogConstants;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.android.gms.location.places.Place;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class UTF8Decoder extends CharsetDecoder {
    private static final Charset charset = Charset.forName("UTF-8");

    private static class Surrogate {
        static final /* synthetic */ boolean $assertionsDisabled = (!UTF8Decoder.class.desiredAssertionStatus());
        public static final int UCS4_MAX = 1114111;
        public static final int UCS4_MIN = 65536;

        private Surrogate() {
        }

        public static boolean neededFor(int i) {
            return i >= 65536 && i <= UCS4_MAX;
        }

        public static char high(int i) {
            if ($assertionsDisabled || neededFor(i)) {
                return (char) (55296 | (((i - 65536) >> 10) & Place.TYPE_SUBLOCALITY_LEVEL_1));
            }
            throw new AssertionError();
        }

        public static char low(int i) {
            if ($assertionsDisabled || neededFor(i)) {
                return (char) (56320 | ((i - 65536) & Place.TYPE_SUBLOCALITY_LEVEL_1));
            }
            throw new AssertionError();
        }
    }

    public UTF8Decoder() {
        super(charset, 1.0f, 1.0f);
    }

    private static boolean isNotContinuation(int i) {
        return (i & Opcodes.CHECKCAST) != 128;
    }

    private static final boolean isMalformed2(int i, int i2) {
        return (i & 30) == 0 || (i2 & Opcodes.CHECKCAST) != 128;
    }

    private static boolean isMalformed3(int i, int i2, int i3) {
        return ((i != -32 || (i2 & 224) != 128) && (i2 & Opcodes.CHECKCAST) == 128 && (i3 & Opcodes.CHECKCAST) == 128) ? false : true;
    }

    private static final boolean isMalformed4(int i, int i2, int i3) {
        return ((i & Opcodes.CHECKCAST) == 128 && (i2 & Opcodes.CHECKCAST) == 128 && (i3 & Opcodes.CHECKCAST) == 128) ? false : true;
    }

    private static CoderResult lookupN(ByteBuffer byteBuffer, int i) {
        for (int i2 = 1; i2 < i; i2++) {
            if (isNotContinuation(byteBuffer.get())) {
                return CoderResult.malformedForLength(i2);
            }
        }
        return CoderResult.malformedForLength(i);
    }

    public static CoderResult malformedN(ByteBuffer byteBuffer, int i) {
        int i2 = 2;
        switch (i) {
            case 1:
                byte b = byteBuffer.get();
                if ((b >> 2) == -2) {
                    if (byteBuffer.remaining() < 4) {
                        return CoderResult.UNDERFLOW;
                    }
                    return lookupN(byteBuffer, 5);
                } else if ((b >> 1) != -2) {
                    return CoderResult.malformedForLength(1);
                } else {
                    if (byteBuffer.remaining() < 5) {
                        return CoderResult.UNDERFLOW;
                    }
                    return lookupN(byteBuffer, 6);
                }
            case 2:
                return CoderResult.malformedForLength(1);
            case 3:
                byte b2 = byteBuffer.get();
                byte b3 = byteBuffer.get();
                if ((b2 == (byte) -32 && (b3 & 224) == 128) || isNotContinuation(b3)) {
                    i2 = 1;
                }
                return CoderResult.malformedForLength(i2);
            case 4:
                int i3 = byteBuffer.get() & 255;
                int i4 = byteBuffer.get() & 255;
                if (i3 > 244 || ((i3 == 240 && (i4 < SyslogConstants.LOG_LOCAL2 || i4 > 191)) || ((i3 == 244 && (i4 & 240) != 128) || isNotContinuation(i4)))) {
                    return CoderResult.malformedForLength(1);
                }
                if (isNotContinuation(byteBuffer.get())) {
                    return CoderResult.malformedForLength(2);
                }
                return CoderResult.malformedForLength(3);
            default:
                throw new IllegalStateException();
        }
    }

    private static CoderResult malformed(ByteBuffer byteBuffer, int i, CharBuffer charBuffer, int i2, int i3) {
        byteBuffer.position(i - byteBuffer.arrayOffset());
        CoderResult malformedN = malformedN(byteBuffer, i3);
        updatePositions(byteBuffer, i, charBuffer, i2);
        return malformedN;
    }

    private static CoderResult xflow(Buffer buffer, int i, int i2, Buffer buffer2, int i3, int i4) {
        updatePositions(buffer, i, buffer2, i3);
        return (i4 == 0 || i2 - i < i4) ? CoderResult.UNDERFLOW : CoderResult.OVERFLOW;
    }

    private CoderResult decodeArrayLoop(ByteBuffer byteBuffer, CharBuffer charBuffer) {
        byte[] array = byteBuffer.array();
        int position = byteBuffer.position() + byteBuffer.arrayOffset();
        int limit = byteBuffer.limit() + byteBuffer.arrayOffset();
        char[] array2 = charBuffer.array();
        int arrayOffset = charBuffer.arrayOffset() + charBuffer.position();
        int arrayOffset2 = charBuffer.arrayOffset() + charBuffer.limit();
        int min = arrayOffset + Math.min(limit - position, arrayOffset2 - arrayOffset);
        while (arrayOffset < min && array[position] >= (byte) 0) {
            int i = arrayOffset + 1;
            int i2 = position + 1;
            array2[arrayOffset] = (char) array[position];
            arrayOffset = i;
            position = i2;
        }
        while (position < limit) {
            byte b = array[position];
            if (b >= (byte) 0) {
                if (arrayOffset >= arrayOffset2) {
                    return xflow(byteBuffer, position, limit, charBuffer, arrayOffset, 1);
                }
                i = arrayOffset + 1;
                array2[arrayOffset] = (char) b;
                position++;
            } else if ((b >> 5) == -2) {
                if (limit - position < 2 || arrayOffset >= arrayOffset2) {
                    return xflow(byteBuffer, position, limit, charBuffer, arrayOffset, 2);
                }
                r8 = array[position + 1];
                if (isMalformed2(b, r8)) {
                    return malformed(byteBuffer, position, charBuffer, arrayOffset, 2);
                }
                i = arrayOffset + 1;
                array2[arrayOffset] = (char) (((b << 6) ^ r8) ^ 3968);
                position += 2;
            } else if ((b >> 4) == -2) {
                if (limit - position < 3 || arrayOffset >= arrayOffset2) {
                    return xflow(byteBuffer, position, limit, charBuffer, arrayOffset, 3);
                }
                r8 = array[position + 1];
                r9 = array[position + 2];
                if (isMalformed3(b, r8, r9)) {
                    return malformed(byteBuffer, position, charBuffer, arrayOffset, 3);
                }
                i = arrayOffset + 1;
                array2[arrayOffset] = (char) ((((b << 12) ^ (r8 << 6)) ^ r9) ^ 8064);
                position += 3;
            } else if ((b >> 3) != -2) {
                return malformed(byteBuffer, position, charBuffer, arrayOffset, 1);
            } else {
                if (limit - position < 4 || arrayOffset2 - arrayOffset < 2) {
                    return xflow(byteBuffer, position, limit, charBuffer, arrayOffset, 4);
                }
                byte b2 = array[position + 1];
                r8 = array[position + 2];
                r9 = array[position + 3];
                i2 = ((((b & 7) << 18) | ((b2 & 63) << 12)) | ((r8 & 63) << 6)) | (r9 & 63);
                if (isMalformed4(b2, r8, r9) || !Surrogate.neededFor(i2)) {
                    return malformed(byteBuffer, position, charBuffer, arrayOffset, 4);
                }
                min = arrayOffset + 1;
                array2[arrayOffset] = Surrogate.high(i2);
                i = min + 1;
                array2[min] = Surrogate.low(i2);
                position += 4;
            }
            arrayOffset = i;
        }
        return xflow(byteBuffer, position, limit, charBuffer, arrayOffset, 0);
    }

    protected CoderResult decodeLoop(ByteBuffer byteBuffer, CharBuffer charBuffer) {
        return decodeArrayLoop(byteBuffer, charBuffer);
    }

    static final void updatePositions(Buffer buffer, int i, Buffer buffer2, int i2) {
        buffer.position(i);
        buffer2.position(i2);
    }
}
