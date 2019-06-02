package com.beastbikes.android.modules.message.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.message.dto.MessageDTO;
import com.beastbikes.android.modules.message.ui.MessageActivity.C1428c;
import java.util.List;

final class MessageActivity$b extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ MessageActivity f10922a;
    /* renamed from: b */
    private final Context f10923b;
    /* renamed from: c */
    private final List<MessageDTO> f10924c;

    public MessageActivity$b(MessageActivity messageActivity, Context context, List<MessageDTO> list) {
        this.f10922a = messageActivity;
        this.f10923b = context;
        this.f10924c = list;
    }

    public int getCount() {
        return this.f10924c.size();
    }

    public Object getItem(int i) {
        return this.f10924c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1428c c1428c;
        if (view == null) {
            view = LayoutInflater.from(this.f10923b).inflate(C1373R.layout.message_activity_list_item, null);
            c1428c = new C1428c(this.f10922a, view);
        } else {
            c1428c = (C1428c) view.getTag();
        }
        c1428c.a((MessageDTO) this.f10924c.get(i));
        return view;
    }
}
