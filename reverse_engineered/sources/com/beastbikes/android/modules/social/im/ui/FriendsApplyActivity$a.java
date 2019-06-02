package com.beastbikes.android.modules.social.im.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.android.modules.social.im.ui.FriendsApplyActivity.C1435b;
import java.util.List;

final class FriendsApplyActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ FriendsApplyActivity f11151a;
    /* renamed from: b */
    private final List<FriendDTO> f11152b;

    public FriendsApplyActivity$a(FriendsApplyActivity friendsApplyActivity, List<FriendDTO> list) {
        this.f11151a = friendsApplyActivity;
        this.f11152b = list;
    }

    public int getCount() {
        return this.f11152b.size();
    }

    public Object getItem(int i) {
        return this.f11152b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1435b c1435b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.apply_list_item, null);
            C1435b c1435b2 = new C1435b(this.f11151a, view);
            view.setTag(c1435b2);
            c1435b = c1435b2;
        } else {
            c1435b = (C1435b) view.getTag();
        }
        c1435b.a((FriendDTO) this.f11152b.get(i), i);
        return view;
    }
}
