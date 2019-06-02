package com.google.zxing.client.android;

import android.content.Intent;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.android.Intents.Scan;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public final class DecodeFormatManager {
    static final Set<BarcodeFormat> AZTEC_FORMATS = EnumSet.of(BarcodeFormat.AZTEC);
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    static final Set<BarcodeFormat> DATA_MATRIX_FORMATS = EnumSet.of(BarcodeFormat.DATA_MATRIX);
    private static final Map<String, Set<BarcodeFormat>> FORMATS_FOR_MODE = new HashMap();
    static final Set<BarcodeFormat> INDUSTRIAL_FORMATS = EnumSet.of(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.CODABAR);
    private static final Set<BarcodeFormat> ONE_D_FORMATS = EnumSet.copyOf(PRODUCT_FORMATS);
    static final Set<BarcodeFormat> PDF417_FORMATS = EnumSet.of(BarcodeFormat.PDF_417);
    static final Set<BarcodeFormat> PRODUCT_FORMATS = EnumSet.of(BarcodeFormat.UPC_A, new BarcodeFormat[]{BarcodeFormat.UPC_E, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8, BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED});
    static final Set<BarcodeFormat> QR_CODE_FORMATS = EnumSet.of(BarcodeFormat.QR_CODE);

    static {
        ONE_D_FORMATS.addAll(INDUSTRIAL_FORMATS);
        FORMATS_FOR_MODE.put(Scan.ONE_D_MODE, ONE_D_FORMATS);
        FORMATS_FOR_MODE.put(Scan.PRODUCT_MODE, PRODUCT_FORMATS);
        FORMATS_FOR_MODE.put(Scan.QR_CODE_MODE, QR_CODE_FORMATS);
        FORMATS_FOR_MODE.put(Scan.DATA_MATRIX_MODE, DATA_MATRIX_FORMATS);
        FORMATS_FOR_MODE.put(Scan.AZTEC_MODE, AZTEC_FORMATS);
        FORMATS_FOR_MODE.put(Scan.PDF417_MODE, PDF417_FORMATS);
    }

    private DecodeFormatManager() {
    }

    public static Set<BarcodeFormat> parseDecodeFormats(Intent intent) {
        Iterable iterable = null;
        CharSequence stringExtra = intent.getStringExtra(Scan.FORMATS);
        if (stringExtra != null) {
            iterable = Arrays.asList(COMMA_PATTERN.split(stringExtra));
        }
        return parseDecodeFormats(iterable, intent.getStringExtra(Scan.MODE));
    }

    private static Set<BarcodeFormat> parseDecodeFormats(Iterable<String> iterable, String str) {
        if (iterable != null) {
            Set<BarcodeFormat> noneOf = EnumSet.noneOf(BarcodeFormat.class);
            try {
                for (String valueOf : iterable) {
                    noneOf.add(BarcodeFormat.valueOf(valueOf));
                }
                return noneOf;
            } catch (IllegalArgumentException e) {
            }
        }
        if (str != null) {
            return (Set) FORMATS_FOR_MODE.get(str);
        }
        return null;
    }
}
