package com.beastbikes.framework.ui.android;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.beastbikes.framework.android.p056e.C1376d;
import com.beastbikes.framework.android.p056e.C2794a;
import com.beastbikes.framework.android.p056e.C2795b;
import com.beastbikes.framework.android.p056e.C2796c;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.utils.ViewIntrospector;
import java.util.List;

public abstract class BaseFragmentActivity extends AppCompatActivity implements C1376d {
    private C2794a asyncTaskQueue;
    private C2796c requestQueueFactory;

    public C2794a getAsyncTaskQueue() {
        return this.asyncTaskQueue;
    }

    public C2796c getRequestQueueFactory() {
        return this.requestQueueFactory;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.asyncTaskQueue = C2795b.a(this);
        this.requestQueueFactory = C2796c.a(this);
        C1459b c1459b = (C1459b) getClass().getAnnotation(C1459b.class);
        if (c1459b != null) {
            super.setContentView(c1459b.m8051a());
        }
        ViewIntrospector.introspect(this, this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        C1460c c1460c = (C1460c) getClass().getAnnotation(C1460c.class);
        if (c1460c != null) {
            getMenuInflater().inflate(c1460c.m8052a(), menu);
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
        super.onStop();
    }

    protected void onDestroy() {
        this.asyncTaskQueue.a(this);
        this.asyncTaskQueue.a();
        this.requestQueueFactory.a(this);
        super.onDestroy();
    }

    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService("activity");
        String packageName = getApplicationContext().getPackageName();
        List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(packageName) && runningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }
}
