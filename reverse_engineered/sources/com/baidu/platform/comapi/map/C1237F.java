package com.baidu.platform.comapi.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1257m.C1236a;
import com.mapbox.mapboxsdk.style.layers.Property;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
/* renamed from: com.baidu.platform.comapi.map.F */
public class C1237F extends TextureView implements OnDoubleTapListener, OnGestureListener, SurfaceTextureListener, C1236a {
    /* renamed from: a */
    public static int f3697a;
    /* renamed from: b */
    public static int f3698b;
    /* renamed from: c */
    private GestureDetector f3699c;
    /* renamed from: d */
    private Handler f3700d;
    /* renamed from: e */
    private C1257m f3701e = null;
    /* renamed from: f */
    private C1249e f3702f;

    public C1237F(Context context, C1232C c1232c, String str) {
        super(context);
        m4620a(context, c1232c, str);
    }

    /* renamed from: a */
    private void m4620a(Context context, C1232C c1232c, String str) {
        setSurfaceTextureListener(this);
        if (context == null) {
            throw new RuntimeException("when you create an mapview, the context can not be null");
        }
        this.f3699c = new GestureDetector(context, this);
        EnvironmentUtilities.initAppDirectory(context);
        if (this.f3702f == null) {
            this.f3702f = new C1249e(context, str);
        }
        this.f3702f.m4674a();
        this.f3702f.m4697b();
        this.f3702f.m4680a(c1232c);
        m4622f();
        this.f3702f.m4678a(this.f3700d);
        this.f3702f.m4715e();
    }

    /* renamed from: f */
    private void m4622f() {
        this.f3700d = new C1238G(this);
    }

    /* renamed from: a */
    public int mo2674a() {
        return this.f3702f == null ? 0 : MapRenderer.nativeRender(this.f3702f.f3761h);
    }

    /* renamed from: a */
    public void m4624a(String str, Rect rect) {
        if (this.f3702f != null && this.f3702f.f3760g != null) {
            if (rect != null) {
                int i = rect.left;
                int i2 = f3698b < rect.bottom ? 0 : f3698b - rect.bottom;
                int width = rect.width();
                int height = rect.height();
                if (i >= 0 && i2 >= 0 && width > 0 && height > 0) {
                    if (width > f3697a) {
                        width = Math.abs(rect.width()) - (rect.right - f3697a);
                    }
                    if (height > f3698b) {
                        height = Math.abs(rect.height()) - (rect.bottom - f3698b);
                    }
                    if (i > SysOSUtil.getScreenSizeX() || i2 > SysOSUtil.getScreenSizeY()) {
                        this.f3702f.f3760g.m4882a(str, null);
                        if (this.f3701e != null) {
                            this.f3701e.m4776a();
                            return;
                        }
                        return;
                    }
                    f3697a = width;
                    f3698b = height;
                    Bundle bundle = new Bundle();
                    bundle.putInt("x", i);
                    bundle.putInt("y", i2);
                    bundle.putInt(Property.ICON_TEXT_FIT_WIDTH, width);
                    bundle.putInt(Property.ICON_TEXT_FIT_HEIGHT, height);
                    this.f3702f.f3760g.m4882a(str, bundle);
                    if (this.f3701e != null) {
                        this.f3701e.m4776a();
                        return;
                    }
                    return;
                }
                return;
            }
            this.f3702f.f3760g.m4882a(str, null);
            if (this.f3701e != null) {
                this.f3701e.m4776a();
            }
        }
    }

    /* renamed from: b */
    public C1249e m4625b() {
        return this.f3702f;
    }

    /* renamed from: c */
    public void m4626c() {
        if (this.f3702f != null && this.f3702f.f3760g != null) {
            for (C1114l d : this.f3702f.f3759f) {
                d.mo2640d();
            }
            this.f3702f.f3760g.m4919i();
            this.f3702f.f3760g.m4913f();
            this.f3702f.f3760g.m4926p();
            if (this.f3701e != null) {
                this.f3701e.m4776a();
            }
        }
    }

    /* renamed from: d */
    public void m4627d() {
        if (this.f3702f != null && this.f3702f.f3760g != null) {
            this.f3702f.f3760g.m4910e();
            synchronized (this.f3702f) {
                this.f3702f.f3760g.m4910e();
                if (this.f3701e != null) {
                    this.f3701e.m4777b();
                }
            }
        }
    }

    /* renamed from: e */
    public void m4628e() {
        synchronized (this.f3702f) {
            for (C1114l f : this.f3702f.f3759f) {
                f.mo2644f();
            }
            if (this.f3702f != null) {
                this.f3702f.m4700b(this.f3700d);
                this.f3702f.m4669M();
                this.f3702f = null;
            }
            this.f3700d.removeCallbacksAndMessages(null);
        }
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.f3702f == null || this.f3702f.f3760g == null || !this.f3702f.f3762i) {
            return true;
        }
        GeoPoint b = this.f3702f.m4696b((int) motionEvent.getX(), (int) motionEvent.getY());
        if (b == null) {
            return false;
        }
        for (C1114l b2 : this.f3702f.f3759f) {
            b2.mo2634b(b);
        }
        if (!this.f3702f.f3758e) {
            return false;
        }
        C1235E D = this.f3702f.m4660D();
        D.f3678a += 1.0f;
        D.f3681d = b.getLongitudeE6();
        D.f3682e = b.getLatitudeE6();
        this.f3702f.m4682a(D, 300);
        C1249e c1249e = this.f3702f;
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
        if (this.f3702f == null || this.f3702f.f3760g == null || !this.f3702f.f3762i) {
            return true;
        }
        if (!this.f3702f.f3757d) {
            return false;
        }
        float sqrt = (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
        if (sqrt <= 500.0f) {
            return false;
        }
        this.f3702f.m4749z();
        this.f3702f.m4671a(34, (int) (sqrt * 0.6f), (((int) motionEvent2.getY()) << 16) | ((int) motionEvent2.getX()));
        this.f3702f.m4668L();
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
        if (this.f3702f != null && this.f3702f.f3760g != null && this.f3702f.f3762i) {
            String a = this.f3702f.f3760g.m4878a(-1, (int) motionEvent.getX(), (int) motionEvent.getY(), this.f3702f.f3763j);
            if (a == null || a.equals("")) {
                for (C1114l c : this.f3702f.f3759f) {
                    c.mo2638c(this.f3702f.m4696b((int) motionEvent.getX(), (int) motionEvent.getY()));
                }
                return;
            }
            for (C1114l c2 : this.f3702f.f3759f) {
                if (c2.mo2636b(a)) {
                    this.f3702f.f3766n = true;
                } else {
                    c2.mo2638c(this.f3702f.m4696b((int) motionEvent.getX(), (int) motionEvent.getY()));
                }
            }
        }
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        JSONObject jSONObject;
        JSONException e;
        if (!(this.f3702f == null || this.f3702f.f3760g == null || !this.f3702f.f3762i)) {
            String a = this.f3702f.f3760g.m4878a(-1, (int) motionEvent.getX(), (int) motionEvent.getY(), this.f3702f.f3763j);
            if (a == null || a.equals("")) {
                for (C1114l a2 : this.f3702f.f3759f) {
                    a2.mo2628a(this.f3702f.m4696b((int) motionEvent.getX(), (int) motionEvent.getY()));
                }
            } else {
                JSONObject jSONObject2;
                try {
                    jSONObject = new JSONObject(a);
                    try {
                        jSONObject.put("px", (int) motionEvent.getX());
                        jSONObject.put("py", (int) motionEvent.getY());
                        jSONObject2 = jSONObject;
                    } catch (JSONException e2) {
                        e = e2;
                        e.printStackTrace();
                        jSONObject2 = jSONObject;
                        for (C1114l a22 : this.f3702f.f3759f) {
                            if (jSONObject2 == null) {
                                a22.mo2630a(jSONObject2.toString());
                            }
                        }
                        return true;
                    }
                } catch (JSONException e3) {
                    JSONException jSONException = e3;
                    jSONObject = null;
                    e = jSONException;
                    e.printStackTrace();
                    jSONObject2 = jSONObject;
                    for (C1114l a222 : this.f3702f.f3759f) {
                        if (jSONObject2 == null) {
                            a222.mo2630a(jSONObject2.toString());
                        }
                    }
                    return true;
                }
                for (C1114l a2222 : this.f3702f.f3759f) {
                    if (jSONObject2 == null) {
                        a2222.mo2630a(jSONObject2.toString());
                    }
                }
            }
        }
        return true;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.f3702f != null) {
            this.f3701e = new C1257m(surfaceTexture, this, new AtomicBoolean(true), this);
            this.f3701e.start();
            f3697a = i;
            f3698b = i2;
            C1235E D = this.f3702f.m4660D();
            if (D != null) {
                if (D.f3683f == 0 || D.f3683f == -1 || D.f3683f == (D.f3687j.left - D.f3687j.right) / 2) {
                    D.f3683f = -1;
                }
                if (D.f3684g == 0 || D.f3684g == -1 || D.f3684g == (D.f3687j.bottom - D.f3687j.top) / 2) {
                    D.f3684g = -1;
                }
                D.f3687j.left = 0;
                D.f3687j.top = 0;
                D.f3687j.bottom = i2;
                D.f3687j.right = i;
                this.f3702f.m4681a(D);
                this.f3702f.m4676a(f3697a, f3698b);
            }
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (this.f3701e != null) {
            this.f3701e.m4778c();
            this.f3701e = null;
        }
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.f3702f != null) {
            f3697a = i;
            f3698b = i2;
            this.f3702f.m4676a(f3697a, f3698b);
            MapRenderer.nativeResize(this.f3702f.f3761h, i, i2);
        }
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f3702f == null || this.f3702f.f3760g == null) {
            return true;
        }
        super.onTouchEvent(motionEvent);
        for (C1114l a : this.f3702f.f3759f) {
            a.mo2627a(motionEvent);
        }
        return this.f3699c.onTouchEvent(motionEvent) ? true : this.f3702f.m4694a(motionEvent);
    }
}
