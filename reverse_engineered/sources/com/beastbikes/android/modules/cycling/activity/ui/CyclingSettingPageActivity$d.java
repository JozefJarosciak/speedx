package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;

class CyclingSettingPageActivity$d extends BaseAdapter {
    /* renamed from: a */
    public final String[] f8685a;

    public CyclingSettingPageActivity$d(Context context) {
        this.f8685a = context.getResources().getStringArray(C1373R.array.cycling_setting_array);
    }

    public int getCount() {
        return this.f8685a.length;
    }

    public Object getItem(int i) {
        return this.f8685a[i];
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        CyclingSettingPageActivity$c cyclingSettingPageActivity$c;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.cycling_setting_dialog_layout, null);
            cyclingSettingPageActivity$c = new CyclingSettingPageActivity$c(view, i);
        } else {
            cyclingSettingPageActivity$c = (CyclingSettingPageActivity$c) view.getTag();
        }
        cyclingSettingPageActivity$c.m9961a(this.f8685a[i]);
        return view;
    }
}
