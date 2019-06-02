package com.beastbikes.android.modules.user.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.dto.HistogramDTO;
import com.beastbikes.android.modules.user.dto.HistogramDTO.C2410a;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.utils.C2562j;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p088g.C2801d;
import java.util.ArrayList;
import java.util.List;

/* compiled from: HistogramView */
/* renamed from: com.beastbikes.android.modules.user.widget.a */
public class C2535a extends FrameLayout implements OnClickListener, C2534b {
    /* renamed from: a */
    private TextView f11967a;
    /* renamed from: b */
    private TextView f11968b;
    /* renamed from: c */
    private RecyclerView f11969c;
    /* renamed from: d */
    private ViewGroup f11970d;
    /* renamed from: e */
    private View f11971e;
    /* renamed from: f */
    private TextView f11972f;
    /* renamed from: g */
    private HistogramDTO f11973g;
    /* renamed from: h */
    private List<C2410a> f11974h = new ArrayList();
    /* renamed from: i */
    private C2532a f11975i;
    /* renamed from: j */
    private LinearLayoutManager f11976j;
    /* renamed from: k */
    private int f11977k;
    /* renamed from: l */
    private int f11978l;
    /* renamed from: m */
    private String f11979m;
    /* renamed from: n */
    private int f11980n;

    /* compiled from: HistogramView */
    /* renamed from: com.beastbikes.android.modules.user.widget.a$1 */
    class C25301 extends OnScrollListener {
        /* renamed from: a */
        final /* synthetic */ C2535a f11954a;

        C25301(C2535a c2535a) {
            this.f11954a = c2535a;
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            int findLastVisibleItemPosition = this.f11954a.f11976j.findLastVisibleItemPosition();
            if (this.f11954a.f11975i != null && this.f11954a.f11980n != findLastVisibleItemPosition) {
                this.f11954a.f11980n = findLastVisibleItemPosition;
                for (int i3 = 0; i3 < this.f11954a.f11974h.size(); i3++) {
                    C2410a c2410a = (C2410a) this.f11954a.f11974h.get(i3);
                    if (i3 == findLastVisibleItemPosition) {
                        c2410a.m12218a(true);
                    } else {
                        c2410a.m12218a(false);
                    }
                }
                this.f11954a.f11975i.notifyDataSetChanged();
            }
        }
    }

    /* compiled from: HistogramView */
    /* renamed from: com.beastbikes.android.modules.user.widget.a$a */
    private class C2532a extends Adapter<C2533b> {
        /* renamed from: a */
        final /* synthetic */ C2535a f11958a;
        /* renamed from: b */
        private final C2534b f11959b;
        /* renamed from: c */
        private final List<C2410a> f11960c;

        public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
            m12705a((C2533b) viewHolder, i);
        }

        public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return m12704a(viewGroup, i);
        }

        public C2532a(C2535a c2535a, List<C2410a> list, C2534b c2534b) {
            this.f11958a = c2535a;
            this.f11960c = list;
            this.f11959b = c2534b;
        }

        /* renamed from: a */
        public C2533b m12704a(ViewGroup viewGroup, int i) {
            return new C2533b(this.f11958a, LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.histogram_view_item, viewGroup, false));
        }

        /* renamed from: a */
        public void m12705a(final C2533b c2533b, final int i) {
            if (this.f11960c != null && this.f11960c.size() >= 0) {
                C2410a c2410a = (C2410a) this.f11960c.get(i);
                if (c2410a != null) {
                    LayoutParams layoutParams = (LayoutParams) c2533b.f11962b.getLayoutParams();
                    layoutParams.width = this.f11958a.f11977k / 10;
                    c2533b.f11962b.setLayoutParams(layoutParams);
                    c2533b.f11963c.setText(C2555d.m12797a(this.f11958a.getContext(), c2410a.m12217a()));
                    c2533b.f11964d.setText(C2555d.m12803b(this.f11958a.getContext(), c2410a.m12217a()));
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) c2533b.f11965e.getLayoutParams();
                    layoutParams2.height = (int) (c2410a.m12219b() / ((double) this.f11958a.f11978l));
                    c2533b.f11965e.setLayoutParams(layoutParams2);
                    if (c2410a.m12220c()) {
                        c2533b.f11965e.setBackgroundColor(Color.parseColor("#444444"));
                        this.f11958a.f11972f.setText(String.format("%.1f", new Object[]{Double.valueOf(c2410a.m12219b() / 1000.0d)}));
                    } else {
                        c2533b.f11965e.setBackgroundColor(Color.parseColor("#222222"));
                    }
                    c2533b.f11966f.setOnClickListener(new OnClickListener(this) {
                        /* renamed from: c */
                        final /* synthetic */ C2532a f11957c;

                        public void onClick(View view) {
                            this.f11957c.f11959b.mo3520a(c2533b, i);
                        }
                    });
                }
            }
        }

        public int getItemCount() {
            return this.f11960c.size();
        }
    }

    /* compiled from: HistogramView */
    /* renamed from: com.beastbikes.android.modules.user.widget.a$b */
    private class C2533b extends ViewHolder {
        /* renamed from: a */
        final /* synthetic */ C2535a f11961a;
        /* renamed from: b */
        private View f11962b;
        /* renamed from: c */
        private TextView f11963c;
        /* renamed from: d */
        private TextView f11964d;
        /* renamed from: e */
        private TextView f11965e;
        /* renamed from: f */
        private RelativeLayout f11966f;

        public C2533b(C2535a c2535a, View view) {
            this.f11961a = c2535a;
            super(view);
            this.f11962b = view;
            this.f11963c = (TextView) view.findViewById(C1373R.id.histogram_view_item_date_tv);
            this.f11964d = (TextView) view.findViewById(C1373R.id.histogram_view_item_week_tv);
            this.f11965e = (TextView) view.findViewById(C1373R.id.histogram_view_item_histogram_view);
            this.f11966f = (RelativeLayout) view.findViewById(C1373R.id.histogram_view_item_histogram);
        }
    }

    public C2535a(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(C1373R.layout.histogram_view, this);
        m12715a();
    }

    public C2535a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C2535a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.histogram_view_title_tv:
                if (!TextUtils.isEmpty(this.f11979m)) {
                    C2562j.m12867a(getContext(), this.f11979m);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void mo3520a(ViewHolder viewHolder, int i) {
        for (int i2 = 0; i2 < this.f11974h.size(); i2++) {
            C2410a c2410a = (C2410a) this.f11974h.get(i2);
            if (c2410a != null) {
                if (i == i2) {
                    c2410a.m12218a(true);
                } else {
                    c2410a.m12218a(false);
                }
            }
        }
        this.f11975i.notifyDataSetChanged();
    }

    /* renamed from: b */
    public void mo3521b(ViewHolder viewHolder, int i) {
    }

    /* renamed from: a */
    private void m12715a() {
        this.f11967a = (TextView) findViewById(C1373R.id.histogram_view_title_tv);
        this.f11968b = (TextView) findViewById(C1373R.id.histogram_view_rank_tv);
        this.f11970d = (ViewGroup) findViewById(C1373R.id.histogram_view_distance_view);
        this.f11971e = findViewById(C1373R.id.histogram_view_point_view);
        this.f11972f = (TextView) findViewById(C1373R.id.histogram_view_distance_tv);
        this.f11969c = (RecyclerView) findViewById(C1373R.id.histogram_recycler_view);
        this.f11967a.setOnClickListener(this);
        this.f11969c.setHasFixedSize(true);
        this.f11976j = new LinearLayoutManager(getContext());
        this.f11976j.setOrientation(0);
        this.f11969c.setLayoutManager(this.f11976j);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.f11977k = displayMetrics.widthPixels;
        this.f11969c.setOnScrollListener(new C25301(this));
    }

    public void setTitle(int i) {
        this.f11967a.setText(i);
    }

    public void setTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f11967a.setText(C1373R.string.profile_club_empty);
        } else {
            this.f11967a.setText(str);
        }
    }

    public void setClubId(String str) {
        this.f11979m = str;
    }

    public void setHistogramDTO(HistogramDTO histogramDTO) {
        if (histogramDTO != null) {
            this.f11973g = histogramDTO;
            this.f11974h = this.f11973g.getItems();
            this.f11975i = new C2532a(this, this.f11974h, this);
            this.f11969c.setAdapter(this.f11975i);
            this.f11980n = this.f11974h.size() - 1;
            this.f11969c.scrollToPosition(this.f11980n);
            if (histogramDTO.getMax() <= 0.0d) {
                this.f11970d.setVisibility(8);
                this.f11971e.setVisibility(8);
            } else {
                this.f11970d.setVisibility(0);
                this.f11971e.setVisibility(0);
            }
            if (histogramDTO.getMonthRank() <= 0) {
                this.f11968b.setText("－－");
            } else {
                this.f11968b.setText(String.valueOf(histogramDTO.getMonthRank()));
            }
            this.f11978l = (int) (histogramDTO.getMax() / ((double) C2801d.m13756a(getContext(), 100.0f)));
        }
    }

    public void setRank(int i) {
        this.f11968b.setText(String.valueOf(i));
    }
}
