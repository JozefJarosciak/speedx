package com.baidu.mapapi.map;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.fastjson.asm.Opcodes;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.map.MapViewLayoutParams.ELayoutMode;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.platform.comapi.commonutils.C1221a;
import com.baidu.platform.comapi.map.C1114l;
import com.baidu.platform.comapi.map.C1244N;
import com.baidu.platform.comapi.map.C1253i;
import com.baidu.platform.comapi.map.C1255j;
import com.google.common.primitives.Ints;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

@TargetApi(20)
public class WearMapView extends ViewGroup implements OnApplyWindowInsetsListener {
    public static final int BT_INVIEW = 1;
    /* renamed from: b */
    private static final String f3207b = MapView.class.getSimpleName();
    /* renamed from: c */
    private static String f3208c;
    /* renamed from: q */
    private static int f3209q = 0;
    /* renamed from: r */
    private static int f3210r = 0;
    /* renamed from: s */
    private static int f3211s = 10;
    /* renamed from: u */
    private static final SparseArray<Integer> f3212u = new SparseArray();
    /* renamed from: A */
    private int f3213A;
    /* renamed from: B */
    private int f3214B;
    /* renamed from: C */
    private int f3215C;
    /* renamed from: D */
    private int f3216D;
    /* renamed from: E */
    private int f3217E;
    /* renamed from: a */
    C1110a f3218a = C1110a.ROUND;
    /* renamed from: d */
    private C1255j f3219d;
    /* renamed from: e */
    private BaiduMap f3220e;
    /* renamed from: f */
    private ImageView f3221f;
    /* renamed from: g */
    private Bitmap f3222g;
    /* renamed from: h */
    private C1244N f3223h;
    /* renamed from: i */
    private boolean f3224i = true;
    /* renamed from: j */
    private Point f3225j;
    /* renamed from: k */
    private Point f3226k;
    /* renamed from: l */
    private RelativeLayout f3227l;
    /* renamed from: m */
    private SwipeDismissView f3228m;
    public AnimationTask mTask;
    public Timer mTimer;
    public C1111b mTimerHandler;
    /* renamed from: n */
    private TextView f3229n;
    /* renamed from: o */
    private TextView f3230o;
    /* renamed from: p */
    private ImageView f3231p;
    /* renamed from: t */
    private boolean f3232t = true;
    /* renamed from: v */
    private boolean f3233v = true;
    /* renamed from: w */
    private boolean f3234w = true;
    /* renamed from: x */
    private float f3235x;
    /* renamed from: y */
    private C1114l f3236y;
    /* renamed from: z */
    private int f3237z;

    public class AnimationTask extends TimerTask {
        /* renamed from: a */
        final /* synthetic */ WearMapView f3200a;

        public AnimationTask(WearMapView wearMapView) {
            this.f3200a = wearMapView;
        }

        public void run() {
            Message message = new Message();
            message.what = 1;
            this.f3200a.mTimerHandler.sendMessage(message);
        }
    }

    public interface OnDismissCallback {
        void onDismiss();

        void onNotify();
    }

    /* renamed from: com.baidu.mapapi.map.WearMapView$a */
    enum C1110a {
        ROUND,
        RECTANGLE,
        UNDETECTED
    }

    /* renamed from: com.baidu.mapapi.map.WearMapView$b */
    private class C1111b extends Handler {
        /* renamed from: a */
        final /* synthetic */ WearMapView f3205a;
        /* renamed from: b */
        private final WeakReference<Context> f3206b;

        public C1111b(WearMapView wearMapView, Context context) {
            this.f3205a = wearMapView;
            this.f3206b = new WeakReference(context);
        }

        public void handleMessage(Message message) {
            if (((Context) this.f3206b.get()) != null) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1:
                        if (this.f3205a.f3223h != null) {
                            this.f3205a.m4219a(true);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    static {
        f3212u.append(3, Integer.valueOf(2000000));
        f3212u.append(4, Integer.valueOf(1000000));
        f3212u.append(5, Integer.valueOf(500000));
        f3212u.append(6, Integer.valueOf(200000));
        f3212u.append(7, Integer.valueOf(100000));
        f3212u.append(8, Integer.valueOf(50000));
        f3212u.append(9, Integer.valueOf(25000));
        f3212u.append(10, Integer.valueOf(20000));
        f3212u.append(11, Integer.valueOf(10000));
        f3212u.append(12, Integer.valueOf(5000));
        f3212u.append(13, Integer.valueOf(m_AppUI.MSG_APP_DATA_OK));
        f3212u.append(14, Integer.valueOf(1000));
        f3212u.append(15, Integer.valueOf(500));
        f3212u.append(16, Integer.valueOf(200));
        f3212u.append(17, Integer.valueOf(100));
        f3212u.append(18, Integer.valueOf(50));
        f3212u.append(19, Integer.valueOf(20));
        f3212u.append(20, Integer.valueOf(10));
        f3212u.append(21, Integer.valueOf(5));
        f3212u.append(22, Integer.valueOf(2));
    }

    public WearMapView(Context context) {
        super(context);
        m4214a(context, null);
    }

    public WearMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m4214a(context, null);
    }

    public WearMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m4214a(context, null);
    }

    public WearMapView(Context context, BaiduMapOptions baiduMapOptions) {
        super(context);
        m4214a(context, baiduMapOptions);
    }

    /* renamed from: a */
    private int m4209a(int i, int i2) {
        return i - ((int) Math.sqrt(Math.pow((double) i, 2.0d) - Math.pow((double) i2, 2.0d)));
    }

    /* renamed from: a */
    private void m4212a(int i) {
        if (this.f3219d != null) {
            switch (i) {
                case 0:
                    this.f3219d.onPause();
                    m4221b();
                    return;
                case 1:
                    this.f3219d.onResume();
                    m4224c();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    private static void m4213a(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        f3209q = point.x;
        f3210r = point.y;
    }

    /* renamed from: a */
    private void m4214a(Context context, BaiduMapOptions baiduMapOptions) {
        m4213a(context);
        setOnApplyWindowInsetsListener(this);
        this.mTimerHandler = new C1111b(this, context);
        this.mTimer = new Timer();
        if (!(this.mTimer == null || this.mTask == null)) {
            this.mTask.cancel();
        }
        this.mTask = new AnimationTask(this);
        this.mTimer.schedule(this.mTask, 5000);
        C1253i.m4750a();
        BMapManager.init();
        m4215a(context, baiduMapOptions, f3208c);
        this.f3220e = new BaiduMap(this.f3219d);
        this.f3219d.m4760a().m4736p(false);
        this.f3219d.m4760a().m4735o(false);
        m4225c(context);
        m4228d(context);
        m4222b(context);
        if (!(baiduMapOptions == null || baiduMapOptions.f2875h)) {
            this.f3223h.setVisibility(4);
        }
        m4231e(context);
        if (!(baiduMapOptions == null || baiduMapOptions.f2876i)) {
            this.f3227l.setVisibility(4);
        }
        if (!(baiduMapOptions == null || baiduMapOptions.f2879l == null)) {
            this.f3226k = baiduMapOptions.f2879l;
        }
        if (baiduMapOptions != null && baiduMapOptions.f2878k != null) {
            this.f3225j = baiduMapOptions.f2878k;
        }
    }

    /* renamed from: a */
    private void m4215a(Context context, BaiduMapOptions baiduMapOptions, String str) {
        if (baiduMapOptions == null) {
            this.f3219d = new C1255j(context, null, str);
        } else {
            this.f3219d = new C1255j(context, baiduMapOptions.m4090a(), str);
        }
        addView(this.f3219d);
        this.f3236y = new C1138u(this);
        this.f3219d.m4760a().m4685a(this.f3236y);
    }

    /* renamed from: a */
    private void m4216a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-2, -2);
        }
        int i = layoutParams.width;
        i = i > 0 ? MeasureSpec.makeMeasureSpec(i, Ints.MAX_POWER_OF_TWO) : MeasureSpec.makeMeasureSpec(0, 0);
        int i2 = layoutParams.height;
        view.measure(i, i2 > 0 ? MeasureSpec.makeMeasureSpec(i2, Ints.MAX_POWER_OF_TWO) : MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* renamed from: a */
    private void m4217a(View view, boolean z) {
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "TranslationY", new float[]{0.0f, -50.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 0.0f});
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet.addListener(new C1141x(this, view));
            animatorSet.setDuration(1200);
            animatorSet.start();
            return;
        }
        view.setVisibility(0);
        animatorSet = new AnimatorSet();
        ofFloat = ObjectAnimator.ofFloat(view, "TranslationY", new float[]{-50.0f, 0.0f});
        ofFloat2 = ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f});
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(1200);
        animatorSet.start();
    }

    /* renamed from: a */
    private void m4219a(boolean z) {
        if (this.f3224i) {
            m4217a(this.f3223h, z);
        }
    }

    /* renamed from: b */
    private void m4221b() {
        if (this.f3219d != null && !this.f3232t) {
            m4227d();
            this.f3232t = true;
        }
    }

    /* renamed from: b */
    private void m4222b(Context context) {
        this.f3228m = new SwipeDismissView(context, this);
        LayoutParams layoutParams = new LayoutParams((int) ((context.getResources().getDisplayMetrics().density * 34.0f) + 0.5f), f3210r);
        this.f3228m.setBackgroundColor(Color.argb(0, 0, 0, 0));
        this.f3228m.setLayoutParams(layoutParams);
        addView(this.f3228m);
    }

    /* renamed from: c */
    private void m4224c() {
        if (this.f3219d != null && this.f3232t) {
            m4230e();
            this.f3232t = false;
        }
    }

    /* renamed from: c */
    private void m4225c(Context context) {
        String str = "logo_h.png";
        int densityDpi = SysOSUtil.getDensityDpi();
        if (densityDpi < Opcodes.GETFIELD) {
            str = "logo_l.png";
        }
        Bitmap a = C1221a.m4570a(str, context);
        Matrix matrix;
        if (densityDpi > 480) {
            matrix = new Matrix();
            matrix.postScale(2.0f, 2.0f);
            this.f3222g = Bitmap.createBitmap(a, 0, 0, a.getWidth(), a.getHeight(), matrix, true);
        } else if (densityDpi <= 320 || densityDpi > 480) {
            this.f3222g = a;
        } else {
            matrix = new Matrix();
            matrix.postScale(1.5f, 1.5f);
            this.f3222g = Bitmap.createBitmap(a, 0, 0, a.getWidth(), a.getHeight(), matrix, true);
        }
        if (this.f3222g != null) {
            this.f3221f = new ImageView(context);
            this.f3221f.setImageBitmap(this.f3222g);
            addView(this.f3221f);
        }
    }

    /* renamed from: d */
    private void m4227d() {
        if (this.f3219d != null) {
            this.f3219d.m4767c();
        }
    }

    /* renamed from: d */
    private void m4228d(Context context) {
        this.f3223h = new C1244N(context, true);
        if (this.f3223h.m4642a()) {
            this.f3223h.m4644b(new C1139v(this));
            this.f3223h.m4640a(new C1140w(this));
            addView(this.f3223h);
        }
    }

    /* renamed from: e */
    private void m4230e() {
        if (this.f3219d != null) {
            this.f3219d.m4769d();
        }
    }

    /* renamed from: e */
    private void m4231e(Context context) {
        this.f3227l = new RelativeLayout(context);
        this.f3227l.setLayoutParams(new LayoutParams(-2, -2));
        this.f3229n = new TextView(context);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        this.f3229n.setTextColor(Color.parseColor("#FFFFFF"));
        this.f3229n.setTextSize(2, 11.0f);
        this.f3229n.setTypeface(this.f3229n.getTypeface(), 1);
        this.f3229n.setLayoutParams(layoutParams);
        this.f3229n.setId(Integer.MAX_VALUE);
        this.f3227l.addView(this.f3229n);
        this.f3230o = new TextView(context);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.addRule(14);
        this.f3230o.setTextColor(Color.parseColor("#000000"));
        this.f3230o.setTextSize(2, 11.0f);
        this.f3230o.setLayoutParams(layoutParams);
        this.f3227l.addView(this.f3230o);
        this.f3231p = new ImageView(context);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.addRule(14);
        layoutParams.addRule(3, this.f3229n.getId());
        this.f3231p.setLayoutParams(layoutParams);
        Bitmap a = C1221a.m4570a("icon_scale.9.png", context);
        byte[] ninePatchChunk = a.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        this.f3231p.setBackgroundDrawable(new NinePatchDrawable(a, ninePatchChunk, new Rect(), null));
        this.f3227l.addView(this.f3231p);
        addView(this.f3227l);
    }

    public static void setCustomMapStylePath(String str) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("customMapStylePath String is illegal");
        } else if (new File(str).exists()) {
            f3208c = str;
        } else {
            throw new RuntimeException("please check whether the customMapStylePath file exits");
        }
    }

    public static void setMapCustomEnable(boolean z) {
        C1253i.m4752a(z);
    }

    public void addView(View view, LayoutParams layoutParams) {
        if (layoutParams instanceof MapViewLayoutParams) {
            super.addView(view, layoutParams);
        }
    }

    public final BaiduMap getMap() {
        this.f3220e.f2845c = this;
        return this.f3220e;
    }

    public final int getMapLevel() {
        return ((Integer) f3212u.get((int) this.f3219d.m4760a().m4660D().f3678a)).intValue();
    }

    public int getScaleControlViewHeight() {
        return this.f3216D;
    }

    public int getScaleControlViewWidth() {
        return this.f3217E;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        if (windowInsets.isRound()) {
            this.f3218a = C1110a.ROUND;
        } else {
            this.f3218a = C1110a.RECTANGLE;
        }
        return windowInsets;
    }

    public void onCreate(Context context, Bundle bundle) {
        if (bundle != null) {
            f3208c = bundle.getString("customMapPath");
            if (bundle == null) {
                m4214a(context, new BaiduMapOptions());
                return;
            }
            MapStatus mapStatus = (MapStatus) bundle.getParcelable("mapstatus");
            if (this.f3225j != null) {
                this.f3225j = (Point) bundle.getParcelable("scalePosition");
            }
            if (this.f3226k != null) {
                this.f3226k = (Point) bundle.getParcelable("zoomPosition");
            }
            this.f3233v = bundle.getBoolean("mZoomControlEnabled");
            this.f3234w = bundle.getBoolean("mScaleControlEnabled");
            setPadding(bundle.getInt("paddingLeft"), bundle.getInt("paddingTop"), bundle.getInt("paddingRight"), bundle.getInt("paddingBottom"));
            m4214a(context, new BaiduMapOptions().mapStatus(mapStatus));
        }
    }

    public final void onDestroy() {
        this.f3219d.m4765b();
        if (!(this.f3222g == null || this.f3222g.isRecycled())) {
            this.f3222g.recycle();
            this.f3222g = null;
        }
        this.f3223h.m4643b();
        BMapManager.destroy();
        C1253i.m4753b();
        if (this.mTask != null) {
            this.mTask.cancel();
        }
    }

    public final void onDismiss() {
        removeAllViews();
    }

    public final void onEnterAmbient(Bundle bundle) {
        m4212a(0);
    }

    public void onExitAmbient() {
        m4212a(1);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (this.f3223h.getVisibility() != 0) {
                    if (this.f3223h.getVisibility() == 4) {
                        if (this.mTimer != null) {
                            if (this.mTask != null) {
                                this.mTask.cancel();
                            }
                            this.mTimer.cancel();
                            this.mTask = null;
                            this.mTimer = null;
                        }
                        m4219a(false);
                        break;
                    }
                } else if (this.mTimer != null) {
                    if (this.mTask != null) {
                        this.mTimer.cancel();
                        this.mTask.cancel();
                    }
                    this.mTimer = null;
                    this.mTask = null;
                    break;
                }
                break;
            case 1:
                this.mTimer = new Timer();
                if (!(this.mTimer == null || this.mTask == null)) {
                    this.mTask.cancel();
                }
                this.mTask = new AnimationTask(this);
                this.mTimer.schedule(this.mTask, 5000);
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @TargetApi(20)
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        int childCount = getChildCount();
        m4216a(this.f3221f);
        if (((getWidth() - this.f3237z) - this.f3213A) - this.f3221f.getMeasuredWidth() <= 0 || ((getHeight() - this.f3214B) - this.f3215C) - this.f3221f.getMeasuredHeight() <= 0) {
            this.f3237z = 0;
            this.f3213A = 0;
            this.f3215C = 0;
            this.f3214B = 0;
            f = 1.0f;
            f2 = 1.0f;
        } else {
            f = ((float) ((getWidth() - this.f3237z) - this.f3213A)) / ((float) getWidth());
            f2 = ((float) ((getHeight() - this.f3214B) - this.f3215C)) / ((float) getHeight());
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt == this.f3219d) {
                this.f3219d.layout(0, 0, getWidth(), getHeight());
            } else if (childAt == this.f3221f) {
                r6 = (int) (((float) this.f3215C) + (12.0f * f2));
                r3 = 0;
                r0 = 0;
                if (this.f3218a == C1110a.ROUND) {
                    m4216a(this.f3223h);
                    r3 = f3209q / 2;
                    r0 = m4209a(r3, this.f3223h.getMeasuredWidth() / 2);
                    r3 = ((f3209q / 2) - m4209a(r3, r3 - r0)) + f3211s;
                }
                r0 = (f3210r - r0) - r6;
                r3 = f3209q - r3;
                r7 = r3 - this.f3221f.getMeasuredWidth();
                this.f3221f.layout(r7, r0 - this.f3221f.getMeasuredHeight(), r3, r0);
            } else if (childAt == this.f3223h) {
                if (this.f3223h.m4642a()) {
                    m4216a(this.f3223h);
                    if (this.f3226k == null) {
                        r0 = 0;
                        if (this.f3218a == C1110a.ROUND) {
                            r0 = m4209a(f3210r / 2, this.f3223h.getMeasuredWidth() / 2);
                        }
                        r0 = (int) (((float) r0) + ((12.0f * f2) + ((float) this.f3214B)));
                        r3 = (f3209q - this.f3223h.getMeasuredWidth()) / 2;
                        this.f3223h.layout(r3, r0, this.f3223h.getMeasuredWidth() + r3, this.f3223h.getMeasuredHeight() + r0);
                    } else {
                        this.f3223h.layout(this.f3226k.x, this.f3226k.y, this.f3226k.x + this.f3223h.getMeasuredWidth(), this.f3226k.y + this.f3223h.getMeasuredHeight());
                    }
                }
            } else if (childAt == this.f3227l) {
                r3 = 0;
                r0 = 0;
                if (this.f3218a == C1110a.ROUND) {
                    m4216a(this.f3223h);
                    r3 = f3209q / 2;
                    r0 = m4209a(r3, this.f3223h.getMeasuredWidth() / 2);
                    r3 = ((f3209q / 2) - m4209a(r3, r3 - r0)) + f3211s;
                }
                m4216a(this.f3227l);
                if (this.f3225j == null) {
                    r6 = (int) (((float) this.f3215C) + (12.0f * f2));
                    this.f3217E = this.f3227l.getMeasuredWidth();
                    this.f3216D = this.f3227l.getMeasuredHeight();
                    r3 = (int) (((float) r3) + (((float) this.f3237z) + (5.0f * f)));
                    r0 = (f3210r - r6) - r0;
                    r7 = r0 - this.f3227l.getMeasuredHeight();
                    this.f3227l.layout(r3, r7, this.f3217E + r3, r0);
                } else {
                    this.f3227l.layout(this.f3225j.x, this.f3225j.y, this.f3225j.x + this.f3227l.getMeasuredWidth(), this.f3225j.y + this.f3227l.getMeasuredHeight());
                }
            } else if (childAt == this.f3228m) {
                m4216a(this.f3228m);
                this.f3228m.layout(0, 0, this.f3228m.getMeasuredWidth(), f3210r);
            } else {
                LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof MapViewLayoutParams) {
                    Point point;
                    MapViewLayoutParams mapViewLayoutParams = (MapViewLayoutParams) layoutParams;
                    if (mapViewLayoutParams.f3031c == ELayoutMode.absoluteMode) {
                        point = mapViewLayoutParams.f3030b;
                    } else {
                        point = this.f3219d.m4760a().m4673a(CoordUtil.ll2mc(mapViewLayoutParams.f3029a));
                    }
                    m4216a(childAt);
                    r7 = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    float f3 = mapViewLayoutParams.f3032d;
                    int i6 = (int) (((float) point.x) - (f3 * ((float) r7)));
                    r0 = mapViewLayoutParams.f3034f + ((int) (((float) point.y) - (mapViewLayoutParams.f3033e * ((float) measuredHeight))));
                    childAt.layout(i6, r0, i6 + r7, r0 + measuredHeight);
                }
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null && this.f3220e != null) {
            bundle.putParcelable("mapstatus", this.f3220e.getMapStatus());
            if (this.f3225j != null) {
                bundle.putParcelable("scalePosition", this.f3225j);
            }
            if (this.f3226k != null) {
                bundle.putParcelable("zoomPosition", this.f3226k);
            }
            bundle.putBoolean("mZoomControlEnabled", this.f3233v);
            bundle.putBoolean("mScaleControlEnabled", this.f3234w);
            bundle.putInt("paddingLeft", this.f3237z);
            bundle.putInt("paddingTop", this.f3214B);
            bundle.putInt("paddingRight", this.f3213A);
            bundle.putInt("paddingBottom", this.f3215C);
            bundle.putString("customMapPath", f3208c);
        }
    }

    public void removeView(View view) {
        if (view != this.f3221f) {
            super.removeView(view);
        }
    }

    public void setOnDismissCallbackListener(OnDismissCallback onDismissCallback) {
        if (this.f3228m != null) {
            this.f3228m.setCallback(onDismissCallback);
        }
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.f3237z = i;
        this.f3214B = i2;
        this.f3213A = i3;
        this.f3215C = i4;
    }

    public void setScaleControlPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f3225j = point;
            requestLayout();
        }
    }

    public void setShape(C1110a c1110a) {
        this.f3218a = c1110a;
    }

    public void setViewAnimitionEnable(boolean z) {
        this.f3224i = z;
    }

    public void setZoomControlsPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f3226k = point;
            requestLayout();
        }
    }

    public void showScaleControl(boolean z) {
        this.f3227l.setVisibility(z ? 0 : 8);
        this.f3234w = z;
    }

    public void showZoomControls(boolean z) {
        if (this.f3223h.m4642a()) {
            this.f3223h.setVisibility(z ? 0 : 8);
            this.f3233v = z;
        }
    }
}
