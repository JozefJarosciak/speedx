package ch.qos.logback.repackage.com.mindprod.ledatastream;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class LEDataInputStream implements DataInput {
    private static final String EMBEDDED_COPYRIGHT = "copyright (c) 1999-2010 Roedy Green, Canadian Mind Products, http://mindprod.com";
    protected final DataInputStream dis;
    protected final InputStream is;
    protected final byte[] work = new byte[8];

    public LEDataInputStream(InputStream inputStream) {
        this.is = inputStream;
        this.dis = new DataInputStream(inputStream);
    }

    public static String readUTF(DataInput dataInput) throws IOException {
        return DataInputStream.readUTF(dataInput);
    }

    public final void close() throws IOException {
        this.dis.close();
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        return this.is.read(bArr, i, i2);
    }

    public final boolean readBoolean() throws IOException {
        return this.dis.readBoolean();
    }

    public final byte readByte() throws IOException {
        return this.dis.readByte();
    }

    public final char readChar() throws IOException {
        this.dis.readFully(this.work, 0, 2);
        return (char) (((this.work[1] & 255) << 8) | (this.work[0] & 255));
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    public final void readFully(byte[] bArr) throws IOException {
        this.dis.readFully(bArr, 0, bArr.length);
    }

    public final void readFully(byte[] bArr, int i, int i2) throws IOException {
        this.dis.readFully(bArr, i, i2);
    }

    public final int readInt() throws IOException {
        this.dis.readFully(this.work, 0, 4);
        return (((this.work[3] << 24) | ((this.work[2] & 255) << 16)) | ((this.work[1] & 255) << 8)) | (this.work[0] & 255);
    }

    public final String readLine() throws IOException {
        return this.dis.readLine();
    }

    public final long readLong() throws IOException {
        this.dis.readFully(this.work, 0, 8);
        return (((((((((long) this.work[7]) << 56) | (((long) (this.work[6] & 255)) << 48)) | (((long) (this.work[5] & 255)) << 40)) | (((long) (this.work[4] & 255)) << 32)) | (((long) (this.work[3] & 255)) << 24)) | (((long) (this.work[2] & 255)) << 16)) | (((long) (this.work[1] & 255)) << 8)) | ((long) (this.work[0] & 255));
    }

    public final short readShort() throws IOException {
        this.dis.readFully(this.work, 0, 2);
        return (short) (((this.work[1] & 255) << 8) | (this.work[0] & 255));
    }

    public final String readUTF() throws IOException {
        return this.dis.readUTF();
    }

    public final int readUnsignedByte() throws IOException {
        return this.dis.readUnsignedByte();
    }

    public final int readUnsignedShort() throws IOException {
        this.dis.readFully(this.work, 0, 2);
        return ((this.work[1] & 255) << 8) | (this.work[0] & 255);
    }

    public final int skipBytes(int i) throws IOException {
        return this.dis.skipBytes(i);
    }
}
