package com.beastbikes.android.modules.cycling.club.ui.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.utils.C2557f;
import com.beastbikes.framework.ui.android.utils.Toasts;

/* compiled from: SaveImagePopupWindow */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.g */
public class C2159g extends PopupWindow implements OnItemClickListener {
    /* renamed from: a */
    private ListView f10127a;
    /* renamed from: b */
    private LayoutInflater f10128b;
    /* renamed from: c */
    private String f10129c;
    /* renamed from: d */
    private Uri f10130d;
    /* renamed from: e */
    private C2158b f10131e;
    /* renamed from: f */
    private Activity f10132f;

    /* compiled from: SaveImagePopupWindow */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.g$a */
    class C2157a extends BaseAdapter {
        /* renamed from: a */
        final /* synthetic */ C2159g f10125a;
        /* renamed from: b */
        private int[] f10126b = new int[]{C1373R.string.save_image_label, C1373R.string.cancel};

        C2157a(C2159g c2159g) {
            this.f10125a = c2159g;
        }

        public int getCount() {
            return this.f10126b.length;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view != null) {
                return view;
            }
            view = this.f10125a.f10128b.inflate(C1373R.layout.save_image_popup_window_item, null);
            ((TextView) view.findViewById(C1373R.id.save_image_list_view_item)).setText(this.f10126b[i]);
            return view;
        }
    }

    /* compiled from: SaveImagePopupWindow */
    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.g$b */
    public interface C2158b {
        /* renamed from: a */
        void m11079a(Uri uri);
    }

    public C2159g(Activity activity, String str) {
        this.f10132f = activity;
        this.f10129c = str;
        this.f10128b = activity.getLayoutInflater();
        ViewGroup viewGroup = (ViewGroup) this.f10128b.inflate(C1373R.layout.save_image_popup_window, null);
        this.f10127a = (ListView) viewGroup.findViewById(C1373R.id.save_image_list_view);
        this.f10127a.setOnItemClickListener(this);
        setContentView(viewGroup);
        setWidth(-2);
        setHeight(-2);
        setFocusable(true);
        setAnimationStyle(C1373R.style.WindowAnim);
        setBackgroundDrawable(new ColorDrawable(0));
        setSoftInputMode(16);
        setOutsideTouchable(true);
        this.f10127a.setAdapter(new C2157a(this));
        this.f10127a.setOnItemClickListener(this);
        m11081a(0.5f);
    }

    public void dismiss() {
        super.dismiss();
        m11081a(1.0f);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        switch (i) {
            case 0:
                if (this.f10131e != null) {
                    this.f10131e.m11079a(this.f10130d);
                    break;
                } else {
                    m11083b(this.f10129c);
                    break;
                }
        }
        dismiss();
    }

    /* renamed from: a */
    private void m11081a(float f) {
        LayoutParams attributes = this.f10132f.getWindow().getAttributes();
        attributes.alpha = f;
        this.f10132f.getWindow().setAttributes(attributes);
    }

    /* renamed from: a */
    public void m11084a(String str) {
        this.f10129c = str;
    }

    /* renamed from: b */
    private void m11083b(final String str) {
        new AsyncTask<String, Void, String>(this) {
            /* renamed from: b */
            final /* synthetic */ C2159g f10124b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11077a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m11078a((String) obj);
            }

            /* renamed from: a */
            protected String m11077a(String... strArr) {
                return C2557f.m12832a(str, this.f10124b.f10132f);
            }

            /* renamed from: a */
            protected void m11078a(String str) {
                if (!TextUtils.isEmpty(str)) {
                    Toasts.show(this.f10124b.f10132f, (CharSequence) str);
                }
            }
        }.execute(new String[0]);
    }
}
