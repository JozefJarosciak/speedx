package org.java_websocket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketFactory;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshakeBuilder;

public abstract class WebSocketServer extends WebSocketAdapter implements Runnable {
    static final /* synthetic */ boolean $assertionsDisabled = (!WebSocketServer.class.desiredAssertionStatus());
    public static int DECODERS = Runtime.getRuntime().availableProcessors();
    private final InetSocketAddress address;
    private BlockingQueue<ByteBuffer> buffers;
    private final Collection<WebSocket> connections;
    private List<WebSocketServer$WebSocketWorker> decoders;
    private List<Draft> drafts;
    private List<WebSocketImpl> iqueue;
    private volatile AtomicBoolean isclosed;
    private int queueinvokes;
    private AtomicInteger queuesize;
    private Selector selector;
    private Thread selectorthread;
    private ServerSocketChannel server;
    private WebSocketServer$WebSocketServerFactory wsf;

    public abstract void onClose(WebSocket webSocket, int i, String str, boolean z);

    public abstract void onError(WebSocket webSocket, Exception exception);

    public abstract void onMessage(WebSocket webSocket, String str);

    public abstract void onOpen(WebSocket webSocket, ClientHandshake clientHandshake);

    public WebSocketServer() throws UnknownHostException {
        this(new InetSocketAddress(80), DECODERS, null);
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress) {
        this(inetSocketAddress, DECODERS, null);
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, int i) {
        this(inetSocketAddress, i, null);
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, List<Draft> list) {
        this(inetSocketAddress, DECODERS, list);
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, int i, List<Draft> list) {
        this(inetSocketAddress, i, list, new HashSet());
    }

    public WebSocketServer(InetSocketAddress inetSocketAddress, int i, List<Draft> list, Collection<WebSocket> collection) {
        int i2 = 0;
        this.isclosed = new AtomicBoolean(false);
        this.queueinvokes = 0;
        this.queuesize = new AtomicInteger(0);
        this.wsf = new DefaultWebSocketServerFactory();
        if (inetSocketAddress == null || i < 1 || collection == null) {
            throw new IllegalArgumentException("address and connectionscontainer must not be null and you need at least 1 decoder");
        }
        if (list == null) {
            this.drafts = Collections.emptyList();
        } else {
            this.drafts = list;
        }
        this.address = inetSocketAddress;
        this.connections = collection;
        this.iqueue = new LinkedList();
        this.decoders = new ArrayList(i);
        this.buffers = new LinkedBlockingQueue();
        while (i2 < i) {
            WebSocketServer$WebSocketWorker webSocketServer$WebSocketWorker = new WebSocketServer$WebSocketWorker(this);
            this.decoders.add(webSocketServer$WebSocketWorker);
            webSocketServer$WebSocketWorker.start();
            i2++;
        }
    }

    public void start() {
        if (this.selectorthread != null) {
            throw new IllegalStateException(getClass().getName() + " can only be started once.");
        }
        new Thread(this).start();
    }

    public void stop(int i) throws InterruptedException {
        if (this.isclosed.compareAndSet(false, true)) {
            List<WebSocket> arrayList;
            synchronized (this.connections) {
                arrayList = new ArrayList(this.connections);
            }
            for (WebSocket close : arrayList) {
                close.close(1001);
            }
            synchronized (this) {
                if (this.selectorthread != null) {
                    if (Thread.currentThread() != this.selectorthread) {
                    }
                    if (this.selectorthread != Thread.currentThread()) {
                        if (arrayList.size() > 0) {
                            this.selectorthread.join((long) i);
                        }
                        this.selectorthread.interrupt();
                        this.selectorthread.join();
                    }
                }
            }
        }
    }

    public void stop() throws IOException, InterruptedException {
        stop(0);
    }

    public Collection<WebSocket> connections() {
        return this.connections;
    }

    public InetSocketAddress getAddress() {
        return this.address;
    }

    public int getPort() {
        int port = getAddress().getPort();
        if (port != 0 || this.server == null) {
            return port;
        }
        return this.server.socket().getLocalPort();
    }

    public List<Draft> getDraft() {
        return Collections.unmodifiableList(this.drafts);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r11 = this;
        r5 = 0;
        monitor-enter(r11);
        r2 = r11.selectorthread;	 Catch:{ all -> 0x0027 }
        if (r2 == 0) goto L_0x002a;
    L_0x0006:
        r2 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0027 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0027 }
        r3.<init>();	 Catch:{ all -> 0x0027 }
        r4 = r11.getClass();	 Catch:{ all -> 0x0027 }
        r4 = r4.getName();	 Catch:{ all -> 0x0027 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0027 }
        r4 = " can only be started once.";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0027 }
        r3 = r3.toString();	 Catch:{ all -> 0x0027 }
        r2.<init>(r3);	 Catch:{ all -> 0x0027 }
        throw r2;	 Catch:{ all -> 0x0027 }
    L_0x0027:
        r2 = move-exception;
        monitor-exit(r11);	 Catch:{ all -> 0x0027 }
        throw r2;
    L_0x002a:
        r2 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0027 }
        r11.selectorthread = r2;	 Catch:{ all -> 0x0027 }
        r2 = r11.isclosed;	 Catch:{ all -> 0x0027 }
        r2 = r2.get();	 Catch:{ all -> 0x0027 }
        if (r2 == 0) goto L_0x003a;
    L_0x0038:
        monitor-exit(r11);	 Catch:{ all -> 0x0027 }
    L_0x0039:
        return;
    L_0x003a:
        monitor-exit(r11);	 Catch:{ all -> 0x0027 }
        r2 = r11.selectorthread;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "WebsocketSelector";
        r3 = r3.append(r4);
        r4 = r11.selectorthread;
        r6 = r4.getId();
        r3 = r3.append(r6);
        r3 = r3.toString();
        r2.setName(r3);
        r2 = java.nio.channels.ServerSocketChannel.open();	 Catch:{ IOException -> 0x00b7 }
        r11.server = r2;	 Catch:{ IOException -> 0x00b7 }
        r2 = r11.server;	 Catch:{ IOException -> 0x00b7 }
        r3 = 0;
        r2.configureBlocking(r3);	 Catch:{ IOException -> 0x00b7 }
        r2 = r11.server;	 Catch:{ IOException -> 0x00b7 }
        r2 = r2.socket();	 Catch:{ IOException -> 0x00b7 }
        r3 = org.java_websocket.WebSocketImpl.RCVBUF;	 Catch:{ IOException -> 0x00b7 }
        r2.setReceiveBufferSize(r3);	 Catch:{ IOException -> 0x00b7 }
        r3 = r11.address;	 Catch:{ IOException -> 0x00b7 }
        r2.bind(r3);	 Catch:{ IOException -> 0x00b7 }
        r2 = java.nio.channels.Selector.open();	 Catch:{ IOException -> 0x00b7 }
        r11.selector = r2;	 Catch:{ IOException -> 0x00b7 }
        r2 = r11.server;	 Catch:{ IOException -> 0x00b7 }
        r3 = r11.selector;	 Catch:{ IOException -> 0x00b7 }
        r4 = r11.server;	 Catch:{ IOException -> 0x00b7 }
        r4 = r4.validOps();	 Catch:{ IOException -> 0x00b7 }
        r2.register(r3, r4);	 Catch:{ IOException -> 0x00b7 }
    L_0x0088:
        r2 = r11.selectorthread;	 Catch:{ RuntimeException -> 0x01d7 }
        r2 = r2.isInterrupted();	 Catch:{ RuntimeException -> 0x01d7 }
        if (r2 != 0) goto L_0x0233;
    L_0x0090:
        r2 = r11.selector;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029a, InterruptedException -> 0x0207 }
        r2.select();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029a, InterruptedException -> 0x0207 }
        r2 = r11.selector;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029a, InterruptedException -> 0x0207 }
        r2 = r2.selectedKeys();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029a, InterruptedException -> 0x0207 }
        r7 = r2.iterator();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029a, InterruptedException -> 0x0207 }
        r4 = r5;
        r6 = r5;
    L_0x00a1:
        r2 = r7.hasNext();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x02a2, InterruptedException -> 0x0207 }
        if (r2 == 0) goto L_0x018c;
    L_0x00a7:
        r2 = r7.next();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x02a2, InterruptedException -> 0x0207 }
        r0 = r2;
        r0 = (java.nio.channels.SelectionKey) r0;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x02a2, InterruptedException -> 0x0207 }
        r3 = r0;
        r2 = r3.isValid();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        if (r2 != 0) goto L_0x00bd;
    L_0x00b5:
        r6 = r3;
        goto L_0x00a1;
    L_0x00b7:
        r2 = move-exception;
        r11.handleFatal(r5, r2);
        goto L_0x0039;
    L_0x00bd:
        r2 = r3.isAcceptable();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        if (r2 == 0) goto L_0x00ff;
    L_0x00c3:
        r2 = r11.onConnect(r3);	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        if (r2 != 0) goto L_0x00ce;
    L_0x00c9:
        r3.cancel();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r6 = r3;
        goto L_0x00a1;
    L_0x00ce:
        r2 = r11.server;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r2 = r2.accept();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r6 = 0;
        r2.configureBlocking(r6);	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r6 = r11.wsf;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r8 = r11.drafts;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r9 = r2.socket();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r6 = r6.createWebSocket(r11, r8, r9);	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r8 = r11.selector;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r9 = 1;
        r8 = r2.register(r8, r9, r6);	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r6.key = r8;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r8 = r11.wsf;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r9 = r6.key;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r2 = r8.wrapChannel(r2, r9);	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r6.channel = r2;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r7.remove();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r11.allocateBuffers(r6);	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r6 = r3;
        goto L_0x00a1;
    L_0x00ff:
        r2 = r3.isReadable();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        if (r2 == 0) goto L_0x013f;
    L_0x0105:
        r2 = r3.attachment();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r0 = r2;
        r0 = (org.java_websocket.WebSocketImpl) r0;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r4 = r0;
        r6 = r11.takeBuffer();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r2 = r4.channel;	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        r2 = org.java_websocket.SocketChannelIOHelper.read(r6, r4, r2);	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        if (r2 == 0) goto L_0x016d;
    L_0x0119:
        r2 = r6.hasRemaining();	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        if (r2 == 0) goto L_0x0161;
    L_0x011f:
        r2 = r4.inQueue;	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        r2.put(r6);	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        r11.queue(r4);	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        r7.remove();	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        r2 = r4.channel;	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        r2 = r2 instanceof org.java_websocket.WrappedByteChannel;	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        if (r2 == 0) goto L_0x013f;
    L_0x0130:
        r2 = r4.channel;	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        r2 = (org.java_websocket.WrappedByteChannel) r2;	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        r2 = r2.isNeedRead();	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        if (r2 == 0) goto L_0x013f;
    L_0x013a:
        r2 = r11.iqueue;	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        r2.add(r4);	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
    L_0x013f:
        r2 = r3.isWritable();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        if (r2 == 0) goto L_0x02b0;
    L_0x0145:
        r2 = r3.attachment();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r2 = (org.java_websocket.WebSocketImpl) r2;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        r4 = r2.channel;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x02a6, InterruptedException -> 0x0207 }
        r4 = org.java_websocket.SocketChannelIOHelper.batch(r2, r4);	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x02a6, InterruptedException -> 0x0207 }
        if (r4 == 0) goto L_0x02ac;
    L_0x0153:
        r4 = r3.isValid();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x02a6, InterruptedException -> 0x0207 }
        if (r4 == 0) goto L_0x02ac;
    L_0x0159:
        r4 = 1;
        r3.interestOps(r4);	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x02a6, InterruptedException -> 0x0207 }
        r4 = r2;
        r6 = r3;
        goto L_0x00a1;
    L_0x0161:
        r11.pushBuffer(r6);	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        goto L_0x013f;
    L_0x0165:
        r2 = move-exception;
        r11.pushBuffer(r6);	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
        throw r2;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x029f, InterruptedException -> 0x0207 }
    L_0x016a:
        r2 = move-exception;
        goto L_0x0088;
    L_0x016d:
        r11.pushBuffer(r6);	 Catch:{ IOException -> 0x0165, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        goto L_0x013f;
    L_0x0171:
        r2 = move-exception;
        r2 = r11.decoders;
        if (r2 == 0) goto L_0x01f6;
    L_0x0176:
        r2 = r11.decoders;
        r3 = r2.iterator();
    L_0x017c:
        r2 = r3.hasNext();
        if (r2 == 0) goto L_0x01f6;
    L_0x0182:
        r2 = r3.next();
        r2 = (org.java_websocket.server.WebSocketServer$WebSocketWorker) r2;
        r2.interrupt();
        goto L_0x017c;
    L_0x018c:
        r2 = r11.iqueue;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x02a2, InterruptedException -> 0x0207 }
        r2 = r2.isEmpty();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x02a2, InterruptedException -> 0x0207 }
        if (r2 != 0) goto L_0x0088;
    L_0x0194:
        r2 = r11.iqueue;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x02a2, InterruptedException -> 0x0207 }
        r3 = 0;
        r2 = r2.remove(r3);	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x02a2, InterruptedException -> 0x0207 }
        r2 = (org.java_websocket.WebSocketImpl) r2;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x02a2, InterruptedException -> 0x0207 }
        r3 = r2.channel;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x01c9, InterruptedException -> 0x0207 }
        r3 = (org.java_websocket.WrappedByteChannel) r3;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x01c9, InterruptedException -> 0x0207 }
        r4 = r11.takeBuffer();	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x01c9, InterruptedException -> 0x0207 }
        r3 = org.java_websocket.SocketChannelIOHelper.readMore(r4, r2, r3);	 Catch:{ IOException -> 0x01c4, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        if (r3 == 0) goto L_0x01b0;
    L_0x01ab:
        r3 = r11.iqueue;	 Catch:{ IOException -> 0x01c4, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        r3.add(r2);	 Catch:{ IOException -> 0x01c4, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
    L_0x01b0:
        r3 = r4.hasRemaining();	 Catch:{ IOException -> 0x01c4, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        if (r3 == 0) goto L_0x01c0;
    L_0x01b6:
        r3 = r2.inQueue;	 Catch:{ IOException -> 0x01c4, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        r3.put(r4);	 Catch:{ IOException -> 0x01c4, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        r11.queue(r2);	 Catch:{ IOException -> 0x01c4, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
    L_0x01be:
        r4 = r2;
        goto L_0x018c;
    L_0x01c0:
        r11.pushBuffer(r4);	 Catch:{ IOException -> 0x01c4, CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, InterruptedException -> 0x0207 }
        goto L_0x01be;
    L_0x01c4:
        r3 = move-exception;
        r11.pushBuffer(r4);	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x01c9, InterruptedException -> 0x0207 }
        throw r3;	 Catch:{ CancelledKeyException -> 0x016a, ClosedByInterruptException -> 0x0171, IOException -> 0x01c9, InterruptedException -> 0x0207 }
    L_0x01c9:
        r3 = move-exception;
        r4 = r2;
        r2 = r3;
        r3 = r6;
    L_0x01cd:
        if (r3 == 0) goto L_0x01d2;
    L_0x01cf:
        r3.cancel();	 Catch:{ RuntimeException -> 0x01d7 }
    L_0x01d2:
        r11.handleIOException(r3, r4, r2);	 Catch:{ RuntimeException -> 0x01d7 }
        goto L_0x0088;
    L_0x01d7:
        r2 = move-exception;
        r3 = 0;
        r11.handleFatal(r3, r2);	 Catch:{ all -> 0x026f }
        r2 = r11.decoders;
        if (r2 == 0) goto L_0x025e;
    L_0x01e0:
        r2 = r11.decoders;
        r3 = r2.iterator();
    L_0x01e6:
        r2 = r3.hasNext();
        if (r2 == 0) goto L_0x025e;
    L_0x01ec:
        r2 = r3.next();
        r2 = (org.java_websocket.server.WebSocketServer$WebSocketWorker) r2;
        r2.interrupt();
        goto L_0x01e6;
    L_0x01f6:
        r2 = r11.server;
        if (r2 == 0) goto L_0x0039;
    L_0x01fa:
        r2 = r11.server;	 Catch:{ IOException -> 0x0201 }
        r2.close();	 Catch:{ IOException -> 0x0201 }
        goto L_0x0039;
    L_0x0201:
        r2 = move-exception;
        r11.onError(r5, r2);
        goto L_0x0039;
    L_0x0207:
        r2 = move-exception;
        r2 = r11.decoders;
        if (r2 == 0) goto L_0x0222;
    L_0x020c:
        r2 = r11.decoders;
        r3 = r2.iterator();
    L_0x0212:
        r2 = r3.hasNext();
        if (r2 == 0) goto L_0x0222;
    L_0x0218:
        r2 = r3.next();
        r2 = (org.java_websocket.server.WebSocketServer$WebSocketWorker) r2;
        r2.interrupt();
        goto L_0x0212;
    L_0x0222:
        r2 = r11.server;
        if (r2 == 0) goto L_0x0039;
    L_0x0226:
        r2 = r11.server;	 Catch:{ IOException -> 0x022d }
        r2.close();	 Catch:{ IOException -> 0x022d }
        goto L_0x0039;
    L_0x022d:
        r2 = move-exception;
        r11.onError(r5, r2);
        goto L_0x0039;
    L_0x0233:
        r2 = r11.decoders;
        if (r2 == 0) goto L_0x024d;
    L_0x0237:
        r2 = r11.decoders;
        r3 = r2.iterator();
    L_0x023d:
        r2 = r3.hasNext();
        if (r2 == 0) goto L_0x024d;
    L_0x0243:
        r2 = r3.next();
        r2 = (org.java_websocket.server.WebSocketServer$WebSocketWorker) r2;
        r2.interrupt();
        goto L_0x023d;
    L_0x024d:
        r2 = r11.server;
        if (r2 == 0) goto L_0x0039;
    L_0x0251:
        r2 = r11.server;	 Catch:{ IOException -> 0x0258 }
        r2.close();	 Catch:{ IOException -> 0x0258 }
        goto L_0x0039;
    L_0x0258:
        r2 = move-exception;
        r11.onError(r5, r2);
        goto L_0x0039;
    L_0x025e:
        r2 = r11.server;
        if (r2 == 0) goto L_0x0039;
    L_0x0262:
        r2 = r11.server;	 Catch:{ IOException -> 0x0269 }
        r2.close();	 Catch:{ IOException -> 0x0269 }
        goto L_0x0039;
    L_0x0269:
        r2 = move-exception;
        r11.onError(r5, r2);
        goto L_0x0039;
    L_0x026f:
        r2 = move-exception;
        r3 = r2;
        r2 = r11.decoders;
        if (r2 == 0) goto L_0x028b;
    L_0x0275:
        r2 = r11.decoders;
        r4 = r2.iterator();
    L_0x027b:
        r2 = r4.hasNext();
        if (r2 == 0) goto L_0x028b;
    L_0x0281:
        r2 = r4.next();
        r2 = (org.java_websocket.server.WebSocketServer$WebSocketWorker) r2;
        r2.interrupt();
        goto L_0x027b;
    L_0x028b:
        r2 = r11.server;
        if (r2 == 0) goto L_0x0294;
    L_0x028f:
        r2 = r11.server;	 Catch:{ IOException -> 0x0295 }
        r2.close();	 Catch:{ IOException -> 0x0295 }
    L_0x0294:
        throw r3;
    L_0x0295:
        r2 = move-exception;
        r11.onError(r5, r2);
        goto L_0x0294;
    L_0x029a:
        r2 = move-exception;
        r4 = r5;
        r3 = r5;
        goto L_0x01cd;
    L_0x029f:
        r2 = move-exception;
        goto L_0x01cd;
    L_0x02a2:
        r2 = move-exception;
        r3 = r6;
        goto L_0x01cd;
    L_0x02a6:
        r4 = move-exception;
        r10 = r4;
        r4 = r2;
        r2 = r10;
        goto L_0x01cd;
    L_0x02ac:
        r4 = r2;
        r6 = r3;
        goto L_0x00a1;
    L_0x02b0:
        r6 = r3;
        goto L_0x00a1;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.server.WebSocketServer.run():void");
    }

    protected void allocateBuffers(WebSocket webSocket) throws InterruptedException {
        if (this.queuesize.get() < (this.decoders.size() * 2) + 1) {
            this.queuesize.incrementAndGet();
            this.buffers.put(createBuffer());
        }
    }

    protected void releaseBuffers(WebSocket webSocket) throws InterruptedException {
    }

    public ByteBuffer createBuffer() {
        return ByteBuffer.allocate(WebSocketImpl.RCVBUF);
    }

    private void queue(WebSocketImpl webSocketImpl) throws InterruptedException {
        if (webSocketImpl.workerThread == null) {
            webSocketImpl.workerThread = (WebSocketServer$WebSocketWorker) this.decoders.get(this.queueinvokes % this.decoders.size());
            this.queueinvokes++;
        }
        webSocketImpl.workerThread.put(webSocketImpl);
    }

    private ByteBuffer takeBuffer() throws InterruptedException {
        return (ByteBuffer) this.buffers.take();
    }

    private void pushBuffer(ByteBuffer byteBuffer) throws InterruptedException {
        if (this.buffers.size() <= this.queuesize.intValue()) {
            this.buffers.put(byteBuffer);
        }
    }

    private void handleIOException(SelectionKey selectionKey, WebSocket webSocket, IOException iOException) {
        if (webSocket != null) {
            webSocket.closeConnection(1006, iOException.getMessage());
        } else if (selectionKey != null) {
            SelectableChannel channel = selectionKey.channel();
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException e) {
                }
                if (WebSocketImpl.DEBUG) {
                    System.out.println("Connection closed because of" + iOException);
                }
            }
        }
    }

    private void handleFatal(WebSocket webSocket, Exception exception) {
        onError(webSocket, exception);
        try {
            stop();
        } catch (Exception e) {
            onError(null, e);
        } catch (Exception e2) {
            Thread.currentThread().interrupt();
            onError(null, e2);
        }
    }

    protected String getFlashSecurityPolicy() {
        return "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"" + getPort() + "\" /></cross-domain-policy>";
    }

    public final void onWebsocketMessage(WebSocket webSocket, String str) {
        onMessage(webSocket, str);
    }

    @Deprecated
    public void onWebsocketMessageFragment(WebSocket webSocket, Framedata framedata) {
        onFragment(webSocket, framedata);
    }

    public final void onWebsocketMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        onMessage(webSocket, byteBuffer);
    }

    public final void onWebsocketOpen(WebSocket webSocket, Handshakedata handshakedata) {
        if (addConnection(webSocket)) {
            onOpen(webSocket, (ClientHandshake) handshakedata);
        }
    }

    public final void onWebsocketClose(WebSocket webSocket, int i, String str, boolean z) {
        this.selector.wakeup();
        try {
            if (removeConnection(webSocket)) {
                onClose(webSocket, i, str, z);
            }
        } finally {
            try {
                releaseBuffers(webSocket);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    protected boolean removeConnection(WebSocket webSocket) {
        boolean remove;
        synchronized (this.connections) {
            remove = this.connections.remove(webSocket);
            if ($assertionsDisabled || remove) {
            } else {
                throw new AssertionError();
            }
        }
        if (this.isclosed.get() && this.connections.size() == 0) {
            this.selectorthread.interrupt();
        }
        return remove;
    }

    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket webSocket, Draft draft, ClientHandshake clientHandshake) throws InvalidDataException {
        return super.onWebsocketHandshakeReceivedAsServer(webSocket, draft, clientHandshake);
    }

    protected boolean addConnection(WebSocket webSocket) {
        if (this.isclosed.get()) {
            webSocket.close(1001);
            return true;
        }
        boolean add;
        synchronized (this.connections) {
            add = this.connections.add(webSocket);
            if ($assertionsDisabled || add) {
            } else {
                throw new AssertionError();
            }
        }
        return add;
    }

    public final void onWebsocketError(WebSocket webSocket, Exception exception) {
        onError(webSocket, exception);
    }

    public final void onWriteDemand(WebSocket webSocket) {
        WebSocketImpl webSocketImpl = (WebSocketImpl) webSocket;
        try {
            webSocketImpl.key.interestOps(5);
        } catch (CancelledKeyException e) {
            webSocketImpl.outQueue.clear();
        }
        this.selector.wakeup();
    }

    public void onWebsocketCloseInitiated(WebSocket webSocket, int i, String str) {
        onCloseInitiated(webSocket, i, str);
    }

    public void onWebsocketClosing(WebSocket webSocket, int i, String str, boolean z) {
        onClosing(webSocket, i, str, z);
    }

    public void onCloseInitiated(WebSocket webSocket, int i, String str) {
    }

    public void onClosing(WebSocket webSocket, int i, String str, boolean z) {
    }

    public final void setWebSocketFactory(WebSocketServer$WebSocketServerFactory webSocketServer$WebSocketServerFactory) {
        this.wsf = webSocketServer$WebSocketServerFactory;
    }

    public final WebSocketFactory getWebSocketFactory() {
        return this.wsf;
    }

    protected boolean onConnect(SelectionKey selectionKey) {
        return true;
    }

    private Socket getSocket(WebSocket webSocket) {
        return ((SocketChannel) ((WebSocketImpl) webSocket).key.channel()).socket();
    }

    public InetSocketAddress getLocalSocketAddress(WebSocket webSocket) {
        return (InetSocketAddress) getSocket(webSocket).getLocalSocketAddress();
    }

    public InetSocketAddress getRemoteSocketAddress(WebSocket webSocket) {
        return (InetSocketAddress) getSocket(webSocket).getRemoteSocketAddress();
    }

    public void onMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
    }

    public void onFragment(WebSocket webSocket, Framedata framedata) {
    }
}
