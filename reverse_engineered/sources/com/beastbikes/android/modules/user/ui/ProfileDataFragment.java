package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1805k;
import com.beastbikes.android.home.HomeActivity;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.activity.dto.MyGoalInfoDTO;
import com.beastbikes.android.modules.preferences.ui.CyclingSettingActivity;
import com.beastbikes.android.modules.train.dto.CalendarDto;
import com.beastbikes.android.modules.user.dto.UserDetailDTO;
import com.beastbikes.android.modules.user.p152b.C2398b;
import com.beastbikes.android.modules.user.view.C2527b;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.CyclingTargetProgressBar;
import com.beastbikes.android.widget.RippleView;
import com.beastbikes.android.widget.calendar.C2381a;
import com.beastbikes.android.widget.calendar.CalendarView;
import com.beastbikes.android.widget.calendar.CalendarView.C2380a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@C1459b(a = 2130903372)
public class ProfileDataFragment extends SessionFragment implements OnSharedPreferenceChangeListener, OnClickListener, C2527b, C2380a, C2381a {
    @C1458a(a = 2131756662)
    /* renamed from: a */
    private ViewStub f6498a;
    @C1458a(a = 2131756663)
    /* renamed from: b */
    private ViewStub f6499b;
    /* renamed from: c */
    private RelativeLayout f6500c;
    /* renamed from: d */
    private ScrollView f6501d;
    /* renamed from: e */
    private TextView f6502e;
    /* renamed from: f */
    private TextView f6503f;
    /* renamed from: g */
    private TextView f6504g;
    /* renamed from: h */
    private TextView f6505h;
    /* renamed from: i */
    private TextView f6506i;
    /* renamed from: j */
    private TextView f6507j;
    /* renamed from: k */
    private CyclingTargetProgressBar f6508k;
    /* renamed from: l */
    private TextView f6509l;
    /* renamed from: m */
    private TextView f6510m;
    /* renamed from: n */
    private String f6511n;
    /* renamed from: o */
    private C2398b f6512o;
    /* renamed from: p */
    private CalendarView f6513p;
    /* renamed from: q */
    private UserDetailDTO f6514q;
    /* renamed from: r */
    private SharedPreferences f6515r;
    /* renamed from: s */
    private int f6516s;
    /* renamed from: t */
    private int f6517t;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        AVUser currentUser = AVUser.getCurrentUser();
        this.f6512o = new C2398b(this);
        if (currentUser != null) {
            if (currentUser.getTotalMileage() <= 0.0d) {
                m7719a(onCreateView);
            } else {
                m7720b(onCreateView);
            }
        }
        return onCreateView;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onResume() {
        super.onResume();
        this.f6512o.a();
        if (this.f6501d != null) {
            if (C1849a.b(getContext())) {
                this.f6503f.setText(getString(C1373R.string.str_total_mileage) + "(" + getString(C1373R.string.str_mileage_unit_km) + ")");
                this.f6506i.setText(getString(C1373R.string.str_average_speed) + "(" + getString(C1373R.string.str_unit_km_per_hour) + ")");
            } else {
                this.f6503f.setText(getString(C1373R.string.str_total_mileage) + "(" + getString(C1373R.string.str_mileage_unit_mile) + ")");
                this.f6506i.setText(getString(C1373R.string.str_average_speed) + "(" + getString(C1373R.string.str_unit_mile_per_hour) + ")");
            }
        }
        m7730c();
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case C1373R.id.cycling_fragment_start:
                intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("INTENT_TO_CYCLING_FRAGMENT", true);
                getActivity().startActivity(intent);
                return;
            case C1373R.id.cycling_fragment_setting:
                startActivity(new Intent(getActivity(), CyclingSettingActivity.class));
                return;
            case C1373R.id.tv_profile_data_personal_record:
                intent = new Intent(getActivity(), PersonalRecordActivity.class);
                intent.putExtra("user_id", b());
                startActivity(intent);
                return;
            case C1373R.id.img_profile_data_target_edit:
                m7723f();
                return;
            case C1373R.id.tv_profile_data_cycling_history:
                intent = new Intent(getActivity(), CyclingRecordActivity.class);
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser != null) {
                    intent.putExtra("user_id", currentUser.getObjectId());
                    intent.putExtra("avatar_url", currentUser.getAvatar());
                    intent.putExtra("nick_name", currentUser.getDisplayName());
                    intent.putExtra("refresh", true);
                    startActivity(intent);
                    C2580w.a(getActivity(), "查看我的骑行纪录列表", null);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.f6515r != null) {
            this.f6515r.unregisterOnSharedPreferenceChangeListener(this);
            this.f6515r = null;
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (TextUtils.equals(str, "cycling_my_goal")) {
            m7730c();
        }
    }

    /* renamed from: a */
    public ProfileDataFragment m7724a() {
        return this;
    }

    /* renamed from: a */
    public void m7727a(UserDetailDTO userDetailDTO) {
        if (userDetailDTO != null) {
            this.f6514q = userDetailDTO;
            if (userDetailDTO.getTotalDistance() > 0.0d || userDetailDTO.isExistRoute()) {
                if (this.f6499b != null) {
                    m7720b(getView());
                    if (this.f6500c != null) {
                        this.f6500c.setVisibility(8);
                    }
                }
                m7722e();
            } else if (this.f6498a != null) {
                m7719a(getView());
                if (this.f6501d != null) {
                    this.f6501d.setVisibility(8);
                }
            }
        }
    }

    /* renamed from: a */
    public void m7725a(double d) {
        try {
            JSONObject jSONObject = new JSONObject(this.f6515r.getString("cycling_my_goal", ""));
            jSONObject.put("myGoal", 1000.0d * d);
            this.f6515r.edit().putString("cycling_my_goal", jSONObject.toString()).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
        m7730c();
    }

    /* renamed from: a */
    public void m7729a(HashMap<String, CalendarDto> hashMap) {
        if (this.f6513p != null) {
            this.f6513p.setCalendars(hashMap);
        }
    }

    /* renamed from: a */
    public void m7728a(Date date) {
        Intent intent = new Intent(getActivity(), CyclingRecordActivity.class);
        intent.putExtra("filter_day", date.getTime());
        startActivity(intent);
    }

    /* renamed from: a */
    public void m7726a(int i, int i2) {
        this.f6516s = i;
        this.f6517t = i2;
        this.f6512o.a(i, i2);
    }

    /* renamed from: a */
    private void m7719a(View view) {
        this.f6498a.inflate();
        this.f6498a = null;
        this.f6500c = (RelativeLayout) view.findViewById(C1373R.id.rela_profile_data_default);
        RippleView rippleView = (RippleView) view.findViewById(C1373R.id.cycling_fragment_start);
        ImageView imageView = (ImageView) view.findViewById(C1373R.id.cycling_fragment_setting);
        imageView.setVisibility(8);
        rippleView.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    /* renamed from: b */
    private void m7720b(View view) {
        this.f6499b.inflate();
        this.f6499b = null;
        this.f6501d = (ScrollView) view.findViewById(C1373R.id.scroll_profile_data_normal);
        this.f6502e = (TextView) view.findViewById(C1373R.id.tv_profile_data_total_mileage);
        this.f6503f = (TextView) view.findViewById(C1373R.id.tv_profile_data_total_mileage_unit);
        TextView textView = (TextView) view.findViewById(C1373R.id.tv_profile_data_personal_record);
        this.f6504g = (TextView) view.findViewById(C1373R.id.tv_profile_data_total_time);
        this.f6505h = (TextView) view.findViewById(C1373R.id.tv_profile_data_avg_speed);
        this.f6506i = (TextView) view.findViewById(C1373R.id.tv_profile_data_avg_speed_unit);
        this.f6507j = (TextView) view.findViewById(C1373R.id.tv_profile_data_times);
        this.f6508k = (CyclingTargetProgressBar) view.findViewById(C1373R.id.progressBar_profile_data_target);
        this.f6509l = (TextView) view.findViewById(C1373R.id.tv_profile_data_target_progress);
        this.f6510m = (TextView) view.findViewById(C1373R.id.tv_profile_data_finished_target);
        ImageView imageView = (ImageView) view.findViewById(C1373R.id.img_profile_data_target_edit);
        TextView textView2 = (TextView) view.findViewById(C1373R.id.tv_profile_data_cycling_history);
        this.f6513p = (CalendarView) view.findViewById(C1373R.id.calendarView_profile_data);
        this.f6513p.setMonthClickListener(this);
        this.f6513p.setCalendarListener(this);
        textView.setOnClickListener(this);
        imageView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        m7721d();
        Calendar instance = Calendar.getInstance();
        this.f6516s = instance.get(1);
        this.f6517t = instance.get(2) + 1;
        this.f6512o.a(this.f6516s, this.f6517t);
    }

    /* renamed from: d */
    private void m7721d() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f6515r = getActivity().getSharedPreferences(currentUser.getObjectId(), 0);
            this.f6515r.registerOnSharedPreferenceChangeListener(this);
            m7730c();
        }
    }

    /* renamed from: e */
    private void m7722e() {
        if (this.f6514q != null && this.f6504g != null) {
            float totalElapsedTime = (((float) this.f6514q.getTotalElapsedTime()) * 1.0f) / 3600.0f;
            this.f6504g.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Float.valueOf(totalElapsedTime)}));
            double totalDistance = this.f6514q.getTotalDistance();
            double averageSpeed = this.f6514q.getAverageSpeed();
            if (C1849a.b(getActivity())) {
                this.f6502e.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(totalDistance / 1000.0d)}));
                this.f6505h.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(averageSpeed)}));
            } else {
                this.f6502e.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(C1849a.a(totalDistance / 1000.0d))}));
                this.f6505h.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(C1849a.a(averageSpeed))}));
            }
            this.f6507j.setText(String.valueOf(this.f6514q.getTotalCount()));
            if (totalDistance > 0.0d) {
                this.f6512o.a(this.f6516s, this.f6517t);
            }
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                currentUser.setTotalMileage(totalDistance);
                AVUser.saveCurrentUser(currentUser);
            }
        }
    }

    /* renamed from: c */
    public void m7730c() {
        double d = 0.0d;
        if (this.f6515r != null) {
            try {
                MyGoalInfoDTO myGoalInfoDTO = new MyGoalInfoDTO(new JSONObject(this.f6515r.getString("cycling_my_goal", "")));
                double myGoal = myGoalInfoDTO.getMyGoal() / 1000.0d;
                String valueOf = String.valueOf(Math.round(myGoal));
                double curGoal = myGoalInfoDTO.getCurGoal() / 1000.0d;
                if (!C1849a.b(getActivity())) {
                    curGoal = C1849a.a(curGoal);
                }
                int i = 0;
                if (myGoal > 0.0d) {
                    i = (int) Math.round((curGoal / myGoal) * 100.0d);
                }
                if (this.f6508k != null) {
                    this.f6508k.setProgress(i);
                    this.f6509l.setText(i + "%");
                    this.f6511n = valueOf;
                    if (curGoal >= 0.0d) {
                        d = curGoal;
                    }
                    String valueOf2 = String.valueOf(Math.round(d));
                    if (C1849a.b(getContext())) {
                        this.f6510m.setText(valueOf2 + "/" + valueOf + getString(C1373R.string.str_mileage_unit_km));
                    } else {
                        this.f6510m.setText(valueOf2 + "/" + valueOf + getString(C1373R.string.str_mileage_unit_mile));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: f */
    private void m7723f() {
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i <= 20; i++) {
            arrayList.add(String.valueOf(i * 50));
        }
        boolean b = C1849a.b(getActivity());
        C1805k c1805k = new C1805k(getActivity(), new ProfileDataFragment$1(this));
        c1805k.show();
        c1805k.a(arrayList);
        if (b) {
            c1805k.a("KM");
        } else {
            c1805k.a("MI");
        }
        if (!TextUtils.isEmpty(this.f6511n)) {
            c1805k.b(this.f6511n);
        }
    }
}
