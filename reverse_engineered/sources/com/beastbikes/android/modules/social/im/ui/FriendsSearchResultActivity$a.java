package com.beastbikes.android.modules.social.im.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.android.modules.social.im.ui.FriendsSearchResultActivity.C1436b;
import java.util.List;

final class FriendsSearchResultActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ FriendsSearchResultActivity f11164a;
    /* renamed from: b */
    private final List<FriendDTO> f11165b;

    public FriendsSearchResultActivity$a(FriendsSearchResultActivity friendsSearchResultActivity, List<FriendDTO> list) {
        this.f11164a = friendsSearchResultActivity;
        this.f11165b = list;
    }

    public int getCount() {
        return this.f11165b.size();
    }

    public Object getItem(int i) {
        return this.f11165b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1436b c1436b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.apply_list_item, null);
            C1436b c1436b2 = new C1436b(this.f11164a, view);
            view.setTag(c1436b2);
            c1436b = c1436b2;
        } else {
            c1436b = (C1436b) view.getTag();
        }
        c1436b.a((FriendDTO) this.f11165b.get(i));
        return view;
    }
}
