package ch.qos.logback.classic.net;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import javax.net.ServerSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleSocketServer extends Thread {
    private boolean closed = false;
    private CountDownLatch latch;
    private final LoggerContext lc;
    Logger logger = LoggerFactory.getLogger(SimpleSocketServer.class);
    private final int port;
    private ServerSocket serverSocket;
    private List<SocketNode> socketNodeList = new ArrayList();

    public SimpleSocketServer(LoggerContext loggerContext, int i) {
        this.lc = loggerContext;
        this.port = i;
    }

    public static void configureLC(LoggerContext loggerContext, String str) throws JoranException {
        JoranConfigurator joranConfigurator = new JoranConfigurator();
        loggerContext.reset();
        joranConfigurator.setContext(loggerContext);
        joranConfigurator.doConfigure(str);
    }

    protected static void doMain(Class<? extends SimpleSocketServer> cls, String[] strArr) throws Exception {
        int parsePortNumber;
        if (strArr.length == 2) {
            parsePortNumber = parsePortNumber(strArr[0]);
        } else {
            usage("Wrong number of arguments.");
            parsePortNumber = -1;
        }
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        configureLC(loggerContext, strArr[1]);
        new SimpleSocketServer(loggerContext, parsePortNumber).start();
    }

    public static void main(String[] strArr) throws Exception {
        doMain(SimpleSocketServer.class, strArr);
    }

    static int parsePortNumber(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            usage("Could not interpret port number [" + str + "].");
            return -1;
        }
    }

    static void usage(String str) {
        System.err.println(str);
        System.err.println("Usage: java " + SimpleSocketServer.class.getName() + " port configFile");
        System.exit(1);
    }

    public void close() {
        this.closed = true;
        if (this.serverSocket != null) {
            try {
                this.serverSocket.close();
            } catch (Throwable e) {
                this.logger.error("Failed to close serverSocket", e);
            } finally {
                this.serverSocket = null;
            }
        }
        this.logger.info("closing this server");
        synchronized (this.socketNodeList) {
            for (SocketNode close : this.socketNodeList) {
                close.close();
            }
        }
        if (this.socketNodeList.size() != 0) {
            this.logger.warn("Was expecting a 0-sized socketNodeList after server shutdown");
        }
    }

    protected String getClientThreadName(Socket socket) {
        return String.format("Logback SocketNode (client: %s)", new Object[]{socket.getRemoteSocketAddress()});
    }

    public CountDownLatch getLatch() {
        return this.latch;
    }

    protected ServerSocketFactory getServerSocketFactory() {
        return ServerSocketFactory.getDefault();
    }

    protected String getServerThreadName() {
        return String.format("Logback %s (port %d)", new Object[]{getClass().getSimpleName(), Integer.valueOf(this.port)});
    }

    public boolean isClosed() {
        return this.closed;
    }

    public void run() {
        String name = Thread.currentThread().getName();
        try {
            Thread.currentThread().setName(getServerThreadName());
            this.logger.info("Listening on port " + this.port);
            this.serverSocket = getServerSocketFactory().createServerSocket(this.port);
            while (!this.closed) {
                this.logger.info("Waiting to accept a new client.");
                signalAlmostReadiness();
                Socket accept = this.serverSocket.accept();
                this.logger.info("Connected to client at " + accept.getInetAddress());
                this.logger.info("Starting new socket node.");
                Runnable socketNode = new SocketNode(this, accept, this.lc);
                synchronized (this.socketNodeList) {
                    this.socketNodeList.add(socketNode);
                }
                new Thread(socketNode, getClientThreadName(accept)).start();
            }
            Thread.currentThread().setName(name);
        } catch (Throwable e) {
            if (this.closed) {
                this.logger.info("Exception in run method for a closed server. This is normal.");
            } else {
                this.logger.error("Unexpected failure in run method", e);
            }
            Thread.currentThread().setName(name);
        } catch (Throwable th) {
            Thread.currentThread().setName(name);
        }
    }

    void setLatch(CountDownLatch countDownLatch) {
        this.latch = countDownLatch;
    }

    void signalAlmostReadiness() {
        if (this.latch != null && this.latch.getCount() != 0) {
            this.latch.countDown();
        }
    }

    public void socketNodeClosing(SocketNode socketNode) {
        this.logger.debug("Removing {}", (Object) socketNode);
        synchronized (this.socketNodeList) {
            this.socketNodeList.remove(socketNode);
        }
    }
}
