package com.google.i18n.phonenumbers.internal;

import com.google.i18n.phonenumbers.Phonemetadata.PhoneNumberDesc;
import com.google.i18n.phonenumbers.RegexCache;
import java.util.regex.Matcher;

public final class RegexBasedMatcher implements MatcherApi {
    private final RegexCache regexCache = new RegexCache(100);

    public static MatcherApi create() {
        return new RegexBasedMatcher();
    }

    private RegexBasedMatcher() {
    }

    public boolean matchesNationalNumber(String str, PhoneNumberDesc phoneNumberDesc, boolean z) {
        Matcher matcher = this.regexCache.getPatternForRegex(phoneNumberDesc.getNationalNumberPattern()).matcher(str);
        return matcher.matches() || (z && matcher.lookingAt());
    }
}
