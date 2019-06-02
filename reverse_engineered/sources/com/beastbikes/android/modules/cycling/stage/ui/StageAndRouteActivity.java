package com.beastbikes.android.modules.cycling.stage.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.sections.ui.RoutesSelfFrag;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903182)
public class StageAndRouteActivity extends SessionFragmentActivity {
    @C1458a(a = 2131755946)
    /* renamed from: a */
    private TabLayout f5784a;
    @C1458a(a = 2131755947)
    /* renamed from: b */
    private ViewPager f5785b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        m7020a();
    }

    /* renamed from: a */
    private void m7020a() {
        List arrayList = new ArrayList();
        StagesCollectionFrag stagesCollectionFrag = new StagesCollectionFrag();
        Bundle bundle = new Bundle();
        bundle.putInt("stage_type", 0);
        stagesCollectionFrag.setArguments(bundle);
        arrayList.add(new StagesCollectionFrag());
        arrayList.add(new RoutesSelfFrag());
        List arrayList2 = new ArrayList();
        arrayList2.add(getString(C1373R.string.str_segment_collection));
        arrayList2.add(getString(C1373R.string.profile_fragment_detailed_item_my_route));
        this.f5785b.setAdapter(new StageAndRouteActivity$a(this, getSupportFragmentManager(), arrayList, arrayList2));
        this.f5785b.setOffscreenPageLimit(arrayList.size());
        this.f5784a.setupWithViewPager(this.f5785b);
    }
}
