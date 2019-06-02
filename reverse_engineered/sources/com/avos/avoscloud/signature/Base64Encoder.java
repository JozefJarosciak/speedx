package com.avos.avoscloud.signature;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class Base64Encoder extends FilterOutputStream {
    private static final char[] chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private int carryOver;
    private int charCount;

    public Base64Encoder(OutputStream outputStream) {
        super(outputStream);
    }

    public void write(int i) throws IOException {
        if (i < 0) {
            i += 256;
        }
        int i2;
        if (this.charCount % 3 == 0) {
            i2 = i >> 2;
            this.carryOver = i & 3;
            this.out.write(chars[i2]);
        } else if (this.charCount % 3 == 1) {
            i2 = ((this.carryOver << 4) + (i >> 4)) & 63;
            this.carryOver = i & 15;
            this.out.write(chars[i2]);
        } else if (this.charCount % 3 == 2) {
            this.out.write(chars[((this.carryOver << 2) + (i >> 6)) & 63]);
            this.out.write(chars[i & 63]);
            this.carryOver = 0;
        }
        this.charCount++;
        if (this.charCount % 57 == 0) {
            this.out.write(10);
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        for (int i3 = 0; i3 < i2; i3++) {
            write(bArr[i + i3]);
        }
    }

    public void close() throws IOException {
        if (this.charCount % 3 == 1) {
            this.out.write(chars[(this.carryOver << 4) & 63]);
            this.out.write(61);
            this.out.write(61);
        } else if (this.charCount % 3 == 2) {
            this.out.write(chars[(this.carryOver << 2) & 63]);
            this.out.write(61);
        }
        super.close();
    }

    public static String encode(String str) {
        byte[] bArr = null;
        try {
            bArr = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return encode(bArr);
    }

    public static String encode(byte[] bArr) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) (((double) bArr.length) * 1.37d));
        Base64Encoder base64Encoder = new Base64Encoder(byteArrayOutputStream);
        try {
            base64Encoder.write(bArr);
            base64Encoder.close();
            return byteArrayOutputStream.toString("UTF-8");
        } catch (IOException e) {
            return null;
        }
    }
}
