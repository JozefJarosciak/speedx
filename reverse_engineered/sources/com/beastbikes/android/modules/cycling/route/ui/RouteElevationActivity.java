package com.beastbikes.android.modules.cycling.route.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.toolbox.JsonObjectRequest;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.user.ui.C2496a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903661)
public class RouteElevationActivity extends SessionFragmentActivity implements OnClickListener {
    /* renamed from: a */
    private static final Logger f5485a = LoggerFactory.getLogger(RouteElevationActivity.class);
    @C1458a(a = 2131757507)
    /* renamed from: b */
    private LinearLayout f5486b;
    @C1458a(a = 2131757506)
    /* renamed from: c */
    private TextView f5487c;
    @C1458a(a = 2131757509)
    /* renamed from: d */
    private TextView f5488d;
    /* renamed from: e */
    private ArrayList<Double> f5489e;
    /* renamed from: f */
    private C2496a f5490f;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        this.f5490f = new C2496a(this);
        this.f5490f.setMessage(getString(C1373R.string.str_loading));
        this.f5489e = new ArrayList();
        this.f5487c.setOnClickListener(this);
        m6799b();
    }

    /* renamed from: b */
    private void m6799b() {
        this.f5490f.show();
        this.f5490f.setCancelable(false);
        StringBuilder stringBuilder = new StringBuilder("http://maps.google.cn/maps/api/elevation/json?path=");
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("nodes")) {
            stringBuilder.append(intent.getStringExtra("nodes")).append("&samples=200");
            this.f5488d.setText(String.valueOf((int) intent.getDoubleExtra("distance", 10.0d)));
            f5485a.error("the elevation request url is : " + stringBuilder.toString());
        }
        getRequestQueueFactory().a(new JsonObjectRequest(stringBuilder.toString(), null, new RouteElevationActivity$1(this, this), new RouteElevationActivity$2(this, this)), this);
    }

    /* renamed from: a */
    private double m6793a(List<Double> list) {
        try {
            int size = list.size();
            if (size < 1) {
                return 0.0d;
            }
            double doubleValue = ((Double) list.get(0)).doubleValue();
            int i = 0;
            while (i < size) {
                double doubleValue2 = ((Double) list.get(i)).doubleValue();
                if (doubleValue2 <= doubleValue) {
                    doubleValue2 = doubleValue;
                }
                i++;
                doubleValue = doubleValue2;
            }
            return doubleValue;
        } catch (Throwable e) {
            f5485a.error("get the max elevation error", e);
            return 50.0d;
        }
    }

    /* renamed from: b */
    private double m6797b(List<Double> list) {
        try {
            int size = list.size();
            if (size < 1) {
                return 0.0d;
            }
            double doubleValue = ((Double) list.get(0)).doubleValue();
            int i = 0;
            while (i < size) {
                double doubleValue2 = ((Double) list.get(i)).doubleValue();
                if (doubleValue <= doubleValue2) {
                    doubleValue2 = doubleValue;
                }
                i++;
                doubleValue = doubleValue2;
            }
            return doubleValue;
        } catch (Throwable e) {
            f5485a.error("get the min elevation error", e);
            return 0.0d;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.route_map_elevation_back:
                finish();
                return;
            default:
                return;
        }
    }
}
