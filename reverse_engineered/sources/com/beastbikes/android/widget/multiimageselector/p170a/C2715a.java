package com.beastbikes.android.widget.multiimageselector.p170a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.multiimageselector.p171b.C2718a;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: FolderAdapter */
/* renamed from: com.beastbikes.android.widget.multiimageselector.a.a */
public class C2715a extends BaseAdapter {
    /* renamed from: a */
    int f12674a;
    /* renamed from: b */
    int f12675b = 0;
    /* renamed from: c */
    private Context f12676c;
    /* renamed from: d */
    private LayoutInflater f12677d;
    /* renamed from: e */
    private List<C2718a> f12678e = new ArrayList();

    /* compiled from: FolderAdapter */
    /* renamed from: com.beastbikes.android.widget.multiimageselector.a.a$a */
    class C2714a {
        /* renamed from: a */
        ImageView f12669a;
        /* renamed from: b */
        TextView f12670b;
        /* renamed from: c */
        TextView f12671c;
        /* renamed from: d */
        ImageView f12672d;
        /* renamed from: e */
        final /* synthetic */ C2715a f12673e;

        C2714a(C2715a c2715a, View view) {
            this.f12673e = c2715a;
            this.f12669a = (ImageView) view.findViewById(C1373R.id.cover);
            this.f12670b = (TextView) view.findViewById(C1373R.id.name);
            this.f12671c = (TextView) view.findViewById(C1373R.id.size);
            this.f12672d = (ImageView) view.findViewById(C1373R.id.indicator);
            view.setTag(this);
        }

        /* renamed from: a */
        void m13384a(C2718a c2718a) {
            this.f12670b.setText(c2718a.f12691a);
            this.f12671c.setText(c2718a.f12694d.size() + "张");
            Picasso.with(this.f12673e.f12676c).load(new File(c2718a.f12693c.f12695a)).placeholder(C1373R.drawable.multi_image_selector_default_error).resize(this.f12673e.f12674a, this.f12673e.f12674a).centerCrop().into(this.f12669a);
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return m13388a(i);
    }

    public C2715a(Context context) {
        this.f12676c = context;
        this.f12677d = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f12674a = this.f12676c.getResources().getDimensionPixelOffset(C1373R.dimen.folder_cover_size);
    }

    /* renamed from: a */
    public void m13389a(List<C2718a> list) {
        if (list == null || list.size() <= 0) {
            this.f12678e.clear();
        } else {
            this.f12678e = list;
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.f12678e.size() + 1;
    }

    /* renamed from: a */
    public C2718a m13388a(int i) {
        if (i == 0) {
            return null;
        }
        return (C2718a) this.f12678e.get(i - 1);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C2714a c2714a;
        if (view == null) {
            view = this.f12677d.inflate(C1373R.layout.multi_image_selector_list_item_folder, viewGroup, false);
            c2714a = new C2714a(this, view);
        } else {
            c2714a = (C2714a) view.getTag();
        }
        if (c2714a != null) {
            if (i == 0) {
                c2714a.f12670b.setText("所有图片");
                c2714a.f12671c.setText(m13386b() + "张");
                if (this.f12678e.size() > 0) {
                    Picasso.with(this.f12676c).load(new File(((C2718a) this.f12678e.get(0)).f12693c.f12695a)).error(C1373R.drawable.multi_image_selector_default_error).resize(this.f12674a, this.f12674a).centerCrop().into(c2714a.f12669a);
                }
            } else {
                c2714a.m13384a(m13388a(i));
            }
            if (this.f12675b == i) {
                c2714a.f12672d.setVisibility(0);
            } else {
                c2714a.f12672d.setVisibility(4);
            }
        }
        return view;
    }

    /* renamed from: b */
    private int m13386b() {
        if (this.f12678e == null || this.f12678e.size() <= 0) {
            return 0;
        }
        int i = 0;
        for (C2718a c2718a : this.f12678e) {
            i = c2718a.f12694d.size() + i;
        }
        return i;
    }

    /* renamed from: b */
    public void m13390b(int i) {
        if (this.f12675b != i) {
            this.f12675b = i;
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public int m13387a() {
        return this.f12675b;
    }
}
