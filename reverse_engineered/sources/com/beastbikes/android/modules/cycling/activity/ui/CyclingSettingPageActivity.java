package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.alipay.sdk.cons.C0844a;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.dto.PreviewDto;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.convenientbanner.p167c.C2627b;
import com.beastbikes.android.widget.p081b.C2612g;
import com.beastbikes.android.widget.p081b.C2614j;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903117)
@C1460c(a = 2131820575)
public class CyclingSettingPageActivity extends SessionFragmentActivity implements OnClickListener, C1371a, C2612g, C2627b {
    /* renamed from: a */
    public static List<Integer> f4639a = new ArrayList();
    /* renamed from: b */
    private static final Logger f4640b = LoggerFactory.getLogger(CyclingSettingPageActivity.class);
    @C1458a(a = 2131755666)
    /* renamed from: c */
    private LinearLayout f4641c;
    @C1458a(a = 2131755667)
    /* renamed from: d */
    private RecyclerView f4642d;
    /* renamed from: e */
    private CyclingSettingPageActivity$b f4643e;
    /* renamed from: f */
    private ListView f4644f;
    /* renamed from: g */
    private CyclingSettingPageActivity$d f4645g;
    /* renamed from: h */
    private C2621c f4646h;
    /* renamed from: i */
    private C2621c f4647i;
    /* renamed from: j */
    private ItemTouchHelper f4648j;
    /* renamed from: k */
    private List<PreviewDto> f4649k = new ArrayList();
    /* renamed from: l */
    private SharedPreferences f4650l;
    /* renamed from: m */
    private JSONArray f4651m;
    /* renamed from: n */
    private boolean f4652n = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f4650l = getSharedPreferences(m5331p(), 0);
        this.f4643e = new CyclingSettingPageActivity$b(this, this, this.f4649k);
        this.f4642d.setHasFixedSize(true);
        this.f4642d.setAdapter(this.f4643e);
        this.f4642d.setLayoutManager(new LinearLayoutManager(this));
        this.f4648j = new ItemTouchHelper(new C2614j(this.f4643e));
        this.f4648j.attachToRecyclerView(this.f4642d);
        this.f4641c.setOnClickListener(this);
        m6011b();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_cycling_setting_edit:
                if (this.f4652n) {
                    menuItem.setTitle(C1373R.string.str_edit);
                } else {
                    menuItem.setTitle(C1373R.string.club_release_activites_dialog_ok);
                }
                this.f4652n = !this.f4652n;
                for (PreviewDto edit : this.f4649k) {
                    edit.setEdit(this.f4652n);
                }
                this.f4643e.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.activity_cycling_setting_add_data:
                if (!this.f4652n) {
                    if (this.f4649k == null || this.f4649k.size() < 6) {
                        if (this.f4644f == null) {
                            this.f4644f = new ListView(this);
                            if (this.f4645g == null) {
                                this.f4645g = new CyclingSettingPageActivity$d(this);
                            }
                            this.f4644f.setLayoutParams(new LayoutParams(-1, -1));
                            this.f4644f.setDividerHeight(0);
                            this.f4644f.setAdapter(this.f4645g);
                        }
                        if (this.f4646h == null) {
                            this.f4646h = new C2621c(this);
                            this.f4646h.a(C1373R.string.cycling_target_add_data);
                            this.f4646h.a(this.f4644f);
                            this.f4646h.a(C1373R.string.activity_alert_dialog_text_ok, new CyclingSettingPageActivity$2(this));
                            this.f4646h.b(C1373R.string.activity_alert_dialog_text_cancel, new CyclingSettingPageActivity$3(this));
                        }
                        this.f4645g = new CyclingSettingPageActivity$d(this);
                        this.f4644f.setAdapter(this.f4645g);
                        this.f4646h.a();
                        f4639a.clear();
                        return;
                    }
                    if (this.f4647i == null) {
                        this.f4647i = new C2621c(this);
                        this.f4647i.b(C1373R.string.cycling_setting_add_data_error);
                        this.f4647i.a(C1373R.string.activity_alert_dialog_text_ok, new CyclingSettingPageActivity$1(this));
                    }
                    this.f4647i.a();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m6014a(int i) {
    }

    public void finish() {
        if (!(this.f4649k == null || this.f4650l == null)) {
            this.f4650l.edit().remove("cycling_data_setting").commit();
            this.f4651m = new JSONArray();
            for (int i = 0; i < this.f4649k.size(); i++) {
                try {
                    this.f4651m.put(i, ((PreviewDto) this.f4649k.get(i)).getJson());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            this.f4650l.edit().putString("cycling_data_setting", this.f4651m.toString()).commit();
        }
        super.finish();
    }

    /* renamed from: b */
    private void m6011b() {
        this.f4651m = new JSONArray();
        if (this.f4650l.contains("cycling_data_setting")) {
            try {
                this.f4651m = new JSONArray(this.f4650l.getString("cycling_data_setting", ""));
            } catch (Exception e) {
                f4640b.error("get cycling data setting error," + e);
            }
        } else {
            this.f4651m = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("0", 4);
            } catch (Exception e2) {
                f4640b.error("Cycling data put time error, " + e2);
            }
            try {
                this.f4651m.put(0, jSONObject);
            } catch (Exception e22) {
                f4640b.error("Cycling data set time error, " + e22);
            }
            jSONObject = new JSONObject();
            try {
                jSONObject.put("0", 2);
                jSONObject.put(C0844a.f2048d, 0);
            } catch (Exception e222) {
                f4640b.error("Cycling data put altitude and svg speed error, " + e222);
            }
            try {
                this.f4651m.put(1, jSONObject);
            } catch (Exception e2222) {
                f4640b.error("Cycling data set altitude and svg error, " + e2222);
            }
            jSONObject = new JSONObject();
            try {
                jSONObject.put("0", 3);
            } catch (Exception e22222) {
                f4640b.error("Cycling data put altitude and svg error, " + e22222);
            }
            try {
                this.f4651m.put(2, jSONObject);
            } catch (Exception e222222) {
                f4640b.error("Cycling data set uphill distance error, " + e222222);
            }
            this.f4650l.edit().putString("cycling_data_setting", this.f4651m.toString()).commit();
        }
        if (this.f4651m != null && this.f4651m.length() > 0) {
            this.f4649k.clear();
            for (int i = 0; i < this.f4651m.length(); i++) {
                this.f4649k.add(new PreviewDto(this, this.f4651m.optJSONObject(i)));
            }
            this.f4643e.notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void m6015a(ViewHolder viewHolder) {
        this.f4648j.startDrag(viewHolder);
    }
}
