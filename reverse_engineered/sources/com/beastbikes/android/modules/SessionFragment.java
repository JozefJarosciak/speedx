package com.beastbikes.android.modules;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.avos.avoscloud.AVAnalytics;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.ui.android.BaseFragment;

public class SessionFragment extends BaseFragment {
    /* renamed from: b */
    protected String m9708b() {
        Activity activity = getActivity();
        if (activity != null) {
            Intent intent = activity.getIntent();
            if (intent != null) {
                Object stringExtra = intent.getStringExtra("user_id");
                if (!TextUtils.isEmpty(stringExtra)) {
                    return stringExtra;
                }
            }
        }
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            return null;
        }
        return currentUser.getObjectId();
    }

    public void onPause() {
        super.onPause();
        C1457a c1457a = (C1457a) getClass().getAnnotation(C1457a.class);
        if (c1457a != null) {
            AVAnalytics.onFragmentEnd(c1457a.a());
        }
    }

    public void onResume() {
        super.onResume();
        C1457a c1457a = (C1457a) getClass().getAnnotation(C1457a.class);
        if (c1457a != null) {
            AVAnalytics.onFragmentStart(c1457a.a());
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
