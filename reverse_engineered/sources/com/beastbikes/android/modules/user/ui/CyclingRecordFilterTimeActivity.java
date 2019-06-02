package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1794e;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

@C1459b(a = 2130903118)
@C1460c(a = 2131820578)
public class CyclingRecordFilterTimeActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131755669)
    /* renamed from: a */
    private ViewGroup f6336a;
    /* renamed from: b */
    private TextView f6337b;
    @C1458a(a = 2131755670)
    /* renamed from: c */
    private ViewGroup f6338c;
    /* renamed from: d */
    private TextView f6339d;
    /* renamed from: e */
    private SimpleDateFormat f6340e;
    /* renamed from: f */
    private long f6341f;
    /* renamed from: g */
    private long f6342g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f6340e = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
        if (C1849a.c()) {
            this.f6340e = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINESE);
        }
        Calendar instance = Calendar.getInstance();
        CharSequence format = this.f6340e.format(instance.getTime());
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        instance.set(11, 0);
        this.f6341f = instance.getTimeInMillis() / 1000;
        instance.set(11, 24);
        this.f6342g = (instance.getTimeInMillis() / 1000) - 1;
        this.f6336a.setOnClickListener(this);
        ((TextView) this.f6336a.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.label_start_time);
        this.f6337b = (TextView) this.f6336a.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f6337b.setText(format);
        this.f6338c.setOnClickListener(this);
        ((TextView) this.f6338c.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.label_end_time);
        this.f6339d = (TextView) this.f6338c.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f6339d.setText(format);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C1373R.id.menu_sure) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void finish() {
        Intent intent = getIntent();
        intent.putExtra("start_date", this.f6341f);
        intent.putExtra("end_date", this.f6342g);
        setResult(-1, intent);
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.cycling_record_filter_start_date_view:
                m7533a();
                return;
            case C1373R.id.cycling_record_filter_end_date_view:
                m7536b();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m7533a() {
        C1794e c1794e = new C1794e(this, 1, new CyclingRecordFilterTimeActivity$1(this));
        c1794e.show();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(this.f6341f * 1000);
        c1794e.a(gregorianCalendar.get(1), gregorianCalendar.get(2) + 1, gregorianCalendar.get(5));
    }

    /* renamed from: b */
    private void m7536b() {
        C1794e c1794e = new C1794e(this, 1, new CyclingRecordFilterTimeActivity$2(this));
        c1794e.show();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(this.f6342g * 1000);
        c1794e.a(gregorianCalendar.get(1), gregorianCalendar.get(2) + 1, gregorianCalendar.get(5));
    }
}
