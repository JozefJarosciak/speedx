package com.beastbikes.android.modules.user.ui.binding.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.util.ArrayList;

/* compiled from: GroupListView */
/* renamed from: com.beastbikes.android.modules.user.ui.binding.widget.d */
public class C2520d extends RelativeLayout {
    /* renamed from: a */
    private ListView f11928a;
    /* renamed from: b */
    private C2519b f11929b;
    /* renamed from: c */
    private C2514a f11930c;
    /* renamed from: d */
    private View f11931d;
    /* renamed from: e */
    private int f11932e;
    /* renamed from: f */
    private int f11933f;
    /* renamed from: g */
    private OnScrollListener f11934g;
    /* renamed from: h */
    private C2508c f11935h;

    /* compiled from: GroupListView */
    /* renamed from: com.beastbikes.android.modules.user.ui.binding.widget.d$c */
    public interface C2508c {
        /* renamed from: a */
        void mo3512a(C2520d c2520d, View view, int i, int i2);
    }

    /* compiled from: GroupListView */
    /* renamed from: com.beastbikes.android.modules.user.ui.binding.widget.d$a */
    public static abstract class C2514a {
        /* renamed from: a */
        protected final C2520d f11917a;

        /* renamed from: a */
        public abstract int mo3513a();

        /* renamed from: a */
        public abstract int mo3514a(int i);

        /* renamed from: a */
        public abstract View mo3515a(int i, int i2, View view, ViewGroup viewGroup);

        /* renamed from: a */
        public abstract View mo3516a(int i, View view, ViewGroup viewGroup);

        /* renamed from: a */
        public abstract void mo3517a(View view, String str);

        /* renamed from: b */
        public abstract Object mo3518b(int i, int i2);

        /* renamed from: b */
        public abstract String mo3519b(int i);

        public C2514a(C2520d c2520d) {
            this.f11917a = c2520d;
        }
    }

    /* compiled from: GroupListView */
    /* renamed from: com.beastbikes.android.modules.user.ui.binding.widget.d$1 */
    class C25171 implements OnScrollListener {
        /* renamed from: a */
        final /* synthetic */ C2520d f11922a;

        C25171(C2520d c2520d) {
            this.f11922a = c2520d;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (this.f11922a.f11934g != null) {
                this.f11922a.f11934g.onScrollStateChanged(absListView, i);
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            this.f11922a.f11932e = i;
            if (this.f11922a.f11931d != null) {
                this.f11922a.m12656b();
            }
            if (this.f11922a.f11934g != null) {
                this.f11922a.f11934g.onScroll(absListView, i, i2, i3);
            }
        }
    }

    /* compiled from: GroupListView */
    /* renamed from: com.beastbikes.android.modules.user.ui.binding.widget.d$2 */
    class C25182 implements OnItemClickListener {
        /* renamed from: a */
        final /* synthetic */ C2520d f11923a;

        C25182(C2520d c2520d) {
            this.f11923a = c2520d;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.f11923a.f11935h != null) {
                int a = this.f11923a.f11929b.m12648a(i);
                this.f11923a.f11935h.mo3512a(this.f11923a, view, a, (i - ((Integer) this.f11923a.f11929b.f11926c.get(a)).intValue()) - 1);
            }
        }
    }

    /* compiled from: GroupListView */
    /* renamed from: com.beastbikes.android.modules.user.ui.binding.widget.d$b */
    private static class C2519b extends BaseAdapter {
        /* renamed from: a */
        private C2514a f11924a;
        /* renamed from: b */
        private ArrayList<Object> f11925b = new ArrayList();
        /* renamed from: c */
        private ArrayList<Integer> f11926c = new ArrayList();
        /* renamed from: d */
        private ArrayList<Integer> f11927d = new ArrayList();

        public C2519b(C2514a c2514a) {
            this.f11924a = c2514a;
            m12647a();
        }

        /* renamed from: a */
        private void m12647a() {
            this.f11925b.clear();
            this.f11926c.clear();
            this.f11927d.clear();
            int a = this.f11924a.mo3513a();
            for (int i = 0; i < a; i++) {
                int a2 = this.f11924a.mo3514a(i);
                if (a2 > 0) {
                    this.f11926c.add(Integer.valueOf(this.f11925b.size()));
                    this.f11925b.add(this.f11924a.mo3519b(i));
                    for (int i2 = 0; i2 < a2; i2++) {
                        this.f11925b.add(this.f11924a.mo3518b(i, i2));
                    }
                    this.f11927d.add(Integer.valueOf(this.f11925b.size() - 1));
                }
            }
        }

        public int getCount() {
            return this.f11925b.size();
        }

        public Object getItem(int i) {
            return this.f11925b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        /* renamed from: a */
        public int m12648a(int i) {
            int size = this.f11926c.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (i < ((Integer) this.f11926c.get(i2)).intValue()) {
                    return i2 - 1;
                }
            }
            return size - 1;
        }

        /* renamed from: b */
        public boolean m12649b(int i) {
            int size = this.f11926c.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((Integer) this.f11926c.get(i2)).intValue() == i) {
                    return true;
                }
            }
            return false;
        }

        public int getViewTypeCount() {
            return 2;
        }

        public int getItemViewType(int i) {
            return m12649b(i) ? 0 : 1;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int a = m12648a(i);
            if (!m12649b(i)) {
                return this.f11924a.mo3515a(a, (i - ((Integer) this.f11926c.get(a)).intValue()) - 1, view, viewGroup);
            } else if (view != null) {
                return this.f11924a.mo3516a(a, view, viewGroup);
            } else {
                return this.f11924a.mo3516a(a, null, viewGroup);
            }
        }

        public void notifyDataSetChanged() {
            m12647a();
            super.notifyDataSetChanged();
        }

        /* renamed from: c */
        public boolean m12650c(int i) {
            int size = this.f11927d.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((Integer) this.f11927d.get(i2)).intValue() == i) {
                    return true;
                }
            }
            return false;
        }
    }

    public C2520d(Context context) {
        super(context);
        m12654a(context);
    }

    public C2520d(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m12654a(context);
    }

    public C2520d(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m12654a(context);
    }

    /* renamed from: a */
    private void m12654a(Context context) {
        this.f11928a = new ListView(context);
        this.f11928a.setCacheColorHint(0);
        this.f11928a.setSelector(new ColorDrawable());
        this.f11928a.setVerticalScrollBarEnabled(false);
        this.f11928a.setOnScrollListener(new C25171(this));
        this.f11928a.setOnItemClickListener(new C25182(this));
        this.f11928a.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.f11928a);
    }

    public void setDividerHeight(int i) {
        this.f11928a.setDividerHeight(i);
    }

    public void setDivider(Drawable drawable) {
        this.f11928a.setDivider(drawable);
    }

    public void setAdapter(C2514a c2514a) {
        this.f11930c = c2514a;
        this.f11929b = new C2519b(c2514a);
        this.f11928a.setAdapter(this.f11929b);
        m12653a();
    }

    public C2514a getAdapter() {
        return this.f11930c;
    }

    /* renamed from: a */
    private void m12653a() {
        if (this.f11931d != null) {
            removeView(this.f11931d);
        }
        if (this.f11929b.getCount() != 0) {
            this.f11931d = this.f11929b.getView(((Integer) this.f11929b.f11926c.get(this.f11929b.m12648a(this.f11932e))).intValue(), null, this);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
            layoutParams.addRule(9);
            layoutParams.addRule(10);
            addView(this.f11931d, layoutParams);
            this.f11931d.measure(0, 0);
            this.f11933f = this.f11931d.getMeasuredHeight();
            m12656b();
        }
    }

    public void setSelection(int i) {
        m12660a(i, -1);
    }

    /* renamed from: a */
    public void m12660a(int i, int i2) {
        this.f11928a.setSelection((((Integer) this.f11929b.f11926c.get(i)).intValue() + i2) + 1);
    }

    /* renamed from: b */
    private void m12656b() {
        LayoutParams layoutParams = (LayoutParams) this.f11931d.getLayoutParams();
        if (this.f11929b.m12650c(this.f11932e)) {
            this.f11930c.mo3517a(this.f11931d, this.f11930c.mo3519b(this.f11929b.m12648a(this.f11932e)));
            int top = this.f11928a.getChildAt(1).getTop();
            if (top < this.f11933f) {
                layoutParams.setMargins(0, top - this.f11933f, 0, 0);
                this.f11931d.setLayoutParams(layoutParams);
                return;
            }
        }
        layoutParams.topMargin = 0;
        this.f11931d.setLayoutParams(layoutParams);
        if (this.f11929b.m12649b(this.f11932e)) {
            this.f11930c.mo3517a(this.f11931d, this.f11930c.mo3519b(this.f11929b.m12648a(this.f11932e)));
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.f11934g = onScrollListener;
    }

    public void setOnItemClickListener(C2508c c2508c) {
        this.f11935h = c2508c;
    }
}
