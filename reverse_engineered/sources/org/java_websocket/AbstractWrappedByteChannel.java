package org.java_websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SocketChannel;
import javax.net.ssl.SSLException;

public class AbstractWrappedByteChannel implements WrappedByteChannel {
    private final ByteChannel channel;

    public AbstractWrappedByteChannel(ByteChannel byteChannel) {
        this.channel = byteChannel;
    }

    public AbstractWrappedByteChannel(WrappedByteChannel wrappedByteChannel) {
        this.channel = wrappedByteChannel;
    }

    public int read(ByteBuffer byteBuffer) throws IOException {
        return this.channel.read(byteBuffer);
    }

    public boolean isOpen() {
        return this.channel.isOpen();
    }

    public void close() throws IOException {
        this.channel.close();
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        return this.channel.write(byteBuffer);
    }

    public boolean isNeedWrite() {
        return this.channel instanceof WrappedByteChannel ? ((WrappedByteChannel) this.channel).isNeedWrite() : false;
    }

    public void writeMore() throws IOException {
        if (this.channel instanceof WrappedByteChannel) {
            ((WrappedByteChannel) this.channel).writeMore();
        }
    }

    public boolean isNeedRead() {
        return this.channel instanceof WrappedByteChannel ? ((WrappedByteChannel) this.channel).isNeedRead() : false;
    }

    public int readMore(ByteBuffer byteBuffer) throws SSLException {
        return this.channel instanceof WrappedByteChannel ? ((WrappedByteChannel) this.channel).readMore(byteBuffer) : 0;
    }

    public boolean isBlocking() {
        if (this.channel instanceof SocketChannel) {
            return ((SocketChannel) this.channel).isBlocking();
        }
        if (this.channel instanceof WrappedByteChannel) {
            return ((WrappedByteChannel) this.channel).isBlocking();
        }
        return false;
    }
}
