package ch.qos.logback.repackage.brut.androlib.res.xml;

import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.lang.Character.UnicodeBlock;
import java.util.ArrayList;
import java.util.List;

public final class ResXmlEncoders {
    public static String encodeAsResXmlAttr(String str) {
        if (str.isEmpty()) {
            return str;
        }
        char[] toCharArray = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder(str.length() + 10);
        switch (toCharArray[0]) {
            case '#':
            case '?':
            case '@':
                stringBuilder.append(CoreConstants.ESCAPE_CHAR);
                break;
        }
        for (char c : toCharArray) {
            switch (c) {
                case '\n':
                    stringBuilder.append("\\n");
                    continue;
                case '\"':
                    stringBuilder.append("&quot;");
                    continue;
                case '\\':
                    stringBuilder.append(CoreConstants.ESCAPE_CHAR);
                    break;
                default:
                    if (!isPrintableChar(c)) {
                        stringBuilder.append(String.format("\\u%04x", new Object[]{Integer.valueOf(c)}));
                        continue;
                    }
                    break;
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static String encodeAsXmlValue(String str) {
        if (str.isEmpty()) {
            return str;
        }
        char[] toCharArray = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder(str.length() + 10);
        switch (toCharArray[0]) {
            case '#':
            case '?':
            case '@':
                stringBuilder.append(CoreConstants.ESCAPE_CHAR);
                break;
        }
        int length = toCharArray.length;
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i < length) {
            int i6;
            char c = toCharArray[i];
            if (i5 == 0) {
                if (c != ' ') {
                    switch (c) {
                        case '\n':
                        case '\'':
                            i3 = 0;
                            i2 = 1;
                            break;
                        case '\"':
                            stringBuilder.append(CoreConstants.ESCAPE_CHAR);
                            i2 = i3;
                            i3 = 0;
                            break;
                        case '<':
                            if (i3 == 0) {
                                i2 = i3;
                                i5 = 1;
                                i3 = 0;
                                break;
                            }
                            stringBuilder.insert(i4, CoreConstants.DOUBLE_QUOTE_CHAR).append(CoreConstants.DOUBLE_QUOTE_CHAR);
                            i2 = i3;
                            i5 = 1;
                            i3 = 0;
                            break;
                        case '\\':
                            stringBuilder.append(CoreConstants.ESCAPE_CHAR);
                            i2 = i3;
                            i3 = 0;
                            break;
                        default:
                            if (isPrintableChar(c)) {
                                i2 = i3;
                                i3 = 0;
                                break;
                            }
                            stringBuilder.append(String.format("\\u%04x", new Object[]{Integer.valueOf(c)}));
                            i2 = i3;
                            i3 = 0;
                            continue;
                    }
                } else {
                    if (i2 != 0) {
                        i3 = 1;
                    }
                    i2 = i3;
                    i3 = 1;
                }
            } else if (c == '>') {
                i4 = stringBuilder.length() + 1;
                i5 = 0;
                i3 = i2;
                i2 = 0;
            } else {
                i6 = i2;
                i2 = i3;
                i3 = i6;
            }
            stringBuilder.append(c);
            i++;
            i6 = i3;
            i3 = i2;
            i2 = i6;
        }
        if (!(i3 == 0 && i2 == 0)) {
            stringBuilder.insert(i4, CoreConstants.DOUBLE_QUOTE_CHAR).append(CoreConstants.DOUBLE_QUOTE_CHAR);
        }
        return stringBuilder.toString();
    }

    public static String enumerateNonPositionalSubstitutions(String str) {
        List<Integer> findNonPositionalSubstitutions = findNonPositionalSubstitutions(str, -1);
        if (findNonPositionalSubstitutions.size() < 2) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        int i2 = 0;
        for (Integer intValue : findNonPositionalSubstitutions) {
            Integer valueOf = Integer.valueOf(intValue.intValue() + 1);
            int i3 = i + 1;
            stringBuilder.append(str.substring(i2, valueOf.intValue())).append(i3).append(CoreConstants.DOLLAR);
            i2 = valueOf.intValue();
            i = i3;
        }
        stringBuilder.append(str.substring(i2));
        return stringBuilder.toString();
    }

    public static String escapeXmlChars(String str) {
        return str.replace(C0869a.f2161b, "&amp;").replace(SimpleComparison.LESS_THAN_OPERATION, "&lt;");
    }

    private static List<Integer> findNonPositionalSubstitutions(String str, int i) {
        int i2 = 0;
        int length = str.length();
        List<Integer> arrayList = new ArrayList();
        int i3 = 0;
        while (true) {
            int indexOf = str.indexOf(37, i3);
            int i4 = indexOf + 1;
            if (i4 == 0 || i4 == length) {
                break;
            }
            i3 = i4 + 1;
            char charAt = str.charAt(i4);
            if (charAt != CoreConstants.PERCENT_CHAR) {
                if (charAt >= '0' && charAt <= '9' && i3 < length) {
                    do {
                        i4 = i3;
                        i3 = i4 + 1;
                        charAt = str.charAt(i4);
                        if (charAt < '0' || charAt > '9') {
                        }
                    } while (i3 < length);
                    if (charAt == CoreConstants.DOLLAR) {
                        continue;
                    }
                }
                arrayList.add(Integer.valueOf(indexOf));
                if (i != -1) {
                    i2++;
                    if (i2 >= i) {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        return arrayList;
    }

    public static boolean hasMultipleNonPositionalSubstitutions(String str) {
        return findNonPositionalSubstitutions(str, 2).size() > 1;
    }

    private static boolean isPrintableChar(char c) {
        UnicodeBlock of = UnicodeBlock.of(c);
        return (Character.isISOControl(c) || c == '￿' || of == null || of == UnicodeBlock.SPECIALS) ? false : true;
    }
}
