package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.biz.C1916c;
import com.beastbikes.android.modules.cycling.activity.dto.GoalConfigDTO;
import com.beastbikes.android.modules.cycling.activity.dto.MyGoalInfoDTO;
import com.beastbikes.android.modules.cycling.activity.ui.C1940b.C1939c;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.NumberTextView;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@C1459b(a = 2130903294)
public class CyclingTargetSettingActivity extends SessionFragmentActivity implements OnClickListener, C1939c, C2534b {
    @C1458a(a = 2131756462)
    /* renamed from: a */
    private ImageView f4657a;
    @C1458a(a = 2131756463)
    /* renamed from: b */
    private TextView f4658b;
    @C1458a(a = 2131756467)
    /* renamed from: c */
    private RecyclerView f4659c;
    @C1458a(a = 2131756464)
    /* renamed from: d */
    private NumberTextView f4660d;
    @C1458a(a = 2131756465)
    /* renamed from: e */
    private NumberTextView f4661e;
    @C1458a(a = 2131756466)
    /* renamed from: f */
    private ImageView f4662f;
    /* renamed from: g */
    private C1916c f4663g;
    /* renamed from: h */
    private CyclingTargetSettingActivity$b f4664h;
    /* renamed from: i */
    private List<GoalConfigDTO> f4665i;
    /* renamed from: j */
    private C1940b f4666j;
    /* renamed from: k */
    private SharedPreferences f4667k;
    /* renamed from: l */
    private double f4668l;
    /* renamed from: m */
    private String f4669m;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        C2580w.a(this, "目标设置", "save_ridding_goal");
        this.f4663g = new C1916c(this);
        this.f4662f.setOnClickListener(this);
        this.f4657a.setOnClickListener(this);
        this.f4658b.setOnClickListener(this);
        this.f4665i = new ArrayList();
        this.f4664h = new CyclingTargetSettingActivity$b(this, this, this.f4665i);
        this.f4659c.setHasFixedSize(true);
        this.f4659c.setAdapter(this.f4664h);
        this.f4659c.setLayoutManager(new LinearLayoutManager(this));
        this.f4668l = getIntent().getDoubleExtra("target_distance", 0.0d);
        if (C1849a.b(this)) {
            this.f4660d.setText(String.format("%.0f", new Object[]{Double.valueOf(this.f4668l)}));
            this.f4669m = "KM";
            this.f4661e.setText(this.f4669m);
        } else {
            this.f4660d.setText(String.format("%.0f", new Object[]{Double.valueOf(C1849a.a(this.f4668l))}));
            this.f4669m = "MI";
            this.f4661e.setText(this.f4669m);
        }
        this.f4667k = getSharedPreferences(m5331p(), 0);
        m6022a();
        m6025b();
        if (this.f4667k.contains("cycling_my_goal")) {
            Object string = this.f4667k.getString("cycling_my_goal", "");
            if (!TextUtils.isEmpty(string)) {
                try {
                    MyGoalInfoDTO myGoalInfoDTO = new MyGoalInfoDTO(new JSONObject(string));
                    if (C1849a.b(this)) {
                        this.f4660d.setText(String.format("%.0f", new Object[]{Double.valueOf(myGoalInfoDTO.getMyGoal() / 1000.0d)}));
                        return;
                    }
                    this.f4660d.setText(String.format("%.0f", new Object[]{Double.valueOf(C1849a.a(myGoalInfoDTO.getMyGoal() / 1000.0d))}));
                } catch (Exception e) {
                }
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.cycling_target_setting_close:
                finish();
                return;
            case C1373R.id.cycling_target_setting_save:
                C2580w.a(this, "setting_ridding_goal", "setting_ridding_goal");
                for (GoalConfigDTO goalConfigDTO : this.f4665i) {
                    if (goalConfigDTO.isChecked()) {
                        this.f4668l = goalConfigDTO.getDistance() / 1000.0d;
                    }
                }
                if (!C1849a.b(this)) {
                    this.f4668l = C1849a.b(this.f4668l);
                }
                m6023a(this.f4668l);
                return;
            case C1373R.id.cycling_target_setting_select:
                C2580w.a(this, "设定目标", "save_ridding_goal");
                if (this.f4666j == null) {
                    this.f4666j = new C1940b(this, this);
                }
                this.f4666j.showAtLocation(this.f4659c, 1, 0, this.f4658b.getHeight());
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m6030a(ViewHolder viewHolder, int i) {
        if (this.f4665i != null && this.f4665i.size() > 0) {
            switch (i) {
                case 0:
                    C2580w.a(this, "俱乐部月平均里程", "month_rank_avg_distance");
                    break;
                case 1:
                    C2580w.a(this, "月榜冠军里程", "setting_speedx_goal_distance");
                    break;
                case 2:
                    C2580w.a(this, "野兽月平均里程", "setting_speedx_avg_distance");
                    break;
            }
            double d = 0.0d;
            for (int i2 = 0; i2 < this.f4665i.size(); i2++) {
                GoalConfigDTO goalConfigDTO = (GoalConfigDTO) this.f4665i.get(i2);
                if (i2 == i) {
                    goalConfigDTO.setChecked(true);
                    d = goalConfigDTO.getDistance() / 1000.0d;
                } else {
                    goalConfigDTO.setChecked(false);
                }
            }
            this.f4664h.notifyDataSetChanged();
            if (C1849a.b(this)) {
                this.f4660d.setText(String.format("%.0f", new Object[]{Double.valueOf(d)}));
                return;
            }
            this.f4660d.setText(String.format("%.0f", new Object[]{Double.valueOf(C1849a.a(d))}));
        }
    }

    /* renamed from: b */
    public void m6031b(ViewHolder viewHolder, int i) {
    }

    /* renamed from: a */
    public void m6029a(int i) {
        this.f4660d.setText(String.valueOf(i));
        for (GoalConfigDTO checked : this.f4665i) {
            checked.setChecked(false);
        }
        this.f4664h.notifyDataSetChanged();
        this.f4668l = (double) i;
    }

    /* renamed from: a */
    private void m6022a() {
        if (!this.f4667k.contains("cycling_target_setting")) {
            this.f4667k.edit().putString("cycling_target_setting", "sa").commit();
        }
    }

    /* renamed from: b */
    private void m6025b() {
        getAsyncTaskQueue().a(new CyclingTargetSettingActivity$1(this), new Void[0]);
    }

    /* renamed from: a */
    private void m6023a(double d) {
        getAsyncTaskQueue().a(new CyclingTargetSettingActivity$2(this, d), new Double[0]);
    }
}
