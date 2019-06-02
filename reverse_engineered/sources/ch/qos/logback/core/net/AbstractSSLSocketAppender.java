package ch.qos.logback.core.net;

import ch.qos.logback.core.net.ssl.ConfigurableSSLSocketFactory;
import ch.qos.logback.core.net.ssl.SSLComponent;
import ch.qos.logback.core.net.ssl.SSLConfiguration;
import ch.qos.logback.core.net.ssl.SSLParametersConfiguration;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;

public abstract class AbstractSSLSocketAppender<E> extends AbstractSocketAppender<E> implements SSLComponent {
    private SocketFactory socketFactory;
    private SSLConfiguration ssl;

    protected AbstractSSLSocketAppender() {
    }

    @Deprecated
    protected AbstractSSLSocketAppender(String str, int i) {
        super(str, i);
    }

    protected SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public SSLConfiguration getSsl() {
        if (this.ssl == null) {
            this.ssl = new SSLConfiguration();
        }
        return this.ssl;
    }

    public void setSsl(SSLConfiguration sSLConfiguration) {
        this.ssl = sSLConfiguration;
    }

    public void start() {
        try {
            SSLContext createContext = getSsl().createContext(this);
            SSLParametersConfiguration parameters = getSsl().getParameters();
            parameters.setContext(getContext());
            this.socketFactory = new ConfigurableSSLSocketFactory(parameters, createContext.getSocketFactory());
            super.start();
        } catch (Throwable e) {
            addError(e.getMessage(), e);
        }
    }
}
