package org.java_websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import org.java_websocket.WebSocket.Role;

public class SocketChannelIOHelper {
    public static boolean read(ByteBuffer byteBuffer, WebSocketImpl webSocketImpl, ByteChannel byteChannel) throws IOException {
        byteBuffer.clear();
        int read = byteChannel.read(byteBuffer);
        byteBuffer.flip();
        if (read == -1) {
            webSocketImpl.eot();
            return false;
        } else if (read != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean readMore(ByteBuffer byteBuffer, WebSocketImpl webSocketImpl, WrappedByteChannel wrappedByteChannel) throws IOException {
        byteBuffer.clear();
        int readMore = wrappedByteChannel.readMore(byteBuffer);
        byteBuffer.flip();
        if (readMore != -1) {
            return wrappedByteChannel.isNeedRead();
        }
        webSocketImpl.eot();
        return false;
    }

    public static boolean batch(WebSocketImpl webSocketImpl, ByteChannel byteChannel) throws IOException {
        WrappedByteChannel wrappedByteChannel;
        ByteBuffer byteBuffer = (ByteBuffer) webSocketImpl.outQueue.peek();
        if (byteBuffer != null) {
            do {
                byteChannel.write(byteBuffer);
                if (byteBuffer.remaining() > 0) {
                    return false;
                }
                webSocketImpl.outQueue.poll();
                byteBuffer = (ByteBuffer) webSocketImpl.outQueue.peek();
            } while (byteBuffer != null);
            wrappedByteChannel = null;
        } else if (byteChannel instanceof WrappedByteChannel) {
            wrappedByteChannel = (WrappedByteChannel) byteChannel;
            if (wrappedByteChannel.isNeedWrite()) {
                wrappedByteChannel.writeMore();
            }
        } else {
            wrappedByteChannel = null;
        }
        if (webSocketImpl.outQueue.isEmpty() && webSocketImpl.isFlushAndClose() && webSocketImpl.getDraft().getRole() == Role.SERVER) {
            synchronized (webSocketImpl) {
                webSocketImpl.closeConnection();
            }
        }
        if (wrappedByteChannel == null) {
            return true;
        }
        if (((WrappedByteChannel) byteChannel).isNeedWrite()) {
            return false;
        }
        return true;
    }
}
