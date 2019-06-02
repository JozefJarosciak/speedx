package com.twitter;

import com.twitter.Extractor.Entity;
import java.text.Normalizer;
import java.text.Normalizer.Form;

/* compiled from: Validator */
/* renamed from: com.twitter.c */
public class C4571c {
    /* renamed from: a */
    protected int f16199a = 23;
    /* renamed from: b */
    protected int f16200b = 23;
    /* renamed from: c */
    private Extractor f16201c = new Extractor();

    /* renamed from: a */
    public int m18130a(String str) {
        String normalize = Normalizer.normalize(str, Form.NFC);
        int codePointCount = normalize.codePointCount(0, normalize.length());
        int i = codePointCount;
        for (Entity entity : this.f16201c.a(normalize)) {
            i = (entity.f16175c.toLowerCase().startsWith("https://") ? this.f16200b : this.f16199a) + (i + (entity.f16173a - entity.f16174b));
        }
        return i;
    }
}
