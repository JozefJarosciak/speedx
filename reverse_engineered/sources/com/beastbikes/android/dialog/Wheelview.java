package com.beastbikes.android.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.os.Handler;
import android.os.Message;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.beastbikes.android.R$styleable;
import java.util.ArrayList;
import java.util.Iterator;

public class Wheelview extends View {
    @SuppressLint({"HandlerLeak"})
    /* renamed from: a */
    Handler f8135a = new C17803(this);
    /* renamed from: b */
    private float f8136b;
    /* renamed from: c */
    private float f8137c;
    /* renamed from: d */
    private boolean f8138d = false;
    /* renamed from: e */
    private ArrayList<C1781a> f8139e = new ArrayList();
    /* renamed from: f */
    private ArrayList<String> f8140f = new ArrayList();
    /* renamed from: g */
    private int f8141g;
    /* renamed from: h */
    private long f8142h = 0;
    /* renamed from: i */
    private long f8143i = 200;
    /* renamed from: j */
    private int f8144j = 100;
    /* renamed from: k */
    private Paint f8145k;
    /* renamed from: l */
    private int f8146l = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: m */
    private float f8147m = 14.0f;
    /* renamed from: n */
    private float f8148n = 22.0f;
    /* renamed from: o */
    private int f8149o = 50;
    /* renamed from: p */
    private int f8150p = 7;
    /* renamed from: q */
    private int f8151q = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: r */
    private int f8152r = SupportMenu.CATEGORY_MASK;
    /* renamed from: s */
    private float f8153s = 48.0f;
    /* renamed from: t */
    private C1782b f8154t;
    /* renamed from: u */
    private boolean f8155u = true;
    /* renamed from: v */
    private boolean f8156v = false;
    /* renamed from: w */
    private boolean f8157w = false;

    /* renamed from: com.beastbikes.android.dialog.Wheelview$f */
    public interface C1703f {
        /* renamed from: a */
        void mo3218a(int i, String str);
    }

    /* renamed from: com.beastbikes.android.dialog.Wheelview$d */
    public interface C1707d {
        /* renamed from: a */
        void mo3220a(String str);
    }

    /* renamed from: com.beastbikes.android.dialog.Wheelview$e */
    public interface C1708e {
        /* renamed from: a */
        void mo3221a(String str, String str2);
    }

    /* renamed from: com.beastbikes.android.dialog.Wheelview$3 */
    class C17803 extends Handler {
        /* renamed from: a */
        final /* synthetic */ Wheelview f8126a;

        C17803(Wheelview wheelview) {
            this.f8126a = wheelview;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    this.f8126a.invalidate();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: com.beastbikes.android.dialog.Wheelview$a */
    private class C1781a {
        /* renamed from: a */
        public int f8127a = 0;
        /* renamed from: b */
        public String f8128b = "";
        /* renamed from: c */
        public int f8129c = 0;
        /* renamed from: d */
        public int f8130d = 0;
        /* renamed from: e */
        public int f8131e = 0;
        /* renamed from: f */
        final /* synthetic */ Wheelview f8132f;
        /* renamed from: g */
        private Paint f8133g;
        /* renamed from: h */
        private Rect f8134h;

        public C1781a(Wheelview wheelview) {
            this.f8132f = wheelview;
        }

        /* renamed from: a */
        public void m9439a(Canvas canvas) {
            if (this.f8133g == null) {
                this.f8133g = new Paint();
                this.f8133g.setAntiAlias(true);
            }
            if (this.f8134h == null) {
                this.f8134h = new Rect();
            }
            if (m9442b()) {
                this.f8133g.setColor(this.f8132f.f8152r);
                float c = m9443c();
                if (c <= 0.0f) {
                    c *= -1.0f;
                }
                this.f8133g.setTextSize(((1.0f - (c / ((float) this.f8132f.f8149o))) * (this.f8132f.f8148n - this.f8132f.f8147m)) + this.f8132f.f8147m);
            } else {
                this.f8133g.setColor(this.f8132f.f8151q);
                this.f8133g.setTextSize(this.f8132f.f8147m);
            }
            this.f8133g.getTextBounds(this.f8128b, 0, this.f8128b.length(), this.f8134h);
            if (m9440a()) {
                canvas.drawText(this.f8128b, (((float) this.f8129c) + (this.f8132f.f8136b / 2.0f)) - ((float) (this.f8134h.width() / 2)), (float) (((this.f8130d + this.f8131e) + (this.f8132f.f8149o / 2)) + (this.f8134h.height() / 2)), this.f8133g);
            }
        }

        /* renamed from: a */
        public boolean m9440a() {
            if (((float) (this.f8130d + this.f8131e)) > this.f8132f.f8137c || ((this.f8130d + this.f8131e) + (this.f8132f.f8149o / 2)) + (this.f8134h.height() / 2) < 0) {
                return false;
            }
            return true;
        }

        /* renamed from: a */
        public void m9438a(int i) {
            this.f8131e = i;
        }

        /* renamed from: b */
        public void m9441b(int i) {
            this.f8131e = 0;
            this.f8130d += i;
        }

        /* renamed from: b */
        public boolean m9442b() {
            if (((float) (this.f8130d + this.f8131e)) >= ((this.f8132f.f8137c / 2.0f) - ((float) (this.f8132f.f8149o / 2))) + 2.0f && ((float) (this.f8130d + this.f8131e)) <= ((this.f8132f.f8137c / 2.0f) + ((float) (this.f8132f.f8149o / 2))) - 2.0f) {
                return true;
            }
            if (((float) ((this.f8130d + this.f8131e) + this.f8132f.f8149o)) >= ((this.f8132f.f8137c / 2.0f) - ((float) (this.f8132f.f8149o / 2))) + 2.0f && ((float) ((this.f8130d + this.f8131e) + this.f8132f.f8149o)) <= ((this.f8132f.f8137c / 2.0f) + ((float) (this.f8132f.f8149o / 2))) - 2.0f) {
                return true;
            }
            if (((float) (this.f8130d + this.f8131e)) > ((this.f8132f.f8137c / 2.0f) - ((float) (this.f8132f.f8149o / 2))) + 2.0f || ((float) ((this.f8130d + this.f8131e) + this.f8132f.f8149o)) < ((this.f8132f.f8137c / 2.0f) + ((float) (this.f8132f.f8149o / 2))) - 2.0f) {
                return false;
            }
            return true;
        }

        /* renamed from: c */
        public float m9443c() {
            return ((this.f8132f.f8137c / 2.0f) - ((float) (this.f8132f.f8149o / 2))) - ((float) (this.f8130d + this.f8131e));
        }
    }

    /* renamed from: com.beastbikes.android.dialog.Wheelview$b */
    public interface C1782b {
        /* renamed from: a */
        void m9444a(int i, String str);

        /* renamed from: b */
        void m9445b(int i, String str);
    }

    /* renamed from: com.beastbikes.android.dialog.Wheelview$c */
    public interface C1783c {
        /* renamed from: a */
        void m9446a(String str, String str2);
    }

    public Wheelview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m9450a(context, attributeSet);
        m9453b();
    }

    public Wheelview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m9450a(context, attributeSet);
        m9453b();
    }

    public Wheelview(Context context) {
        super(context);
        m9453b();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f8155u) {
            int y = (int) motionEvent.getY();
            switch (motionEvent.getAction()) {
                case 0:
                    this.f8138d = true;
                    this.f8141g = (int) motionEvent.getY();
                    this.f8142h = System.currentTimeMillis();
                    break;
                case 1:
                    int i = y - this.f8141g;
                    if (i <= 0) {
                        i *= -1;
                    }
                    if (System.currentTimeMillis() - this.f8142h >= this.f8143i || r0 <= this.f8144j) {
                        m9463d(y - this.f8141g);
                    } else {
                        m9449a(y - this.f8141g);
                    }
                    m9448a();
                    this.f8138d = false;
                    break;
                case 2:
                    m9454b(y - this.f8141g);
                    m9459c();
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m9455b(canvas);
        m9451a(canvas);
        m9461c(canvas);
    }

    /* renamed from: a */
    private synchronized void m9451a(Canvas canvas) {
        if (!this.f8157w) {
            try {
                Iterator it = this.f8139e.iterator();
                while (it.hasNext()) {
                    ((C1781a) it.next()).m9439a(canvas);
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
        this.f8136b = (float) getWidth();
        if (this.f8136b != 0.0f) {
            setMeasuredDimension(getWidth(), this.f8150p * this.f8149o);
            this.f8136b = (float) getWidth();
        }
    }

    /* renamed from: a */
    private synchronized void m9449a(final int i) {
        new Thread(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ Wheelview f8123b;

            public void run() {
                int i = 0;
                while (i < this.f8123b.f8149o * 5) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.f8123b.m9460c(i > 0 ? i : i * -1);
                    i += 10;
                }
                this.f8123b.m9463d(i > 0 ? i - 10 : (i * -1) + 10);
                this.f8123b.m9448a();
            }
        }).start();
    }

    /* renamed from: a */
    private void m9448a() {
        if (this.f8156v) {
            Iterator it = this.f8139e.iterator();
            while (it.hasNext()) {
                if (((C1781a) it.next()).m9442b()) {
                    return;
                }
            }
            int c = (int) ((C1781a) this.f8139e.get(0)).m9443c();
            if (c < 0) {
                m9467f(c);
            } else {
                m9467f((int) ((C1781a) this.f8139e.get(this.f8139e.size() - 1)).m9443c());
            }
            it = this.f8139e.iterator();
            while (it.hasNext()) {
                C1781a c1781a = (C1781a) it.next();
                if (c1781a.m9442b()) {
                    if (this.f8154t != null) {
                        this.f8154t.m9444a(c1781a.f8127a, c1781a.f8128b);
                        return;
                    }
                    return;
                }
            }
        }
    }

    /* renamed from: b */
    private void m9453b() {
        this.f8157w = true;
        this.f8139e.clear();
        for (int i = 0; i < this.f8140f.size(); i++) {
            C1781a c1781a = new C1781a(this);
            c1781a.f8127a = i;
            c1781a.f8128b = (String) this.f8140f.get(i);
            c1781a.f8129c = 0;
            c1781a.f8130d = this.f8149o * i;
            this.f8139e.add(c1781a);
        }
        this.f8157w = false;
    }

    /* renamed from: b */
    private void m9454b(int i) {
        Iterator it = this.f8139e.iterator();
        while (it.hasNext()) {
            ((C1781a) it.next()).m9438a(i);
        }
        invalidate();
    }

    /* renamed from: c */
    private void m9460c(int i) {
        Iterator it = this.f8139e.iterator();
        while (it.hasNext()) {
            ((C1781a) it.next()).m9438a(i);
        }
        Message message = new Message();
        message.what = 1;
        this.f8135a.sendMessage(message);
    }

    /* renamed from: d */
    private void m9463d(int i) {
        int i2 = 0;
        int i3;
        if (i > 0) {
            i3 = 0;
            while (i3 < this.f8139e.size()) {
                if (((C1781a) this.f8139e.get(i3)).m9442b()) {
                    i2 = (int) ((C1781a) this.f8139e.get(i3)).m9443c();
                    if (this.f8154t != null) {
                        this.f8154t.m9444a(((C1781a) this.f8139e.get(i3)).f8127a, ((C1781a) this.f8139e.get(i3)).f8128b);
                    }
                } else {
                    i3++;
                }
            }
        } else {
            i3 = this.f8139e.size() - 1;
            while (i3 >= 0) {
                if (((C1781a) this.f8139e.get(i3)).m9442b()) {
                    i2 = (int) ((C1781a) this.f8139e.get(i3)).m9443c();
                    if (this.f8154t != null) {
                        this.f8154t.m9444a(((C1781a) this.f8139e.get(i3)).f8127a, ((C1781a) this.f8139e.get(i3)).f8128b);
                    }
                } else {
                    i3--;
                }
            }
        }
        Iterator it = this.f8139e.iterator();
        while (it.hasNext()) {
            ((C1781a) it.next()).m9441b(i + 0);
        }
        m9465e(i2);
        Message message = new Message();
        message.what = 1;
        this.f8135a.sendMessage(message);
    }

    /* renamed from: e */
    private synchronized void m9465e(final int i) {
        new Thread(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ Wheelview f8125b;

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
                    it = this.f8125b.f8139e.iterator();
                    while (it.hasNext()) {
                        ((C1781a) it.next()).m9441b(1 * i2);
                    }
                    message = new Message();
                    message.what = 1;
                    this.f8125b.f8135a.sendMessage(message);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i = i3;
                }
                it = this.f8125b.f8139e.iterator();
                while (it.hasNext()) {
                    ((C1781a) it.next()).m9441b(i3 * i2);
                }
                message = new Message();
                message.what = 1;
                this.f8125b.f8135a.sendMessage(message);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                Iterator it2 = this.f8125b.f8139e.iterator();
                while (it2.hasNext()) {
                    C1781a c1781a = (C1781a) it2.next();
                    if (c1781a.m9442b()) {
                        if (this.f8125b.f8154t != null) {
                            this.f8125b.f8154t.m9444a(c1781a.f8127a, c1781a.f8128b);
                            return;
                        }
                        return;
                    }
                }
            }
        }).start();
    }

    /* renamed from: f */
    private void m9467f(int i) {
        Iterator it = this.f8139e.iterator();
        while (it.hasNext()) {
            ((C1781a) it.next()).m9441b(i);
        }
        Message message = new Message();
        message.what = 1;
        this.f8135a.sendMessage(message);
    }

    /* renamed from: c */
    private void m9459c() {
        if (this.f8154t != null) {
            Iterator it = this.f8139e.iterator();
            while (it.hasNext()) {
                C1781a c1781a = (C1781a) it.next();
                if (c1781a.m9442b()) {
                    this.f8154t.m9445b(c1781a.f8127a, c1781a.f8128b);
                }
            }
        }
    }

    /* renamed from: b */
    private void m9455b(Canvas canvas) {
        if (this.f8145k == null) {
            this.f8145k = new Paint();
            this.f8145k.setColor(this.f8146l);
            this.f8145k.setAntiAlias(true);
            this.f8145k.setStrokeWidth(1.0f);
        }
        canvas.drawLine(0.0f, ((this.f8137c / 2.0f) - ((float) (this.f8149o / 2))) + 2.0f, this.f8136b, ((this.f8137c / 2.0f) - ((float) (this.f8149o / 2))) + 2.0f, this.f8145k);
        canvas.drawLine(0.0f, ((this.f8137c / 2.0f) + ((float) (this.f8149o / 2))) - 2.0f, this.f8136b, ((this.f8137c / 2.0f) + ((float) (this.f8149o / 2))) - 2.0f, this.f8145k);
    }

    /* renamed from: c */
    private void m9461c(Canvas canvas) {
        Shader linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, this.f8153s, 15921906, 15921906, TileMode.MIRROR);
        Paint paint = new Paint();
        paint.setShader(linearGradient);
        canvas.drawRect(0.0f, 0.0f, this.f8136b, this.f8153s, paint);
        linearGradient = new LinearGradient(0.0f, this.f8137c - this.f8153s, 0.0f, this.f8137c, 15921906, 15921906, TileMode.MIRROR);
        Paint paint2 = new Paint();
        paint2.setShader(linearGradient);
        canvas.drawRect(0.0f, this.f8137c - this.f8153s, this.f8136b, this.f8137c, paint2);
    }

    /* renamed from: a */
    private void m9450a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.NumberPicker);
        this.f8149o = (int) obtainStyledAttributes.getDimension(4, 32.0f);
        this.f8147m = obtainStyledAttributes.getDimension(1, 14.0f);
        this.f8148n = obtainStyledAttributes.getDimension(3, 22.0f);
        this.f8150p = obtainStyledAttributes.getInt(5, 7);
        this.f8151q = obtainStyledAttributes.getColor(0, ViewCompat.MEASURED_STATE_MASK);
        this.f8152r = obtainStyledAttributes.getColor(2, SupportMenu.CATEGORY_MASK);
        this.f8146l = obtainStyledAttributes.getColor(6, ViewCompat.MEASURED_STATE_MASK);
        this.f8153s = obtainStyledAttributes.getDimension(7, 48.0f);
        this.f8156v = obtainStyledAttributes.getBoolean(8, false);
        this.f8155u = obtainStyledAttributes.getBoolean(9, true);
        obtainStyledAttributes.recycle();
        this.f8137c = (float) (this.f8150p * this.f8149o);
    }

    public void setData(ArrayList<String> arrayList) {
        this.f8140f = arrayList;
        m9453b();
    }

    public int getSelected() {
        Iterator it = this.f8139e.iterator();
        while (it.hasNext()) {
            C1781a c1781a = (C1781a) it.next();
            if (c1781a.m9442b()) {
                return c1781a.f8127a;
            }
        }
        return -1;
    }

    public String getSelectedText() {
        Iterator it = this.f8139e.iterator();
        while (it.hasNext()) {
            C1781a c1781a = (C1781a) it.next();
            if (c1781a.m9442b()) {
                return c1781a.f8128b;
            }
        }
        return "";
    }

    public void setEnable(boolean z) {
        this.f8155u = z;
    }

    public void setDefault(int i) {
        m9467f((int) ((C1781a) this.f8139e.get(i)).m9443c());
    }

    public int getListSize() {
        if (this.f8139e == null) {
            return 0;
        }
        return this.f8139e.size();
    }

    public void setOnSelectListener(C1782b c1782b) {
        this.f8154t = c1782b;
    }
}
