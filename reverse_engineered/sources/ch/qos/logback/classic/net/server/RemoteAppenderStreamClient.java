package ch.qos.logback.classic.net.server;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.util.CloseUtil;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

class RemoteAppenderStreamClient implements RemoteAppenderClient {
    private final String id;
    private final InputStream inputStream;
    private LoggerContext lc;
    private Logger logger;
    private final Socket socket;

    public RemoteAppenderStreamClient(String str, InputStream inputStream) {
        this.id = str;
        this.socket = null;
        this.inputStream = inputStream;
    }

    public RemoteAppenderStreamClient(String str, Socket socket) {
        this.id = str;
        this.socket = socket;
        this.inputStream = null;
    }

    private ObjectInputStream createObjectInputStream() throws IOException {
        return this.inputStream != null ? new ObjectInputStream(this.inputStream) : new ObjectInputStream(this.socket.getInputStream());
    }

    public void close() {
        if (this.socket != null) {
            CloseUtil.closeQuietly(this.socket);
        }
    }

    public void run() {
        Closeable createObjectInputStream;
        Object e;
        Throwable th;
        this.logger.info(this + ": connected");
        Closeable closeable = null;
        try {
            createObjectInputStream = createObjectInputStream();
            while (true) {
                try {
                    ILoggingEvent iLoggingEvent = (ILoggingEvent) createObjectInputStream.readObject();
                    Logger logger = this.lc.getLogger(iLoggingEvent.getLoggerName());
                    if (logger.isEnabledFor(iLoggingEvent.getLevel())) {
                        logger.callAppenders(iLoggingEvent);
                    }
                } catch (EOFException e2) {
                    closeable = createObjectInputStream;
                } catch (IOException e3) {
                    e = e3;
                } catch (ClassNotFoundException e4) {
                } catch (RuntimeException e5) {
                    e = e5;
                }
            }
        } catch (EOFException e6) {
            if (closeable != null) {
                CloseUtil.closeQuietly(closeable);
            }
            close();
            this.logger.info(this + ": connection closed");
        } catch (IOException e7) {
            IOException iOException = e7;
            createObjectInputStream = null;
            e = iOException;
            try {
                this.logger.info(this + ": " + e);
                if (createObjectInputStream != null) {
                    CloseUtil.closeQuietly(createObjectInputStream);
                }
                close();
                this.logger.info(this + ": connection closed");
            } catch (Throwable th2) {
                th = th2;
                if (createObjectInputStream != null) {
                    CloseUtil.closeQuietly(createObjectInputStream);
                }
                close();
                this.logger.info(this + ": connection closed");
                throw th;
            }
        } catch (ClassNotFoundException e8) {
            createObjectInputStream = null;
            this.logger.error(this + ": unknown event class");
            if (createObjectInputStream != null) {
                CloseUtil.closeQuietly(createObjectInputStream);
            }
            close();
            this.logger.info(this + ": connection closed");
        } catch (RuntimeException e9) {
            RuntimeException runtimeException = e9;
            createObjectInputStream = null;
            e = runtimeException;
            this.logger.error(this + ": " + e);
            if (createObjectInputStream != null) {
                CloseUtil.closeQuietly(createObjectInputStream);
            }
            close();
            this.logger.info(this + ": connection closed");
        } catch (Throwable th3) {
            Throwable th4 = th3;
            createObjectInputStream = null;
            th = th4;
            if (createObjectInputStream != null) {
                CloseUtil.closeQuietly(createObjectInputStream);
            }
            close();
            this.logger.info(this + ": connection closed");
            throw th;
        }
    }

    public void setLoggerContext(LoggerContext loggerContext) {
        this.lc = loggerContext;
        this.logger = loggerContext.getLogger(getClass().getPackage().getName());
    }

    public String toString() {
        return "client " + this.id;
    }
}
