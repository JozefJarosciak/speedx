package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityUser;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2570p;
import com.beastbikes.android.widget.C2638d.C2077a;
import com.squareup.picasso.Picasso;

class ClubActUserListActivity$a extends C2077a {
    /* renamed from: a */
    final /* synthetic */ ClubActUserListActivity f9447a;
    /* renamed from: b */
    private Context f9448b;
    /* renamed from: c */
    private LayoutInflater f9449c;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ClubActUserListActivity$a$a */
    class C2075a extends ViewHolder {
        /* renamed from: a */
        ImageView f9444a;
        /* renamed from: b */
        TextView f9445b;
        /* renamed from: c */
        final /* synthetic */ ClubActUserListActivity$a f9446c;

        public C2075a(ClubActUserListActivity$a clubActUserListActivity$a, View view) {
            this.f9446c = clubActUserListActivity$a;
            super(view);
            this.f9445b = (TextView) view.findViewById(C1373R.id.item_act_list_user_tv);
            this.f9444a = (ImageView) view.findViewById(C1373R.id.item_act_list_user_iv);
        }
    }

    public ClubActUserListActivity$a(ClubActUserListActivity clubActUserListActivity, Context context) {
        this.f9447a = clubActUserListActivity;
        this.f9448b = context;
        this.f9449c = LayoutInflater.from(context);
    }

    /* renamed from: a */
    public ViewHolder mo3371a() {
        return new C2075a(this, this.f9449c.inflate(C1373R.layout.item_act_list_user, null));
    }

    /* renamed from: a */
    public void mo3372a(ViewHolder viewHolder, Object obj, int i, boolean z) {
        if (viewHolder instanceof C2075a) {
            C2075a c2075a = (C2075a) viewHolder;
            final ClubActivityUser clubActivityUser = (ClubActivityUser) obj;
            if (clubActivityUser != null) {
                if (TextUtils.isEmpty(clubActivityUser.getAvatar())) {
                    c2075a.f9444a.setImageResource(C1373R.drawable.ic_avatar);
                } else {
                    Picasso.with(this.f9448b).load(clubActivityUser.getAvatar()).fit().error(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).centerCrop().into(c2075a.f9444a);
                }
                c2075a.f9445b.setText(C2570p.m12883a(clubActivityUser.getNickname(), clubActivityUser.getRemarks()));
                c2075a.itemView.setOnClickListener(new OnClickListener(this) {
                    /* renamed from: b */
                    final /* synthetic */ ClubActUserListActivity$a f9443b;

                    public void onClick(View view) {
                        Intent intent = new Intent(this.f9443b.f9448b, ProfileActivity.class);
                        intent.putExtra("user_id", clubActivityUser.getUserId());
                        intent.putExtra("avatar", clubActivityUser.getAvatar());
                        intent.putExtra("nick_name", clubActivityUser.getNickname());
                        intent.putExtra("remarks", clubActivityUser.getRemarks());
                        this.f9443b.f9448b.startActivity(intent);
                    }
                });
            }
        }
    }
}
