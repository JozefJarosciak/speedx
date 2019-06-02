package com.beastbikes.android.modules.user.view;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.view.ActionProvider;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

public class RecordMenuActionProvider extends ActionProvider implements OnClickListener {
    /* renamed from: a */
    private TextView f11944a;
    /* renamed from: b */
    private TextView f11945b;
    /* renamed from: c */
    private int f11946c = C1373R.string.str_add_data;
    /* renamed from: d */
    private String f11947d;
    /* renamed from: e */
    private C2525a f11948e;

    /* renamed from: com.beastbikes.android.modules.user.view.RecordMenuActionProvider$a */
    public interface C2525a {
        /* renamed from: a */
        void m12672a();
    }

    public RecordMenuActionProvider(Context context) {
        super(context);
    }

    public View onCreateActionView() {
        View inflate = LayoutInflater.from(getContext()).inflate(C1373R.layout.record_menu_item_action_provider, null);
        this.f11944a = (TextView) inflate.findViewById(C1373R.id.record_menu_item_filter_view);
        this.f11945b = (TextView) inflate.findViewById(C1373R.id.record_menu_item_filter_desc);
        inflate.setOnClickListener(this);
        this.f11944a.setText(this.f11946c);
        if (TextUtils.isEmpty(this.f11947d)) {
            this.f11945b.setVisibility(8);
        } else {
            this.f11945b.setVisibility(0);
            this.f11945b.setText(this.f11947d);
        }
        return inflate;
    }

    public void onClick(View view) {
        if (this.f11948e != null) {
            this.f11948e.m12672a();
        }
    }

    /* renamed from: a */
    public void m12674a(C2525a c2525a) {
        this.f11948e = c2525a;
    }

    /* renamed from: a */
    public void m12673a(@StringRes int i) {
        if (this.f11944a != null) {
            this.f11944a.setText(i);
        }
        this.f11946c = i;
    }

    /* renamed from: a */
    public void m12675a(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f11945b.setVisibility(8);
            return;
        }
        if (this.f11945b != null) {
            this.f11945b.setText(str);
            this.f11945b.setVisibility(0);
        }
        this.f11947d = str;
    }
}
