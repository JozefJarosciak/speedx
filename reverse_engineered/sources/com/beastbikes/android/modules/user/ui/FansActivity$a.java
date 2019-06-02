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

final class FansActivity$a extends C2077a {
    /* renamed from: a */
    final /* synthetic */ FansActivity f11609a;
    /* renamed from: b */
    private Context f11610b;
    /* renamed from: c */
    private C2534b f11611c;

    public FansActivity$a(FansActivity fansActivity, Context context, C2534b c2534b) {
        this.f11609a = fansActivity;
        this.f11610b = context;
        this.f11611c = c2534b;
    }

    /* renamed from: a */
    public ViewHolder mo3371a() {
        return new FansActivity$b(this.f11609a, LayoutInflater.from(this.f11610b).inflate(C1373R.layout.fans_and_follow_item, null, false));
    }

    /* renamed from: a */
    public void mo3372a(ViewHolder viewHolder, Object obj, final int i, boolean z) {
        if (obj != null) {
            final FriendDTO friendDTO = (FriendDTO) obj;
            final FansActivity$b fansActivity$b = (FansActivity$b) viewHolder;
            Object avatar = friendDTO.getAvatar();
            if (TextUtils.isEmpty(avatar)) {
                Picasso.with(this.f11610b).load(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).error(C1373R.drawable.ic_avatar).fit().centerCrop().into(fansActivity$b.f11613b);
            } else {
                Picasso.with(this.f11610b).load(avatar).placeholder(C1373R.drawable.ic_avatar).error(C1373R.drawable.ic_avatar).fit().centerCrop().into(fansActivity$b.f11613b);
            }
            CharSequence remarks = friendDTO.getRemarks();
            if (TextUtils.isEmpty(remarks)) {
                remarks = friendDTO.getNickname();
            }
            fansActivity$b.f11614c.setText(remarks);
            switch (friendDTO.getStatus()) {
                case 0:
                case 1:
                    fansActivity$b.f11615d.setText(C1373R.string.profile_fragment_follow);
                    fansActivity$b.f11615d.setTextColor(this.f11609a.getResources().getColor(C1373R.color.design_color_c7));
                    fansActivity$b.f11615d.setBackgroundResource(C1373R.drawable.border_1px_solid_red_radius_6dp);
                    break;
                case 2:
                case 3:
                    fansActivity$b.f11615d.setText(C1373R.string.label_mutual_follower);
                    fansActivity$b.f11615d.setTextColor(this.f11609a.getResources().getColor(C1373R.color.text_black_color));
                    fansActivity$b.f11615d.setBackgroundResource(C1373R.drawable.border_1px_solid_white_black_radius_2dp);
                    break;
            }
            fansActivity$b.f11616e.setOnClickListener(new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ FansActivity$a f11602c;

                public void onClick(View view) {
                    this.f11602c.f11611c.mo3520a(fansActivity$b, i);
                }
            });
            fansActivity$b.f11616e.setOnLongClickListener(new OnLongClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ FansActivity$a f11605c;

                public boolean onLongClick(View view) {
                    this.f11605c.f11611c.mo3521b(fansActivity$b, i);
                    return true;
                }
            });
            fansActivity$b.f11615d.setOnClickListener(new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ FansActivity$a f11608c;

                public void onClick(View view) {
                    switch (friendDTO.getStatus()) {
                        case 0:
                        case 1:
                            FansActivity.b(this.f11608c.f11609a, friendDTO.getFriendId(), i);
                            return;
                        case 2:
                        case 3:
                            FansActivity.c(this.f11608c.f11609a, friendDTO.getFriendId(), i);
                            return;
                        default:
                            return;
                    }
                }
            });
        }
    }
}
