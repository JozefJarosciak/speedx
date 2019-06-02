package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2052c;
import com.beastbikes.android.modules.cycling.club.dto.ClubUser;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2570p;
import com.beastbikes.android.widget.PullRefreshListView;
import com.beastbikes.android.widget.PullRefreshListView.C2597b;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

@C1457a(a = "谁赞过我")
@C1459b(a = 2130903206)
public class ThumbsListActivity extends SessionFragmentActivity implements OnItemClickListener, C2597b {
    @C1458a(a = 2131756083)
    /* renamed from: a */
    private PullRefreshListView f5325a;
    @C1458a(a = 2131756084)
    /* renamed from: b */
    private ImageView f5326b;
    @C1458a(a = 2131756085)
    /* renamed from: c */
    private TextView f5327c;
    /* renamed from: d */
    private List<ClubUser> f5328d;
    /* renamed from: e */
    private C1802i f5329e;
    /* renamed from: f */
    private int f5330f = 1;
    /* renamed from: g */
    private int f5331g = 50;
    /* renamed from: h */
    private C2052c f5332h;
    /* renamed from: i */
    private ThumbsListActivity$a f5333i;
    /* renamed from: j */
    private int f5334j = -1;
    /* renamed from: k */
    private int f5335k = -1;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ThumbsListActivity$b */
    class C1412b extends ViewHolder<ClubUser> {
        @C1458a(a = 2131756776)
        /* renamed from: a */
        public View f5319a;
        @C1458a(a = 2131756777)
        /* renamed from: b */
        public View f5320b;
        /* renamed from: c */
        final /* synthetic */ ThumbsListActivity f5321c;
        @C1458a(a = 2131756774)
        /* renamed from: d */
        private CircleImageView f5322d;
        @C1458a(a = 2131756775)
        /* renamed from: e */
        private TextView f5323e;
        /* renamed from: f */
        private View f5324f;

        public /* synthetic */ void bind(Object obj) {
            m6620a((ClubUser) obj);
        }

        public C1412b(ThumbsListActivity thumbsListActivity, View view) {
            this.f5321c = thumbsListActivity;
            super(view);
            this.f5324f = view;
        }

        /* renamed from: a */
        public void m6620a(ClubUser clubUser) {
            if (clubUser != null) {
                if (TextUtils.isEmpty(clubUser.getAvatar())) {
                    this.f5322d.setImageResource(C1373R.drawable.ic_avatar);
                } else {
                    Picasso.with(getContext()).load(clubUser.getAvatar()).fit().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).centerCrop().into(this.f5322d);
                }
                this.f5323e.setText(C2570p.a(clubUser.getNickName(), clubUser.getRemarks()));
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
        Intent intent = getIntent();
        if (intent != null) {
            this.f5334j = intent.getIntExtra("feed_id", -1);
            this.f5335k = intent.getIntExtra("photo_id", -1);
        }
        this.f5328d = new ArrayList();
        this.f5333i = new ThumbsListActivity$a(this, this.f5328d);
        this.f5332h = new C2052c(this);
        this.f5325a.setAdapter(this.f5333i);
        this.f5325a.setPullRefreshEnable(false);
        this.f5325a.setPullLoadEnable(true);
        this.f5325a.b(C1373R.color.discover_color2);
        this.f5325a.setListViewListener(this);
        this.f5325a.setOnItemClickListener(this);
        m6624c();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ClubUser clubUser = (ClubUser) this.f5328d.get(i - 1);
        if (clubUser != null) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user_id", clubUser.getUserId());
            intent.putExtra("avatar", clubUser.getAvatar());
            intent.putExtra("nick_name", clubUser.getNickName());
            intent.putExtra("remarks", clubUser.getRemarks());
            startActivity(intent);
        }
    }

    /* renamed from: a */
    public void m6633a() {
    }

    public void b_() {
        this.f5330f++;
        m6624c();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: c */
    private void m6624c() {
        this.f5329e = new C1802i(this, getString(C1373R.string.str_loading), false);
        this.f5329e.show();
        getAsyncTaskQueue().a(new ThumbsListActivity$1(this), new Void[0]);
    }
}
