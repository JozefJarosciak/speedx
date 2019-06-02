package com.beastbikes.android.modules.cycling.activity.ui.record;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.ui.record.p119h.C2001b;
import com.beastbikes.android.modules.cycling.activity.ui.record.p120a.C1966b;
import com.beastbikes.android.modules.cycling.activity.ui.record.p122b.C1976h;
import com.beastbikes.android.modules.cycling.activity.ui.record.p126g.C2000b;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.TrainStatisticsChartView;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.dto.C2411a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p121c.C1968d;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903120)
public class CyclingReportTrainStatisticsActivity extends SessionFragmentActivity implements OnClickListener, C2001b {
    /* renamed from: a */
    private static final Logger f4783a = LoggerFactory.getLogger("CyclingReportTrainStatisticsActivity");
    @C1458a(a = 2131755684)
    /* renamed from: b */
    private ImageView f4784b;
    @C1458a(a = 2131755685)
    /* renamed from: c */
    private VerticalViewPager f4785c;
    /* renamed from: d */
    private ArrayList<TrainStatisticsChartView> f4786d;
    /* renamed from: e */
    private C1966b f4787e;
    /* renamed from: f */
    private C1977b f4788f;
    /* renamed from: g */
    private int f4789g = 0;
    /* renamed from: h */
    private C1802i f4790h;
    /* renamed from: i */
    private ArrayList<C2411a> f4791i;
    /* renamed from: j */
    private ArrayList<Entry> f4792j;
    /* renamed from: k */
    private ArrayList<Entry> f4793k;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.f4789g = intent.getIntExtra("train_course_id", -1);
        this.f4788f = C1977b.a();
        ArrayList d = this.f4788f.d();
        ActivityDTO c = this.f4788f.c();
        if (c == null || d == null) {
            finish();
            return;
        }
        this.f4791i = new ArrayList();
        int courseTime = (int) (((((double) c.getCourseTime()) * 1.0d) / c.getElapsedTime()) * ((double) d.size()));
        f4783a.info("courseTime: " + c.getCourseTime() + "  elapsedTime: " + c.getElapsedTime() + " sample size: " + d.size() + " sampleCount: " + courseTime);
        for (int i = 0; i < courseTime; i++) {
            this.f4791i.add(d.get(i));
        }
        m6105d();
        C2000b c2000b = new C2000b(this);
        c2000b.b();
        c2000b.c();
        c2000b.a();
    }

    /* renamed from: d */
    private void m6105d() {
        this.f4784b.setOnClickListener(this);
        this.f4786d = new ArrayList();
        LayoutInflater from = LayoutInflater.from(this);
        TrainStatisticsChartView trainStatisticsChartView = (TrainStatisticsChartView) from.inflate(C1373R.layout.layout_train_statistics_power, null);
        TrainStatisticsChartView trainStatisticsChartView2 = (TrainStatisticsChartView) from.inflate(C1373R.layout.layout_train_statistics_cadence, null);
        this.f4786d.add(trainStatisticsChartView);
        this.f4786d.add(trainStatisticsChartView2);
        ActivityDTO c = this.f4788f.c();
        if (c != null) {
            C1968d c1976h = new C1976h((double) c.getCourseTime());
            trainStatisticsChartView.setXValueFormatter(c1976h);
            trainStatisticsChartView2.setXValueFormatter(c1976h);
        }
        this.f4787e = new C1966b(this.f4786d);
        this.f4785c.setAdapter(this.f4787e);
    }

    /* renamed from: e */
    private void m6106e() {
        if (this.f4790h == null) {
            this.f4790h = new C1802i(this, getString(C1373R.string.str_loading), false);
        }
        if (!this.f4790h.isShowing()) {
            this.f4790h.show();
        }
    }

    /* renamed from: f */
    private void m6107f() {
        if (this.f4790h != null && this.f4790h.isShowing()) {
            this.f4790h.dismiss();
        }
    }

    /* renamed from: a */
    public CyclingReportTrainStatisticsActivity m6108a() {
        return this;
    }

    /* renamed from: b */
    public int m6112b() {
        return this.f4789g;
    }

    /* renamed from: a */
    public void m6109a(TrainCourseDTO trainCourseDTO) {
        ((TrainStatisticsChartView) this.f4786d.get(0)).a(trainCourseDTO, this.f4792j, true);
        ((TrainStatisticsChartView) this.f4786d.get(1)).a(trainCourseDTO, this.f4793k, false);
        this.f4787e.notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m6110a(String str) {
    }

    /* renamed from: c */
    public ArrayList<C2411a> m6115c() {
        return this.f4791i;
    }

    /* renamed from: a */
    public void m6111a(ArrayList<Entry> arrayList) {
        this.f4792j = arrayList;
    }

    /* renamed from: b */
    public void m6114b(ArrayList<Entry> arrayList) {
        this.f4793k = arrayList;
    }

    /* renamed from: b */
    public void m6113b(String str) {
        m6106e();
    }

    /* renamed from: c */
    public void m6116c(String str) {
        m6107f();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.img_close:
                finish();
                return;
            default:
                return;
        }
    }
}
