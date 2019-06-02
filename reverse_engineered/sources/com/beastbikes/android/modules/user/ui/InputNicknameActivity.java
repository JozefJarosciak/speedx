package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.widget.EditText;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1457a(a = "输入昵称页面")
@C1459b(a = 2130903144)
public class InputNicknameActivity extends BaseUserInfoSettingsActivity {
    @C1458a(a = 2131755790)
    /* renamed from: d */
    private EditText f6426d;
    /* renamed from: e */
    private String f6427e;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.f6427e = intent.getStringExtra("key_nickname");
        m7651i();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C1373R.menu.menu_with_one_title_without_icon, menu);
        menu.findItem(C1373R.id.menu_with_one_title_without_icon).setTitle(C1373R.string.str_save);
        return true;
    }

    /* renamed from: i */
    private void m7651i() {
        this.f6426d.setText(this.f6427e + "");
    }

    /* renamed from: c */
    public String mo2804c() {
        return this.f6426d.getText().toString().trim();
    }

    /* renamed from: d */
    public boolean m7654d() {
        return true;
    }

    /* renamed from: a */
    public void m7652a(ProfileDTO profileDTO) {
        Intent intent = new Intent();
        intent.putExtra("key_nickname", profileDTO.getNickname());
        setResult(-1, intent);
        finish();
    }
}
