package com.beastbikes.android.modules.cycling.sections.ui;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class SectionDetailActivity$4 implements OnTouchListener {
    /* renamed from: a */
    final /* synthetic */ SectionDetailActivity f10588a;

    SectionDetailActivity$4(SectionDetailActivity sectionDetailActivity) {
        this.f10588a = sectionDetailActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            SectionDetailActivity.t(this.f10588a).requestDisallowInterceptTouchEvent(false);
        } else {
            SectionDetailActivity.t(this.f10588a).requestDisallowInterceptTouchEvent(true);
        }
        return false;
    }
}
