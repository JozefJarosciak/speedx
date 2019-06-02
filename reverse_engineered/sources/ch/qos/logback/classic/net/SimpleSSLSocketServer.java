package ch.qos.logback.classic.net;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.net.ssl.ConfigurableSSLServerSocketFactory;
import ch.qos.logback.core.net.ssl.SSLParametersConfiguration;
import java.security.NoSuchAlgorithmException;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;

public class SimpleSSLSocketServer extends SimpleSocketServer {
    private final ServerSocketFactory socketFactory;

    public SimpleSSLSocketServer(LoggerContext loggerContext, int i) throws NoSuchAlgorithmException {
        this(loggerContext, i, SSLContext.getDefault());
    }

    public SimpleSSLSocketServer(LoggerContext loggerContext, int i, SSLContext sSLContext) {
        super(loggerContext, i);
        if (sSLContext == null) {
            throw new NullPointerException("SSL context required");
        }
        SSLParametersConfiguration sSLParametersConfiguration = new SSLParametersConfiguration();
        sSLParametersConfiguration.setContext(loggerContext);
        this.socketFactory = new ConfigurableSSLServerSocketFactory(sSLParametersConfiguration, sSLContext.getServerSocketFactory());
    }

    public static void main(String[] strArr) throws Exception {
        SimpleSocketServer.doMain(SimpleSSLSocketServer.class, strArr);
    }

    protected ServerSocketFactory getServerSocketFactory() {
        return this.socketFactory;
    }
}
