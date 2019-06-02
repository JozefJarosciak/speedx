package com.beastbikes.android.modules.cycling.activity.ui;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.dialog.C1805k;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.biz.C1916c;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.cycling.activity.dto.MyGoalInfoDTO;
import com.beastbikes.android.modules.cycling.activity.p114a.C1901a;
import com.beastbikes.android.modules.preferences.ui.CyclingSettingActivity;
import com.beastbikes.android.modules.user.ui.CyclingRecordActivity;
import com.beastbikes.android.p054a.C1529b;
import com.beastbikes.android.p054a.C1529b.C1528a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.CyclingNumberTextView;
import com.beastbikes.android.widget.CyclingTargetProgressBar;
import com.beastbikes.android.widget.NumberTextView;
import com.beastbikes.android.widget.RippleView;
import com.beastbikes.android.widget.RippleView.C2600a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.business.BusinessException;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903363)
public class CyclingFragment extends SessionFragment implements OnSharedPreferenceChangeListener, OnClickListener, C1371a, C1528a, C2600a {
    /* renamed from: a */
    private static final Logger f4611a = LoggerFactory.getLogger(CyclingFragment.class);
    /* renamed from: A */
    private int f4612A;
    /* renamed from: B */
    private String f4613B;
    /* renamed from: b */
    private final BroadcastReceiver f4614b = new CyclingFragment$a(this, null);
    /* renamed from: c */
    private View f4615c;
    @C1458a(a = 2131756625)
    /* renamed from: d */
    private ImageView f4616d;
    @C1458a(a = 2131756624)
    /* renamed from: e */
    private TextView f4617e;
    @C1458a(a = 2131756623)
    /* renamed from: f */
    private CyclingNumberTextView f4618f;
    @C1458a(a = 2131756630)
    /* renamed from: g */
    private NumberTextView f4619g;
    @C1458a(a = 2131756631)
    /* renamed from: h */
    private NumberTextView f4620h;
    @C1458a(a = 2131756632)
    /* renamed from: i */
    private NumberTextView f4621i;
    @C1458a(a = 2131756627)
    /* renamed from: j */
    private TextView f4622j;
    @C1458a(a = 2131756626)
    /* renamed from: k */
    private TextView f4623k;
    @C1458a(a = 2131756906)
    /* renamed from: l */
    private RippleView f4624l;
    @C1458a(a = 2131756629)
    /* renamed from: m */
    private View f4625m;
    @C1458a(a = 2131756907)
    /* renamed from: n */
    private ImageView f4626n;
    @C1458a(a = 2131756908)
    /* renamed from: o */
    private TextView f4627o;
    @C1458a(a = 2131756909)
    /* renamed from: p */
    private ImageView f4628p;
    @C1458a(a = 2131756628)
    /* renamed from: q */
    private CyclingTargetProgressBar f4629q;
    /* renamed from: r */
    private C1916c f4630r;
    /* renamed from: s */
    private C1398a f4631s;
    /* renamed from: t */
    private Timer f4632t;
    /* renamed from: u */
    private SharedPreferences f4633u;
    /* renamed from: v */
    private SharedPreferences f4634v;
    /* renamed from: w */
    private MyGoalInfoDTO f4635w;
    /* renamed from: x */
    private Toolbar f4636x;
    /* renamed from: y */
    private boolean f4637y = false;
    /* renamed from: z */
    private C1901a f4638z;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f4615c = super.onCreateView(layoutInflater, viewGroup, bundle);
        setHasOptionsMenu(true);
        this.f4630r = new C1916c(getActivity());
        this.f4616d.setOnClickListener(this);
        this.f4624l.setOnRippleCompleteListener(this);
        this.f4625m.setOnClickListener(this);
        this.f4628p.setOnClickListener(this);
        this.f4636x = (Toolbar) getActivity().findViewById(C1373R.id.toolbar);
        return this.f4615c;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_cycling_fragment_setting:
                if (this.f4638z == null) {
                    this.f4638z = new C1901a(getActivity());
                }
                this.f4638z.show();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onAttach(Activity activity) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.beastbikes.intent.action.ACTIVITY_COMPLETE");
        intentFilter.addAction("action.target.distance");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        activity.registerReceiver(this.f4614b, intentFilter);
        super.onAttach(activity);
    }

    public void onDetach() {
        getActivity().unregisterReceiver(this.f4614b);
        super.onDetach();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f4631s = new C1398a(getActivity());
        this.f4633u = getActivity().getSharedPreferences(b(), 0);
        this.f4633u.registerOnSharedPreferenceChangeListener(this);
        this.f4634v = getActivity().getSharedPreferences(getActivity().getPackageName(), 0);
        m6001h();
        m5997e();
    }

    public void onResume() {
        super.onResume();
        this.f4637y = C1849a.b(getActivity());
        CharSequence charSequence = getString(C1373R.string.cycling_fragment_current_distance_label) + "(KM)";
        if (!this.f4637y) {
            charSequence = getString(C1373R.string.cycling_fragment_current_distance_label) + "(MI)";
        }
        this.f4617e.setText(charSequence);
        m5999f();
        m5996d();
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.f4632t != null) {
            this.f4632t.cancel();
            this.f4632t = null;
        }
        if (this.f4633u != null) {
            this.f4633u.unregisterOnSharedPreferenceChangeListener(this);
            this.f4633u = null;
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            getActivity().setTitle(C1373R.string.activity_fragment_title);
            m5993c();
        }
    }

    /* renamed from: c */
    private void m5993c() {
        boolean z = this.f4633u.getBoolean("beast.cycling.state.check", true);
        if (this.f4631s != null && z) {
            Serializable a = this.f4631s.m5861a();
            if (a != null) {
                Intent intent = new Intent(getContext(), CyclingActivity.class);
                intent.putExtra("local_activity", a);
                startActivity(intent);
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.cycling_fragment_target_setting_iv:
                m6004k();
                C2580w.a(getActivity(), "click_ridding_goal", "click_ridding_goal");
                return;
            case C1373R.id.fragment_cycling_record:
                Intent intent = new Intent(getActivity(), CyclingRecordActivity.class);
                if (AVUser.getCurrentUser() != null) {
                    intent.putExtra("user_id", AVUser.getCurrentUser().getObjectId());
                    intent.putExtra("avatar_url", AVUser.getCurrentUser().getAvatar());
                    intent.putExtra("nick_name", AVUser.getCurrentUser().getDisplayName());
                    intent.putExtra("refresh", true);
                    startActivity(intent);
                    C2580w.a(getActivity(), "查看我的骑行纪录列表", null);
                    return;
                }
                return;
            case C1373R.id.cycling_fragment_setting:
                startActivity(new Intent(getActivity(), CyclingSettingActivity.class));
                return;
            default:
                return;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i2) {
            case 0:
                switch (i) {
                    case 16061:
                        if (C1529b.a(getActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION"})) {
                            m6000g();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        C1529b.a(i, strArr, iArr, this);
    }

    /* renamed from: a */
    public void m6005a(int i, List<String> list) {
        f4611a.trace("获取权限，Permissions = " + list.toString());
        m6000g();
    }

    /* renamed from: b */
    public void m6007b(int i, List<String> list) {
        f4611a.trace("获取权限失败，Permissions = " + list.toString());
    }

    /* renamed from: a */
    public void m6006a(RippleView rippleView) {
        String[] strArr = new String[]{"android.permission.ACCESS_FINE_LOCATION"};
        if (C1529b.a(getActivity(), strArr)) {
            m6000g();
            return;
        }
        f4611a.warn("start ActivityService has no location permission");
        C1529b.a(this, getString(C1373R.string.msg_start_cycling_get_location_permission), 12, strArr);
    }

    /* renamed from: d */
    private void m5996d() {
        LocalActivity a = this.f4631s.m5861a();
        if (a == null) {
            this.f4626n.setImageResource(C1373R.drawable.ic_start_cycling_en);
            if (C1849a.c()) {
                this.f4627o.setVisibility(0);
                return;
            } else {
                this.f4627o.setVisibility(8);
                return;
            }
        }
        switch (a.getState()) {
            case 0:
            case 4:
                this.f4626n.setImageResource(C1373R.drawable.ic_start_cycling_en);
                if (C1849a.c()) {
                    this.f4627o.setVisibility(0);
                    return;
                } else {
                    this.f4627o.setVisibility(8);
                    return;
                }
            case 1:
                this.f4626n.setImageResource(C1373R.drawable.ic_cycling_pause_icon);
                this.f4627o.setVisibility(8);
                return;
            case 2:
            case 3:
                this.f4626n.setImageResource(C1373R.drawable.ic_cycling_start_icon);
                this.f4627o.setVisibility(8);
                return;
            default:
                return;
        }
    }

    /* renamed from: e */
    private void m5997e() {
        getAsyncTaskQueue().a(new CyclingFragment$1(this), new Void[0]);
    }

    /* renamed from: f */
    private void m5999f() {
        MyGoalInfoDTO myGoalInfoDTO;
        Exception e;
        MyGoalInfoDTO a;
        double myGoal;
        String valueOf;
        double curGoal;
        Object valueOf2;
        long j;
        long j2;
        long j3;
        long monthTime;
        int round;
        if (this.f4633u.contains("cycling_my_goal")) {
            Object string = this.f4633u.getString("cycling_my_goal", "");
            if (!TextUtils.isEmpty(string)) {
                try {
                    myGoalInfoDTO = new MyGoalInfoDTO(new JSONObject(string));
                    try {
                        this.f4635w = myGoalInfoDTO;
                        f4611a.info("GoalInfo JsonObject = " + this.f4635w.toString());
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        a = m5986a(myGoalInfoDTO);
                        if (a != null) {
                            myGoal = a.getMyGoal() / 1000.0d;
                            valueOf = String.valueOf(Math.round(myGoal));
                            this.f4613B = valueOf;
                            curGoal = a.getCurGoal() / 1000.0d;
                            if (!C1849a.b(getActivity())) {
                                curGoal = C1849a.a(curGoal);
                            }
                            if (curGoal < 0.0d) {
                                curGoal = 0.0d;
                            }
                            valueOf2 = String.valueOf(Math.round(curGoal));
                            this.f4618f.setText(valueOf2);
                            this.f4619g.setText(String.valueOf(a.getMonthCount()));
                            if (C1849a.b(getContext())) {
                                this.f4621i.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(a.getMonthAvgSpeed())}));
                                this.f4618f.setText(valueOf2);
                                this.f4623k.setText(valueOf2 + "/" + valueOf + "KM");
                            } else {
                                this.f4621i.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(C1849a.d(a.getMonthAvgSpeed()))}));
                                this.f4618f.setText(valueOf2);
                                this.f4623k.setText(valueOf2 + "/" + valueOf + "MI");
                            }
                            j = 0;
                            j2 = 0;
                            j3 = 0;
                            monthTime = (long) a.getMonthTime();
                            if (monthTime > 0) {
                                j = monthTime / 3600;
                                j2 = (monthTime % 3600) / 60;
                                j3 = (monthTime % 3600) % 60;
                            }
                            this.f4620h.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d", new Object[]{Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)}));
                            if (a.getMyGoal() == 0.0d) {
                                round = (int) Math.round((curGoal / myGoal) * 100.0d);
                                if (round == this.f4629q.getProgress()) {
                                    this.f4629q.setProgress(0);
                                    this.f4622j.setText(round + "%");
                                    this.f4629q.setProgress(round);
                                }
                            }
                        }
                    }
                } catch (Exception e3) {
                    Exception exception = e3;
                    myGoalInfoDTO = null;
                    e = exception;
                    e.printStackTrace();
                    a = m5986a(myGoalInfoDTO);
                    if (a != null) {
                        myGoal = a.getMyGoal() / 1000.0d;
                        valueOf = String.valueOf(Math.round(myGoal));
                        this.f4613B = valueOf;
                        curGoal = a.getCurGoal() / 1000.0d;
                        if (C1849a.b(getActivity())) {
                            curGoal = C1849a.a(curGoal);
                        }
                        if (curGoal < 0.0d) {
                            curGoal = 0.0d;
                        }
                        valueOf2 = String.valueOf(Math.round(curGoal));
                        this.f4618f.setText(valueOf2);
                        this.f4619g.setText(String.valueOf(a.getMonthCount()));
                        if (C1849a.b(getContext())) {
                            this.f4621i.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(a.getMonthAvgSpeed())}));
                            this.f4618f.setText(valueOf2);
                            this.f4623k.setText(valueOf2 + "/" + valueOf + "KM");
                        } else {
                            this.f4621i.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(C1849a.d(a.getMonthAvgSpeed()))}));
                            this.f4618f.setText(valueOf2);
                            this.f4623k.setText(valueOf2 + "/" + valueOf + "MI");
                        }
                        j = 0;
                        j2 = 0;
                        j3 = 0;
                        monthTime = (long) a.getMonthTime();
                        if (monthTime > 0) {
                            j = monthTime / 3600;
                            j2 = (monthTime % 3600) / 60;
                            j3 = (monthTime % 3600) % 60;
                        }
                        this.f4620h.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d", new Object[]{Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)}));
                        if (a.getMyGoal() == 0.0d) {
                            round = (int) Math.round((curGoal / myGoal) * 100.0d);
                            if (round == this.f4629q.getProgress()) {
                                this.f4629q.setProgress(0);
                                this.f4622j.setText(round + "%");
                                this.f4629q.setProgress(round);
                            }
                        }
                    }
                }
                a = m5986a(myGoalInfoDTO);
                if (a != null) {
                    myGoal = a.getMyGoal() / 1000.0d;
                    valueOf = String.valueOf(Math.round(myGoal));
                    this.f4613B = valueOf;
                    curGoal = a.getCurGoal() / 1000.0d;
                    if (C1849a.b(getActivity())) {
                        curGoal = C1849a.a(curGoal);
                    }
                    if (curGoal < 0.0d) {
                        curGoal = 0.0d;
                    }
                    valueOf2 = String.valueOf(Math.round(curGoal));
                    this.f4618f.setText(valueOf2);
                    this.f4619g.setText(String.valueOf(a.getMonthCount()));
                    if (C1849a.b(getContext())) {
                        this.f4621i.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(a.getMonthAvgSpeed())}));
                        this.f4618f.setText(valueOf2);
                        this.f4623k.setText(valueOf2 + "/" + valueOf + "KM");
                    } else {
                        this.f4621i.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(C1849a.d(a.getMonthAvgSpeed()))}));
                        this.f4618f.setText(valueOf2);
                        this.f4623k.setText(valueOf2 + "/" + valueOf + "MI");
                    }
                    j = 0;
                    j2 = 0;
                    j3 = 0;
                    monthTime = (long) a.getMonthTime();
                    if (monthTime > 0) {
                        j = monthTime / 3600;
                        j2 = (monthTime % 3600) / 60;
                        j3 = (monthTime % 3600) % 60;
                    }
                    this.f4620h.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d", new Object[]{Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)}));
                    if (a.getMyGoal() == 0.0d) {
                        round = (int) Math.round((curGoal / myGoal) * 100.0d);
                        if (round == this.f4629q.getProgress()) {
                            this.f4629q.setProgress(0);
                            this.f4622j.setText(round + "%");
                            this.f4629q.setProgress(round);
                        }
                    }
                }
            }
        }
        myGoalInfoDTO = null;
        a = m5986a(myGoalInfoDTO);
        if (a != null) {
            myGoal = a.getMyGoal() / 1000.0d;
            valueOf = String.valueOf(Math.round(myGoal));
            this.f4613B = valueOf;
            curGoal = a.getCurGoal() / 1000.0d;
            if (C1849a.b(getActivity())) {
                curGoal = C1849a.a(curGoal);
            }
            if (curGoal < 0.0d) {
                curGoal = 0.0d;
            }
            valueOf2 = String.valueOf(Math.round(curGoal));
            this.f4618f.setText(valueOf2);
            this.f4619g.setText(String.valueOf(a.getMonthCount()));
            if (C1849a.b(getContext())) {
                this.f4621i.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(C1849a.d(a.getMonthAvgSpeed()))}));
                this.f4618f.setText(valueOf2);
                this.f4623k.setText(valueOf2 + "/" + valueOf + "MI");
            } else {
                this.f4621i.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(a.getMonthAvgSpeed())}));
                this.f4618f.setText(valueOf2);
                this.f4623k.setText(valueOf2 + "/" + valueOf + "KM");
            }
            j = 0;
            j2 = 0;
            j3 = 0;
            monthTime = (long) a.getMonthTime();
            if (monthTime > 0) {
                j = monthTime / 3600;
                j2 = (monthTime % 3600) / 60;
                j3 = (monthTime % 3600) % 60;
            }
            this.f4620h.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d", new Object[]{Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)}));
            if (a.getMyGoal() == 0.0d) {
                round = (int) Math.round((curGoal / myGoal) * 100.0d);
                if (round == this.f4629q.getProgress()) {
                    this.f4629q.setProgress(0);
                    this.f4622j.setText(round + "%");
                    this.f4629q.setProgress(round);
                }
            }
        }
    }

    /* renamed from: a */
    private MyGoalInfoDTO m5986a(MyGoalInfoDTO myGoalInfoDTO) {
        if (myGoalInfoDTO == null) {
            return null;
        }
        try {
            List<LocalActivity> c = this.f4631s.m5874c(b(), "");
            if (c == null || c.size() < 1) {
                return myGoalInfoDTO;
            }
            for (LocalActivity localActivity : c) {
                if (localActivity != null && localActivity.getTotalDistance() > 10.0d) {
                    double curGoal = myGoalInfoDTO.getCurGoal() + localActivity.getTotalDistance();
                    myGoalInfoDTO.setCurGoal(curGoal);
                    double monthTime = myGoalInfoDTO.getMonthTime() + localActivity.getTotalElapsedTime();
                    myGoalInfoDTO.setMonthTime(monthTime);
                    myGoalInfoDTO.setMonthAvgSpeed((curGoal / monthTime) * 3.6d);
                    myGoalInfoDTO.setMonthCount(myGoalInfoDTO.getMonthCount() + 1);
                }
            }
            return myGoalInfoDTO;
        } catch (BusinessException e) {
            f4611a.error("Query local activity unsynced error, " + e);
            return myGoalInfoDTO;
        }
    }

    /* renamed from: g */
    private void m6000g() {
        C2580w.a(getActivity(), "", "click_ridding_start");
        if (m6003j()) {
            LocalActivity a = this.f4631s.m5861a();
            try {
                int i;
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser == null || currentUser.getTrainingId() <= 0) {
                    i = 1;
                } else {
                    i = 2;
                }
                if (a == null) {
                    this.f4612A = i;
                    m5990a(i);
                    return;
                } else if (a.getState() == 3 || a.getState() == 2 || a.getState() == 1) {
                    Intent intent = new Intent("com.beastbikes.intent.action.ACTIVITY_MANAGER");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.putExtra(C0861d.f2143o, "com.beastbikes.intent.action.ACTIVITY_RESUME");
                    intent.setPackage(getActivity().getPackageName());
                    getActivity().startService(intent);
                    intent = new Intent(getActivity(), CyclingActivity.class);
                    intent.putExtra("cycling_type", i);
                    getActivity().startActivity(intent);
                    return;
                } else {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        C2621c c2621c = new C2621c(getActivity());
        c2621c.a(C1373R.string.activity_no_GPS_title);
        c2621c.b(C1373R.string.activity_no_GPS_message);
        c2621c.b(true);
        c2621c.a(C1373R.string.activity_alert_dialog_text_ok, new CyclingFragment$2(this, c2621c));
        c2621c.b(C1373R.string.activity_alert_dialog_text_cancel, new CyclingFragment$3(this, c2621c));
        c2621c.a();
    }

    /* renamed from: a */
    private void m5990a(int i) {
        Intent intent = new Intent(getActivity(), CyclingStartAnimationActivity.class);
        intent.putExtra("cycling_type", i);
        getActivity().startActivity(intent);
    }

    /* renamed from: h */
    private void m6001h() {
        if (!m6002i() && !this.f4634v.getBoolean("Enable.Ride.Protection", false)) {
            this.f4634v.edit().putBoolean("Enable.Ride.Protection", true).apply();
            new Builder(getActivity()).setTitle(C1373R.string.str_open_keep_cycling).setMessage(getString(C1373R.string.str_open_keep_cycling_desc1) + "\n" + getString(C1373R.string.str_open_keep_cycling_desc2)).setNegativeButton(C1373R.string.cancel, null).setPositiveButton(C1373R.string.str_ok, new CyclingFragment$4(this)).create().show();
        }
    }

    /* renamed from: i */
    private boolean m6002i() {
        CharSequence packageName = getActivity().getPackageName();
        Object string = Secure.getString(getActivity().getContentResolver(), "enabled_notification_listeners");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        for (String unflattenFromString : string.split(":")) {
            ComponentName unflattenFromString2 = ComponentName.unflattenFromString(unflattenFromString);
            if (unflattenFromString2 != null && TextUtils.equals(packageName, unflattenFromString2.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: j */
    private boolean m6003j() {
        try {
            return ((LocationManager) getActivity().getSystemService(MapboxEvent.TYPE_LOCATION)).isProviderEnabled("gps");
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (TextUtils.equals(str, "cycling_my_goal")) {
            m5999f();
        }
    }

    /* renamed from: k */
    private void m6004k() {
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i <= 20; i++) {
            arrayList.add(String.valueOf(i * 50));
        }
        boolean b = C1849a.b(getActivity());
        C1805k c1805k = new C1805k(getActivity(), new CyclingFragment$5(this));
        c1805k.show();
        c1805k.a(arrayList);
        if (b) {
            c1805k.a("KM");
        } else {
            c1805k.a("MI");
        }
        if (!TextUtils.isEmpty(this.f4613B)) {
            c1805k.b(this.f4613B);
        }
    }

    /* renamed from: a */
    private void m5989a(double d) {
        getAsyncTaskQueue().a(new CyclingFragment$6(this, new C1802i(getActivity(), getString(C1373R.string.empty), false), d), new Double[0]);
    }
}
