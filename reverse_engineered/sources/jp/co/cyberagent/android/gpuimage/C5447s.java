package jp.co.cyberagent.android.gpuimage;

/* compiled from: GPUImageEmbossFilter */
/* renamed from: jp.co.cyberagent.android.gpuimage.s */
public class C5447s extends C5423a {
    /* renamed from: a */
    private float f17539a;

    public C5447s() {
        this(1.0f);
    }

    public C5447s(float f) {
        this.f17539a = f;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        m19620b(this.f17539a);
    }

    /* renamed from: b */
    public void m19620b(float f) {
        this.f17539a = f;
        m19508a(new float[]{-2.0f * f, -f, 0.0f, -f, 1.0f, f, 0.0f, f, 2.0f * f});
    }
}
