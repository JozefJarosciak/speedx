package com.google.common.net;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.net.InetAddress;
import java.text.ParseException;

@Beta
public final class HostSpecifier {
    private final String canonicalForm;

    private HostSpecifier(String str) {
        this.canonicalForm = str;
    }

    public static HostSpecifier fromValid(String str) {
        InetAddress forString;
        HostAndPort fromString = HostAndPort.fromString(str);
        Preconditions.checkArgument(!fromString.hasPort());
        String hostText = fromString.getHostText();
        InetAddress inetAddress = null;
        try {
            forString = InetAddresses.forString(hostText);
        } catch (IllegalArgumentException e) {
            forString = inetAddress;
        }
        if (forString != null) {
            return new HostSpecifier(InetAddresses.toUriString(forString));
        }
        InternetDomainName from = InternetDomainName.from(hostText);
        if (from.hasPublicSuffix()) {
            return new HostSpecifier(from.toString());
        }
        String str2 = "Domain name does not have a recognized public suffix: ";
        String valueOf = String.valueOf(hostText);
        throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }

    public static HostSpecifier from(String str) throws ParseException {
        try {
            return fromValid(str);
        } catch (Throwable e) {
            String str2 = "Invalid host specifier: ";
            String valueOf = String.valueOf(str);
            ParseException parseException = new ParseException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), 0);
            parseException.initCause(e);
            throw parseException;
        }
    }

    public static boolean isValid(String str) {
        try {
            fromValid(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HostSpecifier)) {
            return false;
        }
        return this.canonicalForm.equals(((HostSpecifier) obj).canonicalForm);
    }

    public int hashCode() {
        return this.canonicalForm.hashCode();
    }

    public String toString() {
        return this.canonicalForm;
    }
}
