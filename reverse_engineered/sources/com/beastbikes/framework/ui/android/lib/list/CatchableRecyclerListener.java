package com.beastbikes.framework.ui.android.lib.list;

import android.view.View;
import android.widget.AbsListView.RecyclerListener;

public abstract class CatchableRecyclerListener implements RecyclerListener {
    public abstract void intlOnMovedToScrapHeap(View view);

    public void onMovedToScrapHeap(View view) {
        try {
            intlOnMovedToScrapHeap(view);
        } catch (Error e) {
            throw ((Error) e.fillInStackTrace());
        }
    }
}
