package com.beastbikes.android.modules.cycling.sections.ui;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class SectionDetailActivity$5 implements OnTouchListener {
    /* renamed from: a */
    final /* synthetic */ SectionDetailActivity f10589a;

    SectionDetailActivity$5(SectionDetailActivity sectionDetailActivity) {
        this.f10589a = sectionDetailActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                SectionDetailActivity.t(this.f10589a).requestDisallowInterceptTouchEvent(true);
                return false;
            case 1:
                SectionDetailActivity.t(this.f10589a).requestDisallowInterceptTouchEvent(false);
                return true;
            case 2:
                SectionDetailActivity.t(this.f10589a).requestDisallowInterceptTouchEvent(true);
                return false;
            default:
                return true;
        }
    }
}
