package com.digits.sdk.android;

import android.app.Activity;
import android.os.Bundle;

public class ContactsActivity extends Activity {
    /* renamed from: a */
    C2928o f13073a;

    public void onCreate(Bundle bundle) {
        setTheme(getIntent().getIntExtra("THEME_RESOURCE_ID", C2876R.style.Digits_default));
        super.onCreate(bundle);
        this.f13073a = new C2928o(this);
        this.f13073a.m14238a();
    }
}
