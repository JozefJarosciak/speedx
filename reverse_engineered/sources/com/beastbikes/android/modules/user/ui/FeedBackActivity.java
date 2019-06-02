package com.beastbikes.android.modules.user.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.p062c.C1880a;
import com.beastbikes.android.modules.p062c.C1882d;
import com.beastbikes.android.utils.C2564l;
import com.beastbikes.android.utils.ab;
import com.beastbikes.android.widget.C2657e;
import com.beastbikes.android.widget.multiimageselector.MultiImageSelectorActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.android.p088g.C2803i;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903126)
@C1460c(a = 2131820545)
public class FeedBackActivity extends SessionFragmentActivity implements OnClickListener, C1882d {
    /* renamed from: n */
    private static final Logger f6366n = LoggerFactory.getLogger("FeedBackActivity");
    @C1458a(a = 2131755707)
    /* renamed from: a */
    TextView f6367a;
    @C1458a(a = 2131755708)
    /* renamed from: b */
    TextView f6368b;
    @C1458a(a = 2131755709)
    /* renamed from: c */
    View f6369c;
    @C1458a(a = 2131755710)
    /* renamed from: d */
    TextView f6370d;
    @C1458a(a = 2131755712)
    /* renamed from: e */
    EditText f6371e;
    @C1458a(a = 2131755713)
    /* renamed from: f */
    ImageView f6372f;
    @C1458a(a = 2131755715)
    /* renamed from: g */
    CheckBox f6373g;
    @C1458a(a = 2131755714)
    /* renamed from: h */
    View f6374h;
    @C1458a(a = 2131755716)
    /* renamed from: i */
    View f6375i;
    @C1458a(a = 2131755717)
    /* renamed from: j */
    EditText f6376j;
    @C1458a(a = 2131755522)
    /* renamed from: k */
    View f6377k;
    /* renamed from: l */
    ArrayList<String> f6378l = new ArrayList();
    /* renamed from: m */
    C1802i f6379m;
    /* renamed from: o */
    private C2657e f6380o;
    /* renamed from: p */
    private String f6381p;
    /* renamed from: q */
    private int f6382q = 0;
    /* renamed from: r */
    private String f6383r = null;
    /* renamed from: s */
    private String f6384s = null;
    /* renamed from: t */
    private StringBuilder f6385t = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        for (Object add : getResources().getStringArray(C1373R.array.feedback_issue_type)) {
            this.f6378l.add(add);
        }
        m7583c();
    }

    /* renamed from: c */
    private void m7583c() {
        this.f6367a.setOnClickListener(this);
        this.f6367a.setTag(Boolean.valueOf(false));
        this.f6367a.setTextColor(-2743260);
        this.f6367a.setBackgroundResource(C1373R.drawable.bg_feedback_tab);
        this.f6368b.setOnClickListener(this);
        this.f6369c.setOnClickListener(this);
        this.f6372f.setOnClickListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.activity_complain_menu_commit:
                m7590g();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.tab_app_error:
            case C1373R.id.tab_product_suggest:
                m7585d();
                return;
            case C1373R.id.feedback_issue_type:
                this.f6380o = new C2657e(this, this.f6378l, 2, new FeedBackActivity$1(this));
                this.f6380o.showAtLocation(this.f6377k, 81, 0, 0);
                return;
            case C1373R.id.feedback_iv_picture:
                m7587e();
                return;
            default:
                return;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i2) {
            case -1:
                switch (i) {
                    case m_AppUI.MSG_MAP_ROUTEALERT_GPSSTATE /*65297*/:
                        ArrayList stringArrayListExtra = intent.getStringArrayListExtra("select_result");
                        if (stringArrayListExtra != null && stringArrayListExtra.size() > 0) {
                            this.f6381p = (String) stringArrayListExtra.get(0);
                            Picasso.with(this).load("file://" + this.f6381p).fit().centerCrop().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).into(this.f6372f);
                            break;
                        }
                    default:
                        break;
                }
        }
        super.onActivityResult(i, i2, intent);
    }

    /* renamed from: a */
    public void m7592a(String str) {
        f6366n.info("Upload log success: " + str);
    }

    /* renamed from: a */
    public void m7591a() {
    }

    /* renamed from: d */
    private void m7585d() {
        if (((Boolean) this.f6367a.getTag()).booleanValue()) {
            this.f6367a.setTag(Boolean.valueOf(false));
            this.f6367a.setTextColor(-2743260);
            this.f6367a.setBackgroundResource(C1373R.drawable.bg_feedback_tab);
            this.f6368b.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.f6368b.setBackgroundResource(C1373R.drawable.transparent);
            this.f6369c.setVisibility(0);
            this.f6374h.setVisibility(0);
            this.f6375i.setVisibility(0);
            this.f6382q = 0;
            return;
        }
        this.f6367a.setTag(Boolean.valueOf(true));
        this.f6367a.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f6367a.setBackgroundResource(C1373R.drawable.transparent);
        this.f6368b.setTextColor(-2743260);
        this.f6368b.setBackgroundResource(C1373R.drawable.bg_feedback_tab);
        this.f6369c.setVisibility(8);
        this.f6374h.setVisibility(8);
        this.f6375i.setVisibility(8);
        this.f6382q = 1;
    }

    /* renamed from: e */
    private void m7587e() {
        Intent intent = new Intent(this, MultiImageSelectorActivity.class);
        intent.putExtra("gallery_full", true);
        intent.putExtra("is_max", false);
        intent.putExtra("max_select_count", 1);
        intent.putStringArrayListExtra("default_list", new ArrayList());
        startActivityForResult(intent, m_AppUI.MSG_MAP_ROUTEALERT_GPSSTATE);
    }

    /* renamed from: f */
    private void m7589f() {
        if (this.f6373g.isChecked() && this.f6382q == 0) {
            try {
                if (C2564l.a(getPackageManager().getApplicationInfo(getPackageName(), 128).dataDir + File.separator + "files" + File.separator + "log", Environment.getExternalStorageDirectory().getAbsolutePath() + "/beast")) {
                    m7579a(new Date());
                } else {
                    m7579a(new Date());
                }
            } catch (NameNotFoundException e) {
            }
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    /* renamed from: a */
    private void m7579a(Date date) {
        int i = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String str = ".log";
        File[] listFiles = new File(getFilesDir(), "log").listFiles(new FeedBackActivity$2(this));
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
                    f6366n.info("Log name : " + file2.getAbsolutePath());
                    arrayList.add(file2);
                }
                i++;
            }
            AVUser currentUser = AVUser.getCurrentUser();
            String str2 = "logs/android_" + String.valueOf(currentUser != null ? currentUser.getObjectId() : C2803i.a(this)) + "_.zip";
            str2 = C2564l.a(str2, simpleDateFormat.format(new Date()), str2.length() - 5);
            C1880a c1880a = new C1880a(this);
            c1880a.a(this);
            this.f6385t = new StringBuilder();
            try {
                ab.a(arrayList, file);
                c1880a.a(str2, file, str2);
                this.f6385t.append(str2);
            } catch (IOException e) {
                IOException iOException = e;
                str = str2;
                for (File file3 : arrayList) {
                    str = C2564l.a(str, simpleDateFormat.format(new Date(file3.lastModified())), str.length() - 5);
                    f6366n.info("Log name : " + file3.getAbsolutePath());
                    c1880a.a(str, file3, str);
                    this.f6385t.append(str).append(",");
                }
                iOException.printStackTrace();
            }
        }
    }

    /* renamed from: g */
    private void m7590g() {
        String str = null;
        if (TextUtils.isEmpty(this.f6371e.getText())) {
            Toasts.show(this, C1373R.string.feedback_issue_desc_not_null);
            return;
        }
        this.f6383r = this.f6371e.getText().toString();
        if (this.f6382q == 0) {
            if (TextUtils.isEmpty(this.f6384s)) {
                Toasts.show(this, C1373R.string.feedback_issue_type_not_null);
                return;
            } else if (TextUtils.isEmpty(this.f6376j.getText())) {
                Toasts.show(this, C1373R.string.feedback_contact_not_null);
                return;
            }
        }
        if (!TextUtils.isEmpty(this.f6376j.getText())) {
            str = this.f6376j.getText().toString();
        }
        getAsyncTaskQueue().a(new FeedBackActivity$3(this), new String[]{str});
    }
}
