package com.beastbikes.android.modules.preferences.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.utils.Toasts;

@C1457a(a = "文本编辑(全部)")
@C1459b(a = 2130903349)
@C1460c(a = 2131820568)
public class BaseEditTextActivity extends SessionFragmentActivity {
    @C1458a(a = 2131756580)
    /* renamed from: a */
    private EditText f5878a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent != null) {
            CharSequence stringExtra = intent.getStringExtra("value");
            if (intent.hasExtra("length")) {
                this.f5878a.setFilters(new InputFilter[]{new LengthFilter(intent.getIntExtra("length", 12))});
            }
            this.f5878a.setText(stringExtra);
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.edit_text_activity_action_button_save:
                Intent intent = getIntent();
                if (intent == null) {
                    return false;
                }
                String obj = this.f5878a.getText().toString();
                if (TextUtils.isEmpty(obj.trim())) {
                    Toasts.show(this, C1373R.string.edit_text_activity_can_not_be_null);
                    return false;
                }
                intent.putExtra("value", obj);
                setResult(-1, intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
