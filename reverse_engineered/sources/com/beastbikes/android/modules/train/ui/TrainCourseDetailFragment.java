package com.beastbikes.android.modules.train.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO;
import com.beastbikes.android.modules.train.ui.p150a.C2370a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;

@C1459b(a = 2130903380)
public class TrainCourseDetailFragment extends SessionFragment {
    @C1458a(a = 2131756682)
    /* renamed from: a */
    private RecyclerView f6250a;
    /* renamed from: b */
    private C2370a f6251b;

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        m7435a();
        Bundle arguments = getArguments();
        if (arguments != null) {
            TrainCourseDTO trainCourseDTO = (TrainCourseDTO) arguments.getSerializable("train_type_single");
            if (trainCourseDTO != null) {
                ArrayList programs = trainCourseDTO.getPrograms();
                if (programs != null) {
                    this.f6251b.a(programs);
                }
            }
        }
    }

    /* renamed from: a */
    private void m7435a() {
        this.f6250a.setHasFixedSize(true);
        this.f6250a.setLayoutManager(new TrainCourseDetailFragment$1(this, getContext(), 1, false));
        this.f6251b = new C2370a(getContext());
        this.f6250a.setAdapter(this.f6251b);
    }
}
