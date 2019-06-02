package jp.co.cyberagent.android.gpuimage.p205a;

import jp.co.cyberagent.android.gpuimage.Rotation;

/* compiled from: TextureRotationUtil */
/* renamed from: jp.co.cyberagent.android.gpuimage.a.a */
public class C5420a {
    /* renamed from: a */
    public static final float[] f17393a = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    /* renamed from: b */
    public static final float[] f17394b = new float[]{1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f};
    /* renamed from: c */
    public static final float[] f17395c = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    /* renamed from: d */
    public static final float[] f17396d = new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};

    /* renamed from: a */
    public static float[] m19484a(Rotation rotation, boolean z, boolean z2) {
        float[] fArr;
        switch (rotation) {
            case ROTATION_90:
                fArr = f17394b;
                break;
            case ROTATION_180:
                fArr = f17395c;
                break;
            case ROTATION_270:
                fArr = f17396d;
                break;
            default:
                fArr = f17393a;
                break;
        }
        float[] fArr2 = z ? new float[]{C5420a.m19483a(fArr[0]), fArr[1], C5420a.m19483a(fArr[2]), fArr[3], C5420a.m19483a(fArr[4]), fArr[5], C5420a.m19483a(fArr[6]), fArr[7]} : fArr;
        if (!z2) {
            return fArr2;
        }
        return new float[]{fArr2[0], C5420a.m19483a(fArr2[1]), fArr2[2], C5420a.m19483a(fArr2[3]), fArr2[4], C5420a.m19483a(fArr2[5]), fArr2[6], C5420a.m19483a(fArr2[7])};
    }

    /* renamed from: a */
    private static float m19483a(float f) {
        if (f == 0.0f) {
            return 1.0f;
        }
        return 0.0f;
    }
}
