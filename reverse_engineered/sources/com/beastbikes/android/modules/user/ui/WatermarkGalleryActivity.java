package com.beastbikes.android.modules.user.ui;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.dto.C2412b;
import com.beastbikes.android.modules.user.dto.C2414c;
import com.beastbikes.android.modules.user.filter.other.DrawableCenterTextView;
import com.beastbikes.android.modules.user.filter.p155b.C2441a;
import com.beastbikes.android.modules.user.filter.utils.FilterTools;
import com.beastbikes.android.modules.user.filter.utils.FilterTools.C2468a;
import com.beastbikes.android.modules.user.filter.utils.FilterTools.FilterType;
import com.beastbikes.android.utils.C2553b;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C2801d;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.squareup.picasso.Picasso;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import jp.co.cyberagent.android.gpuimage.C5421v;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

@C1459b(a = 2130903216)
public class WatermarkGalleryActivity extends SessionFragmentActivity implements OnClickListener, OnSeekBarChangeListener {
    /* renamed from: A */
    private ImageView f6678A;
    /* renamed from: B */
    private TextView f6679B;
    @C1458a(a = 2131756162)
    /* renamed from: C */
    private ViewGroup f6680C;
    /* renamed from: D */
    private ImageView f6681D;
    /* renamed from: E */
    private TextView f6682E;
    @C1458a(a = 2131756163)
    /* renamed from: F */
    private ViewGroup f6683F;
    /* renamed from: G */
    private ImageView f6684G;
    /* renamed from: H */
    private TextView f6685H;
    @C1458a(a = 2131756164)
    /* renamed from: I */
    private ViewGroup f6686I;
    /* renamed from: J */
    private ImageView f6687J;
    /* renamed from: K */
    private TextView f6688K;
    @C1458a(a = 2131756165)
    /* renamed from: L */
    private ViewGroup f6689L;
    /* renamed from: M */
    private ImageView f6690M;
    /* renamed from: N */
    private TextView f6691N;
    @C1458a(a = 2131756166)
    /* renamed from: O */
    private ViewGroup f6692O;
    /* renamed from: P */
    private ImageView f6693P;
    /* renamed from: Q */
    private TextView f6694Q;
    @C1458a(a = 2131756167)
    /* renamed from: R */
    private ViewGroup f6695R;
    /* renamed from: S */
    private ImageView f6696S;
    /* renamed from: T */
    private TextView f6697T;
    @C1458a(a = 2131756168)
    /* renamed from: U */
    private ViewGroup f6698U;
    /* renamed from: V */
    private ImageView f6699V;
    /* renamed from: W */
    private TextView f6700W;
    @C1458a(a = 2131756169)
    /* renamed from: X */
    private ViewGroup f6701X;
    /* renamed from: Y */
    private ImageView f6702Y;
    /* renamed from: Z */
    private TextView f6703Z;
    /* renamed from: a */
    private final DisplayMetrics f6704a = new DisplayMetrics();
    private TextView aA;
    private Uri aB;
    private C5421v aC;
    private C2468a aD;
    private int aE = 0;
    private int aF;
    private int aG = 0;
    private C2441a aH;
    private C2441a aI;
    private List<ViewGroup> aJ = new ArrayList(21);
    private List<ImageView> aK = new ArrayList(9);
    private ActivityDTO aL;
    private Bitmap aM;
    private RequestQueue aN;
    private LayoutParams aO;
    @C1458a(a = 2131756170)
    private ViewGroup aa;
    private ImageView ab;
    private TextView ac;
    @C1458a(a = 2131756171)
    private ViewGroup ad;
    private ImageView ae;
    private TextView af;
    @C1458a(a = 2131756172)
    private ViewGroup ag;
    private ImageView ah;
    private TextView ai;
    @C1458a(a = 2131756173)
    private ViewGroup aj;
    private ImageView ak;
    private TextView al;
    @C1458a(a = 2131756174)
    private ViewGroup am;
    private ImageView an;
    private TextView ao;
    @C1458a(a = 2131756175)
    private ViewGroup ap;
    private ImageView aq;
    private TextView ar;
    @C1458a(a = 2131756176)
    private ViewGroup as;
    private ImageView at;
    private TextView au;
    @C1458a(a = 2131756177)
    private ViewGroup av;
    private ImageView aw;
    private TextView ax;
    @C1458a(a = 2131756178)
    private ViewGroup ay;
    private ImageView az;
    @C1458a(a = 2131756139)
    /* renamed from: b */
    private ImageView f6705b;
    @C1458a(a = 2131756140)
    /* renamed from: c */
    private ImageView f6706c;
    @C1458a(a = 2131756141)
    /* renamed from: d */
    private ImageView f6707d;
    @C1458a(a = 2131756142)
    /* renamed from: e */
    private TextView f6708e;
    @C1458a(a = 2131756146)
    /* renamed from: f */
    private RelativeLayout f6709f;
    @C1458a(a = 2131756143)
    /* renamed from: g */
    private FrameLayout f6710g;
    @C1458a(a = 2131756144)
    /* renamed from: h */
    private GPUImageView f6711h;
    @C1458a(a = 2131756145)
    /* renamed from: i */
    private ImageView f6712i;
    @C1458a(a = 2131756147)
    /* renamed from: j */
    private DrawableCenterTextView f6713j;
    @C1458a(a = 2131756148)
    /* renamed from: k */
    private DrawableCenterTextView f6714k;
    @C1458a(a = 2131756150)
    /* renamed from: l */
    private ViewGroup f6715l;
    @C1458a(a = 2131756157)
    /* renamed from: m */
    private LinearLayout f6716m;
    @C1458a(a = 2131756156)
    /* renamed from: n */
    private LinearLayout f6717n;
    @C1458a(a = 2131756151)
    /* renamed from: o */
    private ViewGroup f6718o;
    @C1458a(a = 2131756149)
    /* renamed from: p */
    private ImageView f6719p;
    @C1458a(a = 2131756158)
    /* renamed from: q */
    private ViewGroup f6720q;
    /* renamed from: r */
    private ImageView f6721r;
    /* renamed from: s */
    private TextView f6722s;
    @C1458a(a = 2131756159)
    /* renamed from: t */
    private ViewGroup f6723t;
    /* renamed from: u */
    private ImageView f6724u;
    /* renamed from: v */
    private TextView f6725v;
    @C1458a(a = 2131756160)
    /* renamed from: w */
    private ViewGroup f6726w;
    /* renamed from: x */
    private ImageView f6727x;
    /* renamed from: y */
    private TextView f6728y;
    @C1458a(a = 2131756161)
    /* renamed from: z */
    private ViewGroup f6729z;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        this.aN = Volley.newRequestQueue(this);
        getWindowManager().getDefaultDisplay().getMetrics(this.f6704a);
        this.aF = BitmapFactory.decodeResource(getResources(), C1373R.drawable.offline_map_tab_cursor).getWidth();
        this.aE = ((this.f6704a.widthPixels / 2) - this.aF) / 2;
        Matrix matrix = new Matrix();
        matrix.postTranslate((float) this.aE, 0.0f);
        this.f6719p.setImageMatrix(matrix);
        LayoutParams layoutParams = this.f6711h.getLayoutParams();
        layoutParams.height = this.f6704a.widthPixels;
        layoutParams.width = this.f6704a.widthPixels;
        this.f6711h.setLayoutParams(layoutParams);
        this.f6705b.setOnClickListener(this);
        this.f6708e.setOnClickListener(this);
        this.f6705b.setOnClickListener(this);
        this.f6706c.setOnClickListener(this);
        this.f6707d.setOnClickListener(this);
        this.f6714k.setOnClickListener(this);
        this.f6713j.setOnClickListener(this);
        this.f6713j.setSelected(true);
        this.f6706c.setEnabled(false);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("path")) {
            this.aB = (Uri) intent.getParcelableExtra("path");
            this.f6711h.setImage(this.aB);
        }
        m7879a();
        C2580w.a(this, "", "click_ridding_history_share_digital_watermarking_chose_filter");
    }

    /* renamed from: a */
    private void m7879a() {
        if (this.aB != null) {
            Options options = new Options();
            ContentResolver contentResolver = getContentResolver();
            options.inSampleSize = 6;
            try {
                this.aM = BitmapFactory.decodeStream(contentResolver.openInputStream(this.aB), null, options);
            } catch (FileNotFoundException e) {
            }
            if (this.aM == null) {
                finish();
            }
            this.f6721r = (ImageView) this.f6720q.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.f6722s = (TextView) this.f6720q.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.f6722s.setText(C1373R.string.source_image);
            this.f6720q.setOnClickListener(this);
            this.f6720q.setTag(FilterType.NO_FILTER);
            this.f6721r.setImageBitmap(this.aM);
            this.f6724u = (ImageView) this.f6723t.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.f6725v = (TextView) this.f6723t.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.f6725v.setText("AMARO");
            this.f6723t.setOnClickListener(this);
            this.f6723t.setTag(FilterType.I_AMARO);
            this.f6727x = (ImageView) this.f6726w.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.f6728y = (TextView) this.f6726w.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.f6728y.setText("HUDSON");
            this.f6726w.setOnClickListener(this);
            this.f6726w.setTag(FilterType.I_HUDSON);
            this.f6678A = (ImageView) this.f6729z.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.f6679B = (TextView) this.f6729z.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.f6679B.setText("VALENCIA");
            this.f6729z.setOnClickListener(this);
            this.f6729z.setTag(FilterType.I_VALENCIA);
            this.f6681D = (ImageView) this.f6680C.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.f6682E = (TextView) this.f6680C.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.f6682E.setText("XOROII");
            this.f6680C.setOnClickListener(this);
            this.f6680C.setTag(FilterType.I_XPROII);
            this.f6684G = (ImageView) this.f6683F.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.f6685H = (TextView) this.f6683F.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.f6685H.setText("EARLYBIRD");
            this.f6683F.setOnClickListener(this);
            this.f6683F.setTag(FilterType.I_EARLYBIRD);
            this.f6687J = (ImageView) this.f6686I.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.f6688K = (TextView) this.f6686I.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.f6688K.setText("LOMO");
            this.f6686I.setOnClickListener(this);
            this.f6686I.setTag(FilterType.I_LOMO);
            this.f6690M = (ImageView) this.f6689L.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.f6691N = (TextView) this.f6689L.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.f6691N.setText("BRANNAN");
            this.f6689L.setOnClickListener(this);
            this.f6689L.setTag(FilterType.I_BRANNAN);
            this.f6693P = (ImageView) this.f6692O.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.f6694Q = (TextView) this.f6692O.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.f6694Q.setText("INKWELL");
            this.f6692O.setOnClickListener(this);
            this.f6692O.setTag(FilterType.I_INKWELL);
            this.f6696S = (ImageView) this.f6695R.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.f6697T = (TextView) this.f6695R.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.f6697T.setText("HEFE");
            this.f6695R.setOnClickListener(this);
            this.f6695R.setTag(FilterType.I_HEFE);
            this.f6699V = (ImageView) this.f6698U.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.f6700W = (TextView) this.f6698U.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.f6700W.setText("NASHVILLE");
            this.f6698U.setOnClickListener(this);
            this.f6698U.setTag(FilterType.I_NASHVILLE);
            this.f6702Y = (ImageView) this.f6701X.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.f6703Z = (TextView) this.f6701X.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.f6703Z.setText("TONE_CURVE");
            this.f6701X.setOnClickListener(this);
            this.f6701X.setTag(FilterType.TONE_CURVE);
            this.ab = (ImageView) this.aa.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.ac = (TextView) this.aa.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.ac.setText("VIGNETTE");
            this.aa.setOnClickListener(this);
            this.aa.setTag(FilterType.VIGNETTE);
            this.ae = (ImageView) this.ad.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.af = (TextView) this.ad.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.af.setText("SUTRO");
            this.ad.setOnClickListener(this);
            this.ad.setTag(FilterType.I_SUTRO);
            this.ah = (ImageView) this.ag.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.ai = (TextView) this.ag.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.ai.setText("TOASTER");
            this.ag.setOnClickListener(this);
            this.ag.setTag(FilterType.I_TOASTER);
            this.ak = (ImageView) this.aj.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.al = (TextView) this.aj.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.al.setText("WALDEN");
            this.aj.setOnClickListener(this);
            this.aj.setTag(FilterType.I_WALDEN);
            this.an = (ImageView) this.am.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.ao = (TextView) this.am.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.ao.setText("1977");
            this.am.setOnClickListener(this);
            this.am.setTag(FilterType.I_1977);
            this.aq = (ImageView) this.ap.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.ar = (TextView) this.ap.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.ar.setText("LORDKELVIN");
            this.ap.setOnClickListener(this);
            this.ap.setTag(FilterType.I_LORDKELVIN);
            this.at = (ImageView) this.as.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.au = (TextView) this.as.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.au.setText("CONTRAST");
            this.as.setOnClickListener(this);
            this.as.setTag(FilterType.CONTRAST);
            this.aw = (ImageView) this.av.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.ax = (TextView) this.av.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.ax.setText("SEPIA");
            this.av.setOnClickListener(this);
            this.av.setTag(FilterType.SEPIA);
            this.az = (ImageView) this.ay.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_img);
            this.aA = (TextView) this.ay.findViewById(C1373R.id.activity_watermark_gallery_lv_item_filter_name);
            this.aA.setText("Lookup Amatorka");
            this.ay.setOnClickListener(this);
            this.ay.setTag(FilterType.LOOKUP_AMATORKA);
            this.f6721r.setImageBitmap(this.aM);
            this.f6724u.setImageBitmap(this.aM);
            this.f6727x.setImageBitmap(this.aM);
            this.f6678A.setImageBitmap(this.aM);
            this.f6681D.setImageBitmap(this.aM);
            this.f6684G.setImageBitmap(this.aM);
            this.f6687J.setImageBitmap(this.aM);
            this.f6690M.setImageBitmap(this.aM);
            this.f6693P.setImageBitmap(this.aM);
            this.f6696S.setImageBitmap(this.aM);
            this.f6699V.setImageBitmap(this.aM);
            this.f6702Y.setImageBitmap(this.aM);
            this.ab.setImageBitmap(this.aM);
            this.ae.setImageBitmap(this.aM);
            this.ah.setImageBitmap(this.aM);
            this.ak.setImageBitmap(this.aM);
            this.an.setImageBitmap(this.aM);
            this.aq.setImageBitmap(this.aM);
            this.at.setImageBitmap(this.aM);
            this.aw.setImageBitmap(this.aM);
            this.az.setImageBitmap(this.aM);
            this.aJ.add(this.f6720q);
            this.aJ.add(this.f6723t);
            this.aJ.add(this.f6726w);
            this.aJ.add(this.f6729z);
            this.aJ.add(this.f6680C);
            this.aJ.add(this.f6683F);
            this.aJ.add(this.f6686I);
            this.aJ.add(this.f6689L);
            this.aJ.add(this.f6692O);
            this.aJ.add(this.f6695R);
            this.aJ.add(this.f6698U);
            this.aJ.add(this.f6701X);
            this.aJ.add(this.aa);
            this.aJ.add(this.ad);
            this.aJ.add(this.ag);
            this.aJ.add(this.aj);
            this.aJ.add(this.am);
            this.aJ.add(this.ap);
            this.aJ.add(this.as);
            this.aJ.add(this.av);
            this.aJ.add(this.ay);
            m7880a(0);
            Intent intent = getIntent();
            if (intent != null && intent.hasExtra("dto")) {
                this.aL = (ActivityDTO) intent.getSerializableExtra("dto");
            }
            if (this.aL == null) {
                finish();
            }
            this.aO = new LayoutParams(C2801d.a(this, 90.0f), C2801d.a(this, 90.0f));
            m7885b();
            View gPUImageView = new GPUImageView(this);
            gPUImageView.setImage(this.aM);
            this.f6716m.addView(gPUImageView, new LayoutParams(1, 1));
            List arrayList = new ArrayList(5);
            for (FilterType a : FilterTools.a()) {
                arrayList.add(FilterTools.a(this, a));
            }
            getAsyncTaskQueue().a(new WatermarkGalleryActivity$1(this, arrayList, gPUImageView), new Void[0]);
            gPUImageView = new GPUImageView(this);
            gPUImageView.setImage(this.aM);
            this.f6716m.addView(gPUImageView, new LayoutParams(1, 1));
            arrayList = new ArrayList(5);
            for (FilterType a2 : FilterTools.b()) {
                arrayList.add(FilterTools.a(this, a2));
            }
            getAsyncTaskQueue().a(new WatermarkGalleryActivity$5(this, arrayList, gPUImageView), new Void[0]);
            gPUImageView = new GPUImageView(this);
            gPUImageView.setImage(this.aM);
            this.f6716m.addView(gPUImageView, new LayoutParams(1, 1));
            arrayList = new ArrayList(5);
            for (FilterType a22 : FilterTools.c()) {
                arrayList.add(FilterTools.a(this, a22));
            }
            getAsyncTaskQueue().a(new WatermarkGalleryActivity$6(this, arrayList, gPUImageView), new Void[0]);
            gPUImageView = new GPUImageView(this);
            gPUImageView.setImage(this.aM);
            this.f6716m.addView(gPUImageView, new LayoutParams(1, 1));
            arrayList = new ArrayList(5);
            for (FilterType a222 : FilterTools.d()) {
                arrayList.add(FilterTools.a(this, a222));
            }
            getAsyncTaskQueue().a(new WatermarkGalleryActivity$7(this, arrayList, gPUImageView), new Void[0]);
        }
    }

    public void onClick(View view) {
        boolean z = true;
        int i = (this.aE * 2) + this.aF;
        Animation translateAnimation;
        switch (view.getId()) {
            case C1373R.id.activity_watermark_gallery_back:
                setResult(-1);
                finish();
                return;
            case C1373R.id.activity_watermark_gallery_reverse:
                if (this.aH != null) {
                    C2441a c2441a = this.aH;
                    if (this.aH.a()) {
                        z = false;
                    }
                    c2441a.setReverseMode(z);
                    this.aH.performClick();
                    return;
                }
                return;
            case C1373R.id.activity_watermark_gallery_rotate:
                this.aG = (this.aG + 90) % 360;
                if (this.aH != null) {
                    this.aH.setRotation((float) this.aG);
                }
                if (this.aI != null) {
                    this.aI.setRotation((float) this.aG);
                }
                this.f6711h.a(this.aG);
                return;
            case C1373R.id.activity_watermark_gallery_next:
                C2580w.a(this, "", "click_ridding_history_share_digital_watermarking_next");
                m7888c();
                return;
            case C1373R.id.activity_watermark_gallery_tab_title1:
                C2580w.a(this, "", "click_ridding_history_share_digital_watermarking_chose_filter");
                translateAnimation = new TranslateAnimation((float) (i * 0), (float) (i * 0), 0.0f, 0.0f);
                translateAnimation.setFillAfter(true);
                translateAnimation.setDuration(300);
                this.f6719p.startAnimation(translateAnimation);
                this.f6713j.setSelected(true);
                this.f6714k.setSelected(false);
                this.f6706c.setEnabled(false);
                m7894e();
                return;
            case C1373R.id.activity_watermark_gallery_tab_title2:
                C2580w.a(this, "", "click_ridding_history_share_digital_watermarking_chose_sticker");
                translateAnimation = new TranslateAnimation((float) (i * 1), (float) (i * 1), 0.0f, 0.0f);
                translateAnimation.setFillAfter(true);
                translateAnimation.setDuration(300);
                this.f6719p.startAnimation(translateAnimation);
                this.f6713j.setSelected(false);
                this.f6714k.setSelected(true);
                this.f6706c.setEnabled(true);
                m7891d();
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter0:
                m7880a(0);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter1:
                m7880a(1);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter2:
                m7880a(2);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter3:
                m7880a(3);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter4:
                m7880a(4);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter5:
                m7880a(5);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter6:
                m7880a(6);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter7:
                m7880a(7);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter8:
                m7880a(8);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter9:
                m7880a(9);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter10:
                m7880a(10);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter11:
                m7880a(11);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter12:
                m7880a(12);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter13:
                m7880a(13);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter14:
                m7880a(14);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter15:
                m7880a(15);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter16:
                m7880a(16);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter17:
                m7880a(17);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter18:
                m7880a(18);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter19:
                m7880a(19);
                return;
            case C1373R.id.activity_watermark_gallery_filter_filter20:
                m7880a(20);
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 2:
                if (-1 == i2) {
                    setResult(-1, intent);
                    finish();
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected void onResume() {
        this.f6712i.setVisibility(4);
        super.onResume();
    }

    /* renamed from: b */
    private void m7885b() {
        String str;
        List arrayList = new ArrayList();
        String str2 = "";
        if (Locale.getDefault().getLanguage().equals("zh")) {
            str = "http://bazaar.speedx.com/watermarks/watermark_zh_android.json?ver=" + System.currentTimeMillis();
        } else {
            str = "http://bazaar.speedx.com/watermarks/watermark_en_android.json?ver=" + System.currentTimeMillis();
        }
        Request jsonArrayRequest = new JsonArrayRequest(str, new WatermarkGalleryActivity$8(this, arrayList), new WatermarkGalleryActivity$9(this));
        jsonArrayRequest.setShouldCache(false);
        this.aN.add(jsonArrayRequest);
    }

    /* renamed from: a */
    public void m7915a(List<C2412b> list) {
        if (list != null && list.size() != 0) {
            boolean z;
            if (Locale.getDefault().getLanguage().equals("zh")) {
                z = true;
            } else {
                z = false;
            }
            LayoutParams layoutParams = new LinearLayout.LayoutParams(C2801d.a(this, 90.0f), C2801d.a(this, 90.0f));
            layoutParams.setMargins(C2801d.a(this, 4.0f), C2801d.a(this, 4.0f), C2801d.a(this, 4.0f), C2801d.a(this, 4.0f));
            for (int i = 0; i < list.size(); i++) {
                LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this).inflate(C1373R.layout.activity_watermark_gallery_item_sticker, null);
                this.f6717n.addView(linearLayout);
                LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(C1373R.id.activity_watermark_gallery_lv_item_sticker_sticker);
                ImageView imageView = (ImageView) linearLayout.findViewById(C1373R.id.activity_watermark_gallery_lv_item_sticker_bar);
                List c = ((C2412b) list.get(i)).c();
                C2441a c2441a = new C2441a(this);
                c2441a.setIsChineseVersion(z);
                c2441a.setActivityDto(this.aL);
                c2441a.setLayoutParams(layoutParams);
                c2441a.setCover(this.aM);
                c2441a.setWaterMark((C2412b) list.get(i));
                linearLayout2.addView(c2441a);
                this.aK.add(imageView);
                if (c != null && c.size() > 0) {
                    for (int i2 = 0; i2 < c.size(); i2++) {
                        m7914a(new C2414c((C2414c) c.get(i2)), c2441a);
                    }
                }
                c2441a.setOnClickListener(new WatermarkGalleryActivity$10(this, i, c2441a, z, layoutParams, list, c));
            }
        }
    }

    /* renamed from: a */
    private void m7880a(int i) {
        for (View selected : this.aJ) {
            selected.setSelected(false);
        }
        View selected2 = (View) this.aJ.get(i);
        selected2.setSelected(true);
        m7882a(FilterTools.a(this, (FilterType) selected2.getTag()));
        this.f6711h.requestRender();
    }

    /* renamed from: b */
    private void m7886b(int i) {
        for (View selected : this.aK) {
            selected.setSelected(false);
        }
        ((View) this.aK.get(i)).setSelected(true);
    }

    /* renamed from: c */
    private void m7888c() {
        C2496a c2496a = new C2496a(this);
        c2496a.setMessage(getString(C1373R.string.saving));
        c2496a.show();
        this.f6712i.setImageBitmap(this.f6711h.getBitmapWithFilterApplied());
        this.f6712i.setVisibility(0);
        String c = C2553b.c(C2553b.b(this.f6710g));
        c2496a.dismiss();
        Toasts.show(this, C1373R.string.activity_finished_share_sdcard_success);
        Intent intent = new Intent(this, WatermarkFinishedActivity.class);
        intent.putExtra("path", c);
        startActivityForResult(intent, 2);
    }

    /* renamed from: d */
    private void m7891d() {
        this.f6715l.setVisibility(8);
        Animation translateAnimation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        Animation animationSet = new AnimationSet(this, null);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(300);
        animationSet.setAnimationListener(new WatermarkGalleryActivity$11(this));
        this.f6718o.startAnimation(animationSet);
    }

    /* renamed from: e */
    private void m7894e() {
        this.f6718o.setVisibility(8);
        Animation translateAnimation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        Animation animationSet = new AnimationSet(this, null);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(300);
        animationSet.setAnimationListener(new WatermarkGalleryActivity$12(this));
        this.f6715l.startAnimation(animationSet);
    }

    /* renamed from: a */
    private void m7882a(C5421v c5421v) {
        if (this.aC == null || !(c5421v == null || this.aC.getClass().equals(c5421v.getClass()))) {
            this.aC = c5421v;
            this.f6711h.setFilter(this.aC);
            this.aD = new C2468a(this.aC);
        }
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (this.aD != null) {
            this.aD.a(i);
        }
        this.f6711h.requestRender();
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    /* renamed from: a */
    public void m7914a(C2414c c2414c, C2441a c2441a) {
        m7889c(c2414c, c2441a);
        m7892d(c2414c, c2441a);
    }

    /* renamed from: c */
    private void m7889c(C2414c c2414c, C2441a c2441a) {
        Picasso.with(this).load(c2414c.b()).into(new WatermarkGalleryActivity$2(this, c2414c, c2441a));
    }

    /* renamed from: d */
    private void m7892d(C2414c c2414c, C2441a c2441a) {
        Picasso.with(this).load(c2414c.a()).into(new WatermarkGalleryActivity$3(this, c2414c, c2441a));
    }

    /* renamed from: b */
    public void m7916b(C2414c c2414c, C2441a c2441a) {
        if (c2414c != null) {
            runOnUiThread(new WatermarkGalleryActivity$4(this, c2441a, c2414c));
        }
    }
}
