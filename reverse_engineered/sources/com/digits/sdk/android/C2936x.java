package com.digits.sdk.android;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

/* compiled from: CountryListAdapter */
/* renamed from: com.digits.sdk.android.x */
class C2936x extends ArrayAdapter<C2935w> implements SectionIndexer {
    /* renamed from: a */
    private final HashMap<String, Integer> f13353a = new LinkedHashMap();
    /* renamed from: b */
    private final HashMap<String, Integer> f13354b = new LinkedHashMap();
    /* renamed from: c */
    private String[] f13355c;

    public C2936x(Context context) {
        super(context, C2876R.layout.dgts__country_row, 16908308);
    }

    /* renamed from: a */
    public void m14261a(List<C2935w> list) {
        int i = 0;
        for (C2935w c2935w : list) {
            String toUpperCase = c2935w.f13350a.substring(0, 1).toUpperCase(Locale.getDefault());
            if (!this.f13353a.containsKey(toUpperCase)) {
                this.f13353a.put(toUpperCase, Integer.valueOf(i));
            }
            this.f13354b.put(c2935w.f13350a, Integer.valueOf(i));
            i++;
            add(c2935w);
        }
        this.f13355c = new String[this.f13353a.size()];
        this.f13353a.keySet().toArray(this.f13355c);
        notifyDataSetChanged();
    }

    public Object[] getSections() {
        return this.f13355c;
    }

    public int getPositionForSection(int i) {
        if (this.f13355c == null || i <= 0) {
            return 0;
        }
        if (i >= this.f13355c.length) {
            i = this.f13355c.length - 1;
        }
        return ((Integer) this.f13353a.get(this.f13355c[i])).intValue();
    }

    public int getSectionForPosition(int i) {
        return 0;
    }

    /* renamed from: a */
    public int m14260a(String str) {
        Integer num = (Integer) this.f13354b.get(str);
        return num == null ? 0 : num.intValue();
    }
}
