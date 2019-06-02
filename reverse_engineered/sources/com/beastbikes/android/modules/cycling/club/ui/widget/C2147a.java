package com.beastbikes.android.modules.cycling.club.ui.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.Wheelview;
import com.beastbikes.android.dialog.Wheelview.C1783c;
import java.util.ArrayList;
import java.util.Calendar;

/* compiled from: ClubCalendarPopupWindow */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.a */
public class C2147a extends PopupWindow implements OnClickListener {
    /* renamed from: a */
    private View f10057a;
    /* renamed from: b */
    private TextView f10058b;
    /* renamed from: c */
    private TextView f10059c;
    /* renamed from: d */
    private TextView f10060d;
    /* renamed from: e */
    private Wheelview f10061e;
    /* renamed from: f */
    private Wheelview f10062f;
    /* renamed from: g */
    private C1783c f10063g;
    /* renamed from: h */
    private ArrayList<String> f10064h;
    /* renamed from: i */
    private ArrayList<String> f10065i;
    /* renamed from: j */
    private int f10066j;
    /* renamed from: k */
    private int f10067k;
    /* renamed from: l */
    private Activity f10068l;
    /* renamed from: m */
    private int f10069m = m_AppUI.V_WM_PERMCHECK;
    /* renamed from: n */
    private int f10070n = 2020;
    /* renamed from: o */
    private int f10071o;
    /* renamed from: p */
    private int f10072p;

    /* compiled from: ClubCalendarPopupWindow */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.a$1 */
    class C21461 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2147a f10056a;

        C21461(C2147a c2147a) {
            this.f10056a = c2147a;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int top = this.f10056a.f10057a.findViewById(C1373R.id.club_calendar_popup_window_view).getTop();
            int y = (int) motionEvent.getY();
            if (motionEvent.getAction() == 1 && y < top) {
                this.f10056a.dismiss();
            }
            return true;
        }
    }

    public C2147a(Activity activity, C1783c c1783c) {
        super(activity);
        this.f10068l = activity;
        this.f10057a = LayoutInflater.from(activity).inflate(C1373R.layout.club_calendar_popup_window, null);
        this.f10063g = c1783c;
        m11038a();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.club_calendar_popup_window_save_btn:
                if (this.f10063g != null) {
                    this.f10071o = this.f10061e.getSelected();
                    this.f10072p = this.f10062f.getSelected();
                    this.f10063g.m9446a(this.f10061e.getSelectedText(), this.f10062f.getSelectedText());
                    dismiss();
                    return;
                }
                return;
            case C1373R.id.club_calendar_popup_window_cancel_btn:
                dismiss();
                return;
            default:
                return;
        }
    }

    public void dismiss() {
        super.dismiss();
        m11039a(1.0f);
    }

    /* renamed from: a */
    private void m11039a(float f) {
        LayoutParams attributes = this.f10068l.getWindow().getAttributes();
        attributes.alpha = f;
        this.f10068l.getWindow().setAttributes(attributes);
    }

    /* renamed from: a */
    private void m11038a() {
        int i;
        this.f10058b = (TextView) this.f10057a.findViewById(C1373R.id.club_calendar_popup_window_cancel_btn);
        this.f10059c = (TextView) this.f10057a.findViewById(C1373R.id.club_calendar_popup_window_save_btn);
        this.f10060d = (TextView) this.f10057a.findViewById(C1373R.id.club_calendar_popup_window_title);
        this.f10061e = (Wheelview) this.f10057a.findViewById(C1373R.id.popup_window_wheel_year_view);
        this.f10062f = (Wheelview) this.f10057a.findViewById(C1373R.id.popup_window_wheel_month_view);
        this.f10058b.setOnClickListener(this);
        this.f10059c.setOnClickListener(this);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        int i2 = instance.get(1);
        int i3 = instance.get(2) + 1;
        this.f10064h = new ArrayList();
        int i4 = 0;
        for (i = this.f10069m; i <= this.f10070n; i++) {
            if (i == i2) {
                this.f10071o = i4;
            }
            this.f10064h.add(String.valueOf(i));
            i4++;
        }
        this.f10061e.setData(this.f10064h);
        this.f10065i = new ArrayList();
        i4 = 0;
        for (i = 1; i <= 12; i++) {
            if (i == i3) {
                this.f10072p = i4;
            }
            this.f10065i.add(String.valueOf(i));
            i4++;
        }
        this.f10062f.setData(this.f10065i);
        this.f10061e.setDefault(this.f10071o);
        this.f10062f.setDefault(this.f10072p);
        setContentView(this.f10057a);
        setWidth(-1);
        setHeight(-2);
        setFocusable(true);
        setAnimationStyle(C1373R.style.AnimBottom);
        setBackgroundDrawable(new ColorDrawable(0));
        this.f10057a.setOnTouchListener(new C21461(this));
    }

    public void showAtLocation(View view, int i, int i2, int i3) {
        super.showAtLocation(view, i, i2, i3);
        m11039a(0.5f);
    }

    /* renamed from: a */
    public void m11040a(int i) {
        int i2 = 0;
        for (int i3 = this.f10069m; i3 <= this.f10070n; i3++) {
            if (i == i3) {
                this.f10066j = i2;
                this.f10061e.setDefault(i2);
            }
            i2++;
        }
    }

    /* renamed from: b */
    public void m11041b(int i) {
        int i2 = 0;
        for (int i3 = 1; i3 <= 12; i3++) {
            if (i == i3) {
                this.f10067k = i2;
                this.f10062f.setDefault(i2);
            }
            i2++;
        }
    }
}
