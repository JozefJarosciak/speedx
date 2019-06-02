package com.beastbikes.android.modules.user.ui;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.dto.SearchRegionDTO;
import com.beastbikes.android.modules.user.ui.p160c.C2524a;
import java.util.ArrayList;

class SearchRegionActivity$a extends Adapter<SearchRegionActivity$b> {
    /* renamed from: a */
    final /* synthetic */ SearchRegionActivity f11772a;
    /* renamed from: b */
    private ArrayList<SearchRegionDTO> f11773b = new ArrayList();
    /* renamed from: c */
    private C2524a f11774c;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m12534a((SearchRegionActivity$b) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m12533a(viewGroup, i);
    }

    public SearchRegionActivity$a(SearchRegionActivity searchRegionActivity, C2524a c2524a) {
        this.f11772a = searchRegionActivity;
        this.f11774c = c2524a;
    }

    /* renamed from: a */
    public SearchRegionActivity$b m12533a(ViewGroup viewGroup, int i) {
        return new SearchRegionActivity$b(this.f11772a, LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.list_item_search_region, viewGroup, false), this.f11774c);
    }

    /* renamed from: a */
    public void m12534a(SearchRegionActivity$b searchRegionActivity$b, int i) {
        SearchRegionDTO searchRegionDTO = (SearchRegionDTO) this.f11773b.get(i);
        if (searchRegionDTO != null) {
            searchRegionActivity$b.itemView.setTag(searchRegionDTO);
            StringBuilder stringBuilder = new StringBuilder();
            if (!TextUtils.isEmpty(searchRegionDTO.getCountry())) {
                stringBuilder.append(searchRegionDTO.getCountry());
            }
            if (!TextUtils.isEmpty(searchRegionDTO.getProvince())) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(searchRegionDTO.getProvince());
            }
            if (!TextUtils.isEmpty(searchRegionDTO.getCity())) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(searchRegionDTO.getCity());
            }
            searchRegionActivity$b.f11777c.setText(stringBuilder.toString());
        }
    }

    public int getItemCount() {
        return this.f11773b.size();
    }

    /* renamed from: a */
    public void m12535a(ArrayList<SearchRegionDTO> arrayList) {
        this.f11773b.clear();
        if (arrayList != null) {
            this.f11773b.addAll(arrayList);
        }
        notifyDataSetChanged();
    }
}
