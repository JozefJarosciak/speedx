package com.beastbikes.android.authentication.ui;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class AuthenticationActivity$1 implements OnTouchListener {
    /* renamed from: a */
    final /* synthetic */ AuthenticationActivity f7274a;

    AuthenticationActivity$1(AuthenticationActivity authenticationActivity) {
        this.f7274a = authenticationActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        Drawable drawable = AuthenticationActivity.a(this.f7274a).getCompoundDrawables()[2];
        if (drawable != null && motionEvent.getAction() == 1 && motionEvent.getX() > ((float) ((AuthenticationActivity.a(this.f7274a).getWidth() - AuthenticationActivity.a(this.f7274a).getPaddingRight()) - drawable.getIntrinsicWidth()))) {
            AuthenticationActivity.a(this.f7274a).setText("");
        }
        return false;
    }
}
