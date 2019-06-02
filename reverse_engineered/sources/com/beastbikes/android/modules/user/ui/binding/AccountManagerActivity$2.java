package com.beastbikes.android.modules.user.ui.binding;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.List;

class AccountManagerActivity$2 extends AsyncTask<Void, Void, List<AccountDTO>> {
    /* renamed from: a */
    final /* synthetic */ AccountManagerActivity f11850a;

    AccountManagerActivity$2(AccountManagerActivity accountManagerActivity) {
        this.f11850a = accountManagerActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12576a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12577a((List) obj);
    }

    /* renamed from: a */
    protected List<AccountDTO> m12576a(Void... voidArr) {
        return AccountManagerActivity.a(this.f11850a).m8471c();
    }

    /* renamed from: a */
    protected void m12577a(List<AccountDTO> list) {
        AccountManagerActivity.a(this.f11850a, list);
        if (AccountManagerActivity.b(this.f11850a).getStatus() != 1 && AccountManagerActivity.c(this.f11850a).getStatus() != 1) {
            Toasts.show(this.f11850a, (int) C1373R.string.str_account_manage_bind_email_phone_for_account_safety, 0);
        }
    }
}
