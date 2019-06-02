package com.beastbikes.android.ble.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.C1603b;
import com.beastbikes.android.ble.CentralService;
import com.beastbikes.android.ble.CentralService.C1596c;
import com.beastbikes.android.ble.biz.p097b.C1619c;
import com.beastbikes.android.ble.dto.SpeedXForceScreenPageDTO;
import com.beastbikes.android.ble.ui.p098a.C1716f;
import com.beastbikes.android.ble.ui.p099b.C1745a;
import com.beastbikes.android.ble.ui.p099b.C1746b;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.ui.widget.CyclingPreviewLayout;
import com.beastbikes.android.utils.C2557f;
import com.beastbikes.android.widget.p081b.C2614j;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903188)
@C1460c(a = 2131820577)
public class SpeedXForceScreenSettingsActivity extends SessionFragmentActivity implements ServiceConnection, OnClickListener, C1619c, C1745a, C1746b {
    /* renamed from: a */
    private static final Logger f4289a = LoggerFactory.getLogger(SpeedXForceScreenSettingsActivity.class);
    @C1458a(a = 2131755998)
    /* renamed from: b */
    private RecyclerView f4290b;
    @C1458a(a = 2131755999)
    /* renamed from: c */
    private CyclingPreviewLayout f4291c;
    @C1458a(a = 2131756000)
    /* renamed from: d */
    private LinearLayout f4292d;
    @C1458a(a = 2131756001)
    /* renamed from: e */
    private ImageView f4293e;
    @C1458a(a = 2131756002)
    /* renamed from: f */
    private LinearLayout f4294f;
    @C1458a(a = 2131756003)
    /* renamed from: g */
    private TextView f4295g;
    @C1458a(a = 2131756004)
    /* renamed from: h */
    private TextView f4296h;
    /* renamed from: i */
    private CentralService f4297i;
    /* renamed from: j */
    private SpeedXForceScreenSettingsActivity$b f4298j;
    /* renamed from: k */
    private ArrayList<SpeedXForceScreenPageDTO> f4299k;
    /* renamed from: l */
    private ArrayList<ArrayList<Integer>> f4300l;
    /* renamed from: m */
    private int f4301m = -1;
    /* renamed from: n */
    private int[] f4302n;
    /* renamed from: o */
    private String[] f4303o;
    /* renamed from: p */
    private String[] f4304p;
    /* renamed from: q */
    private String[] f4305q;
    /* renamed from: r */
    private C1802i f4306r;
    /* renamed from: s */
    private Handler f4307s;
    /* renamed from: t */
    private C1716f f4308t;
    /* renamed from: u */
    private SharedPreferences f4309u;
    /* renamed from: v */
    private int f4310v;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        Resources resources = getResources();
        this.f4303o = resources.getStringArray(C1373R.array.cycling_data_settings_desc);
        if (C1849a.b(this)) {
            this.f4305q = resources.getStringArray(C1373R.array.cycling_data_settings_desc_unit_km);
        } else {
            this.f4305q = resources.getStringArray(C1373R.array.cycling_data_settings_desc_unit_mi);
        }
        this.f4304p = resources.getStringArray(C1373R.array.cycling_data_settings_format);
        this.f4302n = resources.getIntArray(C1373R.array.cycling_data_settings_value);
        this.f4299k = new ArrayList();
        this.f4300l = new ArrayList();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f4307s = new SpeedXForceScreenSettingsActivity$a(this, this);
        this.f4310v = intent.getIntExtra("screen_settings_type", 1);
        if (this.f4310v == 1) {
            m5651i();
        } else {
            m5652j();
        }
        m5643b();
        m5644c();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        boolean onCreateOptionsMenu = super.onCreateOptionsMenu(menu);
        MenuItem findItem = menu.findItem(C1373R.id.menu_speedx_force_screen_settings_synchronized);
        if (findItem != null) {
            findItem.setTitle(this.f4310v == 1 ? C1373R.string.str_save : C1373R.string.str_synchronization);
        }
        return onCreateOptionsMenu;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_speedx_force_screen_settings_synchronized:
                m5650h();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.linear_speedx_force_screen_setting_up:
                m5646d();
                return;
            case C1373R.id.tv_speedx_force_screen_setting_edit:
                this.f4294f.setVisibility(8);
                this.f4293e.setImageResource(C1373R.drawable.ic_speedx_force_screen_up);
                m5638a(this.f4301m, true);
                return;
            case C1373R.id.tv_speedx_force_screen_setting_delete:
                m5647e();
                this.f4294f.setVisibility(8);
                this.f4293e.setImageResource(C1373R.drawable.ic_speedx_force_screen_up);
                return;
            default:
                return;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f4297i != null) {
            unbindService(this);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            ArrayList integerArrayListExtra = intent.getIntegerArrayListExtra("extra_selected_items_position");
            if (i == 16) {
                int intExtra = intent.getIntExtra("page_index", -1);
                if (intExtra != -1 && integerArrayListExtra != null && !integerArrayListExtra.isEmpty()) {
                    SpeedXForceScreenPageDTO speedXForceScreenPageDTO = new SpeedXForceScreenPageDTO(true, integerArrayListExtra);
                    if (this.f4299k.size() >= 3) {
                        this.f4299k.remove(intExtra);
                        this.f4299k.add(intExtra, speedXForceScreenPageDTO);
                        this.f4298j.notifyItemChanged(intExtra);
                    } else {
                        this.f4299k.add(intExtra, speedXForceScreenPageDTO);
                        this.f4298j.notifyItemChanged(intExtra);
                        this.f4298j.notifyItemChanged(intExtra + 1);
                    }
                    if (this.f4301m >= 0 && this.f4301m < this.f4299k.size()) {
                        ((SpeedXForceScreenPageDTO) this.f4299k.get(this.f4301m)).setChecked(false);
                        this.f4298j.notifyItemChanged(this.f4301m);
                    }
                    this.f4301m = intExtra;
                    this.f4291c.a(integerArrayListExtra);
                }
            } else if (i == 17) {
                ((SpeedXForceScreenPageDTO) this.f4299k.get(this.f4301m)).setPagePositions(integerArrayListExtra);
                this.f4298j.notifyItemChanged(this.f4301m);
                this.f4291c.a(integerArrayListExtra);
            }
        }
    }

    /* renamed from: a */
    public void m5657a(int i) {
        m5638a(i, false);
    }

    /* renamed from: b */
    public void m5659b(int i) {
        if (this.f4301m != i) {
            ((SpeedXForceScreenPageDTO) this.f4299k.get(this.f4301m)).setChecked(false);
            ((SpeedXForceScreenPageDTO) this.f4299k.get(i)).setChecked(true);
            this.f4298j.notifyItemChanged(this.f4301m);
            this.f4298j.notifyItemChanged(i);
            this.f4301m = i;
            this.f4291c.a(((SpeedXForceScreenPageDTO) this.f4299k.get(i)).getPagePositions());
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f4297i = ((C1596c) iBinder).a();
        C1603b c = this.f4297i.c();
        if (c == null) {
            f4289a.error("onServiceConnected(), S605Invocation is null");
            return;
        }
        c.a(this);
        c.g();
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    /* renamed from: a */
    public void m5658a(ArrayList<ArrayList<Integer>> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            ArrayList arrayList2 = (ArrayList) new Gson().fromJson(C2557f.a(this, "default_screen_settings_for_central"), new SpeedXForceScreenSettingsActivity$1(this).getType());
            if (arrayList2 != null) {
                this.f4300l.clear();
                this.f4300l.addAll(arrayList2);
            }
        } else {
            this.f4300l.clear();
            this.f4300l.addAll(arrayList);
        }
        m5649g();
    }

    /* renamed from: a */
    public void m5656a() {
        m5654l();
        m5641a(getString(C1373R.string.str_synchronization_success));
    }

    public void onButtonClick(View view) {
        m5655m();
    }

    /* renamed from: b */
    private void m5643b() {
        LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        this.f4290b.setLayoutManager(linearLayoutManager);
        this.f4290b.setHasFixedSize(true);
        this.f4298j = new SpeedXForceScreenSettingsActivity$b(this, this.f4299k);
        this.f4290b.setAdapter(this.f4298j);
        this.f4298j.a(this);
        new ItemTouchHelper(new C2614j(this.f4298j)).attachToRecyclerView(this.f4290b);
        m5649g();
    }

    /* renamed from: c */
    private void m5644c() {
        this.f4292d.setOnClickListener(this);
        this.f4295g.setOnClickListener(this);
        this.f4296h.setOnClickListener(this);
    }

    /* renamed from: d */
    private void m5646d() {
        if (this.f4294f.getVisibility() == 0) {
            this.f4294f.setVisibility(8);
            this.f4293e.setImageResource(C1373R.drawable.ic_speedx_force_screen_up);
            return;
        }
        this.f4294f.setVisibility(0);
        this.f4293e.setImageResource(C1373R.drawable.ic_speedx_force_screen_down);
    }

    /* renamed from: e */
    private void m5647e() {
        new Builder(this).setMessage(getString(C1373R.string.dialog_sure_or_delete)).setPositiveButton(getString(C1373R.string.str_ok), new SpeedXForceScreenSettingsActivity$2(this)).setNegativeButton(getString(C1373R.string.str_cancel), null).create().show();
    }

    /* renamed from: f */
    private void m5648f() {
        f4289a.info("Current page: " + this.f4301m);
        if (this.f4301m != -1 && this.f4299k.size() > 1) {
            this.f4299k.remove(this.f4301m);
            this.f4298j.notifyItemRemoved(this.f4301m);
            int size = this.f4299k.size();
            this.f4296h.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.f4296h.setEnabled(true);
            this.f4296h.setOnClickListener(this);
            if (size <= 1) {
                this.f4301m = -1;
                this.f4291c.a(null);
                return;
            }
            ((SpeedXForceScreenPageDTO) this.f4299k.get(0)).setChecked(true);
            this.f4298j.notifyItemChanged(0);
            this.f4291c.a(((SpeedXForceScreenPageDTO) this.f4299k.get(0)).getPagePositions());
            if (size == 2) {
                if (((SpeedXForceScreenPageDTO) this.f4299k.get(1)).getPagePositions() == null) {
                    this.f4296h.setTextColor(-10066330);
                    this.f4296h.setEnabled(false);
                    this.f4296h.setOnClickListener(null);
                    return;
                }
                this.f4299k.add(new SpeedXForceScreenPageDTO(false, null));
                this.f4298j.notifyItemChanged(2);
            }
            f4289a.info("size: " + size);
        }
    }

    /* renamed from: g */
    private void m5649g() {
        this.f4299k.clear();
        Iterator it = this.f4300l.iterator();
        while (it.hasNext()) {
            ArrayList arrayList = (ArrayList) it.next();
            ArrayList arrayList2 = new ArrayList();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                int intValue = ((Integer) it2.next()).intValue();
                if (this.f4310v != 1 || intValue != 64) {
                    arrayList2.add(Integer.valueOf(m5636a(intValue, this.f4302n)));
                }
            }
            this.f4299k.add(new SpeedXForceScreenPageDTO(false, arrayList2));
        }
        if (!this.f4299k.isEmpty()) {
            ((SpeedXForceScreenPageDTO) this.f4299k.get(0)).setChecked(true);
            this.f4291c.a(((SpeedXForceScreenPageDTO) this.f4299k.get(0)).getPagePositions());
            this.f4301m = 0;
        }
        if (this.f4299k.size() < 3) {
            this.f4299k.add(new SpeedXForceScreenPageDTO(false, null));
        }
        this.f4298j.notifyDataSetChanged();
    }

    /* renamed from: h */
    private void m5650h() {
        this.f4300l.clear();
        Iterator it = this.f4299k.iterator();
        while (it.hasNext()) {
            ArrayList pagePositions = ((SpeedXForceScreenPageDTO) it.next()).getPagePositions();
            if (pagePositions != null) {
                ArrayList arrayList = new ArrayList();
                Iterator it2 = pagePositions.iterator();
                while (it2.hasNext()) {
                    int intValue = ((Integer) it2.next()).intValue();
                    arrayList.add(Integer.valueOf(this.f4302n[intValue]));
                    f4289a.info("value: " + this.f4302n[intValue]);
                }
                this.f4300l.add(arrayList);
            }
        }
        if (this.f4310v == 1) {
            Intent intent = new Intent();
            String toJson = new Gson().toJson(this.f4300l);
            intent.putExtra("screen_settings_values", toJson);
            this.f4309u.edit().putString("key_cycling_data_settings", toJson).apply();
            m5329c(16, intent);
            finish();
        } else if (this.f4297i == null) {
            f4289a.error("syncToCentral(), CentralService is null");
        } else {
            C1603b c = this.f4297i.c();
            if (c == null) {
                f4289a.error("syncToCentral(), S605Invocation is null");
                return;
            }
            m5653k();
            this.f4307s.sendMessageDelayed(Message.obtain(), 3000);
            c.a(this.f4300l);
        }
    }

    /* renamed from: a */
    private int m5636a(int i, int[] iArr) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i == iArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    /* renamed from: a */
    private void m5638a(int i, boolean z) {
        if (this.f4301m != -1 || !z) {
            Intent intent = new Intent(this, SpeedXForceScreenEditActivity.class);
            intent.putExtra("extra_array_desc", this.f4303o);
            intent.putExtra("extra_array_unit", this.f4305q);
            intent.putExtra("extra_array_format", this.f4304p);
            intent.putExtra("screen_settings_type", this.f4310v);
            intent.putExtra("page_index", i);
            intent.putExtra("is_edit", z);
            if (z) {
                intent.putIntegerArrayListExtra("page_item_positions", ((SpeedXForceScreenPageDTO) this.f4299k.get(i)).getPagePositions());
            }
            startActivityForResult(intent, z ? 17 : 16);
        }
    }

    /* renamed from: i */
    private void m5651i() {
        this.f4309u = getSharedPreferences(m5331p(), 0);
        String string = this.f4309u.getString("key_cycling_data_settings", "");
        if (TextUtils.isEmpty(string)) {
            string = C2557f.a(this, "default_screen_settings_for_app");
            this.f4309u.edit().putString("key_cycling_data_settings", string).apply();
        }
        ArrayList arrayList = (ArrayList) new Gson().fromJson(string, new SpeedXForceScreenSettingsActivity$3(this).getType());
        if (arrayList != null) {
            this.f4300l.clear();
            this.f4300l.addAll(arrayList);
        }
    }

    /* renamed from: j */
    private void m5652j() {
        Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
        intent.setPackage(getPackageName());
        bindService(intent, this, 1);
    }

    /* renamed from: k */
    private void m5653k() {
        if (this.f4306r == null) {
            this.f4306r = new C1802i(this, C1373R.string.loading_msg, true);
        }
        if (!this.f4306r.isShowing()) {
            this.f4306r.show();
        }
    }

    /* renamed from: l */
    private void m5654l() {
        if (this.f4306r != null && this.f4306r.isShowing()) {
            this.f4306r.dismiss();
        }
    }

    /* renamed from: a */
    private void m5641a(String str) {
        m5655m();
        this.f4308t = new C1716f(this, str, getString(C1373R.string.str_ok), this);
        if (!this.f4308t.isShowing()) {
            this.f4308t.show();
        }
    }

    /* renamed from: m */
    private void m5655m() {
        if (this.f4308t != null && this.f4308t.isShowing()) {
            this.f4308t.dismiss();
        }
    }
}
