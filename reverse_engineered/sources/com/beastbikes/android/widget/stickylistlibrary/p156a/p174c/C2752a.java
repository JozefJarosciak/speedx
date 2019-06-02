package com.beastbikes.android.widget.stickylistlibrary.p156a.p174c;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.beastbikes.android.widget.stickylistlibrary.p156a.p173b.C2750a;
import com.beastbikes.android.widget.stickylistlibrary.p156a.p175d.C2753b;

/* compiled from: HeaderRenderer */
/* renamed from: com.beastbikes.android.widget.stickylistlibrary.a.c.a */
public class C2752a {
    /* renamed from: a */
    private final C2750a f12880a;
    /* renamed from: b */
    private final C2753b f12881b;
    /* renamed from: c */
    private final Rect f12882c;

    public C2752a(C2753b c2753b) {
        this(c2753b, new C2750a());
    }

    private C2752a(C2753b c2753b, C2750a c2750a) {
        this.f12882c = new Rect();
        this.f12881b = c2753b;
        this.f12880a = c2750a;
    }

    /* renamed from: a */
    public void m13638a(RecyclerView recyclerView, Canvas canvas, View view, Rect rect) {
        canvas.save();
        if (recyclerView.getLayoutManager().getClipToPadding()) {
            m13637a(this.f12882c, recyclerView, view);
            canvas.clipRect(this.f12882c);
        }
        canvas.translate((float) rect.left, (float) rect.top);
        view.draw(canvas);
        canvas.restore();
    }

    /* renamed from: a */
    private void m13637a(Rect rect, RecyclerView recyclerView, View view) {
        this.f12880a.m13636a(rect, view);
        if (this.f12881b.mo3555a(recyclerView) == 1) {
            rect.set(recyclerView.getPaddingLeft(), recyclerView.getPaddingTop(), (recyclerView.getWidth() - recyclerView.getPaddingRight()) - rect.right, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            rect.set(recyclerView.getPaddingLeft(), recyclerView.getPaddingTop(), recyclerView.getWidth() - recyclerView.getPaddingRight(), (recyclerView.getHeight() - recyclerView.getPaddingBottom()) - rect.bottom);
        }
    }
}
