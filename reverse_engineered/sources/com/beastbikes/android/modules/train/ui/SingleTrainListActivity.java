package com.beastbikes.android.modules.train.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.train.dto.C2365a;
import com.beastbikes.android.modules.train.dto.CourseDTO;
import com.beastbikes.android.modules.train.dto.TrainResultDTO;
import com.beastbikes.android.modules.train.p147b.C2352a;
import com.beastbikes.android.modules.train.p148c.C2353a;
import com.beastbikes.android.modules.train.p149d.C2358a;
import com.beastbikes.android.modules.train.ui.p150a.C2372b;
import com.beastbikes.android.utils.C2574s;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C2801d;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import rx.subscriptions.CompositeSubscription;

@C1459b(a = 2130903186)
public class SingleTrainListActivity extends SessionFragmentActivity implements OnTabSelectedListener, OnClickListener, C2352a<CourseDTO>, C2353a {
    @C1458a(a = 2131755993)
    /* renamed from: a */
    private TabLayout f6240a;
    @C1458a(a = 2131755994)
    /* renamed from: b */
    private RecyclerView f6241b;
    /* renamed from: c */
    private C2372b f6242c;
    /* renamed from: d */
    private C2358a f6243d;
    /* renamed from: e */
    private C1802i f6244e;
    /* renamed from: f */
    private PopupWindow f6245f;
    /* renamed from: g */
    private CheckedTextView f6246g;
    /* renamed from: h */
    private CheckedTextView f6247h;
    /* renamed from: i */
    private CompositeSubscription f6248i;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        m7425e();
        this.f6240a.setTabMode(0);
        this.f6243d = new C2358a(this);
        this.f6243d.a();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem add = menu.add(0, C1373R.id.menu_single_train_list_order, 0, C1373R.string.empty);
        add.setShowAsAction(2);
        add.setIcon(C1373R.drawable.ic_order);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_single_train_list_order:
                showPopupWindow(menuItem.getActionView());
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* renamed from: a */
    public SingleTrainListActivity m7427a() {
        return this;
    }

    /* renamed from: a */
    public void m7430a(ArrayList<C2365a> arrayList) {
        if (arrayList != null) {
            m7424b(arrayList);
        }
    }

    /* renamed from: b */
    public void m7431b() {
    }

    /* renamed from: c */
    public void m7432c() {
        if (this.f6244e == null) {
            this.f6244e = new C1802i(this, getString(C1373R.string.str_loading), false);
        }
        if (!this.f6244e.isShowing()) {
            this.f6244e.show();
        }
    }

    /* renamed from: d */
    public void m7433d() {
        if (this.f6244e != null && this.f6244e.isShowing()) {
            this.f6244e.dismiss();
        }
    }

    public void onTabSelected(Tab tab) {
        this.f6242c.a(this.f6243d.a(tab.getPosition() - 1));
        if (this.f6246g != null) {
            this.f6246g.setChecked(true);
        }
        if (this.f6247h != null) {
            this.f6247h.setChecked(false);
        }
    }

    public void onTabUnselected(Tab tab) {
    }

    public void onTabReselected(Tab tab) {
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.view_temp:
                if (this.f6245f != null && this.f6245f.isShowing()) {
                    this.f6245f.dismiss();
                    return;
                }
                return;
            case C1373R.id.checkTV_single_train_popup_time:
                this.f6245f.dismiss();
                if (!this.f6246g.isChecked()) {
                    this.f6246g.toggle();
                    this.f6247h.toggle();
                    this.f6242c.a(this.f6243d.c());
                    return;
                }
                return;
            case C1373R.id.checkTV_single_train_popup_tss:
                this.f6245f.dismiss();
                if (!this.f6247h.isChecked()) {
                    this.f6247h.toggle();
                    this.f6246g.toggle();
                    this.f6242c.a(this.f6243d.d());
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m7428a(CourseDTO courseDTO, int i) {
        Intent intent = new Intent(this, TrainCourseInfoActivity.class);
        intent.putExtra("train_type", "train_type_single");
        intent.putExtra("train_course_id", courseDTO.getId());
        intent.putExtra("train_type_info", true);
        startActivity(intent);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f6248i.clear();
    }

    /* renamed from: e */
    private void m7425e() {
        this.f6242c = new C2372b(this);
        this.f6241b.setHasFixedSize(true);
        this.f6241b.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.f6241b.setAdapter(this.f6242c);
        this.f6242c.a(this);
        this.f6240a.addOnTabSelectedListener(this);
        this.f6248i = new CompositeSubscription();
        this.f6248i.add(C2574s.a().a(TrainResultDTO.class).a(new SingleTrainListActivity$1(this)));
    }

    /* renamed from: b */
    private void m7424b(ArrayList<C2365a> arrayList) {
        r0 = new int[2][];
        r0[0] = new int[]{16842913};
        r0[1] = new int[0];
        this.f6240a.setTabTextColors(new ColorStateList(r0, new int[]{-65494, -6710887}));
        boolean c = C1849a.c();
        this.f6240a.addTab(this.f6240a.newTab().setText((int) C1373R.string.str_train_all_courses));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C2365a c2365a = (C2365a) it.next();
            this.f6240a.addTab(this.f6240a.newTab().setText(c ? c2365a.a() : c2365a.b()));
        }
        m7426f();
    }

    /* renamed from: f */
    private void m7426f() {
        Class cls = this.f6240a.getClass();
        int width = getWindowManager().getDefaultDisplay().getWidth();
        try {
            Field declaredField = cls.getDeclaredField("mTabStrip");
            declaredField.setAccessible(true);
            LinearLayout linearLayout = (LinearLayout) declaredField.get(this.f6240a);
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

    public void showPopupWindow(View view) {
        if (this.f6245f == null) {
            View inflate = LayoutInflater.from(this).inflate(C1373R.layout.layout_single_train_list_popup, null);
            this.f6246g = (CheckedTextView) inflate.findViewById(C1373R.id.checkTV_single_train_popup_time);
            this.f6247h = (CheckedTextView) inflate.findViewById(C1373R.id.checkTV_single_train_popup_tss);
            View findViewById = inflate.findViewById(C1373R.id.view_temp);
            this.f6246g.setCheckMarkDrawable(C1373R.drawable.selector_single_train_popup);
            this.f6247h.setCheckMarkDrawable(C1373R.drawable.selector_single_train_popup);
            this.f6246g.setChecked(true);
            this.f6246g.setOnClickListener(this);
            this.f6247h.setOnClickListener(this);
            findViewById.setOnClickListener(this);
            this.f6245f = new PopupWindow(inflate, -1, -1);
            this.f6245f.setOutsideTouchable(true);
            this.f6245f.setFocusable(true);
            this.f6245f.setTouchable(true);
            this.f6245f.setBackgroundDrawable(new ColorDrawable(2130706432));
        }
        if (!this.f6245f.isShowing()) {
            this.f6245f.showAtLocation(this.f6240a, 48, 0, 0);
        }
    }
}
