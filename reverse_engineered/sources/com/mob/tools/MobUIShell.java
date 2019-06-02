package com.mob.tools;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.mob.tools.utils.ReflectHelper;
import java.util.HashMap;
import org.apache.commons.cli.HelpFormatter;

public class MobUIShell extends Activity {
    private static HashMap<String, FakeActivity> executors = new HashMap();
    public static int forceTheme;
    private FakeActivity executor;

    static {
        MobLog.getInstance().m16933d("===============================", new Object[0]);
        MobLog.getInstance().m16933d("MobTools " + "2016-11-07".replace("-0", HelpFormatter.DEFAULT_OPT_PREFIX).replace(HelpFormatter.DEFAULT_OPT_PREFIX, "."), new Object[0]);
        MobLog.getInstance().m16933d("===============================", new Object[0]);
    }

    protected static String registerExecutor(Object obj) {
        return registerExecutor(String.valueOf(System.currentTimeMillis()), obj);
    }

    protected static String registerExecutor(String str, Object obj) {
        executors.put(str, (FakeActivity) obj);
        return str;
    }

    public void setTheme(int i) {
        if (forceTheme > 0) {
            super.setTheme(forceTheme);
        } else {
            super.setTheme(i);
        }
    }

    protected void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        String str = "";
        str = "";
        try {
            String stringExtra = intent.getStringExtra("launch_time");
            String stringExtra2 = intent.getStringExtra("executor_name");
            this.executor = (FakeActivity) executors.remove(stringExtra);
            if (this.executor == null) {
                this.executor = (FakeActivity) executors.remove(intent.getScheme());
                if (this.executor == null) {
                    this.executor = getDefault();
                    if (this.executor == null) {
                        MobLog.getInstance().m16946w(new RuntimeException("Executor lost! launchTime = " + stringExtra + ", executorName: " + stringExtra2));
                        super.onCreate(bundle);
                        finish();
                        return;
                    }
                }
            }
            MobLog.getInstance().m16939i("MobUIShell found executor: " + this.executor.getClass(), new Object[0]);
            this.executor.setActivity(this);
            super.onCreate(bundle);
            MobLog.getInstance().m16933d(this.executor.getClass().getSimpleName() + " onCreate", new Object[0]);
            this.executor.onCreate();
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
            super.onCreate(bundle);
            finish();
        }
    }

    public FakeActivity getDefault() {
        try {
            String string = getPackageManager().getActivityInfo(getComponentName(), 128).metaData.getString("defaultActivity");
            if (!TextUtils.isEmpty(string)) {
                if (string.startsWith(".")) {
                    string = getPackageName() + string;
                }
                Object importClass = ReflectHelper.importClass(string);
                if (!TextUtils.isEmpty(importClass)) {
                    importClass = ReflectHelper.newInstance(importClass, new Object[0]);
                    if (importClass != null && (importClass instanceof FakeActivity)) {
                        return (FakeActivity) importClass;
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
        }
        return null;
    }

    public void setContentView(int i) {
        setContentView(LayoutInflater.from(this).inflate(i, null));
    }

    public void setContentView(View view) {
        if (view != null) {
            super.setContentView(view);
            if (this.executor != null) {
                this.executor.setContentView(view);
            }
        }
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        if (view != null) {
            if (layoutParams == null) {
                super.setContentView(view);
            } else {
                super.setContentView(view, layoutParams);
            }
            if (this.executor != null) {
                this.executor.setContentView(view);
            }
        }
    }

    protected void onNewIntent(Intent intent) {
        if (this.executor == null) {
            super.onNewIntent(intent);
        } else {
            this.executor.onNewIntent(intent);
        }
    }

    protected void onStart() {
        if (this.executor != null) {
            MobLog.getInstance().m16933d(this.executor.getClass().getSimpleName() + " onStart", new Object[0]);
            this.executor.onStart();
        }
        super.onStart();
    }

    protected void onResume() {
        if (this.executor != null) {
            MobLog.getInstance().m16933d(this.executor.getClass().getSimpleName() + " onResume", new Object[0]);
            this.executor.onResume();
        }
        super.onResume();
    }

    protected void onPause() {
        if (this.executor != null) {
            MobLog.getInstance().m16933d(this.executor.getClass().getSimpleName() + " onPause", new Object[0]);
            this.executor.onPause();
        }
        super.onPause();
    }

    protected void onStop() {
        if (this.executor != null) {
            MobLog.getInstance().m16933d(this.executor.getClass().getSimpleName() + " onStop", new Object[0]);
            this.executor.onStop();
        }
        super.onStop();
    }

    protected void onRestart() {
        if (this.executor != null) {
            MobLog.getInstance().m16933d(this.executor.getClass().getSimpleName() + " onRestart", new Object[0]);
            this.executor.onRestart();
        }
        super.onRestart();
    }

    protected void onDestroy() {
        if (this.executor != null) {
            this.executor.sendResult();
            MobLog.getInstance().m16933d(this.executor.getClass().getSimpleName() + " onDestroy", new Object[0]);
            this.executor.onDestroy();
        }
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (this.executor != null) {
            this.executor.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = false;
        if (this.executor != null) {
            z = this.executor.onKeyEvent(i, keyEvent);
        }
        return z ? true : super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        boolean z = false;
        if (this.executor != null) {
            z = this.executor.onKeyEvent(i, keyEvent);
        }
        return z ? true : super.onKeyUp(i, keyEvent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.executor != null) {
            this.executor.onConfigurationChanged(configuration);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.executor != null) {
            this.executor.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public void finish() {
        if (this.executor == null || !this.executor.onFinish()) {
            super.finish();
        }
    }

    public Object getExecutor() {
        return this.executor;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        boolean onCreateOptionsMenu = super.onCreateOptionsMenu(menu);
        if (this.executor != null) {
            return this.executor.onCreateOptionsMenu(menu);
        }
        return onCreateOptionsMenu;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
        if (this.executor != null) {
            return this.executor.onOptionsItemSelected(menuItem);
        }
        return onOptionsItemSelected;
    }
}
