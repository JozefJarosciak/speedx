package com.digits.sdk.android;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.digits.sdk.android.C2937y.C2875a;
import io.fabric.sdk.android.services.common.C4877i;
import java.util.List;
import java.util.Locale;

public class CountryListSpinner extends TextView implements OnClickListener, C2875a {
    /* renamed from: a */
    private String f13095a;
    /* renamed from: b */
    private C2874a f13096b;
    /* renamed from: c */
    private C2936x f13097c;
    /* renamed from: d */
    private OnClickListener f13098d;
    /* renamed from: e */
    private String f13099e;

    /* renamed from: com.digits.sdk.android.CountryListSpinner$a */
    public class C2874a implements DialogInterface.OnClickListener {
        /* renamed from: a */
        final /* synthetic */ CountryListSpinner f13092a;
        /* renamed from: b */
        private final C2936x f13093b;
        /* renamed from: c */
        private AlertDialog f13094c;

        C2874a(CountryListSpinner countryListSpinner, C2936x c2936x) {
            this.f13092a = countryListSpinner;
            this.f13093b = c2936x;
        }

        /* renamed from: a */
        public void m13854a() {
            if (this.f13094c != null) {
                this.f13094c.dismiss();
                this.f13094c = null;
            }
        }

        /* renamed from: b */
        public boolean m13856b() {
            return this.f13094c != null && this.f13094c.isShowing();
        }

        /* renamed from: a */
        public void m13855a(final int i) {
            if (this.f13093b != null) {
                this.f13094c = new Builder(this.f13092a.getContext()).setSingleChoiceItems(this.f13093b, 0, this).create();
                this.f13094c.setCanceledOnTouchOutside(true);
                final ListView listView = this.f13094c.getListView();
                listView.setFastScrollEnabled(true);
                listView.postDelayed(new Runnable(this) {
                    /* renamed from: c */
                    final /* synthetic */ C2874a f13091c;

                    public void run() {
                        listView.setSelection(i);
                    }
                }, 10);
                this.f13094c.show();
            }
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            C2935w c2935w = (C2935w) this.f13093b.getItem(i);
            this.f13092a.f13099e = c2935w.f13350a;
            this.f13092a.m13860a(c2935w.f13351b, c2935w.f13350a);
            m13854a();
        }
    }

    public CountryListSpinner(Context context) {
        this(context, null, 16842881);
    }

    public CountryListSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842881);
    }

    public CountryListSpinner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13859a();
    }

    void setDialogPopup(C2874a c2874a) {
        this.f13096b = c2874a;
    }

    /* renamed from: a */
    private void m13859a() {
        super.setOnClickListener(this);
        this.f13097c = new C2936x(getContext());
        this.f13096b = new C2874a(this, this.f13097c);
        this.f13095a = getResources().getString(C2876R.string.dgts__country_spinner_format);
        this.f13099e = "";
        m13860a(1, Locale.US.getDisplayCountry());
    }

    /* renamed from: a */
    private void m13860a(int i, String str) {
        setText(String.format(this.f13095a, new Object[]{str, Integer.valueOf(i)}));
        setTag(Integer.valueOf(i));
    }

    /* renamed from: a */
    public void m13864a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.f13099e = str;
            m13860a(Integer.valueOf(str2).intValue(), str);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f13096b.m13856b()) {
            this.f13096b.m13854a();
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.f13098d = onClickListener;
    }

    public void onClick(View view) {
        if (this.f13097c.getCount() == 0) {
            m13863b();
        } else {
            this.f13096b.m13855a(this.f13097c.m14260a(this.f13099e));
        }
        C4877i.a(getContext(), this);
        m13861a(view);
    }

    /* renamed from: b */
    private void m13863b() {
        new C2937y(this).a(aa.a().k(), new Void[0]);
    }

    /* renamed from: a */
    private void m13861a(View view) {
        if (this.f13098d != null) {
            this.f13098d.onClick(view);
        }
    }

    /* renamed from: a */
    public void mo3631a(List<C2935w> list) {
        this.f13097c.m14261a((List) list);
        this.f13096b.m13855a(this.f13097c.m14260a(this.f13099e));
    }
}
