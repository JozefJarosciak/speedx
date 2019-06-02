package com.beastbikes.android.modules.cycling.activity.ui.record;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.widget.slidingup_pannel.SlidingUpPanelLayout.PanelState;
import java.util.ArrayList;
import java.util.List;

public class RecordActionBar extends C1964d<ActivityDTO> implements OnClickListener {
    /* renamed from: a */
    PanelState f8800a = PanelState.COLLAPSED;
    /* renamed from: c */
    private View f8801c;
    /* renamed from: d */
    private View f8802d;
    /* renamed from: e */
    private ImageView f8803e;
    /* renamed from: f */
    private ImageView f8804f;
    /* renamed from: g */
    private ImageView f8805g;
    /* renamed from: h */
    private ImageView f8806h;
    /* renamed from: i */
    private ImageView f8807i;
    /* renamed from: j */
    private TextView f8808j;
    /* renamed from: k */
    private TextView f8809k;
    /* renamed from: l */
    private List<Animator> f8810l;
    /* renamed from: m */
    private List<Animator> f8811m;
    /* renamed from: n */
    private C1952a f8812n;
    /* renamed from: o */
    private boolean f8813o;
    /* renamed from: p */
    private boolean f8814p;

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.RecordActionBar$a */
    public interface C1952a {
        /* renamed from: b */
        void mo3300b(int i);
    }

    public RecordActionBar(Context context) {
        super(context);
    }

    public RecordActionBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RecordActionBar(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setItemClickListener(C1952a c1952a) {
        this.f8812n = c1952a;
    }

    public int getLayRes() {
        return C1373R.layout.record_action_bar;
    }

    /* renamed from: a */
    public void mo3331a() {
        super.mo3331a();
        this.f8803e = (ImageView) findViewById(C1373R.id.action_bar_back);
        this.f8804f = (ImageView) findViewById(C1373R.id.action_bar_tools_camera);
        this.f8805g = (ImageView) findViewById(C1373R.id.action_bar_tools_upload);
        this.f8806h = (ImageView) findViewById(C1373R.id.action_bar_tools_share);
        this.f8807i = (ImageView) findViewById(C1373R.id.action_bar_tools_repair);
        this.f8808j = (TextView) findViewById(C1373R.id.action_bar_tip_cheat);
        this.f8809k = (TextView) findViewById(C1373R.id.action_bar_tools_report);
        this.f8801c = findViewById(C1373R.id.action_bar_tools);
        this.f8802d = findViewById(C1373R.id.action_bar_cheat_report);
        this.f8803e.setOnClickListener(this);
        this.f8804f.setOnClickListener(this);
        this.f8806h.setOnClickListener(this);
        this.f8808j.setOnClickListener(this);
        this.f8805g.setOnClickListener(this);
        this.f8809k.setOnClickListener(this);
        this.f8802d.setOnClickListener(this);
        this.f8807i.setOnClickListener(this);
        m10115a(getContext());
        m10116b(getContext());
        BeastBikes beastBikes = (BeastBikes) BeastBikes.j();
        this.f8813o = beastBikes.h();
        this.f8814p = beastBikes.i();
    }

    public void onClick(View view) {
        if (this.f8812n != null) {
            this.f8812n.mo3300b(view.getId());
        }
    }

    /* renamed from: a */
    public void m10118a(int i, int i2) {
        if (this.f8810l != null && this.f8811m != null) {
            int i3;
            Animator animator;
            if (i == 1 && i2 == 0) {
                for (i3 = 0; i3 < this.f8810l.size(); i3++) {
                    animator = (Animator) this.f8810l.get(i3);
                    if (animator.isRunning()) {
                        animator.end();
                        animator.cancel();
                    }
                    animator.start();
                }
            } else if (i != 2 && i2 == 1) {
                for (i3 = 0; i3 < this.f8811m.size(); i3++) {
                    animator = (Animator) this.f8811m.get(i3);
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
    public void m10119a(ActivityDTO activityDTO) {
        super.mo3332a(activityDTO);
        if (activityDTO != null && !TextUtils.isEmpty(activityDTO.getUserId())) {
            if (AVUser.getCurrentUser() == null || !activityDTO.getUserId().equals(AVUser.getCurrentUser().getObjectId())) {
                this.f8802d.setVisibility(0);
                if (activityDTO.isFake()) {
                    this.f8808j.setVisibility(0);
                    this.f8809k.setVisibility(8);
                    return;
                }
                this.f8808j.setVisibility(8);
                this.f8809k.setVisibility(0);
                if (activityDTO.isHasReport()) {
                    this.f8809k.setText(C1373R.string.activity_complete_activity_already_report);
                    if (VERSION.SDK_INT >= 17) {
                        this.f8809k.setCompoundDrawablesRelativeWithIntrinsicBounds(0, C1373R.drawable.ic_activity_complete_report_icon_selected, 0, 0);
                    }
                    this.f8809k.setTextColor(getResources().getColor(C1373R.color.activity_complete_activity_report_desc_color));
                    this.f8809k.setClickable(false);
                    return;
                }
                this.f8809k.setText(C1373R.string.activity_complete_activity_report);
                if (VERSION.SDK_INT >= 17) {
                    this.f8809k.setCompoundDrawablesRelativeWithIntrinsicBounds(0, C1373R.drawable.ic_activity_complete_report_icon, 0, 0);
                }
                this.f8809k.setTextColor(-1);
                this.f8809k.setClickable(true);
            } else if (activityDTO.isFake()) {
                this.f8802d.setVisibility(0);
                this.f8808j.setVisibility(0);
            } else {
                if (this.f8813o && this.f8814p) {
                    this.f8807i.setVisibility(0);
                }
                switch (activityDTO.getShowRepair()) {
                    case 0:
                        this.f8807i.setSelected(false);
                        break;
                    case 1:
                        this.f8807i.setSelected(true);
                        break;
                    case 2:
                        this.f8807i.setVisibility(8);
                        break;
                }
                if (!TextUtils.isEmpty(activityDTO.getCentralId()) || activityDTO.getShowRepair() == 2 || activityDTO.isNuked() || activityDTO.isFake()) {
                    this.f8807i.setVisibility(8);
                }
                this.f8801c.setVisibility(0);
                if (!activityDTO.isSynced()) {
                    this.f8805g.setVisibility(0);
                }
                this.f8806h.setVisibility(0);
                this.f8804f.setVisibility(0);
            }
        }
    }

    /* renamed from: a */
    private void m10115a(Context context) {
        this.f8810l = new ArrayList();
        Animator loadAnimator = AnimatorInflater.loadAnimator(context, C1373R.animator.actionbar_animator_indicator);
        loadAnimator.setTarget(this.f8803e);
        this.f8810l.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8806h);
        this.f8810l.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8805g);
        this.f8810l.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8808j);
        this.f8810l.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8809k);
        this.f8810l.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8804f);
        this.f8810l.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8807i);
        this.f8810l.add(loadAnimator);
    }

    /* renamed from: b */
    private void m10116b(Context context) {
        this.f8811m = new ArrayList();
        Animator loadAnimator = AnimatorInflater.loadAnimator(context, C1373R.animator.actionbar_animator);
        loadAnimator.setTarget(this.f8803e);
        this.f8811m.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8806h);
        this.f8811m.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8805g);
        this.f8811m.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8808j);
        this.f8811m.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8809k);
        this.f8811m.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8804f);
        this.f8811m.add(loadAnimator);
        loadAnimator = loadAnimator.clone();
        loadAnimator.setTarget(this.f8807i);
        this.f8811m.add(loadAnimator);
    }
}
