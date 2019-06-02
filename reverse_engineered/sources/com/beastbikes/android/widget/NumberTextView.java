package com.beastbikes.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import com.beastbikes.android.R$styleable;
import com.beastbikes.android.utils.C2583z;

public class NumberTextView extends TextView {
    /* renamed from: a */
    private Context f12121a;
    /* renamed from: b */
    private String f12122b;

    public NumberTextView(Context context) {
        super(context);
        this.f12121a = context;
    }

    public NumberTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f12121a = context;
        m12957a(context, attributeSet);
    }

    public NumberTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f12121a = context;
        m12957a(context, attributeSet);
    }

    /* renamed from: a */
    private void m12957a(Context context, AttributeSet attributeSet) {
        try {
            setTypeface(C2583z.m12912a(context.getAssets(), "fonts/BebasNeue.otf"));
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.NumberTextView);
            this.f12122b = obtainStyledAttributes.getString(0);
            obtainStyledAttributes.recycle();
            if (this.f12122b != null && !"".equals(this.f12122b)) {
                setTypeface(C2583z.m12912a(context.getAssets(), "fonts/" + this.f12122b + ".otf"));
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void setTypefaceName(String str) {
        this.f12122b = str;
        setTypeface(Typeface.createFromAsset(this.f12121a.getAssets(), "fonts/" + str + ".otf"));
    }

    public String getTypeFaceName() {
        return this.f12122b;
    }
}
