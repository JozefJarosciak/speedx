package jp.co.cyberagent.android.gpuimage;

import android.graphics.Point;
import android.graphics.PointF;
import android.opengl.GLES20;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/* compiled from: GPUImageToneCurveFilter */
public class ba extends C5421v {
    /* renamed from: a */
    private int[] f17493a = new int[]{-1};
    /* renamed from: b */
    private int f17494b;
    /* renamed from: c */
    private PointF[] f17495c;
    /* renamed from: d */
    private PointF[] f17496d;
    /* renamed from: e */
    private PointF[] f17497e;
    /* renamed from: j */
    private PointF[] f17498j;
    /* renamed from: k */
    private ArrayList<Float> f17499k;
    /* renamed from: l */
    private ArrayList<Float> f17500l;
    /* renamed from: m */
    private ArrayList<Float> f17501m;
    /* renamed from: n */
    private ArrayList<Float> f17502n;

    /* compiled from: GPUImageToneCurveFilter */
    /* renamed from: jp.co.cyberagent.android.gpuimage.ba$1 */
    class C54301 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ ba f17491a;

        C54301(ba baVar) {
            this.f17491a = baVar;
        }

        public void run() {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.f17491a.f17493a[0]);
            if (this.f17491a.f17500l.size() >= 256 && this.f17491a.f17501m.size() >= 256 && this.f17491a.f17502n.size() >= 256 && this.f17491a.f17499k.size() >= 256) {
                byte[] bArr = new byte[1024];
                for (int i = 0; i < 256; i++) {
                    bArr[i * 4] = (byte) (((int) Math.min(Math.max(((Float) this.f17491a.f17499k.get(i)).floatValue() + (((float) i) + ((Float) this.f17491a.f17502n.get(i)).floatValue()), 0.0f), 255.0f)) & 255);
                    bArr[(i * 4) + 1] = (byte) (((int) Math.min(Math.max(((Float) this.f17491a.f17499k.get(i)).floatValue() + (((float) i) + ((Float) this.f17491a.f17501m.get(i)).floatValue()), 0.0f), 255.0f)) & 255);
                    bArr[(i * 4) + 2] = (byte) (((int) Math.min(Math.max(((Float) this.f17491a.f17499k.get(i)).floatValue() + (((float) i) + ((Float) this.f17491a.f17500l.get(i)).floatValue()), 0.0f), 255.0f)) & 255);
                    bArr[(i * 4) + 3] = (byte) -1;
                }
                GLES20.glTexImage2D(3553, 0, 6408, 256, 1, 0, 6408, 5121, ByteBuffer.wrap(bArr));
            }
        }
    }

    /* compiled from: GPUImageToneCurveFilter */
    /* renamed from: jp.co.cyberagent.android.gpuimage.ba$2 */
    class C54312 implements Comparator<PointF> {
        /* renamed from: a */
        final /* synthetic */ ba f17492a;

        C54312(ba baVar) {
            this.f17492a = baVar;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m19576a((PointF) obj, (PointF) obj2);
        }

        /* renamed from: a */
        public int m19576a(PointF pointF, PointF pointF2) {
            if (pointF.x < pointF2.x) {
                return -1;
            }
            if (pointF.x > pointF2.x) {
                return 1;
            }
            return 0;
        }
    }

    public ba() {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " varying highp vec2 textureCoordinate;\n uniform sampler2D inputImageTexture;\n uniform sampler2D toneCurveTexture;\n\n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     lowp float redCurveValue = texture2D(toneCurveTexture, vec2(textureColor.r, 0.0)).r;\n     lowp float greenCurveValue = texture2D(toneCurveTexture, vec2(textureColor.g, 0.0)).g;\n     lowp float blueCurveValue = texture2D(toneCurveTexture, vec2(textureColor.b, 0.0)).b;\n\n     gl_FragColor = vec4(redCurveValue, greenCurveValue, blueCurveValue, textureColor.a);\n }");
        PointF[] pointFArr = new PointF[]{new PointF(0.0f, 0.0f), new PointF(0.5f, 0.5f), new PointF(1.0f, 1.0f)};
        this.f17495c = pointFArr;
        this.f17496d = pointFArr;
        this.f17497e = pointFArr;
        this.f17498j = pointFArr;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17494b = GLES20.glGetUniformLocation(m19502k(), "toneCurveTexture");
        GLES20.glActiveTexture(33987);
        GLES20.glGenTextures(1, this.f17493a, 0);
        GLES20.glBindTexture(3553, this.f17493a[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
    }

    public void d_() {
        super.d_();
        m19589a(this.f17495c);
        m19590b(this.f17496d);
        m19592c(this.f17497e);
        m19593d(this.f17498j);
    }

    /* renamed from: c */
    protected void mo6687c() {
        if (this.f17493a[0] != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.f17493a[0]);
            GLES20.glUniform1i(this.f17494b, 3);
        }
    }

    /* renamed from: a */
    public void m19588a(InputStream inputStream) {
        try {
            m19581b(inputStream);
            short b = m19581b(inputStream);
            ArrayList arrayList = new ArrayList(b);
            for (short s = (short) 0; s < b; s++) {
                short b2 = m19581b(inputStream);
                Object obj = new PointF[b2];
                for (short s2 = (short) 0; s2 < b2; s2++) {
                    obj[s2] = new PointF(((float) m19581b(inputStream)) * 0.003921569f, ((float) m19581b(inputStream)) * 0.003921569f);
                }
                arrayList.add(obj);
            }
            inputStream.close();
            this.f17495c = (PointF[]) arrayList.get(0);
            this.f17496d = (PointF[]) arrayList.get(1);
            this.f17497e = (PointF[]) arrayList.get(2);
            this.f17498j = (PointF[]) arrayList.get(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    private short m19581b(InputStream inputStream) throws IOException {
        return (short) ((inputStream.read() << 8) | inputStream.read());
    }

    /* renamed from: a */
    public void m19589a(PointF[] pointFArr) {
        this.f17495c = pointFArr;
        this.f17499k = m19585e(this.f17495c);
        m19586l();
    }

    /* renamed from: b */
    public void m19590b(PointF[] pointFArr) {
        this.f17496d = pointFArr;
        this.f17500l = m19585e(this.f17496d);
        m19586l();
    }

    /* renamed from: c */
    public void m19592c(PointF[] pointFArr) {
        this.f17497e = pointFArr;
        this.f17501m = m19585e(this.f17497e);
        m19586l();
    }

    /* renamed from: d */
    public void m19593d(PointF[] pointFArr) {
        this.f17498j = pointFArr;
        this.f17502n = m19585e(this.f17498j);
        m19586l();
    }

    /* renamed from: l */
    private void m19586l() {
        m19491a(new C54301(this));
    }

    /* renamed from: e */
    private ArrayList<Float> m19585e(PointF[] pointFArr) {
        if (pointFArr == null || pointFArr.length <= 0) {
            return null;
        }
        int i;
        PointF[] pointFArr2 = (PointF[]) pointFArr.clone();
        Arrays.sort(pointFArr2, new C54312(this));
        Point[] pointArr = new Point[pointFArr2.length];
        for (int i2 = 0; i2 < pointFArr.length; i2++) {
            PointF pointF = pointFArr2[i2];
            pointArr[i2] = new Point((int) (pointF.x * 255.0f), (int) (pointF.y * 255.0f));
        }
        ArrayList a = m19577a(pointArr);
        Point point = (Point) a.get(0);
        if (point.x > 0) {
            for (i = point.x; i >= 0; i--) {
                a.add(0, new Point(i, 0));
            }
        }
        point = (Point) a.get(a.size() - 1);
        if (point.x < 255) {
            for (i = point.x + 1; i <= 255; i++) {
                a.add(new Point(i, 255));
            }
        }
        ArrayList<Float> arrayList = new ArrayList(a.size());
        Iterator it = a.iterator();
        while (it.hasNext()) {
            float f;
            point = (Point) it.next();
            Point point2 = new Point(point.x, point.x);
            float sqrt = (float) Math.sqrt(Math.pow((double) (point2.x - point.x), 2.0d) + Math.pow((double) (point2.y - point.y), 2.0d));
            if (point2.y > point.y) {
                f = -sqrt;
            } else {
                f = sqrt;
            }
            arrayList.add(Float.valueOf(f));
        }
        return arrayList;
    }

    /* renamed from: a */
    private ArrayList<Point> m19577a(Point[] pointArr) {
        ArrayList b = m19580b(pointArr);
        int size = b.size();
        if (size < 1) {
            return null;
        }
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((Double) b.get(i)).doubleValue();
        }
        ArrayList<Point> arrayList = new ArrayList(size + 1);
        for (int i2 = 0; i2 < size - 1; i2++) {
            Point point = pointArr[i2];
            Point point2 = pointArr[i2 + 1];
            for (int i3 = point.x; i3 < point2.x; i3++) {
                double d = ((double) (i3 - point.x)) / ((double) (point2.x - point.x));
                double d2 = 1.0d - d;
                double d3 = (double) (point2.x - point.x);
                d = ((((((d * d) * d) - d) * dArr[i2 + 1]) + ((((d2 * d2) * d2) - d2) * dArr[i2])) * ((d3 * d3) / 6.0d)) + ((((double) point.y) * d2) + (((double) point2.y) * d));
                if (d > 255.0d) {
                    d = 255.0d;
                } else if (d < 0.0d) {
                    d = 0.0d;
                }
                arrayList.add(new Point(i3, (int) Math.round(d)));
            }
        }
        if (arrayList.size() == 255) {
            arrayList.add(pointArr[pointArr.length - 1]);
        }
        return arrayList;
    }

    /* renamed from: b */
    private ArrayList<Double> m19580b(Point[] pointArr) {
        int length = pointArr.length;
        if (length <= 1) {
            return null;
        }
        int i;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, new int[]{length, 3});
        double[] dArr2 = new double[length];
        dArr[0][1] = 1.0d;
        dArr[0][0] = 0.0d;
        dArr[0][2] = 0.0d;
        for (i = 1; i < length - 1; i++) {
            Point point = pointArr[i - 1];
            Point point2 = pointArr[i];
            Point point3 = pointArr[i + 1];
            dArr[i][0] = ((double) (point2.x - point.x)) / 6.0d;
            dArr[i][1] = ((double) (point3.x - point.x)) / 3.0d;
            dArr[i][2] = ((double) (point3.x - point2.x)) / 6.0d;
            dArr2[i] = (((double) (point3.y - point2.y)) / ((double) (point3.x - point2.x))) - (((double) (point2.y - point.y)) / ((double) (point2.x - point.x)));
        }
        dArr2[0] = 0.0d;
        dArr2[length - 1] = 0.0d;
        dArr[length - 1][1] = 1.0d;
        dArr[length - 1][0] = 0.0d;
        dArr[length - 1][2] = 0.0d;
        for (i = 1; i < length; i++) {
            double d = dArr[i][0] / dArr[i - 1][1];
            double[] dArr3 = dArr[i];
            dArr3[1] = dArr3[1] - (dArr[i - 1][2] * d);
            dArr[i][0] = 0.0d;
            dArr2[i] = dArr2[i] - (d * dArr2[i - 1]);
        }
        for (i = length - 2; i >= 0; i--) {
            d = dArr[i][2] / dArr[i + 1][1];
            dArr3 = dArr[i];
            dArr3[1] = dArr3[1] - (dArr[i + 1][0] * d);
            dArr[i][2] = 0.0d;
            dArr2[i] = dArr2[i] - (d * dArr2[i + 1]);
        }
        ArrayList<Double> arrayList = new ArrayList(length);
        for (int i2 = 0; i2 < length; i2++) {
            arrayList.add(Double.valueOf(dArr2[i2] / dArr[i2][1]));
        }
        return arrayList;
    }
}
