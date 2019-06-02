package com.beastbikes.android.modules.user.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.android.widget.C2638d.C2077a;
import com.beastbikes.android.widget.p081b.C2534b;
import com.squareup.picasso.Picasso;

final class FollowSearchResultActivity$a extends C2077a {
    /* renamed from: a */
    final /* synthetic */ FollowSearchResultActivity f11706a;
    /* renamed from: b */
    private Context f11707b;
    /* renamed from: c */
    private C2534b f11708c;

    public FollowSearchResultActivity$a(FollowSearchResultActivity followSearchResultActivity, Context context, C2534b c2534b) {
        this.f11706a = followSearchResultActivity;
        this.f11707b = context;
        this.f11708c = c2534b;
    }

    /* renamed from: a */
    public ViewHolder mo3371a() {
        return new FollowSearchResultActivity$b(this.f11706a, LayoutInflater.from(this.f11707b).inflate(C1373R.layout.follow_search_result_item, null, false));
    }

    /* renamed from: a */
    public void mo3372a(ViewHolder viewHolder, Object obj, final int i, boolean z) {
        if (obj != null) {
            final FriendDTO friendDTO = (FriendDTO) obj;
            if (friendDTO != null) {
                final FollowSearchResultActivity$b followSearchResultActivity$b = (FollowSearchResultActivity$b) viewHolder;
                Object avatar = friendDTO.getAvatar();
                if (TextUtils.isEmpty(avatar)) {
                    Picasso.with(this.f11707b).load(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).error(C1373R.drawable.ic_avatar).fit().centerCrop().into(followSearchResultActivity$b.f11710b);
                } else {
                    Picasso.with(this.f11707b).load(avatar).placeholder(C1373R.drawable.ic_avatar).error(C1373R.drawable.ic_avatar).fit().centerCrop().into(followSearchResultActivity$b.f11710b);
                }
                CharSequence remarks = friendDTO.getRemarks();
                if (TextUtils.isEmpty(remarks)) {
                    remarks = friendDTO.getNickname();
                }
                followSearchResultActivity$b.f11711c.setText(remarks);
                StringBuilder stringBuilder = new StringBuilder();
                if (TextUtils.isEmpty(friendDTO.getThirdNick())) {
                    if (!(TextUtils.isEmpty(friendDTO.getProvince()) || friendDTO.getProvince().equals("null"))) {
                        stringBuilder.append(friendDTO.getProvince()).append(" ");
                    }
                    if (!friendDTO.getProvince().equals("null")) {
                        stringBuilder.append(friendDTO.getCity());
                    }
                } else {
                    stringBuilder.append(friendDTO.getThirdNick());
                }
                followSearchResultActivity$b.f11713e.setText(stringBuilder.toString());
                if (!TextUtils.isEmpty(friendDTO.getClubName())) {
                    followSearchResultActivity$b.f11714f.setText(friendDTO.getClubName());
                }
                switch (friendDTO.getStatus()) {
                    case 0:
                    case 1:
                        followSearchResultActivity$b.f11712d.setText(C1373R.string.profile_fragment_follow);
                        followSearchResultActivity$b.f11712d.setTextColor(this.f11706a.getResources().getColor(C1373R.color.design_color_c7));
                        followSearchResultActivity$b.f11712d.setBackgroundResource(C1373R.drawable.border_1px_solid_red_radius_6dp);
                        break;
                    case 2:
                        followSearchResultActivity$b.f11712d.setText(C1373R.string.label_already_follower);
                        followSearchResultActivity$b.f11712d.setTextColor(this.f11706a.getResources().getColor(C1373R.color.text_black_color));
                        followSearchResultActivity$b.f11712d.setBackgroundResource(C1373R.drawable.border_1px_solid_white_black_radius_2dp);
                        break;
                    case 3:
                        followSearchResultActivity$b.f11712d.setText(C1373R.string.label_mutual_follower);
                        followSearchResultActivity$b.f11712d.setTextColor(this.f11706a.getResources().getColor(C1373R.color.text_black_color));
                        followSearchResultActivity$b.f11712d.setBackgroundResource(C1373R.drawable.border_1px_solid_white_black_radius_2dp);
                        break;
                }
                followSearchResultActivity$b.f11715g.setOnClickListener(new OnClickListener(this) {
                    /* renamed from: c */
                    final /* synthetic */ FollowSearchResultActivity$a f11699c;

                    public void onClick(View view) {
                        this.f11699c.f11708c.mo3520a(followSearchResultActivity$b, i);
                    }
                });
                followSearchResultActivity$b.f11715g.setOnLongClickListener(new OnLongClickListener(this) {
                    /* renamed from: c */
                    final /* synthetic */ FollowSearchResultActivity$a f11702c;

                    public boolean onLongClick(View view) {
                        this.f11702c.f11708c.mo3521b(followSearchResultActivity$b, i);
                        return true;
                    }
                });
                followSearchResultActivity$b.f11712d.setOnClickListener(new OnClickListener(this) {
                    /* renamed from: c */
                    final /* synthetic */ FollowSearchResultActivity$a f11705c;

                    public void onClick(View view) {
                        switch (friendDTO.getStatus()) {
                            case 0:
                            case 1:
                                FollowSearchResultActivity.b(this.f11705c.f11706a, friendDTO.getFriendId(), i);
                                return;
                            case 2:
                            case 3:
                                FollowSearchResultActivity.c(this.f11705c.f11706a, friendDTO.getFriendId(), i);
                                return;
                            default:
                                return;
                        }
                    }
                });
            }
        }
    }
}
