package ch.qos.logback.core.encoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class EventObjectInputStream<E> extends InputStream {
    List<E> buffer = new ArrayList();
    int index = 0;
    NonClosableInputStream ncis;

    EventObjectInputStream(InputStream inputStream) throws IOException {
        this.ncis = new NonClosableInputStream(inputStream);
    }

    private void internalReset() {
        this.index = 0;
        this.buffer.clear();
    }

    public int available() throws IOException {
        return this.ncis.available();
    }

    public void close() throws IOException {
        this.ncis.realClose();
    }

    E getFromBuffer() {
        if (this.index >= this.buffer.size()) {
            return null;
        }
        List list = this.buffer;
        int i = this.index;
        this.index = i + 1;
        return list.get(i);
    }

    public int read() throws IOException {
        throw new UnsupportedOperationException("Only the readEvent method is supported.");
    }

    public E readEvent() throws IOException {
        E fromBuffer = getFromBuffer();
        if (fromBuffer != null) {
            return fromBuffer;
        }
        internalReset();
        int readHeader = readHeader();
        if (readHeader == -1) {
            return null;
        }
        readPayload(readHeader);
        readFooter(readHeader);
        return getFromBuffer();
    }

    E readEvents(ObjectInputStream objectInputStream) throws IOException {
        E readObject;
        ClassNotFoundException e;
        try {
            readObject = objectInputStream.readObject();
            try {
                this.buffer.add(readObject);
            } catch (ClassNotFoundException e2) {
                e = e2;
                e.printStackTrace();
                return readObject;
            }
        } catch (ClassNotFoundException e3) {
            ClassNotFoundException classNotFoundException = e3;
            readObject = null;
            e = classNotFoundException;
            e.printStackTrace();
            return readObject;
        }
        return readObject;
    }

    void readFooter(int i) throws IOException {
        byte[] bArr = new byte[8];
        if (this.ncis.read(bArr) == -1) {
            throw new IllegalStateException("Looks like a corrupt stream");
        } else if (ByteArrayUtil.readInt(bArr, 0) != ObjectStreamEncoder.STOP_PEBBLE) {
            throw new IllegalStateException("Looks like a corrupt stream");
        } else if (ByteArrayUtil.readInt(bArr, 4) != (ObjectStreamEncoder.STOP_PEBBLE ^ i)) {
            throw new IllegalStateException("Invalid checksum");
        }
    }

    int readHeader() throws IOException {
        int i = -1;
        byte[] bArr = new byte[16];
        if (this.ncis.read(bArr) != -1) {
            if (ByteArrayUtil.readInt(bArr, 0) != ObjectStreamEncoder.START_PEBBLE) {
                throw new IllegalStateException("Does not look like data created by ObjectStreamEncoder");
            }
            i = ByteArrayUtil.readInt(bArr, 4);
            if (ByteArrayUtil.readInt(bArr, 12) != (ObjectStreamEncoder.START_PEBBLE ^ i)) {
                throw new IllegalStateException("Invalid checksum");
            }
        }
        return i;
    }

    void readPayload(int i) throws IOException {
        List arrayList = new ArrayList(i);
        ObjectInputStream objectInputStream = new ObjectInputStream(this.ncis);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(readEvents(objectInputStream));
        }
        objectInputStream.close();
    }
}
