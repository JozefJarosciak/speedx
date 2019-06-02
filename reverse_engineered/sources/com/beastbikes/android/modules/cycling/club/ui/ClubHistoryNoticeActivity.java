package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.C2067f;
import com.beastbikes.android.widget.PullRefreshListView;
import com.beastbikes.android.widget.PullRefreshListView.C2597b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;

@C1459b(a = 2130903140)
@C1460c(a = 2131820554)
public class ClubHistoryNoticeActivity extends SessionFragmentActivity implements C2597b {
    @C1458a(a = 2131755782)
    /* renamed from: a */
    private PullRefreshListView f5122a;
    /* renamed from: b */
    private int f5123b = 1;
    /* renamed from: c */
    private int f5124c = 20;
    /* renamed from: d */
    private int f5125d = 111;
    /* renamed from: e */
    private ClubManager f5126e;
    /* renamed from: f */
    private List<C2067f> f5127f;
    /* renamed from: g */
    private String f5128g;
    /* renamed from: h */
    private ClubHistoryNoticeActivity$a f5129h;
    /* renamed from: i */
    private C1802i f5130i;
    /* renamed from: j */
    private int f5131j = 0;
    /* renamed from: k */
    private String f5132k = "";

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ClubHistoryNoticeActivity$b */
    private final class C1408b extends ViewHolder<C2067f> {
        /* renamed from: a */
        final /* synthetic */ ClubHistoryNoticeActivity f5118a;
        /* renamed from: b */
        private View f5119b;
        @C1458a(a = 2131756754)
        /* renamed from: c */
        private TextView f5120c;
        @C1458a(a = 2131756755)
        /* renamed from: d */
        private TextView f5121d;

        public /* synthetic */ void bind(Object obj) {
            m6459a((C2067f) obj);
        }

        public C1408b(ClubHistoryNoticeActivity clubHistoryNoticeActivity, View view) {
            this.f5118a = clubHistoryNoticeActivity;
            super(view);
            this.f5119b = view;
        }

        /* renamed from: a */
        public void m6459a(C2067f c2067f) {
            if (c2067f != null) {
                this.f5120c.setText(c2067f.a());
                CharSequence b = c2067f.b();
                if (!TextUtils.isEmpty(b) && b.contains("T")) {
                    String[] split = b.split("T");
                    if (split != null && split.length > 0) {
                        b = split[0].replace(HelpFormatter.DEFAULT_OPT_PREFIX, ".");
                    }
                }
                this.f5121d.setText(b);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (intent != null) {
            this.f5128g = intent.getStringExtra("club_id");
            this.f5131j = intent.getIntExtra("level", 0);
            this.f5132k = intent.getStringExtra("notice");
        }
        if (!TextUtils.isEmpty(this.f5128g)) {
            this.f5126e = new ClubManager(this);
            this.f5127f = new ArrayList();
            this.f5129h = new ClubHistoryNoticeActivity$a(this, this.f5127f);
            this.f5122a.setAdapter(this.f5129h);
            this.f5122a.setPullRefreshEnable(true);
            this.f5122a.setPullLoadEnable(true);
            this.f5122a.b(C1373R.color.bg_black_color);
            this.f5122a.setListViewListener(this);
            m6463c();
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(C1373R.id.menu_item_add);
        if (this.f5131j == 0) {
            findItem.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_item_add:
                Intent intent = new Intent(this, ClubPostNoticeActivity.class);
                intent.putExtra("notice", this.f5132k);
                startActivityForResult(intent, this.f5125d);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* renamed from: a */
    public void m6469a() {
        this.f5127f.clear();
        this.f5123b = 1;
        this.f5122a.setPullLoadEnable(true);
        m6463c();
    }

    public void b_() {
        this.f5122a.setPullLoadEnable(true);
        this.f5123b++;
        m6463c();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            if (intent != null) {
                this.f5132k = intent.getStringExtra("notice");
            }
            m6469a();
        }
    }

    /* renamed from: c */
    private void m6463c() {
        this.f5130i = new C1802i(this, getString(C1373R.string.str_loading), false);
        this.f5130i.show();
        getAsyncTaskQueue().a(new ClubHistoryNoticeActivity$1(this), new Void[0]);
    }
}
