package com.digits.sdk.android;

import android.app.Activity;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import org.apache.http.HttpStatus;

/* compiled from: DigitsActivityDelegateImpl */
abstract class ac implements ab {
    ac() {
    }

    /* renamed from: a */
    public void mo3635a() {
    }

    /* renamed from: a */
    public void mo3653a(final Activity activity, final ah ahVar, StateButton stateButton) {
        stateButton.setOnClickListener(new OnClickListener(this) {
            /* renamed from: c */
            final /* synthetic */ ac f13120c;

            public void onClick(View view) {
                ahVar.mo3642d();
                ahVar.mo3660a(activity);
            }
        });
    }

    /* renamed from: a */
    void mo3654a(Activity activity, ah ahVar, ao aoVar, InvertedStateButton invertedStateButton) {
        invertedStateButton.setEnabled(false);
        final ao aoVar2 = aoVar;
        final ah ahVar2 = ahVar;
        final Activity activity2 = activity;
        final InvertedStateButton invertedStateButton2 = invertedStateButton;
        invertedStateButton.setOnClickListener(new OnClickListener(this) {
            /* renamed from: e */
            final /* synthetic */ ac f13125e;

            public void onClick(View view) {
                aoVar2.mo3668a(Element.RESEND);
                ahVar2.mo3642d();
                ahVar2.mo3639a(activity2, invertedStateButton2, Verification.sms);
            }
        });
    }

    /* renamed from: a */
    void mo3655a(Activity activity, ah ahVar, ao aoVar, InvertedStateButton invertedStateButton, AuthConfig authConfig) {
        invertedStateButton.setVisibility(authConfig.isVoiceEnabled ? 0 : 8);
        invertedStateButton.setEnabled(false);
        final ao aoVar2 = aoVar;
        final ah ahVar2 = ahVar;
        final Activity activity2 = activity;
        final InvertedStateButton invertedStateButton2 = invertedStateButton;
        invertedStateButton.setOnClickListener(new OnClickListener(this) {
            /* renamed from: e */
            final /* synthetic */ ac f13130e;

            public void onClick(View view) {
                aoVar2.mo3668a(Element.CALL);
                ahVar2.mo3642d();
                ahVar2.mo3639a(activity2, invertedStateButton2, Verification.voicecall);
            }
        });
    }

    /* renamed from: a */
    void mo3656a(ah ahVar, TextView textView, AuthConfig authConfig) {
        m13909a(textView, authConfig.isVoiceEnabled);
        ahVar.mo3643e();
    }

    /* renamed from: a */
    protected void mo3650a(final Activity activity, LinkTextView linkTextView, String str) {
        linkTextView.setText(str);
        linkTextView.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ ac f13132b;

            public void onClick(View view) {
                activity.setResult(HttpStatus.SC_BAD_REQUEST);
                activity.finish();
            }
        });
    }

    /* renamed from: a */
    public void mo3651a(final Activity activity, final ah ahVar, EditText editText) {
        editText.setOnEditorActionListener(new OnEditorActionListener(this) {
            /* renamed from: c */
            final /* synthetic */ ac f13135c;

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 5) {
                    return false;
                }
                ahVar.mo3642d();
                ahVar.mo3660a(activity);
                return true;
            }
        });
        editText.addTextChangedListener(ahVar.mo3640b());
    }

    /* renamed from: a */
    public void mo3652a(Activity activity, final ah ahVar, TextView textView) {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ ac f13137b;

            public void onClick(View view) {
                ahVar.mo3642d();
            }
        });
    }

    /* renamed from: a */
    public void mo3636a(int i, int i2, Activity activity) {
    }

    /* renamed from: a */
    private void m13909a(TextView textView, boolean z) {
        int i = z ? C2876R.id.dgts__callMeButton : C2876R.id.dgts__resendConfirmationButton;
        LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
        layoutParams.addRule(7, i);
        layoutParams.addRule(8, i);
        textView.setLayoutParams(layoutParams);
    }
}
