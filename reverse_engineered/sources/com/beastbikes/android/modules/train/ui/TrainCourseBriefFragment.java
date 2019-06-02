package com.beastbikes.android.modules.train.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO;
import com.beastbikes.android.modules.train.ui.widget.C2386a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903379)
public class TrainCourseBriefFragment extends SessionFragment {
    @C1458a(a = 2131756680)
    /* renamed from: a */
    private LinearLayout f6249a;

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        TrainCourseDTO trainCourseDTO = (TrainCourseDTO) getArguments().getSerializable("train_type_single");
        if (trainCourseDTO != null) {
            m7434a(trainCourseDTO);
        }
    }

    /* renamed from: a */
    public void m7434a(TrainCourseDTO trainCourseDTO) {
        if (trainCourseDTO != null) {
            Object enIntroduction = trainCourseDTO.getEnIntroduction();
            if (C1849a.c()) {
                enIntroduction = trainCourseDTO.getIntroduction();
            }
            if (!TextUtils.isEmpty(enIntroduction)) {
                for (Object obj : enIntroduction.split("\n")) {
                    if (!TextUtils.isEmpty(obj)) {
                        View c2386a = new C2386a(getActivity());
                        c2386a.setIdea(obj);
                        this.f6249a.addView(c2386a);
                    }
                }
            }
        }
    }
}
