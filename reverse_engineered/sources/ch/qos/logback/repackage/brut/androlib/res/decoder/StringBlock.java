package ch.qos.logback.repackage.brut.androlib.res.decoder;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.repackage.brut.androlib.res.xml.ResXmlEncoders;
import ch.qos.logback.repackage.brut.util.ExtDataInput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StringBlock {
    private static final int CHUNK_TYPE = 1835009;
    private static final Logger LOGGER = Logger.getLogger(StringBlock.class.getName());
    private static final CharsetDecoder UTF16LE_DECODER = Charset.forName("UTF-16LE").newDecoder();
    private static final CharsetDecoder UTF8_DECODER = Charset.forName("UTF-8").newDecoder();
    private static final int UTF8_FLAG = 256;
    private boolean m_isUTF8;
    private int[] m_stringOffsets;
    private byte[] m_strings;
    private int[] m_styleOffsets;
    private int[] m_styles;

    private StringBlock() {
    }

    private String decodeString(int i, int i2) {
        try {
            return (this.m_isUTF8 ? UTF8_DECODER : UTF16LE_DECODER).decode(ByteBuffer.wrap(this.m_strings, i, i2)).toString();
        } catch (Throwable e) {
            LOGGER.log(Level.WARNING, null, e);
            return null;
        }
    }

    private static final int getShort(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }

    private static final int getShort(int[] iArr, int i) {
        int i2 = iArr[i / 4];
        return (i % 4) / 2 == 0 ? i2 & 65535 : i2 >>> 16;
    }

    private int[] getStyle(int i) {
        int i2 = 0;
        if (this.m_styleOffsets == null || this.m_styles == null || i >= this.m_styleOffsets.length) {
            return null;
        }
        int i3 = this.m_styleOffsets[i] / 4;
        int i4 = i3;
        int i5 = 0;
        while (i4 < this.m_styles.length && this.m_styles[i4] != -1) {
            i5++;
            i4++;
        }
        if (i5 == 0 || i5 % 3 != 0) {
            return null;
        }
        int[] iArr = new int[i5];
        while (i3 < this.m_styles.length && this.m_styles[i3] != -1) {
            i4 = i2 + 1;
            i5 = i3 + 1;
            iArr[i2] = this.m_styles[i3];
            i2 = i4;
            i3 = i5;
        }
        return iArr;
    }

    private static final int[] getVarint(byte[] bArr, int i) {
        byte b = bArr[i];
        int i2 = (b & 128) != 0 ? 1 : 0;
        int i3 = b & 127;
        if (i2 == 0) {
            return new int[]{i3, 1};
        }
        return new int[]{(i3 << 8) | (bArr[i + 1] & 255), 2};
    }

    private void outputStyleTag(String str, StringBuilder stringBuilder, boolean z) {
        stringBuilder.append('<');
        if (z) {
            stringBuilder.append('/');
        }
        int indexOf = str.indexOf(59);
        if (indexOf == -1) {
            stringBuilder.append(str);
        } else {
            stringBuilder.append(str.substring(0, indexOf));
            if (!z) {
                int i = 1;
                while (i != 0) {
                    String substring;
                    int indexOf2 = str.indexOf(61, indexOf + 1);
                    stringBuilder.append(' ').append(str.substring(indexOf + 1, indexOf2)).append("=\"");
                    int indexOf3 = str.indexOf(59, indexOf2 + 1);
                    if (indexOf3 != -1) {
                        substring = str.substring(indexOf2 + 1, indexOf3);
                    } else {
                        substring = str.substring(indexOf2 + 1);
                        i = 0;
                    }
                    stringBuilder.append(ResXmlEncoders.escapeXmlChars(substring)).append(CoreConstants.DOUBLE_QUOTE_CHAR);
                    indexOf = indexOf3;
                }
            }
        }
        stringBuilder.append('>');
    }

    public static StringBlock read(ExtDataInput extDataInput) throws IOException {
        extDataInput.skipCheckInt(CHUNK_TYPE);
        int readInt = extDataInput.readInt();
        int readInt2 = extDataInput.readInt();
        int readInt3 = extDataInput.readInt();
        int readInt4 = extDataInput.readInt();
        int readInt5 = extDataInput.readInt();
        int readInt6 = extDataInput.readInt();
        StringBlock stringBlock = new StringBlock();
        stringBlock.m_isUTF8 = (readInt4 & 256) != 0;
        stringBlock.m_stringOffsets = extDataInput.readIntArray(readInt2);
        if (readInt3 != 0) {
            stringBlock.m_styleOffsets = extDataInput.readIntArray(readInt3);
        }
        readInt4 = (readInt6 == 0 ? readInt : readInt6) - readInt5;
        if (readInt4 % 4 != 0) {
            throw new IOException("String data size is not multiple of 4 (" + readInt4 + ").");
        }
        stringBlock.m_strings = new byte[readInt4];
        extDataInput.readFully(stringBlock.m_strings);
        if (readInt6 != 0) {
            readInt4 = readInt - readInt6;
            if (readInt4 % 4 != 0) {
                throw new IOException("Style data size is not multiple of 4 (" + readInt4 + ").");
            }
            stringBlock.m_styles = extDataInput.readIntArray(readInt4 / 4);
        }
        return stringBlock;
    }

    public int find(String str) {
        if (str == null) {
            return -1;
        }
        for (int i = 0; i != this.m_stringOffsets.length; i++) {
            int i2 = this.m_stringOffsets[i];
            int i3 = getShort(this.m_strings, i2);
            if (i3 == str.length()) {
                int i4 = i2;
                i2 = 0;
                while (i2 != i3) {
                    i4 += 2;
                    if (str.charAt(i2) != getShort(this.m_strings, i4)) {
                        break;
                    }
                    i2++;
                }
                if (i2 == i3) {
                    return i;
                }
            }
        }
        return -1;
    }

    public CharSequence get(int i) {
        return getString(i);
    }

    public int getCount() {
        return this.m_stringOffsets != null ? this.m_stringOffsets.length : 0;
    }

    public String getHTML(int i) {
        String string = getString(i);
        if (string == null) {
            return string;
        }
        int[] style = getStyle(i);
        if (style == null) {
            return ResXmlEncoders.escapeXmlChars(string);
        }
        StringBuilder stringBuilder = new StringBuilder(string.length() + 32);
        int[] iArr = new int[(style.length / 3)];
        int i2 = 0;
        boolean z = false;
        while (true) {
            int i3;
            int i4 = 0;
            int i5 = -1;
            while (i4 != style.length) {
                if (style[i4 + 1] != -1 && (i5 == -1 || style[i5 + 1] > style[i4 + 1])) {
                    i5 = i4;
                }
                i4 += 3;
            }
            boolean length = i5 != -1 ? style[i5 + 1] : string.length();
            int i6 = i2 - 1;
            boolean z2 = z;
            int i7 = i6;
            while (i7 >= 0) {
                i3 = iArr[i7];
                boolean z3 = style[i3 + 2];
                if (z3 >= length) {
                    break;
                }
                if (z2 <= z3) {
                    stringBuilder.append(ResXmlEncoders.escapeXmlChars(string.substring(z2, z3 + 1)));
                    z2 = z3 + 1;
                }
                outputStyleTag(getString(style[i3]), stringBuilder, true);
                i7--;
            }
            i3 = i7 + 1;
            if (z2 < length) {
                stringBuilder.append(ResXmlEncoders.escapeXmlChars(string.substring(z2, length)));
                z = length;
            } else {
                z = z2;
            }
            if (i5 == -1) {
                return stringBuilder.toString();
            }
            outputStyleTag(getString(style[i5]), stringBuilder, false);
            style[i5 + 1] = -1;
            i2 = i3 + 1;
            iArr[i3] = i5;
        }
    }

    public String getString(int i) {
        if (i < 0 || this.m_stringOffsets == null || i >= this.m_stringOffsets.length) {
            return null;
        }
        int i2;
        int i3 = this.m_stringOffsets[i];
        if (this.m_isUTF8) {
            i2 = getVarint(this.m_strings, i3)[1] + i3;
            int[] varint = getVarint(this.m_strings, i2);
            i3 = varint[1] + i2;
            i2 = varint[0];
        } else {
            i2 = getShort(this.m_strings, i3) * 2;
            i3 += 2;
        }
        return decodeString(i3, i2);
    }
}
