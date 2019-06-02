package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ClubActInfoEditorActivity$5 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ClubActInfoEditorActivity f9424a;

    ClubActInfoEditorActivity$5(ClubActInfoEditorActivity clubActInfoEditorActivity) {
        this.f9424a = clubActInfoEditorActivity;
    }

    public void onClick(View view) {
        if (ClubActInfoEditorActivity.e(this.f9424a)) {
            Intent intent = new Intent();
            intent.putExtra(ClubActInfoEditorActivity.f4811b, ClubActInfoEditorActivity.f(this.f9424a).getHtml());
            this.f9424a.setResult(-1, intent);
            this.f9424a.a();
            this.f9424a.finish();
            return;
        }
        Toasts.showOnUiThread(this.f9424a, this.f9424a.getString(C1373R.string.club_act_info_text_iamge_count));
    }
}
