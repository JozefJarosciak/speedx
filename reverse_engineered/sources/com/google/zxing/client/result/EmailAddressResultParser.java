package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Map;
import java.util.regex.Pattern;

public final class EmailAddressResultParser extends ResultParser {
    private static final Pattern COMMA = Pattern.compile(",");

    public EmailAddressParsedResult parse(Result result) {
        String[] strArr = null;
        String massagedText = ResultParser.getMassagedText(result);
        if (massagedText.startsWith("mailto:") || massagedText.startsWith("MAILTO:")) {
            String[] strArr2;
            String[] split;
            String str;
            String str2;
            String substring = massagedText.substring(7);
            int indexOf = substring.indexOf(63);
            if (indexOf >= 0) {
                substring = substring.substring(0, indexOf);
            }
            CharSequence urlDecode = ResultParser.urlDecode(substring);
            if (urlDecode.isEmpty()) {
                strArr2 = null;
            } else {
                strArr2 = COMMA.split(urlDecode);
            }
            Map parseNameValuePairs = ResultParser.parseNameValuePairs(massagedText);
            if (parseNameValuePairs != null) {
                String[] split2;
                if (strArr2 == null) {
                    substring = (String) parseNameValuePairs.get("to");
                    if (substring != null) {
                        split = COMMA.split(substring);
                        substring = (String) parseNameValuePairs.get("cc");
                        if (substring == null) {
                            split2 = COMMA.split(substring);
                        } else {
                            split2 = null;
                        }
                        substring = (String) parseNameValuePairs.get("bcc");
                        if (substring != null) {
                            strArr = COMMA.split(substring);
                        }
                        substring = (String) parseNameValuePairs.get("subject");
                        str = (String) parseNameValuePairs.get("body");
                        strArr2 = split;
                        split = strArr;
                        strArr = split2;
                        str2 = substring;
                    }
                }
                split = strArr2;
                substring = (String) parseNameValuePairs.get("cc");
                if (substring == null) {
                    split2 = null;
                } else {
                    split2 = COMMA.split(substring);
                }
                substring = (String) parseNameValuePairs.get("bcc");
                if (substring != null) {
                    strArr = COMMA.split(substring);
                }
                substring = (String) parseNameValuePairs.get("subject");
                str = (String) parseNameValuePairs.get("body");
                strArr2 = split;
                split = strArr;
                strArr = split2;
                str2 = substring;
            } else {
                str = null;
                str2 = null;
                split = null;
            }
            return new EmailAddressParsedResult(strArr2, strArr, split, str2, str);
        } else if (EmailDoCoMoResultParser.isBasicallyValidEmailAddress(massagedText)) {
            return new EmailAddressParsedResult(massagedText);
        } else {
            return null;
        }
    }
}
