package cn.jpush.android.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import cn.jpush.android.api.C0417m;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.data.C0441m;
import cn.jpush.android.helpers.C0456f;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.util.ac;
import java.io.File;

public class PushActivity extends Activity {
    /* renamed from: z */
    private static final String[] f910z;
    /* renamed from: a */
    private boolean f911a = false;
    /* renamed from: b */
    private String f912b;
    /* renamed from: c */
    private FullScreenView f913c = null;
    /* renamed from: d */
    private Handler f914d = new C0487f(this);

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 20;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u001cb\u000bhd).\u001bzrlj\u000bov9b\u001a)t#j\u000b)~\".\u0004yb?f1~r.x\u0007l`\u0013b\u000fpx9z@qz /";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x00fa;
            case 1: goto L_0x00fe;
            case 2: goto L_0x0102;
            case 3: goto L_0x0106;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 23;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u001c{\u001daV/z\u0007~8w";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "*g\u0002l-c!";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = " o\u0017fb8";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "-m\u001a`x\"l\u000f{[-w\u0001|c\u0005j";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "&~\u001bz\u0013y\u000bka%k\u0019V{-w\u0001|c";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0002{\u0002e7!k\u001dzv+kNly8g\u001ap6lM\u0002fd).>|d$O\r}~:g\u001ap6";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u001cb\u000bhd).\u000fmslb\u000fpx9zN{r?a\u001b{t).\u0004yb?f1~r.x\u0007l`\u0013b\u000fpx9z@qz .\u001af7>k\u001d&{-w\u0001|cl/";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "%j";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\tv\u001a{vlj\u000f}vlg\u001d)y#zNzr>g\u000fe~6o\ferm";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "*|\u0001dH;o\u0017";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = ".a\np";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u001bo\u001cg~\"i｢gb bNdr?}\u000fnrlk\u0000}~8wO)T a\u001dl7\u001c{\u001daV/z\u0007~8wO";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u001c{\u001daV/z\u0007~8wNnr8. \\[\u0000.\u0007gc)`\u001a(";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u0005`\u0018h{%jNdd+.\u001apg).\u001af7?f\u0001~7a.";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = ".o\u001dlV/z\u0007~8wN)*l";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "8a\u001eHt8g\u0018`c5.N47";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "-m\u001a`a%z\u0017";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u000fo\u0000.cli\u000b}7 o\u001bgt$.\u0007gc)`\u001a)q#|N}%}Nyv/e\u000fnrm";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u0018f\u000b)g-m\u0005hp).\u0019`c$.\u001aarli\u0007r\".\u0000hz).\rhy\"a\u001a)u).\bfb\"jO";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        f910z = r4;
        return;
    L_0x00fa:
        r9 = 76;
        goto L_0x0020;
    L_0x00fe:
        r9 = 14;
        goto L_0x0020;
    L_0x0102:
        r9 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        goto L_0x0020;
    L_0x0106:
        r9 = 9;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.ui.PushActivity.<clinit>():void");
    }

    /* renamed from: a */
    static /* synthetic */ void m1542a(PushActivity pushActivity, C0429c c0429c) {
        ac.m1584c();
        if (c0429c == null) {
            ac.m1587d(f910z[1], f910z[6]);
            pushActivity.finish();
        }
        C0441m c0441m = (C0441m) c0429c;
        if (c0441m.f711H == 0) {
            int identifier = pushActivity.getResources().getIdentifier(f910z[5], f910z[3], pushActivity.getPackageName());
            if (identifier == 0) {
                ac.m1589e(f910z[1], f910z[7]);
                pushActivity.finish();
                return;
            }
            pushActivity.setContentView(identifier);
            String str = c0441m.f716a;
            if (C0456f.m1402a(str)) {
                String str2 = c0441m.f715L;
                if (c0441m.p) {
                    int identifier2 = pushActivity.getResources().getIdentifier(f910z[4], f910z[8], pushActivity.getPackageName());
                    if (identifier2 == 0) {
                        ac.m1589e(f910z[1], f910z[0]);
                        pushActivity.finish();
                        return;
                    }
                    pushActivity.f913c = (FullScreenView) pushActivity.findViewById(identifier2);
                    pushActivity.f913c.initModule(pushActivity, c0429c);
                    if (TextUtils.isEmpty(str2) || !new File(str2.replace(f910z[2], "")).exists() || pushActivity.f911a) {
                        pushActivity.f913c.loadUrl(str);
                    } else {
                        pushActivity.f913c.loadUrl(str2);
                    }
                }
                if (!pushActivity.f911a) {
                    C0459i.m1415a(pushActivity.f912b, 1000, pushActivity);
                    return;
                }
                return;
            }
            C0417m.m1215a((Context) pushActivity, c0429c, 0);
            pushActivity.finish();
        }
    }

    /* renamed from: c */
    private void m1543c() {
        PackageManager packageManager = getPackageManager();
        String packageName = getApplicationContext().getPackageName();
        if (packageName.isEmpty()) {
            ac.m1587d(f910z[1], f910z[19]);
            return;
        }
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage == null) {
            ac.m1587d(f910z[1], f910z[18]);
            return;
        }
        launchIntentForPackage.addFlags(335544320);
        startActivity(launchIntentForPackage);
    }

    /* renamed from: a */
    public final void m1544a() {
        if (this.f913c != null) {
            this.f913c.showTitleBar();
        }
    }

    /* renamed from: b */
    public final void m1545b() {
        try {
            ActivityManager activityManager = (ActivityManager) getSystemService(f910z[17]);
            ComponentName componentName = ((RunningTaskInfo) activityManager.getRunningTasks(1).get(0)).baseActivity;
            ComponentName componentName2 = ((RunningTaskInfo) activityManager.getRunningTasks(1).get(0)).topActivity;
            new StringBuilder(f910z[15]).append(componentName.toString());
            ac.m1581b();
            new StringBuilder(f910z[16]).append(componentName2.toString());
            ac.m1581b();
            if (!(componentName == null || componentName2 == null || !componentName2.toString().equals(componentName.toString()))) {
                m1543c();
            }
        } catch (Exception e) {
            m1543c();
        }
        finish();
    }

    public void onBackPressed() {
        if (this.f913c == null || !this.f913c.webviewCanGoBack()) {
            C0459i.m1415a(this.f912b, 1006, this);
            m1545b();
            return;
        }
        this.f913c.webviewGoBack();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() != null) {
            try {
                this.f911a = getIntent().getBooleanExtra(f910z[10], false);
                C0429c c0429c = (C0429c) getIntent().getSerializableExtra(f910z[11]);
                if (c0429c != null) {
                    this.f912b = c0429c.f613c;
                    if (c0429c != null) {
                        switch (c0429c.f625o) {
                            case 0:
                                Message message = new Message();
                                message.what = 1;
                                message.obj = c0429c;
                                this.f914d.sendMessageDelayed(message, 500);
                                return;
                            default:
                                new StringBuilder(f910z[14]).append(c0429c.f625o);
                                ac.m1586d();
                                C0417m.m1215a((Context) this, c0429c, 0);
                                finish();
                                return;
                        }
                    }
                    ac.m1587d(f910z[1], f910z[6]);
                    finish();
                    return;
                }
                ac.m1587d(f910z[1], f910z[12]);
                finish();
                return;
            } catch (Exception e) {
                ac.m1589e(f910z[1], f910z[9]);
                e.printStackTrace();
                finish();
                return;
            }
        }
        ac.m1587d(f910z[1], f910z[13]);
        finish();
    }

    protected void onDestroy() {
        if (this.f913c != null) {
            this.f913c.destory();
        }
        if (this.f914d.hasMessages(2)) {
            this.f914d.removeMessages(2);
        }
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        if (this.f913c != null) {
            this.f913c.pause();
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.f913c != null) {
            this.f913c.resume();
        }
    }
}
