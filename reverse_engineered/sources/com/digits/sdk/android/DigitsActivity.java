package com.digits.sdk.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.avos.avoscloud.AVException;

public abstract class DigitsActivity extends Activity {
    /* renamed from: a */
    ab f13071a;

    /* renamed from: a */
    abstract ab mo3630a();

    public void onCreate(Bundle bundle) {
        setTheme(aa.a().f());
        super.onCreate(bundle);
        this.f13071a = mo3630a();
        Bundle extras = getIntent().getExtras();
        if (this.f13071a.mo3657a(extras)) {
            setContentView(this.f13071a.mo3659c());
            this.f13071a.mo3649a(this, extras);
            return;
        }
        finish();
        throw new IllegalAccessError("This activity can only be started from Digits");
    }

    public void onResume() {
        super.onResume();
        this.f13071a.mo3658b();
    }

    public void onDestroy() {
        this.f13071a.mo3635a();
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.f13071a.mo3636a(i, i2, this);
        if (i2 == 200 && i == AVException.EXCEEDED_QUOTA) {
            finish();
        }
    }
}
