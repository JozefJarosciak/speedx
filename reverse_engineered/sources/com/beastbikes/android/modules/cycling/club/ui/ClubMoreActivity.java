package com.beastbikes.android.modules.cycling.club.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.p168e.C2655d;
import com.beastbikes.android.widget.p168e.p169a.C2642c;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;

@C1457a(a = "俱乐部管理")
@C1459b(a = 2130903095)
public class ClubMoreActivity extends SessionFragmentActivity implements OnClickListener, C1371a {
    /* renamed from: A */
    private TextView f5193A;
    /* renamed from: B */
    private ImageView f5194B;
    /* renamed from: C */
    private TextView f5195C;
    @C1458a(a = 2131755477)
    /* renamed from: D */
    private ViewGroup f5196D;
    /* renamed from: E */
    private TextView f5197E;
    /* renamed from: F */
    private ImageView f5198F;
    /* renamed from: G */
    private TextView f5199G;
    @C1458a(a = 2131755485)
    /* renamed from: H */
    private ViewGroup f5200H;
    @C1458a(a = 2131755486)
    /* renamed from: I */
    private ViewGroup f5201I;
    /* renamed from: J */
    private TextView f5202J;
    /* renamed from: K */
    private ImageView f5203K;
    /* renamed from: L */
    private TextView f5204L;
    @C1458a(a = 2131755487)
    /* renamed from: M */
    private ViewGroup f5205M;
    /* renamed from: N */
    private View f5206N;
    /* renamed from: O */
    private ClubManager f5207O;
    /* renamed from: P */
    private SharedPreferences f5208P;
    /* renamed from: Q */
    private C2655d f5209Q;
    /* renamed from: R */
    private String f5210R;
    /* renamed from: S */
    private String f5211S;
    /* renamed from: T */
    private String f5212T = "";
    /* renamed from: U */
    private int f5213U = 0;
    /* renamed from: V */
    private ClubInfoCompact f5214V;
    /* renamed from: W */
    private C1802i f5215W;
    /* renamed from: X */
    private C2642c f5216X;
    /* renamed from: Y */
    private String f5217Y = "俱乐部招人啦！！！";
    /* renamed from: Z */
    private String f5218Z = "http://img.wdjimg.com/mms/icon/v1/0/02/d7f68ce13acfe90d00b7527b2ec57020_256_256.png";
    @C1458a(a = 2131755475)
    /* renamed from: a */
    private LinearLayout f5219a;
    private String aa;
    private String ab = "我在野兽骑行找到了组织～快加入我们俱乐部一起愉快的玩耍吧！";
    private int ac;
    private int ad;
    @C1458a(a = 2131755474)
    /* renamed from: b */
    private ViewGroup f5220b;
    /* renamed from: c */
    private TextView f5221c;
    /* renamed from: d */
    private ImageView f5222d;
    /* renamed from: e */
    private TextView f5223e;
    /* renamed from: f */
    private TextView f5224f;
    @C1458a(a = 2131755484)
    /* renamed from: g */
    private ViewGroup f5225g;
    /* renamed from: h */
    private TextView f5226h;
    /* renamed from: i */
    private ImageView f5227i;
    /* renamed from: j */
    private TextView f5228j;
    @C1458a(a = 2131755480)
    /* renamed from: k */
    private ViewGroup f5229k;
    /* renamed from: l */
    private TextView f5230l;
    /* renamed from: m */
    private ImageView f5231m;
    /* renamed from: n */
    private TextView f5232n;
    @C1458a(a = 2131755479)
    /* renamed from: o */
    private View f5233o;
    @C1458a(a = 2131755478)
    /* renamed from: p */
    private ViewGroup f5234p;
    /* renamed from: q */
    private TextView f5235q;
    /* renamed from: r */
    private ImageView f5236r;
    /* renamed from: s */
    private TextView f5237s;
    @C1458a(a = 2131755481)
    /* renamed from: t */
    private View f5238t;
    @C1458a(a = 2131755482)
    /* renamed from: u */
    private ViewGroup f5239u;
    /* renamed from: v */
    private TextView f5240v;
    /* renamed from: w */
    private ImageView f5241w;
    /* renamed from: x */
    private TextView f5242x;
    @C1458a(a = 2131755483)
    /* renamed from: y */
    private View f5243y;
    @C1458a(a = 2131755476)
    /* renamed from: z */
    private ViewGroup f5244z;

    @SuppressLint({"InflateParams"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5207O = new ClubManager(this);
        this.f5208P = getSharedPreferences(m5331p(), 0);
        this.f5225g.setOnClickListener(this);
        this.f5226h = (TextView) this.f5225g.findViewById(C1373R.id.universal_item_lable);
        this.f5226h.setText(C1373R.string.activity_club_manager_item_invite);
        this.f5227i = (ImageView) this.f5225g.findViewById(C1373R.id.universal_item_icon);
        this.f5227i.setImageResource(C1373R.drawable.ic_club_manager_invite);
        this.f5228j = (TextView) this.f5225g.findViewById(C1373R.id.universal_item_dot);
        this.f5229k.setOnClickListener(this);
        this.f5230l = (TextView) this.f5229k.findViewById(C1373R.id.universal_item_lable);
        this.f5230l.setText(C1373R.string.activity_club_manager_item_apply);
        this.f5231m = (ImageView) this.f5229k.findViewById(C1373R.id.universal_item_icon);
        this.f5231m.setImageResource(C1373R.drawable.ic_club_manager_apply);
        this.f5232n = (TextView) this.f5229k.findViewById(C1373R.id.universal_item_dot);
        this.f5232n.setVisibility(8);
        this.f5234p.setOnClickListener(this);
        this.f5235q = (TextView) this.f5234p.findViewById(C1373R.id.universal_item_lable);
        this.f5235q.setText(C1373R.string.activity_club_manager_item_member);
        this.f5236r = (ImageView) this.f5234p.findViewById(C1373R.id.universal_item_icon);
        this.f5237s = (TextView) this.f5234p.findViewById(C1373R.id.universal_item_dot);
        this.f5237s.setVisibility(8);
        this.f5236r.setImageResource(C1373R.drawable.ic_club_manager_mumber);
        this.f5239u.setOnClickListener(this);
        this.f5240v = (TextView) this.f5239u.findViewById(C1373R.id.universal_item_lable);
        this.f5240v.setText(C1373R.string.activity_club_manager_item_setting);
        this.f5241w = (ImageView) this.f5239u.findViewById(C1373R.id.universal_item_icon);
        this.f5241w.setImageResource(C1373R.drawable.ic_club_manager_setting);
        this.f5242x = (TextView) this.f5239u.findViewById(C1373R.id.universal_item_dot);
        this.f5242x.setVisibility(8);
        this.f5244z.setOnClickListener(this);
        this.f5193A = (TextView) this.f5244z.findViewById(C1373R.id.universal_item_lable);
        this.f5193A.setText(C1373R.string.clubfeed_level);
        this.f5194B = (ImageView) this.f5244z.findViewById(C1373R.id.universal_item_icon);
        this.f5194B.setImageResource(C1373R.drawable.ic_club_level);
        this.f5195C = (TextView) this.f5244z.findViewById(C1373R.id.universal_item_dot);
        this.f5195C.setVisibility(8);
        this.f5196D.setOnClickListener(this);
        this.f5197E = (TextView) this.f5196D.findViewById(C1373R.id.universal_item_lable);
        this.f5197E.setText(C1373R.string.club_ablum);
        this.f5198F = (ImageView) this.f5196D.findViewById(C1373R.id.universal_item_icon);
        this.f5198F.setImageResource(C1373R.drawable.ic_club_manager_ablum);
        this.f5199G = (TextView) this.f5196D.findViewById(C1373R.id.universal_item_dot);
        this.f5199G.setVisibility(8);
        this.f5200H.setVisibility(8);
        this.f5201I.setOnClickListener(this);
        this.f5202J = (TextView) this.f5201I.findViewById(C1373R.id.universal_item_lable);
        this.f5202J.setText(getResources().getString(C1373R.string.activity_club_manager_item_transfer));
        this.f5203K = (ImageView) this.f5201I.findViewById(C1373R.id.universal_item_icon);
        this.f5203K.setImageResource(C1373R.drawable.ic_transfer);
        this.f5204L = (TextView) this.f5201I.findViewById(C1373R.id.universal_item_dot);
        this.f5204L.setVisibility(8);
        this.f5205M.setOnClickListener(this);
        this.f5221c = (TextView) this.f5220b.findViewById(C1373R.id.universal_item_lable);
        this.f5222d = (ImageView) this.f5220b.findViewById(C1373R.id.universal_item_icon);
        this.f5223e = (TextView) this.f5220b.findViewById(C1373R.id.universal_item_dot);
        this.f5224f = (TextView) this.f5220b.findViewById(C1373R.id.universal_item_no_text_dot);
        this.f5221c.setText(C1373R.string.club_notice_history);
        this.f5222d.setImageResource(C1373R.drawable.ic_club_manager_history);
        this.f5220b.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent != null) {
            this.f5210R = intent.getStringExtra("club_id");
            this.f5214V = (ClubInfoCompact) getIntent().getSerializableExtra("club_info");
            this.f5212T = this.f5214V.getNotice();
            this.aa = "https://www.speedx.com/app/club/shareClub.html?clubId=" + this.f5214V.getObjectId();
        }
        this.f5206N = LayoutInflater.from(this).inflate(C1373R.layout.browser_activity, null);
    }

    protected void onResume() {
        super.onResume();
        m6530a();
        m6538b();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1009 && intent != null) {
            m6535a(intent.getStringExtra("club_member"), intent.getStringExtra("club_member_name"), intent.getIntExtra("is_quit", 0));
        }
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case C1373R.id.activity_club_manager_history_notice:
                intent = new Intent(this, ClubHistoryNoticeActivity.class);
                intent.putExtra("club_id", this.f5210R);
                intent.putExtra("level", this.f5213U);
                if (!TextUtils.isEmpty(this.f5212T)) {
                    intent.putExtra("notice", this.f5212T);
                }
                startActivity(intent);
                this.f5208P.edit().putInt("beast.club.dot.more", 0).commit();
                return;
            case C1373R.id.activity_club_level:
                intent = new Intent(this, ClubLevelActivity.class);
                intent.putExtra("club_info", this.f5214V);
                startActivity(intent);
                return;
            case C1373R.id.activity_club_manager_ablum:
                C2580w.a(this, "俱乐部相册", null);
                if (AVUser.getCurrentUser() == null) {
                    return;
                }
                if (AVUser.getCurrentUser().getClubId().equals(this.f5214V.getObjectId())) {
                    intent = new Intent(this, ClubGalleryActivity.class);
                    intent.putExtra("club_id", this.f5214V.getObjectId());
                    intent.putExtra("photo_max_count", this.f5214V.getMaxPhotoNum());
                    intent.putExtra("photo_count", this.f5214V.getCurPhotoNum());
                    intent.putExtra("club_status", this.f5214V.getStatus());
                    intent.putExtra("club_manager_id", this.f5214V.getManagerId());
                    intent.putExtra("level", this.f5214V.getLevel());
                    startActivityForResult(intent, 4);
                    return;
                }
                Toasts.show(this, getResources().getString(C1373R.string.cannotlookbeforejoin));
                return;
            case C1373R.id.activity_club_manager_member:
                intent = new Intent(this, ClubMemberManagerActivity.class);
                intent.putExtra("club_id", this.f5210R);
                Bundle bundle = new Bundle();
                bundle.putSerializable("club_info", this.f5214V);
                intent.putExtras(bundle);
                startActivity(intent);
                return;
            case C1373R.id.activity_club_manager_apply:
                startActivity(new Intent(this, ApplyManagerActivity.class));
                return;
            case C1373R.id.activity_club_manager_info_setting:
                intent = new Intent(this, ClubInfoSettingActivity.class);
                intent.putExtra("club_id", this.f5210R);
                startActivity(intent);
                return;
            case C1373R.id.activity_club_manager_invite:
                C2580w.a(this, "邀请好友成功分享到外网", "invite_friends");
                m6544d();
                return;
            case C1373R.id.activity_club_manager_transfer:
                if (this.f5214V.getMembers() > 1) {
                    m6531a(0);
                    return;
                }
                C2621c c2621c = new C2621c(this);
                c2621c.b(C1373R.string.club_member_transfer_check_tip);
                c2621c.a(C1373R.string.club_release_activites_dialog_ok, new ClubMoreActivity$1(this, c2621c)).a();
                return;
            case C1373R.id.activity_club_manager_quit_club:
                C2580w.a(this, "", "click_exit_club");
                m6534a(this.f5214V.getObjectId());
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m6530a() {
        int i = this.f5208P.getInt("beast.club.dot.more", 0);
        if (i > 0) {
            this.f5232n.setVisibility(0);
            this.f5232n.setText(String.valueOf(i));
            return;
        }
        this.f5232n.setVisibility(8);
    }

    /* renamed from: b */
    private void m6538b() {
        getAsyncTaskQueue().a(new ClubMoreActivity$6(this, m5331p()), new Void[0]);
    }

    /* renamed from: c */
    private void m6541c() {
        int i;
        C2621c c2621c;
        Object obj = null;
        Object obj2 = this.f5213U == 128 ? 1 : null;
        if (obj2 == null || this.f5214V.getMembers() <= 1) {
            obj = 1;
        }
        this.ac = C1373R.string.club_info_waiting;
        this.ad = C1373R.string.activity_club_manager_quit_club_success;
        if (obj2 != null) {
            this.ac = C1373R.string.club_quit_waitting;
            this.ad = C1373R.string.activity_club_manager_quit_club_success1;
            if (this.f5214V.getMembers() > 1) {
                i = C1373R.string.activity_club_manager_quit_tip;
                c2621c = new C2621c(this);
                c2621c.b(i);
                if (obj == null) {
                    c2621c.a(C1373R.string.activity_alert_dialog_text_ok, new ClubMoreActivity$8(this, c2621c)).b(C1373R.string.cancel, new ClubMoreActivity$7(this, c2621c)).a();
                } else {
                    c2621c.b(C1373R.string.activity_alert_dialog_text_ok, new ClubMoreActivity$9(this, c2621c)).a();
                }
            }
        }
        i = C1373R.string.activity_club_manager_dialog_title;
        c2621c = new C2621c(this);
        c2621c.b(i);
        if (obj == null) {
            c2621c.b(C1373R.string.activity_alert_dialog_text_ok, new ClubMoreActivity$9(this, c2621c)).a();
        } else {
            c2621c.a(C1373R.string.activity_alert_dialog_text_ok, new ClubMoreActivity$8(this, c2621c)).b(C1373R.string.cancel, new ClubMoreActivity$7(this, c2621c)).a();
        }
    }

    /* renamed from: a */
    private void m6534a(String str) {
        if (!TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new ClubMoreActivity$10(this), new String[]{str});
        }
    }

    /* renamed from: d */
    private void m6544d() {
        if (this.f5209Q == null) {
            this.f5216X = new C2642c(this.f5218Z, this.f5217Y, this.aa, this.ab, this.ab + getString(C1373R.string.weibo_offical), getString(C1373R.string.club_invate_wechat));
            this.f5209Q = new C2655d(this, this.f5216X, "");
            this.f5209Q.a(getResources().getString(C1373R.string.activity_club_manager_invite_share_title));
        }
        this.f5209Q.showAtLocation(this.f5206N, 81, 0, 0);
    }

    /* renamed from: a */
    private void m6532a(int i, int i2) {
        getAsyncTaskQueue().a(new ClubMoreActivity$11(this, this, i), new Void[0]);
    }

    /* renamed from: a */
    private void m6531a(int i) {
        getAsyncTaskQueue().a(new ClubMoreActivity$12(this, this, i), new Void[0]);
    }

    /* renamed from: a */
    private void m6535a(String str, String str2, int i) {
        getAsyncTaskQueue().a(new ClubMoreActivity$13(this, this, str, i), new Void[0]);
    }

    /* renamed from: b */
    private void m6539b(String str) {
        if (!TextUtils.isEmpty(str)) {
            C2621c c2621c = new C2621c(this);
            c2621c.b(true);
            c2621c.b(String.format(getString(C1373R.string.club_more_activity_transfer_message), new Object[]{str}));
            c2621c.a(C1373R.string.club_more_activity_transfer_title);
            c2621c.a(C1373R.string.club_transfer_activity_cancel_transfer, new ClubMoreActivity$3(this, c2621c)).b(C1373R.string.club_transfer_activity_tip_transfer, new ClubMoreActivity$2(this, c2621c)).a();
        }
    }

    /* renamed from: e */
    private void m6546e() {
        getAsyncTaskQueue().a(new ClubMoreActivity$4(this, this), new Void[0]);
    }

    /* renamed from: f */
    private void m6548f() {
        getAsyncTaskQueue().a(new ClubMoreActivity$5(this, this), new Void[0]);
    }
}
