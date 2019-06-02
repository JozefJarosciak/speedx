package com.journeyapps.barcodescanner;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.google.zxing.client.android.C4087R;
import com.journeyapps.barcodescanner.camera.C4139b;
import com.journeyapps.barcodescanner.camera.C4142d;
import com.journeyapps.barcodescanner.camera.C4144k;
import com.journeyapps.barcodescanner.camera.C4145f;
import com.journeyapps.barcodescanner.camera.C4146g;
import com.journeyapps.barcodescanner.camera.C4147h;
import com.journeyapps.barcodescanner.camera.C4148i;
import com.journeyapps.barcodescanner.camera.CameraSettings;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CameraPreview */
/* renamed from: com.journeyapps.barcodescanner.c */
public class C4116c extends ViewGroup {
    /* renamed from: a */
    private static final String f14654a = C4116c.class.getSimpleName();
    /* renamed from: A */
    private final C4119a f14655A = new C41285(this);
    /* renamed from: b */
    private C4139b f14656b;
    /* renamed from: c */
    private WindowManager f14657c;
    /* renamed from: d */
    private Handler f14658d;
    /* renamed from: e */
    private boolean f14659e = false;
    /* renamed from: f */
    private SurfaceView f14660f;
    /* renamed from: g */
    private TextureView f14661g;
    /* renamed from: h */
    private boolean f14662h = false;
    /* renamed from: i */
    private C4167k f14663i;
    /* renamed from: j */
    private int f14664j = -1;
    /* renamed from: k */
    private List<C4119a> f14665k = new ArrayList();
    /* renamed from: l */
    private C4146g f14666l;
    /* renamed from: m */
    private CameraSettings f14667m = new CameraSettings();
    /* renamed from: n */
    private C4168l f14668n;
    /* renamed from: o */
    private C4168l f14669o;
    /* renamed from: p */
    private Rect f14670p;
    /* renamed from: q */
    private C4168l f14671q;
    /* renamed from: r */
    private Rect f14672r = null;
    /* renamed from: s */
    private Rect f14673s = null;
    /* renamed from: t */
    private C4168l f14674t = null;
    /* renamed from: u */
    private double f14675u = 0.1d;
    /* renamed from: v */
    private C4144k f14676v = null;
    /* renamed from: w */
    private boolean f14677w = false;
    /* renamed from: x */
    private final Callback f14678x = new C41232(this);
    /* renamed from: y */
    private final Handler.Callback f14679y = new C41243(this);
    /* renamed from: z */
    private C4126j f14680z = new C41274(this);

    /* compiled from: CameraPreview */
    /* renamed from: com.journeyapps.barcodescanner.c$a */
    public interface C4119a {
        /* renamed from: a */
        void mo5925a();

        /* renamed from: a */
        void mo5926a(Exception exception);

        /* renamed from: b */
        void mo5927b();

        /* renamed from: c */
        void mo5928c();
    }

    /* compiled from: CameraPreview */
    /* renamed from: com.journeyapps.barcodescanner.c$1 */
    class C41221 implements SurfaceTextureListener {
        /* renamed from: a */
        final /* synthetic */ C4116c f14699a;

        C41221(C4116c c4116c) {
            this.f14699a = c4116c;
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            onSurfaceTextureSizeChanged(surfaceTexture, i, i2);
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            this.f14699a.f14671q = new C4168l(i, i2);
            this.f14699a.m16495l();
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return false;
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    /* compiled from: CameraPreview */
    /* renamed from: com.journeyapps.barcodescanner.c$2 */
    class C41232 implements Callback {
        /* renamed from: a */
        final /* synthetic */ C4116c f14700a;

        C41232(C4116c c4116c) {
            this.f14700a = c4116c;
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            this.f14700a.f14671q = null;
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (surfaceHolder == null) {
                Log.e(C4116c.f14654a, "*** WARNING *** surfaceChanged() gave us a null surface!");
                return;
            }
            this.f14700a.f14671q = new C4168l(i2, i3);
            this.f14700a.m16495l();
        }
    }

    /* compiled from: CameraPreview */
    /* renamed from: com.journeyapps.barcodescanner.c$3 */
    class C41243 implements Handler.Callback {
        /* renamed from: a */
        final /* synthetic */ C4116c f14701a;

        C41243(C4116c c4116c) {
            this.f14701a = c4116c;
        }

        public boolean handleMessage(Message message) {
            if (message.what == C4087R.id.zxing_prewiew_size_ready) {
                this.f14701a.m16488b((C4168l) message.obj);
                return true;
            }
            if (message.what == C4087R.id.zxing_camera_error) {
                Exception exception = (Exception) message.obj;
                if (this.f14701a.m16504f()) {
                    this.f14701a.mo5924d();
                    this.f14701a.f14655A.mo5926a(exception);
                }
            }
            return false;
        }
    }

    /* compiled from: CameraPreview */
    /* renamed from: com.journeyapps.barcodescanner.c$4 */
    class C41274 implements C4126j {
        /* renamed from: a */
        final /* synthetic */ C4116c f14703a;

        /* compiled from: CameraPreview */
        /* renamed from: com.journeyapps.barcodescanner.c$4$1 */
        class C41251 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C41274 f14702a;

            C41251(C41274 c41274) {
                this.f14702a = c41274;
            }

            public void run() {
                this.f14702a.f14703a.mo5922b();
            }
        }

        C41274(C4116c c4116c) {
            this.f14703a = c4116c;
        }

        /* renamed from: a */
        public void mo5929a(int i) {
            this.f14703a.f14658d.postDelayed(new C41251(this), 250);
        }
    }

    /* compiled from: CameraPreview */
    /* renamed from: com.journeyapps.barcodescanner.c$5 */
    class C41285 implements C4119a {
        /* renamed from: a */
        final /* synthetic */ C4116c f14704a;

        C41285(C4116c c4116c) {
            this.f14704a = c4116c;
        }

        /* renamed from: a */
        public void mo5925a() {
            for (C4119a a : this.f14704a.f14665k) {
                a.mo5925a();
            }
        }

        /* renamed from: b */
        public void mo5927b() {
            for (C4119a b : this.f14704a.f14665k) {
                b.mo5927b();
            }
        }

        /* renamed from: c */
        public void mo5928c() {
            for (C4119a c : this.f14704a.f14665k) {
                c.mo5928c();
            }
        }

        /* renamed from: a */
        public void mo5926a(Exception exception) {
            for (C4119a a : this.f14704a.f14665k) {
                a.mo5926a(exception);
            }
        }
    }

    @TargetApi(14)
    /* renamed from: a */
    private SurfaceTextureListener mo5921a() {
        return new C41221(this);
    }

    public C4116c(Context context) {
        super(context);
        m16481a(context, null, 0, 0);
    }

    public C4116c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m16481a(context, attributeSet, 0, 0);
    }

    public C4116c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m16481a(context, attributeSet, i, 0);
    }

    /* renamed from: a */
    private void m16481a(Context context, AttributeSet attributeSet, int i, int i2) {
        if (getBackground() == null) {
            setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
        m16499a(attributeSet);
        this.f14657c = (WindowManager) context.getSystemService("window");
        this.f14658d = new Handler(this.f14679y);
        this.f14663i = new C4167k();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        m16493j();
    }

    /* renamed from: a */
    protected void m16499a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C4087R.styleable.zxing_camera_preview);
        int dimension = (int) obtainStyledAttributes.getDimension(C4087R.styleable.zxing_camera_preview_zxing_framing_rect_width, -1.0f);
        int dimension2 = (int) obtainStyledAttributes.getDimension(C4087R.styleable.zxing_camera_preview_zxing_framing_rect_height, -1.0f);
        if (dimension > 0 && dimension2 > 0) {
            this.f14674t = new C4168l(dimension, dimension2);
        }
        this.f14659e = obtainStyledAttributes.getBoolean(C4087R.styleable.zxing_camera_preview_zxing_use_texture_view, true);
        dimension = obtainStyledAttributes.getInteger(C4087R.styleable.zxing_camera_preview_zxing_preview_scaling_strategy, -1);
        if (dimension == 1) {
            this.f14676v = new C4145f();
        } else if (dimension == 2) {
            this.f14676v = new C4147h();
        } else if (dimension == 3) {
            this.f14676v = new C4148i();
        }
        obtainStyledAttributes.recycle();
    }

    /* renamed from: b */
    private void mo5922b() {
        if (m16504f() && getDisplayRotation() != this.f14664j) {
            mo5924d();
            m16503e();
        }
    }

    /* renamed from: j */
    private void m16493j() {
        if (!this.f14659e || VERSION.SDK_INT < 14) {
            this.f14660f = new SurfaceView(getContext());
            if (VERSION.SDK_INT < 11) {
                this.f14660f.getHolder().setType(3);
            }
            this.f14660f.getHolder().addCallback(this.f14678x);
            addView(this.f14660f);
            return;
        }
        this.f14661g = new TextureView(getContext());
        this.f14661g.setSurfaceTextureListener(mo5921a());
        addView(this.f14661g);
    }

    /* renamed from: a */
    public void m16500a(C4119a c4119a) {
        this.f14665k.add(c4119a);
    }

    /* renamed from: k */
    private void m16494k() {
        if (this.f14668n == null || this.f14669o == null || this.f14666l == null) {
            this.f14673s = null;
            this.f14672r = null;
            this.f14670p = null;
            throw new IllegalStateException("containerSize or previewSize is not set yet");
        }
        int i = this.f14669o.f14828a;
        int i2 = this.f14669o.f14829b;
        int i3 = this.f14668n.f14828a;
        int i4 = this.f14668n.f14829b;
        this.f14670p = this.f14666l.m16631a(this.f14669o);
        this.f14672r = m16498a(new Rect(0, 0, i3, i4), this.f14670p);
        Rect rect = new Rect(this.f14672r);
        rect.offset(-this.f14670p.left, -this.f14670p.top);
        this.f14673s = new Rect((rect.left * i) / this.f14670p.width(), (rect.top * i2) / this.f14670p.height(), (i * rect.right) / this.f14670p.width(), (i2 * rect.bottom) / this.f14670p.height());
        if (this.f14673s.width() <= 0 || this.f14673s.height() <= 0) {
            this.f14673s = null;
            this.f14672r = null;
            Log.w(f14654a, "Preview frame is too small");
            return;
        }
        this.f14655A.mo5925a();
    }

    public void setTorch(boolean z) {
        this.f14677w = z;
        if (this.f14656b != null) {
            this.f14656b.m16588a(z);
        }
    }

    /* renamed from: a */
    private void m16484a(C4168l c4168l) {
        this.f14668n = c4168l;
        if (this.f14656b != null && this.f14656b.m16582a() == null) {
            this.f14666l = new C4146g(getDisplayRotation(), c4168l);
            this.f14666l.m16634a(getPreviewScalingStrategy());
            this.f14656b.m16586a(this.f14666l);
            this.f14656b.m16590c();
            if (this.f14677w) {
                this.f14656b.m16588a(this.f14677w);
            }
        }
    }

    public void setPreviewScalingStrategy(C4144k c4144k) {
        this.f14676v = c4144k;
    }

    public C4144k getPreviewScalingStrategy() {
        if (this.f14676v != null) {
            return this.f14676v;
        }
        if (this.f14661g != null) {
            return new C4145f();
        }
        return new C4147h();
    }

    /* renamed from: b */
    private void m16488b(C4168l c4168l) {
        this.f14669o = c4168l;
        if (this.f14668n != null) {
            m16494k();
            requestLayout();
            m16495l();
        }
    }

    /* renamed from: a */
    protected Matrix m16497a(C4168l c4168l, C4168l c4168l2) {
        float f = 1.0f;
        float f2 = ((float) c4168l.f14828a) / ((float) c4168l.f14829b);
        float f3 = ((float) c4168l2.f14828a) / ((float) c4168l2.f14829b);
        if (f2 < f3) {
            f2 = f3 / f2;
        } else {
            float f4 = f2 / f3;
            f2 = 1.0f;
            f = f4;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(f2, f);
        matrix.postTranslate((((float) c4168l.f14828a) - (f2 * ((float) c4168l.f14828a))) / 2.0f, (((float) c4168l.f14829b) - (f * ((float) c4168l.f14829b))) / 2.0f);
        return matrix;
    }

    /* renamed from: l */
    private void m16495l() {
        if (this.f14671q != null && this.f14669o != null && this.f14670p != null) {
            if (this.f14660f != null && this.f14671q.equals(new C4168l(this.f14670p.width(), this.f14670p.height()))) {
                m16483a(new C4142d(this.f14660f.getHolder()));
            } else if (this.f14661g != null && VERSION.SDK_INT >= 14 && this.f14661g.getSurfaceTexture() != null) {
                if (this.f14669o != null) {
                    this.f14661g.setTransform(m16497a(new C4168l(this.f14661g.getWidth(), this.f14661g.getHeight()), this.f14669o));
                }
                m16483a(new C4142d(this.f14661g.getSurfaceTexture()));
            }
        }
    }

    @SuppressLint({"DrawAllocation"})
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        m16484a(new C4168l(i3 - i, i4 - i2));
        if (this.f14660f != null) {
            if (this.f14670p == null) {
                this.f14660f.layout(0, 0, getWidth(), getHeight());
            } else {
                this.f14660f.layout(this.f14670p.left, this.f14670p.top, this.f14670p.right, this.f14670p.bottom);
            }
        } else if (this.f14661g != null && VERSION.SDK_INT >= 14) {
            this.f14661g.layout(0, 0, getWidth(), getHeight());
        }
    }

    public Rect getFramingRect() {
        return this.f14672r;
    }

    public Rect getPreviewFramingRect() {
        return this.f14673s;
    }

    public CameraSettings getCameraSettings() {
        return this.f14667m;
    }

    public void setCameraSettings(CameraSettings cameraSettings) {
        this.f14667m = cameraSettings;
    }

    /* renamed from: e */
    public void m16503e() {
        C4170n.m16706a();
        Log.d(f14654a, "resume()");
        m16496m();
        if (this.f14671q != null) {
            m16495l();
        } else if (this.f14660f != null) {
            this.f14660f.getHolder().addCallback(this.f14678x);
        } else if (this.f14661g != null && VERSION.SDK_INT >= 14) {
            this.f14661g.setSurfaceTextureListener(mo5921a());
        }
        requestLayout();
        this.f14663i.m16692a(getContext(), this.f14680z);
    }

    /* renamed from: d */
    public void mo5924d() {
        C4170n.m16706a();
        Log.d(f14654a, "pause()");
        this.f14664j = -1;
        if (this.f14656b != null) {
            this.f14656b.m16592e();
            this.f14656b = null;
            this.f14662h = false;
        }
        if (this.f14671q == null && this.f14660f != null) {
            this.f14660f.getHolder().removeCallback(this.f14678x);
        }
        if (this.f14671q == null && this.f14661g != null && VERSION.SDK_INT >= 14) {
            this.f14661g.setSurfaceTextureListener(null);
        }
        this.f14668n = null;
        this.f14669o = null;
        this.f14673s = null;
        this.f14663i.m16691a();
        this.f14655A.mo5928c();
    }

    public C4168l getFramingRectSize() {
        return this.f14674t;
    }

    public void setFramingRectSize(C4168l c4168l) {
        this.f14674t = c4168l;
    }

    public double getMarginFraction() {
        return this.f14675u;
    }

    public void setMarginFraction(double d) {
        if (d >= 0.5d) {
            throw new IllegalArgumentException("The margin fraction must be less than 0.5");
        }
        this.f14675u = d;
    }

    public void setUseTextureView(boolean z) {
        this.f14659e = z;
    }

    /* renamed from: f */
    protected boolean m16504f() {
        return this.f14656b != null;
    }

    private int getDisplayRotation() {
        return this.f14657c.getDefaultDisplay().getRotation();
    }

    /* renamed from: m */
    private void m16496m() {
        if (this.f14656b != null) {
            Log.w(f14654a, "initCamera called twice");
            return;
        }
        this.f14656b = m16505g();
        this.f14656b.m16583a(this.f14658d);
        this.f14656b.m16589b();
        this.f14664j = getDisplayRotation();
    }

    /* renamed from: g */
    protected C4139b m16505g() {
        C4139b c4139b = new C4139b(getContext());
        c4139b.m16584a(this.f14667m);
        return c4139b;
    }

    /* renamed from: a */
    private void m16483a(C4142d c4142d) {
        if (!this.f14662h && this.f14656b != null) {
            Log.i(f14654a, "Starting preview");
            this.f14656b.m16585a(c4142d);
            this.f14656b.m16591d();
            this.f14662h = true;
            mo5923c();
            this.f14655A.mo5927b();
        }
    }

    /* renamed from: c */
    protected void mo5923c() {
    }

    public C4139b getCameraInstance() {
        return this.f14656b;
    }

    /* renamed from: h */
    public boolean m16506h() {
        return this.f14662h;
    }

    /* renamed from: a */
    protected Rect m16498a(Rect rect, Rect rect2) {
        Rect rect3 = new Rect(rect);
        rect3.intersect(rect2);
        if (this.f14674t != null) {
            rect3.inset(Math.max(0, (rect3.width() - this.f14674t.f14828a) / 2), Math.max(0, (rect3.height() - this.f14674t.f14829b) / 2));
        } else {
            int min = (int) Math.min(((double) rect3.width()) * this.f14675u, ((double) rect3.height()) * this.f14675u);
            rect3.inset(min, min);
            if (rect3.height() > rect3.width()) {
                rect3.inset(0, (rect3.height() - rect3.width()) / 2);
            }
        }
        return rect3;
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Parcelable bundle = new Bundle();
        bundle.putParcelable("super", onSaveInstanceState);
        bundle.putBoolean("torch", this.f14677w);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable("super"));
            setTorch(bundle.getBoolean("torch"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}
