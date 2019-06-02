package com.baidu.platform.comapi.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.platform.comapi.commonutils.C1221a;

/* renamed from: com.baidu.platform.comapi.map.N */
public class C1244N extends LinearLayout implements OnTouchListener {
    /* renamed from: a */
    private ImageView f3712a;
    /* renamed from: b */
    private ImageView f3713b;
    /* renamed from: c */
    private Context f3714c;
    /* renamed from: d */
    private Bitmap f3715d;
    /* renamed from: e */
    private Bitmap f3716e;
    /* renamed from: f */
    private Bitmap f3717f;
    /* renamed from: g */
    private Bitmap f3718g;
    /* renamed from: h */
    private Bitmap f3719h;
    /* renamed from: i */
    private Bitmap f3720i;
    /* renamed from: j */
    private Bitmap f3721j;
    /* renamed from: k */
    private Bitmap f3722k;
    /* renamed from: l */
    private int f3723l;
    /* renamed from: m */
    private boolean f3724m = false;
    /* renamed from: n */
    private boolean f3725n = false;

    @Deprecated
    public C1244N(Context context) {
        super(context);
        this.f3714c = context;
        m4638c();
        if (this.f3715d != null && this.f3716e != null && this.f3717f != null && this.f3718g != null) {
            this.f3712a = new ImageView(this.f3714c);
            this.f3713b = new ImageView(this.f3714c);
            this.f3712a.setImageBitmap(this.f3715d);
            this.f3713b.setImageBitmap(this.f3717f);
            this.f3723l = m4635a(this.f3717f.getHeight() / 6);
            m4637a(this.f3712a, "main_topbtn_up.9.png");
            m4637a(this.f3713b, "main_bottombtn_up.9.png");
            this.f3712a.setId(0);
            this.f3713b.setId(1);
            this.f3712a.setClickable(true);
            this.f3713b.setClickable(true);
            this.f3712a.setOnTouchListener(this);
            this.f3713b.setOnTouchListener(this);
            setOrientation(1);
            setLayoutParams(new LayoutParams(-2, -2));
            addView(this.f3712a);
            addView(this.f3713b);
            this.f3725n = true;
        }
    }

    public C1244N(Context context, boolean z) {
        super(context);
        this.f3714c = context;
        this.f3724m = z;
        this.f3712a = new ImageView(this.f3714c);
        this.f3713b = new ImageView(this.f3714c);
        if (z) {
            m4639d();
            if (this.f3719h != null && this.f3720i != null && this.f3721j != null && this.f3722k != null) {
                this.f3712a.setLayoutParams(new LayoutParams(-2, -2));
                this.f3713b.setLayoutParams(new LayoutParams(-2, -2));
                this.f3712a.setImageBitmap(this.f3719h);
                this.f3713b.setImageBitmap(this.f3721j);
                setLayoutParams(new LayoutParams(-2, -2));
                setOrientation(0);
            } else {
                return;
            }
        }
        m4638c();
        if (this.f3715d != null && this.f3716e != null && this.f3717f != null && this.f3718g != null) {
            this.f3712a.setImageBitmap(this.f3715d);
            this.f3713b.setImageBitmap(this.f3717f);
            this.f3723l = m4635a(this.f3717f.getHeight() / 6);
            m4637a(this.f3712a, "main_topbtn_up.9.png");
            m4637a(this.f3713b, "main_bottombtn_up.9.png");
            setLayoutParams(new LayoutParams(-2, -2));
            setOrientation(1);
        } else {
            return;
        }
        this.f3712a.setId(0);
        this.f3713b.setId(1);
        this.f3712a.setClickable(true);
        this.f3713b.setClickable(true);
        this.f3712a.setOnTouchListener(this);
        this.f3713b.setOnTouchListener(this);
        addView(this.f3712a);
        addView(this.f3713b);
        this.f3725n = true;
    }

    /* renamed from: a */
    private int m4635a(int i) {
        return (int) ((this.f3714c.getResources().getDisplayMetrics().density * ((float) i)) + 0.5f);
    }

    /* renamed from: a */
    private Bitmap m4636a(String str) {
        Matrix matrix = new Matrix();
        int densityDpi = SysOSUtil.getDensityDpi();
        if (densityDpi > 480) {
            matrix.postScale(1.8f, 1.8f);
        } else if (densityDpi <= 320 || densityDpi > 480) {
            matrix.postScale(1.2f, 1.2f);
        } else {
            matrix.postScale(1.5f, 1.5f);
        }
        Bitmap a = C1221a.m4570a(str, this.f3714c);
        return Bitmap.createBitmap(a, 0, 0, a.getWidth(), a.getHeight(), matrix, true);
    }

    /* renamed from: a */
    private void m4637a(View view, String str) {
        Bitmap a = C1221a.m4570a(str, this.f3714c);
        byte[] ninePatchChunk = a.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        view.setBackgroundDrawable(new NinePatchDrawable(a, ninePatchChunk, new Rect(), null));
        view.setPadding(this.f3723l, this.f3723l, this.f3723l, this.f3723l);
    }

    /* renamed from: c */
    private void m4638c() {
        this.f3715d = m4636a("main_icon_zoomin.png");
        this.f3716e = m4636a("main_icon_zoomin_dis.png");
        this.f3717f = m4636a("main_icon_zoomout.png");
        this.f3718g = m4636a("main_icon_zoomout_dis.png");
    }

    /* renamed from: d */
    private void m4639d() {
        this.f3719h = m4636a("wear_zoom_in.png");
        this.f3720i = m4636a("wear_zoom_in_pressed.png");
        this.f3721j = m4636a("wear_zoon_out.png");
        this.f3722k = m4636a("wear_zoom_out_pressed.png");
    }

    /* renamed from: a */
    public void m4640a(OnClickListener onClickListener) {
        this.f3712a.setOnClickListener(onClickListener);
    }

    /* renamed from: a */
    public void m4641a(boolean z) {
        this.f3712a.setEnabled(z);
        if (z) {
            this.f3712a.setImageBitmap(this.f3715d);
        } else {
            this.f3712a.setImageBitmap(this.f3716e);
        }
    }

    /* renamed from: a */
    public boolean m4642a() {
        return this.f3725n;
    }

    /* renamed from: b */
    public void m4643b() {
        if (!(this.f3715d == null || this.f3715d.isRecycled())) {
            this.f3715d.recycle();
            this.f3715d = null;
        }
        if (!(this.f3716e == null || this.f3716e.isRecycled())) {
            this.f3716e.recycle();
            this.f3716e = null;
        }
        if (!(this.f3717f == null || this.f3717f.isRecycled())) {
            this.f3717f.recycle();
            this.f3717f = null;
        }
        if (!(this.f3718g == null || this.f3718g.isRecycled())) {
            this.f3718g.recycle();
            this.f3718g = null;
        }
        if (!(this.f3719h == null || this.f3719h.isRecycled())) {
            this.f3719h.recycle();
            this.f3719h = null;
        }
        if (!(this.f3720i == null || this.f3720i.isRecycled())) {
            this.f3720i.recycle();
            this.f3720i = null;
        }
        if (!(this.f3721j == null || this.f3721j.isRecycled())) {
            this.f3721j.recycle();
            this.f3721j = null;
        }
        if (this.f3722k != null && !this.f3722k.isRecycled()) {
            this.f3722k.recycle();
            this.f3722k = null;
        }
    }

    /* renamed from: b */
    public void m4644b(OnClickListener onClickListener) {
        this.f3713b.setOnClickListener(onClickListener);
    }

    /* renamed from: b */
    public void m4645b(boolean z) {
        this.f3713b.setEnabled(z);
        if (z) {
            this.f3713b.setImageBitmap(this.f3717f);
        } else {
            this.f3713b.setImageBitmap(this.f3718g);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view instanceof ImageView) {
            switch (((ImageView) view).getId()) {
                case 0:
                    if (motionEvent.getAction() != 0) {
                        if (motionEvent.getAction() == 1) {
                            if (!this.f3724m) {
                                m4637a(this.f3712a, "main_topbtn_up.9.png");
                                break;
                            }
                            this.f3712a.setImageBitmap(this.f3719h);
                            break;
                        }
                    } else if (!this.f3724m) {
                        m4637a(this.f3712a, "main_topbtn_down.9.png");
                        break;
                    } else {
                        this.f3712a.setImageBitmap(this.f3720i);
                        break;
                    }
                    break;
                case 1:
                    if (motionEvent.getAction() != 0) {
                        if (motionEvent.getAction() == 1) {
                            if (!this.f3724m) {
                                m4637a(this.f3713b, "main_bottombtn_up.9.png");
                                break;
                            }
                            this.f3713b.setImageBitmap(this.f3721j);
                            break;
                        }
                    } else if (!this.f3724m) {
                        m4637a(this.f3713b, "main_bottombtn_down.9.png");
                        break;
                    } else {
                        this.f3713b.setImageBitmap(this.f3722k);
                        break;
                    }
                    break;
            }
        }
        return false;
    }
}
