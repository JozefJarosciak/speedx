package org.java_websocket.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import org.apache.http.protocol.HTTP;
import org.java_websocket.AbstractWrappedByteChannel;

public abstract class AbstractClientProxyChannel extends AbstractWrappedByteChannel {
    protected final ByteBuffer proxyHandshake;

    public abstract String buildHandShake();

    public AbstractClientProxyChannel(ByteChannel byteChannel) {
        super(byteChannel);
        try {
            this.proxyHandshake = ByteBuffer.wrap(buildHandShake().getBytes(HTTP.ASCII));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        if (this.proxyHandshake.hasRemaining()) {
            return super.write(this.proxyHandshake);
        }
        return super.write(byteBuffer);
    }
}
