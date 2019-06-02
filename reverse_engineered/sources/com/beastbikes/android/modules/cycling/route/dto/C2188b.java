package com.beastbikes.android.modules.cycling.route.dto;

import com.google.android.gms.location.places.Place;

/* compiled from: GooglePlaceAddressDTO */
/* renamed from: com.beastbikes.android.modules.cycling.route.dto.b */
public class C2188b {
    /* renamed from: a */
    private String f10265a;
    /* renamed from: b */
    private String f10266b;
    /* renamed from: c */
    private String f10267c;
    /* renamed from: d */
    private double f10268d;
    /* renamed from: e */
    private double f10269e;
    /* renamed from: f */
    private String f10270f;

    public C2188b(Place place) {
        this.f10265a = place.getId();
        this.f10266b = place.getName().toString();
        this.f10267c = place.getAddress().toString();
        this.f10268d = place.getLatLng().latitude;
        this.f10269e = place.getLatLng().longitude;
    }

    /* renamed from: a */
    public String m11215a() {
        return this.f10266b;
    }

    /* renamed from: b */
    public String m11217b() {
        return this.f10267c;
    }

    /* renamed from: c */
    public double m11218c() {
        return this.f10268d;
    }

    /* renamed from: d */
    public double m11219d() {
        return this.f10269e;
    }

    /* renamed from: e */
    public String m11220e() {
        return this.f10270f;
    }

    /* renamed from: a */
    public void m11216a(String str) {
        this.f10270f = str;
    }
}
