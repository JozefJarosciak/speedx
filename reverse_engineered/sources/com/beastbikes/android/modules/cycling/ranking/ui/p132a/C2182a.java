package com.beastbikes.android.modules.cycling.ranking.ui.p132a;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.ui.C1563a;
import com.beastbikes.android.home.HomeActivity;
import com.beastbikes.android.utils.C2574s;

/* compiled from: RankMenuPopupWindow */
/* renamed from: com.beastbikes.android.modules.cycling.ranking.ui.a.a */
public class C2182a extends PopupWindow implements OnClickListener {
    /* renamed from: a */
    private TextView f10240a;
    /* renamed from: b */
    private String f10241b;
    /* renamed from: c */
    private Activity f10242c;

    /* renamed from: a */
    public static C2182a m11188a(Activity activity, TextView textView, String str) {
        return new C2182a(activity, textView, str);
    }

    private C2182a(Activity activity, TextView textView, String str) {
        this.f10240a = textView;
        this.f10241b = str;
        this.f10242c = activity;
        View inflate = LayoutInflater.from(activity).inflate(C1373R.layout.rank_fragment_view, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        setWidth(i / 4);
        setHeight(i2 / 6);
        inflate.findViewById(C1373R.id.rank_fragment_view_net).setOnClickListener(this);
        inflate.findViewById(C1373R.id.rank_fragment_view_country).setOnClickListener(this);
        inflate.findViewById(C1373R.id.rank_fragment_view_province).setOnClickListener(this);
        setContentView(inflate);
        setFocusable(true);
        setAnimationStyle(C1373R.style.WindowAnim);
        setBackgroundDrawable(new ColorDrawable(0));
        setOutsideTouchable(true);
        m11189a(0.5f);
    }

    public void onClick(View view) {
        String[] split = this.f10241b.split("\\.");
        C2574s a = C2574s.m12893a();
        switch (view.getId()) {
            case C1373R.id.rank_fragment_view_country:
                HomeActivity.f4418a = 1;
                this.f10240a.setText(C1373R.string.ranking_fragment_menu_whole_country);
                if (split.length >= 1) {
                    a.m12895a(new C1563a(this.f10241b.split("\\.")[0]));
                    break;
                }
                break;
            case C1373R.id.rank_fragment_view_province:
                HomeActivity.f4418a = 2;
                this.f10240a.setText(C1373R.string.ranking_fragment_menu_whole_area);
                if (split.length >= 2) {
                    a.m12895a(new C1563a(this.f10241b.split("\\.")[0] + "." + this.f10241b.split("\\.")[1]));
                    break;
                }
                break;
            case C1373R.id.rank_fragment_view_net:
                HomeActivity.f4418a = 0;
                this.f10240a.setText(C1373R.string.ranking_fragment_menu_safety_net);
                a.m12895a(new C1563a(""));
                break;
        }
        dismiss();
    }

    public void dismiss() {
        super.dismiss();
        m11189a(1.0f);
    }

    /* renamed from: a */
    private void m11189a(float f) {
        LayoutParams attributes = this.f10242c.getWindow().getAttributes();
        attributes.alpha = f;
        this.f10242c.getWindow().setAttributes(attributes);
    }
}
