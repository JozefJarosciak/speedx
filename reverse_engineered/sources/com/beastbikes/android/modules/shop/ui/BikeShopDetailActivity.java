package com.beastbikes.android.modules.shop.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.locale.p104a.C1848b;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.shop.dto.BikeShopInfoDTO;
import com.beastbikes.android.modules.shop.dto.BikeShopTagInfoDto;
import com.beastbikes.android.modules.shop.p073a.C2327a;
import com.beastbikes.android.utils.C2562j;
import com.beastbikes.android.widget.p168e.C2655d;
import com.beastbikes.android.widget.p168e.p169a.C2642c;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.DensityUtil;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;

@C1459b(a = 2130903080)
@C1460c(a = 2131820557)
public class BikeShopDetailActivity extends SessionFragmentActivity implements OnClickListener, C1371a {
    /* renamed from: A */
    private float f6021A;
    /* renamed from: B */
    private C2655d f6022B;
    @C1458a(a = 2131755364)
    /* renamed from: a */
    public TextView f6023a;
    @C1458a(a = 2131755363)
    /* renamed from: b */
    public TextView f6024b;
    @C1458a(a = 2131755366)
    /* renamed from: c */
    public TextView f6025c;
    @C1458a(a = 2131755383)
    /* renamed from: d */
    private LinearLayout f6026d;
    @C1458a(a = 2131755377)
    /* renamed from: e */
    private View f6027e;
    @C1458a(a = 2131755378)
    /* renamed from: f */
    private ImageView f6028f;
    @C1458a(a = 2131755380)
    /* renamed from: g */
    private TextView f6029g;
    @C1458a(a = 2131755382)
    /* renamed from: h */
    private TextView f6030h;
    @C1458a(a = 2131755393)
    /* renamed from: i */
    private TextView f6031i;
    @C1458a(a = 2131755392)
    /* renamed from: j */
    private CircleImageView f6032j;
    @C1458a(a = 2131755390)
    /* renamed from: k */
    private LinearLayout f6033k;
    @C1458a(a = 2131755398)
    /* renamed from: l */
    private TextView f6034l;
    @C1458a(a = 2131755395)
    /* renamed from: m */
    private TextView f6035m;
    @C1458a(a = 2131755396)
    /* renamed from: n */
    private TextView f6036n;
    @C1458a(a = 2131755389)
    /* renamed from: o */
    private LinearLayout f6037o;
    @C1458a(a = 2131755399)
    /* renamed from: p */
    private LinearLayout f6038p;
    @C1458a(a = 2131755388)
    /* renamed from: q */
    private View f6039q;
    @C1458a(a = 2131755391)
    /* renamed from: r */
    private View f6040r;
    @C1458a(a = 2131755386)
    /* renamed from: s */
    private TextView f6041s;
    @C1458a(a = 2131755387)
    /* renamed from: t */
    private TextView f6042t;
    @C1458a(a = 2131755385)
    /* renamed from: u */
    private TextView f6043u;
    @C1458a(a = 2131755379)
    /* renamed from: v */
    private TextView f6044v;
    /* renamed from: w */
    private long f6045w;
    /* renamed from: x */
    private C2327a f6046x;
    /* renamed from: y */
    private BikeShopInfoDTO f6047y;
    /* renamed from: z */
    private float f6048z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.f6033k.setVisibility(intent.getBooleanExtra("show_enter_club", false) ? 0 : 8);
        this.f6045w = intent.getLongExtra("bike_shop_id", -1);
        if (this.f6045w == -1) {
            finish();
        }
        this.f6046x = new C2327a(this);
        SharedPreferences sharedPreferences = getSharedPreferences(C1848b.a().getClass().getName(), 0);
        this.f6048z = Float.parseFloat(sharedPreferences.getString("beast.location.manager.lat", "0"));
        this.f6021A = Float.parseFloat(sharedPreferences.getString("beast.location.manager.lon", "0"));
        m7241b();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_share:
                m7247a();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    /* renamed from: b */
    private void m7241b() {
        this.f6042t.setOnClickListener(this);
        this.f6040r.setOnClickListener(this);
        this.f6041s.setOnClickListener(this);
        m7243c();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        if (this.f6047y != null) {
            if (view == this.f6040r) {
                if (!TextUtils.isEmpty(this.f6047y.getClubId())) {
                    C2562j.a(this, this.f6047y.getClubId());
                }
            } else if (view == this.f6041s) {
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("geo:" + this.f6047y.getLatitude() + "," + this.f6047y.getLongitude() + "?q=" + this.f6047y.getAddress())));
                } catch (Exception e) {
                    Toasts.show(this, C1373R.string.bike_shop_nav_no_map_tip);
                }
            } else if (view == this.f6042t) {
                Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + this.f6047y.getTelephone()));
                intent.setFlags(268435456);
                startActivity(intent);
            }
        }
    }

    /* renamed from: c */
    private void m7243c() {
        getAsyncTaskQueue().a(new BikeShopDetailActivity$1(this), new Void[0]);
    }

    /* renamed from: a */
    public void m7247a() {
        if (this.f6047y != null) {
            if (this.f6022B == null) {
                C2642c c2642c = new C2642c();
                c2642c.b("【" + this.f6047y.getName() + "】" + getString(C1373R.string.shop_detail));
                c2642c.d(getString(C1373R.string.bike_shop_share_content));
                c2642c.a("http://global.speedx.com/image/shareLogo.jpg");
                c2642c.c("https://www.speedx.com/app/shop/shareshop.html?shopId=" + this.f6047y.getShopId());
                c2642c.f(c2642c.d());
                c2642c.e(c2642c.d());
                this.f6022B = new C2655d(this, c2642c, "车店");
            }
            this.f6022B.showAtLocation(this.f6027e, 81, 0, 0);
        }
    }

    /* renamed from: d */
    private void m7245d() {
        if (this.f6047y != null) {
            String str;
            String valueOf;
            if (!TextUtils.isEmpty(this.f6047y.getLogo())) {
                Picasso.with(this).load(this.f6047y.getLogo()).fit().placeholder((int) C1373R.drawable.ic_launcher).error((int) C1373R.drawable.ic_launcher).centerCrop().into(this.f6028f);
            }
            if (!TextUtils.isEmpty(this.f6047y.getClubLogo())) {
                Picasso.with(this).load(this.f6047y.getClubLogo()).fit().placeholder((int) C1373R.drawable.ic_launcher).error((int) C1373R.drawable.ic_launcher).centerCrop().into(this.f6032j);
            }
            if (TextUtils.isEmpty(this.f6047y.getClubName()) || TextUtils.isEmpty(this.f6047y.getClubId())) {
                this.f6033k.setVisibility(8);
            } else {
                this.f6031i.setText(this.f6047y.getClubName());
            }
            this.f6029g.setText(this.f6047y.getName());
            double range;
            DecimalFormat decimalFormat;
            if (C1849a.b(this)) {
                range = this.f6047y.getRange() / 1000.0d;
                if (range < 10.0d) {
                    decimalFormat = new DecimalFormat("#.#");
                } else {
                    decimalFormat = new DecimalFormat("#");
                }
                this.f6030h.setText(decimalFormat.format(range) + getResources().getString(C1373R.string.str_mileage_unit_km));
            } else {
                range = C1849a.a(this.f6047y.getRange()) / 1000.0d;
                if (range < 10.0d) {
                    decimalFormat = new DecimalFormat("#.#");
                } else {
                    decimalFormat = new DecimalFormat("#");
                }
                this.f6030h.setText(decimalFormat.format(range) + getResources().getString(C1373R.string.str_mileage_unit_mile));
            }
            int openHour = this.f6047y.getOpenHour();
            int i = openHour / 100;
            openHour %= 100;
            String str2 = "";
            str2 = "";
            if (openHour < 10) {
                str = "0" + openHour;
            } else {
                str = "" + openHour;
            }
            int closeHour = this.f6047y.getCloseHour();
            int i2 = closeHour / 100;
            closeHour %= 100;
            String str3 = "";
            if (closeHour < 10) {
                str2 = "0" + closeHour;
            } else {
                str2 = "" + closeHour;
            }
            str3 = "";
            if (i < 10) {
                str3 = String.valueOf("0" + i + ":" + str);
            } else {
                str3 = String.valueOf(i + ":" + str);
            }
            if (i2 < 10) {
                valueOf = String.valueOf("0" + i2 + ":" + str2);
            } else {
                valueOf = String.valueOf(i2 + ":" + str2);
            }
            this.f6038p.setVisibility(8);
            switch (this.f6047y.getLevel()) {
                case 0:
                    this.f6044v.setVisibility(8);
                    break;
                case 1:
                    this.f6044v.setVisibility(0);
                    this.f6044v.setBackgroundResource(C1373R.drawable.bg_bike_shop_service_providers);
                    this.f6044v.setText(C1373R.string.str_bicycle_shop_service_level);
                    this.f6044v.setTextColor(Color.parseColor("#713500"));
                    break;
                case 2:
                    this.f6044v.setVisibility(0);
                    this.f6044v.setBackgroundResource(C1373R.drawable.bg_bike_shop_dealers);
                    this.f6044v.setText(C1373R.string.str_bicycle_shop_dealer_level);
                    this.f6044v.setTextColor(Color.parseColor("#ffffff"));
                    break;
            }
            BikeShopTagInfoDto tagInfo = this.f6047y.getTagInfo();
            if (tagInfo != null) {
                this.f6026d.setVisibility(0);
                List arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                arrayList2.add(this.f6024b);
                arrayList2.add(this.f6043u);
                arrayList2.add(this.f6023a);
                arrayList2.add(this.f6025c);
                if (tagInfo.isFix()) {
                    arrayList.add(getString(C1373R.string.str_bicycle_shop_tag_fix));
                }
                if (tagInfo.isCare()) {
                    arrayList.add(getString(C1373R.string.str_bicycle_shop_tag_care));
                }
                if (tagInfo.isOfficeExperience()) {
                    arrayList.add(getString(C1373R.string.str_bicycle_shop_tag_offline));
                }
                if (tagInfo.isRent()) {
                    arrayList.add(getString(C1373R.string.str_bicycle_shop_tag_rent));
                }
                for (i2 = 0; i2 < arrayList.size(); i2++) {
                    ((TextView) arrayList2.get(i2)).setText((CharSequence) arrayList.get(i2));
                    ((TextView) arrayList2.get(i2)).setVisibility(0);
                }
            } else {
                this.f6026d.setVisibility(8);
            }
            this.f6035m.setText(getResources().getString(C1373R.string.opening_hours) + " " + str3 + HelpFormatter.DEFAULT_OPT_PREFIX + valueOf);
            str = "";
            if (!(TextUtils.isEmpty(this.f6047y.getCitygetProvince()) || "null".equals(this.f6047y.getCitygetProvince()))) {
                str = this.f6047y.getCitygetProvince();
            }
            if (!(TextUtils.isEmpty(this.f6047y.getCity()) || "null".equals(this.f6047y.getCity()))) {
                str = str + this.f6047y.getCity();
            }
            if (!(TextUtils.isEmpty(this.f6047y.getDistrict()) || "null".equals(this.f6047y.getDistrict()))) {
                str = str + this.f6047y.getDistrict();
            }
            this.f6041s.setText(str + this.f6047y.getAddress());
            if (!(TextUtils.isEmpty(this.f6047y.getTelephone()) || "null".equals(this.f6047y.getTelephone()))) {
                this.f6042t.setText(this.f6047y.getTelephone());
            }
            if (!(TextUtils.isEmpty(this.f6047y.getDescription()) || "null".equals(this.f6047y.getDescription()))) {
                this.f6034l.setText(this.f6047y.getDescription());
            }
            if (TextUtils.isEmpty(this.f6047y.getClubId())) {
                this.f6033k.setVisibility(8);
            }
            if (TextUtils.equals("null", this.f6047y.getMainProducts())) {
                this.f6047y.setMainProducts("");
            }
            this.f6036n.setText(this.f6047y.getMainProducts());
            ArrayList arrayList3 = new ArrayList();
            if (this.f6047y.getPictures() == null || this.f6047y.getPictures().size() <= 0) {
                this.f6039q.setVisibility(8);
                return;
            }
            for (closeHour = 0; closeHour < this.f6047y.getPictures().size(); closeHour++) {
                str = (String) this.f6047y.getPictures().get(closeHour);
                if (!TextUtils.isEmpty(str)) {
                    arrayList3.add(str);
                    ImageView imageView = new ImageView(this);
                    LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtil.dip2px(this, 60.0f), DensityUtil.dip2px(this, 60.0f));
                    layoutParams.setMargins(0, 0, DensityUtil.dip2px(this, 10.0f), 0);
                    imageView.setLayoutParams(layoutParams);
                    imageView.setId(closeHour);
                    imageView.setOnClickListener(new BikeShopDetailActivity$2(this, arrayList3));
                    this.f6037o.addView(imageView);
                    Picasso.with(this).load(str).into(imageView);
                }
            }
        }
    }
}
