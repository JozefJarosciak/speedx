package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.modules.user.dto.UserDetailDTO;
import com.beastbikes.android.modules.user.p152b.C2403c;
import com.beastbikes.android.modules.user.view.C2469c;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.beastbikes.framework.ui.android.utils.ViewIntrospector;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;

public class BaseProfileFragment extends SessionFragment implements OnClickListener, C2469c {
    /* renamed from: a */
    public CircleImageView f11538a;
    /* renamed from: b */
    public TextView f11539b;
    /* renamed from: c */
    public TextView f11540c;
    /* renamed from: d */
    public C2403c f11541d;
    /* renamed from: e */
    public ProfileDTO f11542e;
    /* renamed from: f */
    public boolean f11543f;
    /* renamed from: g */
    private ImageView f11544g;
    /* renamed from: h */
    private TextView f11545h;
    /* renamed from: i */
    private TextView f11546i;
    /* renamed from: j */
    private LinearLayout f11547j;
    /* renamed from: k */
    private TextView f11548k;
    /* renamed from: l */
    private TextView f11549l;
    /* renamed from: m */
    private TextView f11550m;
    /* renamed from: n */
    private TextView f11551n;
    /* renamed from: o */
    private ViewGroup f11552o;
    /* renamed from: p */
    private Button f11553p;
    /* renamed from: q */
    private TextView f11554q;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1373R.layout.activity_base_profile, null);
        m12366a(inflate);
        return inflate;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f11541d = new C2403c(this);
    }

    /* renamed from: a */
    public void m12368a(@LayoutRes int i) {
        ViewIntrospector.introspect(LayoutInflater.from(getActivity()).inflate(i, this.f11552o), (Object) this);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case C1373R.id.base_profile_avatar:
                if (this.f11542e != null) {
                    intent = new Intent(getActivity(), AvatarViewer.class);
                    intent.putExtra("user_id", this.f11542e.getUserId());
                    intent.putExtra("user_avatar_url", this.f11542e.getAvatar());
                    startActivity(intent);
                    return;
                }
                return;
            case C1373R.id.base_profile_follow_number_view:
                intent = new Intent(getActivity(), FollowActivity.class);
                intent.putExtra("user_id", m9708b());
                startActivity(intent);
                return;
            case C1373R.id.base_profile_follower_view:
                m12375e();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public String mo3486a() {
        return m9708b();
    }

    /* renamed from: c */
    public SessionFragment mo3491c() {
        return this;
    }

    /* renamed from: a */
    public void mo3487a(ProfileDTO profileDTO) {
        if (profileDTO != null) {
            boolean z;
            this.f11542e = profileDTO;
            Object avatar = profileDTO.getAvatar();
            if (!TextUtils.isEmpty(avatar)) {
                Picasso.with(getActivity()).load(avatar).fit().placeholder(C1373R.drawable.ic_avatar).error(C1373R.drawable.ic_avatar).centerCrop().into(this.f11538a);
            }
            this.f11544g.setImageResource(profileDTO.getGender() == 0 ? C1373R.drawable.ic_gender_female : C1373R.drawable.ic_gender_male);
            CharSequence remarks = profileDTO.getRemarks();
            if (!TextUtils.isEmpty(remarks)) {
                this.f11545h.setText(remarks);
            }
            CharSequence nickname = profileDTO.getNickname();
            if (!TextUtils.isEmpty(nickname)) {
                if (!TextUtils.isEmpty(remarks)) {
                    nickname = "(" + nickname + ")";
                }
                this.f11539b.setText(nickname);
            }
            if (!TextUtils.isEmpty(profileDTO.getLocation())) {
                this.f11540c.setText(profileDTO.getLocation());
            }
            this.f11546i.setText("ID: " + profileDTO.getSpeedxId());
            this.f11547j.setVisibility(0);
            if (TextUtils.isEmpty(profileDTO.getClubName())) {
                this.f11549l.setText(C1373R.string.str_not_join_club);
                this.f11548k.setVisibility(8);
            } else {
                this.f11549l.setText(profileDTO.getClubName());
                this.f11548k.setVisibility(0);
                this.f11548k.setText("LV" + profileDTO.getClubLevel());
            }
            this.f11550m.setText(String.valueOf(profileDTO.getFollowNum()));
            this.f11551n.setText(String.valueOf(profileDTO.getFansNum()));
            int followStatu = profileDTO.getFollowStatu();
            if (followStatu == 2 || followStatu == 3) {
                z = true;
            } else {
                z = false;
            }
            this.f11543f = z;
            Button button = this.f11553p;
            if (this.f11543f) {
                followStatu = C1373R.string.friends_send_message;
            } else {
                followStatu = C1373R.string.profile_fragment_follow;
            }
            button.setText(followStatu);
        }
    }

    /* renamed from: a */
    public void mo3489a(boolean z) {
        if (z) {
            Toasts.show(getActivity(), (int) C1373R.string.lable_follow_success_msg);
            this.f11551n.setText(String.valueOf(this.f11542e.getFansNum() + 1));
            this.f11553p.setText(C1373R.string.friends_send_message);
            this.f11543f = true;
        }
    }

    /* renamed from: a */
    public void mo3488a(UserDetailDTO userDetailDTO) {
    }

    /* renamed from: d */
    public long mo3492d() {
        return 0;
    }

    /* renamed from: b */
    public void mo3490b(int i) {
        this.f11554q.setVisibility(i > 0 ? 0 : 8);
        this.f11554q.setText(i > 99 ? "99+" : String.valueOf(i));
    }

    public void onResume() {
        super.onResume();
        this.f11541d.m12200a();
    }

    /* renamed from: a */
    private void m12366a(View view) {
        this.f11538a = (CircleImageView) view.findViewById(C1373R.id.base_profile_avatar);
        this.f11544g = (ImageView) view.findViewById(C1373R.id.base_profile_gender);
        this.f11545h = (TextView) view.findViewById(C1373R.id.base_profile_remark_name);
        this.f11539b = (TextView) view.findViewById(C1373R.id.base_profile_nick_name);
        this.f11540c = (TextView) view.findViewById(C1373R.id.base_profile_location);
        this.f11546i = (TextView) view.findViewById(C1373R.id.base_profile_id);
        this.f11547j = (LinearLayout) view.findViewById(C1373R.id.base_profile_club_view);
        this.f11548k = (TextView) view.findViewById(C1373R.id.base_profile_club_level);
        this.f11549l = (TextView) view.findViewById(C1373R.id.base_profile_club_name);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(C1373R.id.base_profile_statistic_view);
        this.f11552o = (ViewGroup) view.findViewById(C1373R.id.base_profile_parent_view);
        ((ViewGroup) viewGroup.findViewById(C1373R.id.base_profile_follow_number_view)).setOnClickListener(this);
        ((FrameLayout) viewGroup.findViewById(C1373R.id.base_profile_follower_view)).setOnClickListener(this);
        this.f11550m = (TextView) viewGroup.findViewById(C1373R.id.base_profile_follow_number_tv);
        this.f11551n = (TextView) viewGroup.findViewById(C1373R.id.base_profile_follower_number_tv);
        this.f11554q = (TextView) viewGroup.findViewById(C1373R.id.base_profile_follower_dot);
        LinearLayout linearLayout = (LinearLayout) viewGroup.findViewById(C1373R.id.base_profile_follow_view);
        this.f11553p = (Button) viewGroup.findViewById(C1373R.id.base_profile_follow_btn);
        this.f11538a.setOnClickListener(this);
        this.f11538a.setFocusable(true);
        this.f11538a.setFocusableInTouchMode(true);
        this.f11538a.requestFocus();
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null && currentUser.getObjectId().equals(m9708b())) {
            linearLayout.setVisibility(8);
        }
        this.f11553p.setOnClickListener(this);
    }

    /* renamed from: e */
    public void m12375e() {
        Intent intent = new Intent(getActivity(), FansActivity.class);
        intent.putExtra("user_id", m9708b());
        startActivity(intent);
    }
}
