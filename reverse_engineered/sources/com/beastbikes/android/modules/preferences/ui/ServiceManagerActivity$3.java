package com.beastbikes.android.modules.preferences.ui;

import android.os.AsyncTask;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.utils.p080a.C2549c;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class ServiceManagerActivity$3 extends AsyncTask<Void, Void, AccountDTO> {
    /* renamed from: a */
    final /* synthetic */ String f10953a;
    /* renamed from: b */
    final /* synthetic */ String f10954b;
    /* renamed from: c */
    final /* synthetic */ ServiceManagerActivity f10955c;

    ServiceManagerActivity$3(ServiceManagerActivity serviceManagerActivity, String str, String str2) {
        this.f10955c = serviceManagerActivity;
        this.f10953a = str;
        this.f10954b = str2;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11825a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11826a((AccountDTO) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        if (ServiceManagerActivity.b(this.f10955c) == null) {
            ServiceManagerActivity.a(this.f10955c, new C1802i(this.f10955c, "", true));
        }
        ServiceManagerActivity.b(this.f10955c).show();
    }

    /* renamed from: a */
    protected AccountDTO m11825a(Void... voidArr) {
        List list = null;
        try {
            list = ServiceManagerActivity.c(this.f10955c).m8458a(this.f10953a, this.f10954b, 256, null);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return ServiceManagerActivity.a(this.f10955c, list);
    }

    /* renamed from: a */
    protected void m11826a(AccountDTO accountDTO) {
        super.onPostExecute(accountDTO);
        if (ServiceManagerActivity.b(this.f10955c) != null && ServiceManagerActivity.b(this.f10955c).isShowing()) {
            ServiceManagerActivity.b(this.f10955c).dismiss();
        }
        C2549c.m12753a().m12754a(this.f10955c, "com.beastbikes.starva_auth_key").commit();
        C2549c.m12753a().m12754a(this.f10955c, "com.beastbikes.starva_token").commit();
        ServiceManagerActivity.a(this.f10955c, null, null);
    }
}
