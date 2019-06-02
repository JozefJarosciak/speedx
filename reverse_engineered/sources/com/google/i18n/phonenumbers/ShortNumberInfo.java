package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.Phonemetadata.PhoneMetadata;
import com.google.i18n.phonenumbers.Phonemetadata.PhoneNumberDesc;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.internal.MatcherApi;
import com.google.i18n.phonenumbers.internal.RegexBasedMatcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class ShortNumberInfo {
    private static final ShortNumberInfo INSTANCE = new ShortNumberInfo(RegexBasedMatcher.create());
    private static final Set<String> REGIONS_WHERE_EMERGENCY_NUMBERS_MUST_BE_EXACT = new HashSet();
    private static final Logger logger = Logger.getLogger(ShortNumberInfo.class.getName());
    private final Map<Integer, List<String>> countryCallingCodeToRegionCodeMap = CountryCodeToRegionCodeMap.getCountryCodeToRegionCodeMap();
    private final MatcherApi matcherApi;

    public enum ShortNumberCost {
        TOLL_FREE,
        STANDARD_RATE,
        PREMIUM_RATE,
        UNKNOWN_COST
    }

    static {
        REGIONS_WHERE_EMERGENCY_NUMBERS_MUST_BE_EXACT.add("BR");
        REGIONS_WHERE_EMERGENCY_NUMBERS_MUST_BE_EXACT.add("CL");
        REGIONS_WHERE_EMERGENCY_NUMBERS_MUST_BE_EXACT.add("NI");
    }

    public static ShortNumberInfo getInstance() {
        return INSTANCE;
    }

    ShortNumberInfo(MatcherApi matcherApi) {
        this.matcherApi = matcherApi;
    }

    private List<String> getRegionCodesForCountryCode(int i) {
        List list = (List) this.countryCallingCodeToRegionCodeMap.get(Integer.valueOf(i));
        if (list == null) {
            list = new ArrayList(0);
        }
        return Collections.unmodifiableList(list);
    }

    private boolean regionDialingFromMatchesNumber(PhoneNumber phoneNumber, String str) {
        return getRegionCodesForCountryCode(phoneNumber.getCountryCode()).contains(str);
    }

    public boolean isPossibleShortNumberForRegion(PhoneNumber phoneNumber, String str) {
        if (!regionDialingFromMatchesNumber(phoneNumber, str)) {
            return false;
        }
        PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str);
        if (shortNumberMetadataForRegion == null) {
            return false;
        }
        return shortNumberMetadataForRegion.getGeneralDesc().getPossibleLengthList().contains(Integer.valueOf(getNationalSignificantNumber(phoneNumber).length()));
    }

    public boolean isPossibleShortNumber(PhoneNumber phoneNumber) {
        List<String> regionCodesForCountryCode = getRegionCodesForCountryCode(phoneNumber.getCountryCode());
        int length = getNationalSignificantNumber(phoneNumber).length();
        for (String shortNumberMetadataForRegion : regionCodesForCountryCode) {
            PhoneMetadata shortNumberMetadataForRegion2 = MetadataManager.getShortNumberMetadataForRegion(shortNumberMetadataForRegion);
            if (shortNumberMetadataForRegion2 != null && shortNumberMetadataForRegion2.getGeneralDesc().getPossibleLengthList().contains(Integer.valueOf(length))) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidShortNumberForRegion(PhoneNumber phoneNumber, String str) {
        if (!regionDialingFromMatchesNumber(phoneNumber, str)) {
            return false;
        }
        PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str);
        if (shortNumberMetadataForRegion == null) {
            return false;
        }
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        if (matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.getGeneralDesc())) {
            return matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.getShortCode());
        }
        return false;
    }

    public boolean isValidShortNumber(PhoneNumber phoneNumber) {
        List regionCodesForCountryCode = getRegionCodesForCountryCode(phoneNumber.getCountryCode());
        String regionCodeForShortNumberFromRegionList = getRegionCodeForShortNumberFromRegionList(phoneNumber, regionCodesForCountryCode);
        if (regionCodesForCountryCode.size() <= 1 || regionCodeForShortNumberFromRegionList == null) {
            return isValidShortNumberForRegion(phoneNumber, regionCodeForShortNumberFromRegionList);
        }
        return true;
    }

    public ShortNumberCost getExpectedCostForRegion(PhoneNumber phoneNumber, String str) {
        if (!regionDialingFromMatchesNumber(phoneNumber, str)) {
            return ShortNumberCost.UNKNOWN_COST;
        }
        PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str);
        if (shortNumberMetadataForRegion == null) {
            return ShortNumberCost.UNKNOWN_COST;
        }
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        if (!shortNumberMetadataForRegion.getGeneralDesc().getPossibleLengthList().contains(Integer.valueOf(nationalSignificantNumber.length()))) {
            return ShortNumberCost.UNKNOWN_COST;
        }
        if (matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.getPremiumRate())) {
            return ShortNumberCost.PREMIUM_RATE;
        }
        if (matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.getStandardRate())) {
            return ShortNumberCost.STANDARD_RATE;
        }
        if (matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.getTollFree())) {
            return ShortNumberCost.TOLL_FREE;
        }
        if (isEmergencyNumber(nationalSignificantNumber, str)) {
            return ShortNumberCost.TOLL_FREE;
        }
        return ShortNumberCost.UNKNOWN_COST;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.i18n.phonenumbers.ShortNumberInfo.ShortNumberCost getExpectedCost(com.google.i18n.phonenumbers.Phonenumber.PhoneNumber r8) {
        /*
        r7 = this;
        r0 = r8.getCountryCode();
        r1 = r7.getRegionCodesForCountryCode(r0);
        r0 = r1.size();
        if (r0 != 0) goto L_0x0011;
    L_0x000e:
        r1 = com.google.i18n.phonenumbers.ShortNumberInfo.ShortNumberCost.UNKNOWN_COST;
    L_0x0010:
        return r1;
    L_0x0011:
        r0 = r1.size();
        r2 = 1;
        if (r0 != r2) goto L_0x0024;
    L_0x0018:
        r0 = 0;
        r0 = r1.get(r0);
        r0 = (java.lang.String) r0;
        r1 = r7.getExpectedCostForRegion(r8, r0);
        goto L_0x0010;
    L_0x0024:
        r0 = com.google.i18n.phonenumbers.ShortNumberInfo.ShortNumberCost.TOLL_FREE;
        r2 = r1.iterator();
        r1 = r0;
    L_0x002b:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x0010;
    L_0x0031:
        r0 = r2.next();
        r0 = (java.lang.String) r0;
        r0 = r7.getExpectedCostForRegion(r8, r0);
        r3 = com.google.i18n.phonenumbers.ShortNumberInfo.C40661.f14605xe1b2aad7;
        r4 = r0.ordinal();
        r3 = r3[r4];
        switch(r3) {
            case 1: goto L_0x0063;
            case 2: goto L_0x0066;
            case 3: goto L_0x006a;
            case 4: goto L_0x0072;
            default: goto L_0x0046;
        };
    L_0x0046:
        r3 = logger;
        r4 = java.util.logging.Level.SEVERE;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Unrecognised cost for region: ";
        r5 = r5.append(r6);
        r0 = r5.append(r0);
        r0 = r0.toString();
        r3.log(r4, r0);
    L_0x0060:
        r0 = r1;
    L_0x0061:
        r1 = r0;
        goto L_0x002b;
    L_0x0063:
        r1 = com.google.i18n.phonenumbers.ShortNumberInfo.ShortNumberCost.PREMIUM_RATE;
        goto L_0x0010;
    L_0x0066:
        r1 = com.google.i18n.phonenumbers.ShortNumberInfo.ShortNumberCost.UNKNOWN_COST;
        r0 = r1;
        goto L_0x0061;
    L_0x006a:
        r0 = com.google.i18n.phonenumbers.ShortNumberInfo.ShortNumberCost.UNKNOWN_COST;
        if (r1 == r0) goto L_0x0060;
    L_0x006e:
        r1 = com.google.i18n.phonenumbers.ShortNumberInfo.ShortNumberCost.STANDARD_RATE;
        r0 = r1;
        goto L_0x0061;
    L_0x0072:
        r0 = r1;
        goto L_0x0061;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.i18n.phonenumbers.ShortNumberInfo.getExpectedCost(com.google.i18n.phonenumbers.Phonenumber$PhoneNumber):com.google.i18n.phonenumbers.ShortNumberInfo$ShortNumberCost");
    }

    private String getRegionCodeForShortNumberFromRegionList(PhoneNumber phoneNumber, List<String> list) {
        if (list.size() == 0) {
            return null;
        }
        if (list.size() == 1) {
            return (String) list.get(0);
        }
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        for (String str : list) {
            PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str);
            if (shortNumberMetadataForRegion != null && matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.getShortCode())) {
                return str;
            }
        }
        return null;
    }

    Set<String> getSupportedRegions() {
        return MetadataManager.getSupportedShortNumberRegions();
    }

    String getExampleShortNumber(String str) {
        PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str);
        if (shortNumberMetadataForRegion == null) {
            return "";
        }
        PhoneNumberDesc shortCode = shortNumberMetadataForRegion.getShortCode();
        if (shortCode.hasExampleNumber()) {
            return shortCode.getExampleNumber();
        }
        return "";
    }

    String getExampleShortNumberForCost(String str, ShortNumberCost shortNumberCost) {
        PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str);
        if (shortNumberMetadataForRegion == null) {
            return "";
        }
        PhoneNumberDesc phoneNumberDesc = null;
        switch (shortNumberCost) {
            case PREMIUM_RATE:
                phoneNumberDesc = shortNumberMetadataForRegion.getPremiumRate();
                break;
            case STANDARD_RATE:
                phoneNumberDesc = shortNumberMetadataForRegion.getStandardRate();
                break;
            case TOLL_FREE:
                phoneNumberDesc = shortNumberMetadataForRegion.getTollFree();
                break;
        }
        if (phoneNumberDesc == null || !phoneNumberDesc.hasExampleNumber()) {
            return "";
        }
        return phoneNumberDesc.getExampleNumber();
    }

    public boolean connectsToEmergencyNumber(String str, String str2) {
        return matchesEmergencyNumberHelper(str, str2, true);
    }

    public boolean isEmergencyNumber(String str, String str2) {
        return matchesEmergencyNumberHelper(str, str2, false);
    }

    private boolean matchesEmergencyNumberHelper(String str, String str2, boolean z) {
        boolean z2 = false;
        Object extractPossibleNumber = PhoneNumberUtil.extractPossibleNumber(str);
        if (PhoneNumberUtil.PLUS_CHARS_PATTERN.matcher(extractPossibleNumber).lookingAt()) {
            return false;
        }
        PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str2);
        if (shortNumberMetadataForRegion == null || !shortNumberMetadataForRegion.hasEmergency()) {
            return false;
        }
        String normalizeDigitsOnly = PhoneNumberUtil.normalizeDigitsOnly(extractPossibleNumber);
        PhoneNumberDesc emergency = shortNumberMetadataForRegion.getEmergency();
        if (z && !REGIONS_WHERE_EMERGENCY_NUMBERS_MUST_BE_EXACT.contains(str2)) {
            z2 = true;
        }
        return this.matcherApi.matchesNationalNumber(normalizeDigitsOnly, emergency, z2);
    }

    public boolean isCarrierSpecific(PhoneNumber phoneNumber) {
        String regionCodeForShortNumberFromRegionList = getRegionCodeForShortNumberFromRegionList(phoneNumber, getRegionCodesForCountryCode(phoneNumber.getCountryCode()));
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(regionCodeForShortNumberFromRegionList);
        return shortNumberMetadataForRegion != null && matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.getCarrierSpecific());
    }

    public boolean isCarrierSpecificForRegion(PhoneNumber phoneNumber, String str) {
        if (!regionDialingFromMatchesNumber(phoneNumber, str)) {
            return false;
        }
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str);
        if (shortNumberMetadataForRegion == null || !matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.getCarrierSpecific())) {
            return false;
        }
        return true;
    }

    private static String getNationalSignificantNumber(PhoneNumber phoneNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        if (phoneNumber.isItalianLeadingZero()) {
            char[] cArr = new char[phoneNumber.getNumberOfLeadingZeros()];
            Arrays.fill(cArr, '0');
            stringBuilder.append(new String(cArr));
        }
        stringBuilder.append(phoneNumber.getNationalNumber());
        return stringBuilder.toString();
    }

    private boolean matchesPossibleNumberAndNationalNumber(String str, PhoneNumberDesc phoneNumberDesc) {
        if (phoneNumberDesc.getPossibleLengthCount() <= 0 || phoneNumberDesc.getPossibleLengthList().contains(Integer.valueOf(str.length()))) {
            return this.matcherApi.matchesNationalNumber(str, phoneNumberDesc, false);
        }
        return false;
    }
}
