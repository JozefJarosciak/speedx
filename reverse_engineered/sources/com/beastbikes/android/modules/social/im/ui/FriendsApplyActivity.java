package com.beastbikes.android.modules.social.im.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.android.modules.social.im.p074a.C2341a;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2570p;
import com.beastbikes.android.widget.C2621c;
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

@C1459b(a = 2130903384)
@C1460c(a = 2131820555)
@C1457a(a = "好友列表")
public class FriendsApplyActivity extends SessionFragmentActivity implements OnItemClickListener, OnItemLongClickListener, C1371a, C2597b {
    @C1458a(a = 2131756699)
    /* renamed from: a */
    private TextView f6134a;
    @C1458a(a = 2131756700)
    /* renamed from: b */
    private PullRefreshListView f6135b;
    /* renamed from: c */
    private SharedPreferences f6136c;
    /* renamed from: d */
    private FriendsApplyActivity$a f6137d;
    /* renamed from: e */
    private List<FriendDTO> f6138e = new ArrayList();
    /* renamed from: f */
    private C1802i f6139f;
    /* renamed from: g */
    private int f6140g = 1;
    /* renamed from: h */
    private C2341a f6141h;

    /* renamed from: com.beastbikes.android.modules.social.im.ui.FriendsApplyActivity$b */
    private final class C1435b extends ViewHolder<FriendDTO> {
        /* renamed from: a */
        final /* synthetic */ FriendsApplyActivity f6127a;
        @C1458a(a = 2131756179)
        /* renamed from: b */
        private CircleImageView f6128b;
        @C1458a(a = 2131756180)
        /* renamed from: c */
        private TextView f6129c;
        @C1458a(a = 2131756181)
        /* renamed from: d */
        private TextView f6130d;
        @C1458a(a = 2131756184)
        /* renamed from: e */
        private TextView f6131e;
        @C1458a(a = 2131756182)
        /* renamed from: f */
        private TextView f6132f;
        @C1458a(a = 2131756183)
        /* renamed from: g */
        private TextView f6133g;

        public /* synthetic */ void bind(Object obj) {
            m7316a((FriendDTO) obj);
        }

        protected C1435b(FriendsApplyActivity friendsApplyActivity, View view) {
            this.f6127a = friendsApplyActivity;
            super(view);
            this.f6132f.setVisibility(8);
        }

        /* renamed from: a */
        public void m7316a(FriendDTO friendDTO) {
        }

        /* renamed from: a */
        public void m7317a(FriendDTO friendDTO, int i) {
            if (friendDTO != null) {
                if (TextUtils.isEmpty(friendDTO.getAvatar())) {
                    this.f6128b.setImageResource(C1373R.drawable.ic_avatar);
                } else {
                    Picasso.with(getContext()).load(friendDTO.getAvatar()).fit().centerCrop().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).into(this.f6128b);
                }
                this.f6129c.setText(C2570p.a(friendDTO.getNickname(), friendDTO.getRemarks()));
                this.f6130d.setText(friendDTO.getExtra());
                if (friendDTO.getStatus() == 0) {
                    this.f6131e.setVisibility(0);
                    this.f6131e.setText(C1373R.string.friends_apply_agree);
                    this.f6131e.setOnClickListener(new FriendsApplyActivity$b$1(this, friendDTO, i));
                    this.f6133g.setVisibility(8);
                    return;
                }
                this.f6131e.setVisibility(8);
                this.f6133g.setText(C1373R.string.friends_already_add);
                this.f6133g.setVisibility(0);
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
        this.f6141h = new C2341a(this);
        this.f6136c = getSharedPreferences(m5331p(), 0);
        this.f6135b.setDivider(getResources().getDrawable(C1373R.drawable.listview_divider_line_margin_left));
        this.f6135b.setDividerHeight(1);
        this.f6135b.setOnItemClickListener(this);
        this.f6135b.setPullRefreshEnable(false);
        this.f6135b.setListViewListener(this);
        this.f6137d = new FriendsApplyActivity$a(this, this.f6138e);
        this.f6135b.setAdapter(this.f6137d);
        this.f6135b.setOnItemClickListener(this);
        this.f6135b.setOnItemLongClickListener(this);
        registerForContextMenu(this.f6135b);
        m7324c();
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
            intent.putExtra("avatar", friendDTO.getAvatar());
            intent.putExtra("nick_name", friendDTO.getNickname());
            intent.putExtra("remarks", friendDTO.getRemarks());
            startActivity(intent);
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (((FriendDTO) adapterView.getItemAtPosition(i)) == null) {
            return true;
        }
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.apply_clear_item:
                if (this.f6138e == null || this.f6138e.isEmpty()) {
                    return true;
                }
                C2621c c2621c = new C2621c(this);
                c2621c.b(C1373R.string.friends_clear_applys_dialog_title);
                c2621c.a(C1373R.string.clear, new FriendsApplyActivity$2(this, c2621c)).b(C1373R.string.cancel, new FriendsApplyActivity$1(this, c2621c)).a();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        new MenuInflater(view.getContext()).inflate(C1373R.menu.apply_delete_menu, contextMenu);
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        int i;
        int i2 = ((AdapterContextMenuInfo) menuItem.getMenuInfo()).position;
        if (i2 > 0) {
            i = i2 - 1;
        } else {
            i = i2;
        }
        FriendDTO friendDTO = (FriendDTO) this.f6137d.getItem(i);
        if (friendDTO == null) {
            return true;
        }
        switch (menuItem.getItemId()) {
            case C1373R.id.apply_delete_item:
                i2 = friendDTO.getRequestId();
                if (i2 != 0) {
                    m7319a(i2, 1, i);
                    break;
                }
                return true;
        }
        return super.onContextItemSelected(menuItem);
    }

    public void b_() {
        m7324c();
    }

    /* renamed from: a */
    public void m7333a() {
    }

    /* renamed from: a */
    private void m7319a(int i, int i2, int i3) {
        this.f6139f = new C1802i(this, null, true);
        getAsyncTaskQueue().a(new FriendsApplyActivity$3(this, i, i2, i3), new String[0]);
    }

    /* renamed from: c */
    private void m7324c() {
        this.f6139f = new C1802i(this, null, true);
        getAsyncTaskQueue().a(new FriendsApplyActivity$4(this), new Integer[0]);
    }

    /* renamed from: d */
    private void m7326d() {
        if (this.f6138e != null && !this.f6138e.isEmpty()) {
            this.f6139f = new C1802i(this, null, true);
            getAsyncTaskQueue().a(new FriendsApplyActivity$5(this), new Void[0]);
        }
    }
}
