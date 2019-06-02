package com.baidu.platform.comapi.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import ch.qos.logback.core.CoreConstants;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.MapRenderer.C1243a;
import com.mapbox.mapboxsdk.style.layers.Property;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
/* renamed from: com.baidu.platform.comapi.map.j */
public class C1255j extends GLSurfaceView implements OnDoubleTapListener, OnGestureListener, C1243a {
    /* renamed from: a */
    private static final String f3799a = C1255j.class.getSimpleName();
    /* renamed from: b */
    private Handler f3800b;
    /* renamed from: c */
    private MapRenderer f3801c;
    /* renamed from: d */
    private int f3802d;
    /* renamed from: e */
    private int f3803e;
    /* renamed from: f */
    private GestureDetector f3804f;
    /* renamed from: g */
    private C1249e f3805g;

    /* renamed from: com.baidu.platform.comapi.map.j$a */
    static class C1254a {
        /* renamed from: a */
        float f3791a;
        /* renamed from: b */
        float f3792b;
        /* renamed from: c */
        float f3793c;
        /* renamed from: d */
        float f3794d;
        /* renamed from: e */
        boolean f3795e;
        /* renamed from: f */
        float f3796f;
        /* renamed from: g */
        float f3797g;
        /* renamed from: h */
        double f3798h;

        C1254a() {
        }

        public String toString() {
            return "MultiTouch{x1=" + this.f3791a + ", x2=" + this.f3792b + ", y1=" + this.f3793c + ", y2=" + this.f3794d + ", mTwoTouch=" + this.f3795e + ", centerX=" + this.f3796f + ", centerY=" + this.f3797g + ", length=" + this.f3798h + CoreConstants.CURLY_RIGHT;
        }
    }

    public C1255j(Context context, C1232C c1232c, String str) {
        super(context);
        if (context == null) {
            throw new RuntimeException("when you create an mapview, the context can not be null");
        }
        setEGLContextClientVersion(2);
        this.f3804f = new GestureDetector(context, this);
        EnvironmentUtilities.initAppDirectory(context);
        if (this.f3805g == null) {
            this.f3805g = new C1249e(context, str);
        }
        this.f3805g.m4674a();
        m4758f();
        this.f3805g.m4697b();
        this.f3805g.m4680a(c1232c);
        m4759g();
        this.f3805g.m4678a(this.f3800b);
        this.f3805g.m4715e();
        setBackgroundColor(0);
    }

    /* renamed from: a */
    private static boolean m4755a(int i, int i2, int i3, int i4, int i5, int i6) {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        egl10.eglInitialize(eglGetDisplay, new int[2]);
        int[] iArr = new int[1];
        return egl10.eglChooseConfig(eglGetDisplay, new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344}, new EGLConfig[100], 100, iArr) && iArr[0] > 0;
    }

    /* renamed from: f */
    private void m4758f() {
        try {
            if (C1255j.m4755a(5, 6, 5, 0, 24, 0)) {
                setEGLConfigChooser(5, 6, 5, 0, 24, 0);
            } else {
                setEGLConfigChooser(true);
            }
        } catch (IllegalArgumentException e) {
            setEGLConfigChooser(true);
        }
        this.f3801c = new MapRenderer(this, this);
        this.f3801c.m4634a(this.f3805g.f3761h);
        setRenderer(this.f3801c);
        setRenderMode(1);
    }

    /* renamed from: g */
    private void m4759g() {
        this.f3800b = new C1256k(this);
    }

    /* renamed from: a */
    public C1249e m4760a() {
        return this.f3805g;
    }

    /* renamed from: a */
    public void m4761a(float f, float f2) {
        if (this.f3805g != null && this.f3805g.f3760g != null) {
            this.f3805g.m4698b(f, f2);
        }
    }

    /* renamed from: a */
    public void m4762a(int i) {
        if (this.f3805g != null) {
            Message message = new Message();
            message.what = 50;
            message.obj = Long.valueOf(this.f3805g.f3761h);
            boolean p = this.f3805g.m4737p();
            if (i == 3) {
                message.arg1 = 0;
            } else if (p) {
                message.arg1 = 1;
            }
            this.f3800b.sendMessage(message);
        }
    }

    /* renamed from: a */
    public void m4763a(String str, Rect rect) {
        if (this.f3805g != null && this.f3805g.f3760g != null) {
            if (rect != null) {
                int i = rect.left;
                int i2 = this.f3803e < rect.bottom ? 0 : this.f3803e - rect.bottom;
                int width = rect.width();
                int height = rect.height();
                if (i >= 0 && i2 >= 0 && width > 0 && height > 0) {
                    if (width > this.f3802d) {
                        width = Math.abs(rect.width()) - (rect.right - this.f3802d);
                    }
                    if (height > this.f3803e) {
                        height = Math.abs(rect.height()) - (rect.bottom - this.f3803e);
                    }
                    if (i > SysOSUtil.getScreenSizeX() || i2 > SysOSUtil.getScreenSizeY()) {
                        this.f3805g.f3760g.m4882a(str, null);
                        requestRender();
                        return;
                    }
                    this.f3802d = width;
                    this.f3803e = height;
                    Bundle bundle = new Bundle();
                    bundle.putInt("x", i);
                    bundle.putInt("y", i2);
                    bundle.putInt(Property.ICON_TEXT_FIT_WIDTH, width);
                    bundle.putInt(Property.ICON_TEXT_FIT_HEIGHT, height);
                    this.f3805g.f3760g.m4882a(str, bundle);
                    requestRender();
                    return;
                }
                return;
            }
            this.f3805g.f3760g.m4882a(str, null);
            requestRender();
        }
    }

    /* renamed from: a */
    public boolean m4764a(float f, float f2, float f3, float f4) {
        return (this.f3805g == null || this.f3805g.f3760g == null) ? false : this.f3805g.m4690a(f, f2, f3, f4);
    }

    /* renamed from: b */
    public void m4765b() {
        if (this.f3805g != null) {
            for (C1114l f : this.f3805g.f3759f) {
                f.mo2644f();
            }
            this.f3805g.m4700b(this.f3800b);
            this.f3805g.m4669M();
            this.f3805g = null;
        }
    }

    /* renamed from: b */
    public boolean m4766b(float f, float f2) {
        return (this.f3805g == null || this.f3805g.f3760g == null) ? false : this.f3805g.m4713d(f, f2);
    }

    /* renamed from: c */
    public void m4767c() {
        if (this.f3805g != null) {
            this.f3805g.m4743t();
        }
    }

    /* renamed from: c */
    public boolean m4768c(float f, float f2) {
        return (this.f3805g == null || this.f3805g.f3760g == null) ? false : this.f3805g.m4707c(f, f2);
    }

    /* renamed from: d */
    public void m4769d() {
        if (this.f3805g != null) {
            this.f3805g.m4744u();
        }
    }

    /* renamed from: d */
    public boolean m4770d(float f, float f2) {
        return (this.f3805g == null || this.f3805g.f3760g == null) ? false : this.f3805g.m4708c((int) f, (int) f2);
    }

    /* renamed from: e */
    public void mo2677e() {
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.f3805g == null || this.f3805g.f3760g == null || !this.f3805g.f3762i) {
            return true;
        }
        GeoPoint b = this.f3805g.m4696b((int) motionEvent.getX(), (int) motionEvent.getY());
        if (b == null) {
            return false;
        }
        for (C1114l b2 : this.f3805g.f3759f) {
            b2.mo2634b(b);
        }
        if (!this.f3805g.f3758e) {
            return false;
        }
        C1235E D = this.f3805g.m4660D();
        D.f3678a += 1.0f;
        D.f3681d = b.getLongitudeE6();
        D.f3682e = b.getLatitudeE6();
        this.f3805g.m4682a(D, 300);
        C1249e c1249e = this.f3805g;
        C1249e.f3728k = System.currentTimeMillis();
        return true;
    }

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (this.f3805g == null || this.f3805g.f3760g == null || !this.f3805g.f3762i) {
            return true;
        }
        if (!this.f3805g.f3757d) {
            return false;
        }
        float sqrt = (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
        if (sqrt <= 500.0f) {
            return false;
        }
        this.f3805g.m4749z();
        this.f3805g.m4671a(34, (int) (sqrt * 0.6f), (((int) motionEvent2.getY()) << 16) | ((int) motionEvent2.getX()));
        this.f3805g.m4668L();
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
        if (this.f3805g != null && this.f3805g.f3760g != null && this.f3805g.f3762i) {
            String a = this.f3805g.f3760g.m4878a(-1, (int) motionEvent.getX(), (int) motionEvent.getY(), this.f3805g.f3763j);
            if (a == null || a.equals("")) {
                for (C1114l c : this.f3805g.f3759f) {
                    c.mo2638c(this.f3805g.m4696b((int) motionEvent.getX(), (int) motionEvent.getY()));
                }
                return;
            }
            for (C1114l c2 : this.f3805g.f3759f) {
                if (c2.mo2636b(a)) {
                    this.f3805g.f3766n = true;
                } else {
                    c2.mo2638c(this.f3805g.m4696b((int) motionEvent.getX(), (int) motionEvent.getY()));
                }
            }
        }
    }

    public void onPause() {
        super.onPause();
        if (this.f3805g != null && this.f3805g.f3760g != null) {
            this.f3805g.f3760g.m4910e();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.f3805g != null && this.f3805g.f3760g != null) {
            for (C1114l d : this.f3805g.f3759f) {
                d.mo2640d();
            }
            this.f3805g.f3760g.m4919i();
            this.f3805g.f3760g.m4913f();
            this.f3805g.f3760g.m4926p();
            setRenderMode(1);
        }
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        JSONException e;
        if (!(this.f3805g == null || this.f3805g.f3760g == null || !this.f3805g.f3762i)) {
            String a = this.f3805g.f3760g.m4878a(-1, (int) motionEvent.getX(), (int) motionEvent.getY(), this.f3805g.f3763j);
            if (a == null || a.equals("")) {
                for (C1114l a2 : this.f3805g.f3759f) {
                    a2.mo2628a(this.f3805g.m4696b((int) motionEvent.getX(), (int) motionEvent.getY()));
                }
            } else {
                JSONObject jSONObject;
                JSONObject jSONObject2;
                try {
                    jSONObject2 = new JSONObject(a);
                    try {
                        jSONObject2.put("px", (int) motionEvent.getX());
                        jSONObject2.put("py", (int) motionEvent.getY());
                        jSONObject = jSONObject2;
                    } catch (JSONException e2) {
                        e = e2;
                        e.printStackTrace();
                        jSONObject = jSONObject2;
                        for (C1114l a22 : this.f3805g.f3759f) {
                            if (jSONObject == null) {
                                a22.mo2630a(jSONObject.toString());
                            }
                        }
                        return true;
                    }
                } catch (JSONException e3) {
                    JSONException jSONException = e3;
                    jSONObject2 = null;
                    e = jSONException;
                    e.printStackTrace();
                    jSONObject = jSONObject2;
                    for (C1114l a222 : this.f3805g.f3759f) {
                        if (jSONObject == null) {
                            a222.mo2630a(jSONObject.toString());
                        }
                    }
                    return true;
                }
                for (C1114l a2222 : this.f3805g.f3759f) {
                    if (jSONObject == null) {
                        a2222.mo2630a(jSONObject.toString());
                    }
                }
            }
        }
        return true;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f3805g == null || this.f3805g.f3760g == null) {
            return true;
        }
        super.onTouchEvent(motionEvent);
        for (C1114l a : this.f3805g.f3759f) {
            a.mo2627a(motionEvent);
        }
        return this.f3804f.onTouchEvent(motionEvent) ? true : this.f3805g.m4694a(motionEvent);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        super.surfaceChanged(surfaceHolder, i, i2, i3);
        if (this.f3805g != null && this.f3805g.f3760g != null) {
            this.f3801c.f3706a = i2;
            this.f3801c.f3707b = i3;
            this.f3802d = i2;
            this.f3803e = i3;
            this.f3801c.f3708c = 0;
            C1235E D = this.f3805g.m4660D();
            if (D.f3683f == 0 || D.f3683f == -1 || D.f3683f == (D.f3687j.left - D.f3687j.right) / 2) {
                D.f3683f = -1;
            }
            if (D.f3684g == 0 || D.f3684g == -1 || D.f3684g == (D.f3687j.bottom - D.f3687j.top) / 2) {
                D.f3684g = -1;
            }
            D.f3687j.left = 0;
            D.f3687j.top = 0;
            D.f3687j.bottom = i3;
            D.f3687j.right = i2;
            this.f3805g.m4681a(D);
            this.f3805g.m4676a(this.f3802d, this.f3803e);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        super.surfaceCreated(surfaceHolder);
        if (surfaceHolder != null && !surfaceHolder.getSurface().isValid()) {
            surfaceDestroyed(surfaceHolder);
        }
    }
}
