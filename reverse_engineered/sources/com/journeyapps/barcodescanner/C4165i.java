package com.journeyapps.barcodescanner;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

/* compiled from: DefaultDecoderFactory */
/* renamed from: com.journeyapps.barcodescanner.i */
public class C4165i implements C4160f {
    /* renamed from: a */
    private Collection<BarcodeFormat> f14820a;
    /* renamed from: b */
    private Map<DecodeHintType, ?> f14821b;
    /* renamed from: c */
    private String f14822c;

    public C4165i(Collection<BarcodeFormat> collection, Map<DecodeHintType, ?> map, String str) {
        this.f14820a = collection;
        this.f14821b = map;
        this.f14822c = str;
    }

    /* renamed from: a */
    public C4159e mo5934a(Map<DecodeHintType, ?> map) {
        Map enumMap = new EnumMap(DecodeHintType.class);
        enumMap.putAll(map);
        if (this.f14821b != null) {
            enumMap.putAll(this.f14821b);
        }
        if (this.f14820a != null) {
            enumMap.put(DecodeHintType.POSSIBLE_FORMATS, this.f14820a);
        }
        if (this.f14822c != null) {
            enumMap.put(DecodeHintType.CHARACTER_SET, this.f14822c);
        }
        Reader multiFormatReader = new MultiFormatReader();
        multiFormatReader.setHints(enumMap);
        return new C4159e(multiFormatReader);
    }
}
