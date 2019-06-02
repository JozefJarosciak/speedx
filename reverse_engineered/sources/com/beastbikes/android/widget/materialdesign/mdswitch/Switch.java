package com.beastbikes.android.widget.materialdesign.mdswitch;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Checkable;
import com.alipay.sdk.util.C0880h;
import com.beastbikes.android.R$styleable;
import com.beastbikes.android.widget.materialdesign.mdswitch.C2670d.C2660c;
import com.beastbikes.android.widget.materialdesign.mdswitch.C2670d.C2669b;

public class Switch extends View implements Checkable, C2660c {
    /* renamed from: A */
    private int f12427A;
    /* renamed from: B */
    private Path f12428B;
    /* renamed from: C */
    private Paint f12429C;
    /* renamed from: D */
    private boolean f12430D;
    /* renamed from: E */
    private C1706a f12431E;
    /* renamed from: F */
    private final Runnable f12432F;
    /* renamed from: a */
    protected int f12433a;
    /* renamed from: b */
    protected int f12434b;
    /* renamed from: c */
    private C2667c f12435c;
    /* renamed from: d */
    private boolean f12436d;
    /* renamed from: e */
    private Paint f12437e;
    /* renamed from: f */
    private RectF f12438f;
    /* renamed from: g */
    private RectF f12439g;
    /* renamed from: h */
    private Path f12440h;
    /* renamed from: i */
    private int f12441i;
    /* renamed from: j */
    private ColorStateList f12442j;
    /* renamed from: k */
    private Cap f12443k;
    /* renamed from: l */
    private int f12444l;
    /* renamed from: m */
    private ColorStateList f12445m;
    /* renamed from: n */
    private float f12446n;
    /* renamed from: o */
    private int f12447o;
    /* renamed from: p */
    private Interpolator f12448p;
    /* renamed from: q */
    private int f12449q;
    /* renamed from: r */
    private boolean f12450r;
    /* renamed from: s */
    private float f12451s;
    /* renamed from: t */
    private float f12452t;
    /* renamed from: u */
    private float f12453u;
    /* renamed from: v */
    private long f12454v;
    /* renamed from: w */
    private int f12455w;
    /* renamed from: x */
    private float f12456x;
    /* renamed from: y */
    private int[] f12457y;
    /* renamed from: z */
    private int f12458z;

    /* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.Switch$a */
    public interface C1706a {
        /* renamed from: a */
        void mo3219a(Switch switchR, boolean z);
    }

    /* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.Switch$1 */
    class C26581 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ Switch f12425a;

        C26581(Switch switchR) {
            this.f12425a = switchR;
        }

        public void run() {
            this.f12425a.m13210e();
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new C26591();
        /* renamed from: a */
        boolean f12426a;

        /* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.Switch$SavedState$1 */
        static class C26591 implements Creator<SavedState> {
            C26591() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m13200a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m13201a(i);
            }

            /* renamed from: a */
            public SavedState m13200a(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] m13201a(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f12426a = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.f12426a));
        }

        public String toString() {
            return "Switch.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " checked=" + this.f12426a + C0880h.f2222d;
        }
    }

    public Switch(Context context) {
        super(context);
        this.f12434b = Integer.MIN_VALUE;
        this.f12436d = false;
        this.f12441i = -1;
        this.f12443k = Cap.ROUND;
        this.f12444l = -1;
        this.f12447o = -1;
        this.f12449q = 16;
        this.f12450r = false;
        this.f12457y = new int[2];
        this.f12458z = -1;
        this.f12427A = -1;
        this.f12430D = false;
        this.f12432F = new C26581(this);
        m13212a(context, null, 0, 0);
    }

    public Switch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f12434b = Integer.MIN_VALUE;
        this.f12436d = false;
        this.f12441i = -1;
        this.f12443k = Cap.ROUND;
        this.f12444l = -1;
        this.f12447o = -1;
        this.f12449q = 16;
        this.f12450r = false;
        this.f12457y = new int[2];
        this.f12458z = -1;
        this.f12427A = -1;
        this.f12430D = false;
        this.f12432F = new C26581(this);
        m13212a(context, attributeSet, 0, 0);
    }

    public Switch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f12434b = Integer.MIN_VALUE;
        this.f12436d = false;
        this.f12441i = -1;
        this.f12443k = Cap.ROUND;
        this.f12444l = -1;
        this.f12447o = -1;
        this.f12449q = 16;
        this.f12450r = false;
        this.f12457y = new int[2];
        this.f12458z = -1;
        this.f12427A = -1;
        this.f12430D = false;
        this.f12432F = new C26581(this);
        m13212a(context, attributeSet, i, 0);
    }

    @TargetApi(21)
    public Switch(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f12434b = Integer.MIN_VALUE;
        this.f12436d = false;
        this.f12441i = -1;
        this.f12443k = Cap.ROUND;
        this.f12444l = -1;
        this.f12447o = -1;
        this.f12449q = 16;
        this.f12450r = false;
        this.f12457y = new int[2];
        this.f12458z = -1;
        this.f12427A = -1;
        this.f12430D = false;
        this.f12432F = new C26581(this);
        m13212a(context, attributeSet, i, i2);
    }

    /* renamed from: a */
    protected void m13212a(Context context, AttributeSet attributeSet, int i, int i2) {
        this.f12437e = new Paint(1);
        this.f12438f = new RectF();
        this.f12439g = new RectF();
        this.f12440h = new Path();
        this.f12453u = (float) ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        m13214b(context, attributeSet, i, i2);
        if (!isInEditMode()) {
            this.f12433a = C2670d.m13267a(context, attributeSet, i, i2);
        }
    }

    /* renamed from: a */
    public void m13211a(int i) {
        C2674h.m13290a((View) this, i);
        m13214b(getContext(), null, 0, i);
    }

    /* renamed from: b */
    protected void m13214b(Context context, AttributeSet attributeSet, int i, int i2) {
        getRippleManager().m13263a(this, context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Switch, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = obtainStyledAttributes.getIndex(i3);
            if (index == 2) {
                this.f12441i = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == 3) {
                this.f12442j = obtainStyledAttributes.getColorStateList(index);
            } else if (index == 4) {
                index = obtainStyledAttributes.getInteger(index, 0);
                if (index == 0) {
                    this.f12443k = Cap.BUTT;
                } else if (index == 1) {
                    this.f12443k = Cap.ROUND;
                } else {
                    this.f12443k = Cap.SQUARE;
                }
            } else if (index == 5) {
                this.f12445m = obtainStyledAttributes.getColorStateList(index);
            } else if (index == 6) {
                this.f12444l = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == 7) {
                this.f12458z = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                this.f12427A = this.f12458z / 2;
            } else if (index == 8) {
                this.f12447o = obtainStyledAttributes.getInt(index, 0);
            } else if (index == 0) {
                this.f12449q = obtainStyledAttributes.getInt(index, 0);
            } else if (index == 1) {
                setCheckedImmediately(obtainStyledAttributes.getBoolean(index, this.f12450r));
            } else if (index == 9) {
                index = obtainStyledAttributes.getResourceId(9, 0);
                if (index != 0) {
                    this.f12448p = AnimationUtils.loadInterpolator(context, index);
                }
            }
        }
        obtainStyledAttributes.recycle();
        if (this.f12441i < 0) {
            this.f12441i = C2671e.m13275a(context, 2);
        }
        if (this.f12444l < 0) {
            this.f12444l = C2671e.m13275a(context, 8);
        }
        if (this.f12458z < 0) {
            this.f12458z = C2671e.m13275a(context, 2);
            this.f12427A = this.f12458z / 2;
        }
        if (this.f12447o < 0) {
            this.f12447o = context.getResources().getInteger(17694721);
        }
        if (this.f12448p == null) {
            this.f12448p = new DecelerateInterpolator();
        }
        if (this.f12442j == null) {
            r0 = new int[2][];
            r0[0] = new int[]{-16842912};
            r0[1] = new int[]{16842912};
            this.f12442j = new ColorStateList(r0, new int[]{C2661a.m13215a(C2671e.m13278b(context, ViewCompat.MEASURED_STATE_MASK), 0.5f), C2661a.m13215a(C2671e.m13279c(context, ViewCompat.MEASURED_STATE_MASK), 0.5f)});
        }
        if (this.f12445m == null) {
            r0 = new int[2][];
            r0[0] = new int[]{-16842912};
            r0[1] = new int[]{16842912};
            this.f12445m = new ColorStateList(r0, new int[]{16448250, C2671e.m13279c(context, ViewCompat.MEASURED_STATE_MASK)});
        }
        this.f12437e.setStrokeCap(this.f12443k);
        m13203a();
        invalidate();
    }

    /* renamed from: a */
    public void m13213a(C2669b c2669b) {
        int a = C2670d.m13268a().m13271a(this.f12433a);
        if (this.f12434b != a) {
            this.f12434b = a;
            m13211a(this.f12434b);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f12433a != 0) {
            C2670d.m13268a().m13273a((C2660c) this);
            m13213a(null);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C2667c.m13257a((View) this);
        if (this.f12433a != 0) {
            C2670d.m13268a().m13274b((C2660c) this);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        Drawable background = getBackground();
        if (!(background instanceof RippleDrawable) || (drawable instanceof RippleDrawable)) {
            super.setBackgroundDrawable(drawable);
        } else {
            ((C2665b) background).m13254a(drawable);
        }
    }

    protected C2667c getRippleManager() {
        synchronized (C2667c.class) {
            if (this.f12435c == null) {
                this.f12435c = new C2667c();
            }
        }
        return this.f12435c;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        OnClickListener rippleManager = getRippleManager();
        if (onClickListener == rippleManager) {
            super.setOnClickListener(onClickListener);
            return;
        }
        rippleManager.m13262a(onClickListener);
        setOnClickListener(rippleManager);
    }

    public void setOnCheckedChangeListener(C1706a c1706a) {
        this.f12431E = c1706a;
    }

    public void setChecked(boolean z) {
        if (this.f12450r != z) {
            this.f12450r = z;
            if (this.f12431E != null) {
                this.f12431E.mo3219a(this, this.f12450r);
            }
        }
        if (this.f12446n != (this.f12450r ? 1.0f : 0.0f)) {
            m13208c();
        }
    }

    public void setCheckedImmediately(boolean z) {
        if (this.f12450r != z) {
            this.f12450r = z;
            if (this.f12431E != null) {
                this.f12431E.mo3219a(this, this.f12450r);
            }
        }
        this.f12446n = this.f12450r ? 1.0f : 0.0f;
        invalidate();
    }

    public boolean isChecked() {
        return this.f12450r;
    }

    public void toggle() {
        if (isEnabled()) {
            setChecked(!this.f12450r);
        }
    }

    public void onRtlPropertiesChanged(int i) {
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        if (this.f12430D != z) {
            this.f12430D = z;
            invalidate();
        }
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        boolean z = false;
        super.onTouchEvent(motionEvent);
        getRippleManager().m13264a((View) this, motionEvent);
        float x = motionEvent.getX();
        if (this.f12430D) {
            x = (2.0f * this.f12438f.centerX()) - x;
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                this.f12451s = x;
                this.f12452t = this.f12451s;
                this.f12454v = SystemClock.uptimeMillis();
                break;
            case 1:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                x = ((x - this.f12452t) / ((float) (SystemClock.uptimeMillis() - this.f12454v))) * 1000.0f;
                if (Math.abs(x) < this.f12453u) {
                    if ((!this.f12450r && this.f12446n < 0.1f) || (this.f12450r && this.f12446n > 0.9f)) {
                        toggle();
                        break;
                    }
                    if (this.f12446n > 0.5f) {
                        z = true;
                    }
                    setChecked(z);
                    break;
                }
                setChecked(x > 0.0f);
                break;
            case 2:
                this.f12446n = Math.min(1.0f, Math.max(0.0f, ((x - this.f12451s) / (this.f12438f.width() - ((float) (this.f12444l * 2)))) + this.f12446n));
                this.f12451s = x;
                invalidate();
                break;
            case 3:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                if (this.f12446n > 0.5f) {
                    z = true;
                }
                setChecked(z);
                break;
        }
        return true;
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        switch (mode) {
            case Integer.MIN_VALUE:
                size = Math.min(size, getSuggestedMinimumWidth());
                break;
            case 0:
                size = getSuggestedMinimumWidth();
                break;
        }
        switch (mode2) {
            case Integer.MIN_VALUE:
                size2 = Math.min(size2, getSuggestedMinimumHeight());
                break;
            case 0:
                size2 = getSuggestedMinimumHeight();
                break;
        }
        setMeasuredDimension(size, size2);
    }

    public int getSuggestedMinimumWidth() {
        return ((this.f12444l * 4) + Math.max(this.f12458z, getPaddingLeft())) + Math.max(this.f12458z, getPaddingRight());
    }

    public int getSuggestedMinimumHeight() {
        return ((this.f12444l * 2) + Math.max(this.f12458z - this.f12427A, getPaddingTop())) + Math.max(this.f12458z + this.f12427A, getPaddingBottom());
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.f12438f.left = (float) Math.max(this.f12458z, getPaddingLeft());
        this.f12438f.right = (float) (i - Math.max(this.f12458z, getPaddingRight()));
        int i5 = this.f12444l * 2;
        switch (this.f12449q & 112) {
            case 48:
                this.f12438f.top = (float) Math.max(this.f12458z - this.f12427A, getPaddingTop());
                this.f12438f.bottom = ((float) i5) + this.f12438f.top;
                return;
            case 80:
                this.f12438f.bottom = (float) (i2 - Math.max(this.f12458z + this.f12427A, getPaddingBottom()));
                this.f12438f.top = this.f12438f.bottom - ((float) i5);
                return;
            default:
                this.f12438f.top = ((float) (i2 - i5)) / 2.0f;
                this.f12438f.bottom = ((float) i5) + this.f12438f.top;
                return;
        }
    }

    /* renamed from: a */
    private int m13202a(boolean z) {
        this.f12457y[0] = isEnabled() ? 16842910 : -16842910;
        this.f12457y[1] = z ? 16842912 : -16842912;
        return this.f12442j.getColorForState(this.f12457y, 0);
    }

    /* renamed from: b */
    private int m13206b(boolean z) {
        this.f12457y[0] = isEnabled() ? 16842910 : -16842910;
        this.f12457y[1] = z ? 16842912 : -16842912;
        return this.f12445m.getColorForState(this.f12457y, 0);
    }

    /* renamed from: a */
    private void m13203a() {
        if (this.f12458z > 0) {
            if (this.f12429C == null) {
                this.f12429C = new Paint(5);
                this.f12429C.setStyle(Style.FILL);
                this.f12429C.setDither(true);
            }
            float f = ((float) this.f12444l) / ((float) ((this.f12444l + this.f12458z) + this.f12427A));
            float[] fArr = new float[]{0.0f, f, 1.0f};
            f = 0.0f;
            this.f12429C.setShader(new RadialGradient(0.0f, f, (float) (this.f12444l + this.f12458z), new int[]{1275068416, 1275068416, 0}, fArr, TileMode.CLAMP));
            if (this.f12428B == null) {
                this.f12428B = new Path();
                this.f12428B.setFillType(FillType.EVEN_ODD);
            } else {
                this.f12428B.reset();
            }
            float f2 = (float) (this.f12444l + this.f12458z);
            this.f12439g.set(-f2, -f2, f2, f2);
            this.f12428B.addOval(this.f12439g, Direction.CW);
            f2 = (float) (this.f12444l - 1);
            this.f12439g.set(-f2, (-f2) - ((float) this.f12427A), f2, f2 - ((float) this.f12427A));
            this.f12428B.addOval(this.f12439g, Direction.CW);
        }
    }

    /* renamed from: a */
    private void m13204a(float f, float f2, float f3) {
        float f4 = ((float) this.f12441i) / 2.0f;
        this.f12440h.reset();
        float asin;
        if (this.f12443k != Cap.ROUND) {
            this.f12439g.set((f - f3) + 1.0f, (f2 - f3) + 1.0f, (f + f3) - 1.0f, (f2 + f3) - 1.0f);
            asin = (float) ((Math.asin((double) (f4 / (f3 - 1.0f))) / 3.141592653589793d) * 180.0d);
            if (f - f3 > this.f12438f.left) {
                this.f12440h.moveTo(this.f12438f.left, f2 - f4);
                this.f12440h.arcTo(this.f12439g, 180.0f + asin, (-asin) * 2.0f);
                this.f12440h.lineTo(this.f12438f.left, f2 + f4);
                this.f12440h.close();
            }
            if (f + f3 < this.f12438f.right) {
                this.f12440h.moveTo(this.f12438f.right, f2 - f4);
                this.f12440h.arcTo(this.f12439g, -asin, asin * 2.0f);
                this.f12440h.lineTo(this.f12438f.right, f4 + f2);
                this.f12440h.close();
                return;
            }
            return;
        }
        asin = (float) ((Math.asin((double) (f4 / (f3 - 1.0f))) / 3.141592653589793d) * 180.0d);
        if (f - f3 > this.f12438f.left) {
            float acos = (float) ((Math.acos((double) Math.max(0.0f, (((this.f12438f.left + f4) - f) + f3) / f4)) / 3.141592653589793d) * 180.0d);
            this.f12439g.set(this.f12438f.left, f2 - f4, this.f12438f.left + ((float) this.f12441i), f2 + f4);
            this.f12440h.arcTo(this.f12439g, 180.0f - acos, acos * 2.0f);
            this.f12439g.set((f - f3) + 1.0f, (f2 - f3) + 1.0f, (f + f3) - 1.0f, (f2 + f3) - 1.0f);
            this.f12440h.arcTo(this.f12439g, 180.0f + asin, (-asin) * 2.0f);
            this.f12440h.close();
        }
        if (f + f3 < this.f12438f.right) {
            acos = (float) Math.acos((double) Math.max(0.0f, (((f + f3) - this.f12438f.right) + f4) / f4));
            this.f12440h.moveTo((float) (((double) (this.f12438f.right - f4)) + (Math.cos((double) acos) * ((double) f4))), (float) (((double) f2) + (Math.sin((double) acos) * ((double) f4))));
            acos = (float) ((((double) acos) / 3.141592653589793d) * 180.0d);
            this.f12439g.set(this.f12438f.right - ((float) this.f12441i), f2 - f4, this.f12438f.right, f4 + f2);
            this.f12440h.arcTo(this.f12439g, acos, (-acos) * 2.0f);
            this.f12439g.set((f - f3) + 1.0f, (f2 - f3) + 1.0f, (f + f3) - 1.0f, (f2 + f3) - 1.0f);
            this.f12440h.arcTo(this.f12439g, -asin, asin * 2.0f);
            this.f12440h.close();
        }
    }

    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        float width = (((this.f12438f.width() - ((float) (this.f12444l * 2))) * this.f12446n) + this.f12438f.left) + ((float) this.f12444l);
        if (this.f12430D) {
            width = (2.0f * this.f12438f.centerX()) - width;
        }
        float centerY = this.f12438f.centerY();
        m13204a(width, centerY, (float) this.f12444l);
        this.f12437e.setColor(C2661a.m13216a(m13202a(false), m13202a(true), this.f12446n));
        this.f12437e.setStyle(Style.FILL);
        canvas.drawPath(this.f12440h, this.f12437e);
        if (this.f12458z > 0) {
            int save = canvas.save();
            canvas.translate(width, ((float) this.f12427A) + centerY);
            canvas.drawPath(this.f12428B, this.f12429C);
            canvas.restoreToCount(save);
        }
        this.f12437e.setColor(C2661a.m13216a(m13206b(false), m13206b(true), this.f12446n));
        this.f12437e.setStyle(Style.FILL);
        canvas.drawCircle(width, centerY, (float) this.f12444l, this.f12437e);
    }

    /* renamed from: b */
    private void m13207b() {
        this.f12454v = SystemClock.uptimeMillis();
        this.f12456x = this.f12446n;
        this.f12455w = (int) ((this.f12450r ? 1.0f - this.f12456x : this.f12456x) * ((float) this.f12447o));
    }

    /* renamed from: c */
    private void m13208c() {
        if (getHandler() != null) {
            m13207b();
            this.f12436d = true;
            getHandler().postAtTime(this.f12432F, SystemClock.uptimeMillis() + 16);
        } else {
            this.f12446n = this.f12450r ? 1.0f : 0.0f;
        }
        invalidate();
    }

    /* renamed from: d */
    private void m13209d() {
        this.f12436d = false;
        this.f12446n = this.f12450r ? 1.0f : 0.0f;
        if (getHandler() != null) {
            getHandler().removeCallbacks(this.f12432F);
        }
        invalidate();
    }

    /* renamed from: e */
    private void m13210e() {
        float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.f12454v)) / ((float) this.f12455w));
        float interpolation = this.f12448p.getInterpolation(min);
        if (this.f12450r) {
            interpolation += this.f12456x * (1.0f - interpolation);
        } else {
            interpolation = (1.0f - interpolation) * this.f12456x;
        }
        this.f12446n = interpolation;
        if (min == 1.0f) {
            m13209d();
        }
        if (this.f12436d) {
            if (getHandler() != null) {
                getHandler().postAtTime(this.f12432F, SystemClock.uptimeMillis() + 16);
            } else {
                m13209d();
            }
        }
        invalidate();
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f12426a = isChecked();
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setChecked(savedState.f12426a);
        requestLayout();
    }
}
