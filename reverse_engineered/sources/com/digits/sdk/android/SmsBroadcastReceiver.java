package com.digits.sdk.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.EditText;
import java.lang.ref.WeakReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsBroadcastReceiver extends BroadcastReceiver {
    /* renamed from: a */
    final Pattern f13116a = Pattern.compile("\\s(\\d{6}).*Digits by Twitter");
    /* renamed from: b */
    final WeakReference<EditText> f13117b;

    SmsBroadcastReceiver(EditText editText) {
        this.f13117b = new WeakReference(editText);
    }

    public void onReceive(Context context, Intent intent) {
        Object a = m13895a(m13896a(intent));
        if (a != null) {
            EditText editText = (EditText) this.f13117b.get();
            if (editText != null) {
                editText.setText(a);
                editText.setSelection(a.length());
            }
        }
    }

    /* renamed from: a */
    String m13895a(SmsMessage[] smsMessageArr) {
        for (SmsMessage a : smsMessageArr) {
            String a2 = m13894a(a);
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }

    /* renamed from: a */
    String m13894a(SmsMessage smsMessage) {
        CharSequence displayMessageBody = smsMessage.getDisplayMessageBody();
        if (displayMessageBody != null) {
            Matcher matcher = this.f13116a.matcher(displayMessageBody);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    /* renamed from: a */
    SmsMessage[] m13896a(Intent intent) {
        Object[] objArr = (Object[]) intent.getSerializableExtra("pdus");
        int length = objArr.length;
        SmsMessage[] smsMessageArr = new SmsMessage[length];
        for (int i = 0; i < length; i++) {
            smsMessageArr[i] = SmsMessage.createFromPdu((byte[]) objArr[i]);
        }
        return smsMessageArr;
    }
}
