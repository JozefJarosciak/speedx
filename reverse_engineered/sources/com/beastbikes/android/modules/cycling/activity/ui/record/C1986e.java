package com.beastbikes.android.modules.cycling.activity.ui.record;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.C2024b;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.utils.C2553b;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: RecordShare */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.e */
public class C1986e extends C1964d<ActivityDTO> {
    /* renamed from: a */
    private TextView f8928a;
    /* renamed from: c */
    private TextView f8929c;
    /* renamed from: d */
    private TextView f8930d;
    /* renamed from: e */
    private TextView f8931e;
    /* renamed from: f */
    private TextView f8932f;
    /* renamed from: g */
    private TextView f8933g;
    /* renamed from: h */
    private TextView f8934h;
    /* renamed from: i */
    private TextView f8935i;
    /* renamed from: j */
    private TextView f8936j;
    /* renamed from: k */
    private TextView f8937k;
    /* renamed from: l */
    private TextView f8938l;
    /* renamed from: m */
    private TextView f8939m;
    /* renamed from: n */
    private TextView f8940n;
    /* renamed from: o */
    private TextView f8941o;
    /* renamed from: p */
    private ScrollView f8942p;
    /* renamed from: q */
    private ImageView f8943q;
    /* renamed from: r */
    private ImageView f8944r;
    /* renamed from: s */
    private DecimalFormat f8945s = new DecimalFormat("0.0");
    /* renamed from: t */
    private LinearLayout f8946t;

    /* compiled from: RecordShare */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.e$a */
    public interface C1941a {
        /* renamed from: a */
        void mo3295a(Bitmap bitmap);
    }

    public C1986e(Context context) {
        super(context);
    }

    public int getLayRes() {
        return C1373R.layout.activity_cycling_completed_share_data_view;
    }

    /* renamed from: a */
    public void mo3331a() {
        super.mo3331a();
        Typeface createFromAsset = Typeface.createFromAsset(getContext().getAssets(), "fonts/BebasNeue.otf");
        this.f8930d = (TextView) findViewById(C1373R.id.share_data_elev_max_value);
        this.f8931e = (TextView) findViewById(C1373R.id.share_data_elev_gain_value);
        this.f8932f = (TextView) findViewById(C1373R.id.share_data_elev_distance_value);
        this.f8933g = (TextView) findViewById(C1373R.id.share_data_cal_value);
        this.f8934h = (TextView) findViewById(C1373R.id.share_data_cadence_value);
        this.f8935i = (TextView) findViewById(C1373R.id.share_data_elev_heart_rate_value);
        this.f8936j = (TextView) findViewById(C1373R.id.share_data_elev_max_unit);
        this.f8937k = (TextView) findViewById(C1373R.id.share_data_elev_gain_unit);
        this.f8938l = (TextView) findViewById(C1373R.id.share_data_elev_distance_unit);
        this.f8939m = (TextView) findViewById(C1373R.id.share_data_cal_unit);
        this.f8940n = (TextView) findViewById(C1373R.id.share_data_cadence_unit);
        this.f8941o = (TextView) findViewById(C1373R.id.share_data_elev_heart_rate_unit);
        this.f8930d.setTypeface(createFromAsset);
        this.f8931e.setTypeface(createFromAsset);
        this.f8932f.setTypeface(createFromAsset);
        this.f8933g.setTypeface(createFromAsset);
        this.f8934h.setTypeface(createFromAsset);
        this.f8935i.setTypeface(createFromAsset);
        this.f8928a = (TextView) findViewById(C1373R.id.share_data_view_date);
        this.f8929c = (TextView) findViewById(C1373R.id.share_data_view_time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        this.f8929c.setText(simpleDateFormat.format(date));
        this.f8928a.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date));
        this.f8943q = (ImageView) findViewById(C1373R.id.share_data_content_map);
        this.f8944r = (ImageView) findViewById(C1373R.id.share_data_content_summary);
        this.f8942p = (ScrollView) findViewById(C1373R.id.cycling_completed_share_data_view);
        this.f8946t = (LinearLayout) findViewById(C1373R.id.share_data_chart_view);
    }

    /* renamed from: a */
    public void m10227a(Bitmap bitmap, Bitmap bitmap2, ArrayList<C2024b> arrayList, C1941a c1941a) {
        this.f8943q.setImageBitmap(bitmap2);
        this.f8944r.setImageBitmap(bitmap);
        this.f8946t.removeAllViews();
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C2024b c2024b = (C2024b) it.next();
            if (c2024b.mo3360e()) {
                if (c2024b.getParent() != null) {
                    ViewGroup viewGroup = (ViewGroup) c2024b.getParent();
                    viewGroup.removeView(c2024b);
                    arrayList2.add(viewGroup);
                    arrayList3.add(c2024b);
                }
                this.f8946t.addView(c2024b);
            }
        }
        final C1941a c1941a2 = c1941a;
        final Bitmap bitmap3 = bitmap2;
        final Bitmap bitmap4 = bitmap;
        this.f8942p.postDelayed(new Runnable(this) {
            /* renamed from: f */
            final /* synthetic */ C1986e f8912f;

            public void run() {
                c1941a2.mo3295a(C2553b.m12780a(this.f8912f.f8942p));
                this.f8912f.f8943q.setImageBitmap(null);
                this.f8912f.f8944r.setImageBitmap(null);
                this.f8912f.f8946t.removeAllViews();
                for (int i = 0; i < arrayList2.size(); i++) {
                    ((ViewGroup) arrayList2.get(i)).addView((View) arrayList3.get(i));
                }
                if (!(bitmap3 == null || bitmap3.isRecycled())) {
                    bitmap3.recycle();
                }
                if (!(bitmap4 == null || bitmap4.isRecycled())) {
                    bitmap4.recycle();
                }
                System.gc();
            }
        }, 10);
    }
}
