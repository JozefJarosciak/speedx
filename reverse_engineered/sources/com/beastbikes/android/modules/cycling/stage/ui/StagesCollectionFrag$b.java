package com.beastbikes.android.modules.cycling.stage.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.stage.dto.StageDTO;
import java.util.List;

class StagesCollectionFrag$b extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ StagesCollectionFrag f10778a;
    /* renamed from: b */
    private List<StageDTO> f10779b;

    StagesCollectionFrag$b(StagesCollectionFrag stagesCollectionFrag, List<StageDTO> list) {
        this.f10778a = stagesCollectionFrag;
        this.f10779b = list;
    }

    public int getCount() {
        return this.f10779b.size();
    }

    public Object getItem(int i) {
        return this.f10779b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        StagesCollectionFrag$a stagesCollectionFrag$a;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.fragment_stage_collection_list_item, null);
            stagesCollectionFrag$a = new StagesCollectionFrag$a(this.f10778a, view);
        } else {
            stagesCollectionFrag$a = (StagesCollectionFrag$a) view.getTag();
        }
        if (i == StagesCollectionFrag.a(this.f10778a).size() - 1) {
            stagesCollectionFrag$a.m11619a((StageDTO) getItem(i), true);
        } else {
            stagesCollectionFrag$a.m11619a((StageDTO) getItem(i), false);
        }
        return view;
    }
}
