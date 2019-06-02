package com.beastbikes.android.modules.cycling.club.ui;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.C2169c;
import com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.RichEditor.C2072d;
import com.beastbikes.framework.ui.android.utils.Toasts;
import org.apache.http.protocol.HttpRequestExecutor;

class ClubActInfoEditorActivity$4 implements C2072d {
    /* renamed from: a */
    final /* synthetic */ ClubActInfoEditorActivity f9423a;

    ClubActInfoEditorActivity$4(ClubActInfoEditorActivity clubActInfoEditorActivity) {
        this.f9423a = clubActInfoEditorActivity;
    }

    /* renamed from: a */
    public void mo3370a(String str) {
        int i;
        ClubActInfoEditorActivity.a(this.f9423a, str);
        Object a = C2169c.m11126a(str);
        if (TextUtils.isEmpty(a)) {
            i = 0;
        } else {
            i = a.length();
        }
        ClubActInfoEditorActivity.a(this.f9423a).setText(i + "/3000");
        if (i > HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE) {
            Toasts.showOnUiThread(this.f9423a, this.f9423a.getString(C1373R.string.club_act_info_text_count));
            ClubActInfoEditorActivity.a(this.f9423a).setTextColor(SupportMenu.CATEGORY_MASK);
            ClubActInfoEditorActivity.a(this.f9423a, false);
        } else {
            ClubActInfoEditorActivity.a(this.f9423a).setTextColor(ViewCompat.MEASURED_STATE_MASK);
            ClubActInfoEditorActivity.a(this.f9423a, true);
        }
        ClubActInfoEditorActivity.a(this.f9423a, C2169c.m11128c(ClubActInfoEditorActivity.c(this.f9423a)));
        if (ClubActInfoEditorActivity.d(this.f9423a) > 3) {
            Toasts.showOnUiThread(this.f9423a, this.f9423a.getString(C1373R.string.club_act_info_images));
            ClubActInfoEditorActivity.a(this.f9423a, false);
        }
    }
}
