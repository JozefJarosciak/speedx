package com.google.common.io;

import ch.qos.logback.core.CoreConstants;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.RoundingMode;
import java.util.Arrays;

@GwtCompatible(emulated = true)
@Beta
public abstract class BaseEncoding {
    private static final BaseEncoding BASE16 = new StandardBaseEncoding("base16()", "0123456789ABCDEF", null);
    private static final BaseEncoding BASE32 = new StandardBaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", Character.valueOf('='));
    private static final BaseEncoding BASE32_HEX = new StandardBaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", Character.valueOf('='));
    private static final BaseEncoding BASE64 = new StandardBaseEncoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", Character.valueOf('='));
    private static final BaseEncoding BASE64_URL = new StandardBaseEncoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", Character.valueOf('='));

    private static final class Alphabet extends CharMatcher {
        final int bitsPerChar;
        final int bytesPerChunk;
        private final char[] chars;
        final int charsPerChunk;
        private final byte[] decodabet;
        final int mask;
        private final String name;
        private final boolean[] validPadding;

        Alphabet(String str, char[] cArr) {
            int i = 0;
            this.name = (String) Preconditions.checkNotNull(str);
            this.chars = (char[]) Preconditions.checkNotNull(cArr);
            try {
                this.bitsPerChar = IntMath.log2(cArr.length, RoundingMode.UNNECESSARY);
                int min = Math.min(8, Integer.lowestOneBit(this.bitsPerChar));
                this.charsPerChunk = 8 / min;
                this.bytesPerChunk = this.bitsPerChar / min;
                this.mask = cArr.length - 1;
                byte[] bArr = new byte[128];
                Arrays.fill(bArr, (byte) -1);
                for (min = 0; min < cArr.length; min++) {
                    char c = cArr[min];
                    Preconditions.checkArgument(CharMatcher.ASCII.matches(c), "Non-ASCII character: %s", Character.valueOf(c));
                    Preconditions.checkArgument(bArr[c] == (byte) -1, "Duplicate character: %s", Character.valueOf(c));
                    bArr[c] = (byte) min;
                }
                this.decodabet = bArr;
                boolean[] zArr = new boolean[this.charsPerChunk];
                while (i < this.bytesPerChunk) {
                    zArr[IntMath.divide(i * 8, this.bitsPerChar, RoundingMode.CEILING)] = true;
                    i++;
                }
                this.validPadding = zArr;
            } catch (Throwable e) {
                throw new IllegalArgumentException("Illegal alphabet length " + cArr.length, e);
            }
        }

        char encode(int i) {
            return this.chars[i];
        }

        boolean isValidPaddingStartPosition(int i) {
            return this.validPadding[i % this.charsPerChunk];
        }

        int decode(char c) throws IOException {
            if (c <= Ascii.MAX && this.decodabet[c] != (byte) -1) {
                return this.decodabet[c];
            }
            throw new DecodingException("Unrecognized character: " + c);
        }

        private boolean hasLowerCase() {
            for (char isLowerCase : this.chars) {
                if (Ascii.isLowerCase(isLowerCase)) {
                    return true;
                }
            }
            return false;
        }

        private boolean hasUpperCase() {
            for (char isUpperCase : this.chars) {
                if (Ascii.isUpperCase(isUpperCase)) {
                    return true;
                }
            }
            return false;
        }

        Alphabet upperCase() {
            int i = 0;
            if (!hasLowerCase()) {
                return this;
            }
            boolean z;
            if (hasUpperCase()) {
                z = false;
            } else {
                z = true;
            }
            Preconditions.checkState(z, "Cannot call upperCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            while (i < this.chars.length) {
                cArr[i] = Ascii.toUpperCase(this.chars[i]);
                i++;
            }
            return new Alphabet(String.valueOf(this.name).concat(".upperCase()"), cArr);
        }

        Alphabet lowerCase() {
            int i = 0;
            if (!hasUpperCase()) {
                return this;
            }
            boolean z;
            if (hasLowerCase()) {
                z = false;
            } else {
                z = true;
            }
            Preconditions.checkState(z, "Cannot call lowerCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            while (i < this.chars.length) {
                cArr[i] = Ascii.toLowerCase(this.chars[i]);
                i++;
            }
            return new Alphabet(String.valueOf(this.name).concat(".lowerCase()"), cArr);
        }

        public boolean matches(char c) {
            return CharMatcher.ASCII.matches(c) && this.decodabet[c] != (byte) -1;
        }

        public String toString() {
            return this.name;
        }
    }

    public static final class DecodingException extends IOException {
        DecodingException(String str) {
            super(str);
        }

        DecodingException(Throwable th) {
            super(th);
        }
    }

    static final class SeparatedBaseEncoding extends BaseEncoding {
        private final int afterEveryChars;
        private final BaseEncoding delegate;
        private final String separator;
        private final CharMatcher separatorChars;

        SeparatedBaseEncoding(BaseEncoding baseEncoding, String str, int i) {
            boolean z;
            this.delegate = (BaseEncoding) Preconditions.checkNotNull(baseEncoding);
            this.separator = (String) Preconditions.checkNotNull(str);
            this.afterEveryChars = i;
            if (i > 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "Cannot add a separator after every %s chars", Integer.valueOf(i));
            this.separatorChars = CharMatcher.anyOf(str).precomputed();
        }

        CharMatcher padding() {
            return this.delegate.padding();
        }

        int maxEncodedSize(int i) {
            int maxEncodedSize = this.delegate.maxEncodedSize(i);
            return maxEncodedSize + (this.separator.length() * IntMath.divide(Math.max(0, maxEncodedSize - 1), this.afterEveryChars, RoundingMode.FLOOR));
        }

        ByteOutput encodingStream(CharOutput charOutput) {
            return this.delegate.encodingStream(BaseEncoding.separatingOutput(charOutput, this.separator, this.afterEveryChars));
        }

        int maxDecodedSize(int i) {
            return this.delegate.maxDecodedSize(i);
        }

        ByteInput decodingStream(CharInput charInput) {
            return this.delegate.decodingStream(BaseEncoding.ignoringInput(charInput, this.separatorChars));
        }

        public BaseEncoding omitPadding() {
            return this.delegate.omitPadding().withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding withPadChar(char c) {
            return this.delegate.withPadChar(c).withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding withSeparator(String str, int i) {
            throw new UnsupportedOperationException("Already have a separator");
        }

        public BaseEncoding upperCase() {
            return this.delegate.upperCase().withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding lowerCase() {
            return this.delegate.lowerCase().withSeparator(this.separator, this.afterEveryChars);
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.delegate.toString()));
            String valueOf2 = String.valueOf(String.valueOf(this.separator));
            return new StringBuilder((valueOf.length() + 31) + valueOf2.length()).append(valueOf).append(".withSeparator(\"").append(valueOf2).append("\", ").append(this.afterEveryChars).append(")").toString();
        }
    }

    static final class StandardBaseEncoding extends BaseEncoding {
        private final Alphabet alphabet;
        private transient BaseEncoding lowerCase;
        private final Character paddingChar;
        private transient BaseEncoding upperCase;

        StandardBaseEncoding(String str, String str2, Character ch) {
            this(new Alphabet(str, str2.toCharArray()), ch);
        }

        StandardBaseEncoding(Alphabet alphabet, Character ch) {
            boolean z;
            this.alphabet = (Alphabet) Preconditions.checkNotNull(alphabet);
            if (ch == null || !alphabet.matches(ch.charValue())) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "Padding character %s was already in alphabet", ch);
            this.paddingChar = ch;
        }

        CharMatcher padding() {
            return this.paddingChar == null ? CharMatcher.NONE : CharMatcher.is(this.paddingChar.charValue());
        }

        int maxEncodedSize(int i) {
            return this.alphabet.charsPerChunk * IntMath.divide(i, this.alphabet.bytesPerChunk, RoundingMode.CEILING);
        }

        ByteOutput encodingStream(final CharOutput charOutput) {
            Preconditions.checkNotNull(charOutput);
            return new ByteOutput() {
                int bitBuffer = 0;
                int bitBufferLength = 0;
                int writtenChars = 0;

                public void write(byte b) throws IOException {
                    this.bitBuffer <<= 8;
                    this.bitBuffer |= b & 255;
                    this.bitBufferLength += 8;
                    while (this.bitBufferLength >= StandardBaseEncoding.this.alphabet.bitsPerChar) {
                        charOutput.write(StandardBaseEncoding.this.alphabet.encode((this.bitBuffer >> (this.bitBufferLength - StandardBaseEncoding.this.alphabet.bitsPerChar)) & StandardBaseEncoding.this.alphabet.mask));
                        this.writtenChars++;
                        this.bitBufferLength -= StandardBaseEncoding.this.alphabet.bitsPerChar;
                    }
                }

                public void flush() throws IOException {
                    charOutput.flush();
                }

                public void close() throws IOException {
                    if (this.bitBufferLength > 0) {
                        charOutput.write(StandardBaseEncoding.this.alphabet.encode((this.bitBuffer << (StandardBaseEncoding.this.alphabet.bitsPerChar - this.bitBufferLength)) & StandardBaseEncoding.this.alphabet.mask));
                        this.writtenChars++;
                        if (StandardBaseEncoding.this.paddingChar != null) {
                            while (this.writtenChars % StandardBaseEncoding.this.alphabet.charsPerChunk != 0) {
                                charOutput.write(StandardBaseEncoding.this.paddingChar.charValue());
                                this.writtenChars++;
                            }
                        }
                    }
                    charOutput.close();
                }
            };
        }

        int maxDecodedSize(int i) {
            return (int) (((((long) this.alphabet.bitsPerChar) * ((long) i)) + 7) / 8);
        }

        ByteInput decodingStream(final CharInput charInput) {
            Preconditions.checkNotNull(charInput);
            return new ByteInput() {
                int bitBuffer = 0;
                int bitBufferLength = 0;
                boolean hitPadding = false;
                final CharMatcher paddingMatcher = StandardBaseEncoding.this.padding();
                int readChars = 0;

                /* JADX WARNING: inconsistent code. */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public int read() throws java.io.IOException {
                    /*
                    r5 = this;
                    r4 = 1;
                    r0 = -1;
                L_0x0002:
                    r1 = r2;
                    r1 = r1.read();
                    if (r1 != r0) goto L_0x0039;
                L_0x000a:
                    r1 = r5.hitPadding;
                    if (r1 != 0) goto L_0x00e7;
                L_0x000e:
                    r1 = com.google.common.io.BaseEncoding.StandardBaseEncoding.this;
                    r1 = r1.alphabet;
                    r2 = r5.readChars;
                    r1 = r1.isValidPaddingStartPosition(r2);
                    if (r1 != 0) goto L_0x00e7;
                L_0x001c:
                    r0 = new com.google.common.io.BaseEncoding$DecodingException;
                    r1 = r5.readChars;
                    r2 = new java.lang.StringBuilder;
                    r3 = 32;
                    r2.<init>(r3);
                    r3 = "Invalid input length ";
                    r2 = r2.append(r3);
                    r1 = r2.append(r1);
                    r1 = r1.toString();
                    r0.<init>(r1);
                    throw r0;
                L_0x0039:
                    r2 = r5.readChars;
                    r2 = r2 + 1;
                    r5.readChars = r2;
                    r1 = (char) r1;
                    r2 = r5.paddingMatcher;
                    r2 = r2.matches(r1);
                    if (r2 == 0) goto L_0x0080;
                L_0x0048:
                    r1 = r5.hitPadding;
                    if (r1 != 0) goto L_0x007d;
                L_0x004c:
                    r1 = r5.readChars;
                    if (r1 == r4) goto L_0x0060;
                L_0x0050:
                    r1 = com.google.common.io.BaseEncoding.StandardBaseEncoding.this;
                    r1 = r1.alphabet;
                    r2 = r5.readChars;
                    r2 = r2 + -1;
                    r1 = r1.isValidPaddingStartPosition(r2);
                    if (r1 != 0) goto L_0x007d;
                L_0x0060:
                    r0 = new com.google.common.io.BaseEncoding$DecodingException;
                    r1 = r5.readChars;
                    r2 = new java.lang.StringBuilder;
                    r3 = 41;
                    r2.<init>(r3);
                    r3 = "Padding cannot start at index ";
                    r2 = r2.append(r3);
                    r1 = r2.append(r1);
                    r1 = r1.toString();
                    r0.<init>(r1);
                    throw r0;
                L_0x007d:
                    r5.hitPadding = r4;
                    goto L_0x0002;
                L_0x0080:
                    r2 = r5.hitPadding;
                    if (r2 == 0) goto L_0x00ab;
                L_0x0084:
                    r0 = new com.google.common.io.BaseEncoding$DecodingException;
                    r2 = r5.readChars;
                    r3 = new java.lang.StringBuilder;
                    r4 = 61;
                    r3.<init>(r4);
                    r4 = "Expected padding character but found '";
                    r3 = r3.append(r4);
                    r1 = r3.append(r1);
                    r3 = "' at index ";
                    r1 = r1.append(r3);
                    r1 = r1.append(r2);
                    r1 = r1.toString();
                    r0.<init>(r1);
                    throw r0;
                L_0x00ab:
                    r2 = r5.bitBuffer;
                    r3 = com.google.common.io.BaseEncoding.StandardBaseEncoding.this;
                    r3 = r3.alphabet;
                    r3 = r3.bitsPerChar;
                    r2 = r2 << r3;
                    r5.bitBuffer = r2;
                    r2 = r5.bitBuffer;
                    r3 = com.google.common.io.BaseEncoding.StandardBaseEncoding.this;
                    r3 = r3.alphabet;
                    r1 = r3.decode(r1);
                    r1 = r1 | r2;
                    r5.bitBuffer = r1;
                    r1 = r5.bitBufferLength;
                    r2 = com.google.common.io.BaseEncoding.StandardBaseEncoding.this;
                    r2 = r2.alphabet;
                    r2 = r2.bitsPerChar;
                    r1 = r1 + r2;
                    r5.bitBufferLength = r1;
                    r1 = r5.bitBufferLength;
                    r2 = 8;
                    if (r1 < r2) goto L_0x0002;
                L_0x00da:
                    r0 = r5.bitBufferLength;
                    r0 = r0 + -8;
                    r5.bitBufferLength = r0;
                    r0 = r5.bitBuffer;
                    r1 = r5.bitBufferLength;
                    r0 = r0 >> r1;
                    r0 = r0 & 255;
                L_0x00e7:
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.BaseEncoding.StandardBaseEncoding.2.read():int");
                }

                public void close() throws IOException {
                    charInput.close();
                }
            };
        }

        public BaseEncoding omitPadding() {
            return this.paddingChar == null ? this : new StandardBaseEncoding(this.alphabet, null);
        }

        public BaseEncoding withPadChar(char c) {
            if (8 % this.alphabet.bitsPerChar != 0) {
                return (this.paddingChar == null || this.paddingChar.charValue() != c) ? new StandardBaseEncoding(this.alphabet, Character.valueOf(c)) : this;
            } else {
                return this;
            }
        }

        public BaseEncoding withSeparator(String str, int i) {
            Preconditions.checkNotNull(str);
            Preconditions.checkArgument(padding().or(this.alphabet).matchesNoneOf(str), "Separator cannot contain alphabet or padding characters");
            return new SeparatedBaseEncoding(this, str, i);
        }

        public BaseEncoding upperCase() {
            BaseEncoding baseEncoding = this.upperCase;
            if (baseEncoding == null) {
                Alphabet upperCase = this.alphabet.upperCase();
                baseEncoding = upperCase == this.alphabet ? this : new StandardBaseEncoding(upperCase, this.paddingChar);
                this.upperCase = baseEncoding;
            }
            return baseEncoding;
        }

        public BaseEncoding lowerCase() {
            BaseEncoding baseEncoding = this.lowerCase;
            if (baseEncoding == null) {
                Alphabet lowerCase = this.alphabet.lowerCase();
                baseEncoding = lowerCase == this.alphabet ? this : new StandardBaseEncoding(lowerCase, this.paddingChar);
                this.lowerCase = baseEncoding;
            }
            return baseEncoding;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("BaseEncoding.");
            stringBuilder.append(this.alphabet.toString());
            if (8 % this.alphabet.bitsPerChar != 0) {
                if (this.paddingChar == null) {
                    stringBuilder.append(".omitPadding()");
                } else {
                    stringBuilder.append(".withPadChar(").append(this.paddingChar).append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
                }
            }
            return stringBuilder.toString();
        }
    }

    abstract ByteInput decodingStream(CharInput charInput);

    abstract ByteOutput encodingStream(CharOutput charOutput);

    public abstract BaseEncoding lowerCase();

    abstract int maxDecodedSize(int i);

    abstract int maxEncodedSize(int i);

    public abstract BaseEncoding omitPadding();

    abstract CharMatcher padding();

    public abstract BaseEncoding upperCase();

    public abstract BaseEncoding withPadChar(char c);

    public abstract BaseEncoding withSeparator(String str, int i);

    BaseEncoding() {
    }

    public String encode(byte[] bArr) {
        return encode((byte[]) Preconditions.checkNotNull(bArr), 0, bArr.length);
    }

    public final String encode(byte[] bArr, int i, int i2) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        CharOutput stringBuilderOutput = GwtWorkarounds.stringBuilderOutput(maxEncodedSize(i2));
        ByteOutput encodingStream = encodingStream(stringBuilderOutput);
        int i3 = 0;
        while (i3 < i2) {
            try {
                encodingStream.write(bArr[i + i3]);
                i3++;
            } catch (IOException e) {
                throw new AssertionError("impossible");
            }
        }
        encodingStream.close();
        return stringBuilderOutput.toString();
    }

    @GwtIncompatible("Writer,OutputStream")
    public final OutputStream encodingStream(Writer writer) {
        return GwtWorkarounds.asOutputStream(encodingStream(GwtWorkarounds.asCharOutput(writer)));
    }

    @GwtIncompatible("ByteSink,CharSink")
    public final ByteSink encodingSink(final CharSink charSink) {
        Preconditions.checkNotNull(charSink);
        return new ByteSink() {
            public OutputStream openStream() throws IOException {
                return BaseEncoding.this.encodingStream(charSink.openStream());
            }
        };
    }

    private static byte[] extract(byte[] bArr, int i) {
        if (i == bArr.length) {
            return bArr;
        }
        Object obj = new byte[i];
        System.arraycopy(bArr, 0, obj, 0, i);
        return obj;
    }

    public final byte[] decode(CharSequence charSequence) {
        try {
            return decodeChecked(charSequence);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    final byte[] decodeChecked(CharSequence charSequence) throws DecodingException {
        CharSequence trimTrailingFrom = padding().trimTrailingFrom(charSequence);
        ByteInput decodingStream = decodingStream(GwtWorkarounds.asCharInput(trimTrailingFrom));
        byte[] bArr = new byte[maxDecodedSize(trimTrailingFrom.length())];
        int i = 0;
        try {
            int read = decodingStream.read();
            while (read != -1) {
                int i2 = i + 1;
                bArr[i] = (byte) read;
                read = decodingStream.read();
                i = i2;
            }
            return extract(bArr, i);
        } catch (DecodingException e) {
            throw e;
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    @GwtIncompatible("Reader,InputStream")
    public final InputStream decodingStream(Reader reader) {
        return GwtWorkarounds.asInputStream(decodingStream(GwtWorkarounds.asCharInput(reader)));
    }

    @GwtIncompatible("ByteSource,CharSource")
    public final ByteSource decodingSource(final CharSource charSource) {
        Preconditions.checkNotNull(charSource);
        return new ByteSource() {
            public InputStream openStream() throws IOException {
                return BaseEncoding.this.decodingStream(charSource.openStream());
            }
        };
    }

    public static BaseEncoding base64() {
        return BASE64;
    }

    public static BaseEncoding base64Url() {
        return BASE64_URL;
    }

    public static BaseEncoding base32() {
        return BASE32;
    }

    public static BaseEncoding base32Hex() {
        return BASE32_HEX;
    }

    public static BaseEncoding base16() {
        return BASE16;
    }

    static CharInput ignoringInput(final CharInput charInput, final CharMatcher charMatcher) {
        Preconditions.checkNotNull(charInput);
        Preconditions.checkNotNull(charMatcher);
        return new CharInput() {
            public int read() throws IOException {
                int read;
                do {
                    read = charInput.read();
                    if (read == -1) {
                        break;
                    }
                } while (charMatcher.matches((char) read));
                return read;
            }

            public void close() throws IOException {
                charInput.close();
            }
        };
    }

    static CharOutput separatingOutput(final CharOutput charOutput, final String str, final int i) {
        Preconditions.checkNotNull(charOutput);
        Preconditions.checkNotNull(str);
        Preconditions.checkArgument(i > 0);
        return new CharOutput() {
            int charsUntilSeparator = i;

            public void write(char c) throws IOException {
                if (this.charsUntilSeparator == 0) {
                    for (int i = 0; i < str.length(); i++) {
                        charOutput.write(str.charAt(i));
                    }
                    this.charsUntilSeparator = i;
                }
                charOutput.write(c);
                this.charsUntilSeparator--;
            }

            public void flush() throws IOException {
                charOutput.flush();
            }

            public void close() throws IOException {
                charOutput.close();
            }
        };
    }
}
