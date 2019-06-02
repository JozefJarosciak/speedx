package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1786a;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.utils.C2570p;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903152)
@C1460c(a = 2131820572)
public class ClubMemberManagerActivity extends SessionFragmentActivity implements OnItemClickListener {
    @C1458a(a = 2131755816)
    /* renamed from: a */
    private ViewGroup f5169a;
    /* renamed from: b */
    private CircleImageView f5170b;
    /* renamed from: c */
    private TextView f5171c;
    /* renamed from: d */
    private TextView f5172d;
    /* renamed from: e */
    private TextView f5173e;
    /* renamed from: f */
    private TextView f5174f;
    @C1458a(a = 2131755817)
    /* renamed from: g */
    private ListView f5175g;
    /* renamed from: h */
    private ClubMemberManagerActivity$a f5176h;
    /* renamed from: i */
    private List<RankDTO> f5177i = new ArrayList();
    /* renamed from: j */
    private ClubManager f5178j;
    /* renamed from: k */
    private RankDTO f5179k;
    /* renamed from: l */
    private String f5180l;
    /* renamed from: m */
    private ClubInfoCompact f5181m;
    /* renamed from: n */
    private boolean f5182n = false;
    /* renamed from: o */
    private boolean f5183o = false;
    /* renamed from: p */
    private TextView f5184p;
    /* renamed from: q */
    private int f5185q;
    /* renamed from: r */
    private int f5186r;
    /* renamed from: s */
    private boolean f5187s = false;
    /* renamed from: t */
    private int f5188t = 0;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ClubMemberManagerActivity$b */
    private final class C1409b extends ViewHolder<RankDTO> {
        @C1458a(a = 2131756329)
        /* renamed from: a */
        public View f5160a;
        @C1458a(a = 2131756330)
        /* renamed from: b */
        public View f5161b;
        /* renamed from: c */
        final /* synthetic */ ClubMemberManagerActivity f5162c;
        /* renamed from: d */
        private ClubManager f5163d;
        @C1458a(a = 2131756323)
        /* renamed from: e */
        private CircleImageView f5164e;
        @C1458a(a = 2131756327)
        /* renamed from: f */
        private TextView f5165f;
        @C1458a(a = 2131756326)
        /* renamed from: g */
        private TextView f5166g;
        @C1458a(a = 2131756328)
        /* renamed from: h */
        private TextView f5167h;
        @C1458a(a = 2131756325)
        /* renamed from: i */
        private TextView f5168i;

        public /* synthetic */ void bind(Object obj) {
            m6503a((RankDTO) obj);
        }

        protected C1409b(ClubMemberManagerActivity clubMemberManagerActivity, View view, ClubManager clubManager) {
            this.f5162c = clubMemberManagerActivity;
            super(view);
            this.f5163d = clubManager;
        }

        /* renamed from: a */
        public void m6503a(RankDTO rankDTO) {
            double d = 0.0d;
            if (rankDTO != null) {
                if (TextUtils.isEmpty(rankDTO.getAvatarUrl())) {
                    this.f5164e.setImageResource(C1373R.drawable.ic_avatar);
                } else {
                    Picasso.with(getContext()).load(rankDTO.getAvatarUrl()).fit().centerCrop().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).into(this.f5164e);
                }
                this.f5165f.setText(C2570p.a(rankDTO.getNickname(), rankDTO.getRemarks()));
                this.f5167h.setText(this.f5162c.getResources().getString(C1373R.string.jointime) + C2555d.c(C2555d.h(rankDTO.getJoined())));
                if (rankDTO.getMilestone() > 0.0d) {
                    d = rankDTO.getMilestone() / 1000.0d;
                }
                if (C1849a.b(this.f5162c)) {
                    this.f5168i.setText(String.format("%.1f", new Object[]{Double.valueOf(d)}) + " " + this.f5162c.getResources().getString(C1373R.string.str_mileage_unit_km));
                } else {
                    this.f5168i.setText(String.format("%.1f", new Object[]{Double.valueOf(C1849a.a(d))}) + " " + this.f5162c.getResources().getString(C1373R.string.str_mileage_unit_mile));
                }
                if (this.f5162c.f5182n) {
                    this.f5166g.setVisibility(0);
                    this.f5166g.setTag(rankDTO.getUserId());
                    this.f5166g.setOnClickListener(new ClubMemberManagerActivity$b$1(this, rankDTO));
                    return;
                }
                this.f5166g.setVisibility(8);
            }
        }

        /* renamed from: b */
        private void m6502b(RankDTO rankDTO) {
            C2580w.a(this.f5162c, "", "click_propose");
            Context context = getContext();
            new C1786a(context, context.getString(C1373R.string.activity_member_manager_dialog_msg), null, new ClubMemberManagerActivity$b$2(this, rankDTO), C1373R.id.dialog_club_cancel_create_apply_warning).show();
        }
    }

    protected void onCreate(Bundle bundle) {
        boolean z = false;
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent != null) {
            this.f5180l = intent.getStringExtra("club_id");
            this.f5181m = (ClubInfoCompact) intent.getSerializableExtra("club_info");
            this.f5187s = intent.getBooleanExtra("club_select_mode", false);
            this.f5188t = intent.getIntExtra("is_quit", 0);
            this.f5185q = this.f5181m.getMaxMembers();
            this.f5186r = this.f5181m.getMembers();
            if (this.f5181m != null) {
                this.f5183o = this.f5181m.getLevel() == 128;
            }
            try {
                ClubInfoCompact a = new ClubManager(this).a(m5331p());
                if (a != null) {
                    if (a.getLevel() == 128) {
                        z = true;
                    }
                    this.f5183o = z;
                }
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }
        this.f5170b = (CircleImageView) this.f5169a.findViewById(C1373R.id.club_member_list_item_avatar);
        this.f5171c = (TextView) this.f5169a.findViewById(C1373R.id.club_member_list_item_nickname);
        this.f5172d = (TextView) this.f5169a.findViewById(C1373R.id.member_time);
        this.f5173e = (TextView) this.f5169a.findViewById(C1373R.id.member_distance);
        this.f5174f = (TextView) this.f5169a.findViewById(C1373R.id.club_member_list_item_btn_delete);
        this.f5174f.setVisibility(8);
        View inflate = LayoutInflater.from(this).inflate(C1373R.layout.layout_membermanager_footview, null);
        this.f5184p = (TextView) inflate.findViewById(C1373R.id.clubfootviewtv);
        this.f5175g.addFooterView(inflate, null, true);
        this.f5184p.setText(this.f5186r + "/" + this.f5185q);
        this.f5176h = new ClubMemberManagerActivity$a(this, this.f5177i, this.f5178j);
        this.f5178j = new ClubManager(this);
        this.f5175g.setOnItemClickListener(this);
        this.f5175g.setAdapter(this.f5176h);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menu != null && menu.size() > 0) {
            menu.getItem(0).setTitle(getResources().getString(C1373R.string.manage));
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (!this.f5183o || this.f5187s) {
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.member_manager_menu_item:
                if (this.f5182n) {
                    this.f5182n = false;
                    menuItem.setTitle(getResources().getString(C1373R.string.manage));
                    this.f5176h.notifyDataSetChanged();
                    return true;
                }
                this.f5182n = true;
                menuItem.setTitle(getResources().getString(C1373R.string.activity_state_label_finish));
                this.f5176h.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    protected void onResume() {
        super.onResume();
        m6506a();
    }

    public void finish() {
        this.f5182n = false;
        this.f5176h.notifyDataSetChanged();
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        RankDTO rankDTO = (RankDTO) adapterView.getItemAtPosition(i);
        if (rankDTO != null) {
            Intent intent = new Intent();
            if (this.f5187s) {
                C2621c c2621c = new C2621c(this);
                c2621c.b(true);
                c2621c.b(String.format(getString(C1373R.string.club_member_transfer_msg), new Object[]{rankDTO.getNickname()}));
                c2621c.a(C1373R.string.club_member_transfer_ok, new ClubMemberManagerActivity$2(this, c2621c, intent, rankDTO)).b(C1373R.string.cancel, new ClubMemberManagerActivity$1(this, c2621c)).a();
                return;
            }
            intent.setClass(this, ProfileActivity.class);
            intent.putExtra("user_id", rankDTO.getUserId());
            intent.putExtra("avatar", rankDTO.getAvatarUrl());
            intent.putExtra("city", rankDTO.getCity());
            intent.putExtra("remarks", rankDTO.getRemarks());
            startActivity(intent);
        }
    }

    /* renamed from: a */
    private void m6506a() {
        getAsyncTaskQueue().a(new ClubMemberManagerActivity$3(this), new Void[0]);
    }
}
