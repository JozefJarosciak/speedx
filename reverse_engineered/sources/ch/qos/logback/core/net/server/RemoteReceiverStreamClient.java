package ch.qos.logback.core.net.server;

import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.util.CloseUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;

class RemoteReceiverStreamClient extends ContextAwareBase implements RemoteReceiverClient {
    private final String clientId;
    private final OutputStream outputStream;
    private BlockingQueue<Serializable> queue;
    private final Socket socket;

    RemoteReceiverStreamClient(String str, OutputStream outputStream) {
        this.clientId = "client " + str + ": ";
        this.socket = null;
        this.outputStream = outputStream;
    }

    public RemoteReceiverStreamClient(String str, Socket socket) {
        this.clientId = "client " + str + ": ";
        this.socket = socket;
        this.outputStream = null;
    }

    private ObjectOutputStream createObjectOutputStream() throws IOException {
        return this.socket == null ? new ObjectOutputStream(this.outputStream) : new ObjectOutputStream(this.socket.getOutputStream());
    }

    public void close() {
        if (this.socket != null) {
            CloseUtil.closeQuietly(this.socket);
        }
    }

    public boolean offer(Serializable serializable) {
        if (this.queue != null) {
            return this.queue.offer(serializable);
        }
        throw new IllegalStateException("client has no event queue");
    }

    public void run() {
        Closeable createObjectOutputStream;
        Object e;
        Closeable closeable;
        Throwable th;
        addInfo(this.clientId + "connected");
        try {
            createObjectOutputStream = createObjectOutputStream();
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    int i2;
                    try {
                        createObjectOutputStream.writeObject((Serializable) this.queue.take());
                        createObjectOutputStream.flush();
                        i2 = i + 1;
                        if (i2 >= 70) {
                            try {
                                createObjectOutputStream.reset();
                                i2 = 0;
                            } catch (InterruptedException e2) {
                                i2 = 0;
                                Thread.currentThread().interrupt();
                                i = i2;
                            }
                        }
                        i = i2;
                    } catch (InterruptedException e3) {
                        i2 = i;
                        Thread.currentThread().interrupt();
                        i = i2;
                    }
                } catch (SocketException e4) {
                    e = e4;
                    closeable = createObjectOutputStream;
                } catch (IOException e5) {
                    e = e5;
                } catch (RuntimeException e6) {
                    e = e6;
                }
            }
            if (createObjectOutputStream != null) {
                CloseUtil.closeQuietly(createObjectOutputStream);
            }
            close();
            addInfo(this.clientId + "connection closed");
        } catch (SocketException e7) {
            e = e7;
            closeable = null;
            try {
                addInfo(this.clientId + e);
                if (closeable != null) {
                    CloseUtil.closeQuietly(closeable);
                }
                close();
                addInfo(this.clientId + "connection closed");
            } catch (Throwable th2) {
                th = th2;
                createObjectOutputStream = closeable;
                if (createObjectOutputStream != null) {
                    CloseUtil.closeQuietly(createObjectOutputStream);
                }
                close();
                addInfo(this.clientId + "connection closed");
                throw th;
            }
        } catch (IOException e8) {
            e = e8;
            createObjectOutputStream = null;
            try {
                addError(this.clientId + e);
                if (createObjectOutputStream != null) {
                    CloseUtil.closeQuietly(createObjectOutputStream);
                }
                close();
                addInfo(this.clientId + "connection closed");
            } catch (Throwable th3) {
                th = th3;
                if (createObjectOutputStream != null) {
                    CloseUtil.closeQuietly(createObjectOutputStream);
                }
                close();
                addInfo(this.clientId + "connection closed");
                throw th;
            }
        } catch (RuntimeException e9) {
            e = e9;
            createObjectOutputStream = null;
            addError(this.clientId + e);
            if (createObjectOutputStream != null) {
                CloseUtil.closeQuietly(createObjectOutputStream);
            }
            close();
            addInfo(this.clientId + "connection closed");
        } catch (Throwable th4) {
            th = th4;
            createObjectOutputStream = null;
            if (createObjectOutputStream != null) {
                CloseUtil.closeQuietly(createObjectOutputStream);
            }
            close();
            addInfo(this.clientId + "connection closed");
            throw th;
        }
    }

    public void setQueue(BlockingQueue<Serializable> blockingQueue) {
        this.queue = blockingQueue;
    }
}
