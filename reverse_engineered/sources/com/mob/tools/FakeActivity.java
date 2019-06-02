package com.mob.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.UIHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class FakeActivity {
    private static Class<? extends Activity> shellClass;
    protected Activity activity;
    private View contentView;
    private HashMap<String, Object> result;
    private FakeActivity resultReceiver;

    public static void setShell(Class<? extends Activity> cls) {
        shellClass = cls;
    }

    public static void registerExecutor(String str, Object obj) {
        if (shellClass != null) {
            try {
                Method method = shellClass.getMethod("registerExecutor", new Class[]{String.class, Object.class});
                method.setAccessible(true);
                method.invoke(null, new Object[]{str, obj});
                return;
            } catch (Throwable th) {
                MobLog.getInstance().m16946w(th);
                return;
            }
        }
        MobUIShell.registerExecutor(str, obj);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setContentViewLayoutResName(String str) {
        if (this.activity != null) {
            int layoutRes = C4275R.getLayoutRes(this.activity, str);
            if (layoutRes > 0) {
                this.activity.setContentView(layoutRes);
            }
        }
    }

    public void setContentView(View view) {
        this.contentView = view;
    }

    public View getContentView() {
        return this.contentView;
    }

    public <T extends View> T findViewById(int i) {
        if (this.activity == null) {
            return null;
        }
        return this.activity.findViewById(i);
    }

    public <T extends View> T findViewByResName(View view, String str) {
        if (this.activity == null) {
            return null;
        }
        int idRes = C4275R.getIdRes(this.activity, str);
        if (idRes > 0) {
            return view.findViewById(idRes);
        }
        return null;
    }

    public <T extends View> T findViewByResName(String str) {
        if (this.activity == null) {
            return null;
        }
        int idRes = C4275R.getIdRes(this.activity, str);
        if (idRes > 0) {
            return findViewById(idRes);
        }
        return null;
    }

    public void onCreate() {
    }

    public void onNewIntent(Intent intent) {
    }

    public void onStart() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStop() {
    }

    public void onRestart() {
    }

    public boolean onFinish() {
        return false;
    }

    public void onDestroy() {
    }

    public final void finish() {
        if (this.activity != null) {
            this.activity.finish();
        }
    }

    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void startActivity(Intent intent) {
        startActivityForResult(intent, -1);
    }

    public void startActivityForResult(Intent intent, int i) {
        if (this.activity != null) {
            this.activity.startActivityForResult(intent, i);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    public Context getContext() {
        return this.activity;
    }

    public void show(Context context, Intent intent) {
        showForResult(context, intent, null);
    }

    public void show(Context context, Intent intent, boolean z) {
        showForResult(context, intent, null, z);
    }

    public void showForResult(Context context, Intent intent, FakeActivity fakeActivity) {
        showForResult(context, intent, fakeActivity, false);
    }

    public void showForResult(Context context, Intent intent, FakeActivity fakeActivity, final boolean z) {
        String str;
        Intent intent2;
        this.resultReceiver = fakeActivity;
        Message message = new Message();
        if (shellClass != null) {
            Intent intent3 = new Intent(context, shellClass);
            try {
                Method method = shellClass.getMethod("registerExecutor", new Class[]{Object.class});
                method.setAccessible(true);
                str = (String) method.invoke(null, new Object[]{this});
                intent2 = intent3;
            } catch (Throwable th) {
                MobLog.getInstance().m16946w(th);
                str = null;
                intent2 = intent3;
            }
        } else {
            intent2 = new Intent(context, MobUIShell.class);
            str = MobUIShell.registerExecutor(this);
        }
        intent2.putExtra("launch_time", str);
        intent2.putExtra("executor_name", getClass().getName());
        if (intent != null) {
            intent2.putExtras(intent);
        }
        message.obj = new Object[]{context, intent2};
        UIHandler.sendMessage(message, new Callback() {
            public boolean handleMessage(Message message) {
                Object[] objArr = (Object[]) message.obj;
                Context context = (Context) objArr[0];
                Intent intent = (Intent) objArr[1];
                if (z) {
                    intent.addFlags(268435456);
                    intent.addFlags(134217728);
                } else if (!(context instanceof Activity)) {
                    intent.addFlags(268435456);
                }
                context.startActivity(intent);
                return false;
            }
        });
    }

    public FakeActivity getParent() {
        return this.resultReceiver;
    }

    public final void setResult(HashMap<String, Object> hashMap) {
        this.result = hashMap;
    }

    public void sendResult() {
        if (this.resultReceiver != null) {
            this.resultReceiver.onResult(this.result);
        }
    }

    public void onResult(HashMap<String, Object> hashMap) {
    }

    public void runOnUIThread(final Runnable runnable) {
        UIHandler.sendEmptyMessage(0, new Callback() {
            public boolean handleMessage(Message message) {
                runnable.run();
                return false;
            }
        });
    }

    public void runOnUIThread(final Runnable runnable, long j) {
        UIHandler.sendEmptyMessageDelayed(0, j, new Callback() {
            public boolean handleMessage(Message message) {
                runnable.run();
                return false;
            }
        });
    }

    public void setRequestedOrientation(int i) {
        if (this.activity != null) {
            this.activity.setRequestedOrientation(i);
        }
    }

    public void requestPortraitOrientation() {
        setRequestedOrientation(1);
    }

    public void requestLandscapeOrientation() {
        setRequestedOrientation(0);
    }

    public int getOrientation() {
        return this.activity.getResources().getConfiguration().orientation;
    }

    public void requestFullScreen(boolean z) {
        if (this.activity != null) {
            if (z) {
                this.activity.getWindow().addFlags(1024);
                this.activity.getWindow().clearFlags(2048);
            } else {
                this.activity.getWindow().addFlags(2048);
                this.activity.getWindow().clearFlags(1024);
            }
            this.activity.getWindow().getDecorView().requestLayout();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    public void requestPermissions(String[] strArr, int i) {
        if (this.activity != null && VERSION.SDK_INT >= 23) {
            try {
                ReflectHelper.invokeInstanceMethod(this.activity, "requestPermissions", strArr, Integer.valueOf(i));
            } catch (Throwable th) {
                MobLog.getInstance().m16934d(th);
            }
        }
    }
}
