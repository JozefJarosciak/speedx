package com.beastbikes.android.modules.cycling.activity.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.activity.ui.widget.CyclingTrainStateProgressBar;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903366)
public class CyclingTrainFragment extends SessionFragment implements OnClickListener {
    @C1458a(a = 2131756642)
    /* renamed from: a */
    private TextView f4670a;
    @C1458a(a = 2131756644)
    /* renamed from: b */
    private TextView f4671b;
    @C1458a(a = 2131756646)
    /* renamed from: c */
    private ViewStub f4672c;
    @C1458a(a = 2131756647)
    /* renamed from: d */
    private ViewStub f4673d;
    /* renamed from: e */
    private LinearLayout f4674e;
    /* renamed from: f */
    private TextView f4675f;
    /* renamed from: g */
    private ImageView f4676g;
    /* renamed from: h */
    private TextView f4677h;
    /* renamed from: i */
    private ImageView f4678i;
    /* renamed from: j */
    private LinearLayout f4679j;
    /* renamed from: k */
    private CyclingTrainStateProgressBar f4680k;
    /* renamed from: l */
    private CyclingTrainStateProgressBar f4681l;
    /* renamed from: m */
    private View f4682m;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f4682m = super.onCreateView(layoutInflater, viewGroup, bundle);
        return this.f4682m;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        m6032a();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.linear_cycling_train_state1:
                if (this.f4679j == null) {
                    this.f4673d.inflate();
                    this.f4679j = (LinearLayout) this.f4682m.findViewById(C1373R.id.linear_cycling_train_state2);
                    this.f4680k = (CyclingTrainStateProgressBar) this.f4682m.findViewById(C1373R.id.progress_cycling_train_power);
                    this.f4681l = (CyclingTrainStateProgressBar) this.f4682m.findViewById(C1373R.id.progress_cycling_train_cadence);
                    this.f4679j.setOnClickListener(this);
                }
                this.f4674e.setVisibility(8);
                this.f4679j.setVisibility(0);
                return;
            case C1373R.id.linear_cycling_train_state2:
                this.f4674e.setVisibility(0);
                this.f4679j.setVisibility(8);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m6032a() {
        this.f4672c.inflate();
        this.f4674e = (LinearLayout) this.f4682m.findViewById(C1373R.id.linear_cycling_train_state1);
        this.f4675f = (TextView) this.f4682m.findViewById(C1373R.id.tv_cycling_train_target_power);
        this.f4676g = (ImageView) this.f4682m.findViewById(C1373R.id.img_cycling_train_power_state);
        this.f4677h = (TextView) this.f4682m.findViewById(C1373R.id.tv_cycling_train_target_cadence);
        this.f4678i = (ImageView) this.f4682m.findViewById(C1373R.id.img_cycling_train_cadence_state);
        this.f4674e.setOnClickListener(this);
        this.f4670a.append("(" + getString(C1373R.string.str_unit_power) + ")");
        this.f4671b.append("(" + getString(C1373R.string.str_unit_cadence) + ")");
    }
}
