package com.twitter.sdk.android.core.services.params;

public class Geocode {
    /* renamed from: a */
    public final double f16414a;
    /* renamed from: b */
    public final double f16415b;
    /* renamed from: c */
    public final int f16416c;
    /* renamed from: d */
    public final Distance f16417d;

    public enum Distance {
        MILES("mi"),
        KILOMETERS("km");
        
        public final String identifier;

        private Distance(String str) {
            this.identifier = str;
        }
    }

    public String toString() {
        return this.f16414a + "," + this.f16415b + "," + this.f16416c + this.f16417d.identifier;
    }
}
