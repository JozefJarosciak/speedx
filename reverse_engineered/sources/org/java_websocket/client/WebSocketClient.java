package org.java_websocket.client;

import ch.qos.logback.classic.spi.CallerData;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocket.READYSTATE;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.Framedata.Opcode;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeImpl1Client;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;

public abstract class WebSocketClient extends WebSocketAdapter implements Runnable, WebSocket {
    static final /* synthetic */ boolean $assertionsDisabled = (!WebSocketClient.class.desiredAssertionStatus());
    private CountDownLatch closeLatch;
    private CountDownLatch connectLatch;
    private int connectTimeout;
    private Draft draft;
    private WebSocketImpl engine;
    private Map<String, String> headers;
    private InputStream istream;
    private OutputStream ostream;
    private Proxy proxy;
    private Socket socket;
    protected URI uri;
    private Thread writeThread;

    private class WebsocketWriteThread implements Runnable {
        private WebsocketWriteThread() {
        }

        public void run() {
            Thread.currentThread().setName("WebsocketWriteThread");
            while (!Thread.interrupted()) {
                try {
                    ByteBuffer byteBuffer = (ByteBuffer) WebSocketClient.this.engine.outQueue.take();
                    WebSocketClient.this.ostream.write(byteBuffer.array(), 0, byteBuffer.limit());
                    WebSocketClient.this.ostream.flush();
                } catch (IOException e) {
                    WebSocketClient.this.engine.eot();
                    return;
                } catch (InterruptedException e2) {
                    return;
                }
            }
        }
    }

    public abstract void onClose(int i, String str, boolean z);

    public abstract void onError(Exception exception);

    public abstract void onMessage(String str);

    public abstract void onOpen(ServerHandshake serverHandshake);

    public WebSocketClient(URI uri) {
        this(uri, new Draft_17());
    }

    public WebSocketClient(URI uri, Draft draft) {
        this(uri, draft, null, 0);
    }

    public WebSocketClient(URI uri, Draft draft, Map<String, String> map, int i) {
        this.uri = null;
        this.engine = null;
        this.socket = null;
        this.proxy = Proxy.NO_PROXY;
        this.connectLatch = new CountDownLatch(1);
        this.closeLatch = new CountDownLatch(1);
        this.connectTimeout = 0;
        if (uri == null) {
            throw new IllegalArgumentException();
        } else if (draft == null) {
            throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
        } else {
            this.uri = uri;
            this.draft = draft;
            this.headers = map;
            this.connectTimeout = i;
            this.engine = new WebSocketImpl(this, draft);
        }
    }

    public URI getURI() {
        return this.uri;
    }

    public Draft getDraft() {
        return this.draft;
    }

    public void connect() {
        if (this.writeThread != null) {
            throw new IllegalStateException("WebSocketClient objects are not reuseable");
        }
        this.writeThread = new Thread(this);
        this.writeThread.start();
    }

    public boolean connectBlocking() throws InterruptedException {
        connect();
        this.connectLatch.await();
        return this.engine.isOpen();
    }

    public void close() {
        if (this.writeThread != null) {
            this.engine.close(1000);
        }
    }

    public void closeBlocking() throws InterruptedException {
        close();
        this.closeLatch.await();
    }

    public void send(String str) throws NotYetConnectedException {
        this.engine.send(str);
    }

    public void send(byte[] bArr) throws NotYetConnectedException {
        this.engine.send(bArr);
    }

    public void run() {
        try {
            if (this.socket == null) {
                this.socket = new Socket(this.proxy);
            } else if (this.socket.isClosed()) {
                throw new IOException();
            }
            if (!this.socket.isBound()) {
                this.socket.connect(new InetSocketAddress(this.uri.getHost(), getPort()), this.connectTimeout);
            }
            this.istream = this.socket.getInputStream();
            this.ostream = this.socket.getOutputStream();
            sendHandshake();
            this.writeThread = new Thread(new WebsocketWriteThread());
            this.writeThread.start();
            byte[] bArr = new byte[WebSocketImpl.RCVBUF];
            while (!isClosed()) {
                try {
                    int read = this.istream.read(bArr);
                    if (read != -1) {
                        this.engine.decode(ByteBuffer.wrap(bArr, 0, read));
                    }
                } catch (IOException e) {
                    this.engine.eot();
                } catch (Exception e2) {
                    onError(e2);
                    this.engine.closeConnection(1006, e2.getMessage());
                }
            }
            this.engine.eot();
            if (!$assertionsDisabled && !this.socket.isClosed()) {
                throw new AssertionError();
            }
        } catch (Exception e22) {
            onWebsocketError(this.engine, e22);
            this.engine.closeConnection(-1, e22.getMessage());
        }
    }

    private int getPort() {
        int port = this.uri.getPort();
        if (port != -1) {
            return port;
        }
        String scheme = this.uri.getScheme();
        if (scheme.equals("wss")) {
            return WebSocket.DEFAULT_WSS_PORT;
        }
        if (scheme.equals("ws")) {
            return 80;
        }
        throw new RuntimeException("unkonow scheme" + scheme);
    }

    private void sendHandshake() throws InvalidHandshakeException {
        String path = this.uri.getPath();
        String query = this.uri.getQuery();
        if (path == null || path.length() == 0) {
            path = "/";
        }
        if (query != null) {
            path = path + CallerData.NA + query;
        }
        int port = getPort();
        query = this.uri.getHost() + (port != 80 ? ":" + port : "");
        ClientHandshakeBuilder handshakeImpl1Client = new HandshakeImpl1Client();
        handshakeImpl1Client.setResourceDescriptor(path);
        handshakeImpl1Client.put("Host", query);
        if (this.headers != null) {
            for (Entry entry : this.headers.entrySet()) {
                handshakeImpl1Client.put((String) entry.getKey(), (String) entry.getValue());
            }
        }
        this.engine.startHandshake(handshakeImpl1Client);
    }

    public READYSTATE getReadyState() {
        return this.engine.getReadyState();
    }

    public final void onWebsocketMessage(WebSocket webSocket, String str) {
        onMessage(str);
    }

    public final void onWebsocketMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        onMessage(byteBuffer);
    }

    public void onWebsocketMessageFragment(WebSocket webSocket, Framedata framedata) {
        onFragment(framedata);
    }

    public final void onWebsocketOpen(WebSocket webSocket, Handshakedata handshakedata) {
        this.connectLatch.countDown();
        onOpen((ServerHandshake) handshakedata);
    }

    public final void onWebsocketClose(WebSocket webSocket, int i, String str, boolean z) {
        this.connectLatch.countDown();
        this.closeLatch.countDown();
        if (this.writeThread != null) {
            this.writeThread.interrupt();
        }
        try {
            if (this.socket != null) {
                this.socket.close();
            }
        } catch (Exception e) {
            onWebsocketError(this, e);
        }
        onClose(i, str, z);
    }

    public final void onWebsocketError(WebSocket webSocket, Exception exception) {
        onError(exception);
    }

    public final void onWriteDemand(WebSocket webSocket) {
    }

    public void onWebsocketCloseInitiated(WebSocket webSocket, int i, String str) {
        onCloseInitiated(i, str);
    }

    public void onWebsocketClosing(WebSocket webSocket, int i, String str, boolean z) {
        onClosing(i, str, z);
    }

    public void onCloseInitiated(int i, String str) {
    }

    public void onClosing(int i, String str, boolean z) {
    }

    public WebSocket getConnection() {
        return this.engine;
    }

    public InetSocketAddress getLocalSocketAddress(WebSocket webSocket) {
        if (this.socket != null) {
            return (InetSocketAddress) this.socket.getLocalSocketAddress();
        }
        return null;
    }

    public InetSocketAddress getRemoteSocketAddress(WebSocket webSocket) {
        if (this.socket != null) {
            return (InetSocketAddress) this.socket.getRemoteSocketAddress();
        }
        return null;
    }

    public void onMessage(ByteBuffer byteBuffer) {
    }

    public void onFragment(Framedata framedata) {
    }

    public void setProxy(Proxy proxy) {
        if (proxy == null) {
            throw new IllegalArgumentException();
        }
        this.proxy = proxy;
    }

    public void setSocket(Socket socket) {
        if (this.socket != null) {
            throw new IllegalStateException("socket has already been set");
        }
        this.socket = socket;
    }

    public void sendFragmentedFrame(Opcode opcode, ByteBuffer byteBuffer, boolean z) {
        this.engine.sendFragmentedFrame(opcode, byteBuffer, z);
    }

    public boolean isOpen() {
        return this.engine.isOpen();
    }

    public boolean isFlushAndClose() {
        return this.engine.isFlushAndClose();
    }

    public boolean isClosed() {
        return this.engine.isClosed();
    }

    public boolean isClosing() {
        return this.engine.isClosing();
    }

    public boolean isConnecting() {
        return this.engine.isConnecting();
    }

    public boolean hasBufferedData() {
        return this.engine.hasBufferedData();
    }

    public void close(int i) {
        this.engine.close();
    }

    public void close(int i, String str) {
        this.engine.close(i, str);
    }

    public void closeConnection(int i, String str) {
        this.engine.closeConnection(i, str);
    }

    public void send(ByteBuffer byteBuffer) throws IllegalArgumentException, NotYetConnectedException {
        this.engine.send(byteBuffer);
    }

    public void sendFrame(Framedata framedata) {
        this.engine.sendFrame(framedata);
    }

    public InetSocketAddress getLocalSocketAddress() {
        return this.engine.getLocalSocketAddress();
    }

    public InetSocketAddress getRemoteSocketAddress() {
        return this.engine.getRemoteSocketAddress();
    }

    public String getResourceDescriptor() {
        return this.uri.getPath();
    }
}
