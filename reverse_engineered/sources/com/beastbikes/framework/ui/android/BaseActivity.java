package com.beastbikes.framework.ui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.beastbikes.framework.android.p056e.C2794a;
import com.beastbikes.framework.android.p056e.C2795b;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.utils.ViewIntrospector;
import com.umeng.analytics.MobclickAgent;

public abstract class BaseActivity extends Activity {
    private C2794a asyncTaskQueue;

    public C2794a getAsyncTaskQueue() {
        return this.asyncTaskQueue;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.asyncTaskQueue = C2795b.m13743a(this);
        C1459b c1459b = (C1459b) getClass().getAnnotation(C1459b.class);
        if (c1459b != null) {
            super.setContentView(c1459b.a());
        }
        ViewIntrospector.introspect((Activity) this, (Object) this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        C1460c c1460c = (C1460c) getClass().getAnnotation(C1460c.class);
        if (c1460c != null) {
            getMenuInflater().inflate(c1460c.a(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    protected void onStop() {
        this.asyncTaskQueue.m13742a((Object) this);
        super.onStop();
    }

    protected void onDestroy() {
        this.asyncTaskQueue.m13742a((Object) this);
        this.asyncTaskQueue.m13741a();
        super.onDestroy();
    }

    protected void onResume() {
        MobclickAgent.b(this);
        super.onResume();
    }

    protected void onPause() {
        MobclickAgent.a(this);
        super.onPause();
    }
}
