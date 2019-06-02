package com.beastbikes.android.authentication.inputpassword;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.authentication.C1530e;
import com.beastbikes.android.authentication.C1542d;
import com.beastbikes.framework.business.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: InputPasswordPresenter */
/* renamed from: com.beastbikes.android.authentication.inputpassword.a */
public class C1550a extends C1542d {
    /* renamed from: d */
    private static final Logger f7262d = LoggerFactory.getLogger("InputPasswordPresenter");

    public C1550a(C1530e c1530e) {
        super(c1530e);
    }

    /* renamed from: c */
    public void mo3124c() {
        String h = this.a.mo3114h();
        String e = this.a.mo3111e();
        final int i = this.a.mo3115i();
        final String g = this.a.mo3113g();
        final String f = this.a.mo3112f();
        if (!TextUtils.isEmpty(g) && g.length() <= 6) {
            if (i == 2) {
                e = h + e;
            }
            this.b.getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, Boolean>(this) {
                /* renamed from: e */
                final /* synthetic */ C1550a f7261e;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m8502a((Void[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m8503a((Boolean) obj);
                }

                /* renamed from: a */
                protected Boolean m8502a(Void... voidArr) {
                    try {
                        return Boolean.valueOf(this.f7261e.c.m8470b(e, g, i, f));
                    } catch (BusinessException e) {
                        e.printStackTrace();
                        C1550a.f7262d.error("resetPwd error e: " + e);
                        return Boolean.valueOf(false);
                    }
                }

                /* renamed from: a */
                protected void m8503a(Boolean bool) {
                    if (bool.booleanValue()) {
                        this.f7261e.a.mo3117k();
                    }
                }
            }, new Void[0]);
        }
    }
}
