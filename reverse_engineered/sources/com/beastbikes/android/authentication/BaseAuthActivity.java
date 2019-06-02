package com.beastbikes.android.authentication;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import cn.sharesdk.facebook.Facebook;
import cn.sharesdk.google.GooglePlus;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.twitter.Twitter;
import cn.sharesdk.wechat.friends.Wechat;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@C1457a(a = "登录/注册基类")
public abstract class BaseAuthActivity extends BaseSignActivity<C1543c> implements OnPageChangeListener, OnClickListener {
    /* renamed from: a */
    public Button f4003a;
    /* renamed from: b */
    public Button f4004b;
    /* renamed from: g */
    private ViewPager f4005g;
    /* renamed from: h */
    private View f4006h;
    /* renamed from: i */
    private View f4007i;
    /* renamed from: j */
    private TextView f4008j;
    /* renamed from: k */
    private ArrayList<View> f4009k = new ArrayList();

    /* renamed from: b */
    public abstract void mo2735b();

    public abstract void onClickEmail(View view);

    public abstract void onClickPhone(View view);

    /* renamed from: c */
    protected /* synthetic */ C1542d m5271c() {
        return m5266a();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1373R.layout.activity_auth_base);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setBackgroundDrawable(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
        }
        m5264r();
        m5265s();
        e(18);
    }

    /* renamed from: r */
    private void m5264r() {
        this.f4003a = (Button) findViewById(C1373R.id.btn_auth_by_phone);
        this.f4004b = (Button) findViewById(C1373R.id.btn_auth_by_email);
        this.f4005g = (ViewPager) findViewById(C1373R.id.viewPager_auth_third_party);
        this.f4006h = findViewById(C1373R.id.view_auth_indicator_1);
        this.f4007i = findViewById(C1373R.id.view_auth_indicator_2);
        this.f4006h.setSelected(true);
        this.f4007i.setSelected(false);
        this.f4008j = (TextView) findViewById(C1373R.id.tv_change_auth_way);
        this.f4008j.getPaint().setFlags(8);
        LayoutInflater layoutInflater = getLayoutInflater();
        View inflate = layoutInflater.inflate(C1373R.layout.authentication_sign_by_qq_weibo_weixin, null);
        View inflate2 = layoutInflater.inflate(C1373R.layout.authentication_sign_by_facebook_twitter_googleplus, null);
        if (C1849a.a()) {
            this.f4009k.add(inflate);
            this.f4009k.add(inflate2);
        } else {
            this.f4009k.add(inflate2);
            this.f4009k.add(inflate);
        }
        ImageButton imageButton = (ImageButton) inflate.findViewById(C1373R.id.authentication_activity_auth_weixin);
        ImageButton imageButton2 = (ImageButton) inflate.findViewById(C1373R.id.authentication_activity_auth_tencent);
        ImageButton imageButton3 = (ImageButton) inflate.findViewById(C1373R.id.authentication_activity_auth_weibo);
        imageButton.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        imageButton2 = (ImageButton) inflate2.findViewById(C1373R.id.authentication_activity_auth_twitter);
        imageButton3 = (ImageButton) inflate2.findViewById(C1373R.id.authentication_activity_auth_googleplus);
        ((ImageButton) inflate2.findViewById(C1373R.id.authentication_activity_auth_facebook)).setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        this.f4005g.setAdapter(new BaseAuthActivity$a(this, null));
    }

    /* renamed from: s */
    private void m5265s() {
        this.f4003a.setOnClickListener(this);
        this.f4004b.setOnClickListener(this);
        this.f4005g.addOnPageChangeListener(this);
        this.f4008j.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.btn_auth_by_phone:
                onClickPhone(view);
                return;
            case C1373R.id.btn_auth_by_email:
                onClickEmail(view);
                return;
            case C1373R.id.tv_change_auth_way:
                mo2735b();
                return;
            case C1373R.id.authentication_activity_auth_facebook:
                a(Facebook.NAME, 64);
                return;
            case C1373R.id.authentication_activity_auth_twitter:
                a(Twitter.NAME, 32);
                return;
            case C1373R.id.authentication_activity_auth_googleplus:
                a(GooglePlus.NAME, 128);
                return;
            case C1373R.id.authentication_activity_auth_weibo:
                a(SinaWeibo.NAME, 4);
                return;
            case C1373R.id.authentication_activity_auth_weixin:
                a(Wechat.NAME, 16);
                return;
            case C1373R.id.authentication_activity_auth_tencent:
                a(QQ.NAME, 8);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    protected C1543c m5266a() {
        return new C1543c((C1530e) new WeakReference(this).get());
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (i == 0) {
            this.f4006h.setSelected(true);
            this.f4007i.setSelected(false);
            return;
        }
        this.f4006h.setSelected(false);
        this.f4007i.setSelected(true);
    }

    public void onPageScrollStateChanged(int i) {
    }

    /* renamed from: a */
    public void m5267a(int i) {
        this.f4008j.setText(i);
    }

    /* renamed from: a */
    public void m5268a(CharSequence charSequence) {
        this.f4003a.setText(charSequence);
    }

    /* renamed from: b */
    public void m5270b(CharSequence charSequence) {
        this.f4004b.setText(charSequence);
    }
}
