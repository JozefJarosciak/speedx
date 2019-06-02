package com.digits.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.ResultReceiver;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import com.avos.avoscloud.AVException;
import com.twitter.sdk.android.core.C4586l;
import io.fabric.sdk.android.services.common.C4877i;

/* compiled from: DigitsControllerImpl */
abstract class ai implements TextWatcher, ah {
    /* renamed from: a */
    final ag f13175a;
    /* renamed from: b */
    final C2877a f13176b;
    /* renamed from: c */
    final az f13177c;
    /* renamed from: d */
    final ResultReceiver f13178d;
    /* renamed from: e */
    final EditText f13179e;
    /* renamed from: f */
    final StateButton f13180f;
    /* renamed from: g */
    final C4586l<ap> f13181g;
    /* renamed from: h */
    final ao f13182h;
    /* renamed from: i */
    int f13183i = 0;
    /* renamed from: j */
    CountDownTimer f13184j;

    ai(ResultReceiver resultReceiver, StateButton stateButton, EditText editText, ag agVar, az azVar, C2877a c2877a, C4586l<ap> c4586l, ao aoVar) {
        this.f13178d = resultReceiver;
        this.f13175a = agVar;
        this.f13176b = c2877a;
        this.f13180f = stateButton;
        this.f13179e = editText;
        this.f13177c = azVar;
        this.f13181g = c4586l;
        this.f13182h = aoVar;
    }

    /* renamed from: a */
    public void mo3638a(Context context, DigitsException digitsException) {
        this.f13183i++;
        this.f13182h.mo3667a(digitsException);
        if (m13947a(digitsException)) {
            this.f13182h.mo3669b();
            mo3661a(context, this.f13178d, digitsException);
            return;
        }
        this.f13179e.setError(digitsException.getLocalizedMessage());
        this.f13180f.m13882f();
    }

    /* renamed from: a */
    private boolean m13947a(DigitsException digitsException) {
        return this.f13183i == 5 || (digitsException instanceof UnrecoverableException);
    }

    /* renamed from: a */
    void m13951a(Activity activity, Intent intent) {
        activity.startActivityForResult(intent, AVException.EXCEEDED_QUOTA);
    }

    /* renamed from: a */
    public void mo3661a(Context context, ResultReceiver resultReceiver, DigitsException digitsException) {
        Intent intent = new Intent(context, this.f13176b.mo3680d());
        intent.putExtra("receiver", resultReceiver);
        intent.putExtra("fallback_reason", digitsException);
        context.startActivity(intent);
        C4877i.a(context, 200);
    }

    /* renamed from: a */
    public boolean mo3662a(CharSequence charSequence) {
        return !TextUtils.isEmpty(charSequence);
    }

    /* renamed from: d */
    public void mo3642d() {
        this.f13179e.setError(null);
    }

    /* renamed from: c */
    public az mo3641c() {
        return this.f13177c;
    }

    /* renamed from: a */
    public void mo3637a() {
        this.f13180f.m13883g();
    }

    /* renamed from: b */
    public TextWatcher mo3640b() {
        return this;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        mo3642d();
    }

    public void afterTextChanged(Editable editable) {
    }

    /* renamed from: a */
    public void mo3639a(Context context, InvertedStateButton invertedStateButton, Verification verification) {
    }

    /* renamed from: a */
    Bundle m13948a(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("phone_number", str);
        return bundle;
    }

    /* renamed from: a */
    void m13955a(final Context context, ap apVar, final String str) {
        this.f13181g.a(apVar);
        this.f13180f.m13881e();
        this.f13179e.postDelayed(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ ai f13170c;

            public void run() {
                this.f13170c.f13178d.send(200, this.f13170c.m13948a(str));
                C4877i.a((Activity) context, 200);
            }
        }, 1500);
    }

    /* renamed from: a */
    void m13956a(Context context, String str) {
        this.f13180f.m13881e();
        Intent intent = new Intent(context, this.f13176b.mo3682f());
        Bundle a = m13948a(str);
        a.putParcelable("receiver", this.f13178d);
        intent.putExtras(a);
        m13951a((Activity) context, intent);
    }

    /* renamed from: e */
    public void mo3643e() {
        if (this.f13184j != null) {
            this.f13184j.start();
        }
    }

    /* renamed from: f */
    public void mo3644f() {
        if (this.f13184j != null) {
            this.f13184j.cancel();
        }
    }

    /* renamed from: a */
    CountDownTimer m13949a(int i, TextView textView, InvertedStateButton invertedStateButton, InvertedStateButton invertedStateButton2) {
        textView.setText(String.valueOf(15));
        final TextView textView2 = textView;
        final InvertedStateButton invertedStateButton3 = invertedStateButton;
        final InvertedStateButton invertedStateButton4 = invertedStateButton2;
        return new CountDownTimer(this, (long) i, 500) {
            /* renamed from: d */
            final /* synthetic */ ai f13174d;

            public void onTick(long j) {
                textView2.setText(String.valueOf(m13946a((double) j)));
            }

            public void onFinish() {
                textView2.setText("");
                textView2.setEnabled(true);
                invertedStateButton3.setEnabled(true);
                invertedStateButton4.setEnabled(true);
            }

            /* renamed from: a */
            private int m13946a(double d) {
                return (int) Math.ceil(d / 1000.0d);
            }
        };
    }
}
