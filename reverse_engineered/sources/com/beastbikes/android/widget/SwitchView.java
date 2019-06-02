package com.beastbikes.android.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.beastbikes.android.C1373R;

public class SwitchView extends LinearLayout implements Checkable {
    /* renamed from: a */
    private ImageView f12189a;
    /* renamed from: b */
    private boolean f12190b = true;
    /* renamed from: c */
    private boolean f12191c = true;
    /* renamed from: d */
    private float f12192d;
    /* renamed from: e */
    private boolean f12193e = false;
    /* renamed from: f */
    private C2081a f12194f;

    /* renamed from: com.beastbikes.android.widget.SwitchView$a */
    public interface C2081a {
        /* renamed from: a */
        void mo3375a(boolean z);
    }

    /* renamed from: com.beastbikes.android.widget.SwitchView$1 */
    class C26031 implements AnimationListener {
        /* renamed from: a */
        final /* synthetic */ SwitchView f12187a;

        C26031(SwitchView switchView) {
            this.f12187a = switchView;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f12187a.f12189a.clearAnimation();
            this.f12187a.setGravity(5);
            this.f12187a.setBackgroundResource(C1373R.drawable.ic_switch_on);
            this.f12187a.f12191c = true;
        }
    }

    /* renamed from: com.beastbikes.android.widget.SwitchView$2 */
    class C26042 implements AnimationListener {
        /* renamed from: a */
        final /* synthetic */ SwitchView f12188a;

        C26042(SwitchView switchView) {
            this.f12188a = switchView;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f12188a.f12189a.clearAnimation();
            this.f12188a.setGravity(3);
            this.f12188a.setBackgroundResource(C1373R.drawable.ic_switch_off);
            this.f12188a.f12191c = true;
        }
    }

    public SwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m12993a();
    }

    public SwitchView(Context context) {
        super(context);
        m12993a();
    }

    /* renamed from: a */
    private void m12993a() {
        setBackgroundResource(C1373R.drawable.ic_switch_off);
        this.f12189a = new ImageView(getContext());
        this.f12189a.setLayoutParams(new LayoutParams(-2, -2));
        this.f12189a.setImageResource(C1373R.drawable.ic_switch);
        addView(this.f12189a);
        setSwitchStatus(isChecked());
    }

    public boolean getSwitchStatus() {
        return this.f12190b;
    }

    public void setSwitchStatus(boolean z) {
        this.f12190b = z;
        if (z) {
            setGravity(5);
            setBackgroundResource(C1373R.drawable.ic_switch_on);
            return;
        }
        setGravity(3);
        setBackgroundResource(C1373R.drawable.ic_switch_off);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.f12192d = motionEvent.getX();
                break;
            case 1:
                if (Math.abs(motionEvent.getX() - this.f12192d) <= 5.0f) {
                    m12996b();
                }
                this.f12193e = false;
                break;
            case 2:
                if (motionEvent.getX() - this.f12192d <= 5.0f || this.f12190b) {
                    if (motionEvent.getX() - this.f12192d < -5.0f && this.f12190b) {
                        m12996b();
                        break;
                    }
                }
                m12996b();
                break;
                break;
            case 3:
                this.f12193e = false;
                break;
        }
        return true;
    }

    /* renamed from: b */
    private void m12996b() {
        boolean z = true;
        if (this.f12191c && !this.f12193e) {
            this.f12193e = true;
            if (this.f12190b) {
                z = false;
            }
            this.f12190b = z;
            this.f12191c = false;
            if (this.f12194f != null) {
                this.f12194f.mo3375a(this.f12190b);
            }
            m12994a(this.f12190b);
        }
    }

    /* renamed from: a */
    private void m12994a(boolean z) {
        if (z) {
            Animation translateAnimation = new TranslateAnimation(1, 0.0f, 0, (float) (getWidth() - this.f12189a.getWidth()), 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(100);
            translateAnimation.setAnimationListener(new C26031(this));
            this.f12189a.startAnimation(translateAnimation);
            return;
        }
        translateAnimation = new TranslateAnimation(1, 0.0f, 0, (float) (this.f12189a.getWidth() - getWidth()), 1, 0.0f, 1, 0.0f);
        translateAnimation.setDuration(100);
        translateAnimation.setAnimationListener(new C26042(this));
        this.f12189a.startAnimation(translateAnimation);
    }

    public C2081a getOnClickListener() {
        return this.f12194f;
    }

    public void setOnClickSwitchListener(C2081a c2081a) {
        this.f12194f = c2081a;
    }

    public void setChecked(boolean z) {
        this.f12190b = z;
        setSwitchStatus(this.f12190b);
    }

    public boolean isChecked() {
        return this.f12190b;
    }

    public void toggle() {
    }
}
