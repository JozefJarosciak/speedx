package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO;
import com.beastbikes.android.widget.NumberTextView;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903123)
public class CyclingStartAnimationActivity extends SessionFragmentActivity {
    @C1458a(a = 2131755700)
    /* renamed from: a */
    private NumberTextView f4653a;
    /* renamed from: b */
    private Handler f4654b = new Handler();
    /* renamed from: c */
    private int f4655c;
    /* renamed from: d */
    private TrainCourseDTO f4656d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.f4655c = intent.getIntExtra("cycling_type", 1);
        this.f4656d = (TrainCourseDTO) intent.getSerializableExtra("course_info");
        Animation loadAnimation = AnimationUtils.loadAnimation(this, C1373R.anim.anim_cycling_start_inner);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this, C1373R.anim.anim_cycling_start_outer);
        this.f4654b.postDelayed(new CyclingStartAnimationActivity$1(this), 3500);
        loadAnimation.setAnimationListener(new CyclingStartAnimationActivity$3(this, new CyclingStartAnimationActivity$2(this, loadAnimation2)));
        loadAnimation2.setAnimationListener(new CyclingStartAnimationActivity$4(this, loadAnimation));
        this.f4653a.startAnimation(loadAnimation);
    }
}
