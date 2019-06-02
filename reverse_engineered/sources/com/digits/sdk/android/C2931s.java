package com.digits.sdk.android;

import android.content.Context;
import android.content.Intent;

/* compiled from: ContactsControllerImpl */
/* renamed from: com.digits.sdk.android.s */
class C2931s implements C2930q {
    C2931s() {
    }

    /* renamed from: a */
    public void mo3685a(Context context) {
        context.startService(new Intent(context, ContactsUploadService.class));
    }
}
