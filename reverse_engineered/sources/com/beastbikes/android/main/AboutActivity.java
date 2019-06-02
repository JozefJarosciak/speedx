package com.beastbikes.android.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.p062c.C1880a;
import com.beastbikes.android.modules.p062c.C1882d;
import com.beastbikes.android.update.p079a.C2547a;
import com.beastbikes.android.update.ui.VersionUpdateActivity;
import com.beastbikes.android.utils.C2564l;
import com.beastbikes.android.utils.ab;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C1465f;
import com.beastbikes.framework.android.p088g.C2799c;
import com.beastbikes.framework.android.p088g.C2803i;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1457a(a = "关于页")
@C1459b(a = 2130903067)
public class AboutActivity extends BaseFragmentActivity implements OnSharedPreferenceChangeListener, OnClickListener, C1882d {
    /* renamed from: a */
    private static final Logger f4433a = LoggerFactory.getLogger("AboutActivity");
    /* renamed from: b */
    private long f4434b = 0;
    /* renamed from: c */
    private Vector<Long> f4435c = new Vector();
    @C1458a(a = 2131755296)
    /* renamed from: d */
    private TextView f4436d;
    @C1458a(a = 2131755297)
    /* renamed from: e */
    private ViewGroup f4437e;
    @C1458a(a = 2131755298)
    /* renamed from: f */
    private TextView f4438f;
    @C1458a(a = 2131755295)
    /* renamed from: g */
    private LinearLayout f4439g;
    @C1458a(a = 2131755300)
    /* renamed from: h */
    private TextView f4440h;
    @C1458a(a = 2131755302)
    /* renamed from: i */
    private View f4441i;
    @C1458a(a = 2131755303)
    /* renamed from: j */
    private TextView f4442j;
    /* renamed from: k */
    private SharedPreferences f4443k;

    protected void onCreate(Bundle bundle) {
        int i;
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        for (i = 0; i < this.f4437e.getChildCount(); i++) {
            View childAt = this.f4437e.getChildAt(i);
            if (C1849a.a()) {
                if (childAt.getId() == C1373R.id.about_activity_email_lay) {
                    childAt.setVisibility(8);
                } else {
                    childAt.setVisibility(0);
                }
            } else if (childAt.getId() == C1373R.id.about_view || childAt.getId() == C1373R.id.about_activity_email_lay) {
                childAt.setVisibility(0);
            } else {
                childAt.setVisibility(8);
            }
        }
        i = ((WindowManager) getSystemService("window")).getDefaultDisplay().getWidth();
        LayoutParams layoutParams = this.f4439g.getLayoutParams();
        layoutParams.height = (i * 629) / 720;
        this.f4439g.setLayoutParams(layoutParams);
        layoutParams = this.f4436d.getLayoutParams();
        layoutParams.height = (((i * 629) / 720) * 3) / 5;
        this.f4436d.setLayoutParams(layoutParams);
        this.f4436d.setText("V" + C1465f.m8062b(this));
        this.f4436d.setOnClickListener(this);
        this.f4438f.setOnClickListener(this);
        this.f4440h.setOnClickListener(this);
        this.f4443k = PreferenceManager.getDefaultSharedPreferences(this);
        this.f4443k.registerOnSharedPreferenceChangeListener(this);
        m5796c();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
        this.f4443k.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case C1373R.id.activity_about_version_textview:
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (elapsedRealtime > this.f4434b) {
                    for (int size = this.f4435c.size() - 1; size >= 0; size--) {
                        if (((Long) this.f4435c.get(size)).longValue() < elapsedRealtime - 30000) {
                            this.f4435c.remove(size);
                        }
                    }
                    this.f4435c.add(Long.valueOf(elapsedRealtime));
                    if (this.f4435c.size() >= 5) {
                        m5795b();
                        this.f4435c.clear();
                        this.f4434b = SystemClock.elapsedRealtime() + 30000;
                        return;
                    }
                    return;
                }
                return;
            case C1373R.id.activity_about_tutorial:
                intent = new Intent(this, TutorialActivity.class);
                intent.putExtra("enter_type", 1);
                startActivityForResult(intent, 1);
                return;
            case C1373R.id.activity_about_enter_weibo_tv:
                intent = new Intent(this, BrowserActivity.class);
                intent.setData(Uri.parse("http://weibo.com/beastbikes?is_hot=1"));
                startActivity(intent);
                return;
            case C1373R.id.activity_about_update_group:
                startActivity(new Intent(this, VersionUpdateActivity.class));
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    private void m5795b() {
        try {
            if (C2564l.a(getPackageManager().getApplicationInfo(getPackageName(), 128).dataDir + File.separator + "files" + File.separator + "log", Environment.getExternalStorageDirectory().getAbsolutePath() + "/beast")) {
                Toasts.show(this, "export log success");
            } else {
                Toasts.show(this, "export log failed");
            }
            m5794a(new Date());
        } catch (NameNotFoundException e) {
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    /* renamed from: a */
    private void m5794a(Date date) {
        int i = 0;
        switch (C2799c.b(this)) {
            case 1:
            case 7:
            case 9:
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                String str = ".log";
                File[] listFiles = new File(getFilesDir(), "log").listFiles(new AboutActivity$1(this));
                if (listFiles != null && listFiles.length >= 1) {
                    Calendar instance = Calendar.getInstance();
                    instance.add(5, -2);
                    instance.set(11, 0);
                    instance.set(12, 0);
                    instance.set(13, 0);
                    instance.set(14, 0);
                    long timeInMillis = instance.getTimeInMillis();
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/beast/" + simpleDateFormat.format(date) + ".zip");
                    Object<File> arrayList = new ArrayList();
                    int length = listFiles.length;
                    while (i < length) {
                        File file2 = listFiles[i];
                        if (file2.lastModified() > timeInMillis) {
                            arrayList.add(file2);
                        }
                        i++;
                    }
                    AVUser currentUser = AVUser.getCurrentUser();
                    String str2 = "logs/android_" + String.valueOf(currentUser != null ? currentUser.getObjectId() : C2803i.a(this)) + "_.zip";
                    str2 = C2564l.a(str2, simpleDateFormat.format(new Date()), str2.length() - 5);
                    C1880a c1880a = new C1880a(this);
                    c1880a.a(this);
                    try {
                        ab.a(arrayList, file);
                        c1880a.a(str2, file, str2);
                        return;
                    } catch (IOException e) {
                        IOException iOException = e;
                        str = str2;
                        for (File file3 : arrayList) {
                            str = C2564l.a(str, simpleDateFormat.format(new Date(file3.lastModified())), str.length() - 5);
                            c1880a.a(str, file3, str);
                        }
                        iOException.printStackTrace();
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m5798a(String str) {
        f4433a.info("Log path: " + str);
        Toasts.show(this, "Upload log success");
    }

    /* renamed from: a */
    public void m5797a() {
        Toasts.show(this, "Upload log error");
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str.equals("beast.version.update")) {
            m5796c();
        }
    }

    /* renamed from: c */
    private void m5796c() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int i = defaultSharedPreferences.getInt("beast.version.update", 0);
        defaultSharedPreferences.edit().putBoolean("beast.version.update.guide2" + i, false).apply();
        if (i > C2547a.a(this)) {
            this.f4442j.setText(C1373R.string.version_update_has_new);
            this.f4441i.setOnClickListener(this);
            return;
        }
        this.f4442j.setText(C1373R.string.version_update_not_has_new);
    }
}
