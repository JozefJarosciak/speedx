package ch.qos.logback.core.helpers;

import com.j256.ormlite.stmt.query.SimpleComparison;

public class Transform {
    private static final String CDATA_EMBEDED_END = "]]>]]&gt;<![CDATA[";
    private static final String CDATA_END = "]]>";
    private static final int CDATA_END_LEN = CDATA_END.length();
    private static final String CDATA_PSEUDO_END = "]]&gt;";
    private static final String CDATA_START = "<![CDATA[";

    public static void appendEscapingCDATA(StringBuilder stringBuilder, String str) {
        if (str != null) {
            int indexOf = str.indexOf(CDATA_END);
            if (indexOf < 0) {
                stringBuilder.append(str);
                return;
            }
            int i = 0;
            while (indexOf > -1) {
                stringBuilder.append(str.substring(i, indexOf));
                stringBuilder.append(CDATA_EMBEDED_END);
                i = CDATA_END_LEN + indexOf;
                if (i < str.length()) {
                    indexOf = str.indexOf(CDATA_END, i);
                } else {
                    return;
                }
            }
            stringBuilder.append(str.substring(i));
        }
    }

    public static String escapeTags(String str) {
        return (str == null || str.length() == 0) ? str : (str.indexOf(SimpleComparison.LESS_THAN_OPERATION) == -1 && str.indexOf(SimpleComparison.GREATER_THAN_OPERATION) == -1) ? str : escapeTags(new StringBuffer(str));
    }

    public static String escapeTags(StringBuffer stringBuffer) {
        for (int i = 0; i < stringBuffer.length(); i++) {
            char charAt = stringBuffer.charAt(i);
            if (charAt == '<') {
                stringBuffer.replace(i, i + 1, "&lt;");
            } else if (charAt == '>') {
                stringBuffer.replace(i, i + 1, "&gt;");
            }
        }
        return stringBuffer.toString();
    }
}
