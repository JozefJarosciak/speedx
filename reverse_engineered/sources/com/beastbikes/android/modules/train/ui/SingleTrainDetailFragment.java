package com.beastbikes.android.modules.train.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.train.ui.widget.SingleTrainDetailTextView;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903377)
public class SingleTrainDetailFragment extends SessionFragment implements OnClickListener {
    @C1458a(a = 2131756669)
    /* renamed from: a */
    private SingleTrainDetailTextView f6233a;
    @C1458a(a = 2131756671)
    /* renamed from: b */
    private SingleTrainDetailTextView f6234b;
    @C1458a(a = 2131756670)
    /* renamed from: c */
    private View f6235c;
    @C1458a(a = 2131756672)
    /* renamed from: d */
    private View f6236d;
    /* renamed from: e */
    private TrainCourseBriefFragment f6237e;
    /* renamed from: f */
    private TrainCourseDetailFragment f6238f;
    /* renamed from: g */
    private Bundle f6239g;

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f6239g = getArguments();
        if (this.f6239g != null) {
            m7422a();
            m7423a(true);
        }
    }

    /* renamed from: a */
    private void m7422a() {
        this.f6233a.setOnClickListener(this);
        this.f6234b.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.tv_single_train_detail_brief:
                if (!this.f6233a.isChecked()) {
                    m7423a(true);
                    return;
                }
                return;
            case C1373R.id.tv_single_train_detail_detail:
                if (!this.f6234b.isChecked()) {
                    m7423a(false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m7423a(boolean z) {
        this.f6233a.toggle();
        this.f6234b.toggle();
        if (z) {
            if (this.f6237e == null) {
                this.f6237e = new TrainCourseBriefFragment();
                this.f6237e.setArguments(this.f6239g);
            }
            this.f6235c.setVisibility(0);
            this.f6236d.setVisibility(4);
            getChildFragmentManager().beginTransaction().replace(C1373R.id.frame_single_train_course_container, this.f6237e).commit();
            return;
        }
        if (this.f6238f == null) {
            this.f6238f = new TrainCourseDetailFragment();
            this.f6238f.setArguments(this.f6239g);
        }
        this.f6235c.setVisibility(4);
        this.f6236d.setVisibility(0);
        getChildFragmentManager().beginTransaction().replace(C1373R.id.frame_single_train_course_container, this.f6238f).commit();
    }
}
