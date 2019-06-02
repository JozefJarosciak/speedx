package com.google.common.net;

import ch.qos.logback.core.CoreConstants;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.io.Serializable;
import org.slf4j.Marker;

@GwtCompatible
@Beta
public final class HostAndPort implements Serializable {
    private static final int NO_PORT = -1;
    private static final long serialVersionUID = 0;
    private final boolean hasBracketlessColons;
    private final String host;
    private final int port;

    private HostAndPort(String str, int i, boolean z) {
        this.host = str;
        this.port = i;
        this.hasBracketlessColons = z;
    }

    public String getHostText() {
        return this.host;
    }

    public boolean hasPort() {
        return this.port >= 0;
    }

    public int getPort() {
        Preconditions.checkState(hasPort());
        return this.port;
    }

    public int getPortOrDefault(int i) {
        return hasPort() ? this.port : i;
    }

    public static HostAndPort fromParts(String str, int i) {
        boolean z;
        Preconditions.checkArgument(isValidPort(i), "Port out of range: %s", Integer.valueOf(i));
        HostAndPort fromString = fromString(str);
        if (fromString.hasPort()) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, "Host has a port: %s", str);
        return new HostAndPort(fromString.host, i, fromString.hasBracketlessColons);
    }

    public static HostAndPort fromHost(String str) {
        boolean z;
        HostAndPort fromString = fromString(str);
        if (fromString.hasPort()) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, "Host has a port: %s", str);
        return fromString;
    }

    public static HostAndPort fromString(String str) {
        String str2;
        boolean z;
        int i;
        Preconditions.checkNotNull(str);
        String str3 = null;
        if (str.startsWith("[")) {
            String[] hostAndPortFromBracketedHost = getHostAndPortFromBracketedHost(str);
            str2 = hostAndPortFromBracketedHost[0];
            str3 = hostAndPortFromBracketedHost[1];
            z = false;
        } else {
            int indexOf = str.indexOf(58);
            if (indexOf < 0 || str.indexOf(58, indexOf + 1) != -1) {
                z = indexOf >= 0;
                str2 = str;
            } else {
                str2 = str.substring(0, indexOf);
                str3 = str.substring(indexOf + 1);
                z = false;
            }
        }
        if (Strings.isNullOrEmpty(str3)) {
            i = -1;
        } else {
            boolean z2;
            if (str3.startsWith(Marker.ANY_NON_NULL_MARKER)) {
                z2 = false;
            } else {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "Unparseable port number: %s", str);
            try {
                int parseInt = Integer.parseInt(str3);
                Preconditions.checkArgument(isValidPort(parseInt), "Port number out of range: %s", str);
                i = parseInt;
            } catch (NumberFormatException e) {
                String str4 = "Unparseable port number: ";
                String valueOf = String.valueOf(str);
                throw new IllegalArgumentException(valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
            }
        }
        return new HostAndPort(str2, i, z);
    }

    private static String[] getHostAndPortFromBracketedHost(String str) {
        boolean z;
        Preconditions.checkArgument(str.charAt(0) == '[', "Bracketed host-port string must start with a bracket: %s", str);
        int indexOf = str.indexOf(58);
        int lastIndexOf = str.lastIndexOf(93);
        if (indexOf <= -1 || lastIndexOf <= indexOf) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, "Invalid bracketed host/port: %s", str);
        String substring = str.substring(1, lastIndexOf);
        if (lastIndexOf + 1 == str.length()) {
            return new String[]{substring, ""};
        }
        if (str.charAt(lastIndexOf + 1) == CoreConstants.COLON_CHAR) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "Only a colon may follow a close bracket: %s", str);
        for (indexOf = lastIndexOf + 2; indexOf < str.length(); indexOf++) {
            Preconditions.checkArgument(Character.isDigit(str.charAt(indexOf)), "Port must be numeric: %s", str);
        }
        return new String[]{substring, str.substring(lastIndexOf + 2)};
    }

    public HostAndPort withDefaultPort(int i) {
        Preconditions.checkArgument(isValidPort(i));
        return (hasPort() || this.port == i) ? this : new HostAndPort(this.host, i, this.hasBracketlessColons);
    }

    public HostAndPort requireBracketsForIPv6() {
        boolean z;
        if (this.hasBracketlessColons) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, "Possible bracketless IPv6 literal: %s", this.host);
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HostAndPort)) {
            return false;
        }
        HostAndPort hostAndPort = (HostAndPort) obj;
        if (Objects.equal(this.host, hostAndPort.host) && this.port == hostAndPort.port && this.hasBracketlessColons == hostAndPort.hasBracketlessColons) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.host, Integer.valueOf(this.port), Boolean.valueOf(this.hasBracketlessColons)});
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.host.length() + 8);
        if (this.host.indexOf(58) >= 0) {
            stringBuilder.append('[').append(this.host).append(']');
        } else {
            stringBuilder.append(this.host);
        }
        if (hasPort()) {
            stringBuilder.append(CoreConstants.COLON_CHAR).append(this.port);
        }
        return stringBuilder.toString();
    }

    private static boolean isValidPort(int i) {
        return i >= 0 && i <= 65535;
    }
}
