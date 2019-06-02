package com.github.mikephil.charting.p183g;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.github.mikephil.charting.p183g.C3280f.C3273a;

/* compiled from: MPPointF */
/* renamed from: com.github.mikephil.charting.g.e */
public class C3279e extends C3273a {
    /* renamed from: c */
    public static final Creator<C3279e> f14198c = new C32781();
    /* renamed from: f */
    private static C3280f<C3279e> f14199f = C3280f.m15903a(32, new C3279e(0.0f, 0.0f));
    /* renamed from: a */
    public float f14200a;
    /* renamed from: b */
    public float f14201b;

    /* compiled from: MPPointF */
    /* renamed from: com.github.mikephil.charting.g.e$1 */
    static class C32781 implements Creator<C3279e> {
        C32781() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15895a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15896a(i);
        }

        /* renamed from: a */
        public C3279e m15895a(Parcel parcel) {
            C3279e c3279e = new C3279e(0.0f, 0.0f);
            c3279e.m15902a(parcel);
            return c3279e;
        }

        /* renamed from: a */
        public C3279e[] m15896a(int i) {
            return new C3279e[i];
        }
    }

    static {
        f14199f.m15908a(0.5f);
    }

    public C3279e(float f, float f2) {
        this.f14200a = f;
        this.f14201b = f2;
    }

    /* renamed from: a */
    public static C3279e m15897a(float f, float f2) {
        C3279e c3279e = (C3279e) f14199f.m15907a();
        c3279e.f14200a = f;
        c3279e.f14201b = f2;
        return c3279e;
    }

    /* renamed from: b */
    public static C3279e m15899b() {
        return (C3279e) f14199f.m15907a();
    }

    /* renamed from: a */
    public static C3279e m15898a(C3279e c3279e) {
        C3279e c3279e2 = (C3279e) f14199f.m15907a();
        c3279e2.f14200a = c3279e.f14200a;
        c3279e2.f14201b = c3279e.f14201b;
        return c3279e2;
    }

    /* renamed from: b */
    public static void m15900b(C3279e c3279e) {
        f14199f.m15909a((C3273a) c3279e);
    }

    /* renamed from: a */
    public void m15902a(Parcel parcel) {
        this.f14200a = parcel.readFloat();
        this.f14201b = parcel.readFloat();
    }

    /* renamed from: a */
    protected C3273a mo4018a() {
        return new C3279e(0.0f, 0.0f);
    }
}
