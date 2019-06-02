package com.beastbikes.android.modules.cycling.stage.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.utils.DistanceUtil;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.stage.dto.C2263b;
import com.beastbikes.android.modules.cycling.stage.dto.StageDTO;
import com.beastbikes.android.modules.cycling.stage.dto.StagePointDTO;
import com.beastbikes.android.modules.cycling.stage.dto.StageRankDTO;
import com.beastbikes.android.modules.cycling.stage.p135c.C2251c;
import com.beastbikes.android.modules.cycling.stage.p137d.C2259c;
import com.beastbikes.android.modules.cycling.stage.ui.C2280c.C2279b;
import com.beastbikes.android.modules.map.C2296d.C1686a;
import com.beastbikes.android.modules.map.C2296d.C1958b;
import com.beastbikes.android.modules.map.C2296d.C2306c;
import com.beastbikes.android.modules.map.C2296d.C2307d;
import com.beastbikes.android.modules.map.MapType;
import com.beastbikes.android.modules.map.SpeedxMap;
import com.beastbikes.android.modules.map.SpeedxMap.C1685b;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.utils.C2558g;
import com.beastbikes.android.widget.slidingup_pannel.SlidingUpPanelLayout;
import com.beastbikes.android.widget.slidingup_pannel.SlidingUpPanelLayout.C2736c;
import com.beastbikes.android.widget.slidingup_pannel.SlidingUpPanelLayout.PanelState;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903185)
public class StageMapListBaseActivity extends SessionFragmentActivity implements OnScrollChangeListener, OnClickListener, C2259c, C2279b, C1685b, C1686a, C1958b, C2306c, C2307d, C2736c {
    /* renamed from: b */
    private static final Logger f5791b = LoggerFactory.getLogger("StageMapListBaseActivity");
    @C1458a(a = 2131755986)
    /* renamed from: A */
    private TextView f5792A;
    @C1458a(a = 2131757053)
    /* renamed from: B */
    private RelativeLayout f5793B;
    @C1458a(a = 2131757050)
    /* renamed from: C */
    private RelativeLayout f5794C;
    @C1458a(a = 2131757102)
    /* renamed from: D */
    private LinearLayout f5795D;
    @C1458a(a = 2131757103)
    /* renamed from: E */
    private TextView f5796E;
    @C1458a(a = 2131757104)
    /* renamed from: F */
    private ImageView f5797F;
    @C1458a(a = 2131757106)
    /* renamed from: G */
    private TextView f5798G;
    @C1458a(a = 2131757105)
    /* renamed from: H */
    private TextView f5799H;
    @C1458a(a = 2131757107)
    /* renamed from: I */
    private TextView f5800I;
    @C1458a(a = 2131755992)
    /* renamed from: J */
    private LinearLayout f5801J;
    /* renamed from: K */
    private Animation f5802K;
    /* renamed from: L */
    private Animation f5803L;
    /* renamed from: M */
    private Animation f5804M;
    /* renamed from: N */
    private Menu f5805N;
    @C1458a(a = 2131755991)
    /* renamed from: O */
    private LinearLayout f5806O;
    @C1458a(a = 2131757051)
    /* renamed from: P */
    private RecyclerView f5807P;
    /* renamed from: Q */
    private C2280c f5808Q;
    @C1458a(a = 2131757052)
    /* renamed from: R */
    private TextView f5809R;
    /* renamed from: S */
    private PanelState f5810S;
    /* renamed from: T */
    private ArrayList<StageDTO> f5811T = new ArrayList();
    /* renamed from: U */
    private int f5812U;
    /* renamed from: V */
    private int f5813V;
    /* renamed from: W */
    private int f5814W;
    /* renamed from: X */
    private int f5815X;
    /* renamed from: Y */
    private int f5816Y;
    /* renamed from: Z */
    private C2251c f5817Z;
    @C1458a(a = 2131755983)
    /* renamed from: a */
    protected SpeedxMap f5818a;
    private double aa;
    private double ab;
    private ArrayList ac = new ArrayList();
    private ArrayList ad = new ArrayList();
    private Object ae;
    private Object af;
    private Object ag;
    private Object ah;
    private int ai;
    private Canvas aj;
    private Canvas ak;
    private Paint al;
    private Bitmap am;
    private Bitmap an;
    private Rect ao = new Rect();
    private int ap;
    private int aq;
    private int ar;
    private DecimalFormat as;
    private int at;
    private StageDTO au;
    private int av;
    private boolean aw;
    private double ax;
    private C1802i ay;
    private boolean az = true;
    @C1458a(a = 2131755786)
    /* renamed from: c */
    private Toolbar f5819c;
    @C1458a(a = 2131755980)
    /* renamed from: d */
    private SlidingUpPanelLayout f5820d;
    @C1458a(a = 2131755982)
    /* renamed from: e */
    private NestedScrollView f5821e;
    @C1458a(a = 2131757036)
    /* renamed from: f */
    private LinearLayout f5822f;
    @C1458a(a = 2131757035)
    /* renamed from: g */
    private LinearLayout f5823g;
    @C1458a(a = 2131755984)
    /* renamed from: h */
    private RelativeLayout f5824h;
    @C1458a(a = 2131757037)
    /* renamed from: i */
    private ImageView f5825i;
    @C1458a(a = 2131757031)
    /* renamed from: j */
    private RelativeLayout f5826j;
    @C1458a(a = 2131757032)
    /* renamed from: k */
    private ImageView f5827k;
    @C1458a(a = 2131757033)
    /* renamed from: l */
    private ImageView f5828l;
    @C1458a(a = 2131755985)
    /* renamed from: m */
    private ImageView f5829m;
    @C1458a(a = 2131755988)
    /* renamed from: n */
    private RelativeLayout f5830n;
    @C1458a(a = 2131755989)
    /* renamed from: o */
    private ImageView f5831o;
    @C1458a(a = 2131757034)
    /* renamed from: p */
    private LinearLayout f5832p;
    @C1458a(a = 2131757038)
    /* renamed from: q */
    private TextView f5833q;
    @C1458a(a = 2131757039)
    /* renamed from: r */
    private TextView f5834r;
    @C1458a(a = 2131757040)
    /* renamed from: s */
    private TextView f5835s;
    @C1458a(a = 2131757041)
    /* renamed from: t */
    private TextView f5836t;
    @C1458a(a = 2131757042)
    /* renamed from: u */
    private TextView f5837u;
    @C1458a(a = 2131757043)
    /* renamed from: v */
    private TextView f5838v;
    @C1458a(a = 2131757044)
    /* renamed from: w */
    private TextView f5839w;
    @C1458a(a = 2131757047)
    /* renamed from: x */
    private TextView f5840x;
    @C1458a(a = 2131757048)
    /* renamed from: y */
    private TextView f5841y;
    @C1458a(a = 2131757049)
    /* renamed from: z */
    private TextView f5842z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m7029a(bundle);
    }

    public void onResume() {
        super.onResume();
        this.f5818a.c();
    }

    public void onPause() {
        super.onPause();
        this.f5818a.d();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.f5818a.b(bundle);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.f5818a.f();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    protected void onDestroy() {
        this.f5818a.e();
        if (!(this.am == null || this.am.isRecycled())) {
            this.am.recycle();
        }
        if (!(this.an == null || this.an.isRecycled())) {
            this.an.recycle();
        }
        super.onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C1373R.menu.activity_menu_segment_map, menu);
        this.f5805N = menu;
        this.f5805N.findItem(C1373R.id.menu_segment_share).setVisible(false);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                return true;
            case C1373R.id.menu_segment_collection:
                intent = new Intent(this, StageCollectionActivity.class);
                intent.putExtra("enter_source", 1);
                startActivity(intent);
                return true;
            case C1373R.id.menu_segment_list:
                intent = new Intent(this, StageListActivity.class);
                intent.putExtra("extra_stage_list", this.f5811T);
                startActivity(intent);
                return true;
            case C1373R.id.menu_segment_share:
                this.f5817Z.a(this.f5818a, this.f5801J, this.f5832p, this.f5806O, this.f5820d);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* renamed from: a */
    public void m7059a(View view, float f) {
        if (this.f5815X <= 0) {
            this.f5815X = getSupportActionBar().getHeight();
        }
        if (this.f5826j.getVisibility() == 8 && view.getTop() <= this.f5814W) {
            this.f5826j.setVisibility(0);
            this.f5823g.setBackgroundColor(Color.parseColor("#0e0e0e"));
        }
        if (this.f5826j.getVisibility() == 0 && view.getTop() > this.f5814W) {
            this.f5826j.setVisibility(8);
            this.f5823g.setBackgroundResource(C1373R.drawable.bg_segment_shadow);
        }
    }

    /* renamed from: a */
    public void m7060a(View view, PanelState panelState, PanelState panelState2) {
        if (panelState2 == PanelState.ANCHORED || panelState2 == PanelState.EXPANDED) {
            this.f5825i.setImageResource(C1373R.drawable.ic_arrow_down_segment);
            m7034a(true);
        } else if (panelState2 != PanelState.DRAGGING) {
            this.f5825i.setImageResource(C1373R.drawable.ic_arrow_up_segment);
            m7034a(false);
        }
        if (this.f5810S == PanelState.COLLAPSED && panelState2 == PanelState.ANCHORED && this.f5811T != null && !this.f5811T.isEmpty()) {
            this.f5817Z.a(((StageDTO) this.f5811T.get(this.ai)).getStageId());
        }
        if (this.at == 17) {
            if (panelState2 == PanelState.ANCHORED && this.f5810S == PanelState.COLLAPSED) {
                m7042c(false);
                m7054x();
            }
            if (panelState2 == PanelState.COLLAPSED && this.f5810S == PanelState.ANCHORED) {
                this.f5818a.scrollBy(0, (-(this.f5812U - this.f5816Y)) / 2);
                m7042c(true);
                this.f5818a.a(this.f5818a.getZoomLevel());
            }
        }
        if (panelState2 != PanelState.DRAGGING) {
            this.f5810S = panelState2;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.img_segment_my_location:
                this.f5818a.i();
                return;
            case C1373R.id.rela_segment_refresh:
                this.f5831o.startAnimation(this.f5802K);
                this.f5792A.setText(C1373R.string.str_loading);
                this.f5792A.startAnimation(this.f5803L);
                this.f5817Z.a();
                return;
            case C1373R.id.img_segment_back:
                finish();
                return;
            case C1373R.id.img_segment_share:
                this.f5817Z.a(this.f5818a, this.f5801J, this.f5832p, this.f5806O, this.f5820d, true, this.f5816Y);
                return;
            case C1373R.id.img_segment_arrow:
                m7050t();
                return;
            case C1373R.id.tv_segment_collection:
                m7055y();
                return;
            case C1373R.id.rela_segment_no_score:
                startActivity(new Intent(this, StageAchievementDescActivity.class));
                return;
            case C1373R.id.linear_segment_rank_item:
                m7024B();
                return;
            default:
                return;
        }
    }

    public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
        if (i2 > 0) {
            this.f5820d.setTouchEnabled(false);
        } else {
            this.f5820d.setTouchEnabled(true);
        }
    }

    /* renamed from: c */
    public void m7073c(double d, double d2) {
        this.aa = d;
        this.ab = d2;
        if (this.at == 17 && this.az) {
            this.az = false;
            this.f5817Z.a();
            m7035a(true, true);
        }
    }

    /* renamed from: a */
    public void m7064a(Object obj) {
        int i;
        if (C1849a.a()) {
            Marker marker = (Marker) obj;
            for (i = 0; i < this.ad.size(); i++) {
                Marker marker2 = (Marker) this.ad.get(i);
                if (marker == marker2) {
                    m7030a((Polyline) this.ac.get(i), marker2, i);
                }
            }
            return;
        }
        com.google.android.gms.maps.model.Marker marker3 = (com.google.android.gms.maps.model.Marker) obj;
        for (i = 0; i < this.ad.size(); i++) {
            com.google.android.gms.maps.model.Marker marker4 = (com.google.android.gms.maps.model.Marker) this.ad.get(i);
            if (marker3 == marker4) {
                m7033a((com.google.android.gms.maps.model.Polyline) this.ac.get(i), marker4, i);
            }
        }
    }

    /* renamed from: b */
    public void m7071b(Object obj) {
        int i;
        if (C1849a.a()) {
            Polyline polyline = (Polyline) obj;
            for (i = 0; i < this.ac.size(); i++) {
                Polyline polyline2 = (Polyline) this.ac.get(i);
                if (polyline == polyline2) {
                    m7030a(polyline2, (Marker) this.ad.get(i), i);
                }
            }
            return;
        }
        com.google.android.gms.maps.model.Polyline polyline3 = (com.google.android.gms.maps.model.Polyline) obj;
        for (i = 0; i < this.ac.size(); i++) {
            com.google.android.gms.maps.model.Polyline polyline4 = (com.google.android.gms.maps.model.Polyline) this.ac.get(i);
            if (polyline3 == polyline4) {
                m7033a(polyline4, (com.google.android.gms.maps.model.Marker) this.ad.get(i), i);
            }
        }
    }

    /* renamed from: a */
    public void m7057a() {
        m7068a(false, "");
    }

    /* renamed from: b */
    public void m7069b() {
        m7082l();
    }

    /* renamed from: c */
    public double m7072c() {
        return this.aa;
    }

    /* renamed from: d */
    public double m7074d() {
        return this.ab;
    }

    /* renamed from: e */
    public int m7075e() {
        return (int) (this.ax / 2.0d);
    }

    /* renamed from: f */
    public int m7076f() {
        return this.av;
    }

    /* renamed from: g */
    public StageMapListBaseActivity m7077g() {
        return this;
    }

    /* renamed from: a */
    public void m7066a(ArrayList<StageDTO> arrayList) {
        m7023A();
        m7052v();
        if (arrayList == null || arrayList.isEmpty()) {
            f5791b.info("notifySegments is null or empty");
            m7035a(false, true);
            return;
        }
        m7035a(false, false);
        boolean a = C1849a.a();
        f5791b.info("notifySegments");
        this.f5811T.addAll(arrayList);
        for (int i = 0; i < this.f5811T.size(); i++) {
            StageDTO stageDTO = (StageDTO) this.f5811T.get(i);
            this.aj.drawBitmap(BitmapFactory.decodeResource(getResources(), C1373R.drawable.ic_stage_map_list_marker_nor), 0.0f, 0.0f, this.al);
            this.al.getTextBounds(String.valueOf(i + 1), 0, String.valueOf(i + 1).length(), this.ao);
            this.aj.drawText(String.valueOf(i + 1), (float) ((this.ap / 2) - (this.ao.width() / 2)), (float) ((this.ap / 2) + (this.ao.height() / 2)), this.al);
            if (a) {
                this.ac.add((Polyline) this.f5818a.a(stageDTO.getPoints(), 8, 2146648385));
                this.ad.add((Marker) this.f5818a.a(stageDTO.getStartPoint(), BitmapDescriptorFactory.fromBitmap(this.am)));
            } else {
                this.ac.add((com.google.android.gms.maps.model.Polyline) this.f5818a.a(stageDTO.getPoints(), 8, 2146648385));
                this.ad.add((com.google.android.gms.maps.model.Marker) this.f5818a.a(stageDTO.getStartPoint(), com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap(this.am)));
            }
        }
    }

    /* renamed from: a */
    public void m7061a(StageDTO stageDTO) {
        if (stageDTO != null) {
            f5791b.info("get segment detail information success");
            this.au = stageDTO;
            m7049s();
            m7031a(this.au.getStartPoint(), (StagePointDTO) this.au.getPoints().get(this.au.getPoints().size() - 1));
            this.f5818a.a(this.au.getPoints(), 8, -835263);
            this.f5818a.b(this.au.getPoints(), this.f5818a.getWidth() - 100, this.f5816Y - 100);
            this.f5818a.setOnMapChangeFinishedListener(this);
            this.aw = true;
            return;
        }
        f5791b.info("get segment detail information error");
        Toasts.show(this, "获取赛段信息失败，请稍后重试");
        finish();
    }

    /* renamed from: h */
    public void m7078h() {
        f5791b.info("onGetSegmentsFailed");
        m7035a(false, true);
        m7023A();
        m7052v();
    }

    /* renamed from: a */
    public void m7063a(C2263b c2263b) {
        if (c2263b != null) {
            if (c2263b.a() == null || c2263b.a().isEmpty()) {
                this.f5808Q.a();
                this.f5809R.setVisibility(0);
            } else {
                this.f5808Q.a(c2263b.a());
                this.f5809R.setVisibility(8);
            }
            if (c2263b.b()) {
                this.f5795D.setVisibility(0);
                this.f5794C.setVisibility(8);
                m7039b(c2263b.c());
                return;
            }
            this.f5795D.setVisibility(8);
            this.f5794C.setVisibility(0);
        }
    }

    /* renamed from: j */
    public void m7080j() {
        f5791b.info("onGetStageRankDataFailed");
    }

    /* renamed from: a */
    public void m7067a(boolean z, int i) {
        if (z) {
            ((StageDTO) this.f5811T.get(this.ai)).setCollectId(i);
        } else {
            ((StageDTO) this.f5811T.get(this.ai)).setCollectId(0);
        }
        m7043d(z);
    }

    /* renamed from: k */
    public void m7081k() {
        f5791b.info("onCollectFailed");
    }

    /* renamed from: a */
    public void m7062a(StageRankDTO stageRankDTO) {
        Intent intent = new Intent();
        if (stageRankDTO == null) {
            intent.setClass(this, StageRankListActivity.class);
        } else {
            intent.setClass(this, StageProfileScoreActivity.class);
            intent.putExtra("user_id", stageRankDTO.getUserId());
        }
        if ((this.f5811T == null || this.f5811T.isEmpty()) && this.au != null) {
            intent.putExtra("stage_id", this.au.getStageId());
        } else {
            intent.putExtra("stage_id", ((StageDTO) this.f5811T.get(this.ai)).getStageId());
        }
        startActivity(intent);
    }

    /* renamed from: a */
    public void m7058a(double d, double d2) {
    }

    /* renamed from: b */
    public void m7070b(double d, double d2) {
        if (this.aw) {
            this.f5818a.scrollBy(0, (this.f5812U - this.f5816Y) / 2);
            this.aw = false;
        }
        if (this.f5810S == PanelState.COLLAPSED || this.f5810S == PanelState.HIDDEN) {
            m7056z();
        }
    }

    /* renamed from: a */
    public void m7065a(String str, String str2) {
    }

    /* renamed from: i */
    public void m7079i() {
        if (this.aw) {
            this.f5818a.scrollBy(0, (this.f5812U - this.f5816Y) / 2);
            this.aw = false;
        }
    }

    /* renamed from: a */
    private void m7029a(Bundle bundle) {
        setSupportActionBar(this.f5819c);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator((int) C1373R.drawable.ic_record_back);
        }
        this.f5813V = m7025C()[0];
        this.f5812U = m7025C()[1];
        this.f5814W = m7026D();
        this.f5826j.setPadding(0, this.f5814W, 0, 0);
        this.f5806O.setMinimumHeight(this.f5812U);
        this.f5821e.setMinimumHeight(this.f5812U);
        m7038b(bundle);
        this.f5816Y = getResources().getDimensionPixelOffset(C1373R.dimen.dimen_300) + this.f5814W;
        this.ap = getResources().getDimensionPixelOffset(C1373R.dimen.dimen_30);
        this.aq = getResources().getDimensionPixelOffset(C1373R.dimen.dimen_29);
        this.ar = getResources().getDimensionPixelOffset(C1373R.dimen.dimen_46);
        float f = ((float) ((this.f5812U - m7027E()[1]) - this.f5816Y)) / ((float) (this.f5812U - m7027E()[1]));
        this.f5820d.setPanelHeight(m7027E()[1]);
        this.f5820d.setAnchorPoint(f);
        this.f5820d.setPanelState(PanelState.HIDDEN);
        this.f5820d.setScrollDown(true);
        this.f5820d.setDefaultHeight(this.f5816Y);
        this.f5810S = this.f5820d.getPanelState();
        this.as = new DecimalFormat("0.0");
        m7047o();
        m7048r();
        m7040b(false);
        this.f5817Z = new C2251c(this);
        m7046n();
        Config config = Config.ARGB_8888;
        this.am = Bitmap.createBitmap(this.ap, this.ap, config);
        this.an = Bitmap.createBitmap(this.aq, this.ar, config);
        this.aj = new Canvas(this.am);
        this.ak = new Canvas(this.an);
        this.al = new Paint();
        this.al.setTextSize((float) getResources().getDimensionPixelSize(C1373R.dimen.font_12));
        this.al.setColor(-1);
        this.at = getIntent().getIntExtra("intent_type", 17);
        if (this.at == 18) {
            this.au = (StageDTO) getIntent().getSerializableExtra("stage_info");
            this.av = getIntent().getIntExtra("stage_id", -1);
            if (this.au == null && this.av == -1) {
                f5791b.info("地图列表形式");
                this.at = 17;
                this.f5818a.i();
                return;
            }
            f5791b.info("地图详情形式");
            if (this.au != null) {
                m7049s();
                return;
            } else {
                this.f5817Z.b();
                return;
            }
        }
        f5791b.info("地图列表形式");
        this.f5818a.i();
    }

    /* renamed from: n */
    private void m7046n() {
        this.f5820d.a(this);
        this.f5821e.setOnScrollChangeListener(this);
        this.f5827k.setOnClickListener(this);
        this.f5828l.setOnClickListener(this);
        this.f5825i.setOnClickListener(this);
        this.f5829m.setOnClickListener(this);
        this.f5830n.setOnClickListener(this);
        this.f5840x.setOnClickListener(this);
        this.f5794C.setOnClickListener(this);
        this.f5795D.setOnClickListener(this);
        this.f5808Q.a(this);
        this.f5803L.setAnimationListener(new StageMapListBaseActivity$1(this));
        this.f5804M.setAnimationListener(new StageMapListBaseActivity$2(this));
    }

    /* renamed from: b */
    private void m7038b(Bundle bundle) {
        this.f5818a.a(C1849a.a() ? MapType.BaiDu : MapType.Google, this, this, this, this, new StageMapListBaseActivity$3(this));
        this.f5818a.setOnMapStatusChangeListener(this);
        this.f5818a.a(bundle);
    }

    /* renamed from: o */
    private void m7047o() {
        this.f5808Q = new C2280c(this, true);
        this.f5807P.setLayoutManager(new LinearLayoutManager(this));
        this.f5807P.setNestedScrollingEnabled(false);
        this.f5807P.setHasFixedSize(true);
        this.f5807P.setAdapter(this.f5808Q);
    }

    /* renamed from: r */
    private void m7048r() {
        this.f5802K = AnimationUtils.loadAnimation(this, C1373R.anim.anim_segment_refresh);
        this.f5802K.setInterpolator(new LinearInterpolator());
        this.f5803L = AnimationUtils.loadAnimation(this, C1373R.anim.anim_segment_not_find_segment);
        this.f5804M = AnimationUtils.loadAnimation(this, C1373R.anim.anim_segment_not_find_segment_back);
    }

    /* renamed from: a */
    private void m7034a(boolean z) {
        if (this.f5805N != null) {
            if (z) {
                this.f5805N.findItem(C1373R.id.menu_segment_share).setVisible(true);
                this.f5805N.findItem(C1373R.id.menu_segment_collection).setVisible(false);
                this.f5805N.findItem(C1373R.id.menu_segment_list).setVisible(false);
                return;
            }
            this.f5805N.findItem(C1373R.id.menu_segment_share).setVisible(false);
            this.f5805N.findItem(C1373R.id.menu_segment_collection).setVisible(true);
            this.f5805N.findItem(C1373R.id.menu_segment_list).setVisible(true);
        }
    }

    /* renamed from: a */
    private void m7030a(Polyline polyline, Marker marker, int i) {
        this.aj.drawBitmap(BitmapFactory.decodeResource(getResources(), C1373R.drawable.ic_stage_map_list_marker_nor), 0.0f, 0.0f, this.al);
        this.al.getTextBounds(String.valueOf(this.ai + 1), 0, String.valueOf(this.ai + 1).length(), this.ao);
        this.aj.drawText(String.valueOf(this.ai + 1), (float) ((this.ap / 2) - (this.ao.width() / 2)), (float) ((this.ap / 2) + (this.ao.height() / 2)), this.al);
        BitmapDescriptor fromBitmap = BitmapDescriptorFactory.fromBitmap(this.am);
        this.ak.drawBitmap(BitmapFactory.decodeResource(getResources(), C1373R.drawable.ic_stage_map_list_marker_selected), 0.0f, 0.0f, this.al);
        this.al.getTextBounds(String.valueOf(i + 1), 0, String.valueOf(i + 1).length(), this.ao);
        this.ak.drawText(String.valueOf(i + 1), (float) ((this.aq / 2) - (this.ao.width() / 2)), (float) ((this.ar / 2) - (this.ao.width() / 2)), this.al);
        BitmapDescriptor fromBitmap2 = BitmapDescriptorFactory.fromBitmap(this.an);
        this.ai = i;
        if (this.ag == null || polyline != this.ag) {
            if (this.ag != null) {
                Polyline polyline2 = (Polyline) this.ag;
                polyline2.setColor(2146648385);
                polyline2.setWidth(8);
                Marker marker2 = (Marker) this.ah;
                marker2.setZIndex(1);
                marker2.setIcon(fromBitmap);
            } else {
                m7051u();
            }
            polyline.setColor(-835263);
            polyline.setWidth(8);
            this.ag = polyline;
            this.f5818a.a(new LatLng(((StageDTO) this.f5811T.get(i)).getStartPoint().getLatitude(), ((StageDTO) this.f5811T.get(i)).getStartPoint().getLongitude()));
            marker.setZIndex(2);
            marker.setIcon(fromBitmap2);
            this.ah = marker;
            m7053w();
            return;
        }
        polyline.setColor(2146648385);
        polyline.setWidth(8);
        this.ag = null;
        marker.setZIndex(1);
        marker.setIcon(fromBitmap);
        this.ah = null;
        m7052v();
    }

    /* renamed from: a */
    private void m7033a(com.google.android.gms.maps.model.Polyline polyline, com.google.android.gms.maps.model.Marker marker, int i) {
        this.aj.drawBitmap(BitmapFactory.decodeResource(getResources(), C1373R.drawable.ic_stage_map_list_marker_nor), 0.0f, 0.0f, this.al);
        this.al.getTextBounds(String.valueOf(this.ai + 1), 0, String.valueOf(this.ai + 1).length(), this.ao);
        this.aj.drawText(String.valueOf(this.ai + 1), (float) ((this.ap / 2) - (this.ao.width() / 2)), (float) ((this.ap / 2) + (this.ao.height() / 2)), this.al);
        com.google.android.gms.maps.model.BitmapDescriptor fromBitmap = com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap(this.am);
        this.ak.drawBitmap(BitmapFactory.decodeResource(getResources(), C1373R.drawable.ic_stage_map_list_marker_selected), 0.0f, 0.0f, this.al);
        this.al.getTextBounds(String.valueOf(i + 1), 0, String.valueOf(i + 1).length(), this.ao);
        this.ak.drawText(String.valueOf(i + 1), (float) ((this.aq / 2) - (this.ao.width() / 2)), (float) ((this.ar / 2) - (this.ao.width() / 2)), this.al);
        com.google.android.gms.maps.model.BitmapDescriptor fromBitmap2 = com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap(this.an);
        this.ai = i;
        if (this.ag == null || polyline != this.ag) {
            if (this.ag != null) {
                com.google.android.gms.maps.model.Polyline polyline2 = (com.google.android.gms.maps.model.Polyline) this.ag;
                polyline2.setColor(2146648385);
                polyline2.setWidth(8.0f);
                com.google.android.gms.maps.model.Marker marker2 = (com.google.android.gms.maps.model.Marker) this.ah;
                marker2.setZIndex(1.0f);
                marker2.setIcon(fromBitmap);
            } else {
                m7051u();
            }
            polyline.setColor(-835263);
            polyline.setWidth(18.0f);
            this.ag = polyline;
            this.f5818a.a(new LatLng(((StageDTO) this.f5811T.get(i)).getStartPoint().getLatitude(), ((StageDTO) this.f5811T.get(i)).getStartPoint().getLongitude()));
            marker.setZIndex(2.0f);
            marker.setIcon(fromBitmap2);
            this.ah = marker;
            m7053w();
            return;
        }
        polyline.setColor(2146648385);
        polyline.setWidth(8.0f);
        this.ag = null;
        marker.setZIndex(1.0f);
        marker.setIcon(fromBitmap);
        this.ah = null;
        m7052v();
    }

    /* renamed from: b */
    private void m7040b(boolean z) {
        if (z) {
            this.f5794C.setVisibility(8);
            this.f5795D.setVisibility(0);
            return;
        }
        f5791b.error("not joined the segment");
        this.f5794C.setVisibility(0);
        this.f5795D.setVisibility(8);
    }

    /* renamed from: s */
    private void m7049s() {
        this.f5820d.setPanelState(PanelState.ANCHORED);
        this.f5820d.setScrollDown(false);
        m7053w();
        this.f5817Z.a(this.au.getStageId());
    }

    /* renamed from: t */
    private void m7050t() {
        if (this.f5810S == PanelState.ANCHORED) {
            this.f5820d.setPanelState(PanelState.COLLAPSED);
        } else if (this.f5810S == PanelState.EXPANDED || this.f5810S == PanelState.COLLAPSED) {
            this.f5820d.setPanelState(PanelState.ANCHORED);
        }
    }

    /* renamed from: u */
    private void m7051u() {
        this.f5820d.setPanelState(PanelState.COLLAPSED);
        LayoutParams layoutParams = (LayoutParams) this.f5824h.getLayoutParams();
        layoutParams.bottomMargin += m7027E()[1];
        this.f5824h.setLayoutParams(layoutParams);
    }

    /* renamed from: v */
    private void m7052v() {
        if (this.f5820d.getPanelState() == PanelState.COLLAPSED) {
            LayoutParams layoutParams = (LayoutParams) this.f5824h.getLayoutParams();
            layoutParams.bottomMargin -= m7027E()[1];
            this.f5824h.setLayoutParams(layoutParams);
        }
        this.ah = null;
        this.ag = null;
        this.ai = 0;
        this.f5820d.setPanelState(PanelState.HIDDEN);
        this.f5821e.scrollTo(0, 0);
        this.f5833q.setText("");
        this.f5834r.setText("");
        this.f5835s.setText("");
        this.f5837u.setText("");
        this.f5839w.setText("");
        m7043d(false);
        this.f5841y.setText("");
        this.f5842z.setText("");
        this.f5808Q.a();
    }

    /* renamed from: w */
    private void m7053w() {
        StageDTO stageDTO = this.at == 18 ? this.au : (StageDTO) this.f5811T.get(this.ai);
        if (stageDTO != null) {
            boolean z;
            this.f5833q.setText(stageDTO.getStageName());
            this.f5834r.setText(String.format(getResources().getString(C1373R.string.str_segment_people_join), new Object[]{Integer.valueOf(stageDTO.getParticipateNum())}));
            this.f5836t.setText(C1373R.string.str_elevation_diff);
            this.f5838v.setText(C1373R.string.activity_param_label_distance);
            if (C1849a.b(m7077g())) {
                this.f5835s.setText(String.valueOf(this.as.format(stageDTO.getElevationDiff())));
                this.f5837u.setText(String.valueOf(this.as.format(stageDTO.getDistance() / 1000.0d)));
                this.f5836t.append("(m)");
                this.f5838v.append("(km)");
            } else {
                this.f5835s.setText(String.valueOf(this.as.format(C1849a.c(stageDTO.getElevationDiff()))));
                this.f5837u.setText(String.valueOf(this.as.format(C1849a.a(stageDTO.getDistance() / 1000.0d))));
                this.f5836t.append("(ft)");
                this.f5838v.append("(mi)");
            }
            this.f5839w.setText(this.as.format(stageDTO.getSlope()) + "%");
            if (stageDTO.getCollectId() > 0) {
                z = true;
            } else {
                z = false;
            }
            m7043d(z);
            this.f5841y.setText(stageDTO.getStartName());
            this.f5842z.setText(stageDTO.getEndName());
        }
    }

    /* renamed from: c */
    private void m7042c(boolean z) {
        Polyline polyline = this.ac.get(this.ai);
        Iterator it;
        Iterator it2;
        if (C1849a.a()) {
            it = this.ac.iterator();
            while (it.hasNext()) {
                Polyline polyline2 = (Polyline) it.next();
                if (polyline == null || polyline != polyline2) {
                    polyline2.setVisible(z);
                } else if (z) {
                    polyline2.setColor(2146648385);
                } else {
                    polyline2.setColor(-835263);
                }
            }
            it2 = this.ad.iterator();
            while (it2.hasNext()) {
                ((Marker) it2.next()).setVisible(z);
            }
            if (z) {
                if (this.ae != null) {
                    ((Marker) this.ae).remove();
                    this.ae = null;
                }
                if (this.af != null) {
                    ((Marker) this.af).remove();
                    this.af = null;
                    return;
                }
                return;
            }
            return;
        }
        it = this.ac.iterator();
        while (it.hasNext()) {
            Object obj = (com.google.android.gms.maps.model.Polyline) it.next();
            if (polyline == null || polyline != obj) {
                obj.setVisible(z);
            } else if (z) {
                obj.setColor(2146648385);
            } else {
                obj.setColor(-835263);
            }
        }
        it2 = this.ad.iterator();
        while (it2.hasNext()) {
            ((com.google.android.gms.maps.model.Marker) it2.next()).setVisible(z);
        }
        if (!z) {
            if (this.ae != null) {
                ((Marker) this.ae).remove();
                this.ae = null;
            }
            if (this.af != null) {
                ((Marker) this.af).remove();
                this.af = null;
            }
        }
    }

    /* renamed from: x */
    private void m7054x() {
        StageDTO stageDTO = (StageDTO) this.f5811T.get(this.ai);
        this.f5818a.b(stageDTO.getPoints(), this.f5818a.getWidth() - 100, this.f5816Y - 100);
        this.aw = true;
        m7031a(stageDTO.getStartPoint(), (StagePointDTO) stageDTO.getPoints().get(stageDTO.getPoints().size() - 1));
    }

    /* renamed from: a */
    private void m7031a(StagePointDTO stagePointDTO, StagePointDTO stagePointDTO2) {
        this.ae = this.f5818a.a(stagePointDTO, C1373R.drawable.ic_activity_detail_start);
        this.af = this.f5818a.a(stagePointDTO2, C1373R.drawable.ic_activity_finish_end);
    }

    /* renamed from: d */
    private void m7043d(boolean z) {
        if (z) {
            this.f5840x.setText(C1373R.string.str_segment_favorited);
            this.f5840x.setCompoundDrawablesWithIntrinsicBounds(0, C1373R.drawable.ic_segment_collectioned_star, 0, 0);
            return;
        }
        this.f5840x.setText(C1373R.string.str_segment_favorite);
        this.f5840x.setCompoundDrawablesWithIntrinsicBounds(0, C1373R.drawable.ic_segment_collection_star, 0, 0);
    }

    /* renamed from: y */
    private void m7055y() {
        StageDTO stageDTO = this.au != null ? this.au : (StageDTO) this.f5811T.get(this.ai);
        if (stageDTO.getCollectId() <= 0) {
            this.f5817Z.a(stageDTO.getStageId(), false);
        } else {
            this.f5817Z.a(stageDTO.getCollectId(), true);
        }
    }

    /* renamed from: b */
    private void m7039b(StageRankDTO stageRankDTO) {
        if (stageRankDTO != null) {
            this.f5796E.setText(String.valueOf(stageRankDTO.getRanking()));
            this.f5798G.setText(stageRankDTO.getNickname());
            Picasso.with(this).load(stageRankDTO.getAvatar()).placeholder((int) C1373R.drawable.ic_avatar).error((int) C1373R.drawable.ic_avatar).fit().into(this.f5797F);
            this.f5799H.setText(C2555d.b(stageRankDTO.getDuration()));
            this.f5800I.setText(C2555d.a(stageRankDTO.getTime()));
            this.f5793B.setTag(stageRankDTO);
        }
    }

    /* renamed from: a */
    private void m7035a(boolean z, boolean z2) {
        if (z) {
            this.f5831o.startAnimation(this.f5802K);
            this.f5792A.setText(C1373R.string.str_loading);
            if (this.f5792A.getVisibility() != 0) {
                this.f5792A.startAnimation(this.f5803L);
                return;
            }
            return;
        }
        this.f5831o.clearAnimation();
        if (z2) {
            this.f5792A.setText(C1373R.string.str_segment_no_find);
        } else {
            this.f5792A.startAnimation(this.f5804M);
        }
    }

    /* renamed from: e */
    private void m7044e(boolean z) {
        LatLng b;
        boolean a = C1849a.a();
        Point point = new Point(this.f5813V / 2, this.f5812U / 2);
        if (a) {
            com.baidu.mapapi.model.LatLng latLng = (com.baidu.mapapi.model.LatLng) this.f5818a.a(point);
            if (latLng != null) {
                b = C2558g.b(latLng);
            }
            b = null;
        } else {
            com.google.android.gms.maps.model.LatLng latLng2 = (com.google.android.gms.maps.model.LatLng) this.f5818a.a(point);
            if (latLng2 != null) {
                b = C2558g.a(latLng2);
            }
            b = null;
        }
        if (b != null) {
            if (DistanceUtil.getDistance(C2558g.g(m7072c(), m7074d()), C2558g.g(b.getLatitude(), b.getLongitude())) > (this.ax * 3.0d) / 4.0d || z) {
                this.aa = b.getLatitude();
                this.ab = b.getLongitude();
                this.f5817Z.a();
                m7035a(true, true);
            }
        }
    }

    /* renamed from: z */
    private void m7056z() {
        LatLng b;
        LatLng latLng;
        boolean a = C1849a.a();
        Point point = new Point(0, 0);
        Point point2 = new Point(this.f5812U, 0);
        LatLng b2;
        if (a) {
            com.baidu.mapapi.model.LatLng latLng2 = (com.baidu.mapapi.model.LatLng) this.f5818a.a(point);
            com.baidu.mapapi.model.LatLng latLng3 = (com.baidu.mapapi.model.LatLng) this.f5818a.a(point2);
            if (latLng2 != null) {
                b2 = C2558g.b(latLng2);
            } else {
                b2 = null;
            }
            if (latLng3 != null) {
                b = C2558g.b(latLng3);
            } else {
                b = null;
            }
            latLng = b2;
        } else {
            com.google.android.gms.maps.model.LatLng latLng4 = (com.google.android.gms.maps.model.LatLng) this.f5818a.a(point);
            com.google.android.gms.maps.model.LatLng latLng5 = (com.google.android.gms.maps.model.LatLng) this.f5818a.a(point2);
            if (latLng4 != null) {
                b2 = C2558g.a(latLng4);
            } else {
                b2 = null;
            }
            if (latLng5 != null) {
                b = C2558g.a(latLng5);
                latLng = b2;
            } else {
                b = null;
                latLng = b2;
            }
        }
        if (latLng != null && b != null) {
            double distance = DistanceUtil.getDistance(C2558g.g(b.getLatitude(), b.getLongitude()), C2558g.g(latLng.getLatitude(), latLng.getLongitude()));
            if (Math.abs(distance - this.ax) <= (this.ax * 3.0d) / 4.0d || this.ax <= 50.0d) {
                a = false;
            } else {
                a = true;
            }
            this.ax = distance;
            if (a) {
                m7044e(true);
            } else {
                m7044e(false);
            }
        }
    }

    /* renamed from: A */
    private void m7023A() {
        this.f5811T.clear();
        this.ai = 0;
        this.ac.clear();
        this.ad.clear();
        this.f5818a.h();
    }

    /* renamed from: B */
    private void m7024B() {
        Intent intent = new Intent(this, StageProfileScoreActivity.class);
        if ((this.f5811T == null || this.f5811T.isEmpty()) && this.au != null) {
            intent.putExtra("stage_id", this.au.getStageId());
        } else {
            intent.putExtra("stage_id", ((StageDTO) this.f5811T.get(this.ai)).getStageId());
        }
        intent.putExtra("user_id", m5331p());
        startActivity(intent);
    }

    /* renamed from: a */
    protected void m7068a(boolean z, String str) {
        if (getWindow() != null && !isFinishing()) {
            if (this.ay == null) {
                this.ay = new C1802i(this, str, z);
            }
            if (!this.ay.isShowing()) {
                this.ay.show();
            }
        }
    }

    /* renamed from: l */
    protected void m7082l() {
        if (this.ay != null && !isFinishing() && getWindow() != null) {
            if (this.ay.isShowing()) {
                this.ay.dismiss();
            }
            this.ay = null;
        }
    }

    /* renamed from: C */
    private int[] m7025C() {
        WindowManager windowManager = getWindowManager();
        return new int[]{windowManager.getDefaultDisplay().getWidth(), windowManager.getDefaultDisplay().getHeight()};
    }

    /* renamed from: D */
    private int m7026D() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* renamed from: E */
    private int[] m7027E() {
        this.f5822f.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth = this.f5822f.getMeasuredWidth();
        int measuredHeight = this.f5822f.getMeasuredHeight();
        return new int[]{measuredWidth, measuredHeight};
    }
}
