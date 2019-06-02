package com.beastbikes.android.modules.cycling.club.ui;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.avos.avoscloud.AVAnalytics;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.modules.p062c.C1880a;
import com.beastbikes.android.modules.preferences.ui.BaseEditTextActivity;
import com.beastbikes.android.modules.preferences.ui.CutAvatarActivity;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.android.p088g.C2798a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@C1459b(a = 2130903094)
@C1460c(a = 2131820560)
public class ClubInfoSettingActivity extends SessionFragmentActivity implements OnClickListener, C1371a {
    /* renamed from: a */
    String f5138a = "";
    @C1458a(a = 2131755459)
    /* renamed from: b */
    private RelativeLayout f5139b;
    @C1458a(a = 2131755461)
    /* renamed from: c */
    private CircleImageView f5140c;
    @C1458a(a = 2131755463)
    /* renamed from: d */
    private RelativeLayout f5141d;
    @C1458a(a = 2131755465)
    /* renamed from: e */
    private TextView f5142e;
    @C1458a(a = 2131755467)
    /* renamed from: f */
    private RelativeLayout f5143f;
    @C1458a(a = 2131755469)
    /* renamed from: g */
    private TextView f5144g;
    @C1458a(a = 2131755473)
    /* renamed from: h */
    private Switch f5145h;
    /* renamed from: i */
    private ClubInfoCompact f5146i;
    /* renamed from: j */
    private ClubManager f5147j;
    /* renamed from: k */
    private int f5148k;
    /* renamed from: l */
    private File f5149l = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), m6487c());
    /* renamed from: m */
    private String f5150m = "";

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5147j = new ClubManager(this);
        this.f5139b.setOnClickListener(this);
        this.f5141d.setOnClickListener(this);
        this.f5143f.setOnClickListener(this);
        m6480a();
        this.f5145h.setOnCheckedChangeListener(new ClubInfoSettingActivity$1(this));
    }

    protected void onResume() {
        super.onResume();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Cursor query;
        String string;
        Intent intent2;
        Throwable e;
        Cursor cursor;
        super.onActivityResult(i, i2, intent);
        CharSequence string2;
        switch (i) {
            case 2:
                if (i2 == -1 && intent != null) {
                    Uri data = intent.getData();
                    String[] strArr = new String[]{"_data"};
                    try {
                        query = getContentResolver().query(data, strArr, null, null, null);
                        if (query != null) {
                            try {
                                if (query.moveToFirst()) {
                                    string = query.getString(query.getColumnIndex(strArr[0]));
                                    if (!(query == null || query.isClosed())) {
                                        query.close();
                                    }
                                    if (TextUtils.isEmpty(string)) {
                                        string = data.getPath();
                                    }
                                    intent2 = new Intent(this, CutAvatarActivity.class);
                                    intent2.putExtra("path", string);
                                    startActivityForResult(intent2, 4);
                                    return;
                                }
                            } catch (Exception e2) {
                                e = e2;
                                cursor = query;
                                try {
                                    AVAnalytics.onError(this, Log.getStackTraceString(e));
                                    if (!(cursor == null || cursor.isClosed())) {
                                        cursor.close();
                                    }
                                    string = TextUtils.isEmpty(null) ? data.getPath() : null;
                                    intent2 = new Intent(this, CutAvatarActivity.class);
                                    intent2.putExtra("path", string);
                                    startActivityForResult(intent2, 4);
                                    return;
                                } catch (Throwable th) {
                                    e = th;
                                    query = cursor;
                                    query.close();
                                    if (TextUtils.isEmpty(null)) {
                                        data.getPath();
                                    }
                                    throw e;
                                }
                            } catch (Throwable th2) {
                                e = th2;
                                if (!(query == null || query.isClosed())) {
                                    query.close();
                                }
                                if (TextUtils.isEmpty(null)) {
                                    data.getPath();
                                }
                                throw e;
                            }
                        }
                        AVAnalytics.onError(this, "get photo path failed:" + data.toString());
                        string = null;
                        query.close();
                        if (TextUtils.isEmpty(string)) {
                            string = data.getPath();
                        }
                        break;
                    } catch (Exception e3) {
                        e = e3;
                        cursor = null;
                        AVAnalytics.onError(this, Log.getStackTraceString(e));
                        cursor.close();
                        if (TextUtils.isEmpty(null)) {
                        }
                        intent2 = new Intent(this, CutAvatarActivity.class);
                        intent2.putExtra("path", string);
                        startActivityForResult(intent2, 4);
                        return;
                    } catch (Throwable th3) {
                        e = th3;
                        query = null;
                        query.close();
                        if (TextUtils.isEmpty(null)) {
                            data.getPath();
                        }
                        throw e;
                    }
                    intent2 = new Intent(this, CutAvatarActivity.class);
                    intent2.putExtra("path", string);
                    startActivityForResult(intent2, 4);
                    return;
                }
                return;
            case 3:
                if (i2 == -1) {
                    Intent intent3 = new Intent(this, CutAvatarActivity.class);
                    intent3.putExtra("path", this.f5149l.getAbsolutePath());
                    startActivityForResult(intent3, 4);
                    return;
                }
                return;
            case 4:
                if (i2 == 4) {
                    Object stringExtra = intent.getStringExtra("path");
                    this.f5150m = stringExtra;
                    if (TextUtils.isEmpty(stringExtra)) {
                        this.f5140c.setImageResource(C1373R.drawable.ic_club_setting_default_logo);
                        return;
                    } else {
                        Picasso.with(this).load("file://" + stringExtra).fit().centerCrop().error((int) C1373R.drawable.ic_club_setting_default_logo).placeholder((int) C1373R.drawable.ic_club_setting_default_logo).into(this.f5140c);
                        return;
                    }
                }
                return;
            case 5:
                if (-1 == i2) {
                    string2 = intent.getExtras().getString("value");
                    if (!TextUtils.isEmpty(string2) && !this.f5142e.getText().toString().equals(string2)) {
                        this.f5142e.setText(string2);
                        return;
                    }
                    return;
                }
                return;
            case 6:
                if (-1 == i2) {
                    string2 = intent.getExtras().getString("value");
                    if (!TextUtils.isEmpty(string2) && !this.f5144g.getText().toString().equals(string2)) {
                        this.f5144g.setText(string2);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.club_info_menu_setting_savr_item:
                m6481a(menuItem);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* renamed from: a */
    private void m6481a(MenuItem menuItem) {
        String charSequence = this.f5142e.getText().toString();
        if (!TextUtils.isEmpty(charSequence)) {
            String charSequence2 = this.f5144g.getText().toString();
            if (!TextUtils.isEmpty(charSequence2)) {
                menuItem.setEnabled(false);
                File file = new File(this.f5150m);
                try {
                    this.f5138a = C2798a.a(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(this.f5138a) || !file.exists()) {
                    m6483a("", charSequence, charSequence2, "");
                    menuItem.setEnabled(true);
                    return;
                }
                C1880a c1880a = new C1880a(this);
                c1880a.a(new ClubInfoSettingActivity$2(this, charSequence, charSequence2, menuItem));
                charSequence = c1880a.c() + this.f5138a;
                c1880a.a(charSequence, file, charSequence);
                Toasts.show(this, C1373R.string.club_info_setting_save_msg);
            }
        }
    }

    /* renamed from: a */
    private void m6483a(String str, String str2, String str3, String str4) {
        getAsyncTaskQueue().a(new ClubInfoSettingActivity$3(this, str, str2, str3, str4), new Void[0]);
    }

    /* renamed from: a */
    private void m6480a() {
        getAsyncTaskQueue().a(new ClubInfoSettingActivity$4(this, m5331p()), new Void[0]);
    }

    /* renamed from: b */
    private void m6485b() {
        new Builder(this).setTitle(getString(C1373R.string.club_info_setting_logo_title)).setNegativeButton(getString(C1373R.string.str_album), new ClubInfoSettingActivity$6(this)).setPositiveButton(getString(C1373R.string.str_camera), new ClubInfoSettingActivity$5(this)).show();
    }

    /* renamed from: c */
    private String m6487c() {
        return new SimpleDateFormat("'BEAST'_yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".jpg";
    }

    public void onClick(View view) {
        String charSequence;
        Intent intent;
        switch (view.getId()) {
            case C1373R.id.activity_club_info_setting_logo:
                m6485b();
                return;
            case C1373R.id.activity_club_info_setting_name:
                charSequence = this.f5142e.getText().toString();
                intent = new Intent(this, BaseEditTextActivity.class);
                intent.putExtra("value", charSequence);
                intent.putExtra("length", 12);
                startActivityForResult(intent, 5);
                return;
            case C1373R.id.activity_club_info_setting_desc:
                charSequence = this.f5144g.getText().toString();
                intent = new Intent(this, BaseEditTextActivity.class);
                intent.putExtra("value", charSequence);
                intent.putExtra("length", 20);
                startActivityForResult(intent, 6);
                return;
            default:
                return;
        }
    }
}
