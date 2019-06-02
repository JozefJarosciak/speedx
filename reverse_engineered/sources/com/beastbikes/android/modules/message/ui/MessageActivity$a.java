package com.beastbikes.android.modules.message.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.framework.android.p138f.p139a.C2311a;
import com.beastbikes.framework.ui.android.WebActivity;

final class MessageActivity$a extends C2311a {
    /* renamed from: a */
    final /* synthetic */ MessageActivity f10921a;

    public MessageActivity$a(MessageActivity messageActivity, String str) {
        this.f10921a = messageActivity;
        super(str);
    }

    public void onClick(View view) {
        Context context = view.getContext();
        Uri parse = Uri.parse(m11809a());
        Intent intent = new Intent(this.f10921a.getApplicationContext(), BrowserActivity.class);
        intent.setData(parse);
        intent.addFlags(268435456);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setPackage(this.f10921a.getPackageName());
        intent.putExtra(WebActivity.EXTRA_ENTER_ANIMATION, C1373R.anim.activity_in_from_right);
        intent.putExtra(WebActivity.EXTRA_EXIT_ANIMATION, C1373R.anim.activity_out_to_right);
        intent.putExtra(WebActivity.EXTRA_NONE_ANIMATION, C1373R.anim.activity_none);
        context.startActivity(intent);
    }
}
