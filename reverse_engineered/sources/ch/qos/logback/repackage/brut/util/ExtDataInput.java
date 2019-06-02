package ch.qos.logback.repackage.brut.util;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExtDataInput extends DataInputDelegate {
    public ExtDataInput(DataInput dataInput) {
        super(dataInput);
    }

    public ExtDataInput(InputStream inputStream) {
        this(new DataInputStream(inputStream));
    }

    public int[] readIntArray(int i) throws IOException {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = readInt();
        }
        return iArr;
    }

    public String readNulEndedString(int i, boolean z) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(16);
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                break;
            }
            short readShort = readShort();
            if (readShort == (short) 0) {
                break;
            }
            stringBuilder.append((char) readShort);
            i = i2;
        }
        if (z) {
            skipBytes(i2 * 2);
        }
        return stringBuilder.toString();
    }

    public void skipCheckByte(byte b) throws IOException {
        if (readByte() != b) {
            throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", new Object[]{Byte.valueOf(b), Byte.valueOf(readByte())}));
        }
    }

    public void skipCheckInt(int i) throws IOException {
        if (readInt() != i) {
            throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", new Object[]{Integer.valueOf(i), Integer.valueOf(readInt())}));
        }
    }

    public void skipCheckShort(short s) throws IOException {
        if (readShort() != s) {
            throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", new Object[]{Short.valueOf(s), Short.valueOf(readShort())}));
        }
    }

    public void skipInt() throws IOException {
        skipBytes(4);
    }
}
