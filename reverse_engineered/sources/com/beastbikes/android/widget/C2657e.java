package com.beastbikes.android.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.Wheelview;
import com.beastbikes.android.dialog.Wheelview.C1703f;
import java.util.ArrayList;

/* compiled from: WheelViewPopupWindow */
/* renamed from: com.beastbikes.android.widget.e */
public class C2657e extends PopupWindow implements OnClickListener {
    /* renamed from: a */
    private View f12398a;
    /* renamed from: b */
    private TextView f12399b;
    /* renamed from: c */
    private TextView f12400c;
    /* renamed from: d */
    private TextView f12401d;
    /* renamed from: e */
    private Wheelview f12402e;
    /* renamed from: f */
    private ViewGroup f12403f;
    /* renamed from: g */
    private C1703f f12404g;
    /* renamed from: h */
    private String f12405h;
    /* renamed from: i */
    private ArrayList<String> f12406i;
    /* renamed from: j */
    private int f12407j;
    /* renamed from: k */
    private Activity f12408k;

    /* compiled from: WheelViewPopupWindow */
    /* renamed from: com.beastbikes.android.widget.e$1 */
    class C26391 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2657e f12354a;

        C26391(C2657e c2657e) {
            this.f12354a = c2657e;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int top = this.f12354a.f12398a.findViewById(C1373R.id.popup_window_view).getTop();
            int y = (int) motionEvent.getY();
            if (motionEvent.getAction() == 1 && y < top) {
                this.f12354a.dismiss();
            }
            return true;
        }
    }

    public C2657e(Activity activity, ArrayList<String> arrayList, int i, C1703f c1703f) {
        super(activity);
        this.f12398a = activity.getLayoutInflater().inflate(C1373R.layout.speed_force_popup_window, null);
        this.f12404g = c1703f;
        this.f12406i = arrayList;
        this.f12407j = i;
        this.f12408k = activity;
        m13195a();
    }

    public C2657e(Activity activity, String str, ArrayList<String> arrayList, int i, C1703f c1703f) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        this.f12408k = activity;
        this.f12398a = layoutInflater.inflate(C1373R.layout.speed_force_popup_window, null);
        this.f12404g = c1703f;
        this.f12405h = str;
        this.f12406i = arrayList;
        this.f12407j = i;
        m13195a();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.popup_window_save_btn:
                if (this.f12404g != null) {
                    this.f12404g.mo3218a(this.f12402e.getSelected(), this.f12402e.getSelectedText());
                    dismiss();
                    return;
                }
                return;
            case C1373R.id.popup_window_cancel_btn:
                dismiss();
                return;
            default:
                return;
        }
    }

    public void dismiss() {
        super.dismiss();
        m13196a(1.0f);
    }

    /* renamed from: a */
    private void m13196a(float f) {
        LayoutParams attributes = this.f12408k.getWindow().getAttributes();
        attributes.alpha = f;
        this.f12408k.getWindow().setAttributes(attributes);
    }

    /* renamed from: a */
    private void m13195a() {
        this.f12403f = (ViewGroup) this.f12398a.findViewById(C1373R.id.popup_window_view);
        this.f12399b = (TextView) this.f12398a.findViewById(C1373R.id.popup_window_cancel_btn);
        this.f12400c = (TextView) this.f12398a.findViewById(C1373R.id.popup_window_save_btn);
        this.f12401d = (TextView) this.f12398a.findViewById(C1373R.id.popup_window_title);
        this.f12401d.setVisibility(8);
        this.f12402e = (Wheelview) this.f12398a.findViewById(C1373R.id.popup_window_wheel_view);
        if (TextUtils.isEmpty(this.f12405h)) {
            this.f12401d.setVisibility(8);
        } else {
            this.f12401d.setText(this.f12405h);
            this.f12401d.setVisibility(0);
        }
        this.f12399b.setOnClickListener(this);
        this.f12400c.setOnClickListener(this);
        if (this.f12406i != null && !this.f12406i.isEmpty()) {
            this.f12402e.setData(this.f12406i);
            this.f12402e.setDefault(this.f12407j);
            setContentView(this.f12398a);
            setWidth(-1);
            setHeight(-2);
            setFocusable(true);
            setAnimationStyle(C1373R.style.AnimBottom);
            setBackgroundDrawable(new ColorDrawable(0));
            setSoftInputMode(16);
            this.f12398a.setOnTouchListener(new C26391(this));
            m13196a(0.5f);
        }
    }
}
