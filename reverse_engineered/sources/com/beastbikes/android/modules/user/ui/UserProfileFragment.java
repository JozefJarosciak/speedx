package com.beastbikes.android.modules.user.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.preferences.ui.SettingActivity;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.google.android.gms.common.Scopes;
import com.squareup.picasso.Picasso;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient$ConnectionStatusListener$ConnectionStatus;

@C1460c(a = 2131820588)
public class UserProfileFragment extends BaseProfileFragment {
    /* renamed from: g */
    private int f6613g = 48;
    @C1458a(a = 2131756695)
    /* renamed from: h */
    private FrameLayout f6614h;
    @C1458a(a = 2131756696)
    /* renamed from: i */
    private FrameLayout f6615i;
    @C1458a(a = 2131756697)
    /* renamed from: j */
    private FrameLayout f6616j;
    @C1458a(a = 2131756698)
    /* renamed from: k */
    private TextView f6617k;
    /* renamed from: l */
    private SharedPreferences f6618l;

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        super.a(C1373R.layout.fragment_user_profile);
        setHasOptionsMenu(true);
        this.f6614h.setOnClickListener(this);
        this.f6615i.setOnClickListener(this);
        this.f6616j.setOnClickListener(this);
        this.f6618l = getActivity().getSharedPreferences(b(), 0);
        this.d.d();
    }

    public void onResume() {
        super.onResume();
        m7842f();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C1373R.id.setting_menu_item) {
            startActivityForResult(new Intent(getActivity(), SettingActivity.class), this.f6613g);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.f6613g) {
            m7843g();
        }
    }

    public void onClick(View view) {
        super.onClick(view);
        Intent intent;
        switch (view.getId()) {
            case C1373R.id.user_profile_medal_view:
                intent = new Intent(getActivity(), MedalsActivity.class);
                intent.putExtra("user_id", b());
                if (this.e != null) {
                    intent.putExtra("medal_count", this.e.getMedalNum());
                }
                startActivity(intent);
                C2580w.a(getActivity(), "", "click_medal");
                return;
            case C1373R.id.user_profile_grid_view:
                intent = new Intent(getActivity(), GridExploreActivity.class);
                intent.putExtra("user_id", b());
                if (this.e != null) {
                    intent.putExtra(Scopes.PROFILE, this.e);
                }
                startActivity(intent);
                return;
            case C1373R.id.user_profile_message_view:
                if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED)) {
                    try {
                        RongIM.getInstance().startConversationList(getActivity(), null);
                        return;
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: e */
    public void m7846e() {
        super.e();
        this.f6618l.edit().putInt("beast.follow.dot", 0).apply();
    }

    /* renamed from: d */
    public long m7845d() {
        return this.f6618l.getLong("message.lastdate", 0);
    }

    /* renamed from: b */
    public void m7844b(int i) {
        this.f6618l.edit().putInt("beast.friend.new.message.count", i).apply();
        m7842f();
    }

    /* renamed from: f */
    private void m7842f() {
        int i = this.f6618l.getInt("beast.rongcloud.new.message.count", 0);
        int i2 = this.f6618l.getInt("beast.friend.new.message.count", 0) + i;
        this.f6617k.setVisibility(i2 > 0 ? 0 : 8);
        this.f6617k.setText(i2 > 99 ? "99+" : String.valueOf(i));
        super.b(this.f6618l.getInt("beast.follow.dot", 0));
    }

    /* renamed from: g */
    private void m7843g() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            StringBuilder stringBuilder = new StringBuilder();
            if (!TextUtils.isEmpty(currentUser.getCountry())) {
                stringBuilder.append(currentUser.getCountry());
            }
            if (!TextUtils.isEmpty(currentUser.getProvince())) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(currentUser.getProvince());
            }
            if (!TextUtils.isEmpty(currentUser.getCity())) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(currentUser.getCity());
            }
            if (!TextUtils.isEmpty(currentUser.getAvatar())) {
                Picasso.with(getActivity()).load(currentUser.getAvatar()).fit().placeholder((int) C1373R.drawable.ic_avatar).error((int) C1373R.drawable.ic_avatar).centerCrop().into(this.a);
            }
            this.c.setText(stringBuilder.toString());
            this.b.setText(currentUser.getDisplayName());
        }
    }
}
