package com.beastbikes.android.modules.user.filter.other;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.support.v4.app.NotificationManagerCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.SurfaceView;
import android.view.View.MeasureSpec;
import java.util.ArrayList;
import java.util.List;

public class SquareCameraPreview extends SurfaceView {
    /* renamed from: a */
    public static final String f11496a = SquareCameraPreview.class.getSimpleName();
    /* renamed from: b */
    private Camera f11497b;
    /* renamed from: c */
    private float f11498c;
    /* renamed from: d */
    private float f11499d;
    /* renamed from: e */
    private int f11500e;
    /* renamed from: f */
    private boolean f11501f;
    /* renamed from: g */
    private int f11502g = -1;
    /* renamed from: h */
    private int f11503h = 1;
    /* renamed from: i */
    private ScaleGestureDetector f11504i;
    /* renamed from: j */
    private boolean f11505j;
    /* renamed from: k */
    private Area f11506k;
    /* renamed from: l */
    private ArrayList<Area> f11507l;
    /* renamed from: m */
    private Paint f11508m = new Paint();
    /* renamed from: n */
    private boolean f11509n = true;

    /* renamed from: com.beastbikes.android.modules.user.filter.other.SquareCameraPreview$1 */
    class C24431 implements AutoFocusCallback {
        /* renamed from: a */
        final /* synthetic */ SquareCameraPreview f11494a;

        C24431(SquareCameraPreview squareCameraPreview) {
            this.f11494a = squareCameraPreview;
        }

        public void onAutoFocus(boolean z, Camera camera) {
        }
    }

    /* renamed from: com.beastbikes.android.modules.user.filter.other.SquareCameraPreview$a */
    private class C2444a extends SimpleOnScaleGestureListener {
        /* renamed from: a */
        final /* synthetic */ SquareCameraPreview f11495a;

        private C2444a(SquareCameraPreview squareCameraPreview) {
            this.f11495a = squareCameraPreview;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            this.f11495a.f11503h = (int) scaleGestureDetector.getScaleFactor();
            this.f11495a.m12320a(this.f11495a.f11497b.getParameters());
            return true;
        }
    }

    /* renamed from: a */
    public boolean m12324a() {
        return this.f11509n;
    }

    public void setAutoFocus(boolean z) {
        this.f11509n = z;
    }

    public SquareCameraPreview(Context context) {
        super(context);
        m12319a(context);
    }

    public SquareCameraPreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m12319a(context);
    }

    public SquareCameraPreview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m12319a(context);
    }

    /* renamed from: a */
    private void m12319a(Context context) {
        this.f11504i = new ScaleGestureDetector(context, new C2444a());
        this.f11506k = new Area(new Rect(), 1000);
        this.f11507l = new ArrayList();
        this.f11507l.add(this.f11506k);
    }

    protected void onDraw(Canvas canvas) {
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i2);
        int size2 = MeasureSpec.getSize(i);
        if (((double) size2) > ((double) size) * 0.75d) {
            size2 = (int) ((((double) size) * 0.75d) + 0.5d);
        } else {
            size = (int) ((((double) size2) / 0.75d) + 0.5d);
        }
        setMeasuredDimension(size2, size);
    }

    public int getViewWidth() {
        return getWidth();
    }

    public int getViewHeight() {
        return getHeight();
    }

    public void setCamera(Camera camera) {
        this.f11497b = camera;
        if (camera != null) {
            Parameters parameters = camera.getParameters();
            this.f11501f = parameters.isZoomSupported();
            if (this.f11501f) {
                this.f11500e = parameters.getMaxZoom();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f11504i.onTouchEvent(motionEvent);
        if (!m12324a()) {
            return false;
        }
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.f11505j = true;
                this.f11498c = motionEvent.getX();
                this.f11499d = motionEvent.getY();
                this.f11502g = motionEvent.getPointerId(0);
                break;
            case 1:
                if (this.f11505j && m12324a() && this.f11497b != null) {
                    m12323b(this.f11497b.getParameters());
                }
                this.f11502g = -1;
                break;
            case 3:
                this.f11502g = -1;
                break;
            case 5:
                this.f11497b.cancelAutoFocus();
                this.f11505j = false;
                break;
        }
        return true;
    }

    /* renamed from: a */
    private void m12320a(Parameters parameters) {
        int zoom = parameters.getZoom();
        if (this.f11503h == 1) {
            if (zoom < this.f11500e) {
                zoom++;
            }
        } else if (this.f11503h == 0 && zoom > 0) {
            zoom--;
        }
        parameters.setZoom(zoom);
        this.f11497b.setParameters(parameters);
    }

    /* renamed from: b */
    private void m12323b(Parameters parameters) {
        if (m12322a(this.f11498c, this.f11499d)) {
            List supportedFocusModes = parameters.getSupportedFocusModes();
            if (supportedFocusModes != null && supportedFocusModes.contains("auto")) {
                Log.d(f11496a, this.f11507l.size() + "");
                parameters.setFocusAreas(this.f11507l);
                parameters.setFocusMode("auto");
                this.f11497b.setParameters(parameters);
                this.f11497b.autoFocus(new C24431(this));
            }
        }
    }

    /* renamed from: a */
    private boolean m12322a(float f, float f2) {
        int i = (int) (f - 50.0f);
        int i2 = (int) (f + 50.0f);
        int i3 = (int) (f2 - 50.0f);
        int i4 = (int) (50.0f + f2);
        if (NotificationManagerCompat.IMPORTANCE_UNSPECIFIED > i || i > 1000 || NotificationManagerCompat.IMPORTANCE_UNSPECIFIED > i2 || i2 > 1000 || NotificationManagerCompat.IMPORTANCE_UNSPECIFIED > i3 || i3 > 1000 || NotificationManagerCompat.IMPORTANCE_UNSPECIFIED > i4 || i4 > 1000) {
            return false;
        }
        this.f11506k.rect.set(i, i3, i2, i4);
        return true;
    }
}
