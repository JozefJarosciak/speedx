package com.beastbikes.android.modules.user.ui.binding;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: SelectPopupWindow */
/* renamed from: com.beastbikes.android.modules.user.ui.binding.a */
public class C2511a extends PopupWindow {
    /* renamed from: a */
    private TextView f11895a = ((TextView) this.f11897c.findViewById(C1373R.id.popup_window_btn_cancel));
    /* renamed from: b */
    private TextView f11896b = ((TextView) this.f11897c.findViewById(C1373R.id.popup_window_btn_ok));
    /* renamed from: c */
    private View f11897c;

    /* compiled from: SelectPopupWindow */
    /* renamed from: com.beastbikes.android.modules.user.ui.binding.a$1 */
    class C25091 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2511a f11894a;

        C25091(C2511a c2511a) {
            this.f11894a = c2511a;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int top = this.f11894a.f11897c.findViewById(C1373R.id.popup_window_account_slelect).getTop();
            int y = (int) motionEvent.getY();
            if (motionEvent.getAction() == 1 && y < top) {
                this.f11894a.dismiss();
            }
            return true;
        }
    }

    public C2511a(Activity activity, OnClickListener onClickListener) {
        this.f11897c = LayoutInflater.from(activity).inflate(C1373R.layout.popup_window_account_slelect, null);
        this.f11896b.setOnClickListener(onClickListener);
        this.f11895a.setOnClickListener(onClickListener);
        setContentView(this.f11897c);
        setWidth(-1);
        setHeight(-2);
        setFocusable(true);
        setAnimationStyle(C1373R.style.AnimBottom);
        this.f11897c.setOnTouchListener(new C25091(this));
    }
}
