package com.beastbikes.android.widget.stickylistlibrary.stickygridheaders;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.common.primitives.Ints;

/* compiled from: StickyGridHeadersBaseAdapterWrapper */
/* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.b */
public class C2771b extends BaseAdapter {
    /* renamed from: a */
    private final Context f12950a;
    /* renamed from: b */
    private int f12951b;
    /* renamed from: c */
    private boolean f12952c = false;
    /* renamed from: d */
    private DataSetObserver f12953d = new C27671(this);
    /* renamed from: e */
    private final C2766a f12954e;
    /* renamed from: f */
    private StickyGridHeadersGridView f12955f;
    /* renamed from: g */
    private View f12956g;
    /* renamed from: h */
    private View f12957h;
    /* renamed from: i */
    private int f12958i = 1;

    /* compiled from: StickyGridHeadersBaseAdapterWrapper */
    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.b$1 */
    class C27671 extends DataSetObserver {
        /* renamed from: a */
        final /* synthetic */ C2771b f12942a;

        C27671(C2771b c2771b) {
            this.f12942a = c2771b;
        }

        public void onChanged() {
            this.f12942a.m13680a();
        }

        public void onInvalidated() {
            this.f12942a.f12952c = false;
        }
    }

    /* compiled from: StickyGridHeadersBaseAdapterWrapper */
    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.b$a */
    protected class C2768a extends View {
        /* renamed from: a */
        final /* synthetic */ C2771b f12943a;
        /* renamed from: b */
        private View f12944b;

        public C2768a(C2771b c2771b, Context context) {
            this.f12943a = c2771b;
            super(context);
        }

        public void setMeasureTarget(View view) {
            this.f12944b = view;
        }

        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, MeasureSpec.makeMeasureSpec(this.f12944b.getMeasuredHeight(), Ints.MAX_POWER_OF_TWO));
        }
    }

    /* compiled from: StickyGridHeadersBaseAdapterWrapper */
    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.b$b */
    public static class C2769b extends FrameLayout {
        /* renamed from: a */
        private int f12945a;
        /* renamed from: b */
        private StickyGridHeadersGridView f12946b;

        public C2769b(Context context) {
            super(context);
        }

        public C2769b(Context context, StickyGridHeadersGridView stickyGridHeadersGridView) {
            super(context);
            this.f12946b = stickyGridHeadersGridView;
        }

        public C2769b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public C2769b(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        public int getHeaderId() {
            return this.f12945a;
        }

        public void setHeaderId(int i) {
            this.f12945a = i;
        }

        protected LayoutParams generateDefaultLayoutParams() {
            return new LayoutParams(-1, -1);
        }

        protected void onMeasure(int i, int i2) {
            View view = (View) getTag();
            if (view != null) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = generateDefaultLayoutParams();
                    view.setLayoutParams(layoutParams);
                }
                if (view.getVisibility() != 8) {
                    view.measure(C2769b.getChildMeasureSpec(MeasureSpec.makeMeasureSpec(this.f12946b.getWidth(), Ints.MAX_POWER_OF_TWO), 0, layoutParams.width), C2769b.getChildMeasureSpec(MeasureSpec.makeMeasureSpec(0, 0), 0, layoutParams.height));
                }
                setMeasuredDimension(MeasureSpec.getSize(i), view.getMeasuredHeight());
            }
        }
    }

    /* compiled from: StickyGridHeadersBaseAdapterWrapper */
    /* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.b$c */
    protected class C2770c {
        /* renamed from: a */
        protected int f12947a;
        /* renamed from: b */
        protected int f12948b;
        /* renamed from: c */
        final /* synthetic */ C2771b f12949c;

        protected C2770c(C2771b c2771b, int i, int i2) {
            this.f12949c = c2771b;
            this.f12948b = i;
            this.f12947a = i2;
        }
    }

    public C2771b(Context context, StickyGridHeadersGridView stickyGridHeadersGridView, C2766a c2766a) {
        this.f12950a = context;
        this.f12954e = c2766a;
        this.f12955f = stickyGridHeadersGridView;
        c2766a.registerDataSetObserver(this.f12953d);
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public int getCount() {
        int i = 0;
        if (this.f12952c) {
            return this.f12951b;
        }
        this.f12951b = 0;
        int a = this.f12954e.mo3557a();
        if (a == 0) {
            this.f12951b = this.f12954e.getCount();
            this.f12952c = true;
            return this.f12951b;
        }
        while (i < a) {
            this.f12951b += (this.f12954e.mo3558a(i) + m13678d(i)) + this.f12958i;
            i++;
        }
        this.f12952c = true;
        return this.f12951b;
    }

    public Object getItem(int i) throws ArrayIndexOutOfBoundsException {
        C2770c c = m13683c(i);
        if (c.f12948b == -1 || c.f12948b == -2) {
            return null;
        }
        return this.f12954e.getItem(c.f12948b);
    }

    public long getItemId(int i) {
        C2770c c = m13683c(i);
        if (c.f12948b == -2) {
            return -1;
        }
        if (c.f12948b == -1) {
            return -2;
        }
        if (c.f12948b == -3) {
            return -3;
        }
        return this.f12954e.getItemId(c.f12948b);
    }

    public int getItemViewType(int i) {
        C2770c c = m13683c(i);
        if (c.f12948b == -2) {
            return 1;
        }
        if (c.f12948b == -1) {
            return 0;
        }
        if (c.f12948b == -3) {
            return 2;
        }
        int itemViewType = this.f12954e.getItemViewType(c.f12948b);
        return itemViewType != -1 ? itemViewType + 3 : itemViewType;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C2770c c = m13683c(i);
        if (c.f12948b == -2) {
            View b = m13677b(c.f12947a, view, viewGroup);
            View a = this.f12954e.mo3559a(c.f12947a, (View) b.getTag(), viewGroup);
            this.f12955f.m13670b((View) b.getTag());
            b.setTag(a);
            this.f12955f.m13668a(a);
            this.f12956g = b;
            b.forceLayout();
            return b;
        } else if (c.f12948b == -3) {
            r0 = m13675a(view, viewGroup, this.f12956g);
            r0.forceLayout();
            return r0;
        } else if (c.f12948b == -1) {
            return m13675a(view, viewGroup, this.f12957h);
        } else {
            r0 = this.f12954e.getView(c.f12948b, view, viewGroup);
            this.f12957h = r0;
            return r0;
        }
    }

    public int getViewTypeCount() {
        return this.f12954e.getViewTypeCount() + 3;
    }

    public boolean hasStableIds() {
        return this.f12954e.hasStableIds();
    }

    public boolean isEmpty() {
        return this.f12954e.isEmpty();
    }

    public boolean isEnabled(int i) {
        C2770c c = m13683c(i);
        if (c.f12948b == -1 || c.f12948b == -2) {
            return false;
        }
        return this.f12954e.isEnabled(c.f12948b);
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        super.registerDataSetObserver(dataSetObserver);
        this.f12954e.registerDataSetObserver(dataSetObserver);
    }

    /* renamed from: a */
    public void m13681a(int i) {
        this.f12958i = i;
        this.f12952c = false;
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        super.unregisterDataSetObserver(dataSetObserver);
        this.f12954e.unregisterDataSetObserver(dataSetObserver);
    }

    /* renamed from: a */
    private C2768a m13675a(View view, ViewGroup viewGroup, View view2) {
        view = (C2768a) view;
        if (view == null) {
            view = new C2768a(this, this.f12950a);
        }
        view.setMeasureTarget(view2);
        return view;
    }

    /* renamed from: b */
    private C2769b m13677b(int i, View view, ViewGroup viewGroup) {
        C2769b c2769b = (C2769b) view;
        if (c2769b == null) {
            return new C2769b(this.f12950a, this.f12955f);
        }
        return c2769b;
    }

    /* renamed from: d */
    private int m13678d(int i) {
        if (this.f12958i == 0) {
            return 0;
        }
        int a = this.f12954e.mo3558a(i) % this.f12958i;
        if (a != 0) {
            return this.f12958i - a;
        }
        return 0;
    }

    /* renamed from: b */
    protected long m13682b(int i) {
        return (long) m13683c(i).f12947a;
    }

    /* renamed from: a */
    protected View m13679a(int i, View view, ViewGroup viewGroup) {
        if (this.f12954e.mo3557a() == 0) {
            return null;
        }
        return this.f12954e.mo3559a(m13683c(i).f12947a, view, viewGroup);
    }

    /* renamed from: c */
    protected C2770c m13683c(int i) {
        int i2 = 0;
        int a = this.f12954e.mo3557a();
        if (a != 0) {
            int i3 = i;
            while (i2 < a) {
                int a2 = this.f12954e.mo3558a(i2);
                if (i == 0) {
                    return new C2770c(this, -2, i2);
                }
                int i4 = i - this.f12958i;
                if (i4 < 0) {
                    return new C2770c(this, -3, i2);
                }
                int i5 = i3 - this.f12958i;
                if (i4 < a2) {
                    return new C2770c(this, i5, i2);
                }
                i3 = m13678d(i2);
                i5 -= i3;
                i = i4 - (i3 + a2);
                if (i < 0) {
                    return new C2770c(this, -1, i2);
                }
                i2++;
                i3 = i5;
            }
            return new C2770c(this, -1, i2);
        } else if (i >= this.f12954e.getCount()) {
            return new C2770c(this, -1, 0);
        } else {
            return new C2770c(this, i, 0);
        }
    }

    /* renamed from: a */
    protected void m13680a() {
        int i = 0;
        this.f12951b = 0;
        int a = this.f12954e.mo3557a();
        if (a == 0) {
            this.f12951b = this.f12954e.getCount();
            this.f12952c = true;
            return;
        }
        while (i < a) {
            this.f12951b += this.f12954e.mo3558a(i) + this.f12958i;
            i++;
        }
        this.f12952c = true;
    }
}
