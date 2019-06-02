package com.beastbikes.android.modules.user.ui.binding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.user.ui.binding.widget.C2520d;
import com.beastbikes.android.modules.user.ui.binding.widget.C2520d.C2508c;
import com.beastbikes.android.modules.user.ui.binding.widget.C2523e;
import com.beastbikes.android.modules.user.ui.binding.widget.CountryListView;

public class CountryPageActivity extends SessionFragmentActivity implements C2508c {
    /* renamed from: a */
    private CountryListView f11893a;

    /* renamed from: com.beastbikes.android.modules.user.ui.binding.CountryPageActivity$1 */
    class C25071 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ CountryPageActivity f11892a;

        /* renamed from: com.beastbikes.android.modules.user.ui.binding.CountryPageActivity$1$1 */
        class C25061 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C25071 f11891a;

            C25061(C25071 c25071) {
                this.f11891a = c25071;
            }

            public void run() {
                this.f11891a.f11892a.setContentView(C1373R.layout.country_list_page);
                this.f11891a.f11892a.f11893a = (CountryListView) this.f11891a.f11892a.findViewById(C1373R.id.clCountry);
                this.f11891a.f11892a.f11893a.setOnItemClickListener(this.f11891a.f11892a);
            }
        }

        C25071(CountryPageActivity countryPageActivity) {
            this.f11892a = countryPageActivity;
        }

        public void run() {
            this.f11892a.runOnUiThread(new C25061(this));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle(getString(C1373R.string.activity_country_page_title));
        }
        C2523e.m12668a(this, new C25071(this));
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    /* renamed from: a */
    public void mo3512a(C2520d c2520d, View view, int i, int i2) {
        if (i2 >= 0) {
            String[] a = this.f11893a.m12620a(i, i2);
            Intent intent = new Intent();
            if (a != null) {
                intent.putExtra("code", a[1]);
            }
            setResult(-1, intent);
            finish();
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }
}
