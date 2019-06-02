package com.beastbikes.android.widget.materialdesign.mdswitch;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.beastbikes.android.R$styleable;
import com.beastbikes.android.widget.materialdesign.mdswitch.C2665b.C2663a;

/* compiled from: RippleManager */
/* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.c */
public final class C2667c implements OnClickListener {
    /* renamed from: a */
    private OnClickListener f12515a;
    /* renamed from: b */
    private boolean f12516b = false;

    /* compiled from: RippleManager */
    /* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.c$a */
    class C2666a implements Runnable {
        /* renamed from: a */
        View f12513a;
        /* renamed from: b */
        final /* synthetic */ C2667c f12514b;

        public C2666a(C2667c c2667c, View view) {
            this.f12514b = c2667c;
            this.f12513a = view;
        }

        public void run() {
            this.f12514b.f12516b = false;
            this.f12514b.m13261c(this.f12513a);
        }
    }

    /* renamed from: a */
    public void m13263a(View view, Context context, AttributeSet attributeSet, int i, int i2) {
        if (!view.isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MDRippleView, i, i2);
            int resourceId = obtainStyledAttributes.getResourceId(0, 0);
            Drawable drawable = null;
            if (resourceId != 0) {
                drawable = new C2663a(context, resourceId).m13219a(m13260b(view)).m13221a();
            } else if (obtainStyledAttributes.getBoolean(1, false)) {
                drawable = new C2663a(context, attributeSet, i, i2).m13219a(m13260b(view)).m13221a();
            }
            obtainStyledAttributes.recycle();
            if (drawable != null) {
                C2674h.m13291a(view, drawable);
            }
        }
    }

    /* renamed from: b */
    private Drawable m13260b(View view) {
        Drawable background = view.getBackground();
        if (background == null) {
            return null;
        }
        if (background instanceof C2665b) {
            return ((C2665b) background).m13251a();
        }
        return background;
    }

    /* renamed from: a */
    public void m13262a(OnClickListener onClickListener) {
        this.f12515a = onClickListener;
    }

    /* renamed from: a */
    public boolean m13264a(View view, MotionEvent motionEvent) {
        Drawable background = view.getBackground();
        return background != null && (background instanceof C2665b) && ((C2665b) background).onTouch(view, motionEvent);
    }

    public void onClick(View view) {
        long b;
        Drawable background = view.getBackground();
        if (background != null) {
            if (background instanceof C2665b) {
                b = ((C2665b) background).m13255b();
            } else if (background instanceof C2672f) {
                b = ((C2672f) background).m13287a();
            }
            if (b > 0 || view.getHandler() == null || this.f12516b) {
                m13261c(view);
            }
            this.f12516b = true;
            view.getHandler().postDelayed(new C2666a(this, view), b);
            return;
        }
        b = 0;
        if (b > 0) {
        }
        m13261c(view);
    }

    /* renamed from: c */
    private void m13261c(View view) {
        if (this.f12515a != null) {
            this.f12515a.onClick(view);
        }
    }

    /* renamed from: a */
    public static void m13257a(View view) {
        Drawable background = view.getBackground();
        if (background instanceof C2665b) {
            ((C2665b) background).m13256c();
        } else if (background instanceof C2672f) {
            ((C2672f) background).m13288b();
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                C2667c.m13257a(viewGroup.getChildAt(i));
            }
        }
    }
}
