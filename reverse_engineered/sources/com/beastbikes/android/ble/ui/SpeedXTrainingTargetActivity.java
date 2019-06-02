package com.beastbikes.android.ble.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.avos.avoscloud.AVException;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.ble.C1602a;
import com.beastbikes.android.ble.CentralService;
import com.beastbikes.android.ble.CentralService.C1596c;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p097b.C1629m;
import com.beastbikes.android.dialog.Wheelview;
import com.beastbikes.android.dialog.Wheelview.C1707d;
import com.beastbikes.android.dialog.Wheelview.C1708e;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.train.dto.TrainTargetDto;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.utils.aa;
import com.beastbikes.android.widget.C2657e;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.wdullaer.materialdatetimepicker.date.C4789b;
import com.wdullaer.materialdatetimepicker.date.C4789b.C4788b;
import java.util.ArrayList;
import java.util.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903196)
public class SpeedXTrainingTargetActivity extends SessionFragmentActivity implements ServiceConnection, OnClickListener, C1629m, C4788b {
    /* renamed from: a */
    private static final Logger f4375a = LoggerFactory.getLogger(SpeedXTrainingTargetActivity.class);
    @C1458a(a = 2131756057)
    /* renamed from: b */
    private ViewGroup f4376b;
    /* renamed from: c */
    private TextView f4377c;
    @C1458a(a = 2131756058)
    /* renamed from: d */
    private ViewGroup f4378d;
    /* renamed from: e */
    private TextView f4379e;
    @C1458a(a = 2131756059)
    /* renamed from: f */
    private ViewGroup f4380f;
    /* renamed from: g */
    private TextView f4381g;
    @C1458a(a = 2131756060)
    /* renamed from: h */
    private ViewGroup f4382h;
    /* renamed from: i */
    private TextView f4383i;
    @C1458a(a = 2131756061)
    /* renamed from: j */
    private ViewGroup f4384j;
    /* renamed from: k */
    private TextView f4385k;
    @C1458a(a = 2131756062)
    /* renamed from: l */
    private Button f4386l;
    /* renamed from: m */
    private boolean f4387m;
    /* renamed from: n */
    private int f4388n;
    /* renamed from: o */
    private int f4389o;
    /* renamed from: p */
    private int f4390p;
    /* renamed from: q */
    private int f4391q;
    /* renamed from: r */
    private String f4392r;
    /* renamed from: s */
    private CentralService f4393s;
    /* renamed from: t */
    private SharedPreferences f4394t;
    /* renamed from: u */
    private boolean f4395u;
    /* renamed from: v */
    private boolean f4396v;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        Intent intent = getIntent();
        if (intent != null) {
            this.f4395u = intent.getBooleanExtra("show_menu", false);
            this.f4396v = intent.getBooleanExtra("from_speedx_force", false);
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            if (this.f4395u) {
                supportActionBar.setHomeAsUpIndicator((int) C1373R.drawable.ic_close_icon);
                this.f4386l.setVisibility(0);
                this.f4386l.setOnClickListener(this);
            }
        }
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f4394t = getSharedPreferences(currentUser.getObjectId(), 0);
        }
        intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
        intent.setPackage(getPackageName());
        bindService(intent, this, 1);
        this.f4387m = C1849a.b(this);
        m5748b();
        if (this.f4395u) {
            m5751c();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.f4395u) {
            getMenuInflater().inflate(C1373R.menu.menu_train_target, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                break;
            case C1373R.id.train_target_next:
                finish();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f4393s != null) {
            unbindService(this);
        }
    }

    public void finish() {
        m5756e();
        if (this.f4395u && !this.f4396v) {
            startActivity(new Intent(this, SpeedForceActivity.class));
        }
        if (this.f4396v) {
            Intent intent = new Intent("com.beastbikes.android.ble.connected.action");
            intent.addCategory("android.intent.category.DEFAULT");
            sendBroadcast(intent);
        }
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.training_target_setting_ftp:
                m5742a(this.f4388n);
                return;
            case C1373R.id.training_target_setting_gender:
                if (this.f4379e.getText().toString().equals(getString(C1373R.string.str_gender_male))) {
                    this.f4379e.setText(C1373R.string.str_gender_female);
                    this.f4391q = 0;
                    return;
                }
                this.f4379e.setText(C1373R.string.str_gender_male);
                this.f4391q = 1;
                return;
            case C1373R.id.training_target_setting_height:
                if (this.f4387m) {
                    m5744a("cm", (int) AVException.EXCEEDED_QUOTA, 200, this.f4389o, new SpeedXTrainingTargetActivity$1(this));
                    return;
                } else {
                    m5745a("'", "\"", C1849a.g((double) this.f4389o), C1849a.f((double) this.f4389o), new SpeedXTrainingTargetActivity$5(this));
                    return;
                }
            case C1373R.id.training_target_setting_weight:
                if (this.f4387m) {
                    m5744a("kg", 40, 150, Math.round((float) this.f4390p), new SpeedXTrainingTargetActivity$6(this));
                    return;
                } else {
                    m5744a("lb", 88, 330, Math.round((float) C1849a.h((double) this.f4390p)), new SpeedXTrainingTargetActivity$7(this));
                    return;
                }
            case C1373R.id.training_target_setting_age:
                m5754d();
                return;
            case C1373R.id.training_target_setting_submit:
                finish();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m5763a(C4789b c4789b, int i, int i2, int i3) {
        this.f4392r = i + "." + (i2 + 1) + "." + i3;
        this.f4385k.setText(this.f4392r);
    }

    /* renamed from: a */
    public void m5762a(TrainTargetDto trainTargetDto) {
        if (trainTargetDto == null) {
            f4375a.error("onTrainTargetResponse(), Train target is null");
            return;
        }
        this.f4391q = trainTargetDto.getSex();
        this.f4379e.setText(this.f4391q == 0 ? C1373R.string.str_gender_female : C1373R.string.str_gender_male);
        this.f4389o = (int) trainTargetDto.getHeight();
        this.f4390p = (int) trainTargetDto.getWeight();
        if (this.f4387m) {
            this.f4381g.setText(this.f4389o + "cm");
            this.f4383i.setText(this.f4390p + "kg");
        } else {
            this.f4381g.setText(C1849a.g((double) this.f4389o) + "'" + C1849a.f((double) this.f4389o) + "\"");
            this.f4383i.setText(((int) Math.round(C1849a.h((double) this.f4390p))) + "lb");
        }
        this.f4388n = trainTargetDto.getFtp();
        this.f4392r = trainTargetDto.getBirthday();
        this.f4385k.setText(this.f4392r);
        this.f4377c.setText(String.valueOf(this.f4388n));
    }

    /* renamed from: b */
    private void m5748b() {
        this.f4376b.setOnClickListener(this);
        ((TextView) this.f4376b.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_training_target_current_ftp);
        this.f4377c = (TextView) this.f4376b.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f4378d.setOnClickListener(this);
        ((TextView) this.f4378d.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_gender);
        this.f4379e = (TextView) this.f4378d.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f4380f.setOnClickListener(this);
        ((TextView) this.f4380f.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_height);
        this.f4381g = (TextView) this.f4380f.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f4382h.setOnClickListener(this);
        ((TextView) this.f4382h.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_weight);
        this.f4383i = (TextView) this.f4382h.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f4384j.setOnClickListener(this);
        ((TextView) this.f4384j.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_training_target_age);
        this.f4385k = (TextView) this.f4384j.findViewById(C1373R.id.speed_force_setting_item_value);
    }

    /* renamed from: c */
    private void m5751c() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            f4375a.error("AVUser is null");
            return;
        }
        this.f4388n = 200;
        if (this.f4394t != null) {
            this.f4388n = this.f4394t.getInt("PREF.USER.CURRENT.TRAIN.TARGET", 200);
        }
        this.f4377c.setText(String.valueOf(this.f4388n));
        getAsyncTaskQueue().a(new SpeedXTrainingTargetActivity$8(this, new C2389c(this), currentUser), new String[0]);
    }

    /* renamed from: a */
    private void m5744a(String str, int i, int i2, int i3, C1707d c1707d) {
        View inflate = getLayoutInflater().inflate(C1373R.layout.popwindowpickcommon, null, false);
        PopupWindow popupWindow = new PopupWindow(inflate, -1, -2, true);
        popupWindow.setOnDismissListener(new SpeedXTrainingTargetActivity$9(this));
        ArrayList arrayList = new ArrayList();
        for (int i4 = i; i4 <= i2; i4++) {
            arrayList.add(i4 + str);
        }
        Wheelview wheelview = (Wheelview) inflate.findViewById(C1373R.id.one);
        wheelview.setData(arrayList);
        if (i3 - i < 0) {
            wheelview.setDefault(0);
        } else if (i3 - i > i2) {
            wheelview.setDefault(i2);
        } else if (i3 - i < wheelview.getListSize()) {
            wheelview.setDefault(i3 - i);
        }
        ((TextView) inflate.findViewById(C1373R.id.savebtn)).setOnClickListener(new SpeedXTrainingTargetActivity$10(this, c1707d, wheelview, str, popupWindow));
        ((TextView) inflate.findViewById(C1373R.id.cancelbtn)).setOnClickListener(new SpeedXTrainingTargetActivity$11(this, popupWindow));
        m5741a(0.5d);
        popupWindow.setSoftInputMode(16);
        popupWindow.showAtLocation(findViewById(C1373R.id.activity_speedx_training_target), 80, 0, 0);
    }

    /* renamed from: a */
    private void m5745a(String str, String str2, int i, int i2, C1708e c1708e) {
        int i3;
        View inflate = getLayoutInflater().inflate(C1373R.layout.popwindowpickheight, null, false);
        PopupWindow popupWindow = new PopupWindow(inflate, -1, -2, true);
        popupWindow.setOnDismissListener(new SpeedXTrainingTargetActivity$12(this));
        ArrayList arrayList = new ArrayList();
        for (i3 = 4; i3 < 7; i3++) {
            arrayList.add(i3 + str);
        }
        ArrayList arrayList2 = new ArrayList();
        for (i3 = 0; i3 < 12; i3++) {
            arrayList2.add(i3 + str2);
        }
        Wheelview wheelview = (Wheelview) inflate.findViewById(C1373R.id.one);
        wheelview.setData(arrayList);
        Wheelview wheelview2 = (Wheelview) inflate.findViewById(C1373R.id.two);
        wheelview2.setData(arrayList2);
        if (i - 4 < 0) {
            wheelview.setDefault(0);
        } else if (i - 4 > 2) {
            wheelview.setDefault(2);
        } else {
            wheelview.setDefault(i - 4);
        }
        if (i2 < 0) {
            wheelview2.setDefault(0);
        } else if (i2 > 11) {
            wheelview2.setDefault(11);
        } else {
            wheelview2.setDefault(i2);
        }
        ((TextView) inflate.findViewById(C1373R.id.savebtn)).setOnClickListener(new SpeedXTrainingTargetActivity$2(this, c1708e, wheelview, str, wheelview2, str2, popupWindow));
        ((TextView) inflate.findViewById(C1373R.id.cancelbtn)).setOnClickListener(new SpeedXTrainingTargetActivity$3(this, popupWindow));
        m5741a(0.5d);
        popupWindow.setSoftInputMode(16);
        popupWindow.showAtLocation(findViewById(C1373R.id.activity_speedx_training_target), 80, 0, 0);
    }

    /* renamed from: d */
    private void m5754d() {
        int a;
        int a2;
        int i;
        C4789b a3;
        Object charSequence = this.f4385k.getText().toString();
        if (!TextUtils.isEmpty(charSequence)) {
            String[] split = charSequence.split("\\.");
            if (split.length == 3) {
                a = aa.a(split[0]);
                a2 = aa.a(split[1]) - 1;
                i = a2;
                a2 = aa.a(split[2]);
                if (i <= 0) {
                    i = 1;
                }
                a3 = C4789b.a(this, a, i, a2);
                a3.b(Calendar.getInstance());
                a3.a(aa.a(1900, 1, 1));
                a3.b(getResources().getColor(C1373R.color.bg_theme_black_color));
                a3.show(getFragmentManager(), "DatePickerOfBirth");
            }
        }
        a2 = 1;
        a = 1990;
        i = 1;
        if (i <= 0) {
            i = 1;
        }
        a3 = C4789b.a(this, a, i, a2);
        a3.b(Calendar.getInstance());
        a3.a(aa.a(1900, 1, 1));
        a3.b(getResources().getColor(C1373R.color.bg_theme_black_color));
        a3.show(getFragmentManager(), "DatePickerOfBirth");
    }

    /* renamed from: a */
    private void m5741a(double d) {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = (float) d;
        getWindow().setAttributes(attributes);
    }

    /* renamed from: a */
    private void m5742a(int i) {
        if (C1661h.a().b() == null) {
            Toasts.show(this, getString(C1373R.string.str_ble_retry_after_connect_to_device));
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (int i3 = 50; i3 <= 500; i3++) {
            if (i == i3) {
                i2 = i3 - 50;
            }
            arrayList.add(String.valueOf(i3));
        }
        new C2657e(this, arrayList, i2, new SpeedXTrainingTargetActivity$4(this)).showAtLocation(findViewById(C1373R.id.activity_speedx_training_target), 81, 0, 0);
    }

    /* renamed from: e */
    private void m5756e() {
        if (this.f4393s == null) {
            f4375a.info("setTrainTarget(), CentralService is null");
            return;
        }
        C1602a b = this.f4393s.b();
        if (b == null) {
            f4375a.info("setTrainTarget(), Invocation is null");
        } else {
            b.a(this.f4388n, this.f4391q, this.f4389o, this.f4390p, this.f4392r);
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        f4375a.info("onServiceConnected");
        this.f4393s = ((C1596c) iBinder).a();
        C1602a b = this.f4393s.b();
        if (b != null && !this.f4395u) {
            b.a(this);
            b.d();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        f4375a.info("onServiceDisconnected");
    }
}
