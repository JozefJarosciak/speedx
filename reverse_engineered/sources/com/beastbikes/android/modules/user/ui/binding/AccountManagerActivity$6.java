package com.beastbikes.android.modules.user.ui.binding;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.modules.user.dto.AccountDTO;

class AccountManagerActivity$6 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ AccountDTO f11855a;
    /* renamed from: b */
    final /* synthetic */ AccountManagerActivity f11856b;

    AccountManagerActivity$6(AccountManagerActivity accountManagerActivity, AccountDTO accountDTO) {
        this.f11856b = accountManagerActivity;
        this.f11855a = accountDTO;
    }

    public void onClick(View view) {
        this.f11856b.a(null, this.f11855a.getAuthKey(), this.f11855a.getAuthType());
        AccountManagerActivity.f(this.f11856b).m13069b();
    }
}
