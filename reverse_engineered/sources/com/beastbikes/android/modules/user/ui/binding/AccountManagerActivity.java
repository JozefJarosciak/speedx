package com.beastbikes.android.modules.user.ui.binding;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import cn.sharesdk.facebook.Facebook;
import cn.sharesdk.google.GooglePlus;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.twitter.Twitter;
import cn.sharesdk.wechat.friends.Wechat;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.C1537a;
import com.beastbikes.android.authentication.C1541b;
import com.beastbikes.android.authentication.C1541b.C1540b;
import com.beastbikes.android.authentication.p055a.C1536a;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.modules.user.ui.binding.p159a.C2510a;
import com.beastbikes.android.modules.user.ui.p157a.C2493b;
import com.beastbikes.android.modules.user.ui.p157a.C2493b.C2491a;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.List;

@C1457a(a = "账号与密码页")
@C1459b(a = 2130903068)
public class AccountManagerActivity extends BaseAccountActivity implements OnClickListener, C1540b, C2491a {
    @C1458a(a = 2131755306)
    /* renamed from: d */
    private TextView f6730d;
    @C1458a(a = 2131755307)
    /* renamed from: e */
    private TextView f6731e;
    @C1458a(a = 2131755309)
    /* renamed from: f */
    private TextView f6732f;
    @C1458a(a = 2131755310)
    /* renamed from: g */
    private TextView f6733g;
    @C1458a(a = 2131755312)
    /* renamed from: h */
    private TextView f6734h;
    @C1458a(a = 2131755313)
    /* renamed from: i */
    private TextView f6735i;
    @C1458a(a = 2131755308)
    /* renamed from: j */
    private View f6736j;
    @C1458a(a = 2131755305)
    /* renamed from: k */
    private View f6737k;
    @C1458a(a = 2131755311)
    /* renamed from: l */
    private View f6738l;
    @C1458a(a = 2131755314)
    /* renamed from: m */
    private ListView f6739m;
    /* renamed from: n */
    private SparseArray<AccountDTO> f6740n = new SparseArray();
    /* renamed from: o */
    private C2493b f6741o;
    /* renamed from: p */
    private C1536a f6742p;
    /* renamed from: q */
    private C2511a f6743q;
    /* renamed from: r */
    private C2621c f6744r;
    /* renamed from: s */
    private AccountDTO f6745s;
    /* renamed from: t */
    private AccountDTO f6746t;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m7930f();
        this.f6742p = new C1536a(this);
        this.f6741o = new C2493b(this.f6740n, this, this);
        this.f6739m.setAdapter(this.f6741o);
        m7921b();
        e(2);
        e(1);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    /* renamed from: b */
    protected void m7934b(int i, Object obj) {
        super.b(i, obj);
        switch (i) {
            case 1:
                if (obj != null) {
                    m7925c((List) obj);
                }
                d();
                return;
            case 2:
                if (obj != null) {
                    try {
                        m7925c((List) obj);
                        return;
                    } catch (ClassCastException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                return;
            case 3:
                if (obj != null) {
                    this.f6746t.setAuthKey(String.valueOf(obj));
                }
                m7927e();
                d();
                return;
            case 4:
                if (obj != null) {
                    this.f6745s.setAuthKey(String.valueOf(obj));
                }
                m7927e();
                d();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m7932a(AccountDTO accountDTO) {
        if (accountDTO != null) {
            if (accountDTO.getStatus() == 1) {
                m7922b(accountDTO);
                return;
            }
            switch (accountDTO.getAuthType()) {
                case 4:
                    C1541b.a(this, SinaWeibo.NAME, this);
                    break;
                case 8:
                    C1541b.a(this, QQ.NAME, this);
                    break;
                case 16:
                    C1541b.a(this, Wechat.NAME, this);
                    break;
                case 32:
                    C1541b.a(this, Twitter.NAME, this);
                    break;
                case 64:
                    C1541b.a(this, Facebook.NAME, this);
                    break;
                case 128:
                    C1541b.a(this, GooglePlus.NAME, this);
                    break;
            }
            m7927e();
        }
    }

    public void onClick(View view) {
        boolean z = false;
        boolean z2 = true;
        Intent intent;
        String str;
        switch (view.getId()) {
            case C1373R.id.ll_activity_account_and_pwd_mobile:
                if (this.f6746t.getStatus() == 1) {
                    intent = new Intent(this, BindMailPhoneSuccessActivity.class);
                    e(3);
                } else {
                    intent = new Intent(this, ResetBindPhoneActivity.class);
                }
                str = "is_bind_mail";
                if (this.f6745s.getStatus() != 1) {
                    z2 = false;
                }
                intent.putExtra(str, z2);
                intent.putExtra("is_mail", false);
                intent.putExtra("account_dto", this.f6746t);
                startActivity(intent);
                return;
            case C1373R.id.ll_activity_account_and_pwd_email:
                if (this.f6745s.getStatus() == 1) {
                    intent = new Intent(this, BindMailPhoneSuccessActivity.class);
                    e(4);
                } else {
                    intent = new Intent(this, ResetBindMailActivity.class);
                }
                str = "is_bound_phone";
                if (this.f6746t.getStatus() == 1) {
                    z = true;
                }
                intent.putExtra(str, z);
                intent.putExtra("is_mail", true);
                intent.putExtra("account_dto", this.f6745s);
                startActivity(intent);
                return;
            case C1373R.id.ll_activity_account_and_pwd_update_pwd:
                intent = new Intent(this, ResetPwdActivity.class);
                intent.putExtra("is_reset", true);
                startActivity(intent);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m7931a(C1537a c1537a) {
        runOnUiThread(new AccountManagerActivity$1(this, c1537a));
    }

    /* renamed from: b */
    private void m7921b() {
        m7925c(this.f6742p.b());
        getAsyncTaskQueue().a(new AccountManagerActivity$2(this), new Void[0]);
    }

    /* renamed from: a */
    protected void m7933a(List<AccountDTO> list) {
        super.a(list);
        m7925c((List) list);
    }

    /* renamed from: b */
    protected void m7935b(List<AccountDTO> list) {
        super.b(list);
        if (list != null && list.size() > 0) {
            Toasts.show(this, C1373R.string.bind_speed_force_success);
        }
        m7925c((List) list);
    }

    /* renamed from: c */
    private void m7925c(List<AccountDTO> list) {
        if (list != null) {
            for (AccountDTO accountDTO : list) {
                AccountDTO accountDTO2 = (AccountDTO) this.f6740n.get(accountDTO.getAuthType());
                if (accountDTO2 != null) {
                    accountDTO2.clone(accountDTO);
                }
            }
        }
        this.f6745s = (AccountDTO) this.f6740n.get(1);
        this.f6746t = (AccountDTO) this.f6740n.get(2);
        m7927e();
    }

    /* renamed from: e */
    private void m7927e() {
        boolean z;
        CharSequence a;
        boolean z2 = this.f6745s.getStatus() == 1;
        if (this.f6746t.getStatus() == 1) {
            z = true;
        } else {
            z = false;
        }
        TextView textView = this.f6733g;
        if (z2) {
            a = C2510a.a(this.f6745s.getAuthKey());
        } else {
            a = getString(C1373R.string.str_account_manage_unbind);
        }
        textView.setText(a);
        textView = this.f6731e;
        if (z) {
            a = C2510a.b(this.f6746t.getAuthKey());
        } else {
            a = getString(C1373R.string.str_account_manage_unbind);
        }
        textView.setText(a);
        this.f6730d.setEnabled(z);
        this.f6732f.setEnabled(z2);
        if (z2 || z) {
            this.f6735i.setText("");
            this.f6734h.setEnabled(true);
            this.f6738l.setOnClickListener(this);
        } else {
            this.f6734h.setEnabled(false);
        }
        this.f6736j.setOnClickListener(this);
        this.f6737k.setOnClickListener(this);
        this.f6741o.notifyDataSetChanged();
    }

    /* renamed from: f */
    private void m7930f() {
        this.f6740n.put(2, new AccountDTO(getString(C1373R.string.activity_account_management_phone_str), C1373R.drawable.selector_ic_account_mobile, 2));
        this.f6740n.put(1, new AccountDTO(getString(C1373R.string.activity_account_management_mail_str), C1373R.drawable.selector_ic_account_mail, 1));
        if (C1849a.a()) {
            this.f6740n.put(4, new AccountDTO(getString(C1373R.string.activity_account_management_weibo_str), C1373R.drawable.selector_ic_account_wieibo, 4));
            this.f6740n.put(8, new AccountDTO(getString(C1373R.string.activity_account_management_qq_str), C1373R.drawable.selector_ic_account_qq, 8));
            this.f6740n.put(16, new AccountDTO(getString(C1373R.string.activity_account_management_wechat_str), C1373R.drawable.selector_ic_account_wechat, 16));
        }
        this.f6740n.put(64, new AccountDTO(getString(C1373R.string.activity_account_management_facebook_str), C1373R.drawable.selector_ic_account_facebook, 64));
        this.f6740n.put(128, new AccountDTO(getString(C1373R.string.activity_account_management_googleplus_str), C1373R.drawable.selector_ic_account_googleplus, 128));
        this.f6740n.put(32, new AccountDTO(getString(C1373R.string.activity_account_management_twitter_str), C1373R.drawable.selector_ic_account_twitter, 32));
    }

    /* renamed from: b */
    private void m7922b(AccountDTO accountDTO) {
        if (accountDTO.getStatus() == 1) {
            this.f6743q = new C2511a(this, new AccountManagerActivity$3(this, accountDTO));
            this.f6743q.setOnDismissListener(new AccountManagerActivity$4(this));
            this.f6743q.showAtLocation(findViewById(C1373R.id.activity_account_management), 81, 0, 0);
        }
    }

    /* renamed from: c */
    private void m7924c(AccountDTO accountDTO) {
        this.f6744r = new C2621c(this).b(getString(C1373R.string.activity_account_management_dialog_ms_head) + accountDTO.getAccountName() + getString(C1373R.string.activity_account_management_dialog_ms_tail)).a(C1373R.string.club_release_activites_dialog_ok, new AccountManagerActivity$6(this, accountDTO)).b(C1373R.string.activity_alert_dialog_text_cancel, new AccountManagerActivity$5(this));
        this.f6744r.a();
    }
}
