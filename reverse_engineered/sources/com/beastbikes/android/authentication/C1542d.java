package com.beastbikes.android.authentication;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.C1541b.C1540b;
import com.beastbikes.android.authentication.p055a.C1536a;
import com.beastbikes.android.authentication.p055a.C1536a.C1535a;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.business.BusinessException;
import org.json.JSONObject;

/* compiled from: BaseSignPresenter */
/* renamed from: com.beastbikes.android.authentication.d */
public class C1542d implements C1535a {
    /* renamed from: a */
    protected C1530e f7245a;
    /* renamed from: b */
    protected BaseSignActivity f7246b;
    /* renamed from: c */
    protected C1536a f7247c;

    /* compiled from: BaseSignPresenter */
    /* renamed from: com.beastbikes.android.authentication.d$4 */
    class C15474 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1542d f7256a;

        C15474(C1542d c1542d) {
            this.f7256a = c1542d;
        }

        public void run() {
            this.f7256a.f7245a.mo3118l();
        }
    }

    public C1542d(C1530e c1530e) {
        this.f7245a = c1530e;
        this.f7246b = c1530e.mo3110d();
        this.f7247c = new C1536a((BeastBikes) c1530e.mo3110d().getApplication());
    }

    /* renamed from: a */
    public void m8490a() {
        int i = this.f7245a.mo3115i();
        String e = this.f7245a.mo3111e();
        if (TextUtils.isEmpty(e)) {
            this.f7245a.mo3109c(this.f7246b.getString(i == 2 ? C1373R.string.str_please_input_phone_number : C1373R.string.str_please_input_email));
        } else if (i != 2 || e.length() >= 6) {
            String f = this.f7245a.mo3112f();
            if (i == 2 && TextUtils.isEmpty(f)) {
                this.f7245a.mo3109c(this.f7246b.getString(C1373R.string.str_please_input_vcode));
                return;
            }
            String g = this.f7245a.mo3113g();
            if (TextUtils.isEmpty(g)) {
                this.f7245a.mo3109c(this.f7246b.getString(C1373R.string.str_please_input_password));
            } else if (g.length() < 6) {
                this.f7245a.mo3109c(this.f7246b.getString(C1373R.string.str_sign_up_password_limit_6_16));
            } else {
                this.f7245a.mo3118l();
                AccountDTO accountDTO = new AccountDTO();
                accountDTO.setUsername(e);
                accountDTO.setPassword(g);
                accountDTO.setAuthType(i);
                this.f7247c.m8466a(accountDTO, f, (C1535a) this);
            }
        } else {
            this.f7245a.mo3109c(this.f7246b.getString(C1373R.string.str_phone_error));
        }
    }

    /* renamed from: b */
    public void m8493b() {
        Object j = this.f7245a.mo3116j();
        if (!TextUtils.isEmpty(j)) {
            final int i = this.f7245a.mo3115i();
            C1541b.m8484a(this.f7246b, j, new C1540b(this) {
                /* renamed from: b */
                final /* synthetic */ C1542d f7249b;

                /* renamed from: a */
                public void mo3123a(C1537a c1537a) {
                    if (c1537a != null) {
                        this.f7249b.mo3124c();
                        AccountDTO accountDTO = new AccountDTO();
                        accountDTO.setAuthKey(c1537a.m8475b());
                        accountDTO.setAccessToken(c1537a.m8474a());
                        accountDTO.setThirdNick(c1537a.m8476c());
                        accountDTO.setAuthType(i);
                        this.f7249b.f7247c.m8465a(accountDTO, this.f7249b);
                    }
                }
            });
        }
    }

    /* renamed from: a */
    protected void m8492a(final String str, final String str2) {
        this.f7245a.mo3118l();
        this.f7246b.getAsyncTaskQueue().m13740a(new AsyncTask<Object, Object, JSONObject>(this) {
            /* renamed from: c */
            final /* synthetic */ C1542d f7252c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m8497a(objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m8498a((JSONObject) obj);
            }

            /* renamed from: a */
            protected JSONObject m8497a(Object... objArr) {
                return this.f7252c.f7247c.m8462a(str, str2);
            }

            /* renamed from: a */
            protected void m8498a(JSONObject jSONObject) {
                this.f7252c.f7245a.mo3119m();
                if (jSONObject != null) {
                    if (jSONObject.optInt("code", -1) == 0) {
                        this.f7252c.f7245a.mo3109c(this.f7252c.f7246b.getString(C1373R.string.str_vcode_has_sent));
                        this.f7252c.f7245a.mo3106a(9, null);
                        this.f7252c.f7245a.mo3117k();
                        return;
                    }
                    CharSequence optString = jSONObject.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        this.f7252c.f7245a.mo3109c(optString);
                    }
                }
            }
        }, new Object[0]);
    }

    /* renamed from: b */
    protected void m8495b(final String str, final String str2) {
        this.f7245a.mo3118l();
        this.f7246b.getAsyncTaskQueue().m13740a(new AsyncTask<Object, Object, Boolean>(this) {
            /* renamed from: c */
            final /* synthetic */ C1542d f7255c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m8499a(objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m8500a((Boolean) obj);
            }

            /* renamed from: a */
            protected Boolean m8499a(Object... objArr) {
                try {
                    return Boolean.valueOf(this.f7255c.f7247c.m8469b(str, str2));
                } catch (BusinessException e) {
                    e.printStackTrace();
                    return Boolean.valueOf(false);
                }
            }

            /* renamed from: a */
            protected void m8500a(Boolean bool) {
                this.f7255c.f7245a.mo3119m();
                if (bool.booleanValue()) {
                    this.f7255c.f7245a.mo3117k();
                }
            }
        }, new Object[0]);
    }

    /* renamed from: c */
    private void mo3124c() {
        this.f7246b.runOnUiThread(new C15474(this));
    }

    /* renamed from: a */
    public void mo3121a(AuthenticationException authenticationException, AccountDTO accountDTO) {
        this.f7245a.mo3119m();
        if (authenticationException == null) {
            this.f7245a.mo3108b(accountDTO);
        } else if (authenticationException.getErrorNumber() != 1007 || accountDTO.getAuthType() == 1 || accountDTO.getAuthType() == 2) {
            this.f7245a.mo3109c(authenticationException.getMessage());
        } else {
            this.f7245a.mo3107a(accountDTO);
        }
    }

    /* renamed from: b */
    public void mo3122b(AuthenticationException authenticationException, AccountDTO accountDTO) {
        this.f7245a.mo3119m();
        if (authenticationException != null) {
            switch (authenticationException.getErrorNumber()) {
                case 0:
                    this.f7245a.mo3109c(this.f7246b.getString(C1373R.string.str_nickname_has_exist));
                    return;
                case 1008:
                    this.f7245a.mo3109c(authenticationException.getMessage());
                    this.f7245a.mo3120n();
                    return;
                default:
                    this.f7245a.mo3109c(authenticationException.getMessage());
                    return;
            }
        }
        C2580w.m12905a(this.f7246b, this.f7246b.getString(C1373R.string.authentication_event_sign_in), null);
        this.f7245a.mo3108b(accountDTO);
    }
}
