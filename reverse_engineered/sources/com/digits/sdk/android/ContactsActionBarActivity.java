package com.digits.sdk.android;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class ContactsActionBarActivity extends ActionBarActivity {
    /* renamed from: a */
    C2928o f13072a;

    public void onCreate(Bundle bundle) {
        setTheme(getIntent().getIntExtra("THEME_RESOURCE_ID", C2876R.style.Theme_AppCompat_Light));
        super.onCreate(bundle);
        this.f13072a = new C2928o(this);
        this.f13072a.m14238a();
    }
}
