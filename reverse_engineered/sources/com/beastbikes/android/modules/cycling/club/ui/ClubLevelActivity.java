package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$c;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.dto.C2065d;
import com.beastbikes.android.modules.cycling.club.dto.C2068g;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.WebActivity;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903278)
public class ClubLevelActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131756355)
    /* renamed from: a */
    CircleImageView f5151a;
    /* renamed from: b */
    ClubLevelActivity$a f5152b;
    /* renamed from: c */
    private ClubInfoCompact f5153c;
    @C1458a(a = 2131756388)
    /* renamed from: d */
    private ListView f5154d;
    @C1458a(a = 2131756386)
    /* renamed from: e */
    private TextView f5155e;
    @C1458a(a = 2131756387)
    /* renamed from: f */
    private TextView f5156f;
    @C1458a(a = 2131756389)
    /* renamed from: g */
    private TextView f5157g;
    @C1458a(a = 2131756385)
    /* renamed from: h */
    private LinearLayout f5158h;
    /* renamed from: i */
    private List<C2065d> f5159i = new ArrayList();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent != null) {
            Serializable serializableExtra = intent.getSerializableExtra("club_info");
            if (serializableExtra != null) {
                this.f5153c = (ClubInfoCompact) serializableExtra;
            }
            this.f5152b = new ClubLevelActivity$a(this, null, this.f5154d, this.f5159i);
            this.f5154d.setAdapter(this.f5152b);
            m6497b();
            m6498c();
            m6494a();
        }
    }

    protected void onResume() {
        super.onResume();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        if (view == this.f5157g) {
            Uri parse = Uri.parse(new StringBuilder(a$c.f7200a).append("/app/club/privilege.html").toString());
            Intent intent = new Intent(this, BrowserActivity.class);
            intent.setData(parse);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setPackage(getPackageName());
            intent.putExtra(WebActivity.EXTRA_TITLE, getString(C1373R.string.clubfeed_level_understand));
            intent.putExtra(WebActivity.EXTRA_ENTER_ANIMATION, C1373R.anim.activity_in_from_right);
            intent.putExtra(WebActivity.EXTRA_EXIT_ANIMATION, C1373R.anim.activity_out_to_right);
            intent.putExtra(WebActivity.EXTRA_NONE_ANIMATION, C1373R.anim.activity_none);
            startActivity(intent);
        }
    }

    /* renamed from: a */
    private void m6494a() {
        this.f5157g.setOnClickListener(this);
        if (this.f5153c == null) {
            return;
        }
        if (TextUtils.isEmpty(this.f5153c.getLogo())) {
            this.f5151a.setImageResource(C1373R.drawable.ic_avatar_club);
        } else {
            Picasso.with(this).load(this.f5153c.getLogo()).fit().centerCrop().error((int) C1373R.drawable.ic_avatar_club).placeholder((int) C1373R.drawable.ic_avatar_club).into(this.f5151a);
        }
    }

    /* renamed from: a */
    private void m6495a(C2068g c2068g) {
        if (c2068g.b() != null) {
            this.f5158h.removeAllViews();
            for (int i = 0; i < c2068g.b().size(); i++) {
                TextView textView = (TextView) LayoutInflater.from(this).inflate(C1373R.layout.club_privileges_item, null);
                textView.setText((CharSequence) c2068g.b().get(i));
                this.f5158h.addView(textView);
            }
            this.f5155e.setText(" LV." + c2068g.a());
            this.f5156f.setText(" LV." + (c2068g.a() + 1));
        }
    }

    /* renamed from: b */
    private void m6497b() {
        getAsyncTaskQueue().a(new ClubLevelActivity$1(this), new Void[0]);
    }

    /* renamed from: c */
    private void m6498c() {
        getAsyncTaskQueue().a(new ClubLevelActivity$2(this), new Void[0]);
    }
}
