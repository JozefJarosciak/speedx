package com.journeyapps.barcodescanner;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.HybridBinarizer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Decoder */
/* renamed from: com.journeyapps.barcodescanner.e */
public class C4159e implements ResultPointCallback {
    /* renamed from: a */
    private Reader f14804a;
    /* renamed from: b */
    private List<ResultPoint> f14805b = new ArrayList();

    public C4159e(Reader reader) {
        this.f14804a = reader;
    }

    /* renamed from: a */
    public Result m16669a(LuminanceSource luminanceSource) {
        return m16668a(m16671b(luminanceSource));
    }

    /* renamed from: b */
    protected BinaryBitmap m16671b(LuminanceSource luminanceSource) {
        return new BinaryBitmap(new HybridBinarizer(luminanceSource));
    }

    /* renamed from: a */
    protected Result m16668a(BinaryBitmap binaryBitmap) {
        this.f14805b.clear();
        Result decodeWithState;
        try {
            if (this.f14804a instanceof MultiFormatReader) {
                decodeWithState = ((MultiFormatReader) this.f14804a).decodeWithState(binaryBitmap);
                return decodeWithState;
            }
            decodeWithState = this.f14804a.decode(binaryBitmap);
            this.f14804a.reset();
            return decodeWithState;
        } catch (Exception e) {
            decodeWithState = null;
        } finally {
            this.f14804a.reset();
        }
    }

    /* renamed from: a */
    public List<ResultPoint> m16670a() {
        return new ArrayList(this.f14805b);
    }

    public void foundPossibleResultPoint(ResultPoint resultPoint) {
        this.f14805b.add(resultPoint);
    }
}
