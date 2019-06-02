package com.beastbikes.android.modules.social.im.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1800h;
import com.beastbikes.android.dialog.C1800h.C1799a;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.android.modules.social.im.p074a.C2341a;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.PullRefreshListView;
import com.beastbikes.android.widget.PullRefreshListView.C2597b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903385)
public class FriendsSearchResultActivity extends SessionFragmentActivity implements OnItemClickListener, C1799a, C2597b {
    /* renamed from: a */
    private static final Logger f6149a = LoggerFactory.getLogger(FriendsSearchResultActivity.class);
    @C1458a(a = 2131756702)
    /* renamed from: b */
    private PullRefreshListView f6150b;
    @C1458a(a = 2131756701)
    /* renamed from: c */
    private TextView f6151c;
    /* renamed from: d */
    private String f6152d;
    /* renamed from: e */
    private FriendsSearchResultActivity$a f6153e;
    /* renamed from: f */
    private List<FriendDTO> f6154f = new ArrayList();
    /* renamed from: g */
    private int f6155g = 1;
    /* renamed from: h */
    private C1800h f6156h;
    /* renamed from: i */
    private C1802i f6157i;
    /* renamed from: j */
    private FriendDTO f6158j;
    /* renamed from: k */
    private C2341a f6159k;

    /* renamed from: com.beastbikes.android.modules.social.im.ui.FriendsSearchResultActivity$b */
    private final class C1436b extends ViewHolder<FriendDTO> {
        /* renamed from: a */
        final /* synthetic */ FriendsSearchResultActivity f6142a;
        @C1458a(a = 2131756179)
        /* renamed from: b */
        private CircleImageView f6143b;
        @C1458a(a = 2131756180)
        /* renamed from: c */
        private TextView f6144c;
        @C1458a(a = 2131756181)
        /* renamed from: d */
        private TextView f6145d;
        @C1458a(a = 2131756184)
        /* renamed from: e */
        private TextView f6146e;
        @C1458a(a = 2131756182)
        /* renamed from: f */
        private TextView f6147f;
        @C1458a(a = 2131756183)
        /* renamed from: g */
        private TextView f6148g;

        public /* synthetic */ void bind(Object obj) {
            m7334a((FriendDTO) obj);
        }

        protected C1436b(FriendsSearchResultActivity friendsSearchResultActivity, View view) {
            this.f6142a = friendsSearchResultActivity;
            super(view);
            this.f6147f.setVisibility(8);
            this.f6148g.setVisibility(8);
        }

        /* renamed from: a */
        public void m7334a(FriendDTO friendDTO) {
            if (friendDTO != null) {
                if (TextUtils.isEmpty(friendDTO.getAvatar())) {
                    this.f6143b.setImageResource(C1373R.drawable.ic_avatar);
                } else {
                    Picasso.with(getContext()).load(friendDTO.getAvatar()).fit().centerCrop().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).into(this.f6143b);
                }
                this.f6144c.setText(friendDTO.getNickname());
                StringBuilder stringBuilder = new StringBuilder();
                if (!(TextUtils.isEmpty(friendDTO.getProvince()) || friendDTO.getProvince().equals("null"))) {
                    stringBuilder.append(friendDTO.getProvince()).append("\t ");
                }
                if (!(TextUtils.isEmpty(friendDTO.getCity()) || friendDTO.getCity().equals("null"))) {
                    stringBuilder.append(friendDTO.getCity());
                }
                this.f6145d.setText(stringBuilder.toString());
                switch (friendDTO.getStatus()) {
                    case 0:
                        this.f6146e.setText(C1373R.string.friends_add_friend);
                        this.f6146e.setClickable(true);
                        break;
                    case 1:
                        this.f6146e.setText(C1373R.string.friends_apply_agree);
                        this.f6146e.setClickable(true);
                        break;
                    case 2:
                        this.f6146e.setText(C1373R.string.friends_wait_verification);
                        this.f6146e.setClickable(false);
                        break;
                    case 3:
                        this.f6146e.setText(C1373R.string.friends_already_add);
                        this.f6146e.setClickable(false);
                        break;
                }
                this.f6146e.setOnClickListener(new FriendsSearchResultActivity$b$1(this, friendDTO));
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
            this.f6159k = new C2341a(this);
            this.f6150b.b(C1373R.color.common_bg_color);
            this.f6150b.setDivider(getResources().getDrawable(C1373R.drawable.listview_divider_line_margin_left));
            this.f6150b.setDividerHeight(1);
            this.f6150b.setOnItemClickListener(this);
            this.f6150b.setPullRefreshEnable(false);
            this.f6150b.setListViewListener(this);
            this.f6152d = intent.getStringExtra("search_content");
            this.f6153e = new FriendsSearchResultActivity$a(this, this.f6154f);
            this.f6150b.setAdapter(this.f6153e);
            m7341a(this.f6152d);
            C2580w.a(this, "搜索好友", null);
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        FriendDTO friendDTO = (FriendDTO) adapterView.getItemAtPosition(i);
        if (friendDTO != null) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user_id", friendDTO.getFriendId());
            intent.putExtra("remarks", friendDTO.getRemarks());
            intent.putExtra("avatar", friendDTO.getAvatar());
            intent.putExtra("nick_name", friendDTO.getNickname());
            startActivity(intent);
        }
    }

    /* renamed from: b */
    public void m7352b(String str) {
        if (this.f6156h != null) {
            this.f6156h.dismiss();
        }
        if (TextUtils.isEmpty(str)) {
            str = getString(C1373R.string.friends_add_friend_default_msg);
        }
        m7339a(this.f6158j, str);
        C2580w.a(this, "发送好友请求", null);
    }

    public void b_() {
        m7341a(this.f6152d);
    }

    /* renamed from: a */
    public void m7351a() {
    }

    /* renamed from: a */
    private void m7341a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f6157i = new C1802i(this, null, true);
            getAsyncTaskQueue().a(new FriendsSearchResultActivity$1(this, str), new String[0]);
        }
    }

    /* renamed from: a */
    private void m7339a(FriendDTO friendDTO, String str) {
        if (friendDTO != null) {
            Object friendId = friendDTO.getFriendId();
            if (!TextUtils.isEmpty(friendId)) {
                this.f6157i = new C1802i(this, null, true);
                getAsyncTaskQueue().a(new FriendsSearchResultActivity$2(this, friendId, str, friendDTO), new String[0]);
            }
        }
    }

    /* renamed from: a */
    private void m7338a(int i) {
        if (i != 0) {
            this.f6157i = new C1802i(this, null, true);
            getAsyncTaskQueue().a(new FriendsSearchResultActivity$3(this, i), new String[0]);
        }
    }
}
