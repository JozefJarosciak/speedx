package com.beastbikes.android.modules.cycling.club.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2052c;
import com.beastbikes.android.modules.cycling.club.dto.C2066e;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.widget.PullRefreshListView;
import com.beastbikes.android.widget.PullRefreshListView.C2597b;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@C1459b(a = 2130903101)
@C1460c(a = 2131820561)
@C1457a(a = "俱乐部消息")
public class ClubMsgActivity extends SessionFragmentActivity implements OnClickListener, C1371a, C2597b {
    @C1458a(a = 2131755542)
    /* renamed from: a */
    private PullRefreshListView f5251a;
    /* renamed from: b */
    private C2052c f5252b;
    /* renamed from: c */
    private Long f5253c = null;
    /* renamed from: d */
    private int f5254d = 20;
    /* renamed from: e */
    private boolean f5255e = false;
    /* renamed from: f */
    private List<C2066e> f5256f;
    /* renamed from: g */
    private ClubMsgActivity$a f5257g;
    /* renamed from: h */
    private C1802i f5258h;
    /* renamed from: i */
    private View f5259i;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ClubMsgActivity$b */
    public final class C1410b extends ViewHolder<C2066e> {
        /* renamed from: a */
        final /* synthetic */ ClubMsgActivity f5245a;
        @C1458a(a = 2131756736)
        /* renamed from: b */
        private RelativeLayout f5246b;
        @C1458a(a = 2131756737)
        /* renamed from: c */
        private CircleImageView f5247c;
        @C1458a(a = 2131756738)
        /* renamed from: d */
        private TextView f5248d;
        @C1458a(a = 2131756739)
        /* renamed from: e */
        private TextView f5249e;
        @C1458a(a = 2131755444)
        /* renamed from: f */
        private TextView f5250f;

        public /* synthetic */ void bind(Object obj) {
            m6562a((C2066e) obj);
        }

        public C1410b(ClubMsgActivity clubMsgActivity, View view) {
            this.f5245a = clubMsgActivity;
            super(view);
        }

        /* renamed from: a */
        public void m6562a(C2066e c2066e) {
            if (c2066e != null) {
                String str = "";
                CharSequence charSequence = "";
                JSONObject e = c2066e.e();
                ProfileDTO f = c2066e.f();
                ClubInfoCompact g = c2066e.g();
                if (f != null) {
                    str = f.getAvatar();
                    charSequence = f.getNickname();
                } else if (g != null) {
                    str = g.getLogo();
                    charSequence = g.getName();
                }
                if (TextUtils.isEmpty(str)) {
                    this.f5247c.setImageResource(C1373R.drawable.ic_avatar);
                } else {
                    Picasso.with(getContext()).load(str).fit().centerCrop().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).into(this.f5247c);
                }
                this.f5248d.setText(charSequence);
                this.f5249e.setText(C2555d.d(C2555d.h(c2066e.c())));
                this.f5250f.setText(c2066e.a());
                this.f5247c.setOnClickListener(new ClubMsgActivity$b$1(this, c2066e, f));
                this.f5246b.setOnClickListener(new ClubMsgActivity$b$2(this, e));
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (AVUser.getCurrentUser() != null) {
            this.f5256f = new ArrayList();
            this.f5257g = new ClubMsgActivity$a(this, this.f5256f);
            this.f5251a.setAdapter(this.f5257g);
            this.f5251a.setPullLoadEnable(false);
            this.f5251a.setPullRefreshEnable(false);
            this.f5252b = new C2052c(this);
            this.f5259i = LayoutInflater.from(this).inflate(C1373R.layout.footview_club_msg, null);
            this.f5251a.addFooterView(this.f5259i);
            this.f5251a.setListViewListener(this);
            this.f5251a.b(C1373R.color.discover_color3);
            this.f5259i.setOnClickListener(this);
            m6569d();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_item_clear:
                m6567c();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.clubmsg_footview:
                this.f5251a.removeFooterView(this.f5259i);
                this.f5255e = true;
                b_();
                return;
            default:
                return;
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    public void m6574a() {
    }

    public void b_() {
        this.f5251a.setPullLoadEnable(true);
        m6569d();
    }

    /* renamed from: c */
    private void m6567c() {
        this.f5258h = new C1802i(this, "", false);
        this.f5258h.show();
        getAsyncTaskQueue().a(new ClubMsgActivity$1(this), new Void[0]);
    }

    /* renamed from: d */
    private void m6569d() {
        this.f5258h = new C1802i(this, "", false);
        this.f5258h.show();
        getAsyncTaskQueue().a(new ClubMsgActivity$2(this), new Void[0]);
    }
}
