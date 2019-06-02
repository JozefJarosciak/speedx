package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.CharTypes;
import com.alibaba.fastjson.util.Base64;
import com.alibaba.fastjson.util.IOUtils;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.nio.charset.Charset;

public final class SerializeWriter extends Writer {
    private static final ThreadLocal<SoftReference<char[]>> bufLocal = new ThreadLocal();
    protected char[] buf;
    protected int count;
    private int features;
    private final Writer writer;

    public SerializeWriter() {
        this((Writer) null);
    }

    public SerializeWriter(Writer writer) {
        this.writer = writer;
        this.features = JSON.DEFAULT_GENERATE_FEATURE;
        SoftReference softReference = (SoftReference) bufLocal.get();
        if (softReference != null) {
            this.buf = (char[]) softReference.get();
            bufLocal.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[1024];
        }
    }

    public SerializeWriter(SerializerFeature... serializerFeatureArr) {
        this(null, serializerFeatureArr);
    }

    public SerializeWriter(Writer writer, SerializerFeature... serializerFeatureArr) {
        int i = 0;
        this.writer = writer;
        SoftReference softReference = (SoftReference) bufLocal.get();
        if (softReference != null) {
            this.buf = (char[]) softReference.get();
            bufLocal.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[1024];
        }
        for (SerializerFeature mask : serializerFeatureArr) {
            i |= mask.getMask();
        }
        this.features = i;
    }

    public int getBufferLength() {
        return this.buf.length;
    }

    public SerializeWriter(int i) {
        this(null, i);
    }

    public SerializeWriter(Writer writer, int i) {
        this.writer = writer;
        if (i <= 0) {
            throw new IllegalArgumentException("Negative initial size: " + i);
        }
        this.buf = new char[i];
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        if (z) {
            this.features |= serializerFeature.getMask();
        } else {
            this.features &= serializerFeature.getMask() ^ -1;
        }
    }

    public boolean isEnabled(SerializerFeature serializerFeature) {
        return SerializerFeature.isEnabled(this.features, serializerFeature);
    }

    public void write(int i) {
        int i2 = this.count + 1;
        if (i2 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i2);
            } else {
                flush();
                i2 = 1;
            }
        }
        this.buf[this.count] = (char) i;
        this.count = i2;
    }

    public void write(char c) {
        int i = this.count + 1;
        if (i > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i);
            } else {
                flush();
                i = 1;
            }
        }
        this.buf[this.count] = c;
        this.count = i;
    }

    public void write(char[] cArr, int i, int i2) {
        if (i < 0 || i > cArr.length || i2 < 0 || i + i2 > cArr.length || i + i2 < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i2 != 0) {
            int i3 = this.count + i2;
            int i4;
            if (i3 <= this.buf.length) {
                i4 = i3;
                i3 = i2;
                i2 = i4;
            } else if (this.writer == null) {
                expandCapacity(i3);
                i4 = i3;
                i3 = i2;
                i2 = i4;
            } else {
                do {
                    i3 = this.buf.length - this.count;
                    System.arraycopy(cArr, i, this.buf, this.count, i3);
                    this.count = this.buf.length;
                    flush();
                    i2 -= i3;
                    i += i3;
                } while (i2 > this.buf.length);
                i3 = i2;
            }
            System.arraycopy(cArr, i, this.buf, this.count, i3);
            this.count = i2;
        }
    }

    public void expandCapacity(int i) {
        int length = ((this.buf.length * 3) / 2) + 1;
        if (length >= i) {
            i = length;
        }
        Object obj = new char[i];
        System.arraycopy(this.buf, 0, obj, 0, this.count);
        this.buf = obj;
    }

    public void write(String str, int i, int i2) {
        int i3 = this.count + i2;
        int i4;
        if (i3 <= this.buf.length) {
            i4 = i3;
            i3 = i2;
            i2 = i4;
        } else if (this.writer == null) {
            expandCapacity(i3);
            i4 = i3;
            i3 = i2;
            i2 = i4;
        } else {
            do {
                i3 = this.buf.length - this.count;
                str.getChars(i, i + i3, this.buf, this.count);
                this.count = this.buf.length;
                flush();
                i2 -= i3;
                i += i3;
            } while (i2 > this.buf.length);
            i3 = i2;
        }
        str.getChars(i, i3 + i, this.buf, this.count);
        this.count = i2;
    }

    public void writeTo(Writer writer) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        writer.write(this.buf, 0, this.count);
    }

    public void writeTo(OutputStream outputStream, String str) throws IOException {
        writeTo(outputStream, Charset.forName(str));
    }

    public void writeTo(OutputStream outputStream, Charset charset) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        outputStream.write(new String(this.buf, 0, this.count).getBytes(charset));
    }

    public SerializeWriter append(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? "null" : charSequence.toString();
        write(charSequence2, 0, charSequence2.length());
        return this;
    }

    public SerializeWriter append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        String charSequence2 = charSequence.subSequence(i, i2).toString();
        write(charSequence2, 0, charSequence2.length());
        return this;
    }

    public SerializeWriter append(char c) {
        write(c);
        return this;
    }

    public void reset() {
        this.count = 0;
    }

    public char[] toCharArray() {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        Object obj = new char[this.count];
        System.arraycopy(this.buf, 0, obj, 0, this.count);
        return obj;
    }

    public byte[] toBytes(String str) {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        if (str == null) {
            str = "UTF-8";
        }
        return new SerialWriterStringEncoder(Charset.forName(str)).encode(this.buf, 0, this.count);
    }

    public int size() {
        return this.count;
    }

    public String toString() {
        return new String(this.buf, 0, this.count);
    }

    public void close() {
        if (this.writer != null && this.count > 0) {
            flush();
        }
        if (this.buf.length <= 8192) {
            bufLocal.set(new SoftReference(this.buf));
        }
        this.buf = null;
    }

    public void write(String str) {
        if (str == null) {
            writeNull();
        } else {
            write(str, 0, str.length());
        }
    }

    public void writeInt(int i) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            return;
        }
        int stringSize = i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        int i2 = this.count + stringSize;
        if (i2 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i2);
            } else {
                char[] cArr = new char[stringSize];
                IOUtils.getChars(i, stringSize, cArr);
                write(cArr, 0, cArr.length);
                return;
            }
        }
        IOUtils.getChars(i, i2, this.buf);
        this.count = i2;
    }

    public void writeByteArray(byte[] bArr) {
        int i = 0;
        int length = bArr.length;
        if (length == 0) {
            write("\"\"");
            return;
        }
        char c;
        char[] cArr = Base64.CA;
        int i2 = (length / 3) * 3;
        int i3 = (((length - 1) / 3) + 1) << 2;
        int i4 = this.count;
        int i5 = (i3 + this.count) + 2;
        if (i5 > this.buf.length) {
            if (this.writer != null) {
                write((char) CoreConstants.DOUBLE_QUOTE_CHAR);
                i3 = 0;
                while (i3 < i2) {
                    i4 = i3 + 1;
                    i5 = i4 + 1;
                    i4 = ((bArr[i4] & 255) << 8) | ((bArr[i3] & 255) << 16);
                    i3 = i5 + 1;
                    i4 |= bArr[i5] & 255;
                    write(cArr[(i4 >>> 18) & 63]);
                    write(cArr[(i4 >>> 12) & 63]);
                    write(cArr[(i4 >>> 6) & 63]);
                    write(cArr[i4 & 63]);
                }
                i3 = length - i2;
                if (i3 > 0) {
                    i4 = (bArr[i2] & 255) << 10;
                    if (i3 == 2) {
                        i = (bArr[length - 1] & 255) << 2;
                    }
                    i |= i4;
                    write(cArr[i >> 12]);
                    write(cArr[(i >>> 6) & 63]);
                    if (i3 == 2) {
                        c = cArr[i & 63];
                    } else {
                        c = '=';
                    }
                    write(c);
                    write('=');
                }
                write((char) CoreConstants.DOUBLE_QUOTE_CHAR);
                return;
            }
            expandCapacity(i5);
        }
        this.count = i5;
        i3 = i4 + 1;
        this.buf[i4] = CoreConstants.DOUBLE_QUOTE_CHAR;
        i4 = 0;
        while (i4 < i2) {
            int i6 = i4 + 1;
            int i7 = i6 + 1;
            i6 = ((bArr[i6] & 255) << 8) | ((bArr[i4] & 255) << 16);
            i4 = i7 + 1;
            i6 |= bArr[i7] & 255;
            int i8 = i3 + 1;
            this.buf[i3] = cArr[(i6 >>> 18) & 63];
            i7 = i8 + 1;
            this.buf[i8] = cArr[(i6 >>> 12) & 63];
            i8 = i7 + 1;
            this.buf[i7] = cArr[(i6 >>> 6) & 63];
            i3 = i8 + 1;
            this.buf[i8] = cArr[i6 & 63];
        }
        i3 = length - i2;
        if (i3 > 0) {
            i4 = (bArr[i2] & 255) << 10;
            if (i3 == 2) {
                i = (bArr[length - 1] & 255) << 2;
            }
            i |= i4;
            this.buf[i5 - 5] = cArr[i >> 12];
            this.buf[i5 - 4] = cArr[(i >>> 6) & 63];
            char[] cArr2 = this.buf;
            length = i5 - 3;
            if (i3 == 2) {
                c = cArr[i & 63];
            } else {
                c = '=';
            }
            cArr2[length] = c;
            this.buf[i5 - 2] = '=';
        }
        this.buf[i5 - 1] = CoreConstants.DOUBLE_QUOTE_CHAR;
    }

    public void writeFloatAndChar(float f, char c) {
        String f2 = Float.toString(f);
        if (f2.endsWith(".0")) {
            f2 = f2.substring(0, f2.length() - 2);
        }
        write(f2);
        write(c);
    }

    public void writeDoubleAndChar(double d, char c) {
        String d2 = Double.toString(d);
        if (d2.endsWith(".0")) {
            d2 = d2.substring(0, d2.length() - 2);
        }
        write(d2);
        write(c);
    }

    public void writeBooleanAndChar(boolean z, char c) {
        if (z) {
            if (c == CoreConstants.COMMA_CHAR) {
                write("true,");
            } else if (c == ']') {
                write("true]");
            } else {
                write("true");
                write(c);
            }
        } else if (c == CoreConstants.COMMA_CHAR) {
            write("false,");
        } else if (c == ']') {
            write("false]");
        } else {
            write("false");
            write(c);
        }
    }

    public void writeCharacterAndChar(char c, char c2) {
        writeString(Character.toString(c));
        write(c2);
    }

    public void writeEnum(Enum<?> enumR, char c) {
        if (enumR == null) {
            writeNull();
            write((char) CoreConstants.COMMA_CHAR);
        } else if (!isEnabled(SerializerFeature.WriteEnumUsingToString)) {
            writeIntAndChar(enumR.ordinal(), c);
        } else if (isEnabled(SerializerFeature.UseSingleQuotes)) {
            write((char) CoreConstants.SINGLE_QUOTE_CHAR);
            write(enumR.name());
            write((char) CoreConstants.SINGLE_QUOTE_CHAR);
            write(c);
        } else {
            write((char) CoreConstants.DOUBLE_QUOTE_CHAR);
            write(enumR.name());
            write((char) CoreConstants.DOUBLE_QUOTE_CHAR);
            write(c);
        }
    }

    public void writeIntAndChar(int i, char c) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            write(c);
            return;
        }
        int stringSize = (i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i)) + this.count;
        int i2 = stringSize + 1;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                writeInt(i);
                write(c);
                return;
            }
            expandCapacity(i2);
        }
        IOUtils.getChars(i, stringSize, this.buf);
        this.buf[stringSize] = c;
        this.count = i2;
    }

    public void writeLongAndChar(long j, char c) throws IOException {
        if (j == Long.MIN_VALUE) {
            write("-9223372036854775808");
            write(c);
            return;
        }
        int stringSize = (j < 0 ? IOUtils.stringSize(-j) + 1 : IOUtils.stringSize(j)) + this.count;
        int i = stringSize + 1;
        if (i > this.buf.length) {
            if (this.writer != null) {
                writeLong(j);
                write(c);
                return;
            }
            expandCapacity(i);
        }
        IOUtils.getChars(j, stringSize, this.buf);
        this.buf[stringSize] = c;
        this.count = i;
    }

    public void writeLong(long j) {
        if (j == Long.MIN_VALUE) {
            write("-9223372036854775808");
            return;
        }
        int stringSize = j < 0 ? IOUtils.stringSize(-j) + 1 : IOUtils.stringSize(j);
        int i = this.count + stringSize;
        if (i > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i);
            } else {
                char[] cArr = new char[stringSize];
                IOUtils.getChars(j, stringSize, cArr);
                write(cArr, 0, cArr.length);
                return;
            }
        }
        IOUtils.getChars(j, i, this.buf);
        this.count = i;
    }

    public void writeNull() {
        write("null");
    }

    private void writeStringWithDoubleQuote(String str, char c) {
        writeStringWithDoubleQuote(str, c, true);
    }

    private void writeStringWithDoubleQuote(String str, char c, boolean z) {
        if (str == null) {
            writeNull();
            if (c != '\u0000') {
                write(c);
                return;
            }
            return;
        }
        char charAt;
        int length = str.length();
        int i = (this.count + length) + 2;
        if (c != '\u0000') {
            i++;
        }
        if (i > this.buf.length) {
            if (this.writer != null) {
                write((char) CoreConstants.DOUBLE_QUOTE_CHAR);
                for (i = 0; i < str.length(); i++) {
                    charAt = str.charAt(i);
                    if (!isEnabled(SerializerFeature.BrowserCompatible)) {
                        if ((charAt < CharTypes.specicalFlags_doubleQuotes.length && CharTypes.specicalFlags_doubleQuotes[charAt] != (byte) 0) || (charAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                            write((char) CoreConstants.ESCAPE_CHAR);
                            write(CharTypes.replaceChars[charAt]);
                        }
                        write(charAt);
                    } else if (charAt == '\b' || charAt == '\f' || charAt == '\n' || charAt == '\r' || charAt == '\t' || charAt == CoreConstants.DOUBLE_QUOTE_CHAR || charAt == '/' || charAt == CoreConstants.ESCAPE_CHAR) {
                        write((char) CoreConstants.ESCAPE_CHAR);
                        write(CharTypes.replaceChars[charAt]);
                    } else if (charAt < ' ') {
                        write((char) CoreConstants.ESCAPE_CHAR);
                        write('u');
                        write('0');
                        write('0');
                        write(CharTypes.ASCII_CHARS[charAt * 2]);
                        write(CharTypes.ASCII_CHARS[(charAt * 2) + 1]);
                    } else {
                        if (charAt >= Ascii.MAX) {
                            write((char) CoreConstants.ESCAPE_CHAR);
                            write('u');
                            write(CharTypes.digits[(charAt >>> 12) & 15]);
                            write(CharTypes.digits[(charAt >>> 8) & 15]);
                            write(CharTypes.digits[(charAt >>> 4) & 15]);
                            write(CharTypes.digits[charAt & 15]);
                        }
                        write(charAt);
                    }
                }
                write((char) CoreConstants.DOUBLE_QUOTE_CHAR);
                if (c != '\u0000') {
                    write(c);
                    return;
                }
                return;
            }
            expandCapacity(i);
        }
        int i2 = this.count + 1;
        int i3 = i2 + length;
        this.buf[this.count] = CoreConstants.DOUBLE_QUOTE_CHAR;
        str.getChars(0, length, this.buf, i2);
        this.count = i;
        int i4;
        if (isEnabled(SerializerFeature.BrowserCompatible)) {
            i4 = i;
            i = -1;
            for (length = i2; length < i3; length++) {
                char c2 = this.buf[length];
                if (c2 == CoreConstants.DOUBLE_QUOTE_CHAR || c2 == '/' || c2 == CoreConstants.ESCAPE_CHAR) {
                    i4++;
                    i = length;
                } else if (c2 == '\b' || c2 == '\f' || c2 == '\n' || c2 == '\r' || c2 == '\t') {
                    i4++;
                    i = length;
                } else if (c2 < ' ') {
                    i4 += 5;
                    i = length;
                } else if (c2 >= Ascii.MAX) {
                    i4 += 5;
                    i = length;
                }
            }
            if (i4 > this.buf.length) {
                expandCapacity(i4);
            }
            this.count = i4;
            i = i3;
            for (length = i; length >= i2; length--) {
                char c3 = this.buf[length];
                if (c3 == '\b' || c3 == '\f' || c3 == '\n' || c3 == '\r' || c3 == '\t') {
                    System.arraycopy(this.buf, length + 1, this.buf, length + 2, (i - length) - 1);
                    this.buf[length] = CoreConstants.ESCAPE_CHAR;
                    this.buf[length + 1] = CharTypes.replaceChars[c3];
                    i++;
                } else if (c3 == CoreConstants.DOUBLE_QUOTE_CHAR || c3 == '/' || c3 == CoreConstants.ESCAPE_CHAR) {
                    System.arraycopy(this.buf, length + 1, this.buf, length + 2, (i - length) - 1);
                    this.buf[length] = CoreConstants.ESCAPE_CHAR;
                    this.buf[length + 1] = c3;
                    i++;
                } else if (c3 < ' ') {
                    System.arraycopy(this.buf, length + 1, this.buf, length + 6, (i - length) - 1);
                    this.buf[length] = CoreConstants.ESCAPE_CHAR;
                    this.buf[length + 1] = 'u';
                    this.buf[length + 2] = '0';
                    this.buf[length + 3] = '0';
                    this.buf[length + 4] = CharTypes.ASCII_CHARS[c3 * 2];
                    this.buf[length + 5] = CharTypes.ASCII_CHARS[(c3 * 2) + 1];
                    i += 5;
                } else if (c3 >= Ascii.MAX) {
                    System.arraycopy(this.buf, length + 1, this.buf, length + 6, (i - length) - 1);
                    this.buf[length] = CoreConstants.ESCAPE_CHAR;
                    this.buf[length + 1] = 'u';
                    this.buf[length + 2] = CharTypes.digits[(c3 >>> 12) & 15];
                    this.buf[length + 3] = CharTypes.digits[(c3 >>> 8) & 15];
                    this.buf[length + 4] = CharTypes.digits[(c3 >>> 4) & 15];
                    this.buf[length + 5] = CharTypes.digits[c3 & 15];
                    i += 5;
                }
            }
            if (c != '\u0000') {
                this.buf[this.count - 2] = CoreConstants.DOUBLE_QUOTE_CHAR;
                this.buf[this.count - 1] = c;
                return;
            }
            this.buf[this.count - 1] = CoreConstants.DOUBLE_QUOTE_CHAR;
            return;
        }
        int i5;
        int i6 = 0;
        int i7 = -1;
        int i8 = -1;
        length = 0;
        if (z) {
            i4 = i2;
            i5 = i;
            while (i4 < i3) {
                char c4 = this.buf[i4];
                if (c4 >= ']') {
                    if (c4 == ' ') {
                        length = i6 + 1;
                        i7 = i5 + 4;
                        if (i8 == -1) {
                            i8 = i4;
                            i6 = i7;
                            i7 = length;
                            length = i4;
                        } else {
                            i6 = i7;
                            i7 = length;
                            length = i8;
                            i8 = i4;
                        }
                    }
                    c4 = charAt;
                    length = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                } else if (c4 == ' ') {
                    c4 = charAt;
                    length = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                } else if (c4 < '0' || c4 == CoreConstants.ESCAPE_CHAR) {
                    if ((c4 < CharTypes.specicalFlags_doubleQuotes.length && CharTypes.specicalFlags_doubleQuotes[c4] != (byte) 0) || (c4 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        length = i6 + 1;
                        if (i8 == -1) {
                            i8 = i4;
                            i7 = length;
                            i6 = i5;
                            length = i4;
                        } else {
                            i7 = length;
                            i6 = i5;
                            length = i8;
                            i8 = i4;
                        }
                    }
                    c4 = charAt;
                    length = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                } else {
                    c4 = charAt;
                    length = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                }
                i4++;
                i5 = i6;
                i6 = i7;
                i7 = i8;
                i8 = length;
                charAt = c4;
            }
        } else {
            i5 = i;
        }
        i = i5 + i6;
        if (i > this.buf.length) {
            expandCapacity(i);
        }
        this.count = i;
        if (i6 == 1) {
            if (length == 8232) {
                System.arraycopy(this.buf, i7 + 1, this.buf, i7 + 6, (i3 - i7) - 1);
                this.buf[i7] = CoreConstants.ESCAPE_CHAR;
                length = i7 + 1;
                this.buf[length] = 'u';
                length++;
                this.buf[length] = '2';
                length++;
                this.buf[length] = '0';
                length++;
                this.buf[length] = '2';
                this.buf[length + 1] = '8';
            } else {
                System.arraycopy(this.buf, i7 + 1, this.buf, i7 + 2, (i3 - i7) - 1);
                this.buf[i7] = CoreConstants.ESCAPE_CHAR;
                this.buf[i7 + 1] = CharTypes.replaceChars[length];
            }
        } else if (i6 > 1) {
            length = i3;
            for (i = i8 - i2; i < str.length(); i++) {
                char charAt2 = str.charAt(i);
                if ((charAt2 >= CharTypes.specicalFlags_doubleQuotes.length || CharTypes.specicalFlags_doubleQuotes[charAt2] == (byte) 0) && !(charAt2 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                    i4 = i8 + 1;
                    this.buf[i8] = charAt2;
                    i8 = i4;
                } else {
                    i6 = i8 + 1;
                    this.buf[i8] = CoreConstants.ESCAPE_CHAR;
                    i8 = i6 + 1;
                    this.buf[i6] = CharTypes.replaceChars[charAt2];
                    length++;
                }
            }
        }
        if (c != '\u0000') {
            this.buf[this.count - 2] = CoreConstants.DOUBLE_QUOTE_CHAR;
            this.buf[this.count - 1] = c;
            return;
        }
        this.buf[this.count - 1] = CoreConstants.DOUBLE_QUOTE_CHAR;
    }

    public void writeFieldNull(char c, String str) {
        write(c);
        writeFieldName(str);
        writeNull();
    }

    public void writeFieldEmptyList(char c, String str) {
        write(c);
        writeFieldName(str);
        write("[]");
    }

    public void writeFieldNullString(char c, String str) {
        write(c);
        writeFieldName(str);
        if (isEnabled(SerializerFeature.WriteNullStringAsEmpty)) {
            writeString("");
        } else {
            writeNull();
        }
    }

    public void writeFieldNullBoolean(char c, String str) {
        write(c);
        writeFieldName(str);
        if (isEnabled(SerializerFeature.WriteNullBooleanAsFalse)) {
            write("false");
        } else {
            writeNull();
        }
    }

    public void writeFieldNullList(char c, String str) {
        write(c);
        writeFieldName(str);
        if (isEnabled(SerializerFeature.WriteNullListAsEmpty)) {
            write("[]");
        } else {
            writeNull();
        }
    }

    public void writeFieldNullNumber(char c, String str) {
        write(c);
        writeFieldName(str);
        if (isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
            write('0');
        } else {
            writeNull();
        }
    }

    public void writeFieldValue(char c, String str, char c2) {
        write(c);
        writeFieldName(str);
        if (c2 == '\u0000') {
            writeString("\u0000");
        } else {
            writeString(Character.toString(c2));
        }
    }

    public void writeFieldValue(char c, String str, boolean z) {
        int i;
        char c2 = isEnabled(SerializerFeature.UseSingleQuotes) ? CoreConstants.SINGLE_QUOTE_CHAR : CoreConstants.DOUBLE_QUOTE_CHAR;
        if (z) {
            i = 4;
        } else {
            i = 5;
        }
        int length = str.length();
        i += (this.count + length) + 4;
        if (i > this.buf.length) {
            if (this.writer != null) {
                write(c);
                writeString(str);
                write((char) CoreConstants.COLON_CHAR);
                write(z);
                return;
            }
            expandCapacity(i);
        }
        int i2 = this.count;
        this.count = i;
        this.buf[i2] = c;
        i = (i2 + length) + 1;
        this.buf[i2 + 1] = c2;
        str.getChars(0, length, this.buf, i2 + 2);
        this.buf[i + 1] = c2;
        if (z) {
            System.arraycopy(":true".toCharArray(), 0, this.buf, i + 2, 5);
        } else {
            System.arraycopy(":false".toCharArray(), 0, this.buf, i + 2, 6);
        }
    }

    public void write(boolean z) {
        if (z) {
            write("true");
        } else {
            write("false");
        }
    }

    public void writeFieldValue(char c, String str, int i) {
        if (i == Integer.MIN_VALUE || !isEnabled(SerializerFeature.QuoteFieldNames)) {
            writeFieldValue1(c, str, i);
            return;
        }
        char c2 = isEnabled(SerializerFeature.UseSingleQuotes) ? CoreConstants.SINGLE_QUOTE_CHAR : CoreConstants.DOUBLE_QUOTE_CHAR;
        int stringSize = i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        int length = str.length();
        stringSize += (this.count + length) + 4;
        if (stringSize > this.buf.length) {
            if (this.writer != null) {
                writeFieldValue1(c, str, i);
                return;
            }
            expandCapacity(stringSize);
        }
        int i2 = this.count;
        this.count = stringSize;
        this.buf[i2] = c;
        stringSize = (i2 + length) + 1;
        this.buf[i2 + 1] = c2;
        str.getChars(0, length, this.buf, i2 + 2);
        this.buf[stringSize + 1] = c2;
        this.buf[stringSize + 2] = CoreConstants.COLON_CHAR;
        IOUtils.getChars(i, this.count, this.buf);
    }

    public void writeFieldValue1(char c, String str, int i) {
        write(c);
        writeFieldName(str);
        writeInt(i);
    }

    public void writeFieldValue(char c, String str, long j) {
        if (j == Long.MIN_VALUE || !isEnabled(SerializerFeature.QuoteFieldNames)) {
            writeFieldValue1(c, str, j);
            return;
        }
        char c2 = isEnabled(SerializerFeature.UseSingleQuotes) ? CoreConstants.SINGLE_QUOTE_CHAR : CoreConstants.DOUBLE_QUOTE_CHAR;
        int stringSize = j < 0 ? IOUtils.stringSize(-j) + 1 : IOUtils.stringSize(j);
        int length = str.length();
        stringSize += (this.count + length) + 4;
        if (stringSize > this.buf.length) {
            if (this.writer != null) {
                write(c);
                writeFieldName(str);
                writeLong(j);
                return;
            }
            expandCapacity(stringSize);
        }
        int i = this.count;
        this.count = stringSize;
        this.buf[i] = c;
        stringSize = (i + length) + 1;
        this.buf[i + 1] = c2;
        str.getChars(0, length, this.buf, i + 2);
        this.buf[stringSize + 1] = c2;
        this.buf[stringSize + 2] = CoreConstants.COLON_CHAR;
        IOUtils.getChars(j, this.count, this.buf);
    }

    public void writeFieldValue1(char c, String str, long j) {
        write(c);
        writeFieldName(str);
        writeLong(j);
    }

    public void writeFieldValue(char c, String str, float f) {
        write(c);
        writeFieldName(str);
        if (f == 0.0f) {
            write('0');
        } else if (Float.isNaN(f)) {
            writeNull();
        } else if (Float.isInfinite(f)) {
            writeNull();
        } else {
            String f2 = Float.toString(f);
            if (f2.endsWith(".0")) {
                f2 = f2.substring(0, f2.length() - 2);
            }
            write(f2);
        }
    }

    public void writeFieldValue(char c, String str, double d) {
        write(c);
        writeFieldName(str);
        if (d == 0.0d) {
            write('0');
        } else if (Double.isNaN(d)) {
            writeNull();
        } else if (Double.isInfinite(d)) {
            writeNull();
        } else {
            String d2 = Double.toString(d);
            if (d2.endsWith(".0")) {
                d2 = d2.substring(0, d2.length() - 2);
            }
            write(d2);
        }
    }

    public void writeFieldValue(char c, String str, String str2) {
        if (!isEnabled(SerializerFeature.QuoteFieldNames)) {
            write(c);
            writeFieldName(str);
            if (str2 == null) {
                writeNull();
            } else {
                writeString(str2);
            }
        } else if (isEnabled(SerializerFeature.UseSingleQuotes)) {
            write(c);
            writeFieldName(str);
            if (str2 == null) {
                writeNull();
            } else {
                writeString(str2);
            }
        } else if (isEnabled(SerializerFeature.BrowserCompatible)) {
            write(c);
            writeStringWithDoubleQuote(str, CoreConstants.COLON_CHAR);
            writeStringWithDoubleQuote(str2, '\u0000');
        } else {
            writeFieldValueStringWithDoubleQuote(c, str, str2, true);
        }
    }

    private void writeFieldValueStringWithDoubleQuote(char c, String str, String str2, boolean z) {
        int i;
        int length = str.length();
        int i2 = this.count;
        if (str2 == null) {
            i = 4;
            i2 += length + 8;
        } else {
            i = str2.length();
            i2 += (length + i) + 6;
        }
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write(c);
                writeStringWithDoubleQuote(str, CoreConstants.COLON_CHAR, z);
                writeStringWithDoubleQuote(str2, '\u0000', z);
                return;
            }
            expandCapacity(i2);
        }
        this.buf[this.count] = c;
        int i3 = this.count + 2;
        int i4 = i3 + length;
        this.buf[this.count + 1] = CoreConstants.DOUBLE_QUOTE_CHAR;
        str.getChars(0, length, this.buf, i3);
        this.count = i2;
        this.buf[i4] = CoreConstants.DOUBLE_QUOTE_CHAR;
        length = i4 + 1;
        i4 = length + 1;
        this.buf[length] = CoreConstants.COLON_CHAR;
        if (str2 == null) {
            i = i4 + 1;
            this.buf[i4] = 'n';
            length = i + 1;
            this.buf[i] = 'u';
            i = length + 1;
            this.buf[length] = 'l';
            length = i + 1;
            this.buf[i] = 'l';
            return;
        }
        int i5 = i4 + 1;
        this.buf[i4] = CoreConstants.DOUBLE_QUOTE_CHAR;
        int i6 = i5 + i;
        str2.getChars(0, i, this.buf, i5);
        if (z && !isEnabled(SerializerFeature.DisableCheckSpecialChar)) {
            int i7 = 0;
            i4 = -1;
            length = -1;
            i3 = i5;
            int i8 = i2;
            i2 = 0;
            while (i3 < i6) {
                char c2;
                char c3 = this.buf[i3];
                if (c3 == ' ') {
                    i7++;
                    i2 = i8 + 4;
                    if (length == -1) {
                        length = i3;
                        i4 = i3;
                        i8 = i2;
                        i2 = c3;
                    } else {
                        i4 = i3;
                        i8 = i2;
                        c2 = c3;
                    }
                }
                if (c3 >= ']') {
                    i = length;
                    length = i4;
                    i4 = i7;
                } else if (isSpecial(c3, this.features)) {
                    i2 = i7 + 1;
                    if (length == -1) {
                        length = i3;
                        i4 = i2;
                        c2 = c3;
                        i = i3;
                    } else {
                        i4 = i2;
                        c2 = c3;
                        i = length;
                        length = i3;
                    }
                } else {
                    i = length;
                    length = i4;
                    i4 = i7;
                }
                i3++;
                i7 = i4;
                i4 = length;
                length = i;
            }
            if (i7 > 0) {
                i = i8 + i7;
                if (i > this.buf.length) {
                    expandCapacity(i);
                }
                this.count = i;
                if (i7 == 1) {
                    if (i2 == 8232) {
                        System.arraycopy(this.buf, i4 + 1, this.buf, i4 + 6, (i6 - i4) - 1);
                        this.buf[i4] = CoreConstants.ESCAPE_CHAR;
                        i = i4 + 1;
                        this.buf[i] = 'u';
                        i++;
                        this.buf[i] = '2';
                        i++;
                        this.buf[i] = '0';
                        i++;
                        this.buf[i] = '2';
                        this.buf[i + 1] = '8';
                    } else {
                        System.arraycopy(this.buf, i4 + 1, this.buf, i4 + 2, (i6 - i4) - 1);
                        this.buf[i4] = CoreConstants.ESCAPE_CHAR;
                        this.buf[i4 + 1] = CharTypes.replaceChars[i2];
                    }
                } else if (i7 > 1) {
                    i = length;
                    length = i6;
                    for (i2 = length - i5; i2 < str2.length(); i2++) {
                        char charAt = str2.charAt(i2);
                        if ((charAt >= CharTypes.specicalFlags_doubleQuotes.length || CharTypes.specicalFlags_doubleQuotes[charAt] == (byte) 0) && !(charAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                            i3 = i + 1;
                            this.buf[i] = charAt;
                            i = i3;
                        } else {
                            i7 = i + 1;
                            this.buf[i] = CoreConstants.ESCAPE_CHAR;
                            if (CharTypes.specicalFlags_doubleQuotes[charAt] == (byte) 1) {
                                i = i7 + 1;
                                this.buf[i7] = CharTypes.replaceChars[charAt];
                                length++;
                            } else {
                                i3 = i7 + 1;
                                this.buf[i7] = 'u';
                                i7 = i3 + 1;
                                this.buf[i3] = CharTypes.digits[(charAt >>> 12) & 15];
                                i3 = i7 + 1;
                                this.buf[i7] = CharTypes.digits[(charAt >>> 8) & 15];
                                i7 = i3 + 1;
                                this.buf[i3] = CharTypes.digits[(charAt >>> 4) & 15];
                                i = i7 + 1;
                                this.buf[i7] = CharTypes.digits[charAt & 15];
                                length += 5;
                            }
                        }
                    }
                }
            }
        }
        this.buf[this.count - 1] = CoreConstants.DOUBLE_QUOTE_CHAR;
    }

    static final boolean isSpecial(char c, int i) {
        if (c == ' ') {
            return false;
        }
        if (c == '/' && SerializerFeature.isEnabled(i, SerializerFeature.WriteSlashAsSpecial)) {
            return true;
        }
        if (c > '#' && c != CoreConstants.ESCAPE_CHAR) {
            return false;
        }
        if (c <= '\r' || c == CoreConstants.ESCAPE_CHAR || c == CoreConstants.DOUBLE_QUOTE_CHAR) {
            return true;
        }
        return false;
    }

    public void writeFieldValue(char c, String str, Enum<?> enumR) {
        if (enumR == null) {
            write(c);
            writeFieldName(str);
            writeNull();
        } else if (!isEnabled(SerializerFeature.WriteEnumUsingToString)) {
            writeFieldValue(c, str, enumR.ordinal());
        } else if (isEnabled(SerializerFeature.UseSingleQuotes)) {
            writeFieldValue(c, str, enumR.name());
        } else {
            writeFieldValueStringWithDoubleQuote(c, str, enumR.name(), false);
        }
    }

    public void writeFieldValue(char c, String str, BigDecimal bigDecimal) {
        write(c);
        writeFieldName(str);
        if (bigDecimal == null) {
            writeNull();
        } else {
            write(bigDecimal.toString());
        }
    }

    public void writeString(String str, char c) {
        if (isEnabled(SerializerFeature.UseSingleQuotes)) {
            writeStringWithSingleQuote(str);
            write(c);
            return;
        }
        writeStringWithDoubleQuote(str, c);
    }

    public void writeString(String str) {
        if (isEnabled(SerializerFeature.UseSingleQuotes)) {
            writeStringWithSingleQuote(str);
        } else {
            writeStringWithDoubleQuote(str, '\u0000');
        }
    }

    private void writeStringWithSingleQuote(String str) {
        int i = 0;
        int i2;
        if (str == null) {
            i2 = this.count + 4;
            if (i2 > this.buf.length) {
                expandCapacity(i2);
            }
            "null".getChars(0, 4, this.buf, this.count);
            this.count = i2;
            return;
        }
        char charAt;
        i2 = str.length();
        int i3 = (this.count + i2) + 2;
        if (i3 > this.buf.length) {
            if (this.writer != null) {
                write((char) CoreConstants.SINGLE_QUOTE_CHAR);
                while (i < str.length()) {
                    charAt = str.charAt(i);
                    if (charAt <= '\r' || charAt == CoreConstants.ESCAPE_CHAR || charAt == CoreConstants.SINGLE_QUOTE_CHAR || (charAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        write((char) CoreConstants.ESCAPE_CHAR);
                        write(CharTypes.replaceChars[charAt]);
                    } else {
                        write(charAt);
                    }
                    i++;
                }
                write((char) CoreConstants.SINGLE_QUOTE_CHAR);
                return;
            }
            expandCapacity(i3);
        }
        int i4 = this.count + 1;
        int i5 = i4 + i2;
        this.buf[this.count] = CoreConstants.SINGLE_QUOTE_CHAR;
        str.getChars(0, i2, this.buf, i4);
        this.count = i3;
        int i6 = -1;
        int i7 = i4;
        int i8 = 0;
        while (i7 < i5) {
            charAt = this.buf[i7];
            if (charAt <= '\r' || charAt == CoreConstants.ESCAPE_CHAR || charAt == CoreConstants.SINGLE_QUOTE_CHAR || (charAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                i6 = i8 + 1;
                i = charAt;
                i2 = i7;
            } else {
                i2 = i6;
                i6 = i8;
            }
            i7++;
            i8 = i6;
            i6 = i2;
        }
        i2 = i3 + i8;
        if (i2 > this.buf.length) {
            expandCapacity(i2);
        }
        this.count = i2;
        if (i8 == 1) {
            System.arraycopy(this.buf, i6 + 1, this.buf, i6 + 2, (i5 - i6) - 1);
            this.buf[i6] = CoreConstants.ESCAPE_CHAR;
            this.buf[i6 + 1] = CharTypes.replaceChars[i];
        } else if (i8 > 1) {
            System.arraycopy(this.buf, i6 + 1, this.buf, i6 + 2, (i5 - i6) - 1);
            this.buf[i6] = CoreConstants.ESCAPE_CHAR;
            i6++;
            this.buf[i6] = CharTypes.replaceChars[i];
            i = i5 + 1;
            for (i2 = i6 - 2; i2 >= i4; i2--) {
                char c = this.buf[i2];
                if (c <= '\r' || c == CoreConstants.ESCAPE_CHAR || c == CoreConstants.SINGLE_QUOTE_CHAR || (c == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                    System.arraycopy(this.buf, i2 + 1, this.buf, i2 + 2, (i - i2) - 1);
                    this.buf[i2] = CoreConstants.ESCAPE_CHAR;
                    this.buf[i2 + 1] = CharTypes.replaceChars[c];
                    i++;
                }
            }
        }
        this.buf[this.count - 1] = CoreConstants.SINGLE_QUOTE_CHAR;
    }

    public void writeFieldName(String str) {
        writeFieldName(str, false);
    }

    public void writeFieldName(String str, boolean z) {
        if (str == null) {
            write("null:");
        } else if (isEnabled(SerializerFeature.UseSingleQuotes)) {
            if (isEnabled(SerializerFeature.QuoteFieldNames)) {
                writeStringWithSingleQuote(str);
                write((char) CoreConstants.COLON_CHAR);
                return;
            }
            writeKeyWithSingleQuoteIfHasSpecial(str);
        } else if (isEnabled(SerializerFeature.QuoteFieldNames)) {
            writeStringWithDoubleQuote(str, CoreConstants.COLON_CHAR, z);
        } else {
            writeKeyWithDoubleQuoteIfHasSpecial(str);
        }
    }

    private void writeKeyWithDoubleQuoteIfHasSpecial(String str) {
        int i;
        Object obj;
        int i2;
        byte[] bArr = CharTypes.specicalFlags_doubleQuotes;
        int length = str.length();
        int i3 = (this.count + length) + 1;
        if (i3 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i3);
            } else if (length == 0) {
                write((char) CoreConstants.DOUBLE_QUOTE_CHAR);
                write((char) CoreConstants.DOUBLE_QUOTE_CHAR);
                write((char) CoreConstants.COLON_CHAR);
                return;
            } else {
                char charAt;
                for (i = 0; i < length; i++) {
                    charAt = str.charAt(i);
                    if (charAt < bArr.length && bArr[charAt] != (byte) 0) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj != null) {
                    write((char) CoreConstants.DOUBLE_QUOTE_CHAR);
                }
                for (i2 = 0; i2 < length; i2++) {
                    charAt = str.charAt(i2);
                    if (charAt >= bArr.length || bArr[charAt] == (byte) 0) {
                        write(charAt);
                    } else {
                        write((char) CoreConstants.ESCAPE_CHAR);
                        write(CharTypes.replaceChars[charAt]);
                    }
                }
                if (obj != null) {
                    write((char) CoreConstants.DOUBLE_QUOTE_CHAR);
                }
                write((char) CoreConstants.COLON_CHAR);
                return;
            }
        }
        if (length == 0) {
            if (this.count + 3 > this.buf.length) {
                expandCapacity(this.count + 3);
            }
            char[] cArr = this.buf;
            i = this.count;
            this.count = i + 1;
            cArr[i] = CoreConstants.DOUBLE_QUOTE_CHAR;
            cArr = this.buf;
            i = this.count;
            this.count = i + 1;
            cArr[i] = CoreConstants.DOUBLE_QUOTE_CHAR;
            cArr = this.buf;
            i = this.count;
            this.count = i + 1;
            cArr[i] = CoreConstants.COLON_CHAR;
            return;
        }
        int i4 = this.count;
        int i5 = i4 + length;
        str.getChars(0, length, this.buf, i4);
        this.count = i3;
        obj = null;
        i2 = i4;
        while (i2 < i5) {
            char c = this.buf[i2];
            if (c < bArr.length && bArr[c] != (byte) 0) {
                if (obj == null) {
                    i3 += 3;
                    if (i3 > this.buf.length) {
                        expandCapacity(i3);
                    }
                    this.count = i3;
                    System.arraycopy(this.buf, i2 + 1, this.buf, i2 + 3, (i5 - i2) - 1);
                    System.arraycopy(this.buf, 0, this.buf, 1, i2);
                    this.buf[i4] = CoreConstants.DOUBLE_QUOTE_CHAR;
                    i2++;
                    this.buf[i2] = CoreConstants.ESCAPE_CHAR;
                    i2++;
                    this.buf[i2] = CharTypes.replaceChars[c];
                    i5 += 2;
                    this.buf[this.count - 2] = CoreConstants.DOUBLE_QUOTE_CHAR;
                    obj = 1;
                } else {
                    i3++;
                    if (i3 > this.buf.length) {
                        expandCapacity(i3);
                    }
                    this.count = i3;
                    System.arraycopy(this.buf, i2 + 1, this.buf, i2 + 2, i5 - i2);
                    this.buf[i2] = CoreConstants.ESCAPE_CHAR;
                    i2++;
                    this.buf[i2] = CharTypes.replaceChars[c];
                    i5++;
                }
            }
            i2++;
        }
        this.buf[this.count - 1] = CoreConstants.COLON_CHAR;
    }

    private void writeKeyWithSingleQuoteIfHasSpecial(String str) {
        int i;
        Object obj;
        int i2;
        byte[] bArr = CharTypes.specicalFlags_singleQuotes;
        int length = str.length();
        int i3 = (this.count + length) + 1;
        if (i3 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i3);
            } else if (length == 0) {
                write((char) CoreConstants.SINGLE_QUOTE_CHAR);
                write((char) CoreConstants.SINGLE_QUOTE_CHAR);
                write((char) CoreConstants.COLON_CHAR);
                return;
            } else {
                char charAt;
                for (i = 0; i < length; i++) {
                    charAt = str.charAt(i);
                    if (charAt < bArr.length && bArr[charAt] != (byte) 0) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj != null) {
                    write((char) CoreConstants.SINGLE_QUOTE_CHAR);
                }
                for (i2 = 0; i2 < length; i2++) {
                    charAt = str.charAt(i2);
                    if (charAt >= bArr.length || bArr[charAt] == (byte) 0) {
                        write(charAt);
                    } else {
                        write((char) CoreConstants.ESCAPE_CHAR);
                        write(CharTypes.replaceChars[charAt]);
                    }
                }
                if (obj != null) {
                    write((char) CoreConstants.SINGLE_QUOTE_CHAR);
                }
                write((char) CoreConstants.COLON_CHAR);
                return;
            }
        }
        if (length == 0) {
            if (this.count + 3 > this.buf.length) {
                expandCapacity(this.count + 3);
            }
            char[] cArr = this.buf;
            i = this.count;
            this.count = i + 1;
            cArr[i] = CoreConstants.SINGLE_QUOTE_CHAR;
            cArr = this.buf;
            i = this.count;
            this.count = i + 1;
            cArr[i] = CoreConstants.SINGLE_QUOTE_CHAR;
            cArr = this.buf;
            i = this.count;
            this.count = i + 1;
            cArr[i] = CoreConstants.COLON_CHAR;
            return;
        }
        int i4 = this.count;
        int i5 = i4 + length;
        str.getChars(0, length, this.buf, i4);
        this.count = i3;
        obj = null;
        i2 = i4;
        while (i2 < i5) {
            char c = this.buf[i2];
            if (c < bArr.length && bArr[c] != (byte) 0) {
                if (obj == null) {
                    i3 += 3;
                    if (i3 > this.buf.length) {
                        expandCapacity(i3);
                    }
                    this.count = i3;
                    System.arraycopy(this.buf, i2 + 1, this.buf, i2 + 3, (i5 - i2) - 1);
                    System.arraycopy(this.buf, 0, this.buf, 1, i2);
                    this.buf[i4] = CoreConstants.SINGLE_QUOTE_CHAR;
                    i2++;
                    this.buf[i2] = CoreConstants.ESCAPE_CHAR;
                    i2++;
                    this.buf[i2] = CharTypes.replaceChars[c];
                    i5 += 2;
                    this.buf[this.count - 2] = CoreConstants.SINGLE_QUOTE_CHAR;
                    obj = 1;
                } else {
                    i3++;
                    if (i3 > this.buf.length) {
                        expandCapacity(i3);
                    }
                    this.count = i3;
                    System.arraycopy(this.buf, i2 + 1, this.buf, i2 + 2, i5 - i2);
                    this.buf[i2] = CoreConstants.ESCAPE_CHAR;
                    i2++;
                    this.buf[i2] = CharTypes.replaceChars[c];
                    i5++;
                }
            }
            i2++;
        }
        this.buf[i3 - 1] = CoreConstants.COLON_CHAR;
    }

    public void flush() {
        if (this.writer != null) {
            try {
                this.writer.write(this.buf, 0, this.count);
                this.writer.flush();
                this.count = 0;
            } catch (Throwable e) {
                throw new JSONException(e.getMessage(), e);
            }
        }
    }
}
