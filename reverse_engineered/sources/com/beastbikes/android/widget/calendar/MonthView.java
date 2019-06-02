package com.beastbikes.android.widget.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import com.beastbikes.android.R$styleable;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.train.dto.CalendarDto;
import com.beastbikes.android.utils.C2554c;
import com.beastbikes.android.utils.C2555d;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MonthView extends View {
    /* renamed from: a */
    private int f12258a = -1;
    /* renamed from: b */
    private int f12259b = 14;
    /* renamed from: c */
    private int f12260c = Color.parseColor("#303030");
    /* renamed from: d */
    private int f12261d = Color.parseColor("#666666");
    /* renamed from: e */
    private int f12262e = Color.parseColor("#ffffff");
    /* renamed from: f */
    private int f12263f = Color.parseColor("#ff002a");
    /* renamed from: g */
    private GestureDetector f12264g;
    /* renamed from: h */
    private Paint f12265h;
    /* renamed from: i */
    private int f12266i;
    /* renamed from: j */
    private int f12267j;
    /* renamed from: k */
    private int f12268k;
    /* renamed from: l */
    private int f12269l = 6;
    /* renamed from: m */
    private int f12270m;
    /* renamed from: n */
    private int f12271n;
    /* renamed from: o */
    private int f12272o;
    /* renamed from: p */
    private int[][] f12273p;
    /* renamed from: q */
    private HashMap<String, CalendarDto> f12274q;
    /* renamed from: r */
    private C2381a f12275r;
    /* renamed from: s */
    private DecimalFormat f12276s;
    /* renamed from: t */
    private boolean f12277t;

    /* renamed from: com.beastbikes.android.widget.calendar.MonthView$1 */
    class C26221 extends SimpleOnGestureListener {
        /* renamed from: a */
        final /* synthetic */ MonthView f12257a;

        C26221(MonthView monthView) {
            this.f12257a = monthView;
        }

        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            this.f12257a.m13071a((int) motionEvent.getX(), (int) motionEvent.getY());
            return true;
        }
    }

    public MonthView(Context context) {
        super(context);
        m13074a(null);
    }

    public MonthView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m13074a(attributeSet);
    }

    public MonthView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13074a(attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (mode == Integer.MIN_VALUE) {
            size = displayMetrics.densityDpi * 300;
        }
        mode = (C2554c.m12788a(this.f12270m, this.f12271n) + C2554c.m12791b(this.f12270m, this.f12271n)) - 1;
        setMeasuredDimension(size, (mode % 7 == 0 ? mode / 7 : (mode / 7) + 1) * (size / 7));
    }

    protected void onDraw(Canvas canvas) {
        this.f12277t = C1849a.m9645b(getContext());
        m13080d();
        m13082f();
        m13073a(canvas);
        m13076b(canvas);
        m13079c(canvas);
    }

    public boolean performClick() {
        return super.performClick();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.f12264g.onTouchEvent(motionEvent);
    }

    /* renamed from: a */
    private void m13074a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            m13077b(attributeSet);
        }
        this.f12276s = new DecimalFormat("#.#");
        m13078c();
        m13080d();
        m13081e();
        this.f12264g = new GestureDetector(getContext(), new C26221(this));
    }

    /* renamed from: b */
    private void m13077b(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.MonthCalendarView);
        if (obtainStyledAttributes != null) {
            this.f12258a = obtainStyledAttributes.getColor(0, this.f12258a);
            this.f12260c = obtainStyledAttributes.getColor(1, this.f12260c);
            this.f12261d = obtainStyledAttributes.getColor(2, this.f12261d);
            this.f12259b = obtainStyledAttributes.getInteger(3, this.f12259b);
            this.f12262e = obtainStyledAttributes.getInteger(4, this.f12262e);
            this.f12263f = obtainStyledAttributes.getInteger(5, this.f12263f);
            obtainStyledAttributes.recycle();
        }
    }

    /* renamed from: c */
    private void m13078c() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f12265h = new Paint();
        this.f12265h.setAntiAlias(true);
        this.f12265h.setTextSize(displayMetrics.scaledDensity * ((float) this.f12259b));
    }

    /* renamed from: d */
    private void m13080d() {
        this.f12266i = getWidth() / 7;
        this.f12267j = this.f12266i;
        this.f12268k = (int) ((((double) this.f12266i) * 0.5d) * 0.8d);
    }

    /* renamed from: e */
    private void m13081e() {
        Calendar instance = Calendar.getInstance();
        this.f12270m = instance.get(1);
        this.f12271n = instance.get(2);
        this.f12272o = instance.get(5);
    }

    /* renamed from: a */
    private void m13072a(int i, int i2, Canvas canvas, int i3) {
        this.f12265h.setColor(i3);
        canvas.drawCircle((float) (((double) (this.f12266i * i2)) + (((double) this.f12266i) * 0.5d)), (float) (((double) (this.f12267j * i)) + (((double) this.f12267j) * 0.75d)), (float) this.f12269l, this.f12265h);
    }

    /* renamed from: a */
    private void m13073a(Canvas canvas) {
        int i;
        int i2;
        if (this.f12271n == 0) {
            i = this.f12270m - 1;
            i2 = 11;
        } else {
            i = this.f12270m;
            i2 = this.f12271n - 1;
        }
        this.f12265h.setColor(this.f12261d);
        i = C2554c.m12788a(i, i2);
        int b = C2554c.m12791b(this.f12270m, this.f12271n);
        for (i2 = 0; i2 < b - 1; i2++) {
            this.f12273p[0][i2] = ((i - b) + i2) + 2;
            String valueOf = String.valueOf(this.f12273p[0][i2]);
            canvas.drawText(valueOf, (float) ((int) (((float) (this.f12266i * i2)) + ((((float) this.f12266i) - this.f12265h.measureText(valueOf)) / 2.0f))), (float) ((int) (((float) (this.f12267j / 2)) - ((this.f12265h.ascent() + this.f12265h.descent()) / 2.0f))), this.f12265h);
        }
    }

    /* renamed from: b */
    private void m13076b(Canvas canvas) {
        int a = C2554c.m12788a(this.f12270m, this.f12271n);
        int b = C2554c.m12791b(this.f12270m, this.f12271n);
        for (int i = 0; i < a; i++) {
            Object obj;
            String str;
            String valueOf = String.valueOf(i + 1);
            int i2 = ((i + b) - 1) % 7;
            int i3 = ((i + b) - 1) / 7;
            this.f12273p[i3][i2] = i + 1;
            boolean z = false;
            boolean z2 = false;
            if (this.f12274q == null || !this.f12274q.containsKey(valueOf)) {
                obj = null;
                str = valueOf;
            } else {
                CalendarDto calendarDto = (CalendarDto) this.f12274q.get(valueOf);
                boolean isHasTrainingCourse = calendarDto.isHasTrainingCourse();
                if (calendarDto.getDistance() != 0.0d) {
                    String format;
                    if (this.f12277t) {
                        format = this.f12276s.format(calendarDto.getDistance() / 1000.0d);
                    } else {
                        format = this.f12276s.format(C1849a.m9638a(calendarDto.getDistance() / 1000.0d));
                    }
                    boolean z3 = isHasTrainingCourse;
                    obj = 1;
                    str = format;
                    z = calendarDto.isTraining();
                    z2 = z3;
                } else {
                    z2 = isHasTrainingCourse;
                    obj = null;
                    str = valueOf;
                }
            }
            int measureText = (int) (((float) (this.f12266i * i2)) + ((((float) this.f12266i) - this.f12265h.measureText(str)) / 2.0f));
            int ascent = (int) (((float) ((this.f12267j * i3) + (this.f12267j / 2))) - ((this.f12265h.ascent() + this.f12265h.descent()) / 2.0f));
            if (obj != null) {
                int i4 = this.f12266i * i2;
                int i5 = this.f12267j * i3;
                int i6 = this.f12266i + i4;
                int i7 = this.f12267j + i5;
                this.f12265h.setColor(this.f12260c);
                canvas.drawCircle((float) ((i4 + i6) / 2), (float) ((i5 + i7) / 2), (float) this.f12268k, this.f12265h);
                if (z) {
                    m13072a(i3, i2, canvas, this.f12263f);
                }
            }
            if (z2) {
                m13072a(i3, i2, canvas, this.f12262e);
            }
            this.f12265h.setColor(this.f12258a);
            canvas.drawText(str, (float) measureText, (float) ascent, this.f12265h);
        }
    }

    /* renamed from: c */
    private void m13079c(Canvas canvas) {
        this.f12265h.setColor(this.f12261d);
        int a = C2554c.m12788a(this.f12270m, this.f12271n);
        int b = C2554c.m12791b(this.f12270m, this.f12271n);
        int i = (42 - a) - (b - 1);
        if (i >= 7) {
            i -= 7;
        }
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = (((b - 1) + a) + i2) % 7;
            int i4 = (((b - 1) + a) + i2) / 7;
            this.f12273p[i4][i3] = i2 + 1;
            String valueOf = String.valueOf(this.f12273p[i4][i3]);
            canvas.drawText(valueOf, (float) ((int) (((float) (i3 * this.f12266i)) + ((((float) this.f12266i) - this.f12265h.measureText(valueOf)) / 2.0f))), (float) ((int) (((float) ((i4 * this.f12267j) + (this.f12267j / 2))) - ((this.f12265h.ascent() + this.f12265h.descent()) / 2.0f))), this.f12265h);
        }
    }

    /* renamed from: f */
    private void m13082f() {
        this.f12273p = (int[][]) Array.newInstance(Integer.TYPE, new int[]{6, 7});
    }

    /* renamed from: a */
    public void m13083a() {
        if (this.f12271n == 0) {
            this.f12270m--;
            this.f12271n = 11;
        } else {
            this.f12271n--;
        }
        this.f12274q.clear();
        invalidate();
    }

    /* renamed from: b */
    public void m13084b() {
        if (this.f12271n == 11) {
            this.f12270m++;
            this.f12271n = 0;
        } else {
            this.f12271n++;
        }
        this.f12274q.clear();
        invalidate();
    }

    public String getDate() {
        return C2555d.m12806c(this.f12270m, this.f12271n);
    }

    public int getViewHeight() {
        int a = (C2554c.m12788a(this.f12270m, this.f12271n) + C2554c.m12791b(this.f12270m, this.f12271n)) - 1;
        return (a % 7 == 0 ? a / 7 : (a / 7) + 1) * this.f12267j;
    }

    /* renamed from: a */
    private void m13071a(int i, int i2) {
        int i3 = 11;
        if (i2 <= getHeight()) {
            int i4 = i2 / this.f12267j;
            int i5 = i / this.f12266i;
            int a = C2554c.m12788a(this.f12270m, this.f12271n);
            int b = C2554c.m12791b(this.f12270m, this.f12271n);
            int i6 = this.f12270m;
            int i7 = this.f12271n;
            int i8 = ((a + b) - 1) / 7;
            int i9 = ((a + b) - 1) % 7;
            a = (((i4 * 7) + i5) - (b - 1)) + 1;
            if (i4 == 0 && i5 < b - 1) {
                if (this.f12271n == 0) {
                    a = this.f12270m - 1;
                } else {
                    a = this.f12270m;
                    i3 = this.f12271n - 1;
                }
                int a2 = (C2554c.m12788a(a, i3) - ((b - 1) - i5)) + 1;
                i7 = a;
                a = i3;
                i3 = a2;
            } else if (i4 != i8 || i5 < i9) {
                i3 = a;
                a = i7;
                i7 = i6;
            } else {
                if (this.f12271n == 11) {
                    a = this.f12270m + 1;
                    i3 = 0;
                } else {
                    a = this.f12270m;
                    i3 = this.f12271n + 1;
                }
                i7 = a;
                a = i3;
                i3 = (i5 - i9) + 1;
            }
            Calendar instance = Calendar.getInstance();
            instance.set(i7, a, i3);
            String valueOf = String.valueOf(i3);
            double d;
            if (this.f12274q == null || !this.f12274q.containsKey(valueOf)) {
                d = 0.0d;
            } else {
                d = ((CalendarDto) this.f12274q.get(valueOf)).getDistance();
            }
            if (this.f12275r != null && r0 > 0.0d) {
                this.f12275r.mo3482a(instance.getTime());
            }
        }
    }

    public void setMonthClickListener(C2381a c2381a) {
        this.f12275r = c2381a;
    }

    public void setCalendars(HashMap<String, CalendarDto> hashMap) {
        this.f12274q = hashMap;
        invalidate();
    }

    public int getCurrentYear() {
        return this.f12270m;
    }

    public int getCurrentMonth() {
        return this.f12271n + 1;
    }

    public void setDisplayKm(boolean z) {
        this.f12277t = z;
    }
}
