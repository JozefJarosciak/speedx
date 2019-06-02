package com.digits.sdk.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.avos.avoscloud.AVException;

public abstract class DigitsActionBarActivity extends ActionBarActivity {
    /* renamed from: a */
    ab f13070a;

    /* renamed from: a */
    abstract ab mo3629a();

    public void onCreate(Bundle bundle) {
        setTheme(aa.a().f());
        super.onCreate(bundle);
        this.f13070a = mo3629a();
        Bundle extras = getIntent().getExtras();
        if (this.f13070a.mo3657a(extras)) {
            setContentView(this.f13070a.mo3659c());
            this.f13070a.mo3649a(this, extras);
            return;
        }
        finish();
        throw new IllegalAccessError("This activity can only be started from Digits");
    }

    public void onResume() {
        super.onResume();
        this.f13070a.mo3658b();
    }

    public void onDestroy() {
        this.f13070a.mo3635a();
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.f13070a.mo3636a(i, i2, this);
        if (i2 == 200 && i == AVException.EXCEEDED_QUOTA) {
            finish();
        }
    }
}
