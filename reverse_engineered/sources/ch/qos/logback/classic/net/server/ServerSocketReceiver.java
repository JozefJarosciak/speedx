package ch.qos.logback.classic.net.server;

import ch.qos.logback.classic.net.ReceiverBase;
import ch.qos.logback.core.net.AbstractSocketAppender;
import ch.qos.logback.core.net.server.ServerListener;
import ch.qos.logback.core.net.server.ServerRunner;
import ch.qos.logback.core.util.CloseUtil;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import javax.net.ServerSocketFactory;

public class ServerSocketReceiver extends ReceiverBase {
    public static final int DEFAULT_BACKLOG = 50;
    private String address;
    private int backlog = 50;
    private int port = AbstractSocketAppender.DEFAULT_PORT;
    private ServerRunner runner;
    private ServerSocket serverSocket;

    protected ServerListener<RemoteAppenderClient> createServerListener(ServerSocket serverSocket) {
        return new RemoteAppenderServerListener(serverSocket);
    }

    protected ServerRunner createServerRunner(ServerListener<RemoteAppenderClient> serverListener, Executor executor) {
        return new RemoteAppenderServerRunner(serverListener, executor);
    }

    public String getAddress() {
        return this.address;
    }

    public int getBacklog() {
        return this.backlog;
    }

    protected InetAddress getInetAddress() throws UnknownHostException {
        return getAddress() == null ? null : InetAddress.getByName(getAddress());
    }

    public int getPort() {
        return this.port;
    }

    protected Runnable getRunnableTask() {
        return this.runner;
    }

    protected ServerSocketFactory getServerSocketFactory() throws Exception {
        return ServerSocketFactory.getDefault();
    }

    protected void onStop() {
        try {
            if (this.runner != null) {
                this.runner.stop();
            }
        } catch (Throwable e) {
            addError("server shutdown error: " + e, e);
        }
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setBacklog(int i) {
        this.backlog = i;
    }

    public void setPort(int i) {
        this.port = i;
    }

    protected boolean shouldStart() {
        try {
            this.runner = createServerRunner(createServerListener(getServerSocketFactory().createServerSocket(getPort(), getBacklog(), getInetAddress())), getContext().getExecutorService());
            this.runner.setContext(getContext());
            return true;
        } catch (Throwable e) {
            addError("server startup error: " + e, e);
            CloseUtil.closeQuietly(this.serverSocket);
            return false;
        }
    }
}
