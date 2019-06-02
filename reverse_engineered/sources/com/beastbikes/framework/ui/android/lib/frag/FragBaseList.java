package com.beastbikes.framework.ui.android.lib.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.OnFlingListener;

public abstract class FragBaseList<K, D, V extends AbsListView> extends FragPullAbsList<K, D, V> implements OnFlingListener {
    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.pullView.setOnFlingListener(this);
        return onCreateView;
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public boolean onFlingToLeft(float f, float f2, float f3, float f4) {
        return false;
    }

    public boolean onFlingToRight(float f, float f2, float f3, float f4) {
        if (isAdded() && getActivity() != null) {
            getActivity().finish();
        }
        return false;
    }

    public boolean onTouchedAfterFlinged(float f, float f2) {
        return false;
    }
}
