package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.C2638d;
import com.beastbikes.android.widget.C2638d.C2631b;
import com.beastbikes.android.widget.convenientbanner.ConvenientBanner;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.WebActivity;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903133)
public class FollowActivity extends SessionFragmentActivity implements OnClickListener, OnEditorActionListener, C2534b, C2631b {
    @C1458a(a = 2131755742)
    /* renamed from: a */
    private EditText f6386a;
    @C1458a(a = 2131755744)
    /* renamed from: b */
    private TextView f6387b;
    @C1458a(a = 2131755745)
    /* renamed from: c */
    private LinearLayout f6388c;
    @C1458a(a = 2131755741)
    /* renamed from: d */
    private View f6389d;
    @C1458a(a = 2131755743)
    /* renamed from: e */
    private ConvenientBanner f6390e;
    /* renamed from: f */
    private C2638d f6391f;
    /* renamed from: g */
    private List<FriendDTO> f6392g = new ArrayList();
    /* renamed from: h */
    private C2389c f6393h;
    /* renamed from: i */
    private int f6394i = 1;
    /* renamed from: j */
    private boolean f6395j = true;
    /* renamed from: k */
    private boolean f6396k;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        m7602c();
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.getObjectId().equals(m5331p())) {
                this.f6387b.setText(C1373R.string.activity_follow_empty_tip);
            } else {
                this.f6387b.setText(C1373R.string.activity_other_friend_follow_empty_tip);
            }
            this.f6393h = new C2389c(this);
            this.f6391f = new C2638d(this, this.f6388c, this.f6392g, 2);
            this.f6391f.setAdapter(new FollowActivity$c(this, this, this));
            this.f6386a.setOnEditorActionListener(this);
            this.f6394i = 1;
            m7606d();
            this.f6391f.setRecyclerCallBack(this);
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.f6396k) {
            this.f6394i = 1;
            m7606d();
            this.f6396k = false;
        }
    }

    public void finish() {
        View peekDecorView = getWindow().peekDecorView();
        if (peekDecorView != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == 3) {
            Object obj = this.f6386a.getText().toString();
            if (!TextUtils.isEmpty(obj)) {
                this.f6396k = true;
                Intent intent = new Intent(this, FollowSearchResultActivity.class);
                intent.putExtra("search_content", obj);
                intent.putExtra("follow_type", 0);
                intent.putExtra(WebActivity.EXTRA_TITLE, getString(C1373R.string.friends_search_result_title));
                startActivity(intent);
            }
        }
        return false;
    }

    /* renamed from: a */
    public void m7609a() {
        if (!this.f6395j) {
            this.f6394i = 1;
            this.f6391f.setCanLoadMore(true);
            this.f6391f.setHasFooter(true);
            m7606d();
        }
    }

    public void a_() {
        m7606d();
    }

    public void onClick(View view) {
        view.getId();
    }

    /* renamed from: a */
    public void m7610a(ViewHolder viewHolder, int i) {
        if (this.f6392g != null && this.f6392g.size() > 0) {
            FriendDTO friendDTO = (FriendDTO) this.f6392g.get(i);
            if (friendDTO != null) {
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("user_id", friendDTO.getFriendId());
                intent.putExtra("nick_name", friendDTO.getNickname());
                intent.putExtra("remarks", friendDTO.getRemarks());
                intent.putExtra("avatar", friendDTO.getAvatar());
                super.startActivity(intent);
            }
        }
    }

    /* renamed from: b */
    public void m7611b(ViewHolder viewHolder, int i) {
    }

    /* renamed from: c */
    private void m7602c() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.getObjectId().equals(m5331p())) {
                this.f6390e.setVisibility(0);
                List arrayList = new ArrayList();
                arrayList.add(new FollowActivity$a(this, C1373R.string.label_phone_contact, C1373R.drawable.ic_follow_contact_icon, 1, C1373R.string.activity_finished_menu_weibo, C1373R.drawable.ic_follow_weibo_icon, 2));
                this.f6390e.a(new FollowActivity$1(this), arrayList);
                this.f6390e.setCanLoop(false);
                this.f6390e.setcurrentitem(0);
                return;
            }
            this.f6390e.setVisibility(8);
            this.f6389d.setVisibility(8);
        }
    }

    /* renamed from: d */
    private void m7606d() {
        getAsyncTaskQueue().a(new FollowActivity$2(this), new String[0]);
    }

    /* renamed from: a */
    private void m7596a(String str, int i) {
        getAsyncTaskQueue().a(new FollowActivity$3(this, str, i), new String[0]);
    }

    /* renamed from: b */
    private void m7600b(String str, int i) {
        getAsyncTaskQueue().a(new FollowActivity$4(this, str, i), new String[0]);
    }

    /* renamed from: c */
    private void m7604c(String str, int i) {
        C2621c c2621c = new C2621c(this);
        c2621c.b(C1373R.string.msg_unfollow_prompt_dialog);
        c2621c.b(C1373R.string.cancel, new FollowActivity$5(this, c2621c));
        c2621c.a(C1373R.string.activity_alert_dialog_text_ok, new FollowActivity$6(this, str, i, c2621c));
        c2621c.a();
    }
}
