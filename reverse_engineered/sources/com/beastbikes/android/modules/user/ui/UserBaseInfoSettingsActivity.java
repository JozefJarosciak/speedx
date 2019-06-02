package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.squareup.picasso.Picasso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1457a(a = "设置 - 个人信息 - 个人资料设置")
@C1459b(a = 2130903210)
public class UserBaseInfoSettingsActivity extends BaseUserInfoSettingsActivity {
    /* renamed from: d */
    private static final Logger f6608d = LoggerFactory.getLogger("UserBaseInfoSettingsActivity");
    @C1458a(a = 2131756100)
    /* renamed from: e */
    private ImageView f6609e;
    @C1458a(a = 2131756102)
    /* renamed from: f */
    private TextView f6610f;
    @C1458a(a = 2131756103)
    /* renamed from: g */
    private RelativeLayout f6611g;
    @C1458a(a = 2131756105)
    /* renamed from: h */
    private TextView f6612h;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            registerForContextMenu(this.f6609e);
            m7835a(currentUser);
            m7836i();
        }
    }

    /* renamed from: a */
    public void mo2805a(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f6609e.setImageResource(C1373R.drawable.ic_avatar);
            return;
        }
        Picasso.with(this).load("file://" + str).fit().centerCrop().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).into(this.f6609e);
        this.b.a();
    }

    /* renamed from: b */
    public void mo2806b(String str) {
        this.f6612h.setText(str);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 6 && intent != null) {
            this.f6610f.setText(intent.getStringExtra("key_nickname"));
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterForContextMenu(this.f6609e);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.img_user_base_info_avatar:
                openContextMenu(view);
                return;
            case C1373R.id.tv_user_base_info_nickname:
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser == null) {
                    f6608d.error("avUser is null");
                    return;
                }
                Intent intent = new Intent(this, InputNicknameActivity.class);
                intent.putExtra("key_nickname", currentUser.getDisplayName());
                startActivityForResult(intent, 6);
                return;
            case C1373R.id.rela_user_base_info_region:
                Intent intent2 = new Intent(this, SearchRegionActivity.class);
                intent2.putExtra("enter_type", 1);
                startActivityForResult(intent2, 5);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m7835a(AVUser aVUser) {
        if (!TextUtils.isEmpty(aVUser.getAvatar())) {
            Picasso.with(this).load(aVUser.getAvatar()).placeholder((int) C1373R.drawable.ic_avatar).error((int) C1373R.drawable.ic_avatar).into(this.f6609e);
        }
        this.f6610f.setText(aVUser.getDisplayName() + "");
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(aVUser.getCountry())) {
            stringBuilder.append(aVUser.getCountry());
        }
        if (!TextUtils.isEmpty(aVUser.getProvince())) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(aVUser.getProvince());
        }
        if (!TextUtils.isEmpty(aVUser.getCity())) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(aVUser.getCity());
        }
        this.f6612h.setText(stringBuilder.toString());
    }

    /* renamed from: i */
    private void m7836i() {
        this.f6609e.setOnClickListener(this);
        this.f6610f.setOnClickListener(this);
        this.f6611g.setOnClickListener(this);
    }

    /* renamed from: c */
    public String mo2804c() {
        return this.f6610f.getText().toString().trim();
    }

    /* renamed from: d */
    public boolean m7841d() {
        return false;
    }

    /* renamed from: a */
    public void m7837a(ProfileDTO profileDTO) {
    }
}
