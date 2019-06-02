package ch.qos.logback.core.recovery;

import ch.qos.logback.core.net.SyslogOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ResilientSyslogOutputStream extends ResilientOutputStreamBase {
    int port;
    String syslogHost;

    public ResilientSyslogOutputStream(String str, int i) throws UnknownHostException, SocketException {
        this.syslogHost = str;
        this.port = i;
        this.os = new SyslogOutputStream(str, i);
        this.presumedClean = true;
    }

    String getDescription() {
        return "syslog [" + this.syslogHost + ":" + this.port + "]";
    }

    OutputStream openNewOutputStream() throws IOException {
        return new SyslogOutputStream(this.syslogHost, this.port);
    }

    public String toString() {
        return "c.q.l.c.recovery.ResilientSyslogOutputStream@" + System.identityHashCode(this);
    }
}
