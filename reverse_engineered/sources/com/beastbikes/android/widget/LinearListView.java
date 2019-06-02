package com.beastbikes.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.p164c.C2588a;

public class LinearListView extends C2588a {
    /* renamed from: c */
    private static final int[] f12092c = new int[]{16842930, C1373R.attr.dividerThickness};
    /* renamed from: d */
    private View f12093d;
    /* renamed from: e */
    private ListAdapter f12094e;
    /* renamed from: f */
    private boolean f12095f;
    /* renamed from: g */
    private C2126b f12096g;
    /* renamed from: h */
    private DataSetObserver f12097h;

    /* renamed from: com.beastbikes.android.widget.LinearListView$b */
    public interface C2126b {
        /* renamed from: a */
        void mo3418a(LinearListView linearListView, View view, int i, long j);
    }

    /* renamed from: com.beastbikes.android.widget.LinearListView$1 */
    class C25861 extends DataSetObserver {
        /* renamed from: a */
        final /* synthetic */ LinearListView f12080a;

        C25861(LinearListView linearListView) {
            this.f12080a = linearListView;
        }

        public void onChanged() {
            this.f12080a.m12928a();
        }

        public void onInvalidated() {
            this.f12080a.m12928a();
        }
    }

    /* renamed from: com.beastbikes.android.widget.LinearListView$a */
    private class C2587a implements OnClickListener {
        /* renamed from: a */
        int f12081a;
        /* renamed from: b */
        final /* synthetic */ LinearListView f12082b;

        public C2587a(LinearListView linearListView, int i) {
            this.f12082b = linearListView;
            this.f12081a = i;
        }

        public void onClick(View view) {
            if (this.f12082b.f12096g != null && this.f12082b.f12094e != null) {
                this.f12082b.f12096g.mo3418a(this.f12082b, view, this.f12081a, this.f12082b.f12094e.getItemId(this.f12081a));
            }
        }
    }

    public LinearListView(Context context) {
        this(context, null);
    }

    public LinearListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f12097h = new C25861(this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f12092c);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            setDividerThickness(dimensionPixelSize);
        }
        CharSequence[] textArray = obtainStyledAttributes.getTextArray(0);
        if (textArray != null) {
            setAdapter(new ArrayAdapter(context, 17367043, textArray));
        }
        obtainStyledAttributes.recycle();
    }

    public void setOrientation(int i) {
        if (i != getOrientation()) {
            int i2 = this.b;
            this.b = this.a;
            this.a = i2;
        }
        super.setOrientation(i);
    }

    public void setDividerThickness(int i) {
        if (getOrientation() == 1) {
            this.b = i;
        } else {
            this.a = i;
        }
        requestLayout();
    }

    public ListAdapter getAdapter() {
        return this.f12094e;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.f12094e != null) {
            this.f12094e.unregisterDataSetObserver(this.f12097h);
        }
        this.f12094e = listAdapter;
        if (this.f12094e != null) {
            this.f12094e.registerDataSetObserver(this.f12097h);
            this.f12095f = this.f12094e.areAllItemsEnabled();
        }
        m12928a();
    }

    public void setOnItemClickListener(C2126b c2126b) {
        this.f12096g = c2126b;
    }

    public final C2126b getOnItemClickListener() {
        return this.f12096g;
    }

    public void setEmptyView(View view) {
        this.f12093d = view;
        ListAdapter adapter = getAdapter();
        boolean z = adapter == null || adapter.isEmpty();
        m12930a(z);
    }

    public View getEmptyView() {
        return this.f12093d;
    }

    /* renamed from: a */
    private void m12930a(boolean z) {
        if (!z) {
            if (this.f12093d != null) {
                this.f12093d.setVisibility(8);
            }
            setVisibility(0);
        } else if (this.f12093d != null) {
            this.f12093d.setVisibility(0);
            setVisibility(8);
        } else {
            setVisibility(0);
        }
    }

    /* renamed from: a */
    private void m12928a() {
        int i = 0;
        removeAllViews();
        boolean z = this.f12094e == null || this.f12094e.isEmpty();
        m12930a(z);
        if (this.f12094e != null) {
            while (i < this.f12094e.getCount()) {
                View view = this.f12094e.getView(i, null, this);
                if (this.f12095f || this.f12094e.isEnabled(i)) {
                    view.setOnClickListener(new C2587a(this, i));
                }
                if (view != null) {
                    addViewInLayout(view, -1, view.getLayoutParams(), true);
                }
                i++;
            }
        }
    }
}
