package com.beastbikes.android.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.lang.ref.WeakReference;

/* compiled from: LoadingDialog */
/* renamed from: com.beastbikes.android.dialog.i */
public class C1802i extends Dialog {
    /* renamed from: a */
    private TextView f8205a;
    /* renamed from: b */
    private String f8206b;
    /* renamed from: c */
    private AnimationDrawable f8207c;

    public C1802i(Context context, String str, boolean z) {
        super(context, C1373R.style.loading_dialog);
        this.f8206b = str;
        setCancelable(z);
    }

    public C1802i(WeakReference<Context> weakReference, String str, boolean z) {
        super((Context) weakReference.get(), C1373R.style.loading_dialog);
        this.f8206b = str;
        setCancelable(z);
    }

    public C1802i(Context context, int i, boolean z) {
        this(context, context.getString(i), z);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1373R.layout.dialog_loading_layout);
        this.f8205a = (TextView) findViewById(C1373R.id.tipTextView);
        this.f8207c = (AnimationDrawable) ((ImageView) findViewById(C1373R.id.loading_img)).getDrawable();
        if (TextUtils.isEmpty(this.f8206b)) {
            this.f8205a.setVisibility(8);
        } else {
            this.f8205a.setText(this.f8206b);
        }
    }

    public void show() {
        super.show();
        if (this.f8207c != null) {
            this.f8207c.start();
        }
    }

    /* renamed from: a */
    public void m9505a(long j, final String str) {
        show();
        this.f8205a.postDelayed(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C1802i f8204b;

            public void run() {
                if (this.f8204b.isShowing()) {
                    this.f8204b.dismiss();
                    Toasts.show(this.f8204b.getContext(), str);
                }
            }
        }, j);
    }

    public void dismiss() {
        if (isShowing()) {
            super.dismiss();
            if (this.f8207c != null) {
                this.f8207c.stop();
            }
        }
    }
}
