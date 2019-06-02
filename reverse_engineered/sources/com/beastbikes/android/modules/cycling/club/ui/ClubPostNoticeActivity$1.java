package com.beastbikes.android.modules.cycling.club.ui;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class ClubPostNoticeActivity$1 implements OnEditorActionListener {
    /* renamed from: a */
    final /* synthetic */ ClubPostNoticeActivity f9784a;

    ClubPostNoticeActivity$1(ClubPostNoticeActivity clubPostNoticeActivity) {
        this.f9784a = clubPostNoticeActivity;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 66;
    }
}
