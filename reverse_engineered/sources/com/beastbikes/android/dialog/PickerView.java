package com.beastbikes.android.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.beastbikes.android.R$styleable;
import java.util.ArrayList;
import java.util.Iterator;

public class PickerView extends View {
    @SuppressLint({"HandlerLeak"})
    /* renamed from: a */
    Handler f8096a = new C17753(this);
    /* renamed from: b */
    private float f8097b;
    /* renamed from: c */
    private float f8098c;
    /* renamed from: d */
    private boolean f8099d = false;
    /* renamed from: e */
    private ArrayList<C1776a> f8100e = new ArrayList();
    /* renamed from: f */
    private ArrayList<String> f8101f = new ArrayList();
    /* renamed from: g */
    private int f8102g;
    /* renamed from: h */
    private long f8103h = 0;
    /* renamed from: i */
    private long f8104i = 200;
    /* renamed from: j */
    private int f8105j = 100;
    /* renamed from: k */
    private Paint f8106k;
    /* renamed from: l */
    private int f8107l = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: m */
    private float f8108m = 14.0f;
    /* renamed from: n */
    private float f8109n = 22.0f;
    /* renamed from: o */
    private int f8110o = 50;
    /* renamed from: p */
    private int f8111p = 7;
    /* renamed from: q */
    private int f8112q = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: r */
    private int f8113r = SupportMenu.CATEGORY_MASK;
    /* renamed from: s */
    private float f8114s = 48.0f;
    /* renamed from: t */
    private C1777b f8115t;
    /* renamed from: u */
    private boolean f8116u = true;
    /* renamed from: v */
    private boolean f8117v = false;
    /* renamed from: w */
    private boolean f8118w = false;
    /* renamed from: x */
    private String f8119x;
    /* renamed from: y */
    private float f8120y;
    /* renamed from: z */
    private int f8121z;

    /* renamed from: com.beastbikes.android.dialog.PickerView$3 */
    class C17753 extends Handler {
        /* renamed from: a */
        final /* synthetic */ PickerView f8087a;

        C17753(PickerView pickerView) {
            this.f8087a = pickerView;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    this.f8087a.invalidate();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: com.beastbikes.android.dialog.PickerView$a */
    private class C1776a {
        /* renamed from: a */
        public int f8088a = 0;
        /* renamed from: b */
        public String f8089b = "";
        /* renamed from: c */
        public int f8090c = 0;
        /* renamed from: d */
        public int f8091d = 0;
        /* renamed from: e */
        public int f8092e = 0;
        /* renamed from: f */
        final /* synthetic */ PickerView f8093f;
        /* renamed from: g */
        private Paint f8094g;
        /* renamed from: h */
        private Rect f8095h;

        public C1776a(PickerView pickerView) {
            this.f8093f = pickerView;
        }

        /* renamed from: a */
        public void m9405a(Canvas canvas) {
            if (this.f8094g == null) {
                this.f8094g = new Paint();
                this.f8094g.setAntiAlias(true);
            }
            if (this.f8095h == null) {
                this.f8095h = new Rect();
            }
            if (m9408b()) {
                this.f8094g.setColor(this.f8093f.f8113r);
                float c = m9409c();
                if (c <= 0.0f) {
                    c *= -1.0f;
                }
                this.f8094g.setTextSize(((1.0f - (c / ((float) this.f8093f.f8110o))) * (this.f8093f.f8109n - this.f8093f.f8108m)) + this.f8093f.f8108m);
            } else if (this.f8093f.f8111p == 3) {
                this.f8094g.setColor(this.f8093f.f8112q);
                this.f8094g.setTextSize(this.f8093f.f8108m);
            } else {
                this.f8094g.setColor(this.f8093f.f8112q);
                this.f8094g.setTextSize(this.f8093f.f8108m);
            }
            this.f8094g.getTextBounds(this.f8089b, 0, this.f8089b.length(), this.f8095h);
            if (m9406a()) {
                canvas.drawText(this.f8089b, (((float) this.f8090c) + (this.f8093f.f8097b / 2.0f)) - ((float) (this.f8095h.width() / 2)), (float) (((this.f8091d + this.f8092e) + (this.f8093f.f8110o / 2)) + (this.f8095h.height() / 2)), this.f8094g);
            }
        }

        /* renamed from: a */
        public boolean m9406a() {
            if (((float) (this.f8091d + this.f8092e)) > this.f8093f.f8098c || ((this.f8091d + this.f8092e) + (this.f8093f.f8110o / 2)) + (this.f8095h.height() / 2) < 0) {
                return false;
            }
            return true;
        }

        /* renamed from: a */
        public void m9404a(int i) {
            this.f8092e = i;
        }

        /* renamed from: b */
        public void m9407b(int i) {
            this.f8092e = 0;
            this.f8091d += i;
        }

        /* renamed from: b */
        public boolean m9408b() {
            if (((float) (this.f8091d + this.f8092e)) >= ((this.f8093f.f8098c / 2.0f) - ((float) (this.f8093f.f8110o / 2))) + 2.0f && ((float) (this.f8091d + this.f8092e)) <= ((this.f8093f.f8098c / 2.0f) + ((float) (this.f8093f.f8110o / 2))) - 2.0f) {
                return true;
            }
            if (((float) ((this.f8091d + this.f8092e) + this.f8093f.f8110o)) >= ((this.f8093f.f8098c / 2.0f) - ((float) (this.f8093f.f8110o / 2))) + 2.0f && ((float) ((this.f8091d + this.f8092e) + this.f8093f.f8110o)) <= ((this.f8093f.f8098c / 2.0f) + ((float) (this.f8093f.f8110o / 2))) - 2.0f) {
                return true;
            }
            if (((float) (this.f8091d + this.f8092e)) > ((this.f8093f.f8098c / 2.0f) - ((float) (this.f8093f.f8110o / 2))) + 2.0f || ((float) ((this.f8091d + this.f8092e) + this.f8093f.f8110o)) < ((this.f8093f.f8098c / 2.0f) + ((float) (this.f8093f.f8110o / 2))) - 2.0f) {
                return false;
            }
            return true;
        }

        /* renamed from: c */
        public float m9409c() {
            return ((this.f8093f.f8098c / 2.0f) - ((float) (this.f8093f.f8110o / 2))) - ((float) (this.f8091d + this.f8092e));
        }
    }

    /* renamed from: com.beastbikes.android.dialog.PickerView$b */
    public interface C1777b {
        /* renamed from: a */
        void mo3242a(int i, String str);

        /* renamed from: b */
        void mo3243b(int i, String str);
    }

    public PickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m9415a(context, attributeSet);
        m9418b();
    }

    public PickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m9415a(context, attributeSet);
        m9418b();
    }

    public PickerView(Context context) {
        super(context);
        m9418b();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f8116u) {
            int y = (int) motionEvent.getY();
            switch (motionEvent.getAction()) {
                case 0:
                    this.f8099d = true;
                    this.f8102g = (int) motionEvent.getY();
                    this.f8103h = System.currentTimeMillis();
                    break;
                case 1:
                    int i = y - this.f8102g;
                    if (i <= 0) {
                        i *= -1;
                    }
                    if (System.currentTimeMillis() - this.f8103h >= this.f8104i || r0 <= this.f8105j) {
                        m9428d(y - this.f8102g);
                    } else {
                        m9414a(y - this.f8102g);
                    }
                    m9413a();
                    this.f8099d = false;
                    break;
                case 2:
                    m9419b(y - this.f8102g);
                    m9424c();
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m9420b(canvas);
        m9426c(canvas);
        m9416a(canvas);
    }

    /* renamed from: a */
    private synchronized void m9416a(Canvas canvas) {
        if (!this.f8118w) {
            try {
                Iterator it = this.f8100e.iterator();
                while (it.hasNext()) {
                    ((C1776a) it.next()).m9405a(canvas);
                }
            } catch (Exception e) {
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f8097b = (float) getWidth();
        if (this.f8097b != 0.0f) {
            setMeasuredDimension(getWidth(), this.f8111p * this.f8110o);
            this.f8097b = (float) getWidth();
        }
    }

    /* renamed from: a */
    private synchronized void m9414a(final int i) {
        new Thread(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ PickerView f8084b;

            public void run() {
                int i = 0;
                while (i < this.f8084b.f8110o * 5) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.f8084b.m9425c(i > 0 ? i : i * -1);
                    i += 10;
                }
                this.f8084b.m9428d(i > 0 ? i - 10 : (i * -1) + 10);
                this.f8084b.m9413a();
            }
        }).start();
    }

    /* renamed from: a */
    private void m9413a() {
        if (this.f8117v) {
            Iterator it = this.f8100e.iterator();
            while (it.hasNext()) {
                if (((C1776a) it.next()).m9408b()) {
                    return;
                }
            }
            int c = (int) ((C1776a) this.f8100e.get(0)).m9409c();
            if (c < 0) {
                m9432f(c);
            } else {
                m9432f((int) ((C1776a) this.f8100e.get(this.f8100e.size() - 1)).m9409c());
            }
            it = this.f8100e.iterator();
            while (it.hasNext()) {
                C1776a c1776a = (C1776a) it.next();
                if (c1776a.m9408b()) {
                    if (this.f8115t != null) {
                        this.f8115t.mo3242a(c1776a.f8088a, c1776a.f8089b);
                        return;
                    }
                    return;
                }
            }
        }
    }

    /* renamed from: b */
    private void m9418b() {
        this.f8118w = true;
        this.f8100e.clear();
        for (int i = 0; i < this.f8101f.size(); i++) {
            C1776a c1776a = new C1776a(this);
            c1776a.f8088a = i;
            c1776a.f8089b = (String) this.f8101f.get(i);
            c1776a.f8090c = 0;
            c1776a.f8091d = this.f8110o * i;
            this.f8100e.add(c1776a);
        }
        this.f8118w = false;
    }

    /* renamed from: b */
    private void m9419b(int i) {
        Iterator it = this.f8100e.iterator();
        while (it.hasNext()) {
            ((C1776a) it.next()).m9404a(i);
        }
        invalidate();
    }

    /* renamed from: c */
    private void m9425c(int i) {
        Iterator it = this.f8100e.iterator();
        while (it.hasNext()) {
            ((C1776a) it.next()).m9404a(i);
        }
        Message message = new Message();
        message.what = 1;
        this.f8096a.sendMessage(message);
    }

    /* renamed from: d */
    private void m9428d(int i) {
        int i2 = 0;
        int i3;
        if (i > 0) {
            i3 = 0;
            while (i3 < this.f8100e.size()) {
                if (((C1776a) this.f8100e.get(i3)).m9408b()) {
                    i2 = (int) ((C1776a) this.f8100e.get(i3)).m9409c();
                    if (this.f8115t != null) {
                        this.f8115t.mo3242a(((C1776a) this.f8100e.get(i3)).f8088a, ((C1776a) this.f8100e.get(i3)).f8089b);
                    }
                } else {
                    i3++;
                }
            }
        } else {
            i3 = this.f8100e.size() - 1;
            while (i3 >= 0) {
                if (((C1776a) this.f8100e.get(i3)).m9408b()) {
                    i2 = (int) ((C1776a) this.f8100e.get(i3)).m9409c();
                    if (this.f8115t != null) {
                        this.f8115t.mo3242a(((C1776a) this.f8100e.get(i3)).f8088a, ((C1776a) this.f8100e.get(i3)).f8089b);
                    }
                } else {
                    i3--;
                }
            }
        }
        Iterator it = this.f8100e.iterator();
        while (it.hasNext()) {
            ((C1776a) it.next()).m9407b(i + 0);
        }
        m9430e(i2);
        Message message = new Message();
        message.what = 1;
        this.f8096a.sendMessage(message);
    }

    /* renamed from: e */
    private synchronized void m9430e(final int i) {
        new Thread(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ PickerView f8086b;

            public void run() {
                Iterator it;
                Message message;
                int i = i > 0 ? i : i * -1;
                int i2 = i > 0 ? 1 : -1;
                while (true) {
                    int i3 = i - 1;
                    if (i3 <= 0) {
                        break;
                    }
                    it = this.f8086b.f8100e.iterator();
                    while (it.hasNext()) {
                        ((C1776a) it.next()).m9407b(1 * i2);
                    }
                    message = new Message();
                    message.what = 1;
                    this.f8086b.f8096a.sendMessage(message);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i = i3;
                }
                it = this.f8086b.f8100e.iterator();
                while (it.hasNext()) {
                    ((C1776a) it.next()).m9407b(i3 * i2);
                }
                message = new Message();
                message.what = 1;
                this.f8086b.f8096a.sendMessage(message);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                Iterator it2 = this.f8086b.f8100e.iterator();
                while (it2.hasNext()) {
                    C1776a c1776a = (C1776a) it2.next();
                    if (c1776a.m9408b()) {
                        if (this.f8086b.f8115t != null) {
                            this.f8086b.f8115t.mo3242a(c1776a.f8088a, c1776a.f8089b);
                            return;
                        }
                        return;
                    }
                }
            }
        }).start();
    }

    /* renamed from: f */
    private void m9432f(int i) {
        Iterator it = this.f8100e.iterator();
        while (it.hasNext()) {
            ((C1776a) it.next()).m9407b(i);
        }
        Message message = new Message();
        message.what = 1;
        this.f8096a.sendMessage(message);
    }

    /* renamed from: c */
    private void m9424c() {
        if (this.f8115t != null) {
            Iterator it = this.f8100e.iterator();
            while (it.hasNext()) {
                C1776a c1776a = (C1776a) it.next();
                if (c1776a.m9408b()) {
                    this.f8115t.mo3243b(c1776a.f8088a, c1776a.f8089b);
                }
            }
        }
    }

    /* renamed from: b */
    private void m9420b(Canvas canvas) {
        if (this.f8106k == null) {
            this.f8106k = new Paint();
            this.f8106k.setColor(this.f8107l);
            this.f8106k.setAntiAlias(true);
            this.f8106k.setStrokeWidth(1.0f);
        }
        canvas.drawLine(this.f8097b / 3.0f, ((this.f8098c / 2.0f) - ((float) (this.f8110o / 2))) + 2.0f, (this.f8097b / 3.0f) * 2.0f, ((this.f8098c / 2.0f) - ((float) (this.f8110o / 2))) + 2.0f, this.f8106k);
        canvas.drawLine(this.f8097b / 3.0f, ((this.f8098c / 2.0f) + ((float) (this.f8110o / 2))) - 2.0f, (this.f8097b / 3.0f) * 2.0f, ((this.f8098c / 2.0f) + ((float) (this.f8110o / 2))) - 2.0f, this.f8106k);
    }

    /* renamed from: c */
    private void m9426c(Canvas canvas) {
        if (!TextUtils.isEmpty(this.f8119x)) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setTextSize(this.f8120y);
            paint.setColor(this.f8121z);
            Rect rect = new Rect();
            paint.getTextBounds(this.f8119x, 0, this.f8119x.length(), rect);
            canvas.drawText(this.f8119x, (this.f8097b / 4.0f) * 3.0f, ((this.f8098c / 2.0f) - ((float) (rect.height() / 2))) - ((float) rect.top), paint);
        }
    }

    /* renamed from: a */
    private void m9415a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.NumberPicker);
        this.f8110o = (int) obtainStyledAttributes.getDimension(4, 32.0f);
        this.f8108m = obtainStyledAttributes.getDimension(1, 14.0f);
        this.f8109n = obtainStyledAttributes.getDimension(3, 22.0f);
        this.f8111p = obtainStyledAttributes.getInt(5, 7);
        this.f8112q = obtainStyledAttributes.getColor(0, ViewCompat.MEASURED_STATE_MASK);
        this.f8113r = obtainStyledAttributes.getColor(2, SupportMenu.CATEGORY_MASK);
        this.f8107l = obtainStyledAttributes.getColor(6, ViewCompat.MEASURED_STATE_MASK);
        this.f8114s = obtainStyledAttributes.getDimension(7, 48.0f);
        this.f8117v = obtainStyledAttributes.getBoolean(8, false);
        this.f8116u = obtainStyledAttributes.getBoolean(9, true);
        this.f8119x = obtainStyledAttributes.getString(10);
        this.f8120y = obtainStyledAttributes.getDimension(11, 15.0f);
        this.f8121z = obtainStyledAttributes.getColor(12, -1);
        obtainStyledAttributes.recycle();
        this.f8098c = (float) (this.f8111p * this.f8110o);
    }

    public void setUnit(String str) {
        this.f8119x = str;
        invalidate();
    }

    public void setData(ArrayList<String> arrayList) {
        this.f8101f = arrayList;
        m9418b();
        Message message = new Message();
        message.what = 1;
        this.f8096a.sendMessage(message);
    }

    public int getSelected() {
        Iterator it = this.f8100e.iterator();
        while (it.hasNext()) {
            C1776a c1776a = (C1776a) it.next();
            if (c1776a.m9408b()) {
                return c1776a.f8088a;
            }
        }
        return -1;
    }

    public String getSelectedText() {
        Iterator it = this.f8100e.iterator();
        while (it.hasNext()) {
            C1776a c1776a = (C1776a) it.next();
            if (c1776a.m9408b()) {
                return c1776a.f8089b;
            }
        }
        return "";
    }

    public void setEnable(boolean z) {
        this.f8116u = z;
    }

    public void setDefault(int i) {
        m9432f((int) ((C1776a) this.f8100e.get(i)).m9409c());
    }

    public void setDefault(String str) {
        int i = 0;
        while (i < this.f8101f.size()) {
            if (str.equals(this.f8101f.get(i))) {
                break;
            }
            i++;
        }
        i = 0;
        setDefault(i);
    }

    public int getListSize() {
        if (this.f8100e == null) {
            return 0;
        }
        return this.f8100e.size();
    }

    public void setOnSelectListener(C1777b c1777b) {
        this.f8115t = c1777b;
    }
}
