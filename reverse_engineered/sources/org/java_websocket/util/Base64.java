package org.java_websocket.util;

import android.support.v4.view.MotionEventCompat;
import com.facebook.stetho.dumpapp.Framer;
import com.google.common.base.Ascii;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final int DECODE = 0;
    public static final int DONT_GUNZIP = 4;
    public static final int DO_BREAK_LINES = 8;
    public static final int ENCODE = 1;
    private static final byte EQUALS_SIGN = (byte) 61;
    private static final byte EQUALS_SIGN_ENC = (byte) -1;
    public static final int GZIP = 2;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte NEW_LINE = (byte) 10;
    public static final int NO_OPTIONS = 0;
    public static final int ORDERED = 32;
    private static final String PREFERRED_ENCODING = "US-ASCII";
    public static final int URL_SAFE = 16;
    private static final byte WHITE_SPACE_ENC = (byte) -5;
    private static final byte[] _ORDERED_ALPHABET = new byte[]{Framer.STDIN_FRAME_PREFIX, (byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, Framer.STDIN_REQUEST_FRAME_PREFIX, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, Framer.EXIT_FRAME_PREFIX, (byte) 121, (byte) 122};
    private static final byte[] _ORDERED_DECODABET = new byte[]{(byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, (byte) -9, (byte) -9, WHITE_SPACE_ENC, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, WHITE_SPACE_ENC, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 0, (byte) -9, (byte) -9, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) -9, (byte) -9, (byte) -9, (byte) -1, (byte) -9, (byte) -9, (byte) -9, Ascii.VT, Ascii.FF, (byte) 13, Ascii.SO, Ascii.SI, Ascii.DLE, (byte) 17, Ascii.DC2, (byte) 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.EM, (byte) 26, Ascii.ESC, Ascii.FS, Ascii.GS, Ascii.RS, Ascii.US, (byte) 32, Framer.ENTER_FRAME_PREFIX, (byte) 34, (byte) 35, (byte) 36, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 37, (byte) -9, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, Framer.STDIN_FRAME_PREFIX, (byte) 46, (byte) 47, (byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, EQUALS_SIGN, (byte) 62, (byte) 63, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9};
    private static final byte[] _STANDARD_ALPHABET = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, Framer.EXIT_FRAME_PREFIX, (byte) 121, (byte) 122, (byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    private static final byte[] _STANDARD_DECODABET = new byte[]{(byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, (byte) -9, (byte) -9, WHITE_SPACE_ENC, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, WHITE_SPACE_ENC, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 62, (byte) -9, (byte) -9, (byte) -9, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, EQUALS_SIGN, (byte) -9, (byte) -9, (byte) -9, (byte) -1, (byte) -9, (byte) -9, (byte) -9, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, Ascii.VT, Ascii.FF, (byte) 13, Ascii.SO, Ascii.SI, Ascii.DLE, (byte) 17, Ascii.DC2, (byte) 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.EM, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 26, Ascii.ESC, Ascii.FS, Ascii.GS, Ascii.RS, Ascii.US, (byte) 32, Framer.ENTER_FRAME_PREFIX, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, Framer.STDIN_FRAME_PREFIX, (byte) 46, (byte) 47, (byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9};
    private static final byte[] _URL_SAFE_ALPHABET = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, Framer.EXIT_FRAME_PREFIX, (byte) 121, (byte) 122, (byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, Framer.STDIN_FRAME_PREFIX, Framer.STDIN_REQUEST_FRAME_PREFIX};
    private static final byte[] _URL_SAFE_DECODABET = new byte[]{(byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, (byte) -9, (byte) -9, WHITE_SPACE_ENC, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, WHITE_SPACE_ENC, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 62, (byte) -9, (byte) -9, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, EQUALS_SIGN, (byte) -9, (byte) -9, (byte) -9, (byte) -1, (byte) -9, (byte) -9, (byte) -9, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, Ascii.VT, Ascii.FF, (byte) 13, Ascii.SO, Ascii.SI, Ascii.DLE, (byte) 17, Ascii.DC2, (byte) 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.EM, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 63, (byte) -9, (byte) 26, Ascii.ESC, Ascii.FS, Ascii.GS, Ascii.RS, Ascii.US, (byte) 32, Framer.ENTER_FRAME_PREFIX, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, Framer.STDIN_FRAME_PREFIX, (byte) 46, (byte) 47, (byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9};

    public static class InputStream extends FilterInputStream {
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int numSigBytes;
        private int options;
        private int position;

        public InputStream(java.io.InputStream inputStream) {
            this(inputStream, 0);
        }

        public InputStream(java.io.InputStream inputStream, int i) {
            boolean z = true;
            super(inputStream);
            this.options = i;
            this.breakLines = (i & 8) > 0;
            if ((i & 1) <= 0) {
                z = false;
            }
            this.encode = z;
            this.bufferLength = this.encode ? 4 : 3;
            this.buffer = new byte[this.bufferLength];
            this.position = -1;
            this.lineLength = 0;
            this.decodabet = Base64.getDecodabet(i);
        }

        public int read() throws IOException {
            byte[] bArr;
            if (this.position < 0) {
                int i;
                if (this.encode) {
                    bArr = new byte[3];
                    i = 0;
                    int i2 = 0;
                    while (i < 3) {
                        int read = this.in.read();
                        if (read < 0) {
                            break;
                        }
                        bArr[i] = (byte) read;
                        i++;
                        i2++;
                    }
                    if (i2 <= 0) {
                        return -1;
                    }
                    Base64.encode3to4(bArr, 0, i2, this.buffer, 0, this.options);
                    this.position = 0;
                    this.numSigBytes = 4;
                } else {
                    byte[] bArr2 = new byte[4];
                    int i3 = 0;
                    while (i3 < 4) {
                        do {
                            i = this.in.read();
                            if (i < 0) {
                                break;
                            }
                        } while (this.decodabet[i & 127] <= Base64.WHITE_SPACE_ENC);
                        if (i < 0) {
                            break;
                        }
                        bArr2[i3] = (byte) i;
                        i3++;
                    }
                    if (i3 == 4) {
                        this.numSigBytes = Base64.decode4to3(bArr2, 0, this.buffer, 0, this.options);
                        this.position = 0;
                    } else if (i3 == 0) {
                        return -1;
                    } else {
                        throw new IOException("Improperly padded Base64 input.");
                    }
                }
            }
            if (this.position < 0) {
                throw new IOException("Error in Base64 code reading stream.");
            } else if (this.position >= this.numSigBytes) {
                return -1;
            } else {
                if (this.encode && this.breakLines && this.lineLength >= 76) {
                    this.lineLength = 0;
                    return 10;
                }
                this.lineLength++;
                bArr = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                byte b = bArr[i4];
                if (this.position >= this.bufferLength) {
                    this.position = -1;
                }
                return b & 255;
            }
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = 0;
            while (i3 < i2) {
                int read = read();
                if (read >= 0) {
                    bArr[i + i3] = (byte) read;
                    i3++;
                } else if (i3 == 0) {
                    return -1;
                } else {
                    return i3;
                }
            }
            return i3;
        }
    }

    public static class OutputStream extends FilterOutputStream {
        private byte[] b4;
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int options;
        private int position;
        private boolean suspendEncoding;

        public OutputStream(java.io.OutputStream outputStream) {
            this(outputStream, 1);
        }

        public OutputStream(java.io.OutputStream outputStream, int i) {
            int i2;
            boolean z = true;
            super(outputStream);
            this.breakLines = (i & 8) != 0;
            if ((i & 1) == 0) {
                z = false;
            }
            this.encode = z;
            if (this.encode) {
                i2 = 3;
            } else {
                i2 = 4;
            }
            this.bufferLength = i2;
            this.buffer = new byte[this.bufferLength];
            this.position = 0;
            this.lineLength = 0;
            this.suspendEncoding = false;
            this.b4 = new byte[4];
            this.options = i;
            this.decodabet = Base64.getDecodabet(i);
        }

        public void write(int i) throws IOException {
            if (this.suspendEncoding) {
                this.out.write(i);
            } else if (this.encode) {
                r0 = this.buffer;
                r1 = this.position;
                this.position = r1 + 1;
                r0[r1] = (byte) i;
                if (this.position >= this.bufferLength) {
                    this.out.write(Base64.encode3to4(this.b4, this.buffer, this.bufferLength, this.options));
                    this.lineLength += 4;
                    if (this.breakLines && this.lineLength >= 76) {
                        this.out.write(10);
                        this.lineLength = 0;
                    }
                    this.position = 0;
                }
            } else if (this.decodabet[i & 127] > Base64.WHITE_SPACE_ENC) {
                r0 = this.buffer;
                r1 = this.position;
                this.position = r1 + 1;
                r0[r1] = (byte) i;
                if (this.position >= this.bufferLength) {
                    this.out.write(this.b4, 0, Base64.decode4to3(this.buffer, 0, this.b4, 0, this.options));
                    this.position = 0;
                }
            } else if (this.decodabet[i & 127] != Base64.WHITE_SPACE_ENC) {
                throw new IOException("Invalid character in Base64 data.");
            }
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.suspendEncoding) {
                this.out.write(bArr, i, i2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                write(bArr[i + i3]);
            }
        }

        public void flushBase64() throws IOException {
            if (this.position <= 0) {
                return;
            }
            if (this.encode) {
                this.out.write(Base64.encode3to4(this.b4, this.buffer, this.position, this.options));
                this.position = 0;
                return;
            }
            throw new IOException("Base64 input not properly padded.");
        }

        public void close() throws IOException {
            flushBase64();
            super.close();
            this.buffer = null;
            this.out = null;
        }

        public void suspendEncoding() throws IOException {
            flushBase64();
            this.suspendEncoding = true;
        }

        public void resumeEncoding() {
            this.suspendEncoding = false;
        }
    }

    static {
        boolean z;
        if (Base64.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        $assertionsDisabled = z;
    }

    private static final byte[] getAlphabet(int i) {
        if ((i & 16) == 16) {
            return _URL_SAFE_ALPHABET;
        }
        if ((i & 32) == 32) {
            return _ORDERED_ALPHABET;
        }
        return _STANDARD_ALPHABET;
    }

    private static final byte[] getDecodabet(int i) {
        if ((i & 16) == 16) {
            return _URL_SAFE_DECODABET;
        }
        if ((i & 32) == 32) {
            return _ORDERED_DECODABET;
        }
        return _STANDARD_DECODABET;
    }

    private Base64() {
    }

    private static byte[] encode3to4(byte[] bArr, byte[] bArr2, int i, int i2) {
        encode3to4(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    private static byte[] encode3to4(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int i5 = 0;
        byte[] alphabet = getAlphabet(i4);
        int i6 = (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0);
        if (i2 > 2) {
            i5 = (bArr[i + 2] << 24) >>> 24;
        }
        i5 |= i6;
        switch (i2) {
            case 1:
                bArr2[i3] = alphabet[i5 >>> 18];
                bArr2[i3 + 1] = alphabet[(i5 >>> 12) & 63];
                bArr2[i3 + 2] = EQUALS_SIGN;
                bArr2[i3 + 3] = EQUALS_SIGN;
                break;
            case 2:
                bArr2[i3] = alphabet[i5 >>> 18];
                bArr2[i3 + 1] = alphabet[(i5 >>> 12) & 63];
                bArr2[i3 + 2] = alphabet[(i5 >>> 6) & 63];
                bArr2[i3 + 3] = EQUALS_SIGN;
                break;
            case 3:
                bArr2[i3] = alphabet[i5 >>> 18];
                bArr2[i3 + 1] = alphabet[(i5 >>> 12) & 63];
                bArr2[i3 + 2] = alphabet[(i5 >>> 6) & 63];
                bArr2[i3 + 3] = alphabet[i5 & 63];
                break;
        }
        return bArr2;
    }

    public static void encode(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        byte[] bArr = new byte[3];
        byte[] bArr2 = new byte[4];
        while (byteBuffer.hasRemaining()) {
            int min = Math.min(3, byteBuffer.remaining());
            byteBuffer.get(bArr, 0, min);
            encode3to4(bArr2, bArr, min, 0);
            byteBuffer2.put(bArr2);
        }
    }

    public static void encode(ByteBuffer byteBuffer, CharBuffer charBuffer) {
        byte[] bArr = new byte[3];
        byte[] bArr2 = new byte[4];
        while (byteBuffer.hasRemaining()) {
            int min = Math.min(3, byteBuffer.remaining());
            byteBuffer.get(bArr, 0, min);
            encode3to4(bArr2, bArr, min, 0);
            for (min = 0; min < 4; min++) {
                charBuffer.put((char) (bArr2[min] & 255));
            }
        }
    }

    public static String encodeObject(Serializable serializable) throws IOException {
        return encodeObject(serializable, 0);
    }

    public static String encodeObject(Serializable serializable, int i) throws IOException {
        java.io.OutputStream outputStream;
        GZIPOutputStream gZIPOutputStream;
        IOException e;
        java.io.OutputStream outputStream2;
        Throwable th;
        java.io.OutputStream outputStream3 = null;
        if (serializable == null) {
            throw new NullPointerException("Cannot serialize a null object.");
        }
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                ObjectOutputStream objectOutputStream;
                outputStream = new OutputStream(byteArrayOutputStream, i | 1);
                if ((i & 2) != 0) {
                    try {
                        gZIPOutputStream = new GZIPOutputStream(outputStream);
                    } catch (IOException e2) {
                        e = e2;
                        outputStream2 = outputStream3;
                        try {
                            throw e;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        outputStream2 = outputStream3;
                        try {
                            outputStream3.close();
                        } catch (Exception e3) {
                        }
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception e4) {
                        }
                        try {
                            outputStream.close();
                        } catch (Exception e5) {
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e6) {
                        }
                        throw th;
                    }
                    try {
                        objectOutputStream = new ObjectOutputStream(gZIPOutputStream);
                    } catch (IOException e7) {
                        e = e7;
                        throw e;
                    }
                } else {
                    outputStream2 = outputStream3;
                    objectOutputStream = new ObjectOutputStream(outputStream);
                }
                objectOutputStream.writeObject(serializable);
                try {
                    objectOutputStream.close();
                } catch (Exception e8) {
                }
                try {
                    gZIPOutputStream.close();
                } catch (Exception e9) {
                }
                try {
                    outputStream.close();
                } catch (Exception e10) {
                }
                try {
                    byteArrayOutputStream.close();
                } catch (Exception e11) {
                }
                try {
                    return new String(byteArrayOutputStream.toByteArray(), "US-ASCII");
                } catch (UnsupportedEncodingException e12) {
                    return new String(byteArrayOutputStream.toByteArray());
                }
            } catch (IOException e13) {
                e = e13;
                outputStream2 = outputStream3;
                outputStream = outputStream3;
                throw e;
            } catch (Throwable th4) {
                th = th4;
                outputStream2 = outputStream3;
                outputStream = outputStream3;
                outputStream3.close();
                gZIPOutputStream.close();
                outputStream.close();
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (IOException e14) {
            e = e14;
            gZIPOutputStream = outputStream3;
            outputStream = outputStream3;
            byteArrayOutputStream = outputStream3;
            throw e;
        } catch (Throwable th5) {
            th = th5;
            outputStream2 = outputStream3;
            outputStream = outputStream3;
            java.io.OutputStream outputStream4 = outputStream3;
            outputStream3.close();
            gZIPOutputStream.close();
            outputStream.close();
            byteArrayOutputStream.close();
            throw th;
        }
    }

    public static String encodeBytes(byte[] bArr) {
        String str = null;
        try {
            str = encodeBytes(bArr, 0, bArr.length, 0);
        } catch (IOException e) {
            if (!$assertionsDisabled) {
                throw new AssertionError(e.getMessage());
            }
        }
        if ($assertionsDisabled || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    public static String encodeBytes(byte[] bArr, int i) throws IOException {
        return encodeBytes(bArr, 0, bArr.length, i);
    }

    public static String encodeBytes(byte[] bArr, int i, int i2) {
        String str = null;
        try {
            str = encodeBytes(bArr, i, i2, 0);
        } catch (IOException e) {
            if (!$assertionsDisabled) {
                throw new AssertionError(e.getMessage());
            }
        }
        if ($assertionsDisabled || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    public static String encodeBytes(byte[] bArr, int i, int i2, int i3) throws IOException {
        byte[] encodeBytesToBytes = encodeBytesToBytes(bArr, i, i2, i3);
        try {
            return new String(encodeBytesToBytes, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            return new String(encodeBytesToBytes);
        }
    }

    public static byte[] encodeBytesToBytes(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            bArr2 = encodeBytesToBytes(bArr, 0, bArr.length, 0);
        } catch (IOException e) {
            if (!$assertionsDisabled) {
                throw new AssertionError("IOExceptions only come from GZipping, which is turned off: " + e.getMessage());
            }
        }
        return bArr2;
    }

    public static byte[] encodeBytesToBytes(byte[] bArr, int i, int i2, int i3) throws IOException {
        OutputStream outputStream;
        IOException e;
        java.io.OutputStream outputStream2;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPOutputStream gZIPOutputStream = null;
        if (bArr == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        } else if (i < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + i);
        } else if (i2 < 0) {
            throw new IllegalArgumentException("Cannot have length offset: " + i2);
        } else if (i + i2 > bArr.length) {
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bArr.length)}));
        } else if ((i3 & 2) != 0) {
            try {
                GZIPOutputStream gZIPOutputStream2;
                java.io.OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    outputStream = new OutputStream(byteArrayOutputStream2, i3 | 1);
                    try {
                        gZIPOutputStream2 = new GZIPOutputStream(outputStream);
                    } catch (IOException e2) {
                        e = e2;
                        outputStream2 = byteArrayOutputStream2;
                        try {
                            throw e;
                        } catch (Throwable th2) {
                            th = th2;
                            byteArrayOutputStream = outputStream2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception e3) {
                        }
                        try {
                            outputStream.close();
                        } catch (Exception e4) {
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e5) {
                        }
                        throw th;
                    }
                } catch (IOException e6) {
                    e = e6;
                    outputStream = null;
                    outputStream2 = byteArrayOutputStream2;
                    throw e;
                } catch (Throwable th4) {
                    th = th4;
                    outputStream = null;
                    gZIPOutputStream.close();
                    outputStream.close();
                    byteArrayOutputStream.close();
                    throw th;
                }
                try {
                    gZIPOutputStream2.write(bArr, i, i2);
                    gZIPOutputStream2.close();
                    try {
                        gZIPOutputStream2.close();
                    } catch (Exception e7) {
                    }
                    try {
                        outputStream.close();
                    } catch (Exception e8) {
                    }
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception e9) {
                    }
                    return byteArrayOutputStream2.toByteArray();
                } catch (IOException e10) {
                    e = e10;
                    gZIPOutputStream = gZIPOutputStream2;
                    outputStream2 = byteArrayOutputStream2;
                    throw e;
                } catch (Throwable th5) {
                    th = th5;
                    gZIPOutputStream = gZIPOutputStream2;
                    gZIPOutputStream.close();
                    outputStream.close();
                    byteArrayOutputStream.close();
                    throw th;
                }
            } catch (IOException e11) {
                e = e11;
                outputStream = null;
                outputStream2 = null;
                throw e;
            } catch (Throwable th6) {
                th = th6;
                outputStream = null;
                byteArrayOutputStream = null;
                gZIPOutputStream.close();
                outputStream.close();
                byteArrayOutputStream.close();
                throw th;
            }
        } else {
            int i4;
            int i5 = (i3 & 8) != 0 ? 1 : 0;
            int i6 = (i2 / 3) * 4;
            if (i2 % 3 > 0) {
                i4 = 4;
            } else {
                i4 = 0;
            }
            i4 += i6;
            if (i5 != 0) {
                i4 += i4 / 76;
            }
            byte[] bArr2 = new byte[i4];
            int i7 = i2 - 2;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            while (i10 < i7) {
                encode3to4(bArr, i10 + i, 3, bArr2, i9, i3);
                i4 = i8 + 4;
                if (i5 != 0 && i4 >= 76) {
                    bArr2[i9 + 4] = (byte) 10;
                    i9++;
                    i4 = 0;
                }
                i9 += 4;
                i8 = i4;
                i10 += 3;
            }
            if (i10 < i2) {
                encode3to4(bArr, i10 + i, i2 - i10, bArr2, i9, i3);
                i9 += 4;
            }
            if (i9 > bArr2.length - 1) {
                return bArr2;
            }
            Object obj = new byte[i9];
            System.arraycopy(bArr2, 0, obj, 0, i9);
            return obj;
        }
    }

    private static int decode4to3(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr == null) {
            throw new NullPointerException("Source array was null.");
        } else if (bArr2 == null) {
            throw new NullPointerException("Destination array was null.");
        } else if (i < 0 || i + 3 >= bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i)}));
        } else if (i2 < 0 || i2 + 2 >= bArr2.length) {
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", new Object[]{Integer.valueOf(bArr2.length), Integer.valueOf(i2)}));
        } else {
            byte[] decodabet = getDecodabet(i3);
            if (bArr[i + 2] == EQUALS_SIGN) {
                bArr2[i2] = (byte) ((((decodabet[bArr[i]] & 255) << 18) | ((decodabet[bArr[i + 1]] & 255) << 12)) >>> 16);
                return 1;
            } else if (bArr[i + 3] == EQUALS_SIGN) {
                r0 = (((decodabet[bArr[i]] & 255) << 18) | ((decodabet[bArr[i + 1]] & 255) << 12)) | ((decodabet[bArr[i + 2]] & 255) << 6);
                bArr2[i2] = (byte) (r0 >>> 16);
                bArr2[i2 + 1] = (byte) (r0 >>> 8);
                return 2;
            } else {
                r0 = ((((decodabet[bArr[i]] & 255) << 18) | ((decodabet[bArr[i + 1]] & 255) << 12)) | ((decodabet[bArr[i + 2]] & 255) << 6)) | (decodabet[bArr[i + 3]] & 255);
                bArr2[i2] = (byte) (r0 >> 16);
                bArr2[i2 + 1] = (byte) (r0 >> 8);
                bArr2[i2 + 2] = (byte) r0;
                return 3;
            }
        }
    }

    public static byte[] decode(byte[] bArr) throws IOException {
        return decode(bArr, 0, bArr.length, 0);
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) throws IOException {
        if (bArr == null) {
            throw new NullPointerException("Cannot decode null source array.");
        } else if (i < 0 || i + i2 > bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
        } else if (i2 == 0) {
            return new byte[0];
        } else {
            if (i2 < 4) {
                throw new IllegalArgumentException("Base64-encoded string must have at least four characters, but length specified was " + i2);
            }
            int i4;
            byte[] decodabet = getDecodabet(i3);
            Object obj = new byte[((i2 * 3) / 4)];
            byte[] bArr2 = new byte[4];
            int i5 = i;
            int i6 = 0;
            int i7 = 0;
            while (i5 < i + i2) {
                byte b = decodabet[bArr[i5] & 255];
                if (b >= WHITE_SPACE_ENC) {
                    if (b >= (byte) -1) {
                        i4 = i6 + 1;
                        bArr2[i6] = bArr[i5];
                        if (i4 > 3) {
                            i4 = decode4to3(bArr2, 0, obj, i7, i3) + i7;
                            if (bArr[i5] == EQUALS_SIGN) {
                                break;
                            }
                            i6 = i4;
                            i4 = 0;
                        } else {
                            i6 = i7;
                        }
                    } else {
                        i4 = i6;
                        i6 = i7;
                    }
                    i5++;
                    i7 = i6;
                    i6 = i4;
                } else {
                    throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", new Object[]{Integer.valueOf(bArr[i5] & 255), Integer.valueOf(i5)}));
                }
            }
            i4 = i7;
            Object obj2 = new byte[i4];
            System.arraycopy(obj, 0, obj2, 0, i4);
            return obj2;
        }
    }

    public static byte[] decode(String str) throws IOException {
        return decode(str, 0);
    }

    public static byte[] decode(String str, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        IOException e;
        ByteArrayInputStream byteArrayInputStream2;
        Throwable th;
        GZIPInputStream gZIPInputStream = null;
        if (str == null) {
            throw new NullPointerException("Input string was null.");
        }
        byte[] bytes;
        try {
            bytes = str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e2) {
            bytes = str.getBytes();
        }
        bytes = decode(bytes, 0, bytes.length, i);
        int i2;
        if ((i & 4) != 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (bytes != null && bytes.length >= 4 && r1 == 0 && 35615 == ((bytes[0] & 255) | ((bytes[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) {
            byte[] bArr = new byte[2048];
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byteArrayInputStream = new ByteArrayInputStream(bytes);
                    try {
                        GZIPInputStream gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream);
                        while (true) {
                            try {
                                int read = gZIPInputStream2.read(bArr);
                                if (read < 0) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            } catch (IOException e3) {
                                e = e3;
                                gZIPInputStream = gZIPInputStream2;
                                byteArrayInputStream2 = byteArrayInputStream;
                            } catch (Throwable th2) {
                                th = th2;
                                gZIPInputStream = gZIPInputStream2;
                            }
                        }
                        bytes = byteArrayOutputStream.toByteArray();
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e4) {
                        }
                        try {
                            gZIPInputStream2.close();
                        } catch (Exception e5) {
                        }
                        try {
                            byteArrayInputStream.close();
                        } catch (Exception e6) {
                        }
                    } catch (IOException e7) {
                        e = e7;
                        byteArrayInputStream2 = byteArrayInputStream;
                        try {
                            e.printStackTrace();
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e8) {
                            }
                            try {
                                gZIPInputStream.close();
                            } catch (Exception e9) {
                            }
                            try {
                                byteArrayInputStream2.close();
                            } catch (Exception e10) {
                            }
                            return bytes;
                        } catch (Throwable th3) {
                            th = th3;
                            byteArrayInputStream = byteArrayInputStream2;
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e11) {
                            }
                            try {
                                gZIPInputStream.close();
                            } catch (Exception e12) {
                            }
                            try {
                                byteArrayInputStream.close();
                            } catch (Exception e13) {
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        byteArrayOutputStream.close();
                        gZIPInputStream.close();
                        byteArrayInputStream.close();
                        throw th;
                    }
                } catch (IOException e14) {
                    e = e14;
                    byteArrayInputStream2 = null;
                    e.printStackTrace();
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream2.close();
                    return bytes;
                } catch (Throwable th5) {
                    th = th5;
                    byteArrayInputStream = null;
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    throw th;
                }
            } catch (IOException e15) {
                e = e15;
                byteArrayOutputStream = null;
                byteArrayInputStream2 = null;
                e.printStackTrace();
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                byteArrayInputStream2.close();
                return bytes;
            } catch (Throwable th6) {
                th = th6;
                byteArrayOutputStream = null;
                byteArrayInputStream = null;
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                byteArrayInputStream.close();
                throw th;
            }
        }
        return bytes;
    }

    public static Object decodeToObject(String str) throws IOException, ClassNotFoundException {
        return decodeToObject(str, 0, null);
    }

    public static Object decodeToObject(String str, int i, final ClassLoader classLoader) throws IOException, ClassNotFoundException {
        IOException e;
        Throwable th;
        ClassNotFoundException e2;
        java.io.InputStream inputStream = null;
        ByteArrayInputStream byteArrayInputStream;
        try {
            ObjectInputStream objectInputStream;
            byteArrayInputStream = new ByteArrayInputStream(decode(str, i));
            if (classLoader == null) {
                try {
                    objectInputStream = new ObjectInputStream(byteArrayInputStream);
                } catch (IOException e3) {
                    e = e3;
                    try {
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (ClassNotFoundException e4) {
                    e2 = e4;
                    throw e2;
                }
            } else {
                objectInputStream = new ObjectInputStream(byteArrayInputStream) {
                    public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                        Class<?> cls = Class.forName(objectStreamClass.getName(), false, classLoader);
                        if (cls == null) {
                            return super.resolveClass(objectStreamClass);
                        }
                        return cls;
                    }
                };
            }
            Object readObject = objectInputStream.readObject();
            try {
                byteArrayInputStream.close();
            } catch (Exception e5) {
            }
            try {
                objectInputStream.close();
            } catch (Exception e6) {
            }
            return readObject;
        } catch (IOException e7) {
            e = e7;
            byteArrayInputStream = inputStream;
            throw e;
        } catch (ClassNotFoundException e8) {
            e2 = e8;
            byteArrayInputStream = inputStream;
            throw e2;
        } catch (Throwable th3) {
            th = th3;
            java.io.InputStream inputStream2 = inputStream;
            try {
                byteArrayInputStream.close();
            } catch (Exception e9) {
            }
            try {
                inputStream.close();
            } catch (Exception e10) {
            }
            throw th;
        }
    }

    public static void encodeToFile(byte[] bArr, String str) throws IOException {
        IOException e;
        Throwable th;
        if (bArr == null) {
            throw new NullPointerException("Data to encode was null.");
        }
        OutputStream outputStream = null;
        OutputStream outputStream2;
        try {
            outputStream2 = new OutputStream(new FileOutputStream(str), 1);
            try {
                outputStream2.write(bArr);
                try {
                    outputStream2.close();
                } catch (Exception e2) {
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = outputStream2;
                }
            }
        } catch (IOException e4) {
            e = e4;
            outputStream2 = null;
            throw e;
        } catch (Throwable th3) {
            th = th3;
            try {
                outputStream.close();
            } catch (Exception e5) {
            }
            throw th;
        }
    }

    public static void decodeToFile(String str, String str2) throws IOException {
        IOException e;
        Throwable th;
        OutputStream outputStream;
        try {
            outputStream = new OutputStream(new FileOutputStream(str2), 0);
            try {
                outputStream.write(str.getBytes("US-ASCII"));
                try {
                    outputStream.close();
                } catch (Exception e2) {
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (IOException e4) {
            e = e4;
            outputStream = null;
            throw e;
        } catch (Throwable th3) {
            th = th3;
            outputStream = null;
            try {
                outputStream.close();
            } catch (Exception e5) {
            }
            throw th;
        }
    }

    public static byte[] decodeFromFile(String str) throws IOException {
        IOException e;
        int i = 0;
        InputStream inputStream = null;
        Throwable th;
        try {
            File file = new File(str);
            if (file.length() > 2147483647L) {
                throw new IOException("File is too big for this convenience method (" + file.length() + " bytes).");
            }
            Object obj = new byte[((int) file.length())];
            InputStream inputStream2 = new InputStream(new BufferedInputStream(new FileInputStream(file)), 0);
            while (true) {
                try {
                    int read = inputStream2.read(obj, i, 4096);
                    if (read < 0) {
                        break;
                    }
                    i += read;
                } catch (IOException e2) {
                    e = e2;
                    inputStream = inputStream2;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = inputStream2;
                }
            }
            Object obj2 = new byte[i];
            System.arraycopy(obj, 0, obj2, 0, i);
            try {
                inputStream2.close();
            } catch (Exception e3) {
            }
            return obj2;
            try {
                inputStream.close();
            } catch (Exception e4) {
            }
            throw th;
            throw th;
        } catch (IOException e5) {
            e = e5;
            try {
                throw e;
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    public static String encodeFromFile(String str) throws IOException {
        InputStream inputStream;
        IOException e;
        Throwable th;
        int i = 0;
        InputStream inputStream2 = null;
        try {
            File file = new File(str);
            byte[] bArr = new byte[Math.max((int) ((((double) file.length()) * 1.4d) + 1.0d), 40)];
            inputStream = new InputStream(new BufferedInputStream(new FileInputStream(file)), 1);
            while (true) {
                try {
                    int read = inputStream.read(bArr, i, 4096);
                    if (read < 0) {
                        break;
                    }
                    i += read;
                } catch (IOException e2) {
                    e = e2;
                }
            }
            String str2 = new String(bArr, 0, i, "US-ASCII");
            try {
                inputStream.close();
            } catch (Exception e3) {
            }
            return str2;
        } catch (IOException e4) {
            e = e4;
            inputStream = null;
            try {
                throw e;
            } catch (Throwable th2) {
                th = th2;
                inputStream2 = inputStream;
            }
        } catch (Throwable th3) {
            th = th3;
            try {
                inputStream2.close();
            } catch (Exception e5) {
            }
            throw th;
        }
    }

    public static void encodeFileToFile(String str, String str2) throws IOException {
        IOException e;
        Throwable th;
        String encodeFromFile = encodeFromFile(str);
        java.io.OutputStream bufferedOutputStream;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
            try {
                bufferedOutputStream.write(encodeFromFile.getBytes("US-ASCII"));
                try {
                    bufferedOutputStream.close();
                } catch (Exception e2) {
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (IOException e4) {
            e = e4;
            bufferedOutputStream = null;
            throw e;
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = null;
            try {
                bufferedOutputStream.close();
            } catch (Exception e5) {
            }
            throw th;
        }
    }

    public static void decodeFileToFile(String str, String str2) throws IOException {
        IOException e;
        Throwable th;
        byte[] decodeFromFile = decodeFromFile(str);
        java.io.OutputStream outputStream = null;
        java.io.OutputStream bufferedOutputStream;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
            try {
                bufferedOutputStream.write(decodeFromFile);
                try {
                    bufferedOutputStream.close();
                } catch (Exception e2) {
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = bufferedOutputStream;
                }
            }
        } catch (IOException e4) {
            e = e4;
            bufferedOutputStream = null;
            throw e;
        } catch (Throwable th3) {
            th = th3;
            try {
                outputStream.close();
            } catch (Exception e5) {
            }
            throw th;
        }
    }
}
