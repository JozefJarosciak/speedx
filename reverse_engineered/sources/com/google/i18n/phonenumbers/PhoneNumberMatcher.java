package com.google.i18n.phonenumbers;

import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.util.C0880h;
import com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency;
import com.google.i18n.phonenumbers.PhoneNumberUtil.MatchType;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonemetadata.NumberFormat;
import com.google.i18n.phonenumbers.Phonemetadata.PhoneMetadata;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource;
import java.lang.Character.UnicodeBlock;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Marker;

final class PhoneNumberMatcher implements Iterator<PhoneNumberMatch> {
    private static final Pattern[] INNER_MATCHES = new Pattern[]{Pattern.compile("/+(.*)"), Pattern.compile("(\\([^(]*)"), Pattern.compile("(?:\\p{Z}-|-\\p{Z})\\p{Z}*(.+)"), Pattern.compile("[‒-―－]\\p{Z}*(.+)"), Pattern.compile("\\.+\\p{Z}*([^.]+)"), Pattern.compile("\\p{Z}+(\\P{Z}+)")};
    private static final Pattern LEAD_CLASS;
    private static final Pattern MATCHING_BRACKETS;
    private static final Pattern PATTERN;
    private static final Pattern PUB_PAGES = Pattern.compile("\\d{1,5}-+\\d{1,5}\\s{0,4}\\(\\d{1,4}");
    private static final Pattern SLASH_SEPARATED_DATES = Pattern.compile("(?:(?:[0-3]?\\d/[01]?\\d)|(?:[01]?\\d/[0-3]?\\d))/(?:[12]\\d)?\\d{2}");
    private static final Pattern TIME_STAMPS = Pattern.compile("[12]\\d{3}[-/]?[01]\\d[-/]?[0-3]\\d +[0-2]\\d$");
    private static final Pattern TIME_STAMPS_SUFFIX = Pattern.compile(":[0-5]\\d");
    private PhoneNumberMatch lastMatch = null;
    private final Leniency leniency;
    private long maxTries;
    private final PhoneNumberUtil phoneUtil;
    private final String preferredRegion;
    private int searchIndex = 0;
    private State state = State.NOT_READY;
    private final CharSequence text;

    interface NumberGroupingChecker {
        boolean checkGroups(PhoneNumberUtil phoneNumberUtil, PhoneNumber phoneNumber, StringBuilder stringBuilder, String[] strArr);
    }

    private enum State {
        NOT_READY,
        READY,
        DONE
    }

    static {
        String str = "(\\[（［";
        String str2 = ")\\]）］";
        String str3 = "[^" + str + str2 + "]";
        MATCHING_BRACKETS = Pattern.compile("(?:[" + str + "])?(?:" + str3 + "+[" + str2 + "])?" + str3 + "+(?:[" + str + "]" + str3 + "+[" + str2 + "])" + limit(0, 3) + str3 + Marker.ANY_MARKER);
        str2 = limit(0, 2);
        str3 = limit(0, 4);
        String limit = limit(0, 20);
        str3 = "[-x‐-―−ー－-／  ­​⁠　()（）［］.\\[\\]/~⁓∼～]" + str3;
        String str4 = "\\p{Nd}" + limit(1, 20);
        str = "[" + (str + "+＋") + "]";
        LEAD_CLASS = Pattern.compile(str);
        PATTERN = Pattern.compile("(?:" + str + str3 + ")" + str2 + str4 + "(?:" + str3 + str4 + ")" + limit + "(?:" + PhoneNumberUtil.EXTN_PATTERNS_FOR_MATCHING + ")?", 66);
    }

    private static String limit(int i, int i2) {
        if (i >= 0 && i2 > 0 && i2 >= i) {
            return "{" + i + "," + i2 + C0880h.f2222d;
        }
        throw new IllegalArgumentException();
    }

    PhoneNumberMatcher(PhoneNumberUtil phoneNumberUtil, CharSequence charSequence, String str, Leniency leniency, long j) {
        if (phoneNumberUtil == null || leniency == null) {
            throw new NullPointerException();
        } else if (j < 0) {
            throw new IllegalArgumentException();
        } else {
            this.phoneUtil = phoneNumberUtil;
            if (charSequence == null) {
                charSequence = "";
            }
            this.text = charSequence;
            this.preferredRegion = str;
            this.leniency = leniency;
            this.maxTries = j;
        }
    }

    private PhoneNumberMatch find(int i) {
        Matcher matcher = PATTERN.matcher(this.text);
        while (this.maxTries > 0 && matcher.find(r7)) {
            int start = matcher.start();
            CharSequence trimAfterFirstMatch = trimAfterFirstMatch(PhoneNumberUtil.SECOND_NUMBER_START_PATTERN, this.text.subSequence(start, matcher.end()));
            PhoneNumberMatch extractMatch = extractMatch(trimAfterFirstMatch, start);
            if (extractMatch != null) {
                return extractMatch;
            }
            i = start + trimAfterFirstMatch.length();
            this.maxTries--;
        }
        return null;
    }

    private static CharSequence trimAfterFirstMatch(Pattern pattern, CharSequence charSequence) {
        Matcher matcher = pattern.matcher(charSequence);
        if (matcher.find()) {
            return charSequence.subSequence(0, matcher.start());
        }
        return charSequence;
    }

    static boolean isLatinLetter(char c) {
        if (!Character.isLetter(c) && Character.getType(c) != 6) {
            return false;
        }
        UnicodeBlock of = UnicodeBlock.of(c);
        if (of.equals(UnicodeBlock.BASIC_LATIN) || of.equals(UnicodeBlock.LATIN_1_SUPPLEMENT) || of.equals(UnicodeBlock.LATIN_EXTENDED_A) || of.equals(UnicodeBlock.LATIN_EXTENDED_ADDITIONAL) || of.equals(UnicodeBlock.LATIN_EXTENDED_B) || of.equals(UnicodeBlock.COMBINING_DIACRITICAL_MARKS)) {
            return true;
        }
        return false;
    }

    private static boolean isInvalidPunctuationSymbol(char c) {
        return c == CoreConstants.PERCENT_CHAR || Character.getType(c) == 26;
    }

    private PhoneNumberMatch extractMatch(CharSequence charSequence, int i) {
        if (SLASH_SEPARATED_DATES.matcher(charSequence).find()) {
            return null;
        }
        if (TIME_STAMPS.matcher(charSequence).find()) {
            if (TIME_STAMPS_SUFFIX.matcher(this.text.toString().substring(charSequence.length() + i)).lookingAt()) {
                return null;
            }
        }
        String charSequence2 = charSequence.toString();
        PhoneNumberMatch parseAndVerify = parseAndVerify(charSequence2, i);
        return parseAndVerify == null ? extractInnerMatch(charSequence2, i) : parseAndVerify;
    }

    private PhoneNumberMatch extractInnerMatch(String str, int i) {
        for (Pattern matcher : INNER_MATCHES) {
            Matcher matcher2 = matcher.matcher(str);
            Object obj = 1;
            while (matcher2.find() && this.maxTries > 0) {
                if (obj != null) {
                    PhoneNumberMatch parseAndVerify = parseAndVerify(trimAfterFirstMatch(PhoneNumberUtil.UNWANTED_END_CHAR_PATTERN, str.substring(0, matcher2.start())).toString(), i);
                    if (parseAndVerify != null) {
                        return parseAndVerify;
                    }
                    this.maxTries--;
                    obj = null;
                }
                PhoneNumberMatch parseAndVerify2 = parseAndVerify(trimAfterFirstMatch(PhoneNumberUtil.UNWANTED_END_CHAR_PATTERN, matcher2.group(1)).toString(), matcher2.start(1) + i);
                if (parseAndVerify2 != null) {
                    return parseAndVerify2;
                }
                this.maxTries--;
            }
        }
        return null;
    }

    private PhoneNumberMatch parseAndVerify(String str, int i) {
        try {
            if (!MATCHING_BRACKETS.matcher(str).matches() || PUB_PAGES.matcher(str).find()) {
                return null;
            }
            if (this.leniency.compareTo(Leniency.VALID) >= 0) {
                char charAt;
                if (i > 0 && !LEAD_CLASS.matcher(str).lookingAt()) {
                    charAt = this.text.charAt(i - 1);
                    if (isInvalidPunctuationSymbol(charAt) || isLatinLetter(charAt)) {
                        return null;
                    }
                }
                int length = str.length() + i;
                if (length < this.text.length()) {
                    charAt = this.text.charAt(length);
                    if (isInvalidPunctuationSymbol(charAt) || isLatinLetter(charAt)) {
                        return null;
                    }
                }
            }
            PhoneNumber parseAndKeepRawInput = this.phoneUtil.parseAndKeepRawInput(str, this.preferredRegion);
            if (this.phoneUtil.getRegionCodeForCountryCode(parseAndKeepRawInput.getCountryCode()).equals("IL") && this.phoneUtil.getNationalSignificantNumber(parseAndKeepRawInput).length() == 4) {
                if (i == 0) {
                    return null;
                }
                if (i > 0 && this.text.charAt(i - 1) != '*') {
                    return null;
                }
            }
            if (!this.leniency.verify(parseAndKeepRawInput, str, this.phoneUtil)) {
                return null;
            }
            parseAndKeepRawInput.clearCountryCodeSource();
            parseAndKeepRawInput.clearRawInput();
            parseAndKeepRawInput.clearPreferredDomesticCarrierCode();
            return new PhoneNumberMatch(i, str, parseAndKeepRawInput);
        } catch (NumberParseException e) {
            return null;
        }
    }

    static boolean allNumberGroupsRemainGrouped(PhoneNumberUtil phoneNumberUtil, PhoneNumber phoneNumber, StringBuilder stringBuilder, String[] strArr) {
        int length;
        if (phoneNumber.getCountryCodeSource() != CountryCodeSource.FROM_DEFAULT_COUNTRY) {
            String num = Integer.toString(phoneNumber.getCountryCode());
            length = num.length() + stringBuilder.indexOf(num);
        } else {
            length = 0;
        }
        int i = length;
        length = 0;
        while (length < strArr.length) {
            i = stringBuilder.indexOf(strArr[length], i);
            if (i < 0) {
                return false;
            }
            i += strArr[length].length();
            if (length != 0 || i >= stringBuilder.length() || phoneNumberUtil.getNddPrefixForRegion(phoneNumberUtil.getRegionCodeForCountryCode(phoneNumber.getCountryCode()), true) == null || !Character.isDigit(stringBuilder.charAt(i))) {
                length++;
            } else {
                return stringBuilder.substring(i - strArr[length].length()).startsWith(phoneNumberUtil.getNationalSignificantNumber(phoneNumber));
            }
        }
        return stringBuilder.substring(i).contains(phoneNumber.getExtension());
    }

    static boolean allNumberGroupsAreExactlyPresent(PhoneNumberUtil phoneNumberUtil, PhoneNumber phoneNumber, StringBuilder stringBuilder, String[] strArr) {
        int length;
        String[] split = PhoneNumberUtil.NON_DIGITS_PATTERN.split(stringBuilder.toString());
        if (phoneNumber.hasExtension()) {
            length = split.length - 2;
        } else {
            length = split.length - 1;
        }
        if (split.length == 1 || split[length].contains(phoneNumberUtil.getNationalSignificantNumber(phoneNumber))) {
            return true;
        }
        boolean z;
        int i = length;
        length = strArr.length - 1;
        while (length > 0 && i >= 0) {
            if (!split[i].equals(strArr[length])) {
                return false;
            }
            length--;
            i--;
        }
        if (i < 0 || !split[i].endsWith(strArr[0])) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    private static String[] getNationalNumberGroups(PhoneNumberUtil phoneNumberUtil, PhoneNumber phoneNumber, NumberFormat numberFormat) {
        if (numberFormat != null) {
            return phoneNumberUtil.formatNsnUsingPattern(phoneNumberUtil.getNationalSignificantNumber(phoneNumber), numberFormat, PhoneNumberFormat.RFC3966).split(HelpFormatter.DEFAULT_OPT_PREFIX);
        }
        String format = phoneNumberUtil.format(phoneNumber, PhoneNumberFormat.RFC3966);
        int indexOf = format.indexOf(59);
        if (indexOf < 0) {
            indexOf = format.length();
        }
        return format.substring(format.indexOf(45) + 1, indexOf).split(HelpFormatter.DEFAULT_OPT_PREFIX);
    }

    static boolean checkNumberGroupingIsValid(PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil, NumberGroupingChecker numberGroupingChecker) {
        StringBuilder normalizeDigits = PhoneNumberUtil.normalizeDigits(str, true);
        if (numberGroupingChecker.checkGroups(phoneNumberUtil, phoneNumber, normalizeDigits, getNationalNumberGroups(phoneNumberUtil, phoneNumber, null))) {
            return true;
        }
        PhoneMetadata alternateFormatsForCountry = MetadataManager.getAlternateFormatsForCountry(phoneNumber.getCountryCode());
        if (alternateFormatsForCountry != null) {
            for (NumberFormat nationalNumberGroups : alternateFormatsForCountry.numberFormats()) {
                if (numberGroupingChecker.checkGroups(phoneNumberUtil, phoneNumber, normalizeDigits, getNationalNumberGroups(phoneNumberUtil, phoneNumber, nationalNumberGroups))) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean containsMoreThanOneSlashInNationalNumber(PhoneNumber phoneNumber, String str) {
        int indexOf = str.indexOf(47);
        if (indexOf < 0) {
            return false;
        }
        int indexOf2 = str.indexOf(47, indexOf + 1);
        if (indexOf2 < 0) {
            return false;
        }
        boolean z = phoneNumber.getCountryCodeSource() == CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN || phoneNumber.getCountryCodeSource() == CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN;
        return (z && PhoneNumberUtil.normalizeDigitsOnly(str.substring(0, indexOf)).equals(Integer.toString(phoneNumber.getCountryCode()))) ? str.substring(indexOf2 + 1).contains("/") : true;
    }

    static boolean containsOnlyValidXChars(PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
        int i = 0;
        while (i < str.length() - 1) {
            char charAt = str.charAt(i);
            if (charAt == 'x' || charAt == 'X') {
                charAt = str.charAt(i + 1);
                if (charAt == 'x' || charAt == 'X') {
                    i++;
                    if (phoneNumberUtil.isNumberMatch(phoneNumber, str.substring(i)) != MatchType.NSN_MATCH) {
                        return false;
                    }
                } else if (!PhoneNumberUtil.normalizeDigitsOnly(str.substring(i)).equals(phoneNumber.getExtension())) {
                    return false;
                }
            }
            i++;
        }
        return true;
    }

    static boolean isNationalPrefixPresentIfRequired(PhoneNumber phoneNumber, PhoneNumberUtil phoneNumberUtil) {
        if (phoneNumber.getCountryCodeSource() != CountryCodeSource.FROM_DEFAULT_COUNTRY) {
            return true;
        }
        PhoneMetadata metadataForRegion = phoneNumberUtil.getMetadataForRegion(phoneNumberUtil.getRegionCodeForCountryCode(phoneNumber.getCountryCode()));
        if (metadataForRegion == null) {
            return true;
        }
        NumberFormat chooseFormattingPatternForNumber = phoneNumberUtil.chooseFormattingPatternForNumber(metadataForRegion.numberFormats(), phoneNumberUtil.getNationalSignificantNumber(phoneNumber));
        if (chooseFormattingPatternForNumber == null || chooseFormattingPatternForNumber.getNationalPrefixFormattingRule().length() <= 0 || chooseFormattingPatternForNumber.isNationalPrefixOptionalWhenFormatting() || PhoneNumberUtil.formattingRuleHasFirstGroupOnly(chooseFormattingPatternForNumber.getNationalPrefixFormattingRule())) {
            return true;
        }
        return phoneNumberUtil.maybeStripNationalPrefixAndCarrierCode(new StringBuilder(PhoneNumberUtil.normalizeDigitsOnly(phoneNumber.getRawInput())), metadataForRegion, null);
    }

    public boolean hasNext() {
        if (this.state == State.NOT_READY) {
            this.lastMatch = find(this.searchIndex);
            if (this.lastMatch == null) {
                this.state = State.DONE;
            } else {
                this.searchIndex = this.lastMatch.end();
                this.state = State.READY;
            }
        }
        return this.state == State.READY;
    }

    public PhoneNumberMatch next() {
        if (hasNext()) {
            PhoneNumberMatch phoneNumberMatch = this.lastMatch;
            this.lastMatch = null;
            this.state = State.NOT_READY;
            return phoneNumberMatch;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
