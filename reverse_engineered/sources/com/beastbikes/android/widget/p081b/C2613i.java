package com.beastbikes.android.widget.p081b;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import com.beastbikes.framework.ui.android.utils.ViewIntrospector;

/* compiled from: RecyclerViewHolder */
/* renamed from: com.beastbikes.android.widget.b.i */
public abstract class C2613i<T> extends ViewHolder implements OnClickListener, OnLongClickListener {
    /* renamed from: a */
    private View f12208a;

    public C2613i(View view) {
        super(view);
        this.f12208a = view;
        this.f12208a.setTag(this);
        ViewIntrospector.introspect(view, (Object) this);
        this.f12208a.setOnClickListener(this);
        this.f12208a.setOnLongClickListener(this);
    }

    /* renamed from: a */
    public Context m13012a() {
        return this.f12208a.getContext();
    }

    public void onClick(View view) {
    }

    public boolean onLongClick(View view) {
        return false;
    }
}
