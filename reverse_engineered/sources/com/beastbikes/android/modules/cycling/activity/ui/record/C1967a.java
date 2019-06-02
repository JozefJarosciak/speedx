package com.beastbikes.android.modules.cycling.activity.ui.record;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.C2024b;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.C2024b.C1956a;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.dto.C2411a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: AdapterStatistics */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.a */
public class C1967a extends PagerAdapter {
    /* renamed from: a */
    private ArrayList<C2024b> f8861a = null;
    /* renamed from: b */
    private C1979c f8862b;

    C1967a(Context context, C1956a c1956a) {
        this.f8862b = new C1979c(context);
        this.f8861a = this.f8862b.m10208b();
        Iterator it = this.f8861a.iterator();
        while (it.hasNext()) {
            ((C2024b) it.next()).setOnClickSelectMenuListener(c1956a);
        }
    }

    /* renamed from: a */
    public void m10148a(boolean z, ActivityDTO activityDTO, boolean z2) {
        if (z) {
            notifyDataSetChanged();
            return;
        }
        this.f8861a = this.f8862b.m10200a(activityDTO, z2);
        notifyDataSetChanged();
    }

    /* renamed from: a */
    void m10142a(double d, List<Double> list) {
        this.f8862b.m10202a(d, (List) list);
    }

    /* renamed from: a */
    void m10147a(List<Double> list, double d, double d2) {
        this.f8862b.m10207a((List) list, d, d2);
    }

    /* renamed from: a */
    void m10143a(ActivityDTO activityDTO, int i, List<Double> list) {
        this.f8862b.m10203a(activityDTO, i, (List) list);
    }

    /* renamed from: a */
    void m10146a(List<Double> list) {
        this.f8862b.m10206a((List) list);
    }

    /* renamed from: a */
    void m10144a(ActivityDTO activityDTO, ArrayList<Double> arrayList) {
        this.f8862b.m10204a(activityDTO, (ArrayList) arrayList);
    }

    /* renamed from: a */
    void m10145a(ActivityDTO activityDTO, List<C2411a> list, ArrayList<C2411a> arrayList) {
        this.f8862b.m10205a(activityDTO, (List) list, (ArrayList) arrayList);
    }

    /* renamed from: a */
    public void m10141a() {
        if (this.f8861a != null) {
            Iterator it = this.f8861a.iterator();
            while (it.hasNext()) {
                ((C2024b) it.next()).m10453h();
            }
        }
    }

    public int getCount() {
        return this.f8861a.size();
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) this.f8861a.get(i));
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = (View) this.f8861a.get(i);
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        viewGroup.addView(view);
        return this.f8861a.get(i);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    /* renamed from: b */
    public ArrayList<C2024b> m10149b() {
        return this.f8861a;
    }

    /* renamed from: c */
    void m10150c() {
        this.f8862b.m10201a();
        this.f8862b = null;
    }

    /* renamed from: a */
    int m10140a(int i) {
        if (this.f8861a != null) {
            int size = this.f8861a.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((C2024b) this.f8861a.get(i2)).getType() == i) {
                    return i2;
                }
            }
        }
        return 0;
    }
}
