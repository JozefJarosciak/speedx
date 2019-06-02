package org.java_websocket;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.net.ssl.SSLEngineResult.Status;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

public class SSLSocketChannel2 implements ByteChannel, WrappedByteChannel {
    static final /* synthetic */ boolean $assertionsDisabled;
    protected static ByteBuffer emptybuffer = ByteBuffer.allocate(0);
    protected int bufferallocations = 0;
    protected ExecutorService exec;
    protected ByteBuffer inCrypt;
    protected ByteBuffer inData;
    protected ByteBuffer outCrypt;
    protected SSLEngineResult readEngineResult;
    protected SelectionKey selectionKey;
    protected SocketChannel socketChannel;
    protected SSLEngine sslEngine;
    protected List<Future<?>> tasks;
    protected SSLEngineResult writeEngineResult;

    static {
        boolean z;
        if (SSLSocketChannel2.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        $assertionsDisabled = z;
    }

    public SSLSocketChannel2(SocketChannel socketChannel, SSLEngine sSLEngine, ExecutorService executorService, SelectionKey selectionKey) throws IOException {
        if (socketChannel == null || sSLEngine == null || executorService == null) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.socketChannel = socketChannel;
        this.sslEngine = sSLEngine;
        this.exec = executorService;
        SSLEngineResult sSLEngineResult = new SSLEngineResult(Status.BUFFER_UNDERFLOW, sSLEngine.getHandshakeStatus(), 0, 0);
        this.writeEngineResult = sSLEngineResult;
        this.readEngineResult = sSLEngineResult;
        this.tasks = new ArrayList(3);
        if (selectionKey != null) {
            selectionKey.interestOps(selectionKey.interestOps() | 4);
            this.selectionKey = selectionKey;
        }
        createBuffers(sSLEngine.getSession());
        this.socketChannel.write(wrap(emptybuffer));
        processHandshake();
    }

    private void consumeFutureUninterruptible(Future<?> future) {
        Object obj = null;
        while (true) {
            try {
                future.get();
                break;
            } catch (InterruptedException e) {
                obj = 1;
            }
        }
        if (obj != null) {
            try {
                Thread.currentThread().interrupt();
            } catch (Throwable e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    private synchronized void processHandshake() throws IOException {
        if (this.sslEngine.getHandshakeStatus() != HandshakeStatus.NOT_HANDSHAKING) {
            if (!this.tasks.isEmpty()) {
                Iterator it = this.tasks.iterator();
                while (it.hasNext()) {
                    Future future = (Future) it.next();
                    if (future.isDone()) {
                        it.remove();
                    } else if (isBlocking()) {
                        consumeFutureUninterruptible(future);
                    }
                }
            }
            if (this.sslEngine.getHandshakeStatus() == HandshakeStatus.NEED_UNWRAP) {
                if (!isBlocking() || this.readEngineResult.getStatus() == Status.BUFFER_UNDERFLOW) {
                    this.inCrypt.compact();
                    if (this.socketChannel.read(this.inCrypt) == -1) {
                        throw new IOException("connection closed unexpectedly by peer");
                    }
                    this.inCrypt.flip();
                }
                this.inData.compact();
                unwrap();
                if (this.readEngineResult.getHandshakeStatus() == HandshakeStatus.FINISHED) {
                    createBuffers(this.sslEngine.getSession());
                }
            }
            consumeDelegatedTasks();
            if (this.tasks.isEmpty() || this.sslEngine.getHandshakeStatus() == HandshakeStatus.NEED_WRAP) {
                this.socketChannel.write(wrap(emptybuffer));
                if (this.writeEngineResult.getHandshakeStatus() == HandshakeStatus.FINISHED) {
                    createBuffers(this.sslEngine.getSession());
                }
            }
            if ($assertionsDisabled || this.sslEngine.getHandshakeStatus() != HandshakeStatus.NOT_HANDSHAKING) {
                this.bufferallocations = 1;
            } else {
                throw new AssertionError();
            }
        }
    }

    private synchronized ByteBuffer wrap(ByteBuffer byteBuffer) throws SSLException {
        this.outCrypt.compact();
        this.writeEngineResult = this.sslEngine.wrap(byteBuffer, this.outCrypt);
        this.outCrypt.flip();
        return this.outCrypt;
    }

    private synchronized ByteBuffer unwrap() throws SSLException {
        while (true) {
            int remaining = this.inData.remaining();
            this.readEngineResult = this.sslEngine.unwrap(this.inCrypt, this.inData);
            if (this.readEngineResult.getStatus() != Status.OK || (remaining == this.inData.remaining() && this.sslEngine.getHandshakeStatus() != HandshakeStatus.NEED_UNWRAP)) {
                this.inData.flip();
            }
        }
        this.inData.flip();
        return this.inData;
    }

    protected void consumeDelegatedTasks() {
        while (true) {
            Runnable delegatedTask = this.sslEngine.getDelegatedTask();
            if (delegatedTask != null) {
                this.tasks.add(this.exec.submit(delegatedTask));
            } else {
                return;
            }
        }
    }

    protected void createBuffers(SSLSession sSLSession) {
        int applicationBufferSize = sSLSession.getApplicationBufferSize();
        int packetBufferSize = sSLSession.getPacketBufferSize();
        if (this.inData == null) {
            this.inData = ByteBuffer.allocate(applicationBufferSize);
            this.outCrypt = ByteBuffer.allocate(packetBufferSize);
            this.inCrypt = ByteBuffer.allocate(packetBufferSize);
        } else {
            if (this.inData.capacity() != applicationBufferSize) {
                this.inData = ByteBuffer.allocate(applicationBufferSize);
            }
            if (this.outCrypt.capacity() != packetBufferSize) {
                this.outCrypt = ByteBuffer.allocate(packetBufferSize);
            }
            if (this.inCrypt.capacity() != packetBufferSize) {
                this.inCrypt = ByteBuffer.allocate(packetBufferSize);
            }
        }
        this.inData.rewind();
        this.inData.flip();
        this.inCrypt.rewind();
        this.inCrypt.flip();
        this.outCrypt.rewind();
        this.outCrypt.flip();
        this.bufferallocations++;
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        if (isHandShakeComplete()) {
            if (this.bufferallocations <= 1) {
                createBuffers(this.sslEngine.getSession());
            }
            return this.socketChannel.write(wrap(byteBuffer));
        }
        processHandshake();
        return 0;
    }

    public int read(ByteBuffer byteBuffer) throws IOException {
        if (!byteBuffer.hasRemaining()) {
            return 0;
        }
        if (!isHandShakeComplete()) {
            if (isBlocking()) {
                while (!isHandShakeComplete()) {
                    processHandshake();
                }
            } else {
                processHandshake();
                if (!isHandShakeComplete()) {
                    return 0;
                }
            }
        }
        if (this.bufferallocations <= 1) {
            createBuffers(this.sslEngine.getSession());
        }
        int readRemaining = readRemaining(byteBuffer);
        if (readRemaining != 0) {
            return readRemaining;
        }
        if ($assertionsDisabled || this.inData.position() == 0) {
            this.inData.clear();
            if (this.inCrypt.hasRemaining()) {
                this.inCrypt.compact();
            } else {
                this.inCrypt.clear();
            }
            if ((isBlocking() || this.readEngineResult.getStatus() == Status.BUFFER_UNDERFLOW) && this.socketChannel.read(this.inCrypt) == -1) {
                return -1;
            }
            this.inCrypt.flip();
            unwrap();
            readRemaining = transfereTo(this.inData, byteBuffer);
            if (readRemaining == 0 && isBlocking()) {
                return read(byteBuffer);
            }
            return readRemaining;
        }
        throw new AssertionError();
    }

    private int readRemaining(ByteBuffer byteBuffer) throws SSLException {
        if (this.inData.hasRemaining()) {
            return transfereTo(this.inData, byteBuffer);
        }
        if (!this.inData.hasRemaining()) {
            this.inData.clear();
        }
        if (this.inCrypt.hasRemaining()) {
            unwrap();
            int transfereTo = transfereTo(this.inData, byteBuffer);
            if (transfereTo > 0) {
                return transfereTo;
            }
        }
        return 0;
    }

    public boolean isConnected() {
        return this.socketChannel.isConnected();
    }

    public void close() throws IOException {
        this.sslEngine.closeOutbound();
        this.sslEngine.getSession().invalidate();
        if (this.socketChannel.isOpen()) {
            this.socketChannel.write(wrap(emptybuffer));
        }
        this.socketChannel.close();
        this.exec.shutdownNow();
    }

    private boolean isHandShakeComplete() {
        HandshakeStatus handshakeStatus = this.sslEngine.getHandshakeStatus();
        return handshakeStatus == HandshakeStatus.FINISHED || handshakeStatus == HandshakeStatus.NOT_HANDSHAKING;
    }

    public SelectableChannel configureBlocking(boolean z) throws IOException {
        return this.socketChannel.configureBlocking(z);
    }

    public boolean connect(SocketAddress socketAddress) throws IOException {
        return this.socketChannel.connect(socketAddress);
    }

    public boolean finishConnect() throws IOException {
        return this.socketChannel.finishConnect();
    }

    public Socket socket() {
        return this.socketChannel.socket();
    }

    public boolean isInboundDone() {
        return this.sslEngine.isInboundDone();
    }

    public boolean isOpen() {
        return this.socketChannel.isOpen();
    }

    public boolean isNeedWrite() {
        return this.outCrypt.hasRemaining() || !isHandShakeComplete();
    }

    public void writeMore() throws IOException {
        write(this.outCrypt);
    }

    public boolean isNeedRead() {
        return this.inData.hasRemaining() || !(!this.inCrypt.hasRemaining() || this.readEngineResult.getStatus() == Status.BUFFER_UNDERFLOW || this.readEngineResult.getStatus() == Status.CLOSED);
    }

    public int readMore(ByteBuffer byteBuffer) throws SSLException {
        return readRemaining(byteBuffer);
    }

    private int transfereTo(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int remaining = byteBuffer.remaining();
        int remaining2 = byteBuffer2.remaining();
        if (remaining > remaining2) {
            remaining = Math.min(remaining, remaining2);
            for (remaining2 = 0; remaining2 < remaining; remaining2++) {
                byteBuffer2.put(byteBuffer.get());
            }
        } else {
            byteBuffer2.put(byteBuffer);
        }
        return remaining;
    }

    public boolean isBlocking() {
        return this.socketChannel.isBlocking();
    }
}
