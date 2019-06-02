package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.p168e.C2643a;
import com.beastbikes.android.widget.p168e.p169a.C2641b;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C2801d;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;
import java.io.File;

@C1457a(a = "水印相机完成页")
@C1459b(a = 2130903215)
public class WatermarkFinishedActivity extends BaseFragmentActivity implements OnClickListener {
    @C1458a(a = 2131756137)
    /* renamed from: A */
    private LinearLayout f6648A;
    /* renamed from: B */
    private String f6649B;
    /* renamed from: C */
    private C2643a f6650C;
    /* renamed from: D */
    private C2641b f6651D;
    /* renamed from: a */
    private final DisplayMetrics f6652a = new DisplayMetrics();
    @C1458a(a = 2131756135)
    /* renamed from: b */
    private ImageView f6653b;
    @C1458a(a = 2131756133)
    /* renamed from: c */
    private TextView f6654c;
    @C1458a(a = 2131756132)
    /* renamed from: d */
    private ImageView f6655d;
    /* renamed from: e */
    private ViewGroup f6656e;
    /* renamed from: f */
    private ImageView f6657f;
    /* renamed from: g */
    private TextView f6658g;
    /* renamed from: h */
    private ViewGroup f6659h;
    /* renamed from: i */
    private ImageView f6660i;
    /* renamed from: j */
    private TextView f6661j;
    /* renamed from: k */
    private ViewGroup f6662k;
    /* renamed from: l */
    private ImageView f6663l;
    /* renamed from: m */
    private TextView f6664m;
    /* renamed from: n */
    private ViewGroup f6665n;
    /* renamed from: o */
    private ImageView f6666o;
    /* renamed from: p */
    private TextView f6667p;
    /* renamed from: q */
    private ViewGroup f6668q;
    /* renamed from: r */
    private ImageView f6669r;
    /* renamed from: s */
    private TextView f6670s;
    /* renamed from: t */
    private ViewGroup f6671t;
    /* renamed from: u */
    private ImageView f6672u;
    /* renamed from: v */
    private TextView f6673v;
    /* renamed from: w */
    private ViewGroup f6674w;
    /* renamed from: x */
    private ImageView f6675x;
    /* renamed from: y */
    private TextView f6676y;
    /* renamed from: z */
    private LayoutInflater f6677z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        getWindowManager().getDefaultDisplay().getMetrics(this.f6652a);
        m7875a();
        int a = this.f6652a.widthPixels - C2801d.a(this, 26.0f);
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("path");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.f6653b.setImageURI(Uri.fromFile(new File(stringExtra)));
                this.f6649B = stringExtra;
                this.f6653b.setLayoutParams(new LayoutParams(a, a));
            }
        }
        this.f6651D = new C2641b();
        this.f6651D.a(this.f6649B);
        this.f6650C = new C2643a(this, this.f6651D);
    }

    /* renamed from: a */
    private void m7875a() {
        this.f6654c.setOnClickListener(this);
        this.f6655d.setOnClickListener(this);
        this.f6653b.setOnClickListener(this);
        this.f6677z = LayoutInflater.from(this);
        this.f6656e = (ViewGroup) this.f6677z.inflate(C1373R.layout.share_menu_item, null);
        this.f6656e.setId(C1373R.id.share_item_wechat_moments);
        this.f6657f = (ImageView) this.f6656e.findViewById(C1373R.id.share_menu_icon);
        this.f6658g = (TextView) this.f6656e.findViewById(C1373R.id.share_menu_name);
        this.f6659h = (ViewGroup) this.f6677z.inflate(C1373R.layout.share_menu_item, null);
        this.f6659h.setId(C1373R.id.share_item_wechat);
        this.f6660i = (ImageView) this.f6659h.findViewById(C1373R.id.share_menu_icon);
        this.f6661j = (TextView) this.f6659h.findViewById(C1373R.id.share_menu_name);
        this.f6662k = (ViewGroup) this.f6677z.inflate(C1373R.layout.share_menu_item, null);
        this.f6662k.setId(C1373R.id.share_item_qq);
        this.f6663l = (ImageView) this.f6662k.findViewById(C1373R.id.share_menu_icon);
        this.f6664m = (TextView) this.f6662k.findViewById(C1373R.id.share_menu_name);
        this.f6665n = (ViewGroup) this.f6677z.inflate(C1373R.layout.share_menu_item, null);
        this.f6665n.setId(C1373R.id.share_item_weibo);
        this.f6666o = (ImageView) this.f6665n.findViewById(C1373R.id.share_menu_icon);
        this.f6667p = (TextView) this.f6665n.findViewById(C1373R.id.share_menu_name);
        this.f6668q = (ViewGroup) this.f6677z.inflate(C1373R.layout.share_menu_item, null);
        this.f6668q.setId(C1373R.id.share_item_facebook);
        this.f6669r = (ImageView) this.f6668q.findViewById(C1373R.id.share_menu_icon);
        this.f6670s = (TextView) this.f6668q.findViewById(C1373R.id.share_menu_name);
        this.f6671t = (ViewGroup) this.f6677z.inflate(C1373R.layout.share_menu_item, null);
        this.f6671t.setId(C1373R.id.share_item_twitter);
        this.f6672u = (ImageView) this.f6671t.findViewById(C1373R.id.share_menu_icon);
        this.f6673v = (TextView) this.f6671t.findViewById(C1373R.id.share_menu_name);
        this.f6674w = (ViewGroup) this.f6677z.inflate(C1373R.layout.share_menu_item, null);
        this.f6674w.setId(C1373R.id.share_item_sdcard);
        this.f6675x = (ImageView) this.f6674w.findViewById(C1373R.id.share_menu_icon);
        this.f6676y = (TextView) this.f6674w.findViewById(C1373R.id.share_menu_name);
        if (C1849a.a()) {
            this.f6648A.addView(this.f6656e);
            this.f6648A.addView(this.f6659h);
            this.f6648A.addView(this.f6662k);
            this.f6648A.addView(this.f6665n);
            this.f6648A.addView(this.f6668q);
            this.f6648A.addView(this.f6671t);
        } else {
            this.f6648A.addView(this.f6668q);
            this.f6648A.addView(this.f6671t);
            this.f6648A.addView(this.f6656e);
            this.f6648A.addView(this.f6659h);
            this.f6648A.addView(this.f6662k);
            this.f6648A.addView(this.f6665n);
        }
        this.f6648A.addView(this.f6674w);
        m7876b();
        this.f6671t.setOnClickListener(this);
        this.f6656e.setOnClickListener(this);
        this.f6659h.setOnClickListener(this);
        this.f6662k.setOnClickListener(this);
        this.f6665n.setOnClickListener(this);
        this.f6668q.setOnClickListener(this);
        this.f6674w.setOnClickListener(this);
    }

    /* renamed from: b */
    private void m7876b() {
        this.f6657f.setImageResource(C1373R.drawable.share_icon_moments);
        this.f6658g.setText(C1373R.string.activity_finished_menu_wechat_friend);
        this.f6660i.setImageResource(C1373R.drawable.share_icon_wechat);
        this.f6661j.setText(C1373R.string.activity_finished_menu_wechat);
        this.f6663l.setImageResource(C1373R.drawable.share_icon_qq);
        this.f6664m.setText(C1373R.string.activity_finished_menu_qq);
        this.f6666o.setImageResource(C1373R.drawable.share_icon_weibo);
        this.f6667p.setText(C1373R.string.activity_finished_menu_weibo);
        this.f6669r.setImageResource(C1373R.drawable.ic_account_facebook_enable);
        this.f6670s.setText(C1373R.string.activity_account_management_facebook_str);
        this.f6672u.setImageResource(C1373R.drawable.ic_account_twitter_enable);
        this.f6673v.setText(C1373R.string.activity_account_management_twitter_str);
        this.f6676y.setText(C1373R.string.activity_finished_menu_save);
        this.f6675x.setImageResource(C1373R.drawable.share_icon_download);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.share_item_facebook:
                C2580w.a(this, "", "click_ridding_history_share_digital_watermarking_share");
                this.f6650C.a(this.f6651D);
                return;
            case C1373R.id.share_item_qq:
                C2580w.a(this, "", "click_ridding_history_share_digital_watermarking_share");
                this.f6650C.e(this.f6651D);
                return;
            case C1373R.id.share_item_sdcard:
                C2580w.a(this, "", "click_ridding_history_share_digital_watermarking_share");
                this.f6650C.a();
                return;
            case C1373R.id.share_item_twitter:
                C2580w.a(this, "", "click_ridding_history_share_digital_watermarking_share");
                this.f6650C.b(this.f6651D);
                return;
            case C1373R.id.share_item_wechat:
                C2580w.a(this, "", "click_ridding_history_share_digital_watermarking_share");
                this.f6650C.c(this.f6651D);
                return;
            case C1373R.id.share_item_wechat_moments:
                C2580w.a(this, "", "click_ridding_history_share_digital_watermarking_share");
                this.f6650C.d(this.f6651D);
                return;
            case C1373R.id.share_item_weibo:
                C2580w.a(this, "", "click_ridding_history_share_digital_watermarking_share");
                this.f6650C.f(this.f6651D);
                return;
            case C1373R.id.activity_watermark_finished_back:
                finish();
                return;
            case C1373R.id.activity_watermark_finished_next:
                Intent intent = getIntent();
                intent.putExtra("file_path", this.f6649B);
                setResult(-1, intent);
                finish();
                return;
            default:
                return;
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }
}
