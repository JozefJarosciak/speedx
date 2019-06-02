package com.beastbikes.android.modules.cycling.club.dto;

import ch.qos.logback.core.CoreConstants;
import java.util.List;

/* compiled from: ClubActivityMember */
/* renamed from: com.beastbikes.android.modules.cycling.club.dto.b */
public class C2063b {
    /* renamed from: a */
    private List<ClubActivityUser> f9366a;
    /* renamed from: b */
    private C2064c f9367b;
    /* renamed from: c */
    private boolean f9368c;
    /* renamed from: d */
    private String f9369d;
    /* renamed from: e */
    private int f9370e;

    /* renamed from: a */
    public List<ClubActivityUser> m10625a() {
        return this.f9366a;
    }

    /* renamed from: a */
    public void m10629a(List<ClubActivityUser> list) {
        this.f9366a = list;
    }

    /* renamed from: a */
    public void m10626a(int i) {
        this.f9370e = i;
    }

    /* renamed from: a */
    public void m10628a(String str) {
        this.f9369d = str;
    }

    /* renamed from: a */
    public void m10630a(boolean z) {
        this.f9368c = z;
    }

    /* renamed from: a */
    public void m10627a(C2064c c2064c) {
        this.f9367b = c2064c;
    }

    public String toString() {
        return "ClubActivityMember{clubActivityUsers=" + this.f9366a + ", clubActivityOriginator=" + this.f9367b + ", isManager=" + this.f9368c + ", actId='" + this.f9369d + CoreConstants.SINGLE_QUOTE_CHAR + CoreConstants.CURLY_RIGHT;
    }
}
