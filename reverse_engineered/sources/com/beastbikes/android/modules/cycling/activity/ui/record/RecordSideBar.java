package com.beastbikes.android.modules.cycling.activity.ui.record;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import java.util.ArrayList;
import java.util.List;

public class RecordSideBar extends C1964d<ActivityDTO> implements OnClickListener {
    /* renamed from: a */
    private C1953a f8831a;
    /* renamed from: c */
    private ImageView f8832c;
    /* renamed from: d */
    private ImageView f8833d;
    /* renamed from: e */
    private ImageView f8834e;
    /* renamed from: f */
    private int f8835f = 0;
    /* renamed from: g */
    private List<Animator> f8836g;
    /* renamed from: h */
    private List<Animator> f8837h;
    /* renamed from: i */
    private boolean f8838i = false;
    /* renamed from: j */
    private long f8839j;

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.RecordSideBar$a */
    public interface C1953a {
        /* renamed from: a */
        void mo3297a(int i);
    }

    public RecordSideBar(Context context) {
        super(context);
    }

    public RecordSideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RecordSideBar(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public int getLayRes() {
        return C1373R.layout.record_side_bar;
    }

    /* renamed from: a */
    public void mo3331a() {
        super.mo3331a();
        this.f8833d = (ImageView) findViewById(C1373R.id.record_side_btn_visible);
        this.f8832c = (ImageView) findViewById(C1373R.id.record_side_btn_zoom);
        this.f8834e = (ImageView) findViewById(C1373R.id.record_side_btn_switch);
        this.f8834e.setOnClickListener(this);
        this.f8832c.setOnClickListener(this);
        this.f8833d.setOnClickListener(this);
        m10127a(getContext());
        m10128b(getContext());
    }

    public void onClick(View view) {
        if (view.getId() == C1373R.id.record_side_btn_visible) {
            if (System.currentTimeMillis() - this.f8839j >= 3000) {
                this.f8839j = System.currentTimeMillis();
            } else {
                return;
            }
        }
        if (this.f8831a != null) {
            this.f8831a.mo3297a(view.getId());
        }
    }

    public void setOnSideBarItemClickListener(C1953a c1953a) {
        this.f8831a = c1953a;
    }

    public void setDefaultMargin(int i) {
        this.f8835f = i;
    }

    /* renamed from: a */
    public void m10133a(boolean z) {
        this.f8833d.setImageResource(!z ? C1373R.drawable.ic_side_public : C1373R.drawable.ic_side_private);
    }

    /* renamed from: a */
    public void m10130a(int i, int i2) {
        if (this.f8836g != null && this.f8837h != null) {
            int i3;
            Animator animator;
            if (i == 1 && i2 == 0) {
                for (i3 = 0; i3 < this.f8836g.size(); i3++) {
                    animator = (Animator) this.f8836g.get(i3);
                    if (animator.isRunning()) {
                        animator.end();
                        animator.cancel();
                    }
                    animator.start();
                }
            } else if (i != 2 && i2 == 1) {
                for (i3 = 0; i3 < this.f8837h.size(); i3++) {
                    animator = (Animator) this.f8837h.get(i3);
                    if (animator.isRunning()) {
                        animator.end();
                        animator.cancel();
                    }
                    animator.start();
                }
            }
        }
    }

    /* renamed from: a */
    public void m10131a(ActivityDTO activityDTO) {
        boolean z = true;
        super.mo3332a(activityDTO);
        if (activityDTO != null) {
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null && activityDTO.getUserId().equals(currentUser.getObjectId())) {
                this.f8833d.setVisibility(0);
                if (activityDTO.getIsPrivate() != 1) {
                    z = false;
                }
                m10133a(z);
            }
        }
    }

    /* renamed from: a */
    private void m10127a(Context context) {
        this.f8836g = new ArrayList();
        Animator loadAnimator = AnimatorInflater.loadAnimator(context, C1373R.animator.actionbar_animator_indicator);
        loadAnimator.setTarget(this.f8832c);
        this.f8836g.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8833d);
        this.f8836g.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8834e);
        this.f8836g.add(loadAnimator);
    }

    /* renamed from: b */
    private void m10128b(Context context) {
        this.f8837h = new ArrayList();
        Animator loadAnimator = AnimatorInflater.loadAnimator(context, C1373R.animator.actionbar_animator);
        loadAnimator.setTarget(this.f8832c);
        this.f8837h.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8833d);
        this.f8837h.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8834e);
        this.f8837h.add(loadAnimator);
    }
}
