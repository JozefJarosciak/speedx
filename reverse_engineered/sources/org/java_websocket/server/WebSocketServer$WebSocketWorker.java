package org.java_websocket.server;

import java.lang.Thread.UncaughtExceptionHandler;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;

public class WebSocketServer$WebSocketWorker extends Thread {
    static final /* synthetic */ boolean $assertionsDisabled = (!WebSocketServer.class.desiredAssertionStatus());
    private BlockingQueue<WebSocketImpl> iqueue = new LinkedBlockingQueue();
    final /* synthetic */ WebSocketServer this$0;

    public WebSocketServer$WebSocketWorker(final WebSocketServer webSocketServer) {
        this.this$0 = webSocketServer;
        setName("WebSocketWorker-" + getId());
        setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, th);
            }
        });
    }

    public void put(WebSocketImpl webSocketImpl) throws InterruptedException {
        this.iqueue.put(webSocketImpl);
    }

    public void run() {
        WebSocket webSocket = null;
        while (true) {
            ByteBuffer byteBuffer;
            try {
                webSocket = (WebSocketImpl) this.iqueue.take();
                byteBuffer = (ByteBuffer) webSocket.inQueue.poll();
                if ($assertionsDisabled || byteBuffer != null) {
                    webSocket.decode(byteBuffer);
                    WebSocketServer.access$000(this.this$0, byteBuffer);
                } else {
                    throw new AssertionError();
                }
            } catch (InterruptedException e) {
                return;
            } catch (Exception e2) {
                WebSocketServer.access$100(this.this$0, webSocket, e2);
                return;
            } catch (Throwable th) {
                WebSocketServer.access$000(this.this$0, byteBuffer);
            }
        }
    }
}
