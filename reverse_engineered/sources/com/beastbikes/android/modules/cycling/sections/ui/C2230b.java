package com.beastbikes.android.modules.cycling.sections.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.sections.dto.C2223d;
import java.util.List;

/* compiled from: SectionDetailAdapter */
/* renamed from: com.beastbikes.android.modules.cycling.sections.ui.b */
public class C2230b extends BaseAdapter {
    /* renamed from: a */
    private Context f10610a;
    /* renamed from: b */
    private List<C2223d> f10611b;

    public C2230b(Context context, List<C2223d> list) {
        this.f10610a = context;
        this.f10611b = list;
    }

    public int getCount() {
        return this.f10611b.size();
    }

    public Object getItem(int i) {
        return this.f10611b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b$a b_a;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.item_section_detail, null);
            b_a = new b$a(this, view);
        } else {
            b_a = (b$a) view.getTag();
        }
        b_a.a((C2223d) this.f10611b.get(i));
        switch (i) {
            case 0:
                b$a.a(b_a).setText("");
                b$a.a(b_a).setBackgroundResource(C1373R.drawable.ic_section_detail_rank1);
                break;
            case 1:
                b$a.a(b_a).setText("");
                b$a.a(b_a).setBackgroundResource(C1373R.drawable.ic_section_detail_rank2);
                break;
            case 2:
                b$a.a(b_a).setText("");
                b$a.a(b_a).setBackgroundResource(C1373R.drawable.ic_section_detail_rank3);
                break;
            default:
                b$a.a(b_a).setText((i + 1) + "");
                b$a.a(b_a).setBackgroundResource(0);
                break;
        }
        return view;
    }
}
