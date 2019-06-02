package com.beastbikes.android.modules.cycling.sections.ui;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.beastbikes.android.C1373R;

class CompetitionSectionActivity$6 implements TextWatcher {
    /* renamed from: a */
    final /* synthetic */ CompetitionSectionActivity f10569a;

    CompetitionSectionActivity$6(CompetitionSectionActivity competitionSectionActivity) {
        this.f10569a = competitionSectionActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        Drawable drawable = this.f10569a.getResources().getDrawable(C1373R.drawable.ic_section_search);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if (TextUtils.isEmpty(CompetitionSectionActivity.a(this.f10569a).getText().toString())) {
            CompetitionSectionActivity.a(this.f10569a).setCompoundDrawables(drawable, null, null, null);
            return;
        }
        Drawable drawable2 = this.f10569a.getResources().getDrawable(C1373R.drawable.ic_section_search_clear);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        CompetitionSectionActivity.a(this.f10569a).setCompoundDrawables(drawable, null, drawable2, null);
    }
}
