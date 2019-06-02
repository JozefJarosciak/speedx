package com.beastbikes.android.modules.user.ui.binding;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.widget.C2621c;

class BindMailPhoneSuccessActivity$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2621c f11887a;
    /* renamed from: b */
    final /* synthetic */ BindMailPhoneSuccessActivity f11888b;

    BindMailPhoneSuccessActivity$1(BindMailPhoneSuccessActivity bindMailPhoneSuccessActivity, C2621c c2621c) {
        this.f11888b = bindMailPhoneSuccessActivity;
        this.f11887a = c2621c;
    }

    public void onClick(View view) {
        this.f11887a.m13069b();
        Intent intent = new Intent(this.f11888b, ValidMailPhoneActivity.class);
        intent.putExtra(ValidMailPhoneActivity.f6780d, true);
        intent.putExtra("account_dto", this.f11888b.c);
        intent.putExtra("is_mail", BindMailPhoneSuccessActivity.a(this.f11888b));
        this.f11888b.startActivityForResult(intent, 16);
    }
}
