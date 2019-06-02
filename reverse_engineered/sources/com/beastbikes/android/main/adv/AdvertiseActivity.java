package com.beastbikes.android.main.adv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.utils.C2550a;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1457a(a = "广告页")
@C1459b(a = 2130903073)
public class AdvertiseActivity extends SessionFragmentActivity {
    @C1458a(a = 2131755329)
    /* renamed from: a */
    private ImageView f4470a;
    @C1458a(a = 2131755330)
    /* renamed from: b */
    private LinearLayout f4471b;
    @C1458a(a = 2131755331)
    /* renamed from: c */
    private TextView f4472c;
    /* renamed from: d */
    private AdvertiseActivity$a f4473d;
    /* renamed from: e */
    private String f4474e = "";

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        this.f4474e = getIntent().getStringExtra("url");
        Bitmap a = C2550a.a(getIntent().getStringExtra("img_url"), this);
        if (a == null) {
            finish();
            return;
        }
        this.f4470a.setImageBitmap(a);
        this.f4473d = new AdvertiseActivity$a(this, 4000, 1000);
        this.f4473d.start();
        this.f4470a.setOnClickListener(new AdvertiseActivity$1(this, a));
        this.f4471b.setOnClickListener(new AdvertiseActivity$2(this));
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 4:
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 4:
                return true;
            default:
                return super.onKeyUp(i, keyEvent);
        }
    }

    public void finish() {
        super.finish();
        if (this.f4473d != null) {
            this.f4473d.cancel();
        }
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    private void m5833a() {
        if (!TextUtils.isEmpty(this.f4474e)) {
            Uri parse = Uri.parse(this.f4474e);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(parse);
            startActivity(intent);
        }
    }
}
