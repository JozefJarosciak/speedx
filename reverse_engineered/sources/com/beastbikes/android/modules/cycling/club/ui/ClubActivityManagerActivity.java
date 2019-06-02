package com.beastbikes.android.modules.cycling.club.ui;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2049a;
import com.beastbikes.android.modules.cycling.club.dto.ClubActUserList;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityListDTO;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityUser;
import com.beastbikes.android.modules.cycling.p064b.p065a.C2047a;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.p168e.C2655d;
import com.beastbikes.android.widget.p168e.p169a.C2642c;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1457a(a = "俱乐部管理活动页")
@C1459b(a = 2130903084)
public class ClubActivityManagerActivity extends SessionFragmentActivity implements OnClickListener {
    /* renamed from: A */
    private TextView f4869A;
    /* renamed from: B */
    private ImageView f4870B;
    @C1458a(a = 2131755434)
    /* renamed from: C */
    private ViewGroup f4871C;
    /* renamed from: D */
    private TextView f4872D;
    /* renamed from: E */
    private ImageView f4873E;
    @C1458a(a = 2131755435)
    /* renamed from: F */
    private ViewGroup f4874F;
    /* renamed from: G */
    private TextView f4875G;
    /* renamed from: H */
    private ImageView f4876H;
    @C1458a(a = 2131755436)
    /* renamed from: I */
    private ViewGroup f4877I;
    /* renamed from: J */
    private TextView f4878J;
    /* renamed from: K */
    private ImageView f4879K;
    @C1458a(a = 2131755437)
    /* renamed from: L */
    private ViewGroup f4880L;
    /* renamed from: M */
    private TextView f4881M;
    /* renamed from: N */
    private ImageView f4882N;
    @C1458a(a = 2131755424)
    /* renamed from: O */
    private TextView f4883O;
    @C1458a(a = 2131755420)
    /* renamed from: P */
    private View f4884P;
    @C1458a(a = 2131755438)
    /* renamed from: Q */
    private RelativeLayout f4885Q;
    @C1458a(a = 2131755425)
    /* renamed from: R */
    private LinearLayout f4886R;
    /* renamed from: S */
    private C2655d f4887S;
    /* renamed from: T */
    private C2047a f4888T;
    /* renamed from: U */
    private C1802i f4889U;
    /* renamed from: V */
    private String f4890V;
    /* renamed from: W */
    private String f4891W;
    /* renamed from: X */
    private String f4892X;
    /* renamed from: Y */
    private String f4893Y;
    /* renamed from: Z */
    private boolean f4894Z;
    /* renamed from: a */
    private Logger f4895a = LoggerFactory.getLogger(ClubActivityManagerActivity.class);
    private List<ClubActivityUser> aa;
    private C2642c ab;
    @C1458a(a = 2131755421)
    /* renamed from: b */
    private TextView f4896b;
    @C1458a(a = 2131755422)
    /* renamed from: c */
    private TextView f4897c;
    /* renamed from: d */
    private C2049a f4898d;
    /* renamed from: e */
    private ClubActivityListDTO f4899e;
    /* renamed from: f */
    private int f4900f = 1;
    /* renamed from: g */
    private int f4901g = 20;
    @C1458a(a = 2131755423)
    /* renamed from: h */
    private ImageView f4902h;
    @C1458a(a = 2131755426)
    /* renamed from: i */
    private ViewGroup f4903i;
    /* renamed from: j */
    private ImageView f4904j;
    @C1458a(a = 2131755427)
    /* renamed from: k */
    private ViewGroup f4905k;
    /* renamed from: l */
    private ImageView f4906l;
    @C1458a(a = 2131755428)
    /* renamed from: m */
    private ViewGroup f4907m;
    /* renamed from: n */
    private ImageView f4908n;
    @C1458a(a = 2131755429)
    /* renamed from: o */
    private ViewGroup f4909o;
    /* renamed from: p */
    private ImageView f4910p;
    @C1458a(a = 2131755430)
    /* renamed from: q */
    private ViewGroup f4911q;
    /* renamed from: r */
    private ImageView f4912r;
    @C1458a(a = 2131755431)
    /* renamed from: s */
    private ViewGroup f4913s;
    /* renamed from: t */
    private ImageView f4914t;
    /* renamed from: u */
    private List<ImageView> f4915u;
    @C1458a(a = 2131755432)
    /* renamed from: v */
    private ViewGroup f4916v;
    /* renamed from: w */
    private TextView f4917w;
    /* renamed from: x */
    private ImageView f4918x;
    /* renamed from: y */
    private TextView f4919y;
    @C1458a(a = 2131755433)
    /* renamed from: z */
    private ViewGroup f4920z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent != null) {
            this.f4904j = (ImageView) this.f4903i.findViewById(C1373R.id.activity_club_act_manager_member_avater);
            this.f4906l = (ImageView) this.f4905k.findViewById(C1373R.id.activity_club_act_manager_member_avater);
            this.f4908n = (ImageView) this.f4907m.findViewById(C1373R.id.activity_club_act_manager_member_avater);
            this.f4910p = (ImageView) this.f4909o.findViewById(C1373R.id.activity_club_act_manager_member_avater);
            this.f4912r = (ImageView) this.f4911q.findViewById(C1373R.id.activity_club_act_manager_member_avater);
            this.f4914t = (ImageView) this.f4913s.findViewById(C1373R.id.activity_club_act_manager_member_avater);
            this.f4915u = new ArrayList();
            this.f4915u.add(this.f4904j);
            this.f4915u.add(this.f4906l);
            this.f4915u.add(this.f4908n);
            this.f4915u.add(this.f4910p);
            this.f4915u.add(this.f4912r);
            this.f4915u.add(this.f4914t);
            this.f4917w = (TextView) this.f4916v.findViewById(C1373R.id.activity_club_act_manager_item_title);
            this.f4918x = (ImageView) this.f4916v.findViewById(C1373R.id.activity_club_act_manager_item_icon);
            this.f4869A = (TextView) this.f4920z.findViewById(C1373R.id.activity_club_act_manager_item_title);
            this.f4870B = (ImageView) this.f4920z.findViewById(C1373R.id.activity_club_act_manager_item_icon);
            this.f4872D = (TextView) this.f4871C.findViewById(C1373R.id.activity_club_act_manager_item_title);
            this.f4873E = (ImageView) this.f4871C.findViewById(C1373R.id.activity_club_act_manager_item_icon);
            this.f4875G = (TextView) this.f4874F.findViewById(C1373R.id.activity_club_act_manager_item_title);
            this.f4876H = (ImageView) this.f4874F.findViewById(C1373R.id.activity_club_act_manager_item_icon);
            this.f4878J = (TextView) this.f4877I.findViewById(C1373R.id.activity_club_act_manager_item_title);
            this.f4879K = (ImageView) this.f4877I.findViewById(C1373R.id.activity_club_act_manager_item_icon);
            this.f4881M = (TextView) this.f4880L.findViewById(C1373R.id.activity_club_act_manager_item_title);
            this.f4882N = (ImageView) this.f4880L.findViewById(C1373R.id.activity_club_act_manager_item_icon);
            this.f4919y = (TextView) this.f4916v.findViewById(C1373R.id.activity_club_act_manager_item_count);
            this.f4919y.setVisibility(0);
            this.f4917w.setText(getResources().getString(C1373R.string.activity_sign_in));
            this.f4869A.setText(getResources().getString(C1373R.string.activity_batch_message));
            this.f4872D.setText(getResources().getString(C1373R.string.check_club_activity));
            this.f4875G.setText(getResources().getString(C1373R.string.str_edit));
            this.f4878J.setText(getResources().getString(C1373R.string.repost_club_activity));
            this.f4881M.setText(getResources().getString(C1373R.string.cancel_club_activity));
            this.f4918x.setImageResource(C1373R.drawable.ic_club_activity_signin);
            this.f4870B.setImageResource(C1373R.drawable.ic_club_activity_batch_message);
            this.f4873E.setImageResource(C1373R.drawable.ic_club_activity_check);
            this.f4876H.setImageResource(C1373R.drawable.ic_club_activity_edit);
            this.f4879K.setImageResource(C1373R.drawable.ic_club_activity_repost);
            this.f4882N.setImageResource(C1373R.drawable.ic_club_activity_cancel);
            this.f4916v.setOnClickListener(this);
            this.f4920z.setOnClickListener(this);
            this.f4871C.setOnClickListener(this);
            this.f4874F.setOnClickListener(this);
            this.f4877I.setOnClickListener(this);
            this.f4880L.setOnClickListener(this);
            this.f4885Q.setOnClickListener(this);
            this.f4886R.setOnClickListener(this);
            this.f4902h.setOnClickListener(this);
            this.f4883O.setOnClickListener(this);
            this.f4898d = new C2049a(this);
            this.f4888T = new C2047a(this);
            this.f4899e = (ClubActivityListDTO) intent.getSerializableExtra(C0861d.f2139k);
            m6267d();
            m6270e();
            m6272f();
            this.f4919y.setText("(" + this.f4899e.getSignInCount() + "/" + this.f4899e.getMembers() + ")");
            if (!C1849a.a()) {
                this.f4916v.setVisibility(8);
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 1:
                if (i2 == -1) {
                    String stringExtra = intent.getStringExtra("scan_result");
                    if (!TextUtils.isEmpty(stringExtra)) {
                        m6286a(stringExtra);
                        return;
                    }
                    return;
                } else if (i2 != 0) {
                    return;
                } else {
                    return;
                }
            case 13:
                this.f4894Z = true;
                return;
            default:
                return;
        }
    }

    public void finish() {
        if (this.f4894Z) {
            setResult(-1, getIntent());
        }
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case C1373R.id.activity_club_act_manager_member:
            case C1373R.id.activity_club_act_manager_member_count:
            case C1373R.id.activity_club_act_manager_member_layout:
                intent = new Intent(this, ClubActUserListActivity.class);
                intent.putExtra(C0861d.f2139k, new ClubActUserList(this.aa));
                intent.putExtra("CLUB_ACT_ID", this.f4899e.getActId());
                intent.putExtra("CLUB_ACT_MEMBERS", this.f4899e.getMembers());
                if (this.f4899e.getMembers() > this.f4901g) {
                    intent.putExtra("IS_CLUB_ACT_LIST_CAN_LOAD_MORE", true);
                }
                startActivity(intent);
                return;
            case C1373R.id.activity_club_act_manager_signin:
                startActivityForResult(new Intent(this, CaptureActivity.class), 1);
                return;
            case C1373R.id.activity_club_act_manager_batch:
                m6287b();
                return;
            case C1373R.id.activity_club_act_manager_check:
                if (this.f4899e != null) {
                    Uri parse = Uri.parse(ClubActivityInfoBrowserActivity.a(this.f4899e.getActId(), this));
                    Intent intent2 = new Intent(this, ClubActivityInfoBrowserActivity.class);
                    intent2.setData(parse);
                    intent2.putExtra("activity_type", 1);
                    intent2.putExtra("activity_id", this.f4899e.getActId());
                    intent2.addCategory("android.intent.category.DEFAULT");
                    intent2.addCategory("android.intent.category.BROWSABLE");
                    intent2.setPackage(getPackageName());
                    startActivity(intent2);
                    return;
                }
                return;
            case C1373R.id.activity_club_act_manager_edit:
                intent = new Intent(this, ClubActivityReleaseActivity.class);
                intent.putExtra("club_activity_manage_tag", 2);
                m6258a(intent);
                return;
            case C1373R.id.activity_club_act_manager_repost:
                intent = new Intent(this, ClubActivityReleaseActivity.class);
                intent.putExtra("club_activity_manage_tag", 1);
                m6258a(intent);
                return;
            case C1373R.id.activity_club_act_manager_cancel:
                C2621c c2621c = new C2621c(this);
                c2621c.b(C1373R.string.sure_to_cancel_club_activity);
                c2621c.a(C1373R.string.activity_alert_dialog_text_ok, new ClubActivityManagerActivity$1(this, c2621c));
                c2621c.b(C1373R.string.activity_alert_dialog_text_cancel, new ClubActivityManagerActivity$4(this, c2621c));
                c2621c.a();
                return;
            case C1373R.id.activity_club_act_manager_invate_rl:
                m6274g();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m6286a(String str) {
        getAsyncTaskQueue().a(new ClubActivityManagerActivity$5(this, str), new Void[0]);
    }

    /* renamed from: a */
    public void m6285a() {
        getAsyncTaskQueue().a(new ClubActivityManagerActivity$6(this), new Void[0]);
    }

    /* renamed from: a */
    private void m6260a(C2642c c2642c) {
        if (this.f4887S == null) {
            this.f4887S = new C2655d(this, c2642c, "俱乐部活动");
        }
        this.f4887S.showAtLocation(this.f4884P, 81, 0, 0);
    }

    /* renamed from: b */
    public void m6287b() {
        Dialog create = new Builder(this).create();
        create.setCanceledOnTouchOutside(true);
        create.show();
        View inflate = LayoutInflater.from(this).inflate(C1373R.layout.dialog_club_act_batch_message, null);
        create.setContentView(inflate);
        TextView textView = (TextView) inflate.findViewById(C1373R.id.dialog_club_act_batch_message_title);
        TextView textView2 = (TextView) inflate.findViewById(C1373R.id.dialog_club_act_batch_message_time);
        TextView textView3 = (TextView) inflate.findViewById(C1373R.id.dialog_club_act_batch_message_location);
        TextView textView4 = (TextView) inflate.findViewById(C1373R.id.dialog_club_act_batch_message_cancel);
        TextView textView5 = (TextView) inflate.findViewById(C1373R.id.dialog_club_act_batch_message_send);
        textView.setText("[" + this.f4899e.getTitle() + "]" + getResources().getString(C1373R.string.batch_message_title));
        textView2.setText(getResources().getString(C1373R.string.str_time) + C2555d.f(C2555d.b(this.f4899e.getStartDate())) + HelpFormatter.DEFAULT_OPT_PREFIX + C2555d.f(C2555d.b(this.f4899e.getEndDate())));
        textView3.setText(getResources().getString(C1373R.string.point_of_convergence) + this.f4899e.getMobPlace());
        textView4.setOnClickListener(new ClubActivityManagerActivity$7(this, create));
        textView5.setOnClickListener(new ClubActivityManagerActivity$8(this, create));
    }

    /* renamed from: c */
    private void m6265c() {
        getAsyncTaskQueue().a(new ClubActivityManagerActivity$9(this), new Void[0]);
    }

    /* renamed from: d */
    private void m6267d() {
        getAsyncTaskQueue().a(new ClubActivityManagerActivity$10(this), new Void[0]);
    }

    /* renamed from: e */
    private void m6270e() {
        getAsyncTaskQueue().a(new ClubActivityManagerActivity$11(this), new Void[0]);
    }

    /* renamed from: f */
    private void m6272f() {
        if (this.f4899e != null && this.f4899e.getApplyStatus() != 0) {
            this.f4916v.setEnabled(false);
            this.f4917w.setTextColor(getResources().getColor(C1373R.color.activity_fragment_tab_bar_item_label_selected));
            this.f4919y.setTextColor(getResources().getColor(C1373R.color.activity_fragment_tab_bar_item_label_selected));
            this.f4920z.setEnabled(false);
            this.f4869A.setTextColor(getResources().getColor(C1373R.color.activity_fragment_tab_bar_item_label_selected));
            this.f4880L.setEnabled(false);
            this.f4881M.setTextColor(getResources().getColor(C1373R.color.activity_fragment_tab_bar_item_label_selected));
            this.f4918x.setImageResource(C1373R.drawable.ic_club_activity_signin_cancel);
            this.f4870B.setImageResource(C1373R.drawable.ic_club_activity_batch_message_cancel);
            this.f4882N.setImageResource(C1373R.drawable.ic_club_activity_cancel_cancel);
        }
    }

    /* renamed from: g */
    private void m6274g() {
        if (!TextUtils.isEmpty(this.f4899e.getActId())) {
            if (TextUtils.isEmpty(this.f4890V)) {
                this.f4889U = new C1802i(this, "", false);
                getAsyncTaskQueue().a(new ClubActivityManagerActivity$2(this), new String[0]);
                return;
            }
            m6260a(this.ab);
        }
    }

    /* renamed from: a */
    private void m6258a(Intent intent) {
        if (!TextUtils.isEmpty(this.f4899e.getActId())) {
            this.f4889U = new C1802i(this, "", true);
            getAsyncTaskQueue().a(new ClubActivityManagerActivity$3(this, intent), new String[0]);
        }
    }
}
