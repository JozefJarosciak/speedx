package ch.qos.logback.core.net;

import java.net.Socket;
import java.util.concurrent.Callable;
import javax.net.SocketFactory;

public interface SocketConnector extends Callable<Socket> {

    public interface ExceptionHandler {
        void connectionFailed(SocketConnector socketConnector, Exception exception);
    }

    Socket call() throws InterruptedException;

    void setExceptionHandler(ExceptionHandler exceptionHandler);

    void setSocketFactory(SocketFactory socketFactory);
}
