package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class ToggleImageButton extends ImageButton {
    /* renamed from: e */
    private static final int[] f16494e = new int[]{C4689R.attr.state_toggled_on};
    /* renamed from: a */
    boolean f16495a;
    /* renamed from: b */
    String f16496b;
    /* renamed from: c */
    String f16497c;
    /* renamed from: d */
    final boolean f16498d;

    public ToggleImageButton(Context context) {
        this(context, null);
    }

    public ToggleImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ToggleImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArray = null;
        try {
            typedArray = context.getTheme().obtainStyledAttributes(attributeSet, C4689R.styleable.ToggleImageButton, i, 0);
            String string = typedArray.getString(C4689R.styleable.ToggleImageButton_contentDescriptionOn);
            String string2 = typedArray.getString(C4689R.styleable.ToggleImageButton_contentDescriptionOff);
            if (string == null) {
                string = (String) getContentDescription();
            }
            this.f16496b = string;
            this.f16497c = string2 == null ? (String) getContentDescription() : string2;
            this.f16498d = typedArray.getBoolean(C4689R.styleable.ToggleImageButton_toggleOnClick, true);
            setToggledOn(false);
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (this.f16495a) {
            mergeDrawableStates(onCreateDrawableState, f16494e);
        }
        return onCreateDrawableState;
    }

    public boolean performClick() {
        if (this.f16498d) {
            m18490a();
        }
        return super.performClick();
    }

    public void setToggledOn(boolean z) {
        this.f16495a = z;
        setContentDescription(z ? this.f16496b : this.f16497c);
        refreshDrawableState();
    }

    /* renamed from: a */
    public void m18490a() {
        setToggledOn(!this.f16495a);
    }
}
