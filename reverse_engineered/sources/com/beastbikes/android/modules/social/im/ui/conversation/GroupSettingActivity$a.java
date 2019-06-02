package com.beastbikes.android.modules.social.im.ui.conversation;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;

class GroupSettingActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ GroupSettingActivity f11184a;

    GroupSettingActivity$a(GroupSettingActivity groupSettingActivity) {
        this.f11184a = groupSettingActivity;
    }

    public int getCount() {
        if (GroupSettingActivity.d(this.f11184a) == null) {
            return 0;
        }
        if (GroupSettingActivity.d(this.f11184a).size() > 6) {
            return 6;
        }
        return GroupSettingActivity.d(this.f11184a).size();
    }

    public Object getItem(int i) {
        return GroupSettingActivity.d(this.f11184a).get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View circleImageView = new CircleImageView(viewGroup.getContext());
        LayoutParams layoutParams = new LinearLayout.LayoutParams((int) this.f11184a.getResources().getDimension(C1373R.dimen.avatar_m), (int) this.f11184a.getResources().getDimension(C1373R.dimen.avatar_m));
        layoutParams.setMargins(0, 0, (int) this.f11184a.getResources().getDimension(C1373R.dimen.avatar_margin), 0);
        circleImageView.setScaleType(ScaleType.CENTER_CROP);
        circleImageView.setLayoutParams(layoutParams);
        RankDTO rankDTO = (RankDTO) getItem(i);
        if (rankDTO != null) {
            if (TextUtils.isEmpty(rankDTO.getAvatarUrl())) {
                circleImageView.setImageResource(C1373R.drawable.ic_avatar);
            } else {
                Picasso.with(viewGroup.getContext()).load(rankDTO.getAvatarUrl()).fit().error(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).centerCrop().into(circleImageView);
            }
        }
        return circleImageView;
    }
}
