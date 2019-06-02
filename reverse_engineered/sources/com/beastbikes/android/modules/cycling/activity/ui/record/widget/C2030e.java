package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.ui.record.RecordSummary;
import com.beastbikes.android.modules.cycling.activity.ui.record.RecordSummary.C1954a;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.utils.C2553b;

/* compiled from: CyclingCompletedMainItemView */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.e */
public class C2030e extends RelativeLayout {
    /* renamed from: a */
    private RecordSummary f9185a;
    /* renamed from: b */
    private ObjectAnimator f9186b;
    /* renamed from: c */
    private ObjectAnimator f9187c;
    /* renamed from: d */
    private int f9188d;
    /* renamed from: e */
    private VerticalViewPager f9189e;

    /* compiled from: CyclingCompletedMainItemView */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.e$1 */
    class C20281 implements AnimatorListener {
        /* renamed from: a */
        final /* synthetic */ C2030e f9183a;

        C20281(C2030e c2030e) {
            this.f9183a = c2030e;
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
            if (this.f9183a.f9189e != null) {
                this.f9183a.f9189e.setPosY((float) this.f9183a.getWindowHeight());
            }
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    /* compiled from: CyclingCompletedMainItemView */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.e$2 */
    class C20292 implements AnimatorListener {
        /* renamed from: a */
        final /* synthetic */ C2030e f9184a;

        C20292(C2030e c2030e) {
            this.f9184a = c2030e;
        }

        public void onAnimationStart(Animator animator) {
            if (this.f9184a.f9189e != null) {
                this.f9184a.f9189e.setPosY((float) (this.f9184a.getWindowHeight() - this.f9184a.f9188d));
            }
        }

        public void onAnimationEnd(Animator animator) {
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    public C2030e(Context context) {
        super(context);
        m10473a(context);
    }

    public C2030e(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m10473a(context);
    }

    public C2030e(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10473a(context);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    /* renamed from: a */
    private void m10473a(Context context) {
        C2030e.inflate(context, C1373R.layout.layout_cycling_complete_base_info_item, this);
        this.f9185a = (RecordSummary) findViewById(C1373R.id.record_summary);
    }

    /* renamed from: a */
    public void m10476a(int i, VerticalViewPager verticalViewPager) {
        this.f9188d = i;
        this.f9189e = verticalViewPager;
        verticalViewPager.setPosY((float) (getWindowHeight() - i));
    }

    /* renamed from: a */
    public void m10477a(ActivityDTO activityDTO) {
        if (this.f9185a != null) {
            this.f9185a.m10135a(activityDTO);
        }
    }

    public void setOnSummaryItemClickListener(C1954a c1954a) {
        this.f9185a.setOnSummaryItemClickListener(c1954a);
    }

    public int getWindowHeight() {
        return ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getHeight();
    }

    public Bitmap getSummmaryBitmap() {
        return C2553b.m12779a(this.f9185a);
    }

    /* renamed from: a */
    public void m10475a() {
        if (this.f9187c == null) {
            this.f9187c = ObjectAnimator.ofFloat(this.f9185a, "translationY", new float[]{(float) this.f9185a.getHeight()}).setDuration(500);
            this.f9187c.addListener(new C20281(this));
        }
        this.f9187c.start();
    }

    /* renamed from: b */
    public void m10478b() {
        if (this.f9186b == null) {
            this.f9186b = ObjectAnimator.ofFloat(this.f9185a, "translationY", new float[]{0.0f}).setDuration(500);
            this.f9186b.addListener(new C20292(this));
        }
        this.f9186b.start();
    }
}
