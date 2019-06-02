package com.journeyapps.barcodescanner;

import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import java.util.Map;

/* compiled from: BarcodeResult */
/* renamed from: com.journeyapps.barcodescanner.b */
public class C4121b {
    /* renamed from: a */
    protected Result f14696a;
    /* renamed from: b */
    protected C4169m f14697b;
    /* renamed from: c */
    private final int f14698c = 2;

    public C4121b(Result result, C4169m c4169m) {
        this.f14696a = result;
        this.f14697b = c4169m;
    }

    /* renamed from: a */
    public Bitmap m16542a() {
        return this.f14697b.m16702a(2);
    }

    /* renamed from: b */
    public String m16543b() {
        return this.f14696a.getText();
    }

    /* renamed from: c */
    public byte[] m16544c() {
        return this.f14696a.getRawBytes();
    }

    /* renamed from: d */
    public BarcodeFormat m16545d() {
        return this.f14696a.getBarcodeFormat();
    }

    /* renamed from: e */
    public Map<ResultMetadataType, Object> m16546e() {
        return this.f14696a.getResultMetadata();
    }

    public String toString() {
        return this.f14696a.getText();
    }
}
