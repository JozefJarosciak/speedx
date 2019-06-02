package com.beastbikes.android.main;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.framework.android.p082a.p083a.C1457a;

@C1457a(a = "引导页")
public class TutorialActivity extends BaseTutorialActivity implements OnPageChangeListener, OnTouchListener {
    /* renamed from: e */
    private static final int[] f4464e = new int[]{C1373R.drawable.ic_tutorial_page_1, C1373R.drawable.ic_tutorial_page_2, C1373R.drawable.ic_tutorial_page_3, C1373R.drawable.ic_tutorial_page_4};
    /* renamed from: b */
    private ViewPager f4465b;
    /* renamed from: c */
    private ViewGroup f4466c;
    /* renamed from: d */
    private int f4467d;
    /* renamed from: f */
    private int f4468f;
    /* renamed from: g */
    private int f4469g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        m5829c();
        this.f4468f = getIntent().getIntExtra("enter_type", 0);
        if (this.f4468f == 1) {
            this.a.setVisibility(4);
        } else {
            this.a.setVisibility(0);
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_none);
    }

    public void onClickJoinNow(View view) {
        mo2761a();
        super.onClickJoinNow(view);
        finish();
    }

    public void onClickLogin(View view) {
        mo2761a();
        super.onClickLogin(view);
        finish();
    }

    /* renamed from: c */
    private void m5829c() {
        this.f4465b = (ViewPager) findViewById(C1373R.id.viewPager_tutorial_page);
        this.f4466c = (ViewGroup) findViewById(C1373R.id.linear_tutorial_page_indicator);
        this.f4465b.addOnPageChangeListener(this);
        this.f4465b.setAdapter(new TutorialActivity$a(this));
        if (this.f4466c.getChildCount() > 0) {
            this.f4466c.getChildAt(0).setSelected(true);
        }
        this.f4465b.setOnTouchListener(this);
    }

    /* renamed from: a */
    public void mo2761a() {
        getSharedPreferences(getPackageName(), 0).edit().putBoolean("has_shown_tutorials", true).apply();
        if (AVUser.getCurrentUser() != null) {
            finish();
        } else {
            PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("km_or_mi", 0).apply();
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.f4469g = (int) motionEvent.getX();
                break;
            case 2:
                if (((float) this.f4469g) - motionEvent.getX() > 100.0f && this.f4467d == f4464e.length - 1) {
                    finish();
                    break;
                }
        }
        return false;
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.f4465b.setCurrentItem(i, true);
        this.f4467d = i;
        int childCount = this.f4466c.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            boolean z;
            View childAt = this.f4466c.getChildAt(i2);
            if (i2 == i) {
                z = true;
            } else {
                z = false;
            }
            childAt.setSelected(z);
        }
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.f4467d = bundle.getInt("selected-index", 0);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("selected-index", this.f4467d);
        super.onSaveInstanceState(bundle);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (4 != i) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.f4468f == 1) {
            return true;
        }
        return false;
    }
}
