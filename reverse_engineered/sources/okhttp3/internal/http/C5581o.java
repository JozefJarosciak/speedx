package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Protocol;

/* compiled from: StatusLine */
/* renamed from: okhttp3.internal.http.o */
public final class C5581o {
    /* renamed from: a */
    public final Protocol f17988a;
    /* renamed from: b */
    public final int f17989b;
    /* renamed from: c */
    public final String f17990c;

    public C5581o(Protocol protocol, int i, String str) {
        this.f17988a = protocol;
        this.f17989b = i;
        this.f17990c = str;
    }

    /* renamed from: a */
    public static C5581o m20289a(String str) throws IOException {
        Protocol protocol;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: " + str);
            }
            int charAt = str.charAt(7) - 48;
            if (charAt == 0) {
                protocol = Protocol.HTTP_1_0;
            } else if (charAt == 1) {
                protocol = Protocol.HTTP_1_1;
            } else {
                throw new ProtocolException("Unexpected status line: " + str);
            }
        } else if (str.startsWith("ICY ")) {
            protocol = Protocol.HTTP_1_0;
            i = 4;
        } else {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        if (str.length() < i + 3) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        try {
            String str2;
            int parseInt = Integer.parseInt(str.substring(i, i + 3));
            String str3 = "";
            if (str.length() <= i + 3) {
                str2 = str3;
            } else if (str.charAt(i + 3) != ' ') {
                throw new ProtocolException("Unexpected status line: " + str);
            } else {
                str2 = str.substring(i + 4);
            }
            return new C5581o(protocol, parseInt, str2);
        } catch (NumberFormatException e) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f17988a == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        stringBuilder.append(' ').append(this.f17989b);
        if (this.f17990c != null) {
            stringBuilder.append(' ').append(this.f17990c);
        }
        return stringBuilder.toString();
    }
}
