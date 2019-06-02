package ch.qos.logback.core.net.server;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.net.AbstractSocketAppender;
import ch.qos.logback.core.spi.PreSerializationTransformer;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import javax.net.ServerSocketFactory;

public abstract class ServerSocketAppenderBase<E> extends AppenderBase<E> {
    public static final int DEFAULT_BACKLOG = 50;
    public static final int DEFAULT_CLIENT_QUEUE_SIZE = 100;
    private String address;
    private int backlog = 50;
    private int clientQueueSize = 100;
    private int port = AbstractSocketAppender.DEFAULT_PORT;
    private ServerRunner<RemoteReceiverClient> runner;

    protected void append(E e) {
        if (e != null) {
            postProcessEvent(e);
            final Serializable transform = getPST().transform(e);
            this.runner.accept(new ClientVisitor<RemoteReceiverClient>() {
                public void visit(RemoteReceiverClient remoteReceiverClient) {
                    remoteReceiverClient.offer(transform);
                }
            });
        }
    }

    protected ServerListener<RemoteReceiverClient> createServerListener(ServerSocket serverSocket) {
        return new RemoteReceiverServerListener(serverSocket);
    }

    protected ServerRunner<RemoteReceiverClient> createServerRunner(ServerListener<RemoteReceiverClient> serverListener, Executor executor) {
        return new RemoteReceiverServerRunner(serverListener, executor, getClientQueueSize());
    }

    public String getAddress() {
        return this.address;
    }

    public Integer getBacklog() {
        return Integer.valueOf(this.backlog);
    }

    public int getClientQueueSize() {
        return this.clientQueueSize;
    }

    protected InetAddress getInetAddress() throws UnknownHostException {
        return getAddress() == null ? null : InetAddress.getByName(getAddress());
    }

    protected abstract PreSerializationTransformer<E> getPST();

    public int getPort() {
        return this.port;
    }

    protected ServerSocketFactory getServerSocketFactory() throws Exception {
        return ServerSocketFactory.getDefault();
    }

    protected abstract void postProcessEvent(E e);

    public void setAddress(String str) {
        this.address = str;
    }

    public void setBacklog(Integer num) {
        this.backlog = num.intValue();
    }

    public void setClientQueueSize(int i) {
        this.clientQueueSize = i;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public void start() {
        if (!isStarted()) {
            try {
                this.runner = createServerRunner(createServerListener(getServerSocketFactory().createServerSocket(getPort(), getBacklog().intValue(), getInetAddress())), getContext().getExecutorService());
                this.runner.setContext(getContext());
                getContext().getExecutorService().execute(this.runner);
                super.start();
            } catch (Throwable e) {
                addError("server startup error: " + e, e);
            }
        }
    }

    public void stop() {
        if (isStarted()) {
            try {
                this.runner.stop();
                super.stop();
            } catch (Throwable e) {
                addError("server shutdown error: " + e, e);
            }
        }
    }
}
