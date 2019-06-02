package com.beastbikes.android.modules.user.dto;

import com.alipay.sdk.util.C0882j;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class HistogramDTO implements Serializable {
    private List<C2410a> items = new ArrayList();
    private double max;
    private int monthRank;

    /* renamed from: com.beastbikes.android.modules.user.dto.HistogramDTO$a */
    public class C2410a {
        /* renamed from: a */
        final /* synthetic */ HistogramDTO f11392a;
        /* renamed from: b */
        private long f11393b;
        /* renamed from: c */
        private int f11394c;
        /* renamed from: d */
        private String f11395d;
        /* renamed from: e */
        private double f11396e;
        /* renamed from: f */
        private boolean f11397f;

        public C2410a(HistogramDTO histogramDTO, JSONObject jSONObject) {
            this.f11392a = histogramDTO;
            this.f11393b = jSONObject.optLong("date");
            this.f11394c = jSONObject.optInt("color");
            this.f11395d = jSONObject.optString("description");
            this.f11396e = jSONObject.optDouble("value");
        }

        /* renamed from: a */
        public long m12217a() {
            return this.f11393b;
        }

        /* renamed from: b */
        public double m12219b() {
            return this.f11396e;
        }

        /* renamed from: c */
        public boolean m12220c() {
            return this.f11397f;
        }

        /* renamed from: a */
        public void m12218a(boolean z) {
            this.f11397f = z;
        }
    }

    public HistogramDTO(JSONObject jSONObject) {
        this.monthRank = jSONObject.optInt("monthRank");
        this.max = jSONObject.optDouble("max");
        JSONArray optJSONArray = jSONObject.optJSONArray(C0882j.f2229c);
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                this.max = Math.max(this.max, optJSONObject.optDouble("value"));
                C2410a c2410a = new C2410a(this, optJSONArray.optJSONObject(i));
                if (i == optJSONArray.length() - 1) {
                    c2410a.m12218a(true);
                } else {
                    c2410a.m12218a(false);
                }
                this.items.add(c2410a);
            }
        }
    }

    public int getMonthRank() {
        return this.monthRank;
    }

    public void setMonthRank(int i) {
        this.monthRank = i;
    }

    public double getMax() {
        return this.max;
    }

    public void setMax(int i) {
        this.max = (double) i;
    }

    public List<C2410a> getItems() {
        return this.items;
    }

    public void setItems(List<C2410a> list) {
        this.items = list;
    }
}
