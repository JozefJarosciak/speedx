package com.beastbikes.framework.ui.android.lib.list;

import android.view.View;
import android.view.ViewGroup.OnHierarchyChangeListener;

public abstract class CatchableOnHierarchyChangeListener implements OnHierarchyChangeListener {
    public abstract void intlOnChildViewAdded(View view, View view2);

    public abstract void intlOnChildViewRemoved(View view, View view2);

    public void onChildViewAdded(View view, View view2) {
        try {
            intlOnChildViewAdded(view, view2);
        } catch (Error e) {
            throw ((Error) e.fillInStackTrace());
        }
    }

    public void onChildViewRemoved(View view, View view2) {
        try {
            intlOnChildViewRemoved(view, view2);
        } catch (Error e) {
            throw ((Error) e.fillInStackTrace());
        }
    }
}
