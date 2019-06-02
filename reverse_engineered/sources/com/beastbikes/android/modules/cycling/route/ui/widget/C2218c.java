package com.beastbikes.android.modules.cycling.route.ui.widget;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;

/* compiled from: DragSortItemViewCheckable */
/* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.c */
public class C2218c extends C2217b implements Checkable {
    public C2218c(Context context) {
        super(context);
    }

    public boolean isChecked() {
        View childAt = getChildAt(0);
        if (childAt instanceof Checkable) {
            return ((Checkable) childAt).isChecked();
        }
        return false;
    }

    public void setChecked(boolean z) {
        View childAt = getChildAt(0);
        if (childAt instanceof Checkable) {
            ((Checkable) childAt).setChecked(z);
        }
    }

    public void toggle() {
        View childAt = getChildAt(0);
        if (childAt instanceof Checkable) {
            ((Checkable) childAt).toggle();
        }
    }
}
