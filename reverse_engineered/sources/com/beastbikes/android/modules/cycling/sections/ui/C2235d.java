package com.beastbikes.android.modules.cycling.sections.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.RatingBar;
import android.widget.TextView;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.sections.dto.C2222c;
import com.beastbikes.android.utils.C2579v;
import com.beastbikes.android.widget.C2638d.C2077a;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.math.BigDecimal;

/* compiled from: SectionRecyclerViewAdapter */
/* renamed from: com.beastbikes.android.modules.cycling.sections.ui.d */
public class C2235d extends C2077a {
    /* renamed from: a */
    private Context f10659a;
    /* renamed from: b */
    private C2534b f10660b;
    /* renamed from: c */
    private boolean f10661c;

    /* compiled from: SectionRecyclerViewAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.sections.ui.d$a */
    class C2234a extends ViewHolder {
        /* renamed from: a */
        public final View f10648a;
        /* renamed from: b */
        final /* synthetic */ C2235d f10649b;
        /* renamed from: c */
        private CircleImageView f10650c;
        /* renamed from: d */
        private TextView f10651d;
        /* renamed from: e */
        private TextView f10652e;
        /* renamed from: f */
        private TextView f10653f;
        /* renamed from: g */
        private RatingBar f10654g;
        /* renamed from: h */
        private TextView f10655h;
        /* renamed from: i */
        private TextView f10656i;
        /* renamed from: j */
        private TextView f10657j;
        /* renamed from: k */
        private View f10658k;

        public C2234a(C2235d c2235d, View view) {
            this.f10649b = c2235d;
            super(view);
            this.f10648a = view;
            this.f10650c = (CircleImageView) view.findViewById(C1373R.id.item_competition_section_avater);
            this.f10651d = (TextView) view.findViewById(C1373R.id.item_competition_section_title);
            this.f10652e = (TextView) view.findViewById(C1373R.id.item_competition_section_diatance);
            this.f10653f = (TextView) view.findViewById(C1373R.id.item_competition_section_owner);
            this.f10654g = (RatingBar) view.findViewById(C1373R.id.section_ratingbar);
            this.f10655h = (TextView) view.findViewById(C1373R.id.section_elevation);
            this.f10656i = (TextView) view.findViewById(C1373R.id.item_competition_section_total_distance);
            this.f10657j = (TextView) view.findViewById(C1373R.id.item_competition_section_total_distance_unit);
            this.f10658k = view.findViewById(C1373R.id.item_competition_section_diver);
        }
    }

    public C2235d(Context context, C2534b c2534b) {
        this.f10659a = context;
        this.f10660b = c2534b;
        this.f10661c = C1849a.m9645b(context);
    }

    /* renamed from: a */
    public ViewHolder mo3371a() {
        return new C2234a(this, LayoutInflater.from(this.f10659a).inflate(C1373R.layout.item_competition_section, null, false));
    }

    /* renamed from: a */
    public void mo3372a(ViewHolder viewHolder, Object obj, final int i, boolean z) {
        if (viewHolder instanceof C2234a) {
            final C2234a c2234a = (C2234a) viewHolder;
            C2222c c2222c = (C2222c) obj;
            if (TextUtils.isEmpty(c2222c.m11428k())) {
                Picasso.with(this.f10659a).load(C1373R.drawable.ic_launch_logo).fit().placeholder(C1373R.drawable.ic_launch_logo).error(C1373R.drawable.ic_launch_logo).centerCrop().into(c2234a.f10650c);
            } else {
                Picasso.with(this.f10659a).load(c2222c.m11428k()).fit().placeholder(C1373R.drawable.ic_launch_logo).error(C1373R.drawable.ic_launch_logo).centerCrop().into(c2234a.f10650c);
            }
            c2234a.f10651d.setText(c2222c.m11422e());
            if (TextUtils.isEmpty(c2222c.m11424g())) {
                c2234a.f10653f.setText(this.f10659a.getResources().getString(C1373R.string.section_no_lord));
            } else {
                c2234a.f10653f.setText(c2222c.m11424g() + this.f10659a.getResources().getString(C1373R.string.occupy));
            }
            c2234a.f10654g.setRating((float) c2222c.m11427j());
            double c;
            if (this.f10661c) {
                c2234a.f10652e.setText(this.f10659a.getResources().getString(C1373R.string.distance_less_than) + C2579v.m12904a(c2222c.m11419b() / 1000.0d) + this.f10659a.getResources().getString(C1373R.string.str_mileage_unit_km));
                c2234a.f10655h.setText(this.f10659a.getResources().getString(C1373R.string.str_elevation_diff) + " " + ((int) c2222c.m11423f()) + ANSIConstants.ESC_END);
                c = c2222c.m11420c() / 1000.0d;
                if (c < 10.0d) {
                    c2234a.f10656i.setText(new BigDecimal(c).setScale(1, 4) + "");
                } else {
                    c2234a.f10656i.setText(((int) c) + "");
                }
                c2234a.f10657j.setText(this.f10659a.getResources().getString(C1373R.string.kilometre));
            } else {
                c2234a.f10652e.setText(this.f10659a.getResources().getString(C1373R.string.distance_less_than) + C2579v.m12904a(C1849a.m9638a(c2222c.m11419b() / 1000.0d)) + this.f10659a.getResources().getString(C1373R.string.str_mileage_unit_mile));
                c2234a.f10655h.setText(this.f10659a.getResources().getString(C1373R.string.str_elevation_diff) + " " + ((int) C1849a.m9646c(c2222c.m11423f())) + "feet");
                c = C1849a.m9638a(c2222c.m11420c() / 1000.0d);
                if (c < 10.0d) {
                    c2234a.f10656i.setText(new BigDecimal(c).setScale(1, 4) + "");
                } else {
                    c2234a.f10656i.setText(((int) c) + "");
                }
                c2234a.f10657j.setText(this.f10659a.getResources().getString(C1373R.string.miles));
            }
            if (z) {
                c2234a.f10658k.setVisibility(8);
            } else {
                c2234a.f10658k.setVisibility(0);
            }
            c2234a.f10648a.setOnClickListener(new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ C2235d f10644c;

                public void onClick(View view) {
                    this.f10644c.f10660b.mo3520a(c2234a, i);
                }
            });
            c2234a.f10648a.setOnLongClickListener(new OnLongClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ C2235d f10647c;

                public boolean onLongClick(View view) {
                    this.f10647c.f10660b.mo3521b(c2234a, i);
                    return true;
                }
            });
        }
    }
}
