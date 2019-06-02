package com.beastbikes.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.BaseSavedState;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;

public class ClearableEditText extends AppCompatEditText implements TextWatcher {
    /* renamed from: a */
    private Drawable f12068a;
    /* renamed from: b */
    private boolean f12069b;

    protected static class ClearIconSavedState extends BaseSavedState {
        public static final Creator<ClearIconSavedState> CREATOR = new C25851();
        /* renamed from: a */
        private final boolean f12067a;

        /* renamed from: com.beastbikes.android.widget.ClearableEditText$ClearIconSavedState$1 */
        static class C25851 implements Creator<ClearIconSavedState> {
            C25851() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m12913a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m12914a(i);
            }

            /* renamed from: a */
            public ClearIconSavedState m12913a(Parcel parcel) {
                return new ClearIconSavedState(parcel);
            }

            /* renamed from: a */
            public ClearIconSavedState[] m12914a(int i) {
                return new ClearIconSavedState[i];
            }
        }

        private ClearIconSavedState(Parcel parcel) {
            super(parcel);
            this.f12067a = parcel.readByte() != (byte) 0;
        }

        ClearIconSavedState(Parcelable parcelable, boolean z) {
            super(parcelable);
            this.f12067a = z;
        }

        /* renamed from: a */
        boolean m12915a() {
            return this.f12067a;
        }
    }

    public ClearableEditText(Context context) {
        this(context, null);
    }

    public ClearableEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842862);
    }

    public ClearableEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f12069b = false;
        m12916a(attributeSet, i, 0);
    }

    /* renamed from: a */
    private void m12916a(AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ClearableEditText, i, i2);
        if (obtainStyledAttributes.hasValue(0)) {
            this.f12068a = obtainStyledAttributes.getDrawable(0);
        }
        if (this.f12068a == null) {
            this.f12068a = getResources().getDrawable(C1373R.drawable.ic_auth_cencel);
        }
        this.f12068a.setCallback(this);
        obtainStyledAttributes.recycle();
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
    }

    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        return this.f12069b ? new ClearIconSavedState(onSaveInstanceState, true) : onSaveInstanceState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof ClearIconSavedState) {
            ClearIconSavedState clearIconSavedState = (ClearIconSavedState) parcelable;
            super.onRestoreInstanceState(clearIconSavedState.getSuperState());
            this.f12069b = clearIconSavedState.m12915a();
            m12917a(this.f12069b);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (hasFocus()) {
            m12917a(!TextUtils.isEmpty(charSequence));
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!m12918a(motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        setText(null);
        motionEvent.setAction(3);
        m12917a(false);
        return false;
    }

    /* renamed from: a */
    private boolean m12918a(MotionEvent motionEvent) {
        if (this.f12069b && ((int) motionEvent.getX()) >= getWidth() - getCompoundPaddingRight()) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private void m12917a(boolean z) {
        if (!z) {
            setCompoundDrawables(null, null, null, null);
        } else if (this.f12068a != null) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, this.f12068a, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(0, 0, C1373R.drawable.ic_auth_cencel, 0);
        }
        this.f12069b = z;
    }
}
