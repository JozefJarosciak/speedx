package com.beastbikes.android.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.ImageView;
import com.alipay.sdk.cons.C0844a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$c;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.home.HomeActivity;
import com.beastbikes.android.utils.C2574s;
import com.beastbikes.android.utils.p163b.C2552b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.BaseActivity;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903516)
public class MeiZuSettingActivity extends BaseActivity implements OnClickListener {
    /* renamed from: a */
    private static final Logger f4456a = LoggerFactory.getLogger(MeiZuSettingActivity.class);
    @C1458a(a = 2131757190)
    /* renamed from: b */
    private ImageView f4457b;
    @C1458a(a = 2131757191)
    /* renamed from: c */
    private Button f4458c;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f4458c.setOnClickListener(this);
        m5822c();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.miui_setting_go_open:
                m5821b();
                return;
            case C1373R.id.miui_setting_close:
                m5823a();
                return;
            default:
                return;
        }
    }

    public void finish() {
        super.finish();
    }

    protected void onRestart() {
        super.onRestart();
        m5823a();
    }

    /* renamed from: a */
    protected void m5823a() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            CookieManager.getInstance().setCookie(a$c.f7201b, "sessionid=" + currentUser.getSessionToken());
        }
        Intent intent = new Intent(this, HomeActivity.class);
        Intent intent2 = getIntent();
        if (intent2 != null) {
            Object stringExtra = intent2.getStringExtra("push_data");
            if (!TextUtils.isEmpty(stringExtra)) {
                intent.putExtra("push_data", stringExtra);
            }
            String stringExtra2 = intent2.getStringExtra("RONGCLOUDPUSHRONGCLOUDPUSHKEY");
            if (TextUtils.isEmpty(stringExtra2)) {
                Log.e("rongPush", "null");
            } else {
                Log.e("rongPush", stringExtra2);
                intent.putExtra("RONGCLOUDPUSHRONGCLOUDPUSHKEY", stringExtra2);
            }
            if (getIntent() != null) {
                Uri data = getIntent().getData();
                if (data != null) {
                    intent.setData(data);
                }
            }
        }
        C2574s.a().a(new C2552b(18, null));
        startActivity(intent);
        finish();
    }

    /* renamed from: b */
    private void m5821b() {
        Intent intent;
        try {
            intent = new Intent();
            intent.setClassName("com.meizu.safe", "com.meizu.safe.powerui.AppPowerManagerActivity");
            startActivity(intent);
        } catch (Exception e) {
            intent = new Intent();
            intent.setClassName("com.mediatek.batterywarning", "com.mediatek.batterywarning.BatteryWarningActivity");
            try {
                startActivity(intent);
            } catch (Exception e2) {
                intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts(OnlineConfigAgent.KEY_PACKAGE, getPackageName(), null));
                startActivity(intent);
            }
        }
        m5823a();
    }

    /* renamed from: c */
    private void m5822c() {
        InputStream inputStream;
        BufferedReader bufferedReader;
        IOException e;
        String str;
        Object obj;
        int i;
        Throwable th;
        Object obj2;
        Object obj3;
        Object obj4;
        String str2 = null;
        Process exec;
        InputStreamReader inputStreamReader;
        try {
            exec = Runtime.getRuntime().exec("getprop ro.build.display.id");
            try {
                inputStream = exec.getInputStream();
                try {
                    inputStreamReader = new InputStreamReader(inputStream);
                    try {
                        bufferedReader = new BufferedReader(inputStreamReader, 1024);
                        try {
                            str2 = bufferedReader.readLine();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    f4456a.error("Exception while closing InputStream");
                                }
                            }
                            if (inputStreamReader != null) {
                                try {
                                    inputStreamReader.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e32) {
                                    e32.printStackTrace();
                                }
                            }
                            if (exec != null) {
                                exec.destroy();
                            }
                        } catch (IOException e4) {
                            e32 = e4;
                            try {
                                e32.printStackTrace();
                                f4456a.error("Unable to read sysprop ro.miui.ui.version.code");
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e5) {
                                        f4456a.error("Exception while closing InputStream");
                                    }
                                }
                                if (inputStreamReader != null) {
                                    try {
                                        inputStreamReader.close();
                                    } catch (IOException e322) {
                                        e322.printStackTrace();
                                    }
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e3222) {
                                        e3222.printStackTrace();
                                    }
                                }
                                if (exec != null) {
                                    exec.destroy();
                                }
                                str = "Flyme OS ";
                                obj = C0844a.f2048d;
                                if (TextUtils.isEmpty(str2)) {
                                    obj = str2.substring(str.length(), str.length() + 1);
                                    if (TextUtils.isDigitsOnly(obj)) {
                                        i = 1;
                                    } else {
                                        i = Integer.parseInt(obj);
                                    }
                                    if (i >= 5) {
                                        this.f4457b.setImageResource(C1373R.drawable.bg_meizu_4);
                                    } else {
                                        this.f4457b.setImageResource(C1373R.drawable.bg_meizu_5);
                                    }
                                    f4456a.trace("Miui version name " + str2);
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e6) {
                                        f4456a.error("Exception while closing InputStream");
                                    }
                                }
                                if (inputStreamReader != null) {
                                    try {
                                        inputStreamReader.close();
                                    } catch (IOException e32222) {
                                        e32222.printStackTrace();
                                    }
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e322222) {
                                        e322222.printStackTrace();
                                    }
                                }
                                if (exec != null) {
                                    exec.destroy();
                                }
                                throw th;
                            }
                        }
                    } catch (IOException e7) {
                        e322222 = e7;
                        obj2 = str2;
                        e322222.printStackTrace();
                        f4456a.error("Unable to read sysprop ro.miui.ui.version.code");
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (exec != null) {
                            exec.destroy();
                        }
                        str = "Flyme OS ";
                        obj = C0844a.f2048d;
                        if (TextUtils.isEmpty(str2)) {
                            obj = str2.substring(str.length(), str.length() + 1);
                            if (TextUtils.isDigitsOnly(obj)) {
                                i = 1;
                            } else {
                                i = Integer.parseInt(obj);
                            }
                            if (i >= 5) {
                                this.f4457b.setImageResource(C1373R.drawable.bg_meizu_5);
                            } else {
                                this.f4457b.setImageResource(C1373R.drawable.bg_meizu_4);
                            }
                            f4456a.trace("Miui version name " + str2);
                        }
                    } catch (Throwable th3) {
                        obj2 = str2;
                        th = th3;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (exec != null) {
                            exec.destroy();
                        }
                        throw th;
                    }
                } catch (IOException e8) {
                    e322222 = e8;
                    obj3 = str2;
                    obj2 = str2;
                    e322222.printStackTrace();
                    f4456a.error("Unable to read sysprop ro.miui.ui.version.code");
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (exec != null) {
                        exec.destroy();
                    }
                    str = "Flyme OS ";
                    obj = C0844a.f2048d;
                    if (TextUtils.isEmpty(str2)) {
                        obj = str2.substring(str.length(), str.length() + 1);
                        if (TextUtils.isDigitsOnly(obj)) {
                            i = Integer.parseInt(obj);
                        } else {
                            i = 1;
                        }
                        if (i >= 5) {
                            this.f4457b.setImageResource(C1373R.drawable.bg_meizu_4);
                        } else {
                            this.f4457b.setImageResource(C1373R.drawable.bg_meizu_5);
                        }
                        f4456a.trace("Miui version name " + str2);
                    }
                } catch (Throwable th32) {
                    obj3 = str2;
                    obj2 = str2;
                    th = th32;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (exec != null) {
                        exec.destroy();
                    }
                    throw th;
                }
            } catch (IOException e9) {
                e322222 = e9;
                obj4 = str2;
                obj3 = str2;
                obj2 = str2;
                e322222.printStackTrace();
                f4456a.error("Unable to read sysprop ro.miui.ui.version.code");
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (exec != null) {
                    exec.destroy();
                }
                str = "Flyme OS ";
                obj = C0844a.f2048d;
                if (TextUtils.isEmpty(str2)) {
                    obj = str2.substring(str.length(), str.length() + 1);
                    if (TextUtils.isDigitsOnly(obj)) {
                        i = 1;
                    } else {
                        i = Integer.parseInt(obj);
                    }
                    if (i >= 5) {
                        this.f4457b.setImageResource(C1373R.drawable.bg_meizu_5);
                    } else {
                        this.f4457b.setImageResource(C1373R.drawable.bg_meizu_4);
                    }
                    f4456a.trace("Miui version name " + str2);
                }
            } catch (Throwable th322) {
                obj4 = str2;
                obj3 = str2;
                obj2 = str2;
                th = th322;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (exec != null) {
                    exec.destroy();
                }
                throw th;
            }
        } catch (IOException e10) {
            e322222 = e10;
            exec = str2;
            inputStream = str2;
            inputStreamReader = str2;
            bufferedReader = str2;
            e322222.printStackTrace();
            f4456a.error("Unable to read sysprop ro.miui.ui.version.code");
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (exec != null) {
                exec.destroy();
            }
            str = "Flyme OS ";
            obj = C0844a.f2048d;
            if (TextUtils.isEmpty(str2)) {
                obj = str2.substring(str.length(), str.length() + 1);
                if (TextUtils.isDigitsOnly(obj)) {
                    i = 1;
                } else {
                    i = Integer.parseInt(obj);
                }
                if (i >= 5) {
                    this.f4457b.setImageResource(C1373R.drawable.bg_meizu_5);
                } else {
                    this.f4457b.setImageResource(C1373R.drawable.bg_meizu_4);
                }
                f4456a.trace("Miui version name " + str2);
            }
        } catch (Throwable th3222) {
            exec = str2;
            inputStream = str2;
            inputStreamReader = str2;
            bufferedReader = str2;
            th = th3222;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (exec != null) {
                exec.destroy();
            }
            throw th;
        }
        str = "Flyme OS ";
        obj = C0844a.f2048d;
        if (TextUtils.isEmpty(str2)) {
            if (str2.contains(str) && str2.length() > str.length() + 1) {
                obj = str2.substring(str.length(), str.length() + 1);
            }
            if (TextUtils.isDigitsOnly(obj)) {
                i = Integer.parseInt(obj);
            } else {
                i = 1;
            }
            if (i >= 5) {
                this.f4457b.setImageResource(C1373R.drawable.bg_meizu_4);
            } else {
                this.f4457b.setImageResource(C1373R.drawable.bg_meizu_5);
            }
            f4456a.trace("Miui version name " + str2);
        }
    }
}
