package com.beastbikes.android.widget.p081b;

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;

/* compiled from: SimpleItemTouchHelperCallback */
/* renamed from: com.beastbikes.android.widget.b.j */
public class C2614j extends Callback {
    /* renamed from: a */
    private final C1701d f12209a;

    public C2614j(C1701d c1701d) {
        this.f12209a = c1701d;
    }

    public boolean isLongPressDragEnabled() {
        return true;
    }

    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            return C2614j.makeMovementFlags(15, 0);
        }
        if (!(recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
            return C2614j.makeMovementFlags(3, 0);
        }
        int i;
        if (((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation() == 1) {
            i = 3;
        } else {
            i = 12;
        }
        return C2614j.makeMovementFlags(i, 0);
    }

    public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder viewHolder2) {
        if (viewHolder.getItemViewType() != viewHolder2.getItemViewType()) {
            return false;
        }
        this.f12209a.mo3217a(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        return true;
    }

    public void onSwiped(ViewHolder viewHolder, int i) {
        this.f12209a.mo3216a(viewHolder.getAdapterPosition());
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        if (i == 1) {
            viewHolder.itemView.setAlpha(1.0f - (Math.abs(f) / ((float) viewHolder.itemView.getWidth())));
            viewHolder.itemView.setTranslationX(f);
            return;
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
    }

    public void onSelectedChanged(ViewHolder viewHolder, int i) {
        if (i != 0 && (viewHolder instanceof C1925e)) {
            ((C1925e) viewHolder).mo3289a();
        }
        super.onSelectedChanged(viewHolder, i);
    }

    public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setAlpha(1.0f);
        if (viewHolder instanceof C1925e) {
            ((C1925e) viewHolder).mo3290b();
        }
    }
}
