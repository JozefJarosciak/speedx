package com.beastbikes.android.modules.preferences.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;
import com.alipay.sdk.sys.C0869a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;
import com.beastbikes.framework.ui.android.utils.Toasts;

@C1457a(a = "文本编辑(全部)")
@C1459b(a = 2130903349)
@C1460c(a = 2131820568)
public class EditTextActivity extends BaseFragmentActivity {
    /* renamed from: a */
    private C1398a f5893a;
    /* renamed from: b */
    private String f5894b;
    @C1458a(a = 2131756580)
    /* renamed from: c */
    private EditText f5895c;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        int a = m7133a(this, 8.0f);
        this.f5895c.setPadding(a, 0, a, 0);
        Intent intent = getIntent();
        if (intent != null) {
            this.f5895c.setText(intent.getStringExtra("value"));
            if (intent.hasExtra(C0869a.f2169j)) {
                this.f5895c.setFilters(new InputFilter[]{new LengthFilter(8)});
            } else {
                this.f5895c.setFilters(new InputFilter[]{new LengthFilter(20)});
            }
            if (intent.hasExtra("finish") || intent.hasExtra("offline")) {
                this.f5893a = new C1398a((Activity) this);
                if (intent.hasExtra("finish")) {
                    this.f5894b = intent.getStringExtra("finish");
                } else {
                    this.f5894b = intent.getStringExtra("offline");
                }
            }
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.edit_text_activity_action_button_save:
                Intent intent = new Intent();
                Editable text = this.f5895c.getText();
                if (text == null || text.length() <= 0) {
                    Toasts.show(this, C1373R.string.edit_text_activity_can_not_be_null);
                    return false;
                }
                intent.putExtra("value", String.valueOf(text));
                if (TextUtils.isEmpty(text.toString().trim())) {
                    Toasts.show(this, C1373R.string.edit_text_activity_can_not_be_null);
                    return false;
                }
                Intent intent2 = getIntent();
                if (intent2 != null && intent2.hasExtra("offline")) {
                    try {
                        LocalActivity b = this.f5893a.m5868b(this.f5894b);
                        b.setTitle(text.toString());
                        this.f5893a.m5870b(b);
                    } catch (BusinessException e) {
                        finish();
                    }
                }
                setResult(-1, intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* renamed from: a */
    public static int m7133a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }
}
