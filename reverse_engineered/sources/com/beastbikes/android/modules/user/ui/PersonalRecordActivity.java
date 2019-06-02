package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.ui.record.p124e.C1985b;
import com.beastbikes.android.modules.user.dto.PersonalRecordDataDTO;
import com.beastbikes.android.modules.user.dto.PersonalRecordResponseDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.modules.user.ui.p158b.C2497a;
import com.beastbikes.android.modules.user.ui.p158b.C2498b;
import com.beastbikes.android.widget.convenientbanner.ConvenientBanner;
import com.beastbikes.android.widget.convenientbanner.ConvenientBanner.PageIndicatorAlign;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C2801d;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.C3238l;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.listener.C2012c;
import com.github.mikephil.charting.listener.C3285b;
import com.github.mikephil.charting.listener.ChartTouchListener.ChartGesture;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1457a(a = "我的成绩")
@C1459b(a = 2130903548)
public class PersonalRecordActivity extends SessionFragmentActivity implements OnClickListener, OnCheckedChangeListener, C3285b, C2012c {
    /* renamed from: a */
    private final Logger f6477a = LoggerFactory.getLogger(PersonalRecordActivity.class);
    @C1458a(a = 2131757233)
    /* renamed from: b */
    private RadioGroup f6478b;
    @C1458a(a = 2131757239)
    /* renamed from: c */
    private ConvenientBanner<PersonalRecordDataDTO> f6479c;
    @C1458a(a = 2131757240)
    /* renamed from: d */
    private TextView f6480d;
    @C1458a(a = 2131757241)
    /* renamed from: e */
    private LineChart f6481e;
    @C1458a(a = 2131757238)
    /* renamed from: f */
    private TextView f6482f;
    /* renamed from: g */
    private PersonalRecordResponseDTO f6483g;
    /* renamed from: h */
    private ArrayList<PersonalRecordDataDTO> f6484h;
    /* renamed from: i */
    private PopupWindow f6485i;
    /* renamed from: j */
    private C3213d f6486j;
    /* renamed from: k */
    private int f6487k = 3;
    /* renamed from: l */
    private C2389c f6488l;
    /* renamed from: m */
    private C1802i f6489m;
    /* renamed from: n */
    private ArrayList<Entry> f6490n;
    /* renamed from: o */
    private ArrayList<Entry> f6491o;
    /* renamed from: p */
    private C2497a f6492p;
    /* renamed from: q */
    private AsyncTask<Void, Void, PersonalRecordResponseDTO> f6493q;
    /* renamed from: r */
    private boolean f6494r;
    /* renamed from: s */
    private ArrayList<String> f6495s;
    /* renamed from: t */
    private boolean f6496t = true;
    /* renamed from: u */
    private PersonalRecordActivity$a f6497u;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("user_id");
            AVUser currentUser = AVUser.getCurrentUser();
            if (!(currentUser == null || stringExtra.equals(currentUser.getObjectId()))) {
                setTitle(C1373R.string.str_personal_record);
            }
            this.f6488l = new C2389c(this);
            this.f6484h = new ArrayList();
            this.f6490n = new ArrayList();
            this.f6491o = new ArrayList();
            this.f6494r = C1849a.a();
            this.f6492p = new C2497a(new ArrayList());
            this.f6495s = new ArrayList();
            this.f6497u = new PersonalRecordActivity$a(this, null);
            m7695b();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.tv_personal_record_popup_cycling_time:
                if (!this.f6496t) {
                    this.f6496t = true;
                    m7703f();
                    m7693a(this.f6490n);
                    this.f6481e.invalidate();
                    if (this.f6487k != 0) {
                        this.f6481e.a((float) (this.f6490n.size() - 1), 0);
                    }
                    this.f6480d.setText(C1373R.string.str_cycling_time_with_unit);
                    return;
                }
                return;
            case C1373R.id.tv_personal_record_popup_cycling_mileage:
                if (this.f6496t) {
                    this.f6496t = false;
                    m7703f();
                    m7693a(this.f6491o);
                    this.f6481e.invalidate();
                    if (this.f6487k != 0) {
                        this.f6481e.a((float) (this.f6491o.size() - 1), 0);
                    }
                    if (C1849a.b(this)) {
                        this.f6480d.setText(C1373R.string.str_cycling_distance_with_unit_km);
                        return;
                    } else {
                        this.f6480d.setText(C1373R.string.str_cycling_distance_with_unit_mile);
                        return;
                    }
                }
                return;
            case C1373R.id.tv_personal_record_data_time_and_mileage_selector:
                m7700e();
                return;
            default:
                return;
        }
    }

    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case C1373R.id.radioBtn_personal_record_weekly:
                this.f6481e.setTouchEnabled(true);
                this.f6487k = 3;
                m7705g();
                return;
            case C1373R.id.radioBtn_personal_record_monthly:
                this.f6481e.setTouchEnabled(true);
                this.f6487k = 2;
                m7705g();
                return;
            case C1373R.id.radioBtn_personal_record_year:
                this.f6481e.setTouchEnabled(true);
                this.f6487k = 1;
                m7705g();
                return;
            case C1373R.id.radioBtn_personal_record_total:
                this.f6487k = 0;
                this.f6481e.setTouchEnabled(false);
                m7705g();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m7713a(Entry entry, C3213d c3213d) {
        if (c3213d != this.f6486j) {
            this.f6486j = c3213d;
            int a = (int) c3213d.a();
            m7690a(a);
            m7685a(a, c3213d);
        }
    }

    /* renamed from: a */
    public void m7708a() {
        if (this.f6486j != null) {
            this.f6481e.a(this.f6486j);
        }
    }

    /* renamed from: a */
    public void m7712a(MotionEvent motionEvent, ChartGesture chartGesture) {
    }

    /* renamed from: b */
    public void m7716b(MotionEvent motionEvent, ChartGesture chartGesture) {
    }

    /* renamed from: a */
    public void m7709a(MotionEvent motionEvent) {
    }

    /* renamed from: b */
    public void m7714b(MotionEvent motionEvent) {
    }

    /* renamed from: c */
    public void m7717c(MotionEvent motionEvent) {
    }

    /* renamed from: a */
    public void m7711a(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
    }

    /* renamed from: a */
    public void m7710a(MotionEvent motionEvent, float f, float f2) {
    }

    /* renamed from: b */
    public void m7715b(MotionEvent motionEvent, float f, float f2) {
    }

    /* renamed from: b */
    private void m7695b() {
        this.f6479c.a(new int[]{C1373R.drawable.circle_indicator_stroke, C1373R.drawable.circle_indicator_solid});
        this.f6479c.a(PageIndicatorAlign.CENTER_HORIZONTAL, C2801d.a(this, 295.0f));
        this.f6484h.add(null);
        this.f6484h.add(null);
        this.f6479c.a(new PersonalRecordActivity$1(this), this.f6484h);
        this.f6479c.setcurrentitem(0);
        m7699d();
        m7697c();
        m7705g();
    }

    /* renamed from: c */
    private void m7697c() {
        this.f6478b.setOnCheckedChangeListener(this);
        this.f6480d.setOnClickListener(this);
        this.f6481e.setOnChartGestureListener(this);
        this.f6481e.setOnChartValueSelectedListener(this);
    }

    /* renamed from: d */
    private void m7699d() {
        this.f6481e.setTouchEnabled(true);
        this.f6481e.setNoDataText("");
        this.f6481e.setDragEnabled(true);
        this.f6481e.setScaleEnabled(false);
        this.f6481e.setPinchZoom(false);
        this.f6481e.setBackgroundColor(Color.parseColor("#131313"));
        this.f6481e.setUnbindEnabled(true);
        this.f6481e.getDescription().e(false);
        this.f6481e.setExtraBottomOffset(10.0f);
        this.f6481e.setMarker(new C1985b(this, C1373R.drawable.ic_cycling_data_line_chart_marker));
        XAxis xAxis = this.f6481e.getXAxis();
        xAxis.a(10.0f, 10.0f, 0.0f);
        xAxis.a(false);
        xAxis.a(this.f6492p);
        xAxis.a(3, true);
        xAxis.c(true);
        xAxis.b(Color.parseColor("#333333"));
        xAxis.e(-1);
        xAxis.k(10.0f);
        xAxis.a(XAxisPosition.BOTTOM);
        xAxis.j(8.0f);
        YAxis axisLeft = this.f6481e.getAxisLeft();
        axisLeft.e(-1);
        axisLeft.k(10.0f);
        axisLeft.d(0.0f);
        axisLeft.j(0.0f);
        axisLeft.a(Color.parseColor("#979797"));
        axisLeft.a(1.0f, 10.0f, 0.0f);
        axisLeft.a(6, true);
        axisLeft.b(0.5f);
        axisLeft.f(false);
        axisLeft.b(false);
        axisLeft.a(new C2498b());
        axisLeft.d(false);
        this.f6481e.getAxisRight().e(false);
        this.f6481e.setContentDescription("");
        this.f6481e.getLegend().e(false);
    }

    /* renamed from: e */
    private void m7700e() {
        if (this.f6485i == null) {
            View inflate = LayoutInflater.from(this).inflate(C1373R.layout.layout_personal_record_popup, null);
            TextView textView = (TextView) inflate.findViewById(C1373R.id.tv_personal_record_popup_cycling_mileage);
            ((TextView) inflate.findViewById(C1373R.id.tv_personal_record_popup_cycling_time)).setOnClickListener(this);
            textView.setOnClickListener(this);
            this.f6485i = new PopupWindow(inflate, C2801d.a(this, 81.5f), C2801d.a(this, 140.0f));
            this.f6485i.setOutsideTouchable(true);
            this.f6485i.setBackgroundDrawable(new ColorDrawable());
        }
        if (this.f6485i.isShowing()) {
            this.f6485i.dismiss();
        } else {
            this.f6485i.showAsDropDown(this.f6480d);
        }
    }

    /* renamed from: f */
    private void m7703f() {
        if (this.f6485i != null && this.f6485i.isShowing()) {
            this.f6485i.dismiss();
        }
    }

    /* renamed from: g */
    private void m7705g() {
        if (this.f6493q != null) {
            this.f6493q.cancel(true);
        }
        this.f6493q = new PersonalRecordActivity$2(this);
        getAsyncTaskQueue().a(this.f6493q, new Void[0]);
    }

    /* renamed from: a */
    private int m7685a(int i, C3213d c3213d) {
        int i2 = 0;
        this.f6484h.clear();
        if (this.f6483g == null || i == -1) {
            this.f6484h.add(null);
            this.f6484h.add(null);
            this.f6481e.setData(null);
            this.f6481e.invalidate();
            this.f6479c.a();
            this.f6479c.setcurrentitem(0);
            return -1;
        }
        this.f6484h.add(this.f6483g.getPersonalRecordDataDTOs().get(i));
        this.f6484h.add(this.f6483g.getPersonalRecordDataDTOs().get(i));
        this.f6479c.a();
        this.f6479c.setcurrentitem(0);
        ArrayList timeList = this.f6483g.getTimeList();
        ArrayList distanceList = this.f6483g.getDistanceList();
        int size = timeList.size();
        this.f6490n.clear();
        this.f6491o.clear();
        this.f6492p.a(m7689a(this.f6487k, size));
        while (i2 < size) {
            this.f6490n.add(new Entry((float) i2, ((Double) timeList.get(i2)).floatValue()));
            this.f6491o.add(new Entry((float) i2, ((Double) distanceList.get(i2)).floatValue()));
            i2++;
        }
        if (this.f6496t) {
            m7693a(this.f6490n);
        } else {
            m7693a(this.f6491o);
        }
        this.f6481e.a(c3213d);
        return size;
    }

    /* renamed from: a */
    private void m7690a(int i) {
        if (i < this.f6495s.size()) {
            this.f6482f.setText((CharSequence) this.f6495s.get(i));
        }
    }

    /* renamed from: a */
    private ArrayList<String> m7689a(int i, int i2) {
        ArrayList<String> arrayList = new ArrayList();
        this.f6495s.clear();
        Calendar gregorianCalendar;
        int i3;
        int i4;
        int i5;
        switch (i) {
            case 0:
            case 1:
                gregorianCalendar = new GregorianCalendar();
                i3 = gregorianCalendar.get(1);
                this.f6477a.info("Current year: " + i3);
                arrayList.add(String.valueOf(i3));
                if (i != 0) {
                    this.f6495s.add(i3 + getString(C1373R.string.year));
                } else {
                    this.f6495s.add("");
                }
                for (i4 = 0; i4 < i2 - 1; i4++) {
                    gregorianCalendar.set(1, i3 - 1);
                    i3 = gregorianCalendar.get(1);
                    this.f6477a.info("pre year: " + i3);
                    arrayList.add(0, String.valueOf(i3));
                    this.f6495s.add(0, i3 + getString(C1373R.string.year));
                }
                break;
            case 2:
                gregorianCalendar = new GregorianCalendar();
                i3 = gregorianCalendar.get(2);
                String a = m7688a(gregorianCalendar);
                int i6 = gregorianCalendar.get(1);
                this.f6477a.info("Current monthName: " + a + " yearOfMonth: " + i6);
                arrayList.add(a);
                this.f6495s.add(i6 + "." + (i3 + 1));
                for (i4 = 0; i4 < i2 - 1; i4++) {
                    gregorianCalendar.set(2, i3 - 1);
                    i3 = gregorianCalendar.get(2);
                    String a2 = m7688a(gregorianCalendar);
                    i5 = gregorianCalendar.get(1);
                    this.f6477a.info("Pre monthName: " + a2 + " yearOfMonth: " + i5);
                    arrayList.add(0, a2);
                    this.f6495s.add(0, i5 + "." + (i3 + 1));
                }
                break;
            case 3:
                Calendar gregorianCalendar2 = new GregorianCalendar();
                i4 = gregorianCalendar2.get(2);
                Object a3 = m7688a(gregorianCalendar2);
                i5 = gregorianCalendar2.get(5);
                gregorianCalendar2.setFirstDayOfWeek(2);
                gregorianCalendar2.set(7, 2);
                i3 = gregorianCalendar2.get(2);
                int i7 = gregorianCalendar2.get(5);
                if (i3 != i4) {
                    arrayList.add(a3);
                } else {
                    arrayList.add("");
                }
                gregorianCalendar2.set(7, 2);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(i3 + 1);
                stringBuilder.append(".");
                stringBuilder.append(i7);
                stringBuilder.append(HelpFormatter.DEFAULT_OPT_PREFIX);
                stringBuilder.append(i4 + 1);
                stringBuilder.append(".");
                stringBuilder.append(i5);
                this.f6495s.add(stringBuilder.toString());
                this.f6477a.info("Current firstDayOfWeekInMonth: " + i7 + "\ntimeString: " + stringBuilder.toString());
                for (i4 = 1; i4 < i2; i4++) {
                    gregorianCalendar2.set(5, gregorianCalendar2.get(5) - 1);
                    i5 = gregorianCalendar2.get(2);
                    if (i5 != i3) {
                        arrayList.add(0, a3);
                    }
                    a3 = m7688a(gregorianCalendar2);
                    i7 = gregorianCalendar2.get(5);
                    this.f6477a.info("lastDayOfWeekInMonth: " + i7);
                    gregorianCalendar2.set(7, 2);
                    i3 = gregorianCalendar2.get(2);
                    int i8 = gregorianCalendar2.get(5);
                    this.f6477a.info("Pre firstDayOfWeekInMonth: " + i8);
                    if (i3 != i5) {
                        arrayList.add(0, a3);
                    } else {
                        arrayList.add(0, "");
                    }
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(i3 + 1);
                    stringBuilder2.append(".");
                    stringBuilder2.append(i8);
                    stringBuilder2.append(HelpFormatter.DEFAULT_OPT_PREFIX);
                    stringBuilder2.append(i5 + 1);
                    stringBuilder2.append(".");
                    stringBuilder2.append(i7);
                    this.f6495s.add(0, stringBuilder2.toString());
                }
                break;
        }
        return arrayList;
    }

    /* renamed from: a */
    private String m7688a(Calendar calendar) {
        if (this.f6494r) {
            return calendar.getDisplayName(2, 1, Locale.CHINA);
        }
        return calendar.getDisplayName(2, 2, Locale.US);
    }

    /* renamed from: a */
    private void m7693a(ArrayList<Entry> arrayList) {
        float b;
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        if (this.f6496t) {
            b = ((Entry) Collections.max(this.f6490n, this.f6497u)).b();
            if (b <= 2.0f) {
                b = 2.0f;
            }
        } else {
            b = ((Entry) Collections.max(this.f6491o, this.f6497u)).b();
            if (b <= 5.0f) {
                b = 5.0f;
            }
        }
        this.f6481e.getAxisLeft().f(b);
        this.f6481e.u();
        LineDataSet lineDataSet;
        if (this.f6481e.getData() == null || ((C3238l) this.f6481e.getData()).d() <= 0) {
            lineDataSet = new LineDataSet(arrayList, "");
            lineDataSet.c(0);
            lineDataSet.m8251h(-1);
            lineDataSet.m8241b(4.0f);
            lineDataSet.m8244c(true);
            lineDataSet.m8246d(true);
            lineDataSet.m8243c(0.0f);
            lineDataSet.m8252i(-1);
            lineDataSet.e(2.0f);
            lineDataSet.f(1.0f);
            lineDataSet.a(-1);
            lineDataSet.g(true);
            lineDataSet.f(false);
            lineDataSet.b(false);
            lineDataSet.e(true);
            if (C3283i.d() >= 18) {
                lineDataSet.a(ContextCompat.getDrawable(this, C1373R.drawable.bg_fade_red_for_power));
            } else {
                lineDataSet.j(ViewCompat.MEASURED_STATE_MASK);
            }
            List arrayList2 = new ArrayList();
            arrayList2.add(lineDataSet);
            this.f6481e.setData(new C3238l(arrayList2));
            return;
        }
        lineDataSet = (LineDataSet) ((C3238l) this.f6481e.getData()).a(0);
        lineDataSet.b(arrayList);
        lineDataSet.a("");
        ((C3238l) this.f6481e.getData()).b();
        this.f6481e.h();
    }

    /* renamed from: h */
    private void m7706h() {
        if (this.f6489m == null) {
            this.f6489m = new C1802i(this, C1373R.string.loading_msg, true);
        }
        if (!this.f6489m.isShowing()) {
            this.f6489m.show();
        }
    }

    /* renamed from: i */
    private void m7707i() {
        if (this.f6489m != null && this.f6489m.isShowing()) {
            this.f6489m.dismiss();
        }
    }
}
