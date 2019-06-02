package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.modules.user.dto.UserDetailDTO;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.google.android.gms.common.Scopes;
import io.rong.imkit.RongIM;
import java.util.Locale;

public class OtherProfileFragment extends BaseProfileFragment {
    @C1458a(a = 2131755858)
    /* renamed from: g */
    private TextView f6465g;
    @C1458a(a = 2131755859)
    /* renamed from: h */
    private TextView f6466h;
    @C1458a(a = 2131755860)
    /* renamed from: i */
    private TextView f6467i;
    @C1458a(a = 2131755861)
    /* renamed from: j */
    private TextView f6468j;
    @C1458a(a = 2131755862)
    /* renamed from: k */
    private TextView f6469k;
    @C1458a(a = 2131755863)
    /* renamed from: l */
    private TextView f6470l;
    @C1458a(a = 2131755857)
    /* renamed from: m */
    private TextView f6471m;
    @C1458a(a = 2131755864)
    /* renamed from: n */
    private ViewGroup f6472n;
    @C1458a(a = 2131755865)
    /* renamed from: o */
    private ViewGroup f6473o;
    @C1458a(a = 2131755866)
    /* renamed from: p */
    private ViewGroup f6474p;
    /* renamed from: q */
    private TextView f6475q;
    /* renamed from: r */
    private boolean f6476r;

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        super.a(C1373R.layout.activity_other_profile);
        this.f6476r = C1849a.b(getActivity());
        m7681f();
        this.d.c();
    }

    public void onClick(View view) {
        super.onClick(view);
        Intent intent;
        switch (view.getId()) {
            case C1373R.id.tv_other_profile_data_personal_record:
                intent = new Intent(getActivity(), PersonalRecordActivity.class);
                intent.putExtra("user_id", b());
                startActivity(intent);
                return;
            case C1373R.id.base_profile_detail_item_activities:
                intent = new Intent(getActivity(), CyclingRecordActivity.class);
                intent.putExtra("user_id", b());
                if (this.e != null) {
                    String avatar = this.e.getAvatar();
                    String nickname = this.e.getNickname();
                    intent.putExtra("avatar_url", avatar);
                    intent.putExtra("nick_name", nickname);
                }
                startActivity(intent);
                C2580w.a(getActivity(), "查看别人的骑行纪录列表", null);
                return;
            case C1373R.id.base_profile_detail_item_medal:
                intent = new Intent(getActivity(), MedalsActivity.class);
                intent.putExtra("user_id", b());
                if (this.e != null) {
                    intent.putExtra("medal_count", this.e.getMedalNum());
                }
                startActivity(intent);
                C2580w.a(getActivity(), "", "click_medal");
                return;
            case C1373R.id.base_profile_detail_item_grid:
                intent = new Intent(getActivity(), GridExploreActivity.class);
                intent.putExtra("user_id", b());
                if (this.e != null) {
                    intent.putExtra(Scopes.PROFILE, this.e);
                }
                startActivity(intent);
                return;
            case C1373R.id.base_profile_follow_btn:
                if (!this.f) {
                    this.d.b();
                    return;
                } else if (RongIM.getInstance() != null && this.e != null) {
                    C2580w.a(getActivity(), "", "click_profile_messenger");
                    RongIM.getInstance().startPrivateChat(getActivity(), b(), this.e.getNickname());
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m7682a(ProfileDTO profileDTO) {
        super.a(profileDTO);
        if (profileDTO != null) {
            this.f6475q.setText(String.format(getString(C1373R.string.profile_fragment_same_grid_count), new Object[]{Integer.valueOf(profileDTO.getSameNum())}));
        }
    }

    /* renamed from: a */
    public void m7684a(boolean z) {
        super.a(z);
    }

    /* renamed from: a */
    public void m7683a(UserDetailDTO userDetailDTO) {
        if (userDetailDTO != null) {
            CharSequence charSequence;
            CharSequence charSequence2;
            double totalDistance = userDetailDTO.getTotalDistance() / 1000.0d;
            double averageSpeed = userDetailDTO.getAverageSpeed();
            String string = getString(C1373R.string.str_total_mileage);
            String string2 = getString(C1373R.string.str_average_speed);
            if (this.f6476r) {
                charSequence = string + "(KM)";
                charSequence2 = string2 + "(KM/H)";
            } else {
                totalDistance = C1849a.a(totalDistance);
                averageSpeed = C1849a.d(averageSpeed);
                charSequence = string + "(MI)";
                charSequence2 = string2 + "(MPH)";
            }
            this.f6466h.setText(charSequence);
            this.f6465g.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(totalDistance)}));
            this.f6468j.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(averageSpeed)}));
            this.f6469k.setText(charSequence2);
            float totalElapsedTime = (((float) userDetailDTO.getTotalElapsedTime()) * 1.0f) / 3600.0f;
            this.f6467i.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Float.valueOf(totalElapsedTime)}));
            this.f6470l.setText(String.valueOf(userDetailDTO.getTotalCount()));
        }
    }

    /* renamed from: f */
    private void m7681f() {
        this.f6471m.setOnClickListener(this);
        this.f6472n.setOnClickListener(this);
        this.f6473o.setOnClickListener(this);
        this.f6474p.setOnClickListener(this);
        TextView textView = (TextView) this.f6472n.findViewById(C1373R.id.profile_fragment_detail_item_subject);
        TextView textView2 = (TextView) this.f6473o.findViewById(C1373R.id.profile_fragment_detail_item_subject);
        this.f6475q = (TextView) this.f6474p.findViewById(C1373R.id.profile_fragment_detail_item_subject);
        textView.setText(C1373R.string.str_cycling_history);
        textView2.setText(C1373R.string.str_personal_my_medal);
        this.f6475q.setTextColor(Color.parseColor("#999999"));
    }
}
