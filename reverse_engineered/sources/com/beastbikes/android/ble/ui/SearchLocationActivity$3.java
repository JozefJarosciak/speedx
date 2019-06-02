package com.beastbikes.android.ble.ui;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class SearchLocationActivity$3 implements OnEditorActionListener {
    /* renamed from: a */
    final /* synthetic */ SearchLocationActivity f7653a;

    SearchLocationActivity$3(SearchLocationActivity searchLocationActivity) {
        this.f7653a = searchLocationActivity;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        Object trim = SearchLocationActivity.c(this.f7653a).getText().toString().trim();
        if (i != 3 && (keyEvent == null || keyEvent.getKeyCode() != 66)) {
            return false;
        }
        if (TextUtils.isEmpty(trim)) {
            return true;
        }
        SearchLocationActivity.a(this.f7653a, trim);
        return true;
    }
}
