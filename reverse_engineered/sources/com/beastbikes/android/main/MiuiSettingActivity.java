package com.beastbikes.android.main;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

@C1459b(a = 2130903526)
public class MiuiSettingActivity extends BaseActivity implements OnClickListener {
    /* renamed from: a */
    private static final Logger f4459a = LoggerFactory.getLogger(MiuiSettingActivity.class);
    @C1458a(a = 2131757199)
    /* renamed from: b */
    private ImageView f4460b;
    @C1458a(a = 2131757200)
    /* renamed from: c */
    private TextView f4461c;
    @C1458a(a = 2131757190)
    /* renamed from: d */
    private ImageView f4462d;
    @C1458a(a = 2131757191)
    /* renamed from: e */
    private Button f4463e;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f4460b.setOnClickListener(this);
        this.f4463e.setOnClickListener(this);
        m5825c();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.miui_setting_go_open:
                m5824b();
                return;
            case C1373R.id.miui_setting_close:
                m5826a();
                return;
            default:
                return;
        }
    }

    protected void onRestart() {
        super.onRestart();
        m5826a();
    }

    public void finish() {
        super.finish();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 2) {
            m5826a();
            finish();
        }
    }

    /* renamed from: a */
    protected void m5826a() {
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
    private void m5824b() {
        Intent intent;
        try {
            intent = new Intent();
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
            startActivity(intent);
        } catch (Exception e) {
            try {
                PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                Intent intent2 = new Intent("miui.intent.action.APP_PERM_EDITOR");
                intent2.putExtra("extra_package_uid", packageInfo.applicationInfo.uid);
                intent2.putExtra("extra_pkgname", getPackageName());
                try {
                    startActivity(intent2);
                } catch (Exception e2) {
                    intent = new Intent();
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts(OnlineConfigAgent.KEY_PACKAGE, getPackageName(), null));
                    startActivity(intent);
                }
            } catch (NameNotFoundException e3) {
            }
        }
    }

    /* renamed from: c */
    private void m5825c() {
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        IOException e;
        int i;
        Throwable th;
        Object obj;
        Object obj2;
        Object obj3;
        String str = null;
        Process exec;
        try {
            exec = Runtime.getRuntime().exec("getprop ro.miui.ui.version.code");
            try {
                inputStream = exec.getInputStream();
                try {
                    inputStreamReader = new InputStreamReader(inputStream);
                    try {
                        bufferedReader = new BufferedReader(inputStreamReader, 1024);
                        try {
                            str = bufferedReader.readLine();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    f4459a.error("Exception while closing InputStream");
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            if (inputStreamReader != null) {
                                try {
                                    inputStreamReader.close();
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
                                f4459a.error("Unable to read sysprop ro.miui.ui.version.code");
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e5) {
                                        f4459a.error("Exception while closing InputStream");
                                    }
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e322) {
                                        e322.printStackTrace();
                                    }
                                }
                                if (inputStreamReader != null) {
                                    try {
                                        inputStreamReader.close();
                                    } catch (IOException e3222) {
                                        e3222.printStackTrace();
                                    }
                                }
                                if (exec != null) {
                                    exec.destroy();
                                }
                                i = 1;
                                if (!TextUtils.isEmpty(str)) {
                                    i = Integer.parseInt(str);
                                }
                                if (i <= 3) {
                                    this.f4462d.setImageResource(C1373R.drawable.ic_setting_miui_7_bg);
                                    this.f4461c.setText(C1373R.string.miui_setting_desc_2);
                                } else {
                                    this.f4462d.setImageResource(C1373R.drawable.ic_setting_miui_6_bg);
                                    this.f4461c.setText(C1373R.string.miui_setting_desc_1);
                                }
                                f4459a.trace("Miui version name " + str);
                            } catch (Throwable th2) {
                                th = th2;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e6) {
                                        f4459a.error("Exception while closing InputStream");
                                    }
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e32222) {
                                        e32222.printStackTrace();
                                    }
                                }
                                if (inputStreamReader != null) {
                                    try {
                                        inputStreamReader.close();
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
                        obj = str;
                        e322222.printStackTrace();
                        f4459a.error("Unable to read sysprop ro.miui.ui.version.code");
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (exec != null) {
                            exec.destroy();
                        }
                        i = 1;
                        if (TextUtils.isEmpty(str)) {
                            i = Integer.parseInt(str);
                        }
                        if (i <= 3) {
                            this.f4462d.setImageResource(C1373R.drawable.ic_setting_miui_6_bg);
                            this.f4461c.setText(C1373R.string.miui_setting_desc_1);
                        } else {
                            this.f4462d.setImageResource(C1373R.drawable.ic_setting_miui_7_bg);
                            this.f4461c.setText(C1373R.string.miui_setting_desc_2);
                        }
                        f4459a.trace("Miui version name " + str);
                    } catch (Throwable th3) {
                        obj = str;
                        th = th3;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (exec != null) {
                            exec.destroy();
                        }
                        throw th;
                    }
                } catch (IOException e8) {
                    e322222 = e8;
                    obj2 = str;
                    obj = str;
                    e322222.printStackTrace();
                    f4459a.error("Unable to read sysprop ro.miui.ui.version.code");
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (exec != null) {
                        exec.destroy();
                    }
                    i = 1;
                    if (TextUtils.isEmpty(str)) {
                        i = Integer.parseInt(str);
                    }
                    if (i <= 3) {
                        this.f4462d.setImageResource(C1373R.drawable.ic_setting_miui_7_bg);
                        this.f4461c.setText(C1373R.string.miui_setting_desc_2);
                    } else {
                        this.f4462d.setImageResource(C1373R.drawable.ic_setting_miui_6_bg);
                        this.f4461c.setText(C1373R.string.miui_setting_desc_1);
                    }
                    f4459a.trace("Miui version name " + str);
                } catch (Throwable th32) {
                    obj2 = str;
                    obj = str;
                    th = th32;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (exec != null) {
                        exec.destroy();
                    }
                    throw th;
                }
            } catch (IOException e9) {
                e322222 = e9;
                obj2 = str;
                obj3 = str;
                obj = str;
                e322222.printStackTrace();
                f4459a.error("Unable to read sysprop ro.miui.ui.version.code");
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (exec != null) {
                    exec.destroy();
                }
                i = 1;
                if (TextUtils.isEmpty(str)) {
                    i = Integer.parseInt(str);
                }
                if (i <= 3) {
                    this.f4462d.setImageResource(C1373R.drawable.ic_setting_miui_6_bg);
                    this.f4461c.setText(C1373R.string.miui_setting_desc_1);
                } else {
                    this.f4462d.setImageResource(C1373R.drawable.ic_setting_miui_7_bg);
                    this.f4461c.setText(C1373R.string.miui_setting_desc_2);
                }
                f4459a.trace("Miui version name " + str);
            } catch (Throwable th322) {
                obj2 = str;
                obj3 = str;
                obj = str;
                th = th322;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (exec != null) {
                    exec.destroy();
                }
                throw th;
            }
        } catch (IOException e10) {
            e322222 = e10;
            exec = str;
            inputStreamReader = str;
            inputStream = str;
            bufferedReader = str;
            e322222.printStackTrace();
            f4459a.error("Unable to read sysprop ro.miui.ui.version.code");
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (exec != null) {
                exec.destroy();
            }
            i = 1;
            if (TextUtils.isEmpty(str)) {
                i = Integer.parseInt(str);
            }
            if (i <= 3) {
                this.f4462d.setImageResource(C1373R.drawable.ic_setting_miui_6_bg);
                this.f4461c.setText(C1373R.string.miui_setting_desc_1);
            } else {
                this.f4462d.setImageResource(C1373R.drawable.ic_setting_miui_7_bg);
                this.f4461c.setText(C1373R.string.miui_setting_desc_2);
            }
            f4459a.trace("Miui version name " + str);
        } catch (Throwable th3222) {
            exec = str;
            inputStreamReader = str;
            inputStream = str;
            bufferedReader = str;
            th = th3222;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (exec != null) {
                exec.destroy();
            }
            throw th;
        }
        i = 1;
        if (TextUtils.isEmpty(str)) {
            i = Integer.parseInt(str);
        }
        if (i <= 3) {
            this.f4462d.setImageResource(C1373R.drawable.ic_setting_miui_7_bg);
            this.f4461c.setText(C1373R.string.miui_setting_desc_2);
        } else {
            this.f4462d.setImageResource(C1373R.drawable.ic_setting_miui_6_bg);
            this.f4461c.setText(C1373R.string.miui_setting_desc_1);
        }
        f4459a.trace("Miui version name " + str);
    }
}
