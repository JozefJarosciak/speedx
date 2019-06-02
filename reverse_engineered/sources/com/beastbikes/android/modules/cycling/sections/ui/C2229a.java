package com.beastbikes.android.modules.cycling.sections.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.sections.dto.C2224e;
import com.beastbikes.android.widget.C2638d.C2077a;
import com.beastbikes.android.widget.p081b.C2534b;
import java.math.BigDecimal;
import org.apache.commons.cli.HelpFormatter;

/* compiled from: FavorSegmentRecyclerViewAdapter */
/* renamed from: com.beastbikes.android.modules.cycling.sections.ui.a */
public class C2229a extends C2077a {
    /* renamed from: a */
    private Context f10607a;
    /* renamed from: b */
    private C2534b f10608b;
    /* renamed from: c */
    private boolean f10609c;

    /* compiled from: FavorSegmentRecyclerViewAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.sections.ui.a$a */
    class C2228a extends ViewHolder {
        /* renamed from: a */
        public final View f10602a;
        /* renamed from: b */
        final /* synthetic */ C2229a f10603b;
        /* renamed from: c */
        private TextView f10604c;
        /* renamed from: d */
        private TextView f10605d;
        /* renamed from: e */
        private TextView f10606e;

        public C2228a(C2229a c2229a, View view) {
            this.f10603b = c2229a;
            super(view);
            this.f10602a = view;
            this.f10604c = (TextView) view.findViewById(C1373R.id.item_favor_segment_rank_tv);
            this.f10605d = (TextView) view.findViewById(C1373R.id.item_favor_segment_name_tv);
            this.f10606e = (TextView) view.findViewById(C1373R.id.item_favor_segment_detail_tv);
        }
    }

    public C2229a(Context context, C2534b c2534b) {
        this.f10607a = context;
        this.f10608b = c2534b;
        this.f10609c = C1849a.m9645b(context);
    }

    /* renamed from: a */
    public ViewHolder mo3371a() {
        return new C2228a(this, LayoutInflater.from(this.f10607a).inflate(C1373R.layout.item_favor_segment, null, false));
    }

    /* renamed from: a */
    public void mo3372a(ViewHolder viewHolder, Object obj, final int i, boolean z) {
        if (viewHolder instanceof C2228a) {
            String str;
            final C2228a c2228a = (C2228a) viewHolder;
            C2224e c2224e = (C2224e) obj;
            c2228a.f10605d.setText(c2224e.m11438f());
            int d = c2224e.m11436d();
            if (d > 0 && d <= 3) {
                c2228a.f10604c.setText(d + "");
                c2228a.f10604c.setBackgroundResource(C1373R.drawable.ic_favor_segment_rank1);
            } else if (d > 3 && d < 100) {
                c2228a.f10604c.setText(d + "");
                c2228a.f10604c.setBackgroundResource(C1373R.drawable.ic_favor_segment_rank2);
            } else if (d >= 100) {
                c2228a.f10604c.setText("");
                c2228a.f10604c.setBackgroundResource(C1373R.drawable.ic_favor_segment_rank3);
            } else {
                c2228a.f10604c.setText("");
                c2228a.f10604c.setBackgroundResource(0);
            }
            long c = c2224e.m11435c();
            int i2 = ((int) c) / 3600;
            String str2 = "";
            if (i2 == 0) {
                str2 = "00";
            } else if (i2 < 10) {
                str2 = "0" + i2;
            } else {
                str2 = "" + i2;
            }
            int i3 = ((int) (c - ((long) (i2 * 3600)))) / 60;
            String str3 = "";
            if (i3 == 0) {
                str3 = "00";
            } else if (i3 < 10) {
                str3 = "0" + i3;
            } else {
                str3 = "" + i3;
            }
            int i4 = (((int) c) - (i2 * 3600)) - (i3 * 60);
            String str4 = "";
            if (i4 == 0) {
                str = "00";
            } else if (i4 < 10) {
                str = "0" + i4;
            } else {
                str = "" + i4;
            }
            str3 = str2 + ":" + str3 + ":" + str;
            double b;
            if (this.f10609c) {
                b = c2224e.m11434b() / 1000.0d;
                str2 = "";
                if (b < 10.0d) {
                    str2 = new BigDecimal(b).setScale(1, 4) + "";
                } else {
                    str2 = ((int) b) + "";
                }
                b = c2224e.m11437e();
                if (b < 10.0d) {
                    c2228a.f10606e.setText(str2 + this.f10607a.getResources().getString(C1373R.string.str_mileage_unit_km) + HelpFormatter.DEFAULT_OPT_PREFIX + str3 + HelpFormatter.DEFAULT_OPT_PREFIX + new BigDecimal(b).setScale(1, 4) + this.f10607a.getResources().getString(C1373R.string.label_speed_per_hour));
                } else {
                    c2228a.f10606e.setText(str2 + this.f10607a.getResources().getString(C1373R.string.str_mileage_unit_km) + HelpFormatter.DEFAULT_OPT_PREFIX + str3 + HelpFormatter.DEFAULT_OPT_PREFIX + ((int) b) + this.f10607a.getResources().getString(C1373R.string.label_speed_per_hour));
                }
            } else {
                b = C1849a.m9638a(c2224e.m11434b()) / 1000.0d;
                str2 = "";
                if (b < 10.0d) {
                    str2 = new BigDecimal(b).setScale(1, 4) + "";
                } else {
                    str2 = ((int) b) + "";
                }
                new BigDecimal(b).setScale(1, 4);
                b = C1849a.m9648d(c2224e.m11437e());
                if (b < 10.0d) {
                    c2228a.f10606e.setText(str2 + this.f10607a.getResources().getString(C1373R.string.str_mileage_unit_mile) + HelpFormatter.DEFAULT_OPT_PREFIX + str3 + HelpFormatter.DEFAULT_OPT_PREFIX + new BigDecimal(b).setScale(1, 4) + "MPH");
                } else {
                    c2228a.f10606e.setText(str2 + this.f10607a.getResources().getString(C1373R.string.str_mileage_unit_mile) + HelpFormatter.DEFAULT_OPT_PREFIX + str3 + HelpFormatter.DEFAULT_OPT_PREFIX + ((int) b) + "MPH");
                }
            }
            c2228a.f10602a.setOnClickListener(new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ C2229a f10601c;

                public void onClick(View view) {
                    this.f10601c.f10608b.mo3520a(c2228a, i);
                }
            });
        }
    }
}
