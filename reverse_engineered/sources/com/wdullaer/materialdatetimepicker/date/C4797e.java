package com.wdullaer.materialdatetimepicker.date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.wdullaer.materialdatetimepicker.C4779R;
import com.wdullaer.materialdatetimepicker.C4782b;
import com.wdullaer.materialdatetimepicker.date.C4795d.C4793a;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* compiled from: MonthView */
/* renamed from: com.wdullaer.materialdatetimepicker.date.e */
public abstract class C4797e extends View {
    /* renamed from: a */
    protected static int f16817a = 32;
    /* renamed from: b */
    protected static int f16818b = 10;
    /* renamed from: c */
    protected static int f16819c = 1;
    /* renamed from: d */
    protected static int f16820d;
    /* renamed from: e */
    protected static int f16821e;
    /* renamed from: f */
    protected static int f16822f;
    /* renamed from: g */
    protected static int f16823g;
    /* renamed from: h */
    protected static int f16824h;
    /* renamed from: i */
    protected static float f16825i = 0.0f;
    /* renamed from: A */
    protected int f16826A;
    /* renamed from: B */
    protected int f16827B;
    /* renamed from: C */
    protected int f16828C;
    /* renamed from: D */
    protected int f16829D;
    /* renamed from: E */
    protected final Calendar f16830E;
    /* renamed from: F */
    protected int f16831F;
    /* renamed from: G */
    protected C4794b f16832G;
    /* renamed from: H */
    protected int f16833H;
    /* renamed from: I */
    protected int f16834I;
    /* renamed from: J */
    protected int f16835J;
    /* renamed from: K */
    protected int f16836K;
    /* renamed from: L */
    protected int f16837L;
    /* renamed from: M */
    protected int f16838M;
    /* renamed from: N */
    protected int f16839N;
    /* renamed from: O */
    private String f16840O;
    /* renamed from: P */
    private String f16841P;
    /* renamed from: Q */
    private final Formatter f16842Q;
    /* renamed from: R */
    private final StringBuilder f16843R;
    /* renamed from: S */
    private final Calendar f16844S;
    /* renamed from: T */
    private final C4796a f16845T;
    /* renamed from: U */
    private boolean f16846U;
    /* renamed from: V */
    private int f16847V;
    /* renamed from: j */
    protected C4784a f16848j;
    /* renamed from: k */
    protected int f16849k;
    /* renamed from: l */
    protected Paint f16850l;
    /* renamed from: m */
    protected Paint f16851m;
    /* renamed from: n */
    protected Paint f16852n;
    /* renamed from: o */
    protected Paint f16853o;
    /* renamed from: p */
    protected int f16854p;
    /* renamed from: q */
    protected int f16855q;
    /* renamed from: r */
    protected int f16856r;
    /* renamed from: s */
    protected int f16857s;
    /* renamed from: t */
    protected int f16858t;
    /* renamed from: u */
    protected int f16859u;
    /* renamed from: v */
    protected int f16860v;
    /* renamed from: w */
    protected boolean f16861w;
    /* renamed from: x */
    protected int f16862x;
    /* renamed from: y */
    protected int f16863y;
    /* renamed from: z */
    protected int f16864z;

    /* compiled from: MonthView */
    /* renamed from: com.wdullaer.materialdatetimepicker.date.e$b */
    public interface C4794b {
        /* renamed from: a */
        void mo6198a(C4797e c4797e, C4793a c4793a);
    }

    /* compiled from: MonthView */
    /* renamed from: com.wdullaer.materialdatetimepicker.date.e$a */
    protected class C4796a extends ExploreByTouchHelper {
        /* renamed from: a */
        final /* synthetic */ C4797e f16814a;
        /* renamed from: b */
        private final Rect f16815b = new Rect();
        /* renamed from: c */
        private final Calendar f16816c = Calendar.getInstance();

        public C4796a(C4797e c4797e, View view) {
            this.f16814a = c4797e;
            super(view);
        }

        /* renamed from: a */
        public void m18841a(int i) {
            getAccessibilityNodeProvider(this.f16814a).performAction(i, 64, null);
        }

        /* renamed from: a */
        public void m18840a() {
            int focusedVirtualView = getFocusedVirtualView();
            if (focusedVirtualView != Integer.MIN_VALUE) {
                getAccessibilityNodeProvider(this.f16814a).performAction(focusedVirtualView, 128, null);
            }
        }

        protected int getVirtualViewAt(float f, float f2) {
            int a = this.f16814a.m18849a(f, f2);
            return a >= 0 ? a : Integer.MIN_VALUE;
        }

        protected void getVisibleVirtualViews(List<Integer> list) {
            for (int i = 1; i <= this.f16814a.f16827B; i++) {
                list.add(Integer.valueOf(i));
            }
        }

        protected void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityEvent) {
            accessibilityEvent.setContentDescription(m18843b(i));
        }

        protected void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            m18842a(i, this.f16815b);
            accessibilityNodeInfoCompat.setContentDescription(m18843b(i));
            accessibilityNodeInfoCompat.setBoundsInParent(this.f16815b);
            accessibilityNodeInfoCompat.addAction(16);
            if (i == this.f16814a.f16862x) {
                accessibilityNodeInfoCompat.setSelected(true);
            }
        }

        protected boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
            switch (i2) {
                case 16:
                    this.f16814a.m18845a(i);
                    return true;
                default:
                    return false;
            }
        }

        /* renamed from: a */
        protected void m18842a(int i, Rect rect) {
            int i2 = this.f16814a.f16849k;
            int monthHeaderSize = this.f16814a.getMonthHeaderSize();
            int i3 = this.f16814a.f16860v;
            int i4 = (this.f16814a.f16859u - (this.f16814a.f16849k * 2)) / this.f16814a.f16826A;
            int c = (i - 1) + this.f16814a.m18858c();
            i2 += (c % this.f16814a.f16826A) * i4;
            monthHeaderSize += (c / this.f16814a.f16826A) * i3;
            rect.set(i2, monthHeaderSize, i4 + i2, i3 + monthHeaderSize);
        }

        /* renamed from: b */
        protected CharSequence m18843b(int i) {
            this.f16816c.set(this.f16814a.f16858t, this.f16814a.f16857s, i);
            CharSequence format = DateFormat.format("dd MMMM yyyy", this.f16816c.getTimeInMillis());
            if (i != this.f16814a.f16862x) {
                return format;
            }
            return this.f16814a.getContext().getString(C4779R.string.mdtp_item_is_selected, new Object[]{format});
        }
    }

    /* renamed from: a */
    public abstract void mo6201a(Canvas canvas, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9);

    public C4797e(Context context) {
        this(context, null, null);
    }

    public C4797e(Context context, AttributeSet attributeSet, C4784a c4784a) {
        boolean z = false;
        super(context, attributeSet);
        this.f16849k = 0;
        this.f16854p = -1;
        this.f16855q = -1;
        this.f16856r = -1;
        this.f16860v = f16817a;
        this.f16861w = false;
        this.f16862x = -1;
        this.f16863y = -1;
        this.f16864z = 1;
        this.f16826A = 7;
        this.f16827B = this.f16826A;
        this.f16828C = -1;
        this.f16829D = -1;
        this.f16831F = 6;
        this.f16847V = 0;
        this.f16848j = c4784a;
        Resources resources = context.getResources();
        this.f16830E = Calendar.getInstance();
        this.f16844S = Calendar.getInstance();
        this.f16840O = resources.getString(C4779R.string.mdtp_day_of_week_label_typeface);
        this.f16841P = resources.getString(C4779R.string.mdtp_sans_serif);
        if (this.f16848j != null && this.f16848j.mo6189b()) {
            z = true;
        }
        if (z) {
            this.f16833H = ContextCompat.getColor(context, C4779R.color.mdtp_date_picker_text_normal_dark_theme);
            this.f16835J = ContextCompat.getColor(context, C4779R.color.mdtp_date_picker_month_day_dark_theme);
            this.f16838M = ContextCompat.getColor(context, C4779R.color.mdtp_date_picker_text_disabled_dark_theme);
            this.f16837L = ContextCompat.getColor(context, C4779R.color.mdtp_date_picker_text_highlighted_dark_theme);
        } else {
            this.f16833H = ContextCompat.getColor(context, C4779R.color.mdtp_date_picker_text_normal);
            this.f16835J = ContextCompat.getColor(context, C4779R.color.mdtp_date_picker_month_day);
            this.f16838M = ContextCompat.getColor(context, C4779R.color.mdtp_date_picker_text_disabled);
            this.f16837L = ContextCompat.getColor(context, C4779R.color.mdtp_date_picker_text_highlighted);
        }
        this.f16834I = ContextCompat.getColor(context, C4779R.color.mdtp_white);
        this.f16836K = this.f16848j.mo6191c();
        this.f16839N = ContextCompat.getColor(context, C4779R.color.mdtp_white);
        this.f16843R = new StringBuilder(50);
        this.f16842Q = new Formatter(this.f16843R, Locale.getDefault());
        f16820d = resources.getDimensionPixelSize(C4779R.dimen.mdtp_day_number_size);
        f16821e = resources.getDimensionPixelSize(C4779R.dimen.mdtp_month_label_size);
        f16822f = resources.getDimensionPixelSize(C4779R.dimen.mdtp_month_day_label_text_size);
        f16823g = resources.getDimensionPixelOffset(C4779R.dimen.mdtp_month_list_item_header_height);
        f16824h = resources.getDimensionPixelSize(C4779R.dimen.mdtp_day_number_select_circle_radius);
        this.f16860v = (resources.getDimensionPixelOffset(C4779R.dimen.mdtp_date_picker_view_animator_height) - getMonthHeaderSize()) / 6;
        this.f16845T = getMonthViewTouchHelper();
        ViewCompat.setAccessibilityDelegate(this, this.f16845T);
        ViewCompat.setImportantForAccessibility(this, 1);
        this.f16846U = true;
        m18850a();
    }

    public void setDatePickerController(C4784a c4784a) {
        this.f16848j = c4784a;
    }

    protected C4796a getMonthViewTouchHelper() {
        return new C4796a(this, this);
    }

    public void setAccessibilityDelegate(AccessibilityDelegate accessibilityDelegate) {
        if (!this.f16846U) {
            super.setAccessibilityDelegate(accessibilityDelegate);
        }
    }

    public void setOnDayClickListener(C4794b c4794b) {
        this.f16832G = c4794b;
    }

    public boolean dispatchHoverEvent(@NonNull MotionEvent motionEvent) {
        if (this.f16845T.dispatchHoverEvent(motionEvent)) {
            return true;
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 1:
                int a = m18849a(motionEvent.getX(), motionEvent.getY());
                if (a >= 0) {
                    m18845a(a);
                    break;
                }
                break;
        }
        return true;
    }

    /* renamed from: a */
    protected void m18850a() {
        this.f16851m = new Paint();
        this.f16851m.setFakeBoldText(true);
        this.f16851m.setAntiAlias(true);
        this.f16851m.setTextSize((float) f16821e);
        this.f16851m.setTypeface(Typeface.create(this.f16841P, 1));
        this.f16851m.setColor(this.f16833H);
        this.f16851m.setTextAlign(Align.CENTER);
        this.f16851m.setStyle(Style.FILL);
        this.f16852n = new Paint();
        this.f16852n.setFakeBoldText(true);
        this.f16852n.setAntiAlias(true);
        this.f16852n.setColor(this.f16836K);
        this.f16852n.setTextAlign(Align.CENTER);
        this.f16852n.setStyle(Style.FILL);
        this.f16852n.setAlpha(255);
        this.f16853o = new Paint();
        this.f16853o.setAntiAlias(true);
        this.f16853o.setTextSize((float) f16822f);
        this.f16853o.setColor(this.f16835J);
        this.f16853o.setTypeface(C4782b.m18764a(getContext(), "Roboto-Medium"));
        this.f16853o.setStyle(Style.FILL);
        this.f16853o.setTextAlign(Align.CENTER);
        this.f16853o.setFakeBoldText(true);
        this.f16850l = new Paint();
        this.f16850l.setAntiAlias(true);
        this.f16850l.setTextSize((float) f16820d);
        this.f16850l.setStyle(Style.FILL);
        this.f16850l.setTextAlign(Align.CENTER);
        this.f16850l.setFakeBoldText(false);
    }

    protected void onDraw(Canvas canvas) {
        m18851a(canvas);
        m18857b(canvas);
        m18859c(canvas);
    }

    public void setMonthParams(HashMap<String, Integer> hashMap) {
        if (hashMap.containsKey("month") || hashMap.containsKey("year")) {
            setTag(hashMap);
            if (hashMap.containsKey(Property.ICON_TEXT_FIT_HEIGHT)) {
                this.f16860v = ((Integer) hashMap.get(Property.ICON_TEXT_FIT_HEIGHT)).intValue();
                if (this.f16860v < f16818b) {
                    this.f16860v = f16818b;
                }
            }
            if (hashMap.containsKey("selected_day")) {
                this.f16862x = ((Integer) hashMap.get("selected_day")).intValue();
            }
            this.f16857s = ((Integer) hashMap.get("month")).intValue();
            this.f16858t = ((Integer) hashMap.get("year")).intValue();
            Calendar instance = Calendar.getInstance();
            this.f16861w = false;
            this.f16863y = -1;
            this.f16844S.set(2, this.f16857s);
            this.f16844S.set(1, this.f16858t);
            this.f16844S.set(5, 1);
            this.f16847V = this.f16844S.get(7);
            if (hashMap.containsKey("week_start")) {
                this.f16864z = ((Integer) hashMap.get("week_start")).intValue();
            } else {
                this.f16864z = this.f16844S.getFirstDayOfWeek();
            }
            this.f16827B = this.f16844S.getActualMaximum(5);
            for (int i = 0; i < this.f16827B; i++) {
                int i2 = i + 1;
                if (m18847a(i2, instance)) {
                    this.f16861w = true;
                    this.f16863y = i2;
                }
            }
            this.f16831F = m18848e();
            this.f16845T.invalidateRoot();
            return;
        }
        throw new InvalidParameterException("You must specify month and year for this view");
    }

    public void setSelectedDay(int i) {
        this.f16862x = i;
    }

    /* renamed from: b */
    public void m18856b() {
        this.f16831F = 6;
        requestLayout();
    }

    /* renamed from: e */
    private int m18848e() {
        int c = m18858c();
        return ((c + this.f16827B) % this.f16826A > 0 ? 1 : 0) + ((this.f16827B + c) / this.f16826A);
    }

    /* renamed from: a */
    private boolean m18847a(int i, Calendar calendar) {
        return this.f16858t == calendar.get(1) && this.f16857s == calendar.get(2) && i == calendar.get(5);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(i), ((this.f16860v * this.f16831F) + getMonthHeaderSize()) + 5);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.f16859u = i;
        this.f16845T.invalidateRoot();
    }

    public int getMonth() {
        return this.f16857s;
    }

    public int getYear() {
        return this.f16858t;
    }

    protected int getMonthHeaderSize() {
        return f16823g;
    }

    private String getMonthAndYearString() {
        this.f16843R.setLength(0);
        long timeInMillis = this.f16844S.getTimeInMillis();
        return DateUtils.formatDateRange(getContext(), this.f16842Q, timeInMillis, timeInMillis, 52, null).toString();
    }

    /* renamed from: a */
    protected void m18851a(Canvas canvas) {
        canvas.drawText(getMonthAndYearString(), (float) ((this.f16859u + (this.f16849k * 2)) / 2), (float) ((getMonthHeaderSize() - f16822f) / 2), this.f16851m);
    }

    /* renamed from: b */
    protected void m18857b(Canvas canvas) {
        int monthHeaderSize = getMonthHeaderSize() - (f16822f / 2);
        int i = (this.f16859u - (this.f16849k * 2)) / (this.f16826A * 2);
        for (int i2 = 0; i2 < this.f16826A; i2++) {
            int i3 = (((i2 * 2) + 1) * i) + this.f16849k;
            this.f16830E.set(7, (this.f16864z + i2) % this.f16826A);
            canvas.drawText(m18844a(this.f16830E), (float) i3, (float) monthHeaderSize, this.f16853o);
        }
    }

    /* renamed from: c */
    protected void m18859c(Canvas canvas) {
        int monthHeaderSize = (((this.f16860v + f16820d) / 2) - f16819c) + getMonthHeaderSize();
        float f = ((float) (this.f16859u - (this.f16849k * 2))) / (((float) this.f16826A) * 2.0f);
        int i = 1;
        int c = m18858c();
        while (i <= this.f16827B) {
            int i2 = (int) ((((float) ((c * 2) + 1)) * f) + ((float) this.f16849k));
            int i3 = monthHeaderSize - (((this.f16860v + f16820d) / 2) - f16819c);
            Canvas canvas2 = canvas;
            mo6201a(canvas2, this.f16858t, this.f16857s, i, i2, monthHeaderSize, (int) (((float) i2) - f), (int) (((float) i2) + f), i3, i3 + this.f16860v);
            int i4 = c + 1;
            if (i4 == this.f16826A) {
                i4 = 0;
                monthHeaderSize += this.f16860v;
            }
            i++;
            c = i4;
        }
    }

    /* renamed from: c */
    protected int m18858c() {
        return (this.f16847V < this.f16864z ? this.f16847V + this.f16826A : this.f16847V) - this.f16864z;
    }

    /* renamed from: a */
    public int m18849a(float f, float f2) {
        int b = m18855b(f, f2);
        if (b < 1 || b > this.f16827B) {
            return -1;
        }
        return b;
    }

    /* renamed from: b */
    protected int m18855b(float f, float f2) {
        int i = this.f16849k;
        if (f < ((float) i) || f > ((float) (this.f16859u - this.f16849k))) {
            return -1;
        }
        return ((((int) (((f - ((float) i)) * ((float) this.f16826A)) / ((float) ((this.f16859u - i) - this.f16849k)))) - m18858c()) + 1) + ((((int) (f2 - ((float) getMonthHeaderSize()))) / this.f16860v) * this.f16826A);
    }

    /* renamed from: a */
    private void m18845a(int i) {
        if (!this.f16848j.mo6190b(this.f16858t, this.f16857s, i)) {
            if (this.f16832G != null) {
                this.f16832G.mo6198a(this, new C4793a(this.f16858t, this.f16857s, i));
            }
            this.f16845T.sendEventForVirtualView(i, 1);
        }
    }

    /* renamed from: a */
    protected boolean m18853a(int i, int i2, int i3) {
        Calendar[] d = this.f16848j.mo6192d();
        if (d == null) {
            return false;
        }
        for (Calendar calendar : d) {
            if (i < calendar.get(1)) {
                return false;
            }
            if (i <= calendar.get(1)) {
                if (i2 < calendar.get(2)) {
                    return false;
                }
                if (i2 > calendar.get(2)) {
                    continue;
                } else if (i3 < calendar.get(5)) {
                    return false;
                } else {
                    if (i3 <= calendar.get(5)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    private String m18844a(Calendar calendar) {
        Locale locale = Locale.getDefault();
        if (VERSION.SDK_INT >= 18) {
            return new SimpleDateFormat("EEEEE", locale).format(calendar.getTime());
        }
        String format = new SimpleDateFormat("E", locale).format(calendar.getTime());
        String substring = format.toUpperCase(locale).substring(0, 1);
        if (locale.equals(Locale.CHINA) || locale.equals(Locale.CHINESE) || locale.equals(Locale.SIMPLIFIED_CHINESE) || locale.equals(Locale.TRADITIONAL_CHINESE)) {
            int length = format.length();
            substring = format.substring(length - 1, length);
        }
        if (locale.getLanguage().equals("he") || locale.getLanguage().equals("iw")) {
            if (this.f16830E.get(7) != 7) {
                length = format.length();
                substring = format.substring(length - 2, length - 1);
            } else {
                substring = format.toUpperCase(locale).substring(0, 1);
            }
        }
        if (locale.getLanguage().equals("ca")) {
            substring = format.toLowerCase().substring(0, 2);
        }
        if (locale.getLanguage().equals("es") && calendar.get(7) == 4) {
            return "X";
        }
        return substring;
    }

    public C4793a getAccessibilityFocus() {
        int focusedVirtualView = this.f16845T.getFocusedVirtualView();
        if (focusedVirtualView >= 0) {
            return new C4793a(this.f16858t, this.f16857s, focusedVirtualView);
        }
        return null;
    }

    /* renamed from: d */
    public void m18860d() {
        this.f16845T.m18840a();
    }

    /* renamed from: a */
    public boolean m18854a(C4793a c4793a) {
        if (c4793a.f16806a != this.f16858t || c4793a.f16807b != this.f16857s || c4793a.f16808c > this.f16827B) {
            return false;
        }
        this.f16845T.m18841a(c4793a.f16808c);
        return true;
    }
}
