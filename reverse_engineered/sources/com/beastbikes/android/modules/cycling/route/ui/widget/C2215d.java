package com.beastbikes.android.modules.cycling.route.ui.widget;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView.C2210i;

/* compiled from: SimpleFloatViewManager */
/* renamed from: com.beastbikes.android.modules.cycling.route.ui.widget.d */
public class C2215d implements C2210i {
    /* renamed from: a */
    private Bitmap f10456a;
    /* renamed from: b */
    private ImageView f10457b;
    /* renamed from: c */
    private int f10458c = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: d */
    private ListView f10459d;

    public C2215d(ListView listView) {
        this.f10459d = listView;
    }

    /* renamed from: f */
    public void m11379f(int i) {
        this.f10458c = i;
    }

    /* renamed from: e */
    public View mo3430e(int i) {
        View childAt = this.f10459d.getChildAt((this.f10459d.getHeaderViewsCount() + i) - this.f10459d.getFirstVisiblePosition());
        if (childAt == null) {
            return null;
        }
        childAt.setPressed(false);
        childAt.setDrawingCacheEnabled(true);
        this.f10456a = Bitmap.createBitmap(childAt.getDrawingCache());
        childAt.setDrawingCacheEnabled(false);
        if (this.f10457b == null) {
            this.f10457b = new ImageView(this.f10459d.getContext());
        }
        this.f10457b.setBackgroundColor(this.f10458c);
        this.f10457b.setPadding(0, 0, 0, 0);
        this.f10457b.setImageBitmap(this.f10456a);
        this.f10457b.setLayoutParams(new LayoutParams(childAt.getWidth(), childAt.getHeight()));
        return this.f10457b;
    }

    /* renamed from: a */
    public void mo3429a(View view, Point point, Point point2) {
    }

    /* renamed from: a */
    public void mo3428a(View view) {
        ((ImageView) view).setImageDrawable(null);
        this.f10456a.recycle();
        this.f10456a = null;
    }
}
