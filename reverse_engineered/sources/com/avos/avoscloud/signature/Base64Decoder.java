package com.avos.avoscloud.signature;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Base64Decoder extends FilterInputStream {
    private static final char[] chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final int[] ints = new int[128];
    private int carryOver;
    private int charCount;

    static {
        for (int i = 0; i < 64; i++) {
            ints[chars[i]] = i;
        }
    }

    public Base64Decoder(InputStream inputStream) {
        super(inputStream);
    }

    public int read() throws IOException {
        int read;
        do {
            read = this.in.read();
            if (read == -1) {
                return -1;
            }
        } while (Character.isWhitespace((char) read));
        this.charCount++;
        if (read == 61) {
            return -1;
        }
        read = ints[read];
        int i = (this.charCount - 1) % 4;
        if (i == 0) {
            this.carryOver = read & 63;
            return read();
        } else if (i == 1) {
            r0 = ((this.carryOver << 2) + (read >> 4)) & 255;
            this.carryOver = read & 15;
            return r0;
        } else if (i == 2) {
            r0 = ((this.carryOver << 4) + (read >> 2)) & 255;
            this.carryOver = read & 3;
            return r0;
        } else if (i == 3) {
            return ((this.carryOver << 6) + read) & 255;
        } else {
            return -1;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr.length < (i2 + i) - 1) {
            throw new IOException("The input buffer is too small: " + i2 + " bytes requested starting at offset " + i + " while the buffer " + " is only " + bArr.length + " bytes long.");
        }
        int i3 = 0;
        while (i3 < i2) {
            int read = read();
            if (read == -1 && i3 == 0) {
                return -1;
            }
            if (read == -1) {
                break;
            }
            bArr[i + i3] = (byte) read;
            i3++;
        }
        return i3;
    }

    public static String decode(String str) {
        return new String(decodeToBytes(str));
    }

    public static byte[] decodeToBytes(String str) {
        byte[] bytes;
        byte[] bArr = null;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            bytes = null;
        }
        Base64Decoder base64Decoder = new Base64Decoder(new ByteArrayInputStream(bytes));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) (((double) bytes.length) * 0.67d));
        try {
            bytes = new byte[4096];
            while (true) {
                int read = base64Decoder.read(bytes);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bytes, 0, read);
            }
            byteArrayOutputStream.close();
            bArr = byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
        }
        return bArr;
    }

    public static void main(String[] strArr) throws Exception {
        Base64Decoder base64Decoder;
        Throwable th;
        if (strArr.length != 1) {
            System.err.println("Usage: java Base64Decoder fileToDecode");
            return;
        }
        try {
            base64Decoder = new Base64Decoder(new BufferedInputStream(new FileInputStream(strArr[0])));
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = base64Decoder.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    System.out.write(bArr, 0, read);
                }
                if (base64Decoder != null) {
                    base64Decoder.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (base64Decoder != null) {
                    base64Decoder.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            base64Decoder = null;
            if (base64Decoder != null) {
                base64Decoder.close();
            }
            throw th;
        }
    }
}
