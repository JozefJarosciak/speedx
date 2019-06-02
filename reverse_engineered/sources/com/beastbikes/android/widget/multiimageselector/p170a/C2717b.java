package com.beastbikes.android.widget.multiimageselector.p170a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.multiimageselector.p171b.C2719b;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: ImageGridAdapter */
/* renamed from: com.beastbikes.android.widget.multiimageselector.a.b */
public class C2717b extends BaseAdapter {
    /* renamed from: a */
    private Context f12683a;
    /* renamed from: b */
    private LayoutInflater f12684b;
    /* renamed from: c */
    private boolean f12685c = true;
    /* renamed from: d */
    private boolean f12686d = true;
    /* renamed from: e */
    private List<C2719b> f12687e = new ArrayList();
    /* renamed from: f */
    private List<C2719b> f12688f = new ArrayList();
    /* renamed from: g */
    private int f12689g;
    /* renamed from: h */
    private LayoutParams f12690h;

    /* compiled from: ImageGridAdapter */
    /* renamed from: com.beastbikes.android.widget.multiimageselector.a.b$a */
    class C2716a {
        /* renamed from: a */
        ImageView f12679a;
        /* renamed from: b */
        ImageView f12680b;
        /* renamed from: c */
        View f12681c;
        /* renamed from: d */
        final /* synthetic */ C2717b f12682d;

        C2716a(C2717b c2717b, View view) {
            this.f12682d = c2717b;
            this.f12679a = (ImageView) view.findViewById(C1373R.id.image);
            this.f12680b = (ImageView) view.findViewById(C1373R.id.checkmark);
            this.f12681c = view.findViewById(C1373R.id.mask);
            view.setTag(this);
        }

        /* renamed from: a */
        void m13391a(C2719b c2719b) {
            if (c2719b != null) {
                if (this.f12682d.f12686d) {
                    this.f12680b.setVisibility(0);
                    if (this.f12682d.f12688f.contains(c2719b)) {
                        this.f12680b.setImageResource(C1373R.drawable.multi_image_selector_btn_selected);
                        this.f12681c.setVisibility(0);
                    } else {
                        this.f12680b.setImageResource(C1373R.drawable.multi_image_selector_btn_unselected);
                        this.f12681c.setVisibility(8);
                    }
                } else {
                    this.f12680b.setVisibility(8);
                }
                File file = new File(c2719b.f12695a);
                if (this.f12682d.f12689g > 0) {
                    Picasso.with(this.f12682d.f12683a).load(file).placeholder(C1373R.drawable.multi_image_selector_default_error).resize(this.f12682d.f12689g, this.f12682d.f12689g).centerCrop().into(this.f12679a);
                }
            }
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return m13403b(i);
    }

    public C2717b(Context context, boolean z) {
        this.f12683a = context;
        this.f12684b = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f12685c = z;
        this.f12690h = new LayoutParams(-1, -1);
    }

    /* renamed from: a */
    public void m13401a(boolean z) {
        this.f12686d = z;
    }

    /* renamed from: b */
    public void m13404b(boolean z) {
        if (this.f12685c != z) {
            this.f12685c = z;
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public boolean m13402a() {
        return this.f12685c;
    }

    /* renamed from: a */
    public void m13398a(C2719b c2719b) {
        if (this.f12688f.contains(c2719b)) {
            this.f12688f.remove(c2719b);
        } else {
            this.f12688f.add(c2719b);
        }
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m13399a(ArrayList<String> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C2719b a = m13392a((String) it.next());
            if (a != null) {
                this.f12688f.add(a);
            }
        }
        if (this.f12688f.size() > 0) {
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    private C2719b m13392a(String str) {
        if (this.f12687e != null && this.f12687e.size() > 0) {
            for (C2719b c2719b : this.f12687e) {
                if (c2719b.f12695a.equalsIgnoreCase(str)) {
                    return c2719b;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public void m13400a(List<C2719b> list) {
        this.f12688f.clear();
        if (list == null || list.size() <= 0) {
            this.f12687e.clear();
        } else {
            this.f12687e = list;
        }
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m13397a(int i) {
        if (this.f12689g != i) {
            this.f12689g = i;
            this.f12690h = new LayoutParams(this.f12689g, this.f12689g);
            notifyDataSetChanged();
        }
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getItemViewType(int i) {
        if (this.f12685c && i == 0) {
            return 0;
        }
        return 1;
    }

    public int getCount() {
        return this.f12685c ? this.f12687e.size() + 1 : this.f12687e.size();
    }

    /* renamed from: b */
    public C2719b m13403b(int i) {
        if (!this.f12685c) {
            return (C2719b) this.f12687e.get(i);
        }
        if (i == 0) {
            return null;
        }
        return (C2719b) this.f12687e.get(i - 1);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 0) {
            view = this.f12684b.inflate(C1373R.layout.multi_image_selector_list_item_camera, viewGroup, false);
            view.setTag(null);
        } else if (itemViewType == 1) {
            C2716a c2716a;
            if (view == null) {
                view = this.f12684b.inflate(C1373R.layout.multi_image_selector_list_item_image, viewGroup, false);
                c2716a = new C2716a(this, view);
            } else {
                c2716a = (C2716a) view.getTag();
                if (c2716a == null) {
                    view = this.f12684b.inflate(C1373R.layout.multi_image_selector_list_item_image, viewGroup, false);
                    c2716a = new C2716a(this, view);
                }
            }
            if (c2716a != null) {
                c2716a.m13391a(m13403b(i));
            }
        }
        if (((LayoutParams) view.getLayoutParams()).height != this.f12689g) {
            view.setLayoutParams(this.f12690h);
        }
        return view;
    }
}
