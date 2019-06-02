package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class SMTPResultParser extends ResultParser {
    public EmailAddressParsedResult parse(Result result) {
        String massagedText = ResultParser.getMassagedText(result);
        if (!massagedText.startsWith("smtp:") && !massagedText.startsWith("SMTP:")) {
            return null;
        }
        String substring;
        String substring2;
        String str;
        massagedText = massagedText.substring(5);
        int indexOf = massagedText.indexOf(58);
        if (indexOf >= 0) {
            substring = massagedText.substring(indexOf + 1);
            massagedText = massagedText.substring(0, indexOf);
            indexOf = substring.indexOf(58);
            if (indexOf >= 0) {
                substring2 = substring.substring(indexOf + 1);
                substring = substring.substring(0, indexOf);
                str = massagedText;
            } else {
                substring2 = null;
                str = massagedText;
            }
        } else {
            substring2 = null;
            substring = null;
            str = massagedText;
        }
        return new EmailAddressParsedResult(new String[]{str}, null, null, substring, substring2);
    }
}
