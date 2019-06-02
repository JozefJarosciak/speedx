package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.locale.googlemaputils.C1821c;
import com.beastbikes.android.locale.googlemaputils.C1855a;
import com.beastbikes.android.locale.googlemaputils.C1856b;
import com.beastbikes.android.locale.p104a.C1823a;
import com.beastbikes.android.locale.p104a.C1848b;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.utils.C2558g;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.squareup.picasso.Picasso;

@C1457a(a = "个人设置  -- 入口为登录/注册")
@C1459b(a = 2130903167)
public class ProfileSettingsActivity extends BaseUserInfoSettingsActivity implements C1823a, C1821c {
    @C1458a(a = 2131755873)
    /* renamed from: d */
    private ImageView f6570d;
    @C1458a(a = 2131755874)
    /* renamed from: e */
    private EditText f6571e;
    @C1458a(a = 2131755875)
    /* renamed from: f */
    private TextView f6572f;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        C1848b.a().a(mo2808a(), this);
        registerForContextMenu(this.f6570d);
        m7776i();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C1373R.menu.menu_with_one_title_without_icon, menu);
        menu.findItem(C1373R.id.menu_with_one_title_without_icon).setTitle(C1373R.string.str_next);
        return true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.img_profile_settings_avatar:
                openContextMenu(view);
                return;
            case C1373R.id.edit_profile_settings_nickname:
                this.f6571e.requestFocus();
                return;
            case C1373R.id.tv_profile_settings_region:
                startActivityForResult(new Intent(this, SearchRegionActivity.class), 5);
                return;
            default:
                return;
        }
    }

    public void onBackPressed() {
    }

    protected void onDestroy() {
        unregisterForContextMenu(this.f6570d);
        super.onDestroy();
    }

    /* renamed from: a */
    public void m7780a(ProfileDTO profileDTO) {
        Intent intent = new Intent(this, TrainTargetSetActivity.class);
        intent.putExtra("enter_type", 0);
        startActivity(intent);
        finish();
    }

    /* renamed from: c */
    public String mo2804c() {
        return this.f6571e.getText().toString().trim();
    }

    /* renamed from: d */
    public boolean m7784d() {
        return true;
    }

    /* renamed from: a */
    public void mo2805a(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f6570d.setImageResource(C1373R.drawable.ic_avatar);
        } else {
            Picasso.with(this).load("file://" + str).fit().centerCrop().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).into(this.f6570d);
        }
    }

    /* renamed from: b */
    public void mo2806b(String str) {
        this.f6572f.setText(str);
    }

    /* renamed from: i */
    private void m7776i() {
        this.f6570d.setOnClickListener(this);
        this.f6572f.setOnClickListener(this);
    }

    /* renamed from: a */
    public void m7777a(Location location) {
        if (C1849a.a()) {
            m7775a(new LatLng(location.getLatitude(), location.getLongitude()));
        } else {
            new C1855a().a(mo2808a(), getRequestQueueFactory(), location.getLatitude(), location.getLongitude(), this);
        }
    }

    public void e_() {
    }

    /* renamed from: a */
    private void m7775a(LatLng latLng) {
        AsyncTask profileSettingsActivity$1 = new ProfileSettingsActivity$1(this, new C2389c(this));
        getAsyncTaskQueue().a(profileSettingsActivity$1, new String[]{latLng.getLatitude() + "," + latLng.getLongitude()});
    }

    /* renamed from: a */
    public void m7779a(C1856b c1856b) {
        String[] split = c1856b.a().split(",");
        String str = "";
        String str2 = "";
        String str3 = "";
        switch (split.length) {
            case 1:
                str = split[0];
                break;
            case 2:
                str2 = split[0];
                str = split[1];
                break;
            case 3:
                str2 = split[0];
                str3 = split[1];
                str = split[2];
                break;
        }
        this.f6572f.setText(C2558g.a(str2, str3, str));
    }

    /* renamed from: a */
    public void m7778a(VolleyError volleyError) {
    }
}
