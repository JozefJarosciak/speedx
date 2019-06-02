package com.beastbikes.android.modules.user.ui.binding;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Toast;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.p055a.C1536a;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.List;
import org.json.JSONObject;

public class BaseAccountActivity extends SessionFragmentActivity implements TextWatcher {
    /* renamed from: a */
    protected C1536a f11882a;
    /* renamed from: b */
    protected C2389c f11883b;
    /* renamed from: c */
    protected AccountDTO f11884c;
    /* renamed from: d */
    private ActionBar f11885d;
    /* renamed from: e */
    private C1802i f11886e;

    /* renamed from: com.beastbikes.android.modules.user.ui.binding.BaseAccountActivity$7 */
    class C25057 extends AsyncTask<Object, Object, Void> {
        /* renamed from: a */
        final /* synthetic */ BaseAccountActivity f11881a;

        C25057(BaseAccountActivity baseAccountActivity) {
            this.f11881a = baseAccountActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m12590a(objArr);
        }

        /* renamed from: a */
        protected Void m12590a(Object... objArr) {
            try {
                this.f11881a.f11883b.m12136c(this.f11881a.p());
            } catch (BusinessException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        this.f11885d = getSupportActionBar();
        if (this.f11885d != null) {
            this.f11885d.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public void setTitle(int i) {
        this.f11885d.setTitle(i);
    }

    /* renamed from: a */
    protected void m12593a() {
        if (this.f11886e == null) {
            this.f11886e = new C1802i((Context) this, (int) C1373R.string.loading_msg, true);
        }
        if (getWindow() != null && !isFinishing()) {
            this.f11886e.show();
        }
    }

    /* renamed from: c */
    protected void m12605c() {
        if (getWindow() != null && this.f11886e != null && this.f11886e.isShowing()) {
            this.f11886e.dismiss();
        }
    }

    /* renamed from: a */
    protected void m12594a(final String str, final String str2) {
        if (this.f11882a == null) {
            this.f11882a = new C1536a(this);
        }
        getAsyncTaskQueue().m13740a(new AsyncTask<Object, Object, JSONObject>(this) {
            /* renamed from: c */
            final /* synthetic */ BaseAccountActivity f11859c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m12578a(objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m12579a((JSONObject) obj);
            }

            /* renamed from: a */
            protected JSONObject m12578a(Object... objArr) {
                return this.f11859c.f11882a.m8462a(str, str2);
            }

            /* renamed from: a */
            protected void m12579a(JSONObject jSONObject) {
                super.onPostExecute(jSONObject);
                if (jSONObject != null) {
                    if (jSONObject.optInt("code", -1) == 0) {
                        Toast.makeText(this.f11859c, C1373R.string.str_vcode_has_sent, 0).show();
                        this.f11859c.c(9, null);
                        return;
                    }
                    Object optString = jSONObject.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        Toasts.showOnUiThreadWithText(this.f11859c, optString);
                    }
                }
            }
        }, new Object[0]);
    }

    /* renamed from: a */
    protected void m12597a(String str, String str2, String str3, int i, String str4) {
        if (this.f11882a == null) {
            this.f11882a = new C1536a(this);
        }
        m12593a();
        final String str5 = str;
        final String str6 = str2;
        final String str7 = str3;
        final int i2 = i;
        final String str8 = str4;
        getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, List<AccountDTO>>(this) {
            /* renamed from: f */
            final /* synthetic */ BaseAccountActivity f11865f;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m12580a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m12581a((List) obj);
            }

            /* renamed from: a */
            protected List<AccountDTO> m12580a(Void... voidArr) {
                try {
                    return this.f11865f.f11882a.m8459a(str5, str6, str7, i2, str8);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /* renamed from: a */
            protected void m12581a(List<AccountDTO> list) {
                this.f11865f.m12605c();
                this.f11865f.m12603b((List) list);
            }
        }, new Void[0]);
    }

    /* renamed from: a */
    protected void m12595a(final String str, final String str2, final int i) {
        if (this.f11882a == null) {
            this.f11882a = new C1536a(this);
        }
        m12593a();
        getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, List<AccountDTO>>(this) {
            /* renamed from: d */
            final /* synthetic */ BaseAccountActivity f11869d;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m12582a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m12583a((List) obj);
            }

            /* renamed from: a */
            protected List<AccountDTO> m12582a(Void... voidArr) {
                try {
                    return this.f11869d.f11882a.m8457a(str2, i, str);
                } catch (BusinessException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /* renamed from: a */
            protected void m12583a(List<AccountDTO> list) {
                super.onPostExecute(list);
                if (list != null && list.size() > 0) {
                    Toasts.show(this.f11869d, (int) C1373R.string.str_account_manage_unbind_success);
                }
                this.f11869d.m12605c();
                this.f11869d.m12598a((List) list);
            }
        }, new Void[0]);
    }

    /* renamed from: b */
    protected void m12602b(final String str, final String str2, final int i) {
        if (this.f11882a == null) {
            this.f11882a = new C1536a(this);
        }
        m12593a();
        getAsyncTaskQueue().m13740a(new AsyncTask<Object, Object, Boolean>(this) {
            /* renamed from: d */
            final /* synthetic */ BaseAccountActivity f11873d;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m12584a(objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m12585a((Boolean) obj);
            }

            /* renamed from: a */
            protected Boolean m12584a(Object... objArr) {
                try {
                    return Boolean.valueOf(this.f11873d.f11882a.m8468b(str2, i, str));
                } catch (BusinessException e) {
                    e.printStackTrace();
                    return Boolean.valueOf(false);
                }
            }

            /* renamed from: a */
            protected void m12585a(Boolean bool) {
                super.onPostExecute(bool);
                this.f11873d.m12605c();
                this.f11873d.m12599a(bool.booleanValue(), str2, i);
            }
        }, new Object[0]);
    }

    /* renamed from: a */
    protected void m12596a(final String str, final String str2, final String str3) {
        if (this.f11882a == null) {
            this.f11882a = new C1536a(this);
        }
        m12593a();
        getAsyncTaskQueue().m13740a(new AsyncTask<Object, Object, Boolean>(this) {
            /* renamed from: d */
            final /* synthetic */ BaseAccountActivity f11877d;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m12586a(objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m12587a((Boolean) obj);
            }

            /* renamed from: a */
            protected Boolean m12586a(Object... objArr) {
                try {
                    return Boolean.valueOf(this.f11877d.f11882a.m8469b(str2, str));
                } catch (BusinessException e) {
                    e.printStackTrace();
                    return Boolean.valueOf(false);
                }
            }

            /* renamed from: a */
            protected void m12587a(Boolean bool) {
                super.onPostExecute(bool);
                this.f11877d.m12605c();
                this.f11877d.m12600a(bool.booleanValue(), str, str3);
            }
        }, new Object[0]);
    }

    /* renamed from: b */
    protected void m12601b(final String str, final String str2) {
        if (this.f11882a == null) {
            this.f11882a = new C1536a(this);
        }
        m12593a();
        getAsyncTaskQueue().m13740a(new AsyncTask<Object, Object, Boolean>(this) {
            /* renamed from: c */
            final /* synthetic */ BaseAccountActivity f11880c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m12588a(objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m12589a((Boolean) obj);
            }

            /* renamed from: a */
            protected Boolean m12588a(Object... objArr) {
                try {
                    return Boolean.valueOf(this.f11880c.f11882a.m8472c(str, str2));
                } catch (BusinessException e) {
                    e.printStackTrace();
                    return Boolean.valueOf(false);
                }
            }

            /* renamed from: a */
            protected void m12589a(Boolean bool) {
                super.onPostExecute(bool);
                this.f11880c.f11882a = null;
                this.f11880c.f11883b = null;
                this.f11880c.m12605c();
                this.f11880c.m12604b(bool.booleanValue());
            }
        }, new Object[0]);
    }

    /* renamed from: d */
    protected void m12606d() {
        if (this.f11883b == null) {
            this.f11883b = new C2389c((Activity) this);
        }
        getAsyncTaskQueue().m13740a(new C25057(this), new Object[0]);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
    }

    /* renamed from: a */
    protected void m12600a(boolean z, String str, String str2) {
    }

    /* renamed from: a */
    protected void m12598a(List<AccountDTO> list) {
    }

    /* renamed from: b */
    protected void m12603b(List<AccountDTO> list) {
    }

    /* renamed from: b */
    protected void m12604b(boolean z) {
    }

    /* renamed from: a */
    protected void m12599a(boolean z, String str, int i) {
    }
}
