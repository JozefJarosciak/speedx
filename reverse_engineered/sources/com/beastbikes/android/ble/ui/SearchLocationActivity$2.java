package com.beastbikes.android.ble.ui;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

class SearchLocationActivity$2 implements TextWatcher {
    /* renamed from: a */
    final /* synthetic */ SearchLocationActivity f7652a;

    SearchLocationActivity$2(SearchLocationActivity searchLocationActivity) {
        this.f7652a = searchLocationActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Object charSequence2 = charSequence.toString();
        if (TextUtils.isEmpty(charSequence2)) {
            SearchLocationActivity.a(this.f7652a).clear();
            SearchLocationActivity.b(this.f7652a).notifyDataSetChanged();
            return;
        }
        SearchLocationActivity.a(this.f7652a, charSequence2);
    }

    public void afterTextChanged(Editable editable) {
    }
}
