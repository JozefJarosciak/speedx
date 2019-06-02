package ch.qos.logback.core.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SyslogOutputStream extends OutputStream {
    private static final int MAX_LEN = 1024;
    private InetAddress address;
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private DatagramSocket ds;
    private final int port;

    public SyslogOutputStream(String str, int i) throws UnknownHostException, SocketException {
        this.address = InetAddress.getByName(str);
        this.port = i;
        this.ds = new DatagramSocket();
    }

    public void close() {
        this.address = null;
        this.ds = null;
    }

    public void flush() throws IOException {
        byte[] toByteArray = this.baos.toByteArray();
        DatagramPacket datagramPacket = new DatagramPacket(toByteArray, toByteArray.length, this.address, this.port);
        if (this.baos.size() > 1024) {
            this.baos = new ByteArrayOutputStream();
        } else {
            this.baos.reset();
        }
        if (toByteArray.length != 0 && this.ds != null) {
            this.ds.send(datagramPacket);
        }
    }

    public int getPort() {
        return this.port;
    }

    int getSendBufferSize() throws SocketException {
        return this.ds.getSendBufferSize();
    }

    public void write(int i) throws IOException {
        this.baos.write(i);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.baos.write(bArr, i, i2);
    }
}
