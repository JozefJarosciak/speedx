package com.beastbikes.android.modules.cycling.club.ui.widget;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903362)
public class ClubActivityOntListFragment extends SessionFragment {
    @C1458a(a = 2131756621)
    /* renamed from: a */
    private TextView f5352a;
    @C1458a(a = 2131756620)
    /* renamed from: b */
    private TextView f5353b;
    @C1458a(a = 2131756619)
    /* renamed from: c */
    private ImageView f5354c;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Bundle arguments = getArguments();
        boolean z = arguments.getBoolean("isclub");
        if (z) {
            this.f5354c.setVisibility(8);
            this.f5352a.setOnClickListener(new ClubActivityOntListFragment$1(this, z, arguments));
            return;
        }
        this.f5354c.setVisibility(0);
        this.f5352a.setVisibility(8);
        this.f5353b.setText(getResources().getString(C1373R.string.fragment_club_activity_not_ismyclub));
    }
}
