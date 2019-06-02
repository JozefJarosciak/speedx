package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2052c;
import com.beastbikes.android.modules.cycling.club.dto.ClubPhotoDTO;
import com.beastbikes.android.modules.cycling.club.ui.p129b.C2138i;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2152d;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2152d.C2091a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903256)
public class ClubImageDetailsActivity extends SessionFragmentActivity implements C2091a {
    @C1458a(a = 2131755520)
    /* renamed from: a */
    private FrameLayout f5133a;
    @C1458a(a = 2131755522)
    /* renamed from: b */
    private FrameLayout f5134b;
    /* renamed from: c */
    private C2138i f5135c;
    /* renamed from: d */
    private C2052c f5136d;
    /* renamed from: e */
    private ClubPhotoDTO f5137e;

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
        this.f5137e = (ClubPhotoDTO) intent.getSerializableExtra("photo");
        if (this.f5137e == null) {
            finish();
            return;
        }
        this.f5136d = new C2052c(this);
        View c2152d = new C2152d(this);
        c2152d.c();
        c2152d.setListener(this);
        this.f5133a.addView(c2152d);
        View inflate = LayoutInflater.from(this).inflate(C1373R.layout.club_photo_item_base, null);
        this.f5134b.addView(inflate);
        this.f5135c = new C2138i(this, inflate, c2152d);
        this.f5135c.setCommentEditView(c2152d);
        this.f5135c.a(this.f5137e);
        m6471a(this.f5137e.getPhotoId());
        m6474b(this.f5137e.getPhotoId());
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    public void m6476a(String str, int i, int i2) {
        getAsyncTaskQueue().a(new ClubImageDetailsActivity$1(this, str, i), new String[0]);
    }

    /* renamed from: a */
    private void m6471a(int i) {
        getAsyncTaskQueue().a(new ClubImageDetailsActivity$2(this, i), new Integer[0]);
    }

    /* renamed from: b */
    private void m6474b(int i) {
        getAsyncTaskQueue().a(new ClubImageDetailsActivity$3(this, i), new Integer[0]);
    }
}
