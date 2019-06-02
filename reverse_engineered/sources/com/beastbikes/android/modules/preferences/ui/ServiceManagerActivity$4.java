package com.beastbikes.android.modules.preferences.ui;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.utils.p080a.C2549c;

class ServiceManagerActivity$4 extends AsyncTask<Void, Void, AccountDTO> {
    /* renamed from: a */
    final /* synthetic */ ServiceManagerActivity f10956a;

    ServiceManagerActivity$4(ServiceManagerActivity serviceManagerActivity) {
        this.f10956a = serviceManagerActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11827a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11828a((AccountDTO) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        if (ServiceManagerActivity.b(this.f10956a) == null) {
            ServiceManagerActivity.a(this.f10956a, new C1802i(this.f10956a, "", true));
        }
        ServiceManagerActivity.b(this.f10956a).show();
    }

    /* renamed from: a */
    protected AccountDTO m11827a(Void... voidArr) {
        return ServiceManagerActivity.a(this.f10956a, ServiceManagerActivity.c(this.f10956a).m8471c());
    }

    /* renamed from: a */
    protected void m11828a(AccountDTO accountDTO) {
        if (ServiceManagerActivity.b(this.f10956a) != null && ServiceManagerActivity.b(this.f10956a).isShowing()) {
            ServiceManagerActivity.b(this.f10956a).dismiss();
        }
        if (accountDTO != null && accountDTO.getStatus() == 1) {
            if (!TextUtils.isEmpty(accountDTO.getAccessToken())) {
                C2549c.m12753a().m12755a(this.f10956a, "com.beastbikes.starva_auth_key", accountDTO.getAuthKey()).commit();
                C2549c.m12753a().m12755a(this.f10956a, "com.beastbikes.starva_token", accountDTO.getAccessToken()).commit();
            }
            ServiceManagerActivity.a(this.f10956a, accountDTO.getAuthKey(), accountDTO.getAccessToken());
        }
    }
}
