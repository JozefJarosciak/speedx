package com.beastbikes.android.ble.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.dto.CadenceDTO;
import java.util.List;

class SpeedXCadenceSettingActivity$a extends PagerAdapter {
    /* renamed from: a */
    final /* synthetic */ SpeedXCadenceSettingActivity f7714a;
    /* renamed from: b */
    private List<CadenceDTO> f7715b;
    /* renamed from: c */
    private LayoutInflater f7716c;
    /* renamed from: d */
    private SparseArray<View> f7717d;

    public SpeedXCadenceSettingActivity$a(SpeedXCadenceSettingActivity speedXCadenceSettingActivity, Context context, List<CadenceDTO> list) {
        this.f7714a = speedXCadenceSettingActivity;
        this.f7715b = list;
        this.f7717d = new SparseArray(list.size());
        this.f7716c = LayoutInflater.from(context);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = (View) this.f7717d.get(i);
        if (view == null) {
            view = this.f7716c.inflate(C1373R.layout.layout_cadence_viewpager_item, null);
            this.f7717d.put(i, view);
        }
        View view2 = view;
        TextView textView = (TextView) view2.findViewById(C1373R.id.cadence_item_title);
        TextView textView2 = (TextView) view2.findViewById(C1373R.id.cadence_item_desc);
        TextView textView3 = (TextView) view2.findViewById(C1373R.id.cadence_item_value);
        ImageView imageView = (ImageView) view2.findViewById(C1373R.id.cadence_item_select_iv);
        CadenceDTO cadenceDTO = (CadenceDTO) this.f7715b.get(i);
        if (cadenceDTO != null) {
            textView.setText(cadenceDTO.getTitle());
            textView2.setText(cadenceDTO.getDesc());
            textView3.setText(cadenceDTO.getData());
            imageView.setVisibility(cadenceDTO.isSelected() ? 0 : 8);
        }
        viewGroup.addView(view2);
        return view2;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) this.f7717d.get(i));
    }

    public int getCount() {
        return this.f7715b.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public CharSequence getPageTitle(int i) {
        return ((CadenceDTO) this.f7715b.get(i)).getTitle();
    }
}
