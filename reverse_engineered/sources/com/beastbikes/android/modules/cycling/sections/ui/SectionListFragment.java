package com.beastbikes.android.modules.cycling.sections.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.sections.dto.C2222c;
import com.beastbikes.android.widget.C2638d;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903358)
public class SectionListFragment extends SectionBaseFragment implements C2534b {
    @C1458a(a = 2131755444)
    /* renamed from: a */
    private LinearLayout f5772a;
    @C1458a(a = 2131755915)
    /* renamed from: b */
    private LinearLayout f5773b;
    @C1458a(a = 2131755916)
    /* renamed from: c */
    private TextView f5774c;
    /* renamed from: d */
    private List<C2222c> f5775d = new ArrayList();
    /* renamed from: e */
    private C2638d f5776e;
    /* renamed from: f */
    private C2235d f5777f;
    /* renamed from: g */
    private boolean f5778g;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        new LinearLayoutManager(getActivity()).setOrientation(1);
        this.f5777f = new C2235d(getContext(), this);
        this.f5776e = new C2638d(getActivity(), this.f5772a, this.f5775d, 2);
        this.f5776e.setAdapter(this.f5777f);
        this.f5776e.setHasFooter(false);
        this.f5776e.setRefreshEnable(false);
        if (this.f5778g) {
            this.f5774c.setText(getResources().getString(C1373R.string.str_locating_failed));
            this.f5773b.setVisibility(0);
        }
    }

    /* renamed from: a */
    public void m7005a(ViewHolder viewHolder, int i) {
        Intent intent = new Intent(getActivity(), SectionDetailActivity.class);
        intent.putExtra("speedx_section_id", ((C2222c) this.f5775d.get(i)).d());
        startActivity(intent);
    }

    /* renamed from: b */
    public void m7008b(ViewHolder viewHolder, int i) {
    }

    /* renamed from: a */
    public void m7007a(List<C2222c> list) {
        if (list != null && list.size() != 0) {
            if (this.f5774c != null) {
                this.f5773b.setVisibility(8);
            }
            this.f5775d.clear();
            this.f5775d.addAll(list);
            if (this.f5776e != null) {
                this.f5776e.b();
            }
        }
    }

    /* renamed from: a */
    public void m7004a() {
        this.f5775d.clear();
        if (this.f5776e != null) {
            this.f5776e.b();
        }
        if (this.f5774c != null) {
            this.f5774c.setText(getResources().getString(C1373R.string.section_filter_failed));
            this.f5773b.setVisibility(0);
        }
    }

    /* renamed from: a */
    public void m7006a(String str) {
        this.f5775d.clear();
        if (this.f5776e != null) {
            this.f5776e.b();
        }
        this.f5778g = true;
        if (this.f5774c != null) {
            this.f5774c.setText(str);
            this.f5773b.setVisibility(0);
        }
    }

    /* renamed from: b */
    public void m7009b(String str) {
        this.f5775d.clear();
        if (this.f5776e != null) {
            this.f5776e.b();
        }
        if (this.f5774c != null) {
            this.f5774c.setText(str);
            this.f5773b.setVisibility(0);
        }
    }
}
