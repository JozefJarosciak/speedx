package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$c;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.user.dto.MedalDTO;
import com.beastbikes.android.modules.user.ui.p157a.C2495c;
import com.beastbikes.android.modules.user.ui.p157a.C2495c.C2494a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.Iterator;

@C1459b(a = 2130903150)
public class MedalInfoActivity extends SessionFragmentActivity implements OnPageChangeListener, OnClickListener, C2494a {
    @C1458a(a = 2131755804)
    /* renamed from: a */
    private ImageView f6428a;
    @C1458a(a = 2131755803)
    /* renamed from: b */
    private ViewPager f6429b;
    @C1458a(a = 2131755805)
    /* renamed from: c */
    private LinearLayout f6430c;
    /* renamed from: d */
    private LayoutParams f6431d;
    /* renamed from: e */
    private Drawable f6432e;
    /* renamed from: f */
    private int f6433f = 0;
    /* renamed from: g */
    private int f6434g = 0;
    /* renamed from: h */
    private C2495c f6435h;
    /* renamed from: i */
    private ArrayList<MedalDTO> f6436i;
    /* renamed from: j */
    private int f6437j;
    /* renamed from: k */
    private boolean f6438k;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_bottom, C1373R.anim.activity_none);
        this.f6434g = getIntent().getIntExtra("medal_position", 0);
        this.f6436i = (ArrayList) getIntent().getSerializableExtra("medal_list");
        this.f6438k = getIntent().getBooleanExtra("from_push", false);
        if (this.f6436i == null || this.f6436i.isEmpty()) {
            Toasts.show(this, C1373R.string.str_data_error);
            finish();
            return;
        }
        m7656a();
    }

    /* renamed from: a */
    private void m7656a() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(C1373R.dimen.dimen_7);
        this.f6431d = new LayoutParams(dimensionPixelSize, dimensionPixelSize);
        this.f6435h = new C2495c(this, this.f6436i, this.f6438k);
        if (this.f6438k) {
            this.f6435h.a(this);
            this.f6434g = 0;
            m7658c();
            if (this.f6436i.size() > 1) {
                this.f6430c.setVisibility(0);
                this.f6430c.getChildAt(0).setEnabled(true);
            } else {
                this.f6430c.setVisibility(8);
            }
        }
        this.f6429b.setAdapter(this.f6435h);
        this.f6429b.setCurrentItem(this.f6434g);
        m7657b();
    }

    /* renamed from: b */
    private void m7657b() {
        this.f6428a.setOnClickListener(this);
        if (this.f6438k) {
            this.f6429b.addOnPageChangeListener(this);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.activity_medal_info_close:
                finish();
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == this.f6437j && intent != null) {
            Iterator it = this.f6436i.iterator();
            while (it.hasNext()) {
                MedalDTO medalDTO = (MedalDTO) it.next();
                if (medalDTO.getActivityId() == i) {
                    switch (intent.getIntExtra("award_status", 0)) {
                        case 1:
                        case 3:
                            medalDTO.setGiftSituation(3);
                            medalDTO.setStatus(3);
                            break;
                        case 2:
                            medalDTO.setGiftSituation(2);
                            break;
                    }
                    medalDTO.setStatus(3);
                    this.f6435h.a();
                    this.f6435h.notifyDataSetChanged();
                }
            }
            this.f6435h.a();
            this.f6435h.notifyDataSetChanged();
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_bottom);
    }

    /* renamed from: c */
    private void m7658c() {
        int size = this.f6436i.size();
        for (int i = 0; i < size; i++) {
            boolean z;
            LinearLayout linearLayout = this.f6430c;
            if (i == 0) {
                z = true;
            } else {
                z = false;
            }
            linearLayout.addView(m7655a(z));
        }
    }

    /* renamed from: a */
    private View m7655a(boolean z) {
        View view = new View(this);
        this.f6432e = getResources().getDrawable(C1373R.drawable.bg_dot_white_and_gray);
        view.setBackgroundDrawable(this.f6432e);
        view.setEnabled(false);
        if (z) {
            this.f6431d.leftMargin = 0;
        } else {
            this.f6431d.leftMargin = getResources().getDimensionPixelSize(C1373R.dimen.dimen_5);
        }
        view.setLayoutParams(this.f6431d);
        return view;
    }

    /* renamed from: a */
    public void m7659a(MedalDTO medalDTO) {
        if (medalDTO.getActivityId() <= 0 || medalDTO.getGiftId() <= 0) {
            finish();
            return;
        }
        Intent intent = new Intent(this, BrowserActivity.class);
        StringBuilder stringBuilder = new StringBuilder(a$c.f7200a);
        if (medalDTO.getGiftType() != 3 || medalDTO.getGiftSituation() == 2) {
            stringBuilder.append("/app/activity/reward.html?id=");
        } else {
            stringBuilder.append("/app/activity/lottery.html?id=");
        }
        stringBuilder.append(medalDTO.getActivityId());
        intent.setData(Uri.parse(stringBuilder.toString()));
        this.f6437j = medalDTO.getActivityId();
        startActivityForResult(intent, this.f6437j);
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        int size = i % this.f6436i.size();
        this.f6430c.getChildAt(this.f6433f).setEnabled(false);
        this.f6430c.getChildAt(size).setEnabled(true);
        this.f6433f = size;
    }

    public void onPageScrollStateChanged(int i) {
    }
}
