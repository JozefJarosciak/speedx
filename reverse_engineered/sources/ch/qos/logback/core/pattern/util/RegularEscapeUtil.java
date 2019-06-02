package ch.qos.logback.core.pattern.util;

import ch.qos.logback.core.CoreConstants;
import com.avos.avoscloud.AVException;

public class RegularEscapeUtil implements IEscapeUtil {
    public static String basicEscape(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            char charAt = str.charAt(i);
            if (charAt == CoreConstants.ESCAPE_CHAR) {
                i = i2 + 1;
                charAt = str.charAt(i2);
                if (charAt == 'n') {
                    charAt = '\n';
                } else if (charAt == 'r') {
                    charAt = '\r';
                } else if (charAt == 't') {
                    charAt = '\t';
                } else if (charAt == 'f') {
                    charAt = '\f';
                }
            } else {
                i = i2;
            }
            stringBuffer.append(charAt);
        }
        return stringBuffer.toString();
    }

    public void escape(String str, StringBuffer stringBuffer, char c, int i) {
        if (str.indexOf(c) >= 0) {
            stringBuffer.append(c);
            return;
        }
        switch (c) {
            case '\\':
                stringBuffer.append(c);
                return;
            case '_':
                return;
            case 'n':
                stringBuffer.append('\n');
                return;
            case 'r':
                stringBuffer.append('\r');
                return;
            case AVException.OBJECT_TOO_LARGE /*116*/:
                stringBuffer.append('\t');
                return;
            default:
                throw new IllegalArgumentException("Illegal char '" + c + " at column " + i + ". Only \\\\, \\_" + formatEscapeCharsForListing(str) + ", \\t, \\n, \\r combinations are allowed as escape characters.");
        }
    }

    String formatEscapeCharsForListing(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            stringBuilder.append(", \\").append(str.charAt(i));
        }
        return stringBuilder.toString();
    }
}
