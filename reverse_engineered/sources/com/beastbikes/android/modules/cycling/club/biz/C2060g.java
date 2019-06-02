package com.beastbikes.android.modules.cycling.club.biz;

import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.modules.cycling.club.dto.ImageInfo;
import com.beastbikes.android.modules.p062c.C1880a;
import com.beastbikes.android.modules.p062c.C1882d;
import java.io.File;

/* compiled from: ImageUploader */
/* renamed from: com.beastbikes.android.modules.cycling.club.biz.g */
public class C2060g {
    /* renamed from: a */
    private boolean f9353a = true;
    /* renamed from: b */
    private boolean f9354b = false;
    /* renamed from: c */
    private ImageInfo f9355c;
    /* renamed from: d */
    private C1880a f9356d;
    /* renamed from: e */
    private File f9357e;
    /* renamed from: f */
    private String f9358f = "";
    /* renamed from: g */
    private String f9359g = "";
    /* renamed from: h */
    private String f9360h;

    /* compiled from: ImageUploader */
    /* renamed from: com.beastbikes.android.modules.cycling.club.biz.g$1 */
    class C20581 implements C1882d {
        /* renamed from: a */
        final /* synthetic */ C2060g f9351a;

        C20581(C2060g c2060g) {
            this.f9351a = c2060g;
        }

        /* renamed from: a */
        public void mo3362a(String str) {
            this.f9351a.f9353a = false;
            this.f9351a.f9355c.setUrl(str);
        }

        /* renamed from: a */
        public void mo3361a() {
            this.f9351a.f9353a = false;
        }
    }

    /* compiled from: ImageUploader */
    /* renamed from: com.beastbikes.android.modules.cycling.club.biz.g$2 */
    class C20592 implements C1882d {
        /* renamed from: a */
        final /* synthetic */ C2060g f9352a;

        C20592(C2060g c2060g) {
            this.f9352a = c2060g;
        }

        /* renamed from: a */
        public void mo3362a(String str) {
            this.f9352a.f9353a = false;
            this.f9352a.f9355c.setUrl(str);
        }

        /* renamed from: a */
        public void mo3361a() {
            this.f9352a.f9353a = false;
        }
    }

    /* renamed from: a */
    public C2060g m10617a(File file, String str, int i) {
        this.f9357e = file;
        this.f9360h = str;
        this.f9353a = true;
        this.f9354b = false;
        this.f9356d = new C1880a(BeastBikes.j());
        this.f9355c = new ImageInfo();
        this.f9355c.setId(str);
        switch (i) {
            case 0:
                this.f9358f = this.f9356d.m9741b() + str;
                break;
            case 3:
                this.f9358f = this.f9356d.m9744e() + str;
                break;
            case 4:
                this.f9358f = this.f9356d.m9745f() + str;
                break;
            case 5:
                this.f9358f = this.f9356d.m9746g() + str;
                break;
        }
        return this;
    }

    /* renamed from: a */
    public ImageInfo m10618a() {
        while (this.f9353a) {
            if (!this.f9354b) {
                this.f9354b = true;
                if (this.f9357e != null) {
                    this.f9356d.m9739a(this.f9358f, this.f9357e, this.f9358f, new C20581(this));
                } else if (this.f9359g != null) {
                    this.f9356d.m9740a(this.f9360h, this.f9359g, this.f9358f);
                    this.f9356d.m9737a(new C20592(this));
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.f9355c;
    }
}
