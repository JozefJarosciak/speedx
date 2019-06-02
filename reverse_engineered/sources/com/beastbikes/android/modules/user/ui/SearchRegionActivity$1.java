package com.beastbikes.android.modules.user.ui;

import android.text.Editable;
import android.text.TextWatcher;

class SearchRegionActivity$1 implements TextWatcher {
    /* renamed from: a */
    final /* synthetic */ SearchRegionActivity f11771a;

    SearchRegionActivity$1(SearchRegionActivity searchRegionActivity) {
        this.f11771a = searchRegionActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        SearchRegionActivity.a(this.f11771a).m12208a(charSequence.toString(), this.f11771a.j());
    }

    public void afterTextChanged(Editable editable) {
    }
}
