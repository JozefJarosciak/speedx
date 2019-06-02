package com.beastbikes.android.modules.preferences.ui;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.utils.p080a.C2549c;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class ServiceManagerActivity$2 extends AsyncTask<Void, Void, AccountDTO> {
    /* renamed from: a */
    final /* synthetic */ String f10950a;
    /* renamed from: b */
    final /* synthetic */ String f10951b;
    /* renamed from: c */
    final /* synthetic */ ServiceManagerActivity f10952c;

    ServiceManagerActivity$2(ServiceManagerActivity serviceManagerActivity, String str, String str2) {
        this.f10952c = serviceManagerActivity;
        this.f10950a = str;
        this.f10951b = str2;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11823a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11824a((AccountDTO) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        if (ServiceManagerActivity.b(this.f10952c) == null) {
            ServiceManagerActivity.a(this.f10952c, new C1802i(this.f10952c, "", true));
        }
        ServiceManagerActivity.b(this.f10952c).show();
    }

    /* renamed from: a */
    protected AccountDTO m11823a(Void... voidArr) {
        List a;
        try {
            a = ServiceManagerActivity.c(this.f10952c).m8459a(this.f10950a, this.f10951b, "Strava", 0, null);
        } catch (BusinessException e) {
            e.printStackTrace();
            a = null;
        }
        return ServiceManagerActivity.a(this.f10952c, a);
    }

    /* renamed from: a */
    protected void m11824a(AccountDTO accountDTO) {
        super.onPostExecute(accountDTO);
        if (ServiceManagerActivity.b(this.f10952c) != null && ServiceManagerActivity.b(this.f10952c).isShowing()) {
            ServiceManagerActivity.b(this.f10952c).dismiss();
        }
        if (accountDTO == null) {
            C2549c.m12753a().m12754a(this.f10952c, "com.beastbikes.starva_auth_key").commit();
            C2549c.m12753a().m12754a(this.f10952c, "com.beastbikes.starva_token").commit();
            ServiceManagerActivity.a(this.f10952c, null, null);
            return;
        }
        ServiceManagerActivity.a().info("Bind strava return: " + accountDTO.toString());
        if (accountDTO.getStatus() != 1 || TextUtils.isEmpty(accountDTO.getAccessToken())) {
            C2549c.m12753a().m12754a(this.f10952c, "com.beastbikes.starva_auth_key").commit();
            C2549c.m12753a().m12754a(this.f10952c, "com.beastbikes.starva_token").commit();
            ServiceManagerActivity.a(this.f10952c, null, null);
            return;
        }
        C2549c.m12753a().m12755a(this.f10952c, "com.beastbikes.starva_auth_key", accountDTO.getAuthKey()).commit();
        C2549c.m12753a().m12755a(this.f10952c, "com.beastbikes.starva_token", accountDTO.getAccessToken()).commit();
        ServiceManagerActivity.a(this.f10952c, accountDTO.getAuthKey(), accountDTO.getAccessToken());
    }
}
