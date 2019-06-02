package ch.qos.logback.core.encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ObjectStreamEncoder<E> extends EncoderBase<E> {
    public static final int START_PEBBLE = 1853421169;
    public static final int STOP_PEBBLE = 640373619;
    private int MAX_BUFFER_SIZE = 100;
    List<E> bufferList = new ArrayList(this.MAX_BUFFER_SIZE);

    public void close() throws IOException {
        writeBuffer();
    }

    public void doEncode(E e) throws IOException {
        this.bufferList.add(e);
        if (this.bufferList.size() == this.MAX_BUFFER_SIZE) {
            writeBuffer();
        }
    }

    public void init(OutputStream outputStream) throws IOException {
        super.init(outputStream);
        this.bufferList.clear();
    }

    void writeBuffer() throws IOException {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(10000);
        int size = this.bufferList.size();
        writeHeader(byteArrayOutputStream, size);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        for (Object writeObject : this.bufferList) {
            objectOutputStream.writeObject(writeObject);
        }
        this.bufferList.clear();
        objectOutputStream.flush();
        writeFooter(byteArrayOutputStream, size);
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        objectOutputStream.close();
        writeEndPosition(toByteArray);
        this.outputStream.write(toByteArray);
    }

    void writeEndPosition(byte[] bArr) {
        ByteArrayUtil.writeInt(bArr, 8, bArr.length - 8);
    }

    void writeFooter(ByteArrayOutputStream byteArrayOutputStream, int i) {
        ByteArrayUtil.writeInt(byteArrayOutputStream, STOP_PEBBLE);
        ByteArrayUtil.writeInt(byteArrayOutputStream, STOP_PEBBLE ^ i);
    }

    void writeHeader(ByteArrayOutputStream byteArrayOutputStream, int i) {
        ByteArrayUtil.writeInt(byteArrayOutputStream, START_PEBBLE);
        ByteArrayUtil.writeInt(byteArrayOutputStream, i);
        ByteArrayUtil.writeInt(byteArrayOutputStream, 0);
        ByteArrayUtil.writeInt(byteArrayOutputStream, START_PEBBLE ^ i);
    }
}
