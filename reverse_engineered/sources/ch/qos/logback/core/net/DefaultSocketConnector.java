package ch.qos.logback.core.net;

import ch.qos.logback.core.net.SocketConnector.ExceptionHandler;
import ch.qos.logback.core.util.DelayStrategy;
import ch.qos.logback.core.util.FixedDelay;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.SocketFactory;

public class DefaultSocketConnector implements SocketConnector {
    private final InetAddress address;
    private final DelayStrategy delayStrategy;
    private ExceptionHandler exceptionHandler;
    private final int port;
    private SocketFactory socketFactory;

    private static class ConsoleExceptionHandler implements ExceptionHandler {
        private ConsoleExceptionHandler() {
        }

        public void connectionFailed(SocketConnector socketConnector, Exception exception) {
            System.out.println(exception);
        }
    }

    public DefaultSocketConnector(InetAddress inetAddress, int i, long j, long j2) {
        this(inetAddress, i, new FixedDelay(j, j2));
    }

    public DefaultSocketConnector(InetAddress inetAddress, int i, DelayStrategy delayStrategy) {
        this.address = inetAddress;
        this.port = i;
        this.delayStrategy = delayStrategy;
    }

    private Socket createSocket() {
        Socket socket = null;
        try {
            socket = this.socketFactory.createSocket(this.address, this.port);
        } catch (Exception e) {
            this.exceptionHandler.connectionFailed(this, e);
        }
        return socket;
    }

    private void useDefaultsForMissingFields() {
        if (this.exceptionHandler == null) {
            this.exceptionHandler = new ConsoleExceptionHandler();
        }
        if (this.socketFactory == null) {
            this.socketFactory = SocketFactory.getDefault();
        }
    }

    public Socket call() throws InterruptedException {
        useDefaultsForMissingFields();
        Socket createSocket = createSocket();
        while (createSocket == null && !Thread.currentThread().isInterrupted()) {
            Thread.sleep(this.delayStrategy.nextDelay());
            createSocket = createSocket();
        }
        return createSocket;
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }
}
