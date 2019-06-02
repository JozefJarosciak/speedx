package com.beastbikes.android.modules.cycling.club.ui.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import com.beastbikes.android.C1373R;

/* compiled from: ColorPopupWindows */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.c */
public class C2150c extends PopupWindow implements OnClickListener {
    /* renamed from: a */
    private Activity f10082a;
    /* renamed from: b */
    private LayoutInflater f10083b;
    /* renamed from: c */
    private View f10084c;
    /* renamed from: d */
    private C2149a f10085d;

    /* compiled from: ColorPopupWindows */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.c$a */
    public interface C2149a {
        /* renamed from: a */
        void m11045a(int i);
    }

    public C2150c(Activity activity, C2149a c2149a) {
        this.f10082a = activity;
        this.f10085d = c2149a;
        this.f10083b = LayoutInflater.from(activity);
        setFocusable(true);
        setAnimationStyle(C1373R.style.WindowAnim);
        Drawable colorDrawable = new ColorDrawable(0);
        setInputMethodMode(2);
        setBackgroundDrawable(colorDrawable);
        setOutsideTouchable(true);
        m11046a();
    }

    /* renamed from: a */
    private void m11046a() {
        this.f10084c = this.f10083b.inflate(C1373R.layout.popuowindows_color, null);
        setContentView(this.f10084c);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.f10082a.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        setWidth(i / 2);
        setHeight(i2 / 13);
        this.f10084c.findViewById(C1373R.id.view_color_circle_red).setOnClickListener(this);
        this.f10084c.findViewById(C1373R.id.view_color_circle_black).setOnClickListener(this);
        this.f10084c.findViewById(C1373R.id.view_color_circle_green).setOnClickListener(this);
        this.f10084c.findViewById(C1373R.id.view_color_circle_orange).setOnClickListener(this);
        this.f10084c.findViewById(C1373R.id.view_color_circle_violet).setOnClickListener(this);
        this.f10084c.findViewById(C1373R.id.view_color_circle_blue).setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.view_color_circle_red:
                this.f10085d.m11045a(this.f10082a.getResources().getColor(C1373R.color.view_color_circle_red));
                break;
            case C1373R.id.view_color_circle_orange:
                this.f10085d.m11045a(this.f10082a.getResources().getColor(C1373R.color.view_color_circle_orange));
                break;
            case C1373R.id.view_color_circle_green:
                this.f10085d.m11045a(this.f10082a.getResources().getColor(C1373R.color.view_color_circle_green));
                break;
            case C1373R.id.view_color_circle_blue:
                this.f10085d.m11045a(this.f10082a.getResources().getColor(C1373R.color.view_color_circle_blue));
                break;
            case C1373R.id.view_color_circle_violet:
                this.f10085d.m11045a(this.f10082a.getResources().getColor(C1373R.color.view_color_circle_violet));
                break;
            case C1373R.id.view_color_circle_black:
                this.f10085d.m11045a(this.f10082a.getResources().getColor(C1373R.color.view_color_circle_black));
                break;
        }
        dismiss();
    }
}
