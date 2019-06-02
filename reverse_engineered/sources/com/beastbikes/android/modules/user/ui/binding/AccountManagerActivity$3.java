package com.beastbikes.android.modules.user.ui.binding;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.dto.AccountDTO;

class AccountManagerActivity$3 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ AccountDTO f11851a;
    /* renamed from: b */
    final /* synthetic */ AccountManagerActivity f11852b;

    AccountManagerActivity$3(AccountManagerActivity accountManagerActivity, AccountDTO accountDTO) {
        this.f11852b = accountManagerActivity;
        this.f11851a = accountDTO;
    }

    public void onClick(View view) {
        AccountManagerActivity.d(this.f11852b).dismiss();
        if (view.getId() == C1373R.id.popup_window_btn_ok) {
            AccountManagerActivity.a(this.f11852b, this.f11851a);
        }
    }
}
