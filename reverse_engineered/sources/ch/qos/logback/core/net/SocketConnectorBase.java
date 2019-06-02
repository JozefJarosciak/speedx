package ch.qos.logback.core.net;

import ch.qos.logback.core.net.SocketConnector.ExceptionHandler;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.net.SocketFactory;

public class SocketConnectorBase implements SocketConnector {
    private final InetAddress address;
    private final Condition connectCondition;
    private DelayStrategy delayStrategy;
    private ExceptionHandler exceptionHandler;
    private final Lock lock;
    private final int port;
    private Socket socket;
    private SocketFactory socketFactory;

    private static class ConsoleExceptionHandler implements ExceptionHandler {
        private ConsoleExceptionHandler() {
        }

        public void connectionFailed(SocketConnector socketConnector, Exception exception) {
            System.out.println(exception);
        }
    }

    public interface DelayStrategy {
        int nextDelay();
    }

    private static class FixedDelay implements DelayStrategy {
        private int nextDelay;
        private final int retryDelay;

        public FixedDelay(int i, int i2) {
            this.nextDelay = i;
            this.retryDelay = i2;
        }

        public int nextDelay() {
            int i = this.nextDelay;
            this.nextDelay = this.retryDelay;
            return i;
        }
    }

    public SocketConnectorBase(InetAddress inetAddress, int i, int i2, int i3) {
        this(inetAddress, i, new FixedDelay(i2, i3));
    }

    public SocketConnectorBase(InetAddress inetAddress, int i, DelayStrategy delayStrategy) {
        this.lock = new ReentrantLock();
        this.connectCondition = this.lock.newCondition();
        this.address = inetAddress;
        this.port = i;
        this.delayStrategy = delayStrategy;
    }

    private void signalConnected() {
        this.lock.lock();
        try {
            this.connectCondition.signalAll();
        } finally {
            this.lock.unlock();
        }
    }

    public Socket awaitConnection() throws InterruptedException {
        return awaitConnection(Long.MAX_VALUE);
    }

    public Socket awaitConnection(long j) throws InterruptedException {
        this.lock.lock();
        Object obj = null;
        while (this.socket == null && r0 == null) {
            try {
                obj = !this.connectCondition.await(j, TimeUnit.MILLISECONDS) ? 1 : null;
            } finally {
                this.lock.unlock();
            }
        }
        Socket socket = this.socket;
        return socket;
    }

    public Socket call() throws InterruptedException {
        return null;
    }

    public void run() {
        if (this.socket != null) {
            throw new IllegalStateException("connector cannot be reused");
        }
        if (this.exceptionHandler == null) {
            this.exceptionHandler = new ConsoleExceptionHandler();
        }
        if (this.socketFactory == null) {
            this.socketFactory = SocketFactory.getDefault();
        }
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep((long) this.delayStrategy.nextDelay());
                try {
                    this.socket = this.socketFactory.createSocket(this.address, this.port);
                    signalConnected();
                    return;
                } catch (Exception e) {
                    this.exceptionHandler.connectionFailed(this, e);
                }
            } catch (Exception e2) {
                this.exceptionHandler.connectionFailed(this, e2);
                return;
            }
        }
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }
}
