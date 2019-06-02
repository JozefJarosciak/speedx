package com.google.zxing.client.result;

import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.sys.C0869a;
import com.google.zxing.Result;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class ResultParser {
    private static final Pattern AMPERSAND = Pattern.compile(C0869a.f2161b);
    private static final String BYTE_ORDER_MARK = "﻿";
    private static final Pattern DIGITS = Pattern.compile("\\d+");
    private static final Pattern EQUALS = Pattern.compile(SimpleComparison.EQUAL_TO_OPERATION);
    private static final ResultParser[] PARSERS = new ResultParser[]{new BookmarkDoCoMoResultParser(), new AddressBookDoCoMoResultParser(), new EmailDoCoMoResultParser(), new AddressBookAUResultParser(), new VCardResultParser(), new BizcardResultParser(), new VEventResultParser(), new EmailAddressResultParser(), new SMTPResultParser(), new TelResultParser(), new SMSMMSResultParser(), new SMSTOMMSTOResultParser(), new GeoResultParser(), new WifiResultParser(), new URLTOResultParser(), new URIResultParser(), new ISBNResultParser(), new ProductResultParser(), new ExpandedProductResultParser(), new VINResultParser()};

    public abstract ParsedResult parse(Result result);

    protected static String getMassagedText(Result result) {
        String text = result.getText();
        if (text.startsWith("﻿")) {
            return text.substring(1);
        }
        return text;
    }

    public static ParsedResult parseResult(Result result) {
        for (ResultParser parse : PARSERS) {
            ParsedResult parse2 = parse.parse(result);
            if (parse2 != null) {
                return parse2;
            }
        }
        return new TextParsedResult(result.getText(), null);
    }

    protected static void maybeAppend(String str, StringBuilder stringBuilder) {
        if (str != null) {
            stringBuilder.append('\n');
            stringBuilder.append(str);
        }
    }

    protected static void maybeAppend(String[] strArr, StringBuilder stringBuilder) {
        if (strArr != null) {
            for (String str : strArr) {
                stringBuilder.append('\n');
                stringBuilder.append(str);
            }
        }
    }

    protected static String[] maybeWrap(String str) {
        if (str == null) {
            return null;
        }
        return new String[]{str};
    }

    protected static String unescapeBackslash(String str) {
        int indexOf = str.indexOf(92);
        if (indexOf < 0) {
            return str;
        }
        int length = str.length();
        StringBuilder stringBuilder = new StringBuilder(length - 1);
        stringBuilder.append(str.toCharArray(), 0, indexOf);
        indexOf = 0;
        for (int i = indexOf; i < length; i++) {
            char charAt = str.charAt(i);
            if (indexOf == 0 && charAt == CoreConstants.ESCAPE_CHAR) {
                indexOf = 1;
            } else {
                stringBuilder.append(charAt);
                indexOf = 0;
            }
        }
        return stringBuilder.toString();
    }

    protected static int parseHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 65) + 10;
    }

    protected static boolean isStringOfDigits(CharSequence charSequence, int i) {
        return charSequence != null && i > 0 && i == charSequence.length() && DIGITS.matcher(charSequence).matches();
    }

    protected static boolean isSubstringOfDigits(CharSequence charSequence, int i, int i2) {
        if (charSequence == null || i2 <= 0) {
            return false;
        }
        int i3 = i + i2;
        if (charSequence.length() < i3 || !DIGITS.matcher(charSequence.subSequence(i, i3)).matches()) {
            return false;
        }
        return true;
    }

    static Map<String, String> parseNameValuePairs(String str) {
        int indexOf = str.indexOf(63);
        if (indexOf < 0) {
            return null;
        }
        Map<String, String> hashMap = new HashMap(3);
        for (CharSequence appendKeyValue : AMPERSAND.split(str.substring(indexOf + 1))) {
            appendKeyValue(appendKeyValue, hashMap);
        }
        return hashMap;
    }

    private static void appendKeyValue(CharSequence charSequence, Map<String, String> map) {
        String[] split = EQUALS.split(charSequence, 2);
        if (split.length == 2) {
            try {
                map.put(split[0], urlDecode(split[1]));
            } catch (IllegalArgumentException e) {
            }
        }
    }

    static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    static String[] matchPrefixedField(String str, String str2, char c, boolean z) {
        int length = str2.length();
        int i = 0;
        List list = null;
        while (i < length) {
            i = str2.indexOf(str, i);
            if (i < 0) {
                break;
            }
            int length2 = i + str.length();
            Object obj = 1;
            List list2 = list;
            int i2 = length2;
            while (obj != null) {
                int indexOf = str2.indexOf(c, i2);
                if (indexOf < 0) {
                    i2 = str2.length();
                    obj = null;
                } else if (countPrecedingBackslashes(str2, indexOf) % 2 != 0) {
                    i2 = indexOf + 1;
                } else {
                    if (list2 == null) {
                        list2 = new ArrayList(3);
                    }
                    String unescapeBackslash = unescapeBackslash(str2.substring(length2, indexOf));
                    if (z) {
                        unescapeBackslash = unescapeBackslash.trim();
                    }
                    if (!unescapeBackslash.isEmpty()) {
                        list2.add(unescapeBackslash);
                    }
                    i2 = indexOf + 1;
                    obj = null;
                }
            }
            int i3 = i2;
            list = list2;
            i = i3;
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    private static int countPrecedingBackslashes(CharSequence charSequence, int i) {
        int i2 = 0;
        int i3 = i - 1;
        while (i3 >= 0 && charSequence.charAt(i3) == CoreConstants.ESCAPE_CHAR) {
            i2++;
            i3--;
        }
        return i2;
    }

    static String matchSinglePrefixedField(String str, String str2, char c, boolean z) {
        String[] matchPrefixedField = matchPrefixedField(str, str2, c, z);
        return matchPrefixedField == null ? null : matchPrefixedField[0];
    }
}
