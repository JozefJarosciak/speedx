package com.beastbikes.android.modules.cycling.activity.ui;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903350)
public class EmptyActivity extends Activity {
    /* renamed from: a */
    private EmptyActivity$a f4683a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        window.setGravity(8388659);
        LayoutParams attributes = window.getAttributes();
        attributes.width = 1;
        attributes.height = 1;
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        this.f4683a = new EmptyActivity$a(this);
        IntentFilter intentFilter = new IntentFilter("com.beastbikes.intent.action.ACTIVITY_COMPLETE");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        registerReceiver(this.f4683a, intentFilter);
    }

    public void finish() {
        super.finish();
    }

    protected void onDestroy() {
        unregisterReceiver(this.f4683a);
        super.onDestroy();
    }
}
