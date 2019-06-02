package com.beastbikes.android.modules.cycling.route.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.route.dto.C2189c;
import com.beastbikes.android.modules.cycling.route.dto.C2190d;
import com.beastbikes.android.modules.cycling.route.p068a.C2185a;
import com.beastbikes.android.modules.user.dao.entity.LocalUser;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.PullRefreshListView;
import com.beastbikes.android.widget.PullRefreshListView.C2597b;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1457a(a = "路线全部评论页")
@C1459b(a = 2130903657)
public class RouteCommentActivity extends SessionFragmentActivity implements OnClickListener, OnItemClickListener, C2597b {
    /* renamed from: a */
    private static Logger f5474a = LoggerFactory.getLogger(RouteCommentActivity.class);
    @C1458a(a = 2131757489)
    /* renamed from: b */
    private PullRefreshListView f5475b;
    @C1458a(a = 2131757490)
    /* renamed from: c */
    private EditText f5476c;
    @C1458a(a = 2131757491)
    /* renamed from: d */
    private Button f5477d;
    /* renamed from: e */
    private C2185a f5478e;
    /* renamed from: f */
    private C2389c f5479f;
    /* renamed from: g */
    private C2189c f5480g;
    /* renamed from: h */
    private List<C2190d> f5481h = new ArrayList();
    /* renamed from: i */
    private String f5482i;
    /* renamed from: j */
    private int f5483j;
    /* renamed from: k */
    private int f5484k = 2;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5482i = getIntent().getStringExtra("route_id");
        this.f5483j = getIntent().getIntExtra("route_comment_count", 0);
        super.setTitle(String.format(getString(C1373R.string.route_comment_activity_title), new Object[]{Integer.valueOf(this.f5483j)}));
        this.f5478e = new C2185a(this);
        this.f5479f = new C2389c(this);
        this.f5475b.setPullLoadEnable(true);
        this.f5475b.setPullRefreshEnable(true);
        this.f5475b.setListViewListener(this);
        this.f5480g = new C2189c(this.f5481h);
        this.f5475b.setAdapter(this.f5480g);
        this.f5475b.setOnItemClickListener(this);
        this.f5477d.setOnClickListener(this);
        m6785c();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    public void m6791a() {
        m6785c();
    }

    public void b_() {
        m6787d();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.route_comment_send:
                String trim = this.f5476c.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    Toasts.show(this, C1373R.string.route_activity_comment_empty_msg);
                    return;
                }
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.f5476c.getWindowToken(), 2);
                m6782a(trim);
                C2580w.a(this, "路线评论总次数", null);
                return;
            default:
                return;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        C2190d c2190d = (C2190d) adapterView.getAdapter().getItem(i);
        if (c2190d != null) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user_id", c2190d.a());
            intent.putExtra("avatar", c2190d.f());
            intent.putExtra("nick_name", c2190d.b());
            intent.putExtra("remarks", c2190d.g());
            startActivity(intent);
        }
    }

    /* renamed from: c */
    private void m6785c() {
        getAsyncTaskQueue().a(new RouteCommentActivity$1(this), new String[]{this.f5482i});
    }

    /* renamed from: d */
    private void m6787d() {
        getAsyncTaskQueue().a(new RouteCommentActivity$2(this), new String[]{this.f5482i});
    }

    /* renamed from: a */
    private void m6782a(String str) {
        try {
            LocalUser a = this.f5479f.a(m5331p());
            if (a != null && TextUtils.isEmpty(a.getNickname())) {
                a.getUsername();
            }
            getAsyncTaskQueue().a(new RouteCommentActivity$3(this), new String[]{this.f5482i, str});
        } catch (Throwable e) {
            f5474a.error("Send comment for route " + this.f5482i + " error", e);
        }
    }
}
