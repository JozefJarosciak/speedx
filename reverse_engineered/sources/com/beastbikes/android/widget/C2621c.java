package com.beastbikes.android.widget;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: MaterialDialog */
/* renamed from: com.beastbikes.android.widget.c */
public class C2621c {
    /* renamed from: a */
    OnClickListener f12228a;
    /* renamed from: b */
    OnClickListener f12229b;
    /* renamed from: c */
    private boolean f12230c;
    /* renamed from: d */
    private Context f12231d;
    /* renamed from: e */
    private AlertDialog f12232e;
    /* renamed from: f */
    private C2620a f12233f;
    /* renamed from: g */
    private View f12234g;
    /* renamed from: h */
    private int f12235h;
    /* renamed from: i */
    private CharSequence f12236i;
    /* renamed from: j */
    private int f12237j;
    /* renamed from: k */
    private CharSequence f12238k;
    /* renamed from: l */
    private Button f12239l;
    /* renamed from: m */
    private Button f12240m;
    /* renamed from: n */
    private boolean f12241n = false;
    /* renamed from: o */
    private int f12242o = -1;
    /* renamed from: p */
    private Drawable f12243p;
    /* renamed from: q */
    private View f12244q;
    /* renamed from: r */
    private int f12245r;
    /* renamed from: s */
    private OnDismissListener f12246s;
    /* renamed from: t */
    private int f12247t = -1;
    /* renamed from: u */
    private int f12248u = -1;
    /* renamed from: v */
    private String f12249v;
    /* renamed from: w */
    private String f12250w;
    /* renamed from: x */
    private boolean f12251x = true;

    /* compiled from: MaterialDialog */
    /* renamed from: com.beastbikes.android.widget.c$a */
    private class C2620a {
        /* renamed from: a */
        final /* synthetic */ C2621c f12222a;
        /* renamed from: b */
        private TextView f12223b;
        /* renamed from: c */
        private ViewGroup f12224c;
        /* renamed from: d */
        private TextView f12225d;
        /* renamed from: e */
        private Window f12226e;
        /* renamed from: f */
        private LinearLayout f12227f;

        private C2620a(C2621c c2621c) {
            this.f12222a = c2621c;
            c2621c.f12232e = new Builder(c2621c.f12231d).create();
            c2621c.f12232e.show();
            c2621c.f12232e.getWindow().clearFlags(131080);
            c2621c.f12232e.getWindow().setSoftInputMode(15);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) c2621c.f12231d.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            c2621c.f12232e.getWindow().setLayout((displayMetrics.widthPixels * 3) / 4, -2);
            this.f12226e = c2621c.f12232e.getWindow();
            View inflate = LayoutInflater.from(c2621c.f12231d).inflate(C1373R.layout.layout_materialdialog, null);
            inflate.setFocusable(true);
            inflate.setFocusableInTouchMode(true);
            this.f12226e.setBackgroundDrawableResource(C1373R.drawable.material_dialog_window);
            this.f12226e.setContentView(inflate);
            this.f12223b = (TextView) this.f12226e.findViewById(C1373R.id.title);
            this.f12225d = (TextView) this.f12226e.findViewById(C1373R.id.message);
            this.f12227f = (LinearLayout) this.f12226e.findViewById(C1373R.id.buttonLayout);
            c2621c.f12239l = (Button) this.f12227f.findViewById(C1373R.id.btn_p);
            c2621c.f12240m = (Button) this.f12227f.findViewById(C1373R.id.btn_n);
            this.f12224c = (ViewGroup) this.f12226e.findViewById(C1373R.id.message_content_root);
            if (c2621c.f12234g != null) {
                LinearLayout linearLayout = (LinearLayout) this.f12226e.findViewById(C1373R.id.contentView);
                linearLayout.removeAllViews();
                linearLayout.addView(c2621c.f12234g);
            }
            if (c2621c.f12235h != 0) {
                m13023a(c2621c.f12235h);
            }
            if (c2621c.f12236i != null) {
                m13025a(c2621c.f12236i);
            }
            if (c2621c.f12236i == null && c2621c.f12235h == 0) {
                this.f12223b.setVisibility(8);
            }
            if (c2621c.f12237j != 0) {
                m13027b(c2621c.f12237j);
            }
            if (c2621c.f12238k != null) {
                m13028b(c2621c.f12238k);
            }
            if (c2621c.f12247t != -1) {
                c2621c.f12239l.setVisibility(0);
                c2621c.f12239l.setText(c2621c.f12247t);
                c2621c.f12239l.setOnClickListener(c2621c.f12228a);
                if (C2621c.m13041d()) {
                    c2621c.f12239l.setBackgroundResource(17170445);
                }
            }
            if (c2621c.f12248u != -1) {
                c2621c.f12240m.setVisibility(0);
                c2621c.f12240m.setText(c2621c.f12248u);
                c2621c.f12240m.setOnClickListener(c2621c.f12229b);
                if (C2621c.m13041d()) {
                    c2621c.f12240m.setBackgroundResource(17170445);
                }
            }
            if (!c2621c.m13035a(c2621c.f12249v)) {
                c2621c.f12239l.setVisibility(0);
                c2621c.f12239l.setText(c2621c.f12249v);
                c2621c.f12239l.setOnClickListener(c2621c.f12228a);
                if (C2621c.m13041d()) {
                    c2621c.f12239l.setBackgroundResource(17170445);
                }
            }
            if (!c2621c.m13035a(c2621c.f12250w)) {
                c2621c.f12240m.setVisibility(0);
                c2621c.f12240m.setText(c2621c.f12250w);
                c2621c.f12240m.setOnClickListener(c2621c.f12229b);
                if (C2621c.m13041d()) {
                    c2621c.f12240m.setBackgroundResource(17170445);
                }
            }
            if (c2621c.m13035a(c2621c.f12249v) && c2621c.f12247t == -1) {
                c2621c.f12239l.setVisibility(8);
            }
            if (c2621c.m13035a(c2621c.f12250w) && c2621c.f12248u == -1) {
                c2621c.f12240m.setVisibility(8);
            }
            if (c2621c.f12242o != -1) {
                ((LinearLayout) this.f12226e.findViewById(C1373R.id.material_background)).setBackgroundResource(c2621c.f12242o);
            }
            if (c2621c.f12243p != null) {
                ((LinearLayout) this.f12226e.findViewById(C1373R.id.material_background)).setBackgroundDrawable(c2621c.f12243p);
            }
            if (c2621c.f12244q != null) {
                m13024a(c2621c.f12244q);
            } else if (c2621c.f12245r != 0) {
                m13029c(c2621c.f12245r);
            }
            c2621c.f12232e.setCanceledOnTouchOutside(c2621c.f12230c);
            c2621c.f12232e.setCancelable(c2621c.f12251x);
            if (c2621c.f12246s != null) {
                c2621c.f12232e.setOnDismissListener(c2621c.f12246s);
            }
        }

        /* renamed from: a */
        public void m13023a(int i) {
            this.f12223b.setText(i);
        }

        /* renamed from: a */
        public void m13025a(CharSequence charSequence) {
            this.f12223b.setText(charSequence);
        }

        /* renamed from: b */
        public void m13027b(int i) {
            if (this.f12225d != null) {
                this.f12225d.setText(i);
            }
        }

        /* renamed from: b */
        public void m13028b(CharSequence charSequence) {
            if (this.f12225d != null) {
                this.f12225d.setText(charSequence);
            }
        }

        /* renamed from: a */
        public void m13024a(View view) {
            view.setLayoutParams(new LayoutParams(-1, -1));
            if (view instanceof ListView) {
                C2621c.m13033a((ListView) view);
            }
            LinearLayout linearLayout = (LinearLayout) this.f12226e.findViewById(C1373R.id.message_content_view);
            if (linearLayout != null) {
                linearLayout.removeAllViews();
                linearLayout.addView(view);
            }
            int i = 0;
            while (true) {
                int childCount;
                if (linearLayout != null) {
                    childCount = linearLayout.getChildCount();
                } else {
                    childCount = 0;
                }
                if (i < childCount) {
                    if (linearLayout.getChildAt(i) instanceof AutoCompleteTextView) {
                        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) linearLayout.getChildAt(i);
                        autoCompleteTextView.setFocusable(true);
                        autoCompleteTextView.requestFocus();
                        autoCompleteTextView.setFocusableInTouchMode(true);
                    }
                    i++;
                } else {
                    return;
                }
            }
        }

        /* renamed from: c */
        public void m13029c(int i) {
            this.f12224c.removeAllViews();
            LayoutInflater.from(this.f12224c.getContext()).inflate(i, this.f12224c);
        }

        /* renamed from: a */
        public void m13026a(boolean z) {
            this.f12222a.f12232e.setCanceledOnTouchOutside(z);
        }
    }

    public C2621c(Context context) {
        this.f12231d = context;
    }

    /* renamed from: a */
    public void m13063a() {
        if (this.f12241n) {
            this.f12232e.show();
        } else {
            this.f12233f = new C2620a();
        }
        this.f12241n = true;
    }

    /* renamed from: a */
    public void m13064a(boolean z) {
        this.f12251x = z;
    }

    /* renamed from: a */
    public C2621c m13060a(View view) {
        this.f12244q = view;
        this.f12245r = 0;
        if (this.f12233f != null) {
            this.f12233f.m13024a(this.f12244q);
        }
        return this;
    }

    /* renamed from: b */
    public void m13069b() {
        this.f12232e.dismiss();
    }

    /* renamed from: d */
    private static boolean m13041d() {
        return VERSION.SDK_INT >= 21;
    }

    /* renamed from: a */
    public C2621c m13058a(int i) {
        this.f12235h = i;
        if (this.f12233f != null) {
            this.f12233f.m13023a(i);
        }
        return this;
    }

    /* renamed from: a */
    public C2621c m13061a(CharSequence charSequence) {
        this.f12236i = charSequence;
        if (this.f12233f != null) {
            this.f12233f.m13025a(charSequence);
        }
        return this;
    }

    /* renamed from: b */
    public C2621c m13065b(int i) {
        this.f12237j = i;
        if (this.f12233f != null) {
            this.f12233f.m13027b(i);
        }
        return this;
    }

    /* renamed from: b */
    public C2621c m13067b(CharSequence charSequence) {
        this.f12238k = charSequence;
        if (this.f12233f != null) {
            this.f12233f.m13028b(charSequence);
        }
        return this;
    }

    /* renamed from: a */
    public C2621c m13059a(int i, OnClickListener onClickListener) {
        this.f12247t = i;
        this.f12228a = onClickListener;
        return this;
    }

    /* renamed from: a */
    public C2621c m13062a(String str, OnClickListener onClickListener) {
        this.f12249v = str;
        this.f12228a = onClickListener;
        return this;
    }

    /* renamed from: b */
    public C2621c m13066b(int i, OnClickListener onClickListener) {
        this.f12248u = i;
        this.f12229b = onClickListener;
        return this;
    }

    /* renamed from: b */
    public C2621c m13068b(boolean z) {
        this.f12230c = z;
        if (this.f12233f != null) {
            this.f12233f.m13026a(this.f12230c);
        }
        return this;
    }

    /* renamed from: a */
    private boolean m13035a(String str) {
        return str == null || str.isEmpty();
    }

    /* renamed from: a */
    public static void m13033a(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int i = 0;
            for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                View view = adapter.getView(i2, null, listView);
                view.measure(0, 0);
                i += view.getMeasuredHeight();
            }
            LayoutParams layoutParams = listView.getLayoutParams();
            layoutParams.height = (listView.getDividerHeight() * (adapter.getCount() - 1)) + i;
            listView.setLayoutParams(layoutParams);
        }
    }
}
