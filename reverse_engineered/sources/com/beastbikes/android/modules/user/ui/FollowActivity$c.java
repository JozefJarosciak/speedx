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

final class FollowActivity$c extends C2077a {
    /* renamed from: a */
    final /* synthetic */ FollowActivity f11668a;
    /* renamed from: b */
    private Context f11669b;
    /* renamed from: c */
    private C2534b f11670c;

    public FollowActivity$c(FollowActivity followActivity, Context context, C2534b c2534b) {
        this.f11668a = followActivity;
        this.f11669b = context;
        this.f11670c = c2534b;
    }

    /* renamed from: a */
    public ViewHolder mo3371a() {
        return new FollowActivity$d(this.f11668a, LayoutInflater.from(this.f11669b).inflate(C1373R.layout.fans_and_follow_item, null, false));
    }

    /* renamed from: a */
    public void mo3372a(ViewHolder viewHolder, Object obj, final int i, boolean z) {
        if (obj != null) {
            final FriendDTO friendDTO = (FriendDTO) obj;
            if (friendDTO != null) {
                final FollowActivity$d followActivity$d = (FollowActivity$d) viewHolder;
                Object avatar = friendDTO.getAvatar();
                if (TextUtils.isEmpty(avatar)) {
                    Picasso.with(this.f11669b).load(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).error(C1373R.drawable.ic_avatar).fit().centerCrop().into(followActivity$d.f11672b);
                } else {
                    Picasso.with(this.f11669b).load(avatar).placeholder(C1373R.drawable.ic_avatar).error(C1373R.drawable.ic_avatar).fit().centerCrop().into(followActivity$d.f11672b);
                }
                CharSequence remarks = friendDTO.getRemarks();
                if (TextUtils.isEmpty(remarks)) {
                    remarks = friendDTO.getNickname();
                }
                followActivity$d.f11673c.setText(remarks);
                switch (friendDTO.getStatus()) {
                    case 0:
                    case 1:
                        followActivity$d.f11674d.setText(C1373R.string.profile_fragment_follow);
                        followActivity$d.f11674d.setTextColor(this.f11668a.getResources().getColor(C1373R.color.design_color_c7));
                        followActivity$d.f11674d.setBackgroundResource(C1373R.drawable.border_1px_solid_red_radius_6dp);
                        break;
                    case 2:
                        followActivity$d.f11674d.setText(C1373R.string.label_already_follower);
                        followActivity$d.f11674d.setTextColor(this.f11668a.getResources().getColor(C1373R.color.text_black_color));
                        followActivity$d.f11674d.setBackgroundResource(C1373R.drawable.border_1px_solid_white_black_radius_2dp);
                        break;
                    case 3:
                        followActivity$d.f11674d.setText(C1373R.string.label_mutual_follower);
                        followActivity$d.f11674d.setTextColor(this.f11668a.getResources().getColor(C1373R.color.text_black_color));
                        followActivity$d.f11674d.setBackgroundResource(C1373R.drawable.border_1px_solid_white_black_radius_2dp);
                        break;
                }
                followActivity$d.f11675e.setOnClickListener(new OnClickListener(this) {
                    /* renamed from: c */
                    final /* synthetic */ FollowActivity$c f11661c;

                    public void onClick(View view) {
                        this.f11661c.f11670c.mo3520a(followActivity$d, i);
                    }
                });
                followActivity$d.f11675e.setOnLongClickListener(new OnLongClickListener(this) {
                    /* renamed from: c */
                    final /* synthetic */ FollowActivity$c f11664c;

                    public boolean onLongClick(View view) {
                        this.f11664c.f11670c.mo3521b(followActivity$d, i);
                        return true;
                    }
                });
                followActivity$d.f11674d.setOnClickListener(new OnClickListener(this) {
                    /* renamed from: c */
                    final /* synthetic */ FollowActivity$c f11667c;

                    public void onClick(View view) {
                        switch (friendDTO.getStatus()) {
                            case 0:
                            case 1:
                                FollowActivity.b(this.f11667c.f11668a, friendDTO.getFriendId(), i);
                                return;
                            case 2:
                            case 3:
                                FollowActivity.c(this.f11667c.f11668a, friendDTO.getFriendId(), i);
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
