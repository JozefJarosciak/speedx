package com.beastbikes.android.modules.cycling.activity.ui;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.NumberTextView;
import com.beastbikes.framework.android.p088g.C2801d;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import java.util.ArrayList;
import java.util.List;

/* compiled from: TargetDataPopupWindow */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.b */
public class C1940b extends PopupWindow implements OnItemClickListener {
    /* renamed from: a */
    private Activity f8733a;
    /* renamed from: b */
    private LayoutInflater f8734b;
    /* renamed from: c */
    private ListView f8735c;
    /* renamed from: d */
    private C1939c f8736d;
    /* renamed from: e */
    private C1937a f8737e = new C1937a(this);

    /* compiled from: TargetDataPopupWindow */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.b$a */
    class C1937a extends BaseAdapter {
        /* renamed from: a */
        final /* synthetic */ C1940b f8729a;
        /* renamed from: b */
        private List<Integer> f8730b = new ArrayList();

        public C1937a(C1940b c1940b) {
            this.f8729a = c1940b;
            for (int i = 1; i <= 20; i++) {
                this.f8730b.add(Integer.valueOf(i * 50));
            }
        }

        public int getCount() {
            return this.f8730b.size();
        }

        public Object getItem(int i) {
            return this.f8730b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            C1938b c1938b;
            if (view == null) {
                view = this.f8729a.f8734b.inflate(C1373R.layout.target_data_popup_list_item, null);
                c1938b = new C1938b(this.f8729a, view);
            } else {
                c1938b = (C1938b) view.getTag();
            }
            c1938b.m9981a((Integer) this.f8730b.get(i));
            return view;
        }
    }

    /* compiled from: TargetDataPopupWindow */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.b$b */
    private final class C1938b extends ViewHolder<Integer> {
        /* renamed from: a */
        final /* synthetic */ C1940b f8731a;
        /* renamed from: b */
        private NumberTextView f8732b;

        public /* synthetic */ void bind(Object obj) {
            m9981a((Integer) obj);
        }

        public C1938b(C1940b c1940b, View view) {
            this.f8731a = c1940b;
            super(view);
            this.f8732b = (NumberTextView) view.findViewById(C1373R.id.target_data_popup_item_value);
        }

        /* renamed from: a */
        public void m9981a(Integer num) {
            this.f8732b.setText(String.valueOf(num));
        }
    }

    /* compiled from: TargetDataPopupWindow */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.b$c */
    public interface C1939c {
        /* renamed from: a */
        void m9982a(int i);
    }

    public C1940b(Activity activity, C1939c c1939c) {
        super(activity);
        this.f8736d = c1939c;
        this.f8733a = activity;
        this.f8734b = activity.getLayoutInflater();
        ViewGroup viewGroup = (ViewGroup) this.f8734b.inflate(C1373R.layout.target_data_popup_window, null);
        this.f8735c = (ListView) viewGroup.findViewById(C1373R.id.target_data_popup_list);
        setContentView(viewGroup);
        setWidth(C2801d.m13756a(activity, 160.0f));
        setHeight(C2801d.m13756a(activity, 200.0f));
        setFocusable(true);
        setAnimationStyle(C1373R.style.WindowAnim);
        setBackgroundDrawable(new ColorDrawable(0));
        setOutsideTouchable(true);
        this.f8735c.setAdapter(this.f8737e);
        this.f8735c.setOnItemClickListener(this);
    }

    public void dismiss() {
        super.dismiss();
        m9984a(1.0f);
    }

    public void showAtLocation(View view, int i, int i2, int i3) {
        m9984a(0.5f);
        super.showAtLocation(view, i, i2, i3);
    }

    public void showAsDropDown(View view, int i, int i2, int i3) {
        super.showAsDropDown(view, i, i2, i3);
        m9984a(0.5f);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.f8736d != null) {
            this.f8736d.m9982a(((Integer) this.f8737e.getItem(i)).intValue());
        }
        dismiss();
    }

    /* renamed from: a */
    private void m9984a(float f) {
        LayoutParams attributes = this.f8733a.getWindow().getAttributes();
        attributes.alpha = f;
        this.f8733a.getWindow().setAttributes(attributes);
    }
}
