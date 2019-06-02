package com.beastbikes.android.modules.preferences.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.preferences.ui.OfflineMapSettingActivity.C1429a;
import com.beastbikes.android.modules.preferences.ui.OfflineMapSettingActivity.C1430b;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import java.util.ArrayList;
import java.util.List;

final class OfflineMapSettingActivity$d extends BaseExpandableListAdapter {
    /* renamed from: a */
    private final List<OfflineMapSettingActivity$e> f10945a = new ArrayList();

    public OfflineMapSettingActivity$d(OfflineMapSettingActivity offlineMapSettingActivity) {
        this.f10945a.add(new OfflineMapSettingActivity$c(offlineMapSettingActivity));
        this.f10945a.add(new OfflineMapSettingActivity$g(offlineMapSettingActivity));
    }

    /* renamed from: a */
    public void m11815a(OfflineMapSettingActivity offlineMapSettingActivity, int i) {
        int groupCount = getGroupCount();
        int i2 = 0;
        while (i2 < groupCount) {
            try {
                int childrenCount = getChildrenCount(i2);
                for (int i3 = 0; i3 < childrenCount; i3++) {
                    OfflineMapSettingActivity$f offlineMapSettingActivity$f = (OfflineMapSettingActivity$f) getChild(i2, i3);
                    if (offlineMapSettingActivity$f.m11819a()) {
                        if (offlineMapSettingActivity$f.m11817a(i) != null) {
                            offlineMapSettingActivity$f.m11820b();
                            return;
                        }
                    } else if (offlineMapSettingActivity$f.f10947b.cityID == i) {
                        offlineMapSettingActivity$f.m11818a(OfflineMapSettingActivity.c(offlineMapSettingActivity).getUpdateInfo(i));
                        notifyDataSetChanged();
                        return;
                    }
                }
                i2++;
            } finally {
                notifyDataSetChanged();
            }
        }
        notifyDataSetChanged();
    }

    public int getGroupCount() {
        return this.f10945a.size();
    }

    public int getChildrenCount(int i) {
        return ((OfflineMapSettingActivity$e) this.f10945a.get(i)).f10944b.size();
    }

    public Object getGroup(int i) {
        return this.f10945a.get(i);
    }

    public Object getChild(int i, int i2) {
        return ((OfflineMapSettingActivity$e) this.f10945a.get(i)).f10944b.get(i2);
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        ViewHolder c1430b;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), C1373R.layout.offline_map_setting_activity_list_category_item, null);
            c1430b = new C1430b(view);
        } else {
            c1430b = ViewHolder.as(view);
        }
        c1430b.bind(this.f10945a.get(i));
        return view;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        ViewHolder c1429a;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), C1373R.layout.offline_map_setting_activity_list_item, null);
            c1429a = new C1429a(view);
        } else {
            c1429a = C1429a.as(view);
        }
        c1429a.bind(((OfflineMapSettingActivity$e) this.f10945a.get(i)).f10944b.get(i2));
        return view;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }
}
