package com.google.zxing.integration.android;

public final class IntentResult {
    private final String barcodeImagePath;
    private final String contents;
    private final String errorCorrectionLevel;
    private final String formatName;
    private final Integer orientation;
    private final byte[] rawBytes;

    IntentResult() {
        this(null, null, null, null, null, null);
    }

    IntentResult(String str, String str2, byte[] bArr, Integer num, String str3, String str4) {
        this.contents = str;
        this.formatName = str2;
        this.rawBytes = bArr;
        this.orientation = num;
        this.errorCorrectionLevel = str3;
        this.barcodeImagePath = str4;
    }

    public String getContents() {
        return this.contents;
    }

    public String getFormatName() {
        return this.formatName;
    }

    public byte[] getRawBytes() {
        return this.rawBytes;
    }

    public Integer getOrientation() {
        return this.orientation;
    }

    public String getErrorCorrectionLevel() {
        return this.errorCorrectionLevel;
    }

    public String getBarcodeImagePath() {
        return this.barcodeImagePath;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(120);
        stringBuilder.append("Format: ").append(this.formatName).append('\n');
        stringBuilder.append("Contents: ").append(this.contents).append('\n');
        stringBuilder.append("Raw bytes: (").append(this.rawBytes == null ? 0 : this.rawBytes.length).append(" bytes)\n");
        stringBuilder.append("Orientation: ").append(this.orientation).append('\n');
        stringBuilder.append("EC level: ").append(this.errorCorrectionLevel).append('\n');
        stringBuilder.append("Barcode image: ").append(this.barcodeImagePath).append('\n');
        return stringBuilder.toString();
    }
}
