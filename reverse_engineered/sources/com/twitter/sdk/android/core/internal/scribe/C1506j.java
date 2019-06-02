package com.twitter.sdk.android.core.internal.scribe;

import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.models.C1513g;

/* compiled from: ScribeItem */
/* renamed from: com.twitter.sdk.android.core.internal.scribe.j */
public class C1506j {
    @SerializedName("item_type")
    /* renamed from: a */
    public final Integer f7072a;
    @SerializedName("id")
    /* renamed from: b */
    public final Long f7073b;
    @SerializedName("description")
    /* renamed from: c */
    public final String f7074c;
    @SerializedName("card_event")
    /* renamed from: d */
    public final C1504b f7075d;
    @SerializedName("media_details")
    /* renamed from: e */
    public final C1505c f7076e;

    /* compiled from: ScribeItem */
    /* renamed from: com.twitter.sdk.android.core.internal.scribe.j$b */
    public static class C1504b {
        @SerializedName("promotion_card_type")
        /* renamed from: a */
        final int f7068a;

        public C1504b(int i) {
            this.f7068a = i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            if (this.f7068a != ((C1504b) obj).f7068a) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.f7068a;
        }
    }

    /* compiled from: ScribeItem */
    /* renamed from: com.twitter.sdk.android.core.internal.scribe.j$c */
    public static class C1505c {
        @SerializedName("content_id")
        /* renamed from: a */
        public final long f7069a;
        @SerializedName("media_type")
        /* renamed from: b */
        public final int f7070b;
        @SerializedName("publisher_id")
        /* renamed from: c */
        public final long f7071c;

        public C1505c(long j, int i, long j2) {
            this.f7069a = j;
            this.f7070b = i;
            this.f7071c = j2;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C1505c c1505c = (C1505c) obj;
            if (this.f7069a != c1505c.f7069a || this.f7070b != c1505c.f7070b) {
                return false;
            }
            if (this.f7071c != c1505c.f7071c) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return (((((int) (this.f7069a ^ (this.f7069a >>> 32))) * 31) + this.f7070b) * 31) + ((int) (this.f7071c ^ (this.f7071c >>> 32)));
        }
    }

    private C1506j(Integer num, Long l, String str, C1504b c1504b, C1505c c1505c) {
        this.f7072a = num;
        this.f7073b = l;
        this.f7074c = str;
        this.f7075d = c1504b;
        this.f7076e = c1505c;
    }

    /* renamed from: a */
    public static C1506j m8307a(C1513g c1513g) {
        return new j$a().a(0).a(c1513g.f7102i).a();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        if (r4 != r5) goto L_0x0006;
    L_0x0004:
        r1 = r0;
    L_0x0005:
        return r1;
    L_0x0006:
        if (r5 == 0) goto L_0x0005;
    L_0x0008:
        r2 = r4.getClass();
        r3 = r5.getClass();
        if (r2 != r3) goto L_0x0005;
    L_0x0012:
        r5 = (com.twitter.sdk.android.core.internal.scribe.C1506j) r5;
        r2 = r4.f7072a;
        if (r2 == 0) goto L_0x005d;
    L_0x0018:
        r2 = r4.f7072a;
        r3 = r5.f7072a;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0022:
        r2 = r4.f7073b;
        if (r2 == 0) goto L_0x0062;
    L_0x0026:
        r2 = r4.f7073b;
        r3 = r5.f7073b;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0030:
        r2 = r4.f7074c;
        if (r2 == 0) goto L_0x0067;
    L_0x0034:
        r2 = r4.f7074c;
        r3 = r5.f7074c;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x003e:
        r2 = r4.f7075d;
        if (r2 == 0) goto L_0x006c;
    L_0x0042:
        r2 = r4.f7075d;
        r3 = r5.f7075d;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x004c:
        r2 = r4.f7076e;
        if (r2 == 0) goto L_0x0071;
    L_0x0050:
        r2 = r4.f7076e;
        r3 = r5.f7076e;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x005b;
    L_0x005a:
        r0 = r1;
    L_0x005b:
        r1 = r0;
        goto L_0x0005;
    L_0x005d:
        r2 = r5.f7072a;
        if (r2 == 0) goto L_0x0022;
    L_0x0061:
        goto L_0x0005;
    L_0x0062:
        r2 = r5.f7073b;
        if (r2 == 0) goto L_0x0030;
    L_0x0066:
        goto L_0x0005;
    L_0x0067:
        r2 = r5.f7074c;
        if (r2 == 0) goto L_0x003e;
    L_0x006b:
        goto L_0x0005;
    L_0x006c:
        r2 = r5.f7075d;
        if (r2 == 0) goto L_0x004c;
    L_0x0070:
        goto L_0x0005;
    L_0x0071:
        r2 = r5.f7076e;
        if (r2 != 0) goto L_0x005a;
    L_0x0075:
        goto L_0x005b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.twitter.sdk.android.core.internal.scribe.j.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = (this.f7072a != null ? this.f7072a.hashCode() : 0) * 31;
        if (this.f7073b != null) {
            hashCode = this.f7073b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.f7074c != null) {
            hashCode = this.f7074c.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.f7075d != null) {
            hashCode = this.f7075d.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.f7076e != null) {
            i = this.f7076e.hashCode();
        }
        return hashCode + i;
    }
}
