package com.beastbikes.android.ble.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.dto.CadenceDTO;
import com.beastbikes.android.ble.ui.p098a.C1713c;
import com.beastbikes.android.ble.ui.widget.ShapeIndicatorView;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.android.p088g.C2801d;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903189)
@C1460c(a = 2131820550)
public class SpeedXCadenceSettingActivity extends SessionFragmentActivity implements OnPageChangeListener, OnClickListener {
    @C1458a(a = 2131756006)
    /* renamed from: a */
    private TabLayout f4261a;
    @C1458a(a = 2131756007)
    /* renamed from: b */
    private ViewPager f4262b;
    @C1458a(a = 2131756008)
    /* renamed from: c */
    private Button f4263c;
    @C1458a(a = 2131756005)
    /* renamed from: d */
    private ShapeIndicatorView f4264d;
    /* renamed from: e */
    private List<CadenceDTO> f4265e;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        int intExtra = getIntent().getIntExtra("index", 0);
        this.f4263c.setOnClickListener(this);
        this.f4265e = new ArrayList();
        String[] stringArray = getResources().getStringArray(C1373R.array.select_cadence_array);
        String[] stringArray2 = getResources().getStringArray(C1373R.array.select_cadence_title);
        String[] stringArray3 = getResources().getStringArray(C1373R.array.select_cadence_desc);
        for (int i = 0; i < stringArray.length; i++) {
            CadenceDTO cadenceDTO = new CadenceDTO();
            cadenceDTO.setTitle(stringArray2[i]);
            cadenceDTO.setDesc(stringArray3[i]);
            cadenceDTO.setData(stringArray[i]);
            if (intExtra == i) {
                cadenceDTO.setSelected(true);
            } else {
                cadenceDTO.setSelected(false);
            }
            this.f4265e.add(cadenceDTO);
        }
        PagerAdapter speedXCadenceSettingActivity$a = new SpeedXCadenceSettingActivity$a(this, this, this.f4265e);
        this.f4262b.addOnPageChangeListener(this);
        this.f4262b.setAdapter(speedXCadenceSettingActivity$a);
        this.f4262b.setOffscreenPageLimit(this.f4265e.size());
        this.f4261a.setTabsFromPagerAdapter(speedXCadenceSettingActivity$a);
        m5608a();
        this.f4264d.setupWithViewPager(this.f4262b);
        this.f4264d.a(this.f4261a, intExtra);
        this.f4262b.setCurrentItem(intExtra);
        onPageSelected(intExtra);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        SharedPreferences sharedPreferences = getSharedPreferences(m5331p(), 0);
        if (!sharedPreferences.contains("beast.ble.cadence.guide.dialog")) {
            new C1713c(this).show();
            sharedPreferences.edit().putBoolean("beast.ble.cadence.guide.dialog", true).apply();
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_item_cadence_help:
                Intent intent = new Intent(this, BrowserActivity.class);
                intent.setData(Uri.parse("https://hybrid.speedx.com/cadence-notice"));
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.activity_speedx_cadence_setting_btn:
                if (!this.f4263c.isSelected()) {
                    if (C1661h.a().b() == null) {
                        Toasts.show(this, getString(C1373R.string.str_ble_retry_after_connect_to_device));
                        return;
                    }
                    Intent intent = getIntent();
                    intent.putExtra("index", this.f4262b.getCurrentItem());
                    setResult(-1, intent);
                    finish();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (this.f4265e != null && this.f4265e.size() > i) {
            CadenceDTO cadenceDTO = (CadenceDTO) this.f4265e.get(i);
            if (cadenceDTO != null) {
                this.f4263c.setSelected(cadenceDTO.isSelected());
            }
        }
    }

    public void onPageScrollStateChanged(int i) {
    }

    /* renamed from: a */
    private void m5608a() {
        Class cls = this.f4261a.getClass();
        int width = getWindowManager().getDefaultDisplay().getWidth();
        try {
            Field declaredField = cls.getDeclaredField("mTabStrip");
            declaredField.setAccessible(true);
            LinearLayout linearLayout = (LinearLayout) declaredField.get(this.f4261a);
            int a = C2801d.a(this, 14.0f);
            width = (width - (a * 9)) / 5;
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                View childAt = linearLayout.getChildAt(i);
                LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
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
