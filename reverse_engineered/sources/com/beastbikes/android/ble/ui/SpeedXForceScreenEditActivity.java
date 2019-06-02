package com.beastbikes.android.ble.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.ui.p099b.C1747c;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.ui.widget.CyclingPreviewLayout;
import com.beastbikes.android.widget.p081b.C2612g;
import com.beastbikes.android.widget.p081b.C2614j;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.android.p088g.C2801d;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@C1459b(a = 2130903187)
@C1460c(a = 2131820568)
public class SpeedXForceScreenEditActivity extends SessionFragmentActivity implements OnTabSelectedListener, C1747c, C2612g {
    @C1458a(a = 2131755995)
    /* renamed from: a */
    private CyclingPreviewLayout f4276a;
    @C1458a(a = 2131755996)
    /* renamed from: b */
    private TabLayout f4277b;
    @C1458a(a = 2131755997)
    /* renamed from: c */
    private RecyclerView f4278c;
    /* renamed from: d */
    private SpeedXForceScreenEditActivity$a f4279d;
    /* renamed from: e */
    private List<String> f4280e;
    /* renamed from: f */
    private String[][] f4281f;
    /* renamed from: g */
    private ItemTouchHelper f4282g;
    /* renamed from: h */
    private String[] f4283h;
    /* renamed from: i */
    private String[] f4284i;
    /* renamed from: j */
    private ArrayList<Integer> f4285j;
    /* renamed from: k */
    private int f4286k;
    /* renamed from: l */
    private boolean f4287l;
    /* renamed from: m */
    private ArrayList<Integer> f4288m;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        this.f4283h = intent.getStringArrayExtra("extra_array_desc");
        this.f4286k = intent.getIntExtra("page_index", -1);
        this.f4287l = intent.getBooleanExtra("is_edit", false);
        this.f4284i = intent.getStringArrayExtra("extra_array_unit");
        setTitle(getString(C1373R.string.str_ble_screen_data_settings_edit_cycling_ui));
        if (this.f4287l) {
            this.f4288m = intent.getIntegerArrayListExtra("page_item_positions");
        }
        if (this.f4286k == -1) {
            finish();
            return;
        }
        this.f4285j = new ArrayList();
        m5625a();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onTabSelected(Tab tab) {
        if (this.f4279d == null) {
            return;
        }
        if (tab.getPosition() == 0) {
            this.f4279d.a(this.f4280e, tab.getPosition());
        } else if (this.f4281f != null) {
            this.f4279d.a(this.f4281f[tab.getPosition()], tab.getPosition());
        }
    }

    public void onTabUnselected(Tab tab) {
    }

    public void onTabReselected(Tab tab) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.edit_text_activity_action_button_save:
                m5630c();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* renamed from: a */
    public void m5635a(String str, int i) {
        if (this.f4280e.contains(str)) {
            this.f4280e.remove(str);
            m5627b();
            this.f4279d.notifyItemChanged(i);
        } else if (this.f4285j.size() >= 10) {
            Toasts.show(this, getString(C1373R.string.str_ble_at_mont_can_select_10_items));
        } else {
            this.f4280e.add(str);
            m5627b();
            this.f4279d.notifyItemChanged(i);
        }
    }

    /* renamed from: a */
    public void m5632a(int i) {
        this.f4280e.remove(i);
        m5627b();
    }

    /* renamed from: a */
    public void m5633a(int i, int i2) {
        Collections.swap(this.f4280e, i, i2);
        m5627b();
    }

    /* renamed from: a */
    public void m5634a(ViewHolder viewHolder) {
        this.f4282g.attachToRecyclerView(this.f4278c);
    }

    /* renamed from: a */
    private void m5625a() {
        int resourceId;
        int intExtra = getIntent().getIntExtra("screen_settings_type", 1);
        this.f4277b.setTabMode(0);
        this.f4277b.setTabTextColors(-3421237, -65494);
        Resources resources = getResources();
        for (CharSequence charSequence : resources.getStringArray(C1373R.array.cycling_data_settings_options)) {
            if (!charSequence.equals(getString(C1373R.string.str_general)) || intExtra != 1) {
                this.f4277b.addTab(this.f4277b.newTab().setText(charSequence));
            }
        }
        this.f4277b.addOnTabSelectedListener(this);
        if (C1849a.c()) {
            m5631d();
        }
        LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.f4278c.setHasFixedSize(true);
        this.f4278c.setLayoutManager(linearLayoutManager);
        TypedArray obtainTypedArray = resources.obtainTypedArray(C1373R.array.cycling_data_settings_options_child);
        if (intExtra == 1) {
            obtainTypedArray = resources.obtainTypedArray(C1373R.array.app_cycling_data_settings_options_child);
        }
        int length = obtainTypedArray.length();
        this.f4281f = new String[length][];
        for (intExtra = 0; intExtra < length; intExtra++) {
            resourceId = obtainTypedArray.getResourceId(intExtra, 0);
            if (resourceId > 0) {
                this.f4281f[intExtra] = resources.getStringArray(resourceId);
            }
        }
        obtainTypedArray.recycle();
        this.f4280e = new ArrayList();
        this.f4279d = new SpeedXForceScreenEditActivity$a(this, this, this.f4281f[0], 0);
        if (!this.f4287l || this.f4288m == null || this.f4288m.isEmpty()) {
            this.f4280e.addAll(Arrays.asList(this.f4281f[0]));
        } else {
            Iterator it = this.f4288m.iterator();
            while (it.hasNext()) {
                this.f4280e.add(this.f4283h[((Integer) it.next()).intValue()]);
            }
            this.f4279d.a(this.f4280e, 0);
            m5627b();
        }
        this.f4279d.a(this);
        this.f4278c.setAdapter(this.f4279d);
        this.f4282g = new ItemTouchHelper(new C2614j(this.f4279d));
        this.f4282g.attachToRecyclerView(this.f4278c);
    }

    /* renamed from: b */
    private void m5627b() {
        this.f4285j.clear();
        for (String a : this.f4280e) {
            int a2 = m5624a(a, this.f4283h);
            if (a2 >= 0) {
                this.f4285j.add(Integer.valueOf(a2));
            }
        }
        this.f4276a.a(this.f4285j);
    }

    /* renamed from: a */
    private int m5624a(String str, String[] strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (TextUtils.equals(str, strArr[i])) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: c */
    private void m5630c() {
        if (this.f4285j.size() < 3) {
            Toasts.show(this, getString(C1373R.string.str_ble_please_at_least_3_items));
            return;
        }
        Intent intent = new Intent();
        intent.putIntegerArrayListExtra("extra_selected_items_position", this.f4285j);
        intent.putExtra("page_index", this.f4286k);
        setResult(-1, intent);
        finish();
    }

    /* renamed from: d */
    private void m5631d() {
        Class cls = this.f4277b.getClass();
        int width = getWindowManager().getDefaultDisplay().getWidth();
        try {
            Field declaredField = cls.getDeclaredField("mTabStrip");
            declaredField.setAccessible(true);
            LinearLayout linearLayout = (LinearLayout) declaredField.get(this.f4277b);
            int a = C2801d.a(this, 14.0f);
            width /= 5;
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                View childAt = linearLayout.getChildAt(i);
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
                layoutParams.setMargins(a, 0, a, 0);
                layoutParams.width = width;
                childAt.setLayoutParams(layoutParams);
                childAt.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
