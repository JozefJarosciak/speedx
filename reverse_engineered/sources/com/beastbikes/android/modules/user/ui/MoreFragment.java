package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.activity.ui.CyclingTargetSettingActivity;
import com.beastbikes.android.modules.preferences.ui.SettingActivity;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.framework.android.ApplicationContext;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.google.android.gms.common.Scopes;

@C1459b(a = 2130903370)
public class MoreFragment extends SessionFragment implements OnClickListener {
    @C1458a(a = 2131756658)
    /* renamed from: a */
    private ViewGroup f6454a;
    /* renamed from: b */
    private ImageView f6455b;
    /* renamed from: c */
    private TextView f6456c;
    @C1458a(a = 2131756659)
    /* renamed from: d */
    private ViewGroup f6457d;
    /* renamed from: e */
    private ImageView f6458e;
    /* renamed from: f */
    private TextView f6459f;
    @C1458a(a = 2131756660)
    /* renamed from: g */
    private ViewGroup f6460g;
    /* renamed from: h */
    private ImageView f6461h;
    /* renamed from: i */
    private TextView f6462i;
    /* renamed from: j */
    private ProfileDTO f6463j;
    /* renamed from: k */
    private SharedPreferences f6464k;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f6464k = getActivity().getSharedPreferences(b(), 0);
        getActivity().setTitle(C1373R.string.club_discover_group_more);
        this.f6455b = (ImageView) this.f6454a.findViewById(C1373R.id.layout_morefragment_detail_icon);
        this.f6456c = (TextView) this.f6454a.findViewById(C1373R.id.layout_morefragment_detail_title);
        this.f6458e = (ImageView) this.f6457d.findViewById(C1373R.id.layout_morefragment_detail_icon);
        this.f6459f = (TextView) this.f6457d.findViewById(C1373R.id.layout_morefragment_detail_title);
        this.f6461h = (ImageView) this.f6460g.findViewById(C1373R.id.layout_morefragment_detail_icon);
        this.f6462i = (TextView) this.f6460g.findViewById(C1373R.id.layout_morefragment_detail_title);
        this.f6455b.setImageResource(C1373R.drawable.ic_goalsettings);
        this.f6458e.setImageResource(C1373R.drawable.ic_grid);
        this.f6461h.setImageResource(C1373R.drawable.ic_settings);
        this.f6456c.setText(C1373R.string.label_goal_settings);
        this.f6459f.setText(C1373R.string.str_grid_explore);
        this.f6462i.setText(C1373R.string.str_settings);
        this.f6457d.setOnClickListener(this);
        this.f6460g.setOnClickListener(this);
        this.f6454a.setOnClickListener(this);
        m7680a();
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            getActivity().setTitle(C1373R.string.club_discover_group_more);
        }
    }

    public void onResume() {
        super.onResume();
        if (((BeastBikes) ApplicationContext.m5242j()).m5258f()) {
            this.f6457d.setVisibility(0);
        } else {
            this.f6457d.setVisibility(8);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.goal_settings:
                startActivity(new Intent(getActivity(), CyclingTargetSettingActivity.class));
                return;
            case C1373R.id.profile_fragment_detail_item_grid:
                Intent intent = new Intent(getActivity(), GridExploreActivity.class);
                intent.putExtra(Scopes.PROFILE, this.f6463j);
                startActivity(intent);
                return;
            case C1373R.id.settings:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m7680a() {
        getAsyncTaskQueue().a(new MoreFragment$1(this), new Void[0]);
    }
}
