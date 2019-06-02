package com.beastbikes.android.modules.cycling.club.ui.widget;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.beastbikes.android.C1373R;

/* compiled from: CommentEditView */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.d */
public class C2152d extends LinearLayout implements TextWatcher, OnClickListener, OnEditorActionListener {
    /* renamed from: a */
    private C2091a f10087a;
    /* renamed from: b */
    private EditText f10088b;
    /* renamed from: c */
    private Button f10089c;
    /* renamed from: d */
    private View f10090d;
    /* renamed from: e */
    private Activity f10091e;
    /* renamed from: f */
    private C2151b f10092f = new C2151b(this);
    /* renamed from: g */
    private int f10093g;
    /* renamed from: h */
    private int f10094h;
    /* renamed from: i */
    private String f10095i;
    /* renamed from: j */
    private boolean f10096j;

    /* compiled from: CommentEditView */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.d$a */
    public interface C2091a {
        /* renamed from: a */
        void mo3395a(String str, int i, int i2);
    }

    /* compiled from: CommentEditView */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.d$b */
    class C2151b extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ C2152d f10086a;

        C2151b(C2152d c2152d) {
            this.f10086a = c2152d;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(MyFrameLayout.f10047a)) {
                LayoutParams layoutParams = (LayoutParams) this.f10086a.f10090d.getLayoutParams();
                if (intent.getBooleanExtra(MyFrameLayout.f10048b, false)) {
                    layoutParams.height = -2;
                    this.f10086a.f10090d.setMinimumHeight((int) context.getResources().getDimension(C1373R.dimen.clubfeed_comment_et_height));
                } else {
                    layoutParams.height = 0;
                    this.f10086a.f10090d.setMinimumHeight(0);
                }
                this.f10086a.f10090d.setLayoutParams(layoutParams);
            }
        }
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 66;
    }

    public C2152d(Activity activity) {
        super(activity);
        this.f10091e = activity;
        LayoutInflater.from(activity).inflate(C1373R.layout.clubfeed_post, this);
        m11048e();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f10091e.registerReceiver(this.f10092f, new IntentFilter(MyFrameLayout.f10047a));
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f10092f != null && !this.f10092f.isOrderedBroadcast()) {
            this.f10091e.unregisterReceiver(this.f10092f);
        }
    }

    public void setListener(C2091a c2091a) {
        this.f10087a = c2091a;
    }

    /* renamed from: e */
    private void m11048e() {
        this.f10090d = findViewById(C1373R.id.clubfeed_post);
        this.f10088b = (EditText) findViewById(C1373R.id.et_clubfeed_comment);
        this.f10089c = (Button) findViewById(C1373R.id.btn_clubfeed_send);
        this.f10089c.setOnClickListener(this);
        this.f10088b.addTextChangedListener(this);
        this.f10088b.setOnEditorActionListener(this);
        m11053d();
    }

    public void onClick(View view) {
        if (view == this.f10089c) {
            if (!(TextUtils.isEmpty(this.f10088b.getText()) || this.f10087a == null)) {
                this.f10087a.mo3395a(this.f10088b.getText().toString(), this.f10093g, this.f10094h);
                this.f10088b.setText("");
            }
            if (this.f10096j) {
                m11051b();
            } else {
                m11049a();
            }
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.f10089c.setEnabled(i3 > 0);
    }

    public void afterTextChanged(Editable editable) {
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    /* renamed from: a */
    public void m11049a() {
        this.f10088b.setFocusable(true);
        this.f10088b.setEnabled(true);
        this.f10088b.setFocusableInTouchMode(true);
        this.f10088b.requestFocus();
        this.f10088b.setSelection(0);
        ((InputMethodManager) getContext().getSystemService("input_method")).toggleSoftInputFromWindow(this.f10088b.getWindowToken(), 1, 2);
    }

    /* renamed from: b */
    public void m11051b() {
        this.f10088b.setFocusable(true);
        this.f10088b.setEnabled(true);
        this.f10088b.setSelection(0);
        ((InputMethodManager) getContext().getSystemService("input_method")).toggleSoftInputFromWindow(this.f10088b.getWindowToken(), 1, 2);
    }

    /* renamed from: c */
    public void m11052c() {
        LayoutParams layoutParams = (LayoutParams) this.f10090d.getLayoutParams();
        layoutParams.height = -2;
        this.f10090d.setMinimumHeight((int) this.f10091e.getResources().getDimension(C1373R.dimen.clubfeed_comment_et_height));
        this.f10090d.setLayoutParams(layoutParams);
        this.f10096j = true;
    }

    /* renamed from: d */
    public void m11053d() {
        LayoutParams layoutParams = (LayoutParams) this.f10090d.getLayoutParams();
        layoutParams.height = 0;
        this.f10090d.setLayoutParams(layoutParams);
    }

    /* renamed from: a */
    public void m11050a(int i, int i2) {
        if (this.f10094h != i) {
            this.f10088b.setText("");
        } else if (this.f10093g != i2) {
            this.f10088b.setText("");
        }
        this.f10094h = i;
        this.f10093g = i2;
    }

    public void setTextHint(String str) {
        this.f10095i = str;
        this.f10088b.setHint(str);
    }

    public int getFeedId() {
        return this.f10094h;
    }

    public int getReplyId() {
        return this.f10093g;
    }
}
