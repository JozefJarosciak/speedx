package okio;

import android.support.v4.media.session.PlaybackStateCompat;

/* compiled from: SegmentPool */
/* renamed from: okio.q */
final class C5652q {
    /* renamed from: a */
    static C5651p f18250a;
    /* renamed from: b */
    static long f18251b;

    private C5652q() {
    }

    /* renamed from: a */
    static C5651p m20762a() {
        synchronized (C5652q.class) {
            if (f18250a != null) {
                C5651p c5651p = f18250a;
                f18250a = c5651p.f18248f;
                c5651p.f18248f = null;
                f18251b -= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                return c5651p;
            }
            return new C5651p();
        }
    }

    /* renamed from: a */
    static void m20763a(C5651p c5651p) {
        if (c5651p.f18248f != null || c5651p.f18249g != null) {
            throw new IllegalArgumentException();
        } else if (!c5651p.f18246d) {
            synchronized (C5652q.class) {
                if (f18251b + PlaybackStateCompat.ACTION_PLAY_FROM_URI > PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                    return;
                }
                f18251b += PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                c5651p.f18248f = f18250a;
                c5651p.f18245c = 0;
                c5651p.f18244b = 0;
                f18250a = c5651p;
            }
        }
    }
}
