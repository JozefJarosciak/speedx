package ch.qos.logback.classic.net;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

public class SocketNode implements Runnable {
    boolean closed = false;
    LoggerContext context;
    Logger logger;
    ObjectInputStream ois;
    SocketAddress remoteSocketAddress;
    Socket socket;
    SimpleSocketServer socketServer;

    public SocketNode(SimpleSocketServer simpleSocketServer, Socket socket, LoggerContext loggerContext) {
        this.socketServer = simpleSocketServer;
        this.socket = socket;
        this.remoteSocketAddress = socket.getRemoteSocketAddress();
        this.context = loggerContext;
        this.logger = loggerContext.getLogger(SocketNode.class);
    }

    void close() {
        if (!this.closed) {
            this.closed = true;
            if (this.ois != null) {
                try {
                    this.ois.close();
                } catch (Throwable e) {
                    this.logger.warn("Could not close connection.", e);
                } finally {
                    this.ois = null;
                }
            }
        }
    }

    public void run() {
        try {
            this.ois = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
        } catch (Throwable e) {
            this.logger.error("Could not open ObjectInputStream to " + this.socket, e);
            this.closed = true;
        }
        while (!this.closed) {
            try {
                ILoggingEvent iLoggingEvent = (ILoggingEvent) this.ois.readObject();
                Logger logger = this.context.getLogger(iLoggingEvent.getLoggerName());
                if (logger.isEnabledFor(iLoggingEvent.getLevel())) {
                    logger.callAppenders(iLoggingEvent);
                }
            } catch (EOFException e2) {
                this.logger.info("Caught java.io.EOFException closing connection.");
            } catch (SocketException e3) {
                this.logger.info("Caught java.net.SocketException closing connection.");
            } catch (IOException e4) {
                this.logger.info("Caught java.io.IOException: " + e4);
                this.logger.info("Closing connection.");
            } catch (Throwable e5) {
                this.logger.error("Unexpected exception. Closing connection.", e5);
            }
        }
        this.socketServer.socketNodeClosing(this);
        close();
    }

    public String toString() {
        return getClass().getName() + this.remoteSocketAddress.toString();
    }
}
